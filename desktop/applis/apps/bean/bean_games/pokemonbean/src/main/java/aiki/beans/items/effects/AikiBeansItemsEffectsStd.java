package aiki.beans.items.effects;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansItemsEffectsStd{
    public static final String TYPE_EFFECT_END_ROUND_ITEM_BEAN = "aiki.beans.items.effects.EffectEndRoundItemBean";
    private AikiBeansItemsEffectsStd(){}
    public static void build(PokemonStandards _std) {
        buildEffectEndRoundItemBean(_std);
    }
    private static void buildEffectEndRoundItemBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_ITEM_BEAN, type_);
    }
}
