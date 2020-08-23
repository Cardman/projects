package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;


public final class PredefinedClasses {

    private static final String SPACE = " ";
    private PredefinedClasses() {
    }
    public static String getBracedIterableType(ContextEl _context) {
        char endLine_ = ';';
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        LgNames stds_ = _context.getStandards();
        String var_ = stds_.getAliasIterableVar();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasIterable()).append("<").append(var_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasIteratorType()).append("<").append(var_).append(">").append(SPACE);
        iterable_.append(stds_.getAliasIterator()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIteratorType(ContextEl _context) {
        char endLine_ = ';';
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        LgNames stds_ = _context.getStandards();
        String var_ = stds_.getAliasIteratorTypeVar();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasIteratorType()).append("<").append(var_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(var_).append(SPACE);
        iterable_.append(stds_.getAliasNext()).append("()").append(endLine_).append("\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasPrimBoolean()).append(SPACE);
        iterable_.append(stds_.getAliasHasNext()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIterableTableType(ContextEl _context) {
        char endLine_ = ';';
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        LgNames stds_ = _context.getStandards();
        String first_ = stds_.getAliasIterableTableVarFirst();
        String second_ = stds_.getAliasIterableTableVarSecond();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasIterableTable()).append("<").append(first_).append(",").append(second_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasIteratorTableType()).append("<").append(first_).append(",").append(second_).append(">").append(SPACE);
        iterable_.append(stds_.getAliasIteratorTable()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIteratorTableType(ContextEl _context) {
        char endLine_ = ';';
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        LgNames stds_ = _context.getStandards();
        String first_ = stds_.getAliasIteratorTableTypeVarFirst();
        String second_ = stds_.getAliasIteratorTableTypeVarSecond();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasIteratorTableType()).append("<").append(first_).append(",").append(second_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasPairType());
        iterable_.append("<").append(first_).append(",").append(second_).append(">").append(SPACE);
        iterable_.append(stds_.getAliasNextPair()).append("()").append(endLine_).append("\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasPrimBoolean()).append(SPACE);
        iterable_.append(stds_.getAliasHasNextPair()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedPairType(ContextEl _context) {
        char endLine_ = ';';
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        LgNames stds_ = _context.getStandards();
        String first_ = stds_.getAliasPairTypeVarFirst();
        String second_ = stds_.getAliasPairTypeVarSecond();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(stds_.getAliasPairType()).append("<").append(first_).append(",").append(second_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(first_).append(SPACE);
        iterable_.append(stds_.getAliasGetFirst()).append("()").append(endLine_).append("\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(second_).append(SPACE);
        iterable_.append(stds_.getAliasGetSecond()).append("()").append(endLine_).append("\n");
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
        iterable_.append(stds_.getAliasEnumType()).append("{\n");
        char endLine_ = ';';
        String final_ = keyWords_.getKeyWordFinal();
        String static_ = keyWords_.getKeyWordStatic();
        String this_ = keyWords_.getKeyWordThis();
        String return_ = keyWords_.getKeyWordReturn();
        String string_ = stds_.getAliasString();
        String name_ = stds_.getAliasEnumName();
        iterable_.append(public_).append(SPACE);
        iterable_.append(final_).append(SPACE);
        iterable_.append(string_).append(SPACE);
        iterable_.append(name_).append("()").append("{").append("\n");
        iterable_.append(return_).append(" ").append(static_).append("(");
        iterable_.append(stds_.getAliasEnums());
        iterable_.append(").").append(stds_.getAliasName());
        iterable_.append("(").append(this_).append(")");
        iterable_.append(endLine_).append("\n");
        iterable_.append("}").append("\n");
        String int_ = stds_.getAliasPrimInteger();
        String ordinal_ = stds_.getAliasEnumOrdinal();
        iterable_.append(public_).append(SPACE);
        iterable_.append(final_).append(SPACE);
        iterable_.append(int_).append(SPACE);
        iterable_.append(ordinal_).append("()").append("{").append("\n");
        iterable_.append(return_).append(" ").append(static_).append("(");
        iterable_.append(stds_.getAliasEnums());
        iterable_.append(").").append(stds_.getAliasOrdinal());
        iterable_.append("(").append(this_).append(")");
        iterable_.append(endLine_).append("\n");
        iterable_.append("}").append("\n");
        iterable_.append("}").append("\n");
        return iterable_.toString();
    }

    public static String getBracedEnumParamType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String type_ = stds_.getAliasEnumParam();
        String typeSup_ = stds_.getAliasEnumType();
        String var_ = stds_.getAliasEnumParamVar();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(type_).append("<").append(var_).append(":").append(type_).append("<").append(var_).append(">>:").append(typeSup_).append("{\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedSeedGeneratorType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String type_ = stds_.getAliasSeedGenerator();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(type_).append("{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        String tr_ = ValidatorStandard.tr(new StringList(), _context);
        iterable_.append(stds_.getAliasPrimLong()).append(SPACE).append(stds_.getAliasSeedGet()).append("(");
        iterable_.append(stds_.getAliasPrimLong()).append(SPACE).append(tr_).append(");\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedSeedDoubleGeneratorType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String type_ = stds_.getAliasSeedDoubleGenerator();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(type_).append("{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(stds_.getAliasPrimDouble()).append(SPACE).append(stds_.getAliasSeedGet()).append("();\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }
}
