package frontend.base;

import MISC.CONSTANTS;
import backend.ui.ChessMoveList;
import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveGameUI extends UI {
    private Button goBackBtn, saveGameBtn;
    private Label nameOfGameLbl, player1Lbl, player2Lbl, dateOfGameLbl, openingNameLbl;
    private TextField nameOfGameTf, player1Tf, player2Tf, dateOfGameTf, openingNameTf;

    private ChessMoveList cml;

    public SaveGameUI(ChessMoveList cml) {
        this.cml = cml;

        goBackBtn = UIManager.createGoBackBtn();
        saveGameBtn = UIManager.createBtn("Save Game");
        saveGameBtn.setOnAction(e -> {
            boolean emptyFlag = false;
            if(UIManager.isTfEmpty(nameOfGameTf)){
                nameOfGameTf.setBorder(CONSTANTS.TF_ERROR_BORDER);
                emptyFlag = true;
            }

            if(UIManager.isTfEmpty(player1Tf)){
                player2Tf.setBorder(CONSTANTS.TF_ERROR_BORDER);
                emptyFlag = true;
            }

            if(UIManager.isTfEmpty(player2Tf)){
                player2Tf.setBorder(CONSTANTS.TF_ERROR_BORDER);
                emptyFlag = true;
            }

            if(UIManager.isTfEmpty(dateOfGameTf)){
                dateOfGameTf.setBorder(CONSTANTS.TF_ERROR_BORDER);
                emptyFlag = true;
            }

            if(UIManager.isTfEmpty(openingNameTf)){
                openingNameTf.setBorder(CONSTANTS.TF_ERROR_BORDER);
                emptyFlag = true;
            }

            if(!emptyFlag){
                Path data_dir = Paths.get(System.getProperty("user.dir")+"\\src\\Data\\");
                Path game_path = Paths.get(data_dir.toString(), "");

            }
        });

        nameOfGameLbl = UIManager.createLbl("Name of Game: ");
        player1Lbl = UIManager.createLbl("Player (White): ");
        player2Lbl = UIManager.createLbl("Player (Black): ");
        dateOfGameLbl = UIManager.createLbl("Date of Game: ");
        openingNameLbl = UIManager.createLbl("Opening Played: ");

        nameOfGameTf = UIManager.createTf("Anderson vs Kieseritzky");
        player1Tf = UIManager.createTf("Anderson");
        player2Tf = UIManager.createTf("Kieseritzky");
        dateOfGameTf = UIManager.createTf("1851");
        openingNameTf = UIManager.createTf("King's Gambit");

        addNode(nameOfGameLbl, 0, 1, 10);
        addNode(nameOfGameTf, 0, 2, 10);

        addNode(player1Lbl, 0, 3, 10);
        addNode(player1Tf, 0, 4, 10);

        addNode(player2Lbl, 0, 5, 10);
        addNode(player2Tf, 0, 6, 10);

        addNode(dateOfGameLbl, 0, 7, 10);
        addNode(dateOfGameTf, 0, 8, 10);

        addNode(openingNameLbl, 0, 9, 10);
        addNode(openingNameTf, 0, 10, 10);

        addNode(saveGameBtn, 0, 11);
        addNode(goBackBtn, 5, 11);

    }
}
