package com.aitourismplanningmaster.controller;

import com.aitourismplanningmaster.agent.ChenManus;
import com.aitourismplanningmaster.app.TourismPlanningApp;
import com.aitourismplanningmaster.common.BaseResponse;
import com.aitourismplanningmaster.common.ResultUtils;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {
    @Resource
    private TourismPlanningApp tourismPlanningApp;

    @Resource
    private ToolCallback[] allTools;

    @Resource
    private ChatModel dashscopeChatModel;

    /**
     * SSE 流式调用 AI 旅游规划大师应用
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/tourism_app/chat/sse",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> doChatWithTourismPlanningAppSSE(String message, String chatId){
        return tourismPlanningApp.doChatByStream(message,chatId);
    }

    /**
     * 同步调用 AI 旅游规划大师应用
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping("/tourism_app/chat/sync")
    public BaseResponse<String> doChatWithTourismPlanningAppSync(String message, String chatId){
        return ResultUtils.success(tourismPlanningApp.doChat(message,chatId));
    }

    /**
     * 流式调用 Manus 超级智能体
     *
     * @param message
     * @return
     */
    @GetMapping("/manus/chat")
    public SseEmitter doChatWithManus(String message) {
        ChenManus chenManus = new ChenManus(allTools, dashscopeChatModel);
        return chenManus.runStream(message);
    }
}
