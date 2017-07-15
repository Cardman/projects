package aiki.game.fight;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.BeforeClass;
import org.junit.Test;

import code.maths.Rate;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.PseudoPokemonPlayer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;

@SuppressWarnings("static-method")
public class PseudoPokemonPlayerTest extends InitializationDataBase {

    @BeforeClass
    public static void initDataBase() {
        InitializationDataBase.initDataBase();
    }

    @Test
    public void new_PseudoPokemonPlayer_PokemonPlayer_1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setLevel((short) 3);
        pokemon_.setName(PTITARD);
        pokemon_.setAbility(ABSORB_EAU);
        pokemon_.setItem(MULTI_EXP);
        pokemon_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = new PokemonPlayer(pokemon_, _data_);
        pk_.setWonExpSinceLastLevel(new Rate("3/2"));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pk_);
        assertEq(PTITARD, pseudoPk_.getName());
        assertEq(3, pseudoPk_.getLevel());
        assertEq(MULTI_EXP, pseudoPk_.getItem());
        assertEq(new Rate("3/2"), pseudoPk_.getWonPointsSinceLastLevel());
    }
}
