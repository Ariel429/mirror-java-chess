package domain.state;

import domain.MoveParameter;
import domain.Turn;
import domain.board.Board;
import domain.piece.PieceState;
import domain.player.Player;
import domain.position.Position;

import java.util.Map;

public class RunningState implements State {

    private Board board;

    public RunningState(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        board.move(moveParameter.getSource(), moveParameter.getTarget(), turn);
        if (board.isLost(Player.WHITE) || board.isLost(Player.BLACK)) {
            return new EndState(board);
        }
        return this;
    }

    @Override
    public State end() {
        return new EndState(board);
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Map<Position, PieceState> getRemainPiece(Player player) {
        return board.getRemainPieces(player);
    }
}
