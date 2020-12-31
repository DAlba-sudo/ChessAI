package backend.chess.agents;

import backend.chess.ChessBoard;
import backend.ui.UIManager;
import frontend.PlayAI;
import sun.misc.Queue;

public class AI extends Player {
    Queue<String> moves = new Queue<>();

    public AI(ChessBoard cb, int color){
        super(cb, color);
        if(color == 0){
            moves.enqueue("e5");
            moves.enqueue("Nf6");
            moves.enqueue("Qe7");
        } else {
            moves.enqueue("e4");
            moves.enqueue("Nf3");
            moves.enqueue("Qe2");
        }

    }

    @Override
    public String queryMove(){
        return null;
    }
}
