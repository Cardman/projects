package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ParsedFctHeaderResult;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.util.*;
import code.util.core.StringUtil;

public final class AnonymousLambdaOperation extends
        LeafOperation {
    private final AnaLambdaCommonContent lambdaCommonContent;
    private MethodId method;
    private final NamedCalledFunctionBlock block;
    private final ParsedFctHeaderResult parse;

    public AnonymousLambdaOperation(int _index, int _indexChild,
                                    MethodOperation _m, OperationsSequence _op, NamedCalledFunctionBlock _block, ParsedFctHeaderResult _parse) {
        super(_index, _indexChild, _m, _op);
        lambdaCommonContent = new AnaLambdaCommonContent();
        block = _block;
        parse = _parse;
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        _page.getAllAnonymousLambda().add(this);
        int relativeOff_ = getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        for (EntryCust<String,AnaLocalVariable> e: _page.getInfosVars().entryList()) {
            block.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getKey(), e.getValue()));
        }
        for (AnaNamedLocalVariable e: _page.getCache().getLocalVariables()) {
            block.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getName(), e.getLocalVariable()));
        }
        for (EntryCust<String,AnaLoopVariable> e: _page.getLoopsVars().entryList()) {
            block.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getKey(),e.getValue()));
        }
        for (AnaNamedLoopVariable e: _page.getCache().getLoopVariables()) {
            block.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getName(),e.getLocalVariable()));
        }
        analyzeCtor(_page);
    }

    private void analyzeCtor(AnalyzedPageEl _page) {
        int offset_ = _page.getOffset();
        int sumOffset_ = _page.getSumOffset();
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        String foundType_ = foundType(_page, par_);
        String type_ = _page.getAliasFct();
        int nbParams_ = parse.getParametersType().size()+1;
        StringBuilder pattern_ = pattern(type_, nbParams_);
        StringList candidates_ = initCandidates(_page, foundType_, pattern_);
        OperationNode m_ = par_.getOperation();
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        completeCandidate(_page, par_, pattern_, candidates_, m_, list_);
        AnaFormattedRootBlock copy_ = AnaFormattedRootBlock.copy(_page.getGlobalType());
        lambdaCommonContent.setFoundFormatted(copy_);
        lambdaCommonContent.setFileName(_page.getLocalizer().getCurrentFileName());
        AccessedBlock acc_ = block.getAccessedBlock();
        block.getAllReservedInners().addAllElts(acc_.getAllReservedInners());
        MemberCallingsBlock currentFct_ = _page.getCurrentFct();
        if (currentFct_ != null) {
            block.getMappings().putAllMap(currentFct_.getRefMappings());
            block.getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            block.getMappings().putAllMap(acc_.getRefMappings());
        }
        AccessedFct imp_ = _page.getAccessedFct();
        if (imp_ != null) {
            imp_.getElements().getElements().getLambdas().add(block);
        }
        boolean built_ = false;
        StringList parTypes_ = parse.getParametersType();
        if (StringUtil.contains(parTypes_,"")||parse.getReturnType().isEmpty()) {
            completeTypes(_page, candidates_);
            built_ = true;
        }
        if (!built_) {
            block.setVarargs(parse
            );
            block.buildInternImportedTypes(_page);
        }
        RootBlock.validateParameters(block, _page, _page.getCurrentFile());
        AbsBk currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock) currentBlock_).getElements().getElements().getLambdas().add(block);
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getElements().getElements().getLambdas().add(block);
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getElementsType().getLambdas().add(block);
        }
        String importedReturnType_ = block.getImportedReturnType();
        MethodId idC_ = block.getId();
        lambdaCommonContent.setReturnFieldType(importedReturnType_);
        String found_ = lambdaCommonContent.getFoundFormatted().getFormatted();
        StringList params_ = AnaInherits.wildCardFormatParams(found_, idC_, _page);
        MethodId id_ = MethodId.to(idC_.getKind(),params_,idC_);
        method = idC_;
        String fct_ = formatReturn(_page, importedReturnType_, found_, id_);
        lambdaCommonContent.setResult(fct_);
        setResultClass(new AnaClassArgumentMatching(fct_));
        _page.setOffset(offset_);
        _page.setSumOffset(sumOffset_);
    }

    private void completeTypes(AnalyzedPageEl _page, StringList _candidates) {
        StringList modifiedArgCandidates_ = modifiedArgCandidates(_page, _candidates);
        StringList feed_ = params(_page, modifiedArgCandidates_);
        String retType_ = retType(_page, modifiedArgCandidates_);
        block.getImportedParametersTypes().clear();
        block.getImportedParametersTypes().addAllElts(feed_);
        block.setImportedReturnType(retType_);
        block.setVarargs(parse
        );
    }

    private StringList params(AnalyzedPageEl _page, StringList _modifiedArgCandidates) {
        StringList parTypes_ = parse.getParametersType();
        StringList feed_ = new StringList();
        Ints offestsTypes_ = parse.getOffestsTypes();
        int len_ = Math.min(parTypes_.size(), _modifiedArgCandidates.size());
        for (int i = 0; i < len_; i++) {
            String before_ = parTypes_.get(i);
            if (before_.isEmpty()) {
                if (_modifiedArgCandidates.get(i).startsWith("~")) {
                    feed_.add(_modifiedArgCandidates.get(i).substring(1));
                } else {
                    feed_.add(_modifiedArgCandidates.get(i));
                }
                block.getPartOffsetsParams().add(new AnaResultPartType());
            } else {
                feed_.add(block.buildInternParam(offestsTypes_.get(i),before_, _page));
            }
        }
        return feed_;
    }

    private StringList modifiedArgCandidates(AnalyzedPageEl _page, StringList _candidates) {
        StringList parTypes_ = parse.getParametersType();
        StringList modifiedArgCandidates_ = new StringList();
        int nbArgs_ = parTypes_.size() + 1;
        for (int i = 0; i < nbArgs_; i++) {
            StringList argCandidates_ = new StringList();
            for (String s: _candidates) {
                StringList allTypes_ = StringExpUtil.getAllTypes(s);
                String arg_ = allTypes_.get(Math.min(i+1,allTypes_.getLastIndex()));
                if (StringUtil.quickEq(arg_,"?")) {
                    continue;
                }
                argCandidates_.add(arg_);
            }
            if (argCandidates_.onlyOneElt()) {
                modifiedArgCandidates_.add(argCandidates_.first());
            } else {
                modifiedArgCandidates_.add(_page.getAliasObject());
            }
        }
        return modifiedArgCandidates_;
    }

    private String retType(AnalyzedPageEl _page, StringList _modifiedArgCandidates) {
        String retType_;
        if (!parse.getReturnType().isEmpty()) {
            String void_ = _page.getAliasVoid();
            if (StringUtil.quickEq(parse.getReturnType().trim(), void_)) {
                retType_ = void_;
            } else {
                retType_ = block.buildInternRet(parse.getReturnOffest(), parse.getReturnType(), _page);
            }
        } else {
            if (_modifiedArgCandidates.last().startsWith("~")) {
                retType_ = _modifiedArgCandidates.last().substring(1);
            } else {
                retType_ = _modifiedArgCandidates.last();
            }
        }
        return retType_;
    }

    private void completeCandidate(AnalyzedPageEl _page, ParentInferring _par, StringBuilder _pattern, StringList _candidates, OperationNode _m, boolean _list) {
        if (_m instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) _m;
            String inferRecord_ = n_.infer();
            if (!inferRecord_.isEmpty()) {
                feedRecordCandidates(_page, _par, _pattern, _candidates, inferRecord_);
            }
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                feedMethodCandidateIndirect(_page, _par, _pattern, _candidates, n_);
            }
            if (call_ instanceof RetrieveConstructor) {
                feedCtorCandidateIndirect(_page, _par, _pattern, _candidates, n_);
            }
        }
        if (_m instanceof RetrieveMethod){
            feedMethodCandidateDirect(_page, _par, _pattern, _candidates, (RetrieveMethod) _m, _list);
        }
        if (_m instanceof RetrieveConstructor){
            feedCtorCandidateDirect(_page, _par, _pattern, _candidates, (RetrieveConstructor) _m, _list);
        }
    }

    private void feedRecordCandidates(AnalyzedPageEl _page, ParentInferring _par, StringBuilder _pattern, StringList _candidates, String _inferRecord) {
        int nbParentsInfer_ = _par.getNbParentsInfer();
        StringMap<String> vars_ = new StringMap<String>();
        String type_ = _page.getAliasFct();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
        mapping_.setParam(_pattern.toString());
        StringList format_ = InvokingOperation.tryFormatFctRec(_inferRecord, nbParentsInfer_, type_, _pattern.toString(), vars_, _page);
        for (String c: format_) {
            mapping_.setArg(c);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                continue;
            }
            _candidates.add(c);
        }
    }

    private void feedMethodCandidateIndirect(AnalyzedPageEl _page, ParentInferring _par, StringBuilder _pattern, StringList _candidates, NamedArgumentOperation _n) {
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String name_ = _n.getName();
        MethodOperation call_ = _n.getParent();
        StringMap<String> vars_ = new StringMap<String>();
        String type_ = _page.getAliasFct();
        RetrieveMethod f_ = (RetrieveMethod) call_;
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
        mapping_.setParam(_pattern.toString());
        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page, call_);
        CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
        int len_ = methodInfos_.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos_.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                if (InvokingOperation.isValidNameIndex(filter_,methodInfo_, name_)) {
                    newList_.add(methodInfo_);
                }
                StringList format_ = InvokingOperation.tryParamFormatFct(filter_,methodInfo_, name_, nbParentsInfer_, type_, _pattern.toString(), vars_, _page);
                for (String c: format_) {
                    mapping_.setArg(c);
                    if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                        continue;
                    }
                    _candidates.add(c);
                }
            }
            methodInfos_.set(i,newList_);
        }
    }

    private void feedCtorCandidateIndirect(AnalyzedPageEl _page, ParentInferring _par, StringBuilder _pattern, StringList _candidates, NamedArgumentOperation _n) {
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String name_ = _n.getName();
        MethodOperation call_ = _n.getParent();
        StringMap<String> vars_ = new StringMap<String>();
        String type_ = _page.getAliasFct();
        RetrieveConstructor f_ = (RetrieveConstructor) call_;
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
        mapping_.setParam(_pattern.toString());
        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page, call_);
        CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
        int len_ = methodInfos_.size();
        CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
        for (int i = 0; i < len_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            if (InvokingOperation.isValidNameIndex(filter_,methodInfo_, name_)) {
                newList_.add(methodInfo_);
            }
            StringList format_ = InvokingOperation.tryParamFormatFct(filter_,methodInfo_, name_, nbParentsInfer_, type_, _pattern.toString(), vars_, _page);
            for (String c: format_) {
                mapping_.setArg(c);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    continue;
                }
                _candidates.add(c);
            }
        }
        methodInfos_.clear();
        methodInfos_.addAllElts(newList_);
    }

    private void feedMethodCandidateDirect(AnalyzedPageEl _page, ParentInferring _par, StringBuilder _pattern, StringList _candidates, RetrieveMethod _m, boolean _list) {
        StringMap<String> vars_ = new StringMap<String>();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String type_ = _page.getAliasFct();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
        mapping_.setParam(_pattern.toString());
        OperationNode firstChild_ = _m.getFirstChild();
        int deltaCount_ = InvokingOperation.getDeltaCount(_list,firstChild_);
        int indexChild_ = _par.getOperationChild().getIndexChild()-deltaCount_;
        CustList<CustList<MethodInfo>> methodInfos_ = _m.getMethodInfos();
        int len_ = methodInfos_.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos_.get(i).size();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                StringList format_ = InvokingOperation.tryFormatFct(methodInfo_, indexChild_, nbParentsInfer_, type_, _pattern.toString(), vars_, _page);
                for (String c: format_) {
                    mapping_.setArg(c);
                    if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                        continue;
                    }
                    _candidates.add(c);
                }
            }
        }
    }

    private void feedCtorCandidateDirect(AnalyzedPageEl _page, ParentInferring _par, StringBuilder _pattern, StringList _candidates, RetrieveConstructor _m, boolean _list) {
        StringMap<String> vars_ = new StringMap<String>();
        String type_ = _page.getAliasFct();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
        mapping_.setParam(_pattern.toString());
        OperationNode firstChild_ = _m.getFirstChild();
        int deltaCount_ = InvokingOperation.getDeltaCount(_list,firstChild_);
        int indexChild_ = _par.getOperationChild().getIndexChild()-deltaCount_;
        CustList<ConstructorInfo> methodInfos_ = _m.getCtors();
        int len_ = methodInfos_.size();
        for (int i = 0; i < len_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            StringList format_ = InvokingOperation.tryFormatFct(methodInfo_, indexChild_, nbParentsInfer_, type_, _pattern.toString(), vars_, _page);
            for (String c: format_) {
                mapping_.setArg(c);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    continue;
                }
                _candidates.add(c);
            }
        }
    }

    private StringBuilder pattern(String _type, int _nbParams) {
        StringBuilder pattern_ = new StringBuilder(_type);
        StringList wc_ = new StringList();
        for (int i = 0; i < _nbParams; i++) {
            wc_.add("?");
        }
        pattern_.append('<').append(StringUtil.join(wc_,',')).append('>');
        return pattern_;
    }

    private StringList initCandidates(AnalyzedPageEl _page, String _foundType, StringBuilder _pattern) {
        String type_ = _page.getAliasFct();
        StringList candidates_ = new StringList();
        if (!_foundType.isEmpty()) {
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(_pattern.toString());
            mapping_.setArg(_foundType);
            StringList conv_ = InvokingOperation.tryInferOrImplicitFct(type_, _pattern.toString(), new StringMap<String>(), _page, _foundType);
            for (String c: conv_) {
                mapping_.setArg(c);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    continue;
                }
                candidates_.add(c);
            }
        }
        return candidates_;
    }

    private String foundType(AnalyzedPageEl _page, ParentInferring _par) {
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String typeAff_ = typeAff(_page, _par);
        String keyWordVar_ = _page.getKeyWords().getKeyWordVar();
        String foundType_ = EMPTY_STRING;
        if (!InvokingOperation.isUndefined(typeAff_, keyWordVar_)) {
            String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
            if (!InvokingOperation.isNotCorrectDim(cp_)){
                foundType_ = cp_;
            }
        }
        return foundType_;
    }

    static String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        OperationNode m_ = _par.getOperation();
        String typeAff_;
        AbsBk cur_ = _page.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = InvokingOperation.tryGetRetType(_page);
        } else {
            typeAff_ = InvokingOperation.tryGetTypeAff(m_, _par.getOperationChild().getIndexChild());
        }
        return typeAff_;
    }

    private static String formatReturn(AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _constraints) {
        return LambdaOperation.appendParts(_page, _returnType, _realClass, _constraints);
    }
    public NamedCalledFunctionBlock getBlock() {
        return block;
    }

    public AnaLambdaCommonContent getLambdaCommonContent() {
        return lambdaCommonContent;
    }

    public MethodId getMethod() {
        return method;
    }

}
