package Functional;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage  {

    Comparator<Resume> resumeComparator =((o1, o2) -> o1.getUuid().compareTo(o2.getUuid()));
    private final int MAXIMUM_SIZE = 10000;
    private final Resume[] storage = new Resume[MAXIMUM_SIZE];
    private static int counterOfResume = 0;

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exists");
        } else if (counterOfResume != MAXIMUM_SIZE) {
            this.storage[counterOfResume] = r;
            counterOfResume++;
            sort(storage);
        }
    }

    @Override
    public Resume get(String uuid) {
        int foundID = Arrays.binarySearch(storage,0,size()-1,storage[getIndex(uuid)],resumeComparator );
        if (size() == 0) return null;
        if (foundID != -1) {
            return this.storage[foundID];
        } else {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(this.storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void delete(String uuid) {
        int foundID = Arrays.binarySearch(storage,0,size()-1,storage[getIndex(uuid)],resumeComparator );
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
            sort(storage);
        } else {
            System.out.println("Resume " + uuid + "not exist");
        }
    }

    @Override
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

    @Override
    public void update(Resume r) {
        int foundID = getIndex(r.getUuid());
        if (foundID == -1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            this.storage[foundID] = r;
        }
    }

    void sort(Resume[] r) {
        boolean swapped=true;
        int end= size()-1;
        int start=0;
        while(swapped){
            swapped=false;
            for(int i = 0; i < size()-1; i++){
                if((resumeComparator.compare(r[i],r[i+1]))>0){
                    Resume tmp = r[i];
                    r[i]=r[i+1];
                    r[i+1]=tmp;
                    swapped = true;
                }
            }
            if(!swapped)break;
            end--;
            swapped=false;
            for(int i=end; i>start;i--){
                if((resumeComparator.compare(r[i],r[i+1]))>0){
                    Resume tmp = r[i];
                    r[i]=r[i-1];
                    r[i-1]=tmp;
                    swapped = true;
                }
            }
            start++;
        }
    }

}


