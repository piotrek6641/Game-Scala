package cw

import org.junit.Test
import org.junit.Assert._

class GameTest {
  @Test def test_00_ar {
    var game: Game = GameBuilder.initialiseGame1()
    game.arrowRight() 
    assertEquals((1,0), game.getPlayerPos()) 
  }

  @Test def test_01_ar {
    var game: Game = GameBuilder.initialiseGame2()
    game.arrowRight() 
    assertEquals((4,2), game.getPlayerPos()) 
  }
  @Test def test_02_ar {
    var game: Game = GameBuilder.initialiseGame3()
    game.arrowRight() 
    assertEquals((5,1), game.getPlayerPos()) 
  }

  @Test def test_03_al {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowLeft() 
    assertEquals((0,0), game.getPlayerPos()) 
  }

  @Test def test_04_al {
    var game: Game=GameBuilder.initialiseGame2()
    game.arrowLeft() 
    assertEquals((2,2), game.getPlayerPos()) 
  }

  @Test def test_05_al {
    var game: Game=GameBuilder.initialiseGame3()
    game.arrowLeft() 
    assertEquals((4,1), game.getPlayerPos()) 
  }

  @Test def test_06_ar {
    var game: Game=GameBuilder.initialiseGame2()
    game.arrowRight() 
    game.arrowRight() 
    assertEquals((5,2), game.getPlayerPos()) 
  }
  
  @Test def test_07_ar {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowRight(3) 
    assertEquals((2,0), game.getPlayerPos()) 
  }


  @Test def test_08_ad {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowDown() 
    assertEquals((0,1), game.getPlayerPos()) 
  }

  @Test def test_09_ad {
    var game: Game=GameBuilder.initialiseGame2()
    game.arrowDown() 
    assertEquals((3,2), game.getPlayerPos()) 
  }

  @Test def test_10_ad {
    var game: Game=GameBuilder.initialiseGame3()
    game.arrowDown() 
    assertEquals((4,2), game.getPlayerPos()) 
  }

  @Test def test_11_au {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowUp() 
    assertEquals((0,0), game.getPlayerPos()) 
  }

  @Test def test_12_au {
    var game: Game=GameBuilder.initialiseGame2()
    game.arrowUp() 
    assertEquals((3,1), game.getPlayerPos()) 
  }

  @Test def test_13_ad {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowDown() 
    game.arrowDown() 
    assertEquals((0,2), game.getPlayerPos()) 
  }

  @Test def test_14_ad {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowDown(5) 
    assertEquals((0,5), game.getPlayerPos()) 
  }

  @Test def test_15_ad {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowDown(13) 
    assertEquals((0,9), game.getPlayerPos()) 
  }

  @Test def test_16_ad {
    var game: Game=GameBuilder.initialiseGame2()
    game.arrowDown(2) 
    assertEquals((3,2), game.getPlayerPos()) 
  }
  
  @Test def test_17_ad {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowDown(-2) 
    assertEquals((0,0), game.getPlayerPos()) 
  }
  
  @Test def test_18_adar {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowDown(3)
    game.arrowRight(3)
    assertEquals((3,3), game.getPlayerPos())
    assertEquals(10, game.getScore())
  }
  
  @Test def test_19_playeratbounty {
    var game: Game = GameBuilder.initialiseGame3()
    assertEquals(0, game.getScore())
    game.checkBounty()
    assertEquals(5, game.getScore())
  }

  
  
  
  @Test def test_20_adarsave {
    var game: Game=GameBuilder.initialiseGame2()
    game.save()
    game.arrowRight(5)
    game.arrowDown()
    assertEquals((8,3), game.getPlayerPos())
    assertEquals(1, game.getScore())
  }

  @Test def test_21_adarsave {
    var game: Game=GameBuilder.initialiseGame2()
    game.save()
    game.arrowRight(3)
    game.arrowDown()
    assertEquals(1, game.getScore())
    assertEquals((3,2), game.getSavePos())
    game.arrowRight()
    assertEquals((7,3), game.getPlayerPos())
    assertEquals(1, game.getScore())
    assertEquals((-1,-1), game.getSavePos())
  }
  
  @Test def test_22_save {
    var game: Game=GameBuilder.initialiseGame2()
    game.save()
    game.arrowRight(3)
    game.arrowDown(2)
    assertEquals((-1,-1), game.getSavePos()) 
    assertEquals((6,4), game.getPlayerPos()) 
    assertEquals(2, game.getScore()) 
  }
  
  @Test def test_23_save {
    var game: Game=GameBuilder.initialiseGame2()
    game.arrowRight(1)
    game.arrowDown(4)
    game.save()
    game.arrowRight(2)
    game.arrowUp(4)
    assertEquals((-1,-1), game.getSavePos()) 
    assertEquals((6,2), game.getPlayerPos()) 
    assertEquals(2, game.getScore()) 
  }
  
  @Test def test_24_save {
    var game: Game=GameBuilder.initialiseGame1()
    game.arrowRight(2)
    game.arrowDown(3)
    game.save()
    game.arrowDown()
    game.arrowRight()
    game.save()
    assertEquals((3,4), game.getSavePos()) 
    assertEquals((3,4), game.getPlayerPos()) 
    assertEquals(0, game.getScore()) 
  }
  
  @Test def test_25_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("rruu") 
    assertEquals((2,0), game.getPlayerPos()) 
  }


  @Test def test_26_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("rrduur") 
    assertEquals((2,0), game.getPlayerPos()) 
  }

  @Test def test_27_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("rrduurll") 
    assertEquals((0,0), game.getPlayerPos()) 
  }

  @Test def test_28_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("rduur") 
    assertEquals((2,0), game.getPlayerPos()) 
  }

  @Test def test_29_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("rrrruud") 
    assertEquals((2,1), game.getPlayerPos()) 
  }

  @Test def test_30_move {
    var game: Game = GameBuilder.initialiseGame2()
    game.move("rdd") 
    assertEquals((4,4), game.getPlayerPos()) 
    assertEquals(1, game.getScore()) 
  }
  
  @Test def test_31_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.arrowDown(2)
    game.arrowRight(2)
    game.save()
    game.move("dddrrr")
    assertEquals((5,5), game.getPlayerPos()) 
    assertEquals(10, game.getScore()) 
  }
  
  @Test def test_32_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("dddrrrrlll") 
    assertEquals((1,3), game.getPlayerPos())
    assertEquals(10, game.getScore())
  }

  @Test def test_33_move {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("dddddddddddd") 
    assertEquals((0,9), game.getPlayerPos())
    assertEquals(0, game.getScore())
  }
    
  @Test def test_34_suggestmove {
    var game: Game = GameBuilder.initialiseGame1()
    var sol=game.suggestMove(3, 3)
    assertEquals("dddrrr", sol) 
    assertEquals((0,0), game.getPlayerPos()) 
    assertEquals(0, game.getScore()) 
  }

  @Test def test_35_suggestmove {
    var game: Game = GameBuilder.initialiseGame1()
    var sol=game.suggestMove(4, 2)
    assertEquals("", sol) 
    assertEquals((0,0), game.getPlayerPos()) 
    assertEquals(0, game.getScore()) 
  }
  
  @Test def test_36_suggestmove {
    var game: Game = GameBuilder.initialiseGame2()
    game.move("rdd")
    assertEquals((4,4), game.getPlayerPos()) 
    var sol=game.suggestMove(3, 2)
    assertEquals("uul", sol) 
    assertEquals(1, game.getScore()) 
  }

  @Test def test_37_suggestmove {
    var game: Game = GameBuilder.initialiseGame2()
    var sol=game.suggestMove(5, 6)
    assertEquals("", sol) 
    assertEquals((3,2), game.getPlayerPos()) 
    assertEquals(0, game.getScore()) 
  }
  
  @Test def test_38_suggestmove {
    var game: Game = GameBuilder.initialiseGame1()
    var sol=game.suggestMove(10, 10)
    assertEquals("", sol) 
    assertEquals((0,0), game.getPlayerPos()) 
    assertEquals(0, game.getScore()) 
  }
  
  @Test def test_39_suggestmove {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("dddrrr")
    var sol=game.suggestMove(4, 1)
    assertEquals("ruu", sol) 
    assertEquals((3,3), game.getPlayerPos()) 
    assertEquals(10, game.getScore()) 
  }

  @Test def test_40_suggestmove {
  var game: Game = GameBuilder.initialiseGame1()
    game.arrowDown(5)
    assertTrue(game.suggestMove(4,1).equals("rrrruuuu") || game.suggestMove(4,1).equals("uuuurrrr")) 
    assertEquals(0, game.getScore())
  }

  @Test def test_41_maxbounty {
    var game: Game = GameBuilder.initialiseGame1()
    assertEquals(15, game.maxBounty()) 
    assertEquals((0,0), game.getPlayerPos()) 
    assertEquals(0, game.getScore())
  }

  @Test def test_42_maxbounty {
    var game: Game = GameBuilder.initialiseGame2()
    game.move("rrrd")
    assertEquals(2, game.maxBounty())  
    assertEquals(1, game.getScore())
  }
  
  @Test def test_43_maxbounty {
    var game: Game = GameBuilder.initialiseGame2()
    game.move("rrrd")
    assertEquals(2, game.maxBounty())  
    assertEquals(1, game.getScore())
    game.move("ulldd")
    assertEquals(2, game.maxBounty())  
    assertEquals(2, game.getScore())
  }
  
  @Test def test_44_maxbounty {
    var game: Game = GameBuilder.initialiseGame2()
    game.move("rdd")
    assertEquals(2, game.maxBounty())  
    assertEquals(1, game.getScore())
    game.move("uurrd")
    assertEquals(2, game.maxBounty())  
    assertEquals(2, game.getScore())
  }

  @Test def test_45_suggestsolution {
    var game: Game = GameBuilder.initialiseGame2()
    game.move("rrrd")
    assertEquals("", game.suggestSolution())  
  }

  @Test def test_46_suggestsolution {
    var game: Game = GameBuilder.initialiseGame1()
    assertEquals("dddrrrruu", game.suggestSolution()) 
  }

  @Test def test_47_suggestsolution {
    var game: Game = GameBuilder.initialiseGame1()
    game.arrowDown(5)
     assertTrue(game.suggestSolution().equals("uurrrruu") || game.suggestSolution().equals("rrruuruu"))  
  }

  
  @Test def test_48_checkbounties {
    var game: Game = GameBuilder.initialiseGame1()
    game.move("ddddrrrr")
    assertEquals(0, game.getScore())
    assertEquals((4,4), game.getPlayerPos())
    game.setSavePos(0,0)
    game.checkBounties()
    assertEquals(15, game.getScore())
    assertEquals((4,4), game.getPlayerPos())
  } 
  
  @Test def test_49_checkbounties {
    var game: Game = GameBuilder.initialiseGame1()
    game.save()
    game.move("dddrrr")
    assertEquals((-1,-1), game.getSavePos())
    game.arrowRight()
    assertEquals(10, game.getScore())
    assertEquals((4,3), game.getPlayerPos())
  } 
  
  
}