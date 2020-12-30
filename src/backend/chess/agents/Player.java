package backend.chess.agents;

import backend.chess.ChessBoard;
import backend.chess.pieces.old.Piece;
import backend.chess.pieces.revised.PieceV2;

import java.util.LinkedList;

public class Player {

    private LinkedList<PieceV2> pieces = new LinkedList<>();
    private ChessBoard cb;
    private int color;

    public Player(ChessBoard cb, int color){
        this.cb = cb;
        this.color = color;
    }

    private void getPieces(){
        for(PieceV2[] rank : getBoard()){
            for(PieceV2 p : rank){
                if(p.getColor() == this.color){
                    pieces.add(p);
                }
            }
        }
    }

    public PieceV2[][]  getBoard(){
        return this.cb.getBoard();
    }

    public boolean movePiece(String notation) {
        return false;
    }
}
