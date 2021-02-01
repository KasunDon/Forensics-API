package uk.co.which.api.forensics.value;

import static java.util.Objects.requireNonNull;

public class Position {

    private final Coordinate coordinate;
    private final Direction direction;

    private Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }


    public static class PositionFactory {
        public static Position create(int x, int y, Direction direction) {
            return new Position(new Coordinate(x, y), requireNonNull(direction));
        }
    }
}
