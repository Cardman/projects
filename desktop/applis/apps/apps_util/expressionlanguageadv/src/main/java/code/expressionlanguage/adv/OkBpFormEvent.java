package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class OkBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public OkBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getBpForm().setVisible(false);
        window.getSelectedPb().setEnabled(window.getEnabledBp().isSelected());
        window.getSelectedPb().setInstanceType(window.getInstanceType().isSelected());
        window.getSelectedPb().setStaticType(window.getStaticType().isSelected());
        window.setSelectedPb(null);
    }
}
