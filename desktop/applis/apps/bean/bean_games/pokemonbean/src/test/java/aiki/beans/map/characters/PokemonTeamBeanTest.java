package aiki.beans.map.characters;

import org.junit.Test;

public final class PokemonTeamBeanTest extends InitDbCharacters {
    @Test
    public void getTeam1() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetTempTrainer());
    }
    @Test
    public void getTeam2() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetGymTrainer());
    }
    @Test
    public void getTeam3() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetGymLeader());
    }
    @Test
    public void getTeam4() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetTrainerLeague(0));
    }
    @Test
    public void getTeam5() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetTrainerLeague(1));
    }
    @Test
    public void getTeam6() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetMulti(0));
    }
    @Test
    public void getTeam7() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetMulti(1));
    }
    @Test
    public void getImage1() {
        assertEq("AAABAAAQ",callPokemonTeamBeanGetImageGymTrainer(0));
    }
    @Test
    public void getImage2() {
        assertEq("AAABAAAR",callPokemonTeamBeanGetImageGymTrainer(1));
    }
    @Test
    public void getImage3() {
        assertEq("AAABAAAO",callPokemonTeamBeanGetImageGymLeader(0));
    }
    @Test
    public void getImage4() {
        assertEq("AAABAAAP",callPokemonTeamBeanGetImageGymLeader(1));
    }
    @Test
    public void getImage5() {
        assertEq("AAABAAAO",callPokemonTeamBeanGetImageTempTrainer(0));
    }
    @Test
    public void getImage6() {
        assertEq("AAABAAAP",callPokemonTeamBeanGetImageTempTrainer(1));
    }
    @Test
    public void getImage7() {
        assertEq("AAABAAAM",callPokemonTeamBeanGetImageTrainerLeague(0,0));
    }
    @Test
    public void getImage8() {
        assertEq("AAABAAAN",callPokemonTeamBeanGetImageTrainerLeague(0,1));
    }
    @Test
    public void getImage9() {
        assertEq("AAABAAAU",callPokemonTeamBeanGetImageTrainerLeague(1,0));
    }
    @Test
    public void getImage10() {
        assertEq("AAABAAAV",callPokemonTeamBeanGetImageTrainerLeague(1,1));
    }
    @Test
    public void getImage11() {
        assertEq("AAABAAAI",callPokemonTeamBeanGetImageMulti(0,0));
    }
    @Test
    public void getImage12() {
        assertEq("AAABAAAJ",callPokemonTeamBeanGetImageMulti(0,1));
    }
    @Test
    public void getImage13() {
        assertEq("AAABAAAK",callPokemonTeamBeanGetImageMulti(1,0));
    }
    @Test
    public void getImage14() {
        assertEq("AAABAAAL",callPokemonTeamBeanGetImageMulti(1,1));
    }
    @Test
    public void getReward1() {
        assertEq(20,callPokemonTeamBeanRewardGet(0));
    }
    @Test
    public void getReward2() {
        assertEq(15,callPokemonTeamBeanRewardGet(1));
    }
    @Test
    public void getReward3() {
        assertEq(25,callPokemonTeamBeanRewardGet());
    }
    @Test
    public void getMultiplicity1() {
        assertEq(2, callPokemonTeamBeanMultiplicityGetMult());
    }
    @Test
    public void getMultiplicity2() {
        assertEq(1,callPokemonTeamBeanMultiplicityGet());
    }
}
