package test;

import functional.AbstractStorage;
import functional.StorageFactory;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {


    @Override
    AbstractStorage getStorage() {
        return StorageFactory.createStorage(StorageFactory.StorageType.SORTED_ARRAY_STORAGE);
    }

    @Override
    AbstractStorage getOverflowStorage() {
        return StorageFactory.createStorage(StorageFactory.StorageType.SORTED_ARRAY_STORAGE);
    }
}
