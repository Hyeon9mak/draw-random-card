package com.example.randomdrawcard

data class CardRandomDrawRangeByGrade(
  val grade: CardGrade,  
  val startRange: Int,  
  val endRange: Int,  
) {
  init {  
    require(startRange <= endRange) { "확률의 시작 범위는 끝 범위보다 작거나 같아야 합니다." }  
    require(startRange >= 1) { "확률의 범위는 1~100까지의 숫자로만 이루어질 수 있습니다." }  
    require(endRange <= 100) { "확률의 범위는 1~100까지의 숫자로만 이루어질 수 있습니다." }  
  }  
  
  fun isInRange(value: Int): Boolean = value in startRange..endRange  
}