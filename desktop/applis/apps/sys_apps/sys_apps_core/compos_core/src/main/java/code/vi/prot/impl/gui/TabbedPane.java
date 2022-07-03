package code.vi.prot.impl.gui;

import code.gui.AbsCustComponent;
import code.gui.AbsTabbedPane;
import code.gui.FrameUtil;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public final class TabbedPane extends CustComponent implements AbsTabbedPane {

    private final JTabbedPane component = new JTabbedPane();

    public int getComponentCount() {
        return getChildren().size();
    }

    public int getSelectedIndex() {
        return component.getSelectedIndex();
    }

    public void setSelectedIndex(int _index) {
        FrameUtil.selectedIndex(_index, this);
    }

    public void selectIndex(int _index) {
        component.setSelectedIndex(_index);
    }

    public void add(String _title, AbsCustComponent _component) {
        FrameUtil.added(_title, _component,this);
    }

    public void addIntTab(String _title, AbsCustComponent _component) {
        _component.setParent(this);
        getChildren().add(_component);
        component.add(_title, ((CustComponent) _component).getNatComponent());
    }

    public boolean setTab(int _index,AbsCustComponent _component) {
        try {
            return FrameUtil.setTab(_index, _component,this);
        } catch (Exception e) {
            return false;
        }
    }

    public void setTabComponentAt(int _index, AbsCustComponent _component) {
        getChildren().get(_index).setParent(null);
        _component.setParent(this);
        getChildren().set(_index, _component);
        component.setTabComponentAt(_index,((CustComponent) _component).getNatComponent());
    }

    public String getTitle(int _index) {
        try {
            return getTitleAt(_index);
        } catch (Exception e) {
            return "";
        }
    }

    public String getTitleAt(int _index) {
        return component.getTitleAt(_index);
    }

    public boolean setTitle(int _index,String _title) {
        try {
            setTitleAt(_index, _title);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setTitleAt(int _index, String _title) {
        component.setTitleAt(_index, _title);
    }

    public void remove(int _index) {
        getChildren().get(_index).setParent(null);
        getChildren().remove(_index);
        component.removeTabAt(_index);
    }

    @Override
    public JComponent getNatComponent() {
        return component;
    }

    public void removeAll() {
        FrameUtil.remAllFromPanel(getChildren());
        innerRemoveAll();
    }

    public void innerRemoveAll() {
        getChildren().clear();
        component.removeAll();
    }

}
