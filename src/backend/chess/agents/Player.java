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
        boolean isCapturing = ChessManager.isCapturing(notation);
        int[] from = ChessManager.getCoordinateToMoveFrom(notation);
        Class<? extends PieceV2> piece_type = ChessManager.getPieceType(notation);
        boolean canJump;

        for(int i = 0; i < pieces.size(); i++){
            PieceV2 p = pieces.get(i);
            if(!isAmbiguous){
                assert piece_type != null;
                if(piece_type.isInstance(p)){
                    // if a rook is referenced, is our object 'p' a rook? Yes.
                    if(p.isPosLegal(notation)){
                        int[] current_pos = ChessManager.getCoordinateToMoveTo(p.getCurrentPosition());
                        int[] target_pos =  ChessManager.getCoordinateToMoveTo(notation);
                        canJump = p.canJump();
                        if(!canJump) {
                            if(!cb.checkIfPiecesCollide(p, notation, isCapturing)) {
                                if (!isCapturing) {
                                    cb.getBoard()[current_pos[1] - 1][current_pos[0] - 1] = null;
                                    cb.getBoard()[target_pos[1] - 1][target_pos[0] - 1] = p;
                                } else {
                                    cb.getBoard()[current_pos[1] - 1][current_pos[0] - 1] = null;
                                    cb.getBoard()[target_pos[1] - 1][target_pos[0] - 1].setDead();
                                }
                            }
                        }
                        cb.getBoard()[target_pos[1]-1][target_pos[0]-1] = p;
                        return p.setPosition(notation);
                    }
                }
            } else {
                // if it ambiguous we have to do some inferring
                if(from[0] != -1 && from[1] != -1){
                    // if we are given the exact coordinates, we move
                    if(Arrays.equals(p.getCurrentCoordinate(), from)){
                        if(p.isPosLegal(notation)){
                            int[] current_pos = ChessManager.getCoordinateToMoveTo(p.getCurrentPosition());
                            int[] target_pos =  ChessManager.getCoordinateToMoveTo(notation);
                            if(!isCapturing){
                                cb.getBoard()[current_pos[1]-1][current_pos[0]-1] = null;
                                cb.getBoard()[target_pos[1]-1][target_pos[0]-1] = p;
                            } else {
                                cb.getBoard()[current_pos[1]-1][current_pos[0]-1] = null;
                                cb.getBoard()[target_pos[1]-1][target_pos[0]-1].setDead();
                            }
                            cb.getBoard()[target_pos[1]-1][target_pos[0]-1] = p;
                            return p.setPosition(notation);
                        }
                    }
                } else if (from[0] != -1 && from[1] == -1){
                    // if we are only given the file, we infer from that
                    if(p.getCurrentCoordinate()[0] == from[0]){
                        if(p.isPosLegal(notation)){
                            int[] current_pos = ChessManager.getCoordinateToMoveTo(p.getCurrentPosition());
                            int[] target_pos =  ChessManager.getCoordinateToMoveTo(notation);
                            if(!isCapturing){
                                cb.getBoard()[current_pos[1]-1][current_pos[0]-1] = null;
                                cb.getBoard()[target_pos[1]-1][target_pos[0]-1] = p;
                            } else {
                                cb.getBoard()[current_pos[1]-1][current_pos[0]-1] = null;
                                cb.getBoard()[target_pos[1]-1][target_pos[0]-1].setDead();
                            }
                            cb.getBoard()[target_pos[1]-1][target_pos[0]-1] = p;
                            return p.setPosition(notation);
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
