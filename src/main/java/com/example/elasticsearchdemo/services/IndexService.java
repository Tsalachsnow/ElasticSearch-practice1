package com.example.elasticsearchdemo.services;

import org.springframework.stereotype.Service;

@Service
public interface IndexService {
    void tryToCreateIndices();
    void recreateIndices(final boolean deleteExisting);
    String loadMappings(String indexName);
}
