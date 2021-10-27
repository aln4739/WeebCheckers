package com.webcheckers.model;
import com.webcheckers.model.Piece.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import com.webcheckers.model.Piece.Color;

import static org.junit.Assert.*;

/**
 * @author Mohammed Alam
 */
@Tag("Model-tier")
public class TestSpace {
    @Test
    public void testValid() {
        // make a row with a new index
        Space mySpace = new Space(0, true, new Piece(Type.SINGLE, Color.RED));

        // assert expected index vs result form getIndex()
        assertTrue(mySpace.isValid());
    }

    @Test
    public void testNotValid() {
        // make a row with a new index
        Space mySpace = new Space(1, false, new Piece(Type.SINGLE, Color.RED));

        // assert expected index vs result form getIndex()
        assertFalse(mySpace.isValid());
    }

    @Test
    public void testGetPiece() {
        // make a row with a new index
        Piece myPiece = new Piece(Type.SINGLE, Color.RED);
        Space mySpace = new Space(0, true, myPiece);

        // assert expected index vs result form getIndex()
        assertEquals(mySpace.getPiece(), myPiece);
    }

    @Test
    public void testGetCellIDx() {
        // make a row with a new index
        Piece myPiece = new Piece(Type.SINGLE, Color.RED);
        Space mySpace = new Space(0, true, myPiece);

        // assert expected index vs result form getIndex()
        assertEquals(mySpace.getCellIdx(), 0);
    }

    @Test
    public void testEqualsSelf() {
        // make a row with a new index
        Piece myPiece = new Piece(Type.SINGLE, Color.RED);
        Space mySpace = new Space(0, true, myPiece);

        // assert expected index vs result form getIndex()
        assertTrue(mySpace.equals(mySpace));
    }

    @Test
    public void testEqualsOtherObject() {
        // make a row with a new index
        Piece myPiece = new Piece(Type.SINGLE, Color.RED);
        Space mySpace = new Space(0, true, myPiece);

        // assert expected index vs result form getIndex()
        assertFalse(mySpace.equals(myPiece));
    }

    @Test
    public void testSpaceEquals() {
        // make a row with a new index
        Piece myPiece1 = new Piece(Type.SINGLE, Color.RED);
        Space mySpace1 = new Space(0, true, myPiece1);

        Piece myPiece2 = new Piece(Type.SINGLE, Color.RED);
        Space mySpace2 = new Space(0, true, myPiece2);

        // assert expected index vs result form getIndex()
        assertTrue(mySpace1.equals(mySpace2));
    }

    @Test
    public void testSpaceNotEquals() {
        // make a row with a new index
        Piece myPiece1 = new Piece(Type.SINGLE, Color.RED);
        Space mySpace1 = new Space(0, true, myPiece1);

        Piece myPiece2 = new Piece(Type.SINGLE, Color.WHITE);
        Space mySpace2 = new Space(0, false, myPiece2);

        // assert expected index vs result form getIndex()
        assertFalse(mySpace1.equals(mySpace2));
    }

    @Test
    public void testSetPieceOnSpace() {
        // make a row with a new index
        Piece oldPiece = new Piece(Type.SINGLE, Color.RED);
        Space mySpace = new Space(0, true, oldPiece);
        Piece newPiece = new Piece(Type.KING, Color.RED);
        mySpace.setPiece(newPiece);
        // assert expected index vs result form getIndex()
        assertEquals(mySpace.getPiece(), newPiece);
    }

    @Test
    public void testGetPieceOnSpaceNull() {
        // make a row with a new index
        Space mySpace = new Space(0, true, null);
        // assert expected index vs result form getIndex()
        assertNull(mySpace.getPiece());
    }
}
