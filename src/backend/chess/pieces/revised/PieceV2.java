package backend.chess.pieces.revised;

import backend.chess.ChessManager;

public abstract class PieceV2 {

    private String current_position;
    private int color; // 0 > black ; 1 > white;
    private boolean canJump = false;
    private int[] current_coordinates = new int[2];


    public PieceV2(String starting, int color){
        this.current_position = starting;
        this.current_coordinates = ChessManager.getCoordinateToMoveTo(starting);
    }

    public PieceV2(int x, int y, int color){
        String pos_notation = ChessManager.numToFile(x) + y;
        this.current_position = pos_notation;
        this.current_coordinates = ChessManager.getCoordinateToMoveTo(current_position);
    }

    public abstract boolean isMoveLegal(int x, int y);
    public abstract boolean isCaptureLegal(int x, int y);

    public boolean isPosLegal(String notation){
        int[] target_position = ChessManager.getCoordinateToMoveTo(notation);
        int target_rank = target_position[0];
        int target_file = target_position[1];

        if(ChessManager.isCapturing(notation)){
            return isCaptureLegal(target_rank, target_file) && isValidPos(target_rank, target_file);
        }
        return isMoveLegal(target_rank, target_file) && isValidPos(target_rank, target_file);
    }

    public boolean setPosition(String notation){
        if(isPosLegal(notation)){
            this.current_position = notation;
            this.current_coordinates = ChessManager.getCoordinateToMoveTo(notation);
            return true;
        } else {
            return false;
        }
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public boolean canJump() {
        return canJump;
    }

    public int[] getCurrentCoordinate() {
        return current_coordinates;
    }

    public String getCurrentPosition() {
        return current_position;
    }

    public boolean isMovingForward(int y){
        return (y - getCurrentCoordinate()[1]) > 0;
    }

    public boolean isMovingDiagonal(int x, int y){
        return Math.abs(x-getCurrentCoordinate()[0]) == Math.abs(y-getCurrentCoordinate()[1]) && isMoving(x, y);
    }

    public boolean isMoving(int x, int y){
        return (x != getCurrentCoordinate()[0] || y != getCurrentCoordinate()[1]);
    }

    public boolean isValidPos(int x, int y){
        return ChessManager.isWithin(x, 8, 1) && ChessManager.isWithin(y, 8, 1);
    }

    public int delta(int to, int from){
        return Math.abs(to - from);
    }

    public boolean isMovingToSide(int x){
        return delta(x, getCurrentCoordinate()[0]) > 0;
    }

    public void setJump(boolean canJump){
        this.canJump = canJump;
    }
}
