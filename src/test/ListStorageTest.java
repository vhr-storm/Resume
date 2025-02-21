package test;

import functional.AbstractStorage;
import functional.StorageFactory;

public class ListStorageTest extends AbstractStorageTest {
    @Override
    AbstractStorage getStorage() {
        return StorageFactory.createStorage(StorageFactory.StorageType.LIST_STORAGE);
    }

}
