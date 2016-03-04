package com.juhnowski.test2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class App {

    public static void main(String[] args) {

        try {

            Example example = new Example();
            Annotation[] classAnnotations = example.getClass().getAnnotations();

            System.out.println(example.getClass().getName() + " annotations:");
            for (Annotation annotation : classAnnotations) {
                System.out.println("  " + annotation);
            }

            Method[] classMethods = example.getClass().getMethods();
            System.out.println(" Method annotations:");
            for (Method method : classMethods) {
                Annotation[] methodAnnotations = method.getAnnotations();
                if (methodAnnotations.length > 0) {
                    System.out.println("  " + method.getName() + "():");
                }
                for (Annotation annotation : methodAnnotations) {
                    System.out.println("    " + annotation);
                }
            }

        } catch (Exception exc) {
        }
    }
}
