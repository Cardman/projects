package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.SegmentColorPart;
import code.expressionlanguage.analyze.files.SegmentType;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringDataUtil;
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
        _tab.getCommonFrame().getFrames().getCompoFactory().invokeNow(new ClearCharacterAttributes(_tab.getCenter()));
        String t_ = _tab.getCenter().getText();
        syntax(_tab, t_);
        _tab.getParts().clear();
        int index_ = 0;
        while (index_ >= 0) {
            index_ = segment(t_,find_,index_, _tab.getCaseSens().isSelected(), _tab.getWholeWord().isSelected(), _tab.getParts());
        }
        int count_ = _tab.getParts().size();
        for (int i = 0; i < count_; i++) {
            SegmentFindPart seg_ = _tab.getParts().get(i);
            AbsAttrSet as_ = _tab.getFactories().getCompoFactory().newAttrSet();
            as_.addBackground(GuiConstants.GREEN);
            as_.addForeground(GuiConstants.WHITE);
            as_.addFontSize(12);
            _tab.getCommonFrame().getFrames().getCompoFactory().invokeNow(new SetCharacterAttributes(_tab.getCenter(), seg_.getBegin(), seg_.getEnd() - seg_.getBegin(),as_));
        }
        _tab.setCurrentPart(partIndex(_tab.getCenter().getSelectionStart(), _tab.getParts()));
        _tab.getReplaceOne().setEnabled(count_ > 0);
        _tab.getReplaceAll().setEnabled(count_ > 0);
        _tab.getReplaceNext().setEnabled(count_ > 0);
        _tab.getReplacePrevious().setEnabled(count_ > 0);
    }

    static void updateEditorStyle(TabEditor _tab) {
        _tab.getCommonFrame().getFrames().getCompoFactory().invokeNow(new ClearCharacterAttributes(_tab.getCenter()));
        String t_ = _tab.getCenter().getText();
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
            _tab.getCommonFrame().getFrames().getCompoFactory().invokeNow(new SetCharacterAttributes(_tab.getCenter(), seg_.getBegin(), seg_.getEnd() - seg_.getBegin(),as_));
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
    static int segment(String _text, String _find, int _index, boolean _sensCase, boolean _wholeWord, CustList<SegmentFindPart> _parts) {
        SegmentFindPart seg_ = segment(_text, _find, _index,_sensCase,_wholeWord);
        if (seg_.getBegin() == seg_.getEnd()) {
            return -1;
        }
        if (!_wholeWord|| noLetterBefore(_text, seg_.getBegin()) && noLetterAfter(_text, seg_.getEnd())) {
            _parts.add(seg_);
        }
        return seg_.getEnd();
    }
    static SegmentFindPart segment(String _text, String _find, int _index, boolean _sensCase, boolean _wholeWord) {
        if (_wholeWord && !isWordLooking(_find)) {
            return new SegmentFindPart(-1, -1);
        }
        if (!_sensCase) {
            int un_ = _text.length();
            int seg_ = _find.length();
            for (int i = _index; i < un_; i++) {
                if (matchChars(_text, _find, seg_, i, un_)) {
                    return new SegmentFindPart(i, i + _find.length());
                }
            }
            return new SegmentFindPart(-1, -1);
        }
        int next_ = _text.indexOf(_find, _index);
        if (next_ >= _index) {
            return new SegmentFindPart(next_, next_ + _find.length());
        }
        return new SegmentFindPart(-1, -1);
    }

    private static boolean noLetterAfter(String _text, int _nextBound) {
        return _nextBound >= _text.length() || isNotLetterOrDigitLook(_text.charAt(_nextBound));
    }

    private static boolean noLetterBefore(String _text, int _next) {
        return _next <= 0 || isNotLetterOrDigitLook(_text.charAt(_next - 1));
    }

    private static boolean matchChars(String _text, String _find, int _seg, int _i, int _gl) {
        for (int j = 0; j < _seg; j++) {
            if (_i+j>=_gl||!NumParsers.eqChIgnCase(_text.charAt(_i+j), _find.charAt(j))) {
                return false;
            }
        }
        return true;
    }
    private static boolean isWordLooking(String _text) {
        if (_text.isEmpty()) {
            return false;
        }
        for (char c: _text.toCharArray()) {
            if (isNotLetterOrDigitLook(c)) {
                return false;
            }
        }
        return true;
    }
    private static boolean isNotLetterOrDigitLook(char _ch) {
        return _ch != '_' && !StringDataUtil.isLetterOrDigit(_ch);
    }
}
