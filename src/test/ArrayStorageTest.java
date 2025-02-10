package test;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {




    @Test
    void save() throws StorageException {
        ExistStorageException exception = assertThrows(ExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.save(TEST_ARRAY[1]);
        }); // выбрасывает исключение, о том, что уже существует такой uuid
        assertEquals("Resume " + TEST_ARRAY[1].getUuid() + " already exists", exception.getMessage()); // Проверка на существующий элемент [1] в массиве
        exception = assertThrows(ExistStorageException.class, () -> {
            TEST_ARRAY_STORAGE.save(TEST_ARRAY[2]);
        });
        assertEquals("Resume " + TEST_ARRAY[2].getUuid() + " already exists", exception.getMessage()); // Проверка на существующий элемент [2] в массиве
        TEST_ARRAY_STORAGE.clear(); //Очистка массива для проверки повторного сохранения
        TEST_ARRAY_STORAGE.save(TEST_ARRAY[1]);
        Assertions.assertEquals(TEST_ARRAY[1].getUuid(), "uuid11");
        TEST_ARRAY_STORAGE.save(TEST_ARRAY[2]);
        Assertions.assertEquals(TEST_ARRAY[2].getUuid(), "uuid15");

    }

    @Test
    void get() throws StorageException {
        Assertions.assertEquals(DEFAULT_STORAGE.get("uuid1").getUuid(),TEST_ARRAY_STORAGE.get("uuid1").getUuid());// после добавления идентичного элемента, равенство сохраняется
        DEFAULT_STORAGE.save(new Resume("uuid10"));
        Assertions.assertEquals(DEFAULT_STORAGE.get("uuid10").getUuid(),TEST_ARRAY_STORAGE.get("uuid10").getUuid()); // после добавления идентичного элемента, равенство сохраняется
        Assertions.assertNotEquals(DEFAULT_STORAGE.get("uuid10").getUuid(),TEST_ARRAY_STORAGE.get("uuid1").getUuid()); // разные обьекты не должны быть равны
        Assertions.assertNotEquals(DEFAULT_STORAGE.get("uuid10").getUuid(),null);
    }

    @Test
    void getAll() {
        Assertions.assertArrayEquals(DEFAULT_ARRAY,DEFAULT_STORAGE.getAll());
        assertFalse(Arrays.equals(DEFAULT_STORAGE.getAll(),TEST_ARRAY_STORAGE.getAll()));
        DEFAULT_STORAGE.delete("uuid1");
        assertFalse(Arrays.equals(DEFAULT_ARRAY,DEFAULT_STORAGE.getAll()));
        assertFalse(Arrays.equals(DEFAULT_ARRAY,null));
        assertFalse(Arrays.equals(DEFAULT_ARRAY,new Resume[]{}));
        assertFalse(Arrays.equals(DEFAULT_ARRAY,new Resume[10000]));
    }
    @Test
    void delete() throws StorageException {
        TEST_ARRAY_STORAGE.delete("uuid10");
        Assertions.assertEquals(TEST_ARRAY_STORAGE.size(),DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.delete("uuid1");
        Assertions.assertNotEquals(TEST_ARRAY_STORAGE.size(),DEFAULT_STORAGE.size());
        DEFAULT_STORAGE.delete("uuid5");
        Assertions.assertNotEquals(TEST_ARRAY_STORAGE.size(),DEFAULT_STORAGE.size());
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
