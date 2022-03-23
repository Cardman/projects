package aiki.beans.facade.game.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.RateStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansFacadeGameDtoStd {
    public static final String TYPE_STATISTIC_INFO_PK_PLAYER = "aiki.beans.facade.game.dto.StatisticInfoPkPlayer";

    private static final String GET_NAME = "getName";
    private static final String GET_EV = "getEv";
    private static final String GET_IV = "getIv";
    private static final String GET_RATE = "getRate";

    public static void build(PokemonStandards _std) {
        buildStatisticInfoPkPlayer(_std);
    }
    private static void buildStatisticInfoPkPlayer(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_STATISTIC_INFO_PK_PLAYER, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_EV,params_, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_IV,params_, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_RATE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_STATISTIC_INFO_PK_PLAYER, type_);
    }
    public static ResultErrorStd invokeMethodStatisticInfoPkPlayer(ClassMethodId _method, StatisticInfoPkPlayer _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(_inst.getName()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_EV)) {
            res_.setResult(new IntStruct(_inst.getEv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IV)) {
            res_.setResult(new IntStruct(_inst.getIv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_RATE)) {
            res_.setResult(new RateStruct(_inst.getRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
}
