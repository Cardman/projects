package code.mock;

import code.gui.AbsContainer;
import code.gui.AbsCustComponent;
import code.gui.AbsScrollPane;
import code.gui.images.MetaDimension;
import code.gui.images.MetaRect;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class MockScrollPane extends MockCustComponent implements AbsScrollPane, AbsContainer {
    private AbsCustComponent child;
    private int horizontalValue;
    private int verticalValue;
    private boolean leafCompo;
    public MockScrollPane() {
        setViewportView(null);
    }

    public MockScrollPane(AbsCustComponent _ch) {
        this(false,_ch);
    }

    public MockScrollPane(boolean _leaf,AbsCustComponent _ch) {
        leafCompo = _leaf;
        setViewportView(_ch);
    }

    public boolean isLeafCompo() {
        return leafCompo;
    }

    @Override
    public void setViewportView(AbsCustComponent _ch) {
        if (_ch != null) {
            if (!getChildren().isEmpty()) {
                ((MockCustComponent)getChildren().first()).setParent(null);
                getChildren().clear();
            }
            ((MockCustComponent)_ch).setParent(this);
            getChildren().add(_ch);
            child = _ch;
        } else {
            CustList<AbsCustComponent> children_ = getChildren();
            for (AbsCustComponent c: children_) {
                ((MockCustComponent)c).setParent(null);
            }
            children_.clear();
            child = null;
        }
    }

    @Override
    public void setNullViewportView() {
        setViewportView(null);
    }

    @Override
    public int getHorizontalValue() {
        return horizontalValue;
    }

    @Override
    public void setHorizontalValue(int _i) {
        horizontalValue = _i;
    }

    @Override
    public int getVerticalValue() {
        return verticalValue;
    }

    @Override
    public void setVerticalValue(int _i) {
        verticalValue = _i;
    }

    @Override
    public void recalculateViewport() {
        if (child != null) {
            child.setSize(new MetaDimension(getWidth(), getHeight()));
        }
    }

    @Override
    public MetaRect viewRect() {
        MetaDimension dim_ = getPreferredSizeValue();
        return new MetaRect(0,0, NumberUtil.max(1, dim_.getWidth()), NumberUtil.max(1, dim_.getHeight()));
    }

    public AbsCustComponent getChild() {
        return child;
    }
}
