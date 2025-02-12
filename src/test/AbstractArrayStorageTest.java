package test;

import exception.StorageException;
import functional.AbstractArrayStorage;
import functional.StorageFactory;
import model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractArrayStorageTest {
    static AbstractArrayStorage DEFAULT_STORAGE = StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);
    static AbstractArrayStorage TEST_ARRAY_STORAGE = StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);
    static AbstractArrayStorage SORTED_ARRAY_STORAGE = StorageFactory.createStorage(StorageFactory.StorageType.SORTED_ARRAY_STORAGE);
    static Resume[] TEST_ARRAY = {new Resume("uuid10"), new Resume("uuid11"), new Resume("uuid15"), new Resume("uuid14"), new Resume("uuid9"),new Resume("uuid1")};

    static Resume[] DEFAULT_ARRAY = {new Resume("uuid1"), new Resume("uuid5"), new Resume("uuid2"), new Resume("uuid3"), new Resume("uuid6")};
    static Resume[] OVERFLOW_ARRAY = new Resume[10000];

    @BeforeEach
    public void setUp() {
        TEST_ARRAY_STORAGE.clear();
        DEFAULT_STORAGE.clear();
        SORTED_ARRAY_STORAGE.clear();
        for (Resume r : TEST_ARRAY){
            TEST_ARRAY_STORAGE.save(r);
        }
        for (Resume resume : DEFAULT_ARRAY) {
            SORTED_ARRAY_STORAGE.save(resume);
            DEFAULT_STORAGE.save(resume);
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
        Assertions.assertEquals(DEFAULT_STORAGE.get("uuid1").getUuid(), SORTED_ARRAY_STORAGE.get("uuid1").getUuid()); // одинаковые элементы в разных массивах должны быть равны
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
        Assertions.assertNotEquals(1, DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.save(DEFAULT_ARRAY[1]);
        assertEquals(1, DEFAULT_STORAGE.size());
        assertEquals(5, SORTED_ARRAY_STORAGE.size());
        SORTED_ARRAY_STORAGE.delete(DEFAULT_ARRAY[4].getUuid());
        assertEquals(4, SORTED_ARRAY_STORAGE.size());
        SORTED_ARRAY_STORAGE.clear();
        assertEquals(0, SORTED_ARRAY_STORAGE.size());
        Assertions.assertNotEquals(1, SORTED_ARRAY_STORAGE.size());
    }

    @Test
    void update() {
    }
}