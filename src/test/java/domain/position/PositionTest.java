package domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "h8", "a8", "h1"})
    void create(String input) {
        Assertions.assertThat(Position.of(input)).isEqualTo(Position.of(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "h9", "a0", "h0", "i1", "i8", "i0", "i9"})
    void wrongPosition(String input) {
        assertThatThrownBy(() -> Position.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("다른 위치를 받아 상대방 위치의 file 값과 차이를 알아낼 수 있다.")
    void getFileDifference() {
        //given
        Position A1 = Position.of("a1");

        //when //then
        assertThat(Position.of("a1").getFileDifference(Position.of("D1"))).isEqualTo(3);
    }

    @Test
    @DisplayName("다른 위치를 받아 상대방 위치의 rank 값과 차이를 알아낼 수 있다.")
    void getRankDifference() {
        //given
        Position A1 = Position.of("a1");

        //when //then
        assertThat(Position.of("a1").getRankDifference(Position.of("D1"))).isZero();
    }




}