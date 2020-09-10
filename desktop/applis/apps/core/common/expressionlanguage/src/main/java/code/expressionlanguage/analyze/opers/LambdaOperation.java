package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MethodAccessId;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class LambdaOperation extends LeafOperation implements PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;

    private String className;
    private int offset;

    private ClassMethodId method;
    private String foundClass;
    private int ancestor;
    private boolean directCast;
    private boolean expCast;
    private boolean shiftArgument;
    private boolean polymorph;
    private boolean abstractMethod;
    private ConstructorId realId;
    private ClassField fieldId;
    private boolean staticField;
    private boolean finalField;
    private boolean affField;
    private boolean safeInstance;
    private int valueOffset;
    private String fileName = "";
    private String returnFieldType = "";
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private CustList<PartOffset> partOffsetsEnd = new CustList<PartOffset>();
    private int rootNumber = -1;
    private int memberNumber = -1;
    private int operatorNumber = -1;
    private StandardMethod standardMethod;

    public LambdaOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        offset = _op.getValues().firstKey();
        className = _op.getValues().firstValue();
        previousResultClass = new ClassArgumentMatching(EMPTY_STRING);
    }

    @Override
    public void analyze(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        returnFieldType = stds_.getAliasObject();
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_);
        int len_ = args_.size();
        if (len_ < 2) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //last parenthesis
            badCall_.buildError(_conf.getAnalysisMessages().getSplitComa(),
                    Integer.toString(2),
                    Integer.toString(len_)
            );
            _conf.getAnalyzing().getLocalizer().addError(badCall_);
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsetsEnd.add(new PartOffset("</a>",i_+1));
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        generalProcess(_conf, args_);
    }

    private void generalProcess(ContextEl _conf, StringList _args) {
        if (isIntermediateDottedOperation()&&getParent() instanceof SafeDotOperation) {
            safeInstance = true;
        }
        LgNames stds_ = _conf.getStandards();
        int len_ = _args.size();
        MethodOperation m_ = getParent();
        String fromType_ = StringExpUtil.removeDottedSpaces(_args.first());
        CustList<ClassArgumentMatching> methodTypes_ = new CustList<ClassArgumentMatching>();
        KeyWords keyWords_ = _conf.getKeyWords();
        String operator_ = keyWords_.getKeyWordOperator();
        String new_ = keyWords_.getKeyWordNew();
        if (StringList.quickEq(fromType_, operator_)) {
            processOperator(_conf, stds_, _args, len_, methodTypes_);
            return;
        }
        String name_ = _args.get(1).trim();
        if (name_.isEmpty()) {
            processField(_conf, stds_, m_, _args, len_, fromType_);
            return;
        }
        if (StringList.quickEq(name_, new_)) {
            processInstance(_conf, stds_, _args, len_, fromType_, methodTypes_);
            return;
        }
        processMethod(_conf, stds_, m_, _args, len_, fromType_, methodTypes_,
                name_);
    }

    private void processMethod(ContextEl _conf, LgNames _stds,
            MethodOperation _m, StringList _args, int _len, String _fromType,
            CustList<ClassArgumentMatching> _methodTypes, String _name) {
        StringList str_;
        String name_ = _name;
        if (StringList.quickEq(name_,_conf.getKeyWords().getKeyWordExplicit())
                || StringList.quickEq(name_,_conf.getKeyWords().getKeyWordCast())
                || StringList.quickEq(name_,_conf.getKeyWords().getKeyWordTrue())
                || StringList.quickEq(name_,_conf.getKeyWords().getKeyWordFalse())) {
            String exp_;
            if (StringList.quickEq(name_,_conf.getKeyWords().getKeyWordExplicit())){
                exp_ = _conf.getKeyWords().getKeyWordExplicit();
            } else if (StringList.quickEq(name_,_conf.getKeyWords().getKeyWordCast())) {
                exp_ = _conf.getKeyWords().getKeyWordCast();
            } else if (StringList.quickEq(name_,_conf.getKeyWords().getKeyWordTrue())){
                exp_ = _conf.getKeyWords().getKeyWordTrue();
            } else {
                exp_ = _conf.getKeyWords().getKeyWordFalse();
            }
            int i_ = 2;
            ClassMethodId feed_ = null;
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordId_ = keyWords_.getKeyWordId();
            int offset_ = className.indexOf('(')+1;
            offset_ += StringExpUtil.getOffset(_args.first());
            if (StringList.quickEq(name_,_conf.getKeyWords().getKeyWordTrue())
                || StringList.quickEq(name_,_conf.getKeyWords().getKeyWordFalse())) {
                String type_ = ResolvingImportTypes.resolveCorrectTypeAccessible(_conf,offset_,_fromType);
                partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
                ClassMethodIdReturn resMethod_;
                if (StringList.quickEq(name_,_conf.getKeyWords().getKeyWordTrue())){
                    resMethod_ = tryGetDeclaredTrue(_conf, type_);
                } else {
                    resMethod_ = tryGetDeclaredFalse(_conf, type_);
                }
                if (!resMethod_.isFoundMethod()) {
                    int rc_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + offset_;
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    un_.setIndexFile(rc_);
                    //_in len
                    un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                            type_);
                    _conf.getAnalyzing().getLocalizer().addError(un_);
                    getErrs().add(un_.getBuiltError());
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                expCast = true;
                returnFieldType = resMethod_.getOriginalReturnType();
                fileName = resMethod_.getFileName();
                rootNumber = resMethod_.getRootNumber();
                memberNumber = resMethod_.getMemberNumber();
                String foundClass_ = resMethod_.getRealClass();
                foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
                foundClass = resMethod_.getId().getClassName();
                MethodId idCt_ = resMethod_.getRealId();
                method = new ClassMethodId(foundClass_, idCt_);
                ancestor = resMethod_.getAncestor();
                abstractMethod = resMethod_.isAbstractMethod();
                shiftArgument = false;
                String fct_ = formatReturn(foundClass, _conf, resMethod_, false);
                setResultClass(new ClassArgumentMatching(fct_));
                return;
            }
            String type_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_,_fromType);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            MethodId argsRes_;
            if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
                String cl_ = StringExpUtil.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(i_+1, _conf, cl_, MethodAccessKind.STATIC, _args);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                AnaGeneType geneType_ = _conf.getAnalyzing().getAnaGeneType(_conf,StringExpUtil.getIdFromAllTypes(type_));
                if (geneType_ == null) {
                    int rc_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + offset_;
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    un_.setIndexFile(rc_);
                    //_in len
                    un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                            type_);
                    _conf.getAnalyzing().getLocalizer().addError(un_);
                    getErrs().add(un_.getBuiltError());
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                String gene_ = geneType_.getGenericString();
                if (params_.size() < 2) {
                    params_.add(0, gene_);
                }
                feed_ = new ClassMethodId(type_, new MethodId(MethodAccessKind.STATIC, name_, params_, varargFct_));
                for (String s: argsRes_.getParametersTypes()) {
                    String format_ = AnaTemplates.wildCardFormatParam(type_, s, _conf);
                    if (format_.isEmpty()) {
                        ClassMethodIdReturn idDef_ = new ClassMethodIdReturn(true);
                        MethodId idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_stds.getAliasObject()));
                        idDef_.setId(new ClassMethodId(type_, idCast_));
                        idDef_.setRealId(idCast_);
                        idDef_.setRealClass(type_);
                        idDef_.setReturnType(type_);
                        idDef_.setStaticMethod(true);
                        foundClass = type_;
                        MethodId idCt_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_stds.getAliasObject()));
                        method = new ClassMethodId(type_, idCt_);
                        ancestor = idDef_.getAncestor();
                        abstractMethod = idDef_.isAbstractMethod();
                        shiftArgument = false;
                        directCast = true;
                        String fct_ = formatReturn(foundClass, _conf, idDef_, false);
                        setResultClass(new ClassArgumentMatching(fct_));
                        return;
                    }
                    _methodTypes.add(new ClassArgumentMatching(format_));
                }
            } else {
                argsRes_ = resolveArguments(i_, _conf, _args);
                if (argsRes_ == null) {
                    return;
                }
                for (String s: argsRes_.getParametersTypes()) {
                    _methodTypes.add(new ClassArgumentMatching(s));
                }
            }
            if (argsRes_.getParametersTypes().size() > 2) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_conf.getAnalysisMessages().getSplitComaLow(),
                        Integer.toString(2),
                        Integer.toString(argsRes_.getParametersTypes().size())
                );
                _conf.getAnalyzing().getLocalizer().addError(static_);
                getErrs().add(static_.getBuiltError());
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            if (argsRes_.getParametersTypes().size() > 1 && feed_ == null) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_conf.getAnalysisMessages().getSplitComaLow(),
                        Integer.toString(1),
                        Integer.toString(argsRes_.getParametersTypes().size())
                );
                _conf.getAnalyzing().getLocalizer().addError(static_);
                getErrs().add(static_.getBuiltError());
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            if (!ExplicitOperation.customCast(type_)) {
                ClassMethodIdReturn idDef_ = new ClassMethodIdReturn(true);
                MethodId idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_stds.getAliasObject()));
                idDef_.setId(new ClassMethodId(type_, idCast_));
                idDef_.setRealId(idCast_);
                idDef_.setRealClass(type_);
                idDef_.setReturnType(type_);
                idDef_.setStaticMethod(true);
                foundClass = type_;
                MethodId idCt_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_stds.getAliasObject()));
                method = new ClassMethodId(type_, idCt_);
                ancestor = idDef_.getAncestor();
                abstractMethod = idDef_.isAbstractMethod();
                shiftArgument = false;
                directCast = true;
                String fct_ = formatReturn(foundClass, _conf, idDef_, false);
                setResultClass(new ClassArgumentMatching(fct_));
                return;
            }
            if (_methodTypes.size() < 2) {
                _methodTypes.add(0, new ClassArgumentMatching(type_));
            }
            ClassMethodIdReturn id_;
            if (StringList.quickEq(name_,_conf.getKeyWords().getKeyWordExplicit())){
                id_ = tryGetDeclaredCast(_conf,type_, feed_, OperationNode.toArgArray(_methodTypes));
            } else {
                id_ = tryGetDeclaredImplicitCast(_conf,type_, feed_, OperationNode.toArgArray(_methodTypes));
            }
            if (!id_.isFoundMethod()) {
                ClassMethodIdReturn idDef_ = new ClassMethodIdReturn(true);
                MethodId idCast_;
                if (argsRes_.getParametersTypes().isEmpty()) {
                    idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_stds.getAliasObject()));
                } else {
                    idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(argsRes_.getParametersTypes()));
                }
                idDef_.setId(new ClassMethodId(type_, idCast_));
                idDef_.setRealId(idCast_);
                idDef_.setRealClass(type_);
                idDef_.setReturnType(type_);
                idDef_.setStaticMethod(true);
                foundClass = type_;
                MethodId idCt_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_stds.getAliasObject()));
                method = new ClassMethodId(type_, idCt_);
                ancestor = idDef_.getAncestor();
                abstractMethod = idDef_.isAbstractMethod();
                shiftArgument = false;
                directCast = true;
                String fct_ = formatReturn(foundClass, _conf, idDef_, false);
                setResultClass(new ClassArgumentMatching(fct_));
                return;
            }
            expCast = true;
            returnFieldType = id_.getOriginalReturnType();
            fileName = id_.getFileName();
            rootNumber = id_.getRootNumber();
            memberNumber = id_.getMemberNumber();
            String foundClass_ = id_.getRealClass();
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            foundClass = id_.getId().getClassName();
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            abstractMethod = id_.isAbstractMethod();
            shiftArgument = false;
            String fct_ = formatReturn(foundClass, _conf, id_, false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        if (!isIntermediateDottedOperation()) {
            int i_ = 2;
            boolean staticChoiceMethod_ = false;
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordSuper_ = keyWords_.getKeyWordSuper();
            String keyWordThat_ = keyWords_.getKeyWordThat();
            String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
            String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
            String keyWordId_ = keyWords_.getKeyWordId();
            if (i_ < _len && StringList.quickEq(name_, keyWordSuper_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessFromSuper_ = true;
            } else if (i_ < _len && StringList.quickEq(name_, keyWordThat_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else if (i_ < _len && StringList.quickEq(name_, keyWordClasschoice_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessSuper_ = false;
            } else if (i_ < _len && StringList.quickEq(name_, keyWordSuperaccess_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else {
                polymorph = true;
            }
            int vararg_ = -1;
            MethodId argsRes_;
            ClassMethodIdAncestor feed_ = null;
            if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
                MethodAccessId idUpdate_ = new MethodAccessId(i_);
                String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
                String keyWordStaticCall_ = _conf.getKeyWords().getKeyWordStaticCall();
                idUpdate_.setupInfos(i_ + 1,_args,keyWordStatic_,keyWordStaticCall_);
                MethodAccessKind staticFlag_ = idUpdate_.getKind();
                i_ = idUpdate_.getIndex();
                int offset_ = className.indexOf('(')+1;
                offset_ += StringExpUtil.getOffset(_args.first());
                String type_ = ResolvingImportTypes.resolveCorrectType(_conf, offset_, _fromType, staticFlag_ != MethodAccessKind.STATIC);
                partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
                str_ = InvokingOperation.getBounds(type_, _conf);
                String cl_ = StringExpUtil.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(i_+1, _conf, cl_, staticFlag_, _args);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                feed_ = new ClassMethodIdAncestor(new ClassMethodId(cl_, new MethodId(staticFlag_, name_, params_, varargFct_)),idUpdate_.getAncestor());
                for (String s: argsRes_.getParametersTypes()) {
                    String format_ = AnaTemplates.wildCardFormatParam(type_, s, _conf);
                    if (format_.isEmpty()) {
                        FoundErrorInterpret static_ = new FoundErrorInterpret();
                        static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                        static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                        //key word id len
                        static_.buildError(_conf.getAnalysisMessages().getBadParameTypeForId(),
                                s);
                        _conf.getAnalyzing().getLocalizer().addError(static_);
                        getErrs().add(static_.getBuiltError());
                        setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                        return;
                    }
                    _methodTypes.add(new ClassArgumentMatching(format_));
                }
            } else {
                str_ = resolveCorrectTypesExact(_conf, _fromType,_args);
                argsRes_ = resolveArguments(i_, _conf, _args);
                if (argsRes_ == null) {
                    return;
                }
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_;
                }
                for (String s: argsRes_.getParametersTypes()) {
                    _methodTypes.add(new ClassArgumentMatching(s));
                }
            }
            boolean cloneArray_ = cloneArray(str_);
            StringList a_ = new StringList();
            getArrayBounds(str_, a_);
            if (cloneArray_) {
                if (name_.startsWith("[]")) {
                    if (_methodTypes.isEmpty()) {
                        shiftArgument = true;
                        String foundClass_ = StringExpUtil.getPrettyArrayType(_stds.getAliasObject());
                        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                        method = new ClassMethodId(foundClass_, id_);
                        StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
                        fct_.append(Templates.TEMPLATE_BEGIN);
                        String comp_ = foundClass;
                        fct_.append(comp_);
                        fct_.append(Templates.TEMPLATE_SEP);
                        fct_.append(_stds.getAliasPrimInteger());
                        fct_.append(Templates.TEMPLATE_END);
                        setResultClass(new ClassArgumentMatching(fct_.toString()));
                        return;
                    }
                    String foundClass_ = StringExpUtil.getPrettyArrayType(_stds.getAliasObject());
                    shiftArgument = true;
                    StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
                    fct_.append(Templates.TEMPLATE_BEGIN);
                    String comp_ = foundClass;
                    fct_.append(comp_);
                    fct_.append(Templates.TEMPLATE_SEP);
                    boolean err_ = false;
                    StringList params_ = new StringList();
                    for (ClassArgumentMatching p: _methodTypes) {
                        if (!p.isNumericInt(_conf)) {
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            //argument len
                            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                                    StringList.join(p.getNames(),"&"));
                            _conf.getAnalyzing().getLocalizer().addError(un_);
                            getErrs().add(un_.getBuiltError());
                        }
                        String cp_ = comp_;
                        comp_ = StringExpUtil.getQuickComponentType(comp_);
                        if (comp_ == null) {
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            //argument len
                            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                                    cp_);
                            _conf.getAnalyzing().getLocalizer().addError(un_);
                            getErrs().add(un_.getBuiltError());
                            err_ = true;
                            break;
                        }
                        fct_.append(p.getName());
                        params_.add(p.getName());
                        fct_.append(Templates.TEMPLATE_SEP);
                    }
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, params_);
                    method = new ClassMethodId(foundClass_, id_);
                    if (err_) {
                        setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                        return;
                    }
                    if (StringList.quickEq(name_,"[]=")) {
                        fct_.append(comp_);
                        fct_.append(Templates.TEMPLATE_SEP);
                    }
                    fct_.append(comp_);
                    fct_.append(Templates.TEMPLATE_END);
                    setResultClass(new ClassArgumentMatching(fct_.toString()));
                    return;
                }
                if (checkArrayMethod(_conf, _stds, str_, name_)) {
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                String foundClass_ = StringExpUtil.getPrettyArrayType(_stds.getAliasObject());
                MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                method = new ClassMethodId(foundClass_, id_);
                shiftArgument = true;
                StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                fct_.append(foundClass);
                fct_.append(Templates.TEMPLATE_SEP);
                fct_.append(foundClass);
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new ClassArgumentMatching(fct_.toString()));
                return;
            }
            ClassMethodIdReturn id_ = getDeclaredCustMethodLambda(this,_conf, vararg_,
                    MethodAccessKind.INSTANCE, str_, name_,
                    accessSuper_, accessFromSuper_, feed_,
                    OperationNode.toArgArray(_methodTypes));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            standardMethod = id_.getStandardMethod();
            returnFieldType = id_.getOriginalReturnType();
            fileName = id_.getFileName();
            rootNumber = id_.getRootNumber();
            memberNumber = id_.getMemberNumber();
            String foundClass_ = id_.getRealClass();
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            foundClass = id_.getId().getClassName();
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            abstractMethod = id_.isAbstractMethod();
            shiftArgument = !id_.isStaticMethod();
            String fct_ = formatReturn(foundClass, _conf, id_, shiftArgument);
            setResultClass(new ClassArgumentMatching(fct_));
            processAbstract(_conf, staticChoiceMethod_, id_);
            return;
        }
        OperationNode o_ = _m.getFirstChild();
        boolean stAcc_ = o_ instanceof StaticAccessOperation;
        boolean stAccCall_ = o_ instanceof StaticCallAccessOperation;
        if (stAcc_ || stAccCall_) {
            str_ = resolveCorrectTypes(_conf, stAccCall_, _fromType,_args);
            int vararg_ = -1;
            MethodId argsRes_;
            ClassMethodIdAncestor feed_ = null;
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordId_ = keyWords_.getKeyWordId();
            MethodAccessKind kind_;
            if (stAcc_) {
                kind_ = MethodAccessKind.STATIC;
            } else {
                kind_ = MethodAccessKind.STATIC_CALL;
            }
            if (3 < _len && StringList.quickEq(_args.get(2).trim(), keyWordId_)) {
                String nameId_ = _args.get(3).trim();
                int offset_ = _args.first().length() + className.indexOf('(')+1;
                offset_ ++;
                offset_ += _args.get(1).length();
                offset_ ++;
                offset_ += _args.get(2).length();
                offset_ ++;
                offset_ += StringExpUtil.getOffset(_args.get(3));
                String cl_ = ResolvingImportTypes.resolveAccessibleIdType(_conf,offset_,nameId_);
                partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
                if (cl_.isEmpty()) {
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                MethodAccessId acc_ = new MethodAccessId(4);
                acc_.setupAncestor(_args,4);
                int ind_ = acc_.getIndex();
                argsRes_ = resolveArguments(ind_, _conf, cl_, kind_, _args);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                feed_ = new ClassMethodIdAncestor(new ClassMethodId(cl_, new MethodId(kind_, name_, params_, varargFct_)),acc_.getAncestor());
            } else {
                argsRes_ = resolveArguments(2, _conf, _args);
                if (argsRes_ == null) {
                    return;
                }
                if (argsRes_.isVararg()) {
                    vararg_ = _len- 2;
                }
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
            }
            ClassMethodIdReturn id_ = getDeclaredCustMethodLambda(this,_conf, vararg_, kind_, str_, name_, true, false, feed_, OperationNode.toArgArray(_methodTypes));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            standardMethod = id_.getStandardMethod();
            returnFieldType = id_.getOriginalReturnType();
            fileName = id_.getFileName();
            rootNumber = id_.getRootNumber();
            memberNumber = id_.getMemberNumber();
            String foundClass_ = id_.getRealClass();
            MethodId idCt_ = id_.getRealId();
            if (idCt_.getKind() != MethodAccessKind.STATIC_CALL) {
                foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            }
            foundClass = id_.getId().getClassName();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            String fct_ = formatReturn(foundClass, _conf, id_, false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        MethodAccessKind stCtx_;
        int vararg_ = -1;
        int i_ = 2;
        boolean staticChoiceMethod_ = false;
        boolean accessFromSuper_ = false;
        boolean accessSuper_ = true;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordId_ = keyWords_.getKeyWordId();
        if (i_ < _len && StringList.quickEq(name_, keyWordSuper_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (i_ < _len && StringList.quickEq(name_, keyWordThat_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else if (i_ < _len && StringList.quickEq(name_, keyWordClasschoice_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessSuper_ = false;
        } else if (i_ < _len && StringList.quickEq(name_, keyWordSuperaccess_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else {
            polymorph = true;
        }
        MethodId argsRes_;
        ClassMethodIdAncestor feed_ = null;
        if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
            MethodAccessId idUpdate_ = new MethodAccessId(i_);
            String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _conf.getKeyWords().getKeyWordStaticCall();
            idUpdate_.setupInfos(i_ + 1,_args,keyWordStatic_,keyWordStaticCall_);
            stCtx_ = idUpdate_.getKind();
            i_ = idUpdate_.getIndex();
            int offset_ = className.indexOf('(')+1;
            offset_ += StringExpUtil.getOffset(_args.first());
            String type_ = ResolvingImportTypes.resolveCorrectType(_conf, offset_, _fromType, stCtx_ != MethodAccessKind.STATIC);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            str_ = InvokingOperation.getBounds(type_, _conf);
            String cl_ = StringExpUtil.getIdFromAllTypes(type_);
            argsRes_ = resolveArguments(i_+1, _conf, cl_, stCtx_, _args);
            if (argsRes_ == null) {
                return;
            }
            boolean varargFct_ = argsRes_.isVararg();
            StringList params_ = argsRes_.getParametersTypes();
            feed_ = new ClassMethodIdAncestor(new ClassMethodId(cl_, new MethodId(stCtx_, name_, params_, varargFct_)),idUpdate_.getAncestor());
            for (String s: argsRes_.getParametersTypes()) {
                String format_ = AnaTemplates.wildCardFormatParam(type_, s, _conf);
                if (format_.isEmpty()) {
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //key word id len
                    static_.buildError(_conf.getAnalysisMessages().getBadParameTypeForId(),
                            s);
                    _conf.getAnalyzing().getLocalizer().addError(static_);
                    getErrs().add(static_.getBuiltError());
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                _methodTypes.add(new ClassArgumentMatching(format_));
            }
        } else {
            stCtx_ = MethodAccessKind.INSTANCE;
            str_ = resolveCorrectTypesExact(_conf, _fromType,_args);
            argsRes_ = resolveArguments(i_, _conf, _args);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = _len- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
            }
        }
        
        StringList l_ = previousResultClass.getNames();
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _conf));
        }
        boolean cloneArray_ = cloneArray(bounds_);
        StringList a_ = new StringList();
        getArrayBounds(bounds_, a_);
        if (cloneArray_) {
            if (name_.startsWith("[]")) {
                if (_methodTypes.isEmpty()) {
                    String foundClass_ = StringExpUtil.getPrettyArrayType(_stds.getAliasObject());
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                    method = new ClassMethodId(foundClass_, id_);
                    StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
                    fct_.append(Templates.TEMPLATE_BEGIN);
                    fct_.append(_stds.getAliasPrimInteger());
                    fct_.append(Templates.TEMPLATE_END);
                    setResultClass(new ClassArgumentMatching(fct_.toString()));
                    return;
                }
                String foundClass_ = StringExpUtil.getPrettyArrayType(_stds.getAliasObject());
                StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                String comp_ = foundClass;
                boolean err_ = false;
                StringList params_ = new StringList();
                for (ClassArgumentMatching p: _methodTypes) {
                    if (!p.isNumericInt(_conf)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                        un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                        //arg len
                        un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                                StringList.join(p.getNames(),"&"));
                        _conf.getAnalyzing().getLocalizer().addError(un_);
                        getErrs().add(un_.getBuiltError());
                    }
                    String cp_ = comp_;
                    comp_ = StringExpUtil.getQuickComponentType(comp_);
                    if (comp_ == null) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                        un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                        //arg len
                        un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                                cp_);
                        _conf.getAnalyzing().getLocalizer().addError(un_);
                        getErrs().add(un_.getBuiltError());
                        err_ = true;
                        break;
                    }
                    params_.add(p.getName());
                    fct_.append(p.getName());
                    fct_.append(Templates.TEMPLATE_SEP);
                }
                MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, params_);
                method = new ClassMethodId(foundClass_, id_);
                if (err_) {
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                if (StringList.quickEq(name_,"[]=")) {
                    fct_.append(comp_);
                    fct_.append(Templates.TEMPLATE_SEP);
                }
                fct_.append(comp_);
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new ClassArgumentMatching(fct_.toString()));
                return;
            }
            if (checkArrayMethod(_conf, _stds, bounds_, name_)) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            String foundClass_ = StringExpUtil.getPrettyArrayType(_stds.getAliasObject());
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
            method = new ClassMethodId(foundClass_, id_);
            StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(foundClass);
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new ClassArgumentMatching(fct_.toString()));
            return;
        }
        Mapping map_ = new Mapping();
        map_.setArg(new ClassArgumentMatching(bounds_));
        map_.setParam(new ClassArgumentMatching(str_));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        getRefConstraints(_conf, maps_);
        map_.setMapping(maps_);
        if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(bounds_,"&"),
                    StringList.join(str_,"&"));
            _conf.getAnalyzing().getLocalizer().addError(cast_);
            getErrs().add(cast_.getBuiltError());
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        ClassMethodIdReturn id_ = getDeclaredCustMethodLambda(this,_conf, vararg_, stCtx_, str_,
                name_, accessSuper_, accessFromSuper_,
                feed_, OperationNode.toArgArray(_methodTypes));
        if (!id_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        standardMethod = id_.getStandardMethod();
        returnFieldType = id_.getOriginalReturnType();
        fileName = id_.getFileName();
        rootNumber = id_.getRootNumber();
        memberNumber = id_.getMemberNumber();
        String foundClass_ = id_.getRealClass();
        foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        foundClass = id_.getId().getClassName();
        abstractMethod = id_.isAbstractMethod();
        MethodId idCt_ = id_.getRealId();
        method = new ClassMethodId(foundClass_, idCt_);
        ancestor = id_.getAncestor();
        String fct_ = formatReturn(foundClass, _conf, id_, false);
        setResultClass(new ClassArgumentMatching(fct_));
        processAbstract(_conf, staticChoiceMethod_, id_);
    }

    private boolean matchIdKeyWord(StringList _args, int _len, int _i, String _keyWordId) {
        return _i < _len && StringList.quickEq(_args.get(_i).trim(), _keyWordId);
    }

    private boolean cloneArray(StringList _bounds) {
        boolean cloneArray_ = false;
        for (String b: _bounds) {
            if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                cloneArray_ = true;
                break;
            }
        }
        return cloneArray_;
    }

    private boolean checkArrayMethod(ContextEl _conf, LgNames _stds, StringList _str, String _name) {
        if (!StringList.quickEq(_name, _stds.getAliasClone())) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //_name len
            undefined_.buildError(_conf.getAnalysisMessages().getArrayCloneOnly(),
                    _stds.getAliasClone(),
                    StringList.join(_str,"&"));
            _conf.getAnalyzing().getLocalizer().addError(undefined_);
            getErrs().add(undefined_.getBuiltError());
            return true;
        }
        return false;
    }

    private void getArrayBounds(StringList _bounds, StringList _a) {
        for (String b: _bounds) {
            if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                _a.add(b);
                foundClass = b;
            }
        }
    }

    private void processAbstract(ContextEl _conf, boolean _staticChoiceMethod, ClassMethodIdReturn _id) {
        if (_staticChoiceMethod) {
            if (_id.isAbstractMethod()) {
                FoundErrorInterpret abs_ = new FoundErrorInterpret();
                abs_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                abs_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                //method name len
                abs_.buildError(
                        _conf.getAnalysisMessages().getAbstractMethodRef(),
                        _id.getRealClass(),
                        _id.getRealId().getSignature(_conf));
                _conf.getAnalyzing().getLocalizer().addError(abs_);
                getErrs().add(abs_.getBuiltError());
            }
        }
    }

    private void processInstance(ContextEl _conf, LgNames _stds,
            StringList _args, int _len, String _fromType,
            CustList<ClassArgumentMatching> _methodTypes) {
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        String clFrom_ = EMPTY_STRING;
        if (!isIntermediateDottedOperation()) {
            clFrom_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_,_fromType);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            if (clFrom_.startsWith(ARR)) {
                processArray(_conf, _stds, _args, _len, _methodTypes, clFrom_);
                return;
            }
        }
        int vararg_ = -1;
        MethodId argsRes_;
        ConstructorId feed_ = null;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        boolean foundId_ = false;
        String type_ = EMPTY_STRING;
        if (_len > 2 &&StringList.quickEq(_args.get(2).trim(), keyWordId_)) {
            type_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_,_fromType);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            String cl_ = StringExpUtil.getIdFromAllTypes(type_);
            argsRes_ = resolveArguments(3, _conf, cl_, MethodAccessKind.INSTANCE, _args);
            if (argsRes_ == null) {
                return;
            }
            boolean varargFct_ = argsRes_.isVararg();
            StringList params_ = argsRes_.getParametersTypes();
            feed_ = new ConstructorId(cl_, params_, varargFct_);
            for (String s: argsRes_.getParametersTypes()) {
                String format_ = AnaTemplates.wildCardFormatParam(type_, s, _conf);
                if (format_.isEmpty()) {
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //key word id len
                    static_.buildError(_conf.getAnalysisMessages().getBadParameTypeForId(),
                            s);
                    _conf.getAnalyzing().getLocalizer().addError(static_);
                    getErrs().add(static_.getBuiltError());
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                _methodTypes.add(new ClassArgumentMatching(format_));
            }
            foundId_ = true;
        } else {
            int i_ = 2;
            argsRes_ = resolveArguments(i_, _conf, _args);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = _len- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
            }
        }
        if (!isIntermediateDottedOperation()) {
            String id_ = StringExpUtil.getIdFromAllTypes(clFrom_);
            AnaGeneType h_ = _conf.getAnalyzing().getAnaGeneType(_conf,id_);
            if (ContextUtil.isAbstractType(h_)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //_fromType len
                call_.buildError(_conf.getAnalysisMessages().getIllegalCtorAbstract(),
                        id_);
                _conf.getAnalyzing().getLocalizer().addError(call_);
                getErrs().add(call_.getBuiltError());
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            for (String p:StringExpUtil.getAllTypes(clFrom_).mid(1)){
                if (p.startsWith(Templates.SUB_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //_fromType len
                    call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            clFrom_);
                    _conf.getAnalyzing().getLocalizer().addError(call_);
                    getErrs().add(call_.getBuiltError());
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //_fromType len
                    call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            clFrom_);
                    _conf.getAnalyzing().getLocalizer().addError(call_);
                    getErrs().add(call_.getBuiltError());
                }
            }
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructorLambda(this,_conf, vararg_, new ClassArgumentMatching(clFrom_),id_, h_,feed_, OperationNode.toArgArray(_methodTypes));
            realId = ctorRes_.getRealId();
            if (realId == null) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            fileName = ctorRes_.getFileName();
            rootNumber = ctorRes_.getRootNumber();
            memberNumber = ctorRes_.getMemberNumber();
            ConstructorId fid_ = ctorRes_.getConstId();
            StringList parts_ = new StringList();
            if (!h_.isStaticType()) {
                //From analyze
                StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(clFrom_);
                parts_.add(StringList.join(innerParts_.mid(0, innerParts_.size() - 2), ""));
            }
            StringList params_ = fid_.getParametersTypes();
            if (fid_.isVararg()) {
                for (String p: params_.mid(0, params_.size() - 1)) {
                    parts_.add(p);
                }
                String p_ = params_.last();
                parts_.add(StringExpUtil.getPrettyArrayType(p_));
            } else {
                for (String p: params_) {
                    parts_.add(p);
                }
            }
            foundClass = clFrom_;
            parts_.add(clFrom_);
            StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(StringList.join(parts_, Templates.TEMPLATE_SEP));
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new ClassArgumentMatching(fct_.toString()));
            return;
        }
        if (foundId_) {
            processCtor(_conf, _stds, _methodTypes, vararg_, feed_, type_);
            return;
        }
        String cl_ = _fromType.trim();
        String idClass_ = StringExpUtil.getIdFromAllTypes(cl_);
        StringMap<String> ownersMap_ = new StringMap<String>();
        boolean ok_ = true;
        for (String o: previousResultClass.getNames()) {
            if (o.startsWith(ARR)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key word len
                call_.buildError(_conf.getAnalysisMessages().getIllegalCtorArray(),
                        o);
                _conf.getAnalyzing().getLocalizer().addError(call_);
                getErrs().add(call_.getBuiltError());
                ok_ = false;
                continue;
            }
            for (String p:StringExpUtil.getAllTypes(o).mid(1)){
                if (p.startsWith(Templates.SUB_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //key word len
                    call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            o);
                    _conf.getAnalyzing().getLocalizer().addError(call_);
                    getErrs().add(call_.getBuiltError());
                    ok_ = false;
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //key word len
                    call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            o);
                    _conf.getAnalyzing().getLocalizer().addError(call_);
                    getErrs().add(call_.getBuiltError());
                    ok_ = false;
                }
            }
        }
        if (!ok_) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        for (String o: previousResultClass.getNames()) {
            StringList owners_ = AnaTypeUtil.getGenericOwners(o, idClass_, _conf);
            if (owners_.onlyOneElt()) {
                ownersMap_.put(o, owners_.first());
            }
        }
        if (ownersMap_.size() != 1) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_conf.getAnalysisMessages().getNotResolvedOwner(),
                    idClass_
            );
            _conf.getAnalyzing().getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        String sup_ = ownersMap_.values().first();
        String idSup_ = StringExpUtil.getIdFromAllTypes(sup_);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        ContextUtil.appendParts(_conf,offset_,offset_+idClass_.length(),StringList.concat(idSup_,"..",idClass_),partOffsets_);
        offset_ += idClass_.length() + 1;
        StringList partsArgs_ = new StringList();
        for (String a: StringExpUtil.getAllTypes(cl_).mid(1)) {
            int loc_ = StringExpUtil.getOffset(a);
            String res_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_+loc_,a);
            partOffsets_.addAllElts(_conf.getAnalyzing().getCurrentParts());
            partsArgs_.add(res_);
            offset_ += a.length() + 1;
        }
        partOffsets_.addAllElts(partOffsets);
        partOffsets.clear();
        partOffsets.addAllElts(partOffsets_);
        StringMap<StringList> vars_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        cl_ = AnaTemplates.check(getErrs(),StringList.concat(sup_, "..", idClass_), partsArgs_, vars_, _conf);
        processCtor(_conf, _stds, _methodTypes, vararg_, null, cl_);
    }

    private void processCtor(ContextEl _conf, LgNames _stds, CustList<ClassArgumentMatching> _methodTypes, int _vararg, ConstructorId _feed, String _cl) {
        foundClass = _cl;
        shiftArgument = true;
        for (String p:StringExpUtil.getAllTypes(_cl).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key word len or _fromType
                call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                        p,
                        _cl);
                _conf.getAnalyzing().getLocalizer().addError(call_);
                getErrs().add(call_.getBuiltError());
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key word len or _fromType
                call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                        p,
                        _cl);
                _conf.getAnalyzing().getLocalizer().addError(call_);
                getErrs().add(call_.getBuiltError());
            }
        }
        String id_ = StringExpUtil.getIdFromAllTypes(_cl);
        AnaGeneType h_ = _conf.getAnalyzing().getAnaGeneType(_conf,id_);
        if (ContextUtil.isAbstractType(h_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len or _fromType
            call_.buildError(_conf.getAnalysisMessages().getIllegalCtorAbstract(),
                    id_);
            _conf.getAnalyzing().getLocalizer().addError(call_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            getErrs().add(call_.getBuiltError());
            return;
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructorLambda(this,_conf, _vararg, new ClassArgumentMatching(_cl),id_,h_, _feed, OperationNode.toArgArray(_methodTypes));
        realId = ctorRes_.getRealId();
        if (realId == null) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        fileName = ctorRes_.getFileName();
        rootNumber = ctorRes_.getRootNumber();
        memberNumber = ctorRes_.getMemberNumber();
        ConstructorId fid_ = ctorRes_.getConstId();
        StringList parts_ = new StringList();
        StringList params_ = fid_.getParametersTypes();
        if (fid_.isVararg()) {
            for (String p: params_.mid(0, params_.size() - 1)) {
                parts_.add(p);
            }
            String p_ = params_.last();
            parts_.add(StringExpUtil.getPrettyArrayType(p_));
        } else {
            for (String p: params_) {
                parts_.add(p);
            }
        }
        parts_.add(_cl);
        StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
        fct_.append(Templates.TEMPLATE_BEGIN);
        fct_.append(StringList.join(parts_, Templates.TEMPLATE_SEP));
        fct_.append(Templates.TEMPLATE_END);
        setResultClass(new ClassArgumentMatching(fct_.toString()));
    }

    private void processField(ContextEl _conf, LgNames _stds,
            MethodOperation _m, StringList _args, int _len, String _fromType) {
        StringList str_;
        if (_len < 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_conf.getAnalysisMessages().getSplitComa(),
                    Integer.toString(3),
                    Integer.toString(_len)
            );
            _conf.getAnalyzing().getLocalizer().addError(badCall_);
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsetsEnd.add(new PartOffset("</a>",i_+_conf.getKeyWords().getKeyWordLambda().length()));
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        String fieldName_ = _args.get(2).trim();
        int sum_ = className.indexOf('(')+1;
        sum_ += _args.first().length() + 1;
        sum_ += _args.get(1).length() + 1;
        if (!isIntermediateDottedOperation()) {
            String resolved_ = resolveSingleTypeExact(_conf, _args, _fromType);
            str_ = InvokingOperation.getBounds(resolved_, _conf);
            int i_ = 3;
            RootBlock root_ = _conf.getAnalyzing().getAnaClassBody(resolved_);
            if (root_ != null) {
                rootNumber = root_.getNumberAll();
            }
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordSuper_ = keyWords_.getKeyWordSuper();
            String keyWordThat_ = keyWords_.getKeyWordThat();
            String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
            String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
            String keyWordParent_ = keyWords_.getKeyWordParent();
            String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
            if (StringList.quickEq(fieldName_, keyWordInstanceof_)) {
                finalField = true;
                returnFieldType = _stds.getAliasPrimBoolean();
                foundClass = resolved_;
                shiftArgument = true;
                String fct_ = StringList.concat(_stds.getAliasFct(),"<",_stds.getAliasObject(),",",returnFieldType,">");
                setResultClass(new ClassArgumentMatching(fct_));
                return;
            }
            if (StringList.quickEq(fieldName_, keyWordParent_)) {
                String res_ = getParentType(_conf,resolved_);
                finalField = true;
                returnFieldType = res_;
                foundClass = resolved_;
                shiftArgument = true;
                StringList params_ = new StringList();
                String fct_ = formatFieldReturn(_conf, false, params_, res_, shiftArgument);
                setResultClass(new ClassArgumentMatching(fct_));
                return;
            }
            if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuper_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
                accessFromSuper_ = true;
            } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordThat_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
            } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordClasschoice_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
                accessSuper_ = false;
            } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuperaccess_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
            } else {
                sum_ += StringExpUtil.getOffset(_args.get(2));
            }
            boolean aff_ = i_ < _len;
            ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
            FieldResult r_ = getDeclaredCustFieldLambda(sum_,partOffsets,_conf, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, aff_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            fileName = r_.getFileName();
            rootNumber = r_.getRootNumber();
            memberNumber = r_.getMemberNumber();
            valueOffset = r_.getId().getValOffset();
            affField = aff_;
            fieldId = r_.getId().getClassField();
            staticField = r_.getId().isStaticField();
            finalField = r_.getId().isFinalField();
            String out_ = r_.getId().getType();
            returnFieldType = out_;
            foundClass = r_.getId().getDeclaringClass();
            ancestor = r_.getAnc();
            boolean static_ = r_.getId().isStaticField();
            shiftArgument = !static_;
            StringList params_ = new StringList();
            if (aff_) {
                checkFinal(r_.getId(),_conf);
                int offset_ = className.indexOf('(')+1;
                for (int i = 0; i < i_; i++) {
                    offset_ += _args.get(i).length();
                    offset_ ++;
                }
                offset_ += StringExpUtil.getOffset(_args.get(i_));
                String type_ = _args.get(i_).trim();
                String arg_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_,type_);
                partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
                StringMap<StringList> map_ = new StringMap<StringList>();
                getRefConstraints(_conf, map_);
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //field name len
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            arg_,
                            out_);
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                    getErrs().add(cast_.getBuiltError());
                }
                params_.add(arg_);
                //setter
            }
            String fct_ = formatFieldReturn(_conf, static_, params_, out_, shiftArgument);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        OperationNode o_ = _m.getFirstChild();
        if (o_ instanceof StaticAccessOperation) {
            str_ = resolveCorrectTypes(_conf, false, _fromType,_args);
            int i_ = 3;
            boolean aff_ = i_ < _len;
            ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
            sum_ += StringExpUtil.getOffset(_args.get(2));
            FieldResult r_ = getDeclaredCustFieldLambda(sum_,partOffsets,_conf, fromCl_, true, true, fieldName_, aff_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            fileName = r_.getFileName();
            rootNumber = r_.getRootNumber();
            memberNumber = r_.getMemberNumber();
            valueOffset = r_.getId().getValOffset();
            affField = aff_;
            fieldId = r_.getId().getClassField();
            staticField = r_.getId().isStaticField();
            finalField = r_.getId().isFinalField();
            String out_ = r_.getId().getType();
            returnFieldType = out_;
            foundClass = r_.getId().getDeclaringClass();
            ancestor = r_.getAnc();
            StringList params_ = new StringList();
            if (aff_) {
                checkFinal(r_.getId(),_conf);
                int offset_ = className.indexOf('(')+1;
                for (int i = 0; i < i_; i++) {
                    offset_ += _args.get(i).length();
                    offset_ ++;
                }
                offset_ += StringExpUtil.getOffset(_args.get(i_));
                String type_ = _args.get(i_).trim();
                String arg_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_,type_);
                partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
                StringMap<StringList> map_ = new StringMap<StringList>();
                getRefConstraints(_conf, map_);
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //field name len
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            arg_,
                            out_);
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                    getErrs().add(cast_.getBuiltError());
                }
                params_.add(arg_);
                //setter
            }
            String fct_ = formatFieldReturn(_conf, true, params_, out_, false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        String resolved_ = resolveSingleTypeExact(_conf, _args, _fromType);
        str_ = InvokingOperation.getBounds(resolved_, _conf);
        int i_ = 3;
        boolean accessFromSuper_ = false;
        boolean accessSuper_ = true;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordParent_ = keyWords_.getKeyWordParent();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        StringList l_ = previousResultClass.getNames();
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _conf));
        }
        if (StringList.quickEq(fieldName_, keyWordInstanceof_)) {
            finalField = true;
            returnFieldType = _stds.getAliasPrimBoolean();
            foundClass = resolved_;
            String fct_ = StringList.concat(_stds.getAliasFct(),"<",returnFieldType,">");
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        if (StringList.quickEq(fieldName_, keyWordParent_)) {
            Mapping map_ = new Mapping();
            map_.setArg(new ClassArgumentMatching(bounds_));
            map_.setParam(new ClassArgumentMatching(str_));
            StringMap<StringList> maps_ = new StringMap<StringList>();
            getRefConstraints(_conf, maps_);
            map_.setMapping(maps_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key word len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(bounds_,"&"),
                        StringList.join(str_,"&"));
                _conf.getAnalyzing().getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            String res_ = getParentType(_conf,resolved_);
            finalField = true;
            returnFieldType = res_;
            foundClass = resolved_;
            StringList params_ = new StringList();
            String fct_ = formatFieldReturn(_conf, false, params_, res_, false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuper_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
            accessFromSuper_ = true;
        } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordThat_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
        } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordClasschoice_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
            accessSuper_ = false;
        } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuperaccess_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
        } else {
            sum_ += StringExpUtil.getOffset(_args.get(2));
        }
        Mapping map_ = new Mapping();
        map_.setArg(new ClassArgumentMatching(bounds_));
        map_.setParam(new ClassArgumentMatching(str_));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        getRefConstraints(_conf, maps_);
        map_.setMapping(maps_);
        if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(bounds_,"&"),
                    StringList.join(str_,"&"));
            _conf.getAnalyzing().getLocalizer().addError(cast_);
            getErrs().add(cast_.getBuiltError());
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        boolean aff_ = i_ < _len;
        ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
        FieldResult r_ = getDeclaredCustFieldLambda(sum_,partOffsets,_conf, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, aff_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        fileName = r_.getFileName();
        rootNumber = r_.getRootNumber();
        memberNumber = r_.getMemberNumber();
        valueOffset = r_.getId().getValOffset();
        affField = aff_;
        fieldId = r_.getId().getClassField();
        staticField = r_.getId().isStaticField();
        finalField = r_.getId().isFinalField();
        String out_ = r_.getId().getType();
        returnFieldType = out_;
        foundClass = r_.getId().getDeclaringClass();
        ancestor = r_.getAnc();
        boolean static_ = r_.getId().isStaticField();
        StringList params_ = new StringList();
        if (aff_) {
            checkFinal(r_.getId(),_conf);
            int offset_ = className.indexOf('(')+1;
            for (int i = 0; i < i_; i++) {
                offset_ += _args.get(i).length();
                offset_ ++;
            }
            offset_ += StringExpUtil.getOffset(_args.get(i_));
            String type_ = _args.get(i_).trim();
            String arg_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_,type_);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            Mapping mapping_ = new Mapping();
            mapping_.setArg(arg_);
            mapping_.setParam(out_);
            mapping_.setMapping(maps_);
            if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //field name len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        arg_,
                        out_);
                _conf.getAnalyzing().getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
            params_.add(arg_);
            //setter
        }
        String fct_ = formatFieldReturn(_conf, static_, params_, out_, false);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    private static String getParentType(ContextEl _conf, String _converted) {
        if (_converted.startsWith(AnaTemplates.ARR_BEG_STRING)) {
            return _conf.getStandards().getAliasObject();
        }
        StringList rs_ = ParentInstanceOperation.getParentTypeList(_conf,new StringList(_converted));
        return rs_.first();
    }
    private String resolveSingleTypeExact(ContextEl _conf, StringList _args, String _fromType) {
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        String type_ = ResolvingImportTypes.resolveCorrectType(_conf, offset_, _fromType);
        partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        return type_;
    }

    private void checkFinal(FieldInfo _id, ContextEl _conf) {
        if (_id.isFinalField()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //field name len
            un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                    _id.getClassField().getFieldName());
            _conf.getAnalyzing().getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
        }
    }

    private static void getRefConstraints(ContextEl _conf, StringMap<StringList> _map) {
        _map.putAllMap(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
    }

    private void processOperator(ContextEl _conf, LgNames _stds,
            StringList _args, int _len,CustList<ClassArgumentMatching> _methodTypes) {
        if (isIntermediateDottedOperation() && !previousResultClass.getNames().onlyOneElt()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(previousResultClass.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(un_);
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsetsEnd.add(new PartOffset("</a>",i_+_conf.getKeyWords().getKeyWordLambda().length()));
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        int sum_ = className.indexOf('(')+1;
        sum_ += _args.first().length();
        String operator_ = _args.get(1).trim();
        int i_ = 2;
        String from_ = "";
        if (!StringExpUtil.isOper(operator_)) {
            sum_ += 1+_args.get(1).length();
            int offset_ = className.indexOf(',')+1;
            offset_ += StringExpUtil.getOffset(operator_);
            if (operator_.isEmpty()) {
                offset_--;
            }
            String type_ = ResolvingImportTypes.resolveCorrectType(_conf, offset_, operator_, true);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            from_ = type_;
            if (_len > i_) {
                operator_ = _args.get(i_).trim();
            }
            i_++;
        }
        int count_ = partOffsets.size();
        if (!StringExpUtil.isOper(operator_)) {
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFileName(_conf.getCurrentFileName());
            badMeth_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            badMeth_.buildError(_conf.getAnalysisMessages().getBadOperatorName(),
                    operator_);
            _conf.getAnalyzing().getLocalizer().addError(badMeth_);
            int j_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+sum_;
            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badMeth_.getBuiltError()) +"\" class=\"e\">",j_));
            partOffsetsEnd.add(new PartOffset("</a>",j_+Math.max(1,operator_.length())));
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        int vararg_ = -1;
        MethodId argsRes_;
        ClassMethodId feed_ = null;
        int j_ = i_;
        MethodAccessKind staticFlag_ = MethodAccessKind.STATIC;
        if (_len > j_ &&StringList.quickEq(_args.get(j_).trim(), keyWordId_)) {
            i_++;
            MethodAccessId idUpdate_ = new MethodAccessId(i_);
            String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _conf.getKeyWords().getKeyWordStaticCall();
            idUpdate_.setupInfos(i_,_args,keyWordStatic_,keyWordStaticCall_);
            staticFlag_ = idUpdate_.getKind();
            int k_ = idUpdate_.getIndex();
            if (k_ == i_) {
                staticFlag_ = MethodAccessKind.STATIC;
            }
            i_ = idUpdate_.getIndex();
            argsRes_ = resolveArguments(i_, _conf,from_,staticFlag_, _args);
        } else {
            argsRes_ = resolveArguments(i_, _conf, _args);
        }
        if (argsRes_ == null) {
            return;
        }
        if (_len > j_ &&StringList.quickEq(_args.get(j_).trim(), keyWordId_)) {
            boolean varargFct_ = argsRes_.isVararg();
            StringList params_;
            if (isIntermediateDottedOperation()) {
                _methodTypes.add(previousResultClass);
                params_ = new StringList();
                params_.add(previousResultClass.getName());
                params_.addAllElts(argsRes_.getParametersTypes());
                feed_ = new ClassMethodId(from_, new MethodId(staticFlag_, operator_, params_, varargFct_));
            } else {
                params_ = argsRes_.getParametersTypes();
                feed_ = new ClassMethodId(from_, new MethodId(staticFlag_, operator_, params_, varargFct_));
            }
            for (String s: argsRes_.getParametersTypes()) {
                String format_ = AnaTemplates.wildCardFormatParam(from_, s, _conf);
                if (format_.isEmpty()) {
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //key word id len
                    static_.buildError(_conf.getAnalysisMessages().getBadParameTypeForId(),
                            s);
                    _conf.getAnalyzing().getLocalizer().addError(static_);
                    getErrs().add(static_.getBuiltError());
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                _methodTypes.add(new ClassArgumentMatching(format_));
            }
        } else {
            if (isIntermediateDottedOperation()) {
                _methodTypes.add(previousResultClass);
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_+1;
                }
            } else {
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_;
                }
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
            }
        }
        if (!isIntermediateDottedOperation()) {
            ClassMethodIdReturn id_ = getOperator(_conf, from_,_methodTypes, operator_, vararg_, feed_);
            if (!id_.isFoundMethod()) {
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //_name len
                StringList classesNames_ = new StringList();
                for (ClassArgumentMatching c: _methodTypes) {
                    classesNames_.add(StringList.join(c.getNames(), "&"));
                }
                undefined_.buildError(_conf.getAnalysisMessages().getUndefinedMethod(),
                        new MethodId(MethodAccessKind.STATIC, "", classesNames_).getSignature(_conf));
                _conf.getAnalyzing().getLocalizer().addError(undefined_);
                int k_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+sum_+1;
                partOffsets.add(count_,new PartOffset("</a>",k_+Math.max(1,operator_.length())));
                partOffsets.add(count_,new PartOffset("<a title=\""+LinkageUtil.transform(undefined_.getBuiltError()) +"\" class=\"e\">",k_));
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            fileName = id_.getFileName();
            rootNumber = id_.getRootNumber();
            memberNumber = id_.getMemberNumber();
            operatorNumber = id_.getMemberNumber();
            String foundClass_ = id_.getRealClass();
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            foundClass = id_.getId().getClassName();
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            abstractMethod = id_.isAbstractMethod();
            returnFieldType = id_.getReturnType();
            String fct_ = formatReturnOperator(_conf, false, id_);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        ClassMethodIdReturn id_ = getOperator(_conf, from_,_methodTypes, operator_, vararg_, feed_);
        if (!id_.isFoundMethod()) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //_name len
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _methodTypes) {
                classesNames_.add(StringList.join(c.getNames(), "&"));
            }
            undefined_.buildError(_conf.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, "", classesNames_).getSignature(_conf));
            _conf.getAnalyzing().getLocalizer().addError(undefined_);
            int k_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+sum_+1;
            partOffsets.add(count_,new PartOffset("</a>",k_+Math.max(1,operator_.length())));
            partOffsets.add(count_,new PartOffset("<a title=\""+LinkageUtil.transform(undefined_.getBuiltError()) +"\" class=\"e\">",k_));
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        fileName = id_.getFileName();
        rootNumber = id_.getRootNumber();
        memberNumber = id_.getMemberNumber();
        operatorNumber = id_.getMemberNumber();
        returnFieldType = id_.getOriginalReturnType();
        String foundClass_ = id_.getRealClass();
        foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        foundClass = id_.getId().getClassName();
        abstractMethod = id_.isAbstractMethod();
        MethodId idCt_ = id_.getRealId();
        method = new ClassMethodId(foundClass_, idCt_);
        ancestor = id_.getAncestor();
        shiftArgument = true;
        String fct_ = formatReturnOperator(_conf, true, id_);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    private ClassMethodIdReturn getOperator(ContextEl _cont,String _from, CustList<ClassArgumentMatching> _methodTypes, String _operator, int _vararg, ClassMethodId _feed) {
        if (!_from.isEmpty()) {
            if (_feed == null) {
                return tryGetDeclaredCustMethodLambda(_cont, -1, MethodAccessKind.STATIC_CALL,
                        new StringList(_from), _operator, false, false, false, null,
                        OperationNode.toArgArray(_methodTypes));
            }
            return tryGetDeclaredCustMethodLambda(_cont, -1, MethodAccessKind.STATIC_CALL,
                    new StringList(_from), _operator, false, false, false, new ClassMethodIdAncestor(_feed,0),
                    OperationNode.toArgArray(_methodTypes));
        }
        return getOperatorLambda(_cont, _feed, _vararg, _operator, OperationNode.toArgArray(_methodTypes));
    }

    private void processArray(ContextEl _conf, LgNames _stds,
            StringList _args, int _len,
            CustList<ClassArgumentMatching> _methodTypes, String _cl) {
        int i_ = 2;
        StringList parts_ = new StringList();
        boolean err_ = false;
        for (int i = i_; i < _len; i++) {
            String arg_ = StringExpUtil.removeDottedSpaces(_args.get(i));
            parts_.add(arg_);
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(arg_);
            if (!clArg_.isNumericInt(_conf)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                //arg_ len
                un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                        StringList.join(clArg_.getNames(),"&"));
                _conf.getAnalyzing().getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
                err_ = true;
            }
            _methodTypes.add(clArg_);
        }
        if (_methodTypes.isEmpty()) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_conf.getAnalysisMessages().getSplitComa(),
                    Integer.toString(3),
                    Integer.toString(_len)
            );
            _conf.getAnalyzing().getLocalizer().addError(badCall_);
            getErrs().add(badCall_.getBuiltError());
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        if (err_) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        String elt_ = _cl.substring(ARR.length());
        String out_ = StringExpUtil.getPrettyArrayType(elt_, _methodTypes.size());
        foundClass = out_;
        realId = new ConstructorId(out_, parts_, true);
        parts_.add(out_);
        StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
        fct_.append(Templates.TEMPLATE_BEGIN);
        fct_.append(StringList.join(parts_, Templates.TEMPLATE_SEP));
        fct_.append(Templates.TEMPLATE_END);
        setResultClass(new ClassArgumentMatching(fct_.toString()));
    }
    private MethodId resolveArguments(int _from, ContextEl _conf, String _fromType, MethodAccessKind _static, StringList _params){
        StringList out_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        int len_ = _params.size();
        int vararg_ = -1;
        int off_ = className.indexOf('(')+1;
        for (int i = 0; i < len_; i++) {
            if (i < _from) {
                off_ += _params.get(i).length() + 1;
                continue;
            }
            String full_ = _params.get(i);
            int loc_ = StringExpUtil.getOffset(full_);
            if (full_.trim().isEmpty()) {
                loc_--;
            }
            String arg_ = StringExpUtil.removeDottedSpaces(full_);
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    int i_ = off_ + _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + full_.lastIndexOf("...");
                    varg_.setIndexFile(i_);
                    //three dots
                    varg_.buildError(_conf.getAnalysisMessages().getUnexpectedVararg());
                    _conf.getAnalyzing().getLocalizer().addError(varg_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
                    partOffsets.add(new PartOffset("</a>",i_+3));
                    return null;
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf,off_ + loc_,type_, _fromType);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            off_ += _params.get(i).length() + 1;
            out_.add(arg_);
        }
        return new MethodId(_static, EMPTY_STRING, out_, vararg_ != -1);
    }
    private MethodId resolveArguments(int _from, ContextEl _conf, StringList _params){
        StringList out_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        int len_ = _params.size();
        int vararg_ = -1;
        int offset_ = className.indexOf('(')+1;
        for (int i = 0; i < len_; i++) {
            if (i < _from) {
                offset_ += _params.get(i).length() + 1;
                continue;
            }
            String param_ = _params.get(i);
            int loc_ = StringExpUtil.getOffset(param_);
            if (param_.trim().isEmpty()) {
                loc_--;
            }
            String arg_ = StringExpUtil.removeDottedSpaces(param_);
            String type_;
            boolean wrap_ = false;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    int i_ = offset_ + _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + param_.lastIndexOf("...");
                    varg_.setIndexFile(i_);
                    //three dots
                    varg_.buildError(_conf.getAnalysisMessages().getUnexpectedVararg());
                    _conf.getAnalyzing().getLocalizer().addError(varg_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
                    partOffsets.add(new PartOffset("</a>",i_+3));
                    return null;
                }
                wrap_ = true;
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = ResolvingImportTypes.resolveCorrectType(_conf,offset_+loc_,type_);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            if (wrap_) {
                arg_ = StringExpUtil.getPrettyArrayType(arg_);
            }
            offset_ += param_.length() + 1;
            out_.add(arg_);
        }
        return new MethodId(MethodAccessKind.INSTANCE, EMPTY_STRING, out_, vararg_ != -1);
    }

    private StringList resolveCorrectTypes(ContextEl _an, boolean _exact, String _type, StringList _args) {
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        String type_ = ResolvingImportTypes.resolveCorrectType(_an, offset_, _type, _exact);
        partOffsets.addAllElts(_an.getAnalyzing().getCurrentParts());
        return InvokingOperation.getBounds(type_, _an);
    }
    private StringList resolveCorrectTypesExact(ContextEl _an, String _type, StringList _args) {
        String type_ = resolveSingleTypeExact(_an, _args, _type);
        return InvokingOperation.getBounds(type_, _an);
    }
    static String formatReturn(String _foundClass, ContextEl _an, ClassMethodIdReturn _id, boolean _demand) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        String returnType_ = _id.getReturnType();
        StringList paramsReturn_ = new StringList();
        MethodId id_ = _id.getId().getConstraints();
        IdentifiableUtil.appendLeftPart(_foundClass, _demand, paramsReturn_, id_, _id.getRealId().isStaticMethod());
        appendRightPart(_an, _id, paramsReturn_, id_);
        paramsReturn_.add(returnType_);
        return StringList.concat(fctBase_, Templates.TEMPLATE_BEGIN, StringList.join(paramsReturn_, Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }

    private static void appendRightPart(ContextEl _an, ClassMethodIdReturn _out, StringList _paramsReturn, MethodId _id) {
        if (StringList.quickEq(_id.getName(),"[]=")) {
            CustList<OverridableBlock> getIndexers_ = new CustList<OverridableBlock>();
            String idCl_ = StringExpUtil.getIdFromAllTypes(_out.getRealClass());
            for (Block b: ClassesUtil.getDirectChildren(_an.getAnalyzing().getAnaClassBody(idCl_))) {
                if (!(b instanceof OverridableBlock)) {
                    continue;
                }
                OverridableBlock i_ = (OverridableBlock) b;
                if (i_.getKind() != MethodKind.GET_INDEX) {
                    continue;
                }
                if (!i_.getId().eqPartial(_out.getRealId())) {
                    continue;
                }
                getIndexers_.add(i_);
            }
            if (getIndexers_.size() == 1) {
                OverridableBlock matching_ = getIndexers_.first();
                String importedReturnType_ = matching_.getImportedReturnType();
                String real_ = _out.getRealClass();
                importedReturnType_ = AnaTemplates.wildCardFormatReturn(real_, importedReturnType_, _an);
                _paramsReturn.add(importedReturnType_);
            }
        }
    }

    private static String formatReturnOperator(ContextEl _an, boolean _op, ClassMethodIdReturn _id) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        String returnType_ = _id.getReturnType();
        StringList paramsReturn_ = new StringList();
        MethodId id_ = _id.getId().getConstraints();
        StringList params_ = id_.getParametersTypes();
        int start_ = 0;
        if (_op) {
            start_ = 1;
        }
        if (id_.isVararg()) {
            for (String p: params_.mid(start_, params_.size() - 1-start_)) {
                paramsReturn_.add(p);
            }
            String p_ = params_.last();
            paramsReturn_.add(StringExpUtil.getPrettyArrayType(p_));
        } else {
            for (String p: params_.mid(start_)) {
                paramsReturn_.add(p);
            }
        }
        paramsReturn_.add(returnType_);
        return StringList.concat(fctBase_, Templates.TEMPLATE_BEGIN, StringList.join(paramsReturn_, Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }
    private String formatFieldReturn(ContextEl _an, boolean _static, StringList _params, String _returnType, boolean _demand) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        StringList paramsReturn_ = new StringList();
        if (!_static && _demand) {
            paramsReturn_.add(foundClass);
        }
        paramsReturn_.addAllElts(_params);
        paramsReturn_.add(_returnType);
        return StringList.concat(fctBase_, Templates.TEMPLATE_BEGIN, StringList.join(paramsReturn_, Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }

    @Override
    public void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public void setPreviousResultClass(ClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    @Override
    public void setPreviousArgument(Argument _previousArgument) {
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public String getFoundClass() {
        return foundClass;
    }

    public int getAncestor() {
        return ancestor;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public boolean isExpCast() {
        return expCast;
    }

    public boolean isShiftArgument() {
        return shiftArgument;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public ConstructorId getRealId() {
        return realId;
    }

    public ClassField getFieldId() {
        return fieldId;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public boolean isAffField() {
        return affField;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public String getFileName() {
        return fileName;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public int getClassNameOffset() {
        return StringExpUtil.getOffset(className);
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public CustList<PartOffset> getPartOffsetsEnd() {
        return partOffsetsEnd;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getOperatorNumber() {
        return operatorNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }
}
