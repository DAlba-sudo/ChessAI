package frontend.base;

import backend.ui.ChessMoveList;
import backend.ui.UI;
import javafx.scene.control.ListView;

public class GameUI extends UI {

    private ChessMoveList cml;

    public GameUI(){
        cml = new ChessMoveList();
    }

    public void addMove(String move){
        cml.addMove(move);
    }

    public void addCML(int col, int row, int colspan){
        addNode(cml.getMoves(), col, row, colspan);
    }
}
