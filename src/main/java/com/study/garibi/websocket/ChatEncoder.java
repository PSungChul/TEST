package com.study.garibi.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import com.study.garibi.vo.ChatUser;

public class ChatEncoder implements Encoder.Text<ChatUser> {
    @Override
    public String encode(ChatUser chatUser) throws EncodeException {
        Gson gson = new Gson();
        return  gson.toJson(chatUser);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}