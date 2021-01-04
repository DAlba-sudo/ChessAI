package frontend.base;

import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;

import java.util.LinkedList;

public class MainMenuUI extends GameUI {

    private Button goBackBtn, saveMoveList;
    private LinkedList<String> move_list = new LinkedList<>();


    public MainMenuUI() {
        setHeader("AlphaLocke OpenEngine");

        saveMoveList = UIManager.createBtn("Save Game");
        saveMoveList.setPrefWidth(Double.MAX_VALUE);

        goBackBtn = UIManager.createGoBackBtn();
        goBackBtn.setPrefWidth(Double.MAX_VALUE);


        addCML(0, 1, 10);
        addNode(getSendMoveTf(), 0, 2, 10);
        addNode(getSendMoveBtn(), 0, 3, 10);
        addNode(goBackBtn, 0, 4, 5);
        addNode(saveMoveList, 5, 4, 5);
    }
}
