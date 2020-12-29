package frontend;

import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class NewGameUI extends UI {

    private Button goBackBtn;

    private ListView<String> moves = new ListView<>();

    public NewGameUI(){
        setHeader("Chess Game");
        // create instances of UI Components
        goBackBtn = UIManager.createGoBackBtn();

        // create the moves list



        addNode(goBackBtn, 0, 2);
    }

}
