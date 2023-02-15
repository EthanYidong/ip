package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.arg.TaskIndexArgumentValidator;
import com.ethanyidong.bunny.task.Task;

public class DeleteTaskCommand extends ExecutableCommand {
    private int taskIndex;

    @Override
    public CommandValidator[] validators() {
        CommandValidator markIndexValidator =
                new PositionalArgumentCommandValidator(new TaskIndexArgumentValidator());
        return new CommandValidator[]{markIndexValidator};
    }

    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.taskIndex = Integer.parseInt(command.getPositionalArgument()) - 1;
    }

    public void execute(BunnySession bunny) {
        Task deletedTask = bunny.getTask(this.taskIndex);
        bunny.deleteTask(this.taskIndex);
        bunny.printMessage("Noted. I've removed this task:\n\t" + deletedTask + "\nNow you have " +
                bunny.numTasks() + " tasks in the list.");
    }
}
