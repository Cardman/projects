package aiki.game.fight;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.Test;

import aiki.fight.pokemon.NameLevel;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;

@SuppressWarnings("static-method")
public class PseudoPlayerTest extends InitializationDataBase {

    @Test
    public void new_PseudoPlayer_List_List_1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setLevel((short) 3);
        pokemon_.setName(PTITARD);
        pokemon_.setAbility(ABSORB_EAU);
        pokemon_.setItem(MULTI_EXP);
        pokemon_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = new PokemonPlayer(pokemon_, _data_);
        pk_.setWonExpSinceLastLevel(new Rate("3/2"));
        CustList<EqList<NameLevel>> evolutions_;
        evolutions_ = new CustList<EqList<NameLevel>>();
        evolutions_.add(new EqList<NameLevel>());
        evolutions_.first().add(new NameLevel(TETARTE,(short)26));
        evolutions_.first().add(new NameLevel(TARTARD,(short)27));
        PseudoPlayer pseudoPlayer_;
        pseudoPlayer_ = new PseudoPlayer(new CustList<PokemonPlayer>(pk_), evolutions_);
        assertEq(1, pseudoPlayer_.getTeam().size());
        PseudoPokemonPlayer pseudoPk_ = pseudoPlayer_.getTeam().first();
        assertEq(PTITARD, pseudoPk_.getName());
        assertEq(3, pseudoPk_.getLevel());
        assertEq(MULTI_EXP, pseudoPk_.getItem());
        assertEq(new Rate("3/2"), pseudoPk_.getWonPointsSinceLastLevel());
        assertEq(0, pseudoPlayer_.getItems().size());
        assertEq(0, pseudoPlayer_.getUsedStones().size());
        assertEq(1, pseudoPlayer_.getEvolutions().size());
        assertEq(2, pseudoPlayer_.getEvolutions().first().size());
        assertEq(TETARTE, pseudoPlayer_.getEvolutions().first().first().getName());
        assertEq(26,pseudoPlayer_.getEvolutions().first().first().getLevel());
        assertEq(TARTARD, pseudoPlayer_.getEvolutions().first().get(1).getName());
        assertEq(27, pseudoPlayer_.getEvolutions().first().get(1).getLevel());
    }
}
