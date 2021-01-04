package backend.chess.env;

import backend.agents.player;
import backend.ui.UIManager;
import frontend.base.GameUI;

public class ChessGame {
    private ChessBoard cb;
    private player white, black;
    private int numberOfMoves = 0;
    private Thread gameThread;
    private boolean gameIsAlive = true;

    public ChessGame(ChessBoard cb, player white, player black) {
        this.cb = cb;
        this.white = white;
        this.black = black;
        gameThread = new Thread(this::gameLoop);
    }

    public ChessGame(player white, player black) {
        this.cb = new ChessBoard();
        this.white = white;
        this.black = black;
        gameThread = new Thread(this::gameLoop);
    }

    public ChessGame() {
        this.cb = new ChessBoard();
        gameThread = new Thread(this::gameLoop);
    }

    public void start(){
        gameThread.start();
    }

    private void gameLoop(){
        while(isGameIsAlive() && !Thread.interrupted()){
            gameLogic();
        }
    }

    private void gameLogic() {
        // (1) depending on the turn, the "chess game" needs to query a move from specific players
        // (2) the move needs to be validated (check, checkmate, etc)
        // (3) UI needs to be updated
        String moveInNotation;
        if(getNumberOfMoves() == 0 || (getNumberOfMoves() & 2) == 0){
            // game start is white to move
             moveInNotation = getWhite().queryMove();
        } else {
            moveInNotation = getBlack().queryMove();
        }

        if(moveInNotation == null){
            // the move is not valid, so return or raise error (null is returned for invalid moves)
            return;
        }

        if(UIManager.getCurrent_ui() instanceof GameUI){
            // if we are playing a game, add the recently queried move
            ((GameUI) UIManager.getCurrent_ui()).addMove(moveInNotation);
            incrementNumberOfMoves();
        } else {
            setGameIsAlive(false);
            return;
        }
    }


    public boolean isGameIsAlive() {
        return gameIsAlive;
    }

    public void setGameIsAlive(boolean gameIsAlive) {
        this.gameIsAlive = gameIsAlive;
    }

    public void setWhite(player white) {
        this.white = white;
    }

    public void setBlack(player black) {
        this.black = black;
    }

    public ChessBoard getCb() {
        return cb;
    }

    public player getWhite() {
        return white;
    }

    public player getBlack() {
        return black;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void incrementNumberOfMoves(){
        this.numberOfMoves += 1;
    }
}
