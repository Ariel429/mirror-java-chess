package domain;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovingDirectionTest {

    @Test
    @DisplayName("NORTH")
    void findDirectionNorth() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("c4"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH);
    }

}