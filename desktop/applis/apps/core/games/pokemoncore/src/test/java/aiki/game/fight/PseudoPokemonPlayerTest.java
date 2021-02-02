package aiki.game.fight;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;


public class PseudoPokemonPlayerTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void new_PseudoPokemonPlayer_PokemonPlayer_1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setLevel((short) 3);
        pokemon_.setName(PTITARD);
        pokemon_.setAbility(ABSORB_EAU);
        pokemon_.setItem(MULTI_EXP);
        pokemon_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = new PokemonPlayer(pokemon_, data);
        pk_.setWonExpSinceLastLevel(new Rate("3/2"));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pk_);
        assertEq(PTITARD, pseudoPk_.getName());
        assertEq(3, pseudoPk_.getLevel());
        assertEq(MULTI_EXP, pseudoPk_.getItem());
        assertEq(new Rate("3/2"), pseudoPk_.getWonPointsSinceLastLevel());
    }
}
