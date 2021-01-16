package revature.utils

import java.sql.Connection
import java.sql.DriverManager

object ConnectionUtil {

  def getConnection() : Connection = {

    classOf[org.postgresql.Driver].newInstance()
    
    DriverManager.getConnection(
      "jdbc:postgresql://localhost:5432/delaneylekien", 
      "delaneylekien", 
      "Delgagal23$$")

  }
 
} 