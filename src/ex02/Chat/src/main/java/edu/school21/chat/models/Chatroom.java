package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    List<Message> messageList;
    public Chatroom(Long id, String name, User owner, List<Message> messageList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setAuthor(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        final Chatroom chatroom = (Chatroom) other;
        if (!Objects.equals(chatroom.owner, this.owner)) {
            return false;
        }

        if (!Objects.equals(chatroom.name, this.name)) {
            return false;
        }

        if (!Objects.equals(chatroom.messageList, this.messageList)) {
            return false;
        }

        return Objects.equals(this.id, chatroom.id);
    }

    @Override
    public String toString() {
        return "Chatroom(id = "
                + this.id + ", name = "
                + this.name + ", owner = "
                + this.owner + ", messages = "
                + this.messageList + ")";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id.hashCode();
        hash = 31 * hash + name.hashCode();
        hash = 31 * hash + owner.hashCode();
        hash = 31 * hash + messageList.hashCode();
        return hash;
    }
}
