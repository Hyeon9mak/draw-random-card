package com.example.randomdrawcard

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn

@Entity
class CardRandomDraw(  
  
  @Column(nullable = false)
  val name: String,

  @Column(name = "cost", nullable = false)
  val cost: CardRandomDrawCost,

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "card_random_draw_range_by_grade",
    joinColumns = [JoinColumn(name = "card_random_draw_id")]
  )
  @AttributeOverrides(
    value = [
      AttributeOverride(name = "grade", column = Column(name = "grade", nullable = false)),
      AttributeOverride(name = "startRange", column = Column(name = "start_range", nullable = false)),
      AttributeOverride(name = "endRange", column = Column(name = "end_range", nullable = false))
    ]
  )
  private val ranges: MutableList<CardRandomDrawRangeByGrade> = mutableListOf()
) : BaseEntity() {
  init {  
    validateDuplicateGrade()  
    validatePercentRange()  
  }  
  
  private fun validateDuplicateGrade() {  
    val gradeSet = ranges.map { it.grade.value }.toSet()
    require(gradeSet.size == ranges.size) { "중복되는 등급(별)이 존재합니다. 등급(별): ${gradeSet.joinToString(", ")}" }  
  }  
  
  private fun validatePercentRange() {  
    val percentRange = ranges.map { it.startRange..it.endRange }.flatten()  
    require(percentRange.size == 100) { "확률의 범위는 1~100까지의 숫자로만 이루어질 수 있습니다." }  
  }  
  
  fun randomDrawGrade(): CardGrade {  
    val randomValue = (1..100).random()  
    return ranges.first { it.isInRange(randomValue) }.grade  
  }
}