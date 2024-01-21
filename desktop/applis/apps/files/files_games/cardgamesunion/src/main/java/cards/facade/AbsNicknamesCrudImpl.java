package cards.facade;

import code.gui.initialize.AbstractProgramInfos;

public abstract class AbsNicknamesCrudImpl implements AbsNicknamesCrud {
    private final AbstractProgramInfos programInfos;
    private String tempFolder = "";
    private Nicknames nicknames = new Nicknames();

    protected AbsNicknamesCrudImpl(AbstractProgramInfos _p) {
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

    public Nicknames getNicknames() {
        return nicknames;
    }

    public void setNicknames(Nicknames _n) {
        this.nicknames = _n;
    }
}
