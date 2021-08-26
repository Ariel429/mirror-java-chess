package chess.domain.position;

import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    @DisplayName("서로 다른 Rank의 차이를 구한다.")
    void getRankDifferenceTest() {
        assertThat(Rank.EIGHT.getRankDifference(Rank.ONE)).isEqualTo(-7);
    }

}
