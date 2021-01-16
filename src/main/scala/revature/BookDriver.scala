package revature

import java.sql.Connection
import revature.utils.ConnectionUtil
import scala.io.Source
import java.sql.Driver
import java.sql.DriverManager
import revature.model.Book


object BookDriver {
 // val cli = new Cli()
 // cli.menu()

  def main(args: Array[String]) : Unit = {

      val conn = ConnectionUtil.getConnection()

      val bookStatement = conn.prepareStatement("SELECT * FROM book;")
      bookStatement.execute()
      val resultSet = bookStatement.getResultSet()
      while(resultSet.next()) {
        println(Book.fromResultSet(resultSet))

      }

  }
  
}