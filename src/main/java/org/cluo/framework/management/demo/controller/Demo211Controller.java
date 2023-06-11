package org.cluo.framework.management.demo.controller;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.annotation.CmsController;
import org.cluo.framework.management.annotation.CmsField;
import org.cluo.framework.management.annotation.CmsMapping;
import org.cluo.framework.management.annotation.CmsRequestBody;
import org.cluo.framework.management.model.api.CluoList;
import org.cluo.framework.management.model.common.enums.CmsAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author canfuu.cts
 * @class DemoController
 * @date 2023/6/3 13:05
 */
@CmsController(top = "系统设置2", left = "用户1", leftSub = "用户信息管理1")
@RequestMapping("/demo211")
public class Demo211Controller {

    private Map<String, UserResponse> users = new HashMap<>();


    @PostConstruct
    public void init() {
        List.of(new UserResponse().setId("1").setNickname("张三").setGender("男").setEmail("aaa@163.com").setPhone(new Random().nextInt(10000)+"").setUsername(new Random().nextInt(100000)+""),
                new UserResponse().setId("2").setNickname("李四").setGender("男").setEmail("aab@163.com").setPhone(new Random().nextInt(10000)+"").setUsername(new Random().nextInt(100000)+""),
                new UserResponse().setId("3").setNickname("王五").setGender("男").setEmail("abb@1163.com").setPhone(new Random().nextInt(10000)+"").setUsername(new Random().nextInt(100000)+""),
                new UserResponse().setId("4").setNickname("张三").setGender("男").setEmail("abc@163.com").setPhone(new Random().nextInt(10000)+"").setUsername(new Random().nextInt(100000)+""),
                new UserResponse().setId("5").setNickname("李四").setGender("男").setEmail("acc@163.com").setPhone(new Random().nextInt(10000)+"").setUsername(new Random().nextInt(100000)+""),
                new UserResponse().setId("6").setNickname("王五").setGender("男").setEmail("acb@1163.com").setPhone(new Random().nextInt(10000)+"").setUsername(new Random().nextInt(100000)+"")).forEach(userResponse -> {
                    users.put(userResponse.getId(), userResponse);
        });
    }
    @CmsMapping(path = "/user", action = CmsAction.ListSelectData)
    public CluoList<UserResponse> userList(@CmsRequestBody UserRequest userRequest) {
        return CluoList.of(users.size(), users.values().stream().filter(userResponse -> {
                    return userRequest.getKeyword() == null || userRequest.getKeyword().isEmpty() || userResponse.nickname.contains(userRequest.getKeyword());
                })
                .filter(
                        userResponse -> {
                            return userRequest.getEmail() == null || userRequest.getEmail().isEmpty() || userResponse.email.contains(userRequest.getEmail());
                        }
                )
                .filter(
                        userResponse -> {
                            return userRequest.getGender() == null || userRequest.getGender().isEmpty() || userResponse.gender.contains(userRequest.getGender());
                        }
                ).collect(Collectors.toList()));
    }



    @CmsMapping(path = "/user/insert", action = CmsAction.ListInsertData)
    public void userInsert(@CmsRequestBody UserResponse userResponse) {
        users.put(userResponse.getId(), userResponse);
    }

    @CmsMapping(path = "/user/update", action = CmsAction.ListUpdateData)
    public void userUpdate(@CmsRequestBody UserResponse userResponse) {
        users.put(userResponse.getId(), userResponse);
    }

    @CmsMapping(path = "/user/delete", action = CmsAction.ListDeleteData, keyField = "id")
    public void userDelete(@RequestParam("id") String id) {
        users.remove(id);
    }

    @CmsMapping(path = "/user/detail", action = CmsAction.ListSelectDetail, keyField = "id")
    public UserResponse userDetail(@RequestParam("id") String id) {
        return users.get(id);
    }


    public static class UserRequest {
        private String keyword;
        @CmsField(displayName = "邮箱",regex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+", placeholder = "请输入邮箱信息", tips = "请输入正确的邮箱信息")
        private String email;
        private String gender;

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

        public String getGender() {
            return gender;
        }

        public UserRequest setGender(String gender) {
            this.gender = gender;
            return this;
        }
    }

    public static class UserResponse {

        private String id;

        @CmsField(displayName = "用户名")
        private String username;
        private String gender;
        private String password;
        private String nickname;

        @CmsField(displayName = "邮箱",regex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+", placeholder = "请输入邮箱信息", tips = "请输入正确的邮箱信息")
        private String email;
        private String phone;

        public String getId() {
            return id;
        }

        public UserResponse setId(String id) {
            this.id = id;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public UserResponse setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public UserResponse setPassword(String password) {
            this.password = password;
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

        public String getPhone() {
            return phone;
        }

        public UserResponse setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public UserResponse setGender(String gender) {
            this.gender = gender;
            return this;
        }
    }
}
