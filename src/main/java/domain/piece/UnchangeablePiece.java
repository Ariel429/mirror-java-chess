package domain.piece;

import domain.player.Player;
import domain.position.Position;

public abstract class UnchangeablePiece extends Piece {

    protected UnchangeablePiece(PieceType pieceType, Position position, Player player) {
        super(pieceType, position, player);
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }


}
