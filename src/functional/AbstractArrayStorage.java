package functional;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected final int MAXIMUM_SIZE = 10000;
    protected final Resume[] storage = new Resume[MAXIMUM_SIZE];
    protected int counterOfResume = 0;

    @Override
    protected Object getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return ((Integer) searchKey) >= 0;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (counterOfResume >= MAXIMUM_SIZE) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, (Integer) searchKey);
        counterOfResume++;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    void doDelete(Object searchKey) {
        int index = (Integer) searchKey;
        fillDeletedElement(index);
        storage[counterOfResume - 1] = null;
        counterOfResume--;
    }

    @Override
    protected List<Resume> getAllResumes() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, counterOfResume));
    }

    protected abstract int getIndex(String uuid);


    protected abstract void insertElement(Resume r, int index);

    // Метод корректировки массива при удалении элемента.
    protected abstract void fillDeletedElement(int index);

}
