package functional;

import exception.*;

public class StorageFactory {
    public enum StorageType {
        SORTED_ARRAY_STORAGE,
        ARRAY_STORAGE,
        MAP_STORAGE,
        LIST_STORAGE,
        NO_TYPE
    }

    public static AbstractStorage createStorage(StorageType type) {
        AbstractStorage typeStorage = null;
        switch (type) {
            case ARRAY_STORAGE -> typeStorage = new ArrayStorage();
            case SORTED_ARRAY_STORAGE -> typeStorage = new SortedArrayStorage();
            case MAP_STORAGE -> typeStorage = new MapStorage();
            case LIST_STORAGE -> typeStorage = new ListStorage();
            default -> throw new NotExistTypeStorageException(type.toString());
        }
        return typeStorage;
    }
}
