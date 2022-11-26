package aiki.beans.help;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.instances.Instances;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.util.IdMap;
import org.junit.Test;

public final class FightHelpBeanTest extends InitDbFightHelp{

    public static final String CC = "CC";
    @Test
    public void getTrStatistic() {
        assertEq(CC,callFightHelpBeanGetTrStatistic(bean(db()),0));
    }
    @Test
    public void getAnimStatistic() {
        assertEq("AAABAAAA",callFightHelpBeanGetAnimStatistic(bean(db()),0));
    }
    @Test
    public void getAnimAbsorb() {
        assertEq("AAABAAAA",callFightHelpBeanGetAnimAbsorb(bean(db())));
    }
    @Test
    public void getStab() {
        assertEq("1",callFightHelpBeanGetStab(bean(db())));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic,String>());
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK, CC);
        f_.getData().getAnimStatis().addEntry(Statistic.ATTACK.getStatName(), BaseSixtyFourUtil.getImageByString("AAABAAAA"));
        f_.getData().setAnimAbsorb(BaseSixtyFourUtil.getImageByString("AAABAAAA"));
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().addConstNumTest(DataBase.BONUS_BOOST, Rate.one());
        return f_;
    }
}
