package aiki.beans.facade;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.stds.StandardConstructor;
import code.util.CustList;

public final class AikiBeansFacadeStd {
    public static final String TYPE_FORMATTING = "aiki.beans.facade.Formatting";


    public static void build(PokemonStandards _std) {
        buildFormatting(_std);
    }
    private static void buildFormatting(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_FORMATTING, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_FORMATTING, type_);
    }
}
