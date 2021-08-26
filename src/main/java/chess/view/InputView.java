package chess.view;

import chess.controller.dto.Command;
import chess.controller.dto.RequestDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final int COMMAND_INDEX = 0;
    public static final int PARAMETER_START_INDEX = 1;
    public static final String COMMAND_DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public RequestDto inputRequest() {
        System.out.println("명령어를 입력하세요>");
        String input = scanner.nextLine();
        List<String> commands = Arrays.asList(input.split(COMMAND_DELIMITER));
        Command command = Command.of(commands.get(COMMAND_INDEX));
        if (hasParams(commands)) {
            return new RequestDto(command, commands.subList(PARAMETER_START_INDEX, commands.size()));
        }
        return new RequestDto(command);
    }

    private boolean hasParams(List<String> commands) {
        return commands.size() > PARAMETER_START_INDEX;
    }

}
