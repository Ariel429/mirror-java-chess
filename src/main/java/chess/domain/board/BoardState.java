package chess.domain.board;

import chess.domain.piece.PieceDto;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class BoardState {

    private Map<Position, PieceDto> boardState;

    private BoardState(Map<Position, PieceDto> boardState) {
        this.boardState = boardState;
    }

    public static BoardState of(Map<Position, PieceDto> boardState) {
        return new BoardState(boardState);
    }

    public boolean canMove(Position target) {
        PieceDto pieceDto = boardState.get(target);
        return Objects.isNull(pieceDto);
    }

    public boolean canAttack(Position target, Team team) {
        PieceDto pieceDto = boardState.get(target);
        return !Objects.isNull(target) && !team.isSameTeam(pieceDto.getTeam());
    }
}
