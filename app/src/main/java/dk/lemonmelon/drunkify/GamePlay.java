package dk.lemonmelon.drunkify;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

import dk.lemonmelon.drunkify.DatabaseHandlers.PlayerDatabaseHandler;

/**
 * Created by Mathies on 16-03-2016.
 */

public class GamePlay {

    private Context context;
    private Integer currentPlayer = 1;
    private Integer playerCount = 1;
    PlayerDatabaseHandler playerDatabaseHandler;

    public GamePlay(Context context){

        this.context = context;
        this.playerDatabaseHandler = new PlayerDatabaseHandler(context,null,null,1);
        this.playerCount = playerDatabaseHandler.getPlayerCount();

    }
    public void loadPlayerInfo(Integer playerID){

        playerDatabaseHandler.loadPlayerInfo(playerID);

    }
    public String getPlayerName(){

        return playerDatabaseHandler.getPlayerName();

    }
    public Integer getPlayerDrinkCount(){

        return playerDatabaseHandler.getPlayerDrinkCount();

    }
    public Integer getCurrentPlayerID(){

        return currentPlayer;

    }
    public Integer getNextPlayerID(){

        if (currentPlayer < playerCount){

            currentPlayer = ++currentPlayer;

        }
        else {

            currentPlayer = 1;

        }
        return currentPlayer;

    }





















    

}
