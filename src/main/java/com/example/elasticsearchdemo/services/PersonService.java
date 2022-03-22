package com.example.elasticsearchdemo.services;

import com.example.elasticsearchdemo.document.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    void save(final Person person);
    Person findById(final String id);
}
