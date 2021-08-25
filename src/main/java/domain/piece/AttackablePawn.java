package domain.piece;

import domain.MovingDirection;
import domain.exception.MovingDirectionException;
import domain.exception.MovingDistanceException;
import domain.player.Player;
import domain.position.Position;

import java.util.Map;
import java.util.Objects;

public abstract class AttackablePawn extends Pawn {

    protected AttackablePawn(Position position, Player player) {
        super(position, player);
    }

    @Override
    protected void validateAttack(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection direction = MovingDirection.getDirection(position, target);

        PieceDto pieceDto = boardDto.get(target);
        if (ATTACK_DIRECTION_BY_PLAYER.get(player).contains(direction)) {
            if (Objects.isNull(pieceDto)) {
                throw new MovingDirectionException();
            }
            if (position.getRankDifference(target) != 1) {
                throw new MovingDistanceException();
            }
        }
    }
}
