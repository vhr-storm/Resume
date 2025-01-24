package Functional;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private static int counterOfResume = 0;

    void clear() {
        for (int i = 0; i < size(); i++) {
            this.storage[i] = null;
        }
        counterOfResume = 0;
    }

    void save(Resume r) {
        if (counterOfResume != 10000) {
            this.storage[counterOfResume] = r;
            counterOfResume++;
        }
    }

    Resume get(String uuid) {
        int foundID = indexOfResume(uuid);
        if (size() == 0) return null;
        if (foundID != -1) {
            return this.storage[foundID];
        }
        return null;
    }

    int indexOfResume(String uuid) {
        int i = 0;
        for (; i < size(); i++) {
            if (uuid.equals(this.storage[i].toString())) {
                return i;
            }
        }
        return -1;
    }

    void delete(String uuid) {
        int foundID = indexOfResume(uuid);
        if (foundID != -1) {
            this.storage[foundID] = null;
            for (int i = foundID; i < size(); i++) {
                if ((this.storage[i] == null) && (this.storage[i + 1] != null)) {
                    Resume tmp = this.storage[i];
                    this.storage[i] = this.storage[i + 1];
                    this.storage[i + 1] = tmp;
                }
            }
            counterOfResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (counterOfResume == 0) {
            return null;
        }
        Resume[] outputAll = new Resume[counterOfResume];
        for (int i = 0; i < size(); i++) {
            outputAll[i] = this.storage[i];
        }
        return outputAll;
    }

    int size() {
        return counterOfResume;
    }
}
