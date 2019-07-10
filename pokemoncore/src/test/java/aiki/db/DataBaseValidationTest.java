package aiki.db;

import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.fight.util.TypesDuo;
import aiki.game.fight.CheckNumericStringsFight;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.places.Place;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.LawNumber;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.ObjectMap;
import code.util.ShortMap;
import code.util.StringList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class DataBaseValidationTest {
    private static final String LANGUAGE = "en";
    private static final String NULL_REF = DataBase.EMPTY_STRING;
    private static final String PIKACHU = "PIKACHU";
    private static final String ECLAIR = "ECLAIR";
    private static final String POKE_BALL = "POKE_BALL";
    private static final String LUTTE = "LUTTE";
    private static final String PARATONNERRE = "PARATONNERRE";
    private static final String ELECTRICK = "ELECTRICK";

    @Test
    public void fail1Test() {
        PokemonData pkData_ = Instances.newPokemonData();
        AbilityData ab_ = Instances.newAbilityData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower("INVALID");
        eff_.setFail("INVALID");
        eff_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(eff_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setTypes(new StringList(ELECTRICK));
        Ball ball_ = Instances.newBall();
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(ECLAIR,move_);
        pkData_.setTypes(new StringList(ELECTRICK));
        pkData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)0));
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        data_.completeMembers(PIKACHU,pkData_);
        data_.completeMembers(POKE_BALL,ball_);
        data_.completeMembers(PARATONNERRE,ab_);
        data_.getTableTypes().put(new TypesDuo(ELECTRICK,ELECTRICK),new Rate("1"));
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(2);
        DataMap map_ = data_.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        data_.setRateBoost("-2");
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.initCombosTest();
        data_.initTypesByTable();
        data_.setCheckTranslation(false);
        CheckNumericStringsFight.validateNumericBooleanStrings(data_);
        assertTrue(data_.isError());
    }
    static void initConstants(DataBase _data) {
        _data.addConstNumTest(DataBase.MAX_EV, new Rate(20));
        _data.addConstNumTest(DataBase.MAX_IV, new Rate(31));
        _data.addConstNumTest("DEF_MAX_ATT", new Rate(4));
        _data.addConstNumTest(DataBase.DEF_PKEQ, new Rate(6));
        _data.addConstNumTest(DataBase.ARGENT, new Rate(3000));
        _data.addConstNumTest(DataBase.NIVEAU_PK_ECLOSION, new Rate(1));
        _data.addConstNumTest(DataBase.NIVEAU_PK_MAX, new Rate(100));
        _data.addConstNumTest(DataBase.EVO_BONHEUR, new Rate(110));
        _data.addConstNumTest(DataBase.MAX_BONHEUR, new Rate(170));
        _data.addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        _data.addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR, new Rate(10));
        _data.addConstNumTest(DataBase.PP_MAX, new Rate(80));
        _data.addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        _data.addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        _data.addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        _data.addConstNumTest(DataBase.MIN_HP, new Rate(1));
        _data.addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        _data.addConstNumTest(DataBase.MAX_STEPS, new Rate("1024"));
        _data.addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE, new Rate("256"));
        _data.initDefaultConsts(POKE_BALL,
                "caracdroiteferme(div(FOE_PK_MAX_HP,FOE_PK_REMOTE_HP),2)",
                "caracdroiteferme(div(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)",
                "div(2*caracgaucheouvert(VAR__BOOST,0),max(2-VAR__BOOST,1))+div((2+VAR__BOOST)*caracdroiteferme(VAR__BOOST,0),2)",
                "puis(2,VAR__BOOST-4)",
                "div((5+VAR__LANCEUR_NIVEAU)*VAR__ATTACK*VAR__POWER,(125*VAR__DEFENSE))",
                LUTTE,
                "METAMORPH");
    }
    static void initExpPoints(DataBase _data) {
        _data.getExpGrowth().put(ExpType.E,"2*NIVEAU");
        _data.getExpGrowth().put(ExpType.L,"5/4*puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.M,"puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.P,"puis(VAR__NIVEAU,2)");
        _data.getExpGrowth().put(ExpType.F,"VAR__NIVEAU");
        _data.getExpGrowth().put(ExpType.R,"4/5*puis(VAR__NIVEAU,3)");
        _data.getRates().put(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().put(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }

    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Point newPoint(int _x, int _y) {
        return new Point((short)_x, (short)_y);
    }

    private static void initRandomLaws(DataBase _data) {
        MonteCarloNumber monteCarloNumber_;
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("49/50"),new LgInt("14"));
        monteCarloNumber_.addEvent(new Rate("23/25"),new LgInt("8"));
        monteCarloNumber_.addEvent(new Rate("89/100"),new LgInt("5"));
        monteCarloNumber_.addEvent(new Rate("19/20"),new LgInt("11"));
        monteCarloNumber_.addEvent(new Rate("99/100"),new LgInt("15"));
        monteCarloNumber_.addEvent(new Rate("24/25"),new LgInt("12"));
        monteCarloNumber_.addEvent(new Rate("43/50"),new LgInt("2"));
        monteCarloNumber_.addEvent(new Rate("91/100"),new LgInt("7"));
        monteCarloNumber_.addEvent(new Rate("47/50"),new LgInt("10"));
        monteCarloNumber_.addEvent(new Rate("93/100"),new LgInt("9"));
        monteCarloNumber_.addEvent(new Rate("97/100"),new LgInt("13"));
        monteCarloNumber_.addEvent(new Rate("9/10"),new LgInt("6"));
        monteCarloNumber_.addEvent(new Rate("22/25"),new LgInt("4"));
        monteCarloNumber_.addEvent(new Rate("87/100"),new LgInt("3"));
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("16"));
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,(short)4));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("99/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("9/10"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("47/50"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("49/50"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("93/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("24/25"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("19/20"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("22/25"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("97/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("43/50"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("89/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("91/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("23/25"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("87/100"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,(short)3));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("24/25"),new LgInt("5"));
        monteCarloNumber_.addEvent(new Rate("19/20"),new LgInt("6"));
        monteCarloNumber_.addEvent(new Rate("9/10"),new LgInt("11"));
        monteCarloNumber_.addEvent(new Rate("99/100"),new LgInt("2"));
        monteCarloNumber_.addEvent(new Rate("43/50"),new LgInt("15"));
        monteCarloNumber_.addEvent(new Rate("87/100"),new LgInt("14"));
        monteCarloNumber_.addEvent(new Rate("23/25"),new LgInt("9"));
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("93/100"),new LgInt("8"));
        monteCarloNumber_.addEvent(new Rate("91/100"),new LgInt("10"));
        monteCarloNumber_.addEvent(new Rate("47/50"),new LgInt("7"));
        monteCarloNumber_.addEvent(new Rate("22/25"),new LgInt("13"));
        monteCarloNumber_.addEvent(new Rate("97/100"),new LgInt("4"));
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("16"));
        monteCarloNumber_.addEvent(new Rate("49/50"),new LgInt("3"));
        monteCarloNumber_.addEvent(new Rate("89/100"),new LgInt("12"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,(short)2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,(short)1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,(short)5));
    }
}
