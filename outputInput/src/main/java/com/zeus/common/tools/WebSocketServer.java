package com.zeus.common.tools;

import com.zeus.common.Response;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author:tumu
 * @date:2019/8/12
 * @ver:1.0
 **/
@ServerEndpoint(value = "/websocket/{sid}",encoders = {MyEncoder.class})
@Component
public class WebSocketServer {
    private Session session;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet=new CopyOnWriteArraySet<>();
    private static int onlineCount=0;
    private String sid="";


    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid){
        System.out.println(sid);
        this.session = session;
        webSocketSet.add(this);
        //加入set中
        addOnlineCount();
        //在线数加1
        System.out.println("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
         this.sid=sid;
         try {
             sendMessage("连接成功");
         } catch (IOException e) {
             System.out.println("websocket IO异常");
         }
    }
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        //从set中删除
         subOnlineCount();
        // 在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
         for (WebSocketServer item : webSocketSet) {
         try {
         item.sendMessage(message);
         } catch (IOException e) {
         e.printStackTrace();
         }
         }
         }

    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        System.out.println("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }
    public static void sendResponseInfo(Response response, @PathParam("sid") String sid) throws IOException {
        System.out.println("推送消息到窗口"+sid+"，推送内容:"+"Response");
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendResponse(response);
                }else if(item.sid.equals(sid)){
                    item.sendResponse(response);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    public void sendResponse(Response response) throws IOException {
        try {
            this.session.getBasicRemote().sendObject(response);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}

