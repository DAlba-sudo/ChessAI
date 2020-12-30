package backend.chess.agents;

import backend.chess.ChessBoard;
import backend.chess.ChessManager;
import backend.chess.pieces.old.Piece;
import backend.chess.pieces.revised.PieceV2;

import java.util.Arrays;
import java.util.LinkedList;

public class Player {

    private LinkedList<PieceV2> pieces = new LinkedList<>();
    private ChessBoard cb;
    private int color;

    public Player(ChessBoard cb, int color){
        this.cb = cb;
        this.color = color;
        getPieces();
    }

    private void getPieces(){
        for(PieceV2[] rank : getBoard()){
            for(PieceV2 p : rank){
                if(p != null){
                    if(p.getColor() == this.color){
                        pieces.add(p);
                    }
                }
            }
        }
    }

    public PieceV2[][]  getBoard(){
        return this.cb.getBoard();
    }

    public boolean movePiece(String notation) {
        boolean isAmbiguous = ChessManager.isAmbiguous(notation);
        int[] from = ChessManager.getCoordinateToMoveFrom(notation);

        Class<? extends PieceV2> piece_type = ChessManager.getPieceType(notation);
        for(PieceV2 p : pieces){
            if(!isAmbiguous){
                assert piece_type != null;
                if(piece_type.isInstance(p)){
                    // if a rook is referenced, is our object 'p' a rook? Yes.
                    if(p.isPosLegal(notation)){
                        p.setPosition(notation);
                        return true;
                    }
                }
            } else {
                // if it is not ambiguous we have to do some inferring
                if(from[0] != -1 && from[1] != -1){
                    // if we are given the exact coordinates, we move
                    if(Arrays.equals(p.getCurrentCoordinate(), from)){
                        if(p.isPosLegal(notation)){
                            p.setPosition(notation);
                            return true;
                        }
                    }
                } else if (from[0] != -1 && from[1] == -1){
                    // if we are only given the file, we infer from that
                    if(p.getCurrentCoordinate()[0] == from[0]){
                        if(p.isPosLegal(notation)){
                            p.setPosition(notation);
                            return true;
                        }
                    }
                } else {
                    // if it is ambiguous and we are not given a way to infer, it is wrong notation
                    return false;
                }
            }
        }
        return false;
    }
}
