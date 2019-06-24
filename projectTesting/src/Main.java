import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
public class Main {
    public static void main(String[] args) {
        System.out.println("ok");

        MongoClient mongo = new MongoClient("localhost",27017);

        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser","myDb","password".toCharArray());

        Document document = new Document("title", "MongoDB")
                .append("id", 1)
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.tutorialspoint.com/mongodb/")
                .append("by", "tutorials point");
        collection.insertOne(document);
        System.out.println("Document inserted successfully");
    }
}
