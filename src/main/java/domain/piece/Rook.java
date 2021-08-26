package domain.piece;

import domain.BoardState;
import domain.MovingDirection;
import domain.exception.MovingDirectionException;
import domain.exception.ObstacleOnPathException;
import domain.player.Player;
import domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Rook extends UnchangeablePiece {

    private static final String BLACK_ROOK_UNICODE = "\u265C";
    private static final String WHITE_ROOK_UNICODE = "\u2656";
    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                MovingDirection.NORTH,
                MovingDirection.EAST,
                MovingDirection.SOUTH,
                MovingDirection.WEST);
    }

    private Rook(Position position, Player player) {
        super(PieceType.ROOK, position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Rook(position, player);
    }

    @Override
    protected void validateMovingPolicy(Position target, BoardState boardState) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (!MOVING_DIRECTIONS.contains(movingDirection)) {
            throw new MovingDirectionException();
        }

        Position startPathPosition = position.moveByDirection(movingDirection);
        while (!startPathPosition.equals(target)) {
            if (boardState.isNotEmpty(startPathPosition)) {
                throw new ObstacleOnPathException();
            }
            startPathPosition = startPathPosition.moveByDirection(movingDirection);
        }
    }

    @Override
    public String getFigure() {
        if (player == Player.BLACK) {
            return BLACK_ROOK_UNICODE;
        }
        return WHITE_ROOK_UNICODE;
    }
}
