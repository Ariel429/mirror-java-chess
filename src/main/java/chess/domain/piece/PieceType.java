package chess.domain.piece;

import chess.domain.player.Team;

public enum PieceType {

    PAWN(1d),
    KNIGHT(2.5d),
    BISHOP(3d),
    ROOK(5d),
    QUEEN(9d),
    KING(0d);

    private double point;

    PieceType(double point) {
        this.point = point;
    }

    public double getPoint() {
        return point;
    }

    public boolean isSameType(PieceType pieceType) {
        return this == pieceType;
    }
}
