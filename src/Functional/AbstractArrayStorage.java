package Functional;

public abstract class AbstractArrayStorage implements Storage {

     final int MAXIMUM_SIZE=10000;
     final Resume[] storage = new Resume[MAXIMUM_SIZE];
    private static int counterOfResume = 0;
    public  void clear(){
        for (int i = 0; i < size(); i++) {
            this.storage[i] = null;
        }
        counterOfResume = 0;
    }

    public void save(Resume r) {
        int index=getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("Resume " + r.getUuid() + " already exists");
        } else if (counterOfResume != MAXIMUM_SIZE) {
            insertElement(r,index);
            counterOfResume++;
        }
    }

    protected abstract void insertElement(Resume r, int index);
    protected abstract void fillDeletedElement(int index);
    public Resume get(String uuid){
        int foundID = getIndex(uuid);
        if (size() == 0) return null;
        if (foundID != -1) {
            return this.storage[foundID];
        } else {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
    }

    public abstract int getIndex(String uuid);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            fillDeletedElement(index);
            storage[counterOfResume-1]=null;
            counterOfResume--;
        } else{
            System.out.println("Resume "+ uuid+ "not exist");
        }
    }
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

    public void update(Resume r) {
        int foundID = getIndex(r.getUuid());
        if (foundID == -1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            this.storage[foundID] = r;
        }
    }

}
