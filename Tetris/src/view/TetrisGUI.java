/*
 * TCSS 305
 * Assignment 6 - Tetris Project
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Board;

/**
 * Creates the JFrame which holds the tetris Game.
 * Also contains the main method.
 * 
 * @author Trevor N. Lowe
 * @version November 30, 2015 - 12:55 PM
 */
public class TetrisGUI extends JFrame {
    
    // Class Constants
    
    /** A generated serial ID. **/
    private static final long serialVersionUID = -8588811778433928729L;
    
    /** Minimum size of the JFrame. **/
    private static final Dimension MIN_SIZE = new Dimension(364, 240);
    
    /** Game height in blocks. **/
    private static final int INITIAL_GAME_HEIGHT = 20;
    
    /** Game width in blocks. **/
    private static final int INITIAL_GAME_WIDTH = 10;
    
    /** Controls Text. **/
    private static final String CONTROLS = "Left: Move Piece Left\n"
                    + "Right: Move Piece Right\n"
                    + "Down: Move Piece Down\n"
                    + "Up: Rotate Piece\n"
                    + "Space: Hard Drop Piece\n"
                    + "P: Pause";
    
    
    
    // Instance Fields
    
    /** True if first game. **/
    private final boolean myIsFirst;
    
    
    
    
    // Constructors
    
    /** Creates a new tetris game.
     * 
     * @param theIsFirst True if first game
     */
    public TetrisGUI(final boolean theIsFirst) {
        
        super("Trevor's Tetris");
        myIsFirst = theIsFirst;
        if (theIsFirst) {
            JOptionPane.showMessageDialog(this, CONTROLS, "Controls",
                                          JOptionPane.PLAIN_MESSAGE);
        }
        frameSetup();
    }
    
    
    // Instance Methods
    
    /** Sets up the JFrame. **/
    private void frameSetup() {
        
        final Board gameBoard = new Board(INITIAL_GAME_WIDTH, 
                                          INITIAL_GAME_HEIGHT, null);
        final GameBoard board = new GameBoard(gameBoard);
        this.add(board);
        this.addKeyListener(new TetrisKeyListener(gameBoard));
        final SidePanel side = new SidePanel(gameBoard, board);
        this.add(side, BorderLayout.EAST);
        this.setJMenuBar(new TetrisMenu(this));
   
        this.setMinimumSize(MIN_SIZE);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    /** starts a new tetris game. **/
    public void newGame() {
        this.dispose();
        new TetrisGUI(false);
    }
    
    /** 
     * Returns true if first game.
     * 
     * @return is first game
     */
    public boolean isFirst() {
        return myIsFirst;
    }
    
    
    // Main
    
    /**
     * The main method for the tetris project.
     * 
     * @param theArgs command line parameters, unused.
     */
    public static void main(final String[] theArgs) {
        
        new TetrisGUI(true);
    }
}
