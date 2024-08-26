package com.example.randomdrawcard

@JvmInline
value class CardName(
  val value: String
) {
  init {
    require(value.isNotBlank()) { "카드 이름은 공백일 수 없습니다." }
  }
}