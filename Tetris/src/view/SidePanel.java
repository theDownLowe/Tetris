/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Board;

/**
 * A JPanel to hold the next piece, score, and instructions.
 * 
 * @author Trevor N. Lowe
 * @version November 30, 2015 - 1:00 PM
 */
public class SidePanel extends JPanel {

    // Class Constants
    
    /** A generated serial ID. **/
    private static final long serialVersionUID = 4611479497716318416L;
   
    
    //Instance Fields

    
    
    // Constructors
    
    /** Creates a new JPanel to hold the next piece, score, and instructions.
     * 
     * @param theGameBoard the board of the game
     * @param theBoard the GUI board for the game
     */
    public SidePanel(final Board theGameBoard, final GameBoard theBoard) {
   
        super();
      
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new NextPiecePanel(theGameBoard));
        add(new ScorePanel(theBoard));
        setBackground(Color.WHITE);
        setVisible(true);
    }
}
