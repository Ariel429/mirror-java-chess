package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private final static Map<String, Position> matcher = new HashMap<>();

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                matcher.put(key(file, rank), new Position(file, rank));
            }
        }
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final String input) {
        Position position = matcher.get(input.toUpperCase());
        validatePosition(position);
        return position;
    }

    private static void validatePosition(Position position) {
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException("잘못된 위치 정보입니다.");
        }
    }
    private static String key(final File file, final Rank rank) {
        return file.toString() + rank.getRank();
    }
}
