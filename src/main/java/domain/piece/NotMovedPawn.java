package domain.piece;

import domain.MovingDirection;
import domain.exception.MovingDistanceException;
import domain.exception.ObstacleOnPathException;
import domain.player.Player;
import domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class NotMovedPawn extends AttackablePawn {

    private static final String BLACK_PAWN_UNICODE = "\u265F";
    private static final String WHITE_PAWN_UNICODE = "\u2659";

    protected NotMovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new NotMovedPawn(position, player);
    }

    protected void validateMove(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            if (position.getRankDifference(target) != movingDirection.getRankDirection() &&
                    position.getRankDifference(target) != movingDirection.getRankDirection() * 2) {
                throw new MovingDistanceException();
            }
            Position frontPosition = position.moveByDirection(movingDirection);
            if (!Objects.isNull(boardDto.get(frontPosition))) {
                throw new ObstacleOnPathException();
            }
            PieceDto piece = boardDto.get(target);
            if (!Objects.isNull(piece)) {
                throw new ObstacleOnPathException();
            }
        }
    }

    @Override
    protected PieceState makePieceState() {
        return MovedPawn.of(position, player);
    }
}

