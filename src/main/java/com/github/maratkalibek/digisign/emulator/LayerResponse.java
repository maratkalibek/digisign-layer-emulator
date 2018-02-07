package com.github.maratkalibek.digisign.emulator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayerResponse {
  private String result;
  private String errorCode;
}
