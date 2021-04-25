package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: blue    1: red     2: empty
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winning = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int activePlayer =0,i,checkFilled,f=0,j;
    String winner;
    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
//        Log.d("ABCD", String.valueOf(view));
//        Log.d("ABCD", String.valueOf(counter));

        Log.i("Tag",counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        checkFilled = gameState[tappedCounter];
        if(checkFilled!=2 || !gameActive){
            return;
        }
        gameState[tappedCounter] = activePlayer;

        counter.setTranslationY(-1500);
        if(activePlayer == 0) {
            counter.setImageResource(R.drawable.blue);
            activePlayer =1;
        }
        else if(activePlayer == 1){
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
        counter.animate().translationYBy(1500).setDuration(400);

        if(activePlayer==0)
            winner = "Red";
        else
            winner = "Blue";

        for(i=0;i<8;i++){
                if(gameState[winning[i][0]]==gameState[winning[i][1]] && gameState[winning[i][1]]==gameState[winning[i][2]] && gameState[winning[i][0]]!=2) {
                    gameActive = false;
                    //Toast.makeText(this, winner + " has Won", Toast.LENGTH_LONG).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winningTextView = (TextView) findViewById(R.id.winningTextView);
                    winningTextView.setText(winner + " won");
                    winningTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
                else{
                    f=0;
                    for(j=0;j<9;j++){
                        if(gameState[j]==2){
                            f=1;
                            break;
                        }
                    }
                    if(f==0){
                        gameActive = false;
                        //Toast.makeText(this, "It's a Tie", Toast.LENGTH_LONG).show();
                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                        TextView winningTextView = (TextView) findViewById(R.id.winningTextView);
                        winningTextView.setText("It's a tie");
                        winningTextView.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                    }
                }
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winningTextView = (TextView) findViewById(R.id.winningTextView);
        winningTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout =  findViewById(R.id.gridLayout);

        for(i=0;i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(i=0;i<9;i++){
            gameState[i]=2;
        }
        activePlayer =0;
        f=0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}