package dk.lemonmelon.drunkify;

import android.content.Context;

import dk.lemonmelon.drunkify.DatabaseHandlers.ChallengeDatabaseHandler;

/**
 * Created by Mathies on 18-03-2016.
 */
public class Challenge {

    private Integer challengeID;
    private String challengeText;
    private Integer challengePunishment;
    private Integer challengeCount = 1;
    private String challengeExtra;
    private Context context;
    ChallengeDatabaseHandler challengeDatabaseHandler;

    public Challenge(Context context){

        this.context = context;
        this.challengeDatabaseHandler = new ChallengeDatabaseHandler(context,null,null,1);
        this.challengeCount = challengeDatabaseHandler.getChallengeCount();

    }
    public void newChallenge() {

        //Plus and minus are there to ensure value is never = 0

        Integer challengeID = 1 + (int)(long) Math.round((Math.random())* (challengeCount - 1));

        challengeDatabaseHandler.loadChallengeInfo(challengeID);

        this.challengeID = challengeID;
        this.challengeText = challengeDatabaseHandler.getChallengeText();
        this.challengePunishment = challengeDatabaseHandler.getChallengePunishment();
        this.challengeExtra = challengeDatabaseHandler.getChallengeExtra();

        Extra extra = new Extra(context);

        if(challengeExtra != null){

            this.challengeText = extra.getNewText(challengeText,challengeExtra,1);

        }

    }
    public String getChallengeID() {

        return String.valueOf(challengeID);
    }
    public String getChallengeText() {

        return challengeText;
    }
    public String getChallengePunishment() {

        return String.valueOf(challengePunishment);
    }
    public String  getChallengeCount(){

        return String.valueOf(challengeDatabaseHandler.getChallengeCount());

    }






}
