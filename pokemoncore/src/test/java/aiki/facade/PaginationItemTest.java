package aiki.facade;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.items.Berry;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.ItemForBattle;
import aiki.game.fight.InitializationDataBase;
import aiki.game.player.Inventory;
import aiki.util.SortingItem;
import code.maths.LgInt;
import code.util.EqList;
import code.util.StringList;
import code.util.pagination.SearchingMode;
import code.util.pagination.SelectedBoolean;

@SuppressWarnings("static-method")
public class PaginationItemTest extends InitializationDataBase {

    private static final String BATTLE_ITEM = "battle item";
    private static final String BERRY = "berry";

    @Test
    public void match1Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("P*");
        pagination_.getCriteria().setMinPrice(1);
        assertTrue(!pagination_.match(BAIE_ORAN));
    }

    @Test
    public void match2Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("P*");
        assertTrue(pagination_.match(POKE_BALL));
    }

    @Test
    public void sortable1Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable2Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable3Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCmpPrice().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable4Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpPrice().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable5Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpDescription().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable6Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpNumber().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sort1Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(3);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpDescription().setPriority(2);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(1);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }

    @Test
    public void sort2Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpDescription().setPriority(3);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpName().setPriority(2);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(1);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(METRONOME_OBJ, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(BAIE_ORAN, itemName_);
    }

    @Test
    public void sort3Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpPrice().setPriority(3);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpDescription().setPriority(2);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }

    @Test
    public void sort4Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.one());
        itemName_ = METRONOME_OBJ;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.getCmpNumber().setPriority(1);
        pagination_.getCmpNumber().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }

    @Test
    public void sort5Test() {
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(_data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getResults().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getResults().put(sorting_, itemName_);
        pagination_.sort();
        assertEq(2, pagination_.getResults().size());
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }

    @Test
    public void search1Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        pagination_.getCriteria().setSelectedClass(Berry.ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(1, pagination_.getResults().size());
        String itemName_;
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_MEPO, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_MEPO, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(LgInt.one(), sorted_.get(0).getNumber());
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
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        pagination_.getCriteria().setSelectedClass(Berry.ITEM);
        pagination_.getCriteria().setMinPrice(300);
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(0, pagination_.getResults().size());
        EqList<SortingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search3Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpDescription().setPriority(1);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        pagination_.getCriteria().setSelectedClass(Berry.ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(1, pagination_.getResults().size());
        String itemName_;
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_MEPO, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_MEPO, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(LgInt.one(), sorted_.get(0).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search4Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(HealingItem.ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(3, pagination_.getResults().size());
        String itemName_;
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(HUILE, sorted_.get(0).getName());
        assertEq(500, sorted_.get(0).getPrice());
        assertEq(HealingPp.ITEM, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search5Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(EAU_FRAICHE);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(3);
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(HealingItem.ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(3, pagination_.getResults().size());
        String itemName_;
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(3, sorted_.size());
        assertEq(HUILE, sorted_.get(0).getName());
        assertEq(500, sorted_.get(0).getPrice());
        assertEq(HealingPp.ITEM, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(CENDRESACREE, sorted_.get(1).getName());
        assertEq(20000, sorted_.get(1).getPrice());
        assertEq(HealingItem.ITEM, sorted_.get(1).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(1).getNumber());
        assertEq(EAU_FRAICHE, sorted_.get(2).getName());
        assertEq(200, sorted_.get(2).getPrice());
        assertEq(HealingHp.ITEM, sorted_.get(2).getItemClass());
        assertEq(LgInt.one(), sorted_.get(2).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search6Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(3);
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(6, pagination_.getResults().size());
        String itemName_;
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(BAIE_MEPO, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(MULTI_EXP, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(5));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(3, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(BAIE_MEPO, sorted_.get(1).getName());
        assertEq(200, sorted_.get(1).getPrice());
        assertEq(Berry.ITEM, sorted_.get(1).getItemClass());
        assertEq(LgInt.one(), sorted_.get(1).getNumber());
        assertEq(MULTI_EXP, sorted_.get(2).getName());
        assertEq(1000, sorted_.get(2).getPrice());
        assertEq(ItemForBattle.ITEM, sorted_.get(2).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(2).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search7Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeDescription(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfDescription("*ItemForBattle*");
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(1, pagination_.getResults().size());
        String itemName_;
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(MULTI_EXP, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(MULTI_EXP, sorted_.get(0).getName());
        assertEq(1000, sorted_.get(0).getPrice());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(ItemForBattle.ITEM, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search8Test() {
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(_data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(3);
        pagination_.getCriteria().setMaxNumber(LgInt.zero());
        pagination_.setTranslation(_data_, LANGUAGE);
        pagination_.setInventory(inv_);
        pagination_.search(items_, _data_);
        assertEq(5, pagination_.getResults().size());
        String itemName_;
        EqList<SortingItem> sorted_;
        sorted_ = new EqList<SortingItem>(pagination_.getResults().getKeys());
        itemName_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq(MULTI_EXP, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(2));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(3));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getResults().getVal(sorted_.get(4));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(3, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Berry.ITEM, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(MULTI_EXP, sorted_.get(1).getName());
        assertEq(1000, sorted_.get(1).getPrice());
        assertEq(ItemForBattle.ITEM, sorted_.get(1).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(1).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }
}
