package org.cluo.framework.management.demo.controller;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.annotation.CmsController;
import org.cluo.framework.management.annotation.CmsMapping;
import org.cluo.framework.management.annotation.CmsRequestBody;
import org.cluo.framework.management.model.api.CluoList;
import org.cluo.framework.management.model.common.enums.CmsAction;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author canfuu.cts
 * @class DemoController
 * @date 2023/6/3 13:05
 */
@CmsController(top = "系统设置1", left = "用户2", leftSub = "用户信息管理2")
@RequestMapping("/demo122")
public class Demo122Controller {

    @CmsMapping(path = "/user", action = CmsAction.ListSelectData)
    public CluoList<UserResponse> userList(@CmsRequestBody UserRequest userRequest) {
        System.out.println(JSONUtil.fromObjectAsString(userRequest));
        return CluoList.of(
                50L,
                new UserResponse().setId("1").setNickname("张三").setGender("男").setEmail("a@163.com").setPhone("12345678901"),
                new UserResponse().setId("2").setNickname("李四").setGender("男").setEmail("b@163.com").setPhone("12345678902"),
                new UserResponse().setId("3").setNickname("王五").setGender("男").setEmail("c@1163.com").setPhone("12345678903"),
                new UserResponse().setId("1").setNickname("张三").setGender("男").setEmail("a@163.com").setPhone("12345678901"),
                new UserResponse().setId("2").setNickname("李四").setGender("男").setEmail("b@163.com").setPhone("12345678902"),
                new UserResponse().setId("3").setNickname("王五").setGender("男").setEmail("c@1163.com").setPhone("12345678903")
        );
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