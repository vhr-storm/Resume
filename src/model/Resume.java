package model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor

public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;

    @Override
    public String toString() {
        return uuid + " " + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
    @Override
    public int compareTo(Resume o) {
        return this.uuid.compareTo(o.uuid);
    }
}
