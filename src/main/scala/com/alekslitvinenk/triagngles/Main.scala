package com.alekslitvinenk.triagngles

import cats.data.NonEmptyList
import com.alekslitvinenk.triagngles.domain.Calc

import scala.io.Source

object Main extends App {
  
  private val source = Source.fromInputStream(System.in)
  private val lines = source.getLines().takeWhile(p => p.nonEmpty && p != "\n").toList
  private val rootNodeVal = lines.head.trim.toInt
  private val previous = Array(Calc(NonEmptyList(rootNodeVal, List.empty)))
  
  // calculating triangle by taking a line at time and converting it into Calc paths and appending parent Calc path
  val tree = lines.tail.foldLeft(previous) { (prev, line) =>
    // grouping 'prev' Calc paths by original nodes
    val prevOrigNodes = {
      val middle = prev.tail.dropRight(1).grouped(2).toArray
      middle.prepended(Array(prev.head)).appended(Array(prev.last))
    }
    // row of integers from consumed line
    val row = line.split(" ")
      .map(_.trim)
      .map(_.toInt)
    
    // pairs of integers, each pair represents leaf nodes for corresponding row of parent row 'prev'
    val pairs = row.zip(row.tail)
    
    val solutions = prevOrigNodes.zip(pairs).flatMap { o =>
      o._1.flatMap { e =>
        List(
          Calc(e.path :+ o._2._1),
          Calc(e.path :+ o._2._2),
        )
      }
    }
    
    solutions
  }
  
  val rezNode = tree.minBy(_.sum)
  val rezPath = rezNode.path.toList
  println(s"Minimal path is: ${rezPath.mkString(" + ")} = ${rezNode.sum}")
}
