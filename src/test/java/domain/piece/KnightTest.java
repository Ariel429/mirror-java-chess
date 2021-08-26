package domain.piece;

import domain.BoardState;
import domain.player.Player;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {

    private PieceState whiteKnight;
    private Map<Position, PieceDto> boardDto;
    private BoardState boardState;

    @BeforeEach
    void setUp() {
        whiteKnight = Knight.of(Position.of("b1"), Player.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @Test
    @DisplayName("진행하려는 타겟 위치가 비어 있으면 이동 가능")
    void moveToEmpty() {
        assertThat(whiteKnight.move(Position.of("c3"), boardState))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행하려는 타겟 위치에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        //given
        boardDto.put(Position.of("c3"), new PieceDto(Player.WHITE));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteKnight.move(Position.of("c3"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("진행하려는 타겟 위치에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        //given
        boardDto.put(Position.of("c3"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThat(whiteKnight.move(Position.of("c3"), boardState))
                .isInstanceOf(Knight.class);
    }


    @Test
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException() {
        assertThatThrownBy(() -> whiteKnight.move(Position.of("c4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

    @Test
    @DisplayName("진행하려는 타겟 위치에 적군이 있지만, 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        //given
        boardDto.put(Position.of("c4"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteKnight.move(Position.of("c4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }
}
