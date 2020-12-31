package sample;


import backend.chess.ChessBoard;
import backend.chess.ChessGame;
import backend.chess.ChessManager;
import backend.chess.agents.Player;
import backend.chess.pieces.revised.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {

    private static ChessBoard cb;
    private static Path data_dir = Paths.get(System.getProperty("user.dir")+"\\src\\Data");

    public static void main(String[] args) throws IOException {

    }

    public static void movePieceShorthand(Player p, String notation){
        if(p.movePiece(notation)){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move! > " + notation);
        }
        System.out.println();
        System.out.println("---------------------------");

    }

}
