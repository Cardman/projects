package aiki.beans.facade;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringMap;

public final class AikiBeansFacadeStd {
    public static final String TYPE_FORMATTING = "aiki.beans.facade.Formatting";


    public static void build(BeanLgNames _std) {
        buildFormatting(_std);
    }
    private static void buildFormatting(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_FORMATTING, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_FORMATTING, type_);
    }
}
