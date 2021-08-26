package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveByDirectionPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.exception.MovingDistanceException;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends MoveByDirectionPiece {

    private static final String BLACK_KNIGHT_UNICODE = "\u265E";
    private static final String WHITE_KNIGHT_UNICODE = "\u2658";
    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                MovingDirection.NORTH_NORTH_EAST,
                MovingDirection.NORTH_NORTH_WEST,
                MovingDirection.SOUTH_SOUTH_EAST,
                MovingDirection.SOUTH_SOUTH_WEST,
                MovingDirection.SOUTH_EAST_EAST,
                MovingDirection.SOUTH_WEST_WEST,
                MovingDirection.NORTH_EAST_EAST,
                MovingDirection.NORTH_WEST_WEST
        );
    }

    private Knight(Position position, Team team) {
        super(PieceType.KNIGHT, position, team);
    }

    public static Piece of(Position position, Team team) {
        return new Knight(position, team);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Knight(target, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }
}
