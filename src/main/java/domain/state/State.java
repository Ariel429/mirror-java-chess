package domain.state;

import domain.MoveParameter;
import domain.Turn;
import domain.board.Board;

public interface State {

    State start();

    State move(MoveParameter moveParameter, Turn turn);

    State end();

    Board getBoard();
}
