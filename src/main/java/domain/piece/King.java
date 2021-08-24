package domain.piece;

import domain.player.Player;
import domain.position.Position;

import java.util.Map;

public class King extends Piece {

    private static final String BLACK_KING_UNICODE = "\u265A";
    private static final String WHITE_KING_UNICODE = "\u2654";

    private King(Position position, Player player) {
        super(position, player);
    }


    public static Piece of(Position position, Player player) {
        return new King(position, player);
    }

    @Override
    protected Boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        return null;
    }

    @Override
    protected PieceState makePieceState() {
        return null;
    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_KING_UNICODE;
        }
        return WHITE_KING_UNICODE;
    }
}
