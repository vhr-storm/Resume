package functional;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage{
    static final List<Resume> RESUME_LIST = new ArrayList<Resume>();
    @Override
    public void clear() {
        RESUME_LIST.clear();
    }

    @Override
    public void save(Resume r) throws ExistStorageException {
        if(RESUME_LIST.contains(r)){
            throw new ExistStorageException(r.getUuid());
        } else {
            RESUME_LIST.add(r);
        }

    }

    @Override
    public Resume get(String uuid)  {
        Resume r = new Resume(uuid);
        if(!RESUME_LIST.contains(r)){
            throw new NotExistStorageException(uuid);
        }
        return r;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(RESUME_LIST.toArray(new Resume[0]),0,size());
    }

    @Override
    public void delete(String uuid) {
        Resume r = new Resume(uuid);
        if(!RESUME_LIST.contains(r)){
            throw new NotExistStorageException(uuid);
        } else {
            RESUME_LIST.remove(r);
        }
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public int size() {
        return RESUME_LIST.size();
    }

    @Override
    public int getIndex(String uuid) {
        return 0;
    }
}
