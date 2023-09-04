package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsTxtComponent;
import code.gui.events.AfterValidateText;
import code.util.CustList;
import code.util.StringList;

public final class StrictTypeFromFilter implements AfterValidateText {
    private final ResultContext result;

    public StrictTypeFromFilter(ResultContext _r) {
        this.result = _r;
    }

    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        _compo.setText(_typed);
        _compo.setCaretPosition(_typed.length());
    }

    @Override
    public StringList filter(String _look, CustList<String> _dict) {
        ResultContext r_ = getResult();
        ExecRootBlock type_ = r_.getContext().getClasses().getClassesBodies().getVal(_look);
        if (type_ == null) {
            return new StringList();
        }
        return new StringList(_look);
    }

    public ResultContext getResult() {
        return result;
    }

}
