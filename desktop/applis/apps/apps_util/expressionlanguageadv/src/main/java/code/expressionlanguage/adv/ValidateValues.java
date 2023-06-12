package code.expressionlanguage.adv;

import code.expressionlanguage.structs.StringStruct;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class ValidateValues implements AbsActionListener {
    private final CustList<StringStruct> output;
    private final CustList<EditValueRow> comments;

    public ValidateValues(CustList<StringStruct> _w, CustList<EditValueRow> _com) {
        this.output = _w;
        this.comments = _com;
    }

    @Override
    public void action() {
        output.clear();
        int len_ = comments.size();
        for (int i = 0; i < len_; i++) {
            EditValueRow e_ = comments.get(i);
            e_.updateComment();
            output.add(new StringStruct(e_.getValue()));
        }
    }
}
