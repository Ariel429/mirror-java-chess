package chess.domain.board;

import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.EnumRepositoryBoardInitializer;
import chess.domain.piece.Knight;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class BoardTest {

    @Test
    @DisplayName("board는 boardInitializer 타입의 객체를 받아서 생성")
    void initialize() {
        Board board = Board.of(new EnumRepositoryBoardInitializer());
    }

    @Test
    void move() {
        //given
        Board board = Board.of(new EnumRepositoryBoardInitializer());

        //when
        board.move(Position.of("b1"), Position.of("c3"), Turn.from(Player.WHITE));
        Map<Position, PieceState> piecePosition = board.getBoard();

        //then
        assertThat(piecePosition.get(Position.of("b1")))
                .isNull();
        assertThat(piecePosition.get(Position.of("c3")))
                .isInstanceOf(Knight.class);
    }

}