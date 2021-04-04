package io.github.robbietree.domain.service

import io.github.robbietree.domain.ItemRepository;
import spock.lang.Specification;

class AddServiceSpec extends Specification {
    def itemRepository = Mock(ItemRepository)

    def "should add item to repository"() {
        given:
            AddService addService = new AddService(itemRepository)
        when:
            addService.add("username", "content")
        then:
            1 * itemRepository.nextIndex("username")
            1 * itemRepository.save(_)
    }
}
