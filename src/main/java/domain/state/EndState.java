package domain.state;

import domain.MoveParameter;
import domain.Turn;
import domain.board.Board;

public class EndState implements State {

    private final Board board;

    public EndState(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new RunningState(board);
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
