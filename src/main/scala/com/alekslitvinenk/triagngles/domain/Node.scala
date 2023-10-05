package com.alekslitvinenk.triagngles.domain

case class Node(value: Int, path: Array[Int]) {
  val sum: Int = if(path.isEmpty) value else path.sum + value
}
