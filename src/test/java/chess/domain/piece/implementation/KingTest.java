package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {
    private PieceState whiteKing;
    private Map<Position, PieceDto> boardDto;
    private BoardState boardState;

    @BeforeEach
    void setUp() {
        whiteKing = King.of(Position.of("C4"), Team.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(King.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Team.WHITE));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThat(whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(King.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteKing.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }
}