package domain.piece;

import domain.BoardState;
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

class QueenTest {
    private PieceState whiteQueen;
    private Map<Position, PieceDto> boardDto;
    private BoardState boardState;

    @BeforeEach
    void setUp() {
        whiteQueen = Queen.of(Position.of("C4"), Player.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.WHITE));

        //when //then
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A4:B4", "A6:B5", "C6:C5", "E6:D5", "E4:D4", "E2:D3", "C2:C3"}, delimiter = ':')
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath(String target, String path) {
        //given
        boardDto.put(Position.of(path), new PieceDto(Player.WHITE));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThat(whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A4:B4", "A6:B5", "C6:C5", "E6:D5", "E4:D4", "E2:D3", "C2:C3"}, delimiter = ':')
    @DisplayName("진행 경로에 적군이 있는 경우 예외 발생")
    void enemyOnPath(String target, String path) {
        //given
        boardDto.put(Position.of(path), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "A3", "A5", "B2", "E5"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteQueen.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "A3", "A5", "B2", "E5"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        //given
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);

        //when //then
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }


}