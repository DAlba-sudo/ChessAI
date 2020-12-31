package backend.chess.agents;

import backend.chess.ChessBoard;
import backend.chess.ChessManager;
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
        setPieces();
    }

    private void setPieces(){
        // used to update our pieces library (housekeeping)
        if(!pieces.isEmpty()){
            pieces.clear();
        }

        // for each matching piece that is our color, we get it
        for(PieceV2 p : getPiecesFromBoard()){
            if(p.getColor() == this.color && !p.getDead()){
                pieces.add(p);
            }
        }
    }

    private PieceV2[] getPiecesFromBoard(){
        return this.cb.getPiecesOnBoard();
    }

    public boolean movePiece(String notation) {
        // basic informatiom about the move we are about to do
        int[] target_location = ChessManager.getCoordinateToMoveTo(notation);
        boolean isAmbiguous = ChessManager.isAmbiguous(notation);
        boolean isCapturing = ChessManager.isCapturing(notation);
        Class<? extends PieceV2> piece_class = ChessManager.getPieceType(notation);

        // updating our piece library (might remove later)
        getPiecesFromBoard();

        if(!isAmbiguous){
            // if it is not ambigous (i.e., there is only one piece that matches this desc)
            for(PieceV2 p : pieces){
                if(piece_class.isInstance(p)){
                    // if this is a piece we should even consider looking at; check to make sure it is a legal pos
                    boolean doesNotCollide = cb.doesCollide(p, notation);
                    if(p.isPosLegal(notation) && doesNotCollide){
                        if(isCapturing){
                            cb.findPieceByCoordinate(target_location).setDead();
                            p.setPosition(notation);
                            return true;
                        } else {
                            p.setPosition(notation);
                            return true;
                        }
                    }
                }
            }
        } else {
            // if it is an ambiguous case, make some guess baby
            int[] move_from = ChessManager.getCoordinateToMoveFrom(notation);

            for(PieceV2 p : pieces){
                if(piece_class.isInstance(p)){
                    // if this is a piece we should even consider looking at; check to make sure it is a legal pos
                    if(move_from[0] != -1 && move_from[1] == -1){
                        // we are only given a file (N/bd3)
                        if(move_from[0] == p.getCurrentCoordinate()[0]) {
                            if(p.isPosLegal(notation) && cb.doesCollide(p, notation)){
                                if(isCapturing){
                                    cb.findPieceByCoordinate(target_location).setDead();
                                    p.setPosition(notation);
                                    return true;
                                } else {
                                    p.setPosition(notation);
                                    return true;
                                }
                            } else {
                                return false;
                            }
                        }
                    } else if (move_from[0] != -1 && move_from[1] != -1){
                        // if we are given both, we select based on matching arrays
                        if(Arrays.equals(p.getCurrentCoordinate(), move_from)){
                            if(p.isPosLegal(notation) && cb.doesCollide(p, notation)){
                                if(isCapturing){
                                    cb.findPieceByCoordinate(target_location).setDead();
                                    p.setPosition(notation);
                                    return true;
                                } else {
                                    p.setPosition(notation);
                                    return true;
                                }
                            } else {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        return false;
    }
}
