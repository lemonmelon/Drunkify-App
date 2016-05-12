package dk.lemonmelon.drunkify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startGameButton = (Button) findViewById(R.id.playDrunkifyButton);
        Button createChallenge = (Button) findViewById(R.id.createChallengeButton);
        Button dropTable = (Button) findViewById(R.id.dropTableButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToPlayerNameActivity();

            }
        });

        createChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToCreateChallengeActivity();

            }
        });

        }

    private void goToPlayerNameActivity (){

        Intent goToPlayerNameActivity = new Intent(getApplicationContext(),PlayerNameActivity.class);
        startActivity(goToPlayerNameActivity);

    }
    private void goToCreateChallengeActivity (){

        Intent goToCreateChallengeActivity = new Intent(getApplicationContext(),CreateChallengeActivity.class);
        startActivity(goToCreateChallengeActivity);

    }
}


