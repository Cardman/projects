package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import code.scripts.confs.*;
import org.junit.Test;

public final class EffectBeanTest extends InitDbMoveEffect {
    @Test
    public void isAdjAdv1() {
        assertFalse(callEffectBeanIsAdjAdv(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjAdv2() {
        assertTrue(callEffectBeanIsAdjAdv(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ADJ_ADV,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjMult1() {
        assertFalse(callEffectBeanIsAdjMult(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjMult2() {
        assertTrue(callEffectBeanIsAdjMult(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ADJ_MULT,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjUniq1() {
        assertFalse(callEffectBeanIsAdjUniq(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjUniq2() {
        assertTrue(callEffectBeanIsAdjUniq(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ADJ_UNIQ,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllie1() {
        assertFalse(callEffectBeanIsAllie(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllie2() {
        assertTrue(callEffectBeanIsAllie(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllies1() {
        assertFalse(callEffectBeanIsAllies(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllies2() {
        assertTrue(callEffectBeanIsAllies(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAnyFoe1() {
        assertFalse(callEffectBeanIsAnyFoe(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAnyFoe2() {
        assertTrue(callEffectBeanIsAnyFoe(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAutreUniq1() {
        assertFalse(callEffectBeanIsAutreUniq(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAutreUniq2() {
        assertTrue(callEffectBeanIsAutreUniq(dispMoveEffDamage(feedDbMoveDam(TargetChoice.AUTRE_UNIQ,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isGlobale1() {
        assertFalse(callEffectBeanIsGlobale(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isGlobale2() {
        assertTrue(callEffectBeanIsGlobale(dispMoveEffDamage(feedDbMoveDam(TargetChoice.GLOBALE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isLanceur1() {
        assertFalse(callEffectBeanIsLanceur(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isLanceur2() {
        assertTrue(callEffectBeanIsLanceur(dispMoveEffDamage(feedDbMoveDam(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isPseudoGlobale1() {
        assertFalse(callEffectBeanIsPseudoGlobale(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isPseudoGlobale2() {
        assertTrue(callEffectBeanIsPseudoGlobale(dispMoveEffDamage(feedDbMoveDam(TargetChoice.PSEUDO_GLOBALE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isTousAdv1() {
        assertFalse(callEffectBeanIsTousAdv(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isTousAdv2() {
        assertTrue(callEffectBeanIsTousAdv(dispMoveEffDamage(feedDbMoveDam(TargetChoice.TOUS_ADV,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isUniqueImporte1() {
        assertFalse(callEffectBeanIsUniqueImporte(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isUniqueImporte2() {
        assertTrue(callEffectBeanIsUniqueImporte(dispMoveEffDamage(feedDbMoveDam(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void indexGet() {
        assertEq(0,callEffectBeanIndexGet(dispMoveEffDamage(feedDbMoveDam(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void moveGet() {
        assertEq(M_DAM,callEffectBeanMoveGet(dispMoveEffDamage(feedDbMoveDam(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void needSuccessFirstEffect1() {
        assertTrue(callEffectBeanNeedSuccessFirstEffectGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", ""),0)));
    }
    @Test
    public void needSuccessFirstEffect2() {
        assertFalse(callEffectBeanNeedSuccessFirstEffectGet(dispMoveEffDamage(feedDbMoveDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void reasons1() {
        assertSizeEq(3,callEffectBeanReasonsGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", "("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")|"+ VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),0)));
    }
    @Test
    public void reasons2() {
        assertEq("("+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")",elt(callEffectBeanReasonsGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", "("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")|"+ VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),0)),0));
    }
    @Test
    public void reasons3() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectBeanReasonsGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", "("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")|"+ VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),0)),1));
    }
    @Test
    public void reasons4() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectBeanReasonsGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", "("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")|"+ VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),0)),2));
    }
    @Test
    public void mapFail1() {
        assertSizeEq(1,callEffectBeanMapVarsFailGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", "("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")|"+ VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),0)));
    }
    @Test
    public void mapFail2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectBeanMapVarsFailGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", "("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")|"+ VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),0)),0)));
    }
    @Test
    public void mapFail3() {
        assertEq(TIME,second(elt(callEffectBeanMapVarsFailGet(dispMoveEffDamage(feedDbMoveEffDamComp(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1", "("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +")|"+ VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +"|"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),0)),0)));
    }
    @Test
    public void refBase() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFF_HTML,callEffectBeanEffectBeanGet(dispMoveEffDamage(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
}
