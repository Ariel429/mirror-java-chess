package domain.state;

import domain.board.EnumRepositoryBoardInitializer;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyStateTest {

    ReadyState readyState;

    @BeforeEach
    void setUp() {
        readyState = new ReadyState(new EnumRepositoryBoardInitializer());
    }

    @Test
    @DisplayName("ReadyState는 Board를 초기화하고 RunningState로 진입")
    void start() {
        assertThat(readyState.start())
                .isInstanceOf(RunningState.class);
    }
    
    @Test
    @DisplayName("ReadyState는 move 메서드를 지원하지 않음")
    void move() {
        assertThatThrownBy(() -> readyState.move(Position.of("a1"), Position.of("a2")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
    
    @Test
    @DisplayName("ReadyState는 end 메서드를 지원하지 않음")
    void end() {
        assertThatThrownBy(() -> readyState.end())
                .isInstanceOf(UnsupportedOperationException.class);

    }

    @Test
    @DisplayName("ReadyState는 getBoard 메서드를 지원하지 않음")
    void getBoard() {
        assertThatThrownBy(() -> readyState.getBoard())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}