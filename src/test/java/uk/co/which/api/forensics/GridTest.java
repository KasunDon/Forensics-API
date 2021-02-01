package uk.co.which.api.forensics;

import org.junit.jupiter.api.Test;
import uk.co.which.api.forensics.value.Coordinate;
import uk.co.which.api.forensics.value.Grid;
import uk.co.which.api.forensics.value.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.co.which.api.forensics.value.Direction.EAST;
import static uk.co.which.api.forensics.value.Direction.NORTH;
import static uk.co.which.api.forensics.value.Direction.SOUTH;
import static uk.co.which.api.forensics.value.Direction.WEST;

class GridTest {

    private final Grid underTest = new Grid();

    @Test
    public void default_grid_position_should_face_north_0_0() {
        Position defaultPosition = underTest.getPositions().peek();
        Coordinate coordinate = defaultPosition.getCoordinate();

        assertEquals(0, coordinate.getX());
        assertEquals(0, coordinate.getY());

        assertEquals(NORTH, defaultPosition.getDirection());
    }

    @Test
    public void right_turn_360_verification() {
        assertEquals(NORTH, underTest.getCurrentDirection());

        underTest.turnRight();
        assertEquals(EAST, underTest.getCurrentDirection());

        underTest.turnRight();
        assertEquals(SOUTH, underTest.getCurrentDirection());

        underTest.turnRight();
        assertEquals(WEST, underTest.getCurrentDirection());

        underTest.turnRight();
        assertEquals(NORTH, underTest.getCurrentDirection());
    }

    @Test
    public void left_turn_360_verification() {
        assertEquals(NORTH, underTest.getCurrentDirection());

        underTest.turnLeft();
        assertEquals(WEST, underTest.getCurrentDirection());

        underTest.turnLeft();
        assertEquals(SOUTH, underTest.getCurrentDirection());

        underTest.turnLeft();
        assertEquals(EAST, underTest.getCurrentDirection());

        underTest.turnLeft();
        assertEquals(NORTH, underTest.getCurrentDirection());
    }

    @Test
    public void record_position_when_heading_east() {
        Grid grid = new Grid();

        Position defaultPosition = underTest.getPositions().peek();
        Coordinate coordinate = defaultPosition.getCoordinate();

        assertEquals(0, coordinate.getX());
        assertEquals(0, coordinate.getY());

        grid.turnRight();
        Position move = grid.move();

        Coordinate moveCoordinate = getCoordinate(move);
        assertEquals(1, moveCoordinate.getX());
        assertEquals(0, moveCoordinate.getY());
        assertEquals(EAST, move.getDirection());
    }

    @Test
    public void record_position_when_heading_south() {
        Grid grid = new Grid();

        Position defaultPosition = underTest.getPositions().peek();
        Coordinate coordinate = defaultPosition.getCoordinate();

        assertEquals(0, coordinate.getX());
        assertEquals(0, coordinate.getY());

        grid.turnRight();
        grid.turnRight();
        Position move = grid.move();

        Coordinate moveCoordinate = getCoordinate(move);
        assertEquals(0, moveCoordinate.getX());
        assertEquals(1, moveCoordinate.getY());
        assertEquals(SOUTH, move.getDirection());
    }

    @Test
    public void record_position_when_heading_west() {
        Grid grid = new Grid();

        Position defaultPosition = underTest.getPositions().peek();
        Coordinate coordinate = defaultPosition.getCoordinate();

        assertEquals(0, coordinate.getX());
        assertEquals(0, coordinate.getY());

        grid.turnRight();
        grid.turnRight();
        grid.move();
        grid.turnLeft();
        grid.move();
        grid.move();
        grid.turnRight();
        grid.turnRight();
        Position move = grid.move();

        Coordinate moveCoordinate = getCoordinate(move);
        assertEquals(1, moveCoordinate.getX());
        assertEquals(1, moveCoordinate.getY());
        assertEquals(WEST, move.getDirection());
    }

    @Test
    public void record_position_when_heading_north() {
        Grid grid = new Grid();

        Position defaultPosition = underTest.getPositions().peek();
        Coordinate coordinate = defaultPosition.getCoordinate();

        assertEquals(0, coordinate.getX());
        assertEquals(0, coordinate.getY());

        grid.turnRight();
        grid.turnRight();
        grid.move();
        grid.turnLeft();
        grid.move();
        grid.turnRight();
        grid.turnRight();
        grid.turnRight();

        Position move = grid.move();

        Coordinate moveCoordinate = getCoordinate(move);
        assertEquals(1, moveCoordinate.getX());
        assertEquals(0, moveCoordinate.getY());
        assertEquals(NORTH, move.getDirection());
    }


    private Coordinate getCoordinate(Position position) {
        return position.getCoordinate();
    }

}