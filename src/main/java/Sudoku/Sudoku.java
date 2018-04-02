package Sudoku;

import SimulatedAnnealing.Problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Sudoku implements Problem {
    private final int n = 9;
    private int[][] board = new int[n][n];
    private boolean[][] nonChangeableDigits = new boolean[n][n];
    private int x1, y1;
    private int x2, y2;
    private boolean reSwapped = false;

    public Sudoku(String path){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path)).useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int v;
        StringBuilder s = new StringBuilder();
        assert scanner != null;
        while(scanner.hasNext()) s.append(scanner.next());

        String board = s.toString();

        for (int i = 0; i < board.length(); ++i){
            try {
                v = Integer.parseInt(board.substring(i, i + 1));
                nonChangeableDigits[i/n][i%n] = true;
            }
            catch (NumberFormatException e){
                v = 0;
            }
            this.board[i/n][i%n] = v;
        }

        fill();
    }

    private ArrayList<Integer[]> getBlockIndexes(int blockNumber, boolean forChangeableFields){
        ArrayList<Integer[]> indexes = new ArrayList<>();
        int horBlockNumber = blockNumber / 3;
        int verBlockNumber = blockNumber % 3;

        for(int i = 0; i < 9; ++i){
            Integer[] index = new Integer[2];
            index[0] = i/3 + horBlockNumber*3;
            index[1] = i%3 + verBlockNumber*3;
            if(!(forChangeableFields && nonChangeableDigits[index[0]][index[1]])){
                indexes.add(index);
                //System.out.println(index[0] + " " + index[1]);
            }
        }
        return indexes;
    }

    private void fill(){
        for(int i = 0; i < n; ++i){
            ArrayList<Integer[]> indexes = getBlockIndexes(i, false);
            boolean[] digitsOccur = new boolean[9];

            for(Integer[] in : indexes){
                int v = board[in[0]][in[1]];
                if( v != 0 ) digitsOccur[v-1] = true;
            }
            indexes = getBlockIndexes(i, true);
            int j = 0;
            for(Integer[] in : indexes){
                while(digitsOccur[j]) j++;
                board[in[0]][in[1]] = j+1;
                j++;
            }

        }
    }

    private Sudoku(int[][] board, boolean[][] nonChangeableDigits) {
        this.board = board;
        this.nonChangeableDigits = nonChangeableDigits;
    }

    private double blocksEnergy(){
        double e = 0;
        for(int i = 0; i < n; ++i){
            int[] reps = new int[n];

            ArrayList<Integer[]> indexes = getBlockIndexes(i, false);
            for(Integer[] in : indexes){
                reps[board[in[0]][in[1]]-1]++;
            }
            for(int j = 0; j < n; ++j){
                if(reps[i] > 1) e+=(reps[i]-1);
            }
        }
        return e;
    }

    private double rowsEnergy(){
        double e = 0;
        for(int i = 0; i < n; ++i){
            int[] reps = new int[n];
            for(int j = 0; j < n; ++j){
                reps[board[i][j]-1]++;
            }
            for(int j = 0; j < n; ++j){
                if(reps[j] > 1) e+=(reps[j]-1);
            }

        }
        return e;
    }

    private double columnsEnergy(){
        double e = 0;
        for(int i = 0; i < n; ++i){
            int[] reps = new int[n];
            for(int j = 0; j < n; ++j){
                reps[board[j][i]-1]++;
            }
            for(int j = 0; j < n; ++j){
                if(reps[j] > 1) e+=(reps[j]-1);
            }

        }
        return e;
    }

    @Override
    public double getEnergy() {
        return blocksEnergy()+rowsEnergy()+columnsEnergy();
    }

    @Override
    public void swap() {
        int block = (int)(Math.random() * n);
        ArrayList<Integer[]> indexes = getBlockIndexes(block, true);
        if(indexes.size() < 2 ){
            block = (int)(Math.random() * n);
            indexes = getBlockIndexes(block, true);
        }
        int field1 = (int)(Math.random() * indexes.size());
        int field2 = (int)(Math.random() * indexes.size());
        while(field1 == field2) field2 = (int)(Math.random() * indexes.size());
        x1 = indexes.get(field1)[0];  y1 = indexes.get(field1)[1];
        x2 = indexes.get(field2)[0];  y2 = indexes.get(field2)[1];
        int tmp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = tmp;
        reSwapped = false;
    }

    @Override
    public void reSwap() {
        if(!reSwapped) {
            int tmp = board[x1][y1];
            board[x1][y1] = board[x2][y2];
            board[x2][y2] = tmp;
            reSwapped = true;
        }
    }

    @Override
    public Problem copy() {
        return new Sudoku(board, nonChangeableDigits);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                stringBuilder.append(this.board[i][j]).append(' ');
                if(j % 3 == 2) stringBuilder.append(' ');
            }
            stringBuilder.append('\n');
            if(i % 3 == 2) stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
