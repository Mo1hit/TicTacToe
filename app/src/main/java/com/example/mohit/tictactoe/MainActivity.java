package com.example.mohit.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout rootLayout;

    public static final int PLAYER_O=0;
    public static final int PLAYER_X=1;
    public static final int NOPLAYER=-1;

    public static final int INCOMPLETE=1;
    public static final int PLAYER_X_WON=2;
    public static final int PLAYER_O_WON=3;
    public static final int DRAW=4;

    public int currentStatus;
    public int currentPlayer;

    public ArrayList<LinearLayout> rows;
    public TTTButton board[][];

    public int SIZE=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout=findViewById(R.id.rootLayout);

        Intent intent=getIntent();
        String gridSize=intent.getStringExtra("size");
        if(gridSize.equals("size3"))
        {
            SIZE=3;
        }
        else if(gridSize.equals("size4"))
        {
            SIZE=4;
        }
        else if(gridSize.equals("size5"))
        {
            SIZE=5;
        }
        setupGame();
    }

    public void setupGame()
    {
        currentStatus = INCOMPLETE;
        currentPlayer = PLAYER_X;
        rows = new ArrayList<>();
        board = new TTTButton[SIZE] [SIZE];
        rootLayout.removeAllViews();

        for(int i = 0 ; i < SIZE ; i++)
        {
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            rootLayout.addView(linearLayout);
            rows.add(linearLayout);
        }

        for(int i = 0 ; i < SIZE ; i++)
        {
            for(int j = 0 ; j < SIZE ; j++)
            {
                TTTButton button=new TTTButton(this);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(layoutParams);
                button.setOnClickListener(this);
                LinearLayout row=rows.get(i);
                row.addView(button);
                board[i][j]=button;
            }
        }
    }

    @Override
    public void onClick(View view)
    {
        if(currentStatus==INCOMPLETE)
        {
            TTTButton button=(TTTButton) view;
            button.setPlayer(currentPlayer);
            checkGameStatus();
            togglePlayer(currentPlayer);
        }
    }

    public void checkGameStatus()
    {
        //For Horizontal
        for(int i=0 ; i < SIZE ; i++)
        {
            boolean horizontal=true;
            TTTButton firstButton=board[i][0];
            for(int j=0 ; j < SIZE ; j++)
            {
                TTTButton button=board[i][j];
                if(button.getPlayer()!=firstButton.getPlayer()||button.getPlayer()==NOPLAYER)
                {
                    horizontal=false;
                    break;
                }
            }
            if(horizontal)
            {
                int playerWon=firstButton.getPlayer();
                upDateStauts(playerWon);
                return;
            }
        }

        //For Vertical
        for(int j=0;j<SIZE;j++)
        {
            boolean vertical=true;
            TTTButton firstButton=board[0][j];
            for(int i=0;i<SIZE;i++)
            {
                TTTButton button=board[i][j];
                if(button.getPlayer()!=firstButton.getPlayer()||button.getPlayer()==NOPLAYER)
                {
                    vertical=false;
                    break;
                }
            }
            if(vertical)
            {
                int playerWon=firstButton.getPlayer();
                upDateStauts(playerWon);
                return;
            }
        }

        //For First Diagonal
        boolean firstDiagonal=true;
        TTTButton firstDiagButton=board[0][0];
        for(int i=0;i<SIZE;i++)
        {
            TTTButton button=board[i][i];
            if(button.getPlayer()!=firstDiagButton.getPlayer()||button.getPlayer()==NOPLAYER)
            {
                firstDiagonal=false;
                break;
            }
        }
        if(firstDiagonal)
        {
            int playerWon=firstDiagButton.getPlayer();
            upDateStauts(playerWon);
            return;
        }

        //For Second Diagonal
        boolean secondDiagonal=true;
        TTTButton firstButton=board[0][SIZE-1];
        for(int i=0;i<SIZE;i++)
        {
            TTTButton button=board[i][SIZE-i-1];
            if(button.getPlayer()!=firstButton.getPlayer()||button.getPlayer()==NOPLAYER)
            {
                secondDiagonal=false;
                break;
            }
        }
        if(secondDiagonal)
        {
            int playerWon=firstButton.getPlayer();
            upDateStauts(playerWon);
            return;
        }

        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                TTTButton button=board[i][j];
                if(button.getPlayer()==NOPLAYER)
                {
                    return;
                }
            }
        }
        Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show();
        currentStatus=DRAW;
    }

    public void togglePlayer(int player)
    {
        if(player==PLAYER_X)
        {
            currentPlayer=PLAYER_O ;
        }
        else if(player==PLAYER_O)
        {
            currentPlayer=PLAYER_X;
        }
    }

    public void upDateStauts(int playerWon)
    {
        if(playerWon==PLAYER_X)
        {
            Toast.makeText(this, "PLAYER_X WON", Toast.LENGTH_LONG).show();
            currentStatus=PLAYER_X_WON;
        }
        else if(playerWon==PLAYER_O)
        {
            Toast.makeText(this, "PLAYER_O_WON", Toast.LENGTH_LONG).show();
            currentStatus=PLAYER_O_WON;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.resetItem||id==R.id.restart)
        {
            setupGame();
        }
        else if(id==R.id.size3)
        {
            SIZE=3;
            setupGame();
        }
        else if(id==R.id.size4)
        {
            SIZE=4;
            setupGame();
        }
        else if(id==R.id.size5)
        {
            SIZE=5;
            setupGame();
        }
        return super.onContextItemSelected(item);
    }
}