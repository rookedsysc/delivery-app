package org.delivery.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreCategory {
  // 중식
  CHINESE_FOOD("중식", "CHINESE_FOOD"),
  // 양식
  WESTERN_FOOD("양식", "WESTERN_FOOD"),
  // 한식
  KOREAN_FOOD("한식", "KOERAN_FOOD"),
  // 일식
  JAPANESE_FOOD("일식", "JAPESE_FOOD"),
  // 치킨
  CHICKEN("치킨", "CHICKEN"),
  // 피자 
  PIZZA("피자", "PIZZA"),
  // 햄버거 
  HAMBURGER("햄버거", "HAMBUGER"),
  // 커피&차
  COFFEE("커피&차", "COFFEE"),
  ;

  private String display;
  private String description;
}
