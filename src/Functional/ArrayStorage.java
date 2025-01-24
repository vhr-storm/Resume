package Functional;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private static int counterOfResume = 0;
    void clear() {
    }

    void save(Resume r) {
        if(counterOfResume!=10000){
            this.storage[counterOfResume] = r;
            counterOfResume++;
        }
    }

    Resume get(String uuid) {
        int foundID=indexOfResume(uuid);
        if (size() == 0) return null;
        if(foundID!=-1){
            return this.storage[foundID];
        }
        return null;
    }

    int indexOfResume(String uuid){
        int i = 0;
        for (; i < size(); i++) {
            if (uuid.equals(this.storage[i].toString())) {
                return i;
            }
        }
        return -1;
    }
    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return counterOfResume;
    }
}
