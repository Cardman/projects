package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChangeSoftParamsEvent implements AbsActionListener {
    private final CdmParameterSoftDialog parameterSoftDialog;
    private final WindowCdmEditor window;
    public ChangeSoftParamsEvent(CdmParameterSoftDialog _dialog, WindowCdmEditor _ed) {
        parameterSoftDialog = _dialog;
        window = _ed;
    }

    @Override
    public void action() {
        window.getDialogSoft().setVisible(false);
        window.getSoftParams().setDirectMatchKeyValue(parameterSoftDialog.getCheck().isSelected());
        window.updateDoc();
        for (OutputDialogAliases a: window.getAliasesFrames()) {
            a.resetGui(window);
        }
    }
}
