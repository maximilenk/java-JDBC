package edu.school21.chat.repositories;

import java.util.List;

import edu.school21.chat.models.User;

public interface UsersRepository {
    List<User> findAll(int page, int size);
}