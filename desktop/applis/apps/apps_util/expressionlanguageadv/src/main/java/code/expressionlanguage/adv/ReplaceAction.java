package code.expressionlanguage.adv;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.core.DefaultUniformingString;

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
        AbsTextField replacer_ = current.getReplacer();
        int cur_ = current.getCurrentPart();
        String s_ = replacer_.getText();
        StringBuilder copy_ = new StringBuilder(current.centerText());
        CustList<SegmentFindPart> rev_ = current.getParts();
        SegmentFindPart selAfter_ = new SegmentFindPart(0,0);
        int size_ = rev_.size() - 1;
        for (int i = size_; i >= 0; i--) {
            if (i < cur_ && previousReplace || i > cur_ && nextReplace || i == cur_) {
                SegmentFindPart seg_ = rev_.get(i);
                copy_.replace(seg_.getBegin(),seg_.getEnd(),s_);
                int diff_ = s_.length()-(seg_.getEnd() - seg_.getBegin());
                selAfter_ = new SegmentFindPart(seg_.getBegin()+diff_,seg_.getEnd()+diff_);
            }
        }
        current.setEnabledSyntax(false);
        current.centerText(new DefaultUniformingString().apply(copy_.toString()));
        current.setEnabledSyntax(true);
        current.centerSelect(selAfter_.getBegin(),selAfter_.getEnd());
        new UpdatingEditorAndSelect(current).run();
    }

}
