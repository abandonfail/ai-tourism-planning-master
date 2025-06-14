package com.aitourismplanningmaster.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChenManusTest {

    @Resource
    private ChenManus chenManus;

    @Test
    public void run() {
        String userPrompt = """
        我计划和家人一起在上海市静安区附近旅游，请帮我查找 5 公里范围内的热门景点、美食和休闲娱乐项目，
        并结合网络上的图片和地图信息，制定一份详细的一日游旅游行程计划，
        最后以 PDF 格式输出，便于我保存和打印。
        """;

        String answer = chenManus.run(userPrompt);
        Assertions.assertNotNull(answer);
    }
}