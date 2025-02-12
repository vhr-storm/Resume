package functional;

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
