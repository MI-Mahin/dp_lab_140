import java.util.ArrayList;
import java.util.List;

public class BankCommandProcessor {
    private List<IBankCommand> commands = new ArrayList<>();

    public void executeCommand(IBankCommand command) {
        command.execute();
        commands.add(command);
    }
}
