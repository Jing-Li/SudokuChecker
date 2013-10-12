import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SudokuChecker{

    

    /**
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        SudokuChecker sudokuChecker = new SudokuChecker();
        sudokuChecker.process();
    }

    private void process() throws FileNotFoundException{
        File file = new File("in");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int number = 0;
        int N = 0;
        Scanner s = new Scanner(br);
        if (s.hasNext()) {
            number = getInt(s.nextLine());
        }
        for (int i=0;i<number;i++) {
            if (s.hasNext()) {
                N = getInt(s.nextLine());
            }
            int[][] matrix = new int[N*N][N*N];
            for (int j=0;j<N*N;j++) {
                String line = s.nextLine();
                String[] tokens = line.split(" ");
                for(int x=0;x<tokens.length;x++) {
                    matrix[j][x] = getInt(tokens[x]);
                }
            }
            if(!validRow(matrix,N)){
                printNo(i);
                continue;
            } else if(!validColume(matrix,N)){
                printNo(i);
                continue;
                
            } else if(!validSubm(matrix,N)){
                printNo(i);
                continue;
            }
            printYes(i);
        }
        
    }
    private void printNo (int i) {
        i++;
        writeOut("Case #" + i + ": No");
    }
    private void printYes (int i) {
        i++;
        writeOut("Case #" + i + ": Yes");
    }
    private int getInt(String s) {
        return Integer.valueOf(s);
    }
    
    private boolean validRow(int[][] matrix, int N) {
        for(int i=0;i<N*N;i++) {
            Map<Integer,Integer> map = new HashMap<Integer, Integer>();
            for(int j=0;j<N*N;j++){
                if(matrix[i][j]>N*N){
                    return false;
                }
                if(map.containsKey(matrix[i][j])) {
                    return false;
                } else {
                    map.put(matrix[i][j], 1);
                }
            }
        }
        return true;
    }
    
    private boolean validColume(int[][] matrix, int N) {
        for(int i=0;i<N*N;i++) {
            Map<Integer,Integer> map = new HashMap<Integer, Integer>();
            for(int j=0;j<N*N;j++){
                if(matrix[j][i]>N*N){
                    return false;
                }
                if(map.containsKey(matrix[j][i])) {
                    return false;
                } else {
                    map.put(matrix[j][i], 1);
                }
            }
        }
        return true;
    }
    
    private boolean validSubm(int[][] matrix, int N) {
        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++) {
                Map<Integer,Integer> map = new HashMap<Integer, Integer>();
                for(int i=x*N;i<x*N;i++) {
                    for(int j=y*N;j<y*N;j++){
                        if(matrix[i][j]>N*N){
                            return false;
                        }
                        if(map.containsKey(matrix[i][j])) {
                            return false;
                        } else {
                            map.put(matrix[i][j], 1);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private void writeOut(String s){
        File file = new File("output");
        FileWriter fw;
        try {
            fw = new FileWriter(file, true);
            fw.append(s+"\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
