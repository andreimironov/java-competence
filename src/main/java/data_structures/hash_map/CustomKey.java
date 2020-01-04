package data_structures.hash_map;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@RequiredArgsConstructor
@Slf4j
public class CustomKey {
    private final String name;
    private final int id;
    private AtomicInteger hashCodeCallsCount = new AtomicInteger(0);
    private final CopyOnWriteArrayList<Object> equalsCallsObjects = new CopyOnWriteArrayList<>();

    @Override
    public boolean equals(Object o) {
        log.info("Called equals({}) on {}", o, this);
        equalsCallsObjects.add(o);

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomKey customKey = (CustomKey) o;
        return id == customKey.id &&
                name.equals(customKey.name);
    }

    @Override
    public int hashCode() {
        log.info("Called hashCode() on {}", this);
        hashCodeCallsCount.incrementAndGet();

        return id;
    }

    @Override
    public String toString() {
        return "CustomKey{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
