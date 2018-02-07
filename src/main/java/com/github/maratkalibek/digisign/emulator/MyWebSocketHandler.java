package com.github.maratkalibek.digisign.emulator;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

  @Autowired
  private XmlSignResponse xmlSignResponse;

  @Override
  public void handleTransportError(WebSocketSession session, Throwable throwable) {
    log.error("error occured at sender " + session, throwable);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    log.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    log.info("Connected ... " + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage inMsg) throws IOException {
    String inStr = inMsg.getPayload();
    log.info("INCOMING: {}", inStr);
    if ("--heartbeat--".equals(inStr)) {
      sendMessage(session,"--heartbeat--");
    } else {
      ObjectMapper mapper = new ObjectMapper();
      LayerRequest request = mapper.reader().forType(LayerRequest.class).readValue(inStr);
      String method = request.getMethod();
      LayerResponse response = new LayerResponse();
      response.setErrorCode("NONE");
      if ("loadSlotList".equals(method)) {
        response.setErrorCode(xmlSignResponse.loadSlotList());
      } else if ("browseKeyStore".equals(method)) {
        response.setResult(xmlSignResponse.browseKeyStore());
      } else if ("getKeys".equals(method)) {
        response.setResult(xmlSignResponse.getKeys());
      } else if ("getSubjectDN".equals(method)) {
        response.setResult(xmlSignResponse.getSubjectDN());
      } else if ("getNotBefore".equals(method)) {
        response.setResult(xmlSignResponse.getNotBefore());
      } else if ("getNotAfter".equals(method)) {
        response.setResult(xmlSignResponse.getNotAfter());
      } else if ("getNotBefore".equals(method)) {
        response.setResult(xmlSignResponse.getNotBefore());
      } else if ("signXml".equals(method)) {
        response.setResult(xmlSignResponse.signXml());
      }
      String msg = mapper.writeValueAsString(response);
      sendMessage(session, msg);
    }
  }

  private void sendMessage(WebSocketSession session, String s) throws IOException {
    session.sendMessage(new TextMessage(s));
  }
}
