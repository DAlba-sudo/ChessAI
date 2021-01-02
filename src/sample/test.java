package sample;



import backend.chess.env.ChessBoard;
import backend.chess.env.ChessHelper;
import backend.chess.pieces.base.PieceHelper;

import java.io.IOException;
import java.util.Arrays;

public class test {

    public static void main(String[] args) throws IOException {
        ChessBoard cb = new ChessBoard();
        cb.displayBoard();
        System.out.println(cb.getPieceFromNotation("e2"));

    }
}
