package backend.agents;

import backend.chess.env.ChessBoard;
import backend.chess.env.ChessHelper;
import backend.chess.pieces.base.piece;
import backend.chess.pieces.extension.pawn;

import java.util.LinkedList;

public class player {
    private int color;
    private ChessBoard cb;
    private LinkedList<piece> pieces = new LinkedList<>();

    public player(int color, ChessBoard cb){
        this.color = color;
        setBoard(cb);
        setPiecesFromBoard();
    }

    private void setBoard(ChessBoard cb){
        this.cb = cb;
    }

    private void setPiecesFromBoard(){
        for(piece p : cb.getPieceList()){
            if(p.getColor() == getColor()){
                pieces.add(p);
            }
        }
    }

    public boolean move(String notation){
        Class<? extends piece> pieceType = ChessHelper.getPieceType(notation);
        boolean isCapturing = ChessHelper.isCapturing(notation);
        int[] to = ChessHelper.getDestinationFromNotation(notation);
        for(piece p : pieces){
            if(pieceType.isInstance(p)){
                // if piece type is the one we need let's check to make sure it is a legal move
                if(pieceType == pawn.class && !p.getDead()){
                    // special cases for pawn
                    if(isCapturing){
                        int originating_file = ChessHelper.getNumFromFile(notation.charAt(0));
                        if(p.getCurrentCoordinate()[0] == originating_file){
                            piece captured_piece = cb.findPieceByCoordinate(to);
                            if(p.isCaptureLegal(to) && captured_piece != null){
                                captured_piece.setDead();
                                p.setCoordinate(to);
                                return true;
                            }
                        }
                    } else {

                    }
                } else {
                    // handle for non-pawn cases
                }
            }
        }
    }

    public int getColor() {
        return color;
    }
}
