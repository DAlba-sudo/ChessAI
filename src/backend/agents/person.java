package backend.agents;

import backend.chess.env.ChessBoard;
import backend.ui.UI;
import backend.ui.UIManager;
import frontend.base.GameUI;

public class person extends player {
    public person(int color, ChessBoard cb) {
        super(color, cb);
    }

    @Override
    public String queryMove() {
        UI cUI = UIManager.getCurrent_ui();
        if(cUI instanceof GameUI){
            if(((GameUI) cUI).isMoveReady()){
                String move = ((GameUI) cUI).getMove();
                if(movePieceRequest(move)){
                    ((GameUI) cUI).getSendMoveBtn().setDisable(true);
                    return move;
                } else {
                    return null;
                }
            };
        }
        return null;
    }
}
