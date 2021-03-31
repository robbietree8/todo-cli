package io.github.robbietree.domain;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Item {
    private Long itemIndex;
    private String content;
    private String username;
    private ItemStatusEnum status;

    public Item() {
    }

    public Item(Long itemIndex, ItemStatusEnum status, String username, String content) {
        this.itemIndex = itemIndex;
        this.status = status;
        this.username = username;
        this.content = content;
    }

    public static Item create(Long index, String username, String content) {
        return new Item(index, ItemStatusEnum.INIT, username, content);
    }

    public Long getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(Long itemIndex) {
        this.itemIndex = itemIndex;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "Item{" +
                "itemIndex=" + itemIndex +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
