package cards.facade;

import code.gui.initialize.AbstractProgramInfos;

public abstract class AbsCrudImpl {
    private final AbstractProgramInfos programInfos;
    private String tempFolder = "";

    protected AbsCrudImpl(AbstractProgramInfos _p) {
        this.programInfos = _p;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public String getTempFolder() {
        return tempFolder;
    }

    public void setTempFolder(String _t) {
        this.tempFolder = _t;
    }
}
