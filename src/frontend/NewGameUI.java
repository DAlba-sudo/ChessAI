package frontend;

import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class NewGameUI extends UI {

    private Button goBackBtn, practiceOpenings, playWithAI;

    public NewGameUI(){
        setHeader("Chess Game");
        // create instances of UI Components
        goBackBtn = UIManager.createGoBackBtn();
        practiceOpenings = UIManager.createBtn("Practice Openings");
        practiceOpenings.setOnAction(e -> {
            UIManager.displayUI(new PracticeOpeningsUI());
        });
        playWithAI = UIManager.createBtn("Play AI");
        playWithAI.setOnAction(e -> {
            UIManager.displayUI(new PlayAI());
        });

        addNode(goBackBtn, 0, 2);
        addNode(practiceOpenings, 2, 2);
        addNode(playWithAI, 0, 3);
    }

}
