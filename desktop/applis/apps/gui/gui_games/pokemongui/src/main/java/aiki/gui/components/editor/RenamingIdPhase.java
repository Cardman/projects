package aiki.gui.components.editor;

import code.util.*;

public final class RenamingIdPhase {
    private StringList mids = new StringList();
    private String oldId = "";
    private String newId = "";

    public StringList getMids() {
        return mids;
    }

    public void setMids(StringList _m) {
        this.mids = _m;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String _o) {
        this.oldId = _o;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String _n) {
        this.newId = _n;
    }
}
