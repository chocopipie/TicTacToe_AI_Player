package com.networkdemo;

public class Minimax extends AIController {

    static class MinimaxMove {
        int row;
        int col;
    };

    static char player1 = 'X';
    static char opponent1 = 'O';

    //determine if space is set already
    public static boolean isFull(char grid[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i][j] == ' ')
                    return false;

        return true;
    }

    static int checkMove(char grid[][]) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (grid[row][0] == grid[row][1] &&
                    grid[row][1] == grid[row][2])
            {
                if (grid[row][0] == player1)
                    return +10;
                else if (grid[row][0] == opponent1)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (grid[0][col] == grid[1][col] &&
                    grid[1][col] == grid[2][col])
            {
                if (grid[0][col] == player1)
                    return +10;

                else if (grid[0][col] == opponent1)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
        {
            if (grid[0][0] == player1)
                return +10;
            else if (grid[0][0] == opponent1)
                return -10;
        }

        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
        {
            if (grid[0][2] == player1)
                return +10;
            else if (grid[0][2] == opponent1)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    static int minimax(char board[][],
                       int depth, Boolean isMax)
    {
        int score = checkMove(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (isFull(board) == true)
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == ' ')
                    {
                        // Make the move
                        board[i][j] = player1;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == ' ')
                    {
                        // Make the move
                        board[i][j] = opponent1;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    static MinimaxMove findBestMove(char board[][])
    {
        int bestVal = -1000;
        MinimaxMove bestMove = new MinimaxMove();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j] == ' ')
                {
                    // Make the move
                    board[i][j] = player1;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = ' ';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

}
