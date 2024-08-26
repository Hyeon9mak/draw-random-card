package com.example.randomdrawcard

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CardRandomDrawRangeByGradeTest : FreeSpec({

    "카드 등급별 랜덤 뽑기 범위를 생성할 수 있다." {
        val cardRandomDrawRangeByGrade = CardRandomDrawRangeByGrade(
            grade = CardGrade.FIRST,
            startRange = 1,
            endRange = 10
        )
        cardRandomDrawRangeByGrade.grade shouldBe CardGrade.FIRST
        cardRandomDrawRangeByGrade.startRange shouldBe 1
        cardRandomDrawRangeByGrade.endRange shouldBe 10
    }

    "카드 등급별 랜덤 뽑기 확률 시작 값은 0 보다 커야 한다." {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            CardRandomDrawRangeByGrade(
                grade = CardGrade.FIRST,
                startRange = 0,
                endRange = 10,
            )
        }

        exception.message shouldBe "확률의 범위는 1~100까지의 숫자로만 이루어질 수 있습니다."
    }

    "카드 등급별 랜덤 뽑기 확률 끝 값은 100 보다 작거나 같아야 한다." {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            CardRandomDrawRangeByGrade(
                grade = CardGrade.FIRST,
                startRange = 1,
                endRange = 101,
            )
        }

        exception.message shouldBe "확률의 범위는 1~100까지의 숫자로만 이루어질 수 있습니다."
    }

    "카드 등급별 랜덤 뽑기 확률 시작 값은 끝 값보다 작거나 같아야 한다." {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            CardRandomDrawRangeByGrade(
                grade = CardGrade.FIRST,
                startRange = 11,
                endRange = 10,
            )
        }

        exception.message shouldBe "확률의 시작 범위는 끝 범위보다 작거나 같아야 합니다."
    }
})