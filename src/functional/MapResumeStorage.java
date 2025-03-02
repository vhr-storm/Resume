package functional;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> MAP_RESUME = new HashMap<>();

    @Override
    public void clear() {
        MAP_RESUME.clear();
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(MAP_RESUME.values());
    }

    @Override
    Object getSearchKey(String uuid) {
        return new Resume(uuid,"dummy");
    }

    @Override
    boolean isExist(Object searchKey) {
        return MAP_RESUME.containsKey(((Resume)searchKey).getUuid());
    }

    @Override
    void doSave(Resume r, Object searchKey) {
        MAP_RESUME.put(((Resume)searchKey).getUuid(), r);
    }

    @Override
    Resume doGet(Object searchKey) {
        return MAP_RESUME.get(((Resume)searchKey).getUuid());
    }

    @Override
    void doDelete(Object searchKey) {
        MAP_RESUME.remove(((Resume)searchKey).getUuid());
    }

    @Override
    void doUpdate(Resume r, Object searchKey) {
        MAP_RESUME.put(((Resume)searchKey).getUuid(), r);
    }
}
