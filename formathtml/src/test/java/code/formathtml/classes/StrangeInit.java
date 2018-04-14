package code.formathtml.classes;

import code.expressionlanguage.EquallableElUtil;

public class StrangeInit {

    static {
        EquallableElUtil.assertEq(0, 1);
    }

    public static final String NOT_READ = "not_read".intern();

    public static void fail() {
    }
}
