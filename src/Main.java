import Functional.Resume;
import Functional.ArrayStorage;
public class Main {
    static ArrayStorage arrayStorage = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();

        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 =new Resume();
//        r4.uuid="uuid4";
        arrayStorage.save(r1);
        arrayStorage.save(r2);
        arrayStorage.save(r3);

        System.out.println("Get r1: " + arrayStorage.get(r1.getUuid()));
        System.out.println("Size: " + arrayStorage.size());

        printAll();
        arrayStorage.delete(r1.getUuid());
        printAll();
        arrayStorage.clear();
        printAll();
        System.out.println();
        System.out.println("Size: " + arrayStorage.size());
        System.out.println();
        arrayStorage.update(r4);
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : arrayStorage.getAll()) {
            System.out.println(r);
        }
    }
}