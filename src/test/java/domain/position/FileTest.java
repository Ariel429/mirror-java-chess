package domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    @Test
    @DisplayName("서로 다른 파일의 차이를 구한다.")
    void getFileDifferenceTest() {
        Assertions.assertThat(File.A.getFileDifference(File.H)).isEqualTo(7);
    }
}
