package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CdmParameterSoftEvent implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;
    private CdmParameterSoftDialog parameterSoftDialog;

    public CdmParameterSoftEvent(WindowCdmEditor _ed) {
        this.windowCdmEditor = _ed;
    }

    @Override
    public void action() {
        parameterSoftDialog = new CdmParameterSoftDialog(windowCdmEditor);
    }

    public CdmParameterSoftDialog getParameterSoftDialog() {
        return parameterSoftDialog;
    }
}
