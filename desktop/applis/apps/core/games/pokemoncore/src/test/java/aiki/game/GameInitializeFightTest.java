package aiki.game;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.enums.FightType;
import aiki.game.params.Difficulty;
import aiki.map.DataMap;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Campaign;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.montecarlo.MonteCarloEq;


public class GameInitializeFightTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void lawCopy1Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloEq<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data);
        assertEq(2, law_.nbEvents());
        WildPk wildOne_ = new WildPk();
        wildOne_.setName(PIKACHU);
        wildOne_.setLevel((short) 1);
        wildOne_.setAbility(PARATONNERRE);
        wildOne_.setGender(Gender.NO_GENDER);
        WildPk wildTwo_ = new WildPk();
        wildTwo_.setName(PIKACHU);
        wildTwo_.setLevel((short) 3);
        wildTwo_.setAbility(PARATONNERRE);
        wildTwo_.setGender(Gender.NO_GENDER);
        assertTrue(law_.containsEvent(wildOne_));
        assertTrue(law_.containsEvent(wildTwo_));
        assertEq(LgInt.one(), law_.rate(wildOne_));
        assertEq(LgInt.one(), law_.rate(wildTwo_));
    }

    @Test
    public void lawCopy2Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloEq<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data);
        assertEq(1, law_.nbEvents());
        WildPk wildOne_ = new WildPk();
        wildOne_.setName(ARTIKODIN);
        wildOne_.setLevel((short) 1);
        wildOne_.setAbility(PARATONNERRE);
        wildOne_.setGender(Gender.NO_GENDER);
        assertTrue(law_.containsEvent(wildOne_));
        assertEq(LgInt.one(), law_.rate(wildOne_));
    }

    @Test
    public void lawCopy3Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloEq<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data);
        assertEq(2, law_.nbEvents());
        WildPk wildOne_ = new WildPk();
        wildOne_.setName(ARTIKODIN);
        wildOne_.setLevel((short) 1);
        wildOne_.setAbility(PARATONNERRE);
        wildOne_.setGender(Gender.NO_GENDER);
        assertTrue(law_.containsEvent(wildOne_));
        assertTrue(law_.containsEvent(new WildPk()));
        assertEq(LgInt.one(), law_.rate(wildOne_));
        assertEq(LgInt.one(), law_.rate(new WildPk()));
    }

    @Test
    public void lawCopy4Test() {
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, true);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloEq<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.containsEvent(new WildPk()));
        assertTrue(law_.isValid());
    }

    @Test
    public void lawCopy5Test() {
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, true);
        game_.getPlayer().getTeam().add(lasPk_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(MEW);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(MEW, true);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloEq<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.containsEvent(new WildPk()));
        assertTrue(law_.isValid());
    }

    @Test
    public void newRandomPokemon1Test() {
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, true);
        game_.getPlayer().getTeam().add(lasPk_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(MEW);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(MEW, true);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newRandomPokemon(area_.getWildPokemonRand(), data);
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void newRandomPokemon2Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newRandomPokemon(area_.getWildPokemonRand(), data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex1Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data);
        assertEq(1, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex2Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 1, area_, data);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex3Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 2, area_, data);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex4Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex5Test() {
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, true);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void newIndex6Test() {
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, true);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)3,(short)2));
        game_.setPlayerOrientation(Direction.RIGHT);
        Coords next_ = game_.closestTile(map_);
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(next_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(next_.getLevel().getPoint());
        game_.newIndex(false, 0, area_, data);
        assertEq(1, game_.getIndexPeriodFishing());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(PTITARD, game_.getFight().wildPokemon().getName());
        assertEq(EnvironmentType.ROAD, game_.getFight().getEnvType());
    }

    @Test
    public void newIndex7Test() {
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, true);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)3,(short)2));
        game_.setPlayerOrientation(Direction.RIGHT);
        Coords next_ = game_.closestTile(map_);
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(next_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(next_.getLevel().getPoint());
        game_.newIndex(false, 1, area_, data);
        assertEq(0, game_.getIndexPeriodFishing());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(TETARTE, game_.getFight().wildPokemon().getName());
        assertEq(EnvironmentType.ROAD, game_.getFight().getEnvType());
    }

    @Test
    public void incrementPeriod1Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.incrementPeriod(area_, data);
        assertEq(0, game_.getIndexStep());
        assertEq(1, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void incrementPeriod2Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.incrementPeriod(area_, data);
        assertEq(1, game_.getIndexStep());
        assertEq(0, game_.getIndexPeriod());
        assertTrue(!game_.getFight().getFightType().isExisting());
        game_.incrementPeriod(area_, data);
        assertEq(0, game_.getIndexStep());
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void initFishing1Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initFishing(data);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(0, game_.getIndexPeriodFishing());
    }

    @Test
    public void initFishing2Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initFishing(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1, game_.getIndexPeriodFishing());
    }

    @Test
    public void initFishing3Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initFishing(data);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(0, game_.getIndexPeriodFishing());
    }

    @Test
    public void initFishing4Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 2, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(true);
        game_.initFishing(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(0, game_.getIndexPeriodFishing());
    }

    @Test
    public void initLegendaryPokemonFight1Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.initLegendaryPokemonFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(MEW, game_.getFight().wildPokemon().getName());
    }

    @Test
    public void initTrainerFight1Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(3, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(4, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight2Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(13, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(14, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight3Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),1));
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(13, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(14, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight4Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(3, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(4, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight5Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        //game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 10, 4),0));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4),0));
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(3, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(4, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight6Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.TMP_TRAINER, game_.getFight().getFightType());
        assertTrue(game_.isDualFight());
    }

    @Test
    public void initTrainerFight7Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.TMP_TRAINER, game_.getFight().getFightType());
        assertTrue(game_.isDualFight());
    }

    @Test
    public void initTrainerFight8Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR_GYM, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight9Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.GYM_LEADER, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight10Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR_LIGUE, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight11Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR_LIGUE, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight12Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }
}
