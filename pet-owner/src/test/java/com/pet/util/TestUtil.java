package com.pet.util;
import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {

    public static <T> void equalsVerifier(Class<T> clazz) throws Exception {
        T domainObject = clazz.getConstructor().newInstance();
         assertThat(domainObject.toString()).isNotNull();
    }
}
