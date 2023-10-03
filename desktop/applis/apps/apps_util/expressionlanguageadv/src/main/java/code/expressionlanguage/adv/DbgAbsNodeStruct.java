package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ints.Comparing;

public abstract class DbgAbsNodeStruct implements DbgNodeStruct {
    private final CustList<DbgAbsNodeStruct> children = new CustList<DbgAbsNodeStruct>();
    private DbgAbsNodeStruct parentStruct;
    private boolean calculated;
    private final ContextEl result;
    private final ContextEl original;
    private final MutableTreeNodeNav<DbgAbsNodeStruct> node = new MutableTreeNodeNav<DbgAbsNodeStruct>();
    private AbsTextArea logger;
    private AbsPlainButton stop;
    private AbsCustComponent group;
    private String infoStr = "";
    private AbstractMutableTreeNodeCore<String> associated;

    protected DbgAbsNodeStruct(DbgAbsNodeStruct _par) {
        this(_par.getResult(), _par.getOriginal(), _par);
    }

    protected DbgAbsNodeStruct(ContextEl _r, ContextEl _o, DbgAbsNodeStruct _par) {
        this.result = _r;
        this.original = _o;
        parentStruct = _par;
        node.info(this);
    }

    public void removeChildren() {
        for (DbgAbsNodeStruct d: children) {
            d.node.removeFromParent();
            d.parentStruct = null;
            d.associated = null;
        }
        associated.removeAllChildren();
        children.clear();
        node.removeAllChildren();
        setCalculated(false);
    }
    @Override
    public String repr() {
        return infoStr;
    }

    @Override
    public void repr(String _r) {
        infoStr = _r;
    }

    @Override
    public void repr(Struct _r) {
        repr(TreeNodeRenderUtil.wrapValueInner(_r, getResult()));
    }

    public MutableTreeNodeNav<DbgAbsNodeStruct> getNode() {
        return node;
    }

    public ContextEl getResult() {
        return result;
    }

    @Override
    public ContextEl getOriginal() {
        return original;
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
        return true;
    }

    public boolean feedChildren(AbsCompoFactory _compo) {
        Struct vn_ = value();
        if (vn_ == null) {
            return false;
        }
        staticPart(_compo);
        Struct v_ = vn_.getParent();
        if (v_ != NullStruct.NULL_VALUE) {
            DbgParentStruct e_ = new DbgParentStruct(this, v_);
            node.add(e_.getNode());
            AbstractMutableTreeNodeCore<String> sub_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(e_));
            e_.setAssociated(sub_);
            getAssociated().add(sub_);
            children.add(e_);
        }
        if (vn_ instanceof FieldableStruct) {
            CustList<DbgFieldStruct> fielRet_ = new CustList<DbgFieldStruct>();
            CustList<ClassFieldStruct> fs_ = ((FieldableStruct) vn_).getFields();
            for (ClassFieldStruct e: fs_) {
                DbgFieldStruct efs_ = new DbgFieldStruct(this, e.getClassField(), e.getStruct());
                fielRet_.add(efs_);
            }
            sortedAdded(_compo,fielRet_, new DbgFieldStructCmp());
        }
        if (vn_ instanceof ArrayStruct) {
            CustList<Struct> ls_ = ((ArrayStruct) vn_).list();
            int s_ = ls_.size();
            for (int i = 0; i < s_; i++) {
                DbgArrEltStruct efs_ = new DbgArrEltStruct(this, i, ls_.get(i));
                AbstractMutableTreeNodeCore<String> sub_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(efs_));
                efs_.setAssociated(sub_);
                getAssociated().add(sub_);
                children.add(efs_);
                node.add(efs_.getNode());
            }
        }
        return true;
    }

    private void staticPart(AbsCompoFactory _compo) {
        if (this instanceof DbgCallStruct) {
            DbgCallStruct curr_ = (DbgCallStruct) this;
            String id_ = curr_.id();
            CustList<DbgFieldStruct> stFields_ = new CustList<DbgFieldStruct>();
            for (EntryCust<String,Struct> f: NumParsers.getStaticFieldMap(id_,result.getClasses().getStaticFields()).entryList()) {
                DbgFieldStruct efs_ = new DbgFieldStruct(this, new ClassField(id_,f.getKey()), f.getValue());
                stFields_.add(efs_);
            }
            sortedAdded(_compo,stFields_, new DbgFieldStructQuickCmp());
        }
    }

    private void sortedAdded(AbsCompoFactory _compo, CustList<DbgFieldStruct> _fs, Comparing<DbgFieldStruct> _c) {
        _fs.sortElts(_c);
        for (DbgFieldStruct e: _fs) {
            children.add(e);
            AbstractMutableTreeNodeCore<String> sub_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(e));
            e.setAssociated(sub_);
            getAssociated().add(sub_);
            node.add(e.getNode());
        }
    }

    public AbstractMutableTreeNodeCore<String> getAssociated() {
        return associated;
    }

    public void setAssociated(AbstractMutableTreeNodeCore<String> _ass) {
        this.associated = _ass;
    }

    @Override
    public AbsTextArea logs() {
        return logger;
    }

    @Override
    public AbsPlainButton stopButton() {
        return stop;
    }

    @Override
    public AbsCustComponent panel() {
        return group;
    }

    @Override
    public void logs(AbsTextArea _a) {
        logger = _a;
    }

    @Override
    public void stopButton(AbsPlainButton _b) {
        stop = _b;
    }

    @Override
    public void panel(AbsCustComponent _c) {
        group = _c;
    }

    public DbgAbsNodeStruct getParentStruct() {
        return parentStruct;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean _c) {
        this.calculated = _c;
    }
}
