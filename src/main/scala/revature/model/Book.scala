package revature.model

import java.sql.ResultSet
import revature.dao.BookDao


case class Book(
bookId : Int, 
title: String, 
author: String, 
price: Double, 
isbn : String) {}

object Book {
  /**
    * Produces a Book from a record in a ResultSet.  Note that this method does *not* call next()!
    *
    * @param resultSet
    * @return
    */
  def fromResultSet(resultSet : ResultSet) : Book = {
    apply(
      resultSet.getInt("book_id"),
      resultSet.getString("title"),
      resultSet.getString("author"),
      resultSet.getDouble("price"),
      resultSet.getString("isbn")
    )
  }
} 