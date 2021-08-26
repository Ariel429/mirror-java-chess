package chess;

import chess.controller.ChessController;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        InputView consoleInputView = new InputView();
        OutputView consoleOutputview = new OutputView();
        ChessController chessController = new ChessController();

        consoleOutputview.printInitialMessage();
        while (!chessController.isEnd()) {
            try {
                RequestDto requestDto = consoleInputView.inputRequest();
                ResponseDto responseDto = chessController.run(requestDto);
                consoleOutputview.printResponse(responseDto);
            } catch (IllegalArgumentException ie) {
                System.out.println(ie.getMessage());
            }
        }
    }
}
