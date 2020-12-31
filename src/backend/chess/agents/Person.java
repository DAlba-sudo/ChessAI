package backend.chess.agents;

import backend.chess.ChessBoard;
import backend.ui.UI;
import frontend.PlayAI;

public class Person extends Player{

    private PlayAI ui;

    public Person(ChessBoard cb, int color, PlayAI ui) {
        super(cb, color);
        this.ui = ui;
    }

    @Override
    public String queryMove(){
        // allows the user to click the button
        ui.getMoveEnterBtn().setDisable(false);

        // wait for the button to be clicked
        while(!ui.getMoveEnterBtn().isDisabled()){
            // while it is not disabled (i.e., it has not been clicked)
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        // when the button has been clicked, check the tf and return this
        String player_move = ui.getMoveEnterTf().getText();
        if(player_move.length() > 0){
            return player_move;
        } else {
            return null;
        }
    }

}
