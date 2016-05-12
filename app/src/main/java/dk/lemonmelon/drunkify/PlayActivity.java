package dk.lemonmelon.drunkify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.lemonmelon.drunkify.DatabaseHandlers.PlayerDatabaseHandler;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final GamePlay gamePlay = new GamePlay(getApplicationContext());

        gamePlay.loadPlayerInfo(gamePlay.getCurrentPlayerID());
        setPlayerInfo(gamePlay.getPlayerName());

        Button toChallengeButton = (Button)findViewById(R.id.toChallengeButton);
        toChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToChallengeActivity(gamePlay.getCurrentPlayerID());

                gamePlay.loadPlayerInfo(gamePlay.getNextPlayerID());
                setPlayerInfo(gamePlay.getPlayerName());

            }
        });
    }
    private void setPlayerInfo(String playerName){

        final TextView currentPlayerName = (TextView) findViewById(R.id.currentPlayerName);

        currentPlayerName.setText(playerName + "s tur");

    }

    private void goToChallengeActivity(Integer currentPlayerID){

        Intent goToChallengeActivity = new Intent(getApplicationContext(),ChallengeActivity.class);
        startActivity(goToChallengeActivity);


    }
}
