package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ClassFieldBlock;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.accessing.OperatorAccessor;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.inherits.*;
import code.expressionlanguage.analyze.instr.PartOffset;
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
import code.expressionlanguage.analyze.assign.blocks.*;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.analyze.files.FileResolver;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.core.IndexConstants;
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
        if (_page.isDisplayUnusedParameterStaticMethod()) {
            for (RootBlock r: _page.getAllFoundTypes()) {
                for (NamedCalledFunctionBlock o: r.getOverridableBlocks()) {
                    if (!o.isUsedRefMethod()&&o.getKind() == MethodKind.STD_METHOD
                            && MethodId.getKind(o.getModifier()) != MethodAccessKind.INSTANCE) {
                        for (EntryCust<String,AnaLocalVariable> e: o.getUsedParameters().entryList()) {
                            AnaLocalVariable var_ = e.getValue();
                            int indexParam_ = var_.getIndexParam();
                            if (!var_.isUsed()) {
                                FoundWarningInterpret d_ = new FoundWarningInterpret();
                                d_.setIndexFile(var_.getRef());
                                d_.setFileName(o.getFile().getFileName());
                                d_.buildWarning(_page.getAnalysisMessages().getUnusedParamStatic(),e.getKey());
                                _page.getLocalizer().addWarning(d_);
                                o.getParamWarns().get(indexParam_).add(d_.getBuiltWarning());
                            }
                        }
                    }
                }
            }
        }
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
        for (RootBlock e: _page.getAllFoundTypes()) {
            globalType(_page, e);
            _page.setImporting(e);
            _page.setImportingAcces(new TypeAccessor(e.getFullName()));
            _page.setImportingTypes(e);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(e.getMappings());
            for (AbsBk b: getDirectChildren(e)) {
                if (AbsBk.isOverBlock(b)) {
                    _page.setCurrentBlock(e);
                    ((NamedCalledFunctionBlock)b).buildTypes(e, _page);
                }
            }
        }
        IdMap<NamedCalledFunctionBlock,StringMap<GeneStringOverridable>> anaRed_;
        anaRed_ = new IdMap<NamedCalledFunctionBlock,StringMap<GeneStringOverridable>>();
        for (RootBlock e: _page.getAllFoundTypes()) {
            for (NamedCalledFunctionBlock o: e.getOverridableBlocks()) {
                if (o.hiddenInstance() || o.isFinalMethod()) {
                    continue;
                }
                MethodId id_ = o.getId();
                StringMap<GeneStringOverridable> map_ = OverridesTypeUtil.getConcreteMethodsToCall(e, id_, _page);
                map_.putAllMap(o.getOverrides());
                anaRed_.addEntry(o,map_);
            }
        }
        for (RootBlock r: _page.getAllFoundTypes()) {
            if (r.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = r.getAllGenericSuperTypesInfo();
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    String base_ = StringExpUtil.getIdFromAllTypes(s.getFormatted());
                    RootBlock superBl_ = s.getRootBlock();
                    for (NamedCalledFunctionBlock m: superBl_.getOverridableBlocks()) {
                        if (m.isAbstractMethod()) {
                            GeneStringOverridable inf_ = anaRed_.getVal(m).getVal(r.getFullName());
                            if (inf_ == null) {
                                FoundErrorInterpret err_;
                                err_ = new FoundErrorInterpret();
                                err_.setFileName(r.getFile().getFileName());
                                err_.setIndexFile(r.getIdRowCol());
                                //type id
                                err_.buildError(
                                        _page.getAnalysisMessages().getAbstractMethodImpl(),
                                        base_,
                                        m.getSignature(_page),
                                        r.getFullName());
                                _page.addLocError(err_);
                                r.addNameErrors(err_);
                            }
                        }
                    }
                }
            }
        }
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
            if (p.isAbstractMethod()) {
                continue;
            }
            String type_ = p.getClassName();
            if (_page.getAnaClassBody(type_) instanceof InterfaceBlock) {
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
        validateOverridingInherit(_page);
        validateEl(_page);
        AnaTypeUtil.checkInterfaces(_page);
        _page.getSortedOperators().addAllElts(_page.getAllOperators());
        _page.getSortedOperators().sortElts(new AnaOperatorCmp());
        for (OperatorBlock o: _page.getSortedOperators()) {
            for (RootBlock c: o.getAnonymousTypes()) {
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
            for (RootBlock c: o.getLocalTypes()) {
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
        validateOverridingInherit(_page);
        validateEl(_page);
        AnaTypeUtil.checkInterfaces(_page);
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
            _page.getMappingLocal().putAllMap(o.getMappings());
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
            _page.getMappingLocal().putAllMap(o.getMappings());
            o.buildAnnotations(_page);
            o.buildAnnotationsParameters(_page);
        }
        _page.setAnnotAnalysis(false);

        for (IntermediaryResults s:_page.getNextResults()) {
            for (AnonymousInstancingOperation e: _page.getAnonymous()) {
                AnonymousTypeBlock block_ = e.getBlock();
                String base_ = e.getBase();
                String enumClassName_ = _page.getAliasEnumType();
                String enumParamClassName_ = _page.getAliasEnumParam();
                if (StringUtil.quickEq(enumParamClassName_, base_)) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(block_.getFile().getFileName());
                    undef_.setIndexFile(e.getIndex());
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
                    undef_.setFileName(block_.getFile().getFileName());
                    undef_.setIndexFile(e.getIndex());
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
            for (AnonymousTypeBlock a: s.getAnonymousTypes()) {
                processType(basePkgFound_,pkgFound_, a, _page);
            }
            for (NamedCalledFunctionBlock e: s.getAnonymousFunctions()) {
                processType(basePkgFound_,pkgFound_, e, _page);
            }
            for (SwitchMethodBlock e: s.getSwitchMethods()) {
                processType(basePkgFound_,pkgFound_, e, _page);
            }
            processMapping(_page);
            validateInheritingClasses(_page);
            validateIds(_page);
            validateOverridingInherit(_page);
            for (AnonymousInstancingOperation e: _page.getAnonymous()) {
                _page.setGlobalType(e.getGlType());
                e.postAnalyze(_page);
            }
            _page.getAnonymous().clear();
            _page.getAnonymousLambda().clear();
            validateEl(_page);
            for (NamedCalledFunctionBlock e:s.getAnonymousFunctions()) {
                _page.setupFctChars(e);
                _page.getCache().getLocalVariables().clear();
                _page.getCache().getLoopVariables().clear();
                _page.getCache().getLocalVariables().addAllElts(e.getCache().getLocalVariables());
                _page.getCache().getLoopVariables().addAllElts(e.getCache().getLoopVariables());
                StringList params_ = e.getParametersNames();
                StringList types_ = e.getImportedParametersTypes();
                prepareParams(_page, e.getParametersNamesOffset(), e.getParamErrors(),params_,e.getParametersRef(), types_, e.isVarargs());
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(e.getMappings());
                e.buildFctInstructionsReadOnly(_page);
                AnalyzingEl a_ = _page.getAnalysisAss();
                a_.setVariableIssue(_page.isVariableIssue());
                _page.getResultsMethod().addEntry(e,a_);
            }
            for (SwitchMethodBlock e:s.getSwitchMethods()) {
                _page.setupFctChars(e);
                _page.getCache().getLocalVariables().clear();
                _page.getCache().getLoopVariables().clear();
                _page.getCache().getLocalVariables().addAllElts(e.getCache().getLocalVariables());
                _page.getCache().getLoopVariables().addAllElts(e.getCache().getLoopVariables());
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(e.getMappings());
                e.buildFctInstructionsReadOnly(_page);
                AnalyzingEl a_ = _page.getAnalysisAss();
                a_.setVariableIssue(_page.isVariableIssue());
                _page.getResultsSwMethod().addEntry(e,a_);
            }
            _page.setAnnotAnalysis(true);
            for (NamedCalledFunctionBlock e:s.getAnonymousFunctions()) {
                _page.setupFctChars(e);
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(e.getMappings());
                e.buildAnnotations(_page);
                e.buildAnnotationsParameters(_page);
            }
            for (SwitchMethodBlock e:s.getSwitchMethods()) {
                _page.setupFctChars(e);
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(e.getMappings());
                e.buildAnnotations(_page);
                e.buildAnnotationsParameters(_page);
            }
            _page.setAnnotAnalysis(false);
            AnaTypeUtil.checkInterfaces(_page);
        }
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
            d_.setFileName(_root.getFile().getFileName());
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
            badCl_.setFileName(_root.getFile().getFileName());
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
                    badCl_.setFileName(_root.getFile().getFileName());
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
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                //name part if possible or original type
                badCl_.setBuiltError(resClName_.getMessage());
                _page.addLocError(badCl_);
                _root.addNameErrors(badCl_);
                ok_ = false;
            }
        }
        String fullDef_ = _root.getFullDefinition();
        StringList varTypes_ = new StringList();
        String objectClassName_ = _page.getAliasObject();
        StringList params_ = StringExpUtil.getAllTypes(fullDef_);
        StringList namesFromParent_ = getParamVarFromParent(_root);
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
                badCl_.setFileName(_root.getFile().getFileName());
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
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.setBuiltError(res_.getMessage());
                _page.addLocError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringUtil.contains(varTypes_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
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
                badCl_.setFileName(_root.getFile().getFileName());
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
        if (_root instanceof EnumBlock) {
            String fullNameOrig_ = _root.getFullName();
            StringBuilder generic_ = new StringBuilder(fullNameOrig_);
            if (!_root.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t:_root.getParamTypes()) {
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
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof InnerElementBlock) {
            InnerElementBlock i_ = (InnerElementBlock) _root;
            EnumBlock par_ = i_.getParentEnum();
            String type_ = StringUtil.concat(par_.getFullName(),i_.getTempClass());
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof AnnotationBlock) {
            String type_ = _page.getAliasAnnotationType();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_page.getStandardsTypes().contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedTypeStd(),
                    fullName_);
            _page.addLocError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        if (AnaTypeUtil.isPrimitive(fullName_, _page)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedTypePrim(),
                    fullName_);
            _page.addLocError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        if (_root instanceof RootErrorBlock) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_root.getFile().getFileName());
            b_.setIndexFile(((RootErrorBlock)_root).getCategoryOffset());
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            _root.addErrorBlock(b_.getBuiltError());
        }
        _page.getFoundTypes().add(_root);
        _page.getAllFoundTypes().add(_root);
        _page.getSorted().put(_root.getFullName(),_root);
        if (ok_) {
            _page.getRefFoundTypes().add(_root);
        }
        if (_root instanceof AnonymousTypeBlock) {
            int c_ = _page.getCountAnonTypes();
            ((AnonymousTypeBlock)_root).setNumberAnonType(c_);
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
            FileBlock fileBlock_ = new FileBlock(0,_predefined, file_);
            fileBlock_.setNumberFile(_page.getFilesBodies().size());
            _page.putFileBlock(file_, fileBlock_);
            fileBlock_.processLinesTabsWithError(content_, _page);
        }
    }
    public static void parseFiles(AnalyzedPageEl _page) {
        StringMap<FileBlock> files_ = _page.getFilesBodies();
        for (EntryCust<String,FileBlock> f: files_.entryList()) {
            String fileName_ = f.getKey();
            FileBlock block_ = f.getValue();
            if (!block_.getBinChars().isEmpty()) {
                continue;
            }
            String file_ = block_.getContent();
            FileResolver.parseFile(block_, fileName_,file_, _page);
        }
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
                        m_.getMappings().putAllMap(outerFuntion_.getMappings());
                    }
                }
            }
        }
        for (RootBlock r: _page.getFoundTypes()) {
            MemberCallingsBlock outerFuntion_ = r.getStrictOuterFuntion();
            if (outerFuntion_ != null) {
                r.getMappings().putAllMap(outerFuntion_.getMappings());
            }
        }
    }

    public static void fetchByFile(StringList _basePkgFound, StringList _pkgFound, FileBlock _anaFile, AnalyzedPageEl _page) {
        for (AbsBk b: getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processType(_basePkgFound, _pkgFound, r_, _page);
            }
        }
    }

    private static void processType(StringList _basePkgFound, StringList _pkgFound, AbsBk _r, AnalyzedPageEl _page) {
        StringList allReservedInnersRoot_ = new StringList();
        boolean addPkg_ = true;
        if (_r instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _r;
            allReservedInnersRoot_.addAllElts(r_.getAllReservedInners());
            if (r_.getNameLength() != 0) {
                String fullName_ = r_.getFullName();
                for (String p: _pkgFound) {
                    if (!p.startsWith(fullName_)) {
                        continue;
                    }
                    //ERROR
                    FoundErrorInterpret d_ = new FoundErrorInterpret();
                    d_.setFileName(_r.getFile().getFileName());
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
        addReserved(_r, allReservedInnersRoot_);
        if (_r instanceof SwitchMethodBlock) {
            SwitchMethodBlock r_ = (SwitchMethodBlock) _r;
            allReservedInnersRoot_.addAllElts(r_.getAllReservedInners());
        }
        if (_r instanceof OperatorBlock) {
            OperatorBlock r_ = (OperatorBlock) _r;
            allReservedInnersRoot_.addAllElts(r_.getAllReservedInners());
        }
        AbsBk c_ = _r;
        if (c_.getFirstChild() != null) {
            StringList simpleNames_ = new StringList();
            while (true) {
                boolean add_ = true;
                if (c_ instanceof MemberCallingsBlock) {
                    MemberCallingsBlock cur_ = (MemberCallingsBlock) c_;
                    cur_.getReserved().addAllElts(inners(cur_));
                }
                if (c_ instanceof RootBlock) {
                    RootBlock cur_ = (RootBlock) c_;
                    for (RootBlock r:accessedClassMembers(cur_)){
                        cur_.getAllReservedInners().add(r.getName());
                    }
                    for (RootBlock r:accessedInnerElements(cur_)){
                        cur_.getAllReservedInners().add(r.getName());
                    }
                    RootBlock possibleParent_ = cur_.getParentType();
                    OperatorBlock operator_ = cur_.getOperator();
                    MemberCallingsBlock outerFuntion_ = cur_.getOuterFuntionInType();
                    cur_.getAllReservedInners().addAllElts(allReservedInnersRoot_);
                    if (!(cur_ instanceof AnonymousTypeBlock)) {
                        StringList reverv_ = new StringList();
                        String parFullName_ = "";
                        String parGenericString_ = "";
                        if (operator_ != null) {
                            StringList allReservedInners_ = operator_.getAllReservedInners();
                            reverv_.addAllElts(allReservedInners_);
                            cur_.getAllReservedInners().addAllElts(allReservedInners_);
                        }
                        if (possibleParent_ != null) {
                            StringList allReservedInners_ = possibleParent_.getAllReservedInners();
                            reverv_.addAllElts(allReservedInners_);
                            cur_.getAllReservedInners().addAllElts(allReservedInners_);
                            parFullName_ = possibleParent_.getFullName();
                            parGenericString_ = possibleParent_.getGenericString();
                        }
                        if (possibleParent_ != null || operator_ != null) {
                            String s_ = cur_.getName();
                            if (StringUtil.contains(allReservedInnersRoot_, s_)) {
                                FoundErrorInterpret d_ = new FoundErrorInterpret();
                                d_.setFileName(_r.getFile().getFileName());
                                d_.setIndexFile(cur_.getIdRowCol());
                                //s_ len
                                d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                                        s_);
                                _page.addLocError(d_);
                                cur_.addNameErrors(d_);
                                add_ = false;
                            }
                            if (outerFuntion_ != null) {
                                for (RootBlock o : outerFuntion_.getReserved()) {
                                    cur_.getAllReservedInners().add(o.getName());
                                }
                                if (StringUtil.contains(reverv_, s_)) {
                                    FoundErrorInterpret d_ = new FoundErrorInterpret();
                                    d_.setFileName(_r.getFile().getFileName());
                                    d_.setIndexFile(cur_.getIdRowCol());
                                    //s_ len
                                    d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                                            s_);
                                    _page.addLocError(d_);
                                    cur_.addNameErrors(d_);
                                    add_ = false;
                                }
                                StringList namesFromParent_ = getParamVarFromAnyParent(cur_);
                                if (StringUtil.contains(namesFromParent_, s_)) {
                                    FoundErrorInterpret d_ = new FoundErrorInterpret();
                                    d_.setFileName(_r.getFile().getFileName());
                                    d_.setIndexFile(cur_.getIdRowCol());
                                    //s_ len
                                    d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                                            s_);
                                    _page.addLocError(d_);
                                    cur_.addNameErrors(d_);
                                    add_ = false;
                                }
                                StringMap<MappingLocalType> mappings_ = outerFuntion_.getMappings();
                                MappingLocalType resolved_ = mappings_.getVal(s_);
                                if (resolved_ != null) {
                                    FoundErrorInterpret d_ = new FoundErrorInterpret();
                                    d_.setFileName(_r.getFile().getFileName());
                                    d_.setIndexFile(cur_.getIdRowCol());
                                    //s_ len
                                    d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                                            s_);
                                    _page.addLocError(d_);
                                    cur_.addNameErrors(d_);
                                    add_ = false;
                                } else {
                                    mappings_.put(s_, new MappingLocalType(cur_.getFullName(), cur_.getSuffixedName(), cur_, parFullName_, parGenericString_));
                                }
                            }
                        }
                    }
                    if (!(cur_ instanceof AnonymousTypeBlock)) {
                        String s_ = cur_.getName();
                        if (StringUtil.contains(_basePkgFound, s_)) {
                            //ERROR
                            FoundErrorInterpret d_ = new FoundErrorInterpret();
                            d_.setFileName(_r.getFile().getFileName());
                            d_.setIndexFile(cur_.getIdRowCol());
                            //s_ len
                            d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                                    s_);
                            _page.addLocError(d_);
                            cur_.addNameErrors(d_);
                            add_ = false;
                            addPkg_ = false;
                        } else if (StringUtil.contains(simpleNames_, s_)) {
                            //ERROR
                            FoundErrorInterpret d_ = new FoundErrorInterpret();
                            d_.setFileName(_r.getFile().getFileName());
                            d_.setIndexFile(cur_.getIdRowCol());
                            //s_ len
                            d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                                    s_);
                            _page.addLocError(d_);
                            cur_.addNameErrors(d_);
                            add_ = false;
                        }
                    }
                    ClassesUtil.processBracedClass(addPkg_&&add_, cur_, _page);
                }
                AbsBk fc_ = c_.getFirstChild();
                if (fc_ != null) {
                    if (c_ instanceof RootBlock) {
                        String s_ = ((RootBlock)c_).getName();
                        simpleNames_.add(s_);
                    }
                    c_ = fc_;
                    continue;
                }
                boolean end_ = false;
                while (true) {
                    AbsBk n_ = c_.getNextSibling();
                    if (n_ != null) {
                        c_ = n_;
                        break;
                    }
                    BracedBlock p_ = c_.getParent();
                    if (p_ == _r) {
                        end_ = true;
                        break;
                    }
                    c_ = p_;
                    if (c_ instanceof RootBlock) {
                        simpleNames_.removeLast();
                    }
                }
                if (end_) {
                    break;
                }
            }
        } else {
            if (_r instanceof RootBlock) {
                ClassesUtil.processBracedClass(addPkg_, (RootBlock) _r, _page);
            }
        }
    }

    private static void addReserved(AbsBk _r, StringList _allReservedInnersRoot) {
        if (AbsBk.isAnonBlock(_r)) {
            NamedCalledFunctionBlock r_ = (NamedCalledFunctionBlock) _r;
            _allReservedInnersRoot.addAllElts(r_.getAllReservedInners());
        }
    }

    private static CustList<RootBlock> inners(MemberCallingsBlock _caller) {
        CustList<RootBlock> list_ = new CustList<RootBlock>();
        AbsBk c_ = _caller;
        if (_caller.getFirstChild() != null) {
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
                boolean end_ = false;
                while (true) {
                    AbsBk n_ = c_.getNextSibling();
                    if (n_ != null) {
                        c_ = n_;
                        break;
                    }
                    BracedBlock p_ = c_.getParent();
                    if (p_ == _caller) {
                        end_ = true;
                        break;
                    }
                    c_ = p_;
                }
                if (end_) {
                    break;
                }
            }
        }
        return list_;
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
        String objectClassName_ = _page.getAliasObject();
        _page.getListTypesNames().clear();
        validateInheritingClassesId(_page);
        CustList<RootBlock> listTypes_ = _page.getListTypesNames();
        for (RootBlock s: listTypes_) {
            _page.setCurrentBlock(s);
            s.buildDirectGenericSuperTypes(_page);
        }
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setCurrentBlock(c);
            c.buildMapParamType(_page);
        }
        for (RootBlock c: _page.getFoundTypes()) {
            if (c.withoutInstance()) {
                continue;
            }
            StringList allPossibleDirectSuperTypes_ = new StringList();
            MemberCallingsBlock outerFct_ = c.getOuterFuntionInType();
            if (outerFct_ != null) {
                for (RootBlock r:outerFct_.getReserved()) {
                    allPossibleDirectSuperTypes_.add(r.getFullName());
                }
            }
            StringList allDirectSuperTypes_ = new StringList();
            CustList<RootBlock> allAncestors_ = new CustList<RootBlock>();
            RootBlock p_ = c.getParentType();
            while (p_ != null) {
                allAncestors_.add(p_);
                p_ = p_.getParentType();
            }
            for (AnaResultPartType s: c.getResults()) {
                RootBlock s_ = _page.getAnaClassBody(StringExpUtil.getIdFromAllTypes(s.getResult()));
                if (s_ == null) {
                    continue;
                }
                if (s_.withoutInstance()) {
                    continue;
                }
                allDirectSuperTypes_.add(s_.getFullName());
            }
            for (RootBlock a: allAncestors_) {
                MemberCallingsBlock outerFctAnc_ = a.getOuterFuntionInType();
                if (outerFctAnc_ != null) {
                    for (RootBlock r:outerFctAnc_.getReserved()) {
                        allPossibleDirectSuperTypes_.add(r.getFullName());
                    }
                }
                for (AbsBk m: getDirectChildren(a)) {
                    if (!(m instanceof RootBlock)) {
                        continue;
                    }
                    RootBlock m_ = (RootBlock) m;
                    allPossibleDirectSuperTypes_.add(m_.getFullName());
                }
                for (String s: a.getAllSuperTypes()) {
                    RootBlock g_ = _page.getAnaClassBody(s);
                    if (g_ == null) {
                        continue;
                    }
                    for (AbsBk m: getDirectChildren(g_)) {
                        if (!(m instanceof RootBlock)) {
                            continue;
                        }
                        RootBlock m_ = (RootBlock) m;
                        allPossibleDirectSuperTypes_.add(m_.getFullName());
                    }
                }
            }
            if (!allPossibleDirectSuperTypes_.containsAllObj(allDirectSuperTypes_)) {
                for (String s: allDirectSuperTypes_) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(c.getFile().getFileName());
                    enum_.setIndexFile(c.getIdRowCol());
                    //super type len
                    enum_.buildError(_page.getAnalysisMessages().getBadInheritsType(),
                            c.getFullName(),
                            s);
                    _page.addLocError(enum_);
                    c.addNameErrors(enum_);
                }
            }
        }
        validateSingleParameterizedClasses(_page);
        checkTemplatesDef(objectClassName_, _page);
    }

    public static void validateInheritingClassesId(AnalyzedPageEl _page) {
        String objectClassName_ = _page.getAliasObject();
        String enumClassName_ = _page.getAliasEnumType();
        String enumParamClassName_ = _page.getAliasEnumParam();
        String annotName_ = _page.getAliasAnnotationType();
        StringMap<Boolean> builtTypes_ = new StringMap<Boolean>();
        IdList<RootBlock> stClNames_ = new IdList<RootBlock>(_page.getFoundTypes());
        for (RootBlock r: stClNames_) {
            builtTypes_.addEntry(r.getFullName(), false);
        }
        for (RootBlock r: _page.getPrevFoundTypes()) {
            builtTypes_.addEntry(r.getFullName(), true);
        }
        while (true) {
            IdList<RootBlock> next_ = new IdList<RootBlock>();
            for (RootBlock r: stClNames_) {
//                ExecRootBlock exec_ = _page.getMapTypes().getVal(r);
                String c= r.getFullName();
                if (r instanceof AnnotationBlock) {
                    int index_ = 0;
                    StringMap<Integer> foundNames_ = new StringMap<Integer>();
                    for (EntryCust<Integer, String> e: r.getRowColDirectSuperTypes().entryList()) {
                        String s = e.getValue();
                        s = StringExpUtil.removeDottedSpaces(s);
                        String idSuper_ = StringExpUtil.getIdFromAllTypes(s);
                        int offset_ = e.getKey();
                        if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //idSuper_ len
                            undef_.buildError(_page.getAnalysisMessages().getBadInheritsType(),
                                    c,
                                    idSuper_);
                            _page.addLocError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                        foundNames_.addEntry(annotName_,e.getKey());
                        index_++;
                    }
                    for (EntryCust<String,Integer> e: foundNames_.entryList()) {
                        String k_ = e.getKey();
                        int ind_ = e.getValue();
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                    }
                    r.getAllSuperTypes().addAllElts(foundNames_.getKeys());
//                    exec_.getAllSuperTypes().addAllElts(foundNames_.getKeys());
                    r.getAllSuperTypes().add(objectClassName_);
//                    exec_.getAllSuperTypes().add(objectClassName_);
                    r.getAllSuperTypes().removeDuplicates();
//                    exec_.getAllSuperTypes().removeDuplicates();
                    _page.getListTypesNames().add(r);
                    builtTypes_.set(c, true);
                    next_.add(r);
                    continue;
                }
                boolean ready_ = true;
                int index_ = 0;
                CustList<FoundSuperType> types_ = new CustList<FoundSuperType>();
                for (EntryCust<Integer, String> e: r.getRowColDirectSuperTypes().entryList()) {
                    String s = e.getValue();
                    s = StringExpUtil.removeDottedSpaces(s);
                    String idSuper_ = StringExpUtil.getIdFromAllTypes(s);
                    int offset_ = e.getKey();
                    String void_ = _page.getAliasVoid();
                    if (StringUtil.quickEq(idSuper_, void_)) {
                        FoundErrorInterpret undef_ = new FoundErrorInterpret();
                        undef_.setFileName(r.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        //_in len
                        undef_.buildError(_page.getAnalysisMessages().getVoidType(),
                                void_);
                        _page.addLocError(undef_);
                        r.addNameErrors(undef_);
                        index_++;
                        continue;
                    }
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        if (_page.getStandardsTypes().contains(idSuper_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //idSuper_ len
                            undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                                    c,
                                    idSuper_);
                            _page.addLocError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                    }
                    StringList readyTypes_ = new StringList();
                    for (EntryCust<String, Boolean> f: builtTypes_.entryList()) {
                        if (f.getValue()) {
                            readyTypes_.add(f.getKey());
                        }
                    }
                    String foundType_;
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        foundType_ = ResolvingSuperTypes.resolveBaseInherits(idSuper_, r, readyTypes_, _page);
                    } else {
                        InheritReadyTypes inh_ = new InheritReadyTypes(readyTypes_);
                        if (inh_.isReady(idSuper_)) {
                            foundType_ = idSuper_;
                        } else {
                            foundType_ = "";
                        }
                    }
                    RootBlock superType_ = _page.getAnaClassBody(foundType_);
                    if (superType_ == null) {
                        ready_ = false;
                        break;
                    }
                    FoundSuperType f_ = new FoundSuperType();
                    f_.setType(superType_);
                    f_.setLocation(e.getKey());
                    f_.setName(foundType_);
                    types_.add(f_);
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        if (StringUtil.quickEq(enumParamClassName_, foundType_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //original type len
                            undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _page.addLocError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                        if (StringUtil.quickEq(enumClassName_, foundType_) && !StringUtil.quickEq(c, enumParamClassName_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //original type len
                            undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _page.addLocError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                    }
                    index_++;
                }
                if (!ready_) {
                    continue;
                }
                CustList<String> dup_ = new CustList<String>();
                CustList<AnaGeneType> dupTypes_ = new CustList<AnaGeneType>();
                for (FoundSuperType f: types_) {
                    dup_.add(f.getName());
                    dupTypes_.add(f.getType());
                }
                StringMap<Integer> counts_ = new StringMap<Integer>();
                for (String s: dup_) {
                    counts_.put(s,0);
                }
                for (String s: dup_) {
                    counts_.put(s, counts_.getVal(s)+1);
                }
                boolean hasDuplicates_ = false;
                for (EntryCust<String,Integer> e: counts_.entryList()) {
                    if (e.getValue() > 1) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(r.getFile().getFileName());
                        undef_.setIndexFile(r.getIdRowCol());
                        //original type len
                        undef_.buildError(_page.getAnalysisMessages().getDuplicateSuper(),
                                c,e.getKey(),Long.toString(e.getValue()));
                        _page.addLocError(undef_);
                        r.addNameErrors(undef_);
                        hasDuplicates_ = true;
                    }
                }
                if (hasDuplicates_) {
                    continue;
                }
                int indexType_ = -1;
                int nbDirectSuperClass_ = 0;
                for (FoundSuperType f: types_) {
                    indexType_++;
                    String k_ = f.getName();
                    int ind_ = f.getLocation();
                    RootBlock s_ = f.getType();
                    int offset_ = r.getRowColDirectSuperTypes().getKey(indexType_);
                    if (s_ instanceof UniqueRootedBlock) {
                        nbDirectSuperClass_++;
                    }
                    if (r.withoutInstance()) {
                        if (!s_.withoutInstance()) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original k_ string len
                            enum_.buildError(_page.getAnalysisMessages().getBadInheritsTypeInn(),
                                    c,
                                    k_);
                            _page.addLocError(enum_);
                            r.addNameErrors(enum_);
                        }
                    } else {
                        int subSise_ = r.getSelfAndParentTypes().size();
                        int supSise_ = s_.getSelfAndParentTypes().size();
                        if (supSise_ > subSise_) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original k_ string len
                            enum_.buildError(_page.getAnalysisMessages().getBadInheritsTypeAsInn(),
                                    c,
                                    k_,
                                    Long.toString(subSise_-1L),
                                    Long.toString(supSise_-1L));
                            _page.addLocError(enum_);
                            r.addNameErrors(enum_);
                        }
                    }
                    if (r instanceof InterfaceBlock || r instanceof RecordBlock) {
                        if (!(s_ instanceof InterfaceBlock)) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original type len
                            enum_.buildError(_page.getAnalysisMessages().getBadInheritsTypeInt(),
                                    c,k_);
                            _page.addLocError(enum_);
                            r.addNameErrors(enum_);
                        }
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        continue;
                    }
                    if (r instanceof InnerTypeOrElement) {
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        continue;
                    }
                    if (ContextUtil.isFinalType(s_)) {
                        FoundErrorInterpret enum_;
                        enum_ = new FoundErrorInterpret();
                        enum_.setFileName(r.getFile().getFileName());
                        enum_.setIndexFile(offset_);
                        //original type len
                        enum_.buildError(_page.getAnalysisMessages().getFinalType(),
                                c,k_);
                        _page.addLocError(enum_);
                        r.addNameErrors(enum_);
                    }
                    r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                }
                if (nbDirectSuperClass_ > 1) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(r.getFile().getFileName());
                    enum_.setIndexFile(r.getIdRowCol());
                    //second super class
                    enum_.buildError(_page.getAnalysisMessages().getSuperClass(),
                            c,Long.toString(nbDirectSuperClass_));
                    _page.addLocError(enum_);
                    r.addNameErrors(enum_);
                }
                r.getAllSuperTypes().addAllElts(dup_);
                r.getAllSuperTypesInfo().addAllElts(dupTypes_);
//                exec_.getAllSuperTypes().addAllElts(dup_);
                for (FoundSuperType f: types_) {
                    RootBlock s_ = f.getType();
//                    exec_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                    r.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                    r.getAllSuperTypesInfo().addAllElts(s_.getAllSuperTypesInfo());
                }
                r.getAllSuperTypes().add(objectClassName_);
//                exec_.getAllSuperTypes().add(objectClassName_);
                r.getAllSuperTypes().removeDuplicates();
//                exec_.getAllSuperTypes().removeDuplicates();
                _page.getListTypesNames().add(r);
                builtTypes_.set(c, true);
                next_.add(r);
            }
            if (next_.isEmpty()) {
                for (RootBlock r: stClNames_) {
                    _page.getListTypesNames().add(r);
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(r.getFile().getFileName());
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

    private static void checkTemplatesDef(String _objectClassName, AnalyzedPageEl _page) {
        for (RootBlock s: _page.getFoundTypes()) {
            String c = s.getFullName();
            Mapping mapping_ = new Mapping();
            StringMap<StringList> cts_ = new StringMap<StringList>();
            StringList variables_ = new StringList();
            boolean ok_ = true;
            for (TypeVar t: s.getParamTypesMapValues()) {
                cts_.put(t.getName(), t.getConstraints());
                variables_.add(t.getName());
            }
            if (!variables_.isEmpty() && s instanceof AnnotationBlock) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                //first < after type id
                b_.buildError(_page.getAnalysisMessages().getAnnotationParam(),
                        c);
                _page.addLocError(b_);
                s.addNameErrors(b_);
                continue;
            }
            mapping_.setMapping(cts_);
            StringList cyclic_ = mapping_.getCyclic();
            if (!cyclic_.isEmpty()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                //first < after type id
                b_.buildError(_page.getAnalysisMessages().getCyclicMapping(),
                        c);
                _page.addLocError(b_);
                s.addNameErrors(b_);
                continue;
            }
            for (TypeVar t: s.getParamTypesMapValues()) {
                boolean existNative_ = false;
                boolean existCustom_ = false;
                StringList upper_ = Mapping.getAllUpperBounds(cts_, t.getName(),_objectClassName);
                StringList upperNotObj_ = new StringList();
                for (String b: upper_) {
                    if (b.startsWith("[")) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _page.addLocError(un_);
                        s.addNameErrors(un_);
                    }
                    if (AnaTypeUtil.isPrimitive(b, _page)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _page.addLocError(un_);
                        s.addNameErrors(un_);
                    }
                    String baseParams_ = StringExpUtil.getIdFromAllTypes(b);
                    String base_ = StringExpUtil.getQuickComponentBaseType(baseParams_).getComponent();
                    upperNotObj_.add(b);
                    if (_page.getAnaClassBody(base_) != null) {
                        existCustom_ = true;
                    } else {
                        existNative_ = true;
                    }
                }
                boolean okLoc_ = true;
                if (existNative_ && existCustom_) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    //type var len => at def
                    un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                            c);
                    _page.addLocError(un_);
                    s.addNameErrors(un_);
                    okLoc_ = false;
                    ok_ = false;
                }
                for (CustList<TypeInfo> g: OperationNode.typeLists(upper_,MethodAccessKind.INSTANCE, _page)) {
                    StringList all_ = new StringList();
                    for (TypeInfo i: g) {
                        all_.add(i.getFormatted().getFormatted());
                    }
                    checkDupl(_page, s, all_);

                }
                if (okLoc_) {
                    int nbAbs_ = 0;
                    int nbFinal_ = 0;
                    if (existNative_) {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = StringExpUtil.getIdFromAllTypes(b);
                            String base_ = StringExpUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            StandardType type_ = _page.getStandardsTypes().getVal(base_);
                            if (!(type_ instanceof StandardClass)) {
                                continue;
                            }
                            if (ContextUtil.isFinalType(type_)) {
                                nbFinal_++;
                            }
                            if (ContextUtil.isAbstractType(type_)) {
                                nbAbs_++;
                            }
                        }
                    } else {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = StringExpUtil.getIdFromAllTypes(b);
                            String base_ = StringExpUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            RootBlock r_ = _page.getAnaClassBody(base_);
                            if (!(r_ instanceof UniqueRootedBlock)) {
                                continue;
                            }
                            if (ContextUtil.isFinalType(r_)) {
                                nbFinal_++;
                            }
                            if (ContextUtil.isAbstractType(r_)) {
                                nbAbs_++;
                            }
                        }
                    }
                    if (nbAbs_ > 1 || nbFinal_ > 0) {
                        if (nbAbs_ > 1) {
                            //error
                            FoundErrorInterpret inh_;
                            inh_ = new FoundErrorInterpret();
                            inh_.setFileName(s.getFile().getFileName());
                            inh_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            inh_.buildError(_page.getAnalysisMessages().getAbsMapping(),
                                    Long.toString(nbAbs_));
                            _page.addLocError(inh_);
                            s.addNameErrors(inh_);
                            ok_ = false;
                        }
                        if (nbFinal_ > 0) {
                            //error
                            FoundErrorInterpret inh_;
                            inh_ = new FoundErrorInterpret();
                            inh_.setFileName(s.getFile().getFileName());
                            inh_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            inh_.buildError(_page.getAnalysisMessages().getFinalMapping(),
                                    Long.toString(nbFinal_));
                            _page.addLocError(inh_);
                            s.addNameErrors(inh_);
                            ok_ = false;
                        }
                    }
                }
            }
            if (!ok_) {
                continue;
            }
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            for (TypeVar t: s.getParamTypesMapValues()) {
                map_.put(t.getName(), t.getConstraints());
            }
            for (TypeVar t: s.getParamTypes()) {
                for (AnaResultPartType b: t.getResults()) {
                    if (!AnaPartTypeUtil.processAnalyzeConstraintsCore(b, map_, true, _page)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                                b.getResult());
                        _page.addLocError(un_);
//                        s.addNameErrors(un_);
                    }
                }
            }
            for (AnaResultPartType t: s.getResults()) {
                if (!AnaPartTypeUtil.processAnalyzeConstraintsCore(t, map_, true, _page)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    // char : before super type
                    buildErr(_page, t, un_);
                    _page.addLocError(un_);
//                    s.addNameErrors(un_);
                }
            }
//            for (AnaResultPartType t: s.getResults()) {
//                AnaPartTypeUtil.processAnalyzeConstraintsRep(t, s.getSuperTypesParts(), _page);
//            }
//                if (!AnaPartTypeUtil.processAnalyzeConstraints(t, map_, true, s.getSuperTypesParts(), _page)) {
//                    FoundErrorInterpret un_ = new FoundErrorInterpret();
//                    un_.setFileName(s.getFile().getFileName());
//                    un_.setIndexFile(s.getIdRowCol());
//                    // char : before super type
//                    buildErr(_page, t, un_);
//                    _page.addLocError(un_);
//                    s.addNameErrors(un_);
//                }
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
                duplicate_.setFileName(_s.getFile().getFileName());
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
            CustList<AnaFormattedRootBlock> genericSuperTypes_ = i.fetchAllGenericSuperTypes();
            StringList allGenericSuperTypes_ = new StringList();
            for (AnaFormattedRootBlock a: genericSuperTypes_) {
                allGenericSuperTypes_.add(a.getFormatted());
            }
            checkDupl(_page, i, allGenericSuperTypes_);
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
            _page.getMappingLocal().putAllMap(c.getMappings());
            c.validateIds(_page);
            if (c.getNbOperators() > 0) {
                _page.getTypesWithInnerOperators().add(c.getFullName());
            }
        }
        for (RootBlock c: _page.getFoundTypes()) {
            globalType(_page, c);
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getMappings());
            for (AbsBk b: getDirectChildren(c)) {
                if (b instanceof InternOverrideBlock) {
                    _page.setCurrentBlock(c);
                    ((InternOverrideBlock)b).buildTypes(c, _page);
                }
            }
        }
    }

    private static void validateIdsOperators(AnalyzedPageEl _page) {
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        globalType(_page);
        for (OperatorBlock o: _page.getAllOperators()) {
            String name_ = o.getName();
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            _page.getMappingLocal().clear();
            o.buildImportedTypes(_page);
            if (!StringExpUtil.isOper(name_)) {
                FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                badMeth_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                badMeth_.setIndexFile(o.getNameOffset());
                //key word len
                badMeth_.buildError(_page.getAnalysisMessages().getBadOperatorName(),
                        name_);
                _page.addLocError(badMeth_);
                o.addNameErrors(badMeth_);
            }
            MethodId id_ = o.getId();
            for (MethodId m: idMethods_) {
                if (m.eq(id_)) {
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setIndexFile(o.getOffset());
                    duplicate_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                    //key word len
                    duplicate_.buildError(_page.getAnalysisMessages().getDuplicateOperator(),
                            id_.getSignature(_page.getDisplayedStrings()));
                    _page.addLocError(duplicate_);
                    o.addNameErrors(duplicate_);
                }
            }
            idMethods_.add(id_);
            StringList l_ = o.getParametersNames();
            StringList seen_ = new StringList();
            int i_ = 0;
            for (String v: l_) {
                o.addParamErrors();
                o.addParamWarns();
                TokenErrorMessage res_ = ManageTokens.partParam(_page).checkToken(v, _page);
                if (res_.isError()) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                    b_.setIndexFile(o.getOffset());
                    //param name len
                    b_.setBuiltError(res_.getMessage());
                    _page.addLocError(b_);
                    o.addParamErrors(i_,b_);
                }
                if (StringUtil.contains(seen_, v)){
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                    b_.setIndexFile(o.getOffset());
                    //param name len
                    b_.buildError(_page.getAnalysisMessages().getDuplicatedParamName(),
                            v);
                    _page.addLocError(b_);
                    o.addParamErrors(i_,b_);
                } else {
                    seen_.add(v);
                }
                i_++;
            }
            if (o.isRetRef()) {
                if (StringUtil.quickEq(o.getImportedReturnType(), _page.getAliasVoid())) {
                    int r_ = o.getNameOffset();
                    FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                    badMeth_.setFileName(o.getFile().getFileName());
                    badMeth_.setIndexFile(r_);
                    //method name len
                    badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                            o.getSignature(_page),
                            _page.getAliasVoid());
                    _page.addLocError(badMeth_);
                    o.addNameErrors(badMeth_);
                }
            }
        }
    }

    public static void validateOverridingInherit(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getFoundTypes()) {
            c.setupBasicOverrides(_page);
        }
        for (RootBlock c: _page.getFoundTypes()) {
            c.checkCompatibility(_page);
            c.checkImplements(_page);
        }
        for (RootBlock c: _page.getFoundTypes()) {
            c.checkCompatibilityBounds(_page);
        }
    }

    public static CustList<RootBlock> accessedClassMembers(RootBlock _clOwner) {
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        for (AbsBk b: getDirectChildren(_clOwner)) {
            if (!(b instanceof RootBlock)) {
                continue;
            }
            if (b instanceof InnerElementBlock) {
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
            String fullName_ = c.getFullName();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock f_ = (InfoBlock) b;
                StringList fieldNames_ = f_.getFieldName();
                _page.getAllDeclaredFields().addAllElts(fieldNames_);
                if (!f_.isStaticField()) {
                    continue;
                }
                if (!(f_ instanceof FieldBlock)) {
                    _page.getAssignedDeclaredFields().addAllElts(fieldNames_);
                    continue;
                }
                FieldBlock field_ = (FieldBlock) f_;
                _page.getAssignedDeclaredFields().addAllElts(field_.getAssignedDeclaredFields());
                int v_ = 0;
                for (String f: fieldNames_) {
                    StringList err_ = field_.getCstErrorsFields().get(v_);
                    checkConstField(err_,c, fullName_, f, _page);
                    v_++;
                }
            }
            for (AbsBk b: bl_) {
                if (b instanceof InnerTypeOrElement) {
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                    _page.setCurrentFct(null);
                    InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                    _page.setCurrentBlock(b);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(c.getMappings());
                    method_.buildExpressionLanguageReadOnly(_page);
                    _page.getFieldsAssSt().addEntry(b,new AssElementBlock(method_));
                }
                if (b instanceof FieldBlock) {
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                    FieldBlock method_ = (FieldBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    _page.setCurrentBlock(b);
                    _page.setCurrentFct(null);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(c.getMappings());
                    method_.buildExpressionLanguageReadOnly(_page);
                    _page.getFieldsAssSt().addEntry(b,new AssFieldBlock(method_));
                }
                if (b instanceof StaticBlock) {
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                    StaticBlock method_ = (StaticBlock) b;
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(_page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAna().addEntry(method_,a_);
                }
            }
        }
        _page.setAssignedStaticFields(true);
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    _page.getAllDeclaredFields().addAllElts(method_.getFieldName());
                    if (method_.isStaticField()) {
                        _page.getAssignedDeclaredFields().addAllElts(method_.getFieldName());
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    _page.getAssignedDeclaredFields().addAllElts(((FieldBlock)b).getAssignedDeclaredFields());
                }
            }
            for (AbsBk b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                    FieldBlock method_ = (FieldBlock) b;
                    _page.setCurrentBlock(b);
                    _page.setCurrentFct(null);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(c.getMappings());
                    method_.buildExpressionLanguageReadOnly(_page);
                    _page.getFieldsAss().addEntry(b,new AssFieldBlock(method_));
                }
                if (b instanceof InstanceBlock) {
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                    InstanceBlock method_ = (InstanceBlock) b;
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(_page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaInst().addEntry(method_,a_);
                }
            }
            processInterfaceCtor(c, bl_, _page);
            for (AbsBk b: bl_) {
                if (b instanceof ConstructorBlock) {
                    _page.getInitFieldsCtors().clear();
                    _page.getInitFieldsCtors().addAllElts(_page.getInitFields());
                    globalType(_page, c);
                    _page.setCurrentPkg(c.getPackageName());
                    _page.setCurrentFile(c.getFile());
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(_page, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, method_.getParametersRef(), types_, method_.isVarargs());
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(_page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaNamed().addEntry(method_,a_);
                }
            }
        }
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
                    _page.getMappingLocal().putAllMap(method_.getMappings());
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
                    processValueParam(_page, c, method_);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(_page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaMethod().addEntry(method_,a_);
                }
            }
        }
        _page.setAnnotAnalysis(true);
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            globalType(_page, c);
            _page.setCurrentPkg(c.getPackageName());
            _page.setCurrentFile(c.getFile());
            _page.setCurrentFct(null);
            CustList<AbsBk> annotated_ = new CustList<AbsBk>();
            if (!(c instanceof InnerElementBlock)) {
                annotated_.add(c);
            }
            annotated_.addAllElts(getDirectChildren(c));
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getMappings());
            for (AbsBk b:annotated_) {
                _page.setCurrentBlock(b);
                if (AbsBk.isAnnotBlock(b)) {
                    ((NamedCalledFunctionBlock)b).buildExpressionLanguage(_page);
                }
                if (b instanceof NamedFunctionBlock) {
                    ((NamedFunctionBlock)b).buildAnnotations(_page);
                    ((NamedFunctionBlock)b).buildAnnotationsParameters(_page);
                }
                if (b instanceof RootBlock) {
                    ((RootBlock)b).buildAnnotations(_page);
                } else if (b instanceof InfoBlock) {
                    ((InfoBlock)b).buildAnnotations(_page);
                }
            }
        }
        globalType(_page);
        _page.setCurrentFct(null);
        _page.setAnnotAnalysis(false);
        //init annotations here
        for (RootBlock c: _page.getFoundTypes()) {
            c.validateConstructors(_page);
        }
    }

    public static void globalType(AnalyzedPageEl _page, RootBlock _c) {
        _page.setGlobalType(new AnaFormattedRootBlock(_c));
    }

    private static void procBadIndexes(AnalyzedPageEl _page, CustList<BracedBlock> _braced) {
        for (BracedBlock c: _braced) {
            for (int i: c.getBadIndexesGlobal()) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(c.getFile().getFileName());
                b_.setIndexFile(Math.max(0,Math.min(c.getFile().getLength()-1,i)));
                //underline index char
                b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
                _page.addLocError(b_);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(b_);
                g_.setLength(1);
                c.getGlobalErrorsPars().getLi().add(g_);
            }
            for (int i: c.getBadIndexes()) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(c.getFile().getFileName());
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
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            globalType(_page,c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (AbsBk b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock f_ = (InfoBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                for (String f: f_.getFieldName()) {
                    AssignmentBefore as_ = new AssignmentBefore();
                    as_.setUnassignedBefore(true);
                    ass_.put(f, as_);
                }
            }
            StringMap<AssignmentBefore> b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            assVars_.getFinalVariablesGlobal().getFields().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRoot().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            assVars_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.putAllMap(ass_);
            StringMap<SimpleAssignment> assAfter_;
            assAfter_ = new StringMap<SimpleAssignment>();
            AssBlock pr_ = null;
            for (AbsBk b: bl_) {
                AssBlock val_ = _page.getFieldsAssSt().getVal(b);
                if (val_ instanceof AssInfoBlock) {
                    AssInfoBlock aInfo_ = (AssInfoBlock) val_;
                    aInfo_.setAssignmentBeforeAsLeaf(assVars_,pr_);
                    aInfo_.buildExpressionLanguage(assVars_, _page);
                    aInfo_.setAssignmentAfterAsLeaf(assVars_,pr_, _page);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                    pr_ = (AssBlock) aInfo_;
                }
                AnalyzingEl anAss_ = null;
                if (b instanceof MemberCallingsBlock) {
                    anAss_ = _page.getResultsAna().getVal((MemberCallingsBlock) b);
                }
                if (b instanceof MemberCallingsBlock && anAss_ != null) {
                    MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                    AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
                    tryAnalyseAssign(assVars_, pr_, anAss_, assign_, _page);
                    assAfter_.putAllMap(getFieldsRoot(assVars_, assign_));
                    _page.clearAllLocalVars(assVars_);
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
                    un_.setFileName(c.getFile().getFileName());
                    un_.setIndexFile(c.getOffset());
                    un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                            key_,fullName_);
                    _page.addLocError(un_);
                }
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
            String fullName_ = c.getFullName();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (AbsBk b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    InfoBlock f_ = (InfoBlock) b;
                    for (String f: f_.getFieldName()) {
                        AssignmentBefore as_ = new AssignmentBefore();
                        as_.setUnassignedBefore(true);
                        ass_.put(f, as_);
                    }
                }
            }
            StringMap<AssignmentBefore> b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
            assVars_.getFinalVariablesGlobal().getFields().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRoot().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            assVars_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.clear();
            b_.putAllMap(ass_);
            boolean hasCtor_ = false;
            for (AbsBk b: bl_) {
                if (b instanceof ConstructorBlock) {
                    hasCtor_ = true;
                    break;
                }
            }
            StringMap<SimpleAssignment> assAfter_;
            assAfter_ = new StringMap<SimpleAssignment>();
            AssBlock pr_ = null;
            for (AbsBk b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                AssBlock val_ = _page.getFieldsAss().getVal(b);
                if (val_ instanceof AssInfoBlock) {
                    AssInfoBlock aInfo_ = (AssInfoBlock) val_;
                    aInfo_.setAssignmentBeforeAsLeaf(assVars_,pr_);
                    aInfo_.buildExpressionLanguage(assVars_, _page);
                    aInfo_.setAssignmentAfterAsLeaf(assVars_,pr_, _page);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                    pr_ = (AssBlock) aInfo_;
                }
                AnalyzingEl anAss_ = null;
                if (b instanceof MemberCallingsBlock) {
                    anAss_ = _page.getResultsAnaInst().getVal((MemberCallingsBlock) b);
                }
                if (b instanceof MemberCallingsBlock && anAss_ != null) {
                    MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                    AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
                    tryAnalyseAssign(assVars_, pr_, anAss_, assign_, _page);
                    assAfter_.putAllMap(getFieldsRoot(assVars_, assign_));
                    _page.clearAllLocalVars(assVars_);
                    pr_ = assign_;
                }
            }
            b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            if (!hasCtor_) {
                for (EntryCust<String, SimpleAssignment> a : assAfter_.entryList()) {
                    String fieldName_ = a.getKey();
                    ClassField key_ = new ClassField(fullName_, fieldName_);
                    if (!ContextUtil.isFinalField(key_, _page)) {
                        continue;
                    }
                    if (StringUtil.contains(_page.getInitFields(), fieldName_)) {
                        continue;
                    }
                    //error
                    for (AbsBk b : bl_) {
                        if (b instanceof InfoBlock) {
                            if (StringUtil.contains(((InfoBlock) b).getFieldName(), fieldName_)) {
                                FoundErrorInterpret un_ = new FoundErrorInterpret();
                                un_.setFileName(c.getFile().getFileName());
                                un_.setIndexFile(b.getOffset());
                                un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                                        fieldName_,fullName_);
                                _page.addLocError(un_);
                            }
                        }
                    }
                }
            }
            b_.putAllMap(AssignmentsUtil.assignSimpleBefore(assAfter_));
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = null;
                if (b instanceof NamedFunctionBlock) {
                    anAss_ = _page.getResultsAnaNamed().getVal((MemberCallingsBlock) b);
                }
                if (b instanceof NamedFunctionBlock && anAss_ != null) {
                    NamedFunctionBlock m_ = (NamedFunctionBlock) b;
                    AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
                    tryAnalyseAssign(assVars_, null, anAss_, assign_, _page);
                    StringMap<SimpleAssignment> fieldsRoot_ = getFieldsRoot(assVars_, assign_);
                    for (EntryCust<String, SimpleAssignment> f: fieldsRoot_.entryList()) {
                        String fieldName_ = f.getKey();
                        ClassField key_ = new ClassField(fullName_, fieldName_);
                        if (!ContextUtil.isFinalField(key_, _page)) {
                            continue;
                        }
                        if (StringUtil.contains(_page.getInitFieldsCtors(),fieldName_)) {
                            continue;
                        }
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(c.getFile().getFileName());
                        un_.setIndexFile(m_.getNameOffset());
                        un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                                fieldName_,fullName_);
                        _page.addLocError(un_);
                    }
                    _page.clearAllLocalVars(assVars_);
                }
            }
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
                AnalyzingEl anAss_ = null;
                if (b instanceof NamedFunctionBlock) {
                    anAss_ = _page.getResultsAnaMethod().getVal((MemberCallingsBlock) b);
                }
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
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            globalType(_page,c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = null;
                if (b instanceof MemberCallingsBlock) {
                    anAss_ = _page.getResultsAna().getVal((MemberCallingsBlock) b);
                }
                if (b instanceof MemberCallingsBlock && anAss_ != null) {
                    MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                    AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), m_);
                    tryAnalyseAssign(assVars_, anAss_, assign_, _page);
                    _page.clearAllLocalVars(assVars_);
                }
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
                AnalyzingEl anAss_ = null;
                if (b instanceof MemberCallingsBlock) {
                    anAss_ = _page.getResultsAnaInst().getVal((MemberCallingsBlock) b);
                }
                if (b instanceof MemberCallingsBlock && anAss_ != null) {
                    MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                    AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), m_);
                    tryAnalyseAssign(assVars_, anAss_, assign_, _page);
                    _page.clearAllLocalVars(assVars_);
                }
            }
            for (AbsBk b: bl_) {
                AnalyzingEl anAss_ = null;
                if (b instanceof NamedFunctionBlock) {
                    anAss_ = _page.getResultsAnaNamed().getVal((MemberCallingsBlock) b);
                }
                if (b instanceof NamedFunctionBlock && anAss_ != null) {
                    NamedFunctionBlock m_ = (NamedFunctionBlock) b;
                    AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), m_);
                    tryAnalyseAssign(assVars_, anAss_, assign_, _page);
                    _page.clearAllLocalVars(assVars_);
                }
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
                AnalyzingEl anAss_ = null;
                if (b instanceof NamedFunctionBlock) {
                    anAss_ = _page.getResultsAnaMethod().getVal((MemberCallingsBlock) b);
                }
                if (b instanceof NamedFunctionBlock && anAss_ != null) {
                    NamedFunctionBlock m_ = (NamedFunctionBlock) b;
                    AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), m_);
                    tryAnalyseAssign(assVars_, anAss_, assign_, _page);
                    _page.clearAllLocalVars(assVars_);
                }
            }
        }

        for (EntryCust<NamedCalledFunctionBlock, AnalyzingEl> e: _page.getResultsMethod().entryList()) {
            NamedCalledFunctionBlock method_ = e.getKey();
            _page.setupFctChars(method_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), method_);
            tryAnalyseAssign(assVars_, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
        for (EntryCust<SwitchMethodBlock, AnalyzingEl> e: _page.getResultsSwMethod().entryList()) {
            SwitchMethodBlock method_ = e.getKey();
            _page.setupFctChars(method_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), method_);
            tryAnalyseAssign(assVars_, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
        assVars_.setCache(new AnaCache());
        globalType(_page);
        _page.setCurrentPkg("");
        _page.setCurrentFile(null);
        for (EntryCust<OperatorBlock, AnalyzingEl> e: _page.getResultsAnaOperator().entryList()) {
            NamedFunctionBlock m_ = e.getKey();
            AnalyzingEl anAss_ = e.getValue();
            AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), m_);
            tryAnalyseAssign(assVars_, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
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

    private static void processValueParam(AnalyzedPageEl _page, RootBlock _cl, NamedCalledFunctionBlock _method) {
        if (_method.getKind() == MethodKind.SET_INDEX) {
            String p_ = _page.getKeyWords().getKeyWordValue();
            CustList<NamedCalledFunctionBlock> getIndexers_ = new CustList<NamedCalledFunctionBlock>();
            for (NamedCalledFunctionBlock d: _cl.getOverridableBlocks()) {
                if (d.getKind() != MethodKind.GET_INDEX) {
                    continue;
                }
                if (!d.getId().eqPartial(_method.getId())) {
                    continue;
                }
                getIndexers_.add(d);
            }
            if (getIndexers_.size() == 1) {
                NamedCalledFunctionBlock matching_ = getIndexers_.first();
                String c_ = matching_.getImportedReturnType();
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(c_);
                lv_.setConstType(ConstType.PARAM);
                lv_.setKeyWord(true);
                _page.getInfosVars().put(p_, lv_);
            }
        }
    }

    private static void processInterfaceCtor(RootBlock _cl, CustList<AbsBk> _blocks, AnalyzedPageEl _page) {
        boolean hasCtor_ = false;
        for (AbsBk b: _blocks) {
            if (b instanceof ConstructorBlock) {
                hasCtor_ = true;
                break;
            }
        }
        StringList filteredCtor_ = new StringList();
        if (_cl instanceof UniqueRootedBlock) {
            UniqueRootedBlock un_ = (UniqueRootedBlock) _cl;
            StringList all_ = _cl.getAllSuperTypes();
            StringList allCopy_ = new StringList(all_);
            StringUtil.removeAllElements(allCopy_, _page.getPredefinedInterfacesInitOrder());
            String superClass_ = un_.getImportedDirectGenericSuperClass();
            String superClassId_ = StringExpUtil.getIdFromAllTypes(superClass_);
            RootBlock superType_ = _page.getAnaClassBody(superClassId_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringUtil.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            for (String i: allCopy_) {
                RootBlock int_ = _page.getAnaClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (AbsBk b: getDirectChildren(int_)) {
                    if (b instanceof NamedFunctionBlock) {
                        continue;
                    }
                    if (b instanceof InfoBlock) {
                        InfoBlock a_ = (InfoBlock) b;
                        if (!a_.isStaticField()) {
                            filteredCtor_.add(i);
                        }
                    }
                    if (b instanceof InstanceBlock) {
                        filteredCtor_.add(i);
                    }
                }
            }
        }
        _page.getNeedInterfaces().clear();
        _page.getNeedInterfaces().addAllElts(filteredCtor_);
        if (!hasCtor_ && !filteredCtor_.isEmpty()) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFileName(_cl.getFile().getFileName());
            undef_.setIndexFile(_cl.getIdRowCol());
            //original id len
            undef_.buildError(_page.getAnalysisMessages().getMustCallIntCtor(),
                    _cl.getFullName());
            _page.addLocError(undef_);
            _cl.addNameErrors(undef_);
        }
    }

    private static void prepareParams(AnalyzedPageEl _page, Ints _offs, CustList<StringList> _paramErrors, StringList _params, CustList<Boolean> _refParams, StringList _types, boolean _varargs) {
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

    private static void buildParam(AnalyzedPageEl _page, Ints _offs, CustList<Boolean> _refParams, int _i, String _p, String _c) {
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(_c);
        lv_.setRef(_offs.get(_i));
        lv_.setIndexParam(_i);
        if (_refParams.get(_i)) {
            lv_.setConstType(ConstType.REF_PARAM);
        } else {
            lv_.setConstType(ConstType.PARAM);
        }
        _page.getInfosVars().put(_p, lv_);
    }

    private static void checkConstField(StringList _err, RootBlock _cl, String _clName, String _field, AnalyzedPageEl _page) {
        if (NumParsers.getStaticFieldMap(_clName, _page.getStaticFields()).getVal(_field) == null) {
            if (!_cl.withoutInstance()) {
                //ERROR
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cl.getFile().getFileName());
                un_.setIndexFile(_cl.getOffset());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                        _field,_clName);
                _page.addLocError(un_);
                _err.add(un_.getBuiltError());
            }
        }
    }

    private static void initStaticFields(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getFoundTypes()) {
            String fullName_ = c.getFullName();
            CustList<AbsBk> bl_ = getDirectChildren(c);
            StringMap<Struct> cl_ = new StringMap<Struct>();
            for (AbsBk b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock)b;
                if (!i_.isStaticField()) {
                    continue;
                }
                for (String f: i_.getFieldName()) {
                    cl_.put(f, null);
                }
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
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                if (!f_.isFinalField()) {
                    continue;
                }
                globalType(_page, c);
                _page.setCurrentPkg(c.getPackageName());
                _page.setCurrentFile(c.getFile());
                _page.setCurrentBlock(f_);
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(c.getMappings());
                _page.setCurrentFct(null);
                CustList<OperationNode> list_ = f_.buildExpressionLanguageQuickly(_page);
                String cl_ = c.getFullName();
                for (String f: f_.getFieldName()) {
                    ClassField k_ = new ClassField(cl_, f);
                    cstFields_.addEntry(k_,new ClassFieldBlock(list_,f_));
                }
            }
        }
        while (true) {
            boolean calculatedValue_ = false;
            for (EntryCust<ClassField,ClassFieldBlock> e: cstFields_.entryList()) {
                ClassField k_ = e.getKey();
                if (NumParsers.getStaticField(k_, _page.getStaticFields()) != null) {
                    continue;
                }
                ClassFieldBlock cf_ = e.getValue();
                FieldBlock f_ = cf_.getFieldName();
                CustList<OperationNode> ops_ = cf_.getClassName();
                ReachOperationUtil.tryCalculate(f_,ops_, k_.getFieldName(), _page);
                if (NumParsers.getStaticField(k_, _page.getStaticFields()) != null) {
                    calculatedValue_ = true;
                    break;
                }
            }
            if (!calculatedValue_) {
                break;
            }
        }
    }


    public static CustList<NamedCalledFunctionBlock> getMethodAnnotationBodiesById(RootBlock _r, String _id) {
        CustList<NamedCalledFunctionBlock> methods_ = new CustList<NamedCalledFunctionBlock>();
        for (NamedCalledFunctionBlock b: _r.getAnnotationsMethodsBlocks()) {
            if (StringUtil.quickEq(b.getName(), _id)) {
                methods_.add(b);
            }
        }
        return methods_;
    }

}
