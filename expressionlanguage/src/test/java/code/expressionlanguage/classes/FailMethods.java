package code.expressionlanguage.classes;

import code.expressionlanguage.EquallableElUtil;

public class FailMethods {

    public static final String NOT_READ = "not_read";

    public FailMethods() {
        EquallableElUtil.assertEq(0, 1);
    }
    public static void fail() {
        EquallableElUtil.assertEq(0, 1);
    }
}
