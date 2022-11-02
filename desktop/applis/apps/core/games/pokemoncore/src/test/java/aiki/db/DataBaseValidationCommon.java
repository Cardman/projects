package aiki.db;

import aiki.fight.pokemon.enums.ExpType;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.util.Coords;
import aiki.util.LawNumber;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;

public abstract class DataBaseValidationCommon extends EquallablePkUtil {
    protected static final String LANGUAGE = "en";
    protected static final String NULL_REF = DataBase.EMPTY_STRING;
    protected static final String PIKACHU = "PIKACHU";
    protected static final String PIKACHU2 = "PIKACHU2";
    protected static final String PIKACHU3 = "PIKACHU3";
    protected static final String PIKACHU4 = "PIKACHU4";
    protected static final String ECLAIR = "ECLAIR";
    protected static final String POKE_BALL = "POKE_BALL";
    protected static final String LUTTE = "LUTTE";
    protected static final String CHARGE = "CHARGE";
    protected static final String CHARGE2 = "CHARGE2";
    protected static final String CHARGE3 = "CHARGE3";
    protected static final String CHARGE4 = "CHARGE4";
    protected static final String TREMPETTE = "TREMPETTE";
    protected static final String TREMPETTE2 = "TREMPETTE2";
    protected static final String TREMPETTE3 = "TREMPETTE3";
    protected static final String PARATONNERRE = "PARATONNERRE";
    protected static final String ELECTRICK = "ELECTRICK";

    protected static void initConstants(DataBase _data) {
        _data.addConstNumTest(DataBase.MAX_EV, new Rate(20));
        _data.addConstNumTest(DataBase.MAX_IV, new Rate(31));
        _data.addConstNumTest(DataBase.DEF_MAX_ATT, new Rate(4));
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
        _data.addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("1"));
        initDefaultConsts(POKE_BALL,
                "caracdroiteferme(div(VAR__FOE_PK_MAX_HP,VAR__FOE_PK_REMOTE_HP),2)",
                "caracdroiteferme(div(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)",
                "div(2*caracgaucheouvert(VAR__BOOST,0),max(2-VAR__BOOST,1))+div((2+VAR__BOOST)*caracdroiteferme(VAR__BOOST,0),2)",
                "puis(2,VAR__BOOST-4)",
                "div((5+VAR__LANCEUR_NIVEAU)*VAR__ATTACK*VAR__POWER,(125*VAR__DEFENSE))",
                LUTTE,
                "METAMORPH", _data);
    }
    protected static void initExpPoints(DataBase _data) {
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

    protected static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    protected static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    protected static Point newPoint(int _x, int _y) {
        return new Point((short)_x, (short)_y);
    }

    protected static void initRandomLaws(DataBase _data) {
        MonteCarloNumber monteCarloNumber_;
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("12"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,(short)4));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,(short)3));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("12"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,(short)2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,(short)1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,(short)5));
    }
}
