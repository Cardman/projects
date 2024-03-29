package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsSplitPane;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;

public final class SplitPaneStruct extends CustComponentStruct {
    private final AbsSplitPane splitPane;
    private Struct left;
    private Struct right;
    public SplitPaneStruct(String _className, Struct _orient, Struct _left, Struct _right, AbsCompoFactory _compoFactory) {
        super(_className);
        if (GuiConstants.toSplitOrientation(((NumberStruct)_orient).intStruct()) == GuiConstants.HORIZONTAL_SPLIT) {
            splitPane = _compoFactory.newHorizontalSplitPane(((CustComponentStruct)_left).getComponent(),((CustComponentStruct)_right).getComponent());
        } else {
            splitPane = _compoFactory.newVerticalSplitPane(((CustComponentStruct)_left).getComponent(),((CustComponentStruct)_right).getComponent());
        }
        left = _left;
        right = _right;
        ((CustComponentStruct)_left).setParentComponent(this);
        ((CustComponentStruct)_right).setParentComponent(this);
        getChildren().add((CustComponentStruct)_left);
        getChildren().add((CustComponentStruct)_right);
    }

    public Struct getLeftComponent() {
        return left;
    }

    public void setLeftComponent(Struct _scroll) {
        if (!(_scroll instanceof CustComponentStruct)) {
            return;
        }
        CustComponentStruct c_ = (CustComponentStruct) _scroll;
        if (c_.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        left = _scroll;
        getChildren().first().setNullParentComponent();
        c_.setParentComponent(this);
        splitPane.setLeftComponent(c_.getComponent());
        getChildren().set(0, c_);
    }

    public Struct getRightComponent() {
        return right;
    }
    public void setRightComponent(Struct _scroll) {
        if (!(_scroll instanceof CustComponentStruct)) {
            return;
        }
        CustComponentStruct c_ = (CustComponentStruct) _scroll;
        if (c_.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        right = _scroll;
        getChildren().last().setNullParentComponent();
        c_.setParentComponent(this);
        splitPane.setRightComponent(c_.getComponent());
        getChildren().set(1, c_);
    }

    public BooleanStruct isContinuousLayout() {
        return BooleanStruct.of(splitPane.isContinuousLayout());
    }

    public void setContinuousLayout(Struct _b) {
        splitPane.setContinuousLayout(BooleanStruct.isTrue(_b));
    }

    public BooleanStruct isOneTouchExpandable() {
        return BooleanStruct.of(splitPane.isOneTouchExpandable());
    }

    public void setOneTouchExpandable(Struct _b) {
        splitPane.setOneTouchExpandable(BooleanStruct.isTrue(_b));
    }

    public IntStruct getDividerLocation() {
        return new IntStruct(splitPane.getDividerLocation());
    }
    public void setDividerLocation(Struct _i) {
        splitPane.setDividerLocation(((NumberStruct)_i).intStruct());
    }

    public IntStruct getDividerSize() {
        return new IntStruct(splitPane.getDividerSize());
    }
    public void setDividerSize(Struct _i) {
        splitPane.setDividerSize(((NumberStruct)_i).intStruct());
    }

    public void revalidate() {
        splitPane.revalidate();
    }

    @Override
    protected AbsCustComponent getComponent() {
        return splitPane;
    }
}
