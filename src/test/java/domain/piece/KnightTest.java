package domain.piece;

import domain.player.Player;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {

    @Test
    @DisplayName("Kinght 객체는 상하좌우로 1칸 움직인 뒤 대각선 방향으로 이동 가능")
    void moveTest() {
        //given
        Piece knight = Knight.of(Position.of("b1"), Player.WHITE);

        //when
        knight.move(Position.of("c3"));

        //then
        assertThat(knight.getPosition()).isEqualTo(Position.of("c3"));
    }

    @Test
    @DisplayName("Knight 객체의 이동규칙으로 갈 수 없는 position일 때 예외 발생")
    void moveException() {
        //given
        Piece knight = Knight.of(Position.of("b1"), Player.WHITE);

        //when //then
        assertThatThrownBy(() -> knight.move(Position.of("c4")))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
