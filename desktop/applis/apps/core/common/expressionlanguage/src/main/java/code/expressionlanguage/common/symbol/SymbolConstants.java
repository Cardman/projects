package code.expressionlanguage.common.symbol;

import code.expressionlanguage.structs.Struct;

public final class SymbolConstants {
    public static final String AND_LONG = "&";
    public static final String AND_SHORT = "&&";
    public static final String OR_LONG = "|";
    public static final String OR_SHORT = "||";
    public static final String NULL_LONG = "?";
    public static final String NULL_SHORT = "??";
    public static final int SYMBOL_NULL_SAFE_PRIO = 0;
    public static final int SYMBOL_OR_PRIO = 1;
    public static final int SYMBOL_AND_PRIO = 2;
    public static final int SYMBOL_BIT_OR_PRIO = 3;
    public static final int SYMBOL_BIT_XOR_PRIO = 4;
    public static final int SYMBOL_BIT_AND_PRIO = 5;
    public static final int SYMBOL_EQ_PRIO = 6;
    public static final int SYMBOL_NON_EQ_PRIO = 7;
    public static final int SYMBOL_LT_PRIO = 8;
    public static final int SYMBOL_GT_PRIO = 9;
    public static final int SYMBOL_LE_PRIO = 10;
    public static final int SYMBOL_GE_PRIO = 11;
    public static final int SYMBOL_SHIFT_LEFT = 12;
    public static final int SYMBOL_SHIFT_RIGHT = 13;
    public static final int SYMBOL_BIT_SHIFT_LEFT = 14;
    public static final int SYMBOL_BIT_SHIFT_RIGHT = 15;
    public static final int SYMBOL_ROTATE_LEFT = 16;
    public static final int SYMBOL_ROTATE_RIGHT = 17;
    public static final int SYMBOL_SUM_PRIO = 18;
    public static final int SYMBOL_DIFF_PRIO = 19;
    public static final int SYMBOL_MULT_PRIO = 20;
    public static final int SYMBOL_DIV_PRIO = 21;
    public static final int SYMBOL_MOD_PRIO = 22;
    public static final int SYMBOL_ID_PRIO = 23;
    public static final int SYMBOL_OPPOSITE_PRIO = 24;
    public static final int SYMBOL_NEG_BOOL_PRIO = 25;
    public static final int SYMBOL_NEG_NUM_PRIO = 26;
    public static final int SYMBOL_MINUS_ONE_PRIO = 27;
    public static final int SYMBOL_PLUS_ONE_PRIO = 28;
    private SymbolConstants() {
    }
    public static Struct calculateOperator(CommonOperSymbol _symbol, Struct _first, Struct _second) {
        if (_symbol == null) {
            return null;
        }
        return _symbol.calculateOperator(_first, _second);
    }
}
