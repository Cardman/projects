package aiki.gui.components.editor;

import aiki.db.*;
import code.util.*;

public final class RenamingIdPhase {
    private StringList mids = new StringList();
    private String oldId = DataBase.EMPTY_STRING;
    private String newId = DataBase.EMPTY_STRING;
    private String oldMid = DataBase.EMPTY_STRING;
    private String newMid = DataBase.EMPTY_STRING;
    private String oldPref = DataBase.EMPTY_STRING;
    private String newPref = DataBase.EMPTY_STRING;

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

    public String getOldMid() {
        return oldMid;
    }

    public void setOldMid(String _o) {
        this.oldMid = _o;
    }

    public String getNewMid() {
        return newMid;
    }

    public void setNewMid(String _n) {
        this.newMid = _n;
    }

    public String getOldPref() {
        return oldPref;
    }

    public void setOldPref(String _o) {
        this.oldPref = _o;
    }

    public String getNewPref() {
        return newPref;
    }

    public void setNewPref(String _n) {
        this.newPref = _n;
    }
}
