package frontend;

import backend.chess.ChessGame;
import backend.chess.agents.AI;
import backend.chess.agents.Person;
import backend.ui.UI;
import backend.ui.UIManager;
import frontend.base.GameUI;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;

public class PlayAI extends GameUI {

    private ChessGame cg;
    private TextField moveEnterTf;
    private Button moveEnterBtn, goBackBtn;
    private boolean quitting = false;
    private Thread game_thread;

    public PlayAI(){
        super();
        // Chess Game Set UP
        setUpChessGame();

        setHeader("Playing AI");
        goBackBtn = UIManager.createGoBackBtn();
        goBackBtn.setOnAction(e -> {
            quitting = true;
        });

        moveEnterTf = UIManager.createTf();
        moveEnterBtn = UIManager.createBtn("Enter Move");
        moveEnterBtn.setOnAction(e -> {
            moveEnterBtn.setDisable(true);
        });

        addCML(0, 2, 10);
        addNode(moveEnterTf, 0, 3, 10);
        addNode(moveEnterBtn, 0 , 4);
        addNode(goBackBtn, 2, 4);

        game_thread = new Thread(()->{
            while(!Thread.interrupted() && !quitting){
                cg.startGame(quitting);
            }
        });

        game_thread.start();
    }

    public Button getMoveEnterBtn() {
        return moveEnterBtn;
    }

    public TextField getMoveEnterTf() {
        return moveEnterTf;
    }

    public void setUpChessGame(){
        try {
            cg = new ChessGame();
        } catch (IOException ioException){
            System.out.println("Chess game couldn't start! FileWriter raised IOExcpetion.");
        }

        Person player = new Person(cg.getBoard(), 1, this);
        AI bot = new AI(cg.getBoard(), 0);

        cg.setPlayers(player, bot);
    }
}
