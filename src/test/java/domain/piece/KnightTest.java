package domain.piece;

import domain.player.Player;
import domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    void moveTest() {
        //given
        Piece knight = new Knight(Position.of("b1"), Player.WHITE);

        //when
        knight.move(Position.of("c3"));

        //then
        assertThat(knight.getPosition()).isEqualTo(Position.of("c3"));
    }
}
