package domain.piece;

import domain.Direction;
import domain.player.Player;
import domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    private static final String BLACK_KING_UNICODE = "\u265A";
    private static final String WHITE_KING_UNICODE = "\u2654";
    private static final List<Direction> movingDirections;
    public static final int MAXIMUM_DISTANCE = 1;

    static {
        movingDirections = Arrays.asList(
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST,
                Direction.NORTH_EAST,
                Direction.NORTH_WEST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST);
    }

    private King(Position position, Player player) {
        super(position, player);
    }


    public static Piece of(Position position, Player player) {
        return new King(position, player);
    }

    @Override
    protected Boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        Direction direction = Direction.getDirection(position, target);

        if (!movingDirections.contains(direction)) {
            return false;
        }

        int fileDirection = direction.getFileDirection();
        int rankDirection = direction.getRankDirection();

        int fileDifference = Math.abs(position.getFileDifference(target));
        int rankDifference = Math.abs(position.getRankDifference(target));

        if (rankDifference > MAXIMUM_DISTANCE || fileDifference > MAXIMUM_DISTANCE) {
            return false;
        }
        return true;
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_KING_UNICODE;
        }
        return WHITE_KING_UNICODE;
    }
}
