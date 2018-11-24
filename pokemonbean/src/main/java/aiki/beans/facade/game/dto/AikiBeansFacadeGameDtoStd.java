package aiki.beans.facade.game.dto;
import aiki.beans.PokemonStandards;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansFacadeGameDtoStd {
    public static final String TYPE_STATISTIC_INFO_PK_PLAYER = "aiki.beans.facade.game.dto.StatisticInfoPkPlayer";

    private static final String GET_NAME = "getName";
    private static final String GET_EV = "getEv";
    private static final String GET_IV = "getIv";
    private static final String GET_RATE = "getRate";

    public static void build(BeanLgNames _std) {
        buildStatisticInfoPkPlayer(_std);
    }
    private static void buildStatisticInfoPkPlayer(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_STATISTIC_INFO_PK_PLAYER, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_EV,params_,_std.getAliasPrimShort(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_IV,params_,_std.getAliasPrimShort(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_RATE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_STATISTIC_INFO_PK_PLAYER, type_);
    }
    public static ResultErrorStd invokeMethodStatisticInfoPkPlayer(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        StatisticInfoPkPlayer instance_ = (StatisticInfoPkPlayer) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_EV)) {
            res_.setResult(new ShortStruct(instance_.getEv()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IV)) {
            res_.setResult(new ShortStruct(instance_.getIv()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_RATE)) {
            res_.setResult(new StdStruct(instance_.getRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
}
