package code.expressionlanguage.adv;

import code.gui.AbsAttrSet;
import code.gui.AbsTextField;
import code.gui.AbsTextPane;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FindAction implements AbsActionListener {
    private final TabEditor current;

    public FindAction(TabEditor _editor) {
        current = _editor;
    }

    @Override
    public void action() {
        current.getFinderPanel().setVisible(true);
        AbsTextPane editor_ = current.getCenter();
        AbsTextField finder_ = current.getFinder();
        String s_ = StringUtil.nullToEmpty(editor_.getSelectedText());
        if (!s_.isEmpty()) {
            finder_.setText(s_);
        } else {
            finder_.setText(finder_.getText());
        }
    }

    static void updateEditor(TabEditor _tab) {
        String find_ = StringUtil.nullToEmpty(_tab.getFinder().getText());
        String t_ = _tab.getCenter().getText();
        _tab.getCenter().clearCharacterAttributes(0,t_.length());
        _tab.getParts().clear();
        int index_ = 0;
        while (index_ >= 0) {
            index_ = segment(t_,find_,index_, _tab.getParts());
        }
        int count_ = _tab.getParts().size();
        for (int i = 0; i < count_; i++) {
            SegmentFindPart seg_ = _tab.getParts().get(i);
            if (seg_.getBegin() == _tab.getCenter().getSelectionStart()) {
                _tab.setCurrentPart(i);
            }
            AbsAttrSet as_ = _tab.getFactories().getCompoFactory().newAttrSet();
            as_.addBackground(GuiConstants.GREEN);
            as_.addForeground(GuiConstants.WHITE);
            as_.addFontSize(12);
            _tab.getCenter().setCharacterAttributes(seg_.getBegin(), seg_.getEnd() - seg_.getBegin(),as_,false);
        }
    }
    static int segment(String _text, String _find, int _index, CustList<SegmentFindPart> _parts) {
        SegmentFindPart seg_ = segment(_text, _find, _index);
        if (seg_.getBegin() == seg_.getEnd()) {
            return -1;
        }
        _parts.add(seg_);
        return seg_.getEnd();
    }
    static SegmentFindPart segment(String _text, String _find, int _index) {
        int next_ = _text.indexOf(_find, _index);
        if (next_ >= _index) {
            return new SegmentFindPart(next_,next_+_find.length());
        }
        return new SegmentFindPart(-1, -1);
    }
}
