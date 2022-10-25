import javax.swing.JFrame;

public class LifeFrame extends JFrame{
    LifeFrame(){
        this.add(new LifePanel());
        this.setTitle("Conway's Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}
