package aiki.game;

import aiki.db.DataBase;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloList;
import code.util.CustList;
import code.util.core.BoolVal;
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


public class GameInitializeFightTest extends InitializationDataBase {

    @Test
    public void lawCopy1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloList<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data_);
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
        assertTrue(containsPk(law_,wildOne_));
        assertTrue(containsPk(law_,wildTwo_));
        assertEq(LgInt.one(), freqPk(law_,wildOne_));
        assertEq(LgInt.one(), freqPk(law_,wildTwo_));
    }

    @Test
    public void lawCopy2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloList<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data_);
        assertEq(1, law_.nbEvents());
        WildPk wildOne_ = new WildPk();
        wildOne_.setName(ARTIKODIN);
        wildOne_.setLevel((short) 1);
        wildOne_.setAbility(PARATONNERRE);
        wildOne_.setGender(Gender.NO_GENDER);
        assertTrue(containsPk(law_,wildOne_));
        assertEq(LgInt.one(), freqPk(law_,wildOne_));
    }

    @Test
    public void lawCopy3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloList<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data_);
        assertEq(2, law_.nbEvents());
        WildPk wildOne_ = new WildPk();
        wildOne_.setName(ARTIKODIN);
        wildOne_.setLevel((short) 1);
        wildOne_.setAbility(PARATONNERRE);
        wildOne_.setGender(Gender.NO_GENDER);
        assertTrue(containsPk(law_,wildOne_));
        assertTrue(containsPk(law_,new WildPk()));
        assertEq(LgInt.one(), freqPk(law_,wildOne_));
        assertEq(LgInt.one(), freqPk(law_,new WildPk()));
    }

    @Test
    public void lawCopy4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloList<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data_);
        assertEq(1, nbPk(law_));
        assertTrue(containsPk(law_,new WildPk()));
    }

    @Test
    public void lawCopy5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(MEW);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(MEW, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        MonteCarloList<WildPk> law_ = game_.lawCopy(area_.getWildPokemonRand(), data_);
        assertEq(1, nbPk(law_));
        assertTrue(containsPk(law_,new WildPk()));
    }

    @Test
    public void newRandomPokemon1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(MEW);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(MEW, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newRandomPokemon(area_.getWildPokemonRand(), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void newRandomPokemon2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newRandomPokemon(area_.getWildPokemonRand(), data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        assertEq(1, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 1, area_, data_);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 2, area_, data_);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void newIndex5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        assertEq(0, game_.getIndexPeriod());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void newIndex6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)3,(short)2));
        game_.setPlayerOrientation(Direction.RIGHT);
        Coords next_ = game_.closestTile(map_);
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(next_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(next_.getLevel().getPoint());
        game_.newIndex(false, 0, area_, data_);
        assertEq(1, game_.getIndexPeriodFishing());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(PTITARD, game_.getFight().wildPokemon().getName());
        assertEq(EnvironmentType.ROAD, game_.getFight().getEnvType());
    }

    @Test
    public void newIndex7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemonDonne_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        game_.getPlayer().getCaughtPk().put(ARTIKODIN, BoolVal.TRUE);
        game_.getPlayer().getTeam().add(lasPk_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)3,(short)2));
        game_.setPlayerOrientation(Direction.RIGHT);
        Coords next_ = game_.closestTile(map_);
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(next_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(next_.getLevel().getPoint());
        game_.newIndex(false, 1, area_, data_);
        assertEq(0, game_.getIndexPeriodFishing());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(TETARTE, game_.getFight().wildPokemon().getName());
        assertEq(EnvironmentType.ROAD, game_.getFight().getEnvType());
    }

    @Test
    public void incrementPeriod1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.incrementPeriod(area_, data_);
        assertEq(0, game_.getIndexStep());
        assertEq(1, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void incrementPeriod2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)0,(short)2));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.incrementPeriod(area_, data_);
        assertEq(1, game_.getIndexStep());
        assertEq(0, game_.getIndexPeriod());
        assertTrue(!game_.getFight().getFightType().isExisting());
        game_.incrementPeriod(area_, data_);
        assertEq(0, game_.getIndexStep());
        assertEq(0, game_.getIndexPeriod());
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1,game_.getFight().getFoeTeam().getMembers().size());
    }

    @Test
    public void initFishing1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initFishing(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(0, game_.getIndexPeriodFishing());
    }

    @Test
    public void initFishing2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initFishing(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(1, game_.getIndexPeriodFishing());
    }

    @Test
    public void initFishing3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initFishing(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(0, game_.getIndexPeriodFishing());
    }

    @Test
    public void initFishing4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(true);
        game_.initFishing(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(0, game_.getIndexPeriodFishing());
    }

    @Test
    public void initLegendaryPokemonFight1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.initLegendaryPokemonFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(MEW, game_.getFight().wildPokemon().getName());
    }

    @Test
    public void initTrainerFight1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(3, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(4, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(13, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(14, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),1));
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(13, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(14, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(3, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(4, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        //game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 10, 4),0));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4),0));
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR, game_.getFight().getFightType());
        assertEq(2, game_.getFight().getFoeTeam().getMembers().size());
        assertEq(3, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).getLevel());
        assertEq(4, game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).getLevel());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.TMP_TRAINER, game_.getFight().getFightType());
        assertTrue(game_.isDualFight());
    }

    @Test
    public void initTrainerFight7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.TMP_TRAINER, game_.getFight().getFightType());
        assertTrue(game_.isDualFight());
    }

    @Test
    public void initTrainerFight8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR_GYM, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.GYM_LEADER, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR_LIGUE, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.initTrainerFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertEq(FightType.DRESSEUR_LIGUE, game_.getFight().getFightType());
        assertTrue(!game_.isDualFight());
    }

    @Test
    public void initTrainerFight12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
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
    private static int nbPk(MonteCarloList<WildPk> _monte) {
        CustList<WildPk> wp_ = new CustList<WildPk>();
        for (WildPk e: _monte.events()) {
            boolean eq_ = false;
            for (WildPk f: wp_) {
                if (e.eq(f)) {
                    eq_ = true;
                }
            }
            if (!eq_) {
                wp_.add(e);
            }
        }
        return wp_.size();
    }
    private static boolean containsPk(MonteCarloList<WildPk> _monte, WildPk _ev) {
        for (WildPk e: _monte.events()) {
            if (e.eq(_ev)) {
                return true;
            }
        }
        return false;
    }
    private static LgInt freqPk(MonteCarloList<WildPk> _monte, WildPk _ev) {
        LgInt sum_ = LgInt.zero();
        for (EventFreq<WildPk> e: _monte.getEvents()) {
            if (e.getEvent().eq(_ev)) {
                sum_.addNb(e.getFreq());
            }
        }
        return sum_;
    }
}
