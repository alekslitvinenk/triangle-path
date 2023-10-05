package com.alekslitvinenk.triagngles

import com.alekslitvinenk.triagngles.domain.Node

import scala.io.Source

object Main extends App {
  
  private val source = Source.fromInputStream(System.in)
  
  private val lines = source.getLines().takeWhile(p => p.nonEmpty && p != "\n").toList
  private val rootNodeVal = lines.head.toInt
  private val previous = Array(Node(rootNodeVal, Array(rootNodeVal)))
  
  // calculating triangle by taking a line at time and converting it into nodes and appending parent node path's
  val tree = lines.tail.foldLeft(previous) { (prev, line) =>
    // row of integers from consumed line
    val row = line.split(" ").map(_.toInt)
    
    // pairs of integers, each pair represents leaf nodes for corresponding row of parent row 'prev'
    val pairs = row.zip(row.tail)

    // then 'prev' row is zipped with pairs of numbers from current row 'row'
    prev.zip(pairs).flatMap { o =>
      // and here we produce a new row of Nodes
      List(
        Node(o._2._1, o._1.path :+ o._2._1),
        Node(o._2._2, o._1.path :+ o._2._2),
      )
    }
  }
  
  val rezNode = tree.minBy(_.sum)
  val rezPath = rezNode.path.toList
  println(s"Minimal path is: ${rezPath.mkString(" + ")} = ${rezNode.sum}")
}
