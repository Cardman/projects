package code.expressionlanguage.common;

public final class FunctionIdUtil {
    private FunctionIdUtil() {
    }

    public static boolean isOperatorName(String _id) {
        return StringExpUtil.isOper(_id);
    }
}
