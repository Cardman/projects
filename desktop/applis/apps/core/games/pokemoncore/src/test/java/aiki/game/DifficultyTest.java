package aiki.game;

import aiki.db.DataBase;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import code.maths.Rate;


public class DifficultyTest extends InitializationDataBase {


    @Test
    public void validate1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvFoe((short) -1);
        diff_.validate(data_);
        assertEq(0, diff_.getIvFoe());
    }

    @Test
    public void validate2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((short) -1);
        diff_.validate(data_);
        assertEq(0, diff_.getIvPlayer());
    }

    @Test
    public void validate3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvFoe((short) 1000);
        diff_.validate(data_);
        assertEq(31, diff_.getIvFoe());
    }

    @Test
    public void validate4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((short) 1000);
        diff_.validate(data_);
        assertEq(31, diff_.getIvPlayer());
    }

    @Test
    public void validate5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setRateLooseMoneyWin(new Rate("-1"));
        diff_.validate(data_);
        assertEq(Rate.one(), diff_.getRateLooseMoneyWin());
    }

    @Test
    public void isValid8Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setRateWinMoneyBase(new Rate("-1"));
        diff_.validate(data_);
        assertEq(Rate.one(), diff_.getRateWinMoneyBase());
    }

    @Test
    public void isValid9Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setRateWinningExpPtsFight(new Rate("-1"));
        diff_.validate(data_);
        assertEq(Rate.one(), diff_.getRateWinningExpPtsFight());
    }

    @Test
    public void isValid10Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setWinTrainerExp(new Rate("-1"));
        diff_.validate(data_);
        assertEq(new Rate("3/2"), diff_.getWinTrainerExp());
    }
}
