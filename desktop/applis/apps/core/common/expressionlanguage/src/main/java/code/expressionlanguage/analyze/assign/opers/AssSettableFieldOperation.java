package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.DeclaredFieldOperation;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AssSettableFieldOperation extends AssLeafOperation implements AssOperationNodeFull{
    private final AnaSettableOperationContent fieldMetaInfo;
    private final boolean declare;
    private final RootBlock rootBlock;
    public AssSettableFieldOperation(SettableAbstractFieldOperation _ex) {
        super(_ex);
        fieldMetaInfo = _ex.getSettableFieldContent();
        declare = _ex instanceof DeclaredFieldOperation;
        rootBlock = _ex.getFieldType();
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        Argument arg_ = getArgument();
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<AssignmentBefore> assB_ = vars_.getVariablesBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (!declare&&arg_ != null) {
            AssUtil.setAssignments(this,_ass,_a);
            return;
        }
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_page);
        StringMap<Assignment> ass_ =AssignmentsUtil.assignAfter(isBool_,assB_);
        ClassField cl_ = fieldMetaInfo.getClassField();
        boolean procField_ = procField(_page);
        if (procField_) {
            for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                if (StringUtil.quickEq(e.getKey(), cl_.getFieldName()) && !e.getValue().isAssignedBefore() && fieldMetaInfo.isFinalField() && !declare) {
                    //error if final field
                    setRelativeOffsetPossibleAnalyzable(_page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_page);
                    un_.buildError(_page.getAnalysisMessages().getFinalField(),
                            cl_.getFieldName());
                    _page.getLocalizer().addError(un_);
                }
            }
            if (getParent() == null) {
                assA_.put(cl_.getFieldName(),Assignment.assign(isBool_,false,true));
            }
        }
        assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }

    private boolean procField(AnalyzedPageEl _page) {
        boolean procField_ = isFromCurrentClass(_page);
        AssMethodOperation par_ = getParent();
        if (par_ instanceof AssAffectationOperation && isFirstChildInParent()) {
            procField_ = false;
        } else {
            if (par_ instanceof AssDotOperation) {
                boolean cancelCheck_ = cancelCheck(par_);
                if (cancelCheck_ && par_.getParent() instanceof AssAffectationOperation && par_.isFirstChildInParent()) {
                    procField_ = false;
                }
            }
        }
        if (_page.isAssignedStaticFields() && fieldMetaInfo.isStaticField()) {
            procField_ = false;
        }
        if (_page.isAssignedFields()) {
            procField_ = false;
        }
        return procField_;
    }

    private boolean cancelCheck(AssMethodOperation _par) {
        boolean cancelCheck_ = false;
        if (_par.getFirstChild() instanceof AssAccessorOperation) {
            cancelCheck_ = true;
        } else if (_par.getFirstChild() instanceof AssDotOperation) {
            AssOperationNode op_ = ((AssDotOperation) _par.getFirstChild()).getChildrenNodes().last();
            if (op_ instanceof AssAccessorOperation) {
                cancelCheck_ = true;
            }
        }
        return cancelCheck_;
    }

    public boolean isFromCurrentClass(AnalyzedPageEl _page) {
        if (notMatchCurrentType(_page)) {
            return false;
        }
        if (isFirstChild()) {
            return true;
        }
        AssMethodOperation par_ = getParent();
        if (!(par_ instanceof AssDotOperation)) {
            return false;
        }
        if (par_.getFirstChild() instanceof AssAccessorOperation) {
            return true;
        }
        if (par_.getFirstChild() instanceof AssDotOperation) {
            AssOperationNode op_ = ((AssDotOperation)par_.getFirstChild()).getChildrenNodes().last();
            return op_ instanceof AssAccessorOperation;
        }
        return false;
    }

    private boolean notMatchCurrentType(AnalyzedPageEl _page) {
        return rootBlock != _page.getGlobalType().getRootBlock();
    }

    public boolean isDeclare() {
        return declare;
    }

    public ClassField getFieldId() {
        return fieldMetaInfo.getClassField();
    }

    public boolean matchFieldId(ClassField _key) {
        return fieldMetaInfo.getClassField().eq(_key);
    }

    public AnaSettableOperationContent getFieldMetaInfo() {
        return fieldMetaInfo;
    }

}
