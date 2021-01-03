package backend.agents;

import backend.chess.env.ChessBoard;
import backend.chess.pieces.Piece;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ai extends player{
    public ai(int color, ChessBoard cb) {
        super(color, cb);
    }

    public void generateRandomMove(boolean isCapturing){
        try{
            int piece_index = ThreadLocalRandom.current().nextInt(0, getPieces().size()+1);
            Piece p = getPieces().get(piece_index);
            LinkedList<int[]> moves = p.getMoves(isCapturing);
            if(moves.size() == 0){
                generateRandomMove(isCapturing);
            } else {
                int random_move_index = ThreadLocalRandom.current().nextInt(0, moves.size()+1);
                if (!movePieceRequest(moves.get(random_move_index), false, p)){
                    generateRandomMove(isCapturing);
                } else {
                    return;
                }
            }
        } catch (IndexOutOfBoundsException e){
            generateRandomMove(isCapturing);
        }
    }

    @Override
    public int[] queryMove() {
        generateRandomMove(false);
        return new int[0];
    }
}
