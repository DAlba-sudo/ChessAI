package backend.chess;

import backend.chess.agents.Player;

public class ChessGame {

    private ChessBoard board;

    public ChessGame(Player black, Player white){
        board = new ChessBoard();
    }

}
