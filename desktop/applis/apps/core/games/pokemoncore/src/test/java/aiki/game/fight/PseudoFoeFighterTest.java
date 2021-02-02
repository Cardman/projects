package aiki.game.fight;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.pokemon.PkTrainer;
import code.maths.Rate;
import code.util.StringList;


public class PseudoFoeFighterTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void new_PseudoFoeFighter_PokemonTrainer_1Test() {
        PseudoFoeFighter pseudoFoeFighter_;
        PkTrainer foe_ = new PkTrainer();
        foe_.setName(TARTARD);
        foe_.setAbility(ABSORB_EAU);
        foe_.setLevel((short) 10);
        foe_.setItem(NULL_REF);
        foe_.setMoves(new StringList(CHARGE));
        pseudoFoeFighter_ = new PseudoFoeFighter(foe_);
        assertEq(TARTARD, pseudoFoeFighter_.getName());
        assertEq(10, pseudoFoeFighter_.getLevel());
        assertTrue(!pseudoFoeFighter_.isFought());
    }

    @Test
    public void pointsFoe1Test() {
        PseudoFoeFighter pseudoFoeFighter_;
        PkTrainer foe_ = new PkTrainer();
        foe_.setName(TARTARD);
        foe_.setAbility(ABSORB_EAU);
        foe_.setLevel((short) 10);
        foe_.setItem(NULL_REF);
        foe_.setMoves(new StringList(CHARGE));
        pseudoFoeFighter_ = new PseudoFoeFighter(foe_);
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        diff_.setWinTrainerExp(new Rate("3/2"));
        diff_.setRateWinningExpPtsFight(Rate.one());
        Rate wonPoints_ = pseudoFoeFighter_.pointsFoe((byte) 2, diff_, data);
        assertEq(new Rate("5550"),wonPoints_);
    }
}
