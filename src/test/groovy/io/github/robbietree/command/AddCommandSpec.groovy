package io.github.robbietree.command

import io.github.robbietree.domain.SessionRepository
import io.github.robbietree.domain.service.AddService
import io.github.robbietree.infra.FileItemRepository
import picocli.CommandLine
import spock.lang.Specification

class AddCommandSpec extends Specification {
    SessionRepository sessionRepository = Mock(SessionRepository)

    def "should add item successfully"() {
        given:
            sessionRepository.getCurrentUser() >> "mockUser"
            AddCommand addCommand = new AddCommand(sessionRepository, new AddService(new FileItemRepository()))
            CommandLine command = new CommandLine(addCommand)
            StringWriter sw = new StringWriter()
            command.setOut(new PrintWriter(sw))
        when:
            int exitCode = command.execute("just do it")
        then:
            exitCode == 0
            sw.toString().contains("just do it")
    }
}
