package frontend;

import backend.agents.ai;
import backend.agents.person;
import backend.chess.env.ChessGame;
import backend.ui.UI;
import backend.ui.UIManager;
import frontend.base.GameUI;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.beans.EventHandler;

public class PlayAIUI extends GameUI {

    private Button goBackBtn;
    private ChessGame cg;

    public PlayAIUI() {
        cg = new ChessGame();

        ai dumb_machine = new ai(0, cg.getCb());
        person me = new person(1, cg.getCb());
        cg.setBlack(dumb_machine);
        cg.setWhite(me);

        cg.setGameIsAlive(true);

        setHeader("Play the AI");
        goBackBtn = UIManager.createGoBackBtn();

        addCML(0, 1, 10);
        addNode(getSendMoveTf(), 0, 2, 10);
        addNode(getSendMoveBtn(), 0, 3);
        addNode(goBackBtn, 4, 3);

        cg.start();
    }
}
