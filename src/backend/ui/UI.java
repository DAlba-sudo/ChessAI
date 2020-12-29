package backend.ui;

import MISC.CONSTANTS;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class UI {
    private GridPane root;
    private Label header_lbl;

    public UI(){
        this.root = new GridPane();
        this.root.setPadding(CONSTANTS.STND_PADDING);
        this.root.setVgap(8);
        this.root.setHgap(8);
        this.root.setAlignment(Pos.CENTER);

    }

    public void setHeader(String header){
        Label hdr = UIManager.createHdr(header);
        this.header_lbl = hdr;
        addNode(this.header_lbl, 0, 0, 10, 1);
    }

    public GridPane getRoot() {
        return root;
    }

    public void addNode(Control node, int col, int row, int colspan, int rowspan){
        this.root.add(node, col, row, colspan, rowspan);
    }

    public void addNode(Control node, int col, int row, int colspan){
        this.root.add(node, col, row, colspan, 1);
    }

    public void addNode(Control node, int col, int row){
        this.addNode(node, col, row, 1, 1);
    }
}
