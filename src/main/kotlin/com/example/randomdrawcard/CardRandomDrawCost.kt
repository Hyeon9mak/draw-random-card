package com.example.randomdrawcard

@JvmInline
value class CardRandomDrawCost(
    val value: Int
) {
    init {
        require(value > 0) { "카드 랜덤 뽑기 비용은 0 보다 커야 합니다." }
    }
}
