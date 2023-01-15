package com.study.garibi.websocket;

import com.study.garibi.vo.ChatUser;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
@Component
@ServerEndpoint(value = "/{userId}", encoders = {ChatEncoder.class}, decoders = {ChatDecoder.class})
//value에는 연결할 url "/chat으로 시작하는 모든 url이 연결 가능하다.
//예) /chat/id1, /chat/id2 모두 가능
// {userId}는 @pathParam을 통해 파라미터로 사용가능
//사용할 encoder와 decoder 설정
public class ChatEndPoint {
    private Session session;
    private static Set<ChatEndPoint> users = new CopyOnWriteArraySet<>();

    @OnOpen
    public void chatOpen(Session session, @PathParam(value="userId") String userId) {
        this.session = session;
        users.add(this);;
        ChatUser chatUser = new ChatUser();
        chatUser.setUserId(userId);
        chatUser.setMessage(userId + "님이 입장하셨습니다.");
        broadcast(chatUser);
        System.out.println("chat opened - user : " + userId + "entered");
    }

    @OnMessage
    public void chatMessage(ChatUser chatUser,  @PathParam(value="userId") String userId) {
        System.out.println(chatUser.getUserId() + " : " + chatUser.getMessage());
        broadcast(chatUser);
    }

    @OnClose
    public void chatClose(Session session, @PathParam(value="userId") String userId) {
        users.remove(this);
        System.out.println("chat close - user :" + userId + "leaved");
    }

    @OnError
    public void chatError (Session session, Throwable throwable, @PathParam(value="userId") String userId) {
        users.remove(this);
        System.out.println("chat close - user :" + session.getId() + "leaved by coummunication error");
    }

    public void broadcast(ChatUser chatUser) {
        try {
            for (ChatEndPoint user : users) {
                user.session.getBasicRemote().sendObject(chatUser);
            }
        } catch (Exception e) {
            System.out.println("failed to broadcast");
        }
    }
}