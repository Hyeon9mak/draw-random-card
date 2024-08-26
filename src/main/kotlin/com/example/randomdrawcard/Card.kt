package com.example.randomdrawcard

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class Card(  
  @Column(nullable = false)
  var name: CardName,
  
  @Column(nullable = false)  
  @Enumerated(EnumType.STRING)
  var grade: CardGrade,  
) : BaseEntity()
