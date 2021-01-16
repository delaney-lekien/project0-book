package revature

import scala.io.StdIn
import scala.util.matching.Regex
import java.io.FileNotFoundException
import revature.utils.ReadCSV

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
      println("Commands avaliable:")
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
            try {
            println(ReadCSV.get())
            } catch {
                case fnfe: FileNotFoundException => println(s"Failed to find file ${fnfe.getMessage}")
                // try to add in a loop to try again if you have time
                // most likely changing this to read from Database instead of CSV
            }
          }
          case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Update") => {   
            println(arg)
            // Provide functionality here
          }
           case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Add") => {   
            println(arg)
          }
           case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("Remove") => {   
            println(arg)
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

}