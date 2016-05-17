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
    private String challengeExtra;

    public void setChallengeInfo(Integer challengeID, String challengeText, Integer challengePunishment, String challengeExtra) {

        this.challengeID = challengeID;
        this.challengeText = challengeText;
        this.challengePunishment = challengePunishment;
        this.challengeExtra = challengeExtra;

    }
    public Integer getChallengeID() {

        return challengeID;
    }
    public String getChallengeText() {

        return challengeText;
    }
    public Integer getChallengePunishment() {

        return challengePunishment;
    }
    public String getChallengeExtra (){

        return challengeExtra;

    }
}
