package aiki.beans.facade.game.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansFacadeGameDtoStd{
    public static final String TYPE_STATISTIC_INFO_PK_PLAYER = "aiki.beans.facade.game.dto.StatisticInfoPkPlayer";
    private static final String GET_NAME = "getName";
    private static final String GET_EV = "getEv";
    private static final String GET_IV = "getIv";
    private static final String GET_RATE = "getRate";
    private AikiBeansFacadeGameDtoStd(){}
    public static void build(PokemonStandards _std) {
        buildStatisticInfoPkPlayer(_std);
    }
    private static void buildStatisticInfoPkPlayer(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_STATISTIC_INFO_PK_PLAYER, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new StatisticInfoPkPlayerGetName()));
        methods_.add( new SpecNatMethod(GET_EV, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL,new StatisticInfoPkPlayerGetEv()));
        methods_.add( new SpecNatMethod(GET_IV, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL,new StatisticInfoPkPlayerGetIv()));
        methods_.add( new SpecNatMethod(GET_RATE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new StatisticInfoPkPlayerGetRate()));
        _std.getStds().addEntry(TYPE_STATISTIC_INFO_PK_PLAYER, type_);
    }
}
