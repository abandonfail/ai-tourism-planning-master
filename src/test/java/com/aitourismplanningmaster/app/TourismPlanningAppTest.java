package com.aitourismplanningmaster.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourismPlanningAppTest {
    @Resource
    private TourismPlanningApp tourismPlanningApp;

    @Test
    void doChat() {
        // 生成唯一会话ID，保持同一对话上下文
        String chatId = UUID.randomUUID().toString();

        // 第一轮对话：初次打招呼
        String message = "你好，我想规划一次旅行";
        String answer = tourismPlanningApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);

        // 第二轮对话：用户表达初步需求
//        message = "我计划今年暑假去欧洲玩，大概10天左右";
//        answer = tourismPlanningApp.doChat(message, chatId);
//        Assertions.assertNotNull(answer);
//
//        // 第三轮对话：用户询问AI是否记得之前的内容
//        message = "你还记得我说的去欧洲旅游吗？";
//        answer = tourismPlanningApp.doChat(message, chatId);
//        Assertions.assertNotNull(answer);
    }
}