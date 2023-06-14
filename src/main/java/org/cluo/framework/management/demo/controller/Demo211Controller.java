package org.cluo.framework.management.demo.controller;

import org.cluo.framework.management.annotation.CmsController;
import org.cluo.framework.management.annotation.CmsField;
import org.cluo.framework.management.annotation.CmsMapping;
import org.cluo.framework.management.annotation.CmsRequestBody;
import org.cluo.framework.management.model.api.CluoList;
import org.cluo.framework.management.model.common.enums.CmsAction;
import org.cluo.framework.management.model.common.enums.ContentFieldType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author canfuu.cts
 * @class DemoController
 * @date 2023/6/3 13:05
 */
@CmsController(top = "SystemSetting", left = "Auth", leftSub = "UserManagement")
@RequestMapping("/demo211")
public class Demo211Controller {

    private Map<String, UserResponse> users = new HashMap<>();

    /**
     * for search users' list
     * @param userRequest param
     * @return data list
     */
    @CmsMapping(path = "/user", action = CmsAction.ListSelectData)
    public CluoList<UserResponse> userList(@CmsRequestBody UserRequest userRequest) {
        return CluoList.of(users.size(), users.values().stream().filter(userResponse -> {
                    return userRequest.getKeyword() == null || userRequest.getKeyword().isEmpty() || userResponse.nickname.contains(userRequest.getKeyword());
                })
                .filter(
                        userResponse -> {
                            return userRequest.getEmail() == null || userRequest.getEmail().isEmpty() || userResponse.email.contains(userRequest.getEmail());
                        }
                ).collect(Collectors.toList()));
    }


    /**
     * for insert user
     * @param userResponse param
     */
    @CmsMapping(path = "/user/insert", action = CmsAction.ListInsertData)
    public void userInsert(@CmsRequestBody UserResponse userResponse) {
        users.put(userResponse.getId(), userResponse);
    }

    /**
     * for update user
     * @param userResponse param
     */
    @CmsMapping(path = "/user/update", action = CmsAction.ListUpdateData)
    public void userUpdate(@CmsRequestBody UserResponse userResponse) {
        users.put(userResponse.getId(), userResponse);
    }

    /**
     * for delete user
     * @param id param
     */
    @CmsMapping(path = "/user/delete", action = CmsAction.ListDeleteData, keyField = "id")
    public void userDelete(@RequestParam("id") String id) {
        users.remove(id);
    }

    /**
     * for search detail user
     * @param id param
     * @return data
     */
    @CmsMapping(path = "/user/detail", action = CmsAction.ListSelectDetail, keyField = "id")
    public UserResponse userDetail(@RequestParam("id") String id) {
        return users.get(id);
    }

    public static class UserType {
        private String key;
        private String name;
        private String type;

        public String getKey() {
            return key;
        }

        public UserType setKey(String key) {
            this.key = key;
            return this;
        }

        public String getName() {
            return name;
        }

        public UserType setName(String name) {
            this.name = name;
            return this;
        }

        public String getType() {
            return type;
        }

        public UserType setType(String type) {
            this.type = type;
            return this;
        }
    }

    public static class UserRequest {
        private String keyword;
        @CmsField(displayName = "邮箱",regex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+", placeholder = "请输入邮箱信息", tips = "请输入正确的邮箱信息")
        private String email;

        public String getKeyword() {
            return keyword;
        }

        public UserRequest setKeyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public UserRequest setEmail(String email) {
            this.email = email;
            return this;
        }
    }

    public static class UserResponse {

        private String id;

        private String nickname;

        @CmsField(displayName = "邮箱",regex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+", placeholder = "请输入邮箱信息", tips = "请输入正确的邮箱信息")
        private String email;

        @CmsField(type = ContentFieldType.Table, defaultValue = "[{\"key\":\"1\",\"name\":\"\",\"type\":\"\"},{\"key\":\"2\",\"name\":\"\",\"type\":\"\"},{\"key\":\"3\",\"name\":\"\",\"type\":\"\"},{\"key\":\"4\",\"name\":\"\",\"type\":\"\"},{\"key\":\"5\",\"name\":\"\",\"type\":\"\"},{\"key\":\"6\",\"name\":\"\",\"type\":\"\"}]")
        private List<UserType> userTypes;

        public List<UserType> getUserTypes() {
            return userTypes;
        }

        public UserResponse setUserTypes(List<UserType> userTypes) {
            this.userTypes = userTypes;
            return this;
        }

        public String getId() {
            return id;
        }

        public UserResponse setId(String id) {
            this.id = id;
            return this;
        }

        public String getNickname() {
            return nickname;
        }

        public UserResponse setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public UserResponse setEmail(String email) {
            this.email = email;
            return this;
        }
    }
}
