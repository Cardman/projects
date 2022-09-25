package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ClassFieldBlock;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.accessing.OperatorAccessor;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.assign.blocks.*;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.analyze.files.FileResolver;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.FoundSuperType;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.inherits.OverridesTypeUtil;
import code.expressionlanguage.analyze.opers.AnonymousInstancingOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.IntermediaryResults;
import code.expressionlanguage.analyze.syntax.SplitExpressionUtil;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ClassesUtil {
    private static final char DOT = '.';

    private ClassesUtil(){
    }

    public static void postValidation(AnalyzedPageEl _page) {
        _page.setAnnotAnalysis(false);
        if (!_page.getOptions().isReadOnly()) {
            validateFinals(_page);
        } else {
            validateSimFinals(_page);
        }
        for (RootBlock c: _page.getAllFoundTypes()) {
            globalType(_page, c);
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getRefMappings());
            for (AbsBk b: getDirectChildren(c)) {
                if (b instanceof InternOverrideBlock) {
                    _page.setCurrentBlock(c);
                    ((InternOverrideBlock)b).buildTypes(c, _page);
                }
            }
        }
        validateOverridingInherit(_page);
        AnaTypeUtil.checkInterfaces(_page);
        warnings(_page);
        for (RootBlock e: _page.getAllFoundTypes()) {
            ClassMethodIdReturn resDyn_ = tryGetDeclaredToString(e, _page);
            if (resDyn_ != null) {
                _page.getToStr().addEntry(e,resDyn_);
            }
            ClassMethodIdReturn resDynRand_ = tryGetDeclaredRandCode(e, _page);
            if (resDynRand_ != null) {
                _page.getRandCodes().addEntry(e,resDynRand_);
            }
        }
        customOverrides(_page);
        checkImpls(_page);
    }

    private static void customOverrides(AnalyzedPageEl _page) {
        for (RootBlock e: _page.getAllFoundTypes()) {
            globalType(_page, e);
            _page.setImporting(e);
            _page.setImportingAcces(new TypeAccessor(e.getFullName()));
            _page.setImportingTypes(e);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(e.getRefMappings());
            for (AbsBk b: getDirectChildren(e)) {
                if (AbsBk.isOverBlock(b)) {
                    _page.setCurrentBlock(e);
                    ((NamedCalledFunctionBlock)b).buildTypes(e, _page);
                }
            }
        }
    }

    private static void warnings(AnalyzedPageEl _page) {
        if (!_page.isDisplayUnusedParameterStaticMethod()) {
            return;
        }
        for (RootBlock r: _page.getAllFoundTypes()) {
            for (NamedCalledFunctionBlock o: r.getOverridableBlocks()) {
                if (o.isUsedRefMethod() || o.getKind() != MethodKind.STD_METHOD
                        || MethodId.getKind(o.getModifier()) == MethodAccessKind.INSTANCE) {
                    continue;
                }
                for (EntryCust<String,AnaLocalVariable> e: o.getUsedParameters().entryList()) {
                    AnaLocalVariable var_ = e.getValue();
                    int indexParam_ = var_.getIndexParam();
                    if (!var_.isUsed()) {
                        FoundWarningInterpret d_ = new FoundWarningInterpret();
                        d_.setIndexFile(var_.getRef());
                        d_.setFile(o.getFile());
                        d_.buildWarning(_page.getAnalysisMessages().getUnusedParamStatic(),e.getKey());
                        _page.getLocalizer().addWarning(d_);
                        o.getParamWarns().get(indexParam_).add(d_.getBuiltWarning());
                    }
                }
            }
        }
    }

    private static void checkImpls(AnalyzedPageEl _page) {
        IdMap<NamedCalledFunctionBlock, StringMap<GeneStringOverridable>> anaRed_ = anaRed(_page);
        for (RootBlock r: _page.getAllFoundTypes()) {
            loopImpsType(_page, anaRed_, r);
        }
    }

    private static void loopImpsType(AnalyzedPageEl _page, IdMap<NamedCalledFunctionBlock, StringMap<GeneStringOverridable>> _anaRed, RootBlock _r) {
        _page.setCurrentFile(_r.getFile());
        if (!_r.mustImplement()) {
            return;
        }
        CustList<AnaFormattedRootBlock> allSuperClass_ = _r.getAllGenericSuperTypesInfo();
        for (AnaFormattedRootBlock s: allSuperClass_) {
            String base_ = StringExpUtil.getIdFromAllTypes(s.getFormatted());
            RootBlock superBl_ = s.getRootBlock();
            for (NamedCalledFunctionBlock m: superBl_.getOverridableBlocks()) {
                if (!m.isAbstractMethod()) {
                    continue;
                }
                GeneStringOverridable inf_ = _anaRed.getVal(m).getVal(_r.getFullName());
                if (inf_ == null) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFile(_r.getFile());
                    err_.setIndexFile(_r.getIdRowCol());
                    //type id
                    err_.buildError(
                            _page.getAnalysisMessages().getAbstractMethodImpl(),
                            base_,
                            m.getSignature(_page),
                            _r.getFullName());
                    _page.addLocError(err_);
                    _r.addNameErrors(err_);
                }
            }
        }
    }

    private static IdMap<NamedCalledFunctionBlock, StringMap<GeneStringOverridable>> anaRed(AnalyzedPageEl _page) {
        IdMap<NamedCalledFunctionBlock,StringMap<GeneStringOverridable>> anaRed_;
        anaRed_ = new IdMap<NamedCalledFunctionBlock,StringMap<GeneStringOverridable>>();
        for (RootBlock e: _page.getAllFoundTypes()) {
            for (NamedCalledFunctionBlock o: e.getOverridableBlocks()) {
                if (o.hiddenInstance() || o.isFinalMethod()) {
                    continue;
                }
                StringMap<GeneStringOverridable> map_ = OverridesTypeUtil.getConcreteMethodsToCall(e, o, _page);
                map_.putAllMap(o.getOverrides());
                anaRed_.addEntry(o,map_);
            }
        }
        return anaRed_;
    }

    private static ClassMethodIdReturn tryGetDeclaredToString(RootBlock _class, AnalyzedPageEl _page) {
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        String baseCurName_ = _class.getFullName();
        fetchToStringMethods(_class,baseCurName_,methods_, _page.getToStringMethods());
        return getCustResultExec(methods_, _page);
    }

    private static ClassMethodIdReturn tryGetDeclaredRandCode(RootBlock _class, AnalyzedPageEl _page) {
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        String baseCurName_ = _class.getFullName();
        fetchToStringMethods(_class,baseCurName_,methods_, _page.getRandCodeMethods());
        return getCustResultExec(methods_, _page);
    }

    private static ClassMethodIdReturn getCustResultExec(CustList<MethodInfo> _methods, AnalyzedPageEl _page) {
        Parametrable found_ = getFoundMethodExec(_methods, _page);
        if (!(found_ instanceof MethodInfo)) {
            return null;
        }
        MethodInfo m_ = (MethodInfo) found_;
        MethodId id_ = m_.getFormatted();
        return OperationNode.buildResult(m_,id_);
    }

    private static Parametrable getFoundMethodExec(CustList<MethodInfo> _fct, AnalyzedPageEl _page) {
        CustList<MethodInfo> nonAbs_ = new CustList<MethodInfo>();
        CustList<MethodInfo> finals_ = new CustList<MethodInfo>();
        for (MethodInfo p: _fct) {
            if (!p.isFinalMethod()) {
                continue;
            }
            finals_.add(p);
        }
        if (finals_.size() == 1) {
            return finals_.first();
        }
        for (MethodInfo p: _fct) {
            if (p.isAbstractMethod() || _page.getAnaClassBody(p.getClassName()) instanceof InterfaceBlock) {
                continue;
            }
            nonAbs_.add(p);
        }
        if (nonAbs_.size() == 1) {
            return nonAbs_.first();
        }
        nonAbs_.clear();
        for (MethodInfo p: _fct) {
            if (p.isAbstractMethod()) {
                continue;
            }
            nonAbs_.add(p);
        }
        if (nonAbs_.size() == 1) {
            return nonAbs_.first();
        }
        StringList sub_ = new StringList();
        StringMap<MethodInfo> meths_ = new StringMap<MethodInfo>();
        for (MethodInfo p: _fct) {
            String cl_ = p.getClassName();
            meths_.addEntry(cl_,p);
            sub_.add(cl_);
        }
        sub_ = AnaTypeUtil.getSubclasses(sub_, _page);
        if (!sub_.onlyOneElt()) {
            return null;
        }
        return meths_.getVal(sub_.first());
    }

    private static void fetchToStringMethods(RootBlock _root, String _cl, CustList<MethodInfo> _methods, StringMap<ToStringMethodHeader> _toStringMethods) {
        StringList geneSuperTypes_ = new StringList();
        geneSuperTypes_.add(_cl);
        geneSuperTypes_.addAllElts(_root.getAllSuperTypes());
        for (String t: geneSuperTypes_) {
            ToStringMethodHeader toString_ = _toStringMethods.getVal(t);
            if (toString_ == null) {
                continue;
            }
            _methods.add(buildMethodToStringInfo(toString_, t));
        }
    }

    private static MethodInfo buildMethodToStringInfo(ToStringMethodHeader _m, String _formattedClass) {
        String ret_ = _m.getImportedReturnType();
        MethodId id_ = _m.getId();
        MethodInfo mloc_ = new MethodInfo();
        mloc_.memberId(_m);
        mloc_.setAbstractMethod(_m.isAbstractMethod());
        mloc_.setFinalMethod(_m.isFinalMethod());
        mloc_.classMethodId(_formattedClass,id_);
        mloc_.setReturnType(ret_);
        mloc_.setAncestor(0);
        mloc_.formatWithoutParams();
        return mloc_;
    }
    public static void buildAllBracesBodies(StringMap<String> _files, AnalyzedPageEl _page) {
        tryBuildAllBracedClassesBodies(_files, _page);
        validateInheritingClasses(_page);
        validateIdsOperators(_page);
        validateIds(_page);
        validateEl(_page);
        _page.getSortedOperators().addAllElts(_page.getAllOperators());
        _page.getSortedOperators().sortElts(new AnaOperatorCmp());
        CustList<OperatorBlock> sortedOperators_ = getOperatorBlocks(_page);
        for (OperatorBlock o: sortedOperators_) {
            nbTypesOpers(_page, o);
        }
        _page.getPrevFoundTypes().addAllElts(_page.getFoundTypes());
        _page.getFoundTypes().clear();
        StringList basePkgFound_ = _page.getBasePackagesFound();
        StringList pkgFound_ = _page.getPackagesFound();
        for (OperatorBlock o: _page.getAllOperators()) {
            processType(basePkgFound_,pkgFound_,o,_page);
        }
        processMapping(_page);
        validateInheritingClasses(_page);
        validateIds(_page);
        validateEl(_page);
        _page.getPrevFoundTypes().addAllElts(_page.getFoundTypes());
        _page.getFoundTypes().clear();
        CustList<BracedBlock> brBl_ = new CustList<BracedBlock>();
        for (OperatorBlock c: _page.getAllOperators()) {
            brBl_.add(c);
        }
        procBadIndexes(_page, brBl_);
        globalType(_page);
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            StringList params_ = o.getParametersNames();
            StringList types_ = o.getImportedParametersTypes();
            prepareParams(_page,o.getParametersNamesOffset(),o.getParamErrors(), params_, o.getParametersRef(), types_, o.isVarargs());
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(o.getRefMappings());
            o.buildFctInstructionsReadOnly(_page);
            AnalyzingEl a_ = _page.getAnalysisAss();
            a_.setVariableIssue(_page.isVariableIssue());
            _page.getResultsAnaOperator().addEntry(o,a_);
        }
        _page.setAnnotAnalysis(true);
        globalType(_page);
        _page.setCurrentFct(null);
        for (OperatorBlock o: _page.getAllOperators()) {
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            _page.setCurrentBlock(o);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(o.getRefMappings());
            o.buildAnnotations(_page);
            o.buildAnnotationsParameters(_page);
        }
        _page.setAnnotAnalysis(false);

        processAnonymous(_page);
    }

    private static void nbTypesOpers(AnalyzedPageEl _page, OperatorBlock _o) {
        for (RootBlock c: _o.getAnonymousTypes()) {
            StringMap<Integer> countsAnon_ = _page.getCountsAnon();
            Integer val_ = countsAnon_.getVal(c.getName());
            if (val_ == null) {
                countsAnon_.put(c.getName(),1);
                c.setSuffix("*1");
            } else {
                countsAnon_.put(c.getName(),val_+1);
                c.setSuffix("*"+(val_+1));
            }
        }
        for (RootBlock c: _o.getLocalTypes()) {
            StringMap<Integer> counts_ = _page.getCounts();
            Integer val_ = counts_.getVal(c.getName());
            if (val_ == null) {
                counts_.put(c.getName(),1);
                c.setSuffix("+1");
            } else {
                counts_.put(c.getName(),val_+1);
                c.setSuffix("+"+(val_+1));
            }
        }
    }

    private static CustList<OperatorBlock> getOperatorBlocks(AnalyzedPageEl _page) {
        CustList<OperatorBlock> sortedOperators_;
        if (_page.isSortNbOperators()) {
            sortedOperators_ = _page.getSortedNbOperators();
        } else {
            sortedOperators_ = _page.getSortedOperators();
        }
        return sortedOperators_;
    }

    public static void processAnonymous(AnalyzedPageEl _page) {
        for (IntermediaryResults s:_page.getNextResults()) {
            intermediateResult(_page, s);
        }
    }

    private static void intermediateResult(AnalyzedPageEl _page, IntermediaryResults _s) {
        StringList basePkgFound_ = _page.getBasePackagesFound();
        StringList pkgFound_ = _page.getPackagesFound();
        for (AnonymousInstancingOperation e: listInst(_s.getAnonymousTypes())) {
            AnonymousTypeBlock block_ = e.getBlock();
            String base_ = e.getBase();
            String enumClassName_ = _page.getAliasEnumType();
            String enumParamClassName_ = _page.getAliasEnumParam();
            if (StringUtil.quickEq(enumParamClassName_, base_)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFile(block_.getFile());
                undef_.setIndexFile(e);
                //original type len
                undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                        block_.getFullName(),
                        base_);
                _page.addLocError(undef_);
                block_.addNameErrors(undef_);
            }
            if (StringUtil.quickEq(enumClassName_, base_)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFile(block_.getFile());
                undef_.setIndexFile(e);
                //original type len
                undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                        block_.getFullName(),
                        base_);
                _page.addLocError(undef_);
                block_.addNameErrors(undef_);
            }
        }
        _page.getPrevFoundTypes().addAllElts(_page.getFoundTypes());
        _page.getFoundTypes().clear();
        for (AnonymousTypeBlock a: _s.getAnonymousTypes()) {
            processType(basePkgFound_, pkgFound_, a, _page);
        }
        for (NamedCalledFunctionBlock e: _s.getAnonymousFunctions()) {
            processType(basePkgFound_, pkgFound_, e, _page);
        }
        for (SwitchMethodBlock e: _s.getSwitchMethods()) {
            processType(basePkgFound_, pkgFound_, e, _page);
        }
        processMapping(_page);
        validateInheritingClasses(_page);
        validateIds(_page);
        for (AnonymousInstancingOperation e: listInst(_s.getAnonymousTypes())) {
            _page.setGlobalType(e.getGlType());
            _page.setCurrentFile(e.getBlock().getFile());
            e.postAnalyze(_page);
        }
        validateEl(_page);
        for (NamedCalledFunctionBlock e: _s.getAnonymousFunctions()) {
            _page.setupFctChars(e);
            _page.getCache().getLocalVariables().clear();
            _page.getCache().getLoopVariables().clear();
            _page.getCache().getLocalVariables().addAllElts(e.getCache().getLocalVariables());
            _page.getCache().getLoopVariables().addAllElts(e.getCache().getLoopVariables());
            StringList params_ = e.getParametersNames();
            StringList types_ = e.getImportedParametersTypes();
            prepareParams(_page, e.getParametersNamesOffset(), e.getParamErrors(),params_,e.getParametersRef(), types_, e.isVarargs());
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(e.getRefMappings());
            e.buildFctInstructionsReadOnly(_page);
            AnalyzingEl a_ = _page.getAnalysisAss();
            a_.setVariableIssue(_page.isVariableIssue());
            _page.getResultsMethod().addEntry(e,a_);
        }
        for (SwitchMethodBlock e: _s.getSwitchMethods()) {
            _page.setupFctChars(e);
            _page.getCache().getLocalVariables().clear();
            _page.getCache().getLoopVariables().clear();
            _page.getCache().getLocalVariables().addAllElts(e.getCache().getLocalVariables());
            _page.getCache().getLoopVariables().addAllElts(e.getCache().getLoopVariables());
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(e.getRefMappings());
            e.buildFctInstructionsReadOnly(_page);
            AnalyzingEl a_ = _page.getAnalysisAss();
            a_.setVariableIssue(_page.isVariableIssue());
            _page.getResultsSwMethod().addEntry(e,a_);
        }
        _page.setAnnotAnalysis(true);
        for (NamedCalledFunctionBlock e: _s.getAnonymousFunctions()) {
            _page.setupFctChars(e);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(e.getRefMappings());
            e.buildAnnotations(_page);
            e.buildAnnotationsParameters(_page);
        }
        for (SwitchMethodBlock e: _s.getSwitchMethods()) {
            _page.setupFctChars(e);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(e.getRefMappings());
            e.buildAnnotations(_page);
            e.buildAnnotationsParameters(_page);
        }
        _page.setAnnotAnalysis(false);
    }

    private static CustList<AnonymousInstancingOperation> listInst(CustList<AnonymousTypeBlock> _ls) {
        CustList<AnonymousInstancingOperation> out_ = new CustList<AnonymousInstancingOperation>();
        for (AnonymousTypeBlock a: _ls) {
            AnonymousInstancingOperation instancingOperation_ = a.getInstancingOperation();
            if (instancingOperation_ != null) {
                out_.add(instancingOperation_);
            }
        }
        return out_;
    }

    public static void tryBuildAllBracedClassesBodies(StringMap<String> _files, AnalyzedPageEl _page) {
        StringMap<String> files_ = _page.buildFiles();
        buildFilesBodies(files_,true, _page);
        buildFilesBodies(_files,false, _page);
        parseFiles(_page);
    }

    private static void processBracedClass(boolean _add, RootBlock _root, AnalyzedPageEl _page) {
        String fullName_ = _root.getFullName();
        boolean ok_ = _add;
        if (_page.getSorted().contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_root.getFile());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedType(),
                    fullName_);
            _page.addLocError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        _page.setCurrentBlock(_root);
        String packageName_;
        packageName_ = _root.getPackageName();
        if (packageName_.trim().isEmpty()) {
            FoundErrorInterpret badCl_ = new FoundErrorInterpret();
            badCl_.setFile(_root.getFile());
            badCl_.setIndexFile(_root.getBegin());
            //key word category len
            badCl_.buildError(_page.getAnalysisMessages().getEmptyPackage());
            _page.addLocError(badCl_);
            _root.addNameErrors(badCl_);
            ok_ = false;
        } else {
            StringList elements_ = StringUtil.splitChars(packageName_, DOT);
            for (String e: elements_) {
                String tr_ = e.trim();
                TokenErrorMessage res_ = ManageTokens.partClass(_page).checkToken(tr_, _page);
                if (res_.isError()) {
                    FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                    badCl_.setFile(_root.getFile());
                    badCl_.setIndexFile(_root.getIdRowCol());
                    //pkg part or dot
                    badCl_.setBuiltError(res_.getMessage());
                    _page.addLocError(badCl_);
                    _root.addNameErrors(badCl_);
                    ok_ = false;
                }
            }
        }
        if (!(_root instanceof AnonymousTypeBlock)) {
            String className_;
            className_ = _root.getName().trim();
            TokenErrorMessage resClName_ = ManageTokens.partClass(_page).checkTokenKeyVar(className_, _page);
            if (resClName_.isError()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFile(_root.getFile());
                badCl_.setIndexFile(_root.getIdRowCol());
                //name part if possible or original type
                badCl_.setBuiltError(resClName_.getMessage());
                _page.addLocError(badCl_);
                _root.addNameErrors(badCl_);
                ok_ = false;
            }
        }
        typeVarsInit(_root, _page);
        tryAdd(_root, _page, fullName_, ok_);
    }

    private static void tryAdd(RootBlock _root, AnalyzedPageEl _page, String _fullName, boolean _ok) {
        boolean ok_ = _ok;
        if (_root instanceof EnumBlock) {
            String fullNameOrig_ = _root.getFullName();
            StringBuilder generic_ = new StringBuilder(fullNameOrig_);
            if (!_root.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t: _root.getParamTypes()) {
                    vars_.add(StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,t.getName()));
                }
                generic_.append(StringExpUtil.TEMPLATE_BEGIN);
                generic_.append(StringUtil.join(vars_, StringExpUtil.TEMPLATE_SEP));
                generic_.append(StringExpUtil.TEMPLATE_END);
            }
            StringBuilder sBuild_ = new StringBuilder(_page.getAliasEnumParam());
            sBuild_.append(StringExpUtil.TEMPLATE_BEGIN);
            sBuild_.append(generic_);
            sBuild_.append(StringExpUtil.TEMPLATE_END);
            String type_ = sBuild_.toString();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, BoolVal.FALSE);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof InnerElementBlock) {
            InnerElementBlock i_ = (InnerElementBlock) _root;
            EnumBlock par_ = i_.getParentEnum();
            String type_ = StringUtil.concat(par_.getFullName(),i_.getTempClass());
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, BoolVal.FALSE);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof AnnotationBlock) {
            String type_ = _page.getAliasAnnotationType();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, BoolVal.FALSE);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_page.getStandardsTypes().contains(_fullName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_root.getFile());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedTypeStd(),
                    _fullName);
            _page.addLocError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        if (AnaTypeUtil.isPrimitive(_fullName, _page)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_root.getFile());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedTypePrim(),
                    _fullName);
            _page.addLocError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        if (_root instanceof RootErrorBlock) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_root.getFile());
            b_.setIndexFile(((RootErrorBlock) _root).getCategoryOffset());
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            _root.addErrorBlock(b_.getBuiltError());
        }
        _page.getFoundTypes().add(_root);
        _page.getAllFoundTypes().add(_root);
        _page.getSorted().put(_root.getFullName(), _root);
        if (ok_) {
            _page.addRefFoundType(_root);
        }
        if (_root instanceof AnonymousTypeBlock) {
            int c_ = _page.getCountAnonTypes();
            ((AnonymousTypeBlock) _root).setNumberAnonType(c_);
            _page.setCountAnonTypes(c_+1);
        }
        if (_root instanceof InnerElementBlock) {
            int c_ = _page.getCountInnerEltTypes();
            ((InnerElementBlock) _root).setNumberInner(c_);
            _page.setCountInnerEltTypes(c_+1);
        }
        int c_ = _page.getCountTypes();
        _root.setNumberAll(c_);
        _page.setCountTypes(c_+1);
    }

    private static void typeVarsInit(RootBlock _root, AnalyzedPageEl _page) {
        String fullDef_ = _root.getFullDefinition();
        StringList params_ = StringExpUtil.getAllTypes(fullDef_);
        StringList namesFromParent_ = getParamVarFromParent(_root);
        StringList varTypes_ = new StringList();
        String objectClassName_ = _page.getAliasObject();
        int tempOff_ = _root.getTemplateDefOffset() + 1;
        for (String p: params_.mid(IndexConstants.SECOND_INDEX)) {
            TypeVar type_ = new TypeVar();
            int indexDef_ = p.indexOf(StringExpUtil.EXTENDS_DEF);
            StringList parts_ = StringUtil.splitInTwo(p, indexDef_);
            String id_ = parts_.first();
            id_ = id_.trim();
            int offId_ = tempOff_ + StringUtil.getFirstPrintableCharIndex(p);
            if (id_.isEmpty()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFile(_root.getFile());
                badCl_.setIndexFile(offId_);
                //char after def
                badCl_.buildError(_page.getAnalysisMessages().getEmptyPartClassName());
                _page.addLocError(badCl_);
                StringList constraints_ = new StringList();
                Ints ct_ = new Ints();
                ct_.add(0);
                _root.getParamTypesConstraintsOffset().add(ct_);
                constraints_.add(objectClassName_);
                type_.getErrors().add(badCl_.getBuiltError());
                type_.setConstraints(constraints_);
                type_.setName(id_);
                _root.getParamTypes().add(type_);
                type_.setOffset(offId_);
                type_.setLength(1);
                tempOff_ += p.length() + 1;
                continue;
            }
            TokenErrorMessage res_ = ManageTokens.partVarClass(_page).checkTokenKeyVar(id_, _page);
            if (res_.isError()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFile(_root.getFile());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.setBuiltError(res_.getMessage());
                _page.addLocError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringUtil.contains(varTypes_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFile(_root.getFile());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.buildError(_page.getAnalysisMessages().getDuplicatedPartVarClassName(),
                        id_
                );
                _page.addLocError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringUtil.contains(namesFromParent_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFile(_root.getFile());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.buildError(_page.getAnalysisMessages().getDuplicatedPartVarClassName(),
                        id_
                );
                _page.addLocError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            varTypes_.add(id_);
            StringList constraints_ = new StringList();
            if (indexDef_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
                int begin_ = indexDef_ + tempOff_;
                Ints ct_ = new Ints();
                for (String b: StringUtil.splitChars(parts_.last().substring(1), StringExpUtil.SEP_BOUNDS)) {
                    int off_ = begin_ + StringUtil.getFirstPrintableCharIndex(b);
                    constraints_.add(b);
                    ct_.add(off_);
                    begin_ += b.length() + 1;
                }
                _root.getParamTypesConstraintsOffset().add(ct_);
            } else {
                Ints ct_ = new Ints();
                ct_.add(0);
                _root.getParamTypesConstraintsOffset().add(ct_);
                constraints_.add(objectClassName_);
            }
            type_.setConstraints(constraints_);
            type_.setName(id_);
            _root.getParamTypes().add(type_);
            type_.setOffset(offId_);
            type_.setLength(id_.length());
            tempOff_ += p.length() + 1;
        }
    }

    private static StringList getParamVarFromParent(RootBlock _root) {
        if (_root.withoutInstance()) {
            return new StringList();
        }
        return getParamVarFromAnyParent(_root);
    }

    private static StringList getParamVarFromAnyParent(RootBlock _root) {
        StringList namesFromParent_ = new StringList();
        boolean add_ = true;
        RootBlock r_ = _root;
        while (true) {
            r_ = r_.getParentType();
            if (r_ == null) {
                break;
            }
            if (add_){
                for (TypeVar t: r_.getParamTypes()) {
                    namesFromParent_.add(t.getName());
                }
            }
            if (r_.withoutInstance()) {
                add_ = false;
            }
        }
        return namesFromParent_;
    }

    public static void buildFilesBodies(StringMap<String> _files, boolean _predefined, AnalyzedPageEl _page) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileBlock fileBlock_ = new FileBlock(0,_predefined, file_, new DefaultFileEscapedCalc());
            _page.setCurrentFile(fileBlock_);
            fileBlock_.setNumberFile(_page.getFilesBodies().size());
            _page.putFileBlock(file_, fileBlock_);
            fileBlock_.processLinesTabsWithError(content_, _page);
        }
    }
    public static void parseFiles(AnalyzedPageEl _page) {
        StringMap<FileBlock> files_ = _page.getFilesBodies();
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            FileBlock block_ = f.getValue();
            if (!block_.getBinChars().isEmpty()) {
                continue;
            }
            StringComment stringComment_ = block_.stringComment(_page.getComments());
            block_.metrics(stringComment_);
            String file_ = stringComment_.getFile();
            _page.setCurrentFile(block_);
            FileResolver.parseFile(block_, file_, _page);
        }
        trySortNbOpers(_page);
        StringList basePkgFound_ = _page.getBasePackagesFound();
        StringList pkgFound_ = _page.getPackagesFound();
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            pkgFound_.addAllElts(f.getValue().getAllPackages());
        }
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            basePkgFound_.addAllElts(f.getValue().getAllBasePackages());
        }
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            FileBlock value_ = f.getValue();
            fetchOuterTypesCountOpers(_page, value_);
        }
        _page.setNextResults(SplitExpressionUtil.getNextResults(_page));
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            FileBlock value_ = f.getValue();
            fetchByFile(basePkgFound_, pkgFound_, value_, _page);
        }
        processMapping(_page);
    }

    private static void trySortNbOpers(AnalyzedPageEl _page) {
        StringMap<FileBlock> files_ = _page.getFilesBodies();
        int opCount_ = 0;
        int opCountNb_ = 0;
        StringList list_ = new StringList();
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            FileBlock value_ = f.getValue();
            for (AbsBk b: getDirectChildren(value_)) {
                if (b instanceof OperatorBlock) {
                    OperatorBlock op_ = (OperatorBlock) b;
                    String labelNumber_ = op_.getLabelNumber();
                    if (!labelNumber_.isEmpty()) {
                        list_.add(labelNumber_);
                        opCountNb_++;
                    }
                    opCount_++;
                }
            }
        }
        if (opCountNb_ != opCount_ || list_.hasDuplicates()) {
            return;
        }
        feedNb(_page);
    }

    private static void feedNb(AnalyzedPageEl _page) {
        StringMap<FileBlock> files_ = _page.getFilesBodies();
        CustList<OperatorBlock> opers_ = new CustList<OperatorBlock>();
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            FileBlock value_ = f.getValue();
            for (AbsBk b: getDirectChildren(value_)) {
                if (b instanceof OperatorBlock) {
                    OperatorBlock op_ = (OperatorBlock) b;
                    opers_.add(op_);
                }
            }
        }
        opers_.sortElts(new AnaOperatorLabelCmp());
        _page.getSortedNbOperators().addAllElts(opers_);
        _page.setSortNbOperators(true);
    }

    public static void fetchOuterTypesCountOpers(AnalyzedPageEl _page, FileBlock _value) {
        for (AbsBk b: getDirectChildren(_value)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                _page.getOuterTypes().add(r_);
            }
            if (b instanceof OperatorBlock) {
                OperatorBlock r_ = (OperatorBlock) b;
                _page.getAllOperators().add(r_);
                int c_ = _page.getCountOperators();
                r_.setOperatorNumber(c_);
                _page.setCountOperators(c_+1);
            }
        }
    }

    private static void processMapping(AnalyzedPageEl _page) {
        for (RootBlock r: _page.getFoundTypes()) {
            for (AbsBk b: getDirectChildren(r)) {
                if (b instanceof MemberCallingsBlock) {
                    MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                    MemberCallingsBlock outerFuntion_ = m_.getStrictOuterFuntion();
                    if (outerFuntion_ != null) {
                        m_.getMappings().putAllMap(outerFuntion_.getRefMappings());
                    }
                }
            }
        }
        for (RootBlock r: _page.getFoundTypes()) {
            MemberCallingsBlock outerFuntion_ = r.getStrictOuterFuntion();
            if (outerFuntion_ != null) {
                r.getMappings().putAllMap(outerFuntion_.getRefMappings());
            }
        }
    }

    public static void fetchByFile(StringList _basePkgFound, StringList _pkgFound, FileBlock _anaFile, AnalyzedPageEl _page) {
        _page.setCurrentFile(_anaFile);
        for (AbsBk b: getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processType(_basePkgFound, _pkgFound, r_, _page);
            }
        }
    }

    private static void processType(StringList _basePkgFound, StringList _pkgFound, AbsBk _r, AnalyzedPageEl _page) {
        _page.setCurrentFile(_r.getFile());
        StringList allReservedInnersRoot_ = new StringList();
        boolean addPkg_ = checkPkg(_pkgFound, _r, _page, allReservedInnersRoot_);
        feedReservedInnersRoot(_r, allReservedInnersRoot_);
        AbsBk c_ = _r;
        if (c_.getFirstChild() == null) {
            emptyBlock(_r, _page, addPkg_);
            return;
        }
        StringList simpleNames_ = new StringList();
        while (true) {
            feedReserved(c_);
            if (c_ instanceof RootBlock) {
                proc(_basePkgFound,(RootBlock) c_,allReservedInnersRoot_,simpleNames_,addPkg_,_page);
            }
            AbsBk fc_ = c_.getFirstChild();
            if (fc_ != null) {
                feedSimpleNames(c_, simpleNames_);
                c_ = fc_;
                continue;
            }
            while (true) {
                AbsBk n_ = c_.getNextSibling();
                if (n_ != null) {
                    c_ = n_;
                    break;
                }
                BracedBlock p_ = c_.getParent();
                if (p_ == _r) {
                    return;
                }
                c_ = p_;
                removeSimpleNames(c_, simpleNames_);
            }
        }
    }

    private static void feedReserved(AbsBk _c) {
        if (_c instanceof MemberCallingsBlock) {
            MemberCallingsBlock cur_ = (MemberCallingsBlock) _c;
            cur_.getElements().getReserved().addAllElts(inners(cur_));
        }
    }

    private static void emptyBlock(AbsBk _r, AnalyzedPageEl _page, boolean _addPkg) {
        if (_r instanceof RootBlock) {
            ClassesUtil.processBracedClass(_addPkg, (RootBlock) _r, _page);
        }
    }

    private static void removeSimpleNames(AbsBk _c, StringList _simpleNames) {
        if (_c instanceof RootBlock) {
            _simpleNames.removeLast();
        }
    }

    private static void feedSimpleNames(AbsBk _c, StringList _simpleNames) {
        if (_c instanceof RootBlock) {
            String s_ = ((RootBlock) _c).getName();
            _simpleNames.add(s_);
        }
    }

    private static void feedReservedInnersRoot(AbsBk _r, StringList _allReservedInnersRoot) {
        addReserved(_r, _allReservedInnersRoot);
        if (_r instanceof SwitchMethodBlock) {
            SwitchMethodBlock r_ = (SwitchMethodBlock) _r;
            _allReservedInnersRoot.addAllElts(r_.getAllReservedInners());
        }
        if (_r instanceof OperatorBlock) {
            OperatorBlock r_ = (OperatorBlock) _r;
            _allReservedInnersRoot.addAllElts(r_.getAllReservedInners());
        }
    }

    private static boolean checkPkg(StringList _pkgFound, AbsBk _r, AnalyzedPageEl _page, StringList _allReservedInnersRoot) {
        boolean addPkg_ = true;
        if (_r instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _r;
            _allReservedInnersRoot.addAllElts(r_.getAllReservedInners());
            if (r_.getNameLength() != 0) {
                String fullName_ = r_.getFullName();
                for (String p: _pkgFound) {
                    if (!p.startsWith(fullName_)) {
                        continue;
                    }
                    //ERROR
                    FoundErrorInterpret d_ = new FoundErrorInterpret();
                    d_.setFile(_r.getFile());
                    d_.setIndexFile(r_.getIdRowCol());
                    //original id len
                    d_.buildError(_page.getAnalysisMessages().getDuplicatedTypePkg(),
                            fullName_,
                            p);
                    _page.addLocError(d_);
                    r_.addNameErrors(d_);
                    addPkg_ = false;
                }
            }
        }
        return addPkg_;
    }
    private static void proc(StringList _basePkgFound, RootBlock _r,StringList _allReservedInnersRoot,StringList _simpleNames, boolean _addPkg, AnalyzedPageEl _page) {
        boolean addPkg_ = _addPkg;
        boolean add_ = true;
        for (RootBlock r:accessedClassMembers(_r)){
            _r.getAllReservedInners().add(r.getName());
        }
        for (RootBlock r:accessedInnerElements(_r)){
            _r.getAllReservedInners().add(r.getName());
        }
        RootBlock possibleParent_ = _r.getParentType();
        AccessedBlock operator_ = _r.getAccessedBlock();
        MemberCallingsBlock outerFuntion_ = _r.getOuterFuntionInType();
        _r.getAllReservedInners().addAllElts(_allReservedInnersRoot);
        if (_r instanceof AnonymousTypeBlock) {
            ClassesUtil.processBracedClass(addPkg_, _r, _page);
            return;
        }
        StringList reverv_ = new StringList();
        String parFullName_ = "";
        String parGenericString_ = "";
        if (operator_ != null) {
            StringList allReservedInners_ = operator_.getAllReservedInners();
            reverv_.addAllElts(allReservedInners_);
            _r.getAllReservedInners().addAllElts(allReservedInners_);
        }
        if (possibleParent_ != null) {
            parFullName_ = possibleParent_.getFullName();
            parGenericString_ = possibleParent_.getGenericString();
        }
        if (operator_ != null) {
            add_ = addElt(_r, _allReservedInnersRoot, _page, outerFuntion_, reverv_, parFullName_, parGenericString_);
        }
        String s_ = _r.getName();
        if (StringUtil.contains(_basePkgFound, s_)) {
            //ERROR
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_r.getFile());
            d_.setIndexFile(_r.getIdRowCol());
            //s_ len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                    s_);
            _page.addLocError(d_);
            _r.addNameErrors(d_);
            add_ = false;
            addPkg_ = false;
        } else if (StringUtil.contains(_simpleNames, s_)) {
            //ERROR
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_r.getFile());
            d_.setIndexFile(_r.getIdRowCol());
            //s_ len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                    s_);
            _page.addLocError(d_);
            _r.addNameErrors(d_);
            add_ = false;
        }
        ClassesUtil.processBracedClass(addPkg_&&add_, _r, _page);
    }

    private static boolean addElt(RootBlock _r, StringList _allReservedInnersRoot, AnalyzedPageEl _page, MemberCallingsBlock _outerFuntion, StringList _reserv, String _parFullName, String _parGenericString) {
        boolean add_ = true;
        String s_ = _r.getName();
        if (StringUtil.contains(_allReservedInnersRoot, s_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_r.getFile());
            d_.setIndexFile(_r.getIdRowCol());
            //s_ len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                    s_);
            _page.addLocError(d_);
            _r.addNameErrors(d_);
            add_ = false;
        }
        if (_outerFuntion != null) {
            for (RootBlock o : _outerFuntion.getElements().getReserved()) {
                _r.getAllReservedInners().add(o.getName());
            }
            if (StringUtil.contains(_reserv, s_)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFile(_r.getFile());
                d_.setIndexFile(_r.getIdRowCol());
                //s_ len
                d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                        s_);
                _page.addLocError(d_);
                _r.addNameErrors(d_);
                add_ = false;
            }
            StringList namesFromParent_ = getParamVarFromAnyParent(_r);
            if (StringUtil.contains(namesFromParent_, s_)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFile(_r.getFile());
                d_.setIndexFile(_r.getIdRowCol());
                //s_ len
                d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                        s_);
                _page.addLocError(d_);
                _r.addNameErrors(d_);
                add_ = false;
            }
            StringMap<MappingLocalType> mappings_ = _outerFuntion.getMappings();
            MappingLocalType resolved_ = mappings_.getVal(s_);
            if (resolved_ != null) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFile(_r.getFile());
                d_.setIndexFile(_r.getIdRowCol());
                //s_ len
                d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                        s_);
                _page.addLocError(d_);
                _r.addNameErrors(d_);
                add_ = false;
            } else {
                mappings_.put(s_, new MappingLocalType(_r.getFullName(), _r.getSuffixedName(), _r, _parFullName, _parGenericString));
            }
        }
        return add_;
    }

    private static void addReserved(AbsBk _r, StringList _allReservedInnersRoot) {
        if (AbsBk.isAnonBlock(_r)) {
            NamedCalledFunctionBlock r_ = (NamedCalledFunctionBlock) _r;
            _allReservedInnersRoot.addAllElts(r_.getAllReservedInners());
        }
    }

    private static CustList<RootBlock> inners(MemberCallingsBlock _caller) {
        if (_caller.getFirstChild() == null) {
            return new CustList<RootBlock>();
        }
        return innerNotEmpty(_caller);
    }

    private static CustList<RootBlock> innerNotEmpty(MemberCallingsBlock _caller) {
        AbsBk c_ = _caller;
        CustList<RootBlock> list_ = new CustList<RootBlock>();
        while (true) {
            if (c_ instanceof RootBlock) {
                RootBlock cur_ = (RootBlock) c_;
                list_.add(cur_);
            } else {
                AbsBk fc_ = c_.getFirstChild();
                if (fc_ != null) {
                    c_ = fc_;
                    continue;
                }
            }
            while (true) {
                AbsBk n_ = c_.getNextSibling();
                if (n_ != null) {
                    c_ = n_;
                    break;
                }
                BracedBlock p_ = c_.getParent();
                if (p_ == _caller) {
                    return list_;
                }
                c_ = p_;
            }
        }
    }

    public static CustList<AbsBk> getDirectChildren(AbsBk _element) {
        CustList<AbsBk> list_ = new CustList<AbsBk>();
        if (_element == null) {
            return list_;
        }
        AbsBk elt_ = _element.getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public static void validateInheritingClasses(AnalyzedPageEl _page) {
        _page.getListTypesNames().clear();
        validateInheritingClassesId(_page);
        CustList<RootBlock> listTypes_ = _page.getListTypesNames();
        for (RootBlock s: listTypes_) {
            _page.setCurrentBlock(s);
            _page.setCurrentFile(s.getFile());
            s.buildDirectGenericSuperTypes(_page);
        }
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setCurrentBlock(c);
            _page.setCurrentFile(c.getFile());
            c.buildMapParamType(_page);
        }
        for (RootBlock c: _page.getFoundTypes()) {
            checkSuperTypes(_page, c);
        }
        validateSingleParameterizedClasses(_page);
        checkTemplatesDef(_page);
    }

    private static void checkSuperTypes(AnalyzedPageEl _page, RootBlock _c) {
        _page.setCurrentFile(_c.getFile());
        if (_c.withoutInstance()) {
            return;
        }
        StringList allDirectSuperTypes_ = new StringList();
        CustList<RootBlock> allAncestors_ = new CustList<RootBlock>();
        RootBlock p_ = _c.getParentType();
        while (p_ != null) {
            allAncestors_.add(p_);
            p_ = p_.getParentType();
        }
        for (AnaResultPartType s: _c.getResults()) {
            RootBlock s_ = _page.getAnaClassBody(StringExpUtil.getIdFromAllTypes(s.getResult()));
            if (s_ == null || s_.withoutInstance()) {
                continue;
            }
            allDirectSuperTypes_.add(s_.getFullName());
        }
        StringList allPossibleDirectSuperTypes_ = allPossibleDirectSuperTypes(_page, _c, allAncestors_);
        if (!allPossibleDirectSuperTypes_.containsAllObj(allDirectSuperTypes_)) {
            for (String s: allDirectSuperTypes_) {
                FoundErrorInterpret enum_;
                enum_ = new FoundErrorInterpret();
                enum_.setFile(_c.getFile());
                enum_.setIndexFile(_c.getIdRowCol());
                //super type len
                enum_.buildError(_page.getAnalysisMessages().getBadInheritsType(),
                        _c.getFullName(),
                        s);
                _page.addLocError(enum_);
                _c.addNameErrors(enum_);
            }
        }
    }

    private static StringList allPossibleDirectSuperTypes(AnalyzedPageEl _page, RootBlock _c, CustList<RootBlock> _allAncestors) {
        StringList allPossibleDirectSuperTypes_ = new StringList();
        MemberCallingsBlock outerFct_ = _c.getOuterFuntionInType();
        feedReserved(outerFct_, allPossibleDirectSuperTypes_);
        for (RootBlock a: _allAncestors) {
            MemberCallingsBlock outerFctAnc_ = a.getOuterFuntionInType();
            feedReserved(outerFctAnc_, allPossibleDirectSuperTypes_);
            feedInners(a, allPossibleDirectSuperTypes_);
            for (String s: a.getAllSuperTypes()) {
                RootBlock g_ = _page.getAnaClassBody(s);
                if (g_ == null) {
                    continue;
                }
                feedInners(g_, allPossibleDirectSuperTypes_);
            }
        }
        return allPossibleDirectSuperTypes_;
    }

    private static void feedInners(RootBlock _r, StringList _allPossibleDirectSuperTypes) {
        for (AbsBk m: getDirectChildren(_r)) {
            if (!(m instanceof RootBlock)) {
                continue;
            }
            RootBlock m_ = (RootBlock) m;
            _allPossibleDirectSuperTypes.add(m_.getFullName());
        }
    }

    private static void feedReserved(MemberCallingsBlock _outerFct, StringList _allPossibleDirectSuperTypes) {
        if (_outerFct != null) {
            for (RootBlock r: _outerFct.getElements().getReserved()) {
                _allPossibleDirectSuperTypes.add(r.getFullName());
            }
        }
    }

    public static void validateInheritingClassesId(AnalyzedPageEl _page) {
        StringMap<BoolVal> builtTypes_ = new StringMap<BoolVal>();
        IdList<RootBlock> stClNames_ = new IdList<RootBlock>(_page.getFoundTypes());
        for (RootBlock r: stClNames_) {
            builtTypes_.addEntry(r.getFullName(), BoolVal.FALSE);
        }
        for (RootBlock r: _page.getPrevFoundTypes()) {
            builtTypes_.addEntry(r.getFullName(), BoolVal.TRUE);
        }
        while (true) {
            IdList<RootBlock> next_ = new IdList<RootBlock>();
            for (RootBlock r: stClNames_) {
//                ExecRootBlock exec_ = _page.getMapTypes().getVal(r);
                tryBuildInherits(_page, builtTypes_, next_, r);
            }
            if (next_.isEmpty()) {
                for (RootBlock r: stClNames_) {
                    _page.getListTypesNames().add(r);
                    _page.setCurrentFile(r.getFile());
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFile(r.getFile());
                    undef_.setIndexFile(r.getIdRowCol());
                    //id len
                    undef_.buildError(_page.getAnalysisMessages().getUnknownSuperType(),
                            r.getFullName());
                    _page.addLocError(undef_);
                    r.addNameErrors(undef_);
                }
                break;
            }
            stClNames_.removeAllElements(next_);
        }
    }

    private static void tryBuildInherits(AnalyzedPageEl _page, StringMap<BoolVal> _builtTypes, IdList<RootBlock> _next, RootBlock _r) {
        String objectClassName_ = _page.getAliasObject();
        String annotName_ = _page.getAliasAnnotationType();
        String c= _r.getFullName();
        _page.setCurrentFile(_r.getFile());
        if (_r instanceof AnnotationBlock) {
            StringMap<Integer> foundNames_ = new StringMap<Integer>();
            IntMap<String> rowColDirectSuperTypes_ = _r.getRowColDirectSuperTypes();
            int len_ = rowColDirectSuperTypes_.size();
            for (int i = 0; i < len_; i++) {
                String s = _r.getRowColDirectSuperTypes().getValue(i);
                s = StringExpUtil.removeDottedSpaces(s);
                String idSuper_ = StringExpUtil.getIdFromAllTypes(s);
                int offset_ = _r.getRowColDirectSuperTypes().getKey(i);
                if (_r.getExplicitDirectSuperTypes().getValue(i) == BoolVal.TRUE) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFile(_r.getFile());
                    undef_.setIndexFile(offset_);
                    //idSuper_ len
                    undef_.buildError(_page.getAnalysisMessages().getBadInheritsType(),
                            c,
                            idSuper_);
                    _page.addLocError(undef_);
                    _r.addNameErrors(undef_);
                    continue;
                }
                foundNames_.addEntry(annotName_,offset_);
            }
            for (EntryCust<String,Integer> e: foundNames_.entryList()) {
                String k_ = e.getKey();
                int ind_ = e.getValue();
                _r.getImportedDirectBaseSuperTypes().put(ind_,k_);
            }
            _r.getAllSuperTypes().addAllElts(foundNames_.getKeys());
//                    exec_.getAllSuperTypes().addAllElts(foundNames_.getKeys());
            _r.getAllSuperTypes().add(objectClassName_);
//                    exec_.getAllSuperTypes().add(objectClassName_);
            _r.getAllSuperTypes().removeDuplicates();
//                    exec_.getAllSuperTypes().removeDuplicates();
            _page.getListTypesNames().add(_r);
            _builtTypes.set(c, BoolVal.TRUE);
            _next.add(_r);
            return;
        }
        CustList<FoundSuperType> types_ = new CustList<FoundSuperType>();
        boolean ready_ = ready(_page, _builtTypes, _r, c, types_);
        if (!ready_) {
            return;
        }
        CustList<String> dup_ = new CustList<String>();
        CustList<AnaGeneType> dupTypes_ = new CustList<AnaGeneType>();
        for (FoundSuperType f: types_) {
            dup_.add(f.getName());
            dupTypes_.add(f.getType());
        }
        boolean hasDuplicates_ = hasDuplicates(_page, _r, c, dup_);
        if (hasDuplicates_) {
            return;
        }
        int nbDirectSuperClass_ = nbDirectSuperClass(_page, _r, c, types_);
        if (nbDirectSuperClass_ > 1) {
            FoundErrorInterpret enum_;
            enum_ = new FoundErrorInterpret();
            enum_.setFile(_r.getFile());
            enum_.setIndexFile(_r.getIdRowCol());
            //second super class
            enum_.buildError(_page.getAnalysisMessages().getSuperClass(),
                    c,Long.toString(nbDirectSuperClass_));
            _page.addLocError(enum_);
            _r.addNameErrors(enum_);
        }
        _r.getAllSuperTypes().addAllElts(dup_);
        _r.getAllSuperTypesInfo().addAllElts(dupTypes_);
//                exec_.getAllSuperTypes().addAllElts(dup_);
        for (FoundSuperType f: types_) {
            RootBlock s_ = f.getType();
//                    exec_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
            _r.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
            _r.getAllSuperTypesInfo().addAllElts(s_.getAllSuperTypesInfo());
        }
        _r.getAllSuperTypes().add(objectClassName_);
//                exec_.getAllSuperTypes().add(objectClassName_);
        _r.getAllSuperTypes().removeDuplicates();
//                exec_.getAllSuperTypes().removeDuplicates();
        _page.getListTypesNames().add(_r);
        _builtTypes.set(c, BoolVal.TRUE);
        _next.add(_r);
    }

    private static int nbDirectSuperClass(AnalyzedPageEl _page, RootBlock _r, String _c, CustList<FoundSuperType> _types) {
        int nbDirectSuperClass_ = 0;
        for (FoundSuperType f: _types) {
            RootBlock s_ = f.getType();
            if (s_ instanceof UniqueRootedBlock) {
                nbDirectSuperClass_++;
            }
            tryPutSuperType(_page, _r, _c,f);
        }
        return nbDirectSuperClass_;
    }

    private static boolean hasDuplicates(AnalyzedPageEl _page, RootBlock _r, String _c, CustList<String> _dup) {
        StringMap<Integer> counts_ = count(_dup);
        boolean hasDuplicates_ = false;
        for (EntryCust<String,Integer> e: counts_.entryList()) {
            if (e.getValue() > 1) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFile(_r.getFile());
                undef_.setIndexFile(_r.getIdRowCol());
                //original type len
                undef_.buildError(_page.getAnalysisMessages().getDuplicateSuper(),
                        _c,e.getKey(),Long.toString(e.getValue()));
                _page.addLocError(undef_);
                _r.addNameErrors(undef_);
                hasDuplicates_ = true;
            }
        }
        return hasDuplicates_;
    }

    private static boolean ready(AnalyzedPageEl _page, StringMap<BoolVal> _builtTypes, RootBlock _r, String _c, CustList<FoundSuperType> _types) {
        IntMap<String> rowColDirectSuperTypes_ = _r.getRowColDirectSuperTypes();
        int len_ = rowColDirectSuperTypes_.size();
        boolean ready_ = true;
        for (int i = 0; i < len_; i++) {
            if (notReady(_page, _builtTypes, _r, _c,i, _types)) {
                ready_ = false;
                break;
            }
        }
        return ready_;
    }

    private static StringMap<Integer> count(CustList<String> _dup) {
        StringMap<Integer> counts_ = new StringMap<Integer>();
        for (String s: _dup) {
            counts_.put(s,0);
        }
        for (String s: _dup) {
            counts_.put(s, counts_.getVal(s)+1);
        }
        return counts_;
    }

    private static boolean notReady(AnalyzedPageEl _page, StringMap<BoolVal> _builtTypes, RootBlock _r, String _c, int _index, CustList<FoundSuperType> _types) {

        String enumClassName_ = _page.getAliasEnumType();
        String enumParamClassName_ = _page.getAliasEnumParam();
        String s = _r.getRowColDirectSuperTypes().getValue(_index);
        s = StringExpUtil.removeDottedSpaces(s);
        String idSuper_ = StringExpUtil.getIdFromAllTypes(s);
        int offset_ = _r.getRowColDirectSuperTypes().getKey(_index);
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(idSuper_, void_)) {
            FoundErrorInterpret undef_ = new FoundErrorInterpret();
            undef_.setFile(_r.getFile());
            undef_.setIndexFile(offset_);
            //_in len
            undef_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.addLocError(undef_);
            _r.addNameErrors(undef_);
        } else if (_r.getExplicitDirectSuperTypes().getValue(_index) == BoolVal.TRUE && _page.getStandardsTypes().contains(idSuper_)) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFile(_r.getFile());
            undef_.setIndexFile(offset_);
            //idSuper_ len
            undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                    _c,
                    idSuper_);
            _page.addLocError(undef_);
            _r.addNameErrors(undef_);
        } else {
            String foundType_ = foundInheritType(_page, _builtTypes, _r, _index, idSuper_);
            RootBlock superType_ = _page.getAnaClassBody(foundType_);
            if (superType_ == null) {
                return true;
            }
            FoundSuperType f_ = new FoundSuperType();
            f_.setType(superType_);
            f_.setLocation(offset_);
            f_.setName(foundType_);
            _types.add(f_);
            if (_r.getExplicitDirectSuperTypes().getValue(_index) == BoolVal.TRUE && (StringUtil.quickEq(enumParamClassName_, foundType_) || StringUtil.quickEq(enumClassName_, foundType_) && !StringUtil.quickEq(_c, enumParamClassName_))) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFile(_r.getFile());
                undef_.setIndexFile(offset_);
                //original type len
                undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                        _c,
                        foundType_);
                _page.addLocError(undef_);
                _r.addNameErrors(undef_);
            }
        }
        return false;
    }

    private static void tryPutSuperType(AnalyzedPageEl _page, RootBlock _r, String _c, FoundSuperType _f) {
        int offset_ = _f.getLocation();
        String k_ = _f.getName();
        int ind_ = _f.getLocation();
        RootBlock s_ = _f.getType();
        if (_r.withoutInstance()) {
            if (!s_.withoutInstance()) {
                FoundErrorInterpret enum_;
                enum_ = new FoundErrorInterpret();
                enum_.setFile(_r.getFile());
                enum_.setIndexFile(offset_);
                //original k_ string len
                enum_.buildError(_page.getAnalysisMessages().getBadInheritsTypeInn(),
                        _c,
                        k_);
                _page.addLocError(enum_);
                _r.addNameErrors(enum_);
            }
        } else {
            int subSise_ = _r.getSelfAndParentTypes().size();
            int supSise_ = s_.getSelfAndParentTypes().size();
            if (supSise_ > subSise_) {
                FoundErrorInterpret enum_;
                enum_ = new FoundErrorInterpret();
                enum_.setFile(_r.getFile());
                enum_.setIndexFile(offset_);
                //original k_ string len
                enum_.buildError(_page.getAnalysisMessages().getBadInheritsTypeAsInn(),
                        _c,
                        k_,
                        Long.toString(subSise_-1L),
                        Long.toString(supSise_-1L));
                _page.addLocError(enum_);
                _r.addNameErrors(enum_);
            }
        }
        if (_r instanceof InterfaceBlock) {
            if (!(s_ instanceof InterfaceBlock)) {
                FoundErrorInterpret enum_;
                enum_ = new FoundErrorInterpret();
                enum_.setFile(_r.getFile());
                enum_.setIndexFile(offset_);
                //original type len
                enum_.buildError(_page.getAnalysisMessages().getBadInheritsTypeInt(),
                        _c, k_);
                _page.addLocError(enum_);
                _r.addNameErrors(enum_);
            }
            _r.getImportedDirectBaseSuperTypes().put(ind_, k_);
            return;
        }
        if (_r instanceof InnerTypeOrElement) {
            _r.getImportedDirectBaseSuperTypes().put(ind_, k_);
            return;
        }
        if (ContextUtil.isFinalType(s_)) {
            FoundErrorInterpret enum_;
            enum_ = new FoundErrorInterpret();
            enum_.setFile(_r.getFile());
            enum_.setIndexFile(offset_);
            //original type len
            enum_.buildError(_page.getAnalysisMessages().getFinalType(),
                    _c, k_);
            _page.addLocError(enum_);
            _r.addNameErrors(enum_);
        }
        _r.getImportedDirectBaseSuperTypes().put(ind_, k_);
    }

    private static String foundInheritType(AnalyzedPageEl _page, StringMap<BoolVal> _builtTypes, RootBlock _r, int _index, String _candidate) {
        StringList readyTypes_ = new StringList();
        for (EntryCust<String, BoolVal> f : _builtTypes.entryList()) {
            if (f.getValue() == BoolVal.TRUE) {
                readyTypes_.add(f.getKey());
            }
        }
        String foundType_;
        if (_r.getExplicitDirectSuperTypes().getValue(_index) == BoolVal.TRUE) {
            foundType_ = ResolvingSuperTypes.resolveBaseInherits(_candidate, _r, readyTypes_, _page);
        } else {
            InheritReadyTypes inh_ = new InheritReadyTypes(readyTypes_);
            if (inh_.isReady(_candidate)) {
                foundType_ = _candidate;
            } else {
                foundType_ = "";
            }
        }
        return foundType_;
    }

    private static void checkTemplatesDef(AnalyzedPageEl _page) {
        for (RootBlock s: _page.getFoundTypes()) {
            checkTypeTempDef(_page, s);
//            for (AnaResultPartType t: s.getResults()) {
//                AnaPartTypeUtil.processAnalyzeConstraintsRep(t, s.getSuperTypesParts(), _page);
//            }
//                if (!AnaPartTypeUtil.processAnalyzeConstraints(t, map_, true, s.getSuperTypesParts(), _page)) {
//                    FoundErrorInterpret un_ = new FoundErrorInterpret();
//                    un_.setFile(s.getFile());
//                    un_.setIndexFile(s.getIdRowCol());
//                    // char : before super type
//                    buildErr(_page, t, un_);
//                    _page.addLocError(un_);
//                    s.addNameErrors(un_);
//                }
        }
    }

    private static void checkTypeTempDef(AnalyzedPageEl _page, RootBlock _s) {
        _page.setCurrentFile(_s.getFile());
        String c = _s.getFullName();
        Mapping mapping_ = new Mapping();
        StringMap<StringList> cts_ = new StringMap<StringList>();
        StringList variables_ = new StringList();
        for (TypeVar t: _s.getParamTypesMapValues()) {
            cts_.put(t.getName(), t.getConstraints());
            variables_.add(t.getName());
        }
        if (!variables_.isEmpty() && _s instanceof AnnotationBlock) {
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFile(_s.getFile());
            b_.setIndexFile(_s.getIdRowCol());
            //first < after type id
            b_.buildError(_page.getAnalysisMessages().getAnnotationParam(),
                    c);
            _page.addLocError(b_);
            _s.addNameErrors(b_);
            return;
        }
        mapping_.setMapping(cts_);
        StringList cyclic_ = mapping_.getCyclic();
        if (!cyclic_.isEmpty()) {
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFile(_s.getFile());
            b_.setIndexFile(_s.getIdRowCol());
            //first < after type id
            b_.buildError(_page.getAnalysisMessages().getCyclicMapping(),
                    c);
            _page.addLocError(b_);
            _s.addNameErrors(b_);
            return;
        }
        for (TypeVar t: _s.getParamTypesMapValues()) {
            ok(_page, _s, cts_, t);
        }
        checkConstraints(_page, _s);
    }

    private static void ok(AnalyzedPageEl _page, RootBlock _s, StringMap<StringList> _cts, TypeVar _t){
        String objectClassName_ = _page.getAliasObject();
        StringList upper_ = Mapping.getAllUpperBounds(_cts, _t.getName(), objectClassName_);
        StringList upperNotObj_ = new StringList();
        for (String b: upper_) {
            checkBoundsTypes(_page, _s, b);
            upperNotObj_.add(b);
        }
        checkDuplicatesFct(_page, _s, upper_);
    }
    private static void checkBoundsTypes(AnalyzedPageEl _page, RootBlock _s, String _b) {
        if (_b.startsWith("[")) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_s.getFile());
            un_.setIndexFile(_s.getIdRowCol());
            //type var len => at def
            un_.buildError(_page.getAnalysisMessages().getUnexpectedTypeBound(),
                    _b);
            _page.addLocError(un_);
            _s.addNameErrors(un_);
        }
        if (AnaTypeUtil.isPrimitive(_b, _page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_s.getFile());
            un_.setIndexFile(_s.getIdRowCol());
            //type var len => at def
            un_.buildError(_page.getAnalysisMessages().getUnexpectedTypeBound(),
                    _b);
            _page.addLocError(un_);
            _s.addNameErrors(un_);
        }
    }

    private static void checkDuplicatesFct(AnalyzedPageEl _page, RootBlock _s, StringList _upper) {
        for (CustList<TypeInfo> g: OperationNode.typeLists(_upper,MethodAccessKind.INSTANCE, _page)) {
            StringList all_ = new StringList();
            for (TypeInfo i: g) {
                all_.add(i.getFormatted().getFormatted());
            }
            checkDupl(_page, _s, all_);

        }
    }

    private static void checkConstraints(AnalyzedPageEl _page, RootBlock _s) {
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        for (TypeVar t: _s.getParamTypesMapValues()) {
            map_.put(t.getName(), t.getConstraints());
        }
        for (TypeVar t: _s.getParamTypes()) {
            for (AnaResultPartType b: t.getResults()) {
                if (!AnaPartTypeUtil.processAnalyzeConstraintsCore(b, map_, true, _page)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_s.getFile());
                    un_.setIndexFile(_s.getIdRowCol());
                    //type var len => at def
                    un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                            b.getResult());
                    _page.addLocError(un_);
//                        s.addNameErrors(un_);
                }
            }
        }
        for (AnaResultPartType t: _s.getResults()) {
            if (!AnaPartTypeUtil.processAnalyzeConstraintsCore(t, map_, true, _page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_s.getFile());
                un_.setIndexFile(_s.getIdRowCol());
                // char : before super type
                buildErr(_page, t, un_);
                _page.addLocError(un_);
//                    s.addNameErrors(un_);
            }
        }
    }

    private static void buildErr(AnalyzedPageEl _page, AnaResultPartType _t, FoundErrorInterpret _un) {
        if (_t.getResult().isEmpty()) {
            _un.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _page.getAliasObject());
        } else {
            _un.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _t.getResult());
        }
    }

    private static void checkDupl(AnalyzedPageEl _page, RootBlock _s, StringList _all) {
        StringMap<StringList> baseParams_ = getBaseParams(_all);
        for (EntryCust<String, StringList> e: baseParams_.entryList()) {
            if (!e.getValue().onlyOneElt()) {
                FoundErrorInterpret duplicate_;
                duplicate_ = new FoundErrorInterpret();
                duplicate_.setFile(_s.getFile());
                duplicate_.setIndexFile(_s.getIdRowCol());
                //type var len => at def
                duplicate_.buildError(_page.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                        StringUtil.join(e.getValue(), ExportCst.JOIN_TYPES));
                _page.addLocError(duplicate_);
                _s.addNameErrors(duplicate_);
            }
        }
    }

    private static void validateSingleParameterizedClasses(AnalyzedPageEl _page) {
        for (RootBlock i: _page.getFoundTypes()) {
            _page.setCurrentFile(i.getFile());
            CustList<AnaFormattedRootBlock> genericSuperTypes_ = i.fetchAllGenericSuperTypes();
            checkDupl(_page, i, RootBlock.allGenericClasses(genericSuperTypes_));
            i.getAllGenericSuperTypesInfo().addAllElts(genericSuperTypes_);
            CustList<AnaFormattedRootBlock> genericClasses_ = i.fetchAllGenericClasses();
            i.getAllGenericClassesInfo().addAllElts(genericClasses_);
        }

    }

    private static StringMap<StringList> getBaseParams(StringList _genericSuperTypes) {
        StringMap<StringList> baseParams_ = new StringMap<StringList>();
        for (String t: _genericSuperTypes) {
            String key_ = StringExpUtil.getIdFromAllTypes(t);
            if (baseParams_.contains(key_)) {
                baseParams_.getVal(key_).add(t);
            } else {
                baseParams_.put(key_, new StringList(t));
            }
        }
        return baseParams_;
    }

    public static void validateIds(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getFoundTypes()) {
            globalType(_page, c);
            _page.setCurrentPkg(c.getPackageName());
            _page.setCurrentFile(c.getFile());
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getRefMappings());
            c.validateIds(_page);
            if (c.getNbOperators() > 0) {
                _page.getTypesWithInnerOperators().add(c.getFullName());
            }
        }
    }

    private static void validateIdsOperators(AnalyzedPageEl _page) {
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        globalType(_page);
        for (OperatorBlock o: _page.getAllOperators()) {
            loopExternalOperators(_page, idMethods_, o);
        }
    }

    private static void loopExternalOperators(AnalyzedPageEl _page, CustList<MethodId> _allOps, OperatorBlock _o) {
        _page.setCurrentFile(_o.getFile());
        String name_ = _o.getName();
        _page.setImporting(_o);
        _page.setImportingAcces(new OperatorAccessor());
        _page.setImportingTypes(_o);
        _page.getMappingLocal().clear();
        _o.buildImportedTypes(_page);
        if (!StringExpUtil.isOper(name_)) {
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(_page.getCurrentFile());
            badMeth_.setIndexFile(_o.getNameOffset());
            //key word len
            badMeth_.buildError(_page.getAnalysisMessages().getBadOperatorName(),
                    name_);
            _page.addLocError(badMeth_);
            _o.addNameErrors(badMeth_);
        }
        MethodId id_ = _o.getId();
        for (MethodId m: _allOps) {
            if (m.eq(id_)) {
                FoundErrorInterpret duplicate_;
                duplicate_ = new FoundErrorInterpret();
                duplicate_.setIndexFile(_o.getOffset());
                duplicate_.setFile(_page.getCurrentFile());
                //key word len
                duplicate_.buildError(_page.getAnalysisMessages().getDuplicateOperator(),
                        id_.getSignature(_page.getDisplayedStrings()));
                _page.addLocError(duplicate_);
                _o.addNameErrors(duplicate_);
            }
        }
        _allOps.add(id_);
        StringList l_ = _o.getParametersNames();
        StringList seen_ = new StringList();
        int i_ = 0;
        for (String v: l_) {
            _o.addParamErrors();
            _o.addParamWarns();
            TokenErrorMessage res_ = ManageTokens.partParam(_page).checkToken(v, _page);
            if (res_.isError()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFile(_page.getCurrentFile());
                b_.setIndexFile(_o.getOffset());
                //param name len
                b_.setBuiltError(res_.getMessage());
                _page.addLocError(b_);
                _o.addParamErrors(i_,b_);
            }
            if (StringUtil.contains(seen_, v)){
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFile(_page.getCurrentFile());
                b_.setIndexFile(_o.getOffset());
                //param name len
                b_.buildError(_page.getAnalysisMessages().getDuplicatedParamName(),
                        v);
                _page.addLocError(b_);
                _o.addParamErrors(i_,b_);
            } else {
                seen_.add(v);
            }
            i_++;
        }
        if (_o.isRetRef() && StringUtil.quickEq(_o.getImportedReturnType(), _page.getAliasVoid())) {
            int r_ = _o.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(_o.getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                    _o.getSignature(_page),
                    _page.getAliasVoid());
            _page.addLocError(badMeth_);
            _o.addNameErrors(badMeth_);
        }
    }

    public static void validateOverridingInherit(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getAllFoundTypes()) {
            _page.setCurrentFile(c.getFile());
            c.setupBasicOverrides(_page);
        }
        for (RootBlock c: _page.getAllFoundTypes()) {
            _page.setCurrentFile(c.getFile());
            c.checkCompatibility(_page);
            c.checkImplements(_page);
        }
        for (RootBlock c: _page.getAllFoundTypes()) {
            _page.setCurrentFile(c.getFile());
            c.checkCompatibilityBounds(_page);
        }
    }

    public static CustList<RootBlock> accessedClassMembers(RootBlock _clOwner) {
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        for (AbsBk b: getDirectChildren(_clOwner)) {
            if (!(b instanceof RootBlock) || b instanceof InnerElementBlock) {
                continue;
            }
            RootBlock r_ = (RootBlock) b;
            inners_.add(r_);
        }
        return inners_;
    }


    public static CustList<RootBlock> accessedInnerElements(RootBlock _clOwner) {
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        for (AbsBk b: getDirectChildren(_clOwner)) {
            if (!(b instanceof InnerElementBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) b;
            inners_.add(r_);
        }
        return inners_;
    }

    //validate el and its possible returned type
    public static void validateEl(AnalyzedPageEl _page) {
        initStaticFields(_page);
        CustList<BracedBlock> brBl_ = new CustList<BracedBlock>();
        for (RootBlock c: _page.getFoundTypes()) {
            brBl_.add(c);
        }
        procBadIndexes(_page, brBl_);
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                assignementCheck(_page, b);
            }
            allStaticFielsByType(_page, c, bl_);
        }
        _page.setAssignedStaticFields(true);
        for (RootBlock c: _page.getFoundTypes()) {
            CustList<AbsBk> bl_ = allInstanceFieldsByType(_page, c);
            processInterfaceCtor(c, bl_, _page);
            allCtorsByType(_page, c, bl_);
        }
        loopOverrides(_page);
        loopAnnots(_page);
        globalType(_page);
        _page.setCurrentFct(null);
        _page.setAnnotAnalysis(false);
        //init annotations here
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setCurrentFile(c.getFile());
            c.validateConstructors(_page);
        }
    }

    private static void assignementCheck(AnalyzedPageEl _page, AbsBk _b) {
        if (!(_b instanceof InfoBlock)) {
            return;
        }
        InfoBlock f_ = (InfoBlock) _b;
        StringList fieldNames_ = f_.getElements().getFieldName();
        _page.getAllDeclaredFields().addAllElts(fieldNames_);
        if (!f_.isStaticField()) {
            return;
        }
        if (!(f_ instanceof FieldBlock)) {
            _page.getAssignedDeclaredFields().addAllElts(fieldNames_);
            return;
        }
        FieldBlock field_ = (FieldBlock) f_;
        _page.getAssignedDeclaredFields().addAllElts(field_.getAssignedDeclaredFields());
    }

    private static void allStaticFielsByType(AnalyzedPageEl _page, RootBlock _c, CustList<AbsBk> _bl) {
        for (AbsBk b: _bl) {
            if (b instanceof InnerTypeOrElement) {
                globalType(_page, _c);
                _page.setCurrentPkg(_c.getPackageName());
                _page.setCurrentFile(_c.getFile());
                _page.setCurrentFct(null);
                InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                _page.setCurrentBlock(b);
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(_c.getRefMappings());
                method_.buildExpressionLanguageReadOnly(_page);
            }
            if (b instanceof FieldBlock) {
                globalType(_page, _c);
                _page.setCurrentPkg(_c.getPackageName());
                _page.setCurrentFile(_c.getFile());
                FieldBlock method_ = (FieldBlock) b;
                if (!method_.isStaticField()) {
                    continue;
                }
                _page.setCurrentBlock(b);
                _page.setCurrentFct(null);
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(_c.getRefMappings());
                method_.buildExpressionLanguageReadOnly(_page);
            }
            if (b instanceof StaticBlock) {
                globalType(_page, _c);
                _page.setCurrentPkg(_c.getPackageName());
                _page.setCurrentFile(_c.getFile());
                StaticBlock method_ = (StaticBlock) b;
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(method_.getRefMappings());
                method_.buildFctInstructionsReadOnly(_page);
                AnalyzingEl a_ = _page.getAnalysisAss();
                a_.setVariableIssue(_page.isVariableIssue());
                _page.getResultsAna().addEntry(method_,a_);
            }
        }
    }

    private static CustList<AbsBk> allInstanceFieldsByType(AnalyzedPageEl _page, RootBlock _c) {
        _page.setImporting(_c);
        _page.setImportingAcces(new TypeAccessor(_c.getFullName()));
        _page.setImportingTypes(_c);
        _page.getInitFields().clear();
        _page.getAssignedDeclaredFields().clear();
        _page.getAllDeclaredFields().clear();
        CustList<AbsBk> bl_ = getDirectChildren(_c);
        for (AbsBk b: bl_) {
            if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                _page.getAllDeclaredFields().addAllElts(method_.getElements().getFieldName());
                if (method_.isStaticField()) {
                    _page.getAssignedDeclaredFields().addAllElts(method_.getElements().getFieldName());
                    continue;
                }
            }
            if (b instanceof FieldBlock) {
                _page.getAssignedDeclaredFields().addAllElts(((FieldBlock)b).getAssignedDeclaredFields());
            }
        }
        elInstanceFields(_page, _c, bl_);
        return bl_;
    }

    private static void elInstanceFields(AnalyzedPageEl _page, RootBlock _c, CustList<AbsBk> _bl) {
        for (AbsBk b: _bl) {
            if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                if (method_.isStaticField()) {
                    continue;
                }
            }
            if (b instanceof FieldBlock) {
                globalType(_page, _c);
                _page.setCurrentPkg(_c.getPackageName());
                _page.setCurrentFile(_c.getFile());
                FieldBlock method_ = (FieldBlock) b;
                _page.setCurrentBlock(b);
                _page.setCurrentFct(null);
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(_c.getRefMappings());
                method_.buildExpressionLanguageReadOnly(_page);
            }
            if (b instanceof InstanceBlock) {
                globalType(_page, _c);
                _page.setCurrentPkg(_c.getPackageName());
                _page.setCurrentFile(_c.getFile());
                InstanceBlock method_ = (InstanceBlock) b;
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(method_.getRefMappings());
                method_.buildFctInstructionsReadOnly(_page);
                AnalyzingEl a_ = _page.getAnalysisAss();
                a_.setVariableIssue(_page.isVariableIssue());
                _page.getResultsAnaInst().addEntry(method_,a_);
            }
        }
    }

    private static void allCtorsByType(AnalyzedPageEl _page, RootBlock _c, CustList<AbsBk> _bl) {
        for (AbsBk b: _bl) {
            if (b instanceof ConstructorBlock) {
                _page.getInitFieldsCtors().clear();
                _page.getInitFieldsCtors().addAllElts(_page.getInitFields());
                globalType(_page, _c);
                _page.setCurrentPkg(_c.getPackageName());
                _page.setCurrentFile(_c.getFile());
                ConstructorBlock method_ = (ConstructorBlock) b;
                StringList params_ = method_.getParametersNames();
                StringList types_ = method_.getImportedParametersTypes();
                prepareParams(_page, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, method_.getParametersRef(), types_, method_.isVarargs());
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(method_.getRefMappings());
                method_.buildFctInstructionsReadOnly(_page);
                AnalyzingEl a_ = _page.getAnalysisAss();
                a_.setVariableIssue(_page.isVariableIssue());
                _page.getResultsAnaNamed().addEntry(method_,a_);
            }
        }
    }

    private static void loopOverrides(AnalyzedPageEl _page) {
        _page.setAssignedFields(true);
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                if (!AbsBk.isOverBlock(b)) {
                    continue;
                }
                NamedCalledFunctionBlock method_ = (NamedCalledFunctionBlock) b;
                if (isStdOrExplicit(method_)) {
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(_page,method_.getParametersNamesOffset(), method_.getParamErrors(),params_, method_.getParametersRef(), types_, method_.isVarargs());
                    method_.getUsedParameters().addAllEntries(_page.getInfosVars());
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getRefMappings());
                    method_.buildFctInstructionsReadOnly(_page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaMethod().addEntry(method_,a_);
                } else {
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                     StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(_page, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, method_.getParametersRef(), types_, method_.isVarargs());
                    processValueParam(_page, method_);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getRefMappings());
                    method_.buildFctInstructionsReadOnly(_page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaMethod().addEntry(method_,a_);
                }
            }
        }
    }

    private static void loopAnnots(AnalyzedPageEl _page) {
        _page.setAnnotAnalysis(true);
        for (RootBlock c: _page.getFoundTypes()) {
            loopAnnots(_page, c);
        }
    }

    private static void loopAnnots(AnalyzedPageEl _page, RootBlock _c) {
        _page.setImporting(_c);
        _page.setImportingAcces(new TypeAccessor(_c.getFullName()));
        _page.setImportingTypes(_c);
        globalType(_page, _c);
        _page.setCurrentPkg(_c.getPackageName());
        _page.setCurrentFile(_c.getFile());
        _page.setCurrentFct(null);
        CustList<AbsBk> annotated_ = new CustList<AbsBk>();
        if (!(_c instanceof InnerElementBlock)) {
            annotated_.add(_c);
        }
        annotated_.addAllElts(getDirectChildren(_c));
        _page.getMappingLocal().clear();
        _page.getMappingLocal().putAllMap(_c.getRefMappings());
        for (AbsBk b:annotated_) {
            _page.setCurrentBlock(b);
            if (AbsBk.isAnnotBlock(b)) {
                ((NamedCalledFunctionBlock)b).buildExpressionLanguage(_page);
            }
            if (b instanceof NamedFunctionBlock) {
                ((NamedFunctionBlock)b).buildAnnotations(_page);
                ((NamedFunctionBlock)b).buildAnnotationsParameters(_page);
            }
            if (b instanceof NamedCalledFunctionBlock) {
                ((NamedCalledFunctionBlock)b).buildAnnotationsSupp(_page);
            }
            if (b instanceof RootBlock) {
                ((RootBlock)b).buildAnnotations(_page);
            } else if (b instanceof InfoBlock) {
                ((InfoBlock)b).buildAnnotations(_page);
            }
        }
    }

    public static void globalType(AnalyzedPageEl _page, AccessedBlock _c) {
        if (_c instanceof RootBlock) {
            _page.setGlobalType(new AnaFormattedRootBlock((RootBlock) _c));
            _page.setCurrentFile(_c.getFile());
        }
    }

    private static void procBadIndexes(AnalyzedPageEl _page, CustList<BracedBlock> _braced) {
        for (BracedBlock c: _braced) {
            _page.setCurrentFile(c.getFile());
            for (int i: c.getBadIndexesGlobal()) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFile(c.getFile());
                int indexErr_ = NumberUtil.max(0, NumberUtil.min(c.getFile().getLength() - 1, i));
                b_.setIndexFile(indexErr_);
                //underline index char
                b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
                _page.addLocError(b_);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(b_,indexErr_);
                g_.setLength(1);
                c.getGlobalErrorsPars().getLi().add(g_);
            }
            for (int i: c.getBadIndexes()) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFile(c.getFile());
                b_.setIndexFile(i);
                //underline index char
                b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
                _page.addLocError(b_);
                c.addErrorBlock(b_.getBuiltError());
            }
        }
    }

    private static void validateFinals(AnalyzedPageEl _page) {
        AssignedVariablesBlock assVars_ = new AssignedVariablesBlock();
        _page.setAssignedStaticFields(false);
        _page.setAssignedFields(false);
        for (RootBlock c: _page.getAllFoundTypes()) {
            checkFinalsStaticFields(_page, assVars_, c);

        }
        _page.setAssignedStaticFields(true);
        for (RootBlock c: _page.getAllFoundTypes()) {
            checkFinalsInstanceFields(_page, assVars_, c);
        }
        _page.setAssignedFields(true);
        assVars_.getFinalVariablesGlobal().getFields().clear();
        assVars_.getFinalVariablesGlobal().getFieldsRoot().clear();
        assVars_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
        assVars_.getFinalVariablesGlobal().getFieldsBefore().clear();
        StringMap<AssignmentBefore> b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
        b_.clear();

        for (RootBlock c: _page.getAllFoundTypes()) {
            _page.setImporting(c);
            globalType(_page,c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = tryGetAss(b, _page.getResultsAnaMethod());
                if (b instanceof NamedFunctionBlock && anAss_ != null) {
                    NamedFunctionBlock m_ = (NamedFunctionBlock) b;
                    AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
                    tryAnalyseAssign(assVars_, null, anAss_, assign_, _page);
                    _page.clearAllLocalVars(assVars_);
                }
            }
        }
        for (EntryCust<NamedCalledFunctionBlock, AnalyzingEl> e: _page.getResultsMethod().entryList()) {
            NamedCalledFunctionBlock method_ = e.getKey();
            _page.setupFctChars(method_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), method_);
            tryAnalyseAssign(assVars_, null, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
        for (EntryCust<SwitchMethodBlock, AnalyzingEl> e: _page.getResultsSwMethod().entryList()) {
            SwitchMethodBlock method_ = e.getKey();
            _page.setupFctChars(method_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), method_);
            tryAnalyseAssign(assVars_, null, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
        assVars_.setCache(new AnaCache());
        globalType(_page);
        _page.setCurrentPkg("");
        _page.setCurrentFile(null);
        for (EntryCust<OperatorBlock, AnalyzingEl> e: _page.getResultsAnaOperator().entryList()) {
            NamedFunctionBlock m_ = e.getKey();
            AnalyzingEl anAss_ = e.getValue();
            AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
            tryAnalyseAssign(assVars_, null, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
    }

    private static void checkFinalsInstanceFields(AnalyzedPageEl _page, AssignedVariablesBlock _assVars, RootBlock _c) {
        _page.setImporting(_c);
        globalType(_page, _c);
        _page.setImportingAcces(new TypeAccessor(_c.getFullName()));
        _page.setImportingTypes(_c);
        _page.getInitFields().clear();
        _page.getAssignedDeclaredFields().clear();
        _page.getAllDeclaredFields().clear();
        String fullName_ = _c.getFullName();
        CustList<AbsBk> bl_ = getDirectChildren(_c);
        StringMap<AssignmentBefore> ass_ = ass(bl_);
        StringMap<AssignmentBefore> b_ = _assVars.getFinalVariablesGlobal().getFieldsRootBefore();
        _assVars.getFinalVariablesGlobal().getFields().clear();
        _assVars.getFinalVariablesGlobal().getFieldsRoot().clear();
        _assVars.getFinalVariablesGlobal().getFieldsRootBefore().clear();
        _assVars.getFinalVariablesGlobal().getFieldsBefore().clear();
        b_.clear();
        b_.putAllMap(ass_);
        boolean hasCtor_ = hasCtor(bl_);
        StringMap<SimpleAssignment> assAfter_;
        assAfter_ = new StringMap<SimpleAssignment>();
        finFct(_page, _assVars, bl_, assAfter_);
        b_ = _assVars.getFinalVariablesGlobal().getFieldsRootBefore();
        b_.clear();
        if (!hasCtor_) {
            for (EntryCust<String, SimpleAssignment> a : assAfter_.entryList()) {
                String fieldName_ = a.getKey();
                ClassField key_ = new ClassField(fullName_, fieldName_);
                if (!ContextUtil.isFinalField(key_, _page) || StringUtil.contains(_page.getInitFields(), fieldName_)) {
                    continue;
                }
                //error
                for (AbsBk b : bl_) {
                    if (b instanceof InfoBlock && StringUtil.contains(((InfoBlock) b).getElements().getFieldName(), fieldName_)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_c.getFile());
                        un_.setIndexFile(b.getOffset());
                        un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                                fieldName_, fullName_);
                        _page.addLocError(un_);
                    }
                }
            }
        }
        b_.putAllMap(AssignmentsUtil.assignSimpleBefore(assAfter_));
        finFcts(_page, _assVars, _c, fullName_, bl_);
    }

    private static void finFcts(AnalyzedPageEl _page, AssignedVariablesBlock _assVars, RootBlock _c, String _fullName, CustList<AbsBk> _bl) {
        for (AbsBk b: _bl) {
            finFct(_page, _assVars, _c, _fullName, b);
        }
    }

    private static void finFct(AnalyzedPageEl _page, AssignedVariablesBlock _assVars, RootBlock _c, String _fullName, AbsBk _b) {
        AnalyzingEl anAss_ = tryGetAss(_b, _page.getResultsAnaNamed());
        if (_b instanceof NamedFunctionBlock && anAss_ != null) {
            NamedFunctionBlock m_ = (NamedFunctionBlock) _b;
            AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
            tryAnalyseAssign(_assVars, null, anAss_, assign_, _page);
            StringMap<SimpleAssignment> fieldsRoot_ = getFieldsRoot(_assVars, assign_);
            for (EntryCust<String, SimpleAssignment> f: fieldsRoot_.entryList()) {
                String fieldName_ = f.getKey();
                ClassField key_ = new ClassField(_fullName, fieldName_);
                if (!ContextUtil.isFinalField(key_, _page) || StringUtil.contains(_page.getInitFieldsCtors(), fieldName_)) {
                    continue;
                }
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_c.getFile());
                un_.setIndexFile(m_.getNameOffset());
                un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                        fieldName_, _fullName);
                _page.addLocError(un_);
            }
            _page.clearAllLocalVars(_assVars);
        }
    }

    private static void finFct(AnalyzedPageEl _page, AssignedVariablesBlock _assVars, CustList<AbsBk> _bl, StringMap<SimpleAssignment> _assAfter) {
        AssBlock pr_ = null;
        for (AbsBk b: _bl) {
            if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                if (method_.isStaticField()) {
                    continue;
                }
            }
            AssBlock val_ = _page.getFieldsAss().getVal(b);
            if (val_ instanceof AssInfoBlock) {
                AssInfoBlock aInfo_ = (AssInfoBlock) val_;
                aInfo_.setAssignmentBeforeAsLeaf(_assVars,pr_);
                aInfo_.buildExpressionLanguage(_assVars, _page);
                aInfo_.setAssignmentAfterAsLeaf(_assVars,pr_, _page);
                _assAfter.putAllMap(_assVars.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                pr_ = (AssBlock) aInfo_;
            }
            AnalyzingEl anAss_ = tryGetAss(b, _page.getResultsAnaInst());
            if (b instanceof MemberCallingsBlock && anAss_ != null) {
                MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
                tryAnalyseAssign(_assVars, pr_, anAss_, assign_, _page);
                _assAfter.putAllMap(getFieldsRoot(_assVars, assign_));
                _page.clearAllLocalVars(_assVars);
                pr_ = assign_;
            }
        }
    }

    private static boolean hasCtor(CustList<AbsBk> _bl) {
        boolean hasCtor_ = false;
        for (AbsBk b: _bl) {
            if (b instanceof ConstructorBlock) {
                hasCtor_ = true;
                break;
            }
        }
        return hasCtor_;
    }

    private static StringMap<AssignmentBefore> ass(CustList<AbsBk> _bl) {
        StringMap<AssignmentBefore> ass_;
        ass_ = new StringMap<AssignmentBefore>();
        for (AbsBk b: _bl) {
            if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                if (method_.isStaticField()) {
                    continue;
                }
            }
            if (b instanceof FieldBlock) {
                InfoBlock f_ = (InfoBlock) b;
                for (String f: f_.getElements().getFieldName()) {
                    AssignmentBefore as_ = new AssignmentBefore();
                    as_.setUnassignedBefore(true);
                    ass_.put(f, as_);
                }
            }
        }
        return ass_;
    }

    private static void checkFinalsStaticFields(AnalyzedPageEl _page, AssignedVariablesBlock _assVars, RootBlock _c) {
        _page.setImporting(_c);
        _page.setImportingAcces(new TypeAccessor(_c.getFullName()));
        _page.setImportingTypes(_c);
        globalType(_page, _c);
        _page.getInitFields().clear();
        _page.getAssignedDeclaredFields().clear();
        _page.getAllDeclaredFields().clear();
        String fullName_ = _c.getFullName();
        CustList<AbsBk> bl_ = getDirectChildren(_c);
        StringMap<AssignmentBefore> ass_;
        ass_ = new StringMap<AssignmentBefore>();
        feedInfos(_page, bl_);
        for (AbsBk b: bl_) {
            initSt(ass_, b);
        }
        StringMap<AssignmentBefore> b_ = _assVars.getFinalVariablesGlobal().getFieldsRootBefore();
        b_.clear();
        _assVars.getFinalVariablesGlobal().getFields().clear();
        _assVars.getFinalVariablesGlobal().getFieldsRoot().clear();
        _assVars.getFinalVariablesGlobal().getFieldsRootBefore().clear();
        _assVars.getFinalVariablesGlobal().getFieldsBefore().clear();
        b_.putAllMap(ass_);
        StringMap<SimpleAssignment> assAfter_;
        assAfter_ = new StringMap<SimpleAssignment>();
        AssBlock pr_ = null;
        for (AbsBk b: bl_) {
            AssBlock val_ = _page.getFieldsAssSt().getVal(b);
            if (val_ instanceof AssInfoBlock) {
                AssInfoBlock aInfo_ = (AssInfoBlock) val_;
                aInfo_.setAssignmentBeforeAsLeaf(_assVars,pr_);
                aInfo_.buildExpressionLanguage(_assVars, _page);
                aInfo_.setAssignmentAfterAsLeaf(_assVars,pr_, _page);
                assAfter_.putAllMap(_assVars.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                pr_ = (AssBlock) aInfo_;
            }
            AnalyzingEl anAss_ = tryGetAss(b, _page.getResultsAna());
            if (b instanceof MemberCallingsBlock && anAss_ != null) {
                MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
                tryAnalyseAssign(_assVars, pr_, anAss_, assign_, _page);
                assAfter_.putAllMap(getFieldsRoot(_assVars, assign_));
                _page.clearAllLocalVars(_assVars);
                pr_ = assign_;
            }
        }
        for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
            String key_ = a.getKey();
            ClassField id_ = new ClassField(fullName_, key_);
            if (!ContextUtil.isFinalField(id_, _page)) {
                continue;
            }
            if (!StringUtil.contains(_page.getInitFields(),key_)) {
                //error
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_c.getFile());
                un_.setIndexFile(_c.getOffset());
                un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                        key_,fullName_);
                _page.addLocError(un_);
            }
        }
    }

    private static void initSt(StringMap<AssignmentBefore> _ass, AbsBk _b) {
        if (!(_b instanceof InfoBlock)) {
            return;
        }
        InfoBlock f_ = (InfoBlock) _b;
        if (!f_.isStaticField()) {
            return;
        }
        for (String f: f_.getElements().getFieldName()) {
            AssignmentBefore as_ = new AssignmentBefore();
            as_.setUnassignedBefore(true);
            _ass.put(f, as_);
        }
    }

    private static void feedInfos(AnalyzedPageEl _page, CustList<AbsBk> _bl) {
        for (AbsBk b: _bl) {
            if (!(b instanceof InfoBlock)) {
                continue;
            }
            InfoBlock f_ = (InfoBlock) b;
            if (f_ instanceof InnerElementBlock) {
                _page.getFieldsAssSt().addEntry(b,new AssElementBlock((InnerTypeOrElement) f_));
            }
            if (f_ instanceof FieldBlock) {
                if (!f_.isStaticField()) {
                    _page.getFieldsAss().addEntry(b,new AssFieldBlock((FieldBlock) f_));
                } else {
                    _page.getFieldsAssSt().addEntry(b,new AssFieldBlock((FieldBlock) f_));
                }
            }
        }
    }

    public static void globalType(AnalyzedPageEl _page) {
        _page.setGlobalType(AnaFormattedRootBlock.defValue());
    }

    private static void tryAnalyseAssign(AssignedVariablesBlock _assVars, AssBlock _pr, AnalyzingEl _anAss, AssMemberCallingsBlock _assign, AnalyzedPageEl _page) {
        if (!_anAss.isVariableIssue()) {
            _assign.buildFctInstructions(_pr,_assVars, _page);
        }
    }

    private static void validateSimFinals(AnalyzedPageEl _page) {
        AssignedVariablesBlock assVars_ = new AssignedVariablesBlock();
        _page.setAssignedStaticFields(false);
        _page.setAssignedFields(false);
        for (RootBlock c: _page.getAllFoundTypes()) {
            initAssignements(_page, c);
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = tryGetAss(b, _page.getResultsAna());
                anaAssignmentsMem(_page, assVars_, b, anAss_);
            }
        }
        _page.setAssignedStaticFields(true);
        for (RootBlock c: _page.getAllFoundTypes()) {
            _page.setImporting(c);
            globalType(_page,c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = tryGetAss(b, _page.getResultsAnaInst());
                anaAssignmentsMem(_page, assVars_, b, anAss_);
            }
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = tryGetAss(b, _page.getResultsAnaNamed());
                anaAssignmentsMem(_page, assVars_, b, anAss_);
            }
        }
        _page.setAssignedFields(true);

        for (RootBlock c: _page.getAllFoundTypes()) {
            _page.setImporting(c);
            globalType(_page,c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = tryGetAss(b, _page.getResultsAnaMethod());
                anaAssignmentsMem(_page, assVars_, b, anAss_);
            }
        }

        for (EntryCust<NamedCalledFunctionBlock, AnalyzingEl> e: _page.getResultsMethod().entryList()) {
            NamedCalledFunctionBlock method_ = e.getKey();
            _page.setupFctChars(method_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            anaAssignmentsMem(_page,assVars_,method_,anAss_);
        }
        for (EntryCust<SwitchMethodBlock, AnalyzingEl> e: _page.getResultsSwMethod().entryList()) {
            SwitchMethodBlock method_ = e.getKey();
            _page.setupFctChars(method_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            anaAssignmentsMem(_page,assVars_,method_,anAss_);
        }
        assVars_.setCache(new AnaCache());
        globalType(_page);
        _page.setCurrentPkg("");
        for (EntryCust<OperatorBlock, AnalyzingEl> e: _page.getResultsAnaOperator().entryList()) {
            NamedFunctionBlock m_ = e.getKey();
            AnalyzingEl anAss_ = e.getValue();
            _page.setCurrentFile(m_.getFile());
            anaAssignmentsMem(_page,assVars_,m_,anAss_);
        }
    }

    private static void anaAssignmentsMem(AnalyzedPageEl _page, AssignedVariablesBlock _assVars, AbsBk _b, AnalyzingEl _anAss) {
        if (_b instanceof MemberCallingsBlock && _anAss != null) {
            anaAssignmentsMem(_page,_assVars,(MemberCallingsBlock)_b,_anAss);
        }
    }

    private static void anaAssignmentsMem(AnalyzedPageEl _page, AssignedVariablesBlock _assVars, MemberCallingsBlock _b, AnalyzingEl _anAss) {
        AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(_anAss.getCanCompleteNormally(), _anAss.getCanCompleteNormallyGroup(), _b);
        tryAnalyseAssign(_assVars, _anAss, assign_, _page);
        _page.clearAllLocalVars(_assVars);
    }

    private static AnalyzingEl tryGetAss(AbsBk _b, IdMap<MemberCallingsBlock, AnalyzingEl> _resultsAnaMethod) {
        AnalyzingEl anAss_ = null;
        if (_b instanceof MemberCallingsBlock) {
            anAss_ = _resultsAnaMethod.getVal((MemberCallingsBlock) _b);
        }
        return anAss_;
    }

    private static void initAssignements(AnalyzedPageEl _page, RootBlock _c) {
        _page.setImporting(_c);
        _page.setImportingAcces(new TypeAccessor(_c.getFullName()));
        _page.setImportingTypes(_c);
        globalType(_page, _c);
        _page.getInitFields().clear();
        _page.getAssignedDeclaredFields().clear();
        _page.getAllDeclaredFields().clear();
    }

    private static void tryAnalyseAssign(AssignedVariablesBlock _assVars, AnalyzingEl _anAss, AssSimStdMethodBlock _assign, AnalyzedPageEl _page) {
        if (!_anAss.isVariableIssue()) {
            _assign.buildFctInstructions(_assVars, _page);
        }
    }

    private static StringMap<SimpleAssignment> getFieldsRoot(AssignedVariablesBlock _assVars, AssMemberCallingsBlock _res) {
        AssignedVariables val_ = _assVars.getFinalVariables().getVal(_res);
        if (val_ == null) {
            return new StringMap<SimpleAssignment>();
        }
        return val_.getFieldsRoot();
    }

    private static boolean isStdOrExplicit(NamedCalledFunctionBlock _method) {
        return _method.getKind() == MethodKind.STD_METHOD || _method.getKind() == MethodKind.TO_STRING || _method.getKind() == MethodKind.EXPLICIT_CAST || _method.getKind() == MethodKind.IMPLICIT_CAST
                || _method.getKind() == MethodKind.TRUE_OPERATOR || _method.getKind() == MethodKind.FALSE_OPERATOR || _method.getKind() == MethodKind.RAND_CODE;
    }

    private static void processValueParam(AnalyzedPageEl _page, NamedCalledFunctionBlock _method) {
        if (_method.getKind() == MethodKind.SET_INDEX) {
            String p_ = _page.getKeyWords().getKeyWordValue();
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(_method.getReturnTypeGet());
            lv_.setConstType(ConstType.PARAM);
            lv_.setFinalVariable(true);
            lv_.setKeyWord(true);
            _page.getInfosVars().put(p_, lv_);
        }
    }

    private static void processInterfaceCtor(RootBlock _cl, CustList<AbsBk> _blocks, AnalyzedPageEl _page) {
        if (_cl instanceof RecordBlock) {
            return;
        }
        boolean hasCtor_ = false;
        for (AbsBk b: _blocks) {
            if (b instanceof ConstructorBlock) {
                hasCtor_ = true;
                break;
            }
        }
        StringList filteredCtor_ = new StringList();
        if (_cl instanceof UniqueRootedBlock) {
            StringList all_ = _cl.getAllSuperTypes();
            StringList allCopy_ = new StringList(all_);
            String superClass_ = _cl.getImportedDirectGenericSuperClass();
            String superClassId_ = StringExpUtil.getIdFromAllTypes(superClass_);
            RootBlock superType_ = _page.getAnaClassBody(superClassId_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringUtil.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            for (String i: allCopy_) {
                AnaTypeUtil.feedInst(_page,filteredCtor_,i);
            }
        }
        _page.getNeedInterfaces().clear();
        _page.getNeedInterfaces().addAllElts(filteredCtor_);
        if (!hasCtor_ && !filteredCtor_.isEmpty()) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFile(_cl.getFile());
            undef_.setIndexFile(_cl.getIdRowCol());
            //original id len
            undef_.buildError(_page.getAnalysisMessages().getMustCallIntCtor(),
                    _cl.getFullName());
            _page.addLocError(undef_);
            _cl.addNameErrors(undef_);
        }
    }

    private static void prepareParams(AnalyzedPageEl _page, Ints _offs, CustList<StringList> _paramErrors, StringList _params, CustList<BoolVal> _refParams, StringList _types, boolean _varargs) {
        int len_ = _params.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (!_paramErrors.get(i).isEmpty()) {
                continue;
            }
            String c_ = _types.get(i);
            if (_varargs && i + 1 == len_) {
                c_ = StringExpUtil.getPrettyArrayType(c_);
            }
            String p_ = _params.get(i);
            buildParam(_page, _offs, _refParams, i, p_, c_);
        }
    }

    private static void buildParam(AnalyzedPageEl _page, Ints _offs, CustList<BoolVal> _refParams, int _i, String _p, String _c) {
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(_c);
        lv_.setRef(_offs.get(_i));
        lv_.setIndexParam(_i);
        if (_refParams.get(_i) == BoolVal.TRUE) {
            lv_.setConstType(ConstType.REF_PARAM);
        } else {
            lv_.setConstType(ConstType.PARAM);
            lv_.setFinalVariable(true);
        }
        _page.getInfosVars().put(_p, lv_);
    }

    private static void initStaticFields(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getFoundTypes()) {
            String fullName_ = c.getFullName();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            StringMap<Struct> cl_ = new StringMap<Struct>();
            for (AbsBk b: bl_) {
                initStaticValues(cl_, b);
            }
            _page.getStaticFields().put(fullName_, cl_);
        }
        IdMap<ClassField,ClassFieldBlock> cstFields_ = new IdMap<ClassField,ClassFieldBlock>();
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                cstValues(_page, cstFields_, c, b);
            }
        }
        reduce(_page, cstFields_);
    }

    private static void cstValues(AnalyzedPageEl _page, IdMap<ClassField, ClassFieldBlock> _cstFields, RootBlock _c, AbsBk _b) {
        if (!(_b instanceof FieldBlock)) {
            return;
        }
        FieldBlock f_ = (FieldBlock) _b;
        if (!f_.isStaticField() || !f_.isFinalField()) {
            return;
        }
        globalType(_page, _c);
        _page.setCurrentPkg(_c.getPackageName());
        _page.setCurrentFile(_c.getFile());
        _page.setCurrentBlock(f_);
        _page.getMappingLocal().clear();
        _page.getMappingLocal().putAllMap(_c.getRefMappings());
        _page.setCurrentFct(null);
        CustList<OperationNode> list_ = f_.buildExpressionLanguageQuickly(_page);
        String cl_ = _c.getFullName();
        for (String f: f_.getElements().getFieldName()) {
            ClassField k_ = new ClassField(cl_, f);
            _cstFields.addEntry(k_,new ClassFieldBlock(list_,f_));
        }
    }

    private static void initStaticValues(StringMap<Struct> _values, AbsBk _b) {
        if (!(_b instanceof InfoBlock)) {
            return;
        }
        InfoBlock i_ = (InfoBlock) _b;
        if (!i_.isStaticField()) {
            return;
        }
        for (String f: i_.getElements().getFieldName()) {
            _values.put(f, null);
        }
    }

    private static void reduce(AnalyzedPageEl _page, IdMap<ClassField, ClassFieldBlock> _cstFields) {
        while (true) {
            boolean calculatedValue_ = false;
            for (EntryCust<ClassField,ClassFieldBlock> e: _cstFields.entryList()) {
                ClassField k_ = e.getKey();
                if (NumParsers.getStaticField(k_, _page.getStaticFields()) == null) {
                    ClassFieldBlock cf_ = e.getValue();
                    FieldBlock f_ = cf_.getFieldName();
                    _page.setCurrentFile(f_.getFile());
                    CustList<OperationNode> ops_ = cf_.getClassName();
                    ReachOperationUtil.tryCalculate(f_, ops_, k_.getFieldName(), _page);
                    if (NumParsers.getStaticField(k_, _page.getStaticFields()) != null) {
                        calculatedValue_ = true;
                        break;
                    }
                }
            }
            if (!calculatedValue_) {
                break;
            }
        }
    }


    public static CustList<NamedCalledFunctionBlock> getMethodAnnotationBodiesById(RootBlock _r, String _id) {
        CustList<NamedCalledFunctionBlock> methods_ = new CustList<NamedCalledFunctionBlock>();
        for (NamedCalledFunctionBlock b: _r.getValidMethods()) {
            if (StringUtil.quickEq(b.getName(), _id)) {
                methods_.add(b);
            }
        }
        return methods_;
    }

}
