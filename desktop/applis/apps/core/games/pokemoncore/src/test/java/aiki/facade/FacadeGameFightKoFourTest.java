package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.Fighter;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionMove;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.util.core.BoolVal;
import org.junit.Test;

public final class FacadeGameFightKoFourTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(CHARGE, HATE, data_);
        game_.initTrainerFight(data_);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }

    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.chooseFrontFighter( 0);
        facadeGame_.chooseMove(CHARGE);
        facadeGame_.setFirstChosenMovePlayerTarget( 1);
        AbstractAction action_;
        action_ = facadeGame_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertEq(CHARGE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ONE), ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(2, facadeGame_.getFight().getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, facadeGame_.getFight().getTemp().getChosableFoeTargets().get(0).getChosable());
        assertSame(BoolVal.TRUE, facadeGame_.getFight().getTemp().getChosableFoeTargets().get(1).getChosable());
        assertEq(2, facadeGame_.getFight().getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE, facadeGame_.getFight().getTemp().getChosablePlayerTargets().get(0).getChosable());
        assertSame(BoolVal.TRUE, facadeGame_.getFight().getTemp().getChosablePlayerTargets().get(1).getChosable());
    }

}
