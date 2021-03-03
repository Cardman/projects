package code.maths.litteral;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public final class EvolvedMathFactory {

    private final LgInt maxRd = LgInt.getMaxLongPlusOne();

    public static StringList getFunctions() {
        StringList list_ = new StringList();
        list_.add(MbOperationNode.PUIS);
        list_.add(MbOperationNode.QUOT);
        list_.add(MbOperationNode.MOD);
        list_.add(MbOperationNode.MODTAUX);
        list_.add(MbOperationNode.ABS);
        list_.add(MbOperationNode.ENT);
        list_.add(MbOperationNode.TRONC);
        list_.add(MbOperationNode.NUM);
        list_.add(MbOperationNode.DEN);
        list_.add(MbOperationNode.MIN);
        list_.add(MbOperationNode.MAX);
        list_.add(MbOperationNode.MOY);
        list_.add(MbOperationNode.VAR);
        list_.add(MbOperationNode.CARAC_FERME);
        list_.add(MbOperationNode.CARAC_OUVERT);
        list_.add(MbOperationNode.CARAC_SEMI_OUVERT_G);
        list_.add(MbOperationNode.CARAC_SEMI_OUVERT_D);
        list_.add(MbOperationNode.CARAC_DROITE_OUVERT);
        list_.add(MbOperationNode.CARAC_DROITE_FERME);
        list_.add(MbOperationNode.CARAC_GAUCHE_OUVERT);
        list_.add(MbOperationNode.CARAC_GAUCHE_FERME);
        list_.add(MbOperationNode.SGN);
        list_.add(MbOperationNode.CARD);
        list_.add(MbOperationNode.INTER);
        list_.add(MbOperationNode.UNION);
        list_.add(MbOperationNode.COMPL);
        list_.add(MbOperationNode.INCL);
        list_.add(MbOperationNode.NON_INCL);
        list_.add(MbOperationNode.EQ_NUM);
        list_.add(MbOperationNode.NON_EQ_NUM);
        list_.add(MbOperationNode.DIV_FCT);
        return list_;
    }
    public static Rate evaluateDirectlyRate(String _numExp) {
        return MathUtil.processEl(_numExp, false, new StringMap<String>()).getRateVal();
    }

    public static boolean evaluateDirectlyBoolean(String _booleanExp) {
        return MathUtil.processEl(_booleanExp, false, new StringMap<String>()).isBoolVal();
    }

    public static EvolvedNumString createNumericableString(String _chaineNumerique,
                                                           StringMap<String> _vars) {
        return new EvolvedNumString(_chaineNumerique, _vars);
    }

    public static EvolvedBooleanString createBooleanString(String _chaineBooleenne,
                                                           StringMap<String> _vars) {
        return new EvolvedBooleanString(_chaineBooleenne, _vars);
    }

    public static String getTrueString() {
        return MbOperationNode.TRUE_STRING;
    }

    public static String getFalseString() {
        return MbOperationNode.FALSE_STRING;
    }

    public static char getSepartorSetChar() {
        return MbOperationNode.DELIMITER_STRING_SEP;
    }

    public static Rate evaluateNumericable(String _numericString, StringMap<String> _variables,
                                           Rate _default) {
        MbArgument argument_ = MathUtil.processEl(_numericString, false, _variables);
        Rate obj_ = argument_.getRateVal();
        if (argument_.getArgClass() == MathType.RATE) {
            return obj_;
        }
        return new Rate(_default);
    }

    public static Rate evaluatePositiveOrZeroExp(String _numericString,
                                                 StringMap<String> _variables, Rate _default) {
        MbArgument argument_ = MathUtil.processEl(_numericString, false, _variables);
        if (argument_.getArgClass() != MathType.RATE) {
            return _default.absNb();
        }
        Rate r_ = argument_.getRateVal();
        if (!r_.isZeroOrGt()) {
            return _default.absNb();
        }
        return r_;
    }

    public static Rate evaluatePositiveExp(String _numericString,
                                           StringMap<String> _variables, Rate _default) {
        MbArgument argument_ = MathUtil.processEl(_numericString, false, _variables);
        if (argument_.getArgClass() != MathType.RATE) {
            return _default.absNb();
        }
        Rate r_ = argument_.getRateVal();
        if (r_.isZero()) {
            return _default.absNb();
        }
        if (!r_.isZeroOrGt()) {
            return _default.absNb();
        }
        return r_;
    }

    public static boolean evaluateBoolean(String _booleanString,
                                          StringMap<String> _variables, boolean _default) {
        MbArgument argument_ = MathUtil.processEl(_booleanString, false, _variables);
        if (argument_.getArgClass() != MathType.BOOLEAN) {
            return _default;
        }
        return argument_.isBoolVal();
    }
    public LgInt getMaxRandomNb() {
        return maxRd;
    }
}
