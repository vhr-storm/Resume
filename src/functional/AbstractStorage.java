package functional;

import exception.ExistStorageException;
import model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage{
    abstract List<Resume> getAllResumes();
    abstract Object getSearchKey(String uuid);
    abstract boolean isExist(Object searchKey);
    abstract void doSave(Resume r, Object searchKey);

    @Override
    public void clear() {
        getAllResumes().clear();
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if(isExist(searchKey)){
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r,searchKey);
        }
    }



    @Override
    public Resume get(String uuid) {
        return null;
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
        return 0;
    }

}
