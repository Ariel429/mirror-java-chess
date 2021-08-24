package domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    @DisplayName("서로 다른 Rank의 차이를 구한다.")
    void getRankDifferenceTest() {
        assertThat(Rank.ONE.getRankDifference(Rank.EIGHT))
                .isEqualTo(7);
    }

}
