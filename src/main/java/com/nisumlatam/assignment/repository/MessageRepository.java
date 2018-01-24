package com.nisumlatam.assignment.repository;

import com.nisumlatam.assignment.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
