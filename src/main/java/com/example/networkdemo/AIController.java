package com.example.networkdemo;

import com.example.networkdemo.Board;

public class AIController extends Board{

    //makeMove(x,y)
    public static Minimax.MinimaxMove makeMove(char[][] grid) {
                Minimax.MinimaxMove bestMove = Minimax.findBestMove(grid);
                return bestMove;
            }
}