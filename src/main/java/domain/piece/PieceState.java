package domain.piece;

import domain.BoardState;
import domain.player.Player;
import domain.position.Position;

import java.util.Map;

public interface PieceState {

    PieceState move(Position target, BoardState boardState);

    Player getPlayer();

    String getFigure();

    double getPoint();
}
