package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.*;

public final class FacadeGameInventoryTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        game = game_;
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        facadeGame = facadeGame_;
    }

    @Test
    public void giveObject1Test() {
        game.getPlayer().getItem(MULTI_EXP);
        facadeGame.setContentOfNameItem(MULTI_EXP);
        facadeGame.searchObjectToUse();
        facadeGame.checkLineItem(0);
        facadeGame.setGivingObject(true);
        facadeGame.chooseObject();
        facadeGame.clearSortingItem();
        facadeGame.openMenu();
        assertTrue(facadeGame.getPlayer().getIndexesOfPokemonTeam().contains(0));
        facadeGame.selectPokemon((short) 0);
        assertEq(MULTI_EXP,game.getPlayer().getPokemonPlayerList().getValue(0).getItem());
        assertEq(LgInt.zero(),game.getPlayer().getInventory().getNumber(MULTI_EXP));
    }

    @Test
    public void giveObject2Test() {
        game.getPlayer().getItem(MULTI_EXP);
        facadeGame.setContentOfNameItem(MULTI_EXP);
        facadeGame.searchObjectToUse();
        facadeGame.checkLineItem(0);
        facadeGame.setGivingObject(true);
        facadeGame.chooseObject();
        facadeGame.clearSortingItem();
        facadeGame.openMenu();
        facadeGame.selectPokemon((short) 1);
        assertEq(NULL_REF,game.getPlayer().getPokemonPlayerList().getValue(0).getItem());
        assertEq(PIERRE_LUNE,game.getPlayer().getPokemonPlayerList().getValue(1).getItem());
        assertEq(LgInt.one(),game.getPlayer().getInventory().getNumber(MULTI_EXP));
    }

    @Test
    public void useObject1Test() {
        PokemonPlayer pokemonPlayer_ = game.getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        game.getPlayer().getItem(POTION);
        facadeGame.setContentOfNameItem(POTION);
        facadeGame.searchObjectToUse();
        facadeGame.checkLineItem(0);
        facadeGame.setGivingObject(false);
        facadeGame.chooseObject();
        facadeGame.clearSortingItem();
        assertTrue(facadeGame.usedObject());
        facadeGame.useObject();
        assertTrue(facadeGame.getPlayer().getIndexesOfPokemonTeam().contains(0));
        assertEq(POTION,facadeGame.getPlayer().getSelectedObject());
        facadeGame.openMenu();
        facadeGame.selectPokemon((short) 0);
        assertEq(new Rate("21"),pokemonPlayer_.getRemainingHp());
        assertEq(LgInt.zero(),game.getPlayer().getInventory().getNumber(POTION));
    }

    @Test
    public void useObject2Test() {
        PokemonPlayer pokemonPlayer_ = game.getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        game.getPlayer().getItem(POTION);
        facadeGame.setContentOfNameItem(POTION);
        facadeGame.searchObjectToUse();
        facadeGame.checkLineItem(0);
        facadeGame.setGivingObject(false);
        facadeGame.chooseObject();
        facadeGame.clearSortingItem();
        assertTrue(facadeGame.usedObject());
        facadeGame.useObject();
        assertTrue(facadeGame.getPlayer().getIndexesOfPokemonTeam().contains(0));
        assertEq(POTION,facadeGame.getPlayer().getSelectedObject());
        facadeGame.openMenu();
        facadeGame.exitInteract();
        assertEq(new Rate("1"),pokemonPlayer_.getRemainingHp());
        assertEq(LgInt.one(),game.getPlayer().getInventory().getNumber(POTION));
    }

    @Test
    public void useObject3Test() {
        game.getPlayer().getItem(PIERRE_LUNE);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        game.getPlayer().recevoirPokemon(givPk_, game.getDifficulty(), data);
        facadeGame.setContentOfNameItem(PIERRE_LUNE);
        facadeGame.searchObjectToUse();
        facadeGame.checkLineItem(0);
        facadeGame.setGivingObject(false);
        facadeGame.chooseObject();
        facadeGame.clearSortingItem();
        assertTrue(facadeGame.usedObject());
        facadeGame.useObject();
        assertTrue(facadeGame.getPlayer().getIndexesOfPokemonTeam().contains(2));
        assertEq(PIERRE_LUNE,facadeGame.getPlayer().getSelectedObject());
        facadeGame.openMenu();
        facadeGame.selectPokemon((short) 2);
        assertTrue(facadeGame.usedObjectForEvolving());
        assertEq(4, facadeGame.getKeptMovesToEvo().size());
        assertEq(1, facadeGame.getUnKeptMovesToEvo().size());
        assertEq(0, facadeGame.getPlayer().getNewAbilitiesToBeChosen().size());
        String move_ = facadeGame.getUnKeptMovesToEvo().first();
        facadeGame.addOrDeleteMoveEvo(move_);
        assertEq(5, facadeGame.getKeptMovesToEvo().size());
        assertEq(0, facadeGame.getUnKeptMovesToEvo().size());
        facadeGame.addOrDeleteMoveEvo(move_);
        assertEq(4, facadeGame.getKeptMovesToEvo().size());
        assertEq(1, facadeGame.getUnKeptMovesToEvo().size());
    }

}
