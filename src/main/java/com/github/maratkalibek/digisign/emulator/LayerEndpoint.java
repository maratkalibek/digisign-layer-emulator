package com.github.maratkalibek.digisign.emulator;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class LayerEndpoint {

  @MessageMapping("/")
  @SendTo("/")
  public LayerResponse process(LayerRequest request) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new LayerResponse("OK", "OK");
  }

}
