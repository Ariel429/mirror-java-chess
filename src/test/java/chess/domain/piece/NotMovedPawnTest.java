package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.NotMovedPawn;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class NotMovedPawnTest {

    private PieceState whiteMovedPawn;
    private Map<Position, PieceDto> boardDto;
    private BoardState boardState;

    @BeforeEach
    void setUp() {
        whiteMovedPawn = NotMovedPawn.of(Position.of("b3"), Player.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @Test
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToALly() {
        //given
        boardDto.put(Position.of("b4"), new PieceDto(Player.WHITE));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("b4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b4", "b5"})
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("직선으로 2칸 진행할 떄 진행 경로에 적군이 있는 경우")
    void frontMoveObstacle() {
        //given
        boardDto.put(Position.of("b5"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("b5"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 장애물이 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b4", "b5"})
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteMovedPawn.move(Position.of(target), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("대각선으로 진행할 떄 진행 타겟에 적군이 있는 경우 이동 가능")
    void diagonalMoveToEnemy() {
        //given
        boardDto.put(Position.of("c4"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThat(whiteMovedPawn.move(Position.of("c4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c4", "d5", "a4"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String target) {
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "a5"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }
}