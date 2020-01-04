package data_structures.hash_map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class HashMapWithStringKeyTest {
    private HashMap<String, String> hashMap = new HashMap<>();
    private String key1 = "key1";
    private String key2 = "key2";
    private String value1 = "value1";
    private String value2 = "value2";

    @BeforeEach
    void beforeEach() {
        hashMap.clear();
    }

    @Test
    void testNullKeys() {
        assertThat(hashMap.get(null)).isNull();
        assertThat(hashMap.containsKey(null)).isFalse();

        hashMap.put(null, value1);

        assertThat(hashMap.get(null)).isEqualTo(value1);
        assertThat(hashMap.containsKey(null)).isTrue();
    }

    @Test
    void testNullValues() {
        assertThat(hashMap.get(key1)).isNull();
        assertThat(hashMap.containsValue(null)).isFalse();

        hashMap.put(key1, null);

        assertThat(hashMap.get(key1)).isNull();
        assertThat(hashMap.containsValue(null)).isTrue();
    }

    @Test
    void testLastInsertedValue() {
        String previousValue = hashMap.put(key1, value1);
        assertThat(previousValue).isNull();
        assertThat(hashMap.get(key1)).isEqualTo(value1);

        previousValue = hashMap.put(key1, value2);

        assertThat(previousValue).isEqualTo(value1);
        assertThat(hashMap.get(key1)).isEqualTo(value2);
    }

    @Test
    void testKetSet() {
        hashMap.put(null, null);
        hashMap.put(key1, value1);
        hashMap.put(key2, value2);

        Set<String> keySet = hashMap.keySet();
        assertThat(keySet.size()).isEqualTo(3);

        keySet.remove(null);
        assertThat(keySet.size()).isEqualTo(2);
    }

    @Test
    void testValueSet() {
        hashMap.put(null, value1);
        hashMap.put(key1, value1);
        hashMap.put(key2, value2);

        Collection<String> valueSet = hashMap.values();
        assertThat(valueSet.size()).isEqualTo(3);

        valueSet.remove(value1);
        assertThat(valueSet.size()).isEqualTo(2);
    }

    @Test
    void testEntrySet() {
        hashMap.put(null, value1);
        hashMap.put(key1, value1);
        hashMap.put(key2, value2);

        Set<HashMap.Entry<String, String>> entrySet = hashMap.entrySet();
        assertThat(entrySet.size()).isEqualTo(3);

        entrySet.remove(new AbstractMap.SimpleEntry<>(key1, value1));
        assertThat(entrySet.size()).isEqualTo(2);
    }

    @Test
    void testMapModificationAfterIteratorCreationIsNotAllowed() {
        hashMap.put(key1, value1);
        hashMap.put(key2, value2);
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        hashMap.remove(key1);

        assertThatExceptionOfType(ConcurrentModificationException.class).isThrownBy(() -> {
            while (iterator.hasNext()) {
                iterator.next();
            }
        });
    }

    @Test
    void testMapModificationThroughIteratorAfterIteratorCreationIsAllowed() {
        hashMap.put(key1, value1);
        hashMap.put(key2, value2);
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }
}
