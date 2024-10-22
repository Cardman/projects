package aiki.db;

import aiki.map.pokemon.*;
import code.util.CustList;

public abstract class ChangeStringFieldPkList {
    private final ChangeStringFieldVisit changeStringFieldVisit;

    protected ChangeStringFieldPkList(ChangeStringFieldVisit _v) {
        this.changeStringFieldVisit = _v;
    }

    public void changeValue(String _oldName, String _newName) {
        int len_ = length();
        for (int i =0; i < len_; i++) {
            DataBase.changeValue(changeStringFieldVisit.visit(elt(i)),_oldName,_newName);
        }
    }

    public CustList<ChangeStringFieldMatch> changeValue() {
        CustList<ChangeStringFieldMatch> chg_ = new CustList<ChangeStringFieldMatch>();
        int len_ = length();
        for (int i =0; i < len_; i++) {
            chg_.add(new ChangeStringFieldMatchDef(changeStringFieldVisit.visit(elt(i))));
        }
        return chg_;
    }
    public abstract int length();
    public abstract Pokemon elt(int _i);
}
