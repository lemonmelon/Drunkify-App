package dk.lemonmelon.drunkify;

import android.content.Context;

import dk.lemonmelon.drunkify.DatabaseHandlers.ChallengeDatabaseHandler;
import dk.lemonmelon.drunkify.DatabaseHandlers.PlayerDatabaseHandler;

/**
 * Created by Mathies on 23-04-2016.
 */
public class Extra {

    private Context context;
    private Integer playerCount = 1;
    Integer databaseRowID = 1;

    PlayerDatabaseHandler playerDatabaseHandler;


    public Extra(Context context){

        this.context = context;
        this.playerDatabaseHandler = new PlayerDatabaseHandler(context,null,null,1);
        this.playerCount = playerDatabaseHandler.getPlayerCount();



    }
    public String getNewText(String text, String extra, Integer extraNumber){

        String player = "Spiller";
        String newText = text;

        String[] extraSplit = extra.split("_");

        if (extraSplit[1].equals("Random")){

            this.databaseRowID = 1 + (int)(long) Math.round((Math.random())* (playerCount - 1));

        }

        if(extraSplit[0].equals("Player")){

            playerDatabaseHandler.loadPlayerInfo(databaseRowID);
            String playerName = playerDatabaseHandler.getPlayerName();

            newText = text.replace("!" + String.valueOf(extraNumber), playerName);

        }
        return newText;

    }













}
