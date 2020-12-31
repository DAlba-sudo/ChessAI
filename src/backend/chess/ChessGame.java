package backend.chess;

import backend.chess.agents.Player;
import backend.fsm.state;
import backend.ui.UIManager;
import frontend.PlayAI;
import frontend.base.GameUI;
import sun.misc.Queue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChessGame {

    private ChessBoard board;
    private Player black, white;
    private boolean endGame = false;

    Queue<String> moveQueue = new Queue<>();

    // data saving
    private Path data_dir = Paths.get(System.getProperty("user.dir")+"\\src\\Data");
    private Path current_game = Paths.get(data_dir.toString() + "\\cgame.txt");

    private boolean whiteToMove = true;

    public ChessGame() throws IOException {
        new BufferedWriter(new FileWriter(new File(current_game.toUri()), false)).close();
        board = new ChessBoard();
    }

    public ChessGame(Player black, Player white) throws IOException {
        this();
        this.black = black;
        this.white = white;
    }



    private Player getBlack() {
        return black;
    }

    private Player getWhite() {
        return white;
    }

    public void startGame(boolean quitting){
        while(!Thread.interrupted() && !quitting){
            gameLogic();
        }
    }

    public void setPlayers(Player p1, Player p2){
        if(p1.getColor() == 1){
            this.white = p1;
            this.black = p2;
        } else if (p1.getColor() == 0){
            this.black = p1;
            this.white = p2;
        }
    }

    // set out the logic for the game
    private void gameLogic(){
        Player playerToMove;
        if(whiteToMove){
            playerToMove = getWhite();
        } else {
            playerToMove = getBlack();
        }

        // asks for a move from the player
        String move = playerToMove.queryMove();
        if(playerToMove.movePiece(move)){
            // if it is a valid move (record it as such)
//            recordMove(move);
            if(GameUI.class.isInstance(UIManager.getCurrent_ui())){
                GameUI ui = (GameUI) UIManager.getCurrent_ui();
                ui.addMove(move);
            }
            whiteToMove = !whiteToMove;
        } else {
            System.out.println("Move Failed! [" + playerToMove.getColor() + "] @" + move);
        }
    }

    public void recordMove(String move){
        try {
            BufferedWriter game = new BufferedWriter(new FileWriter(new File(current_game.toUri()), true));
            if (whiteToMove) {
                // if white, simply write the move
                game.write((move.trim().replace("\n", "").replace("\r", "") + ";"));
            } else {
                // if black, write the move with a '\n'
                game.write((move.trim().replace("\n", "").replace("\r", "") + "\n"));
            }
            game.flush();
            game.close();
        } catch (IOException e){
            System.out.println("Couldn't record move! @" + move);
        }
    }

    public ChessBoard getBoard() {
        return board;
    }

    public Path getCurrenGamePath() {
        return current_game;
    }
}
