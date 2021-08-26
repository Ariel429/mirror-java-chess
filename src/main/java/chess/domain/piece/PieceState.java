package chess.domain.piece;

import chess.domain.board.BoardState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;

public interface PieceState {

    PieceState move(Position target, BoardState boardState);

    List<Position> getMovablePositions(BoardState boardState);

    Team getTeam();

    String getFigure();

    double getPoint();
}
