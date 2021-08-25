package domain.piece;

import domain.MovingDirection;
import domain.exception.MovingDistanceException;
import domain.exception.ObstacleOnPathException;
import domain.player.Player;
import domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class MovedPawn extends AttackablePawn {

    protected MovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static MovedPawn of(Position position, Player player) {
        return new MovedPawn(position, player);
    }

    @Override
    protected void validateMove(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            if (position.getRankDifference(target) != movingDirection.getRankDirection()) {
                throw new MovingDistanceException();
            }
            PieceDto piece = boardDto.get(target);
            if (!Objects.isNull(piece)) {
                throw new ObstacleOnPathException();
            }
        }
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }
}

