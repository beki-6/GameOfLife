import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class LifePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 20;
    int[][] cells = new int[SCREEN_WIDTH/UNIT_SIZE][SCREEN_HEIGHT/UNIT_SIZE];
    int gen = 0;
    Timer timer;

    LifePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        for(int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i++) { 
            for(int j = 0; j < SCREEN_HEIGHT/UNIT_SIZE; j++){
                cells[i][j] = new Random().nextInt(2);
            }
        }
        timer = new Timer(100, this);
        timer.start();
    }
    public void generate(){
        int[][] new_cells = new int[SCREEN_WIDTH/UNIT_SIZE][SCREEN_HEIGHT/UNIT_SIZE];
        for(int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i++) { 
            for(int j = 0; j < SCREEN_HEIGHT/UNIT_SIZE; j++){
                new_cells[i][j] = rules(i, j);
            }
        }
        cells = new_cells;
        gen++;
    }
    public int rules(int i, int j){
        int state = cells[i][j];
        int sum = aliveNeighbors(i, j);
        if(cells[i][j] == 0 && sum == 3) state = 1;
        else if(cells[i][j] == 1 && sum > 3) state = 0;
        else if(cells[i][j] == 1 && sum < 2) state = 0;
        return state;
    }
    public int aliveNeighbors(int a, int b) {
        int sum = 0, N = SCREEN_HEIGHT/UNIT_SIZE;
        for(int i = -1; i < 2; i++) { 
            for(int j = -1; j < 2; j++){
                int x = i, y = j;
                if(i == 1) x = (a+i) % N;
                if(j == 1) y = (b+j) % N;
                if(a > 0 && i != 1) x = a+i;
                if(b > 0 && j != 1) y = b+j;  
                if(a == 0 && i == -1) x = N-1;
                if(b == 0 && j == -1) y = N-1;
                sum += cells[x][y];
            }
        }
        sum -= cells[a][b];
        return sum;  
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i++) { 
            for(int j = 0; j < SCREEN_HEIGHT/UNIT_SIZE; j++){
                if(cells[i][j] == 1) g.setColor(Color.WHITE);
                else if(cells[i][j] == 0) g.setColor(Color.BLACK);
                g.fillRect(i*UNIT_SIZE, j*UNIT_SIZE, UNIT_SIZE-1, UNIT_SIZE-1);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        generate();
        repaint();
    }
}
