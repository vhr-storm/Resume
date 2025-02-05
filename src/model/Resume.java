package model;

import java.util.UUID;

public class Resume implements Comparable<Resume> {
    private final String uuid;

    public Resume(){
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }


    @Override
    public String toString() {
        return uuid;
    }

    public String getUuid() {
        return uuid;
    }


    @Override
    public int compareTo(Resume o) {
        return this.uuid.compareTo(o.uuid);
    }
}
