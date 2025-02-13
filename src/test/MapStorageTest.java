package test;

import functional.AbstractStorage;
import functional.StorageFactory;

public class MapStorageTest extends AbstractStorageTest {


    @Override
    AbstractStorage getStorage() {
        return StorageFactory.createStorage(StorageFactory.StorageType.MAP_STORAGE);
    }


}
