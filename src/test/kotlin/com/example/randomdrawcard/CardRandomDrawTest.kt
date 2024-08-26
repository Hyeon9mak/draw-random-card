package com.example.randomdrawcard

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class CardRandomDrawTest : FreeSpec({

    "카드 랜덤 뽑기를 생성할 수 있다." {
        val cardRandomDraw = CardRandomDraw(
            name = "1 코스트 카드 뽑기",
            cost = CardRandomDrawCost(value = 1),
            ranges = mutableListOf(
                CardRandomDrawRangeByGrade(
                    grade = CardGrade.FIRST,
                    startRange = 1,
                    endRange = 10
                ),
                CardRandomDrawRangeByGrade(
                    grade = CardGrade.SECOND,
                    startRange = 11,
                    endRange = 100
                ),
            )
        )

        cardRandomDraw.name shouldBe "1 코스트 카드 뽑기"
        cardRandomDraw.cost.value shouldBe 1
    }

    "카드 랜덤 뽑기를 진행할 수 있다." {
        val cardRandomDraw = CardRandomDraw(
            name = "1 코스트 카드 뽑기",
            cost = CardRandomDrawCost(value = 1),
            ranges = mutableListOf(
                CardRandomDrawRangeByGrade(
                    grade = CardGrade.FIRST,
                    startRange = 1,
                    endRange = 10
                ),
                CardRandomDrawRangeByGrade(
                    grade = CardGrade.SECOND,
                    startRange = 11,
                    endRange = 100
                ),
            )
        )

        val randomGrade = cardRandomDraw.randomDrawGrade()
        CardGrade.entries.toTypedArray().shouldContain(randomGrade)
    }

    "카드 랜덤 뽑기는 중복되는 뽑기 등급을 가질 수 없다." {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            CardRandomDraw(
                name = "1 코스트 카드 뽑기",
                cost = CardRandomDrawCost(value = 1),
                ranges = mutableListOf(
                    CardRandomDrawRangeByGrade(
                        grade = CardGrade.FIRST,
                        startRange = 1,
                        endRange = 10
                    ),
                    CardRandomDrawRangeByGrade(
                        grade = CardGrade.FIRST,
                        startRange = 11,
                        endRange = 100
                    )
                )
            )
        }

        exception.message shouldBe "중복되는 등급(별)이 존재합니다. 등급(별): 1"
    }

    "카드 랜덤 뽑기는 1~100 사이의 확률 범위를 모두 가져야한다." {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            CardRandomDraw(
                name = "1 코스트 카드 뽑기",
                cost = CardRandomDrawCost(value = 1),
                ranges = mutableListOf(
                    CardRandomDrawRangeByGrade(
                        grade = CardGrade.FIRST,
                        startRange = 1,
                        endRange = 10
                    ),
                    CardRandomDrawRangeByGrade(
                        grade = CardGrade.SECOND,
                        startRange = 11,
                        endRange = 99
                    )
                )
            )
        }

        exception.message shouldBe "확률의 범위는 1~100까지의 숫자로만 이루어질 수 있습니다."
    }

    "카드 랜덤 뽑기는 1~100 사이의 중복 범위를 가질 수 없다." {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            CardRandomDraw(
                name = "1 코스트 카드 뽑기",
                cost = CardRandomDrawCost(value = 1),
                ranges = mutableListOf(
                    CardRandomDrawRangeByGrade(
                        grade = CardGrade.FIRST,
                        startRange = 1,
                        endRange = 10
                    ),
                    CardRandomDrawRangeByGrade(
                        grade = CardGrade.SECOND,
                        startRange = 5,
                        endRange = 100
                    )
                )
            )
        }

        exception.message shouldBe "확률의 범위는 1~100까지의 숫자로만 이루어질 수 있습니다."
    }
})