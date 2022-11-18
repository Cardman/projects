package aiki.beans.map.characters;

import org.junit.Test;

public final class AllyBeanTest extends InitDbCharacters {
    @Test
    public void getTeam1() {
        assertSizeEq(2,callAllyBeanTeamGet());
    }
    @Test
    public void getTeam2() {
        assertEq(18,callPkTrainerGetLevel(elt(callAllyBeanTeamGet(),0)));
    }
    @Test
    public void getTeam3() {
        assertEq(I_HEAL_HP_STATUS,callPkTrainerGetItem(elt(callAllyBeanTeamGet(),0)));
    }
    @Test
    public void getTeam4() {
        assertSizeEq(2,callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),0)));
    }
    @Test
    public void getTeam5() {
        assertEq(M_POK_04,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),0)),0));
    }
    @Test
    public void getTeam6() {
        assertEq(M_POK_05,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),0)),1));
    }
    @Test
    public void getTeam7() {
        assertEq(19,callPkTrainerGetLevel(elt(callAllyBeanTeamGet(),1)));
    }
    @Test
    public void getTeam8() {
        assertEq(I_HEAL_PP,callPkTrainerGetItem(elt(callAllyBeanTeamGet(),1)));
    }
    @Test
    public void getTeam9() {
        assertSizeEq(2,callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),1)));
    }
    @Test
    public void getTeam10() {
        assertEq(M_POK_06,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),1)),0));
    }
    @Test
    public void getTeam11() {
        assertEq(M_POK_07,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),1)),1));
    }
    @Test
    public void getImage1() {
        assertEq("AAABAAAQ",callAllyBeanGetImage(0));
    }
    @Test
    public void getImage2() {
        assertEq("AAABAAAR",callAllyBeanGetImage(1));
    }
    @Test
    public void getName1() {
        assertEq(P_POK_16_TR,callAllyBeanGetName(0));
    }
    @Test
    public void getName2() {
        assertEq(P_POK_17_TR,callAllyBeanGetName(1));
    }
    @Test
    public void getAbility1() {
        assertEq(A_ABILITY_TR,callAllyBeanGetAbility(0));
    }
    @Test
    public void getAbility2() {
        assertEq(A_ABILITY2_TR,callAllyBeanGetAbility(1));
    }
    @Test
    public void getItem1() {
        assertEq(I_HEAL_HP_STATUS_TR,callAllyBeanGetItem(0));
    }
    @Test
    public void getItem2() {
        assertEq(I_HEAL_PP_TR,callAllyBeanGetItem(1));
    }
    @Test
    public void getMove1() {
        assertEq(M_POK_04_TR,callAllyBeanGetMove(0,0));
    }
    @Test
    public void getMove2() {
        assertEq(M_POK_05_TR,callAllyBeanGetMove(0,1));
    }
    @Test
    public void getMove3() {
        assertEq(M_POK_06_TR,callAllyBeanGetMove(1,0));
    }
    @Test
    public void getMove4() {
        assertEq(M_POK_07_TR,callAllyBeanGetMove(1,1));
    }
}
