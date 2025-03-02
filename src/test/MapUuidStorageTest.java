package test;

import functional.AbstractStorage;
import functional.StorageFactory;

public class MapUuidStorageTest extends AbstractStorageTest {


    @Override
    AbstractStorage getStorage() {
        return StorageFactory.createStorage(StorageFactory.StorageType.MAP_UUID_STORAGE);
    }


}
