package com.thoughtworks.traing.chensen.todoservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtworks.traing.chensen.todoservice.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SpellCheckService {

    @Autowired
    private SpellChecker spellChecker;


//    @Retryable(maxAttempts = 2, backoff = @Backoff(20))
    @HystrixCommand(fallbackMethod = "checkFallback")
    public List<Todo> check(List<Todo> todos) {
        spellChecker.check(todos, Todo::getContent, Todo::setSuggestion);
        log.info("checkspell success");
        return todos;
    }

    public List<Todo> checkFallback(List<Todo> todos) {
        log.info("checkFallBack");
        return todos;
    }


//    @Recover
//    public List<Todo> onFail(RuntimeException e, List<Todo> todos) {
//        log.info("checkspell fail {}", e.getMessage());
//        return todos;
//    }
}
