package code.gui.document;

import code.gui.*;
import code.gui.events.*;
import code.util.*;

public abstract class BeanAnchorEvent implements AbsMouseListenerIntRel {
    private final StringMap<AbsBeanRender> renders;

    protected BeanAnchorEvent(StringMap<AbsBeanRender> _r) {
        this.renders = _r;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        action(renders);
    }

    protected abstract void action(StringMap<AbsBeanRender> _r);
}
