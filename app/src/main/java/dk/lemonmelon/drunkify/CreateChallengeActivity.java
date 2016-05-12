package dk.lemonmelon.drunkify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import dk.lemonmelon.drunkify.DatabaseHandlers.ChallengeDatabaseHandler;

public class CreateChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_challenge);

        Button saveNewChallengeButton = (Button) findViewById(R.id.saveNewChallengeButton);
        final EditText newChallengeText = (EditText)  findViewById(R.id.newChallengeText);
        final EditText extraNameText = (EditText) findViewById(R.id.extraName);
        final EditText extraModifierText = (EditText) findViewById(R.id.extraModifier);

        final NumberPicker newChallengePunishmentPicker = (NumberPicker) findViewById(R.id.newChallengePunishmentPicker);

        newChallengePunishmentPicker.setMaxValue(10);
        newChallengePunishmentPicker.setMinValue(1);

        final ChallengeDatabaseHandler challengeDatabaseHandler = new ChallengeDatabaseHandler(getApplicationContext(),null,null,1);

        saveNewChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = newChallengeText.getText().toString();
                Integer punishment = newChallengePunishmentPicker.getValue();
                String extra = null;

                String extraName = extraNameText.getText().toString();
                String extraModifier = extraModifierText.getText().toString();

                if(extraName.isEmpty()){

                }
                else {

                    extra = extraName + "_" + extraModifier;

                }

                challengeDatabaseHandler.addChallenge(text, punishment,extra);
                Toast.makeText(getApplicationContext(), "Gemt", Toast.LENGTH_SHORT).show();

                finish();

            }
        });



    }
}
