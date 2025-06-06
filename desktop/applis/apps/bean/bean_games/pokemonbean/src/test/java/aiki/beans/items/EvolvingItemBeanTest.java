package aiki.beans.items;

import aiki.beans.CommonBean;
import org.junit.Test;

public final class EvolvingItemBeanTest extends InitDbItemEvolving {
    @Test
    public void getPokemon1() {
        assertSizeEq(1,callEvolvingItemBeanPokemonGet());
    }
    @Test
    public void getPokemon2() {
        assertEq(P_POKEMON,eltTkKey(callEvolvingItemBeanPokemonGet(),0));
    }
    @Test
    public void getTrPokemon() {
        assertEq(P_POKEMON_TR,callEvolvingItemBeanGetTrPokemon());
    }
    @Test
    public void clickPokemon1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callEvolvingItemBeanClickPokemon());
    }
    @Test
    public void clickPokemon2() {
        assertEq(P_POKEMON,callEvolvingItemBeanClickPokemonId());
    }

}
