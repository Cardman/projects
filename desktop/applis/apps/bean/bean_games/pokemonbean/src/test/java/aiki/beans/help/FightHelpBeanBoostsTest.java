package aiki.beans.help;

import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import code.maths.Rate;
import code.util.LongTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanBoostsTest extends InitDbFightHelp {
    @Test
    public void init() {
        LongTreeMap<Rate> b_ = FightHelpBean.boostsInit(1, 1, db().getData());
        assertEq(1,b_.size());
        assertEq(1,b_.getKey(0));
        assertEq(Rate.newRate("2"),b_.getValue(0));
    }
    @Test
    public void ini() {
        assertSizeEq(1,callFightHelpBeanBoostsGet(bean(db())));
    }
    @Test
    public void form() {
        assertEq("b+1",callFightHelpBeanRateFormulaGet(bean(db())));
    }
    @Test
    public void varBoost() {
        assertEq("b",callFightHelpBeanBoostVarGet(bean(db())));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().setRateBoost(VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+"+1");
        StringMap<String> litteral_ = new StringMap<String>();
        litteral_.addEntry(MessagesDataBaseConstants.DEF_BOOST, StringUtil.concat("_",TAB,"b",TAB,"_"));
        f_.getData().getLitterals().addEntry(LANGUAGE,litteral_);
        return f_;
    }
}
