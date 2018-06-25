package com.example.mohit.tictactoe;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class TTTButton extends AppCompatButton
{
    private int player=MainActivity.NOPLAYER;

    public TTTButton(Context context)
    {
        super(context);
    }

    public void setPlayer(int player)
    {
        this.player=player;
        if(player==MainActivity.PLAYER_O)
        {
            setText("O");
        }
        else if(player==MainActivity.PLAYER_X)
        {
            setText("X");
        }
        setEnabled(false);
    }

    public int getPlayer()
    {
        return player;
    }
}