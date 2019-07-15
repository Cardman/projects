package aiki.facade;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.SortingPokemonPlayer;
import code.util.CustList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;


public class PaginationPokemonPlayerTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void match1Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("P*");
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match2Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setSearchModeAbility(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfAbility("P*");
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match3Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setSearchModeItem(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfItem(NULL_REF);
        pagination_.getCriteria().setWithItem(SelectedBoolean.YES);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match4Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setGender(Gender.FEMALE);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match5Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setGender(Gender.MALE);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match6Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setContentOfMove(CHARGE);
        pagination_.getCriteria().setSearchModeMove(SearchingMode.WHOLE_STRING);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match7Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setMaxLevel(1L);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match8Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setContentOfMove(VAGUE_PSY);
        pagination_.getCriteria().setSearchModeMove(SearchingMode.WHOLE_STRING);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(NULL_REF);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(pagination_.match(pkPlayer_));
    }

    @Test
    public void match9Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setSearchModeItem(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfItem("BAIE*");
        pagination_.getCriteria().setWithItem(SelectedBoolean.YES);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(MULTI_EXP);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match10Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setMaxNbPossEvols(0L);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(MULTI_EXP);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(!pagination_.match(pkPlayer_));
    }

    @Test
    public void match11Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCriteria().setMaxNbPossEvols(1L);
        Pokemon pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        pk_.setAbility(ABSORB_EAU);
        pk_.setItem(MULTI_EXP);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, data);
        assertTrue(pagination_.match(pkPlayer_));
    }

    @Test
    public void sortable1Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable2Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable3Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpLevel().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable4Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpAbility().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable5Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpItem().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable6Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpGender().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable7Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpPossEvos().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable8Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpLevel().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sort1Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        SortingPokemonPlayer sorting_;
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 3);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 0);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 2);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 1);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 4);
        pagination_.getResults().put(sorting_, pk_);
        pagination_.sort();
        assertEq(5, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
    }

    @Test
    public void sort2Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        SortingPokemonPlayer sorting_;
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 3);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 0);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 2);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 1);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 4);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        sorting_ = toSorting(pk_, 5);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 6);
        pagination_.getResults().put(sorting_, pk_);
        pagination_.sort();
        assertEq(7, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(6));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
    }

    @Test
    public void sort3Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpName().setPriority(4);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(3);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(2);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(1);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        SortingPokemonPlayer sorting_;
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 3);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 0);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 2);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 1);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 4);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        sorting_ = toSorting(pk_, 5);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 6);
        pagination_.getResults().put(sorting_, pk_);
        pagination_.sort();
        assertEq(7, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(6));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
    }

    @Test
    public void sort4Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpName().setPriority(4);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(3);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(2);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpItem().setPriority(1);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        SortingPokemonPlayer sorting_;
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 3);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 0);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 2);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 1);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 4);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        sorting_ = toSorting(pk_, 5);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 6);
        pagination_.getResults().put(sorting_, pk_);
        pagination_.sort();
        assertEq(7, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(6));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
    }

    @Test
    public void sort5Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpName().setPriority(4);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(3);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(2);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES_AND_NO);
        pagination_.getCmpItem().setPriority(1);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        SortingPokemonPlayer sorting_;
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 3);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 0);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 2);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 1);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 4);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        sorting_ = toSorting(pk_, 5);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 6);
        pagination_.getResults().put(sorting_, pk_);
        pagination_.sort();
        assertEq(7, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(6));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
    }

    @Test
    public void calculateRendered1Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        SortingPokemonPlayer sorting_;
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 3);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 0);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 2);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 1);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 4);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        sorting_ = toSorting(pk_, 5);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 6);
        pagination_.getResults().put(sorting_, pk_);
        pagination_.sort();
        pagination_.calculateRendered();
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, sorted_.get(0).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(0).getGender());
        assertEq(FOUR, sorted_.get(0).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(0).getItem());
        assertEq(4, sorted_.get(0).getLevel());
        assertEq(0, sorted_.get(0).getNbPossEvos());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, sorted_.get(1).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(1).getGender());
        assertEq(FOUR, sorted_.get(1).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(1).getItem());
        assertEq(2, sorted_.get(1).getLevel());
        assertEq(0, sorted_.get(1).getNbPossEvos());
    }

    @Test
    public void pages1Test() {
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        SortingPokemonPlayer sorting_;
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 3);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 0);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 2);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        sorting_ = toSorting(pk_, 1);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        sorting_ = toSorting(pk_, 4);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        sorting_ = toSorting(pk_, 5);
        pagination_.getResults().put(sorting_, pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        sorting_ = toSorting(pk_, 6);
        pagination_.getResults().put(sorting_, pk_);
        pagination_.sort();
        pagination_.calculateRendered();
        assertEq(4, pagination_.pages());
    }

    @Test
    public void search1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        assertEq(7, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(6));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, sorted_.get(0).getName());
        assertEq(LIMAGMA, sorted_.get(0).getKeyName());
        assertEq(Gender.NO_GENDER, sorted_.get(0).getGender());
        assertEq(FOUR, sorted_.get(0).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(0).getItem());
        assertEq(BAIE_MEPO, sorted_.get(0).getKeyItem());
        assertEq(4, sorted_.get(0).getLevel());
        assertEq(4, sorted_.get(0).getIndex());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, sorted_.get(1).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(1).getGender());
        assertEq(FOUR, sorted_.get(1).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(1).getItem());
        assertEq(2, sorted_.get(1).getLevel());
        assertEq(1, sorted_.get(1).getIndex());
        assertEq(CustList.FIRST_INDEX, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void search2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.getCriteria().setContentOfName("*Z*");
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.search(player_.getBox());
        assertEq(0, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void search3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(player_.getBox());
        assertEq(5, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(PIKACHU, sorted_.get(0).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(0).getGender());
        assertEq(STATIK, sorted_.get(0).getAbility());
        assertEq(NULL_REF, sorted_.get(0).getItem());
        assertEq(2, sorted_.get(0).getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, sorted_.get(1).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(1).getGender());
        assertEq(FOUR, sorted_.get(1).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(1).getItem());
        assertEq(2, sorted_.get(1).getLevel());
        assertEq(CustList.FIRST_INDEX, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void search4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        player_.getBox().add(new Egg(PIKACHU));
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        assertEq(7, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(6));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, sorted_.get(0).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(0).getGender());
        assertEq(FOUR, sorted_.get(0).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(0).getItem());
        assertEq(4, sorted_.get(0).getLevel());
        assertEq(5, sorted_.get(0).getIndex());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, sorted_.get(1).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(1).getGender());
        assertEq(FOUR, sorted_.get(1).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(1).getItem());
        assertEq(2, sorted_.get(1).getLevel());
        assertEq(2, sorted_.get(1).getIndex());
        assertEq(CustList.FIRST_INDEX, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void search5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        player_.getBox().add(new Egg(PIKACHU));
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.getCriteria().setMinNbPossEvols(1L);
        pagination_.search(player_.getBox());
        assertEq(2, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        assertEq(CustList.FIRST_INDEX, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void search6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpPossEvos().setPriority(6);
        pagination_.getCmpPossEvos().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        assertEq(7, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(6));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, sorted_.get(0).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(0).getGender());
        assertEq(FOUR, sorted_.get(0).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(0).getItem());
        assertEq(4, sorted_.get(0).getLevel());
        assertEq(4, sorted_.get(0).getIndex());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, sorted_.get(1).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(1).getGender());
        assertEq(FOUR, sorted_.get(1).getAbility());
        assertEq(BAIE_MEPO, sorted_.get(1).getItem());
        assertEq(2, sorted_.get(1).getLevel());
        assertEq(1, sorted_.get(1).getIndex());
        assertEq(CustList.FIRST_INDEX, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void checkLine1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.checkLine(1);
        assertEq(1, pagination_.getLine());
    }

    @Test
    public void changePage1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.changePage(1);
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        assertEq(1, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void currentIndex1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.checkLine(1);
        assertEq(1, pagination_.currentIndex());
    }

    @Test
    public void currentIndex2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        player_.getBox().add(new Egg(PIKACHU));
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.checkLine(1);
        assertEq(1, pagination_.currentIndex());
    }

    @Test
    public void currentIndex3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.checkLine(1);
        assertEq(0, pagination_.currentIndex());
    }

    @Test
    public void currentObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.checkLine(1);
        assertSame(player_.getBox().get(1), pagination_.currentObject());
    }

    @Test
    public void currentObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        player_.getBox().add(new Egg(PIKACHU));
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.checkLine(1);
        assertSame(player_.getBox().get(1), pagination_.currentObject());
    }

    @Test
    public void enabledPrevious1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        assertTrue(!pagination_.enabledPrevious());
    }

    @Test
    public void enabledNext1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        assertTrue(pagination_.enabledNext());
    }

    @Test
    public void next1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.next();
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        assertEq(1, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void previous1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.next();
        assertTrue(pagination_.enabledNext());
        pagination_.next();
        assertTrue(pagination_.enabledPrevious());
        pagination_.previous();
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        assertEq(1, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void begin1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.next();
        assertTrue(pagination_.enabledNext());
        pagination_.next();
        assertTrue(pagination_.enabledPrevious());
        pagination_.begin();
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        assertEq(0, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void end1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.end();
        assertTrue(!pagination_.enabledNext());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        assertEq(3, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void nextDelta1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.setDelta(3);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.nextDelta();
        assertTrue(!pagination_.enabledNext());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        assertEq(3, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void previousDelta1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.setDelta(3);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.nextDelta();
        pagination_.previousDelta();
        assertTrue(!pagination_.enabledPrevious());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(4, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(LIMAGMA, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(FOUR, pk_.getAbility());
        assertEq(BAIE_MEPO, pk_.getItem());
        assertEq(2, pk_.getLevel());
        assertEq(0, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void newSearch1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.getCriteria().setContentOfName("P*");
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.newSearch();
        assertEq(3, pagination_.getResults().size());
        CustList<SortingPokemonPlayer> sorted_;
        sorted_ = new CustList<SortingPokemonPlayer>(pagination_.getResults().getKeys());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(PIKACHU, pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(STATIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getItem());
        assertEq(2, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.FEMALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(PTITARD, pk_.getName());
        assertEq(Gender.MALE, pk_.getGender());
        assertEq(ABSORB_EAU, pk_.getAbility());
        assertEq(MULTI_EXP, pk_.getItem());
        assertEq(3, pk_.getLevel());
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        pk_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(PIKACHU, sorted_.get(0).getName());
        assertEq(Gender.NO_GENDER, sorted_.get(0).getGender());
        assertEq(STATIK, sorted_.get(0).getAbility());
        assertEq(NULL_REF, sorted_.get(0).getItem());
        assertEq(2, sorted_.get(0).getLevel());
        pk_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(PTITARD, sorted_.get(1).getName());
        assertEq(Gender.FEMALE, sorted_.get(1).getGender());
        assertEq(ABSORB_EAU, sorted_.get(1).getAbility());
        assertEq(MULTI_EXP, sorted_.get(1).getItem());
        assertEq(3, sorted_.get(1).getLevel());
        assertEq(CustList.FIRST_INDEX, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
    }

    @Test
    public void clear1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, data);
        PokemonPlayer pk_;
        pk_ = newPokemonPlayer(PIKACHU, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.MALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(PTITARD, Gender.FEMALE, 3, ABSORB_EAU, MULTI_EXP);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 4, FOUR, BAIE_MEPO);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, FOUR, NULL_REF);
        player_.getBox().add(pk_);
        pk_ = newPokemonPlayer(LIMAGMA, Gender.NO_GENDER, 2, STATIK, NULL_REF);
        player_.getBox().add(pk_);
        PaginationPokemonPlayer pagination_;
        pagination_ = new PaginationPokemonPlayer();
        pagination_.setTranslation(data,LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.setDelta(3);
        pagination_.getCmpName().setPriority(5);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpLevel().setPriority(4);
        pagination_.getCmpLevel().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpGender().setPriority(3);
        pagination_.getCmpGender().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpItem().setPriority(2);
        pagination_.getCmpItem().setIncreasing(SelectedBoolean.NO);
        pagination_.getCmpAbility().setPriority(1);
        pagination_.getCmpAbility().setIncreasing(SelectedBoolean.YES);
        pagination_.search(player_.getBox());
        pagination_.clear();
        assertEq(0, pagination_.getResults().size());
        assertEq(0, pagination_.getRendered().size());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.currentIndex());
    }

    private SortingPokemonPlayer toSorting(
            PokemonPlayer _pk, int _index) {
        SortingPokemonPlayer sorting_;
        sorting_ = new SortingPokemonPlayer();
        sorting_.setName(_pk.getName());
        sorting_.setLevel(_pk.getLevel());
        sorting_.setAbility(_pk.getAbility());
        sorting_.setGender(_pk.getGender());
        sorting_.setItem(_pk.getItem());
        sorting_.setIndex(_index);
        sorting_.setNbPossEvos((short) _pk.getDirectEvolutions(data).size());
        return sorting_;
    }

    private PokemonPlayer newPokemonPlayer(
            String _name,
            Gender _gender,
            int _level,
            String _ability,
            String _object) {
        Pokemon pk_ = new WildPk();
        pk_.setName(_name);
        pk_.setGender(_gender);
        pk_.setLevel((short) _level);
        pk_.setAbility(_ability);
        pk_.setItem(_object);
        return new PokemonPlayer(pk_, data);
    }
}
