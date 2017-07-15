package code.expressionlanguage.classes;


public class MyImpl implements IThree {

    public static String ovOne(IOne _i) {
        return "one";
    }

    public static String ovOne(ITwo _i) {
        return "two";
    }

    public static String ovOne(IThree _i) {
        return "three";
    }

    public static String ovTwo(IOne _i) {
        return "one";
    }

    public static String ovTwo(ITwo _i) {
        return "two";
    }

    @Override
    public String testThree() {
        return "three";
    }

    @Override
    public String testOne() {
        return "one";
    }

    @Override
    public String testTwo() {
        return "two";
    }

}
