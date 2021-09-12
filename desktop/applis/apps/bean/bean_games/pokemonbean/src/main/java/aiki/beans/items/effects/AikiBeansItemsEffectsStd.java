package aiki.beans.items.effects;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.stds.StandardConstructor;
import code.util.CustList;

public final class AikiBeansItemsEffectsStd {
    public static final String TYPE_EFFECT_END_ROUND_ITEM_BEAN = "aiki.beans.items.effects.EffectEndRoundItemBean";


    public static void build(PokemonStandards _std) {
        buildEffectEndRoundItemBean(_std);
    }
    private static void buildEffectEndRoundItemBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_ITEM_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_ITEM_BEAN, type_);
    }
}
