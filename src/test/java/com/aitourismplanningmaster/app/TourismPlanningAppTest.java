package com.aitourismplanningmaster.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


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

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
//        String message = "情侣旅行怎么选目的地更有氛围？";
        String message = "我正在谈恋爱，打算去旅游，怎么选目的地有氛围一点";
        String answer =  tourismPlanningApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithTools() {
        // 测试联网搜索问题的答案
//        testMessage("周末想带女朋友去上海约会，推荐几个适合情侣的小众打卡地？");

        // 测试网页抓取：恋爱案例分析
//        testMessage("最近和对象吵架了，看看编程导航网站（codefather.cn）的其他情侣是怎么解决矛盾的？");

        // 测试资源下载：图片下载
        testMessage("直接下载一张适合做手机壁纸的星空情侣图片为文件");

        // 测试终端操作：执行代码
//        testMessage("执行 Python3 脚本来生成数据分析报告");

        // 测试文件操作：保存用户档案
//        testMessage("保存我的恋爱档案为文件");

        // 测试 PDF 生成
//        testMessage("生成一份‘七夕约会计划’PDF，包含餐厅预订、活动流程和礼物清单");
    }

    private void testMessage(String message) {
        String chatId = UUID.randomUUID().toString();
        String answer = tourismPlanningApp.doChatWithTools(message, chatId);
        Assertions.assertNotNull(answer);
    }
}