package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.SegmentColorPart;
import code.expressionlanguage.analyze.files.SegmentType;
import code.expressionlanguage.analyze.files.StringComment;
import code.gui.AbsAttrSet;
import code.gui.AbsTextField;
import code.gui.AbsTextPane;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FindAction implements AbsActionListener {
    private final TabEditor current;
    private final boolean readOnly;

    public FindAction(TabEditor _editor, boolean _readOnly) {
        current = _editor;
        readOnly = _readOnly;
    }

    @Override
    public void action() {
        current.getNavModifPanel().setVisible(true);
        current.getReplacerPanel().setVisible(!readOnly);
        AbsTextPane editor_ = current.getCenter();
        AbsTextField finder_ = current.getFinder();
        String s_ = StringUtil.nullToEmpty(editor_.getSelectedText());
        if (!s_.isEmpty()) {
            finder_.setText(s_);
        } else {
            new UpdatingEditorAndSelect(current).run();
        }
    }

    static void updateEditor(TabEditor _tab) {
        String find_ = StringUtil.nullToEmpty(_tab.getFinder().getText());
        String t_ = _tab.getCenter().getText();
        _tab.getCenter().clearCharacterAttributes(0,t_.length());
        syntax(_tab, t_);
        _tab.getParts().clear();
        int index_ = 0;
        while (index_ >= 0) {
            index_ = segment(t_,find_,index_, _tab.getParts());
        }
        int count_ = _tab.getParts().size();
        for (int i = 0; i < count_; i++) {
            SegmentFindPart seg_ = _tab.getParts().get(i);
            AbsAttrSet as_ = _tab.getFactories().getCompoFactory().newAttrSet();
            as_.addBackground(GuiConstants.GREEN);
            as_.addForeground(GuiConstants.WHITE);
            as_.addFontSize(12);
            _tab.getCenter().setCharacterAttributes(seg_.getBegin(), seg_.getEnd() - seg_.getBegin(),as_,false);
        }
        _tab.setCurrentPart(partIndex(_tab.getCenter().getSelectionStart(), _tab.getParts()));
        _tab.getReplaceOne().setEnabled(count_ > 0);
        _tab.getReplaceAll().setEnabled(count_ > 0);
        _tab.getReplaceNext().setEnabled(count_ > 0);
        _tab.getReplacePrevious().setEnabled(count_ > 0);
    }

    static void updateEditorStyle(TabEditor _tab) {
        String t_ = _tab.getCenter().getText();
        _tab.getCenter().clearCharacterAttributes(0,t_.length());
        syntax(_tab,t_);
        _tab.getParts().clear();
    }

    private static void syntax(TabEditor _tab, String _text) {
        StringComment sc_ = new StringComment(_text, _tab.getWindowEditor().getComments());
        CustList<SegmentColorPart> segText_ = sc_.getSegmentColorParts();
        int partCount_ = segText_.size();
        for (int i = 0; i < partCount_; i++) {
            SegmentColorPart seg_ = segText_.get(i);
            AbsAttrSet as_ = _tab.getFactories().getCompoFactory().newAttrSet();
            if (seg_.getType() == SegmentType.STRING) {
                as_.addForeground(GuiConstants.GREEN);
            } else {
                as_.addForeground(GuiConstants.GRAY);
            }
            as_.addFontSize(12);
            _tab.getCenter().setCharacterAttributes(seg_.getBegin(), seg_.getEnd() - seg_.getBegin(),as_,false);
        }
    }
    static int partIndex(int _begin, CustList<SegmentFindPart> _parts) {
        int count_ = _parts.size();
        for (int i = 0; i < count_; i++) {
            SegmentFindPart seg_ = _parts.get(i);
            if (_begin <= seg_.getBegin()) {
                return i;
            }
        }
        if (count_ > 0) {
            return _parts.getLastIndex();
        }
        return -1;
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
