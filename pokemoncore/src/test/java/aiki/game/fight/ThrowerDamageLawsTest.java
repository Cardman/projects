package aiki.game.fight;
import static code.maths.EquallableMathUtil.assertEq;

import org.junit.BeforeClass;
import org.junit.Test;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.ObjectMap;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.TeamPosition;
import aiki.game.fight.ThrowerDamageLaws;

@SuppressWarnings("static-method")
public class ThrowerDamageLawsTest extends InitializationDataBase {

    @BeforeClass
    public static void initDataBase() {
        InitializationDataBase.initDataBase();
    }

    @Test
    public void min1Test() {
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1/2"), new LgInt("1"));
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("2"), new LgInt("1"));
        law_.addEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("1"),thrower_.min(fighter_));
    }

    @Test
    public void max1Test() {
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1/2"), new LgInt("1"));
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("2"), new LgInt("1"));
        law_.addEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("8"),thrower_.max(fighter_));
    }

    @Test
    public void avg1Test() {
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1/2"), new LgInt("1"));
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("2"), new LgInt("1"));
        law_.addEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("15/4"),thrower_.avg(fighter_));
    }

    @Test
    public void varFromIndependantLaws1Test() {
        assertEq(new Rate("115/16"),ThrowerDamageLaws.varFromIndependantLaws(new Rate("1/16"), new Rate("3/4"), new Rate("9"), new Rate("5")));
    }

    @Test
    public void var1Test() {
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1/2"), new LgInt("1"));
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("2"), new LgInt("1"));
        law_.addEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("115/16"),thrower_.var(fighter_));
    }
}
