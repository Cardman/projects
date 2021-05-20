package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.core.StringUtil;

public final class RendSwitchBlock extends RendParentBlock implements RendWithEl {

    private final String label;

    private final int valueOffset;

    private final CustList<RendDynOperationNode> opValue;

    private final boolean enumTest;
    private final String instanceTest;

    public RendSwitchBlock(int _offsetTrim, String _label, int _valueOffset, CustList<RendDynOperationNode> _opValue, boolean _enumTest, String _instanceTest) {
        super(_offsetTrim);
        this.label = _label;
        this.valueOffset = _valueOffset;
        this.opValue = _opValue;
        this.enumTest = _enumTest;
        this.instanceTest = _instanceTest;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stds, _ctx, _stack, _rendStack);
            return;
        }
        ip_.setOffset(valueOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        Argument arg_ =  RenderExpUtil.calculateReuse(opValue,_cont, _stds, _ctx, _stack, _rendStack);
        if (_ctx.callsOrException(_stack)) {
            return;
        }
        if (!instanceTest.isEmpty()) {
            RendSwitchBlockStack if_ = new RendSwitchBlockStack();
            if_.setLabel(label);
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
                            found_ = fetch(if_,arg_,found_,b_, _ctx, _rendStack);
                        }
                    }
                }
            }
            if (found_ == null) {
                for (RendParentBlock b: children_) {
                    if (b instanceof RendDefaultCondition) {
                        RendDefaultCondition b_ = (RendDefaultCondition) b;
                        found_ = fetch(if_,arg_,found_,b_, _ctx, _rendStack);
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
        if_.setLabel(label);
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
            String name_ = NumParsers.getNameOfEnum(arg_.getStruct());
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    found_ = b;
                    continue;
                }
                RendCaseCondition c_ = (RendCaseCondition) b;
                if (c_.getArgument() != null) {
                    continue;
                }
                if (StringUtil.quickEq(c_.getValue().trim(), name_)) {
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
    private static RendParentBlock fetch(RendSwitchBlockStack _if, Argument _arg,
                                         RendParentBlock _found, RendSwitchPartCondition _s, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_found != null) {
            return _found;
        }
        String type_ = _s.getImportedClassName();
        ImportingPage ip_ = _rendStackCall.getLastPage();
        Struct struct_ = _arg.getStruct();
        if (ExecInherits.safeObject(type_, struct_.getClassName(_ctx), _ctx) == ErrorType.NOTHING) {
            String var_ = _s.getVariableName();
            ip_.putValueVar(var_,LocalVariable.newLocalVariable(struct_,type_));
            _if.setLastVisitedBlock(_s);
            return _s;
        }
        return null;
    }

}
