package aiki.beans.items;

import aiki.beans.CommonBean;
import org.junit.Test;

public final class EvolvingStoneBeanTest extends InitDbItemEvolving {
    @Test
    public void getPokemon1() {
        assertSizeEq(1,callEvolvingStoneBeanPokemonGet());
    }
    @Test
    public void getPokemon2() {
        assertEq(P_POKEMON,eltTkKey(callEvolvingStoneBeanPokemonGet(),0));
    }
    @Test
    public void getTrPokemon() {
        assertEq(P_POKEMON_TR,callEvolvingStoneBeanGetTrPokemon());
    }
    @Test
    public void clickPokemon1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callEvolvingStoneBeanClickPokemon());
    }
    @Test
    public void clickPokemon2() {
        assertEq(P_POKEMON,callEvolvingStoneBeanClickPokemonId());
    }

}
