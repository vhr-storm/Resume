package functional;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage implements Storage {
    static final Comparator<Resume> RESUME_COMPARATOR = ((o1, o2) ->o1.getFullName().compareTo(o2.getFullName()));
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
    public List<Resume> getAllSorted() {
        List<Resume> list = getAllResumes();
        return list.stream()
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
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
