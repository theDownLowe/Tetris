/*
 * TCSS 305
 * Assignment 6 - Tetris Project
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AbstractPiece;
import model.Board;
import model.Piece;

/**
 * The Panel to display the next tetris piece.
 * 
 * @author Trevor N. Lowe
 * @version December 1, 2015 - 7:50 PM
 */
public class NextPiecePanel extends JPanel implements Observer {

    // Class Constants
    
    /** A generated serial ID. **/
    private static final long serialVersionUID = 2562459531421195328L;
    
    /** Initial panel size.*/
    private static final Dimension INITIAL_SIZE = new Dimension(210, -500);
    
    
    // Instance Fields
    
    /** The game board. **/
    private final Board myBoard;
    
    
    // Constructors
    
    /**
     * Creates a new NextPiecePanel object.
     * 
     * @param theGameBoard the board for the game
     */
    public NextPiecePanel(final Board theGameBoard) {
        
        super();
        myBoard = theGameBoard;
        myBoard.addObserver(this);
        this.add(new JLabel("Next Piece"));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(INITIAL_SIZE);
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
     
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Panel border
        final double borderW = this.getWidth() - (GameBoard.BORDER_OFFSET * 2);
        final double borderH = this.getHeight() - (GameBoard.BORDER_OFFSET * 2);
        final Rectangle2D.Double border = new Rectangle2D.Double(GameBoard.BORDER_OFFSET, 
                                                                 GameBoard.BORDER_OFFSET, 
                                                                 borderW, borderH);
        g2d.draw(border);
        
        // Next Piece
        final Piece nextPiece = myBoard.getNextPiece();
        final int[][] p = ((AbstractPiece) nextPiece).getBoardCoordinates();
        for (int i = 0; i < p.length; i++) {
            final int xPosition = p[i][0];
            final int yPosition = p[i][1];
            final double width = ((this.getWidth() - (2 * GameBoard.BORDER_OFFSET) 
                                  - 2 - (myBoard.getWidth() - 1)) / myBoard.getWidth()) * 2;
            final double height = ((this.getHeight() - (2 * GameBoard.BORDER_OFFSET) 
                                   - 2 - (myBoard.getHeight() - 1)) / myBoard.getHeight()) * 2;
            final double x = (GameBoard.BORDER_OFFSET + 2) 
                              + (xPosition * width) + xPosition - 75;
            final double y = ((this.getHeight() - GameBoard.BORDER_OFFSET)
                              - ((yPosition + 1) * height) - yPosition - 1) + 450;
            final Rectangle2D.Double rect = new Rectangle2D.Double(x, y, 
                                                                   width, height);
            g2d.fill(rect);
        }
    }

    @Override
    public void update(final Observable theObservable, final Object theObject) {

        this.repaint();
    }
}
