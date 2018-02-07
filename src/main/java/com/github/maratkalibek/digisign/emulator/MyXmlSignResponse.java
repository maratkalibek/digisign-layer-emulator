package com.github.maratkalibek.digisign.emulator;

import org.springframework.stereotype.Component;

@Component
public class MyXmlSignResponse implements XmlSignResponse {

  @Override
  public String loadSlotList() {
    return "ERROR";
  }

  @Override
  public String browseKeyStore() {
    return "/home/user/AUTH_RSA.p12";
  }

  @Override
  public String getKeys() {
    return "RSA|ТЕСТ ТЕСТОВ|1234567890123456789012345678901234567890|1234567890123456789012345678901234567890";
  }

  @Override
  public String getSubjectDN() {
    // TODO: ПОСТАВИТЬ РЕАЛЬНЫЕ ЗНАЧЕНИЯ
    return "CN=ТЕСТ ТЕСТОВ,SURNAME=ТЕСТОВ,SERIALNUMBER=IIN123456789012,C=KZ,L=АСТАНА,S=АСТАНА,G=ТЕСТВОВИЧ,E=TEST@TEST.KZ";
  }

  @Override
  public String getNotBefore() {
    // 01.02.2018 18:19:20
    return "01.02.2018 18:19:20";
  }

  @Override
  public String getNotAfter() {
    // 01.02.2019 18:19:20
    return "01.02.2019 18:19:20";
  }

  @Override
  public String signXml() {
    // TODO: ПОСТАВИТЬ РЕАЛЬНЫЕ ЗНАЧЕНИЯ
    return "<a></a>";
  }
}
