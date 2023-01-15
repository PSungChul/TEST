package com.study.garibi.websocket;

import com.google.gson.Gson;
import com.study.garibi.vo.ChatUser;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatDecoder implements Decoder.Text<ChatUser> {

    @Override
    public ChatUser decode(String s) throws DecodeException {
        Gson gson = new Gson();
        return gson.fromJson(s,ChatUser.class);
    }

    @Override //decode메소드 이전에 실행
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}