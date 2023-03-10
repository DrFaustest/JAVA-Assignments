/*
 * Name:        Scott Faust
 * Class:       22_WI_INFO_1521_WW
 * Assignment:  Maze Final Project
 * Date:        1/27/2023
 * Resources:  https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html -- for Swing documentation
 * 
 * Description: A GUI for the MazeSolver class.
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MazeSolverGUI extends JFrame implements ActionListener {
    public static int[][] m1 = {
        {1,1,1,1,1,1,1,1,1,1,1,1}, 
        {1,0,0,0,1,0,0,0,0,0,0,1}, 
        {-1,0,1,0,1,0,1,1,1,1,0,1}, 
        {1,1,1,0,1,0,0,0,0,1,0,1}, 
        {1,0,0,0,0,1,1,1,0,1,0,-1}, 
        {1,1,1,1,0,1,0,1,0,1,0,1}, 
        {1,0,0,1,0,1,0,1,0,1,0,1}, 
        {1,1,0,1,0,1,0,1,0,1,0,1}, 
        {1,0,0,0,0,0,0,0,0,1,0,1}, 
        {1,1,1,1,1,1,0,1,1,1,0,1}, 
        {1,0,0,0,0,0,0,1,0,0,0,1}, 
        {1,1,1,1,1,1,1,1,1,1,1,1}};
    public static int[][] m2 = {
        {1,1,1,1,1,1,1,1,1,1,1,1}, 
        {1,0,0,0,1,0,0,0,1,1,0,-1}, 
        {1,0,1,0,0,0,1,0,0,0,0,1}, 
        {1,0,1,1,1,1,0,1,0,1,0,1}, 
        {1,0,0,0,1,1,0,0,0,0,0,1}, 
        {1,1,1,0,1,1,1,1,0,1,0,1}, 
        {-1,0,0,0,0,0,0,0,0,0,1,1}, 
        {1,1,1,1,1,1,1,1,1,1,1,1}};
    public static int[][] m3 = {
        {1,1,1,1,1,1,1,1,1}, 
        {1,0,1,0,1,0,0,0,1}, 
        {1,0,0,0,1,0,1,1,1}, 
        {1,1,1,0,1,0,1,0,-1}, 
        {-1,0,0,0,0,0,1,0,1}, 
        {1,1,0,1,0,1,1,0,1}, 
        {1,0,0,1,0,1,0,0,1}, 
        {1,1,0,1,0,1,0,0,1}, 
        {1,1,1,1,1,1,1,1,1}}; 

        
    private Maze m;
    private MazeSolver ms;

    private JLabel messageLabel;
    private JTextArea textArea;

    public MazeSolverGUI() {

        super("Maze Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Maze Solver!");
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton maze1Button = new JButton("Maze 1");
        maze1Button.addActionListener(this);
        buttonPanel.add(maze1Button);
        JButton maze2Button = new JButton("Maze 2");
        maze2Button.addActionListener(this);
        buttonPanel.add(maze2Button);
        JButton maze3Button = new JButton("Maze 3");
        maze3Button.addActionListener(this);
        buttonPanel.add(maze3Button);
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(quitButton);

        textArea = new JTextArea(15,15);
        buttonPanel.add(textArea);


        panel.add(buttonPanel, BorderLayout.CENTER);

        messageLabel = new JLabel();
        panel.add(messageLabel, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("Maze 1")) {
            solveMaze(m1);
        } else if (button.getText().equals("Maze 2")) {
            solveMaze(m2);
        } else if (button.getText().equals("Maze 3")) {
            solveMaze(m3);
        }
    }

    public String solveMaze(int[][] maze) {
        String printedSolution = "";
        m = new Maze(maze);
        ms = new MazeSolver(m);
        boolean solved = ms.solveMaze();
        if (solved) {
            printedSolution = m.printMaze();
            messageLabel.setText("The maze was solved!");
        } else {
            messageLabel.setText("The maze was not solved.");
        }
        textArea.setText(printedSolution);
        return printedSolution;
    }


    public static void main(String[] args) {
        new MazeSolverGUI();
    }
}

    
