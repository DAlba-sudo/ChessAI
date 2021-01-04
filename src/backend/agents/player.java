package backend.agents;

import backend.chess.env.ChessBoard;
import backend.chess.env.ChessNotation;
import backend.chess.pieces.*;

import java.util.LinkedList;

public abstract class player {
    private int color;
    private LinkedList<Piece> pieces = new LinkedList<>();
    private ChessBoard cb;

    public player(int color, ChessBoard cb){
        setColor(color);
        setCb(cb);
        getPiecesFromBoard();
    }

    public boolean movePieceRequest(String notation){
        int[] to = ChessNotation.findTo(notation);
        boolean isCapture = ChessNotation.isCapture(notation);
        Class<? extends Piece> pieceType = ChessNotation.getPieceType(notation);
        int[] from = ChessNotation.findFrom(notation, pieceType);

        Piece piece = getPieceGivenInfo(to, from, isCapture, pieceType);

        if(piece != null) {
            return movePieceRequest(to, isCapture, piece);
        } else {
            return false;
        }
    }

    private Piece getPieceGivenInfo(int[] to, int[] from, boolean isCapturing, Class<? extends Piece> pieceType) {
        for(Piece p : pieces){
            if(pieceType.isInstance(p)){
                if(from[0] != -1 || from[1] != -1){
                    if((from[0] == p.getPos()[0] && (from[1] == -1)) || (from[0] == p.getPos()[0] && (from[1] == p.getPos()[1]))){
                        // if the piece matches some parameter from our thing
                        return p;
                    }
                } else {
                    if(p.isMoveLegal(to, isCapturing)){
                        return p;
                    }
                }
            }
        }
        return null;
    }

    public boolean movePieceRequest(int[] to, boolean isCapture, Piece piece){
        getPiecesFromBoard(); // update our piece list each time

        boolean isValidMove = getCb().validateMove(to, isCapture, piece);
        // if the chess board validates the move, hooray! Now move the piece.
        if(isValidMove){
            // move the piece
            if(isCapture){
                Piece pieceTkn = getCb().locatePiece(to);
                pieceTkn.setDead(true);
                getCb().getPieces().remove(pieceTkn);
            }
            piece.setPos(to);
            return true;
        } else {
            return false;
        }
    }

    private void getPiecesFromBoard(){
        for(Piece p : getCb().getPieces()){
            if(p.getColor() == getColor() && !p.isDead()){
                // if the pieces' color is the same as our own and is alive
                // take that junt
                pieces.add(p);
            }
        }
    }

    public LinkedList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(LinkedList<Piece> pieces) {
        this.pieces = pieces;
    }

    public ChessBoard getCb() {
        return cb;
    }

    public void setCb(ChessBoard cb) {
        this.cb = cb;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    // method that will act as the interface for the move request (tbd per implementation)
    public abstract String queryMove();
}
