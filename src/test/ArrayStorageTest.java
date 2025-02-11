package test;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import functional.AbstractArrayStorage;
import functional.StorageFactory;
import model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    protected static AbstractArrayStorage DEFAULT_STORAGE =
            StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);
    protected static AbstractArrayStorage TEST_ARRAY_STORAGE =
            StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);

    protected static AbstractArrayStorage TEST_OVERFLOW_ARRAY_STORAGE =
            StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);

    protected static Resume[] TEST_ARRAY = {
            new Resume("uuid10"),
            new Resume("uuid11"),
            new Resume("uuid15"),
            new Resume("uuid14"),
            new Resume("uuid9"),
            new Resume("uuid1")
    };

    protected static Resume[] DEFAULT_ARRAY = {
            new Resume("uuid1"),
            new Resume("uuid5"),
            new Resume("uuid2"),
            new Resume("uuid3"),
            new Resume("uuid6")
    };
    static final int MAXIMUM_SIZE = 10000;
    protected static Resume[] OVERFLOW_ARRAY = new Resume[MAXIMUM_SIZE];

    @BeforeEach
    public void setUp() {
        TEST_ARRAY_STORAGE.clear();
        DEFAULT_STORAGE.clear();
        for (Resume r : TEST_ARRAY) {
            TEST_ARRAY_STORAGE.save(r);
        }
        for (Resume resume : DEFAULT_ARRAY) {
            DEFAULT_STORAGE.save(resume);
        }
    }

    @Test
    @DisplayName("При сохранении резюме оно должно сохраняться")
    void savingResumeShouldSave(){
        TEST_ARRAY_STORAGE.save(new Resume("uuid111"));
        Assertions.assertEquals(new Resume("uuid111").getUuid(),TEST_ARRAY_STORAGE.get("uuid111").getUuid());
        TEST_ARRAY_STORAGE.save(new Resume("uuid151"));
        Assertions.assertEquals(new Resume("uuid151").getUuid(),TEST_ARRAY_STORAGE.get("uuid151").getUuid());
    }
    @Test
    @DisplayName("При сохранении резюме должен увеличиваться размер Storage")
    void savingResumeShouldIncreaseOfSize() {
        Resume newResume = new Resume("uuid7");
        TEST_ARRAY_STORAGE.save(newResume);
        Assertions.assertEquals(7, TEST_ARRAY_STORAGE.size());
        DEFAULT_STORAGE.save(newResume);
        Assertions.assertEquals(6, DEFAULT_STORAGE.size());
    }

    @Test
    @DisplayName("При сохранении, существующего резюме, должен выкидывать исключение ExistStorageException")
    void savingExistingResumeShouldThrowException() {
        Resume newResume = new Resume("uuid1");
        ExistStorageException existStorageException = assertThrows(ExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.save(newResume);
        });
        assertEquals("Resume " + newResume.getUuid() + " already exists", existStorageException.getMessage());
        existStorageException = assertThrows(ExistStorageException.class, () -> {
            DEFAULT_STORAGE.save(newResume);
        });
        assertEquals("Resume " + newResume.getUuid() + " already exists", existStorageException.getMessage());
    }

    @Test
    @DisplayName("При сохранении резюме в заполненный Storage должен выкидывать исключение StorageException")
    void savingOverflowResumeShouldThrowException() {
        for (int i = 0; i < MAXIMUM_SIZE; i++) {
            OVERFLOW_ARRAY[i] = new Resume();
        }
        for (Resume r : OVERFLOW_ARRAY) {
            TEST_OVERFLOW_ARRAY_STORAGE.save(r);
        }
        Resume newResume = new Resume("uuid1");
        StorageException storageException = assertThrows(StorageException.class, () -> {
            TEST_OVERFLOW_ARRAY_STORAGE.save(newResume);
        });
        assertEquals("Storage overflow", storageException.getMessage());
    }

    @Test
    @DisplayName("При удалении резюме, Storage должен уменьшать size()")
    void deleteResumeShouldDecreaseOfSize(){
        int size = DEFAULT_ARRAY.length - 1;
        TEST_ARRAY_STORAGE.delete("uuid10");
        assertEquals(5,TEST_ARRAY_STORAGE.size());
        for(Resume r: DEFAULT_ARRAY){
            DEFAULT_STORAGE.delete(r.getUuid());
            assertEquals(size,DEFAULT_STORAGE.size());
            size--;
        }
    }

    @Test
    @DisplayName("При удалении удаленного резюме должен выкидывать исключение NotExistStorageException")
    void deletingNotExistingResumeShouldThrowException() {
        NotExistStorageException notExistStorageException = assertThrows(NotExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.delete("uuid11555");
        });
        assertEquals("Resume uuid11555 not exist", notExistStorageException.getMessage());
        notExistStorageException = assertThrows(NotExistStorageException.class, () -> {
            DEFAULT_STORAGE.delete("uuid55");
        });
        assertEquals("Resume uuid55 not exist", notExistStorageException.getMessage());
    }

    @Test
    @DisplayName("При взятии, существующего резюме, должен его брать")
    void gettingExistingResumeShouldGetIt(){
        Assertions.assertEquals(new Resume("uuid10").getUuid(),TEST_ARRAY_STORAGE.get("uuid10").getUuid());
        Assertions.assertEquals(new Resume("uuid6").getUuid(),DEFAULT_STORAGE.get("uuid6").getUuid());
    }

    @Test
    @DisplayName("При взятии, несуществующего резюме, должен выбрасывать NotExistStorageException")
    void gettingNotExistingResumeShouldThrowException() {
        NotExistStorageException notExistStorageException = assertThrows(NotExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.get("uuid1666");
        });
        assertEquals("Resume uuid1666 not exist", notExistStorageException.getMessage());
    }

}
