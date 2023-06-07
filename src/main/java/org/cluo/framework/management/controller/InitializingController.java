package org.cluo.framework.management.controller;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.model.common.CmsPublicInfo;
import org.cluo.framework.management.service.CmsSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author canfuu.cts
 * @class InitializingController
 * @date 2023/6/1 23:59
 */
@RestController
@RequestMapping("/cms/initializing")
public class InitializingController {

    @Autowired
    private CmsSystemService cmsSystemService;
    @RequestMapping("/public-info")
    public void publicInfo(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().write(JSONUtil.fromObjectAsBytes(cmsSystemService.publicInfo()));
    }
}
