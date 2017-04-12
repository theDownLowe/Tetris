/*
 * TCSS 305
 * Assignment 6 - Tetris Project
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Panel displaying tetris pieces on the game board.
 * 
 * @author Trevor N. Lowe
 * @version December 11, 2015 - 5:53 PM
 */
public class ScorePanel extends JPanel {

    // Class Constants
    
    /** A generated serial ID. **/
    private static final long serialVersionUID = 9000333720550925581L;

    /** Initial panel size.*/
    private static final Dimension INITIAL_SIZE = new Dimension(30, 650);
    
    /** Strut size. **/
    private static final int STRUT_SIZE = 10;
    
    /** Next level lines modifier. **/
    private static final int LINE_MOD = 5;
    
    /** The base score. **/
    private static final int BASE_SCORE = 10;
    
    
    // Non-Instance Fields
    
    /** The current level. **/
    private static int myLevel = 1;
    
    
    // Instance Fields
    
    /** The amount of lines cleared. **/
    private int myLineCount;
    
    /** The current score. **/
    private int myScore;
    
    /** Lines until the next level. **/
    private int myNextLevel;
    
    /**
     * Creates a new score panel.
     * 
     * @param theBoard the board for the game
     */
    public ScorePanel(final GameBoard theBoard) {
        
        super();
        myLineCount = 0;
        myScore = 0;
        myNextLevel = LINE_MOD;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(INITIAL_SIZE);
        setupPanel(theBoard);
        setBackground(Color.WHITE);
    }
    
    
    // Instance Methods
    
    /** 
     * Sets up the score panel.
     * 
     * @param theBoard the board for the game
     */
    private void setupPanel(final GameBoard theBoard) {
        
        final JLabel lines = new JLabel();
        final JLabel score = new JLabel();
        final JLabel level = new JLabel();
        final JLabel nextLevel = new JLabel();
        setText(lines, score, level, nextLevel);
        add(lines);
        add(Box.createVerticalStrut(STRUT_SIZE));
        add(score);
        add(Box.createVerticalStrut(STRUT_SIZE));
        add(level);
        add(Box.createVerticalStrut(STRUT_SIZE));
        add(nextLevel);
        theBoard.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent theEvent) {
                if ("ScoreUpdate".equals(theEvent.getPropertyName())) {
                    final int linesCleared = (int) theEvent.getNewValue();
                    myLineCount += linesCleared;
                    myNextLevel -= linesCleared;
                    if (myNextLevel <= 0) {
                        myLevel++;
                        myNextLevel = myLevel * LINE_MOD;
                    }
                    myScore += BASE_SCORE * myLevel * Math.pow(linesCleared, 2);
                                    
                    setText(lines, score, level, nextLevel);
                }
            }
        });
    }
    
    /**
     * Sets the text for several JLabels on the GUI.
     * 
     * @param theLines label of the amount of lines cleared
     * @param theScore label of the current score
     * @param theLevel label of the current level
     * @param theNextLevel label of lines until next level
     */
    private void setText(final JLabel theLines, final JLabel theScore,
                         final JLabel theLevel, final JLabel theNextLevel) {
     
        theLines.setText("Lines Cleared: " + myLineCount);
        theScore.setText("Score: " + myScore);
        theLevel.setText("Level: " + myLevel);
        theNextLevel.setText("Lines to next Level: " + myNextLevel);
    }
    
    
    // Non-Instance Methods
    
    /**
     * Returns the current level.
     * 
     * @return the current level
     */
    public static int getLevel() {
        return myLevel;
    }
}
