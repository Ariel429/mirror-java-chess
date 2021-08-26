package domain.piece;

import domain.BoardState;
import domain.player.Player;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class RookTest {
    private PieceState whiteRook;
    private Map<Position, PieceDto> boardDto;
    private BoardState boardState;

    @BeforeEach
    void setUp() {
        whiteRook = Rook.of(Position.of("b3"), Player.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b8", "b1", "a3", "h3"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteRook.move(Position.of(target), boardState))
                .isInstanceOf(Rook.class);
    }
    
    @Test
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        //given
        boardDto.put(Position.of("b8"), new PieceDto(Player.WHITE));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteRook.move(Position.of("b8"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath() {
        //given
        boardDto.put(Position.of("b7"), new PieceDto(Player.WHITE));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteRook.move(Position.of("b8"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        //given
        boardDto.put(Position.of("b8"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThat(whiteRook.move(Position.of("b8"), boardState))
                .isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("진행 경로에 적군이 있는 경우 예외 발생")
    void enemyOnPath() {
        //given
        boardDto.put(Position.of("b6"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteRook.move(Position.of("b8"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 장애물이 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a4", "c4", "a2", "c2"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String target) {
        assertThatThrownBy(() -> whiteRook.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        //given
        boardDto.put(Position.of("a4"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteRook.move(Position.of("a4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }
}