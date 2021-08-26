package domain.piece;

import domain.BoardState;
import domain.MovingDirection;
import domain.exception.MovingDirectionException;
import domain.exception.MovingDistanceException;
import domain.player.Player;
import domain.position.Position;

public abstract class AttackablePawn extends Pawn {

    protected AttackablePawn(Position position, Player player) {
        super(position, player);
    }

    @Override
    protected void validateAttack(Position target, BoardState boardState) {
        MovingDirection direction = MovingDirection.getDirection(position, target);

        if (ATTACK_DIRECTION_BY_PLAYER.get(player).contains(direction)) {
            if (boardState.isEmpty(target)) {
                throw new MovingDirectionException();
            }
            if (position.getRankDifference(target) != direction.getRankDirection()) {
                throw new MovingDistanceException();
            }
        }
    }
}
