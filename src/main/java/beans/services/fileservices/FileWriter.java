package beans.services.fileservices;

import java.util.List;

public interface FileWriter<T> {

    String writeDocument(T document);

    String writeDocuments(List<T> documents);

    String generateFileName(T document);

}
