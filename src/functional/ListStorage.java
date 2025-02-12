package functional;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.ArrayList;
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

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public void delete(String uuid) {

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
