package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class EffectDamageBeanTest extends InitDbMoveEffectDamage {

    @Test
    public void hasConstPower1() {
        assertFalse(callEffectDamageBeanHasConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,true,true,1,true,true,true)),0)));
    }
    @Test
    public void hasConstPower2() {
        assertTrue(callEffectDamageBeanHasConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void hasDeterminatedLawForDamage1() {
        assertTrue(callEffectDamageBeanHasDeterminatedLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void hasDeterminatedLawForDamage2() {
        assertFalse(callEffectDamageBeanHasDeterminatedLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one()),"1", LgInt.one())),0)));
    }
    @Test
    public void power1() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectDamageBeanPowerGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void hasLawForDamage1() {
        assertTrue(callEffectDamageBeanHasLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void hasLawForDamage2() {
        assertFalse(callEffectDamageBeanHasLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getDamageLaw1() {
        assertSizeEq(2,callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void getDamageLaw2() {
        assertEq("1",first(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)),0)));
    }
    @Test
    public void getDamageLaw3() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)),1)));
    }
    @Test
    public void getDamageLaw4() {
        assertEq(Rate.newRate("1/4"),second(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getDamageLaw5() {
        assertEq(Rate.newRate("3/4"),second(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.newLgInt("3"))),0)),1)));
    }
    @Test
    public void getDamageLaw6() {
        assertSizeEq(0,callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void getMapVarsDamage1() {
        assertSizeEq(1,callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void getMapVarsDamage2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.one())),0)),0)));
    }
    @Test
    public void getMapVarsDamage3() {
        assertEq(TIME,second(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getMapVarsDamage4() {
        assertSizeEq(1,callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,true,true,1,true,true,true)),0)));
    }
    @Test
    public void getMapVarsDamage5() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,true,true,1,true,true,true)),0)),0)));
    }
    @Test
    public void getMapVarsDamage6() {
        assertEq(TIME,second(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,true,true,1,true,true,true)),0)),0)));
    }
    @Test
    public void getHitsLaw1() {
        assertSizeEq(2,callEffectDamageBeanHitsLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withHitLawEv(withHitLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("5"), LgInt.one()),Rate.newRate("7"), LgInt.one())),0)));
    }
    @Test
    public void getHitsLaw2() {
        assertEq(5,first(elt(callEffectDamageBeanHitsLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withHitLawEv(withHitLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("7"), LgInt.one()),Rate.newRate("5"), LgInt.one())),0)),0)));
    }
    @Test
    public void getHitsLaw3() {
        assertEq(7,first(elt(callEffectDamageBeanHitsLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withHitLawEv(withHitLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("7"), LgInt.one()),Rate.newRate("5"), LgInt.one())),0)),1)));
    }
    @Test
    public void getHitsLaw4() {
        assertEq(Rate.newRate("1/4"),second(elt(callEffectDamageBeanHitsLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withHitLawEv(withHitLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("5"), LgInt.one()),Rate.newRate("7"), LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getHitsLaw5() {
        assertEq(Rate.newRate("3/4"),second(elt(callEffectDamageBeanHitsLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withHitLawEv(withHitLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("5"), LgInt.one()),Rate.newRate("7"), LgInt.newLgInt("3"))),0)),1)));
    }
    @Test
    public void getHitsLaw6() {
        assertSizeEq(0,callEffectDamageBeanHitsLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withHitLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("7"), LgInt.one())),0)));
    }
    @Test
    public void getNbHits() {
        assertEq(7,callEffectDamageBeanNbHitsGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withHitLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("7"), LgInt.one())),0)));
    }
    @Test
    public void getChLaw1() {
        assertSizeEq(2,callEffectDamageBeanChLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withChLawEv(withChLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("5"), LgInt.one()),Rate.newRate("7"), LgInt.one())),0)));
    }
    @Test
    public void getChLaw2() {
        assertEq(Rate.newRate("5"),first(elt(callEffectDamageBeanChLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withChLawEv(withChLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("7"), LgInt.one()),Rate.newRate("5"), LgInt.one())),0)),0)));
    }
    @Test
    public void getChLaw3() {
        assertEq(Rate.newRate("7"),first(elt(callEffectDamageBeanChLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withChLawEv(withChLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("7"), LgInt.one()),Rate.newRate("5"), LgInt.one())),0)),1)));
    }
    @Test
    public void getChLaw4() {
        assertEq(Rate.newRate("1/4"),second(elt(callEffectDamageBeanChLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withChLawEv(withChLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("5"), LgInt.one()),Rate.newRate("7"), LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getChLaw5() {
        assertEq(Rate.newRate("3/4"),second(elt(callEffectDamageBeanChLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withChLawEv(withChLawEv(effDam("1",true,true,1,true,true,true),Rate.newRate("5"), LgInt.one()),Rate.newRate("7"), LgInt.newLgInt("3"))),0)),1)));
    }
    @Test
    public void getConstDamage1() {
        assertFalse(callEffectDamageBeanConstDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,false)),0)));
    }
    @Test
    public void getConstDamage2() {
        assertTrue(callEffectDamageBeanConstDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getUserAttack1() {
        assertFalse(callEffectDamageBeanUserAttackGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,false,1,true,true,true)),0)));
    }
    @Test
    public void getUserAttack2() {
        assertTrue(callEffectDamageBeanUserAttackGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getTargetDefense1() {
        assertFalse(callEffectDamageBeanTargetDefenseGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",false,true,1,true,true,true)),0)));
    }
    @Test
    public void getTargetDefense2() {
        assertTrue(callEffectDamageBeanTargetDefenseGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getRandMax1() {
        assertFalse(callEffectDamageBeanRandMaxGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,false,true)),0)));
    }
    @Test
    public void getRandMax2() {
        assertTrue(callEffectDamageBeanRandMaxGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getSummingUserTeamOkFighter1() {
        assertFalse(callEffectDamageBeanSummingUserTeamOkFighterGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,false,true,true)),0)));
    }
    @Test
    public void getSummingUserTeamOkFighter2() {
        assertTrue(callEffectDamageBeanSummingUserTeamOkFighterGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getMultDamageAgainst1() {
        assertSizeEq(1,callEffectDamageBeanMultDamageAgainstGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)));
    }
    @Test
    public void getMultDamageAgainst2() {
        assertEq(C_CAT1_TR,first(elt(callEffectDamageBeanMultDamageAgainstGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)),0)));
    }
    @Test
    public void getMultDamageAgainst3() {
        assertEq(Rate.one(),second(elt(callEffectDamageBeanMultDamageAgainstGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)),0)));
    }
    @Test
    public void counterDamageCat1() {
        assertFalse(callEffectDamageBeanCounterDamageCat(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),C_CAT, LgInt.one())),0)));
    }
    @Test
    public void counterDamageCat2() {
        assertTrue(callEffectDamageBeanCounterDamageCat(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)));
    }
    @Test
    public void counterDamageCat3() {
        assertFalse(callEffectDamageBeanCounterDamageCat(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void constPower1() {
        assertFalse(callEffectDamageBeanConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),C_CAT, LgInt.one())),0)));
    }
    @Test
    public void constPower2() {
        assertTrue(callEffectDamageBeanConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void constPower3() {
        assertFalse(callEffectDamageBeanConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)));
    }
    @Test
    public void constPower4() {
        assertFalse(callEffectDamageBeanConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getChRate() {
        assertEq(1,callEffectDamageBeanChRateGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getStatisAtt() {
        assertEq(ST_ATT_TR,callEffectDamageBeanStatisAttGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getStatisDef() {
        assertEq(ST_DEF_TR,callEffectDamageBeanStatisDefGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getIgnVarStatTargetPos() {
        assertSizeEq(1,callEffectDamageBeanIgnVarStatTargetPosGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)));
    }
    @Test
    public void getTranslatedStatisTarget() {
        assertEq(ST_DEF_TR,callEffectDamageBeanGetTranslatedStatisTarget(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0),0));
    }
    @Test
    public void getIgnVarStatUserNeg() {
        assertSizeEq(1,callEffectDamageBeanIgnVarStatUserNegGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)));
    }
    @Test
    public void getTranslatedStatisUser() {
        assertEq(ST_ATT_TR,callEffectDamageBeanGetTranslatedStatisUser(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0),0));
    }
    @Test
    public void getBoostStatisOnceKoFoe1() {
        assertSizeEq(1,callEffectDamageBeanBoostStatisOnceKoFoeGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)));
    }
    @Test
    public void getBoostStatisOnceKoFoe2() {
        assertEq(1,second(elt(callEffectDamageBeanBoostStatisOnceKoFoeGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0)),0)));
    }
    @Test
    public void getTranslatedStatisKo() {
        assertEq(ST_SPEED_TR,callEffectDamageBeanGetTranslatedStatisKo(dispMoveEffDamage(feedDbMoveEffDataDamComp(withMultDamageAgainst(effDam("1",true,true,1,true,true,true),C_CAT, Rate.one())),0),0));
    }
}
