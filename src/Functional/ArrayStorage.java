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
        return null;
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
