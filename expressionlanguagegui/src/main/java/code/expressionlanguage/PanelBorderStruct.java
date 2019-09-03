package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.Panel;
import code.util.StringList;

import java.awt.BorderLayout;

public final class PanelBorderStruct extends PanelStruct {
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
        if (StringList.quickEq(ct_,"4")) {
            ok_ = true;
            value_ = BorderLayout.CENTER;
        } else if (StringList.quickEq(ct_,"2")) {
            ok_ = true;
            value_ = BorderLayout.NORTH;
        } else if (StringList.quickEq(ct_,"6")) {
            ok_ = true;
            value_ = BorderLayout.SOUTH;
        } else if (StringList.quickEq(ct_,"3")) {
            ok_ = true;
            value_ = BorderLayout.WEST;
        } else if (StringList.quickEq(ct_,"5")) {
            ok_ = true;
            value_ = BorderLayout.EAST;
        } else if (StringList.quickEq(ct_,"0")) {
            ok_ = true;
            value_ = BorderLayout.BEFORE_FIRST_LINE;
        } else if (StringList.quickEq(ct_,"8")) {
            ok_ = true;
            value_ = BorderLayout.AFTER_LAST_LINE;
        } else if (StringList.quickEq(ct_,"1")) {
            ok_ = true;
            value_ = BorderLayout.BEFORE_LINE_BEGINS;
        } else if (StringList.quickEq(ct_,"7")) {
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
