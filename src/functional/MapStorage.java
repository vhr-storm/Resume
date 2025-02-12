package functional;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> MAP_RESUME = new HashMap<>();

    @Override
    List<Resume> getAllResumes() {
        return new ArrayList<>(MAP_RESUME.values());
    }

    @Override
    Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    boolean isExist(Object searchKey) {
        return MAP_RESUME.containsKey((String) searchKey);
    }

    @Override
    void doSave(Resume r, Object searchKey) {
        MAP_RESUME.put((String) searchKey, r);
    }

    @Override
    Resume doGet(Object searchKey) {
        return MAP_RESUME.get((String) searchKey);
    }

    @Override
    void doDelete(Object searchKey) {
        MAP_RESUME.remove((String) searchKey);
    }

    @Override
    void doUpdate(Resume r, Object searchKey) {
        MAP_RESUME.put((String) searchKey, r);
    }

}
