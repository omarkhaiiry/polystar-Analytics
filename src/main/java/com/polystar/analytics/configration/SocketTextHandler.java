package com.polystar.analytics.configration;

import com.polystar.analytics.model.service.AnalyticsService;
import com.polystar.analytics.template.ClientTemplate;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.LinkedHashMap;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
    AnalyticsService analyticsService = new AnalyticsService();

    public SocketTextHandler() {
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        LinkedHashMap<String,Integer> result = analyticsService.topRepeatedCountWordsConcat(jsonObject.getString("line"));
        System.out.println(result);
        session.sendMessage(new TextMessage(result.toString()));

    }

}