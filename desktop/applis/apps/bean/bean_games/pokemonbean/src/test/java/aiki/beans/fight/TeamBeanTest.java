package aiki.beans.fight;

import aiki.beans.InitDbFight;
import aiki.beans.PkFight;
import aiki.facade.FacadeGame;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import org.junit.Test;

public final class TeamBeanTest extends InitDbFight {
    @Test
    public void foeTeam1() {
        assertFalse(callTeamBeanFoeTeamGet(beanTeam(clickPlayerCaller())));
    }
    @Test
    public void foeTeam2() {
        assertTrue(callTeamBeanFoeTeamGet(beanTeam(clickFoeCaller())));
    }

    private Struct beanTeam(NatCaller _caller) {
        FacadeGame facade_ = facade(dbTeam());
        PkFight stds_ = new PkFight();
        Struct bFigtht_ = beanFight(stds_,EN, facade_);
        Struct bTeam_ = beanTeam(stds_,EN, facade_);
        transit(stds_, _caller,displaying(bFigtht_),bTeam_);
        return bTeam_;
    }
}
