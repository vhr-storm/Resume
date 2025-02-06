package Functional;
import Exception.*;

public class StorageFactory {
    public enum StorageType{
        SORTED_ARRAY_STORAGE,
        ARRAY_STORAGE,
        NO_TYPE
    }
    public static   AbstractArrayStorage createStorage(StorageType type) {
        AbstractArrayStorage typeStorage = null;
        switch (type){
            case ARRAY_STORAGE -> typeStorage = new ArrayStorage();
            case SORTED_ARRAY_STORAGE -> typeStorage = new SortedArrayStorage();
            default -> throw new NotExistTypeStorageException(type.toString());
        }
        return typeStorage;
    }
}
