package test;

import exception.StorageException;
import functional.AbstractStorage;
import model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    abstract AbstractStorage getOverflowStorage();

    protected static final int MAXIMUM_SIZE = 10000;
    protected static final Resume[] OVERFLOW_ARRAY = new Resume[MAXIMUM_SIZE];
    protected AbstractStorage TEST_OVERFLOW_STORAGE = getOverflowStorage();

    @Override
    @BeforeEach
    public void setUp() {
        // Очистка перед каждым тестом
        TEST_STORAGE.clear();
        TEST_OVERFLOW_STORAGE.clear();
        // Заполняем основное хранилище тестовыми данными
        for (Resume r : TEST_RESUMES) {
            TEST_STORAGE.save(r);
        }

    }

    @Test
    @Disabled("Отключено после проведенных тестов, заполнение занимает много времени")
    public void testSaveOverflow() {
        // Заполняем переполненное хранилище до предела
        for (int i = 0; i < MAXIMUM_SIZE; i++) {
            OVERFLOW_ARRAY[i] = new Resume();
        }
        for (Resume r : OVERFLOW_ARRAY) {
            TEST_OVERFLOW_STORAGE.save(r);
        }
        Resume newResume = new Resume("uuid_overflow");
        StorageException exception = assertThrows(StorageException.class, () -> TEST_OVERFLOW_STORAGE.save(newResume));
        assertEquals("Storage overflow", exception.getMessage());
    }
}
