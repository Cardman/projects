package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;


public final class PredefinedClasses {

    private static final String SPACE = " ";
    private PredefinedClasses() {
    }
    public static boolean isPredefined(String _type, Analyzable _context) {
        LgNames stds_ = _context.getStandards();
        return stds_.getPredefinedClasses().containsStr(_type);
    }

    public static String getBracedIterableType(ContextEl _context) {
        char endLine_ = _context.getOptions().getEndLine();
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasIterable()).append("<#T>{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasIteratorType()).append("<#T>").append(SPACE);
        iterable_.append(stds_.getAliasIterator()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIteratorType(ContextEl _context) {
        char endLine_ = _context.getOptions().getEndLine();
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        LgNames stds_ = _context.getStandards();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasIteratorType()).append("<#T>{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append("#T").append(SPACE);
        iterable_.append(stds_.getAliasNext()).append("()").append(endLine_).append("\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasPrimBoolean()).append(SPACE);
        iterable_.append(stds_.getAliasHasNext()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedEnumType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasEnum()).append("{\n");
        if (_context.getOptions().isSpecialEnumsMethods()) {
            char endLine_ = _context.getOptions().getEndLine();
            String abstract_ = keyWords_.getKeyWordAbstract();
            String string_ = stds_.getAliasString();
            String name_ = stds_.getAliasName();
            iterable_.append(public_).append(SPACE);
            iterable_.append(abstract_).append(SPACE);
            iterable_.append(string_).append(SPACE);
            iterable_.append(name_).append("()").append(endLine_).append("\n");
            String int_ = stds_.getAliasPrimInteger();
            String ordinal_ = stds_.getAliasOrdinal();
            iterable_.append(public_).append(SPACE);
            iterable_.append(abstract_).append(SPACE);
            iterable_.append(int_).append(SPACE);
            iterable_.append(ordinal_).append("()").append(endLine_).append("\n");
        }
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedEnumParamType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String type_ = stds_.getAliasEnumParam();
        String typeSup_ = stds_.getAliasEnum();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(type_).append("<#T:").append(type_).append("<#T>>:").append(typeSup_).append("{\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }
}
