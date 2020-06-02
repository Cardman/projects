package aiki.facade;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import code.util.CustList;
import org.junit.Before;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.game.fight.InitializationDataBase;
import aiki.game.player.Inventory;
import aiki.util.SortingHealingItem;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;


public class PaginationHealingItemTest extends InitializationDataBase {

    private static final String BERRY = "berry";
    private static final String HEALING_ITEM = "healing item";
    private static final String PP = "pp";

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void match1Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("P*");
        assertTrue(!pagination_.match(BAIE_ORAN));
    }

    @Test
    public void match2Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("P*");
        assertTrue(pagination_.match(POTION));
    }

    @Test
    public void sortable1Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable2Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable3Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpPrice().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable4Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpPrice().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable5Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpDescription().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable6Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpHealOneMove().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable7Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpHp().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable8Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpStatistics().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable9Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpNbHealedStatus().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable10Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpPp().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable11Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpKo().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable12Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpRelativeRateHp().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable13Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpRelativeRatePp().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable14Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpNumber().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable15Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpRateHp().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sort1Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(0);
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(HEALING_ITEM);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(3);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpDescription().setPriority(2);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(1);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(POTION, itemName_);
    }

    @Test
    public void sort2Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(0);
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(HEALING_ITEM);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(2);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(POTION, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(BAIE_ORAN, itemName_);
    }

    @Test
    public void sort3Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(0);
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(HEALING_ITEM);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpHp().setPriority(2);
        pagination_.getCmpHp().setIncreasing(SelectedBoolean.NO);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(POTION, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(BAIE_ORAN, itemName_);
    }

    @Test
    public void sort4Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(HUILE);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setHealOneMove(true);
        sorting_.setIndex(0);
        itemName_ = HUILE;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(ELIXIR);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setIndex(1);
        itemName_ = ELIXIR;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpHealOneMove().setPriority(2);
        pagination_.getCmpHealOneMove().setIncreasing(SelectedBoolean.NO);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(ELIXIR, itemName_);
    }

    @Test
    public void sort5Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(HUILE);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setHealOneMove(true);
        sorting_.setIndex(0);
        itemName_ = HUILE;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(ELIXIR);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setIndex(1);
        itemName_ = ELIXIR;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpHealOneMove().setPriority(2);
        pagination_.getCmpHealOneMove().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(ELIXIR, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(HUILE, itemName_);
    }

    @Test
    public void sort6Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(RAPPEL);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setKo(true);
        sorting_.setIndex(0);
        itemName_ = RAPPEL;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpKo().setPriority(2);
        pagination_.getCmpKo().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(POTION, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(RAPPEL, itemName_);
    }

    @Test
    public void sort7Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(HUILE);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setIndex(0);
        itemName_ = HUILE;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(ELIXIR);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("20"));
        sorting_.setIndex(1);
        itemName_ = ELIXIR;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPp().setPriority(2);
        pagination_.getCmpPp().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(ELIXIR, itemName_);
    }

    @Test
    public void sort8Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(REVEIL);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setNbHealedStatus(1);
        sorting_.setIndex(0);
        itemName_ = REVEIL;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(ELIXIR);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("20"));
        sorting_.setIndex(1);
        itemName_ = ELIXIR;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpNbHealedStatus().setPriority(2);
        pagination_.getCmpNbHealedStatus().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(ELIXIR, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(REVEIL, itemName_);
    }

    @Test
    public void sort9Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_LANSAT);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setNbStatistics(1);
        sorting_.setIndex(0);
        itemName_ = BAIE_LANSAT;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(ELIXIR);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("20"));
        sorting_.setIndex(1);
        itemName_ = ELIXIR;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpStatistics().setPriority(2);
        pagination_.getCmpStatistics().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(ELIXIR, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(BAIE_LANSAT, itemName_);
    }

    @Test
    public void sort10Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setIndex(0);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION_MAX);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("20"));
        sorting_.setRelativeRateHp(true);
        sorting_.setIndex(1);
        itemName_ = POTION_MAX;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpRelativeRateHp().setPriority(2);
        pagination_.getCmpRelativeRateHp().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(POTION, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(POTION_MAX, itemName_);
    }

    @Test
    public void sort11Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(HUILE);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setIndex(0);
        itemName_ = HUILE;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(HUILE_MAX);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setRelativeRatePp(true);
        sorting_.setIndex(1);
        itemName_ = HUILE_MAX;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpRelativeRatePp().setPriority(2);
        pagination_.getCmpRelativeRatePp().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(HUILE_MAX, itemName_);
    }

    @Test
    public void sort12Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(0);
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(HEALING_ITEM);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpDescription().setPriority(2);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(1);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(POTION, itemName_);
    }

    @Test
    public void sort13Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setNumber(LgInt.one());
        sorting_.setIndex(0);
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(HEALING_ITEM);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpNumber().setPriority(1);
        pagination_.getCmpNumber().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(POTION, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(BAIE_ORAN, itemName_);
    }

    @Test
    public void sort14Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(50);
        sorting_.setHpRate(new Rate("1/4"));
        sorting_.setHp(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setNumber(LgInt.one());
        sorting_.setIndex(0);
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(HEALING_ITEM);
        sorting_.setPrice(25);
        sorting_.setHpRate(new Rate("1/2"));
        sorting_.setHp(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpRateHp().setPriority(1);
        pagination_.getCmpRateHp().setIncreasing(SelectedBoolean.NO);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(POTION, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(BAIE_ORAN, itemName_);
    }

    @Test
    public void sort15Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(0);
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(POTION);
        sorting_.setItemClass(HEALING_ITEM);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(Rate.zero());
        sorting_.setIndex(1);
        itemName_ = POTION;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpHp().setPriority(2);
        pagination_.getCmpHp().setIncreasing(SelectedBoolean.YES_AND_NO);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(POTION, itemName_);
    }

    @Test
    public void sort16Test() {
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setTranslation(data, LANGUAGE);
        SortingHealingItem sorting_;
        String itemName_;
        sorting_ = new SortingHealingItem();
        sorting_.setName(HUILE);
        sorting_.setItemClass(PP);
        sorting_.setPrice(50);
        sorting_.setHp(new Rate("20"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setIndex(0);
        itemName_ = HUILE;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingHealingItem();
        sorting_.setName(HUILE_MAX);
        sorting_.setItemClass(PP);
        sorting_.setPrice(25);
        sorting_.setHp(new Rate("30"));
        sorting_.setHpRate(Rate.zero());
        sorting_.setPp(new Rate("10"));
        sorting_.setRelativeRatePp(true);
        sorting_.setIndex(1);
        itemName_ = HUILE_MAX;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpRelativeRatePp().setPriority(2);
        pagination_.getCmpRelativeRatePp().setIncreasing(SelectedBoolean.YES_AND_NO);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(HUILE_MAX, itemName_);
    }
    @Test
    public void search1Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        //pagination_.getCriteria().setSelectedClass(Berry.class);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(4, pagination_.getResults().size());
        String itemName_;
        CustList<SortingHealingItem> sorted_;
        SortingHealingItem elt_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_MEPO, itemName_);
        assertEq(BAIE_MEPO, elt_.getName());
        assertEq(BAIE_MEPO, elt_.getKeyName());
        assertEq(LgInt.one(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(), elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        assertEq(new Rate("10"), elt_.getPp());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(POTION, itemName_);
        assertEq(POTION, elt_.getName());
        assertEq(POTION, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHp.ITEM, elt_.getItemClass());
        assertEq(3, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("20"),elt_.getHp());
        assertEq(Rate.zero(), elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(2);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(POTION_MAX, itemName_);
        assertEq(POTION_MAX, elt_.getName());
        assertEq(POTION_MAX, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(2500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.one(), elt_.getHpRate());
        assertEq(Rate.zero(), elt_.getPp());
        assertEq(Rate.zero(), elt_.getHp());
        elt_ = sorted_.get(3);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(RAPPEL, itemName_);
        assertEq(RAPPEL, elt_.getName());
        assertEq(RAPPEL, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(1500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(elt_.isKo());
        assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("1/2"),elt_.getHpRate());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHp());
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_MEPO, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search2Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("..I*");
        //pagination_.getCriteria().setSelectedClass(Berry.class);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(6, pagination_.getResults().size());
        String itemName_;
        SortingHealingItem elt_;
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_ORAN, itemName_);
        assertEq(BAIE_ORAN, elt_.getName());
        assertEq(BAIE_ORAN, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("10"), elt_.getHp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_MEPO, itemName_);
        assertEq(BAIE_MEPO, elt_.getName());
        assertEq(BAIE_MEPO, elt_.getKeyName());
        assertEq(LgInt.one(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(2);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(HUILE, itemName_);
        assertEq(HUILE, elt_.getName());
        assertEq(HUILE, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingPp.ITEM, elt_.getItemClass());
        assertEq(500, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(3);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(HUILE_MAX, itemName_);
        assertEq(HUILE_MAX, elt_.getName());
        assertEq(HUILE_MAX, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingPp.ITEM, elt_.getItemClass());
        assertEq(1100, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(elt_.isRelativeRatePp());
        assertEq(new Rate("50"), elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(4);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(ELIXIR, itemName_);
        assertEq(ELIXIR, elt_.getName());
        assertEq(ELIXIR, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingPp.ITEM, elt_.getItemClass());
        assertEq(1000, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(5);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_LANSAT, itemName_);
        assertEq(BAIE_LANSAT, elt_.getName());
        assertEq(BAIE_LANSAT, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(1, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search3Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(Berry.ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(4, pagination_.getResults().size());
        String itemName_;
        SortingHealingItem elt_;
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_ORAN, itemName_);
        assertEq(BAIE_ORAN, elt_.getName());
        assertEq(BAIE_ORAN, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("10"), elt_.getHp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_MEPO, itemName_);
        assertEq(BAIE_MEPO, elt_.getName());
        assertEq(BAIE_MEPO, elt_.getKeyName());
        assertEq(LgInt.one(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(2);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_LANSAT, itemName_);
        assertEq(BAIE_LANSAT, elt_.getName());
        assertEq(BAIE_LANSAT, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(1, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(3);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_ENIGMA, itemName_);
        assertEq(BAIE_ENIGMA, elt_.getName());
        assertEq(BAIE_ENIGMA, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(new Rate("1/4"), elt_.getHpRate());
        assertEq(Rate.zero(), elt_.getHp());
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search4Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(HealingPp.ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(4, pagination_.getResults().size());
        String itemName_;
        SortingHealingItem elt_;
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(HUILE, itemName_);
        assertEq(HUILE, elt_.getName());
        assertEq(HUILE, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingPp.ITEM, elt_.getItemClass());
        assertEq(500, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(HUILE_MAX, itemName_);
        assertEq(HUILE_MAX, elt_.getName());
        assertEq(HUILE_MAX, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingPp.ITEM, elt_.getItemClass());
        assertEq(1100, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(elt_.isRelativeRatePp());
        assertEq(new Rate("50"), elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(2);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(ELIXIR, itemName_);
        assertEq(ELIXIR, elt_.getName());
        assertEq(ELIXIR, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingPp.ITEM, elt_.getItemClass());
        assertEq(1000, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(3);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(MAX_ELIXIR, itemName_);
        assertEq(MAX_ELIXIR, elt_.getName());
        assertEq(MAX_ELIXIR, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingPp.ITEM, elt_.getItemClass());
        assertEq(3000, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(elt_.isRelativeRatePp());
        assertEq(new Rate("50"), elt_.getPp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getHpRate());
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(HUILE, sorted_.get(0).getName());
        assertEq(500, sorted_.get(0).getPrice());
        assertEq(HealingPp.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search5Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(HealingStatus.ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(3, pagination_.getResults().size());
        String itemName_;
        SortingHealingItem elt_;
        CustList<SortingHealingItem> sorted_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(REVEIL, itemName_);
        assertEq(REVEIL, elt_.getName());
        assertEq(REVEIL, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(2, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingStatus.ITEM, elt_.getItemClass());
        assertEq(100, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(POTION_MAX, itemName_);
        assertEq(POTION_MAX, elt_.getName());
        assertEq(POTION_MAX, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(2500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.one(), elt_.getHpRate());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHp());
        elt_ = sorted_.get(2);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(RAPPEL, itemName_);
        assertEq(RAPPEL, elt_.getName());
        assertEq(RAPPEL, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(1500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(elt_.isKo());
        assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("1/2"),elt_.getHpRate());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHp());
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(REVEIL, sorted_.get(0).getName());
        assertEq(100, sorted_.get(0).getPrice());
        assertEq(HealingStatus.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search6Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(HealingStatus.ITEM);
        pagination_.getCriteria().setMinPp((long) 1);
        pagination_.getCriteria().setMaxPp((long) 1);
//        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
//        pagination_.getCriteria().setContentOfName("..I*");
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(0, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search7Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(HealingStatus.ITEM);
        pagination_.getCriteria().setMinHp(Rate.one());
        pagination_.getCriteria().setMaxHp(Rate.one());
//        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
//        pagination_.getCriteria().setContentOfName("..I*");
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(0, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search8Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSearchModeStatus(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfStatus(SOMMEIL);
//        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
//        pagination_.getCriteria().setContentOfName("..I*");
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(1, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(REVEIL, sorted_.get(0).getName());
        assertEq(100, sorted_.get(0).getPrice());
        assertEq(HealingStatus.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search9Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setStatistic(Statistic.CRITICAL_HIT);
//        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
//        pagination_.getCriteria().setContentOfName("..I*");
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(1, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_LANSAT, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search10Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setMinPrice(1L);
        pagination_.getCriteria().setMaxPrice(1L);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(0, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search11Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setKo(SelectedBoolean.YES);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(1, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(RAPPEL, sorted_.get(0).getName());
        assertEq(1500, sorted_.get(0).getPrice());
        assertEq(HealingHpStatus.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search12Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setContentOfDescription("Z*");
        pagination_.getCriteria().setSearchModeDescription(SearchingMode.META_CHARACTER);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(0, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search13Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        pagination_.getCmpDescription().setPriority(2);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(2);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        //pagination_.getCriteria().setSelectedClass(Berry.class);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(4, pagination_.getResults().size());
        String itemName_;
        CustList<SortingHealingItem> sorted_;
        SortingHealingItem elt_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_MEPO, itemName_);
        assertEq(BAIE_MEPO, elt_.getName());
        assertEq(BAIE_MEPO, elt_.getKeyName());
        assertEq(LgInt.one(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(POTION, itemName_);
        assertEq(POTION, elt_.getName());
        assertEq(POTION, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHp.ITEM, elt_.getItemClass());
        assertEq(3, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("20"),elt_.getHp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHpRate());
        elt_ = sorted_.get(2);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(POTION_MAX, itemName_);
        assertEq(POTION_MAX, elt_.getName());
        assertEq(POTION_MAX, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(2500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.one(), elt_.getHpRate());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHp());
        elt_ = sorted_.get(3);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(RAPPEL, itemName_);
        assertEq(RAPPEL, elt_.getName());
        assertEq(RAPPEL, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(1500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(elt_.isKo());
        assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate("1/2"),elt_.getHpRate());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(Rate.zero(), elt_.getHp());
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_MEPO, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search14Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(MAX_ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(BAIE_ENIGMA);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(MASTER_BALL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setMinNumber(new LgInt("2"));
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(0, pagination_.getResults().size());
        CustList<SortingHealingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search15Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(BAIE_GOWAV);
        items_.add(PETIT_RAPPEL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setMinRateHp(new Rate("1/8"));
        //pagination_.getCriteria().setSelectedClass(Berry.class);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(4, pagination_.getResults().size());
        String itemName_;
        CustList<SortingHealingItem> sorted_;
        SortingHealingItem elt_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(POTION_MAX, itemName_);
        assertEq(POTION_MAX, elt_.getName());
        assertEq(POTION_MAX, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(2500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getPp());
        assertEq(new Rate("1"), elt_.getHpRate());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(RAPPEL, itemName_);
        assertEq(RAPPEL, elt_.getName());
        assertEq(RAPPEL, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(1500, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getPp());
        assertEq(new Rate("1/2"), elt_.getHpRate());
        elt_ = sorted_.get(2);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_GOWAV, itemName_);
        assertEq(BAIE_GOWAV, elt_.getName());
        assertEq(BAIE_GOWAV, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(), elt_.getPp());
        assertEq(new Rate("1/8"), elt_.getHpRate());
        elt_ = sorted_.get(3);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(PETIT_RAPPEL, itemName_);
        assertEq(PETIT_RAPPEL, elt_.getName());
        assertEq(PETIT_RAPPEL, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(HealingHpStatus.ITEM, elt_.getItemClass());
        assertEq(3, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(!elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(Rate.zero(),elt_.getPp());
        assertEq(new Rate("1/8"), elt_.getHpRate());
//        elt_ = sorted_.get(2);
//        itemName_ = pagination_.getResults().getVal(elt_);
//        assertEq(POTION_MAX, itemName_);
//        assertEq(POTION_MAX, elt_.getName());
//        assertEq(POTION_MAX, elt_.getKeyName());
//        assertEq(LgInt.zero(), elt_.getNumber());
//        assertEq(0, elt_.getNbHealedStatus());
//        assertEq(0, elt_.getNbStatistics());
//        assertEq(HealingHpStatus.class.getName(), elt_.getItemClass());
//        assertEq(2500, elt_.getPrice());
//        assertTrue(!elt_.isHealOneMove());
//        assertTrue(!elt_.isKo());
//        assertTrue(elt_.isRelativeRateHp());
//        assertTrue(!elt_.isRelativeRatePp());
//        assertEq(Rate.one(), elt_.getHp());
//        assertEq(Rate.zero(),elt_.getPp());
//        assertEq(Rate.zero(), elt_.getHpRate());
//        elt_ = sorted_.get(3);
//        itemName_ = pagination_.getResults().getVal(elt_);
//        assertEq(RAPPEL, itemName_);
//        assertEq(RAPPEL, elt_.getName());
//        assertEq(RAPPEL, elt_.getKeyName());
//        assertEq(LgInt.zero(), elt_.getNumber());
//        assertEq(0, elt_.getNbHealedStatus());
//        assertEq(0, elt_.getNbStatistics());
//        assertEq(HealingHpStatus.class.getName(), elt_.getItemClass());
//        assertEq(1500, elt_.getPrice());
//        assertTrue(!elt_.isHealOneMove());
//        assertTrue(elt_.isKo());
//        assertTrue(elt_.isRelativeRateHp());
//        assertTrue(!elt_.isRelativeRatePp());
//        assertEq(new Rate("1/2"),elt_.getHp());
//        assertEq(Rate.zero(),elt_.getPp());
//        assertEq(Rate.zero(), elt_.getHpRate());
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(POTION_MAX, sorted_.get(0).getName());
        assertEq(2500, sorted_.get(0).getPrice());
        assertEq(HealingHpStatus.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search16Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(POTION);
        items_.add(HUILE);
        items_.add(HUILE_MAX);
        items_.add(ELIXIR);
        items_.add(REVEIL);
        items_.add(BAIE_LANSAT);
        items_.add(POTION_MAX);
        items_.add(RAPPEL);
        items_.add(BAIE_GOWAV);
        items_.add(PETIT_RAPPEL);
        Inventory inv_;
        inv_ = new Inventory(data);
        inv_.getItem(BAIE_MEPO);
        PaginationHealingItem pagination_;
        pagination_ = new PaginationHealingItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data, LANGUAGE);
        pagination_.getCriteria().setMaxRateHp(new Rate("3/8"));
        //pagination_.getCriteria().setSelectedClass(Berry.class);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data);
        assertEq(10, pagination_.getResults().size());
        String itemName_;
        CustList<SortingHealingItem> sorted_;
        SortingHealingItem elt_;
        sorted_ = new CustList<SortingHealingItem>(pagination_.getResults().getKeys());
        elt_ = sorted_.get(0);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_ORAN, itemName_);
        assertEq(BAIE_ORAN, elt_.getName());
        assertEq(BAIE_ORAN, elt_.getKeyName());
        assertEq(LgInt.zero(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(!elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(new Rate(10),elt_.getHp());
        assertEq(Rate.zero(), elt_.getPp());
        assertEq(new Rate("0"), elt_.getHpRate());
        elt_ = sorted_.get(1);
        itemName_ = pagination_.getResults().getVal(elt_);
        assertEq(BAIE_MEPO, itemName_);
        assertEq(BAIE_MEPO, elt_.getName());
        assertEq(BAIE_MEPO, elt_.getKeyName());
        assertEq(LgInt.one(), elt_.getNumber());
        assertEq(0, elt_.getNbHealedStatus());
        assertEq(0, elt_.getNbStatistics());
        assertEq(Berry.ITEM, elt_.getItemClass());
        assertEq(200, elt_.getPrice());
        assertTrue(elt_.isHealOneMove());
        assertTrue(!elt_.isKo());
        //assertTrue(elt_.isRelativeRateHp());
        assertTrue(!elt_.isRelativeRatePp());
        assertEq(Rate.zero(),elt_.getHp());
        assertEq(new Rate("10"), elt_.getPp());
        assertEq(new Rate("0"), elt_.getHpRate());

        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }
}
