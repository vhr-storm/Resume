package functional;

import exception.*;
import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void insertElement(Resume r, int index) throws StorageException {
        int insertId = -index - 1;
        if (insertId < 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            System.arraycopy(storage, insertId, storage, insertId + 1, counterOfResume - insertId);
            storage[insertId] = r;
        }
    }

    @Override
    protected void fillDeletedElement(int index) {
        int move = counterOfResume - index - 1;
        if (move > 0) {
            System.arraycopy(storage, index + 1, storage, index, move);
        }
    }

    @Override
    public Object getSearchKey(String uuid) {
        Resume key = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, counterOfResume, key);
    }


}




