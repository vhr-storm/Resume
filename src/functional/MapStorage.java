package functional;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    private final Map<String,Resume> MAP_RESUME = new HashMap<>();

    @Override
    public void clear() {

        if(!MAP_RESUME.isEmpty()){
           MAP_RESUME.clear();
        }

    }

    @Override
    public void save(Resume r) {

        if (MAP_RESUME.containsKey(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        } else {
            MAP_RESUME.put(r.getUuid(),r);
        }

    }

    @Override
    public Resume get(String uuid) {
        Resume r = new Resume(uuid);

        if (!MAP_RESUME.containsKey(r.getUuid())) {
            throw new NotExistStorageException(uuid);
        }

        return r;
    }

    @Override
    public Resume[] getAll() {
        return MAP_RESUME.values().toArray(new Resume[0]);
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
