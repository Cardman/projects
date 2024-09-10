package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;

public abstract class InitDbMoveEffectEndRound extends InitDbMoveEffect {

    public static NaSt callEffectEndRoundMoveBeanEndRoundRankGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundMoveBeanEndRoundRankGet(),_str,_args);
    }

    public static NaSt callEffectEndRoundMoveBeanMapVarsFailEndRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundMoveBeanMapVarsFailEndRoundGet(),_str,_args);
    }

    public static NaSt callEffectEndRoundMoveBeanReasonsEndRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundMoveBeanReasonsEndRoundGet(),_str,_args);
    }
    protected static NaSt dispMoveEffEndRound() {
        return dispMoveEffEndRound(feedDbMoveEffDataDam());
    }
    protected static NaSt dispMoveEffEndRound(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectEndRound(pk_);
        StringMap<String> mapping_ = mappingToEffectEndRound();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectEndRound(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_ENDROUND,_pk.beanEffectEndRoundMoveBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectEndRound() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFENDROUND_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_ENDROUND);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataDam() {
        FacadeGame facade_ = facade();
        addEff(facade_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        EffectSwitchTypes sw_ = Instances.newEffectSwitchTypes();
        sw_.getChgtTypeByEnv().addEntry(EnvironmentType.ROAD,T_TYPE1);
        chg_.getEffects().add(sw_);
        facade_.getData().completeMembers(M_STA, chg_);
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        EffectInvoke inv_ = Instances.newEffectInvoke();
        inv_.getMoveFctEnv().addEntry(EnvironmentType.ROAD,T_TYPE1);
        minv_.getEffects().add(inv_);
        facade_.getData().completeMembers(M_WEA, minv_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEff(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, eff());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectEndRoundIndividual eff() {
        EffectEndRoundIndividual e_ = Instances.newEffectEndRoundIndividual();
        e_.setFailEndRound(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.setEndRoundRank(1);
        return e_;
    }
}
