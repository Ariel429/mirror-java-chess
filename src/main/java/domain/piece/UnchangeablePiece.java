package domain.piece;

import domain.player.Player;
import domain.position.Position;

public abstract class UnchangeablePiece extends Piece {

    protected UnchangeablePiece(Position position, Player player) {
        super(position, player);
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }


}
