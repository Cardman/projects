package aiki.game.fight;

import aiki.db.DataBase;
import aiki.game.params.*;
import org.junit.Test;

import aiki.fight.pokemon.NameLevel;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;


public class PseudoPlayerTest extends InitializationDataBase {

    @Test
    public void new_PseudoPlayer_List_List_1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setLevel( 3);
        pokemon_.setName(PTITARD);
        pokemon_.setAbility(ABSORB_EAU);
        pokemon_.setItem(MULTI_EXP);
        pokemon_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(),pokemon_);
        pk_.setWonExpSinceLastLevel(new Rate("3/2"));
        CustList<CustList<NameLevel>> evolutions_;
        evolutions_ = new CustList<CustList<NameLevel>>();
        evolutions_.add(new CustList<NameLevel>());
        evolutions_.first().add(new NameLevel(TETARTE,26));
        evolutions_.first().add(new NameLevel(TARTARD,27));
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
