package domain.piece;

import domain.player.Player;
import domain.position.Position;

public class Pawn extends Piece {

    protected Pawn(Position position, Player player) {
        super(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
