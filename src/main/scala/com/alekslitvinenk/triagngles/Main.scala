package com.alekslitvinenk.triagngles

import com.alekslitvinenk.triagngles.domain.Node

import scala.io.Source

object Main extends App {
  
  private val source = Source.fromResource("data_small.txt")
  private val source2 = Source.fromInputStream(System.in)
  
  private val lines = source.getLines()
  private val previous = Array(Node(lines.next().toInt))
  
  val tree = lines.foldLeft(previous) { (prev, line) =>
    val row = line.split(" ").map(_.toInt)
    val pairs = row.zip(row.tail)
    
    prev.zip(pairs).flatMap { o =>
      List(
        Node(o._2._1, o._2._1 :: o._1.prevPath),
        Node(o._2._1, o._2._1 :: o._1.prevPath),
      )
    }
  }
  
  val rez = tree.minBy(_.sum).path
  println(rez)
}
