package test;

import functional.AbstractStorage;
import functional.StorageFactory;

public class MapResumeStorageTest extends AbstractStorageTest{
    @Override
    AbstractStorage getStorage() {
        return StorageFactory.createStorage(StorageFactory.StorageType.MAP_RESUME_STORAGE);
    }
}
