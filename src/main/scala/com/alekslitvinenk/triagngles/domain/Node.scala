package com.alekslitvinenk.triagngles.domain

case class Node(value: Int, prevPath: List[Int] = List.empty) {
  
  lazy val path: Seq[Int] = value :: prevPath
  lazy val sum: Int = path.sum
}
