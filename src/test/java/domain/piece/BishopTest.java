package domain.piece;

import domain.player.Player;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BishopTest {
    private PieceState whiteBishop;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteBishop = Bishop.of(Position.of("c4"), Player.WHITE);
        boardDto = new HashMap<>();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2", "b3", "d5", "e6", "f7", "g8"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteBishop.move(Position.of(target), boardDto))
                .isInstanceOf(Bishop.class);
    }
    
    @Test
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath() {
        //given
        boardDto.put(Position.of("d5"), new PieceDto(Player.WHITE));

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of("g8"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 position입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        //given
        boardDto.put(Position.of("g8"), new PieceDto(Player.BLACK));

        //when //then
        assertThat(whiteBishop.move(Position.of("g8"), boardDto))
                .isInstanceOf(Bishop.class);
    }
    
    @Test
    @DisplayName("진행 경로에 적군이 있는 경우 예외 발생")
    void enemyOnPath() {
        //given
        boardDto.put(Position.of("d5"), new PieceDto(Player.BLACK));
                
        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of("e6"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 position입니다.");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"b4", "d4", "c3", "c5" })
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String target) {
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 position입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        //given
        boardDto.put(Position.of("b4"), new PieceDto(Player.BLACK));

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of("b4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 position입니다.");
    }
}