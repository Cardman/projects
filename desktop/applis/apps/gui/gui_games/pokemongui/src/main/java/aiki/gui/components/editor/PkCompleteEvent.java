package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.MetaRect;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.litteralcom.*;
import code.util.StringList;
import code.util.core.*;

public final class PkCompleteEvent implements AbsActionListener {
    private final GeneComponentModelText input;
    private final FacadeGame facadeGame;
    private final ScrollCustomGraphicList<String> element;
    private final AbsPopupMenu popupMenu;

    public PkCompleteEvent(GeneComponentModelText _i, FacadeGame _f, ScrollCustomGraphicList<String> _s, AbsPopupMenu _p) {
        this.input = _i;
        this.facadeGame = _f;
        this.element = _s;
        this.popupMenu = _p;
    }

    @Override
    public void action() {
        AbsTextPane textPane_ = input.getTextPane();
        input.getWords().clear();
        int caretPosition_ = textPane_.getCaretPosition();
        AbstractProgramInfos compoFactory_ = input.getCompoFactory();
        input.getWords().addAllElts(ConverterCommonMapUtil.complete(facadeGame.getData(), compoFactory_, textPane_.getText(), caretPosition_));
        StringList res_ = words(textPane_, input.getWords(), 0);
        if (res_.isEmpty()) {
            popupMenu.setVisible(false);
            return;
        }
        element.clear();
        for (String w: res_) {
            element.add(w);
        }
        element.revalidate();
        element.repaint();
        MetaRect metaRect_ = textPane_.modelToView(caretPosition_);
        popupMenu.show(textPane_,metaRect_.getPoint().getXcoord(),metaRect_.getPoint().getYcoord()+textPane_.getMetaFont().getRealSize());
    }
    public static StringList words(AbsTextPane _txt, StringList _words, int _diff) {
        String text_ = _txt.getText();
        int adj_ = _txt.getCaretPosition() + _diff;
        int pr_ = previousChar(text_, adj_);
        StringList filtered_ = new StringList();
        for (String w: _words) {
            if (text_.startsWith(w.substring(0,adj_-pr_),pr_)) {
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
