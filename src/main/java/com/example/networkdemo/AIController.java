package com.example.networkdemo;

public class AIController extends Board{

    //makeMove(x,y)
    public Minimax.MinimaxMove makeMove(char grid[][]) {
                Minimax.MinimaxMove bestMove = Minimax.findBestMove(grid);
                return bestMove;
            }

    //rematchRequest
    public requestRematch() {

    }

    //rematchAccept
    public requestRematch() {

    }
}