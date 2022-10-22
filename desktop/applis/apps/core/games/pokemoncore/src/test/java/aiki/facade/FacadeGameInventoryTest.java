package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class FacadeGameInventoryTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data_);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        return facadeGame_;
    }

    @Test
    public void giveObject1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getItem(MULTI_EXP);
        facadeGame_.setContentOfNameItem(MULTI_EXP);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(true);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        facadeGame_.openMenu();
        assertTrue(facadeGame_.getPlayer().getIndexesOfPokemonTeam().contains(0));
        facadeGame_.selectPokemon((short) 0);
        assertEq(MULTI_EXP,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getItem());
        assertEq(LgInt.zero(),facadeGame_.getGame().getPlayer().getInventory().getNumber(MULTI_EXP));
    }

    @Test
    public void giveObject2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getItem(MULTI_EXP);
        facadeGame_.setContentOfNameItem(MULTI_EXP);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(true);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        facadeGame_.openMenu();
        assertTrue(facadeGame_.isGivingObject());
        facadeGame_.selectPokemon((short) 1);
        assertEq(NULL_REF,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getItem());
        assertEq(PIERRE_LUNE,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(1).getItem());
        assertEq(LgInt.one(),facadeGame_.getGame().getPlayer().getInventory().getNumber(MULTI_EXP));
    }

    @Test
    public void takeTest() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getItem(MULTI_EXP);
        facadeGame_.setContentOfNameItem(MULTI_EXP);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(true);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        facadeGame_.openMenu();
        assertTrue(facadeGame_.isGivingObject());
        facadeGame_.selectPokemon((short) 1);
        facadeGame_.setChosenTeamPokemon((short) 1);
        facadeGame_.takeObjectFromTeam();
        assertEq(NULL_REF,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getItem());
        assertEq(NULL_REF,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(1).getItem());
    }
    @Test
    public void useObject1Test() {
        FacadeGame facadeGame_ = initTests();
        PokemonPlayer pokemonPlayer_ = facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        facadeGame_.getGame().getPlayer().getItem(POTION);
        facadeGame_.setContentOfNameItem(POTION);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(false);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        assertTrue(!facadeGame_.isGivingObject());
        assertTrue(facadeGame_.usedObject());
        facadeGame_.useObject();
        assertTrue(facadeGame_.getPlayer().getIndexesOfPokemonTeam().contains(0));
        assertEq(POTION,facadeGame_.getPlayer().getSelectedObject());
        facadeGame_.openMenu();
        facadeGame_.selectPokemon((short) 0);
        assertEq(new Rate("21"),pokemonPlayer_.getRemainingHp());
        assertEq(LgInt.zero(),facadeGame_.getGame().getPlayer().getInventory().getNumber(POTION));
    }

    @Test
    public void useObject2Test() {
        FacadeGame facadeGame_ = initTests();
        PokemonPlayer pokemonPlayer_ = facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        facadeGame_.getGame().getPlayer().getItem(POTION);
        facadeGame_.setContentOfNameItem(POTION);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(false);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        assertTrue(facadeGame_.usedObject());
        facadeGame_.useObject();
        assertTrue(facadeGame_.getPlayer().getIndexesOfPokemonTeam().contains(0));
        assertEq(POTION,facadeGame_.getPlayer().getSelectedObject());
        facadeGame_.openMenu();
        facadeGame_.exitInteract();
        assertEq(new Rate("1"),pokemonPlayer_.getRemainingHp());
        assertEq(LgInt.one(),facadeGame_.getGame().getPlayer().getInventory().getNumber(POTION));
    }

    @Test
    public void useObject3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getItem(PIERRE_LUNE);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        facadeGame_.getGame().getPlayer().recevoirPokemon(givPk_, facadeGame_.getGame().getDifficulty(), facadeGame_.getData());
        facadeGame_.setContentOfNameItem(PIERRE_LUNE);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(false);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        assertTrue(facadeGame_.usedObject());
        facadeGame_.useObject();
        assertTrue(facadeGame_.getPlayer().getIndexesOfPokemonTeam().contains(2));
        assertEq(PIERRE_LUNE,facadeGame_.getPlayer().getSelectedObject());
        facadeGame_.openMenu();
        facadeGame_.selectPokemon((short) 2);
        assertNotNull(facadeGame_.getPlayer().getSelectedPkTeam());
        assertTrue(facadeGame_.usedObjectForEvolving());
        assertEq(4, facadeGame_.getKeptMovesToEvo().size());
        assertEq(1, facadeGame_.getUnKeptMovesToEvo().size());
        assertEq(0, facadeGame_.getPlayer().getNewAbilitiesToBeChosen().size());
        String move_ = facadeGame_.getUnKeptMovesToEvo().first();
        facadeGame_.addOrDeleteMoveEvo(move_);
        assertEq(5, facadeGame_.getKeptMovesToEvo().size());
        assertEq(0, facadeGame_.getUnKeptMovesToEvo().size());
        facadeGame_.addOrDeleteMoveEvo(move_);
        assertEq(4, facadeGame_.getKeptMovesToEvo().size());
        assertEq(1, facadeGame_.getUnKeptMovesToEvo().size());
    }

    @Test
    public void useObject4Test() {
        FacadeGame facadeGame_ = initTests();
        PokemonPlayer pokemonPlayer_ = facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        facadeGame_.getGame().getPlayer().getItem(POTION);
        facadeGame_.setChosenTeamPokemon((short) 0);
        facadeGame_.setContentOfNameHealingItem(POTION);
        facadeGame_.searchPokemonHealingItem();
        facadeGame_.checkLineHealingItem(0);
        facadeGame_.setGivingObject(false);
        facadeGame_.setChosenHealingItemWalk();
        facadeGame_.clearSortingHealingItem();
        facadeGame_.getPlayer().getIndexesOfPokemonTeam().clear();
        facadeGame_.useObject();
        facadeGame_.selectPokemon((short) 0);
        assertEq(new Rate("21"),pokemonPlayer_.getRemainingHp());
        assertEq(LgInt.zero(),facadeGame_.getGame().getPlayer().getInventory().getNumber(POTION));
    }

    @Test
    public void useObject5Test() {
        FacadeGame facadeGame_ = initTests();
        PokemonPlayer pokemonPlayer_ = facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.setRemainingHp(Rate.one());
        facadeGame_.getGame().getPlayer().getItem(PP_PLUS);
        facadeGame_.setChosenTeamPokemon((short) 0);
        facadeGame_.setContentOfNameItem(PP_PLUS);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(false);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        facadeGame_.getPlayer().getIndexesOfPokemonTeam().clear();
        facadeGame_.useObject();
        facadeGame_.selectPokemon((short) 0);
        assertTrue(facadeGame_.usedObjectForBoosting());
        assertEq(20,facadeGame_.getPlayer().getPokemonPlayerList().firstValue().getMoves().getVal(JACKPOT).getMax());
        facadeGame_.gainPpMax(JACKPOT);
        assertEq(23,facadeGame_.getPlayer().getPokemonPlayerList().firstValue().getMoves().getVal(JACKPOT).getMax());
    }

    @Test
    public void useObject6Test() {
        FacadeGame facadeGame_ = initTests();
        PokemonPlayer pokemonPlayer_ = facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pokemonPlayer_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        facadeGame_.getGame().getPlayer().getItem(HUILE);
        facadeGame_.setChosenTeamPokemon((short) 0);
        facadeGame_.setContentOfNameHealingItem(HUILE);
        facadeGame_.searchPokemonHealingItem();
        facadeGame_.checkLineHealingItem(0);
        facadeGame_.setGivingObject(false);
        facadeGame_.setChosenHealingItemWalk();
        facadeGame_.clearSortingHealingItem();
        facadeGame_.getPlayer().getIndexesOfPokemonTeam().clear();
        facadeGame_.useObject();
        facadeGame_.selectPokemon((short) 0);
        assertTrue(facadeGame_.usedObjectForHealingAmove());
        facadeGame_.healMove(JACKPOT);
        assertEq(10,pokemonPlayer_.getMoves().getVal(JACKPOT).getCurrent());
    }

    @Test
    public void evolveTest() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getItem(PIERRE_LUNE);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        facadeGame_.getGame().getPlayer().recevoirPokemon(givPk_, facadeGame_.getGame().getDifficulty(), facadeGame_.getData());
        facadeGame_.setContentOfNameItem(PIERRE_LUNE);
        facadeGame_.searchObjectToUse();
        facadeGame_.checkLineItem(0);
        facadeGame_.setGivingObject(false);
        facadeGame_.chooseObject();
        facadeGame_.clearSortingItem();
        facadeGame_.useObject();
        facadeGame_.openMenu();
        facadeGame_.selectPokemon((short) 2);
        String move_ = facadeGame_.getUnKeptMovesToEvo().first();
        facadeGame_.addOrDeleteMoveEvo(move_);
        facadeGame_.addOrDeleteMoveEvo(move_);
        facadeGame_.evolvePokemon();
        assertEq(MELODELFE, facadeGame_.getPlayer().getPokemonPlayerList().getValue(2).getName());
    }
    @Test
    public void useTm1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTm((short)2);
        facadeGame_.searchTmToUse();
        facadeGame_.checkLineMove(0);
        facadeGame_.chooseMoveByObject();
        facadeGame_.clearSortingMove();
        assertTrue(!facadeGame_.getPlayer().getSelectedMove().isEmpty());
        facadeGame_.openMenu();
        assertTrue(facadeGame_.getPlayer().getIndexesOfPokemonTeam().contains(0));
        facadeGame_.choosePokemonForLearningMove((byte) 0);
    }
    @Test
    public void useTm2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getMoves().put(PISTOLET_A_O,new UsesOfMove((short) 20));
        facadeGame_.getGame().getPlayer().getTm((short)2);
        facadeGame_.searchTmToUse();
        facadeGame_.checkLineMove(0);
        facadeGame_.chooseMoveByObject();
        facadeGame_.clearSortingMove();
        assertTrue(!facadeGame_.getPlayer().getSelectedMove().isEmpty());
        facadeGame_.openMenu();
        assertTrue(facadeGame_.getPlayer().getIndexesOfPokemonTeam().contains(0));
        facadeGame_.choosePokemonForLearningMove((byte) 0);
        assertTrue(!facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getMoves().contains(TONNERRE));
        facadeGame_.learnMove(PISTOLET_A_O);
        assertTrue(facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getMoves().contains(TONNERRE));
    }
}
