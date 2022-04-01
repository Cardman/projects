package aiki.beans.validators;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansValidatorsStd{
    public static final String TYPE_POSITIVE_RATE_VALIDATOR = "aiki.beans.validators.PositiveRateValidator";
    public static final String TYPE_RATE_VALIDATOR = "aiki.beans.validators.RateValidator";
    public static final String TYPE_SHORT_VALIDATOR = "aiki.beans.validators.ShortValidator";
    public static final String TYPE_UNSELECTED_RADIO = "aiki.beans.validators.UnselectedRadio";
    private AikiBeansValidatorsStd(){}
    public static void build(PokemonStandards _std) {
        buildPositiveRateValidator(_std);
        buildRateValidator(_std);
        buildShortValidator(_std);
        buildUnselectedRadio(_std);
    }
    private static void buildPositiveRateValidator(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_POSITIVE_RATE_VALIDATOR, fields_, methods_, BeanNatLgNames.TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_POSITIVE_RATE_VALIDATOR, type_);
    }
    private static void buildRateValidator(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_RATE_VALIDATOR, fields_, methods_, BeanNatLgNames.TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_RATE_VALIDATOR, type_);
    }
    private static void buildShortValidator(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_SHORT_VALIDATOR, fields_, methods_, BeanNatLgNames.TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_SHORT_VALIDATOR, type_);
    }
    private static void buildUnselectedRadio(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_UNSELECTED_RADIO, fields_, methods_, BeanNatLgNames.TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_UNSELECTED_RADIO, type_);
    }
}
