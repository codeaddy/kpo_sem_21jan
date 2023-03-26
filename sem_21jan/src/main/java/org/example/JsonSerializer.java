package org.example;

import org.example.annotations.serialization.Published;
import org.json.JSONObject;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class JsonSerializer<T> {
    private final Set<Field> publishedFields;

    public JsonSerializer(Class<T> serializedClass) {
        publishedFields = new HashSet<Field>();
        for (Field field : ReflectionUtils.getAllFields(serializedClass)) {
            if (field.getAnnotation(Published.class) != null) {
                publishedFields.add(field);
            }
        }
    }

    public JSONObject serialize(T o) throws IllegalAccessException {
        JSONObject result = new JSONObject();
        for (Field field : publishedFields) {
            field.setAccessible(true);
            result.put(field.getName(), field.get(o));
        }
        return result;
    }
}
