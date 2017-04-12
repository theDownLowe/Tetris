/*
 * TCSS 305
 * Assignment 6 - Tetris Project
 */

package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Board;

/**
 * A key listener class for the tetris game.
 * 
 * @author Trevor N. Lowe
 * @version November 30, 2015 - 2:07 PM
 */
public class TetrisKeyListener extends KeyAdapter {
        
    /** The game Board. **/
    private final Board myBoard;
    
    /**
     * Creates a new TetrisKeyListener object.
     * 
     * @param theGameBoard the board of the game.
     */
    public TetrisKeyListener(final Board theGameBoard) {
        
        super();
        myBoard = theGameBoard;
    }
    
    @Override
    public void keyPressed(final KeyEvent theEvent) {
            
        super.keyPressed(theEvent);
        if (BoardPanel.gameInPlay()) {
            if (KeyEvent.VK_LEFT == theEvent.getKeyCode()) {
                myBoard.moveLeft();
            } else if (KeyEvent.VK_RIGHT == theEvent.getKeyCode()) {
                myBoard.moveRight();
            } else if (KeyEvent.VK_DOWN == theEvent.getKeyCode()) {
                myBoard.moveDown();
            } else if (KeyEvent.VK_UP == theEvent.getKeyCode()) {
                myBoard.rotate();
            } else if (KeyEvent.VK_SPACE == theEvent.getKeyCode()) {
                myBoard.hardDrop();
            }
        }
        
        if (KeyEvent.VK_P == theEvent.getExtendedKeyCode()) {
            BoardPanel.paused();
        }
    }
}
