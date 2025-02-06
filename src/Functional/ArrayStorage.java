package Functional;

import model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[counterOfResume];
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */


}
