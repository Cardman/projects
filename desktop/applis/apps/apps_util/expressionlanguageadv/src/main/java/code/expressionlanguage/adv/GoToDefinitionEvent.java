package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.gui.events.AbsActionListener;
import code.gui.images.MetaPoint;

public final class GoToDefinitionEvent implements AbsActionListener {
    private final AnalyzedPageEl page;
    private final RowSrcLocation result;
    private final WindowWithTreeImpl window;

    public GoToDefinitionEvent(AnalyzedPageEl _p,RowSrcLocation _r, WindowWithTreeImpl _w) {
        page = _p;
        this.result = _r;
        window = _w;
    }

    @Override
    public void action() {
        boolean f_ = ContextUtil.isFromCustFile(page.getPreviousFilesBodies().getVal(result.getFileName()));
        if (!f_) {
            return;
        }
        String str_ = window.pathToSrc()+result.getFileName();
        if (window.openFile(str_)){
            int ind_ = window.getEditors().getSelectedIndex();
            TabEditor tab_ = window.getTabs().get(ind_);
            MetaPoint p_ = tab_.getCenter().modelToView(result.getIndex()).getPoint();
            tab_.getScCenter().setHorizontalValue(p_.getXcoord());
            tab_.getScCenter().setVerticalValue(p_.getYcoord());
        }
    }

}
