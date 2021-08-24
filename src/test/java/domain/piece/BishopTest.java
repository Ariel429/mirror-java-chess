package domain.piece;

import domain.player.Player;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @ParameterizedTest
    @ValueSource(strings = {"a2", "b3", "d5", "e6", "f7", "g8"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteBishop.move(Position.of(target), boardDto))
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

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A6:B5", "E6:D5", "G8:F7"}, delimiter = ':')
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath(String target, String path) {
        //given
        boardDto.put(Position.of(path), new PieceDto(Player.WHITE));

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 position입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "B3", "D5", "E6", "F7", "G8"})
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));

        //when //then
        assertThat(whiteBishop.move(Position.of(target), boardDto))
                .isInstanceOf(Bishop.class);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A6:B5", "E6:D5", "G8:F7"}, delimiter = ':')
    @DisplayName("진행 경로에 적군이 있는 경우 예외 발생")
    void enemyOnPath(String target, String path) {
        //given
        boardDto.put(Position.of(path), new PieceDto(Player.BLACK));
                
        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardDto))
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

    @ParameterizedTest
    @ValueSource(strings = {"b4", "d4", "c3", "c5" })
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));

        //when //then
        assertThatThrownBy(() -> whiteBishop.move(Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 position입니다.");
    }
}