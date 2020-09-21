package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ClassFieldBlock;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.accessing.OperatorAccessor;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.FoundSuperType;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.AnonymousInstancingOperation;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ParametersGroup;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.assign.blocks.*;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecAnonymousInstancingOperation;
import code.expressionlanguage.exec.opers.ExecAnonymousLambdaOperation;
import code.expressionlanguage.exec.opers.ExecNamedArgumentOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.files.FileResolver;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.inherits.OverridesTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public final class ClassesUtil {
    private static final char DOT = '.';

    private ClassesUtil(){
    }

    public static void postValidation(AnalyzedPageEl _page) {
        StringMap<PolymorphMethod> toStringMethodsToCallBodies_ = _page.getToStringMethodsToCallBodies();
        _page.setAnnotAnalysis(false);
        if (!_page.getOptions().isReadOnly()) {
            validateFinals(_page);
        } else {
            validateSimFinals(_page);
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: _page.getMapTypes().entryList()) {
            ClassMethodIdReturn resDyn_ = tryGetDeclaredToString(e.getKey(), _page);
            if (resDyn_.isFoundMethod()) {
                ExecRootBlock ex_ = ExecOperationNode.fetchType(resDyn_.getRootNumber(), _page);
                ExecNamedFunctionBlock fct_ = ExecOperationNode.fetchFunction(resDyn_.getRootNumber(),resDyn_.getMemberNumber(), _page);
                String fullName_ = e.getKey().getFullName();
                toStringMethodsToCallBodies_.addEntry(fullName_,new PolymorphMethod(ex_,fct_));
                _page.getToStringOwners().add(fullName_);
                _page.getCoverage().putToStringOwner(fullName_);
            }
        }
        _page.getToStringOwners().add(_page.getStandards().getAliasObject());
        _page.getCoverage().putToStringOwner(_page.getStandards().getAliasObject());
        for (EntryCust<RootBlock,ExecRootBlock> e: _page.getMapTypes().entryList()) {
            RootBlock c = e.getKey();
            _page.setGlobalClass(c.getGenericString());
            _page.setGlobalType(c);
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getMappings());
            for (Block b: getDirectChildren(c)) {
                if (b instanceof OverridableBlock) {
                    _page.setCurrentAnaBlock(c);
                    _page.setCurrentBlock(c);
                    ((OverridableBlock)b).buildTypes(c, _page);
                }
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: _page.getMapTypes().entryList()) {
            RootBlock root_ = e.getKey();
            IdMap<OverridableBlock, ExecOverridableBlock> allMethods_ = _page.getMapMembers().getValue(root_.getNumberAll()).getAllMethods();
            ClassMethodIdOverrides redirections_ = e.getValue().getRedirections();
            for (EntryCust<OverridableBlock,ExecOverridableBlock> f: allMethods_.entryList()) {
                OverridableBlock key_ = f.getKey();
                if (key_.hiddenInstance()) {
                    continue;
                }
                if (key_.isFinalMethod()) {
                    continue;
                }
                MethodId id_ = key_.getId();
                ClassMethodIdOverride override_ = new ClassMethodIdOverride(ExecOperationNode.fetchFunction(root_.getNumberAll(),key_.getNameNumber(), _page));
                StringMap<GeneStringOverridable> map_ = OverridesTypeUtil.getConcreteMethodsToCall(root_, id_, _page);
                map_.putAllMap(key_.getOverrides());
                for (EntryCust<String,GeneStringOverridable> g: map_.entryList()) {
                    override_.put(g.getKey(),g.getValue(), _page);
                }
                redirections_.add(override_);
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: _page.getMapTypes().entryList()) {
            RootBlock root_ = e.getKey();
            if (root_.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = root_.getAllGenericSuperTypesInfo();
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    String base_ = StringExpUtil.getIdFromAllTypes(s.getFormatted());
                    RootBlock superBl_ = s.getRootBlock();
                    for (OverridableBlock m: ClassesUtil.getMethodExecBlocks(superBl_)) {
                        if (m.isAbstractMethod()) {
                            ExecRootBlock ex_ = _page.getMapTypes().getValue(superBl_.getNumberAll());
                            ExecOverrideInfo val_ = ex_.getRedirections().getVal(ExecOperationNode.fetchFunction(superBl_.getNumberAll(),m.getNameNumber(), _page), root_.getFullName());
                            if (val_ == null) {
                                FoundErrorInterpret err_;
                                err_ = new FoundErrorInterpret();
                                err_.setFileName(root_.getFile().getFileName());
                                err_.setIndexFile(root_.getIdRowCol());
                                //type id
                                err_.buildError(
                                        _page.getAnalysisMessages().getAbstractMethodImpl(),
                                        base_,
                                        m.getSignature(_page),
                                        root_.getFullName());
                                _page.addLocError(err_);
                                root_.addNameErrors(err_);
                            }
                        }
                    }
                }
            } else {
                CustList<AnaFormattedRootBlock> allSuperClass_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(root_,root_.getGenericString()));
                allSuperClass_.addAllElts(root_.getAllGenericSuperTypesInfo());
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    RootBlock superBl_ = s.getRootBlock();
                    for (OverridableBlock m: ClassesUtil.getMethodExecBlocks(superBl_)) {
                        if (m.isAbstractMethod()) {
                            ExecRootBlock ex_ = _page.getMapTypes().getValue(superBl_.getNumberAll());
                            ExecOverrideInfo val_ = ex_.getRedirections().getVal(ExecOperationNode.fetchFunction(superBl_.getNumberAll(),m.getNameNumber(), _page), root_.getFullName());
                            if (val_ == null) {
                                ExecOverridableBlock value_ = _page.getMapMembers().getValue(superBl_.getNumberAll()).getAllMethods().getValue(m.getNameOverrideNumber());
                                e.getValue().getFunctionalBodies().add(new ExecFunctionalInfo(s.getFormatted(),value_));
                            }
                        }
                    }
                }
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: _page.getMapTypes().entryList()) {
            RootBlock root_ = e.getKey();
            Members valueMember_ = _page.getMapMembers().getValue(root_.getNumberAll());
            IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> allFct_ = valueMember_.getAllFct();
            IdMap<InfoBlock, ExecInfoBlock> allFields_ = valueMember_.getAllFields();
            for (Block b: ClassesUtil.getDirectChildren(root_)) {
                if (b instanceof MemberCallingsBlock) {
                    MemberCallingsBlock b1_ = (MemberCallingsBlock) b;
                    ExecMemberCallingsBlock value_ = allFct_.getValue(b1_.getNumberFct());
                    feedFct(_page, b1_, value_);
                }
                if (b instanceof InfoBlock) {
                    ExecInfoBlock value_ = allFields_.getValue(((InfoBlock)b).getFieldNumber());
                    for (AnonymousTypeBlock a: ((InfoBlock)b).getAnonymous()) {
                        value_.getAnonymous().add(_page.getMapTypes().getValue(a.getNumberAll()));
                    }
                    for (AnonymousFunctionBlock a: ((InfoBlock)b).getAnonymousFct()) {
                        value_.getAnonymousLambda().add(_page.getMapAnonLambda().getValue(a.getNumberLambda()));
                    }
                }
            }
            ExecRootBlock value_ = e.getValue();
            for (AnonymousFunctionBlock a: root_.getAnonymousRootFct()) {
                value_.getAnonymousRootLambda().add(_page.getMapAnonLambda().getValue(a.getNumberLambda()));
            }
            for (AnonymousTypeBlock a: root_.getAnonymousRoot()) {
                value_.getAnonymousRoot().add(_page.getMapTypes().getValue(a.getNumberAll()));
            }
        }
        for (EntryCust<AnonymousFunctionBlock, ExecAnonymousFunctionBlock> a: _page.getMapAnonLambda().entryList()) {
            AnonymousFunctionBlock key_ = a.getKey();
            ExecAnonymousFunctionBlock value_ = a.getValue();
            feedFct(_page, key_, value_);
        }
    }

    private static void feedFct(AnalyzedPageEl page_, MemberCallingsBlock b1_, ExecMemberCallingsBlock value_) {
        for (AnonymousFunctionBlock a: b1_.getAnonymousFct()) {
            value_.getAnonymousLambda().add(page_.getMapAnonLambda().getValue(a.getNumberLambda()));
        }
        for (AnonymousTypeBlock a: b1_.getAnonymous()) {
            value_.getAnonymous().add(page_.getMapTypes().getValue(a.getNumberAll()));
        }
        for (RootBlock a: b1_.getReserved()) {
            value_.getReserved().add(page_.getMapTypes().getValue(a.getNumberAll()));
        }
    }

    private static ClassMethodIdReturn tryGetDeclaredToString(RootBlock _class, AnalyzedPageEl _page) {
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        String baseCurName_ = _class.getFullName();
        fetchToStringMethods(_class,baseCurName_,methods_, _page);
        return getCustResultExec(methods_, _page);
    }

    private static ClassMethodIdReturn getCustResultExec(CustList<MethodInfo> _methods, AnalyzedPageEl _page) {
        Parametrable found_ = getFoundMethodExec(_methods, _page);
        if (!(found_ instanceof MethodInfo)) {
            return new ClassMethodIdReturn(false);
        }
        MethodInfo m_ = (MethodInfo) found_;
        MethodId constraints_ = m_.getConstraints();
        String baseClassName_ = m_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = m_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(m_.getReturnType());
        res_.setAncestor(m_.getAncestor());
        res_.setStandardMethod(m_.getStandardMethod());
        res_.setRootNumber(m_.getRootNumber());
        res_.setMemberNumber(m_.getMemberNumber());
        res_.setAbstractMethod(m_.isAbstractMethod());
        res_.setStaticMethod(m_.isStatic());
        return res_;
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

    private static void fetchToStringMethods(RootBlock _root, String _cl, CustList<MethodInfo> _methods, AnalyzedPageEl _page) {
        StringList geneSuperTypes_ = new StringList();
        geneSuperTypes_.add(_cl);
        geneSuperTypes_.addAllElts(_root.getAllSuperTypes());
        for (String t: geneSuperTypes_) {
            ToStringMethodHeader toString_ = _page.getToStringMethods().getVal(t);
            if (toString_ == null) {
                continue;
            }
            _methods.add(buildMethodToStringInfo(toString_, t));
        }
    }

    private static MethodInfo buildMethodToStringInfo(ToStringMethodHeader _m, String _formattedClass) {
        String ret_ = _m.getImportedReturnType();
        ParametersGroup p_ = new ParametersGroup();
        MethodId id_ = _m.getId();
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setRootNumber(_m.getNumberRoot());
        mloc_.setMemberNumber(_m.getNumberAll());
        mloc_.setClassName(_formattedClass);
        mloc_.setStaticMethod(id_.getKind());
        mloc_.setAbstractMethod(_m.isAbstractMethod());
        mloc_.setFinalMethod(_m.isFinalMethod());
        mloc_.setConstraints(id_);
        mloc_.setParameters(p_);
        mloc_.setReturnType(ret_);
        mloc_.setAncestor(0);
        mloc_.formatWithoutParams();
        return mloc_;
    }
    public static void buildAllBracesBodies(StringMap<String> _files, AnalyzedPageEl _page) {
        StringMap<ExecFileBlock> outExec_ = new StringMap<ExecFileBlock>();
        tryBuildAllBracedClassesBodies(_files, outExec_, _page);
        validateInheritingClasses(_page);
        validateIds(_page);
        validateOverridingInherit(_page);
        validateEl(_page);
        AnaTypeUtil.checkInterfaces(_page);
        AnalyzedPageEl page_ = _page;
        StringList basePkgFound_ = page_.getBasePackagesFound();
        StringList pkgFound_ = page_.getPackagesFound();
        while (true) {
            boolean contained_ = false;
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: page_.getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousTypeBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    contained_ = true;
                    if (parentType_ == null) {
                        continue;
                    }
                    StringMap<Integer> countsAnon_ = parentType_.getCountsAnon();
                    Integer val_ = countsAnon_.getVal(block_.getName());
                    if (val_ == null) {
                        countsAnon_.put(block_.getName(),1);
                        block_.setSuffix("*1");
                    } else {
                        countsAnon_.put(block_.getName(),val_+1);
                        block_.setSuffix("*"+(val_+1));
                    }
                }
            }
            for (IdMap<AnonymousLambdaOperation, ExecAnonymousLambdaOperation> m: page_.getMapAnonymousLambda()) {
                for (EntryCust<AnonymousLambdaOperation, ExecAnonymousLambdaOperation> e: m.entryList()) {
                    contained_ = true;
                    AnonymousFunctionBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    parentType_.setCountsAnonFct(parentType_.getCountsAnonFct()+1);
                    block_.setIntenName(Integer.toString(parentType_.getCountsAnonFct()));
                }
            }
            if (!contained_) {
                break;
            }
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: page_.getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousTypeBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    if (parentType_ == null) {
                        continue;
                    }
                    String base_ = e.getKey().getBase();
                    String enumClassName_ = page_.getStandards().getAliasEnumType();
                    String enumParamClassName_ = page_.getStandards().getAliasEnumParam();
                    if (StringList.quickEq(enumParamClassName_, base_)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(block_.getFile().getFileName());
                        undef_.setIndexFile(e.getKey().getIndex());
                        //original type len
                        undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                                block_.getFullName(),
                                base_);
                        _page.addLocError(undef_);
                        block_.addNameErrors(undef_);
                    }
                    if (StringList.quickEq(enumClassName_, base_)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(block_.getFile().getFileName());
                        undef_.setIndexFile(e.getKey().getIndex());
                        //original type len
                        undef_.buildError(_page.getAnalysisMessages().getReservedType(),
                                block_.getFullName(),
                                base_);
                        _page.addLocError(undef_);
                        block_.addNameErrors(undef_);
                    }
                }
            }
            page_.getPrevFoundTypes().addAllElts(page_.getFoundTypes());
            page_.getFoundTypes().clear();
            page_.getFoundOperators().clear();
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: page_.getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousTypeBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    if (parentType_ == null) {
                        continue;
                    }
                    int numberFile_ = block_.getFile().getNumberFile();
                    processType(basePkgFound_,pkgFound_,outExec_.getValue(numberFile_), block_, _page);
                }
            }
            IdMap<AnonymousFunctionBlock, ExecNamedFunctionBlock> retr_ = new IdMap<AnonymousFunctionBlock, ExecNamedFunctionBlock>();
            for (IdMap<AnonymousLambdaOperation, ExecAnonymousLambdaOperation> m: page_.getMapAnonymousLambda()) {
                for (EntryCust<AnonymousLambdaOperation, ExecAnonymousLambdaOperation> e: m.entryList()) {
                    AnonymousLambdaOperation key_ = e.getKey();
                    e.getValue().setExecAnonymousLambdaOperation(key_, _page);
                    AnonymousFunctionBlock method_ = key_.getBlock();
                    retr_.addEntry(method_,e.getValue().getFunction());
                }
            }
            for (EntryCust<AnonymousFunctionBlock, ExecNamedFunctionBlock> e: retr_.entryList()) {
                AnonymousFunctionBlock block_ = e.getKey();
                int numberFile_ = block_.getFile().getNumberFile();
                ExecFileBlock value_ = outExec_.getValue(numberFile_);
                e.getValue().setFile(value_);
                processType(basePkgFound_,pkgFound_, value_, block_, _page);
            }
            for (RootBlock r: page_.getFoundTypes()) {
                for (Block b: getDirectChildren(r)) {
                    if (b instanceof MemberCallingsBlock) {
                        MemberCallingsBlock m_ = (MemberCallingsBlock) b;
                        MemberCallingsBlock outerFuntion_ = m_.getStrictOuterFuntion();
                        if (outerFuntion_ != null) {
                            m_.getMappings().putAllMap(outerFuntion_.getMappings());
                        }
                    }
                }
            }
            for (RootBlock r: page_.getFoundTypes()) {
                MemberCallingsBlock outerFuntion_ = r.getStrictOuterFuntion();
                if (outerFuntion_ != null) {
                    r.getMappings().putAllMap(outerFuntion_.getMappings());
                }
            }
            innerFetchExec(_page);
            validateInheritingClasses(_page);
            validateIds(_page);
            validateOverridingInherit(_page);
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: page_.getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousInstancingOperation key_ = e.getKey();
                    AnonymousTypeBlock block_ = key_.getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    if (parentType_ == null) {
                        continue;
                    }
                    page_.setGlobalClass(key_.getGlClass());
                    key_.postAnalyze(_page);
                    ExecAnonymousInstancingOperation value_ = e.getValue();
                    CustList<OperationNode> read_ = key_.getChildrenNodes();
                    CustList<ExecOperationNode> write_ = value_.getChildrenNodes();
                    int min_ = Math.min(read_.size(),write_.size());
                    for (int i = 0; i < min_;i++){
                        OperationNode r_ = read_.get(i);
                        ExecOperationNode w_ = null;
                        int in_ = -1;
                        if (r_ instanceof NamedArgumentOperation) {
                            w_ = write_.get(i);
                            in_ = ((NamedArgumentOperation)r_).getIndex();
                        }
                        if (w_ instanceof ExecNamedArgumentOperation) {
                            ((ExecNamedArgumentOperation)w_).setIndex(in_);
                        }
                    }
                    value_.setExecAnonymousInstancingOperation(key_, _page);
                }
            }

            page_.getMapAnonymous().clear();
            page_.getMapAnonymousLambda().clear();
            validateEl(_page);
            for (EntryCust<AnonymousFunctionBlock, ExecNamedFunctionBlock> e:retr_.entryList()) {
                AnonymousFunctionBlock method_ = e.getKey();
                RootBlock c_ = method_.getParentType();
                page_.setImporting(c_);
                page_.setImportingAcces(new TypeAccessor(c_.getFullName()));
                page_.setImportingTypes(c_);
                page_.setGlobalClass(c_.getGenericString());
                page_.setGlobalType(c_);
                page_.setGlobalDirType(c_);
                page_.getCache().getLocalVariables().clear();
                page_.getCache().getLoopVariables().clear();
                page_.getCache().getLocalVariables().addAllElts(method_.getCache().getLocalVariables());
                page_.getCache().getLoopVariables().addAllElts(method_.getCache().getLoopVariables());
                page_.getCoverage().putCalls(c_.getFullName(),method_);
                StringList params_ = method_.getParametersNames();
                StringList types_ = method_.getImportedParametersTypes();
                prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                page_.getMappingLocal().clear();
                page_.getMappingLocal().putAllMap(method_.getMappings());
                method_.buildFctInstructionsReadOnly(e.getValue(), _page);
                AnalyzingEl a_ = page_.getAnalysisAss();
                a_.setVariableIssue(page_.isVariableIssue());
                page_.getResultsMethod().addEntry(method_,a_);
            }
            AnaTypeUtil.checkInterfaces(_page);
        }

    }

    public static void tryBuildAllBracedClassesBodies(StringMap<String> _files, StringMap<ExecFileBlock> _outExec, AnalyzedPageEl _page) {
        LgNames stds_ = _page.getStandards();
        StringMap<String> files_ = stds_.buildFiles(_page);
        StringMap<FileBlock> out_ = new StringMap<FileBlock>();
        buildFilesBodies(files_,true,out_, _outExec, _page);
        buildFilesBodies(_files,false,out_, _outExec, _page);
        parseFiles(out_, _outExec, _page);
    }

    public static void processBracedClass(boolean _add, ExecFileBlock _exFile, Block _outer, RootBlock _root, AnalyzedPageEl _page) {
        String fullName_ = _root.getFullName();
        boolean ok_ = _add;
        if (_page.getClasses().getClassesBodies().contains(fullName_)) {
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
        _page.setCurrentAnaBlock(_root);
        String packageName_;
        packageName_ = _root.getPackageName();
        LgNames lgNames_ = _page.getStandards();
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
            StringList elements_ = StringList.splitChars(packageName_, DOT);
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
        String className_;
        className_ = _root.getName().trim();
        TokenErrorMessage resClName_ = ManageTokens.partClass(_page).checkToken(className_, _page);
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
        String fullDef_ = _root.getFullDefinition();
        StringList varTypes_ = new StringList();
        String objectClassName_ = _page.getStandards().getAliasObject();
        StringList params_ = StringExpUtil.getAllTypes(fullDef_);
        StringList namesFromParent_ = getParamVarFromParent(_root);
        int tempOff_ = _root.getTemplateDefOffset() + 1;
        for (String p: params_.mid(CustList.SECOND_INDEX)) {
            int delta_ = 0;
            TypeVar type_ = new TypeVar();
            int indexDef_ = p.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(p, indexDef_);
            String id_ = parts_.first();
            int offId_ = tempOff_ + StringList.getFirstPrintableCharIndex(p);
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
                type_.setLength(id_.length()+1);
                tempOff_ += p.length() + 1;
                continue;
            }
            TokenErrorMessage res_ = ManageTokens.partVarClass(_page).checkToken(id_, _page);
            if (res_.isError()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.setBuiltError(res_.getMessage());
                _page.addLocError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringList.contains(varTypes_, id_)) {
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
            if (StringList.contains(namesFromParent_, id_)) {
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
            if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                int begin_ = delta_ + indexDef_;
                Ints ct_ = new Ints();
                for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                    int off_ = begin_ + StringList.getFirstPrintableCharIndex(b);
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
            type_.setLength(id_.length()+delta_);
            tempOff_ += p.length() + 1;
        }
        if (_root instanceof EnumBlock) {
            String fullNameOrig_ = _root.getFullName();
            StringBuilder generic_ = new StringBuilder(fullNameOrig_);
            if (!_root.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t:_root.getParamTypes()) {
                    vars_.add(StringList.concat(AnaTemplates.PREFIX_VAR_TYPE,t.getName()));
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(StringList.join(vars_, Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            StringBuilder sBuild_ = new StringBuilder(_page.getStandards().getAliasEnumParam());
            sBuild_.append(Templates.TEMPLATE_BEGIN);
            sBuild_.append(generic_);
            sBuild_.append(Templates.TEMPLATE_END);
            String type_ = sBuild_.toString();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof InnerElementBlock) {
            InnerElementBlock i_ = (InnerElementBlock) _root;
            EnumBlock par_ = i_.getParentEnum();
            String type_ = StringList.concat(par_.getFullName(),i_.getTempClass());
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof AnnotationBlock) {
            String type_ = _page.getStandards().getAliasAnnotationType();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (lgNames_.getStandards().contains(fullName_)) {
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
        _page.getFoundTypes().add(_root);
        if (ok_) {
            _page.getRefFoundTypes().add(_root);
            _page.getCoverage().putType(_root);
        }
        RootBlock parentType_ = _root.getParentType();
        int index_ = -1;
        if (parentType_ != null) {
            index_ = parentType_.getNumberAll();
            _root.setFile(parentType_.getFile());
        }
        ExecRootBlock execParentType_ = null;
        if (_page.getMapTypes().getKeys().isValidIndex(index_)) {
            execParentType_ = _page.getMapTypes().getValue(index_);
        }
        if (_root instanceof AnonymousTypeBlock) {
            ExecAnonymousTypeBlock e_ = new ExecAnonymousTypeBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(_page.getMapTypes().size());
            _page.getMapTypes().put(_root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
        }
        if (_root instanceof ClassBlock) {
            ExecClassBlock e_ = new ExecClassBlock((ClassBlock) _root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(_page.getMapTypes().size());
            _page.getMapTypes().put(_root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof EnumBlock) {
            ExecEnumBlock e_ = new ExecEnumBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(_page.getMapTypes().size());
            _page.getMapTypes().put(_root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InterfaceBlock) {
            ExecInterfaceBlock e_ = new ExecInterfaceBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(_page.getMapTypes().size());
            _page.getMapTypes().put(_root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof AnnotationBlock) {
            ExecAnnotationBlock e_ = new ExecAnnotationBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(_page.getMapTypes().size());
            _page.getMapTypes().put(_root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InnerElementBlock) {
            ExecInnerElementBlock e_ = new ExecInnerElementBlock((InnerElementBlock) _root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(_page.getMapTypes().size());
            _page.getMapTypes().put(_root, e_);
            ((InnerElementBlock) _root).setNumberInner(_page.getMapInnerEltTypes().size());
            _page.getMapInnerEltTypes().put((InnerElementBlock) _root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
    }

    private static StringList getParamVarFromParent(RootBlock _root) {
        if (_root.isStaticType()) {
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
            if (r_.isStaticType()) {
                add_ = false;
            }
        }
        return namesFromParent_;
    }

    private static void appendType(ExecFileBlock _exFile, Block _outer, RootBlock _root, ExecRootBlock e_) {
        if (_outer == _root) {
            _exFile.appendChild(e_);
        }
    }

    private static void innerFetchExec(AnalyzedPageEl _page) {
        IdMap<RootBlock, ExecRootBlock> mapTypes_ = _page.getMapTypes();
        for (RootBlock r: _page.getFoundTypes()) {
            ExecRootBlock current_ = mapTypes_.getValue(r.getNumberAll());
            RootBlock k_ = r;
            Members mem_ = new Members();
            mem_.getAllAnnotables().addEntry(k_,current_);
            for (Block b: getDirectChildren(k_)) {
                if (b instanceof RootBlock) {
                    ExecRootBlock val_ = mapTypes_.getValue(((RootBlock) b).getNumberAll());
                    current_.getChildrenTypes().add(val_);
                    mem_.getAllAnnotables().addEntry((RootBlock) b,val_);
                }
                if (b instanceof InnerElementBlock) {
                    ExecInnerElementBlock val_ = _page.getMapInnerEltTypes().getValue(((InnerElementBlock) b).getNumberInner());
                    current_.appendChild(val_);
                    current_.getEnumElements().add(val_);
                    ((InfoBlock) b).setFieldNumber(mem_.getAllFields().size());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllElementFields().addEntry((InnerElementBlock) b,val_);
                }
                if (b instanceof ElementBlock) {
                    ExecElementBlock val_ = new ExecElementBlock((ElementBlock) b);
                    current_.appendChild(val_);
                    current_.getEnumElements().add(val_);
                    val_.setFile(current_.getFile());
                    ((InfoBlock) b).setFieldNumber(mem_.getAllFields().size());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllElementFields().addEntry((ElementBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((ElementBlock) b,val_);
                }
                if (b instanceof FieldBlock) {
                    ExecFieldBlock val_ = new ExecFieldBlock((FieldBlock) b);
                    current_.appendChild(val_);
                    if (!((FieldBlock)b).isStaticField()) {
                        current_.getInstanceFields().add(val_);
                    }
                    val_.setFile(current_.getFile());
                    ((InfoBlock) b).setFieldNumber(mem_.getAllFields().size());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllExplicitFields().addEntry((FieldBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((FieldBlock) b,val_);
                }
                if (b instanceof ConstructorBlock) {
                    ExecConstructorBlock val_ = new ExecConstructorBlock((ConstructorBlock)b);
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllCtors().addEntry((ConstructorBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((ConstructorBlock) b,val_);
                    ((ConstructorBlock) b).setNameNumber(mem_.getAllNamed().size());
                    mem_.getAllNamed().addEntry((ConstructorBlock) b,val_);
                    ((MemberCallingsBlock)b).setNumberFct(mem_.getAllFct().size());
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof OverridableBlock) {
                    ExecOverridableBlock val_ = new ExecOverridableBlock((OverridableBlock)b);
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    ((OverridableBlock) b).setNameOverrideNumber(mem_.getAllMethods().size());
                    mem_.getAllMethods().addEntry((OverridableBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((OverridableBlock) b,val_);
                    ((OverridableBlock) b).setNameNumber(mem_.getAllNamed().size());
                    mem_.getAllNamed().addEntry((OverridableBlock) b,val_);
                    ((MemberCallingsBlock)b).setNumberFct(mem_.getAllFct().size());
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof AnnotationMethodBlock) {
                    ExecAnnotationMethodBlock val_ = new ExecAnnotationMethodBlock((AnnotationMethodBlock)b);
                    current_.appendChild(val_);
                    current_.getAnnotationsFields().add(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllAnnotMethods().addEntry((AnnotationMethodBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((AnnotationMethodBlock) b,val_);
                    ((AnnotationMethodBlock) b).setNameNumber(mem_.getAllNamed().size());
                    mem_.getAllNamed().addEntry((AnnotationMethodBlock) b,val_);
                    ((MemberCallingsBlock)b).setNumberFct(mem_.getAllFct().size());
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof InstanceBlock) {
                    ExecInstanceBlock val_ = new ExecInstanceBlock(b.getOffset());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    ((InitBlock) b).setNumber(mem_.getAllInits().size());
                    val_.setNumber(mem_.getAllInits().size());
                    mem_.getAllInits().put((InitBlock) b,val_);
                    ((MemberCallingsBlock)b).setNumberFct(mem_.getAllFct().size());
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof StaticBlock) {
                    ExecStaticBlock val_ = new ExecStaticBlock(b.getOffset());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    ((InitBlock) b).setNumber(mem_.getAllInits().size());
                    val_.setNumber(mem_.getAllInits().size());
                    mem_.getAllInits().put((InitBlock) b,val_);
                    ((MemberCallingsBlock)b).setNumberFct(mem_.getAllFct().size());
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
            }
            _page.getMapMembers().addEntry(k_, mem_);
        }
    }

    public static void buildFilesBodies(StringMap<String> _files, boolean _predefined, StringMap<FileBlock> _out, StringMap<ExecFileBlock> _outExec, AnalyzedPageEl _page) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileBlock fileBlock_ = new FileBlock(new OffsetsBlock(),_predefined);
            fileBlock_.setFileName(file_);
            AnalyzedPageEl page_ = _page;
            page_.putFileBlock(file_, fileBlock_);
            page_.getCoverage().putFile(fileBlock_);
            page_.getErrors().putFile(fileBlock_, _page);
            fileBlock_.processLinesTabsWithError(content_, _page);
            fileBlock_.setNumberFile(_out.size());
            _out.addEntry(file_,fileBlock_);
            ExecFileBlock exFile_ = new ExecFileBlock(fileBlock_);
            _outExec.addEntry(file_,exFile_);
        }
    }
    public static void parseFiles(StringMap<FileBlock> _files, StringMap<ExecFileBlock> _outExec, AnalyzedPageEl _page) {
        for (EntryCust<String,FileBlock> f: _files.entryList()) {
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
        for (EntryCust<String,FileBlock> f: _files.entryList()) {
            pkgFound_.addAllElts(f.getValue().getAllPackages());
        }
        for (EntryCust<String,FileBlock> f: _files.entryList()) {
            basePkgFound_.addAllElts(f.getValue().getAllBasePackages());
        }
        int i_ = 0;
        for (EntryCust<String,FileBlock> f: _files.entryList()) {
            FileBlock value_ = f.getValue();
            ExecFileBlock exFile_ = _outExec.getValue(i_);
            fetchByFile(basePkgFound_, pkgFound_, value_, exFile_, _page);
            i_++;
        }
        for (RootBlock r: _page.getFoundTypes()) {
            for (Block b: getDirectChildren(r)) {
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
        innerFetchExec(_page);
    }

    public static void fetchByFile(StringList _basePkgFound, StringList _pkgFound, FileBlock _anaFile, ExecFileBlock _exeFile, AnalyzedPageEl _page) {
        for (Block b: getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processType(_basePkgFound, _pkgFound, _exeFile, r_, _page);
            }
            if (b instanceof OperatorBlock) {
                OperatorBlock r_ = (OperatorBlock) b;
                ExecOperatorBlock e_ = new ExecOperatorBlock(r_);
                _exeFile.appendChild(e_);
                e_.setFile(_exeFile);
                AnalyzedPageEl page_ = _page;
                page_.getClasses().getOperators().add(e_);
                page_.getFoundOperators().add(r_);
                r_.setNameNumber(page_.getMapOperators().size());
                page_.getMapOperators().put(r_,e_);
                page_.getCoverage().putOperator(r_);
                Block c_ = r_;
                if (r_.getFirstChild() != null) {
                    while (true) {
                        if (c_ instanceof RootBlock) {
                            RootBlock cur_ = (RootBlock) c_;
                            String s_ = cur_.getName();
                            FoundErrorInterpret d_ = new FoundErrorInterpret();
                            d_.setFileName(r_.getFile().getFileName());
                            d_.setIndexFile(cur_.getIdRowCol());
                            //s_ len
                            d_.buildError(_page.getAnalysisMessages().getDuplicatedInnerType(),
                                    s_);
                            _page.addLocError(d_);
                            cur_.addNameErrors(d_);
                        }
                        Block fc_ = c_.getFirstChild();
                        if (fc_ != null) {
                            c_ = fc_;
                            continue;
                        }
                        boolean end_ = false;
                        while (true) {
                            Block n_ = c_.getNextSibling();
                            if (n_ != null) {
                                c_ = n_;
                                break;
                            }
                            BracedBlock p_ = c_.getParent();
                            if (p_ == r_) {
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
            }
        }
    }

    private static void processType(StringList _basePkgFound, StringList _pkgFound, ExecFileBlock _exeFile, Block _r, AnalyzedPageEl _page) {
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
        if (_r instanceof AnonymousFunctionBlock) {
            AnonymousFunctionBlock r_ = (AnonymousFunctionBlock) _r;
            allReservedInnersRoot_.addAllElts(r_.getAllReservedInners());
        }
        Block c_ = _r;
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
                    String s_ = cur_.getName();
                    MemberCallingsBlock outerFuntion_ = cur_.getOuterFuntionInType();
                    cur_.getAllReservedInners().addAllElts(allReservedInnersRoot_);
                    if (!(c_ instanceof AnonymousTypeBlock) && possibleParent_ != null) {
                        cur_.getAllReservedInners().addAllElts(possibleParent_.getAllReservedInners());
                        if (StringList.contains(allReservedInnersRoot_, s_)) {
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
                            for (RootBlock o:outerFuntion_.getReserved()) {
                                cur_.getAllReservedInners().add(o.getName());
                            }
                            if (StringList.contains(possibleParent_.getAllReservedInners(), s_)) {
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
                            if (StringList.contains(namesFromParent_, s_)) {
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
                                Integer val_ = possibleParent_.getCounts().getVal(s_);
                                if (val_ == null) {
                                    possibleParent_.getCounts().put(s_,1);
                                    cur_.setSuffix("+1");
                                } else {
                                    possibleParent_.getCounts().put(s_,val_+1);
                                    cur_.setSuffix("+"+(val_+1));
                                }
                                mappings_.put(s_, new MappingLocalType(cur_.getFullName(),cur_.getSuffixedName(),possibleParent_,cur_));

                            }

                        }
                    }
                    if (StringList.contains(_basePkgFound, s_)) {
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
                    } else if (StringList.contains(simpleNames_, s_)) {
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
                    ClassesUtil.processBracedClass(addPkg_&&add_, _exeFile, _r,cur_, _page);
                }
                Block fc_ = c_.getFirstChild();
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
                    Block n_ = c_.getNextSibling();
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
                ClassesUtil.processBracedClass(addPkg_, _exeFile, _r, (RootBlock) _r, _page);
            }
        }
    }

    private static CustList<RootBlock> inners(MemberCallingsBlock _caller) {
        CustList<RootBlock> list_ = new CustList<RootBlock>();
        Block c_ = _caller;
        if (_caller.getFirstChild() != null) {
            while (true) {
                if (c_ instanceof RootBlock) {
                    RootBlock cur_ = (RootBlock) c_;
                    list_.add(cur_);
                } else {
                    Block fc_ = c_.getFirstChild();
                    if (fc_ != null) {
                        c_ = fc_;
                        continue;
                    }
                }
                boolean end_ = false;
                while (true) {
                    Block n_ = c_.getNextSibling();
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

    public static CustList<ExecBlock> getSortedDescNodes(ExecBlock _root) {
        CustList<ExecBlock> list_ = new CustList<ExecBlock>();
        ExecBlock c_ = _root;
        ExecBlock f_ = c_.getFirstChild();
        list_.add(c_);
        if (f_ == null) {
            return list_;
        }
        c_ = f_;
        while (c_ != null) {
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static ExecBlock getNext(ExecBlock _current, ExecBlock _root) {
        ExecBlock n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        ExecBlock current_ = _current;
        while (true) {
            n_ = current_.getNextSibling();
            if (n_ != null) {
                return n_;
            }
            n_ = current_.getParent();
            if (n_ == _root) {
                return null;
            }
            current_ = n_;
        }
    }

    public static CustList<Block> getDirectChildren(Block _element) {
        CustList<Block> list_ = new CustList<Block>();
        if (_element == null) {
            return list_;
        }
        Block elt_ = _element.getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public static void validateInheritingClasses(AnalyzedPageEl _page) {
        AnalyzedPageEl page_ = _page;
        String objectClassName_ = page_.getStandards().getAliasObject();
        page_.getListTypesNames().clear();
        validateInheritingClassesId(_page);
        CustList<RootBlock> listTypes_ = page_.getListTypesNames();
        for (RootBlock s: listTypes_) {
            page_.setCurrentBlock(s);
            page_.setCurrentAnaBlock(s);
            s.buildDirectGenericSuperTypes(_page);
        }
        for (RootBlock c: page_.getFoundTypes()) {
            page_.setCurrentBlock(c);
            page_.setCurrentAnaBlock(c);
            c.buildMapParamType(_page);
        }
        for (RootBlock c: page_.getFoundTypes()) {
            page_.getMapTypes().getValue(c.getNumberAll()).buildMapParamType(c);
        }
        for (RootBlock c: page_.getFoundTypes()) {
            if (c.isStaticType()) {
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
                if (s_.isStaticType()) {
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
                for (Block m: getDirectChildren(a)) {
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
                    for (Block m: getDirectChildren(g_)) {
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
        String objectClassName_ = _page.getStandards().getAliasObject();
        String enumClassName_ = _page.getStandards().getAliasEnumType();
        String enumParamClassName_ = _page.getStandards().getAliasEnumParam();
        String annotName_ = _page.getStandards().getAliasAnnotationType();
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
                ExecRootBlock exec_ = _page.getMapTypes().getVal(r);
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
                    exec_.getAllSuperTypes().addAllElts(foundNames_.getKeys());
                    r.getAllSuperTypes().add(objectClassName_);
                    exec_.getAllSuperTypes().add(objectClassName_);
                    r.getAllSuperTypes().removeDuplicates();
                    exec_.getAllSuperTypes().removeDuplicates();
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
                    String void_ = _page.getStandards().getAliasVoid();
                    if (StringList.quickEq(idSuper_, void_)) {
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
                        if (_page.getStandards().getStandards().contains(idSuper_)) {
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
                        if (StringList.quickEq(enumParamClassName_, foundType_)) {
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
                        if (StringList.quickEq(enumClassName_, foundType_) && !StringList.quickEq(c, enumParamClassName_)) {
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
                for (FoundSuperType f: types_) {
                    dup_.add(f.getName());
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
                        undef_.setIndexFile(0);
                        //original type len
                        undef_.buildError(_page.getAnalysisMessages().getDuplicateSuper(),
                                c,e.getKey(),Integer.toString(e.getValue()));
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
                    if (r.isStaticType()) {
                        if (!s_.isStaticType()) {
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
                                    Integer.toString(subSise_-1),
                                    Integer.toString(supSise_-1));
                            _page.addLocError(enum_);
                            r.addNameErrors(enum_);
                        }
                    }
                    if (r instanceof InterfaceBlock) {
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
                            c,Integer.toString(nbDirectSuperClass_));
                    _page.addLocError(enum_);
                    r.addNameErrors(enum_);
                }
                r.getAllSuperTypes().addAllElts(dup_);
                exec_.getAllSuperTypes().addAllElts(dup_);
                for (FoundSuperType f: types_) {
                    RootBlock s_ = f.getType();
                    exec_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                    r.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                }
                r.getAllSuperTypes().add(objectClassName_);
                exec_.getAllSuperTypes().add(objectClassName_);
                r.getAllSuperTypes().removeDuplicates();
                exec_.getAllSuperTypes().removeDuplicates();
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
                    undef_.setIndexFile(0);
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
        LgNames stds_ = _page.getStandards();
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
                        all_.add(i.getType());
                    }
                    StringMap<StringList>baseParams_ = getBaseParams(all_);
                    for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                        if (!e.getValue().onlyOneElt()) {
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setFileName(s.getFile().getFileName());
                            duplicate_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            duplicate_.buildError(_page.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                                    StringList.join(e.getValue(),"&"));
                            _page.addLocError(duplicate_);
                            s.addNameErrors(duplicate_);
                        }
                    }

                }
                if (okLoc_) {
                    int nbAbs_ = 0;
                    int nbFinal_ = 0;
                    if (existNative_) {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = StringExpUtil.getIdFromAllTypes(b);
                            String base_ = StringExpUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            StandardType type_ = stds_.getStandards().getVal(base_);
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
                                    Integer.toString(nbAbs_));
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
                                    Integer.toString(nbFinal_));
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
            for (TypeVar t: s.getParamTypesMapValues()) {
                for (AnaResultPartType b: t.getResults()) {
                    if (!AnaPartTypeUtil.processAnalyzeConstraints(b, map_, true, _page)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                                b.getResult());
                        _page.addLocError(un_);
                        s.addNameErrors(un_);
                    }
                }
            }
            for (AnaResultPartType t: s.getResults()) {
                if (!AnaPartTypeUtil.processAnalyzeConstraints(t, map_, true, _page)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    // char : before super type
                    un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                            t.getResult());
                    _page.addLocError(un_);
                    s.addNameErrors(un_);
                }
            }
        }
    }

    private static void validateSingleParameterizedClasses(AnalyzedPageEl _page) {
        for (RootBlock i: _page.getFoundTypes()) {
            CustList<AnaFormattedRootBlock> genericSuperTypes_ = i.fetchAllGenericSuperTypes();
            for (AnaFormattedRootBlock a: genericSuperTypes_) {
                i.getAllGenericSuperTypes().add(a.getFormatted());
            }
            StringMap<StringList> baseParams_ = getBaseParams(i.getAllGenericSuperTypes());
            for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                if (!e.getValue().onlyOneElt()) {
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setFileName(i.getFile().getFileName());
                    duplicate_.setIndexFile(i.getIdRowCol());
                    //original id len
                    duplicate_.buildError(_page.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                            StringList.join(e.getValue(),"&"));
                    _page.addLocError(duplicate_);
                    i.addNameErrors(duplicate_);
                }
            }
            i.getAllGenericSuperTypesInfo().addAllElts(genericSuperTypes_);
            CustList<AnaFormattedRootBlock> genericClasses_ = i.fetchAllGenericClasses();
            for (AnaFormattedRootBlock a: genericClasses_) {
                i.getAllGenericClasses().add(a.getFormatted());
            }
            ExecRootBlock value_ = _page.getMapTypes().getValue(i.getNumberAll());
            if (i instanceof UniqueRootedBlock && genericClasses_.size() > 1) {
                value_.setUniqueType(ExecOperationNode.fetchType(genericClasses_.get(1).getRootBlock().getNumberAll(), _page));
            }
            ConstructorBlock emptyCtor_ = i.getEmptyCtor();
            if (emptyCtor_ != null) {
                value_.setEmptyCtor(ExecOperationNode.fetchFunction(i.getNumberAll(),emptyCtor_.getNameNumber(), _page));
            }
            i.getAllGenericClassesInfo().addAllElts(genericClasses_);
        }
        for (RootBlock i: _page.getFoundTypes()) {
            CustList<AnaFormattedRootBlock> allGenericSuperTypes_ = i.getAllGenericSuperTypesInfo();
            CustList<ExecFormattedRootBlock> l_ = new CustList<ExecFormattedRootBlock>();
            for (AnaFormattedRootBlock s: allGenericSuperTypes_) {
                l_.add(new ExecFormattedRootBlock(_page.getMapTypes().getValue(s.getRootBlock().getNumberAll()),s.getFormatted()));
            }
            _page.getMapTypes().getValue(i.getNumberAll()).getAllGenericSuperTypes().addAllElts(l_);
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
        AnalyzedPageEl page_ = _page;
        IdMap<RootBlock, ExecRootBlock> mapTypes_ = page_.getMapTypes();
        for (RootBlock c: page_.getFoundTypes()) {
            ExecRootBlock type_ = mapTypes_.getValue(c.getNumberAll());
            page_.setGlobalClass(c.getGenericString());
            page_.setGlobalType(c);
            page_.setGlobalDirType(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
            page_.getMappingLocal().clear();
            page_.getMappingLocal().putAllMap(c.getMappings());
            c.validateIds(type_,page_.getMapMembers().getVal(c), _page);
            if (c.getNbOperators() > 0) {
                _page.getTypesWithInnerOperators().add(c.getFullName());
            }
        }
        for (RootBlock c: page_.getFoundTypes()) {
            ExecRootBlock type_ = mapTypes_.getValue(c.getNumberAll());
            type_.validateIds(c,page_.getMapMembers());
        }
        for (RootBlock c: page_.getFoundTypes()) {
            page_.setGlobalClass(c.getGenericString());
            page_.setGlobalType(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
            page_.getMappingLocal().clear();
            page_.getMappingLocal().putAllMap(c.getMappings());
            for (Block b: getDirectChildren(c)) {
                if (b instanceof InternOverrideBlock) {
                    page_.setCurrentAnaBlock(c);
                    page_.setCurrentBlock(c);
                    ((InternOverrideBlock)b).buildTypes(c, _page);
                }
            }
        }
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        page_.setGlobalClass("");
        page_.setGlobalType((RootBlock) null);
        for (OperatorBlock o: _page.getFoundOperators()) {
            ExecOperatorBlock value_ = _page.getMapOperators().getValue(o.getNameNumber());
            String name_ = o.getName();
            page_.setImporting(o);
            page_.setImportingAcces(new OperatorAccessor());
            page_.setImportingTypes(o);
            page_.getMappingLocal().clear();
            o.buildImportedTypes(_page);
            value_.buildImportedTypes(o);
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
                    duplicate_.setIndexFile(o.getOffset().getOffsetTrim());
                    duplicate_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                    //key word len
                    duplicate_.buildError(_page.getAnalysisMessages().getDuplicateOperator(),
                            id_.getSignature(page_));
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
                TokenErrorMessage res_ = ManageTokens.partParam(page_).checkToken(v, page_);
                if (res_.isError()) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
                    //param name len
                    b_.setBuiltError(res_.getMessage());
                    _page.addLocError(b_);
                    o.addParamErrors(i_,b_);
                }
                if (StringList.contains(seen_, v)){
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
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
        for (Block b: getDirectChildren(_clOwner)) {
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
    public static CustList<GeneCustStaticMethod> getMethodBlocks(AnaGeneType _element) {
        CustList<GeneCustStaticMethod> methods_ = new CustList<GeneCustStaticMethod>();
        if (_element instanceof RootBlock) {
            for (GeneCustStaticMethod m:getMethodAnaBlocks((RootBlock) _element)) {
                methods_.add(m);
            }
        }
        if (_element instanceof StandardType) {
            for (StandardMethod m : ((StandardType) _element).getMethods()) {
                methods_.add(m);
            }
        }
        return methods_;
    }


    public static CustList<GeneCustStaticMethod> getMethodAnaBlocks(RootBlock _element) {
        CustList<GeneCustStaticMethod> methods_ = new CustList<GeneCustStaticMethod>();
        for (Block b: getDirectChildren(_element)) {
            if (b instanceof OverridableBlock) {
                methods_.add((GeneCustStaticMethod) b);
            }
            if (b instanceof AnnotationMethodBlock) {
                methods_.add((GeneCustStaticMethod) b);
            }
        }
        return methods_;
    }
    public static CustList<RootBlock> accessedInnerElements(RootBlock _clOwner) {
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        for (Block b: getDirectChildren(_clOwner)) {
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
        for (OperatorBlock c: _page.getFoundOperators()) {
            brBl_.add(c);
        }
        for (RootBlock c: _page.getFoundTypes()) {
            brBl_.add(c);
        }
        for (BracedBlock c: brBl_) {
            for (int i: c.getBadIndexes()) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(c.getFile().getFileName());
                b_.setIndexFile(Math.max(0,Math.min(c.getFile().getLength()-1,i)));
                //underline index char
                b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
                _page.addLocError(b_);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(b_);
                g_.setLength(1);
                c.getGlobalErrorsPars().add(g_);
            }
        }
        for (RootBlock c: _page.getFoundTypes()) {
            Members mem_ = _page.getMapMembers().getVal(c);
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock f_ = (InfoBlock) b;
                _page.getAllDeclaredFields().addAllElts(f_.getFieldName());
                if (!f_.isStaticField()) {
                    continue;
                }
                if (f_ instanceof FieldBlock) {
                    _page.getAssignedDeclaredFields().addAllElts(((FieldBlock)f_).getAssignedDeclaredFields());
                }
                if (f_ instanceof InnerTypeOrElement) {
                    _page.getAssignedDeclaredFields().addAllElts(f_.getFieldName());
                }
                int v_ = 0;
                for (String f: f_.getFieldName()) {
                    StringList err_ = getErFields(f_, v_);
                    checkConstField(err_,c, fullName_, f, _page);
                    v_++;
                }
            }
            for (Block b: bl_) {
                if (b instanceof InnerTypeOrElement) {
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    _page.setCurrentFct(null);
                    InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                    _page.setCurrentBlock(b);
                    _page.setCurrentAnaBlock(b);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(c.getMappings());
                    ExecInnerTypeOrElement val_ = mem_.getAllElementFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(val_, _page);
                    _page.getFieldsAssSt().addEntry(b,new AssElementBlock(method_));
                }
                if (b instanceof FieldBlock) {
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    FieldBlock method_ = (FieldBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    _page.setCurrentBlock(b);
                    _page.setCurrentAnaBlock(b);
                    _page.setCurrentFct(null);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(c.getMappings());
                    ExecFieldBlock exp_ = mem_.getAllExplicitFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(exp_, _page);
                    _page.getFieldsAssSt().addEntry(b,new AssFieldBlock(method_));
                }
                if (b instanceof StaticBlock) {
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    StaticBlock method_ = (StaticBlock) b;
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(mem_.getAllInits().getVal(method_), _page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAna().addEntry(method_,a_);
                }
            }
        }
        _page.setAssignedStaticFields(true);
        for (RootBlock c: _page.getFoundTypes()) {
            Members mem_ = _page.getMapMembers().getVal(c);
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            _page.getCoverage().putCalls(fullName_);
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
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
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    FieldBlock method_ = (FieldBlock) b;
                    _page.setCurrentBlock(b);
                    _page.setCurrentAnaBlock(b);
                    _page.setCurrentFct(null);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(c.getMappings());
                    ExecFieldBlock exp_ = mem_.getAllExplicitFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(exp_, _page);
                    _page.getFieldsAss().addEntry(b,new AssFieldBlock(method_));
                }
                if (b instanceof InstanceBlock) {
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    InstanceBlock method_ = (InstanceBlock) b;
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(mem_.getAllInits().getVal(method_), _page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaInst().addEntry(method_,a_);
                }
            }
            processInterfaceCtor(c, fullName_, bl_, _page);
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    _page.getInitFieldsCtors().clear();
                    _page.getInitFieldsCtors().addAllElts(_page.getInitFields());
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    _page.getCoverage().putCalls(fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(_page, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(mem_.getAllCtors().getVal(method_), _page);
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
            Members mem_ = _page.getMapMembers().getVal(c);
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
                if (!(b instanceof OverridableBlock)) {
                    continue;
                }
                OverridableBlock method_ = (OverridableBlock) b;
                if (isStdOrExplicit(method_)) {
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putCalls(fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(_page,method_.getParametersNamesOffset(), method_.getParamErrors(),params_, types_, method_.isVarargs());
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(mem_.getAllMethods().getVal(method_), _page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaMethod().addEntry(method_,a_);
                } else {
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putCalls(fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(_page, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                    processValueParam(_page, c, method_);
                    _page.getMappingLocal().clear();
                    _page.getMappingLocal().putAllMap(method_.getMappings());
                    method_.buildFctInstructionsReadOnly(mem_.getAllMethods().getVal(method_), _page);
                    AnalyzingEl a_ = _page.getAnalysisAss();
                    a_.setVariableIssue(_page.isVariableIssue());
                    _page.getResultsAnaMethod().addEntry(method_,a_);
                }
            }
        }
        _page.setGlobalClass("");
        _page.setGlobalType((RootBlock)null);
        _page.setGlobalDirType(null);
        putCallOperator(_page);
        for (OperatorBlock o: _page.getFoundOperators()) {
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            _page.getCoverage().putCalls("",o);
            StringList params_ = o.getParametersNames();
            StringList types_ = o.getImportedParametersTypes();
            ExecOperatorBlock value_ = _page.getMapOperators().getValue(o.getNameNumber());
            prepareParams(_page,o.getParametersNamesOffset(),o.getParamErrors(), params_, types_, o.isVarargs());
            _page.getMappingLocal().clear();
            o.buildFctInstructionsReadOnly(value_, _page);
            AnalyzingEl a_ = _page.getAnalysisAss();
            a_.setVariableIssue(_page.isVariableIssue());
            _page.getResultsAnaOperator().addEntry(o,a_);
        }
        _page.setAnnotAnalysis(true);
        for (RootBlock c: _page.getFoundTypes()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            Members mem_ = _page.getMapMembers().getVal(c);
            _page.setGlobalClass(c.getGenericString());
            _page.setGlobalType(c);
            _page.setCurrentFct(null);
            CustList<Block> annotated_ = new CustList<Block>();
            if (!(c instanceof InnerElementBlock)) {
                annotated_.add(c);
            }
            annotated_.addAllElts(getDirectChildren(c));
            _page.getCoverage().putBlockOperations(_page.getMapTypes().getVal(c),c);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getMappings());
            for (Block b:annotated_) {
                _page.setCurrentBlock(b);
                _page.setCurrentAnaBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    _page.setAnnotAnalysisField(true);
                    _page.setGlobalDirType(c);
                    ((AnnotationMethodBlock)b).buildExpressionLanguage(mem_.getAllAnnotMethods().getVal(((AnnotationMethodBlock)b)), _page);
                    _page.getCoverage().putBlockOperations(mem_.getAllAnnotMethods().getVal((AnnotationMethodBlock) b),b);
                    _page.setAnnotAnalysisField(false);
                }
                if (b instanceof RootBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putBlockOperationsField(_page, b);
                    ((RootBlock)b).buildAnnotations(mem_.getAllAnnotables().getVal((AnnotableBlock) b), _page);
                }
                if (b instanceof NamedFunctionBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putBlockOperationsField(_page, b);
                    ((NamedFunctionBlock)b).buildAnnotations(mem_.getAllNamed().getVal((NamedFunctionBlock) b), _page);
                    ((NamedFunctionBlock)b).buildAnnotationsParameters(mem_.getAllNamed().getVal((NamedFunctionBlock) b), _page);
                }
                if (b instanceof InfoBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putBlockOperationsField(_page, b);
                    _page.getCoverage().putBlockOperations((ExecBlock) mem_.getAllFields().getVal((InfoBlock) b),b);
                    ((InfoBlock)b).buildAnnotations(mem_.getAllAnnotables().getVal((AnnotableBlock) b), _page);
                }
            }
        }
        _page.setGlobalClass("");
        _page.setGlobalType((RootBlock)null);
        _page.setGlobalDirType(null);
        _page.setCurrentFct(null);
        for (OperatorBlock o: _page.getFoundOperators()) {
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            _page.setCurrentBlock(o);
            _page.setCurrentAnaBlock(o);
            _page.setAnnotAnalysisField(false);
            _page.getCoverage().putBlockOperationsField(_page, o);
            _page.getMappingLocal().clear();
            ExecOperatorBlock value_ = _page.getMapOperators().getValue(o.getNameNumber());
            o.buildAnnotations(value_, _page);
            o.buildAnnotationsParameters(value_, _page);
        }
        _page.setAnnotAnalysis(false);
        //init annotations here
        for (RootBlock c: _page.getFoundTypes()) {
            c.validateConstructors(_page);
        }
    }

    private static void validateFinals(AnalyzedPageEl _page) {
        AssignedVariablesBlock assVars_ = new AssignedVariablesBlock();
        _page.setAssignedStaticFields(false);
        _page.setAssignedFields(false);
        for (RootBlock c: _page.getMapTypes().getKeys()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.setGlobalClass(c.getGenericString());
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (Block b: bl_) {
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
            for (Block b: bl_) {
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
                if (!StringList.contains(_page.getInitFields(),key_)) {
                    //error
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(c.getFile().getFileName());
                    un_.setIndexFile(c.getOffset().getOffsetTrim());
                    un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                            key_,fullName_);
                    _page.addLocError(un_);
                }
            }

        }
        _page.setAssignedStaticFields(true);
        for (RootBlock c: _page.getMapTypes().getKeys()) {
            _page.setImporting(c);
            _page.setGlobalClass(c.getGenericString());
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (Block b: bl_) {
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
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    hasCtor_ = true;
                    break;
                }
            }
            StringMap<SimpleAssignment> assAfter_;
            assAfter_ = new StringMap<SimpleAssignment>();
            AssBlock pr_ = null;
            for (Block b: bl_) {
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
                    if (StringList.contains(_page.getInitFields(), fieldName_)) {
                        continue;
                    }
                    //error
                    for (Block b : bl_) {
                        if (b instanceof InfoBlock) {
                            if (StringList.contains(((InfoBlock) b).getFieldName(), fieldName_)) {
                                FoundErrorInterpret un_ = new FoundErrorInterpret();
                                un_.setFileName(c.getFile().getFileName());
                                un_.setIndexFile(((InfoBlock) b).getFieldNameOffset());
                                un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                                        fieldName_,fullName_);
                                _page.addLocError(un_);
                            }
                        }
                    }
                }
            }
            b_.putAllMap(AssignmentsUtil.assignSimpleBefore(assAfter_));
            for (Block b: bl_) {
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
                        if (StringList.contains(_page.getInitFieldsCtors(),fieldName_)) {
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

        for (RootBlock c: _page.getMapTypes().getKeys()) {
            _page.setImporting(c);
            _page.setGlobalClass(c.getGenericString());
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
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
        for (EntryCust<AnonymousFunctionBlock, AnalyzingEl> e: _page.getResultsMethod().entryList()) {
            AnonymousFunctionBlock method_ = e.getKey();
            RootBlock c_ = method_.getParentType();
            _page.setImporting(c_);
            _page.setImportingAcces(new TypeAccessor(c_.getFullName()));
            _page.setImportingTypes(c_);
            _page.setGlobalClass(c_.getGenericString());
            _page.setGlobalType(c_);
            _page.setGlobalDirType(c_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), method_);
            tryAnalyseAssign(assVars_, null, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
        assVars_.setCache(new AnaCache());
        _page.setGlobalClass("");
        _page.setGlobalType((RootBlock)null);
        _page.setGlobalDirType(null);
        for (EntryCust<OperatorBlock, AnalyzingEl> e: _page.getResultsAnaOperator().entryList()) {
            NamedFunctionBlock m_ = e.getKey();
            AnalyzingEl anAss_ = e.getValue();
            AssMemberCallingsBlock assign_ = AssBlockUtil.getExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), anAss_.getLabelsMapping(), m_);
            tryAnalyseAssign(assVars_, null, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
    }

    private static void tryAnalyseAssign(AssignedVariablesBlock assVars_, AssBlock pr_, AnalyzingEl anAss_, AssMemberCallingsBlock assign_, AnalyzedPageEl _page) {
        if (!anAss_.isVariableIssue()) {
            assign_.buildFctInstructions(pr_,assVars_, _page);
        }
    }

    private static void validateSimFinals(AnalyzedPageEl _page) {
        AssignedVariablesBlock assVars_ = new AssignedVariablesBlock();
        _page.setAssignedStaticFields(false);
        _page.setAssignedFields(false);
        for (RootBlock c: _page.getMapTypes().getKeys()) {
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.setGlobalClass(c.getGenericString());
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
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
        for (RootBlock c: _page.getMapTypes().getKeys()) {
            _page.setImporting(c);
            _page.setGlobalClass(c.getGenericString());
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.getInitFields().clear();
            _page.getAssignedDeclaredFields().clear();
            _page.getAllDeclaredFields().clear();
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
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
            for (Block b: bl_) {
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

        for (RootBlock c: _page.getMapTypes().getKeys()) {
            _page.setImporting(c);
            _page.setGlobalClass(c.getGenericString());
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
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

        for (EntryCust<AnonymousFunctionBlock, AnalyzingEl> e: _page.getResultsMethod().entryList()) {
            AnonymousFunctionBlock method_ = e.getKey();
            RootBlock c_ = method_.getParentType();
            _page.setImporting(c_);
            _page.setImportingAcces(new TypeAccessor(c_.getFullName()));
            _page.setImportingTypes(c_);
            _page.setGlobalClass(c_.getGenericString());
            _page.setGlobalType(c_);
            _page.setGlobalDirType(c_);
            AnalyzingEl anAss_ = e.getValue();
            assVars_.setCache(method_.getCache());
            AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), method_);
            tryAnalyseAssign(assVars_, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
        assVars_.setCache(new AnaCache());
        _page.setGlobalClass("");
        _page.setGlobalType((RootBlock)null);
        _page.setGlobalDirType(null);
        for (EntryCust<OperatorBlock, AnalyzingEl> e: _page.getResultsAnaOperator().entryList()) {
            NamedFunctionBlock m_ = e.getKey();
            AnalyzingEl anAss_ = e.getValue();
            AssSimStdMethodBlock assign_ = AssBlockUtil.getSimExecutableNodes(anAss_.getCanCompleteNormally(), anAss_.getCanCompleteNormallyGroup(), m_);
            tryAnalyseAssign(assVars_, anAss_, assign_, _page);
            _page.clearAllLocalVars(assVars_);
        }
    }
    private static void tryAnalyseAssign(AssignedVariablesBlock assVars_, AnalyzingEl anAss_, AssSimStdMethodBlock assign_, AnalyzedPageEl _page) {
        if (!anAss_.isVariableIssue()) {
            assign_.buildFctInstructions(assVars_, _page);
        }
    }

    private static StringMap<SimpleAssignment> getFieldsRoot(AssignedVariablesBlock assVars_, AssMemberCallingsBlock res_) {
        AssignedVariables val_ = assVars_.getFinalVariables().getVal(res_);
        if (val_ == null) {
            return new StringMap<SimpleAssignment>();
        }
        return val_.getFieldsRoot();
    }

    private static void putCallOperator(AnalyzedPageEl _page) {
        if (_page.getFoundOperators().isEmpty()) {
            return;
        }
        _page.getCoverage().putCalls("");
    }

    private static StringList getErFields(InfoBlock f_, int v_) {
        StringList errs_ = new StringList();
        if (f_ instanceof FieldBlock) {
            errs_ = ((FieldBlock)f_).getCstErrorsFields().get(v_);
        }
        return errs_;
    }

    private static boolean isStdOrExplicit(OverridableBlock method_) {
        return method_.getKind() == MethodKind.STD_METHOD || method_.getKind() == MethodKind.TO_STRING || method_.getKind() == MethodKind.EXPLICIT_CAST || method_.getKind() == MethodKind.IMPLICIT_CAST
                || method_.getKind() == MethodKind.TRUE_OPERATOR || method_.getKind() == MethodKind.FALSE_OPERATOR;
    }

    private static void processValueParam(AnalyzedPageEl _page, RootBlock _cl, OverridableBlock _method) {
        if (_method.getKind() == MethodKind.SET_INDEX) {
            String p_ = _page.getKeyWords().getKeyWordValue();
            CustList<OverridableBlock> getIndexers_ = new CustList<OverridableBlock>();
            for (Block d: getDirectChildren(_cl)) {
                if (!(d instanceof OverridableBlock)) {
                    continue;
                }
                OverridableBlock i_ = (OverridableBlock) d;
                if (i_.getKind() != MethodKind.GET_INDEX) {
                    continue;
                }
                if (!i_.getId().eqPartial(_method.getId())) {
                    continue;
                }
                getIndexers_.add(i_);
            }
            if (getIndexers_.size() == 1) {
                OverridableBlock matching_ = getIndexers_.first();
                String c_ = matching_.getImportedReturnType();
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(c_);
                lv_.setConstType(ConstType.PARAM);
                lv_.setKeyWord(true);
                _page.getInfosVars().put(p_, lv_);
            }
        }
    }

    private static void processInterfaceCtor(RootBlock _cl, String _name, CustList<Block> _blocks, AnalyzedPageEl _page) {
        boolean hasCtor_ = false;
        for (Block b: _blocks) {
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
            StringList.removeAllElements(allCopy_, _page.getStandards().getPredefinedInterfacesInitOrder());
            String superClass_ = un_.getImportedDirectGenericSuperClass();
            String superClassId_ = StringExpUtil.getIdFromAllTypes(superClass_);
            RootBlock superType_ = _page.getAnaClassBody(superClassId_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            for (String i: allCopy_) {
                RootBlock int_ = _page.getAnaClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (Block b: getDirectChildren(int_)) {
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
            undef_.setIndexFile(0);
            //original id len
            undef_.buildError(_page.getAnalysisMessages().getMustCallIntCtor(),
                    _cl.getFullName());
            _page.addLocError(undef_);
            _cl.addNameErrors(undef_);
        }
    }

    private static void prepareParams(AnalyzedPageEl _page, Ints _offs, CustList<StringList> _paramErrors,StringList _params, StringList _types, boolean _varargs) {
        int len_ = _params.size();
        if (!_varargs) {
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                if (!_paramErrors.get(i).isEmpty()) {
                    continue;
                }
                String p_ = _params.get(i);
                String c_ = _types.get(i);
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(c_);
                lv_.setRef(_offs.get(i));
                lv_.setConstType(ConstType.PARAM);
                _page.getInfosVars().put(p_, lv_);
            }
        } else {
            for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                if (!_paramErrors.get(i).isEmpty()) {
                    continue;
                }
                String p_ = _params.get(i);
                String c_ = _types.get(i);
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(c_);
                lv_.setRef(_offs.get(i));
                lv_.setConstType(ConstType.PARAM);
                _page.getInfosVars().put(p_, lv_);
            }
            if (_paramErrors.last().isEmpty()) {
                String p_ = _params.last();
                String c_ = _types.last();
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(StringExpUtil.getPrettyArrayType(c_));
                lv_.setRef(_offs.last());
                lv_.setConstType(ConstType.PARAM);
                _page.getInfosVars().put(p_, lv_);
            }
        }
    }

    private static void checkConstField(StringList _err, RootBlock _cl, String _clName, String _field, AnalyzedPageEl _page) {
        if (Classes.getStaticFieldMap(_clName, _page.getStaticFields()).getVal(_field) == null) {
            if (!_cl.isStaticType()) {
                //ERROR
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cl.getFile().getFileName());
                un_.setIndexFile(_cl.getOffset().getOffsetTrim());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                        _field,_clName);
                _page.addLocError(un_);
                _err.add(un_.getBuiltError());
            }
        }
    }

    public static void initStaticFields(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getFoundTypes()) {
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            StringMap<Struct> cl_ = new StringMap<Struct>();
            for (Block b: bl_) {
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
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
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
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                _page.setCurrentBlock(f_);
                _page.setCurrentAnaBlock(f_);
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
                StringMap<StringMap<Struct>> staticFields_1 = _page.getStaticFields();
                Struct value_ = Classes.getStaticField(k_, staticFields_1);
                if (value_ != null) {
                    continue;
                }
                ClassFieldBlock cf_ = e.getValue();
                FieldBlock f_ = cf_.getFieldName();
                CustList<OperationNode> ops_ = cf_.getClassName();
                ElUtil.tryCalculate(f_,ops_, k_.getFieldName(), _page);
                StringMap<StringMap<Struct>> staticFields_ = _page.getStaticFields();
                value_ = Classes.getStaticField(k_, staticFields_);
                if (value_ != null) {
                    calculatedValue_ = true;
                    break;
                }
            }
            if (!calculatedValue_) {
                break;
            }
        }
    }


    public static CustList<InfoBlock> getFieldBlocks(RootBlock _element){
        CustList<InfoBlock> methods_ = new CustList<InfoBlock>();
        for (Block b: getDirectChildren(_element)) {
            if (b instanceof InfoBlock) {
                methods_.add((InfoBlock) b);
            }
        }
        return methods_;
    }

    public static CustList<AnnotationMethodBlock> getMethodAnnotationBodiesById(Block _r, String _id) {
        CustList<AnnotationMethodBlock> methods_ = new CustList<AnnotationMethodBlock>();
        for (Block b: getDirectChildren(_r)) {
            if (!(b instanceof AnnotationMethodBlock)) {
                continue;
            }
            AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
            if (StringList.quickEq(a_.getName(), _id)) {
                methods_.add(a_);
            }
        }
        return methods_;
    }

    public static CustList<OverridableBlock> getMethodExecBlocks(RootBlock _element) {
        CustList<OverridableBlock> methods_ = new CustList<OverridableBlock>();
        for (Block b: getDirectChildren(_element)) {
            if (b instanceof OverridableBlock) {
                methods_.add((OverridableBlock) b);
            }
        }
        return methods_;
    }
}
