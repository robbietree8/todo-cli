package io.github.robbietree.domain;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Item {
    private Long index;
    private String content;
    private ItemStatusEnum status;

    public Item(Long index, ItemStatusEnum status, String content) {
        this.index = index;
        this.status = status;
        this.content = content;
    }

    public static Item create(Long index, String content) {
        return new Item(index, ItemStatusEnum.INIT, content);
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ItemStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ItemStatusEnum status) {
        this.status = status;
    }

    public void markAsDone() {
        setStatus(ItemStatusEnum.DONE);
    }

    public boolean isDone() {
        return status == ItemStatusEnum.DONE;
    }

    public boolean notDone() {
        return !isDone();
    }
}
