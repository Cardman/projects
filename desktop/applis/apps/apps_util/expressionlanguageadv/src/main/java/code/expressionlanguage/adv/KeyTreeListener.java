package code.expressionlanguage.adv;

import code.gui.AbsCtrlKeyState;
import code.gui.AbstractMutableTreeNode;
import code.gui.GuiConstants;
import code.gui.events.AbsKeyListenerPress;

public final class KeyTreeListener implements AbsKeyListenerPress {
    private final WindowCdmEditor editor;

    public KeyTreeListener(WindowCdmEditor _ed) {
        editor = _ed;
    }

    @Override
    public void keyPressed(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        if (_keyState.isControlDown()&&!_keyState.isAltDown()&&!_keyState.isShiftDown()&&_keyCode == GuiConstants.VK_F5) {
            AbstractMutableTreeNode sel_ = editor.getFolderSystem().selectEvt();
            if (sel_ == null) {
                return;
            }
            String str_ = WindowCdmEditor.buildPath(sel_);
            editor.refresh(str_);
        }
    }
}
