package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class Validator {
    public static List<String> validate(Object obj) {
        Class<?> klass = obj.getClass();
        Field[] fields = klass.getDeclaredFields();
        List<String> list = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    if (field.get(obj) == null) list.add(field.getName());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }
}
// END
