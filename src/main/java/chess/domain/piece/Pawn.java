package chess.domain.piece;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.*;

public abstract class Pawn extends Piece {

    protected static final Map<Team, MovingDirection> MOVING_DIRECTION_BY_TEAM;
    protected static final Map<Team, List<MovingDirection>> ATTACK_DIRECTION_BY_TEAM;

    static {
        MOVING_DIRECTION_BY_TEAM = new HashMap<>();
        MOVING_DIRECTION_BY_TEAM.put(Team.WHITE, MovingDirection.NORTH);
        MOVING_DIRECTION_BY_TEAM.put(Team.BLACK, MovingDirection.SOUTH);

        ATTACK_DIRECTION_BY_TEAM = new HashMap<>();
        ATTACK_DIRECTION_BY_TEAM.put(Team.WHITE, Arrays.asList(
                MovingDirection.NORTH_EAST,
                MovingDirection.NORTH_WEST
        ));
        ATTACK_DIRECTION_BY_TEAM.put(Team.BLACK, Arrays.asList(
                MovingDirection.SOUTH_EAST,
                MovingDirection.SOUTH_WEST
        ));
    }

    protected Pawn(Position position, Team team) {
        super(PieceType.PAWN, position, team);
    }

    protected abstract List<Position> movePositions(BoardSituation boardSituation);

    public List<Position> getMovablePositions(BoardSituation boardSituation) {
        List<Position> positions = attackPositions(boardSituation);
        positions.addAll(movePositions(boardSituation));
        return positions;
    }

    private List<Position> attackPositions(BoardSituation boardSituation) {
        List<Position> attackPositions = new ArrayList<>();
        List<MovingDirection> attackDirections = ATTACK_DIRECTION_BY_TEAM.get(team);
        for (MovingDirection attackDirection : attackDirections) {
            canAttackBy(attackDirection, boardSituation, attackPositions);
        }
        return attackPositions;
    }

    private void canAttackBy(MovingDirection attackDirection, BoardSituation boardSituation, List<Position> attackPositions) {
        Position startPosition = position;
        if (startPosition.canMoveBy(attackDirection)) {
            startPosition = startPosition.moveByDirection(attackDirection);
            if (boardSituation.canAttack(startPosition, team)) {
                attackPositions.add(startPosition);
            }
        }
    }

}
