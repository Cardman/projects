package code.expressionlanguage.classes;

import code.util.opers.EquallableUtil;

public class FailMethods {

    public static final String NOT_READ = "not_read";

    public FailMethods() {
        EquallableUtil.assertEq(0, 1);
    }
    public static void fail() {
        EquallableUtil.assertEq(0, 1);
    }
}
