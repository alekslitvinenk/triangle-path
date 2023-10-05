package com.alekslitvinenk.triagngles

import com.alekslitvinenk.triagngles.domain.Node

import scala.io.Source

object Main extends App {
  
  private val source = Source.fromInputStream(System.in)
  
  private val lines = source.getLines().takeWhile(p => p.nonEmpty && p != "\n").toList
  private val rootNodeVal = lines.head.toInt
  private val previous = Array(Node(rootNodeVal, Array(rootNodeVal)))
  
  val tree = lines.tail.foldLeft(previous) { (prev, line) =>
    val row = line.split(" ").map(_.toInt)
    val pairs = row.zip(row.tail)

    prev.zip(pairs).flatMap { o =>
      List(
        Node(o._2._1, o._1.path :+ o._2._1),
        Node(o._2._2, o._1.path :+ o._2._2),
      )
    }
  }
  
  val rez = tree.minBy(_.sum).path.toList
  println(rez)
}
