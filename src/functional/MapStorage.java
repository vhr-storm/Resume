package functional;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> MAP_RESUME = new HashMap<>();



    @Override
    public void save(Resume r) {

        if (MAP_RESUME.containsKey(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        } else {
            MAP_RESUME.put(r.getUuid(), r);
        }

    }

    @Override
    public Resume get(String uuid) {

        if (!MAP_RESUME.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }

        return MAP_RESUME.get(uuid);
    }

    @Override
    public Resume[] getAll() {
        return MAP_RESUME.values().toArray(new Resume[0]);
    }

    @Override
    public void delete(String uuid) {

        if (!MAP_RESUME.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            MAP_RESUME.remove(uuid);
        }

    }

    @Override
    public void update(Resume r) {

        if (!MAP_RESUME.containsKey(r.getUuid())) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            MAP_RESUME.put(r.getUuid(), r);
        }

    }

    @Override
    public int size() {
        return MAP_RESUME.size();
    }

    @Override
    List<Resume> getAllResumes() {
        return new ArrayList<>(MAP_RESUME.values());
    }

}
