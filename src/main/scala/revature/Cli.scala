package revature

import scala.io.StdIn
import scala.util.matching.Regex
import revature.dao.BookDao
import revature.model.Book


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
      println("2. Update a listing information: Update")
      println("3. Add a book: Add")
      println("4. Buy a book: Remove")
      println("5. Exit application: Exit")
  }

  def menu() : Unit = { 
      printWelcome()
      var continueMenuLoop = true
      while (continueMenuLoop) {
      printOptions()

      val input = StdIn.readLine()
      input match {
          case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Get") => {   
            println("--Books avaliable for purchase:--")
            printBooks()
          }
          case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Update") => {   
            updateSubMenu()
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
  
  
  def updateSubMenu() : Unit = {
    println("What value would you like to change?")
    val updateInput1 = StdIn.readLine()
    println("What would you like to change that value too?")
    val updateInput2 = StdIn.readLine()
    try {
      if(BookDao.updateBook(updateInput2)) {
      println(s"${updateInput1} has been changed too ${updateInput2}.")
      }
    } catch {
      case eu : Exception => {
        println("Unable to change field, please try again.")
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