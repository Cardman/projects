package aiki.beans.items.effects;
import aiki.beans.AikiBeansStd;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringMap;

public final class AikiBeansItemsEffectsStd {
    public static final String TYPE_EFFECT_END_ROUND_ITEM_BEAN = "aiki.beans.items.effects.EffectEndRoundItemBean";


    public static void build(BeanLgNames _std) {
        buildEffectEndRoundItemBean(_std);
    }
    private static void buildEffectEndRoundItemBean(BeanLgNames _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_ITEM_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_ITEM_BEAN, type_);
    }
}
