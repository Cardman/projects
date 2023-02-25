package code.expressionlanguage.adv;

import code.gui.AbsTextField;
import code.gui.AbsTextPane;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class ReplaceAction implements AbsActionListener {
    private final TabEditor current;
    private final boolean previousReplace;
    private final boolean nextReplace;

    public ReplaceAction(TabEditor _editor, boolean _p, boolean _n) {
        current = _editor;
        previousReplace = _p;
        nextReplace = _n;
    }

    @Override
    public void action() {
        current.setEnabledSyntax(false);
        current.getCenter().setEditable(true);
        AbsTextPane editor_ = current.getCenter();
        AbsTextField replacer_ = current.getReplacer();
        int cur_ = current.getCurrentPart();
        String s_ = replacer_.getText();
        CustList<SegmentFindPart> rev_ = current.getParts();
        int size_ = rev_.size() - 1;
        for (int i = size_; i >= 0; i--) {
            if (i < cur_ && previousReplace || i > cur_ && nextReplace || i == cur_) {
                SegmentFindPart seg_ = rev_.get(i);
                editor_.select(seg_.getBegin(),seg_.getEnd());
                editor_.replaceSelection(s_);
            }
        }
        current.getCenter().setEditable(false);
        current.setEnabledSyntax(true);
        new UpdatingEditorAndSelect(current).run();
    }

}
