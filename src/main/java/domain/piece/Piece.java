package domain.piece;

import domain.player.Player;
import domain.position.Position;

public abstract class Piece {

    protected Position position;
    protected Player player;

    protected Piece(Position position, Player player) {
        this.position = position;
        this.player = player;
    }

    public abstract void move(Position target);

    public Position getPosition() {
        return position;
    }

}
