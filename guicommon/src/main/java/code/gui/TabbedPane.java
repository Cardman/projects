package code.gui;

import code.util.CustList;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public final class TabbedPane extends CustComponent {

    private JTabbedPane component = new JTabbedPane();

    public int getComponentCount() {
        return getChildren().size();
    }

    public void add(String _title, CustComponent _component) {
        if (_component.getParent() != null) {
            return;
        }
        _component.setParent(this);
        getChildren().add(_component);
        component.add(_title, _component.getComponent());
    }

    public void setTab(int _index,CustComponent _component) {
        if (!getChildren().isValidIndex(_index)) {
            return;
        }
        if (_component.getParent() != null) {
            return;
        }
        getChildren().get(_index).setParent(null);
        _component.setParent(this);
        getChildren().set(_index,_component);
        component.setTabComponentAt(_index, _component.getComponent());
    }
    public String getTitle(int _index) {
        if (!getChildren().isValidIndex(_index)) {
            return "";
        }
        return component.getTitleAt(_index);
    }
    public void setTitle(int _index,String _title) {
        if (!getChildren().isValidIndex(_index)) {
            return;
        }
        component.setTitleAt(_index,_title);
    }

    public void remove(int _index) {
        getChildren().get(_index).setParent(null);
        getChildren().remove(_index);
        component.removeTabAt(_index);
    }

    public int index(CustComponent _cust) {
        int i_ = 0;
        int index_ = -1;
        for (CustComponent c: getChildren()) {
            if (c.getComponent() == _cust.getComponent()) {
                index_ = i_;
                break;
            }
            i_++;
        }
        return index_;
    }
    public int remove(CustComponent _cust) {
        int i_ = 0;
        int index_ = -1;
        CustList<CustComponent> rem_ = new CustList<CustComponent>();
        CustList<String> remTitles_ = new CustList<String>();
        for (CustComponent c: getChildren()) {
            if (c.getComponent() == _cust.getComponent()) {
                c.setParent(null);
                _cust.setParent(null);
                index_ = i_;
            } else {
                remTitles_.add(component.getTitleAt(i_));
                rem_.add(c);
            }
            i_++;
        }
        getChildren().clear();
        component.removeAll();
        i_ = 0;
        for (CustComponent c: rem_) {
            getChildren().add(c);
            component.addTab(remTitles_.get(i_),c.getComponent());
            i_++;
        }
        return index_;
    }
    public CustComponent getComponent(int _index) {
        return getChildren().get(_index);
    }

    @Override
    protected JComponent getComponent() {
        return component;
    }

    public void removeAll() {
        for (CustComponent c: getChildren()) {
            c.setParent(null);
        }
        getChildren().clear();
        component.removeAll();
    }

}
