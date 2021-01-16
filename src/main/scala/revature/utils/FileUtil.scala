package revature.utils

import scala.io.BufferedSource
import scala.io.Source

object ReadCSV {

  def get() : Unit = {
    println("Title, Author, Price, ISBN")
    val bufferedSource = io.Source.fromFile("BookLog.csv")
    for (line <- bufferedSource.getLines) {
        var Array(title, author, price, isbn) = line.split(",").map(_.trim)
        println(s"""|$title $author $price $isbn""".stripMargin)
  }
 }
}