package domain.piece;

import domain.player.Player;
import domain.position.Position;

public class Knight extends Piece{

    protected Knight(Position position, Player player) {
        super(position, player);
    }

    @Override
    public void move(Position target) {
        if (!canMove(target)) {
            throw new IllegalArgumentException("움직일 수 없는 position입니다");
        }
        this.position = target;
    }

    private boolean canMove(Position target) {
        int fileDifference = position.getFileDifference(target);
        int rankDifference = position.getRankDifference(target);

        return fileDifference + rankDifference == 3 && fileDifference != 0 && rankDifference != 0;
    }
}
