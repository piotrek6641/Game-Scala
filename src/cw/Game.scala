package cw

import com.sun.xml.internal.fastinfoset.util.ValueArray
import sun.security.util.Length

/**
 * This class holds an instance of a simple game where
 * a player moves on a field and collects bounties.
 * See the explanation sheet and comments in this file for details. The constructor builds an
 * instance of a game using the accepted parameters.
 *
 * @param wall A list of coordinates (as tuples) where walls exist. Example: The parameter List((0,0),(0,1)) puts two wall elements in the upper left corner and the position below.
 * @param bounty A list of bounties, each is a position and a value (i.e. a 3 value tuple). Example: List((0,0,5)) puts a bounty in the upper left corner which adds 5 to the score.
 * @param initialX The initial x position of the player.
 * @param initialY The initial y position of the player. If initialX and initialY are 0, the player starts in the upper left corner.
 */
class Game(wall: List[(Int, Int)], bounty: List[(Int, Int, Int)], initialX: Int, initialY: Int) {

  //the current grid, a 10x10 field, where -1=empty, 0=wall, any positive number=bounty
  private var field: Array[Array[Int]] = Array.ofDim[Int](10, 10)

  /* Please note - to align with the overall case study (see explanation sheet), both of the above two-dimensional arrays
   * should be accessed in the format field(col)(row) so field(2)(0) would retrieve the 3rd column and the 1st row (as indexing starts at zero),
   * equivalent to an (x,y) coordinate of (2,0). You may therefore visualise each inner array as representing a column of data.
   */

  //the current score, initially 0
  private var score: Int = 0
  //the current player position. As the player moves these positions update.
  private var positionX: Int = initialX
  private var positionY: Int = initialY
  //the current X and Y save position, initially -1
  private var saveX: Int = -1
  private var saveY: Int = -1
  

  /* This code is executed as part of the constructor. It firstly initialises all cells to -1 (i.e. empty).
   * It uses the list of walls provided to initialise the walls in the field array by setting given coordinates to 0.
   * It then uses the list of bounties to initialise the bounties in the field array by setting given coordinates to the provided number.
   */
  for (i <- 0 until 10; k <- 0 until 10) field(i)(k) = -1
  wall.foreach(w => field(w._1)(w._2) = 0)
  bounty.foreach(w => field(w._1)(w._2) = w._3)

  /**
   * Repeatedly run a sequence of commands. For example:
   *    for(i <- 1 to 5) println("Hello")
   * can be replaced by
   *    rpt(5)(println("Hello"))
   */
  def rpt(n: Int)(commands: => Unit) {
    for (i <- 1 to n) { commands }
  }

/********************************************************************************
   * COURSEWORK STARTS HERE - COMPLETE THE DEFINITIONS OF EACH OF THE OPERATIONS
   * WE SUGGEST YOU RUN THE GameTest SUITE AFTER EVERY CHANGE YOU MAKE TO THESE
   * SO YOU CAN SEE PROGRESS AND CHECK THAT YOU'VE NOT BROKEN ANYTHING THAT USED
   * TO WORK.
   *******************************************************************************/

  /**
   * Returns the current position of the player as a tuple, in (x,y) order.
   */
  def getPlayerPos(): (Int, Int) = {
    
    return (positionX, positionY);
  }
  

  /**
   * Returns the current score.
   */
  def getScore(): Int = return(score)

  /**
   * Move the player one place to the left.
   * If there is a wall or the field ends, nothing happens.
   * If there is a bounty, it is collected (i.e. a call to checkBounty() is made).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
  def arrowLeft() {
    if(positionX-1 <0 || (field(positionX-1)(positionY)==0 ))
    {
      positionX=positionX
    }
    else
    {
    
    this.positionX-=1
     
    if(field(positionX)(positionY)>0)
    {
      checkBounty()
      checkBounties()
    }
    }
    
  }
 

  /**
   * Move the player one place to the right.
   * If there is a wall or the field ends, nothing happens.
   * If there is a bounty, it is collected (i.e. a call to checkBounty() is made).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
 
  
  def arrowRight():Unit=  {
   
       if(positionX+1 <0 || positionX+1 >9 || (field(positionX+1)(positionY)==0 ))
    {
      positionX=positionX
    }
       else
       positionX+=1
      
    
      checkBounty()
       checkBounties()
    
    
    
    
   
  }
  

    
  

  /**
   * Move the player one place up.
   * If there is a wall or the field ends, nothing happens.
   * If there is a bounty, it is collected (i.e. a call to checkBounty() is made).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
  def arrowUp() {
        if(positionY-1 <0 || (field(positionX)(positionY-1)==0 ))
    {
      positionY=positionY
    }
       else
       positionY-=1
       if(field(positionX)(positionY)>0)
    {
      checkBounty()
      checkBounties()
    }
    
  }

  /**
   * Move the player one place down.
   * If there is a wall or the field ends, nothing happens.
   * If there is a bounty, it is collected (i.e. a call to checkBounty() is made).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
  def arrowDown() {
     if((positionY+1 <0 || positionY+1>9) || (field(positionX)(positionY+1)==0 ))
    {
      positionY=positionY
    }
       else
       positionY+=1
       
    
      checkBounty()
     checkBounties()
    
    
  }

  /**
   * Move the player n places to the left. Negative numbers or 0 as a parameter cause no effect.
   * If there is a wall or the field ends, the player stops before the wall or end of the field.
   * Any bounties are collected (i.e. a call to checkBounty() is made after each move).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
  def arrowLeft(n: Int) :Unit ={
    rpt(n)
    {
      arrowLeft()
    }
  }

  /**
   * Move the player n places to the right. Negative numbers or 0 as a parameter cause no effect.
   * If there is a wall or the field ends, the player stops before the wall or end of the field.
   * Any bounties are collected (i.e. a call to checkBounty() is made after each move).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
  def arrowRight(n: Int) {

  rpt(n)
    {
      arrowRight()
    }
  }

  /**
   * Move the player n places up. Negative numbers or 0 as a parameter cause no effect.
   * If there is a wall or the field ends, the player stops before the wall or end of the field.
   * Any bounties are collected (i.e. a call to checkBounty() is made after each move).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
  def arrowUp(n: Int) {
     rpt(n)
    {
      arrowUp()
    }
  }

  /**
   * Move the player n places down. Negative numbers or 0 as a parameter cause no effect.
   * If there is a wall or the field ends, the player stops before the wall or end of the field.
   * Any bounties are collected (i.e. a call to checkBounty() is made after each move).
   * A more advanced requirement would be to call checkBounties() if completed.
   */
  def arrowDown(n: Int) {
    rpt(n)
    {
      arrowDown()
    }
  }

  /**
   * Checks if the current position is a bounty. A bounty exists if the cell
   * has a value larger than 0. If a bounty does exist, increase the score,
   * and then erase the bounty, i.e. set it to -1.
   */
  def checkBounty() {
  if (field(positionX)(positionY)>0)
  {
    var points=field(positionX)(positionY)
    score+=points
    field(positionX)(positionY)=(-1)
  }
  
  }

  //The methods beyond this point (aside to those in GameBuilder which is a separate task) are more complex than those above.

  /**
   * This moves the player according to a string. The string can contain the
   * letters l, r, u, d representing left, right, up, down moves.  If
   * there is a wall or the field ends, the individual move is not
   * executed. Any further moves are done. Any bounties are collected and the
   * save position is evaluated.
   */
  def move(s: String) {
    

    
   for(x <-s)
    {
      x match
      {
        case 'l'=>arrowLeft()
        case 'r'=>arrowRight()
        case 'u'=>arrowUp()
        case 'd'=>arrowDown()
        case _=>None
      }
    }
      
    
  }

  /**
   * Identifies the maximum overall bounty in the game. This is the sum
   * of the current score and the possible score from collecting all of the remaining bounties.
   * No bounties are collected here, only the max score is returned.
   */
  def maxBounty(): Int = {
    var bounty=score
    for (i <- 0 until 10; k <- 0 until 10)
    {
      if (field(i)(k)>0)
     bounty+=field(i)(k)
    }
    return(bounty)
  }

  /**
   * Checks if the rectangle defined by the current position and saved position
   * covers nine or more positions. If yes, it collects bounties in it, increases the
   * score, and erases the bounties. Also resets the saved position to -1,-1.
   */
  def checkBounties() {
    if(saveX==(-1) && saveY==(-1))
    {
      None
    }
    else
    {
    var numbers=0
    if(saveX<positionX && saveY<positionY)
    for(i<- saveX until positionX+1;k<-saveY until positionY+1)
    {
      numbers+=1
    }
    if(saveX>positionX && saveY>positionY)
    for(i<- positionX until saveX+1;k<-positionY until saveY+1)
    {
      numbers+=1
    }
    if(saveX<positionX && saveY>positionY)
    for(i<- saveX until positionX+1;k<-positionY until saveY+1)
    {
      numbers+=1
    }
    if(saveX>positionX && saveY<positionY)
    for(i<- positionX+1 until saveX;k<-saveY until positionY+1)
    {
      numbers+=1
    }
    if (numbers>=9)
    {
      for(i<- saveX until positionX+1;k<-saveY until positionY+1)
      
        if (field(i)(k)>0)
      {
    var points=field(i)(k)
    score+=points
    field(i)(k)=(-1)
        }
    
    if(saveX>positionX && saveY>positionY)
    for(i<- positionX until saveX+1;k<-positionY until saveY+1)
    {
        if (field(i)(k)>0){
    var points=field(i)(k)
    score+=points
    field(i)(k)=(-1)
        }
    }
    if(saveX<positionX && saveY>positionY)
    for(i<- saveX until positionX+1;k<-positionY until saveY+1)
    {
       if (field(i)(k)>0){
    var points=field(i)(k)
    score+=points
    field(i)(k)=(-1)
    }
    }
    if(saveX>positionX && saveY<positionY)
    for(i<- positionX until saveX+1;k<-saveY until positionY+1)
    {
       if (field(i)(k)>0){
    var points=field(i)(k)
    score+=points
    field(i)(k)=(-1)
    }
      
    }
   setSavePos(-1, -1)
   
  }
    }
  }

  /**
   * This gives a string in the format for move, which collects the maximum bounty. No specific
   * requirements for the efficiency of the solution exist, but the solution must consist of a finite number
   * of steps. The move is combined of a number of moves
   * given by suggestMove. If these are not possible, an empty string is returned. No bounties are collected
   * and the player must be at the original position after the execution of the method.
   */
  def suggestSolution():String=
  {
  var s=""
  var tempX=positionX
  var tempY=positionY
  var tempScore=score
  var x=0
  
  
 rpt(bounty.toList.length)
 {
   if(suggestMove(bounty(x)._1,bounty(x)._2)=="" || suggestMove(bounty(x+1)._1,bounty(x+1)._2)>suggestMove(bounty(x)._1,bounty(x)._2))
   {
     x+=1
   }
   s+=suggestMove(bounty(x)._1,bounty(x)._2)
   positionX=bounty(x)._1
   positionY=bounty(x)._2
   field(positionX)(positionY)=bounty(x)._3  
   x=0
   
   
 }
   positionX=tempX
   positionY=tempY
   score=tempScore
  return s
  }
  
  

  /**
   * This gives a string in the format for move, which moves from the current position to
   * position x,y. No specific requirements for the efficiency of the solution exist. The move
   * cannot jump walls. The method is restricted to finding a path which is combined of a number of
   * left and then a number of up movement, or left/down, or right/up, or right/down movements only.
   * If this is not possible due to walls, it returns an empty string. No actual move is done. If
   * x or y are outside the field, an empty string is returned as well.
   */
 
   def reverseSuggestMove(x: Int, y: Int): String = {
    var step=""
    var z=x
    var w=y
    var b=y
    var c=x
    if (z>=10 || w>=10 || z<0 || w<0 || field(z)(w)==0)
    {
      return ""
    }
    if (z>positionX) 
    {
      while(z>positionX)
      {
         if(field(z)(positionY)==0)
        {
          return""
        }
      step+="r"
      z-=1
      }
    }
    if (z<positionX)
    {
      while(z<positionX)
      {
         if(field(z)(positionY)==0)
        {
          return""
        }
      step+="l"
      z+=1
      }
      
    }
    if (w>positionY)
    {
      while(w>positionY)      {
        if(field(c)(w)==0)
        {
          return""
        }
      step+="d"
      w-=1  
      }
    }
     if (w<positionY)
    {
      while(w<positionY)
      {
        if(field(c)(w)==0)
        {
          return""
        }
      step+="u"
      w+=1
      }
    }
    
    
    
   
    
    
    return step
  }
  
  
  
  
  
  def suggestMove(x: Int, y: Int): String = {
    var step=""
    var z=x
    var w=y
    var b=y
    var c=x
    if (z>=10 || w>=10 || z<0 || w<0 || field(z)(w)==0)
    {
      return ""
    }
    if (w>positionY)
    {
      while(w>positionY)
      {
        if(field(positionX)(w)==0)
        {
          return(reverseSuggestMove(x, y))
        }
      step+="d"
      w-=1  
      }
    }
     if (w<positionY)
    {
      while(w<positionY)
      {
        if(field(positionX)(w)==0)
        {
          return(reverseSuggestMove(x, y))
        }
      step+="u"
      w+=1
      }
    }
    
    if (z>positionX) 
    {
      while(z>positionX)
      {
         if(field(z)(b)==0)
        {
          return(reverseSuggestMove(x, y))
        }
      step+="r"
      z-=1
      }
    }
    if (z<positionX)
    {
      while(z<positionX)
      {
         if(field(z)(b)==0)
        {
          return(reverseSuggestMove(x, y))
        }
      step+="l"
      z+=1
      }
      
    }
    
   
    
    
    return step
  }

  
  /* --- The three save methods below are used by the unit tests to simulate certain conditions --- */

  /**
   * Updates saveX and saveY to the current player position.
   */
  def save(): Unit = {
    /* This method is already implemented. You should not change it */
    saveX = positionX
    saveY = positionY
  }

  /**
   * Returns the current save position as a tuple, in (x,y) order.
   */
  def getSavePos(): (Int, Int) = {
    /* This method is already implemented. You should not change it */
    return (saveX, saveY);
  }

  /**
   * Sets the savePos to the values of the parameters.
   */
  def setSavePos(saveX: Int, saveY: Int): Unit = {
    /* This method is already implemented. You should not change it */
    this.saveX = saveX
    this.saveY = saveY
  }

}

/**
 * This object builds and returns a standard instance of Game.
 * It is used by the unit tests to initialise the game in different states.
 * Currently, there are three ways in which a game can be initialised,
 * the first has been completed but the other two initialisation methods need writing.
 */
object GameBuilder {

  /**
   * @return A game with
   * - walls in positions 3,0 3,1 and 3,2
   * - a bounty at 4,1 which increases score by 5
   * - a bounty at 3,3 which increases score by 10
   * - the player starting in position 0,0
   */
  def initialiseGame1(): Game = {
    /* This method is already implemented. You should not change it */
    return new Game(List((3, 0), (3, 1), (3, 2)), List((4, 1, 5), (3, 3, 10)), 0, 0)
  }

  /**
   * @return A game with
   * - walls in positions 3,3 3,4 3,5 5,3 5,4 and 5,5
   * - a bounty at 4,4 which increases score by 1
   * - a bounty at 6,3 which increases score by 1
   * - the player starting in position 3,2
   */
  def initialiseGame2(): Game = {
    return new Game(List((3,3), (3,4), (3,5) , (5,3), (5,4), (5,5)) , List((4,4,1) , (6,3,1)), 3, 2)
  }

  /**
   * @return A game with
   * - walls in positions 3,0 3,1 and 3,2
   * - a bounty at 4,1 which increases score by 5
   * - a bounty at 3,3 which increases score by 10
   * - the player starting in position 4,1
   */
  def initialiseGame3(): Game = {
    return new Game(List((3,0), (3,1) , (3,2)), List((4,1,5),(3,3,10)), 4, 1)
  }
}

