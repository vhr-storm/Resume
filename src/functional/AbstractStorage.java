package functional;

import model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage{
    @Override
    public void clear() {
        getAllResumes().clear();
    }

    @Override
    public void save(Resume r) {

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
    abstract List<Resume> getAllResumes();
}
