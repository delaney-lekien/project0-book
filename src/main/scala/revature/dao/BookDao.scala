package revature.dao

import revature.model.Book
import revature.utils.ConnectionUtil
import scala.util.Using
import scala.collection.mutable.ArrayBuffer

object BookDao {

  def getAll() : Unit = {
    val conn = ConnectionUtil.getConnection()
    Using.Manager { use =>
      val getStatement = use(conn.prepareStatement("SELECT * FROM book;"))
      getStatement.execute()
      val resultSet = use(getStatement.getResultSet())
      while(resultSet.next()) {
        println(Book.fromResultSet(resultSet))
      } 
    }
  }

  def addBook(book: Book) : Boolean = {
    val conn = ConnectionUtil.getConnection()
    Using.Manager { use =>
      val addStatement = use(conn.prepareStatement("INSERT INTO book VALUES (DEFAULT, ?, ?, ?, ?);"))
      addStatement.setString(1, book.title)
      addStatement.setString(2, book.author)
      addStatement.setDouble(3, book.price)
      addStatement.setString(4, book.isbn)
      addStatement.execute()
      addStatement.getUpdateCount() > 0
    }.getOrElse(false)
  }


  def removeBook(title: String) : Boolean = {
    val conn = ConnectionUtil.getConnection()
    Using.Manager { use => 
      val deleteStatement = use(conn.prepareStatement("DELETE FROM book WHERE title = ?;"))
      deleteStatement.setString(1, title)
      deleteStatement.execute()
      deleteStatement.getUpdateCount() > 0
    }.getOrElse(false)
  }

  def updateBook(author: String) : Boolean = {
    val conn = ConnectionUtil.getConnection()
    Using.Manager { use =>
      val updateStatement = use(conn.prepareStatement("UPDATE book SET author = ? WHERE author = 'Roux';"))
      updateStatement.setString(1, author)
      updateStatement.execute()
      updateStatement.getUpdateCount() > 0
    }.getOrElse(false)
  }
}
