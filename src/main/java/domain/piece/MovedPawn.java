package domain.piece;

import domain.BoardState;
import domain.MovingDirection;
import domain.exception.MovingDistanceException;
import domain.exception.ObstacleOnPathException;
import domain.player.Player;
import domain.position.Position;

public class MovedPawn extends AttackablePawn {

    protected MovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static MovedPawn of(Position position, Player player) {
        return new MovedPawn(position, player);
    }

    @Override
    protected void validateMove(Position target, BoardState boardState) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            if (position.getRankDifference(target) != movingDirection.getRankDirection()) {
                throw new MovingDistanceException();
            }
            if (boardState.isNotEmpty(target)) {
                throw new ObstacleOnPathException();
            }
        }
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }
}

