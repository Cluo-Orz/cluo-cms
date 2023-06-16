package org.cluo.framework.management.support;

import org.cluo.framework.management.annotation.CmsController;
import org.cluo.framework.management.annotation.CmsField;
import org.cluo.framework.management.annotation.CmsMapping;
import org.cluo.framework.management.annotation.CmsRequestBody;
import org.cluo.framework.management.exception.CmsMappingNotFoundException;
import org.cluo.framework.management.exception.CmsReturnTypeException;
import org.cluo.framework.management.exception.LeftSubDuplicatedException;
import org.cluo.framework.management.model.api.CluoList;
import org.cluo.framework.management.model.common.ActionProps;
import org.cluo.framework.management.model.common.CmsBarInfo;
import org.cluo.framework.management.model.common.CmsContentConfig;
import org.cluo.framework.management.model.common.KeyLabel;
import org.cluo.framework.management.model.common.enums.CmsAction;
import org.cluo.framework.management.model.common.enums.ContentType;
import org.cluo.framework.management.model.common.params.ActionFieldModel;
import org.cluo.framework.management.model.common.params.ActionModel;
import org.cluo.framework.management.util.PinyinConverter;
import org.cluo.framework.reflection.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class ControllerCmsApiScanner implements ApplicationRunner {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Autowired
    private CmsApi cmsApi;


    private final static Object lock = new Object();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        List<Future<?>> futures = new ArrayList<>();

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            // 调用线程池, 异步调用parseMappingInfo方法
            Future<?> future = threadPoolExecutor.submit(() -> parseMappingInfo(entry));
            futures.add(future);
        }

        try {
            // 等待所有任务执行完
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException e) {
            // 当主线程被中断时，取消所有任务并中断线程池
            threadPoolExecutor.shutdownNow();
            Thread.currentThread().interrupt(); // 重新设置中断状态
        } catch (ExecutionException e) {
            // 子线程抛出异常，将其包装并抛出到主线程
            threadPoolExecutor.shutdownNow();
            Throwable cause = e.getCause();
            if (cause instanceof Exception) {
                throw (Exception) cause;
            } else {
                throw new RuntimeException(cause);
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    private void parseMappingInfo(Map.Entry<RequestMappingInfo, HandlerMethod> entry) {
        RequestMappingInfo requestMappingInfo = entry.getKey();
        HandlerMethod handlerMethod = entry.getValue();

        CmsController cmsController = handlerMethod.getBeanType().getAnnotation(CmsController.class);

        if (cmsController == null) {
            return;
        }

        CmsMapping mapping = handlerMethod.getMethodAnnotation(CmsMapping.class);

        if (mapping == null) {
            return;
        }

        MethodParameter bodyParameter = null;
        CmsRequestBody bodyAnnotation = null;
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            bodyAnnotation = methodParameter.getParameterAnnotation(CmsRequestBody.class);
            if (bodyAnnotation != null) {
                bodyParameter = methodParameter;
                break;
            }
        }

        register(requestMappingInfo, handlerMethod, cmsController, mapping, bodyAnnotation, bodyParameter);
    }

    private synchronized void register(
            RequestMappingInfo mappingInfo,
            HandlerMethod handlerMethod,
            CmsController cmsController,
            CmsMapping cmsMapping,
            CmsRequestBody cmsRequestBody,
            MethodParameter bodyParameter
    ) {
        String topBarTitle = cmsController.top();
        String sideBarTitle = cmsController.left();
        String subSideBarTitle = cmsController.leftSub();
        String topBarKey = PinyinConverter.convertToPinyin(topBarTitle);
        String sideBarKey =topBarKey+"_" +PinyinConverter.convertToPinyin(sideBarTitle);
        String subSideBarKey = sideBarKey+"_"+ PinyinConverter.convertToPinyin(subSideBarTitle);
        CmsBarInfo barInfo = cmsApi.computeIfAbsent(topBarKey, new CmsBarInfo());
        CmsContentConfig cmsContentConfig = cmsApi.computeIfAbsent(subSideBarKey, new CmsContentConfig());



        synchronized (lock) {

            if(cmsController.isDefault() || !StringUtils.hasText(barInfo.getDefaultSideBar())){
                barInfo.setDefaultSideBar(sideBarKey);
                barInfo.setDefaultSideBarTitle(sideBarTitle);
                barInfo.setDefaultSubSideBar(subSideBarKey);
                barInfo.setDefaultSubSideBarTitle(subSideBarTitle);
            }

            if(cmsController.isDefault() || !StringUtils.hasText(cmsApi.getDefaultTopBar())){
                cmsApi.setDefaultTopBar(topBarKey);
                cmsApi.setDefaultTopBarTitle(topBarTitle);
            }

            if(StringUtils.hasText(cmsApi.getDefaultTopBar()) && cmsApi.getDefaultTopBar().equals(topBarKey)){
                cmsApi.setDefaultTopBar(topBarKey);
                cmsApi.setDefaultTopBarTitle(topBarTitle);
            }



            if (barInfo.getTopBar() == null) {
                barInfo.setTopBar(new KeyLabel(topBarKey, topBarTitle));
            }

            List<KeyLabel> sideBars = barInfo.getSideBars();

            KeyLabel keyLabel = null;
            for (KeyLabel sideBar : sideBars) {
                if (sideBar.getKey().equals(sideBarKey)) {
                    keyLabel = sideBar;
                    break;
                }
            }

            if (keyLabel == null) {
                keyLabel = new KeyLabel(
                        sideBarKey,
                        sideBarTitle,
                        cmsController.icon()
                );
                sideBars.add(keyLabel);
            }


            List<KeyLabel> subSideBars = keyLabel.getChildren();
            if(subSideBars==null){
                subSideBars = new ArrayList<>();
                keyLabel.setChildren(subSideBars);
            }

            KeyLabel subKeyLabel = null;

            for (KeyLabel subSideBar : subSideBars) {
                if (subSideBar.getKey().equals(subSideBarKey)) {
                    subKeyLabel = subSideBar;
                    break;
                }
            }

            if (subKeyLabel == null) {
                subSideBars.add(new KeyLabel(subSideBarKey, subSideBarTitle));

                cmsContentConfig.setType(cmsController.type());
            }

        }

        List<ActionFieldModel> actionFieldModels = new ArrayList<>();

        ActionProps props = new ActionProps();

        List<ActionFieldModel> actionParamModels = new ArrayList<>();

        MethodParameter returnTypeParam = handlerMethod.getReturnType();
        Type returnType = returnTypeParam.getGenericParameterType();
        Class<?> returnClass = returnTypeParam.getParameterType();
        if(cmsMapping.action()[0].equals(CmsAction.ListSelectData) && !returnClass.isAssignableFrom(List.class) && !returnClass.isAssignableFrom(CluoList.class)){
            throw new CmsReturnTypeException("CmsController返回类型必须是List或者CmsListModel" + handlerMethod.getMethod().toGenericString());
        }
        if(returnClass.isAssignableFrom(CluoList.class)){
            props.setHasPagination(true);
        }

        if (((Type)returnType) instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) (Type)returnType;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();

            if (typeArguments.length > 0) {
                Type typeArgument = typeArguments[0];
                if (typeArgument instanceof Class) {
                    returnClass = (Class<?>) typeArgument;
                }
            }
        }

        ReflectUtil.getFields(returnClass).forEach(field -> {
            actionFieldModels.add(buildFieldModel(field));
        });


        if(bodyParameter != null) {
            Class<?> parameterClass = bodyParameter.getParameterType();

            ReflectUtil.getFields(parameterClass).forEach(field -> {
                actionParamModels.add(buildFieldModel(field));
            });



        }

        for (CmsAction cmsAction : cmsMapping.action()) {
            cmsContentConfig.getActions().add(ActionModel.build(mappingInfo.getDirectPaths().stream().findFirst().orElseThrow(CmsMappingNotFoundException::new))
                    .setMethod("post")
                    .setContentType(cmsMapping.fileUpload()? ContentType.MULTIPART_FORM_DATA:ContentType.APPLICATION_JSON)
                    .setAction(cmsAction.name())
                    .setParams(actionParamModels)
                    .setKeyField(cmsMapping.keyField())
                    .setFields(actionFieldModels)
                    .setDefaultPage(cmsMapping.defaultPage())
                    .setDefaultSize(cmsMapping.defaultSize())
                    .setProps(props)
            );
        }

        System.out.println("==========");
        System.out.println(handlerMethod.getBeanType().getName());
        System.out.println(handlerMethod.getMethod().getName());
        System.out.println(mappingInfo);
        System.out.println(PinyinConverter.convertToPinyin(cmsController.top()));
    }

    public ActionFieldModel buildFieldModel(Field field) {
        CmsField cmsField = field.getAnnotation(CmsField.class);
        if (cmsField != null) {
            return new ActionFieldModel()
                    .setPlaceholder(cmsField.placeholder())
                    .setName(StringUtils.hasText(cmsField.name()) ? cmsField.name() : field.getName())
                    .setType(cmsField.type())
                    .setDefaultValue(cmsField.defaultValue())
                    .setRegex(cmsField.regex())
                    .setTips(cmsField.tips())
                    .setDisplayName(cmsField.displayName())
                    .setRequired(cmsField.required())
                    .setDataUrl(cmsField.dataUrl())
                    .setFileSuffix(cmsField.fileSuffix())
                    .setFileCount(cmsField.fileCount());
        } else {
            return new ActionFieldModel()
                    .setPlaceholder("请输入" + field.getName())
                    .setDisplayName(field.getName())
                    .setName(field.getName());
        }
    }
}