package functional;

import model.Resume;

public interface Storage {
    void clear();

    void save(Resume r);

    Resume get(String uuid);

    Resume[] getAll();

    void delete(String uuid);

    void update(Resume r);

    int size();

}
