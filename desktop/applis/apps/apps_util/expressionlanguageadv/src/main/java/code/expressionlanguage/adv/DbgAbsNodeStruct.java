package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.MutableTreeNodeCoreUtil;
import code.gui.MutableTreeNodeNav;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ints.Comparing;

public abstract class DbgAbsNodeStruct extends MutableTreeNodeNav implements DbgNodeStruct {
    private final CustList<DbgAbsNodeStruct> children = new CustList<DbgAbsNodeStruct>();
    private DbgAbsNodeStruct parentStruct;
    private boolean calculated;
    private final ContextEl result;

    protected DbgAbsNodeStruct(ContextEl _r) {
        this.result = _r;
    }

    public ContextEl getResult() {
        return result;
    }

    public CustList<DbgAbsNodeStruct> getChildren() {
        return children;
    }

    public boolean select() {
        if (isCalculated()) {
            return false;
        }
        setCalculated(true);
        DbgAbsNodeStruct anc_ = this;
        boolean ent_ = false;
        while (anc_ != null) {
            if (ent_ && anc_.value() == value()) {
                return false;
            }
            anc_ = anc_.getParentStruct();
            ent_ = true;
        }
        Struct vn_ = value();
        if (vn_ == null) {
            return false;
        }
        staticPart();
        Struct v_ = vn_.getParent();
        if (v_ != NullStruct.NULL_VALUE) {
            DbgParentStruct e_ = new DbgParentStruct(result, v_);
            e_.setParentStruct(this);
            MutableTreeNodeCoreUtil.add(this, e_);
            children.add(e_);
        }
        if (vn_ instanceof FieldableStruct) {
            CustList<DbgFieldStruct> fielRet_ = new CustList<DbgFieldStruct>();
            CustList<ClassFieldStruct> fs_ = ((FieldableStruct) vn_).getFields();
            for (ClassFieldStruct e: fs_) {
                DbgFieldStruct efs_ = new DbgFieldStruct(result, e.getClassField(), e.getStruct());
                efs_.setParentStruct(this);
                fielRet_.add(efs_);
            }
            sortedAdded(fielRet_, new DbgFieldStructCmp());
        }
        if (vn_ instanceof ArrayStruct) {
            CustList<Struct> ls_ = ((ArrayStruct) vn_).list();
            int s_ = ls_.size();
            for (int i = 0; i < s_; i++) {
                DbgArrEltStruct efs_ = new DbgArrEltStruct(result, i, ls_.get(i));
                efs_.setParentStruct(this);
                children.add(efs_);
                MutableTreeNodeCoreUtil.add(this, efs_);
            }
        }
        return true;
    }

    private void staticPart() {
        if (this instanceof DbgCallStruct) {
            DbgCallStruct curr_ = (DbgCallStruct) this;
            String id_ = curr_.id();
            CustList<DbgFieldStruct> stFields_ = new CustList<DbgFieldStruct>();
            for (EntryCust<String,Struct> f: NumParsers.getStaticFieldMap(id_,result.getClasses().getStaticFields()).entryList()) {
                DbgFieldStruct efs_ = new DbgFieldStruct(result, new ClassField(id_,f.getKey()), f.getValue());
                efs_.setParentStruct(this);
                stFields_.add(efs_);
            }
            sortedAdded(stFields_, new DbgFieldStructQuickCmp());
        }
    }

    private void sortedAdded(CustList<DbgFieldStruct> _fs, Comparing<DbgFieldStruct> _c) {
        _fs.sortElts(_c);
        for (DbgFieldStruct e: _fs) {
            children.add(e);
            MutableTreeNodeCoreUtil.add(this, e);
        }
    }

    public DbgAbsNodeStruct getParentStruct() {
        return parentStruct;
    }

    public void setParentStruct(DbgAbsNodeStruct _p) {
        this.parentStruct = _p;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean _c) {
        this.calculated = _c;
    }
}
