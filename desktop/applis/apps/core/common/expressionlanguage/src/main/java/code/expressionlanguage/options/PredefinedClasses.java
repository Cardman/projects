package code.expressionlanguage.options;

import code.expressionlanguage.stds.AliasCore;
import code.expressionlanguage.stds.AliasPredefinedTypes;


public final class PredefinedClasses {

    private static final String SPACE = " ";
    private PredefinedClasses() {
    }
    public static String getBracedIterableType(KeyWords _keyWords, AliasPredefinedTypes _predefTypes) {
        char endLine_ = ';';
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        String var_ = predefTypes_.getAliasIterableVar();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(predefTypes_.getAliasIterable()).append("<").append(var_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(predefTypes_.getAliasIteratorType()).append("<").append(var_).append(">").append(SPACE);
        iterable_.append(predefTypes_.getAliasIterator()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIteratorType(KeyWords _keyWords, AliasPredefinedTypes _predefTypes, String _aliasPrimBoolean) {
        char endLine_ = ';';
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        String var_ = predefTypes_.getAliasIteratorTypeVar();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(predefTypes_.getAliasIteratorType()).append("<").append(var_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(var_).append(SPACE);
        iterable_.append(predefTypes_.getAliasNext()).append("()").append(endLine_).append("\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(_aliasPrimBoolean).append(SPACE);
        iterable_.append(predefTypes_.getAliasHasNext()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIterableTableType(KeyWords _keyWords, AliasPredefinedTypes _predefTypes) {
        char endLine_ = ';';
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        String first_ = predefTypes_.getAliasIterableTableVarFirst();
        String second_ = predefTypes_.getAliasIterableTableVarSecond();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(predefTypes_.getAliasIterableTable()).append("<").append(first_).append(",").append(second_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(predefTypes_.getAliasIteratorTableType()).append("<").append(first_).append(",").append(second_).append(">").append(SPACE);
        iterable_.append(predefTypes_.getAliasIteratorTable()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedIteratorTableType(KeyWords _keyWords, AliasPredefinedTypes _predefTypes, String _aliasPrimBoolean) {
        char endLine_ = ';';
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        String first_ = predefTypes_.getAliasIteratorTableTypeVarFirst();
        String second_ = predefTypes_.getAliasIteratorTableTypeVarSecond();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(predefTypes_.getAliasIteratorTableType()).append("<").append(first_).append(",").append(second_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(predefTypes_.getAliasPairType());
        iterable_.append("<").append(first_).append(",").append(second_).append(">").append(SPACE);
        iterable_.append(predefTypes_.getAliasNextPair()).append("()").append(endLine_).append("\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(_aliasPrimBoolean).append(SPACE);
        iterable_.append(predefTypes_.getAliasHasNextPair()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedPairType(KeyWords _keyWords, AliasPredefinedTypes _predefTypes) {
        char endLine_ = ';';
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        String first_ = predefTypes_.getAliasPairTypeVarFirst();
        String second_ = predefTypes_.getAliasPairTypeVarSecond();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(predefTypes_.getAliasPairType()).append("<").append(first_).append(",").append(second_).append(">{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(first_).append(SPACE);
        iterable_.append(predefTypes_.getAliasGetFirst()).append("()").append(endLine_).append("\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(second_).append(SPACE);
        iterable_.append(predefTypes_.getAliasGetSecond()).append("()").append(endLine_).append("\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }
    public static String getBracedEnumType(AliasPredefinedTypes _predefTypes, AliasCore _coreNames, KeyWords _keyWords, String _aliasString, String _aliasPrimInteger) {
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        AliasCore coreNames_ = _coreNames;
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(predefTypes_.getAliasEnumType()).append("{\n");
        char endLine_ = ';';
        String final_ = keyWords_.getKeyWordFinal();
        String static_ = keyWords_.getKeyWordStatic();
        String this_ = keyWords_.getKeyWordThis();
        String return_ = keyWords_.getKeyWordReturn();
        String string_ = _aliasString;
        String name_ = predefTypes_.getAliasEnumName();
        iterable_.append(public_).append(SPACE);
        iterable_.append(final_).append(SPACE);
        iterable_.append(string_).append(SPACE);
        iterable_.append(name_).append("()").append("{").append("\n");
        iterable_.append(return_).append(" ").append(static_).append("(");
        iterable_.append(coreNames_.getAliasEnums());
        iterable_.append(").").append(coreNames_.getAliasName());
        iterable_.append("(").append(this_).append(")");
        iterable_.append(endLine_).append("\n");
        iterable_.append("}").append("\n");
        String int_ = _aliasPrimInteger;
        String ordinal_ = predefTypes_.getAliasEnumOrdinal();
        iterable_.append(public_).append(SPACE);
        iterable_.append(final_).append(SPACE);
        iterable_.append(int_).append(SPACE);
        iterable_.append(ordinal_).append("()").append("{").append("\n");
        iterable_.append(return_).append(" ").append(static_).append("(");
        iterable_.append(coreNames_.getAliasEnums());
        iterable_.append(").").append(coreNames_.getAliasOrdinal());
        iterable_.append("(").append(this_).append(")");
        iterable_.append(endLine_).append("\n");
        iterable_.append("}").append("\n");
        iterable_.append("}").append("\n");
        return iterable_.toString();
    }

    public static String getBracedEnumParamType(AliasPredefinedTypes _predefTypes, KeyWords _keyWords) {
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String type_ = predefTypes_.getAliasEnumParam();
        String typeSup_ = predefTypes_.getAliasEnumType();
        String var_ = predefTypes_.getAliasEnumParamVar();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(type_).append("<").append(var_).append(":").append(type_).append("<").append(var_).append(">>:").append(typeSup_).append("{\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedSeedGeneratorType(AliasPredefinedTypes _predefTypes, KeyWords _keyWords, String _aliasPrimLong) {
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String type_ = predefTypes_.getAliasSeedGenerator();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(type_).append("{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        String tr_ = predefTypes_.getParams().getAliasSeedGenerator0Get0();
        iterable_.append(_aliasPrimLong).append(SPACE).append(predefTypes_.getAliasSeedGet()).append("(");
        iterable_.append(_aliasPrimLong).append(SPACE).append(tr_).append(");\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }

    public static String getBracedSeedDoubleGeneratorType(AliasPredefinedTypes _predefTypes, KeyWords _keyWords, String _aliasPrimDouble) {
        AliasPredefinedTypes predefTypes_ = _predefTypes;
        KeyWords keyWords_ = _keyWords;
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String type_ = predefTypes_.getAliasSeedDoubleGenerator();
        StringBuilder iterable_ = new StringBuilder(public_).append(SPACE);
        iterable_.append(interface_).append(SPACE);
        iterable_.append(type_).append("{\n");
        iterable_.append(public_).append(SPACE);
        iterable_.append(abstract_).append(SPACE);
        iterable_.append(_aliasPrimDouble).append(SPACE).append(predefTypes_.getAliasSeedGet()).append("();\n");
        iterable_.append("}\n");
        return iterable_.toString();
    }
}
