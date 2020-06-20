package code.expressionlanguage.common;

import code.expressionlanguage.functionid.Identifiable;
import code.util.StringList;

public final class FunctionIdUtil {
    private FunctionIdUtil() {
    }
    public static boolean isOperatorName(Identifiable _id) {
        return !StringList.isDollarWord(_id.getName()) && !_id.getName().startsWith("[]");
    }
}
