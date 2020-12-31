package frontend;

import MISC.CONSTANTS;
import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PracticeOpeningsUI extends UI {

    private Label selectedOpening;
    private TextField addNewOpeningTf;
    private Button addNewOpeningBtn;


    public PracticeOpeningsUI(){
        setHeader("Select an opening...");

        selectedOpening = UIManager.createLbl("Your selected opening: ");
        addNewOpeningBtn = UIManager.createBtn("Create New Opening");
        addNewOpeningTf = UIManager.createTf();
        addNewOpeningBtn.setOnAction(e -> {
            // add an opening to the opening db and then go to new screen
            if(UIManager.isTfEmpty(addNewOpeningTf)){
                addNewOpeningTf.setBorder(CONSTANTS.TF_ERROR_BORDER);
            } else {
                try {
                    Files.createDirectory(Paths.get((System.getProperty("user.dir") + "\\src\\Data\\OPENINGS\\" +
                            addNewOpeningTf.getText() + "\\")));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                // after folder created, go into opening design
            }
        });

        addNode(selectedOpening, 0, 1);
        addNode(addNewOpeningBtn, 0, 2);

    }

}
