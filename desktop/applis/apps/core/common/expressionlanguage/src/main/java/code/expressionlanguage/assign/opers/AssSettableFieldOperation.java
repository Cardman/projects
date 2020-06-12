package code.expressionlanguage.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.exec.ExecSettableFieldOperation;
import code.expressionlanguage.opers.util.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssSettableFieldOperation extends AssLeafOperation implements AssSettableElResult {
    private FieldInfo fieldMetaInfo;
    AssSettableFieldOperation(ExecSettableFieldOperation _ex) {
        super(_ex);
        fieldMetaInfo = _ex.getFieldMetaInfo();
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        Argument arg_ = getArgument();
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (arg_ != null) {
            AssUtil.setAssignments(this,_ass,_a);
            return;
        }
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        ass_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assB_));
        assAfM_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assM_));
        boolean procField_ = isFromCurrentClass(_conf);
        ClassField cl_ = fieldMetaInfo.getClassField();
        AssMethodOperation par_ = getParent();
        if (par_ instanceof AssAffectationOperation && isFirstChildInParent()) {
            procField_ = false;
        } else {
            if (par_ instanceof AssDotOperation) {
                boolean cancelCheck_ = false;
                if (par_.getFirstChild() instanceof AssThisOperation) {
                    cancelCheck_ = true;
                } else if (par_.getFirstChild() instanceof AssStaticAccessOperation) {
                    cancelCheck_ = true;
                } else if (par_.getFirstChild() instanceof AssDotOperation) {
                    AssOperationNode op_ = ((AssDotOperation)par_.getFirstChild()).getChildrenNodes().last();
                    if (op_ instanceof AssThisOperation) {
                        cancelCheck_ = true;
                    }
                }
                if (cancelCheck_) {
                    if (par_.getParent() instanceof AssAffectationOperation && par_.isFirstChildInParent()) {
                        procField_ = false;
                    }
                }
            }
        }
        if (_conf.isAssignedStaticFields()) {
            FieldInfo meta_ = _conf.getFieldInfo(cl_);
            if (meta_.isStaticField()) {
                procField_ = false;
            }
        }
        if (_conf.isAssignedFields()) {
            procField_ = false;
        }
        if (procField_) {
            for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                if (StringList.quickEq(e.getKey(),cl_.getFieldName()) && !e.getValue().isAssignedBefore()) {
                    FieldInfo meta_ = _conf.getFieldInfo(cl_);
                    if (meta_.isFinalField() && !AssUtil.isDeclaringField(this, _ass)) {
                        //error if final field
                        setRelativeOffsetPossibleAnalyzable(_conf);
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                        un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                        un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                                cl_.getFieldName());
                        _conf.addError(un_);
                    }
                }
            }
            if (getParent() == null) {
                assA_.put(cl_.getFieldName(),Assignment.assign(isBool_,false,true));
            }
        }
        assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
        vars_.getVariables().put(this, ass_);
        vars_.getMutableLoop().put(this, assAfM_);
        vars_.getFields().put(this, assA_);
    }
    public final boolean isFromCurrentClass(ContextEl _an) {
        if (notMatchCurrentType(_an)) {
            return false;
        }
        if (isFirstChild()) {
            return true;
        }
        AssMethodOperation par_ = getParent();
        if (!(par_ instanceof AssDotOperation)) {
            return false;
        }
        if (par_.getFirstChild() instanceof AssThisOperation) {
            return true;
        }
        if (par_.getFirstChild() instanceof AssStaticAccessOperation) {
            return true;
        }
        if (par_.getFirstChild() instanceof AssDotOperation) {
            AssOperationNode op_ = ((AssDotOperation)par_.getFirstChild()).getChildrenNodes().last();
            return op_ instanceof AssThisOperation;
        }
        return false;
    }

    private boolean notMatchCurrentType(ContextEl _an) {
        ClassField clField_ = fieldMetaInfo.getClassField();
        String gl_ = _an.getAnalyzing().getGlobalClass();
        String id_ = Templates.getIdFromAllTypes(gl_);
        return !StringList.quickEq(clField_.getClassName(), id_);
    }

    public final ClassField getFieldId() {
        return fieldMetaInfo.getClassField();
    }

    public boolean matchFieldId(ClassField key_) {
        return fieldMetaInfo.getClassField().eq(key_);
    }
}
