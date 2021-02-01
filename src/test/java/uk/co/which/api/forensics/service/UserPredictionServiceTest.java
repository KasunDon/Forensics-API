package uk.co.which.api.forensics.service;

import org.assertj.core.api.NotThrownAssert;
import org.junit.jupiter.api.Test;
import uk.co.which.api.forensics.exception.UserPredictionExceededException;
import uk.co.which.api.forensics.value.Coordinate;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserPredictionServiceTest {

    private final UserPredictionService underTest = new UserPredictionService();

    @Test
    public void test_throw_an_exception_when_email_is_null() {
        assertThrows(NullPointerException.class, () -> underTest.store(null, new Coordinate(0, 0)));
    }

    @Test
    public void test_throw_an_exception_when_coordinate_is_null() {
        assertThrows(NullPointerException.class, () -> underTest.store("k@k.com", null));
    }


    @Test
    public void test_adding_prediction_coordinates_using_email() {
        assertThatNoException();

        String email = "email@email.com";
        underTest.store(email, new Coordinate(0, 0));
        assertEquals(1, underTest.getPredictions(email).size());
    }

    @Test
    public void dedupe_prediction_coordinates() {
        NotThrownAssert notThrownAssert = assertThatNoException();

        String email = "email@email.com";
        underTest.store(email, new Coordinate(0, 0));
        underTest.store(email, new Coordinate(0, 0));
        assertEquals(1, underTest.getPredictions(email).size());
    }

    @Test
    public void thrown_an_exception_prediction_exceeds_more_than_5_coordinates_per_email() {
        String email = "email@email.com";
        assertThrows(UserPredictionExceededException.class, () -> {
            underTest.store(email, new Coordinate(0, 0));
            underTest.store(email, new Coordinate(0, 1));
            underTest.store(email, new Coordinate(0, 2));
            underTest.store(email, new Coordinate(0, 3));
            underTest.store(email, new Coordinate(0, 4));
            underTest.store(email, new Coordinate(0, 5));
        });
    }

    @Test
    public void locate_returns_true_user_prediction_matches_grid_coodinate() {
        String email = "email@email.com";
        assertTrue(underTest.locate(email, new Coordinate(0, 0)));
    }

    @Test
    public void locate_returns_false_user_prediction_matches_grid_coodinate() {
        String email = "email@email.com";
        assertFalse(underTest.locate(email, new Coordinate(1, 0)));
    }
}