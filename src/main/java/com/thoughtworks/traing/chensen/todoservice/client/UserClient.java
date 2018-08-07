package com.thoughtworks.traing.chensen.todoservice.client;


import com.thoughtworks.traing.chensen.todoservice.dto.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user", url="http://localhost:8081")
public interface UserClient {
    @PostMapping("/verification")
    User verifyToken(String token);
}
