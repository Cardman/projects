package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.UsesOfMove;
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
import static org.junit.Assert.assertTrue;

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
        assertTrue(facadeGame.isGivingObject());
        facadeGame.selectPokemon((short) 1);
        assertEq(NULL_REF,game.getPlayer().getPokemonPlayerList().getValue(0).getItem());
        assertEq(PIERRE_LUNE,game.getPlayer().getPokemonPlayerList().getValue(1).getItem());
        assertEq(LgInt.one(),game.getPlayer().getInventory().getNumber(MULTI_EXP));
    }

    @Test
    public void takeTest() {
        game.getPlayer().getItem(MULTI_EXP);
        facadeGame.setContentOfNameItem(MULTI_EXP);
        facadeGame.searchObjectToUse();
        facadeGame.checkLineItem(0);
        facadeGame.setGivingObject(true);
        facadeGame.chooseObject();
        facadeGame.clearSortingItem();
        facadeGame.openMenu();
        assertTrue(facadeGame.isGivingObject());
        facadeGame.selectPokemon((short) 1);
        facadeGame.setChosenTeamPokemon((short) 1);
        facadeGame.takeObjectFromTeam();
        assertEq(NULL_REF,game.getPlayer().getPokemonPlayerList().getValue(0).getItem());
        assertEq(NULL_REF,game.getPlayer().getPokemonPlayerList().getValue(1).getItem());
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
        assertTrue(!facadeGame.isGivingObject());
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
        assertNotNull(facadeGame.getSelectedPkTeam());
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

    @Test
    public void useObject4Test() {
        PokemonPlayer pokemonPlayer_ = game.getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        game.getPlayer().getItem(POTION);
        facadeGame.setChosenTeamPokemon((short) 0);
        facadeGame.setContentOfNameHealingItem(POTION);
        facadeGame.searchPokemonHealingItem();
        facadeGame.checkLineHealingItem(0);
        facadeGame.setGivingObject(false);
        facadeGame.setChosenHealingItemWalk();
        facadeGame.clearSortingHealingItem();
        facadeGame.getPlayer().getIndexesOfPokemonTeam().clear();
        facadeGame.useObject();
        facadeGame.selectPokemon((short) 0);
        assertEq(new Rate("21"),pokemonPlayer_.getRemainingHp());
        assertEq(LgInt.zero(),game.getPlayer().getInventory().getNumber(POTION));
    }

    @Test
    public void useObject5Test() {
        PokemonPlayer pokemonPlayer_ = game.getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        game.getPlayer().getItem(PP_PLUS);
        facadeGame.setChosenTeamPokemon((short) 0);
        facadeGame.setContentOfNameItem(PP_PLUS);
        facadeGame.searchObjectToUse();
        facadeGame.checkLineItem(0);
        facadeGame.setGivingObject(false);
        facadeGame.chooseObject();
        facadeGame.clearSortingItem();
        facadeGame.getPlayer().getIndexesOfPokemonTeam().clear();
        facadeGame.useObject();
        facadeGame.selectPokemon((short) 0);
        assertTrue(facadeGame.usedObjectForBoosting());
        assertEq(20,facadeGame.getPlayer().getPokemonPlayerList().firstValue().getMoves().getVal(JACKPOT).getMax());
        facadeGame.gainPpMax(JACKPOT);
        assertEq(23,facadeGame.getPlayer().getPokemonPlayerList().firstValue().getMoves().getVal(JACKPOT).getMax());
    }

    @Test
    public void useObject6Test() {
        PokemonPlayer pokemonPlayer_ = game.getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        game.getPlayer().getItem(HUILE);
        facadeGame.setChosenTeamPokemon((short) 0);
        facadeGame.setContentOfNameHealingItem(HUILE);
        facadeGame.searchPokemonHealingItem();
        facadeGame.checkLineHealingItem(0);
        facadeGame.setGivingObject(false);
        facadeGame.setChosenHealingItemWalk();
        facadeGame.clearSortingHealingItem();
        facadeGame.getPlayer().getIndexesOfPokemonTeam().clear();
        facadeGame.useObject();
        facadeGame.selectPokemon((short) 0);
        assertTrue(facadeGame.usedObjectForHealingAmove());
        facadeGame.healMove(JACKPOT);
        assertEq(10,pokemonPlayer_.getMoves().getVal(JACKPOT).getCurrent());
    }

    @Test
    public void evolveTest() {
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
        facadeGame.useObject();
        facadeGame.openMenu();
        facadeGame.selectPokemon((short) 2);
        String move_ = facadeGame.getUnKeptMovesToEvo().first();
        facadeGame.addOrDeleteMoveEvo(move_);
        facadeGame.addOrDeleteMoveEvo(move_);
        facadeGame.evolvePokemon();
        assertEq(MELODELFE, facadeGame.getPlayer().getPokemonPlayerList().getValue(2).getName());
    }
    @Test
    public void useTm1Test() {
        game.getPlayer().getTm((short)2);
        facadeGame.searchTmToUse();
        facadeGame.checkLineMove(0);
        facadeGame.chooseMoveByObject();
        facadeGame.clearSortingMove();
        assertTrue(!facadeGame.getPlayer().getSelectedMove().isEmpty());
        facadeGame.openMenu();
        assertTrue(facadeGame.getPlayer().getIndexesOfPokemonTeam().contains(0));
        facadeGame.choosePokemonForLearningMove((byte) 0);
    }
    @Test
    public void useTm2Test() {
        game.getPlayer().getPokemonPlayerList().getValue(0).getMoves().put(PISTOLET_A_O,new UsesOfMove((short) 20));
        game.getPlayer().getTm((short)2);
        facadeGame.searchTmToUse();
        facadeGame.checkLineMove(0);
        facadeGame.chooseMoveByObject();
        facadeGame.clearSortingMove();
        assertTrue(!facadeGame.getPlayer().getSelectedMove().isEmpty());
        facadeGame.openMenu();
        assertTrue(facadeGame.getPlayer().getIndexesOfPokemonTeam().contains(0));
        facadeGame.choosePokemonForLearningMove((byte) 0);
        assertTrue(!game.getPlayer().getPokemonPlayerList().getValue(0).getMoves().contains(TONNERRE));
        facadeGame.learnMove(PISTOLET_A_O);
        assertTrue(game.getPlayer().getPokemonPlayerList().getValue(0).getMoves().contains(TONNERRE));
    }
}
