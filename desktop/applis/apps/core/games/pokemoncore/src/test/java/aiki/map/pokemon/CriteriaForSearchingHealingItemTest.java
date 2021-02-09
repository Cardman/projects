package aiki.map.pokemon;

import aiki.db.DataBase;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.fight.InitializationDataBase;
import code.maths.Rate;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;


public class CriteriaForSearchingHealingItemTest extends InitializationDataBase {

    @Test
    public void matchClasses1Test() {
        DataBase data_ = initDb();
        assertTrue(CriteriaForSearchingHealingItem.matchClasses(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchClasses2Test() {
        DataBase data_ = initDb();
        assertTrue(CriteriaForSearchingHealingItem.matchClasses(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchClasses3Test() {
        DataBase data_ = initDb();
        assertTrue(!CriteriaForSearchingHealingItem.matchClasses(data_.getItem(MULTI_EXP)));
    }

    @Test
    public void getStatus1Test() {
        DataBase data_ = initDb();
        assertEq(0, CriteriaForSearchingHealingItem.getStatus(data_.getItem(BAIE_MEPO)).size());
    }

    @Test
    public void getStatus2Test() {
        DataBase data_ = initDb();
        assertEq(0, CriteriaForSearchingHealingItem.getStatus(data_.getItem(EAU_FRAICHE)).size());
    }

    @Test
    public void getStatus3Test() {
        DataBase data_ = initDb();
        StringList st_ = CriteriaForSearchingHealingItem.getStatus(data_.getItem(BAIE_CERIZ));
        assertEq(1, st_.size());
        assertEq(PARALYSIE, st_.first());
    }

    @Test
    public void getStatus4Test() {
        DataBase data_ = initDb();
        StringList st_ = CriteriaForSearchingHealingItem.getStatus(data_.getItem(TOTAL_SOIN));
        assertEq(7, st_.size());
        assertTrue(StringUtil.contains(st_, BRULURE));
        assertTrue(StringUtil.contains(st_, GEL));
        assertTrue(StringUtil.contains(st_, PARALYSIE));
        assertTrue(StringUtil.contains(st_, POISON_ST));
        assertTrue(StringUtil.contains(st_, POISON_GRAVE));
        assertTrue(StringUtil.contains(st_, SOMMEIL));
        assertTrue(StringUtil.contains(st_, SOMMEIL_REPOS));
    }

    @Test
    public void matchStatus1Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        assertTrue(criteria_.matchStatus(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchStatus2Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        assertTrue(criteria_.matchStatus(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchStatus3Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        assertTrue(criteria_.matchStatus(data_.getItem(BAIE_CERIZ)));
    }

    @Test
    public void matchStatus4Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        assertTrue(criteria_.matchStatus(data_.getItem(TOTAL_SOIN)));
    }

    @Test
    public void matchStatus5Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        criteria_.setContentOfStatus(CONFUSION);
        criteria_.setSearchModeStatus(SearchingMode.WHOLE_STRING);
        assertTrue(!criteria_.matchStatus(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchStatus6Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        criteria_.setContentOfStatus(CONFUSION);
        criteria_.setSearchModeStatus(SearchingMode.WHOLE_STRING);
        assertTrue(!criteria_.matchStatus(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchStatus7Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        criteria_.setContentOfStatus(CONFUSION);
        criteria_.setSearchModeStatus(SearchingMode.WHOLE_STRING);
        assertTrue(!criteria_.matchStatus(data_.getItem(BAIE_CERIZ)));
    }

    @Test
    public void matchStatus8Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        criteria_.setContentOfStatus(CONFUSION);
        criteria_.setSearchModeStatus(SearchingMode.WHOLE_STRING);
        assertTrue(!criteria_.matchStatus(data_.getItem(TOTAL_SOIN)));
    }

    @Test
    public void matchStatus9Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        criteria_.setContentOfStatus(PARALYSIE);
        criteria_.setSearchModeStatus(SearchingMode.WHOLE_STRING);
        assertTrue(criteria_.matchStatus(data_.getItem(BAIE_CERIZ)));
    }

    @Test
    public void matchStatus10Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setTranslatedStatus(data_.getTranslatedStatus().getVal(LANGUAGE));
        criteria_.setContentOfStatus(SOMMEIL);
        criteria_.setSearchModeStatus(SearchingMode.WHOLE_STRING);
        assertTrue(criteria_.matchStatus(data_.getItem(TOTAL_SOIN)));
    }

    @Test
    public void matchHp1Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchHp(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchHp2Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchHp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchHp3Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchHp(data_.getItem(BAIE_CERIZ)));
    }

    @Test
    public void matchHp4Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchHp(data_.getItem(TOTAL_SOIN)));
    }

    /*@Test
    public void matchHp5Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchHp(data.getItem(EAU_FRAICHE)));
    }*/

    @Test
    public void matchHp6Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        assertTrue(criteria_.matchHp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchHp7Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        criteria_.setMinHp(new Rate("100"));
        assertTrue(!criteria_.matchHp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchHp8Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        criteria_.setMaxHp(new Rate("100"));
        assertTrue(criteria_.matchHp(data_.getItem(EAU_FRAICHE)));
    }

    /*@Test
    public void matchHp9Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchHp(data.getItem(BAIE_ORAN)));
    }*/

    @Test
    public void matchHp10Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        assertTrue(criteria_.matchHp(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchHp11Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        criteria_.setMinHp(new Rate("100"));
        assertTrue(!criteria_.matchHp(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchHp12Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        criteria_.setMaxHp(new Rate("100"));
        assertTrue(criteria_.matchHp(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchHp13Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        assertTrue(!criteria_.matchHp(data_.getItem(BAIE_ENIGMA)));
    }

    /*@Test
    public void matchHp14Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(criteria_.matchHp(data.getItem(BAIE_ENIGMA)));
    }*/

    @Test
    public void matchHp15Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        criteria_.setMinHp(new Rate("1/2"));
        assertTrue(!criteria_.matchHp(data_.getItem(BAIE_ENIGMA)));
    }

    /*@Test
    public void matchHp16Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        criteria_.setMaxHp(new Rate("1/2"));
        assertTrue(criteria_.matchHp(data.getItem(BAIE_ENIGMA)));
    }*/

    @Test
    public void matchHp17Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        assertTrue(!criteria_.matchHp(data_.getItem(BAIE_GOWAV)));
    }

    /*@Test
    public void matchHp18Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(criteria_.matchHp(data.getItem(BAIE_GOWAV)));
    }*/

    @Test
    public void matchHp19Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        criteria_.setMinHp(new Rate("1/2"));
        assertTrue(!criteria_.matchHp(data_.getItem(BAIE_GOWAV)));
    }

    /*@Test
    public void matchHp20Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        criteria_.setMaxHp(new Rate("1/2"));
        assertTrue(criteria_.matchHp(data.getItem(BAIE_GOWAV)));
    }*/

    /*@Test
    public void matchHp21Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setMinHp(new Rate("1/16"));
        criteria_.setMaxHp(new Rate("1/2"));
        assertTrue(criteria_.matchHp(data.getItem(BAIE_ENIGMA)));
    }*/

    @Test
    public void matchHp22Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setMinHp(new Rate("1/16"));
        criteria_.setMaxHp(new Rate("50"));
        assertTrue(criteria_.matchHp(data_.getItem(BAIE_ORAN)));
    }

    /*@Test
    public void matchHp23Test() {
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setMinHp(new Rate("1/16"));
        criteria_.setMaxHp(new Rate("50"));
        assertTrue(criteria_.matchHp(data.getItem(BAIE_GOWAV)));
    }*/

    @Test
    public void matchHp24Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setMinHp(new Rate("1/4"));
        criteria_.setMaxHp(new Rate("50"));
        assertTrue(!criteria_.matchHp(data_.getItem(BAIE_GOWAV)));
    }

    @Test
    public void matchHp25Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchHp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchHp26Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.NO);
        assertTrue(!criteria_.matchHp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchRateHp1Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchRateHp(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchRateHp2Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchRateHp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchRateHp3Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchRateHp(data_.getItem(BAIE_CERIZ)));
    }

    @Test
    public void matchRateHp4Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchRateHp(data_.getItem(TOTAL_SOIN)));
    }

    @Test
    public void matchRateHp5Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchRateHp(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchRateHp6Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchRateHp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchRateHp7Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchRateHp(data_.getItem(BAIE_CERIZ)));
    }

    @Test
    public void matchRateHp8Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchRateHp(data_.getItem(TOTAL_SOIN)));
    }

    @Test
    public void matchRateHp9Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(criteria_.matchRateHp(data_.getItem(BAIE_ENIGMA)));
    }

    @Test
    public void matchRateHp10Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        assertTrue(criteria_.matchRateHp(data_.getItem(PETIT_RAPPEL)));
    }

    @Test
    public void matchRateHp11Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        criteria_.setMinRateHp(new Rate("1/2"));
        assertTrue(!criteria_.matchRateHp(data_.getItem(BAIE_ENIGMA)));
    }

    @Test
    public void matchRateHp12Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRateHp(SelectedBoolean.YES);
        criteria_.setMinRateHp(new Rate("1/2"));
        assertTrue(!criteria_.matchRateHp(data_.getItem(PETIT_RAPPEL)));
    }

    @Test
    public void matchRateHp13Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchRateHp(data_.getItem(PETIT_RAPPEL)));
    }

    @Test
    public void matchPp1Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchPp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchPp2Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchPp(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchPp3Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchPp(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchPp4Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp5Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp6Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchPp7Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchPp8Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchPp9Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp10Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp11Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        assertTrue(criteria_.matchPp(data_.getItem(MAX_ELIXIR)));
    }

    @Test
    public void matchPp12Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        assertTrue(criteria_.matchPp(data_.getItem(HUILE_MAX)));
    }

    @Test
    public void matchPp13Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchPp14Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchPp15Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        assertTrue(criteria_.matchPp(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchPp16Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        assertTrue(criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp17Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        assertTrue(criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp18Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(MAX_ELIXIR)));
    }

    @Test
    public void matchPp19Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE_MAX)));
    }

    @Test
    public void matchPp20Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp21Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp22Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setMaxPp((long) 20);
        assertTrue(criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp23Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setMaxPp((long) 20);
        assertTrue(criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp24Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchPp25Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        criteria_.setHealOneMove(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(MAX_ELIXIR)));
    }

    @Test
    public void matchPp26Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        criteria_.setHealOneMove(SelectedBoolean.YES);
        assertTrue(criteria_.matchPp(data_.getItem(HUILE_MAX)));
    }

    @Test
    public void matchPp27Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setHealOneMove(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp28Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setHealOneMove(SelectedBoolean.YES);
        assertTrue(criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp29Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setHealOneMove(SelectedBoolean.YES);
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp30Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp31Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp32Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(criteria_.matchPp(data_.getItem(MAX_ELIXIR)));
    }

    @Test
    public void matchPp33Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.YES);
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE_MAX)));
    }

    @Test
    public void matchPp34Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setRelativeRatePp(SelectedBoolean.NO);
        criteria_.setHealOneMove(SelectedBoolean.NO);
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp35Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE_MAX)));
    }

    @Test
    public void matchPp36Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.YES);
        assertTrue(!criteria_.matchPp(data_.getItem(MAX_ELIXIR)));
    }

    @Test
    public void matchPp37Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.YES);
        assertTrue(criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp38Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp39Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.YES);
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp40Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.NO);
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchPp41Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.YES);
        assertTrue(criteria_.matchPp(data_.getItem(HUILE_MAX)));
    }

    @Test
    public void matchPp42Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setHealOneMove(SelectedBoolean.NO);
        assertTrue(criteria_.matchPp(data_.getItem(MAX_ELIXIR)));
    }

    @Test
    public void matchPp43Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchPp(data_.getItem(HUILE_MAX)));
    }

    @Test
    public void matchPp44Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchPp(data_.getItem(MAX_ELIXIR)));
    }

    @Test
    public void matchPp45Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(HUILE)));
    }

    @Test
    public void matchPp46Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setMinPp((long) 20);
        assertTrue(!criteria_.matchPp(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchStatistic1Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchStatistic(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchStatistic2Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchStatistic(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchStatistic3Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchStatistic(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchStatistic4Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchStatistic(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchStatistic5Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchStatistic(data_.getItem(HUILE)));
    }

    @Test
    public void matchStatistic6Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setStatistic(Statistic.ATTACK);
        assertTrue(!criteria_.matchStatistic(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchStatistic7Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setStatistic(Statistic.ATTACK);
        assertTrue(!criteria_.matchStatistic(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchStatistic8Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setStatistic(Statistic.ATTACK);
        assertTrue(!criteria_.matchStatistic(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchStatistic9Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setStatistic(Statistic.ATTACK);
        assertTrue(!criteria_.matchStatistic(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchStatistic10Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setStatistic(Statistic.ATTACK);
        assertTrue(!criteria_.matchStatistic(data_.getItem(HUILE)));
    }

    @Test
    public void matchStatistic11Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setStatistic(Statistic.ATTACK);
        assertTrue(!criteria_.matchStatistic(data_.getItem(BAIE_LANSAT)));
    }

    @Test
    public void matchStatistic12Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setStatistic(Statistic.CRITICAL_HIT);
        assertTrue(criteria_.matchStatistic(data_.getItem(BAIE_LANSAT)));
    }

    @Test
    public void matchKo1Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchKo(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchKo2Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchKo(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchKo3Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchKo(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchKo4Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchKo(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchKo5Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchKo(data_.getItem(HUILE)));
    }

    @Test
    public void matchKo6Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        assertTrue(criteria_.matchKo(data_.getItem(RAPPEL)));
    }

    @Test
    public void matchKo7Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.NO);
        assertTrue(criteria_.matchKo(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchKo8Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.NO);
        assertTrue(criteria_.matchKo(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchKo9Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.NO);
        assertTrue(criteria_.matchKo(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchKo10Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.NO);
        assertTrue(criteria_.matchKo(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchKo11Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.NO);
        assertTrue(criteria_.matchKo(data_.getItem(HUILE)));
    }

    @Test
    public void matchKo12Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.NO);
        assertTrue(!criteria_.matchKo(data_.getItem(RAPPEL)));
    }

    @Test
    public void matchKo13Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.YES);
        assertTrue(!criteria_.matchKo(data_.getItem(EAU_FRAICHE)));
    }

    @Test
    public void matchKo14Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.YES);
        assertTrue(!criteria_.matchKo(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchKo15Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.YES);
        assertTrue(!criteria_.matchKo(data_.getItem(BAIE_MEPO)));
    }

    @Test
    public void matchKo16Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.YES);
        assertTrue(!criteria_.matchKo(data_.getItem(ELIXIR)));
    }

    @Test
    public void matchKo17Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.YES);
        assertTrue(!criteria_.matchKo(data_.getItem(HUILE)));
    }

    @Test
    public void matchKo18Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingHealingItem criteria_;
        criteria_ = new CriteriaForSearchingHealingItem();
        criteria_.setKo(SelectedBoolean.YES);
        assertTrue(criteria_.matchKo(data_.getItem(RAPPEL)));
    }
}
