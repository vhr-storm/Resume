package functional;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private final Map<String, Resume> MAP_UUID = new HashMap<>();

    @Override
    public void clear() {
        MAP_UUID.clear();
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(MAP_UUID.values());
    }

    @Override
    Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    boolean isExist(Object searchKey) {
        return MAP_UUID.containsKey((String) searchKey);
    }

    @Override
    void doSave(Resume r, Object searchKey) {
        MAP_UUID.put((String) searchKey, r);
    }

    @Override
    Resume doGet(Object searchKey) {
        return MAP_UUID.get((String) searchKey);
    }

    @Override
    void doDelete(Object searchKey) {
        MAP_UUID.remove((String) searchKey);
    }

    @Override
    void doUpdate(Resume r, Object searchKey) {
        MAP_UUID.put((String) searchKey, r);
    }

}
