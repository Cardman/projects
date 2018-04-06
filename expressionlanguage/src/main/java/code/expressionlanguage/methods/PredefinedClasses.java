package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;


public final class PredefinedClasses {

    public static final String ITERABLE = "$iterable";
    public static final String ITERATOR = "$iterator";
    public static final String ENUM = "$enum";
    public static final String ENUM_PARAM = "$Enum";
    private static final String PUBLIC_ACCESS = "PUBLIC";

    private PredefinedClasses() {
    }
    public static boolean isPredefined(String _type, ContextEl _context) {
        if (_context.getClasses() == null) {
            return isPredefined(_type);
        }
        LgNames stds_ = _context.getStandards();
        boolean pred_ = false;
        if (StringList.quickEq(_type, stds_.getAliasIterable())) {
            pred_ = true;
        } else if (StringList.quickEq(_type, stds_.getAliasIteratorType())) {
            pred_ = true;
        } else if (StringList.quickEq(_type, stds_.getAliasEnum())) {
            pred_ = true;
        } else if (StringList.quickEq(_type, stds_.getAliasEnumParam())) {
            pred_ = true;
        }
        return pred_;
    }
    private static boolean isPredefined(String _type) {
        boolean pred_ = false;
        if (StringList.quickEq(_type, PredefinedClasses.ITERABLE)) {
            pred_ = true;
        } else if (StringList.quickEq(_type, PredefinedClasses.ITERATOR)) {
            pred_ = true;
        } else if (StringList.quickEq(_type, PredefinedClasses.ENUM)) {
            pred_ = true;
        } else if (StringList.quickEq(_type, PredefinedClasses.ENUM_PARAM)) {
            pred_ = true;
        }
        return pred_;
    }
    public static String getIterableType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder("<interface access='").append(PUBLIC_ACCESS).append("' name='").append(stds_.getAliasIterable()).append("' template='&lt;#T&gt;'>\n");
        iterable_.append("<method access='").append(PUBLIC_ACCESS).append("' modifier='abstract' name='").append(stds_.getAliasIterator()).append("' class='").append(stds_.getAliasIteratorType()).append("&lt;#T&gt;'/>\n");
        iterable_.append("</interface>\n");
        return iterable_.toString();
    }

    public static String getIteratorType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder("<interface access='").append(PUBLIC_ACCESS).append("' name='").append(stds_.getAliasIteratorType()).append("' template='&lt;#T&gt;'>\n");
        iterable_.append("<method access='").append(PUBLIC_ACCESS).append("' modifier='abstract' name='").append(stds_.getAliasNext()).append("' class='#T'/>\n");
        iterable_.append("<method access='").append(PUBLIC_ACCESS).append("' modifier='abstract' name='").append(stds_.getAliasHasNext()).append("' class='").append(stds_.getAliasPrimBoolean()).append("'/>\n");
        iterable_.append("</interface>\n");
        return iterable_.toString();
    }

    public static String getEnumType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder("<interface access='").append(PUBLIC_ACCESS).append("' name='").append(stds_.getAliasEnum()).append("'>\n");
        iterable_.append("<method access='").append(PUBLIC_ACCESS).append("' modifier='abstract' name='").append(stds_.getAliasName()).append("' class='").append(stds_.getAliasString()).append("'/>\n");
        iterable_.append("<method access='").append(PUBLIC_ACCESS).append("' modifier='abstract' name='").append(stds_.getAliasOrdinal()).append("' class='").append(stds_.getAliasPrimInteger()).append("'/>\n");
        iterable_.append("</interface>\n");
        return iterable_.toString();
    }

    public static String getEnumParamType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String type_ = stds_.getAliasEnumParam();
        String typeSup_ = stds_.getAliasEnum();
        StringBuilder iterable_ = new StringBuilder("<interface access='").append(PUBLIC_ACCESS).append("' name='").append(type_).append("' template='&lt;#T:").append(type_).append("&lt;#T&gt;&gt;' class0='").append(typeSup_).append("'>\n");
        iterable_.append("</interface>\n");
        return iterable_.toString();
    }
    public static String getBracedIterableType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder("$public $interface ").append(stds_.getAliasIterable()).append("<#T>{\n");
        iterable_.append("$public $abstract ").append(stds_.getAliasIteratorType()).append("<#T> ").append(stds_.getAliasIterator()).append("():\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIteratorType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder("$public $interface ").append(stds_.getAliasIteratorType()).append("<#T>{\n");
        iterable_.append("$public $abstract #T ").append(stds_.getAliasNext()).append("():\n");
        iterable_.append("$public $abstract ").append(stds_.getAliasPrimBoolean()).append(" ").append(stds_.getAliasHasNext()).append("():\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedEnumType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder("$public $interface ").append(stds_.getAliasEnum()).append("{\n");
        iterable_.append("$public $abstract ").append(stds_.getAliasString()).append(" ").append(stds_.getAliasName()).append("():\n");
        iterable_.append("$public $abstract ").append(stds_.getAliasPrimInteger()).append(" ").append(stds_.getAliasOrdinal()).append("():\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedEnumParamType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String type_ = stds_.getAliasEnumParam();
        String typeSup_ = stds_.getAliasEnum();
        StringBuilder iterable_ = new StringBuilder("$public $interface ").append(type_).append("<#T:").append(type_).append("<#T>>:").append(typeSup_).append("{\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }
}
