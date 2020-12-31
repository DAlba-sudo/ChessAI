package backend.ui;

import javafx.scene.control.ListView;

import java.io.*;
import java.nio.file.Path;

public class ChessMoveList {

    private ListView<String> moves = new ListView<>();

    public ChessMoveList(){
        // class used to visualize the moves by player and ai

    }

    public void addMove(String move){
        int move_num = moves.getItems().size();
        if(move_num != 0) {
            String latest_move = moves.getItems().get(move_num - 1);
            String[] latest_move_split = latest_move.split(" {5}");
            if (latest_move_split.length == 2) {
                // we have a full entry
                moves.getItems().add(formatMove(move) + "     ");
            } else {
                // we don't have a full entry
                if (latest_move_split.length == 1) {
                    // white has entered a move (so black format)
                    String latestWhiteMove = moves.getItems().get(move_num - 1);
                    moves.getItems().remove(move_num - 1);
                    moves.getItems().add(latestWhiteMove + formatMove(move));
                }
            }
        } else {
            moves.getItems().add(formatMove(move) + "     ");
        }
    }

    public ListView<String> getMoves() {
        return moves;
    }

    private String formatMove(String move){
        return move.trim().replace("\n", "").replace("\r", "");
    }

    public void saveList(Path p){
        // saves to a set file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(p.toFile(), false));
            for(String move_pair : moves.getItems()){
                bw.write(format_move_pair(move_pair));
            }
        } catch (IOException e){
            System.out.println("Error saving game!");
        }
    }

    public void loadFromPath(Path p){
        // loads all moves from path
        try{
            BufferedReader br = new BufferedReader(new FileReader(p.toFile()));
            String line;
            while((line = br.readLine()) != null){
                line = line.trim().replace("\n", "").replace("\r", "");
                String[] moves_in_pair = line.split(";");
                for(String m : moves_in_pair){
                    addMove(m);
                }
            }
        } catch (IOException e){
            System.out.println("Unable to load file");
        }
    }

    private String format_move_pair(String move_pair){
        String[] moves_in_pair = move_pair.split(" {5}");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < moves_in_pair.length; i++){
            if(i == 0){
                sb.append(moves_in_pair[i]);
            } else if (i == moves_in_pair.length-1){
                sb.append(";").append(moves_in_pair[i]).append("\n");
            } else {
                sb.append(";").append(moves_in_pair[i]);
            }
        }
        return sb.toString();
    }

}
