package functional;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> LIST_RESUME = new ArrayList<>();

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
        return (Integer) searchKey >= 0;
    }

    @Override
    void doSave(Resume r, Object searchKey) {
        LIST_RESUME.add(r);
    }

    @Override
    Resume doGet(Object searchKey) {
        return LIST_RESUME.get((Integer) searchKey);
    }

    @Override
    void doDelete(Object searchKey) {
        LIST_RESUME.remove((int) (Integer) searchKey);
    }

    @Override
    void doUpdate(Resume r, Object searchKey) {
        LIST_RESUME.set((Integer) searchKey, r);
    }

}
