package domain;

import domain.controller.ChessController;
import domain.view.InputView;
import domain.view.OutputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        InputView consoleInputView = new InputView();
        OutputView consoleOutputview = new OutputView();

        ChessController chessController = new ChessController(consoleInputView, consoleOutputview);
        chessController.run();

    }
}
