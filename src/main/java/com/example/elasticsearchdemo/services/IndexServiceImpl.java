package com.example.elasticsearchdemo.services;

        import com.example.elasticsearchdemo.helper.Indices;
        import com.example.elasticsearchdemo.helper.Util;
        import java.util.List;
        import org.elasticsearch.client.RestHighLevelClient;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
        import org.elasticsearch.client.RequestOptions;
        import org.elasticsearch.client.indices.CreateIndexRequest;
        import org.elasticsearch.client.indices.GetIndexRequest;
        import org.elasticsearch.common.xcontent.XContentType;
        import org.springframework.beans.factory.annotation.Autowired;

        import javax.annotation.PostConstruct;

//@Service
public class IndexServiceImpl implements IndexService {
    private static final Logger LOG = LoggerFactory.getLogger(IndexServiceImpl.class);
    private static final List<String> INDICES = List.of(Indices.VEHICLE_INDEX);
    private final RestHighLevelClient client;

    @Autowired
    public IndexServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndices() {
        recreateIndices(false);
    }

@Override
    public void recreateIndices(final boolean deleteExisting) {
        final String settings = Util.loadAsString("static/es-settings.json");

        if (settings == null) {
            LOG.error("Failed to load index settings");
            return;
        }

        for (final String indexName : INDICES) {
            try {
                final boolean indexExists = client
                        .indices()
                        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if (indexExists) {
                    if (!deleteExisting) {
                        continue;
                    }

                    client.indices().delete(
                            new DeleteIndexRequest(indexName),
                            RequestOptions.DEFAULT
                    );
                }

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);

                final String mappings = loadMappings(indexName);
                if (mappings != null) {
                    createIndexRequest.mapping(mappings, XContentType.JSON);
                }

                client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            } catch (final Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public String loadMappings(String indexName) {
        final String mappings = Util.loadAsString("static/mappings/" + indexName + ".json");
        if (mappings == null) {
            LOG.error("Failed to load mappings for index with name '{}'", indexName);
            return null;
        }

        return mappings;
    }
}
