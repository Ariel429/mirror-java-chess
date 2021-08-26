package chess.domain.board;

import chess.domain.piece.implementation.Bishop;
import chess.domain.piece.implementation.King;
import chess.domain.piece.implementation.Knight;
import chess.domain.piece.implementation.NotMovedPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.implementation.Queen;
import chess.domain.piece.implementation.Rook;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.function.BiFunction;

public enum InitialPieceRepository {

    BLACK_PAWN_1(NotMovedPawn::of, Team.BLACK, Position.of("A7")),
    BLACK_PAWN_2(NotMovedPawn::of, Team.BLACK, Position.of("B7")),
    BLACK_PAWN_3(NotMovedPawn::of, Team.BLACK, Position.of("C7")),
    BLACK_PAWN_4(NotMovedPawn::of, Team.BLACK, Position.of("D7")),
    BLACK_PAWN_5(NotMovedPawn::of, Team.BLACK, Position.of("E7")),
    BLACK_PAWN_6(NotMovedPawn::of, Team.BLACK, Position.of("F7")),
    BLACK_PAWN_7(NotMovedPawn::of, Team.BLACK, Position.of("G7")),
    BLACK_PAWN_8(NotMovedPawn::of, Team.BLACK, Position.of("H7")),
    BLACK_ROOK_1(Rook::of, Team.BLACK, Position.of("A8")),
    BLACK_KNIGHT_1(Knight::of, Team.BLACK, Position.of("B8")),
    BLACK_BISHOP_1(Bishop::of, Team.BLACK, Position.of("C8")),
    BLACK_QUEEN(Queen::of, Team.BLACK, Position.of("D8")),
    BLACK_KING(King::of, Team.BLACK, Position.of("E8")),
    BLACK_BISHOP_2(Bishop::of, Team.BLACK, Position.of("F8")),
    BLACK_KNIGHT_2(Knight::of, Team.BLACK, Position.of("G8")),
    BLACK_ROOK_2(Rook::of, Team.BLACK, Position.of("H8")),
    WHITE_PAWN_1(NotMovedPawn::of, Team.WHITE, Position.of("A2")),
    WHITE_PAWN_2(NotMovedPawn::of, Team.WHITE, Position.of("B2")),
    WHITE_PAWN_3(NotMovedPawn::of, Team.WHITE, Position.of("C2")),
    WHITE_PAWN_4(NotMovedPawn::of, Team.WHITE, Position.of("D2")),
    WHITE_PAWN_5(NotMovedPawn::of, Team.WHITE, Position.of("E2")),
    WHITE_PAWN_6(NotMovedPawn::of, Team.WHITE, Position.of("F2")),
    WHITE_PAWN_7(NotMovedPawn::of, Team.WHITE, Position.of("G2")),
    WHITE_PAWN_8(NotMovedPawn::of, Team.WHITE, Position.of("H2")),
    WHITE_ROOK_1(Rook::of, Team.WHITE, Position.of("A1")),
    WHITE_KNIGHT_1(Knight::of, Team.WHITE, Position.of("B1")),
    WHITE_BISHOP_1(Bishop::of, Team.WHITE, Position.of("C1")),
    WHITE_QUEEN(Queen::of, Team.WHITE, Position.of("D1")),
    WHITE_KING(King::of, Team.WHITE, Position.of("E1")),
    WHITE_BISHOP_2(Bishop::of, Team.WHITE, Position.of("F1")),
    WHITE_KNIGHT_2(Knight::of, Team.WHITE, Position.of("G1")),
    WHITE_ROOK_2(Rook::of, Team.WHITE, Position.of("H1"));

    private BiFunction<Position, Team, Piece> generator;
    private Team team;
    private Position position;

    InitialPieceRepository(BiFunction<Position, Team, Piece> generator, Team team, Position position) {
        this.generator = generator;
        this.team = team;
        this.position = position;
    }

    public Piece getInitialPiece() {
        return generator.apply(position, team);
    }

    public Position getPosition() {
        return position;
    }
}