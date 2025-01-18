package aiki.beans.effects;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.*;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.*;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbEffects extends InitDbConstr {

    public static final String M_STA_00 = "M_STA_00";
    public static final String M_STA_01 = "M_STA_01";
    public static final String M_STA_02 = "M_STA_02";
    public static final String M_STA_03 = "M_STA_03";
    public static final String M_STA_00_TR = "M_STA_00_TR";
    public static final String M_STA_01_TR = "M_STA_01_TR";
    public static final String M_STA_02_TR = "M_STA_02_TR";
    public static final String M_STA_03_TR = "M_STA_03_TR";
    public static final String EV_TR = "EV_TR";
    public static void fwdComboDto(NaSt _update, NaSt _use) {
        callEffectComboBeanCombosSet(_update,callCombosBeanCombosGet(_use));
    }

    public static NaSt callEffectComboBeanCombosSet(NaSt _str, NaSt _args) {
        return BeanPokemonCommonTs.callStruct(new EffectComboBeanCombosSet(),_str,_args);
    }

    public static NaSt callCombosBeanCombosGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new CombosBeanCombosGet(),_str,_args);
    }

    public static NaSt callCombosBeanComboGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new CombosBeanComboGet(),_str,_args);
    }

    public static NaSt callCombosBeanGetCombosKey() {
        return BeanPokemonCommonTs.callLongs(new CombosBeanGetCombosKey(),dispAllCombos());
    }

    protected static NaSt dispAllCombos() {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToCombosSet(pk_);
        NaSt combos_ = all_.getVal(AikiBeansEffectsStd.BEAN_COMBOS);
        beforeDisplaying(combos_);
        return combos_;
    }

    protected static NaSt dispAllCombos(int _ind) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToCombosSet(pk_);
        NaSt combos_ = all_.getVal(AikiBeansEffectsStd.BEAN_COMBOS);
        NaSt combo_ = all_.getVal(AikiBeansEffectsStd.BEAN_COMBO);
        beforeDisplaying(combos_);
        callCombosBeanComboGet(combos_);
        fwdComboDto(combo_,combos_);
        callEffectComboBeanIndexSet(combo_,_ind);
        beforeDisplaying(combo_);
        return combo_;
    }

    public static NaSt callEffectComboBeanIndexSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EffectComboBeanIndexSet(),_str,_args);
    }

    public static StringMap<NaSt> beanToCombosSet(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        map_.addEntry(AikiBeansEffectsStd.BEAN_COMBO,_pk.beanEffectComboBean(EN));
        map_.addEntry(AikiBeansEffectsStd.BEAN_COMBOS,_pk.beanCombosBean(EN));
        return map_;
    }
    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_STA_00,moveSta(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(M_STA_01,moveSta(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(M_STA_02,moveSta(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(M_STA_03,moveSta(TargetChoice.ANY_FOE));
        Combos c_ = Instances.newCombos();
        EffectCombo co1_ = Instances.newEffectCombo();
        EffectTeam et_ = Instances.newEffectTeam();
        et_.getMultStatisticFoe().addEntry(Statistic.EVASINESS,Rate.one());
        co1_.getTeamMove().add(et_);
        co1_.setMultEvtRateSecEff(Rate.one());
        co1_.setRankIncrementNbRound(3);
        c_.getEffects().add(new ListEffectCombo(new StringList(M_STA_00,M_STA_01), co1_));
        EffectCombo co2_ = Instances.newEffectCombo();
        EffectEndRoundFoe ef_ = Instances.newEffectEndRoundFoe();
        ef_.setEndRoundRank(1);
        ef_.setFailEndRound(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        co2_.getEffectEndRound().add(ef_);
        co2_.setMultEvtRateSecEff(Rate.newRate("2"));
        co2_.setRankIncrementNbRound(4);
        c_.getEffects().add(new ListEffectCombo(new StringList(M_STA_00,M_STA_02), co2_));
        EffectCombo co3_ = Instances.newEffectCombo();
        co3_.getRepeatedRoundsLaw().addQuickEvent(Rate.newRate("5"),LgInt.newLgInt("15"));
        co3_.getRepeatedRoundsLaw().addQuickEvent(Rate.newRate("7"),LgInt.newLgInt("13"));
        c_.getEffects().add(new ListEffectCombo(new StringList(M_STA_00,M_STA_03), co3_));
        EffectCombo co4_ = Instances.newEffectCombo();
        c_.getEffects().add(new ListEffectCombo(new StringList(M_STA_01,M_STA_02), co4_));
        EffectCombo co5_ = Instances.newEffectCombo();
        c_.getEffects().add(new ListEffectCombo(new StringList(M_STA_01,M_STA_03), co5_));
        EffectCombo co6_ = Instances.newEffectCombo();
        c_.getEffects().add(new ListEffectCombo(new StringList(M_STA_02,M_STA_03), co6_));
        facade_.getData().setCombos(c_);
        facade_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        facade_.getData().completeVariables();
        facade_.getData().completeMembersCombos();
        facade_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA_00,M_STA_00_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA_01,M_STA_01_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA_02,M_STA_02_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA_03,M_STA_03_TR);
        facade_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic,String>());
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.EVASINESS, EV_TR);
        return facade_;
    }
}
