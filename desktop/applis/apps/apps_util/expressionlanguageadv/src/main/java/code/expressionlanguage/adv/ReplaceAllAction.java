package code.expressionlanguage.adv;

import code.gui.AbsTextField;
import code.gui.AbsTextPane;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class ReplaceAllAction implements AbsActionListener {
    private final TabEditor current;

    public ReplaceAllAction(TabEditor _editor) {
        current = _editor;
    }

    @Override
    public void action() {
        current.setEnabledSyntax(false);
        AbsTextPane editor_ = current.getCenter();
        AbsTextField replacer_ = current.getReplacer();
        String s_ = replacer_.getText();
        CustList<SegmentFindPart> rev_ = current.getParts().getReverse();
        int size_ = rev_.size();
        for (int i = 0; i < size_; i++) {
            SegmentFindPart seg_ = rev_.get(i);
            editor_.select(seg_.getBegin(),seg_.getEnd());
            editor_.replaceSelection(s_);
        }
        current.setEnabledSyntax(true);
        new UpdatingEditorAndSelect(current).run();
    }

}
