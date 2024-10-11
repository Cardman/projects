package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.items.HealingHp;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbHealingHp extends InitDbHealing {

    public static NaSt callHealingHpBeanHpGet() {
        return BeanPokemonCommonTs.callLongs(new HealingHpBeanHpGet(),ppDb());
    }
    public static StringMap<NaSt> beanToHealingHp(PkData _pk) {
        StringMap<NaSt> map_ = beanToHealing(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_HEALINGHP,_pk.beanHealingHpBean(EN));
        return map_;
    }
    protected static NaSt ppDb() {
        PkData pk_ = pkDataByFacade(feedDbHp());
        StringMap<NaSt> all_ = beanToHealingHp(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_HEALINGHP, pk_, all_);
    }

    private static FacadeGame feedDbHp() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        HealingHp h_ = Instances.newHealingHp();
        h_.setHp(Rate.one());
        facade_.getData().completeMembers(I_BASE,h_);
        facade_.getData().completeMembers(I_BALL,ball());
        facade_.getData().completeMembers(I_BOOST,boost());
        trsCore(facade_);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_BASE,I_BASE_TR);
        facade_.getData().getTranslatedClassesDescriptions().addEntry(LANGUAGE,new StringMap<String>());
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BASE).getItemType(),CI_BOOST_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BALL).getItemType(),CI_BALL_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().addConstNumTest(DataBase.MAX_EV, Rate.newRate("2"));
        facade_.getData().completeVariables();
        facade_.getData().getMiniItems().addEntry(I_BASE, instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_BALL, instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_BOOST, instance(IMG_MAX_RAI));
        return facade_;
    }
}
