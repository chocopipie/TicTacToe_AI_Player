package com.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AIController {

    //makeMove(x,y)
    public Minimax.MinimaxMove makeMove(char grid[][]) {
                Minimax.MinimaxMove bestMove = Minimax.findBestMove(grid);
                return bestMove;
            }

    //rematchRequest
    

    //rematchAccept

}