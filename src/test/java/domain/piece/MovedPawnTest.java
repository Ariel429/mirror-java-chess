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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MovedPawnTest {

    private PieceState whiteMovedPawn;
    private Map<Position, PieceDto> boardDto;
    
    @BeforeEach
    void setUp() {
        whiteMovedPawn = MovedPawn.of(Position.of("b3"), Player.WHITE);
        boardDto = new HashMap<>();
    }
    
    @Test
    @DisplayName("진행하려는 타겟 위치에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        //given
        boardDto.put(Position.of("b4"), new PieceDto(Player.WHITE));

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("b4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy() {
        //given
        boardDto.put(Position.of("b4"), new PieceDto(Player.BLACK));

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("b4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteMovedPawn.move(Position.of("b4"), boardDto))
                .isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("대각선으로 진행할 때 진행하려는 타겟 위치에 적군이 있으면 이동 가능")
    void diagonalMoveToEnemy() {
        //given
        boardDto.put(Position.of("c4"), new PieceDto(Player.BLACK));

        //when //then
        assertThat(whiteMovedPawn.move(Position.of("c4"), boardDto))
                .isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"d5", "a4", "c4"})
    @DisplayName("진행 규칙에 어긋나는 타겟 위치로 이동하려고 하면 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(input), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }

    @Test
    @DisplayName("진행하려는 타겟에 적군이 있지만, 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        //given
        boardDto.put(Position.of("d4"), new PieceDto(Player.BLACK));

        //when //then
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("d4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 방향입니다.");
    }
}
