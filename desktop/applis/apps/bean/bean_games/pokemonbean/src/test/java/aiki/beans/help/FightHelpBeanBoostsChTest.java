package aiki.beans.help;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import code.maths.Rate;
import code.util.LongTreeMap;
import org.junit.Test;

public final class FightHelpBeanBoostsChTest extends InitDbFightHelp {
    @Test
    public void init() {
        LongTreeMap<Rate> b_ = FightHelpBean.boostsChInit(1, 1, db().getData());
        assertEq(1,b_.size());
        assertEq(1,b_.getKey(0));
        assertEq(Rate.newRate("2"),b_.getValue(0));
    }
    @Test
    public void ini() {
        assertSizeEq(1,callFightHelpBeanBoostsChGet(bean(db())));
    }
    @Test
    public void form() {
        assertEq("b+1",callFightHelpBeanRateFormulaChGet(bean(db())));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().setRateBoostCriticalHit(VAR_PREFIX+ DataBase.DEF_BOOST+"+1");
        return f_;
    }
}
