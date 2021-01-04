package backend.agents;

import backend.chess.env.ChessBoard;
import backend.chess.env.ChessNotation;
import backend.chess.pieces.Piece;
import backend.ui.UI;
import backend.ui.UIManager;
import frontend.base.GameUI;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ai extends player{
    public ai(int color, ChessBoard cb) {
        super(color, cb);
    }

    public String generateRandomMove(boolean isCapturing){
        try{
            int piece_index = ThreadLocalRandom.current().nextInt(0, getPieces().size()+1);
            Piece p = getPieces().get(piece_index);
            LinkedList<int[]> moves = p.getMoves(isCapturing);
            if(moves.size() == 0){
                generateRandomMove(isCapturing);
            } else {
                int random_move_index = ThreadLocalRandom.current().nextInt(0, moves.size()+1);
                int[] move = moves.get(random_move_index);
                if (!movePieceRequest(move, isCapturing, p)){
                    generateRandomMove(isCapturing);
                } else {
                    return ChessNotation.toNotation(p, move, isCapturing);
                }
            }
        } catch (IndexOutOfBoundsException e){
            // TODO: 1/3/2021 Not sure why this catch is here so debug for that!
            generateRandomMove(isCapturing);
        }
        return null;
    }

    @Override
    public String queryMove() {
        String move = generateRandomMove(false);
        UI cUI = UIManager.getCurrent_ui();
        if(cUI instanceof GameUI){
            ((GameUI) cUI).getSendMoveBtn().setDisable(false);
        }
        System.out.println("AI Moved");
        return move;
    }
}
