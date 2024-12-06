package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.maths.litteralcom.*;
import code.util.StringList;
import code.util.core.*;

public final class PkCompleteEvent implements AbsActionListener {
    private final GeneComponentModelText input;
    private final FacadeGame facadeGame;

    public PkCompleteEvent(GeneComponentModelText _i, FacadeGame _f) {
        this.input = _i;
        this.facadeGame = _f;
    }

    @Override
    public void action() {
        AbsTextPane textPane_ = input.getTextPane();
        input.getWords().clear();
        int caretPosition_ = textPane_.getCaretPosition();
        AbstractProgramInfos compoFactory_ = input.getCompoFactory();
        input.setCompleteOffset(previousChar(textPane_.getText(), caretPosition_));
        input.getWords().addAllElts(ConverterCommonMapUtil.complete(facadeGame.getData(), compoFactory_, textPane_.getText(), caretPosition_));
        computeWords(input, 0);
    }

    public static void computeWords(GeneComponentModelText _input, int _diff) {
        AbsTextPane textPane_ = _input.getTextPane();
        int caretPosition_ = textPane_.getCaretPosition();
        StringList res_ = words(textPane_, _input.getWords(), _diff);
        if (res_.isEmpty()) {
            _input.getPopupMenu().setVisible(false);
            return;
        }
        _input.getElement().clear();
        for (String w: res_) {
            _input.getElement().add(w);
        }
        _input.getElement().select(0);
        _input.getElement().applyRows();
        _input.getElement().revalidate();
        _input.getElement().repaint();
        MetaRect metaRect_ = textPane_.modelToView(previousChar(textPane_.getText(), caretPosition_));
        _input.getPopupMenu().show(textPane_,metaRect_.getPoint().getXcoord(),metaRect_.getPoint().getYcoord()+textPane_.getMetaFont().getRealSize());
    }
    public static StringList words(AbsTextPane _txt, StringList _words, int _diff) {
        String text_ = _txt.getText();
        int adj_ = _txt.getCaretPosition() + _diff;
        int pr_ = previousChar(text_, adj_);
        StringList filtered_ = new StringList();
        for (String w: _words) {
            if (text_.startsWith(w.substring(0,NumberUtil.max(0, NumberUtil.min(adj_-pr_,w.length()))),pr_)) {
                filtered_.add(w);
            }
        }
        return filtered_;
    }
    public static int previousChar(String _text, int _caret) {
        int pr_ = NumberUtil.min(_caret-1, _text.length() - 1);
        while (pr_ >= 0 && MathExpUtil.isWordChar(_text.charAt(pr_))) {
            pr_--;
        }
        return pr_+1;
    }
}
