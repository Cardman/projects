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
        window.getSelectedPb().getValue().setEnabled(window.getEnabledBp().isSelected());
        window.getSelectedPb().getValue().setInstanceType(window.getInstanceType().isSelected());
        window.getSelectedPb().getValue().setStaticType(window.getStaticType().isSelected());
        window.setSelectedPb(null);
    }
}
