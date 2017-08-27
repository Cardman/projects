package aiki.game.fight;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

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
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class PseudoPlayerFighterTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    @Test
    public void new_PseudoPlayerFighter_PokemonPlayer_1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(OEUF_CHANCE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
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
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(OEUF_CHANCE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)25));
        evos_.add(new NameLevel(TARTARD,(short)25));
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
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        Rate nbPoints_ = pseudoFighter_.numberNecessaryPointsForGrowingLevel((short) 4, _data_);
        assertEq(new Rate("7"),nbPoints_);
        nbPoints_ = pseudoFighter_.numberNecessaryPointsForGrowingLevel((short) 5, _data_);
        assertEq(new Rate("9"),nbPoints_);
    }

    @Test
    public void newLevelWonPoints1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        assertEq(3, result_.getLevel());
        assertEq(new Rate("7"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate("4"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        assertEq(4, result_.getLevel());
        assertEq(new Rate("16"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 99);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1000"));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        assertEq(100, result_.getLevel());
        assertEq(new Rate("199"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        assertEq(100, result_.getLevel());
    }

    @Test
    public void changeWonPoints1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(Rate.zero(), pseudoFighter_.getWonExp());
        assertEq(new Rate("3"), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate(4));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(Rate.zero(), pseudoFighter_.getWonExp());
        assertEq(new Rate("2"), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate(14));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(Rate.zero(), pseudoFighter_.getWonExp());
        assertEq(new Rate("3"), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 99);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("0"));
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(new Rate("801"), pseudoFighter_.getWonExp());
        assertEq(Rate.zero(), pseudoFighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void newMoves1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, new EqList<NameLevel>());
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringList moves_ = pseudoFighter_.newMoves(result_.getLevel(), _data_);
        assertEq(9, moves_.size());
        assertTrue(moves_.containsObj(ECUME));
        assertTrue(moves_.containsObj(HYPNOSE));
        assertTrue(moves_.containsObj(PISTOLET_A_O));
        assertTrue(moves_.containsObj(TORGNOLES));
        assertTrue(moves_.containsObj(DANSE_PLUIE));
        assertTrue(moves_.containsObj(PLAQUAGE));
        assertTrue(moves_.containsObj(BULLES_D_O));
        assertTrue(moves_.containsObj(TIR_DE_BOUE));
        assertTrue(moves_.containsObj(COGNOBIDON));
    }

    @Test
    public void changeLevelsEvolutions1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
        assertEq(PTITARD, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(0, pseudoFighter_.getEvoLevels().size());
    }

    @Test
    public void changeLevelsEvolutions2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)32));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
        assertEq(PTITARD, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(32, pseudoFighter_.getEvoLevels().first().getLevel());
    }

    @Test
    public void changeLevelsEvolutions3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TETARTE, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
    }

    @Test
    public void changeLevelsEvolutions4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)25));
        evos_.add(new NameLevel(TARTARD,(short)25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
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
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TARTARD,(short)25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
        assertEq(TETARTE, pseudoFighter_.getName());
        assertEq(31, pseudoFighter_.getLevel());
        assertEq(new Rate("48"), pseudoFighter_.getWonExpSinceLastLevel());
        assertEq(1, pseudoFighter_.getEvoLevels().size());
        assertEq(TARTARD, pseudoFighter_.getEvoLevels().first().getName());
        assertEq(31, pseudoFighter_.getEvoLevels().first().getLevel());
    }

    @Test
    public void changeLevelsEvolutions6Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)25));
        evos_.add(new NameLevel(TARTARD,(short)25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
        pseudoFighter_.changeLevelsEvolutions(_data_);
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
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)25));
        evos_.add(new NameLevel(TARPAUD,(short)25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
        pseudoFighter_.changeLevelsEvolutions(_data_);
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
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)25));
        evos_.add(new NameLevel(TARTARD,(short)25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = pseudoFighter_.newLevelWonPoints(_data_);
        pseudoFighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        pseudoFighter_.getMoves().add(pseudoFighter_.newMoves(result_.getLevel(), _data_));
        pseudoFighter_.setLevel(result_.getLevel());
        pseudoFighter_.changeLevelsEvolutions(_data_);
        StringList moves_ = pseudoFighter_.newMovesEvolution(_data_);
        assertEq(8, moves_.size());
        assertTrue(moves_.containsObj(TOURNIQUET));
        assertTrue(moves_.containsObj(ECUME));
        assertTrue(moves_.containsObj(HYPNOSE));
        assertTrue(moves_.containsObj(PISTOLET_A_O));
        assertTrue(moves_.containsObj(TORGNOLES));
        assertTrue(moves_.containsObj(DANSE_PLUIE));
        assertTrue(moves_.containsObj(PLAQUAGE));
        assertTrue(moves_.containsObj(BULLES_D_O));
    }

    @Test
    public void calculateNewLevel1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)25));
        evos_.add(new NameLevel(TARTARD,(short)25));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        pseudoFighter_.calculateNewLevel((byte) 0, _data_);
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
        assertTrue(moves_.containsObj(TOURNIQUET));
        assertTrue(moves_.containsObj(ECUME));
        assertTrue(moves_.containsObj(HYPNOSE));
        assertTrue(moves_.containsObj(PISTOLET_A_O));
        assertTrue(moves_.containsObj(TORGNOLES));
        assertTrue(moves_.containsObj(DANSE_PLUIE));
        assertTrue(moves_.containsObj(PLAQUAGE));
        assertTrue(moves_.containsObj(BULLES_D_O));
        assertTrue(moves_.containsObj(TIR_DE_BOUE));
        assertTrue(moves_.containsObj(COGNOBIDON));
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(2, abilities_.size());
        assertTrue(abilities_.containsObj(MOITEUR));
        assertTrue(abilities_.containsObj(ABSORB_EAU));
        assertEq(1, pseudoFighter_.getEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getEvolutions().getVal((byte) 0));
        assertEq(1, pseudoFighter_.getInfosRealEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getInfosRealEvolutions().first().getName());
        assertEq(31, pseudoFighter_.getInfosRealEvolutions().first().getLevel());
    }

    @Test
    public void calculateNewLevel2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)32));
        evos_.add(new NameLevel(TARTARD,(short)32));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("1000"));
        pseudoFighter_.calculateNewLevel((byte) 0, _data_);
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
        assertTrue(moves_.containsObj(ECUME));
        assertTrue(moves_.containsObj(HYPNOSE));
        assertTrue(moves_.containsObj(PISTOLET_A_O));
        assertTrue(moves_.containsObj(TORGNOLES));
        assertTrue(moves_.containsObj(DANSE_PLUIE));
        assertTrue(moves_.containsObj(PLAQUAGE));
        assertTrue(moves_.containsObj(BULLES_D_O));
        assertTrue(moves_.containsObj(TIR_DE_BOUE));
        assertTrue(moves_.containsObj(COGNOBIDON));
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(0, abilities_.size());
        assertEq(0, pseudoFighter_.getEvolutions().size());
        assertEq(0, pseudoFighter_.getInfosRealEvolutions().size());
    }

    @Test
    public void calculateNewLevel3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 99);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TETARTE,(short)100));
        evos_.add(new NameLevel(TARTARD,(short)100));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("10000"));
        pseudoFighter_.calculateNewLevel((byte) 0, _data_);
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
        assertTrue(moves_.containsObj(TOURNIQUET));
        assertTrue(moves_.containsObj(ECUME));
        assertTrue(moves_.containsObj(HYPNOSE));
        assertTrue(moves_.containsObj(PISTOLET_A_O));
        assertTrue(moves_.containsObj(TORGNOLES));
        assertTrue(moves_.containsObj(DANSE_PLUIE));
        assertTrue(moves_.containsObj(PLAQUAGE));
        assertTrue(moves_.containsObj(BULLES_D_O));
        assertTrue(moves_.containsObj(TIR_DE_BOUE));
        assertTrue(moves_.containsObj(COGNOBIDON));
        assertTrue(moves_.containsObj(REVEIL_FORCE));
        assertTrue(moves_.containsObj(HYDROCANON));
        assertTrue(moves_.containsObj(BOUE_BOMBE));
        assertEq(1, pseudoFighter_.getAbilities().size());
        StringList abilities_ = pseudoFighter_.getAbilities().first();
        assertEq(2, abilities_.size());
        assertTrue(abilities_.containsObj(MOITEUR));
        assertTrue(abilities_.containsObj(ABSORB_EAU));
        assertEq(1, pseudoFighter_.getEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getEvolutions().getVal((byte) 0));
        assertEq(1, pseudoFighter_.getInfosRealEvolutions().size());
        assertEq(TETARTE, pseudoFighter_.getInfosRealEvolutions().first().getName());
        assertEq(100, pseudoFighter_.getInfosRealEvolutions().first().getLevel());
    }

    @Test
    public void calculateNewLevel4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 99);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TARTARD,(short)100));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("10000"));
        pseudoFighter_.calculateNewLevel((byte) 0, _data_);
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
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        PseudoPokemonPlayer pseudoPk_ = new PseudoPokemonPlayer(pokemonUser_);
        EqList<NameLevel> evos_;
        evos_ = new EqList<NameLevel>();
        evos_.add(new NameLevel(TARTARD,(short)100));
        PseudoPlayerFighter pseudoFighter_ = new PseudoPlayerFighter(pseudoPk_, true, evos_);
        pseudoFighter_.setWonExp(new Rate("10000"));
        pseudoFighter_.calculateNewLevel((byte) 0, _data_);
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
