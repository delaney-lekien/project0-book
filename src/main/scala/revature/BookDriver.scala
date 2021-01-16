package revature

import java.sql.Connection
import revature.utils.ConnectionUtil
import scala.io.Source
import java.sql.Driver
import java.sql.DriverManager
import revature.model.Book


object BookDriver extends App{
 val cli = new Cli()
 cli.menu()

}
