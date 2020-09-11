package code.expressionlanguage.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssSettableFieldOperation extends AssLeafOperation implements AssSettableElResult {
    private FieldInfo fieldMetaInfo;
    private boolean declare;
    AssSettableFieldOperation(SettableAbstractFieldOperation _ex) {
        super(_ex);
        fieldMetaInfo = _ex.getFieldMetaInfo();
        declare = _ex.isDeclare();
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        Argument arg_ = getArgument();
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<AssignmentBefore> assB_ = vars_.getVariablesBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (!declare&&arg_ != null) {
            AssUtil.setAssignments(this,_ass,_a);
            return;
        }
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(page_);
        StringMap<Assignment> ass_ =AssignmentsUtil.assignAfter(isBool_,assB_);
        boolean procField_ = isFromCurrentClass(_conf);
        ClassField cl_ = fieldMetaInfo.getClassField();
        AssMethodOperation par_ = getParent();
        if (par_ instanceof AssAffectationOperation && isFirstChildInParent()) {
            procField_ = false;
        } else {
            if (par_ instanceof AssDotOperation) {
                boolean cancelCheck_ = false;
                if (par_.getFirstChild() instanceof AssAccessorOperation) {
                    cancelCheck_ = true;
                } else if (par_.getFirstChild() instanceof AssDotOperation) {
                    AssOperationNode op_ = ((AssDotOperation)par_.getFirstChild()).getChildrenNodes().last();
                    if (op_ instanceof AssAccessorOperation) {
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
            if (fieldMetaInfo.isStaticField()) {
                procField_ = false;
            }
        }
        if (_conf.isAssignedFields()) {
            procField_ = false;
        }
        if (procField_) {
            for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                if (StringList.quickEq(e.getKey(),cl_.getFieldName()) && !e.getValue().isAssignedBefore()) {
                    if (ContextUtil.isFinalField(_conf,cl_) && !declare) {
                        //error if final field
                        setRelativeOffsetPossibleAnalyzable(_conf);
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(page_.getLocalizer().getCurrentFileName());
                        un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
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
        if (par_.getFirstChild() instanceof AssAccessorOperation) {
            return true;
        }
        if (par_.getFirstChild() instanceof AssDotOperation) {
            AssOperationNode op_ = ((AssDotOperation)par_.getFirstChild()).getChildrenNodes().last();
            return op_ instanceof AssAccessorOperation;
        }
        return false;
    }

    private boolean notMatchCurrentType(ContextEl _an) {
        ClassField clField_ = fieldMetaInfo.getClassField();
        String gl_ = _an.getAnalyzing().getGlobalClass();
        String id_ = StringExpUtil.getIdFromAllTypes(gl_);
        return !StringList.quickEq(clField_.getClassName(), id_);
    }

    public boolean isDeclare() {
        return declare;
    }

    public final ClassField getFieldId() {
        return fieldMetaInfo.getClassField();
    }

    public boolean matchFieldId(ClassField key_) {
        return fieldMetaInfo.getClassField().eq(key_);
    }
}
