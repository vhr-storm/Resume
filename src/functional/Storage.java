package functional;

import model.Resume;

public interface Storage {
    public void clear();

    public void save(Resume r);

    public Resume get(String uuid);

    public Resume[] getAll();

    public void delete(String uuid);

    public void update(Resume r);

    public int size();

    public int getIndex(String uuid);

}
