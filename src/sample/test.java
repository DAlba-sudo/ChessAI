package sample;



import backend.agents.ai;
import backend.agents.player;
import backend.chess.env.CM;
import backend.chess.env.ChessBoard;


import java.io.IOException;
import java.util.Arrays;

public class test {

    public static void main(String[] args) throws IOException {
        ChessBoard cb = new ChessBoard();
        cb.displayBoard();

        ai jurdok = new ai(0, cb);
        ai diego_but_really_smart = new ai(1, cb);

        // TODO: 1/2/2021 Ensure all moves that are made are legal! Also don't forget that you need to fine-tune 
        // TODO: 1/2/2021 the 'from' notation generation thingy in ChessNotation! Connect the person class to the UI
        // TODO: 1/2/2021 and take her for a spin! 
        
        int rounds = 50;
        for(int i = 0; i < rounds; i++){
            jurdok.queryMove();
            diego_but_really_smart.queryMove();
            cb.displayBoard();
        }
    }
}
