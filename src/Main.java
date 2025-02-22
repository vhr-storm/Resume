import functional.AbstractStorage;
import functional.StorageFactory;
import model.Resume;

public class Main {

    static AbstractStorage arraySortedStorage = StorageFactory.createStorage(StorageFactory.StorageType.ARRAY_STORAGE);
    protected static final Resume[] TEST_RESUMES = {
            new Resume("uuid10","Roman"),
            new Resume("uuid11","Evgeniy"),
            new Resume("uuid15","Alina"),
            new Resume("uuid14","Natalia"),
            new Resume("uuid9","Algrgey"),
    };
    public static void main(String[] args) {

        arraySortedStorage.save(TEST_RESUMES[0]);

        arraySortedStorage.save(TEST_RESUMES[1]);
        arraySortedStorage.save(TEST_RESUMES[2]);
        arraySortedStorage.save(TEST_RESUMES[3]);
        arraySortedStorage.save(TEST_RESUMES[4]);
//        arraySortedStorage.save(r1);

        System.out.println("Get r1: " + arraySortedStorage.get(TEST_RESUMES[0].getUuid()));

        System.out.println("Size: " + arraySortedStorage.size());

        printAll();
        arraySortedStorage.delete(TEST_RESUMES[0].getUuid());
//        arraySortedStorage.delete(r1.getUuid());
        printAll();
//        arraySortedStorage.clear();
        printAll();
        System.out.println();
        System.out.println("Size: " + arraySortedStorage.size());
        arraySortedStorage.update(TEST_RESUMES[3]);
        System.out.println("Get r4: " + arraySortedStorage.get(TEST_RESUMES[3].getUuid()));
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : arraySortedStorage.getAllSorted()) {
            System.out.println(r);
        }
    }
}