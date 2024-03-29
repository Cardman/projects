package code.formathtml.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.exec.opers.*;
import code.formathtml.util.InputInfo;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatInputBuilder {

    public void tryBuildInputResult(String _name, ResultInput _resultInput, AnaRendBlock _bl, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _resultInput.setOpsReadRoot(RenderAnalysis.getRootAnalyzedOperations(_name+"()", 0, _anaDoc, _page, new ResultExpression()));
        OperationNode settable_ = _resultInput.getOpsReadRoot();
        _resultInput.setClassName(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
        _resultInput.setResult(settable_.getResultClass());
        AnaClassArgumentMatching pr_;
        if (settable_ instanceof FctOperation) {
            pr_ = new AnaClassArgumentMatching(_page.getGlobalClass());
            _resultInput.setSettable(settable_);
        } else {
            OperationNode last_ = ((MethodOperation) settable_).getChildrenNodes().last();
            pr_ = ((FctOperation) last_).getPreviousResultClass();
            _resultInput.setSettable(last_);
        }
        _resultInput.setPreviousResult(pr_);
        StringList varNames_ = new StringList();
        String varPrevLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
        varNames_.add(varPrevLoc_);
        String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
        varNames_.add(varLoc_);
        InputInfo info_ = new InputInfo();
        info_.getVarTypes().add(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
//        _resultInput.get.getVarTypes().add(NumParsers.getSingleNameOrEmpty(result.getNames()));
        _resultInput.setVarNamesParams(info_);
        _resultInput.setVarNames(varNames_);
        _resultInput.setVarName(StringUtil.concat(varPrevLoc_,AnaRendBlock.COMMA,varLoc_));
    }

    public CustList<RendDynOperationNode> buildWritePart(ResultInput _resultInput, Forwards _forwards) {
        FctOperation settable_ = (FctOperation) _resultInput.getSettable();
        CustList<RendDynOperationNode> w_ = new CustList<RendDynOperationNode>();
        String cl_ = NumParsers.getSingleNameOrEmpty(_resultInput.getResult().getNames());
        ExecClassArgumentMatching pr_ = FetchMemberUtil.toExec(_resultInput.getPreviousResult());
        ExecClassArgumentMatching clResField_ = new ExecClassArgumentMatching(cl_);
        OperationNode root_ = _resultInput.getOpsReadRoot();
        if (settable_ == root_) {
            AnaCallFctContent callFctContent_ = settable_.getCallFctContent();
            ClassMethodId classMethodId_ = callFctContent_.getClassMethodId();
            MethodId constraints_ = classMethodId_.getConstraints();
            AnaCallFctContent gene_ = new AnaCallFctContent("");
            gene_.setClassMethodId(new ClassMethodId(classMethodId_.getClassName(),new MethodId(MethodAccessKind.INSTANCE,constraints_.getName(),new StringList(cl_))));
            RendStdFctOperation rendStd_ = new RendStdFctOperation(new ExecOperationContent(0, pr_, 2),true,new ExecStdFctContent(gene_,false),new ExecArrContent(false));
            RendDotOperation rendDot_ = new RendDotOperation(new ExecOperationContent(0, clResField_, 3));
            RendThisOperation rendPrevVar_ = new RendThisOperation(new ExecOperationContent(0, pr_, 0), 0);
            RendStdRefVariableOperation rendVar_ = new RendStdRefVariableOperation(new ExecOperationContent(0, clResField_, 1), new ExecVariableContent(generateVariable(_resultInput.getVarNames().last())));
            rendStd_.appendChild(rendVar_);
            rendPrevVar_.setSiblingSet(rendStd_);
            rendDot_.appendChild(rendPrevVar_);
            rendDot_.appendChild(rendStd_);
            w_.add(rendPrevVar_);
            w_.add(rendVar_);
            w_.add(rendStd_);
            w_.add(rendDot_);
            return w_;
        }
        AnaCallFctContent callFctContent_ = settable_.getCallFctContent();
        ClassMethodId classMethodId_ = callFctContent_.getClassMethodId();
        MethodId constraints_ = classMethodId_.getConstraints();
        AnaCallFctContent gene_ = new AnaCallFctContent("");
        gene_.setClassMethodId(new ClassMethodId(classMethodId_.getClassName(),new MethodId(MethodAccessKind.INSTANCE,constraints_.getName(),new StringList(cl_))));
        RendStdFctOperation rendStd_ = new RendStdFctOperation(new ExecOperationContent(0, pr_, 2),true,new ExecStdFctContent(gene_,false),new ExecArrContent(false));
        RendDotOperation rendDot_ = new RendDotOperation(new ExecOperationContent(0, clResField_, 3));
        RendStdRefVariableOperation rendPrevVar_ = new RendStdRefVariableOperation(new ExecOperationContent(0, pr_, 0), new ExecVariableContent(generateVariable(_resultInput.getVarNames().first())));
        rendPrevVar_.setSiblingSet(rendStd_);
        rendDot_.appendChild(rendPrevVar_);
        rendDot_.appendChild(rendStd_);
        RendStdRefVariableOperation rendVar_ = new RendStdRefVariableOperation(new ExecOperationContent(0, clResField_, 1), new ExecVariableContent(generateVariable(_resultInput.getVarNames().last())));
        rendStd_.appendChild(rendVar_);
        w_.add(rendPrevVar_);
        w_.add(rendVar_);
        w_.add(rendStd_);
        w_.add(rendDot_);
        return w_;
    }
    private static AnaVariableContent generateVariable(String _varLoc) {
        AnaVariableContent cont_ = new AnaVariableContent(0);
        cont_.setDeep(-1);
        cont_.setVariableName(_varLoc);
        return cont_;
    }
}
