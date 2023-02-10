package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class FindAction implements AbsActionListener {
    private final WindowCdmEditor current;

    public FindAction(WindowCdmEditor _editor) {
        current = _editor;
    }

    @Override
    public void action() {
        current.getFinderPanel().setVisible(true);
        AbsTextPane editor_ = current.getCenter();
        AbsCommonFrame frame_ = current.getCommonFrame();
        AbsTextField finder_ = current.getFinder();
        String s_ = StringUtil.nullToEmpty(editor_.getSelectedText());
        if (!s_.isEmpty()) {
            finder_.setText(s_);
        }
        String find_ = StringUtil.nullToEmpty(finder_.getText());
        if (find_.isEmpty()) {
            frame_.pack();
            return;
        }
        String t_ = editor_.getText();
        editor_.clearCharacterAttributes(0,t_.length());
        int index_ = 0;
        while (index_ >= 0) {
            int next_ = t_.indexOf(find_, index_);
            if (next_ >= 0) {
                AbsAttrSet as_ = frame_.getFrames().getCompoFactory().newAttrSet();
                as_.addBackground(GuiConstants.GREEN);
                as_.addForeground(GuiConstants.WHITE);
                as_.addFontSize(12);
                editor_.setCharacterAttributes(next_, find_.length(),as_,false);
                index_ = next_ + find_.length();
            } else {
                index_ = -1;
            }
        }
        frame_.pack();
    }
}
