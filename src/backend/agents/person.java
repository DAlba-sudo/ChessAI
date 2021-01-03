package backend.agents;

import backend.chess.env.ChessBoard;

public class person extends player {
    public person(int color, ChessBoard cb) {
        super(color, cb);
    }

    @Override
    public int[] queryMove() {
        return new int[0];
    }
}
