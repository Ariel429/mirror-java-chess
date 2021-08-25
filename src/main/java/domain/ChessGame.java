package domain;

import domain.board.Board;
import domain.board.EnumRepositoryBoardInitializer;
import domain.piece.Pawn;
import domain.piece.PieceState;
import domain.player.Player;
import domain.position.File;
import domain.state.ReadyState;
import domain.state.State;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private State state;
    private Turn turn = Turn.from(Player.WHITE);

    public ChessGame() {
        this.state = new ReadyState(new EnumRepositoryBoardInitializer());
    }

    public void start() {
        state = state.start();
    }

    public void move(MoveParameter moveParameter) {
        state = state.move(moveParameter, turn);
    }

    public void end() {
        state = state.end();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public Map<Player, Double> getStatus() {
        Map<Player, Double> status = new HashMap<>();
        double blackSum = 0;
        double whiteSum = 0;

        for (File file : File.values()) {
            blackSum += getPawnPoints(file, Player.BLACK);
            whiteSum += getPawnPoints(file, Player.WHITE);
        }

        blackSum += state.getRemainPiece(Player.BLACK)
                .values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .mapToDouble(PieceState::getPoint)
                .sum();
        whiteSum += state.getRemainPiece(Player.WHITE)
                .values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .mapToDouble(PieceState::getPoint)
                .sum();

        status.put(Player.BLACK, blackSum);
        status.put(Player.WHITE, whiteSum);
        return Collections.unmodifiableMap(status);
    }


    private double getPawnPoints(File file, Player player) {
        double pawnSum = state.getRemainPiece(player)
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Pawn)
                .filter(entry -> entry.getKey().isSameFile(file))
                .mapToDouble(entry -> entry.getValue().getPoint())
                .sum();

        if (pawnSum > 1) {
            return pawnSum / 2d;
        }
        return pawnSum;
    }

}
