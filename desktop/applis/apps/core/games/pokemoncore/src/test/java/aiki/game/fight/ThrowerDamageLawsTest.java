package aiki.game.fight;

import aiki.util.TeamPositionsMonteCarloNumber;
import org.junit.Test;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;



public class ThrowerDamageLawsTest extends InitializationDataBase {

    @Test
    public void min1Test() {
        TeamPosition fighter_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1/2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new TeamPositionsMonteCarloNumber());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new TeamPositionsMonteCarloNumber());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new TeamPositionsMonteCarloNumber());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("1"),thrower_.min(fighter_));
    }

    @Test
    public void max1Test() {
        TeamPosition fighter_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1/2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new TeamPositionsMonteCarloNumber());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new TeamPositionsMonteCarloNumber());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new TeamPositionsMonteCarloNumber());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("8"),thrower_.max(fighter_));
    }

    @Test
    public void avg1Test() {
        TeamPosition fighter_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1/2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new TeamPositionsMonteCarloNumber());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new TeamPositionsMonteCarloNumber());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new TeamPositionsMonteCarloNumber());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("15/4"),thrower_.avg(fighter_));
    }

    @Test
    public void varFromIndependantLaws1Test() {
        assertEq(new Rate("115/16"),ThrowerDamageLaws.varFromIndependantLaws(new Rate("1/16"), new Rate("3/4"), new Rate("9"), new Rate("5")));
    }

    @Test
    public void var1Test() {
        TeamPosition fighter_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        ThrowerDamageLaws thrower_ = new ThrowerDamageLaws();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1/2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setBase(new TeamPositionsMonteCarloNumber());
        thrower_.getBase().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("2"), new LgInt("1"));
        law_.addQuickEvent(new Rate("8"), new LgInt("1"));
        thrower_.setRandomRate(law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setCriticalHit(new TeamPositionsMonteCarloNumber());
        thrower_.getCriticalHit().put(fighter_, law_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate("1"), new LgInt("1"));
        thrower_.setNumberHits(new TeamPositionsMonteCarloNumber());
        thrower_.getNumberHits().put(fighter_, law_);
        assertEq(new Rate("115/16"),thrower_.vr(fighter_));
    }
}
