package domain.piece;

import domain.player.Player;
import domain.position.Position;

import java.util.Map;

public interface PieceState {

    PieceState move(Position target, Map<Position, PieceDto> boardDto);

    Player getPlayer();

    String getFigure();

    double getPoint();
}
