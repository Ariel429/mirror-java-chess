package domain.state;

import domain.MoveParameter;
import domain.Turn;
import domain.board.Board;
import domain.piece.PieceState;
import domain.player.Player;
import domain.position.Position;

import java.util.Map;

public interface State {

    State start();

    State move(MoveParameter moveParameter, Turn turn);

    State end();

    Board getBoard();

    boolean isEnd();

    Map<Position, PieceState> getRemainPiece(Player player);
}
