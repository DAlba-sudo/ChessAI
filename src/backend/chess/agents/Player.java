package backend.chess.agents;

import backend.chess.ChessBoard;
import backend.chess.pieces.old.Piece;

import java.util.LinkedList;

public class Player {

    private LinkedList<Piece> pieces = new LinkedList<>();
    private ChessBoard cb;
    private int color;

    public Player(ChessBoard cb, int color){
        this.cb = cb;
        this.color = color;
    }

    private void getPieces(){
        for(Piece[] rank : getBoard()){
            for(Piece p : rank){
                if(p.getColor() == this.color){
                    pieces.add(p);
                }
            }
        }
    }

    public Piece[][]  getBoard(){
        return this.cb.getBoard();
    }

    public boolean movePiece(String notation) {
        return false;
    }
}
