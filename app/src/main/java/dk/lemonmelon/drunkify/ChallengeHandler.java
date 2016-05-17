package dk.lemonmelon.drunkify;

import android.content.Context;

import dk.lemonmelon.drunkify.DatabaseHandlers.ChallengeDatabaseHandler;

/**
 * Created by Mathies on 17-05-2016.
 */
public class ChallengeHandler {

    private Context context;
    private ChallengeDatabaseHandler challengeDatabaseHandler;
    private Extra extra;

    public ChallengeHandler(Context context){

        this.context = context;
        this.challengeDatabaseHandler = new ChallengeDatabaseHandler(context,null,null,1);
        this.extra = new Extra(context);

    }
    public Challenge getChallenge(Integer ID){

        return challengeDatabaseHandler.getChallengeInfo(ID);

    }
    public Challenge loadUnpackedChallenge (Integer ID){

     Challenge challenge = getChallenge(ID);

        String challengeExtra = challenge.getChallengeExtra();

        if(challengeExtra != null){

            challenge = extra.unpackChallenge(challenge);

        }
        return challenge;

    }
    public Integer getRandomChallengeID (){

        //Plus and minus are there to ensure value is never = 0

        return 1 + (int)(long) Math.round((Math.random())* (getChallengeCount()- 1));

    }
    public Integer  getChallengeCount(){

        return challengeDatabaseHandler.getChallengeCount();

    }
}
