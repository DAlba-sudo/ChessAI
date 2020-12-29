package backend.ui;

import MISC.CONSTANTS;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Stack;

public class UIManager {

    // the stack will be used for navigating 'views'
    private static Stack<UI> uiStack = new Stack<>();

    // UI Properties
    private static UI current_ui = null;
    private static Stage current_stage;

    public UIManager(UI start, Stage current_stage){
        current_ui = start;
        this.current_stage = current_stage;

        // display our current ui
    }

    // display new ui
    public static void displayUI(UI ui){
        uiStack.push(ui);
        current_ui = ui;
        current_stage.getScene().setRoot(current_ui.getRoot());
    }

    // go back to last ui
    public static UI getLastUI(){
        System.out.println(uiStack.toString());
        if(current_ui == uiStack.peek()){
            // if our current ui is the top most one, return the next one
            uiStack.pop();
            current_ui = uiStack.peek();
            return uiStack.peek();
        } else {
            System.out.println(current_ui + " does not equal");;
            return null;
        }
    }

    // getter for our current ui
    public static UI getCurrent_ui() {
        return current_ui;
    }

    // ui additional stuff
    public static Button createBtn(String text){
        // creates a button
        Button btn = new Button(text);
        btn.setTextFill(CONSTANTS.STND_WHITE);
        btn.setBackground(CONSTANTS.STND_BUTTON_BACKGROUND);
        btn.setPadding(CONSTANTS.STND_PADDING);
        btn.setFont(CONSTANTS.STND_LABEL);

        return btn;
    }

    public static Label createLbl(String text){
        // creates a label
        Label lbl = new Label(text);
        lbl.setTextFill(CONSTANTS.STND_BLACK);
        lbl.setPadding(CONSTANTS.STND_PADDING);
        lbl.setWrapText(true);
        return lbl;
    }

    public static Label createHdr(String text){
        // creates a header (a large label)
        Label hdr = new Label(text);
        hdr.setPadding(CONSTANTS.STND_PADDING);
        hdr.setFont(CONSTANTS.STND_HEADER);
        hdr.setTextFill(CONSTANTS.STND_BLACK);
        hdr.setWrapText(true);
        return hdr;
    }

    public static Button createGoBackBtn(){
        // creates a button to go back with
        Button goBackBtn = createBtn("Return");
        goBackBtn.setOnMouseReleased(e -> {
            Parent lastRoot = getLastUI().getRoot();
            System.out.println(lastRoot.getClass().getName());
            if (lastRoot != null) {
                current_stage.getScene().setRoot(lastRoot);
            } else {
                System.out.println(UIManager.class.getName() + ": tried to go back but there was no root!" );
            }
        });
        return goBackBtn;
    }

    public static TextField createTf(String prompt){
        // creates a text field with a prompt
        TextField tf = new TextField();
        tf.setPadding(CONSTANTS.STND_PADDING);
        tf.setPromptText(prompt);
        return tf;
    }

    public static TextField createTf(){
        // Creates a text field with no prompt
        return createTf("");
    }

    // verification for textfields
    public static boolean verifyTf(TextField tf, int min_len, int max_len){
        return (tf.getText().length() < max_len) && (tf.getText().length() > min_len);
    }

    public static boolean isTfEmpty(TextField tf){
        boolean isEmpty = (tf.getText().length() == 0);
        if (isEmpty){
            tf.setBorder(CONSTANTS.TF_ERROR_BORDER);
        } else {
            tf.setBorder(CONSTANTS.STND_TF_BORDER);
        }
        return isEmpty;
    }




}
