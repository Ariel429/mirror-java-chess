package chess.domain.direction;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MovingDirectionTest {

    @Test
    @DisplayName("NORTH")
    void findDirectionNorth() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("c4"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH);
    }

    @Test
    @DisplayName("NORTH_EAST")
    void findDirectionNorthEast() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d4"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH_EAST);
    }

    @Test
    @DisplayName("EAST")
    void findDirectionEast() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d3"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.EAST);
    }

    @Test
    @DisplayName("SOUTH_EAST")
    void findDirectionSouthEast() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d2"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_EAST);
    }

    @Test
    @DisplayName("SOUTH")
    void findDirectionSouth() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("c2"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.SOUTH);
    }

    @Test
    @DisplayName("SOUTH_WEST")
    void findDirectionSouthWest() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b2"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_WEST);
    }

    @Test
    @DisplayName("WEST")
    void findDirectionWest() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b3"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.WEST);
    }

    @Test
    @DisplayName("NORTH_WEST")
    void findDirectionNorthWest() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b4"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH_WEST);
    }

    @Test
    @DisplayName("NORTH_NORTH_EAST")
    void findDirectionNorthNorthEast() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d5"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH_NORTH_EAST);
    }

    @Test
    @DisplayName("NORTH_EAST_EAST")
    void findDirectionNorthEastEast() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("e4"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH_EAST_EAST);
    }

    @Test
    @DisplayName("SOUTH_EAST_EAST")
    void findDirectionSouthEastEast() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("e2"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_EAST_EAST);
    }

    @Test
    @DisplayName("SOUTH_SOUTH_EAST")
    void findDirectionSouthSouthEast() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d1"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_SOUTH_EAST);
    }

    @Test
    @DisplayName("SOUTH_SOUTH_WEST")
    void findDirectionSouthSouthWest() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b1"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_SOUTH_WEST);
    }

    @Test
    @DisplayName("SOUTH_WEST_WEST")
    void findDirectionSouthWestWest() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("a2"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_WEST_WEST);
    }

    @Test
    @DisplayName("NORTH_WEST_WEST")
    void findDirectionNorthWestWest() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("a4"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH_WEST_WEST);
    }

    @Test
    @DisplayName("NORTH_NORTH_WEST")
    void findDirectionNorthNorthWest() {
        //given
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b5"));

        //when //then
        assertThat(direction).isEqualTo(MovingDirection.NORTH_NORTH_WEST);
    }

    @Test
    @DisplayName("체스 움직임 예외")
    void findDirectionException() {
        assertThatThrownBy(() -> MovingDirection.getDirection(Position.of("c3"), Position.of("f4")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}