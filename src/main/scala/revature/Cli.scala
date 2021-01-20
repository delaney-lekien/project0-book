package revature

import scala.io.StdIn
import scala.util.matching.Regex
import revature.dao.BookDao
import revature.model.Book
import java.lang.NumberFormatException
import revature.ReadCSV


/**
  * A CLI that allows the user to interact with our application.
  *
  * Contains logic for interacting with user.
  * 
  */
class Cli {

  val commandArgPattern : Regex = "(\\w+)\\s*(.*)".r

  def printWelcome() : Unit = {
      println("Welcome to the Buy-A-Book database!")
  }

  def printOptions() : Unit = {
      println("--Commands avaliable:--")
      println("1. Get avaliable book list: Get")
      println("2. Update a book title: Update Title")
      println("3. Update a book author: Update Author")
      println("4. Update a book price: Update Price")
      println("5. Add a book: Add")
      println("6. Buy a book: Remove")
      println("7. Exit application: Exit")
  }

  def menu() : Unit = { 
      printWelcome()
      var continueMenuLoop = true
      while (continueMenuLoop) {
      printOptions()

      val input = StdIn.readLine()
      input match {
          case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Get") => {   
            println("--Books available for purchase:--")
            BookDao.getAll()
          }
            case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Update")
           && arg.equalsIgnoreCase("Title") => {   
            updateSubMenuTitle()
          }
          case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Update") 
           && arg.equalsIgnoreCase("Author") => {   
            updateSubMenuAuthor()
          }
            case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Update")
           && arg.equalsIgnoreCase("Price") => {   
            updateSubMenuPrice()
          }
           case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Add") => {   
            runAddBooksMenu()
          }
           case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Remove") => {   
            runDeleteBookSubMenu()
          }
          case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Exit") => {   
            continueMenuLoop = false
          }
          // must have "exit" value to not return stack trace. 
          // I'd like to fix this 
          case commandArgPattern(cmd, arg) => {
              println(s"""|Please select one of the given commands for functionality. Input: $cmd $arg""".stripMargin)
          }
           case _ => {   
            println("""|No input given""".stripMargin)
          }
        }
     
     }
     println("Thank you for using Buy-A-Book!")
  }

  def printBooks() : Unit = {
    BookDao.getAll()
  }
  
  def updateSubMenuTitle() : Unit = {
    println("Enter in the book title you want updated.")
    val updateTitleName = StdIn.readLine()
    println("Enter in corresponding Id number.")
    val updateIdTitle : Int = try {
      StdIn.readInt()
    } catch {
      case ex : NumberFormatException => 9999
    }
    try {
      if(BookDao.updateTitle(updateTitleName, updateIdTitle)) {
        println(s"Book #${updateIdTitle} has been updated with ${updateTitleName}")
      } 
    } catch {
      case e : Exception => {
        println("Exception Caught")
      }
    }
  }

  def updateSubMenuAuthor() : Unit = {
    println("Enter in the author name you want updated.")
    val updateAuthorName = StdIn.readLine()
    println("Enter in corresponding Id number.")
    val updateIdAuthor : Int = try {
      StdIn.readInt()
    } catch {
      case ex : NumberFormatException => 9999
    }
    try {
      if(BookDao.updateAuthor(updateAuthorName, updateIdAuthor)) {
        println(s"Book #${updateIdAuthor} has been updated with ${updateAuthorName}")
      } 
    } catch {
      case e : Exception => {
        println("Exception Caught")
      }
    }
  }

   def updateSubMenuPrice() : Unit = {
    println("Enter in the price you want updated.")
    val updatePriceName : Double = StdIn.readDouble()
    println("Enter in corresponding Id number.")
    val updateIdPrice : Int = try {
      StdIn.readInt()
    } catch {
      case ex : NumberFormatException => 9999
    }
    try {
      if(BookDao.updatePrice(updatePriceName, updateIdPrice)) {
        println(s"Book #${updateIdPrice} has been updated with ${updatePriceName}")
      } 
    } catch {
      case e : Exception => {
        println("Exception Caught")
      }
    }
  }


  def runAddBooksMenu() : Unit = {
    println("Input Title:")
    val titleInput = StdIn.readLine()
    println("Input Author Name:")
    val authorInput = StdIn.readLine()
    println("Enter the Price:")
    val priceInput = StdIn.readLine()
    println("Enter in ISBN:")
    val isbnInput = StdIn.readLine()
    try {
      if (BookDao.addBook(Book(0, titleInput, authorInput, priceInput.toDouble, isbnInput))) {
        println("New book added!")
      }
    } catch {
      case e : Exception => {
        println("Please enter in correct input types. Please try again.")
      }
    }
  }

  def runDeleteBookSubMenu() : Unit = {
    println("Enter the Title of the entry you would like to remove:")
    val removeTitleInput = StdIn.readLine()
    try {
      if (BookDao.removeBook(removeTitleInput)) {
        println(s"Book: ${removeTitleInput} removed.")
      }
    } catch {
      case ex : Exception => {
        println("Not able to remove book. Please insert a Title Name to remove. ")
      }
    }
  }

}