package domain.state;

import domain.board.Board;
import domain.position.Position;

public interface State {

    State start();

    State move(Position source, Position target);

    State end();

    Board getBoard();
}
