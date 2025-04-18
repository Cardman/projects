package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import code.util.StringMap;

public abstract class InitDbMoveEffectLightDisplay extends InitDbMoveEffect {
    protected static String dispEffectSwitchPosition() {
        return callEffectBeanMoveGet(dispMoveEffAccuracy(feedDbMoveEffDataAccuracy(Instances.newEffectSwitchPosition())));
    }
    protected static String dispMoveEffAccuracy() {
        return callEffectBeanMoveGet(dispMoveEffAccuracy(feedDbMoveEffDataAccuracy(Instances.newEffectAccuracy())));
    }
    protected static EffectBean dispMoveEffAccuracy(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectAccuracy();
        return transitEffectQuick(0,0,pk_,all_);
    }
//    public static StringMap<NaSt> beanToEffectAccuracy(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_ACCURACY,_pk.beanEffectBean(EN));
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_SWITCHPOSITION,_pk.beanEffectBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectAccuracy() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFACCURACY_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_ACCURACY);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOSITION_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHPOSITION);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataAccuracy(Effect _eff) {
        FacadeGame facade_ = facade();
        addEffAccuracy(facade_,_eff);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffAccuracy(FacadeGame _facade, Effect _eff) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        _facade.getData().completeMembers(M_DAM, dam_);
    }
}
