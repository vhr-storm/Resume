package functional;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {
    abstract List<Resume> getAllResumes();

    abstract Object getSearchKey(String uuid);

    abstract boolean isExist(Object searchKey);

    abstract void doSave(Resume r, Object searchKey);

    abstract Resume doGet(Object searchKey);

    abstract void doDelete(Object searchKey);

    abstract void doUpdate(Resume r, Object searchKey);

    @Override
    public void clear() {
        getAllResumes().clear();
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());

        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, searchKey);
        }

    }


    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        return doGet(searchKey);
    }


    @Override
    public Resume[] getAll() {
        List<Resume> list = getAllResumes();
        return list.toArray(new Resume[0]);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        doDelete(searchKey);
    }


    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }

        doUpdate(r, searchKey);
    }


    @Override
    public int size() {
        return getAllResumes().size();
    }

}
