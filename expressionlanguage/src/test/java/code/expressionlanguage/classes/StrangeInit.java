package code.expressionlanguage.classes;

import code.expressionlanguage.EquallableElUtil;

public class StrangeInit {

    static {
        EquallableElUtil.assertEq(0, 1);
    }

    public static final String NOT_READ = "not_read";

    public static void fail() {
    }
}
