package code.expressionlanguage.classes;

import code.util.opers.EquallableUtil;

public class StrangeInit {

    static {
        EquallableUtil.assertEq(0, 1);
    }

    public static final String NOT_READ = "not_read";

    public static void fail() {
    }
}
