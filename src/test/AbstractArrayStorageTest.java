package test;

import exception.StorageException;
import functional.AbstractArrayStorage;
import functional.StorageFactory;
import model.Resume;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractArrayStorageTest {
    static AbstractArrayStorage DEFAULT_STORAGE = StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);
    static AbstractArrayStorage TEST_ARRAY_STORAGE = StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);

    static AbstractArrayStorage SORTED_ARRAY_STORAGE = StorageFactory.createStorage(StorageFactory.StorageType.SORTED_ARRAY_STORAGE);
    static Resume[] TEST_ARRAY = {new Resume("uuid10"), new Resume("uuid11"), new Resume("uuid15"), new Resume("uuid14"), new Resume("uuid9")};
    static Resume[] DEFAULT_ARRAY = {new Resume("uuid1"), new Resume("uuid5"), new Resume("uuid2"), new Resume("uuid3"), new Resume("uuid6")};
    static Resume[] OVERFLOW_ARRAY = new Resume[10000];
    @BeforeAll
    public static void setUp() {
        for (Resume resume : DEFAULT_ARRAY) {
           SORTED_ARRAY_STORAGE.save(resume);
            DEFAULT_STORAGE.save(resume);

        }
        for (Resume r : TEST_ARRAY){
            TEST_ARRAY_STORAGE.save(r);
        }
    }

    @Test
    void clear() {
        DEFAULT_STORAGE.clear();
        assertEquals(0, DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.clear();
        DEFAULT_STORAGE.clear();
        assertEquals(0, DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.clear();
        assertEquals(0, DEFAULT_STORAGE.size());
    }

    @Test
    void save() throws StorageException {

    }

    @Test
    void get() throws StorageException {
        DEFAULT_STORAGE.get("uuid1");
    }

    @Test
    void delete() throws StorageException {
    }

    @Test
    void getAll() {
    }

    @Test
    void size() {
        assertEquals(5, DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.delete(DEFAULT_ARRAY[4].getUuid());
        assertEquals(4, DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.clear();
        assertEquals(0, DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.save(DEFAULT_ARRAY[1]);
        assertEquals(1, DEFAULT_STORAGE.size());
    }

    @Test
    void update() {
    }
}