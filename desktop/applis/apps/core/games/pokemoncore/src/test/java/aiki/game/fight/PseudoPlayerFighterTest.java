package aiki.game.fight;

import aiki.db.DataBase;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.NameLevel;
import aiki.game.fight.util.LevelExpPoints;
import aiki.game.params.Difficulty;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;


public class PseudoPlayerFighterTest extends InitializationDataBase {

    @Test
    public void new_PseudoPlayerFighter_PokemonPlayer_1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(OEUF_CHANCE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        assertEq(PIKACHU,pseudoFighter_.getName());
        assertEq(3,pseudoFighter_.getLevel());
        assertEq(OEUF_CHANCE,pseudoFighter_.getItem());
        assertEq(new Rate("3/2"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), pseudoFighter_.getWonExp());
        assertEq(0, pseudoFighter_.getMoves().size());
        assertEq(0, pseudoFighter_.getAbilities().size());
        assertEq(0, pseudoFighter_.getEvoLevels().size());
        assertEq(0, pseudoFighter_.getFoes().size());
        assertEq(0, pseudoFighter_.getEvolutions().size());
        assertTrue(pseudoFighter_.isFront());
    }

    @Test
    public void new_PseudoPlayerFighter_PokemonPlayer_2Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(OEUF_CHANCE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,25));
        evos_.add(new NameLevel(TARTARD,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, false, evos_);
        assertEq(PTITARD,pseudoFighter_.getName());
        assertEq(3,pseudoFighter_.getLevel());
        assertEq(OEUF_CHANCE,pseudoFighter_.getItem());
        assertEq(new Rate("3/2"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), pseudoFighter_.getWonExp());
        assertEq(0, pseudoFighter_.getMoves().size());
        assertEq(0, pseudoFighter_.getAbilities().size());
        assertEq(2, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(25, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().last().getName());
        assertEq(25, pseudoFighter_.getEvoLevels().last().getLevel());
        assertEq(0, pseudoFighter_.getFoes().size());
        assertEq(0, pseudoFighter_.getEvolutions().size());
        assertTrue(!pseudoFighter_.isFront());
    }

    @Test
    public void numberNecessaryPointsForGrowingLevel1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        Rate nbPoints_ = pseudoFighter_.numberNecessaryPointsForGrowingLevel( 4, data_);
        assertEq(new Rate("7"),nbPoints_);
        nbPoints_ = pseudoFighter_.numberNecessaryPointsForGrowingLevel( 5, data_);
        assertEq(new Rate("9"),nbPoints_);
    }

    @Test
    public void newLevelWonPoints1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        assertEq(3, result_.getLevel());
        assertEq(new Rate("7"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints2Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate("4"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        assertEq(4, result_.getLevel());
        assertEq(new Rate("16"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints3Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 99);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1000"));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        assertEq(100, result_.getLevel());
        assertEq(new Rate("199"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints4Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 100);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        assertEq(100, result_.getLevel());
    }

    @Test
    public void changeWonPoints1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        assertEq(Rate.zero(), pseudoFighter_.getWonExp());
        assertEq(new Rate("3"), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints2Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate(4));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        assertEq(Rate.zero(), pseudoFighter_.getWonExp());
        assertEq(new Rate("2"), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints3Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate(14));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        assertEq(Rate.zero(), pseudoFighter_.getWonExp());
        assertEq(new Rate("3"), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints4Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 99);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(MORPHING,  10);
        moves_.put(BROUHAHA,  10);
        moves_.put(POUV_ANTIQUE,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK,  1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("0"));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        assertEq(new Rate("801"), pseudoFighter_.getWonExp());
        assertEq(Rate.zero(), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void newMoves1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new CustList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        StringList moves_ = pseudoFighter_.newMoves(result_.getLevel(), data_);
        assertEq(9, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, DANSE_PLUIE));
        assertTrue(StringUtil.contains(moves_, PLAQUAGE));
        assertTrue(StringUtil.contains(moves_, BULLES_D_O));
        assertTrue(StringUtil.contains(moves_, TIR_DE_BOUE));
        assertTrue(StringUtil.contains(moves_, COGNOBIDON));
    }

    @Test
    public void changeLevelsEvolutions1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        assertEq(PTITARD, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(0, pseudoFighter_.getEvoLevels().size());
    }

    @Test
    public void changeLevelsEvolutions2Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,32));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        assertEq(PTITARD, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(32, pseudoFighter_.getEvoLevels().first().getLevel());
    }

    @Test
    public void changeLevelsEvolutions3Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
    }

    @Test
    public void changeLevelsEvolutions4Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,25));
        evos_.add(new NameLevel(TARTARD,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(2, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().last().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().last().getLevel());
    }

    @Test
    public void changeLevelsEvolutions5Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TARTARD,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
    }

    @Test
    public void changeLevelsEvolutions6Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,25));
        evos_.add(new NameLevel(TARTARD,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        pseudoFighter_.changeLevelsEvolutions(data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(2, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().last().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().last().getLevel());
    }

    @Test
    public void changeLevelsEvolutions7Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,25));
        evos_.add(new NameLevel(TARPAUD,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        pseudoFighter_.changeLevelsEvolutions(data_);
        assertEq(TARPAUD, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(2, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(TARPAUD, pseudoFighter_.getEvoLevels().last().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().last().getLevel());
    }

    @Test
    public void newMovesEvolution1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,25));
        evos_.add(new NameLevel(TARTARD,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(data_);
        StringList moves_ = pseudoFighter_.newMovesEvolution(data_);
        assertEq(8, moves_.size());
        assertTrue(StringUtil.contains(moves_, TOURNIQUET));
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, DANSE_PLUIE));
        assertTrue(StringUtil.contains(moves_, PLAQUAGE));
        assertTrue(StringUtil.contains(moves_, BULLES_D_O));
    }

    @Test
    public void calculateNewLevel1Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,25));
        evos_.add(new NameLevel(TARTARD,25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        pseudoFighter_.calculateNewLevel( 0, data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(2, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().last().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().last().getLevel());
        assertEq(1, pseudoFighter_.getMoves().size());
        StringList moves_ = pseudoFighter_.getMoves().first();
        assertEq(10, moves_.size());
        assertTrue(StringUtil.contains(moves_, TOURNIQUET));
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, DANSE_PLUIE));
        assertTrue(StringUtil.contains(moves_, PLAQUAGE));
        assertTrue(StringUtil.contains(moves_, BULLES_D_O));
        assertTrue(StringUtil.contains(moves_, TIR_DE_BOUE));
        assertTrue(StringUtil.contains(moves_, COGNOBIDON));
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(2, abilities_.size());
        assertTrue(StringUtil.contains(abilities_, MOITEUR));
        assertTrue(StringUtil.contains(abilities_, ABSORB_EAU));
        assertEq(1, pseudoFighter_.getEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getEvolutions().getVal( 0));
        assertEq(1, pseudoFighter_.getInfosRealEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getInfosRealEvolutions().first().getName());
        assertEq(31, pseudoFighter_.getInfosRealEvolutions().first().getLevel());
    }

    @Test
    public void calculateNewLevel2Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,32));
        evos_.add(new NameLevel(TARTARD,32));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        pseudoFighter_.calculateNewLevel( 0, data_);
        assertEq(PTITARD, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(2, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(32, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().last().getName());
        assertEq(32, pseudoFighter_.getEvoLevels().last().getLevel());
        assertEq(1, pseudoFighter_.getMoves().size());
        StringList moves_ = pseudoFighter_.getMoves().first();
        assertEq(9, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, DANSE_PLUIE));
        assertTrue(StringUtil.contains(moves_, PLAQUAGE));
        assertTrue(StringUtil.contains(moves_, BULLES_D_O));
        assertTrue(StringUtil.contains(moves_, TIR_DE_BOUE));
        assertTrue(StringUtil.contains(moves_, COGNOBIDON));
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(0, abilities_.size());
        assertEq(0, pseudoFighter_.getEvolutions().size());
        assertEq(0, pseudoFighter_.getInfosRealEvolutions().size());
    }

    @Test
    public void calculateNewLevel3Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 99);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,100));
        evos_.add(new NameLevel(TARTARD,100));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("10000"));
        pseudoFighter_.calculateNewLevel( 0, data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(100, pseudoFighter_.getLevel());
        assertEq(new Rate("0"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(2, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(100, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().last().getName());
        assertEq(100, pseudoFighter_.getEvoLevels().last().getLevel());
        assertEq(1, pseudoFighter_.getMoves().size());
        StringList moves_ = pseudoFighter_.getMoves().first();
        assertEq(13, moves_.size());
        assertTrue(StringUtil.contains(moves_, TOURNIQUET));
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, DANSE_PLUIE));
        assertTrue(StringUtil.contains(moves_, PLAQUAGE));
        assertTrue(StringUtil.contains(moves_, BULLES_D_O));
        assertTrue(StringUtil.contains(moves_, TIR_DE_BOUE));
        assertTrue(StringUtil.contains(moves_, COGNOBIDON));
        assertTrue(StringUtil.contains(moves_, REVEIL_FORCE));
        assertTrue(StringUtil.contains(moves_, HYDROCANON));
        assertTrue(StringUtil.contains(moves_, BOUE_BOMBE));
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(2, abilities_.size());
        assertTrue(StringUtil.contains(abilities_, MOITEUR));
        assertTrue(StringUtil.contains(abilities_, ABSORB_EAU));
        assertEq(1, pseudoFighter_.getEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getEvolutions().getVal( 0));
        assertEq(1, pseudoFighter_.getInfosRealEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getInfosRealEvolutions().first().getName());
        assertEq(100, pseudoFighter_.getInfosRealEvolutions().first().getLevel());
    }

    @Test
    public void calculateNewLevel4Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 99);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TARTARD,100));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("10000"));
        pseudoFighter_.calculateNewLevel( 0, data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(100, pseudoFighter_.getLevel());
        assertEq(new Rate("0"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(100, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(1, pseudoFighter_.getMoves().size());
        StringList moves_ = pseudoFighter_.getMoves().first();
        assertEq(0, moves_.size());
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(0, abilities_.size());
        assertEq(0, pseudoFighter_.getEvolutions().size());
        assertEq(0, pseudoFighter_.getInfosRealEvolutions().size());
    }

    @Test
    public void calculateNewLevel5Test() {
        DataBase data_ = initDb();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 100);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        CustList<NameLevel> evos_;
        evos_ = new CustList<NameLevel>();
        evos_.add(new NameLevel(TARTARD,100));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("10000"));
        pseudoFighter_.calculateNewLevel( 0, data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(100, pseudoFighter_.getLevel());
        assertEq(new Rate("0"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(100, pseudoFighter_.getEvoLevels().first().getLevel());
        assertEq(1, pseudoFighter_.getMoves().size());
        StringList moves_ = pseudoFighter_.getMoves().first();
        assertEq(0, moves_.size());
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(0, abilities_.size());
        assertEq(0, pseudoFighter_.getEvolutions().size());
        assertEq(0, pseudoFighter_.getInfosRealEvolutions().size());
    }
}
