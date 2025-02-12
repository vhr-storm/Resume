package functional;

import exception.NotExistStorageException;
import model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> LIST_RESUME = new ArrayList<>();

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(LIST_RESUME.toArray(new Resume[0]), 0, size());
    }

    @Override
    public void delete(String uuid) {
        Resume r = new Resume(uuid);

        if (!LIST_RESUME.contains(r)) {
            throw new NotExistStorageException(uuid);
        } else {
            LIST_RESUME.remove(r);
        }

    }

    @Override
    public void update(Resume r) throws NotExistStorageException {
        int index = getIndex(r.getUuid());

        if (!LIST_RESUME.contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            LIST_RESUME.set(index, r);
        }

    }

    @Override
    public int size() {
        return LIST_RESUME.size();
    }

    @Override
    List<Resume> getAllResumes() {
        return LIST_RESUME;
    }

    @Override
    Object getSearchKey(String uuid) {
        return LIST_RESUME.indexOf(new Resume(uuid));
    }

    @Override
    boolean isExist(Object searchKey) {
        return LIST_RESUME.contains((Resume) searchKey);
    }

    @Override
    void doSave(Resume r, Object searchKey) {
        LIST_RESUME.add(r);
    }

    @Override
    Resume doGet(Object searchKey) {
        return LIST_RESUME.get((Integer)searchKey);
    }


    public int getIndex(String uuid) {
        return LIST_RESUME.indexOf(new Resume(uuid));
    }
}
