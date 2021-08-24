package domain;

import domain.board.Board;
import domain.board.EnumRepositoryBoardInitializer;
import domain.state.ReadyState;
import domain.state.State;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new ReadyState(new EnumRepositoryBoardInitializer());
    }

    public void start() {
        state = state.start();
    }

    public void move(MoveParameter moveParameter) {
        state = state.move(moveParameter.getSource(), moveParameter.getTarget());
    }

    public void end() {
        state = state.end();
    }

    public Board getBoard() {
        return state.getBoard();
    }

}
