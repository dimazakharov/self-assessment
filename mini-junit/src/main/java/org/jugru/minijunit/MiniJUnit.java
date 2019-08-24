package org.jugru.minijunit;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MiniJUnit {

    private final Class<Test> testAnnotation = Test.class;

    public TestResult test(String basePackage)  {


        List<Class<?>> classes = getSuitableClasses(basePackage);

        TestResult testResult = new TestResult();
        for (Class<?> aClass : classes) {
            try {
                tryTestClass(testResult, aClass);
            } catch (Throwable throwable) {
                handleTestClassError(testResult, aClass);
            }
        }
        return testResult;

    }

    private void handleTestClassError(TestResult testResult, Class<?> aClass) {
        for (Method method : getSuitableMethods(aClass)) {
            testResult.incrementFailed();
            testResult.addDetails(new TestDetail(aClass.getName(), method.getName(), false, "Can't create object"));
        }
    }

    private void tryTestClass(TestResult testResult, Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object testObject = ConstructorUtils.invokeExactConstructor(aClass);

        for (Method testMethod : getSuitableMethods(aClass)) {
            try {
                MethodUtils.invokeMethod(testObject, testMethod.getName());
                testResult.incrementPassed();
                testResult.addDetails(new TestDetail(aClass.getName(), testMethod.getName(), true, null));
            } catch (InvocationTargetException e) {
                testResult.incrementFailed();
                if (e.getCause() instanceof AssertionError)
                    testResult.addDetails(new TestDetail(aClass.getName(), testMethod.getName(), false, e.getCause().getMessage()));
                else
                    testResult.addDetails(new TestDetail(aClass.getName(), testMethod.getName(), false, e.getCause().toString()));
            }
        }
    }

    private List<Method> getSuitableMethods(Class<?> aClass) {
        List<Method> methods = MethodUtils.getMethodsListWithAnnotation(aClass, testAnnotation, false, false);
        methods.removeIf(method -> method.getParameterCount() > 0);
        methods.removeIf(method -> !Void.TYPE.equals(method.getReturnType()));


        return methods;
    }

    private List<Class<?>> getSuitableClasses(String basePackage) {
        ScanResult scanResult = getScanResult(basePackage);

        List<Class<?>> classes = scanResult.getClassesWithMethodAnnotation(testAnnotation.getName()).loadClasses(true);
        classes.removeIf(aClass -> ConstructorUtils.getAccessibleConstructor(aClass) == null);
        return classes;
    }

    private ScanResult getScanResult(String basePackage) {
        ClassGraph classGraph = new ClassGraph()
                .enableAllInfo();
        if (StringUtils.isNoneEmpty(basePackage)) {
            classGraph.whitelistPackages(basePackage);
        }
        return classGraph.scan();
    }


}
