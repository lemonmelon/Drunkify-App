package dk.lemonmelon.drunkify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        TextView challengeTextView = (TextView)findViewById(R.id.challengeText);
        TextView challengePunishmentView = (TextView)findViewById(R.id.challengePunishmentCounter);
        Button failChallengeButton = (Button)findViewById(R.id.failChallengeButton);
        
        ChallengeHandler challengeHandler = new ChallengeHandler(getApplicationContext());
        final GamePlay gamePlay = new GamePlay(getApplicationContext());

        Challenge challenge = challengeHandler.loadUnpackedChallenge((challengeHandler.getRandomChallengeID()));



        String challengeText = challenge.getChallengeText();
        String challengePunishment = String.valueOf(challenge.getChallengePunishment());

        challengeTextView.setText(challengeText);
        challengePunishmentView.setText(challengePunishment);

        failChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void goToPlayActivity (){

        Intent goToPlayActivity = new Intent(getApplicationContext(),PlayActivity.class);
        startActivity(goToPlayActivity);

    }

}

