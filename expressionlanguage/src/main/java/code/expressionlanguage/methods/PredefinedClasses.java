package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.LgNames;


public final class PredefinedClasses {

    private PredefinedClasses() {
    }
    public static boolean isPredefined(String _type, Analyzable _context) {
        LgNames stds_ = _context.getStandards();
        return stds_.getPredefinedClasses().containsStr(_type);
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
