package aiki.beans;

import aiki.beans.abilities.AikiBeansAbilitiesStd;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.items.AikiBeansItemsStd;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.pokemon.AikiBeansPokemonStd;
import aiki.beans.simulation.AikiBeansSimulationStd;
import aiki.beans.status.AikiBeansStatusStd;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WelcomeBeanTest extends InitDbWelcome {
    @Test
    public void display1() {
        Struct bean_ = beanWelcome(feedDb());
        displaying(bean_);
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void display2() {
        Struct bean_ = beanWelcome(feedDbBase());
        displaying(bean_);
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void allMoves1() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertFalse(forms(bean_).contains(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void allMoves2() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(displaying(beanWelcome(feedDb())))));
    }
    @Test
    public void learntMoves() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateLearntMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertTrue(forms(bean_).contains(CST_LEARNT));
        assertTrue(forms(bean_).getValBool(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void notLearntMoves() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateNotLearntMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertTrue(forms(bean_).contains(CST_LEARNT));
        assertFalse(forms(bean_).getValBool(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void abilities() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansAbilitiesStd.WEB_HTML_ABILITY_ABILITIES_HTML,navigateAbilities(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_ABILITIES_SET));
        assertTrue(forms(bean_).getValAbilityData(CST_ABILITIES_SET).isEmpty());
    }
    @Test
    public void items() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansItemsStd.WEB_HTML_ITEMS_ITEMS_HTML,navigateItems(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        assertTrue(forms(bean_).getValItemData(CST_ITEMS_SET).isEmpty());
    }
    @Test
    public void pokedex() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML,navigatePokedex(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        assertTrue(forms(bean_).getValPokemonData(CST_POKEMON_SET).isEmpty());
    }
    @Test
    public void status() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansStatusStd.WEB_HTML_STATUS_STATUS_HTML,navigateStatus(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_STATUS_SET));
        assertTrue(forms(bean_).getValStatusData(CST_STATUS_SET).isEmpty());
    }
    @Test
    public void simulation() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,navigateSimulation(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_SIMULATION_STATE));
        assertSame(SimulationSteps.DIFF,forms(bean_).getValSimStep(CST_SIMULATION_STATE));
    }
}
