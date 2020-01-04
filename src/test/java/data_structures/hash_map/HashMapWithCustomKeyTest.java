package data_structures.hash_map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapWithCustomKeyTest {
    private HashMap<CustomKey, String> hashMap = new HashMap<>();
    private String value1 = "value1";
    private String value2 = "value2";
    private String value3 = "value3";

    @BeforeEach
    void beforeEach() {
        hashMap.clear();
    }

    @Test
    void testNullKeyHashCodeMethodIsNotCalled() {
        hashMap.put(null, value1);
    }

    @Test
    void testNotNullKeyHashCodeMethodIsCalled() {
        CustomKey key = new CustomKey("key", 1);
        hashMap.put(key, value1);
        assertThat(key.getHashCodeCallsCount().get()).isEqualTo(1);
    }

    @Test
    void testEqualsMethodIsCalledWhenKeysCollide() {
        CustomKey key1 = new CustomKey("first", 1);
        CustomKey key2 = new CustomKey("second", 2);
        CustomKey key3 = new CustomKey("third", 2);

        hashMap.put(key1, value1);
        hashMap.put(key2, value2);
        hashMap.put(key3, value3);

        assertThat(key2.getEqualsCallsObjects()).isEmpty();
        assertThat(key3.getEqualsCallsObjects()).containsExactly(key2);
    }
}
