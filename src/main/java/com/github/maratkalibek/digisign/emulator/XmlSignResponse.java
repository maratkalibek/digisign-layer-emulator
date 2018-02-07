package com.github.maratkalibek.digisign.emulator;

public interface XmlSignResponse {
  String loadSlotList();
  String browseKeyStore();
  String getKeys();
  String getSubjectDN();
  String getNotBefore();
  String getNotAfter();
  String signXml();
}
