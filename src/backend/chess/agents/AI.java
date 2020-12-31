package backend.chess.agents;

import backend.chess.ChessBoard;
import backend.chess.ChessManager;
import backend.chess.pieces.revised.PieceV2;
import backend.ui.UIManager;
import frontend.PlayAI;
import sun.misc.Queue;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AI extends Player {

    public AI(ChessBoard cb, int color){
        super(cb, color);

    }

    public String randomMove(boolean includeCapture){
        int randomNum = ThreadLocalRandom.current().nextInt(0, getPieces().size());
        PieceV2 piece = getPieces().get(randomNum);
        LinkedList<int[]> getLegalMoves = piece.getLegalMoves(includeCapture);
        if(!getLegalMoves.isEmpty()){
            // does collide returns true when no collissions (i know I will have to rename later)
            getLegalMoves.removeIf(coordinate -> !getCb().doesCollide(piece, coordinate) || (includeCapture && getCb().findPieceByCoordinate(coordinate) == null));
            if(!getLegalMoves.isEmpty()){
                // does collide returns true when no collissions (i know I will have to rename later)
                int random_move_index = ThreadLocalRandom.current().nextInt(0, getLegalMoves.size());
                return ChessManager.coordinateToNotation(getLegalMoves.get(random_move_index), piece, false);
            } else {
                return randomMove(includeCapture);
            }
        } else {
            return randomMove(includeCapture);
        }
    }

    @Override
    public String queryMove(){
        return randomMove(false);
    }
}
