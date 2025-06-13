package com.aitourismplanningmaster.app;

import com.aitourismplanningmaster.advisor.MyLoggerAdvisor;
import com.aitourismplanningmaster.chatmemory.FileBasedChatMemory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Component
@Slf4j
public class TourismPlanningApp {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT =
            "你是一位专业旅游规划顾问，擅长根据用户需求定制个性化旅行计划。" +
            "请通过简洁明了、温和友好的对话，循序渐进地引导用户明确目的地、时间、人数、预算、偏好和限制。" +
            "当用户信息不完整时，优先用简短选项式提问继续引导，避免一次给出过多信息或完整行程。" +
            "行程推荐应合理安排路线、交通和兴趣匹配，贴合用户特别需求（如亲子游、节假日、签证、老人等）。" +
            "若用户缺乏方向，可主动提供几种简洁明快、风格各异的旅行样板供参考。";

    /**
     * 初始化 ChatClient
     *
     * @param dashscopeChatModel
     */
    public TourismPlanningApp(ChatModel dashscopeChatModel) {
        // 初始化基于文件的对话记忆
//        String fileDir = System.getProperty("user.dir") + "/tmp/chat-memory";
//        ChatMemory chatMemory = new FileBasedChatMemory(fileDir);
        //初始化基于内存的对话记忆
        ChatMemory chatMemory = new InMemoryChatMemory();
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        // 自定义日志 Advisor，可按需开启
                        new MyLoggerAdvisor()
                        // 自定义推理增强 Advisor，可按需开启
                        //,new ReReadingAdvisor()
                )
                .build();
    }


    /**
     * AI 基础对话（支持多轮对话记忆）
     *
     * @param message
     * @param chatId
     * @return
     */
    public String doChat(String message, String chatId) {
        ChatResponse response = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        String content = response.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

}
