package code.gui;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.util.CustList;

public class GraphicList {

    private CustList<Object> list;
    private CustList<Component> listComponents = new CustList<Component>();

    private CustCellRender render;

    private ListSelection listener;

    private JPanel panel;

    private int firstIndex = -1;

    private int lastIndex = -1;

    private boolean simple;

    public GraphicList(boolean _simple, Object... _objects) {
        list = new CustList<Object>(_objects);
        simple = _simple;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        buildList();
    }

    protected void buildList() {
        rebuild();
    }

    public void rebuild() {
        CustCellRender r_ = getRender();
        if (r_ == null) {
            return;
        }
        JPanel panel_ = getPanel();
        panel_.removeAll();
        int index_ = 0;
        for (Object o: getList()) {
            JLabel lab_ = new JLabel();
            getListComponents().add(lab_);
            panel_.add(lab_);
            Component c_ = r_.getListCellRendererComponent(this, o, index_, false, false);
            r_.paintComponent(c_);
            index_++;
        }
        index_ = 0;
        if (simple) {
            for (Component c: listComponents) {
                c.addMouseListener(new SimpleSelectEltList(this, index_));
                index_++;
            }
        } else {
            for (Component c: listComponents) {
                c.addKeyListener(new MultSelectKeyEltList(this, index_));
                c.addMouseListener(new MultSelectEltList(this, index_));
                index_++;
            }
        }
    }
    public ListSelection getListener() {
        return listener;
    }

    public void setListener(ListSelection _listener) {
        listener = _listener;
    }

    public CustCellRender getRender() {
        return render;
    }
    public void setRender(CustCellRender _render) {
        render = _render;
    }
    public CustList<Object> getList() {
        return list;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int _firstIndex) {
        firstIndex = _firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int _lastIndex) {
        lastIndex = _lastIndex;
    }

    public JPanel getPanel() {
        return panel;
    }

    public CustList<Component> getListComponents() {
        return listComponents;
    }
}
