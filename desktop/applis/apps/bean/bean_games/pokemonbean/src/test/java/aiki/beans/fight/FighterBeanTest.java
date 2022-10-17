package aiki.beans.fight;

import aiki.beans.InitDbFight;
import aiki.beans.PkFight;
import aiki.facade.FacadeGame;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import org.junit.Test;

public final class FighterBeanTest extends InitDbFight {

    @Test
    public void name() {
        assertEq(PIKACHU_TR,callFighterBeanNameGet(playerPath(0)));
    }

    private Struct foePath(long..._args) {
        return beanFighter(clickFoeCaller(),_args);
    }

    private Struct playerPath(long..._args) {
        return beanFighter(clickPlayerCaller(),_args);
    }

    private Struct beanFighter(NatCaller _caller,long..._args) {
        FacadeGame facade_ = facadeFighters(dbFighter());
        PkFight stds_ = new PkFight();
        Struct bFighter_ = beanFighter(stds_,EN, facade_);
        Struct bTeam_ = beanTeam(stds_,_caller,facade_);
        transit(stds_, clickTeamFighterCaller(),bTeam_,bFighter_,_args);
        return bFighter_;
    }
}
