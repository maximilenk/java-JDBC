package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User
{
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> rooms;

    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.rooms = rooms;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getRooms() {
        return rooms;
    }

    public void setRooms(List<Chatroom> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        final User user = (User) other;
        if (!Objects.equals(user.login, this.login)) {
            return false;
        }

        if (!Objects.equals(user.password, this.password)) {
            return false;
        }

        if (!Objects.equals(user.rooms, this.rooms)) {
            return false;
        }

        if (!Objects.equals(user.createdRooms, this.createdRooms)) {
            return false;
        }

        return this.id == user.id;
    }

    @Override
    public String toString() {
        return "User: id = "
                + this.id + ", login = "
                + this.login + ", password = "
                + this.password + ", created rooms =  "
                + this.createdRooms + ", used rooms = "
                + this.rooms;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id.hashCode();
        hash = 31 * hash + login.hashCode();
        hash = 31 * hash + password.hashCode();
        hash = 31 * hash + createdRooms.hashCode();
        hash = 31 * hash + rooms.hashCode();
        return hash;
    }
}
