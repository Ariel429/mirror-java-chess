package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public String getRank() {
        return String.valueOf(value);
    }

    public int getRankDifference(Rank targetRank) {
        return targetRank.value - value;
    }

    public Rank add(int rank) {
        return values()[ordinal() - rank];
    }
}
