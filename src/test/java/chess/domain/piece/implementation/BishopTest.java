package chess.domain.piece.implementation;

import chess.controller.dto.PieceDto;
import chess.domain.board.BoardSituation;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    private PieceState whiteBishop;
    private BoardSituation boardSituation;
    private Map<Position, Team> boardDto;
    private Team whiteTeam = Team.WHITE;
    private Team blackTeam = Team.BLACK;

    @BeforeEach
    void setUp() {
        whiteBishop = Bishop.of(Position.of("c4"), Team.WHITE);
        boardDto = new HashMap<>();
        boardSituation = BoardSituation.of(boardDto);
    }

    @Test
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteBishop.move(Position.of("d5"), boardSituation))
                .isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2", "b3", "d5", "e6", "f7", "g8"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        //given
        boardDto.put(Position.of(target), whiteTeam);
        boardSituation = BoardSituation.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath() {
        //given
        boardDto.put(Position.of("b5"), whiteTeam);
        boardSituation = BoardSituation.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of("a6"), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        //given
        boardDto.put(Position.of("d5"), blackTeam);
        boardSituation = BoardSituation.of(boardDto);

        //when //then
        assertThat(whiteBishop.move(Position.of("d5"), boardSituation))
                .isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A6:B5", "E6:D5", "G8:F7"}, delimiter = ':')
    @DisplayName("진행 경로에 적군이 있는 경우 이동 불가")
    void enemyOnPath(String target, String path) {
        //given
        boardDto.put(Position.of(path), blackTeam);
        boardSituation = BoardSituation.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException() {
        assertThatThrownBy(() -> whiteBishop.move(Position.of("e5"), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "B2", "D4"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        //given
        boardDto.put(Position.of(target), blackTeam);

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }
}