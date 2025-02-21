package test;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import functional.AbstractStorage;
import model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractStorageTest {


    abstract AbstractStorage getStorage();

    // Наборы тестовых резюме
    protected static final Resume[] TEST_RESUMES = {
            new Resume("uuid10"),
            new Resume("uuid11"),
            new Resume("uuid15"),
            new Resume("uuid14"),
            new Resume("uuid9"),
    };


    protected AbstractStorage TEST_STORAGE = getStorage();

    @BeforeEach
    public void setUp() {
        // Очистка перед каждым тестом
        TEST_STORAGE.clear();
        // Заполняем основное хранилище тестовыми данными
        for (Resume r : TEST_RESUMES) {
            TEST_STORAGE.save(r);
        }

    }

    @Test
    public void testClear() {
        TEST_STORAGE.clear();
        assertEquals(0, TEST_STORAGE.size(), "После clear() хранилище должно быть пустым");
    }

    @Test
    public void testSave() {
        Resume newResume = new Resume("uuid_new");
        TEST_STORAGE.save(newResume);

        assertEquals(newResume.getUuid(), TEST_STORAGE.get(newResume.getUuid()).getUuid());
        assertEquals(TEST_RESUMES.length + 1, TEST_STORAGE.size(), "Размер хранилища увеличился после сохранения");
    }

    @Test
    public void testSaveExisting() {
        // Попытка сохранить уже существующее резюме (например, с uuid "uuid1")
        Resume existing = new Resume("uuid9");
        ExistStorageException exception = assertThrows(ExistStorageException.class, () -> TEST_STORAGE.save(existing));
        assertEquals("Resume " + existing.getUuid() + " already exists", exception.getMessage());
    }


    @Test
    public void testDelete() {
        int initialSize = TEST_STORAGE.size();
        TEST_STORAGE.delete("uuid10");
        assertEquals(initialSize - 1, TEST_STORAGE.size(), "Размер хранилища должен уменьшиться после удаления");
        assertThrows(NotExistStorageException.class, () -> TEST_STORAGE.get("uuid10"));
    }

    @Test
    public void testDeleteNotExisting() {
        NotExistStorageException exception = assertThrows(NotExistStorageException.class, () -> TEST_STORAGE.delete(
                "nonexistent"));
        assertEquals("Resume nonexistent not exist", exception.getMessage());
    }

    @Test
    public void testUpdate() {
        // Создаём новое резюме с тем же uuid, что и у одного из сохранённых
        Resume updated = new Resume("uuid11");
        // При необходимости можно изменить и другие поля резюме
        TEST_STORAGE.update(updated);
        assertEquals(updated, TEST_STORAGE.get("uuid11"), "Резюме не было обновлено");
    }

    @Test
    public void testUpdateNotExisting() {
        Resume nonExisting = new Resume("nonexistent");
        NotExistStorageException exception = assertThrows(NotExistStorageException.class,
                () -> TEST_STORAGE.update(nonExisting));
        assertEquals("Resume nonexistent not exist", exception.getMessage());
    }

    @Test
    public void testGet() {
        Resume testResume = new Resume("uuid_new");
        NotExistStorageException ex = assertThrows(NotExistStorageException.class, () -> {
            TEST_STORAGE.get(testResume.getUuid());
        });
        assertEquals("Resume " + testResume.getUuid() + " not exist", ex.getMessage());
    }

    @Test
    public void testGetNotExisting() {
        NotExistStorageException exception = assertThrows(NotExistStorageException.class, () -> TEST_STORAGE.get(
                "nonexistent"));
        assertEquals("Resume nonexistent not exist", exception.getMessage());
    }

    @Test
    public void testGetAll() {
        Resume[] all = TEST_STORAGE.getAll();
        Resume[] expected = new Resume[TEST_RESUMES.length];
        System.arraycopy(TEST_RESUMES, 0, expected, 0, TEST_RESUMES.length);
        // Если реализация требует определённого порядка, можно отсортировать массивы
        Arrays.sort(all);
        Arrays.sort(expected);
        assertArrayEquals(expected, all, "Набор резюме, возвращаемых getAll(), не совпадает с ожидаемым");
    }

    @Test
    public void testSize() {
        assertEquals(TEST_RESUMES.length, TEST_STORAGE.size(), "Неверное значение size()");
    }
}