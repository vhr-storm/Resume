package exception;

public class NotExistTypeStorageException extends StorageException {
    public NotExistTypeStorageException(String type) {
        super(STR."Unknown storage type \{type}", type);
    }
}
