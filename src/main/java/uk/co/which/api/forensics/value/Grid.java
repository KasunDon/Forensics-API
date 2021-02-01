package uk.co.which.api.forensics.value;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static uk.co.which.api.forensics.value.Direction.EAST;
import static uk.co.which.api.forensics.value.Direction.NORTH;
import static uk.co.which.api.forensics.value.Direction.SOUTH;
import static uk.co.which.api.forensics.value.Direction.WEST;
import static uk.co.which.api.forensics.value.Position.PositionFactory.create;

public class Grid {

    private final static Map<Direction, Direction> RIGHT = new HashMap<Direction, Direction>() {
        {
            put(NORTH, EAST);
            put(EAST, SOUTH);
            put(SOUTH, WEST);
            put(WEST, NORTH);
        }
    };
    private final static Map<Direction, Direction> LEFT = new HashMap<Direction, Direction>() {
        {
            put(NORTH, WEST);
            put(WEST, SOUTH);
            put(SOUTH, EAST);
            put(EAST, NORTH);
        }
    };

    private Direction currentDirection = NORTH;
    private final Stack<Position> positions = new Stack<>();

    public Grid() {
        positions.push(create(0, 0, NORTH));
    }

    public Position move() {
        Position position = positions.peek();
        Coordinate coordinate = position.getCoordinate();

        if (currentDirection == EAST) {
            Position newPosition = create(coordinate.getX() + 1, coordinate.getY(), currentDirection);
            positions.push(newPosition);
            return newPosition;
        }

        if (currentDirection == SOUTH) {
            Position newPosition = create(coordinate.getX(), coordinate.getY() + 1, currentDirection);
            positions.push(newPosition);
            return newPosition;
        }

        if (currentDirection == NORTH) {
            Position newPosition = create(coordinate.getX(), coordinate.getY() - 1, currentDirection);
            positions.push(newPosition);
            return newPosition;
        }

        Position newPosition = create(coordinate.getX() - 1, coordinate.getY(), currentDirection);
        positions.push(newPosition);
        return newPosition;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Stack<Position> getPositions() {
        return positions;
    }

    public void turnRight() {
        currentDirection = RIGHT.get(currentDirection);
    }

    public void turnLeft() {
        currentDirection = LEFT.get(currentDirection);
    }
}
