package aiki.beans.validators;
import aiki.beans.PokemonStandards;
import code.bean.nat.SpecialNatClass;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.StandardConstructor;
import code.bean.nat.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;

public final class AikiBeansValidatorsStd {
    public static final String TYPE_POSITIVE_RATE_VALIDATOR = "aiki.beans.validators.PositiveRateValidator";
    public static final String TYPE_RATE_VALIDATOR = "aiki.beans.validators.RateValidator";
    public static final String TYPE_SHORT_VALIDATOR = "aiki.beans.validators.ShortValidator";
    public static final String TYPE_UNSELECTED_RADIO = "aiki.beans.validators.UnselectedRadio";


    public static void build(PokemonStandards _std) {
        buildPositiveRateValidator(_std);
        buildRateValidator(_std);
        buildShortValidator(_std);
        buildUnselectedRadio(_std);
    }
    private static void buildPositiveRateValidator(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POSITIVE_RATE_VALIDATOR, fields_, constructors_, methods_, BeanNatLgNames.TYPE_VALIDATOR, MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_POSITIVE_RATE_VALIDATOR, type_);
    }
    private static void buildRateValidator(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_RATE_VALIDATOR, fields_, constructors_, methods_, BeanNatLgNames.TYPE_VALIDATOR, MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_RATE_VALIDATOR, type_);
    }
    private static void buildShortValidator(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_SHORT_VALIDATOR, fields_, constructors_, methods_, BeanNatLgNames.TYPE_VALIDATOR, MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_SHORT_VALIDATOR, type_);
    }
    private static void buildUnselectedRadio(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_UNSELECTED_RADIO, fields_, constructors_, methods_, BeanNatLgNames.TYPE_VALIDATOR, MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_UNSELECTED_RADIO, type_);
    }
}
