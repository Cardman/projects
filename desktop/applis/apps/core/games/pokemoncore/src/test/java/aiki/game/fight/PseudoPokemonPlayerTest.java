package aiki.game.fight;

import aiki.db.DataBase;
import aiki.game.params.*;
import org.junit.Test;

import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;


public class PseudoPokemonPlayerTest extends InitializationDataBase {

    @Test
    public void new_PseudoPokemonPlayer_PokemonPlayer_1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setLevel( 3);
        pokemon_.setName(PTITARD);
        pokemon_.setAbility(ABSORB_EAU);
        pokemon_.setItem(MULTI_EXP);
        pokemon_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(),pokemon_);
        pk_.setWonExpSinceLastLevel(new Rate("3/2"));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pk_);
        assertEq(PTITARD, pseudoPk_.getName());
        assertEq(3, pseudoPk_.getLevel());
        assertEq(MULTI_EXP, pseudoPk_.getItem());
        assertEq(new Rate("3/2"), pseudoPk_.getWonPointsSinceLastLevel());
    }
}
