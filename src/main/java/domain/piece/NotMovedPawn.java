package domain.piece;

import domain.player.Player;
import domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class NotMovedPawn extends Piece {

    private static final String BLACK_PAWN_UNICODE = "\u265F";
    private static final String WHITE_PAWN_UNICODE = "\u2659";

    protected NotMovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new NotMovedPawn(position, player);
    }

    @Override
    protected Boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        int fileDifference = position.getFileDifference(target);
        int rankDifference = position.getRankDifference(target);
        PieceDto targetPiece = boardDto.get(target);

        checkPawnDirection(rankDifference);

        if (!Objects.isNull(targetPiece) && Math.abs(rankDifference) == 1) {
            return true;
        }

        if (Objects.isNull(targetPiece) && Math.abs(rankDifference) == 2) {
            // 플레이어에 따라 Rank에 -1, +1 위치에 해당하는 말이 있는지 확인
        }

        if (fileDifference == 0 && rankDifference <= 2) {
            return false;
        }
        return null;
    }

    private Boolean checkPawnDirection(int rankDifference) {
        if (player == Player.WHITE) {
            return rankDifference > 0;
        }
        return rankDifference < 0;
    }

    @Override
    protected PieceState makePieceState() {
        return null;
    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_PAWN_UNICODE;
        }
        return WHITE_PAWN_UNICODE;
    }

}

