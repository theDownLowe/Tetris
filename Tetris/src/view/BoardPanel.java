/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

/**
 * This class provides the game board panel for the GUI, which runs the game.
 * 
 * @author Trevor N. Lowe
 * @version November 30, 2015 - 12:34 PM
 */
public class BoardPanel extends JPanel implements Observer {    
    
    // Class Constants
    
    /** How far in pixels the border of the game is from the JPanel. **/
    protected static final int BORDER_OFFSET = 19;
    
    /** A generated serial ID. **/
    private static final long serialVersionUID = 3425951472904830505L;

    /** Initial size of the game board. **/
    private static final Dimension INITIAL_SIZE = new Dimension(500, 960);
    
    /** Starting tick rate. **/
    private static final int INITIAL_TICK = 1000;
    
    /** Amount tick rate changes per level. **/
    private static final int TICK_RATE = 100;
    
    
    // Non-Instance Fields
    
    /** Is game over. **/
    private static boolean myGameOver;
    
    
    // Instance Fields
    
    /** Timer for game. **/
    private static Timer myTimer;
    
    /** Game board. **/
    private final Board myBoard;
    
    
    // Constructors
    
    /** Creates a tetris game and graphics.
     * 
     * @param theGameBoard the board for the game
     */
    protected BoardPanel(final Board theGameBoard) {
        
        super();
        myBoard = theGameBoard;
        this.setup();
    }
    
    
    // Instance Methods    
    
    /** Sets up the panel and game. **/
    private void setup() {
        
        myBoard.addObserver(this);
          
        myTimer = new Timer(INITIAL_TICK, new TimeListener());
        myTimer.start();
          
        this.setPreferredSize(INITIAL_SIZE);
        this.setBackground(Color.WHITE);
        this.setVisible(true);        
    }
    
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Board border
        final double borderW = this.getWidth() - (BORDER_OFFSET * 2);
        final double borderH = this.getHeight() - (BORDER_OFFSET * 2);
        final Rectangle2D.Double border = new Rectangle2D.Double(BORDER_OFFSET, BORDER_OFFSET, 
                                                                 borderW, borderH);
        g2d.draw(border);
        
        // Pause Display
        
    }
     
    @Override
    public void update(final Observable theObservable, final Object theObject) {
       
        if (myBoard.isGameOver()) {
            
            myTimer.stop();
            JOptionPane.showMessageDialog(this, "You Lost!");
        }
    }
    
    
    // Non-Instance Methods
    
    /**
     * Returns if timer is running.
     * 
     * @return if timer is running
     */
    public static boolean gameInPlay() {
        return myTimer.isRunning();
    }
    
    /** Pauses/Un-pauses the game. **/
    public static void paused() {
        if (myTimer.isRunning()) {
            myTimer.stop();
        } else if (!myTimer.isRunning() && !myGameOver) {
            myTimer.start();
        }
    }
    
    /** Ends the game. **/
    public static void endGame() {
        myTimer.stop();
        myGameOver = true;
    }
    
    
    // Inner Classes
    
    /**
     * An inner class listener for the timer.
     * 
     * @author Trevor N. Lowe
     * @version November 30, 2015 - 1:02 PM
     */
    private class TimeListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {

            myBoard.step();
            myTimer.setDelay(INITIAL_TICK - ((ScorePanel.getLevel() - 1) * TICK_RATE));
        }
    }
}
