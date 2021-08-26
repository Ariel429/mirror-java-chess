package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.MovingDirection;
import chess.exception.MovingDistanceException;
import chess.exception.ObstacleOnPathException;
import chess.domain.player.Player;
import chess.domain.position.Position;

public class NotMovedPawn extends AttackablePawn {

    private static final String BLACK_PAWN_UNICODE = "\u265F";
    private static final String WHITE_PAWN_UNICODE = "\u2659";

    protected NotMovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new NotMovedPawn(position, player);
    }

    protected void validateMove(Position target, BoardState boardState) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            if (position.getRankDifference(target) != movingDirection.getRankDirection() &&
                    position.getRankDifference(target) != movingDirection.getRankDirection() * 2) {
                throw new MovingDistanceException();
            }
            Position frontPosition = position.moveByDirection(movingDirection);
            if (boardState.isNotEmpty(frontPosition)) {
                throw new ObstacleOnPathException();
            }
            if (boardState.isNotEmpty(target)) {
                throw new ObstacleOnPathException();
            }
        }
    }

    @Override
    protected PieceState makePieceState() {
        return MovedPawn.of(position, player);
    }
}

