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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("При сохранении резюме должен увеличиваться размер Storage")
    void savingResumeShouldIncreaseOfSize() {
        Resume newResume = new Resume("uuid7");
        TEST_ARRAY_STORAGE.save(newResume);
        Assertions.assertEquals(7, TEST_ARRAY_STORAGE.size());
        DEFAULT_STORAGE.save(newResume);
        Assertions.assertEquals(6, DEFAULT_STORAGE.size());
    }

    @Test
    @DisplayName("При сохранении уже имеющегося резюме должен выкидывать исключение ExistStorageException")
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
    void save() throws StorageException {
        ExistStorageException exception = assertThrows(ExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.save(TEST_ARRAY[1]);
        }); // выбрасывает исключение, о том, что уже существует такой uuid
        assertEquals("Resume " + TEST_ARRAY[1].getUuid() + " already exists", exception.getMessage()); // Проверка на
        // существующий элемент [1] в массиве
        exception = assertThrows(ExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.save(TEST_ARRAY[2]);
        });
        assertEquals("Resume " + TEST_ARRAY[2].getUuid() + " already exists", exception.getMessage()); // Проверка на
        // существующий элемент [2] в массиве
        TEST_ARRAY_STORAGE.clear(); //Очистка массива для проверки повторного сохранения
        TEST_ARRAY_STORAGE.save(TEST_ARRAY[1]);
        Assertions.assertEquals(TEST_ARRAY[1].getUuid(), "uuid11");
        TEST_ARRAY_STORAGE.save(TEST_ARRAY[2]);
        Assertions.assertEquals(TEST_ARRAY[2].getUuid(), "uuid15");

    }

    @Test
    void get() throws StorageException {
        Assertions.assertEquals(DEFAULT_STORAGE.get("uuid1").getUuid(), TEST_ARRAY_STORAGE.get("uuid1").getUuid());//
        // после добавления идентичного элемента, равенство сохраняется
        DEFAULT_STORAGE.save(new Resume("uuid10"));
        Assertions.assertEquals(DEFAULT_STORAGE.get("uuid10").getUuid(), TEST_ARRAY_STORAGE.get("uuid10").getUuid()); // после добавления идентичного элемента, равенство сохраняется
        Assertions.assertNotEquals(DEFAULT_STORAGE.get("uuid10").getUuid(),
                TEST_ARRAY_STORAGE.get("uuid1").getUuid()); // разные обьекты не должны быть равны
        Assertions.assertNotEquals(DEFAULT_STORAGE.get("uuid10").getUuid(), null);
    }

    @Test
    void getAll() {
        Assertions.assertArrayEquals(DEFAULT_ARRAY, DEFAULT_STORAGE.getAll());
        assertFalse(Arrays.equals(DEFAULT_STORAGE.getAll(), TEST_ARRAY_STORAGE.getAll()));
        DEFAULT_STORAGE.delete("uuid1");
        assertFalse(Arrays.equals(DEFAULT_ARRAY, DEFAULT_STORAGE.getAll()));
        assertFalse(Arrays.equals(DEFAULT_ARRAY, null));
        assertFalse(Arrays.equals(DEFAULT_ARRAY, new Resume[]{}));
        assertFalse(Arrays.equals(DEFAULT_ARRAY, new Resume[10000]));
    }

    @Test
    void delete() throws StorageException {
        TEST_ARRAY_STORAGE.delete("uuid10");
        Assertions.assertEquals(TEST_ARRAY_STORAGE.size(), DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.delete("uuid1");
        Assertions.assertNotEquals(TEST_ARRAY_STORAGE.size(), DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.delete("uuid5");
        Assertions.assertNotEquals(TEST_ARRAY_STORAGE.size(), DEFAULT_STORAGE.size());
        NotExistStorageException exception = assertThrows(NotExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.delete("uuid10");
        });
        assertEquals("Resume uuid10 not exist", exception.getMessage());
        exception = assertThrows(NotExistStorageException.class, () -> {
            DEFAULT_STORAGE.delete("uuid5");
        });
        assertEquals("Resume uuid5 not exist", exception.getMessage());
    }

    void update() {
        TEST_ARRAY_STORAGE.update(TEST_ARRAY[1]);
    }
}
