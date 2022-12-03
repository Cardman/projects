package aiki.beans.facade.game.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
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
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, new StatisticInfoPkPlayerGetName()));
        methods_.add( new SpecNatMethod(GET_EV, BeanNatCommonLgNames.PRIM_INTEGER, new StatisticInfoPkPlayerGetEv()));
        methods_.add( new SpecNatMethod(GET_IV, BeanNatCommonLgNames.PRIM_INTEGER, new StatisticInfoPkPlayerGetIv()));
        methods_.add( new SpecNatMethod(GET_RATE,BeanNatCommonLgNames.TYPE_RATE, new StatisticInfoPkPlayerGetRate()));
        _std.getStds().addEntry(TYPE_STATISTIC_INFO_PK_PLAYER, type_);
    }
}
