package uk.co.which.api.forensics.service;

import org.springframework.stereotype.Component;
import uk.co.which.api.forensics.exception.UserPredictionExceededException;
import uk.co.which.api.forensics.value.Coordinate;
import uk.co.which.api.forensics.value.Grid;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@Component
public class UserPredictionService {

    private final Grid grid = new Grid();
    private final Map<String, Set<Coordinate>> predictions = new ConcurrentHashMap<>();

    public void store(
        String email,
        Coordinate coordinate
    ) {
        requireNonNull(email);
        requireNonNull(coordinate);

        Set<Coordinate> coordinates = predictions.getOrDefault(email, new HashSet<>());
        if (coordinates.size() == 5) {
            throw new UserPredictionExceededException();
        }
        coordinates.add(coordinate);
        predictions.put(email, coordinates);
    }

    public boolean locate(String email, Coordinate coordinate) {
        store(email, coordinate);
        return grid.getPositions().peek().getCoordinate().equals(coordinate);
    }

    public Grid getGrid() {
        return grid;
    }

    public Set<Coordinate> getPredictions(String email) {
        return predictions.get(email);
    }
}
