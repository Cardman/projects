package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.EnumerableStruct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringList;

public final class RendSwitchBlock extends RendParentBlock implements RendBreakableBlock,RendReducableOperations,RendWithEl {

    private String label;

    private int valueOffset;

    private CustList<RendDynOperationNode> opValue;

    private boolean enumTest;
    private String instanceTest;

    public RendSwitchBlock(int _offsetTrim, String label, int valueOffset, CustList<RendDynOperationNode> opValue, boolean enumTest, String instanceTest) {
        super(_offsetTrim);
        this.label = label;
        this.valueOffset = valueOffset;
        this.opValue = opValue;
        this.enumTest = enumTest;
        this.instanceTest = instanceTest;
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        ip_.setOffset(valueOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        Argument arg_ =  RenderExpUtil.calculateReuse(opValue,_cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        if (!instanceTest.isEmpty()) {
            RendSwitchBlockStack if_ = new RendSwitchBlockStack();
            RendBlock n_ = getFirstChild();
            CustList<RendParentBlock> children_;
            children_ = new CustList<RendParentBlock>();
            while (n_ != null) {
                if (n_ instanceof RendParentBlock) {
                    children_.add((RendParentBlock)n_);
                }
                n_ = n_.getNextSibling();
            }
            if_.setBlock(this);
            RendParentBlock found_ = null;
            if (arg_.isNull()) {
                for (RendParentBlock b: children_) {
                    if (b instanceof RendCaseCondition) {
                        RendCaseCondition c_ = (RendCaseCondition) b;
                        if (c_.getImportedClassName().isEmpty()) {
                            if_.setLastVisitedBlock(b);
                            found_ = b;
                            break;
                        }
                    }
                }
            } else {
                for (RendParentBlock b: children_) {
                    if (b instanceof RendCaseCondition) {
                        RendCaseCondition c_ = (RendCaseCondition) b;
                        if (!c_.getImportedClassName().isEmpty()) {
                            RendCaseCondition b_ = (RendCaseCondition) b;
                            found_ = fetch(_cont,if_,arg_,found_,b_, _ctx);
                        }
                    }
                }
            }
            if (found_ == null) {
                for (RendParentBlock b: children_) {
                    if (b instanceof RendDefaultCondition) {
                        RendDefaultCondition b_ = (RendDefaultCondition) b;
                        found_ = fetch(_cont,if_,arg_,found_,b_, _ctx);
                    }
                }
            }
            if (found_ == null) {
                if_.setCurrentVisitedBlock(this);
            } else {
                rw_.setRead(found_);
                if_.setCurrentVisitedBlock(found_);
            }
            ip_.addBlock(if_);
            return;
        }
        RendSwitchBlockStack if_ = new RendSwitchBlockStack();
        RendBlock n_ = getFirstChild();
        CustList<RendParentBlock> children_;
        children_ = new CustList<RendParentBlock>();
        while (n_ != null) {
            if (n_ instanceof RendParentBlock) {
                children_.add((RendParentBlock)n_);
                if_.setLastVisitedBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        RendParentBlock found_ = null;
        if (arg_.isNull()) {
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    found_ = b;
                    continue;
                }
                RendCaseCondition c_ = (RendCaseCondition) b;
                Argument argRes_ = c_.getArgument();
                if (argRes_ == null) {
                    continue;
                }
                if (argRes_.isNull()) {
                    found_ = c_;
                    break;
                }
            }
        } else if (enumTest) {
            EnumerableStruct en_ = (EnumerableStruct) arg_.getStruct();
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    found_ = b;
                    continue;
                }
                RendCaseCondition c_ = (RendCaseCondition) b;
                if (c_.getArgument() != null) {
                    continue;
                }
                if (StringList.quickEq(c_.getValue().trim(), en_.getName())) {
                    found_ = c_;
                    break;
                }
            }
        } else {
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    found_ = b;
                    continue;
                }
                RendCaseCondition c_ = (RendCaseCondition) b;
                Argument argRes_ = c_.getArgument();
                if (argRes_.getStruct().sameReference(arg_.getStruct())) {
                    found_ = c_;
                    break;
                }
            }
        }
        if (found_ == null) {
            if_.setCurrentVisitedBlock(this);
        } else {
            rw_.setRead(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }
    private static RendParentBlock fetch(Configuration _cont, RendSwitchBlockStack if_, Argument arg_,
                                         RendParentBlock _found, RendSwitchPartCondition _s, ContextEl _ctx) {
        if (_found != null) {
            return _found;
        }
        String type_ = _s.getImportedClassName();
        ImportingPage ip_ = _cont.getLastPage();
        if (ExecTemplates.safeObject(type_, arg_, _ctx) == ErrorType.NOTHING) {
            String var_ = _s.getVariableName();
            ip_.putValueVar(var_,LocalVariable.newLocalVariable(arg_.getStruct(),type_));
            if_.setLastVisitedBlock(_s);
            return _s;
        }
        return null;
    }

}
