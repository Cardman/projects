package cards.gui;

import cards.gui.dialogs.help.ComparatorListSizeElement;
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.HelpIndexes;
import cards.gui.dialogs.help.HelpIndexesTree;
import code.sml.Document;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class HelpIndexesTest extends EquallableCardsGuiUtil {
    @Test
    public void eq1() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes(one_);
        assertTrue(one_.eq(two_));
    }
    @Test
    public void eq2() {
        HelpIndexes one_ = new HelpIndexes();
        HelpIndexes two_ = new HelpIndexes();
        two_.add(0);
        assertFalse(one_.eq(two_));
    }
    @Test
    public void eq3() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        two_.add(2);
        assertFalse(one_.eq(two_));
    }
    @Test
    public void map1() {
        HelpIndexes one_ = new HelpIndexes();
        HelpIndexes two_ = new HelpIndexes();
        HelpIndexesTree map_ = new HelpIndexesTree();
        map_.put(one_, eh());
        map_.put(two_, eh());
        assertEq(1,map_.size());
    }
    @Test
    public void map2() {
        HelpIndexes one_ = new HelpIndexes();
        HelpIndexes two_ = new HelpIndexes();
        two_.add(1);
        HelpIndexesTree map_ = new HelpIndexesTree();
        map_.put(one_, eh());
        map_.put(two_, eh());
        assertEq(2,map_.size());
    }

    @Test
    public void sort1() {
        HelpIndexes one_ = new HelpIndexes();
        HelpIndexes two_ = new HelpIndexes();
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(one_);
        ls_.add(two_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
    }
    @Test
    public void sort2() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(one_);
        ls_.add(two_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
        assertEq(0,ls_.get(0).size());
        assertEq(1,ls_.get(1).size());
        assertEq(1,ls_.get(1).get(0));
    }
    @Test
    public void sort3() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(two_);
        ls_.add(one_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
        assertEq(0,ls_.get(0).size());
        assertEq(1,ls_.get(1).size());
        assertEq(1,ls_.get(1).get(0));
    }
    @Test
    public void sort4() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        two_.add(2);
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(one_);
        ls_.add(two_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
        assertEq(1,ls_.get(0).size());
        assertEq(1,ls_.get(0).get(0));
        assertEq(1,ls_.get(1).size());
        assertEq(2,ls_.get(1).get(0));
    }
    @Test
    public void sort5() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        two_.add(2);
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(two_);
        ls_.add(one_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
        assertEq(1,ls_.get(0).size());
        assertEq(1,ls_.get(0).get(0));
        assertEq(1,ls_.get(1).size());
        assertEq(2,ls_.get(1).get(0));
    }
    @Test
    public void sort6() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        two_.add(1);
        two_.add(2);
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(one_);
        ls_.add(two_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
        assertEq(2,ls_.get(0).size());
        assertEq(1,ls_.get(0).get(0));
        assertEq(1,ls_.get(0).get(1));
        assertEq(2,ls_.get(1).size());
        assertEq(1,ls_.get(1).get(0));
        assertEq(2,ls_.get(1).get(1));
    }
    @Test
    public void sort7() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        two_.add(1);
        two_.add(2);
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(two_);
        ls_.add(one_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
        assertEq(2,ls_.get(0).size());
        assertEq(1,ls_.get(0).get(0));
        assertEq(1,ls_.get(0).get(1));
        assertEq(2,ls_.get(1).size());
        assertEq(1,ls_.get(1).get(0));
        assertEq(2,ls_.get(1).get(1));
    }
    @Test
    public void sort8() {
        HelpIndexes one_ = new HelpIndexes();
        one_.add(1);
        HelpIndexes two_ = new HelpIndexes();
        two_.add(1);
        CustList<HelpIndexes> ls_ = new CustList<HelpIndexes>();
        ls_.add(one_);
        ls_.add(two_);
        ls_.sortElts(new ComparatorListSizeElement());
        assertEq(2,ls_.size());
    }

    static ElementHelp eh() {
        return new ElementHelp("", "", new StringMap<StringMap<String>>(), new StringMap<Document>());
    }

}
