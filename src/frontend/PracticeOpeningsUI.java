package frontend;

import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PracticeOpeningsUI extends UI {

    private Label selectedOpening;
    private Button addNewOpening;


    public PracticeOpeningsUI(){
        setHeader("Select an opening...");

        selectedOpening = UIManager.createLbl("Your selected opening: ");
        addNewOpening = UIManager.createBtn("Create New Opening");

        addNode(selectedOpening, 0, 1);
        addNode(addNewOpening, 0, 2);

    }

}
