package chess.domain.piece;

import chess.domain.board.BoardState;
import chess.domain.direction.MovingDirection;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveByDistancePiece extends Piece{

    protected MoveByDistancePiece(PieceType pieceType, Position position, Team team) {
        super(pieceType, position, team);
    }

    @Override
    public List<Position> getMovablePositions(BoardState boardState) {
        List<Position> positions = new ArrayList<>();
        for (MovingDirection movingDirection : getMovingDirections()) {
            addPositionBy(boardState, positions, movingDirection);
        }
        return positions;
    }

    private void addPositionBy(BoardState boardState, List<Position> positions, MovingDirection movingDirection) {
        Position targetPosition = position;
        if (targetPosition.canMove(movingDirection)) {
            targetPosition = targetPosition.moveByDirection(movingDirection);
            if (boardState.canMove(targetPosition) || boardState.canAttack(targetPosition, team)) {
                positions.add(targetPosition);
            }
        }
    }

    protected abstract List<MovingDirection> getMovingDirections();
}
