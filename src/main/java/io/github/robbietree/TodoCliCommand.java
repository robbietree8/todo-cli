package io.github.robbietree;

import io.github.robbietree.command.AddCommand;
import io.github.robbietree.command.DoneCommand;
import io.github.robbietree.command.ExportCommand;
import io.github.robbietree.command.ImportCommand;
import io.github.robbietree.command.InitCommand;
import io.github.robbietree.command.ListCommand;
import io.github.robbietree.command.LoginCommand;
import io.github.robbietree.command.LogoutCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;

@Command(name = "todo", description = "todo commands", mixinStandardHelpOptions = true,
        subcommands = {
                AddCommand.class,
                ListCommand.class,
                DoneCommand.class,
                LoginCommand.class,
                LogoutCommand.class,
                ExportCommand.class,
                ImportCommand.class,
                InitCommand.class})
public class TodoCliCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("todo cli");
    }

    public static void main(String[] args) {
        PicocliRunner.run(TodoCliCommand.class, args);

        System.exit(0);
    }
}
