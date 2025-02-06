package Functional;

import Exception.*;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected final int MAXIMUM_SIZE = 10000;
    protected final Resume[] storage = new Resume[MAXIMUM_SIZE];
    protected static int counterOfResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, counterOfResume, null);
        counterOfResume = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (counterOfResume != MAXIMUM_SIZE) {
            insertElement(r, index);
            counterOfResume++;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (size() == 0) return null;
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return this.storage[index];

    }

    public abstract int getIndex(String uuid);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[counterOfResume - 1] = null;
            counterOfResume--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, counterOfResume);
    }

    public int size() {
        return counterOfResume;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            this.storage[index] = r;
        }
    }

}
