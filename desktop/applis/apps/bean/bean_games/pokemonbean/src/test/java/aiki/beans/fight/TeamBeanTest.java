package aiki.beans.fight;

import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class TeamBeanTest extends InitDbFight {
    @Test
    public void foeTeam1() {
        assertFalse(callTeamBeanFoeTeamGet(playerPath()));
    }
    @Test
    public void foeTeam2() {
        assertTrue(callTeamBeanFoeTeamGet(foePath()));
    }
    @Test
    public void members1() {
        assertSizeEq(8,callTeamBeanGetMembers(playerPath()));
    }
    @Test
    public void members2() {
        assertSizeEq(8,callTeamBeanGetMembers(foePath()));
    }
    @Test
    public void members3() {
        assertEq(0,eltInt(callTeamBeanGetMembers(playerPath()),0));
    }
    @Test
    public void members4() {
        assertEq(0,eltInt(callTeamBeanGetMembers(foePath()),0));
    }
    @Test
    public void members5() {
        assertEq(1,eltInt(callTeamBeanGetMembers(playerPath()),1));
    }
    @Test
    public void members6() {
        assertEq(1,eltInt(callTeamBeanGetMembers(foePath()),1));
    }
    @Test
    public void members7() {
        assertEq(2,eltInt(callTeamBeanGetMembers(playerPath()),2));
    }
    @Test
    public void members8() {
        assertEq(2,eltInt(callTeamBeanGetMembers(foePath()),2));
    }
    @Test
    public void members9() {
        assertEq(3,eltInt(callTeamBeanGetMembers(playerPath()),3));
    }
    @Test
    public void members10() {
        assertEq(3,eltInt(callTeamBeanGetMembers(foePath()),3));
    }
    @Test
    public void members11() {
        assertEq(4,eltInt(callTeamBeanGetMembers(playerPath()),4));
    }
    @Test
    public void members12() {
        assertEq(4,eltInt(callTeamBeanGetMembers(foePath()),4));
    }
    @Test
    public void members13() {
        assertEq(5,eltInt(callTeamBeanGetMembers(playerPath()),5));
    }
    @Test
    public void members14() {
        assertEq(5,eltInt(callTeamBeanGetMembers(foePath()),5));
    }
    @Test
    public void members15() {
        assertEq(6,eltInt(callTeamBeanGetMembers(playerPath()),6));
    }
    @Test
    public void members16() {
        assertEq(6,eltInt(callTeamBeanGetMembers(foePath()),6));
    }
    @Test
    public void members17() {
        assertEq(7,eltInt(callTeamBeanGetMembers(playerPath()),7));
    }
    @Test
    public void members18() {
        assertEq(7,eltInt(callTeamBeanGetMembers(foePath()),7));
    }
    @Test
    public void trLink1() {
        assertEq(PIKACHU_TR,callTeamBeanGetTrPokemonLink(playerPath(),0));
    }
    @Test
    public void trLink2() {
        assertEq(PIKACHU_TR,callTeamBeanGetTrPokemonLink(foePath(),0));
    }
    @Test
    public void trLink3() {
        assertEq(PIKA_2_TR,callTeamBeanGetTrPokemonLink(playerPath(),1));
    }
    @Test
    public void trLink4() {
        assertEq(PIKA_2_TR,callTeamBeanGetTrPokemonLink(foePath(),1));
    }
    @Test
    public void trLink5() {
        assertEq(PIKACHU_TR+" "+1,callTeamBeanGetTrPokemonLink(playerPath(),2));
    }
    @Test
    public void trLink6() {
        assertEq(PIKACHU_TR+" "+1,callTeamBeanGetTrPokemonLink(foePath(),2));
    }
    @Test
    public void trLink7() {
        assertEq(PIKA_2_TR +" "+1,callTeamBeanGetTrPokemonLink(playerPath(),3));
    }
    @Test
    public void trLink8() {
        assertEq(PIKA_2_TR +" "+1,callTeamBeanGetTrPokemonLink(foePath(),3));
    }
    @Test
    public void trLink9() {
        assertEq(PIKACHU_TR+" "+2,callTeamBeanGetTrPokemonLink(playerPath(),4));
    }
    @Test
    public void trLink10() {
        assertEq(PIKACHU_TR+" "+2,callTeamBeanGetTrPokemonLink(foePath(),4));
    }
    @Test
    public void trLink11() {
        assertEq(PIKA_2_TR +" "+2,callTeamBeanGetTrPokemonLink(playerPath(),5));
    }
    @Test
    public void trLink12() {
        assertEq(PIKA_2_TR +" "+2,callTeamBeanGetTrPokemonLink(foePath(),5));
    }
    @Test
    public void trLink13() {
        assertEq(PIKACHU_TR+" "+3,callTeamBeanGetTrPokemonLink(playerPath(),6));
    }
    @Test
    public void trLink14() {
        assertEq(PIKACHU_TR+" "+3,callTeamBeanGetTrPokemonLink(foePath(),6));
    }
    @Test
    public void trLink15() {
        assertEq(PIKA_2_TR +" "+3,callTeamBeanGetTrPokemonLink(playerPath(),7));
    }
    @Test
    public void trLink16() {
        assertEq(PIKA_2_TR +" "+3,callTeamBeanGetTrPokemonLink(foePath(),7));
    }
    @Test
    public void againstFoe1() {
        assertSizeEq(8,callTeamBeanPlayerFightersAgainstFoeGet(playerPath()));
    }
    @Test
    public void againstFoe2() {
        assertSizeEq(0,callTeamBeanPlayerFightersAgainstFoeGet(foePath()));
    }
    @Test
    public void againstFoe3() {
        assertEq(0,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),0)));
    }
    @Test
    public void againstFoe4() {
        assertEq(1,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),1)));
    }
    @Test
    public void againstFoe5() {
        assertEq(2,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),2)));
    }
    @Test
    public void againstFoe6() {
        assertEq(3,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),3)));
    }
    @Test
    public void againstFoe7() {
        assertEq(4,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),4)));
    }
    @Test
    public void againstFoe8() {
        assertEq(5,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),5)));
    }
    @Test
    public void againstFoe9() {
        assertEq(6,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),6)));
    }
    @Test
    public void againstFoe10() {
        assertEq(7,firstFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),7)));
    }
    @Test
    public void againstFoe11() {
        assertSizeEq(4,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),0)));
    }
    @Test
    public void againstFoe12() {
        assertSizeEq(4,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),1)));
    }
    @Test
    public void againstFoe13() {
        assertSizeEq(4,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),2)));
    }
    @Test
    public void againstFoe14() {
        assertSizeEq(4,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),3)));
    }
    @Test
    public void againstFoe15() {
        assertSizeEq(0,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),4)));
    }
    @Test
    public void againstFoe16() {
        assertSizeEq(0,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),5)));
    }
    @Test
    public void againstFoe17() {
        assertSizeEq(0,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),6)));
    }
    @Test
    public void againstFoe18() {
        assertSizeEq(0,secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),7)));
    }
    @Test
    public void againstFoe19() {
        assertEq(0,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),0)),0));
    }
    @Test
    public void againstFoe20() {
        assertEq(0,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),1)),0));
    }
    @Test
    public void againstFoe21() {
        assertEq(0,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),2)),0));
    }
    @Test
    public void againstFoe22() {
        assertEq(0,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),3)),0));
    }
    @Test
    public void againstFoe23() {
        assertEq(1,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),0)),1));
    }
    @Test
    public void againstFoe24() {
        assertEq(1,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),1)),1));
    }
    @Test
    public void againstFoe25() {
        assertEq(1,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),2)),1));
    }
    @Test
    public void againstFoe26() {
        assertEq(1,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),3)),1));
    }
    @Test
    public void againstFoe27() {
        assertEq(2,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),0)),2));
    }
    @Test
    public void againstFoe28() {
        assertEq(2,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),1)),2));
    }
    @Test
    public void againstFoe29() {
        assertEq(2,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),2)),2));
    }
    @Test
    public void againstFoe30() {
        assertEq(2,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),3)),2));
    }
    @Test
    public void againstFoe31() {
        assertEq(3,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),0)),3));
    }
    @Test
    public void againstFoe32() {
        assertEq(3,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),1)),3));
    }
    @Test
    public void againstFoe33() {
        assertEq(3,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),2)),3));
    }
    @Test
    public void againstFoe34() {
        assertEq(3,eltIntFighterAgainstFoes(secondFighterAgainstFoes(eltFighterAgainstFoes(callTeamBeanPlayerFightersAgainstFoeGet(playerPath()),3)),3));
    }
    @Test
    public void pkPlName1() {
        assertEq(PIKACHU_TR,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),0));
    }
    @Test
    public void pkPlName2() {
        assertEq(PIKA_2_TR,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),1));
    }
    @Test
    public void pkPlName3() {
        assertEq(PIKACHU_TR+" "+1,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),2));
    }
    @Test
    public void pkPlName4() {
        assertEq(PIKA_2_TR +" "+1,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),3));
    }
    @Test
    public void pkPlName5() {
        assertEq(PIKACHU_TR+" "+2,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),4));
    }
    @Test
    public void pkPlName6() {
        assertEq(PIKA_2_TR +" "+2,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),5));
    }
    @Test
    public void pkPlName7() {
        assertEq(PIKACHU_TR+" "+3,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),6));
    }
    @Test
    public void pkPlName8() {
        assertEq(PIKA_2_TR +" "+3,callTeamBeanGetPlayerFigtherAgainstFoe(playerPath(),7));
    }
    @Test
    public void pkFoeName1() {
        assertEq(PIKACHU_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),0,0));
    }
    @Test
    public void pkFoeName2() {
        assertEq(PIKACHU_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),1,0));
    }
    @Test
    public void pkFoeName3() {
        assertEq(PIKACHU_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),2,0));
    }
    @Test
    public void pkFoeName4() {
        assertEq(PIKACHU_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),3,0));
    }
    @Test
    public void pkFoeName5() {
        assertEq(PIKA_2_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),0,1));
    }
    @Test
    public void pkFoeName6() {
        assertEq(PIKA_2_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),1,1));
    }
    @Test
    public void pkFoeName7() {
        assertEq(PIKA_2_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),2,1));
    }
    @Test
    public void pkFoeName8() {
        assertEq(PIKA_2_TR,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),3,1));
    }
    @Test
    public void pkFoeName9() {
        assertEq(PIKACHU_TR+" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),0,2));
    }
    @Test
    public void pkFoeName10() {
        assertEq(PIKACHU_TR+" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),1,2));
    }
    @Test
    public void pkFoeName11() {
        assertEq(PIKACHU_TR+" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),2,2));
    }
    @Test
    public void pkFoeName12() {
        assertEq(PIKACHU_TR+" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),3,2));
    }
    @Test
    public void pkFoeName13() {
        assertEq(PIKA_2_TR +" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),0,3));
    }
    @Test
    public void pkFoeName14() {
        assertEq(PIKA_2_TR +" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),1,3));
    }
    @Test
    public void pkFoeName15() {
        assertEq(PIKA_2_TR +" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),2,3));
    }
    @Test
    public void pkFoeName16() {
        assertEq(PIKA_2_TR +" "+1,callTeamBeanGetFoeFigtherAgainstFoe(playerPath(),3,3));
    }
    @Test
    public void enMoves1() {
        assertSizeEq(1,callTeamBeanEnabledMovesGet(playerPath()));
    }
    @Test
    public void enMoves2() {
        assertEq(M_TEAM_TR,firstActivityOfMove(eltActivityOfMove(callTeamBeanEnabledMovesGet(playerPath()),0)));
    }
    @Test
    public void enMovesGr1() {
        assertSizeEq(1,callTeamBeanEnabledMovesByGroupGet(playerPath()));
    }
    @Test
    public void enMovesGr2() {
        assertSizeEq(2,firstActivityOfMove2(eltActivityOfMove2(callTeamBeanEnabledMovesByGroupGet(playerPath()),0)));
    }
    @Test
    public void enMovesGr3() {
        assertEq(M_TEAM_TR,elt(firstActivityOfMove2(eltActivityOfMove2(callTeamBeanEnabledMovesByGroupGet(playerPath()),0)),0));
    }
    @Test
    public void enMovesGr4() {
        assertEq(M_TEAM_SEND_TR,elt(firstActivityOfMove2(eltActivityOfMove2(callTeamBeanEnabledMovesByGroupGet(playerPath()),0)),1));
    }
    @Test
    public void getKey() {
        assertEq(M_TEAM_TR+ DataBase.SEPARATOR_MOVES +M_TEAM_SEND_TR,callTeamBeanGetKey(playerPath(),0));
    }
    @Test
    public void nbUses1() {
        assertSizeEq(1,callTeamBeanNbUsesMovesGet(playerPath()));
    }
    @Test
    public void nbUses2() {
        assertEq(M_USE_TR,firstStrNb(eltStrNb(callTeamBeanNbUsesMovesGet(playerPath()),0)));
    }
    @Test
    public void nbUses3() {
        assertEq(0,secondStrNb(eltStrNb(callTeamBeanNbUsesMovesGet(playerPath()),0)));
    }
    @Test
    public void sendFoe1() {
        assertSizeEq(1,callTeamBeanEnabledMovesWhileSendingFoeUsesGet(playerPath()));
    }
    @Test
    public void sendFoe2() {
        assertEq(M_TEAM_SEND_TR,firstLgInt(eltLgInt(callTeamBeanEnabledMovesWhileSendingFoeUsesGet(playerPath()),0)));
    }
    @Test
    public void sendFoe3() {
        assertEq(LgInt.zero(),secondLgInt(eltLgInt(callTeamBeanEnabledMovesWhileSendingFoeUsesGet(playerPath()),0)));
    }
    @Test
    public void heal1() {
        assertSizeEq(1,callTeamBeanTeamBeanHealAfterGet(playerPath()));
    }
    @Test
    public void heal2() {
        assertSizeEq(1,callTeamBeanTeamBeanHealAfterGet(foePath()));
    }
    @Test
    public void heal3() {
        assertEq(M_STACK_TR,firstHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(playerPath()),0)));
    }
    @Test
    public void heal4() {
        assertEq(M_STACK_TR,firstHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(foePath()),0)));
    }
    @Test
    public void heal5() {
        assertSizeEq(4,secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(playerPath()),0)));
    }
    @Test
    public void heal6() {
        assertSizeEq(4,secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(foePath()),0)));
    }
    @Test
    public void heal7() {
        assertEq(0,firstHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(playerPath()),0)),0)));
    }
    @Test
    public void heal8() {
        assertEq(0,firstHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(foePath()),0)),0)));
    }
    @Test
    public void heal9() {
        assertEq(1,callStacksOfUsesGetNbRounds(secondHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(playerPath()),0)),0))));
    }
    @Test
    public void heal10() {
        assertEq(0,callStacksOfUsesGetNbRounds(secondHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(foePath()),0)),0))));
    }
    @Test
    public void heal11() {
        assertTrue(callStacksOfUsesIsFirstStacked(secondHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(playerPath()),0)),0))));
    }
    @Test
    public void heal12() {
        assertFalse(callStacksOfUsesIsFirstStacked(secondHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(foePath()),0)),0))));
    }
    @Test
    public void heal13() {
        assertTrue(callStacksOfUsesIsLastStacked(secondHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(playerPath()),0)),0))));
    }
    @Test
    public void heal14() {
        assertFalse(callStacksOfUsesIsLastStacked(secondHeal2(eltHeal2(secondHeal(eltHeal(callTeamBeanTeamBeanHealAfterGet(foePath()),0)),0))));
    }
    @Test
    public void ant1() {
        assertSizeEq(1,callTeamBeanMovesAnticipationGet(playerPath()));
    }
    @Test
    public void ant2() {
        assertSizeEq(1,callTeamBeanMovesAnticipationGet(foePath()));
    }
    @Test
    public void ant3() {
        assertEq(M_ANT_TR,firstAnt(eltAnt(callTeamBeanMovesAnticipationGet(playerPath()),0)));
    }
    @Test
    public void ant4() {
        assertEq(M_ANT_TR,firstAnt(eltAnt(callTeamBeanMovesAnticipationGet(foePath()),0)));
    }
    @Test
    public void ant5() {
        assertSizeEq(4,secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(playerPath()),0)));
    }
    @Test
    public void ant6() {
        assertSizeEq(4,secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(foePath()),0)));
    }
    @Test
    public void ant7() {
        assertEq(0,firstAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(playerPath()),0)),0)));
    }
    @Test
    public void ant8() {
        assertEq(0,firstAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(foePath()),0)),0)));
    }
    @Test
    public void ant9() {
        assertEq(1,callAnticipationGetNbRounds(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(playerPath()),0)),0))));
    }
    @Test
    public void ant10() {
        assertEq(0,callAnticipationGetNbRounds(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(foePath()),0)),0))));
    }
    @Test
    public void ant11() {
        assertTrue(callAnticipationIsIncrementing(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(playerPath()),0)),0))));
    }
    @Test
    public void ant12() {
        assertFalse(callAnticipationIsIncrementing(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(foePath()),0)),0))));
    }
    @Test
    public void ant13() {
        assertEq(Fighter.BACK,callAnticipationGetTargetPositionValue(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(playerPath()),0)),0))));
    }
    @Test
    public void ant14() {
        assertEq(0,callAnticipationGetTargetPositionValue(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(foePath()),0)),0))));
    }
    @Test
    public void ant15() {
        assertEq(Rate.one(),callAnticipationGetDamage(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(playerPath()),0)),0))));
    }
    @Test
    public void ant16() {
        assertEq(Rate.zero(),callAnticipationGetDamage(secondAnt2(eltAnt2(secondAnt(eltAnt(callTeamBeanMovesAnticipationGet(foePath()),0)),0))));
    }
    @Test
    public void ant17() {
        assertTrue(callTeamBeanIsBackMovesAnticipationTeam(playerPath(),0,0));
    }
    @Test
    public void ant18() {
        assertFalse(callTeamBeanIsBackMovesAnticipationTeam(foePath(),0,0));
    }
    @Test
    public void ant19() {
        assertFalse(callTeamBeanIsFoeMovesAnticipationTeam(playerPath(),0,0));
    }
    @Test
    public void ant20() {
        assertTrue(callTeamBeanIsFoeMovesAnticipationTeam(foePath(),0,0));
    }
    @Test
    public void ant21() {
        assertFalse(callTeamBeanIsPlayerMovesAnticipationTeam(playerPath(),0,0));
    }
    @Test
    public void ant22() {
        assertTrue(callTeamBeanIsPlayerMovesAnticipationTeam(foePath(),0,1));
    }

    @Test
    public void clickFighter() {
        TeamBean fBean_ = playerPath();
        assertEq(CommonBean.WEB_FIGHT_HTML_FIGHTER_HTML,navigateTeamFighter(fBean_,1));
        assertTrue(fBean_.getForms().contains(NO_TEAM));
        assertTrue(fBean_.getForms().contains(NO_FIGHTER));
        assertEq(Fight.CST_PLAYER,fBean_.getForms().getValInt(NO_TEAM));
        assertEq(1,fBean_.getForms().getValInt(NO_FIGHTER));
    }
    private TeamBean foePath() {
        return beanTeam(Fight.CST_FOE);
    }

    private TeamBean playerPath() {
        return beanTeam(Fight.CST_PLAYER);
    }
}
