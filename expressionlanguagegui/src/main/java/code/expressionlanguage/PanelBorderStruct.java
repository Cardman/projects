package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.Panel;
import code.util.StringList;

import java.awt.BorderLayout;

public final class PanelBorderStruct extends PanelStruct {

    public static final String CENTER = "4";
    public static final String NORTH = "2";
    public static final String SOUTH = "6";
    public static final String WEST = "3";
    public static final String EAST = "5";
    public static final String BEFORE_FIRST_LINE = "0";
    public static final String AFTER_LAST_LINE = "8";
    public static final String BEFORE_LINE_BEGINS = "1";
    public static final String AFTER_LINE_ENDS = "7";

    private PanelBorderStruct(String _className) {
        super(_className,Panel.newBorder());
    }

    public static PanelStruct newBorder(String _className) {
        return new PanelBorderStruct(_className);
    }

    public void add(CustComponentStruct _comp, Struct _constraint) {
        if (_comp.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        if (!(_constraint instanceof StringStruct)) {
            _comp.setParentComponent(this);
            getChildren().add(_comp);
            getPanel().add(_comp.getComponent(), BorderLayout.CENTER);
            return;
        }
        StringStruct c_ = (StringStruct)_constraint;
        String ct_ = c_.getInstance();
        String value_ = "";
        boolean ok_ = false;
        if (StringList.quickEq(ct_, CENTER)) {
            ok_ = true;
            value_ = BorderLayout.CENTER;
        } else if (StringList.quickEq(ct_, NORTH)) {
            ok_ = true;
            value_ = BorderLayout.NORTH;
        } else if (StringList.quickEq(ct_, SOUTH)) {
            ok_ = true;
            value_ = BorderLayout.SOUTH;
        } else if (StringList.quickEq(ct_, WEST)) {
            ok_ = true;
            value_ = BorderLayout.WEST;
        } else if (StringList.quickEq(ct_, EAST)) {
            ok_ = true;
            value_ = BorderLayout.EAST;
        } else if (StringList.quickEq(ct_, BEFORE_FIRST_LINE)) {
            ok_ = true;
            value_ = BorderLayout.BEFORE_FIRST_LINE;
        } else if (StringList.quickEq(ct_, AFTER_LAST_LINE)) {
            ok_ = true;
            value_ = BorderLayout.AFTER_LAST_LINE;
        } else if (StringList.quickEq(ct_, BEFORE_LINE_BEGINS)) {
            ok_ = true;
            value_ = BorderLayout.BEFORE_LINE_BEGINS;
        } else if (StringList.quickEq(ct_, AFTER_LINE_ENDS)) {
            ok_ = true;
            value_ = BorderLayout.AFTER_LINE_ENDS;
        }
        if (!ok_) {
            return;
        }
        _comp.setParentComponent(this);
        getChildren().add(_comp);
        getPanel().add(_comp.getComponent(), value_);
    }
}
