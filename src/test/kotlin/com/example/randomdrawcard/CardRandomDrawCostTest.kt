package com.example.randomdrawcard

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardRandomDrawCostTest : StringSpec({

        "카드 랜덤 뽑기 비용을 생성할 수 있다." {
            val randomDrawCost = CardRandomDrawCost(value = 100)
            randomDrawCost.value shouldBe 100
        }

        "카드 랜덤 뽑기 비용은 0 보다 커야 한다." {
            val exception = shouldThrowExactly<IllegalArgumentException> { CardRandomDrawCost(value = -1) }
            exception.message shouldBe "카드 랜덤 뽑기 비용은 0 보다 커야 합니다."
        }
})