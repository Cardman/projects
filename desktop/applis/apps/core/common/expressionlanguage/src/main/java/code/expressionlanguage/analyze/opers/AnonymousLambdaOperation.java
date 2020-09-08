package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.files.ParsedFctHeader;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.util.*;

public final class AnonymousLambdaOperation extends
        LeafOperation {
    private ClassMethodId method;
    private String foundClass;
    private String fileName;
    private String returnFieldType = "";
    private AnonymousFunctionBlock block;
    private ParsedFctHeader parse;
    private int rootNumber = -1;

    public AnonymousLambdaOperation(int _index, int _indexChild,
                                    MethodOperation _m, OperationsSequence _op, AnonymousFunctionBlock _block, ParsedFctHeader _parse) {
        super(_index, _indexChild, _m, _op);
        block = _block;
        parse = _parse;
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        for (EntryCust<String,AnaLocalVariable> e: _conf.getAnalyzing().getInfosVars().entryList()) {
            block.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getKey(),e.getValue()));
        }
        for (AnaNamedLocalVariable e: _conf.getAnalyzing().getCache().getLocalVariables()) {
            block.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getName(),e.getLocalVariable()));
        }
        for (EntryCust<String,AnaLoopVariable> e: _conf.getAnalyzing().getLoopsVars().entryList()) {
            block.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getKey(),e.getValue()));
        }
        for (AnaNamedLoopVariable e: _conf.getAnalyzing().getCache().getLoopVariables()) {
            block.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getName(),e.getLocalVariable()));
        }
        analyzeCtor(_conf);
    }
    private void analyzeCtor(ContextEl _conf) {
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String typeAff_;
        AnalyzedBlock cur_ = _conf.getAnalyzing().getCurrentAnaBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = InvokingOperation.tryGetRetType(_conf);
        } else {
            typeAff_ = InvokingOperation.tryGetTypeAff(m_);
        }
        String keyWordVar_ = _conf.getKeyWords().getKeyWordVar();
        String foundType_ = EMPTY_STRING;
        if (!InvokingOperation.isUndefined(typeAff_, keyWordVar_)) {
            String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
            if (!InvokingOperation.isNotCorrectDim(cp_)){
                foundType_ = cp_;
            }
        }
        String type_ = _conf.getStandards().getAliasFct();
        StringMap<String> vars_ = new StringMap<String>();
        int nbParams_ = parse.getParametersType().size()+1;
        StringBuilder pattern_ = new StringBuilder(type_);
        StringList wc_ = new StringList();
        for (int i = 0; i < nbParams_; i++) {
            wc_.add("?");
        }
        pattern_.append('<').append(StringList.join(wc_,',')).append('>');
        StringList candidates_ = new StringList();
        if (!foundType_.isEmpty()) {
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(pattern_.toString());
            mapping_.setArg(foundType_);
            if (AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                candidates_.add(foundType_);
            }
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                RetrieveMethod f_ = (RetrieveMethod) call_;
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
                mapping_.setParam(pattern_.toString());
                NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(call_);
                CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                int len_ = methodInfos_.size();
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        String format_ = InvokingOperation.tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _conf);
                        if (format_ == null) {
                            continue;
                        }
                        mapping_.setArg(format_);
                        if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                            continue;
                        }
                        candidates_.add(format_);
                    }
                }
            }
            if (call_ instanceof RetrieveConstructor) {
                RetrieveConstructor f_ = (RetrieveConstructor) call_;
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
                mapping_.setParam(pattern_.toString());
                NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(call_);
                CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                int len_ = methodInfos_.size();
                for (int i = 0; i < len_; i++) {
                    ConstructorInfo methodInfo_ = methodInfos_.get(i);
                    String format_ = InvokingOperation.tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _conf);
                    if (format_ == null) {
                        continue;
                    }
                    mapping_.setArg(format_);
                    if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                        continue;
                    }
                    candidates_.add(format_);
                }
            }
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(pattern_.toString());
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = InvokingOperation.getDeltaCount(firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    String format_ = InvokingOperation.tryFormat(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _conf);
                    if (format_ == null) {
                        continue;
                    }
                    mapping_.setArg(format_);
                    if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                        continue;
                    }
                    candidates_.add(format_);
                }
            }
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(pattern_.toString());
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = InvokingOperation.getDeltaCount(firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                String format_ = InvokingOperation.tryFormat(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _conf);
                if (format_ == null) {
                    continue;
                }
                mapping_.setArg(format_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                    continue;
                }
                candidates_.add(format_);
            }
        }
        foundClass = _conf.getAnalyzing().getGlobalClass();
        fileName = _conf.getAnalyzing().getLocalizer().getCurrentFileName();
        RootBlock globalType_ = _conf.getAnalyzing().getGlobalType();
        rootNumber = globalType_.getNumberAll();
        block.setParentType(globalType_);
        block.getAllReservedInners().addAllElts(_conf.getAnalyzing().getGlobalDirType().getAllReservedInners());
        MemberCallingsBlock currentFct_ = _conf.getAnalyzing().getCurrentFct();
        if (currentFct_ != null) {
            currentFct_.getAnonymousFct().add(block);
            block.getMappings().putAllMap(currentFct_.getMappings());
            block.getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            block.getMappings().putAllMap(_conf.getAnalyzing().getGlobalDirType().getMappings());
        }
        boolean built_ = false;
        StringList parTypes_ = parse.getParametersType();
        if (StringList.contains(parTypes_,"")||parse.getReturnType().isEmpty()) {
            StringList modifiedArgCandidates_ = new StringList();
            int nbArgs_ = parTypes_.size() + 1;
            for (int i = 0; i < nbArgs_; i++) {
                StringList argCandidates_ = new StringList();
                for (String s: candidates_) {
                    StringList allTypes_ = StringExpUtil.getAllTypes(s);
                    String arg_ = allTypes_.get(Math.min(i+1,allTypes_.getLastIndex()));
                    if (StringList.quickEq(arg_,"?")) {
                        continue;
                    }
                    argCandidates_.add(arg_);
                }
                if (argCandidates_.onlyOneElt()) {
                    modifiedArgCandidates_.add(argCandidates_.first());
                } else {
                    modifiedArgCandidates_.add(_conf.getStandards().getAliasObject());
                }
            }
            StringList feed_ = new StringList();
            Ints offestsTypes_ = parse.getOffestsTypes();
            int len_ = Math.min(parTypes_.size(),modifiedArgCandidates_.size());
            for (int i = 0; i < len_; i++) {
                String before_ = parTypes_.get(i);
                if (before_.isEmpty()) {
                    feed_.add(modifiedArgCandidates_.get(i));
                    block.getPartOffsetsParams().add(new CustList<PartOffset>());
                } else {
                    feed_.add(block.buildInternParam(_conf,offestsTypes_.get(i),before_));
                }
            }
            String retType_;
            if (!parse.getReturnType().isEmpty()) {
                retType_ = block.buildInternRet(_conf,parse.getReturnOffest(),parse.getReturnType());
            } else {
                retType_ = modifiedArgCandidates_.last();
            }
            block.getImportedParametersTypes().clear();
            block.getImportedParametersTypes().addAllElts(feed_);
            block.setImportedReturnType(retType_);
            block.setVarargs(parse.getParametersType(),parse.getOffestsTypes(),
                    parse.getParametersName(),parse.getOffestsParams(),
                    parse.getReturnType(), parse.getReturnOffest());
            built_ = true;
        }
        if (!built_) {
            block.setVarargs(parse.getParametersType(), parse.getOffestsTypes(),
                    parse.getParametersName(), parse.getOffestsParams(),
                    parse.getReturnType(), parse.getReturnOffest());
            block.buildInternImportedTypes(_conf);
        }
        globalType_.validateParameters(_conf,block);
        Block currentBlock_ = _conf.getAnalyzing().getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getAnonymousFct().add(block);
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getAnonymousFct().add(block);
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getAnonymousRootFct().add(block);
        }
        String importedReturnType_ = block.getImportedReturnType();
        ParametersGroup p_ = new ParametersGroup();
        MethodId idC_ = block.getId();
        MethodInfo mloc_ = new MethodInfo();
        returnFieldType = importedReturnType_;
        mloc_.setOriginalReturnType(importedReturnType_);
        mloc_.setClassName(foundClass);
        mloc_.setConstraints(idC_);
        mloc_.setParameters(p_);
        mloc_.setReturnType(importedReturnType_);
        mloc_.format(idC_.getKind() == MethodAccessKind.STATIC,_conf);
        MethodId constraints_ = mloc_.getConstraints();
        String baseClassName_ = mloc_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = mloc_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(mloc_.getReturnType());
        res_.setOriginalReturnType(mloc_.getOriginalReturnType());
        res_.setFileName(mloc_.getFileName());
        res_.setAncestor(mloc_.getAncestor());
        res_.setAbstractMethod(mloc_.isAbstractMethod());
        res_.setStaticMethod(block.isStaticMethod());
        String foundClass_ = res_.getRealClass();
        MethodId idCt_ = res_.getRealId();
        if (idCt_.getKind() != MethodAccessKind.STATIC_CALL) {
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        }
        foundClass = res_.getId().getClassName();
        method = new ClassMethodId(foundClass_, idCt_);
        String fct_ = LambdaOperation.formatReturn(foundClass, _conf, res_, false);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public AnonymousFunctionBlock getBlock() {
        return block;
    }

    public String getFileName() {
        return fileName;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public String getFoundClass() {
        return foundClass;
    }
}
