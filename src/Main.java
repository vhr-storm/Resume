import Functional.AbstractArrayStorage;
import Functional.StorageFactory;
import model.Resume;
import Functional.SortedArrayStorage;

public class Main { ;
    static AbstractArrayStorage arraySortedStorage = StorageFactory.createStorage(StorageFactory.StorageType.NO_TYPE);
    public static void main(String[] args) {
        Resume r1 = new Resume("uuid3");
        Resume r2 = new Resume("uuid1");
        Resume r3 = new Resume("uuid5");
        Resume r4 =new Resume("uuid4");

        arraySortedStorage.save(r1);
        arraySortedStorage.save(r2);
        arraySortedStorage.save(r3);
        arraySortedStorage.save(r4);
        System.out.println("Get r1: " + arraySortedStorage.get(r1.getUuid()));
        System.out.println("Get r2: " + arraySortedStorage.get(r2.getUuid()));

        System.out.println("Size: " + arraySortedStorage.size());

        printAll();
        arraySortedStorage.delete(r1.getUuid());
        printAll();
        arraySortedStorage.clear();
        arraySortedStorage.delete(r1.getUuid());
        printAll();
        System.out.println();
        System.out.println("Size: " + arraySortedStorage.size());
//        System.out.println(); arraySortedStorage.update(r4);
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : arraySortedStorage.getAll()) {
            System.out.println(r);
        }
    }
}