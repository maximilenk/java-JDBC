package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime dateAndTime;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime dateAndTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateAndTime = dateAndTime;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        final Message message = (Message) other;
        if (!Objects.equals(message.dateAndTime, this.dateAndTime)) {
            return false;
        }

        if (!Objects.equals(message.author, this.author)) {
            return false;
        }

        if (!Objects.equals(message.room, this.room)) {
            return false;
        }

        if (!Objects.equals(message.text, this.text)) {
            return false;
        }

        return this.id == message.id;
    }

    @Override
    public String toString() {
        return "Message: id = "
                + this.id + ", author = "
                + this.author + ", room = "
                + this.room + ", text =  "
                + this.text + ", dateAndTime = "
                + dateFormat.format(dateAndTime);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id.hashCode();
        hash = 31 * hash + author.hashCode();
        hash = 31 * hash + room.hashCode();
        hash = 31 * hash + text.hashCode();
        hash = 31 * hash + dateFormat.hashCode();
        return hash;
    }
}
