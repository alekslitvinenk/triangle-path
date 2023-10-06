package com.alekslitvinenk.triagngles.domain

import cats.data.NonEmptyList

case class Calc(path: NonEmptyList[Int]) {
  val value: Int = path.head
  val sum: Int = path.toList.sum
}
