package com.example.randomdrawcard

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardNameTest : StringSpec({

    "카드 이름을 생성할 수 있다." {
        val cardName = "카드 이름"
        val name = CardName(value = cardName)
        name.value shouldBe cardName
    }

    "카드 이름은 공백일 수 없다." {
        val exception = shouldThrowExactly<IllegalArgumentException> { CardName(value = " ") }
        exception.message shouldBe "카드 이름은 공백일 수 없습니다."
    }
})
