package domain.piece;

import domain.MovingDirection;
import domain.exception.MovingDistanceException;
import domain.player.Player;
import domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Knight extends UnchangeablePiece {

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

    private Knight(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Knight(position, player);
    }

    @Override
    protected void validateMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (!MOVING_DIRECTIONS.contains(movingDirection)) {
            throw new MovingDistanceException();
        }

        int fileDifference = position.getFileDifference(target);
        int rankDifference = position.getRankDifference(target);

        if (!(movingDirection.getFileDirection() == fileDifference &&
                movingDirection.getRankDirection() == rankDifference)) {
            throw new MovingDistanceException();
        }
    }

    @Override
    public String getFigure() {
        if (player == Player.BLACK) {
            return BLACK_KNIGHT_UNICODE;
        }
        return WHITE_KNIGHT_UNICODE;
    }

}
