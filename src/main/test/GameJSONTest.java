import gamepackage.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameJSONTest {
    @Test
    public void saveTest() {
        Game testGame = new Game(2);
        testGame.updateState("0;project;0;1;2");
        testGame.updateState("1;project;0;1;2");
        System.out.println(testGame.isFirstTurn()); //false
        System.out.println(testGame.getCurrentPlayer()); //0
        System.out.println(testGame.getRegularTilesLeft().size());     // 101?
        for (Map.Entry<Cat, CatBoard> element : testGame.getCatBoards().entrySet()) {
            System.out.println(element.getKey().toString() + " " + Arrays.toString(element.getValue().getPreferredPatterns()));      //Cat, Pattern,Pattern
        }
        System.out.println(testGame.getPlayers()[0].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[0].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(1).getColor().toString());
        System.out.println(testGame.getPlayers()[1].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[1].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(1).getColor().toString());
        testGame.saveToJSON("test.json");
    }

    @Test
    public void loadTest() {
        Game testGame = new Game(2);
        testGame.loadGameStateFromJSON("test.json");
        System.out.println(testGame.isFirstTurn()); //false
        System.out.println(testGame.getCurrentPlayer()); //0
        System.out.println(testGame.getRegularTilesLeft().size());     // 101
        for (Map.Entry<Cat, CatBoard> element : testGame.getCatBoards().entrySet()) {
            System.out.println(element.getKey().toString() + " " + Arrays.toString(element.getValue().getPreferredPatterns()));      //Cat, Pattern,Pattern
        }
        System.out.println(testGame.getPlayers()[0].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[0].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(1).getColor().toString());
        System.out.println(testGame.getPlayers()[1].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[1].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(1).getColor().toString());
        System.out.println(testGame.getCatBoards().size());
    }

    @Test
    public void saveAndLoadTest() {


        Game testGame = new Game(2);
        testGame.updateState("0;project;0;1;2");
        testGame.updateState("1;project;0;1;2");
        System.out.println("IsFirstTurn:");
        System.out.println(testGame.isFirstTurn()); //false
        System.out.println("CurrentPlayer:");
        System.out.println(testGame.getCurrentPlayer()); //0
        System.out.println("NumberOfTilesLeft:");
        System.out.println(testGame.getRegularTilesLeft().size());     // 101
        System.out.println("CatBoards:");
        for (Map.Entry<Cat, CatBoard> element : testGame.getCatBoards().entrySet()) {
            System.out.println(element.getKey().toString() + " " + Arrays.toString(element.getValue().getPreferredPatterns()));      //Cat, Pattern,Pattern
        }
        System.out.println("HandTiles:");
        System.out.println(testGame.getPlayers()[0].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[0].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(1).getColor().toString());
        System.out.println(testGame.getPlayers()[1].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[1].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(1).getColor().toString());
        System.out.println("Project Tiles:");
        System.out.println(testGame.getPlayers()[0].getBoard().getField(4,2).getProjectTile()
              //  .toString()
                +" "+testGame.getPlayers()[0].getBoard().getField(2,3).getProjectTile()
             //   .toString()
                +" "+testGame.getPlayers()[0].getBoard().getField(3,4).getProjectTile()
             //   .toString()
                   );

        System.out.println(testGame.getPlayers()[1].getBoard().getField(4,2).getProjectTile()
             //   .toString()
                +" "+testGame.getPlayers()[1].getBoard().getField(2,3).getProjectTile()
             //   .toString()
                +" "+testGame.getPlayers()[1].getBoard().getField(3,4).getProjectTile()
              //  .toString()
        );
        testGame.saveToJSON("test.json");
        testGame = new Game(2);
        testGame.loadGameStateFromJSON("test.json");
        System.out.println("IsFirstTurn:");
        System.out.println(testGame.isFirstTurn());
        System.out.println("CurrentPlayer:");
        System.out.println(testGame.getCurrentPlayer());
        System.out.println("NumberOfTilesLeft:");

        System.out.println(testGame.getRegularTilesLeft().size());
        System.out.println("CatBoards:");

        for (Map.Entry<Cat, CatBoard> element : testGame.getCatBoards().entrySet()) {
            System.out.println(element.getKey().toString() + " " + Arrays.toString(element.getValue().getPreferredPatterns()));      //Cat, Pattern,Pattern
        }
        System.out.println("HandTiles:");

        System.out.println(testGame.getPlayers()[0].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[0].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[0].getTilesOnHand().get(1).getColor().toString());
        System.out.println(testGame.getPlayers()[1].getTilesOnHand().get(0).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(0).getColor().toString() + ";" + testGame.getPlayers()[1].getTilesOnHand().get(1).getPattern().toString() + " " + testGame.getPlayers()[1].getTilesOnHand().get(1).getColor().toString());
        System.out.println("Project Tiles:");
        System.out.println(Arrays.toString(testGame.getPlayers()[0].getProjectTiles()));
        System.out.println(Arrays.toString(testGame.getPlayers()[1].getProjectTiles()));


    }


}
