package com.abandon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 模拟登录验证
 * @author guozx
 * @date 2022/12/13
 */
@Slf4j
@RestController
public class LoginController {

    /**
     * 模拟登录验证
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/check")
    @ResponseBody
    public Map<String, Object> check(@RequestParam(required = true) String username, @RequestParam(required = true) String password) {
        Map<String, Object> result = new LinkedHashMap<>(1);
        log.info(MessageFormat.format("用户名：{0}，密码：{1}", username, password));
        // 模拟登录
        log.info("模拟登录流程");
        result.put("code", 200);
        result.put("msg", "登录成功");
        return result;
    }
}
