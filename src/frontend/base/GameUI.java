package frontend.base;

import MISC.CONSTANTS;
import backend.ui.ChessMoveList;
import backend.ui.UI;
import backend.ui.UIManager;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GameUI extends UI {

    private ChessMoveList cml;
    private TextField sendMoveTf;
    private Button sendMoveBtn;

    public GameUI(){
        cml = new ChessMoveList();
        sendMoveTf = UIManager.createTf();
        sendMoveBtn = UIManager.createBtn("Enter Move");
        sendMoveBtn.setPrefWidth(Double.MAX_VALUE);
        sendMoveBtn.setOnMouseClicked(e -> {
            if(sendMoveTf.getText().length() <= 0) {
                sendMoveTf.setBorder(CONSTANTS.TF_ERROR_BORDER);
                return;
            } else {
                addMove(getSendMoveTf().getText());
            }
        });
    }

    public boolean isMoveReady(){
        if(sendMoveTf.getText().length() >= 2 && sendMoveBtn.isDisabled()){
            return true;
        }
        return false;
    }

    public String getMove(){
        return sendMoveTf.getText();
    }

    public void addMove(String move){
        cml.addMove(move);
    }

    public void addCML(int col, int row, int colspan){
        addNode(cml.getMoves(), col, row, colspan);
    }

    public TextField getSendMoveTf() {
        return sendMoveTf;
    }

    public void setSendMoveTf(TextField sendMoveTf) {
        this.sendMoveTf = sendMoveTf;
    }

    public Button getSendMoveBtn() {
        return sendMoveBtn;
    }

    public void setSendMoveBtn(Button sendMoveBtn) {
        this.sendMoveBtn = sendMoveBtn;
    }
}
