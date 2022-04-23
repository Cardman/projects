package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ParsedAnnotations;
import code.expressionlanguage.analyze.instr.DefaultProcessKeyWord;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.core.StringUtil;

public final class SwitchOperation extends AbstractUnaryOperation implements PreAnalyzableOperation,SettableElResult {
    private final SwitchMethodBlock switchMethod;
    private final String methodName;
    private AnaResultPartType partOffsets = new AnaResultPartType();
    private String retType = EMPTY_STRING;
    private final AnaArrContent arrContent;
    private final int delta;
    public SwitchOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op, SwitchMethodBlock _switchMethod, int _delta) {
        super(_index, _indexChild, _m, _op);
        switchMethod = _switchMethod;
        methodName = _op.getFctName();
        arrContent = new AnaArrContent();
        delta = _delta;
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        _page.getAllSwitchMethods().add(this);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        for (EntryCust<String, AnaLocalVariable> e: _page.getInfosVars().entryList()) {
            switchMethod.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getKey(), e.getValue()));
        }
        for (AnaNamedLocalVariable e: _page.getCache().getLocalVariables()) {
            switchMethod.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getName(), e.getLocalVariable()));
        }
        for (EntryCust<String, AnaLoopVariable> e: _page.getLoopsVars().entryList()) {
            switchMethod.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getKey(),e.getValue()));
        }
        for (AnaNamedLoopVariable e: _page.getCache().getLoopVariables()) {
            switchMethod.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getName(),e.getLocalVariable()));
        }
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        AbsBk cur_ = _page.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            retType = InvokingOperation.tryGetRetType(_page);
        } else {
            retType = InvokingOperation.tryGetTypeAff(m_, par_.getOperationChild().getIndexChild());
        }
        if (retType.isEmpty()) {
            int nbParentsInfer_ = par_.getNbParentsInfer();
            boolean list_ = false;
            if (m_ instanceof ArgumentListInstancing){
                list_ = true;
                m_ = m_.getParent().getParent();
            }
            if (m_ instanceof NamedArgumentOperation){
                NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
                String inferRecord_ = n_.infer();
                if (!inferRecord_.isEmpty()) {
                    String format_ = StringExpUtil.getQuickComponentType(inferRecord_, nbParentsInfer_);
                    if (format_ != null) {
                        retType = format_;
                    }
                }
                String name_ = n_.getName();
                MethodOperation call_ = n_.getParent();
                if (call_ instanceof RetrieveMethod) {
                    RetrieveMethod f_ = (RetrieveMethod) call_;
                    NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page,call_);
                    CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                    int len_ = methodInfos_.size();
                    StringList candidates_ = new StringList();
                    for (int i = 0; i < len_; i++) {
                        int gr_ = methodInfos_.get(i).size();
                        CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                        for (int j = 0; j < gr_; j++) {
                            MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                            String format_ = tryParamFormatEmp(filter_,methodInfo_, name_, nbParentsInfer_);
                            if (format_ == null) {
                                continue;
                            }
                            candidates_.add(format_);
                            newList_.add(methodInfo_);
                        }
                        methodInfos_.set(i,newList_);
                    }
                    if (candidates_.onlyOneElt()) {
                        retType = candidates_.first();
                    }
                }
                if (call_ instanceof RetrieveConstructor) {
                    RetrieveConstructor f_ = (RetrieveConstructor) call_;
                    NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page,call_);
                    CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                    int len_ = methodInfos_.size();
                    StringList candidates_ = new StringList();
                    CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                    for (int i = 0; i < len_; i++) {
                        ConstructorInfo methodInfo_ = methodInfos_.get(i);
                        String format_ = tryParamFormatEmp(filter_,methodInfo_, name_, nbParentsInfer_);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.clear();
                    methodInfos_.addAllElts(newList_);
                    if (candidates_.onlyOneElt()) {
                        retType = candidates_.first();
                    }
                }
            }
            if (m_ instanceof RetrieveMethod){
                RetrieveMethod f_ = (RetrieveMethod) m_;
                OperationNode firstChild_ = f_.getFirstChild();
                int deltaCount_ = InvokingOperation.getDeltaCount(list_,firstChild_);
                int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
                CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        String format_ = tryFormatEmp(methodInfo_, indexChild_, nbParentsInfer_);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.set(i,newList_);
                }
                if (candidates_.onlyOneElt()) {
                    retType = candidates_.first();
                }
            }
            if (m_ instanceof RetrieveConstructor){
                RetrieveConstructor f_ = (RetrieveConstructor) m_;
                OperationNode firstChild_ = f_.getFirstChild();
                int deltaCount_ = InvokingOperation.getDeltaCount(list_,firstChild_);
                int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
                CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                for (int i = 0; i < len_; i++) {
                    ConstructorInfo methodInfo_ = methodInfos_.get(i);
                    String format_ = tryFormatEmp(methodInfo_, indexChild_, nbParentsInfer_);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
                if (candidates_.onlyOneElt()) {
                    retType = candidates_.first();
                }
            }
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String switchWord_ = keyWords_.getKeyWordSwitch();
        String afterSwitch_ = methodName.trim().substring(switchWord_.length());
        String suppType_ = "";
        int offDelta_ = StringUtil.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offDelta_, _page);
        if (afterSwitch_.trim().startsWith("[")) {
            int delta_ = 0;
            int start_ = afterSwitch_.indexOf('[') + 1;
            int k_ = start_;
            int len_ = afterSwitch_.length();
            int count_ = 1;
            boolean defined_ = false;
            while (k_ < len_) {
                char ch_ = afterSwitch_.charAt(k_);
                if (ch_ == '[') {
                    count_++;
                }
                if (count_ == 1 && ch_ == ':') {
                    int l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,k_+1);
                    if (afterSwitch_.startsWith("@",l_)) {
                        ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(l_),l_);
                        parse_.parse();
                        l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,parse_.getIndex());
                    }
                    if (afterSwitch_.startsWith(":",l_)) {
                        int n_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,l_+1);
                        if (afterSwitch_.startsWith("@",n_)) {
                            ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(n_),n_);
                            parse_.parse();
                            n_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,parse_.getIndex());
                        }
                        l_ = n_;
                    }
                    if (afterSwitch_.startsWith("]",l_)) {
                        suppType_ = afterSwitch_.substring(start_,k_);
                        if (suppType_.trim().isEmpty()) {
                            defined_ = true;
                        }
                        delta_ = StringExpUtil.getOffset(suppType_);
                        k_ = len_;
                        continue;
                    }
                }
                if (ch_ == ']') {
                    count_--;
                    if (count_ == 0) {
                        suppType_ = afterSwitch_.substring(start_,k_);
                        if (suppType_.trim().isEmpty()) {
                            defined_ = true;
                        }
                        delta_ = StringExpUtil.getOffset(suppType_);
                        k_ = len_;
                        continue;
                    }
                }
                k_++;
            }
            if (!defined_) {
                partOffsets = ResolvingTypes.resolveCorrectType(switchWord_.length() + start_ + delta_, suppType_, _page);
                retType = partOffsets.getResult(_page);
            }
        }
        if (retType.isEmpty()) {
            retType = _page.getAliasObject();
        }
        switchMethod.setRetType(retType);
        MethodOperation parent_ = getParent();
        int indCh_ = getIndexChild();
        while (atMostOne(parent_)) {
            indCh_ = parent_.getIndexChild();
            parent_ = parent_.getParent();
        }
        if (isParentSetter(parent_)&&indCh_==0) {
            switchMethod.setRetRef(true);
        }
        AccessedBlock acc_ = switchMethod.getAccessedBlock();
        switchMethod.getAllReservedInners().addAllElts(acc_.getAllReservedInners());
        MemberCallingsBlock currentFct_ = _page.getCurrentFct();
        if (currentFct_ != null) {
            switchMethod.getMappings().putAllMap(currentFct_.getRefMappings());
            switchMethod.getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            switchMethod.getMappings().putAllMap(acc_.getRefMappings());
        }
        AccessedFct imp_ = _page.getAccessedFct();
        if (imp_ != null) {
            imp_.getSwitchMethods().add(switchMethod);
        }
        AbsBk currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getSwitchMethods().add(switchMethod);
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getSwitchMethods().add(switchMethod);
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getSwitchMethodRoots().add(switchMethod);
        }
    }
    private static String tryParamFormatEmp(NameParametersFilter _filter, Parametrable _param, String _name, int _nbParentsInfer) {
        if (!InvokingOperation.isValidNameIndex(_filter,_param,_name)) {
            return null;
        }
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
        return tryFormatEmp(_param, ind_, _nbParentsInfer);
    }
    private static String tryFormatEmp(Parametrable _param, int _indexChild, int _nbParentsInfer) {
        return InvokingOperation.tryGetParamAny(_param,_indexChild,_nbParentsInfer);
    }
    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        AnaClassArgumentMatching resCh_ = getFirstChild().getResultClass();
        SwitchBlock.processAfterEl(resCh_,switchMethod,_page);
        switchMethod.setResult(AnaClassArgumentMatching.copy(resCh_, _page.getPrimitiveTypes()));
        setResultClass(new AnaClassArgumentMatching(retType));
        SwitchBlock.processChildren(switchMethod,_page);
    }

    public SwitchMethodBlock getSwitchMethod() {
        return switchMethod;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    public AnaArrContent getArrContent() {
        return arrContent;
    }

    public int getDelta() {
        return delta;
    }
}
