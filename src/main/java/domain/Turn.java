package domain;

import domain.player.Player;

public class Turn {
    private Player turn;

    private Turn(Player turn) {
        this.turn = turn;
    }

    public static Turn from(Player turn) {
        return new Turn(turn);
    }

    public void switchTurn() {
        turn = turn.toggle();
    }

    public boolean isSamePlayer(Player player) {
        return turn.equals(player);
    }
}
