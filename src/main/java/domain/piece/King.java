package domain.piece;

import domain.player.Player;
import domain.position.Position;

public class King extends Piece {

    protected King(Position position, Player player) {
        super(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
