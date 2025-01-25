package Functional;

public abstract class AbstractArrayStorage {
    public abstract void clear();
    public abstract void save(Resume r);
    public abstract Resume get(String uuid);
    public abstract Resume[] getAll();
    public abstract void delete(String uuid);
    public abstract void update(Resume r);
    public abstract int size();
}
