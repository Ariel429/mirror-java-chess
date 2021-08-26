package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        InputView consoleInputView = new InputView();
        OutputView consoleOutputview = new OutputView();

        ChessController chessController = new ChessController(consoleInputView, consoleOutputview);
        chessController.run();

    }
}
