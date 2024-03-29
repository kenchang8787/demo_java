package kenchang8787.com.custom_annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomAnnotation {

  public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

    Cat myCat = new Cat("Dragon", 3);
    // 這邊展示在執行階段時, 我有可以讀到這個 Annotation
    if (myCat.getClass().isAnnotationPresent(VeryImportant.class)) {
      System.out.println("This thing is very important");
    }

    for (Method method : myCat.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(RumImmediately.class)) {

        RumImmediately annotation = method.getAnnotation(RumImmediately.class);

        for (int i = 0; i < annotation.times(); i++) method.invoke(myCat);
      }
    }

    for (Field field : myCat.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(ImportantString.class)) {

        Object objectValue = field.get(myCat);

        if (objectValue instanceof String stringValue) {
          System.out.println(stringValue.toUpperCase());
        }
      }
    }
  }
}
