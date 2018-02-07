package com.github.maratkalibek.digisign.emulator;

import lombok.Data;

import java.util.List;

@Data
public class LayerRequest {
  public String method;
  public List<String> args;
}
