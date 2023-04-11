package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.expressionlanguage.analyze.types.LocationsPartTypeUtil;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;

public final class ResultExpressionOperationNode {
    private ResultExpression resultExpression;
    private AbsBk block;
    private OperationNode found;
    public static CustList<SrcFileLocation> locations(AnalyzedPageEl _page, String _fileName, int _caret) {
        ResultExpressionOperationNode res_ = container(_page, _fileName, _caret);
        OperationNode foundOp_ = res_.getFound();
        if (foundOp_ instanceof AbsFctOperation) {
            int mb_ = res_.begin(foundOp_)+((AbsFctOperation)foundOp_).getDelta();
            int me_ = mb_+((AbsFctOperation)foundOp_).getLengthMethod();
            if (inRange(mb_,_caret,me_)) {
                AnaCallFctContent c_ = ((AbsFctOperation) foundOp_).getCallFctContent();
                return methodsLocations(c_);
            }
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((AbsFctOperation) foundOp_).getPartOffsets(),_fileName,_caret);
        }
        if (foundOp_ instanceof StaticAccessOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticAccessOperation)foundOp_).getPartOffsets(),_fileName,_caret);
        }
        if (foundOp_ instanceof StaticCallAccessOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticCallAccessOperation)foundOp_).getPartOffsets().getResult(),_fileName,_caret);
        }
        if (foundOp_ instanceof FunctFilterOperation) {
            return fetch(_fileName, _caret, ((FunctFilterOperation) foundOp_).getPartOffsets());
        }
        if (foundOp_ instanceof StandardInstancingOperation) {
            StandardInstancingOperation instStd_ = (StandardInstancingOperation) foundOp_;
            return fetch(_fileName, _caret,instStd_.getPartsInstInitInterfaces());
        }
        return new CustList<SrcFileLocation>();
    }

    private static CustList<SrcFileLocation> fetch(String _fileName, int _caret, CustList<AnaResultPartTypeDtoInt> _list) {
        CustList<SrcFileLocation> s_ = new CustList<SrcFileLocation>();
        for (AnaResultPartTypeDtoInt a: _list) {
            s_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(a, _fileName, _caret));
        }
        return s_;
    }

    private static CustList<SrcFileLocation> methodsLocations(AnaCallFctContent _c) {
        NamedFunctionBlock cust_ = fct(_c);
        if (cust_ != null) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationMethod(cust_));
            return ls_;
        }
        StandardMethod std_ = _c.getStandardMethod();
        if (std_ != null) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationStdMethod(std_));
            return ls_;
        }
        return new CustList<SrcFileLocation>();
    }

    private static NamedFunctionBlock fct(AnaCallFctContent _c) {
        AnaTypeFct f_ = _c.getFunction();
        if (f_ == null) {
            return null;
        }
        return f_.getFunction();
    }

    public static ResultExpressionOperationNode container(AnalyzedPageEl _page, String _fileName, int _caret) {
        AbsBk sub_ = _page.getPreviousFilesBodies().getVal(_fileName);
        ResultExpressionOperationNode out_ = new ResultExpressionOperationNode();
        while (sub_ != null) {
            sub_ = subContainer(_caret, sub_);
            ResultExpression res_ = result(sub_, _caret);
            out_.setResultExpression(res_);
            BracedBlock next_;
            if (res_ == null) {
                next_ = null;
                out_.setBlock(sub_);
                out_.setFound(null);
            } else {
                OperationNode found_ = out_.subContainer(_caret);
                next_ = nextBlock(found_, res_.getSumOffset(),_caret);
                if (next_ == null) {
                    out_.setBlock(sub_);
                    out_.setFound(found_);
                }
            }
            sub_ = next_;
        }
        return out_;
    }

    public static AbsBk subContainer(int _caret, AbsBk _bl) {
        AbsBk current_ = _bl;
        AbsBk out_ = _bl;
        while (current_ != null) {
            AbsBk ch_ = current_.getFirstChild();
            if (match(current_, _caret)) {
                out_ = current_;
                current_ = ch_;
                continue;
            }
            while (current_ != null) {
                AbsBk n_ = current_.getNextSibling();
                current_ = n_;
                if (match(n_, _caret)) {
                    out_ = n_;
                    break;
                }
            }
        }
        return out_;
    }

    private static boolean match(AbsBk _b, int _caret) {
        return _b!=null&&_b.getOffset() <= _caret && _caret < _b.getEndAll();
    }

    public static ResultExpression result(AbsBk _block, int _caret) {
        if (_block instanceof InfoBlock) {
            ResultParsedAnnots a_ = ((InfoBlock) _block).getAnnotations();
            int index_ = indexOfAnnot(a_, _caret);
            if (index_ > -1) {
                return a_.getAnnotations().get(index_).getRes();
            }
        }
        if (_block instanceof InnerTypeOrElement) {
            return lookElt((InnerTypeOrElement) _block, _caret);
        }
        if (_block instanceof RootBlock) {
            return resRoot((RootBlock) _block, _caret);
        }
        if (_block instanceof NamedFunctionBlock) {
            return lookFct(_block, _caret);
        }
        if (_block instanceof SwitchMethodBlock) {
            return resSw((SwitchMethodBlock) _block, _caret);
        }
        if (_block instanceof FieldBlock) {
            ResultExpression res_ = ((FieldBlock) _block).getRes();
            if (inExp(res_,_caret)){
                return res_;
            }
            return null;
        }
        return instrLook(_block, _caret);
    }

    private static ResultExpression lookElt(InnerTypeOrElement _block, int _caret) {
        AnaElementContent e_ = _block.getElementContent();
        int beginName_ = e_.getFieldNameOffest();
        int endName_ = beginName_ + e_.getFieldName().length();
        if (inRange(beginName_,_caret,endName_)) {
            return _block.getRes();
        }
        int begin_ = e_.getTempClassOffset();
        int end_ = begin_+e_.getTempClass().length();
        if (inRange(begin_,_caret,end_)) {
            return null;
        }
        return _block.getRes();
    }

    private static ResultExpression lookFct(AbsBk _block, int _caret) {
        ResultParsedAnnots a_ = ((NamedFunctionBlock) _block).getAnnotations();
        int index_ = indexOfAnnot(a_, _caret);
        if (index_ > -1) {
            return a_.getAnnotations().get(index_).getRes();
        }
        CustList<ResultParsedAnnots> params_ = ((NamedFunctionBlock) _block).getAnnotationsParams();
        int ind_ = indexOfAnnots(params_, _caret);
        if (ind_ > -1) {
            int loc_ = indexOfAnnot(params_.get(ind_), _caret);
            return params_.get(ind_).getAnnotations().get(loc_).getRes();
        }
        if (_block instanceof NamedCalledFunctionBlock) {
            ResultParsedAnnots annotationsSupp_ = ((NamedCalledFunctionBlock) _block).getAnnotationsSupp();
            int indexSupp_ = indexOfAnnot(annotationsSupp_, _caret);
            if (indexSupp_ > -1) {
                return annotationsSupp_.getAnnotations().get(indexSupp_).getRes();
            }
        }
        return null;
    }

    private static ResultExpression instrLook(AbsBk _block, int _caret) {
        if (_block instanceof ConditionBlock) {
            ResultExpression res_ = ((ConditionBlock) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof WithFilterContent) {
            return caseLook((WithFilterContent) _block, _caret);
        }
        if (_block instanceof SwitchBlock) {
            ResultExpression res_ = ((SwitchBlock) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ForEachLoop) {
            ResultExpression res_ = ((ForEachLoop) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ForEachTable) {
            ResultExpression res_ = ((ForEachTable) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        return defLook(_block, _caret);
    }

    private static ResultExpression caseLook(WithFilterContent _block, int _caret) {
        ResultExpression resValue_ = _block.getFilterContent().getResValue();
        if (inExp(resValue_, _caret)){
            return resValue_;
        }
        ResultExpression resCondition_ = _block.getFilterContent().getResCondition();
        if (inExp(resCondition_, _caret)){
            return resCondition_;
        }
        return null;
    }

    private static ResultExpression defLook(AbsBk _block, int _caret) {
        if (_block instanceof Line) {
            ResultExpression res_ = ((Line) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ReturnMethod) {
            ResultExpression res_ = ((ReturnMethod) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof Throwing) {
            ResultExpression res_ = ((Throwing) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ForIterativeLoop) {
            return lookForIter((ForIterativeLoop) _block, _caret);
        }
        if (_block instanceof ForMutableIterativeLoop) {
            return lookForMut((ForMutableIterativeLoop) _block, _caret);
        }
        return null;
    }

    private static ResultExpression lookForIter(ForIterativeLoop _block, int _caret) {
        ResultExpression resValue_ = _block.getResInit();
        if (inExp(resValue_, _caret)){
            return resValue_;
        }
        ResultExpression resCondition_ = _block.getResExp();
        if (inExp(resCondition_, _caret)){
            return resCondition_;
        }
        ResultExpression resStep_ = _block.getResStep();
        if (inExp(resStep_, _caret)){
            return resStep_;
        }
        return null;
    }

    private static ResultExpression lookForMut(ForMutableIterativeLoop _block, int _caret) {
        ResultExpression resValue_ = _block.getResInit();
        if (inExp(resValue_, _caret)){
            return resValue_;
        }
        ResultExpression resCondition_ = _block.getResExp();
        if (inExp(resCondition_, _caret)){
            return resCondition_;
        }
        ResultExpression resStep_ = _block.getResStep();
        if (inExp(resStep_, _caret)){
            return resStep_;
        }
        return null;
    }

    public static ResultExpression resRoot(RootBlock _block, int _caret) {
        ResultParsedAnnots a_ = _block.getAnnotations();
        int index_ = indexOfAnnot(a_, _caret);
        if (index_ > -1) {
            return a_.getAnnotations().get(index_).getRes();
        }
        return null;
    }

    public static ResultExpression resSw(SwitchMethodBlock _block, int _caret) {
        ResultParsedAnnots a_ = _block.getAnnotations();
        int index_ = indexOfAnnot(a_, _caret);
        if (index_ > -1) {
            return a_.getAnnotations().get(index_).getRes();
        }
        CustList<ResultParsedAnnots> params_ = _block.getAnnotationsParams();
        int ind_ = indexOfAnnots(params_, _caret);
        if (ind_ > -1) {
            int loc_ = indexOfAnnot(params_.get(ind_), _caret);
            return params_.get(ind_).getAnnotations().get(loc_).getRes();
        }
        return null;
    }

    private static boolean inExp(ResultExpression _res, int _caret) {
        int begin_ = _res.getSumOffset();
        int end_ = begin_ + _res.getAnalyzedString().length();
        return begin_<=_caret&&_caret<end_;
    }

    private static int indexOfAnnots(CustList<ResultParsedAnnots> _a, int _caret) {
        int len_ = _a.size();
        for (int i = 0; i < len_; i++) {
            ResultParsedAnnots annot_ = _a.get(i);
            int loc_ = indexOfAnnot(annot_, _caret);
            if (loc_ > -1) {
                return i;
            }
        }
        return -1;
    }

    private static int indexOfAnnot(ResultParsedAnnots _a, int _caret) {
        CustList<ResultParsedAnnot> annots_ = _a.getAnnotations();
        int len_ = annots_.size();
        for (int i = 0; i < len_; i++) {
            ResultParsedAnnot annot_ = annots_.get(i);
            if (annot_.getIndex()<=_caret&&_caret<annot_.getIndex()+annot_.getAnnotation().trim().length()){
                return i;
            }
        }
        return -1;
    }

    public OperationNode subContainer(int _caret) {
        OperationNode current_ = root(resultExpression);
        OperationNode out_ = root(resultExpression);
        while (current_ != null) {
            OperationNode ch_ = current_.getFirstChild();
            if (match(current_, _caret)) {
                out_ = current_;
                current_ = ch_;
                continue;
            }
            while (current_ != null) {
                OperationNode n_ = current_.getNextSibling();
                current_ = n_;
                if (match(n_, _caret)) {
                    out_ = n_;
                    break;
                }
            }
        }
        return out_;
    }

    public static BracedBlock nextBlock(OperationNode _result, int _sum, int _caret) {
        if (_result instanceof AnonymousLambdaOperation) {
            NamedCalledFunctionBlock anon_ = ((AnonymousLambdaOperation) _result).getBlock();
            int arrow_ = anon_.getNameOffset();
            if (inRange(arrow_, _caret, arrow_ + 2)) {
                return null;
            }
            return anon_;
        }
        if (_result instanceof SwitchOperation) {
            ResultExpression res_ = resSw(((SwitchOperation) _result).getSwitchMethod(), _caret);
            if (res_ != null) {
                return ((SwitchOperation) _result).getSwitchMethod();
            }
            int begin_ = _sum+_result.getIndexInEl()+((SwitchOperation) _result).getOffsetFct();
            int end_ = begin_+((SwitchOperation) _result).getMethodName().length();
            if (inRange(begin_, _caret, end_)) {
                return null;
            }
            return ((SwitchOperation) _result).getSwitchMethod();
        }
        if (_result instanceof AnonymousInstancingOperation) {
            ResultExpression res_ = resRoot(((AnonymousInstancingOperation) _result).getBlock(), _caret);
            if (res_ != null) {
                return ((AnonymousInstancingOperation) _result).getBlock();
            }
            int begin_ = _sum+_result.getIndexInEl()+((AnonymousInstancingOperation) _result).getOffsetFct();
            int end_ = begin_+((AnonymousInstancingOperation) _result).getMethodName().length();
            if (inRange(begin_, _caret, end_)) {
                return null;
            }
            return ((AnonymousInstancingOperation) _result).getBlock();
        }
        return null;
    }

    private static OperationNode root(ResultExpression _r) {
        return _r.getRoot();
    }

    private boolean match(OperationNode _b, int _caret) {
        if (_b == null) {
            return false;
        }
        int begin_ = begin(_b);
        int end_ = end(_b);
        return inRange(begin_, _caret, end_);
    }

    public int begin() {
        return begin(getFound());
    }

    public int begin(OperationNode _b) {
        return _b.getIndexInEl() + resultExpression.getSumOffset();
    }

    public int end() {
        return end(getFound());
    }

    public int end(OperationNode _b) {
        MethodOperation par_ = _b.getParent();
        if (par_ != null) {
            int indexChild_ = _b.getIndexChild();
            return begin(_b) + par_.getChildren().getValue(indexChild_).length();
        }
        return begin(_b) + resultExpression.getAnalyzedString().length();
    }

    public static boolean inRange(int _begin, int _caret, int _end) {
        return _begin <= _caret && _caret < _end;
    }

    public AbsBk getBlock() {
        return block;
    }

    public void setBlock(AbsBk _b) {
        this.block = _b;
    }

    public OperationNode getFound() {
        return found;
    }

    public void setFound(OperationNode _f) {
        this.found = _f;
    }

    public void setResultExpression(ResultExpression _r) {
        this.resultExpression = _r;
    }
}
