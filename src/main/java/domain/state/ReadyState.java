package domain.state;

import domain.MoveParameter;
import domain.Turn;
import domain.board.Board;
import domain.board.BoardInitializer;

public class ReadyState implements State {

    private final BoardInitializer boardInitializer;

    public ReadyState(BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
    }


    @Override
    public State start() {
        return new RunningState(Board.of(boardInitializer));
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        throw new UnsupportedOperationException("게임이 시작되지 않아 체스 말을 움직일 수 없습니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("게임이 시작되지 않아 종료할 수 없습니다.");
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }
}
