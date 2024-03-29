package code.gui;

import code.mock.*;
import code.util.*;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class AbsComboBoxTest extends EquallableGuiFctUtil {
    @Test
    public void c1() {
        NumComboBox n_ = new NumComboBox(init(), -1,0,1,2);
        n_.setSelectedItem(-2);
        assertEq(0, n_.getSelectedIndex());
    }
    @Test
    public void c2() {
        NumComboBox n_ = new NumComboBox(init(), -1,0,1,2);
        n_.setSelectedItem(1);
        assertEq(2, n_.getSelectedIndex());
    }
    @Test
    public void c3() {
        NumComboBox n_ = new NumComboBox(init());
        n_.addItem(2);
        assertEq(1,n_.getItemCount());
    }
    @Test
    public void c4() {
        IntTreeComboBox i_ = new IntTreeComboBox(getCombo());
        IntMap<String> map_ = new IntMap<String>();
        map_.addEntry(-2,"");
        map_.addEntry(-1,"-1");
        map_.addEntry(0,"0");
        map_.addEntry(1,"1");
        i_.refresh(map_);
        assertEq(4,i_.getElements().size());
        i_.selectItem(2);
        assertEq("0",i_.getSelectedItem());
    }
    @Test
    public void c5() {
        IntTreeComboBox i_ = new IntTreeComboBox(getCombo());
        i_.setItems(NumberUtil.wrapIntArray(0,1,2,3));
        assertEq(4,i_.getElements().size());
        i_.selectItem(2);
        assertEq("2",i_.getSelectedItem());
    }
    @Test
    public void c6() {
        IntTreeComboBox i_ = new IntTreeComboBox(getCombo());
        IntMap<String> map_ = new IntMap<String>();
        map_.addEntry(-2,"");
        map_.addEntry(-1,"-1");
        map_.addEntry(0,"0");
        map_.addEntry(1,"1");
        i_.refresh(map_);
        i_.removeItem(1);
        assertEq(3,i_.getElements().size());
        i_.selectItem(1);
        assertEq("0",i_.getSelectedItem());
    }
    @Test
    public void c7() {
        ComboBox<Ints> b_ = new ComboBox<Ints>(getCombo());
        b_.getCurrent();
        IdMap<Ints,String> m_ = new IdMap<Ints, String>();
        Ints one_ = Ints.newList();
        Ints two_ = Ints.newList();
        m_.addEntry(one_,"1");
        m_.addEntry(two_,"2");
        b_.refresh(m_);
        assertFalse(b_.getElements().isEmpty());
    }
    @Test
    public void c8() {
        ComboBox<Ints> b_ = new ComboBox<Ints>(getCombo());
        b_.getCurrent();
        IdMap<Ints,String> m_ = new IdMap<Ints, String>();
        Ints one_ = Ints.newList();
        Ints two_ = Ints.newList();
        m_.addEntry(one_,"1");
        m_.addEntry(two_,"2");
        b_.refresh(m_);
        assertEq(2,b_.getElements().size());
        b_.setSelectedItem(Ints.newList());
        assertEq(0,b_.getSelectedIndex());
        b_.setSelectedItem(one_);
        assertEq(0,b_.getSelectedIndex());
        b_.setSelectedItem(two_);
        assertEq(1,b_.getSelectedIndex());
        assertEq(0,b_.getCurrent().size());
    }
    @Test
    public void c9() {
        ComboBox<Ints> b_ = new ComboBox<Ints>(getCombo());
        b_.getCurrent();
        IdMap<Ints,String> m_ = new IdMap<Ints, String>();
        Ints one_ = Ints.newList();
        Ints two_ = Ints.newList();
        Ints three_ = Ints.newList();
        m_.addEntry(one_,"1");
        m_.addEntry(two_,"2");
        m_.addEntry(three_,"3");
        b_.refresh(m_);
        b_.removeItem(1);
        assertEq(2,b_.getElements().size());
        b_.setSelectedItem(three_);
        assertEq(1,b_.getSelectedIndex());
    }
    @Test
    public void c10() {
        ComboBox<Ints> b_ = new ComboBox<Ints>(getCombo());
        b_.getCurrent();
        IdMap<Ints,String> m_ = new IdMap<Ints, String>();
        Ints one_ = Ints.newList();
        Ints two_ = Ints.newList();
        Ints three_ = Ints.newList();
        m_.addEntry(one_,"1");
        m_.addEntry(two_,"2");
        m_.addEntry(three_,"3");
        b_.refresh(m_);
        b_.removeItem(-1);
        assertEq(3,b_.getElements().size());
    }
    @Test
    public void c11() {
        ComboBox<Ints> b_ = new ComboBox<Ints>(getCombo());
        b_.getCurrent();
        IdMap<Ints,String> m_ = new IdMap<Ints, String>();
        Ints one_ = Ints.newList();
        Ints two_ = Ints.newList();
        m_.addEntry(one_,"1");
        m_.addEntry(two_,"2");
        CustList<Ints> ls_ = new CustList<Ints>();
        ls_.add(one_);
        ls_.add(two_);
        b_.refresh(ls_,m_);
        assertEq(2,b_.getElements().size());
        b_.setSelectedItem(Ints.newList());
        assertEq(0,b_.getSelectedIndex());
        b_.setSelectedItem(one_);
        assertEq(0,b_.getSelectedIndex());
        b_.setSelectedItem(two_);
        assertEq(1,b_.getSelectedIndex());
        assertEq(0,b_.getCurrent().size());
    }
    @Test
    public void c12() {
        ComboBox<Ints> b_ = new ComboBox<Ints>(getCombo());
        Ints one_ = Ints.newList();
        Ints two_ = Ints.newList();
        CustList<Ints> ls_ = new CustList<Ints>();
        ls_.add(one_);
        ls_.add(two_);
        b_.refresh(ls_,new IdMap<Ints, String>());
        b_.addItem(one_,"1");
        b_.addItem(two_,"2");
        assertEq(2,b_.getElements().size());
        b_.self();
        MockListSampleSelection list_ = new MockListSampleSelection();
        b_.setListener(list_);
        list_.valueChanged(new SelectionInfo(0,0, true));
        list_.valueChanged(new SelectionInfo(0,0, false));
    }
    @Test
    public void c13() {
        NumComboBox n_ = new NumComboBox(init());
        n_.addItem(2);
        n_.addItem(3);
        n_.selectItem(1);
        assertEq(3,n_.getCurrent());
        n_.removeAllItems();
        assertEq(0,n_.getItemCount());
    }
    @Test
    public void c14() {
        NumComboBox n_ = new NumComboBox(init());
        n_.setItems(3);
        assertEq(3,n_.getItemCount());
        n_.selectItem(2);
        assertEq(2, n_.getSelectedIndex());
    }

    private ScrollCustomCombo getCombo() {
        return GuiBaseUtil.combo(new MockImageFactory(), new StringList(), -1, new MockCompoFactory());
    }
}
