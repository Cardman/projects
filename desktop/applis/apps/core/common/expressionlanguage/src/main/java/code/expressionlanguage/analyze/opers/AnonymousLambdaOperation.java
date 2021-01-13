package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.files.ParsedFctHeader;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaLambdaAnoContent;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.util.*;
import code.util.core.StringUtil;

public final class AnonymousLambdaOperation extends
        LeafOperation {
    private final AnaLambdaCommonContent lambdaCommonContent;
    private final AnaLambdaAnoContent lambdaAnoContent;
    private final AnonymousFunctionBlock block;
    private final ParsedFctHeader parse;

    public AnonymousLambdaOperation(int _index, int _indexChild,
                                    MethodOperation _m, OperationsSequence _op, AnonymousFunctionBlock _block, ParsedFctHeader _parse) {
        super(_index, _indexChild, _m, _op);
        lambdaCommonContent = new AnaLambdaCommonContent();
        lambdaAnoContent = new AnaLambdaAnoContent();
        block = _block;
        parse = _parse;
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        _page.getAnonymousLambda().last().add(this);
        _page.getAllAnonymousLambda().add(this);
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
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
        int globalOffset_ = _page.getGlobalOffset();
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String typeAff_;
        Block cur_ = _page.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = InvokingOperation.tryGetRetType(_page);
        } else {
            typeAff_ = InvokingOperation.tryGetTypeAff(m_);
        }
        String keyWordVar_ = _page.getKeyWords().getKeyWordVar();
        String foundType_ = EMPTY_STRING;
        if (!InvokingOperation.isUndefined(typeAff_, keyWordVar_)) {
            String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
            if (!InvokingOperation.isNotCorrectDim(cp_)){
                foundType_ = cp_;
            }
        }
        String type_ = _page.getAliasFct();
        StringMap<String> vars_ = new StringMap<String>();
        int nbParams_ = parse.getParametersType().size()+1;
        StringBuilder pattern_ = new StringBuilder(type_);
        StringList wc_ = new StringList();
        for (int i = 0; i < nbParams_; i++) {
            wc_.add("?");
        }
        pattern_.append('<').append(StringUtil.join(wc_,',')).append('>');
        StringList candidates_ = new StringList();
        if (!foundType_.isEmpty()) {
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(pattern_.toString());
            mapping_.setArg(foundType_);
            if (AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                candidates_.add(foundType_);
            }
        }
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                RetrieveMethod f_ = (RetrieveMethod) call_;
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                mapping_.setParam(pattern_.toString());
                NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(call_);
                CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                int len_ = methodInfos_.size();
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        if (InvokingOperation.isValidNameIndex(filter_,methodInfo_,name_)) {
                            newList_.add(methodInfo_);
                        }
                        String format_ = InvokingOperation.tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _page);
                        if (format_ == null) {
                            continue;
                        }
                        mapping_.setArg(format_);
                        if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                            continue;
                        }
                        candidates_.add(format_);
                    }
                    methodInfos_.set(i,newList_);
                }
            }
            if (call_ instanceof RetrieveConstructor) {
                RetrieveConstructor f_ = (RetrieveConstructor) call_;
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                mapping_.setParam(pattern_.toString());
                NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(call_);
                CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                int len_ = methodInfos_.size();
                CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                for (int i = 0; i < len_; i++) {
                    ConstructorInfo methodInfo_ = methodInfos_.get(i);
                    if (InvokingOperation.isValidNameIndex(filter_,methodInfo_,name_)) {
                        newList_.add(methodInfo_);
                    }
                    String format_ = InvokingOperation.tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _page);
                    if (format_ == null) {
                        continue;
                    }
                    mapping_.setArg(format_);
                    if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                        continue;
                    }
                    candidates_.add(format_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
            }
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(pattern_.toString());
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = InvokingOperation.getDeltaCount(list_,firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    String format_ = InvokingOperation.tryFormat(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _page);
                    if (format_ == null) {
                        continue;
                    }
                    mapping_.setArg(format_);
                    if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                        continue;
                    }
                    candidates_.add(format_);
                }
            }
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(pattern_.toString());
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = InvokingOperation.getDeltaCount(list_,firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                String format_ = InvokingOperation.tryFormat(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _page);
                if (format_ == null) {
                    continue;
                }
                mapping_.setArg(format_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    continue;
                }
                candidates_.add(format_);
            }
        }
        lambdaCommonContent.setFoundClass(_page.getGlobalClass());
        lambdaCommonContent.setFileName(_page.getLocalizer().getCurrentFileName());
        RootBlock globalType_ = _page.getGlobalType();
        lambdaAnoContent.setRootNumber(globalType_.getNumberAll());
        block.getAllReservedInners().addAllElts(_page.getGlobalType().getAllReservedInners());
        MemberCallingsBlock currentFct_ = _page.getCurrentFct();
        if (currentFct_ != null) {
            currentFct_.getAnonymousFct().add(block);
            block.getMappings().putAllMap(currentFct_.getMappings());
            block.getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            block.getMappings().putAllMap(_page.getGlobalType().getMappings());
        }
        boolean built_ = false;
        StringList parTypes_ = parse.getParametersType();
        if (StringUtil.contains(parTypes_,"")||parse.getReturnType().isEmpty()) {
            StringList modifiedArgCandidates_ = new StringList();
            int nbArgs_ = parTypes_.size() + 1;
            for (int i = 0; i < nbArgs_; i++) {
                StringList argCandidates_ = new StringList();
                for (String s: candidates_) {
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
            StringList feed_ = new StringList();
            Ints offestsTypes_ = parse.getOffestsTypes();
            int len_ = Math.min(parTypes_.size(),modifiedArgCandidates_.size());
            for (int i = 0; i < len_; i++) {
                String before_ = parTypes_.get(i);
                if (before_.isEmpty()) {
                    if (modifiedArgCandidates_.get(i).startsWith("~")) {
                        feed_.add(modifiedArgCandidates_.get(i).substring(1));
                    } else {
                        feed_.add(modifiedArgCandidates_.get(i));
                    }
                    block.getPartOffsetsParams().add(new CustList<PartOffset>());
                } else {
                    feed_.add(block.buildInternParam(offestsTypes_.get(i),before_, _page));
                }
            }
            String retType_;
            if (!parse.getReturnType().isEmpty()) {
                String void_ = _page.getAliasVoid();
                if (StringUtil.quickEq(parse.getReturnType().trim(), void_)) {
                    retType_ = void_;
                } else {
                    retType_ = block.buildInternRet(parse.getReturnOffest(), parse.getReturnType(), _page);
                }
            } else {
                if (modifiedArgCandidates_.last().startsWith("~")) {
                    retType_ = modifiedArgCandidates_.last().substring(1);
                } else {
                    retType_ = modifiedArgCandidates_.last();
                }
            }
            block.getImportedParametersTypes().clear();
            block.getImportedParametersTypes().addAllElts(feed_);
            block.setImportedReturnType(retType_);
            block.setVarargs(parse.getParametersType(),parse.getOffestsTypes(),
                    parse.getParametersName(),parse.getOffestsParams(),
                    parse.getParametersRef(),parse.isRetRef(), parse.getReturnType(), parse.getReturnOffest());
            built_ = true;
        }
        if (!built_) {
            block.setVarargs(parse.getParametersType(), parse.getOffestsTypes(),
                    parse.getParametersName(), parse.getOffestsParams(),
                    parse.getParametersRef(),parse.isRetRef(), parse.getReturnType(), parse.getReturnOffest());
            block.buildInternImportedTypes(_page);
        }
        globalType_.validateParameters(block, _page);
        Block currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getAnonymousFct().add(block);
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getAnonymousFct().add(block);
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getAnonymousRootFct().add(block);
        }
        String importedReturnType_ = block.getImportedReturnType();
        MethodId idC_ = block.getId();
        lambdaCommonContent.setReturnFieldType(importedReturnType_);
        String found_ = lambdaCommonContent.getFoundClass();
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setClassName(found_);
        mloc_.setConstraints(idC_);
        mloc_.format(idC_.getKind() == MethodAccessKind.STATIC, _page);
        MethodId id_ = mloc_.getFormatted();
        String foundClass_ = found_;
        if (idC_.getKind() != MethodAccessKind.STATIC_CALL) {
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        }
        lambdaAnoContent.setMethod(new ClassMethodId(foundClass_, idC_));
        String fct_ = LambdaOperation.formatReturn(_page, importedReturnType_, found_, idC_, id_);
        setResultClass(new AnaClassArgumentMatching(fct_));
        _page.setOffset(offset_);
        _page.setGlobalOffset(globalOffset_);
    }

    public AnonymousFunctionBlock getBlock() {
        return block;
    }

    public int getRootNumber() {
        return lambdaAnoContent.getRootNumber();
    }

    public AnaLambdaCommonContent getLambdaCommonContent() {
        return lambdaCommonContent;
    }

    public AnaLambdaAnoContent getLambdaAnoContent() {
        return lambdaAnoContent;
    }
}
