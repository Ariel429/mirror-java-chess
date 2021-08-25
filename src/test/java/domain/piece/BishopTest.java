package domain.piece;

import domain.player.Player;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    private PieceState whiteBishop;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteBishop = Bishop.of(Position.of("c4"), Player.WHITE);
        boardDto = new HashMap<>();
    }

    @Test
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteBishop.move(Position.of("d5"), boardDto))
                .isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2", "b3", "d5", "e6", "f7", "g8"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.WHITE));

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath() {
        //given
        boardDto.put(Position.of("b5"), new PieceDto(Player.WHITE));

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of("a6"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        //given
        boardDto.put(Position.of("d5"), new PieceDto(Player.BLACK));

        //when //then
        assertThat(whiteBishop.move(Position.of("d5"), boardDto))
                .isInstanceOf(Bishop.class);
    }
    
    @Test
    @DisplayName("진행 경로에 적군이 있는 경우 이동 불가")
    void enemyOnPath() {
        //given
        boardDto.put(Position.of("b3"), new PieceDto(Player.BLACK));
                
        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of("a2"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 장애물이 있습니다.");
    }
    
    @Test
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException() {
        assertThatThrownBy(() -> whiteBishop.move(Position.of("e5"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        //given
        boardDto.put(Position.of("c5"), new PieceDto(Player.BLACK));

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of("c5"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }
}