package frontend;

import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StartUI extends UI {

    private Button startGameBtn, goBackBtn;

    public StartUI(){
        setHeader("Welcome to Diego's Chess AI");

        goBackBtn = UIManager.createGoBackBtn();

        startGameBtn = UIManager.createBtn("Start Game");
        startGameBtn.setOnAction(e -> {
            UIManager.displayUI(new NewGameUI());
        });

        addNode(startGameBtn, 0, 1);
        addNode(goBackBtn, 2, 1);
    }

}
