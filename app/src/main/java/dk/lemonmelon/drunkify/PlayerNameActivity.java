package dk.lemonmelon.drunkify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.os.Handler;

import dk.lemonmelon.drunkify.DatabaseHandlers.PlayerDatabaseHandler;

public class PlayerNameActivity extends AppCompatActivity {

    public static Integer playerCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        final PlayerDatabaseHandler playerDatabaseHandler = new PlayerDatabaseHandler(getApplicationContext(),null,null,1);

        playerDatabaseHandler.dropPlayerTable();

        playerDatabaseHandler.createPlayerTable();

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        final Button setPlayerNameButton = (Button) findViewById(R.id.setPlayerNameButton);

        final NumberPicker playerNumberPicker = (NumberPicker) findViewById(R.id.playerNumberPicker);
        playerNumberPicker.setMaxValue(30);
        playerNumberPicker.setMinValue(1);

        fadeInPlayerName(1);

        playerNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                playerCount = newVal;

                Integer valueDifference = newVal - oldVal;

                if (newVal > oldVal) {

                    for (int i = ++oldVal; i <= newVal; i++) {

                        fadeInPlayerName(i);
                    }

                }

                if (newVal < oldVal) {

                    for (int i = ++newVal; i <= oldVal; i++) {


                        final LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

                        findViewById(i).animate().alpha(0.0f).setDuration(1000);

                        final Integer id = i;
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ll.removeView(findViewById(id));
                            }
                        }, 1000);

                    }
                }

            }

        });

        setPlayerNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 1; i <= playerCount; i++) {

                    EditText playerNameEditText = (EditText) findViewById(i);
                    String playerName = playerNameEditText.getText().toString();

                    playerDatabaseHandler.addPlayer(i,playerName);
                }

                goToPlayActivity();
            }
        });
    }
    private void goToPlayActivity (){

        Intent goToPlayActivity = new Intent(getApplicationContext(),PlayActivity.class);
        startActivity(goToPlayActivity);

    }

    private void fadeInPlayerName (Integer i) {

        final LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

        EditText playerName = new EditText(getApplicationContext());
        playerName.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        String playerCountString = String.valueOf(i);

        playerName.setId(i);
        playerName.setHint("Spiller " + playerCountString);
        playerName.setAlpha(0);
        playerName.animate().alpha(1.0f).setDuration(1000);
        ll.addView(playerName);

    }


}
