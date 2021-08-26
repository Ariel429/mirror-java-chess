package chess.domain.board;

import chess.domain.board.initializer.AutomatedBoardInitializer;
import chess.domain.game.Turn;
import chess.domain.piece.implementation.Knight;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class BoardTest {

    @Test
    @DisplayName("board는 boardInitializer 타입의 객체를 받아서 생성")
    void initialize() {
        Board board = Board.of(new AutomatedBoardInitializer());
    }

    @Test
    void move() {
        //given
        Board board = Board.of(new AutomatedBoardInitializer());

        //when
        board.move(Position.of("b1"), Position.of("c3"), Turn.from(Team.WHITE));
        Map<Position, PieceState> piecePosition = board.getBoard();

        //then
        assertThat(piecePosition.get(Position.of("b1")))
                .isNull();
        assertThat(piecePosition.get(Position.of("c3")))
                .isInstanceOf(Knight.class);
    }

}