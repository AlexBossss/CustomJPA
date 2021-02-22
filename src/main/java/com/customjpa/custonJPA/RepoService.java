package com.customjpa.custonJPA;

import org.reflections.Reflections;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class RepoService {
    private static Reflections reflections;

    private static Map<String, ?> beans;

    static {
        createInstance();
    }

    private static void createInstance() {
        reflections = new Reflections("com");
        beans = Stream.of(
                reflections.getTypesAnnotatedWith(JPARepo.class)
        )
                .flatMap(Collection::stream)
                .filter(jpaIntr -> !jpaIntr.getName().equals("com.customjpa.custonJPA.CustomJPA"))
                .collect(toMap(Class::getName, RepoService::createRepo));
    }

    private static Object createRepo(Class<?> cls) {
        return Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[]{cls},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        String methodName = method.getName();
                        Class<?> returnType = method.getReturnType();
                        if (methodName.equals("findByName")) {
                            System.out.println("Name " + args[0]);
                        }
                        return returnType.getConstructor().newInstance();
                    }
                }
        );
    }

    public static <T> T getRepo(Class<T> className) {
        return (T) beans.get(className.getName());
    }
}
