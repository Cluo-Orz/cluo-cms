package org.cluo.framework.management.demo.controller;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.annotation.CmsController;
import org.cluo.framework.management.annotation.CmsMapping;
import org.cluo.framework.management.annotation.CmsRequestBody;
import org.cluo.framework.management.model.common.enums.CmsAction;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author canfuu.cts
 * @class DemoController
 * @date 2023/6/3 13:05
 */
@CmsController(top = "系统设置1", left = "用户2", leftSub = "用户信息管理1")
@RequestMapping("/demo121")
public class Demo121Controller {

    @CmsMapping(path = "/user", action = CmsAction.ListSelectData)
    public List<UserResponse> userList(@CmsRequestBody UserRequest userRequest) {
        System.out.println(JSONUtil.fromObjectAsString(userRequest));
        return List.of(new UserResponse().setId("2").setNickname("李四").setGender("男").setEmail("b@163.com").setPhone("12345678902"));
    }

    public static class UserRequest {
        private String keyword;
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
        private String username;
        private String gender;
        private String password;
        private String nickname;
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
