package aiki.facade;

import aiki.db.DataBase;
import aiki.fight.items.*;
import code.util.CustList;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.game.player.Inventory;
import aiki.util.SortingItem;
import code.maths.LgInt;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;


public class PaginationItemTest extends InitializationDataBase {

    private static final String BATTLE_ITEM = "battle item";
    private static final String BERRY = "berry";

    @Test
    public void match1Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("P*");
        pagination_.getCriteria().setMinPrice(1L);
        assertTrue(!pagination_.match(BAIE_ORAN));
    }

    @Test
    public void match2Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("P*");
        assertTrue(pagination_.match(POKE_BALL));
    }

    @Test
    public void sortable1Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable2Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable3Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCmpPrice().setPriority(1);
        assertTrue(pagination_.sortable());
    }

    @Test
    public void sortable4Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpPrice().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable5Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpDescription().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sortable6Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpNumber().setPriority(1);
        assertTrue(!pagination_.sortable());
    }

    @Test
    public void sort1Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getItems().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getItems().put(sorting_, itemName_);
        pagination_.getCmpName().setPriority(3);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpDescription().setPriority(2);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(1);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getItems().size());
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }

    @Test
    public void sort2Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getItems().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getItems().put(sorting_, itemName_);
        pagination_.getCmpDescription().setPriority(3);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpName().setPriority(2);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpPrice().setPriority(1);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getItems().size());
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(METRONOME_OBJ, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(BAIE_ORAN, itemName_);
    }

    @Test
    public void sort3Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getItems().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getItems().put(sorting_, itemName_);
        pagination_.getCmpPrice().setPriority(3);
        pagination_.getCmpPrice().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpDescription().setPriority(2);
        pagination_.getCmpDescription().setIncreasing(SelectedBoolean.YES);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpName().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getItems().size());
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }

    @Test
    public void sort4Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getItems().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.one());
        itemName_ = METRONOME_OBJ;
        pagination_.getItems().put(sorting_, itemName_);
        pagination_.getCmpNumber().setPriority(1);
        pagination_.getCmpNumber().setIncreasing(SelectedBoolean.YES);
        pagination_.sort();
        assertEq(2, pagination_.getItems().size());
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }


    @Test
    public void sort4_Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getItems().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.one());
        itemName_ = METRONOME_OBJ;
        pagination_.getItems().put(sorting_, itemName_);
        pagination_.getCmpNumber().setPriority(1);
        pagination_.getCmpNumber().setIncreasing(SelectedBoolean.NO);
        pagination_.sort();
        assertEq(2, pagination_.getItems().size());
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(METRONOME_OBJ, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(BAIE_ORAN, itemName_);
    }

    @Test
    public void sort5Test() {
        DataBase data_ = initDb();
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setTranslation(data_, LANGUAGE);
        SortingItem sorting_;
        String itemName_;
        sorting_ = new SortingItem();
        sorting_.setName(BAIE_ORAN);
        sorting_.setItemClass(BERRY);
        sorting_.setPrice(25);
        sorting_.setIndex(0);
        sorting_.setNumber(LgInt.zero());
        itemName_ = BAIE_ORAN;
        pagination_.getItems().put(sorting_, itemName_);
        sorting_ = new SortingItem();
        sorting_.setName(METRONOME_OBJ);
        sorting_.setItemClass(BATTLE_ITEM);
        sorting_.setPrice(50);
        sorting_.setIndex(1);
        sorting_.setNumber(LgInt.zero());
        itemName_ = METRONOME_OBJ;
        pagination_.getItems().put(sorting_, itemName_);
        pagination_.sort();
        assertEq(2, pagination_.getItems().size());
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(METRONOME_OBJ, itemName_);
    }

    @Test
    public void search1Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        pagination_.getCriteria().setSelectedClass(Item.BERRY);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertTrue(!pagination_.hasNoRendered());
        assertEq(1, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_MEPO, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_MEPO, sorted_.get(0).getName());
        assertEq(BAIE_MEPO, sorted_.get(0).getKeyName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(LgInt.one(), sorted_.get(0).getNumber());
        assertEq(Item.BERRY, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
        assertEq(-1, pagination_.currentIndex());
        assertEq("", pagination_.currentObject());
        pagination_.checkLine(0);
        assertEq(1, pagination_.currentIndex());
        assertEq(BAIE_MEPO, pagination_.currentObject());
    }

    @Test
    public void search2Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        pagination_.getCriteria().setSelectedClass(Item.BERRY);
        pagination_.getCriteria().setMinPrice(300L);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertEq(0, pagination_.getItems().size());
        CustList<SortingItem> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search3Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCmpName().setPriority(1);
        pagination_.getCmpDescription().setPriority(1);
        pagination_.getCriteria().setSearchModeName(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfName("*P*");
        pagination_.getCriteria().setSelectedClass(Item.BERRY);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertEq(1, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_MEPO, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_MEPO, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Item.BERRY, sorted_.get(0).getItemClass());
        assertEq(LgInt.one(), sorted_.get(0).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search4Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(Item.HEALING_ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertEq(3, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(2));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(HUILE, sorted_.get(0).getName());
        assertEq(500, sorted_.get(0).getPrice());
        assertEq(Item.HEALING_PP, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search5Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(EAU_FRAICHE);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(3);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCriteria().setSelectedClass(Item.HEALING_ITEM);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertEq(3, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(2));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(3, sorted_.size());
        assertEq(HUILE, sorted_.get(0).getName());
        assertEq(500, sorted_.get(0).getPrice());
        assertEq(Item.HEALING_PP, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(CENDRESACREE, sorted_.get(1).getName());
        assertEq(20000, sorted_.get(1).getPrice());
        assertEq(Item.HEALING_ITEM, sorted_.get(1).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(1).getNumber());
        assertEq(EAU_FRAICHE, sorted_.get(2).getName());
        assertEq(200, sorted_.get(2).getPrice());
        assertEq(Item.HEALING_HP, sorted_.get(2).getItemClass());
        assertEq(LgInt.one(), sorted_.get(2).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search6Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(3);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertEq(6, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(BAIE_MEPO, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(2));
        assertEq(MULTI_EXP, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(3));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(4));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(5));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(3, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Item.BERRY, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(BAIE_MEPO, sorted_.get(1).getName());
        assertEq(200, sorted_.get(1).getPrice());
        assertEq(Item.BERRY, sorted_.get(1).getItemClass());
        assertEq(LgInt.one(), sorted_.get(1).getNumber());
        assertEq(MULTI_EXP, sorted_.get(2).getName());
        assertEq(1000, sorted_.get(2).getPrice());
        assertEq(Item.ITEM_FOR_BATTLE, sorted_.get(2).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(2).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search7Test() {
        DataBase data_ = initDb();
        String it_ = "ItemForBattle";
        data_.getTranslatedClassesDescriptions().getVal(LANGUAGE).put(Item.ITEM_FOR_BATTLE, it_);
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.getCriteria().setSearchModeDescription(SearchingMode.META_CHARACTER);
        pagination_.getCriteria().setContentOfDescription("*ItemForBattle*");
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertEq(1, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(MULTI_EXP, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(MULTI_EXP, sorted_.get(0).getName());
        assertEq(1000, sorted_.get(0).getPrice());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(it_, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void search8Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        items_.add(HUILE);
        items_.add(CENDRESACREE);
        items_.add(EAU_FRAICHE);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(3);
        pagination_.getCriteria().setMaxNumber(LgInt.zero());
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        assertEq(5, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(1));
        assertEq(MULTI_EXP, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(2));
        assertEq(HUILE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(3));
        assertEq(CENDRESACREE, itemName_);
        itemName_ = pagination_.getItems().getVal(sorted_.get(4));
        assertEq(EAU_FRAICHE, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(3, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(Item.BERRY, sorted_.get(0).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(MULTI_EXP, sorted_.get(1).getName());
        assertEq(1000, sorted_.get(1).getPrice());
        assertEq(Item.ITEM_FOR_BATTLE, sorted_.get(1).getItemClass());
        assertEq(LgInt.zero(), sorted_.get(1).getNumber());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void newSearch1Test() {
        DataBase data_ = initDb();
        StringList items_;
        items_ = new StringList();
        items_.add(BAIE_ORAN);
        items_.add(BAIE_MEPO);
        items_.add(MULTI_EXP);
        Inventory inv_;
        inv_ = new Inventory(data_);
        inv_.getItem(BAIE_MEPO);
        PaginationItem pagination_;
        pagination_ = new PaginationItem();
        pagination_.setNbResultsPerPage(1);
        pagination_.setTranslation(data_, LANGUAGE);
        pagination_.setInventory(inv_);
        pagination_.search(items_, data_);
        pagination_.getCriteria().setSearchModeName(SearchingMode.SUBSTRING);
        pagination_.getCriteria().setContentOfName("BAIE");
        pagination_.newSearch();
        assertEq(2, pagination_.getItems().size());
        String itemName_;
        CustList<SortingItem> sorted_;
        sorted_ = new CustList<SortingItem>(pagination_.getItems().getKeys());
        itemName_ = pagination_.getItems().getVal(sorted_.get(0));
        assertEq(BAIE_ORAN, itemName_);
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq(BAIE_ORAN, sorted_.get(0).getName());
        assertEq(BAIE_ORAN, sorted_.get(0).getKeyName());
        assertEq(200, sorted_.get(0).getPrice());
        assertEq(LgInt.zero(), sorted_.get(0).getNumber());
        assertEq(Item.BERRY, sorted_.get(0).getItemClass());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

}
