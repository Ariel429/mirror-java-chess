package domain.piece;

import domain.player.Player;
import domain.position.Position;

public class King extends Piece {

    private King(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new King(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
