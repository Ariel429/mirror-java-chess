package chess.domain.piece;

import chess.domain.board.BoardState;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.exception.MovingException;

import java.util.List;

public abstract class Piece implements PieceState {

    protected PieceType pieceType;
    protected Position position;
    protected Team team;

    protected Piece(PieceType pieceType, Position position, Team team) {
        this.pieceType = pieceType;
        this.position = position;
        this.team = team;
    }

    @Override
    public PieceState move(Position target, BoardState boardState) {
        List<Position> positions = getMovablePositions(boardState);
        if (!positions.contains(target)) {
            throw new MovingException();
        }
        return movedPieceState(target);
    }

    protected abstract PieceState movedPieceState(Position target);

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public String getFigure() {
        return pieceType.getFigure(team);
    }

    @Override
    public double getPoint(BoardState boardState) {
        return pieceType.getPoint();
    }
}
