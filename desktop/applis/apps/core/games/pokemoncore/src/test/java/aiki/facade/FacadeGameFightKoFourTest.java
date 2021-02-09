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
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import org.junit.Test;

public final class FacadeGameFightKoFourTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
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
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(CHARGE);
        facadeGame_.setFirstChosenMovePlayerTarget((byte) 1);
        AbstractAction action_;
        action_ = facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(CHARGE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ONE, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(2, facadeGame_.getFight().getChosableFoeTargets().size());
        assertTrue(facadeGame_.getFight().getChosableFoeTargets().get(0));
        assertTrue(facadeGame_.getFight().getChosableFoeTargets().get(1));
        assertEq(2, facadeGame_.getFight().getChosablePlayerTargets().size());
        assertTrue(!facadeGame_.getFight().getChosablePlayerTargets().get(0));
        assertTrue(facadeGame_.getFight().getChosablePlayerTargets().get(1));
    }

    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }
    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

}
