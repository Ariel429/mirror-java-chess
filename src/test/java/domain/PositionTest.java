package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "h8", "a8", "h1"})
    void create(String input) {
        assertThat(Position.of(input)).isEqualTo(Position.of(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "h9", "a0", "h0", "i1", "i8", "i0", "i9"})
    void wrongPosition(String input) {
        assertThatThrownBy(() -> Position.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}