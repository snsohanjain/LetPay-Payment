package com.letpay.axies;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.*;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentRestControllerTest {
    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase database;

    @Mock
    private MongoCollection<Document> collection;

    @InjectMocks
    private PaymentRestController paymentRestController;

    @Test
    public void testGetAllPayments() {
        // Mocking the mongo client, database, and collection
        Mockito.when(mongoClient.getDatabase(Mockito.anyString())).thenReturn(database);
        Mockito.when(database.getCollection(Mockito.anyString())).thenReturn(collection);

        // Mocking the find method to return a list of documents
        FindIterable<Document> iterable = Mockito.mock(FindIterable.class);
        Mockito.when(collection.find(Mockito.any(Document.class))).thenReturn(iterable);

        // Calling the method to test
        List<Document> payments = paymentRestController.getAllPayments();

        // Checking the returned documents
        assertEquals(createDocumentList(), payments);
    }

    private List<Document> createDocumentList() {
        // Create a sample list of documents for testing
        List<Document> documents = new ArrayList<>();
        Document doc1 = new Document("id", 1);
        doc1.put("localDateTime", "2023-04-17T12:34:56");
        documents.add(doc1);

        Document doc2 = new Document("id", 2);
        doc2.put("localDateTime", "2023-04-16T00:00:00");
        documents.add(doc2);
        return documents;
    }
}
