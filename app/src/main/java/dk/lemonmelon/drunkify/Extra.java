package dk.lemonmelon.drunkify;

import android.content.Context;

import dk.lemonmelon.drunkify.DatabaseHandlers.ChallengeDatabaseHandler;
import dk.lemonmelon.drunkify.DatabaseHandlers.PlayerDatabaseHandler;

/**
 * Created by Mathies on 23-04-2016.
 */
public class Extra {

    private Context context;
    Integer databaseRowID = 1;


    PlayerDatabaseHandler playerDatabaseHandler;


    public Extra(Context context){

        this.context = context;

    }
    public Challenge unpackChallenge(Challenge challenge){

        this.playerDatabaseHandler = new PlayerDatabaseHandler(context,null,null,1);

        Integer challengeID = challenge.getChallengeID();
        String challengeText = challenge.getChallengeText();
        Integer challengePunishment = challenge.getChallengePunishment();
        String challengeExtra = challenge.getChallengeExtra();

        String [] extraSplit = challenge.getChallengeExtra().split("_");


        if (extraSplit[1].equals("Random")){

            Integer count = playerDatabaseHandler.getPlayerCount();

            this.databaseRowID = 1 + (int)(long) Math.round((Math.random())* (count - 1));

            playerDatabaseHandler.loadPlayerInfo(databaseRowID);

            String playerName = playerDatabaseHandler.getPlayerName();

            challengeText = challengeText.replace("!1",playerName);

        }
        if(extraSplit[0].equals("Player")){

            playerDatabaseHandler.loadPlayerInfo(databaseRowID);

        }

        challenge.setChallengeInfo(challengeID,challengeText,challengePunishment,null);

        return challenge;

    }


}
