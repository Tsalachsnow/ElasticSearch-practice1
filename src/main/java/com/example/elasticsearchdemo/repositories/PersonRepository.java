package com.example.elasticsearchdemo.repositories;

import com.example.elasticsearchdemo.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {
}
