package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MovedPawnTest {

    private PieceState whiteMovedPawn;
    private Map<Position, PieceDto> boardDto;
    private BoardState boardState;
    private PieceDto whitePiece = new PieceDto(PieceType.PAWN, Team.WHITE);
    private PieceDto blackPiece = new PieceDto(PieceType.PAWN, Team.BLACK);
    
    @BeforeEach
    void setUp() {
        whiteMovedPawn = MovedPawn.of(Position.of("b3"), Team.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }
    
    @Test
    @DisplayName("Pawn은 바로 앞에 기물이 있는 경우 전진할 수 없음")
    void moveToAlly() {
        //given
        boardDto.put(Position.of("b4"), whitePiece);
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("b4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy() {
        //given
        boardDto.put(Position.of("b4"), blackPiece);
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("b4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteMovedPawn.move(Position.of("b4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("대각선으로 진행할 때 진행하려는 타겟 위치에 적군이 있으면 이동 가능")
    void diagonalMoveToEnemy() {
        //given
        boardDto.put(Position.of("c4"), blackPiece);
        boardState = BoardState.of(boardDto);

        //when //then
        assertThat(whiteMovedPawn.move(Position.of("c4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"d5", "a4", "c4"})
    @DisplayName("진행 규칙에 어긋나는 타겟 위치로 이동하려고 하면 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행하려는 타겟에 적군이 있지만, 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        //given
        boardDto.put(Position.of("d4"), blackPiece);
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("d4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
