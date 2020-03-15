package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.ReturnMethod;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class DimensionArrayInstancing extends
        AbstractArrayInstancingOperation implements PreAnalyzableOperation {
    private int countArrayDims;
    private String typeInfer = EMPTY_STRING;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public DimensionArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        countArrayDims = getOperations().getCountArrays();
    }

    @Override
    public void preAnalyze(Analyzable _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String methodName_ = getMethodName();
        String className_ = methodName_.trim().substring(newKeyWord_.length());
        int local_ = StringList.getFirstPrintableCharIndex(className_);
        className_ = className_.trim();
        StringMap<String> vars_ = new StringMap<String>();

        String inferForm_ = Templates.getInferForm(className_);
        if (inferForm_ == null) {
            return;
        }
        String type_;
        int nbParentsInfer_ = 0;
        OperationNode current_;
        String mName_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(mName_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _an);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        MethodOperation m_;
        type_ = _an.resolveAccessibleIdTypeWithoutError(newKeyWord_.length()+local_,inferForm_);
        partOffsets_.addAllElts(_an.getContextEl().getCoverage().getCurrentParts());
        if (type_.isEmpty()) {
            return;
        }
        current_ = this;
        m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof ElementArrayInstancing) && !(m_ instanceof InferArrayInstancing)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                if (m_ instanceof AbstractTernaryOperation) {
                    if (m_.getFirstChild() == current_) {
                        break;
                    }
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                break;
            }
            nbParentsInfer_++;
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        String typeAff_ = EMPTY_STRING;
        Block cur_ = _an.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            FunctionBlock f_ = _an.getAnalyzing().getCurrentFct();
            if (f_ instanceof NamedFunctionBlock) {
                NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
                String ret_ = n_.getImportedReturnType();
                String void_ = _an.getStandards().getAliasVoid();
                if (!StringList.quickEq(ret_, void_)) {
                    typeAff_ = ret_;
                }
            }
        } else if (m_ instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) m_;
            SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
            if (s_ != null) {
                ClassArgumentMatching c_ = s_.getResultClass();
                typeAff_ = c_.getSingleNameOrEmpty();
            }
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (typeAff_.isEmpty() || StringList.quickEq(typeAff_, keyWordVar_)) {
            return;
        }
        int chCount_ = getOperations().getValues().size();
        String cp_ = PrimitiveTypeUtil.getQuickComponentType(typeAff_, chCount_+countArrayDims+nbParentsInfer_);
        if (cp_ == null) {
            return;
        }
        String infer_ = Templates.tryInfer(type_,vars_, cp_, _an);
        if (infer_ == null) {
            return;
        }
        partOffsets.addAllElts(partOffsets_);
        int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
        int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
        _an.appendTitleParts(begin_,end_,infer_,partOffsets);
        typeInfer = infer_;
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        setClassName(_conf.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        if (typeInfer.isEmpty()) {
            int local_ = StringList.getFirstPrintableCharIndex(className_);
            className_ = _conf.resolveCorrectType(new_.length()+local_,className_);
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
        } else {
            className_ = typeInfer;
        }
        for (OperationNode o: chidren_) {
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
            if (!o.getResultClass().isNumericInt(_conf)) {
                ClassArgumentMatching cl_ = o.getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                un_.setOperands(cl_);
                _conf.addError(un_);
            }
            o.getResultClass().setUnwrapObject(_conf.getStandards().getAliasPrimInteger());
            o.cancelArgument();
        }
        setClassName(className_);
        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(className_, chidren_.size()+countArrayDims)));
    }

    public int getCountArrayDims() {
        return countArrayDims;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
