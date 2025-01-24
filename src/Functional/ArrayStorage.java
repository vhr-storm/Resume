package Functional;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private static int counterOfResume = 0;

    public void clear() {
        for (int i = 0; i < size(); i++) {
            this.storage[i] = null;
        }
        counterOfResume = 0;
    }

    public void save(Resume r) {
        if (counterOfResume != 10000) {
            this.storage[counterOfResume] = r;
            counterOfResume++;
        }
    }

    public Resume get(String uuid) {
        int foundID = indexOfResume(uuid);
        if (size() == 0) return null;
        if (foundID != -1) {
            return this.storage[foundID];
        }
        return null;
    }

    public int indexOfResume(String uuid) {
        int i = 0;
        for (; i < size(); i++) {
            if (uuid.equals(this.storage[i].toString())) {
                return i;
            }
        }
        return -1;
    }

    public void delete(String uuid) {
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
    public Resume[] getAll() {
        Resume[] outputAll = new Resume[counterOfResume];
        for (int i = 0; i < size(); i++) {
            outputAll[i] = this.storage[i];
        }
        return outputAll;
    }

    public int size() {
        return counterOfResume;
    }
}
