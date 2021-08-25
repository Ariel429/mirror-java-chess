package domain.view;

import domain.controller.dto.ResponseDto;
import domain.player.Player;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.sql.SQLOutput;
import java.util.Map;

public class OutputView {

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printResponse(ResponseDto responseDto) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        Map<Position, String> board = responseDto.getBoard();

        for (Rank rank : Rank.values()) {
            sb.append(getRankString(board, rank));
            sb.append(" (rank " + rank.getRank() + ")");
            sb.append("\n");
        }

        sb.append(getFileNames());
        sb.append("\n");

        System.out.println(sb.toString());
    }

    private String getRankString(Map<Position, String> board, Rank rank) {
        StringBuilder sb = new StringBuilder();
        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            String piece = getPiece(board, position);
            sb.append("[" + piece + "]");
        }
        return sb.toString();
    }

    private String getPiece(Map<Position, String> board, Position position) {
        String piece = board.get(position);
        if (board.get(position) == null) {
            return " ";
        }
        return piece;
    }

    private String getFileNames() {
        StringBuilder sb = new StringBuilder();
        for (File file : File.values()) {
            sb.append(" " + file.toString() + " ");
        }
        return sb.toString();
    }

    public void printStatus(Map<Player, Double> status) {
        status.entrySet()
                .stream()
                .map(entry -> entry.getKey().toString() + " : " + entry.getValue())
                .forEach(System.out::println);
    }
}
