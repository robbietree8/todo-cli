package io.github.robbietree.command;

import io.github.robbietree.domain.service.LoginService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;

@Command(name = "login", description = "login to system", mixinStandardHelpOptions = true)
public class LoginCommand implements Runnable {
    @Option(names = {"-u"}, description = "username")
    String username = "";

    @Option(names = {"-p"}, description = "password", interactive = true, arity = "0..1")
    String password = "";

    @Inject
    LoginService loginService;

    @Override
    public void run() {
        boolean success = loginService.login(username, password);
        if(success) {
            System.out.println("Login success!");
        }else {
            System.out.println("Login failed!");
        }
    }
}
