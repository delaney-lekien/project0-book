package revature.dao

import revature.model.Book
import revature.utils.ConnectionUtil
import scala.util.Using
import scala.collection.mutable.ArrayBuffer

object BookDao {

  def getAll() : Unit = {
    val conn = ConnectionUtil.getConnection();
    Using.Manager { use =>
      val getStatement = use(conn.prepareStatement("SELECT * FROM book;"))
      getStatement.execute()
      val resultSet = use(getStatement.getResultSet())
      while(resultSet.next()) {
        println(Book.fromResultSet(resultSet))
      }
      
    }
    
  }
  getAll()

}
