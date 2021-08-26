package chess.domain.piece.implementation;

import chess.controller.dto.PieceDto;
import chess.domain.board.BoardSituation;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    private PieceState whiteKnight;
    private Map<Position, Team> boardDto;
    private BoardSituation boardSituation;
    private Team whiteTeam = Team.WHITE;
    private Team blackTeam = Team.BLACK;

    @BeforeEach
    void setUp() {
        whiteKnight = Knight.of(Position.of("b1"), Team.WHITE);
        boardDto = new HashMap<>();
        boardSituation = BoardSituation.of(boardDto);
    }

    @Test
    @DisplayName("진행하려는 타겟 위치가 비어 있으면 이동 가능")
    void moveToEmpty() {
        assertThat(whiteKnight.move(Position.of("c3"), boardSituation))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행하려는 타겟 위치에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        //given
        boardDto.put(Position.of("c3"), whiteTeam);
        boardSituation = BoardSituation.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteKnight.move(Position.of("c3"), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행하려는 타겟 위치에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        //given
        boardDto.put(Position.of("c3"), blackTeam);
        boardSituation = BoardSituation.of(boardDto);

        //when //then
        assertThat(whiteKnight.move(Position.of("c3"), boardSituation))
                .isInstanceOf(Knight.class);
    }


    @Test
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException() {
        assertThatThrownBy(() -> whiteKnight.move(Position.of("c4"), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행하려는 타겟 위치에 적군이 있지만, 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        //given
        boardDto.put(Position.of("c4"), blackTeam);
        boardSituation = BoardSituation.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteKnight.move(Position.of("c4"), boardSituation))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
