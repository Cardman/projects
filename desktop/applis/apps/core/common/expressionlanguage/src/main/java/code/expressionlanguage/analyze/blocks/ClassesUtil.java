package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
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
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecAnonymousInstancingOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecFunctionalInfo;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.util.PolymorphMethod;
import code.expressionlanguage.files.FileResolver;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.inherits.OverridesTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.options.ValidatorStandard;
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

    public static void postValidation(ContextEl _context) {
        StringMap<ClassMethodId> toStringMethodsToCall_ = _context.getClasses().getToStringMethodsToCall();
        StringMap<PolymorphMethod> toStringMethodsToCallBodies_ = _context.getClasses().getToStringMethodsToCallBodies();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (EntryCust<RootBlock,ExecRootBlock> e: page_.getMapTypes().entryList()) {
            ClassMethodIdReturn resDyn_ = tryGetDeclaredToString(_context, e.getKey());
            if (resDyn_.isFoundMethod()) {
                String foundClass_ = resDyn_.getRealClass();
                MethodId id_ = resDyn_.getRealId();
                ExecRootBlock ex_ = ExecOperationNode.fetchType(_context,resDyn_.getRootNumber());
                ExecNamedFunctionBlock fct_ = ExecOperationNode.fetchFunction(resDyn_.getRootNumber(),resDyn_.getMemberNumber(),_context);
                ClassMethodId methodId_ = new ClassMethodId(foundClass_, id_);
                toStringMethodsToCall_.addEntry(e.getKey().getFullName(),methodId_);
                toStringMethodsToCallBodies_.addEntry(e.getKey().getFullName(),new PolymorphMethod(ex_,fct_));
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: page_.getMapTypes().entryList()) {
            RootBlock c = e.getKey();
            page_.setGlobalClass(c.getGenericString());
            page_.setGlobalType(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
            page_.getMappingLocal().clear();
            page_.getMappingLocal().putAllMap(c.getMappings());
            for (Block b: getDirectChildren(c)) {
                if (b instanceof OverridableBlock) {
                    page_.setCurrentAnaBlock(c);
                    page_.setCurrentBlock(c);
                    ((OverridableBlock)b).buildTypes(c,_context);
                }
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: page_.getMapTypes().entryList()) {
            RootBlock root_ = e.getKey();
            IdMap<OverridableBlock, ExecOverridableBlock> allMethods_ = page_.getMapMembers().getValue(root_.getNumberAll()).getAllMethods();
            ClassMethodIdOverrides redirections_ = e.getValue().getRedirections();
            String fullName_ = root_.getFullName();
            for (EntryCust<OverridableBlock,ExecOverridableBlock> f: allMethods_.entryList()) {
                OverridableBlock key_ = f.getKey();
                if (key_.hiddenInstance()) {
                    continue;
                }
                if (key_.isFinalMethod()) {
                    continue;
                }
                MethodId id_ = key_.getId();
                ClassMethodIdOverride override_ = new ClassMethodIdOverride(ExecOperationNode.fetchFunction(root_.getNumberAll(),key_.getNameNumber(),_context));
                StringMap<ClassMethodId> map_ = OverridesTypeUtil.getConcreteMethodsToCall(root_, id_, _context);
                map_.putAllMap(key_.getOverrides());
                for (EntryCust<String,ClassMethodId> g: map_.entryList()) {
                    override_.put(_context,g.getKey(),g.getValue());
                }
                redirections_.add(override_);
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: page_.getMapTypes().entryList()) {
            RootBlock root_ = e.getKey();
            if (root_.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = root_.getAllGenericSuperTypesInfo();
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    String base_ = StringExpUtil.getIdFromAllTypes(s.getFormatted());
                    RootBlock superBl_ = s.getRootBlock();
                    for (OverridableBlock m: ClassesUtil.getMethodExecBlocks(superBl_)) {
                        if (m.isAbstractMethod()) {
                            ExecRootBlock ex_ = page_.getMapTypes().getValue(superBl_.getNumberAll());
                            ExecOverrideInfo val_ = ex_.getRedirections().getVal(ExecOperationNode.fetchFunction(superBl_.getNumberAll(),m.getNameNumber(),_context), root_.getFullName());
                            if (val_ == null) {
                                FoundErrorInterpret err_;
                                err_ = new FoundErrorInterpret();
                                err_.setFileName(root_.getFile().getFileName());
                                err_.setIndexFile(root_.getIdRowCol());
                                //type id
                                err_.buildError(
                                        _context.getAnalysisMessages().getAbstractMethodImpl(),
                                        base_,
                                        m.getSignature(_context),
                                        root_.getFullName());
                                _context.addError(err_);
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
                            ExecRootBlock ex_ = page_.getMapTypes().getValue(superBl_.getNumberAll());
                            ExecOverrideInfo val_ = ex_.getRedirections().getVal(ExecOperationNode.fetchFunction(superBl_.getNumberAll(),m.getNameNumber(),_context), root_.getFullName());
                            if (val_ == null) {
                                root_.getFunctional().add(new ClassMethodId(s.getFormatted(),m.getId()));
                            }
                        }
                    }
                }
                for (ClassMethodId c: root_.getFunctional()) {
                    CustList<ExecOverridableBlock> list_ = ExecBlock.getDeepMethodBodiesById(_context, StringExpUtil.getIdFromAllTypes(c.getClassName()), c.getConstraints());
                    for (ExecOverridableBlock d: list_) {
                        e.getValue().getFunctionalBodies().add(new ExecFunctionalInfo(c.getClassName(),d));
                    }
                }
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: page_.getMapTypes().entryList()) {
            RootBlock root_ = e.getKey();
            Members valueMember_ = page_.getMapMembers().getValue(root_.getNumberAll());
            IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> allFct_ = valueMember_.getAllFct();
            IdMap<InfoBlock, ExecInfoBlock> allFields_ = valueMember_.getAllFields();
            for (Block b: ClassesUtil.getDirectChildren(root_)) {
                if (b instanceof MemberCallingsBlock) {
                    ExecMemberCallingsBlock value_ = allFct_.getValue(((MemberCallingsBlock) b).getNumberFct());
                    for (AnonymousTypeBlock a: ((MemberCallingsBlock)b).getAnonymous()) {
                        value_.getAnonymous().add(page_.getMapTypes().getValue(a.getNumberAll()));
                    }
                    for (RootBlock a: ((MemberCallingsBlock)b).getReserved()) {
                        value_.getReserved().add(page_.getMapTypes().getValue(a.getNumberAll()));
                    }
                }
                if (b instanceof InfoBlock) {
                    ExecInfoBlock value_ = allFields_.getValue(((InfoBlock)b).getFieldNumber());
                    for (AnonymousTypeBlock a: ((InfoBlock)b).getAnonymous()) {
                        value_.getAnonymous().add(page_.getMapTypes().getValue(a.getNumberAll()));
                    }
                }
            }
        }
    }

    private static ClassMethodIdReturn tryGetDeclaredToString(ContextEl _conf, RootBlock _class) {
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        String baseCurName_ = _class.getFullName();
        fetchToStringMethods(_conf,_class,baseCurName_,methods_);
        return getCustResultExec(_conf, methods_);
    }

    private static ClassMethodIdReturn getCustResultExec(ContextEl _conf,
                                                         CustList<MethodInfo> _methods) {
        Parametrable found_ = getFoundMethodExec(_methods, _conf);
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

    private static Parametrable getFoundMethodExec(CustList<MethodInfo> _fct, ContextEl _context) {
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
            if (_context.getAnalyzing().getAnaClassBody(type_) instanceof InterfaceBlock) {
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
        sub_ = AnaTypeUtil.getSubclasses(sub_,_context);
        if (!sub_.onlyOneElt()) {
            return null;
        }
        return meths_.getVal(sub_.first());
    }

    private static void fetchToStringMethods(ContextEl _conf, RootBlock _root, String _cl, CustList<MethodInfo> _methods) {
        StringList geneSuperTypes_ = new StringList();
        geneSuperTypes_.add(_cl);
        geneSuperTypes_.addAllElts(_root.getAllSuperTypes());
        for (String t: geneSuperTypes_) {
            ToStringMethodHeader toString_ = _conf.getAnalyzing().getToStringMethods().getVal(t);
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
    public static void buildAllBracesBodies(StringMap<String> _files, ContextEl _context) {
        StringMap<ExecFileBlock> outExec_ = new StringMap<ExecFileBlock>();
        tryBuildAllBracedClassesBodies(_files, _context, outExec_);
        validateInheritingClasses(_context);
        validateIds(_context);
        validateOverridingInherit(_context);
        validateEl(_context);
        AnaTypeUtil.checkInterfaces(_context);
        StringList basePkgFound_ = _context.getAnalyzing().getBasePackagesFound();
        StringList pkgFound_ = _context.getAnalyzing().getPackagesFound();
        while (!_context.getAnalyzing().getMapAnonymous().isEmpty()) {
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: _context.getAnalyzing().getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousTypeBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
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
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: _context.getAnalyzing().getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousTypeBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    if (parentType_ == null) {
                        continue;
                    }
                    String base_ = e.getKey().getBase();
                    String enumClassName_ = _context.getStandards().getAliasEnumType();
                    String enumParamClassName_ = _context.getStandards().getAliasEnumParam();
                    if (StringList.quickEq(enumParamClassName_, base_)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(block_.getFile().getFileName());
                        undef_.setIndexFile(e.getKey().getIndex());
                        //original type len
                        undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                block_.getFullName(),
                                base_);
                        _context.addError(undef_);
                        block_.addNameErrors(undef_);
                    }
                    if (StringList.quickEq(enumClassName_, base_)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(block_.getFile().getFileName());
                        undef_.setIndexFile(e.getKey().getIndex());
                        //original type len
                        undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                block_.getFullName(),
                                base_);
                        _context.addError(undef_);
                        block_.addNameErrors(undef_);
                    }
                }
            }
            _context.getAnalyzing().getPrevFoundTypes().addAllElts(_context.getAnalyzing().getFoundTypes());
            _context.getAnalyzing().getFoundTypes().clear();
            _context.getAnalyzing().getFoundOperators().clear();
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: _context.getAnalyzing().getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousTypeBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    if (parentType_ == null) {
                        continue;
                    }
                    int numberFile_ = block_.getFile().getNumberFile();
                    processType(_context,basePkgFound_,pkgFound_,outExec_.getValue(numberFile_), block_);
                }
            }
            for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
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
            for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
                MemberCallingsBlock outerFuntion_ = r.getStrictOuterFuntion();
                if (outerFuntion_ != null) {
                    r.getMappings().putAllMap(outerFuntion_.getMappings());
                }
            }
            innerFetchExec(_context);
            validateInheritingClasses(_context);
            validateIds(_context);
            validateOverridingInherit(_context);
            for (IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> m: _context.getAnalyzing().getMapAnonymous()) {
                for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: m.entryList()) {
                    AnonymousTypeBlock block_ = e.getKey().getBlock();
                    RootBlock parentType_ = block_.getParentType();
                    if (parentType_ == null) {
                        continue;
                    }
                    _context.getAnalyzing().setGlobalClass(e.getKey().getGlClass());
                    e.getKey().postAnalyze(_context);
                    e.getValue().setExecAnonymousInstancingOperation(e.getKey(),_context);
                }
            }
            _context.getAnalyzing().getMapAnonymous().clear();
            validateEl(_context);
            AnaTypeUtil.checkInterfaces(_context);
        }

    }

    public static void tryBuildAllBracedClassesBodies(StringMap<String> _files, ContextEl _context, StringMap<ExecFileBlock> _outExec) {
        LgNames stds_ = _context.getStandards();
        StringMap<String> files_ = stds_.buildFiles(_context);
        StringMap<FileBlock> out_ = new StringMap<FileBlock>();
        buildFilesBodies(_context,files_,true,out_, _outExec);
        buildFilesBodies(_context,_files,false,out_, _outExec);
        parseFiles(_context,out_, _outExec);
    }

    public static void processBracedClass(boolean _add,ExecFileBlock _exFile, RootBlock _outer, RootBlock _root, ContextEl _context) {
        String fullName_ = _root.getFullName();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        boolean ok_ = _add;
        if (_context.getClasses().getClassesBodies().contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedType(),
                    fullName_);
            _context.addError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        page_.setCurrentBlock(_root);
        page_.setCurrentAnaBlock(_root);
        String packageName_;
        packageName_ = _root.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (packageName_.trim().isEmpty()) {
            FoundErrorInterpret badCl_ = new FoundErrorInterpret();
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getBegin());
            //key word category len
            badCl_.buildError(_context.getAnalysisMessages().getEmptyPackage());
            _context.addError(badCl_);
            _root.addNameErrors(badCl_);
            ok_ = false;
        } else {
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                String tr_ = e.trim();
                TokenErrorMessage res_ = ManageTokens.partClass(_context).checkToken(_context, tr_);
                if (res_.isError()) {
                    FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setIndexFile(_root.getIdRowCol());
                    //pkg part or dot
                    badCl_.setBuiltError(res_.getMessage());
                    _context.addError(badCl_);
                    _root.addNameErrors(badCl_);
                    ok_ = false;
                }
            }
        }
        String className_;
        className_ = _root.getName().trim();
        TokenErrorMessage resClName_ = ManageTokens.partClass(_context).checkToken(_context, className_);
        if (resClName_.isError()) {
            FoundErrorInterpret badCl_ = new FoundErrorInterpret();
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getIdRowCol());
            //name part if possible or original type
            badCl_.setBuiltError(resClName_.getMessage());
            _context.addError(badCl_);
            _root.addNameErrors(badCl_);
            ok_ = false;
        }
        String fullDef_ = _root.getFullDefinition();
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        StringList params_ = StringExpUtil.getAllTypes(fullDef_);
        StringList namesFromParent_ = getParamVarFromParent(_root);
        int tempOff_ = _root.getTemplateDefOffset() + 1;
        for (String p: params_.mid(CustList.SECOND_INDEX)) {
            int delta_ = 0;
            String name_;
            if (p.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
                delta_++;
                name_ = p.substring(AnaTemplates.PREFIX_VAR_TYPE.length());
            } else {
                name_ = p;
            }
            TypeVar type_ = new TypeVar();
            int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(name_, indexDef_);
            String id_ = parts_.first();
            int offId_ = tempOff_ + StringList.getFirstPrintableCharIndex(p);
            if (id_.isEmpty()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //char after def
                badCl_.buildError(_context.getAnalysisMessages().getEmptyPartClassName());
                _context.addError(badCl_);
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
            TokenErrorMessage res_ = ManageTokens.partVarClass(_context).checkToken(_context, id_);
            if (res_.isError()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.setBuiltError(res_.getMessage());
                _context.addError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringList.contains(varTypes_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.buildError(_context.getAnalysisMessages().getDuplicatedPartVarClassName(),
                        id_
                );
                _context.addError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringList.contains(namesFromParent_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.buildError(_context.getAnalysisMessages().getDuplicatedPartVarClassName(),
                        id_
                );
                _context.addError(badCl_);
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
            StringBuilder sBuild_ = new StringBuilder(_context.getStandards().getAliasEnumParam());
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
            String type_ = _context.getStandards().getAliasAnnotationType();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (lgNames_.getStandards().contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedTypeStd(),
                    fullName_);
            _context.addError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        if (PrimitiveTypeUtil.isPrimitive(fullName_, _context)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedTypePrim(),
                    fullName_);
            _context.addError(d_);
            _root.addNameErrors(d_);
            ok_ = false;
        }
        page_.getFoundTypes().add(_root);
        if (ok_) {
            page_.getRefFoundTypes().add(_root);
            _context.getCoverage().putType(_context,_root);
        }
        RootBlock parentType_ = _root.getParentType();
        int index_ = -1;
        if (parentType_ != null) {
            index_ = parentType_.getNumberAll();
            _root.setFile(parentType_.getFile());
        }
        ExecRootBlock execParentType_ = null;
        if (page_.getMapTypes().getKeys().isValidIndex(index_)) {
            execParentType_ = page_.getMapTypes().getValue(index_);
        }
        if (_root instanceof AnonymousTypeBlock) {
            ExecAnonymousTypeBlock e_ = new ExecAnonymousTypeBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(page_.getMapTypes().size());
            page_.getMapTypes().put(_root, e_);
            _context.getClasses().getClassesBodies().put(fullName_, e_);
        }
        if (_root instanceof ClassBlock) {
            ExecClassBlock e_ = new ExecClassBlock((ClassBlock) _root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(page_.getMapTypes().size());
            page_.getMapTypes().put(_root, e_);
            _context.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof EnumBlock) {
            ExecEnumBlock e_ = new ExecEnumBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(page_.getMapTypes().size());
            page_.getMapTypes().put(_root, e_);
            _context.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InterfaceBlock) {
            ExecInterfaceBlock e_ = new ExecInterfaceBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(page_.getMapTypes().size());
            page_.getMapTypes().put(_root, e_);
            _context.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof AnnotationBlock) {
            ExecAnnotationBlock e_ = new ExecAnnotationBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(page_.getMapTypes().size());
            page_.getMapTypes().put(_root, e_);
            _context.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InnerElementBlock) {
            ExecInnerElementBlock e_ = new ExecInnerElementBlock((InnerElementBlock) _root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            _root.setNumberAll(page_.getMapTypes().size());
            page_.getMapTypes().put(_root, e_);
            ((InnerElementBlock) _root).setNumberInner(page_.getMapInnerEltTypes().size());
            page_.getMapInnerEltTypes().put((InnerElementBlock) _root, e_);
            _context.getClasses().getClassesBodies().put(fullName_, e_);
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

    private static void appendType(ExecFileBlock _exFile, RootBlock _outer, RootBlock _root, ExecRootBlock e_) {
        if (_outer == _root) {
            _exFile.appendChild(e_);
        }
    }

    private static void innerFetchExec(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        IdMap<RootBlock, ExecRootBlock> mapTypes_ = page_.getMapTypes();
        for (RootBlock r: page_.getFoundTypes()) {
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
                    ExecInnerElementBlock val_ = page_.getMapInnerEltTypes().getValue(((InnerElementBlock) b).getNumberInner());
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
            page_.getMapMembers().addEntry(k_, mem_);
        }
    }

    public static void buildFilesBodies(ContextEl _context, StringMap<String> _files, boolean _predefined, StringMap<FileBlock> _out, StringMap<ExecFileBlock> _outExec) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileBlock fileBlock_ = new FileBlock(new OffsetsBlock(),_predefined);
            fileBlock_.setFileName(file_);
            _context.getAnalyzing().putFileBlock(file_, fileBlock_);
            _context.getCoverage().putFile(_context,fileBlock_);
            _context.getAnalyzing().getErrors().putFile(_context,fileBlock_);
            fileBlock_.processLinesTabsWithError(_context,content_);
            fileBlock_.setNumberFile(_out.size());
            _out.addEntry(file_,fileBlock_);
            ExecFileBlock exFile_ = new ExecFileBlock(fileBlock_);
            _outExec.addEntry(file_,exFile_);
        }
    }
    public static void parseFiles(ContextEl _context, StringMap<FileBlock> _files, StringMap<ExecFileBlock> _outExec) {
        for (EntryCust<String,FileBlock> f: _files.entryList()) {
            String fileName_ = f.getKey();
            FileBlock block_ = f.getValue();
            if (!block_.getBinChars().isEmpty()) {
                continue;
            }
            String file_ = block_.getContent();
            FileResolver.parseFile(block_, fileName_,file_, _context);
        }
        StringList basePkgFound_ = _context.getAnalyzing().getBasePackagesFound();
        StringList pkgFound_ = _context.getAnalyzing().getPackagesFound();
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
            fetchByFile(_context, basePkgFound_, pkgFound_, value_, exFile_);
            i_++;
        }
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
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
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
            MemberCallingsBlock outerFuntion_ = r.getStrictOuterFuntion();
            if (outerFuntion_ != null) {
                r.getMappings().putAllMap(outerFuntion_.getMappings());
            }
        }
        innerFetchExec(_context);
    }

    public static void fetchByFile(ContextEl _context, StringList _basePkgFound, StringList _pkgFound, FileBlock _anaFile, ExecFileBlock _exeFile) {
        for (Block b: getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processType(_context, _basePkgFound, _pkgFound, _exeFile, r_);
            }
            if (b instanceof OperatorBlock) {
                OperatorBlock r_ = (OperatorBlock) b;
                ExecOperatorBlock e_ = new ExecOperatorBlock(r_);
                _exeFile.appendChild(e_);
                e_.setFile(_exeFile);
                _context.getClasses().getOperators().add(e_);
                _context.getAnalyzing().getFoundOperators().add(r_);
                r_.setNameNumber(_context.getAnalyzing().getMapOperators().size());
                _context.getAnalyzing().getMapOperators().put(r_,e_);
                _context.getCoverage().putOperator(_context,r_);
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
                            d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                    s_);
                            _context.addError(d_);
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

    private static void processType(ContextEl _context, StringList _basePkgFound, StringList _pkgFound, ExecFileBlock _exeFile, RootBlock _r) {
        StringList allReservedInnersRoot_ = new StringList(_r.getAllReservedInners());
        boolean addPkg_ = true;
        if (_r.getNameLength() != 0) {
            String fullName_ = _r.getFullName();
            for (String p: _pkgFound) {
                if (!p.startsWith(fullName_)) {
                    continue;
                }
                //ERROR
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_r.getFile().getFileName());
                d_.setIndexFile(_r.getIdRowCol());
                //original id len
                d_.buildError(_context.getAnalysisMessages().getDuplicatedTypePkg(),
                        fullName_,
                        p);
                _context.addError(d_);
                _r.addNameErrors(d_);
                addPkg_ = false;
            }
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
                            d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                    s_);
                            _context.addError(d_);
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
                                d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                        s_);
                                _context.addError(d_);
                                cur_.addNameErrors(d_);
                                add_ = false;
                            }
                            StringList namesFromParent_ = getParamVarFromAnyParent(cur_);
                            if (StringList.contains(namesFromParent_, s_)) {
                                FoundErrorInterpret d_ = new FoundErrorInterpret();
                                d_.setFileName(_r.getFile().getFileName());
                                d_.setIndexFile(cur_.getIdRowCol());
                                //s_ len
                                d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                        s_);
                                _context.addError(d_);
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
                                d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                        s_);
                                _context.addError(d_);
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
                        d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                s_);
                        _context.addError(d_);
                        cur_.addNameErrors(d_);
                        add_ = false;
                        addPkg_ = false;
                    } else if (StringList.contains(simpleNames_, s_)) {
                        //ERROR
                        FoundErrorInterpret d_ = new FoundErrorInterpret();
                        d_.setFileName(_r.getFile().getFileName());
                        d_.setIndexFile(cur_.getIdRowCol());
                        //s_ len
                        d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                s_);
                        _context.addError(d_);
                        cur_.addNameErrors(d_);
                        add_ = false;
                    }
                    ClassesUtil.processBracedClass(addPkg_&&add_, _exeFile, _r,cur_, _context);
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
            ClassesUtil.processBracedClass(addPkg_, _exeFile, _r, _r, _context);
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

    public static void validateInheritingClasses(ContextEl _context) {
        String objectClassName_ = _context.getStandards().getAliasObject();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        page_.getListTypesNames().clear();
        validateInheritingClassesId(_context);
        CustList<RootBlock> listTypes_ = page_.getListTypesNames();
        for (RootBlock s: listTypes_) {
            page_.setCurrentBlock(s);
            page_.setCurrentAnaBlock(s);
            s.buildDirectGenericSuperTypes(_context);
        }
        for (RootBlock c: page_.getFoundTypes()) {
            page_.setCurrentBlock(c);
            page_.setCurrentAnaBlock(c);
            c.buildMapParamType(_context);
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
                RootBlock s_ = _context.getAnalyzing().getAnaClassBody(StringExpUtil.getIdFromAllTypes(s.getResult()));
                if (s_ == null) {
                    continue;
                }
                if (s_.isStaticType()) {
                    continue;
                }
                allDirectSuperTypes_.add(s_.getFullName());
            }
            for (RootBlock a: allAncestors_) {
                for (Block m: getDirectChildren(a)) {
                    if (!(m instanceof RootBlock)) {
                        continue;
                    }
                    RootBlock m_ = (RootBlock) m;
                    allPossibleDirectSuperTypes_.add(m_.getFullName());
                }
                for (String s: a.getAllSuperTypes()) {
                    RootBlock g_ = _context.getAnalyzing().getAnaClassBody(s);
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
                    enum_.buildError(_context.getAnalysisMessages().getBadInheritsType(),
                            c.getFullName(),
                            s);
                    _context.addError(enum_);
                    c.addNameErrors(enum_);
                }
            }
        }
        validateSingleParameterizedClasses(_context);
        checkTemplatesDef(_context, objectClassName_);
    }

    public static void validateInheritingClassesId(ContextEl _context) {
        String objectClassName_ = _context.getStandards().getAliasObject();
        String enumClassName_ = _context.getStandards().getAliasEnumType();
        String enumParamClassName_ = _context.getStandards().getAliasEnumParam();
        String annotName_ = _context.getStandards().getAliasAnnotationType();
        StringMap<Boolean> builtTypes_ = new StringMap<Boolean>();
        IdList<RootBlock> stClNames_ = new IdList<RootBlock>(_context.getAnalyzing().getFoundTypes());
        for (RootBlock r: stClNames_) {
            builtTypes_.addEntry(r.getFullName(), false);
        }
        for (RootBlock r: _context.getAnalyzing().getPrevFoundTypes()) {
            builtTypes_.addEntry(r.getFullName(), true);
        }
        while (true) {
            IdList<RootBlock> next_ = new IdList<RootBlock>();
            for (RootBlock r: stClNames_) {
                ExecRootBlock exec_ = _context.getAnalyzing().getMapTypes().getVal(r);
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
                            undef_.buildError(_context.getAnalysisMessages().getBadInheritsType(),
                                    c,
                                    idSuper_);
                            _context.addError(undef_);
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
                    _context.getAnalyzing().getListTypesNames().add(r);
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
                    String void_ = _context.getStandards().getAliasVoid();
                    if (StringList.quickEq(idSuper_, void_)) {
                        FoundErrorInterpret undef_ = new FoundErrorInterpret();
                        undef_.setFileName(r.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        //_in len
                        undef_.buildError(_context.getAnalysisMessages().getVoidType(),
                                void_);
                        _context.addError(undef_);
                        r.addNameErrors(undef_);
                        index_++;
                        continue;
                    }
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        if (_context.getStandards().getStandards().contains(idSuper_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //idSuper_ len
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    idSuper_);
                            _context.addError(undef_);
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
                        foundType_ = ResolvingSuperTypes.resolveBaseInherits(_context,idSuper_, r, readyTypes_);
                    } else {
                        InheritReadyTypes inh_ = new InheritReadyTypes(readyTypes_);
                        if (inh_.isReady(idSuper_)) {
                            foundType_ = idSuper_;
                        } else {
                            foundType_ = "";
                        }
                    }
                    RootBlock superType_ = _context.getAnalyzing().getAnaClassBody(foundType_);
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
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _context.addError(undef_);
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
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _context.addError(undef_);
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
                        undef_.buildError(_context.getAnalysisMessages().getDuplicateSuper(),
                                c,e.getKey(),Integer.toString(e.getValue()));
                        _context.addError(undef_);
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
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeInn(),
                                    c,
                                    k_);
                            _context.addError(enum_);
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
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeAsInn(),
                                    c,
                                    k_,
                                    Integer.toString(subSise_-1),
                                    Integer.toString(supSise_-1));
                            _context.addError(enum_);
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
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeInt(),
                                    c,k_);
                            _context.addError(enum_);
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
                        enum_.buildError(_context.getAnalysisMessages().getFinalType(),
                                c,k_);
                        _context.addError(enum_);
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
                    enum_.buildError(_context.getAnalysisMessages().getSuperClass(),
                            c,Integer.toString(nbDirectSuperClass_));
                    _context.addError(enum_);
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
                _context.getAnalyzing().getListTypesNames().add(r);
                builtTypes_.set(c, true);
                next_.add(r);
            }
            if (next_.isEmpty()) {
                for (RootBlock r: stClNames_) {
                    _context.getAnalyzing().getListTypesNames().add(r);
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(r.getFile().getFileName());
                    undef_.setIndexFile(0);
                    //id len
                    undef_.buildError(_context.getAnalysisMessages().getUnknownSuperType(),
                            r.getFullName());
                    _context.addError(undef_);
                    r.addNameErrors(undef_);
                }
                break;
            }
            stClNames_.removeAllElements(next_);
        }
    }

    private static void checkTemplatesDef(ContextEl _context,
                                          String _objectClassName) {
        LgNames stds_ = _context.getStandards();
        for (RootBlock s: _context.getAnalyzing().getFoundTypes()) {
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
                b_.buildError(_context.getAnalysisMessages().getAnnotationParam(),
                        c);
                _context.addError(b_);
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
                b_.buildError(_context.getAnalysisMessages().getCyclicMapping(),
                        c);
                _context.addError(b_);
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
                        un_.buildError(_context.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _context.addError(un_);
                        s.addNameErrors(un_);
                    }
                    if (PrimitiveTypeUtil.isPrimitive(b,_context)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _context.addError(un_);
                        s.addNameErrors(un_);
                    }
                    String baseParams_ = StringExpUtil.getIdFromAllTypes(b);
                    String base_ = StringExpUtil.getQuickComponentBaseType(baseParams_).getComponent();
                    upperNotObj_.add(b);
                    if (_context.getAnalyzing().getAnaClassBody(base_) != null) {
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
                    un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                            c);
                    _context.addError(un_);
                    s.addNameErrors(un_);
                    okLoc_ = false;
                    ok_ = false;
                }
                for (CustList<TypeInfo> g: OperationNode.typeLists(_context,upper_,MethodAccessKind.INSTANCE)) {
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
                            duplicate_.buildError(_context.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                                    StringList.join(e.getValue(),"&"));
                            _context.addError(duplicate_);
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
                            RootBlock r_ = _context.getAnalyzing().getAnaClassBody(base_);
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
                            inh_.buildError(_context.getAnalysisMessages().getAbsMapping(),
                                    Integer.toString(nbAbs_));
                            _context.addError(inh_);
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
                            inh_.buildError(_context.getAnalysisMessages().getFinalMapping(),
                                    Integer.toString(nbFinal_));
                            _context.addError(inh_);
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
                    if (!AnaPartTypeUtil.processAnalyzeConstraints(b, map_, _context, true)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                                b.getResult());
                        _context.addError(un_);
                        s.addNameErrors(un_);
                    }
                }
            }
            for (AnaResultPartType t: s.getResults()) {
                if (!AnaPartTypeUtil.processAnalyzeConstraints(t, map_, _context, true)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    // char : before super type
                    un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                            t.getResult());
                    _context.addError(un_);
                    s.addNameErrors(un_);
                }
            }
        }
    }

    private static void validateSingleParameterizedClasses(ContextEl _context) {
        for (RootBlock i: _context.getAnalyzing().getFoundTypes()) {
            CustList<AnaFormattedRootBlock> genericSuperTypes_ = i.getAllGenericSuperTypes(_context);
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
                    duplicate_.buildError(_context.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                            StringList.join(e.getValue(),"&"));
                    _context.addError(duplicate_);
                    i.addNameErrors(duplicate_);
                }
            }
            i.getAllGenericSuperTypesInfo().addAllElts(genericSuperTypes_);
            CustList<AnaFormattedRootBlock> genericClasses_ = i.getAllGenericClasses(_context);
            for (AnaFormattedRootBlock a: genericClasses_) {
                i.getAllGenericClasses().add(a.getFormatted());
            }
            i.getAllGenericClassesInfo().addAllElts(genericClasses_);
        }
        for (RootBlock i: _context.getAnalyzing().getFoundTypes()) {
            CustList<AnaFormattedRootBlock> allGenericSuperTypes_ = i.getAllGenericSuperTypesInfo();
            CustList<ExecFormattedRootBlock> l_ = new CustList<ExecFormattedRootBlock>();
            for (AnaFormattedRootBlock s: allGenericSuperTypes_) {
                l_.add(new ExecFormattedRootBlock(_context.getAnalyzing().getMapTypes().getValue(s.getRootBlock().getNumberAll()),s.getFormatted()));
            }
            _context.getAnalyzing().getMapTypes().getValue(i.getNumberAll()).getAllGenericSuperTypes().addAllElts(l_);
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

    public static void validateIds(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
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
            c.validateIds(_context, type_,page_.getMapMembers().getVal(c));
            if (c.getNbOperators() > 0) {
                _context.getAnalyzing().getTypesWithInnerOperators().add(c.getFullName());
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
                    ((InternOverrideBlock)b).buildTypes(c,_context);
                }
            }
        }
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        page_.setGlobalClass("");
        page_.setGlobalType(null);
        for (OperatorBlock o: _context.getAnalyzing().getFoundOperators()) {
            ExecOperatorBlock value_ = _context.getAnalyzing().getMapOperators().getValue(o.getNameNumber());
            String name_ = o.getName();
            page_.setImporting(o);
            page_.setImportingAcces(new OperatorAccessor());
            page_.setImportingTypes(o);
            page_.getMappingLocal().clear();
            o.buildImportedTypes(_context);
            value_.buildImportedTypes(o);
            if (!StringExpUtil.isOper(name_)) {
                FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                badMeth_.setFileName(_context.getCurrentFileName());
                badMeth_.setIndexFile(o.getNameOffset());
                //key word len
                badMeth_.buildError(_context.getAnalysisMessages().getBadOperatorName(),
                        name_);
                _context.addError(badMeth_);
                o.addNameErrors(badMeth_);
            }
            MethodId id_ = o.getId();
            for (MethodId m: idMethods_) {
                if (m.eq(id_)) {
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setIndexFile(o.getOffset().getOffsetTrim());
                    duplicate_.setFileName(_context.getCurrentFileName());
                    //key word len
                    duplicate_.buildError(_context.getAnalysisMessages().getDuplicateOperator(),
                            id_.getSignature(_context));
                    _context.addError(duplicate_);
                    o.addNameErrors(duplicate_);
                }
            }
            idMethods_.add(id_);
            StringList l_ = o.getParametersNames();
            StringList seen_ = new StringList();
            int i_ = 0;
            for (String v: l_) {
                o.addParamErrors();
                TokenErrorMessage res_ = ManageTokens.partParam(_context).checkToken(_context, v);
                if (res_.isError()) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
                    //param name len
                    b_.setBuiltError(res_.getMessage());
                    _context.addError(b_);
                    o.addParamErrors(i_,b_);
                }
                if (StringList.contains(seen_, v)){
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
                    //param name len
                    b_.buildError(_context.getAnalysisMessages().getDuplicatedParamName(),
                            v);
                    _context.addError(b_);
                    o.addParamErrors(i_,b_);
                } else {
                    seen_.add(v);
                }
                i_++;
            }
        }
    }

    public static void validateOverridingInherit(ContextEl _context) {
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.setupBasicOverrides(_context);
        }
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.checkCompatibility(_context);
            c.checkImplements(_context);
        }
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.checkCompatibilityBounds(_context);
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
            for (StandardMethod m : ((StandardType) _element).getMethods().values()) {
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
    public static void validateEl(ContextEl _context) {
        initStaticFields(_context);
        AnalyzedPageEl page_ = _context.getAnalyzing();
        CustList<BracedBlock> brBl_ = new CustList<BracedBlock>();
        for (OperatorBlock c: _context.getAnalyzing().getFoundOperators()) {
            brBl_.add(c);
        }
        for (RootBlock c: page_.getFoundTypes()) {
            brBl_.add(c);
        }
        for (BracedBlock c: brBl_) {
            for (int i: c.getBadIndexes()) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(c.getFile().getFileName());
                b_.setIndexFile(Math.max(0,Math.min(c.getFile().getLength()-1,i)));
                //underline index char
                b_.buildError(_context.getAnalysisMessages().getBadIndexInParser());
                _context.addError(b_);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(b_);
                g_.setLength(1);
                c.getGlobalErrorsPars().add(g_);
            }
        }
        if (!_context.getOptions().isReadOnly()) {
            validateFinals(_context);
        } else {
            for (RootBlock c: page_.getFoundTypes()) {
                Members mem_ = page_.getMapMembers().getVal(c);
                page_.setImporting(c);
                page_.setImportingAcces(new TypeAccessor(c.getFullName()));
                page_.setImportingTypes(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (!(b instanceof InfoBlock)) {
                        continue;
                    }
                    InfoBlock f_ = (InfoBlock) b;
                    page_.getAllDeclaredFields().addAllElts(f_.getFieldName());
                    if (!f_.isStaticField()) {
                        continue;
                    }
                    if (f_ instanceof FieldBlock) {
                        page_.getAssignedDeclaredFields().addAllElts(((FieldBlock)f_).getAssignedDeclaredFields());
                    }
                    if (f_ instanceof InnerTypeOrElement) {
                        page_.getAssignedDeclaredFields().addAllElts(f_.getFieldName());
                    }
                    int v_ = 0;
                    for (String f: f_.getFieldName()) {
                        StringList err_ = getErFields(f_, v_);
                        checkConstField(_context, err_,c, fullName_, f);
                        v_++;
                    }
                }
                for (Block b: bl_) {
                    if (b instanceof InnerTypeOrElement) {
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        page_.setCurrentFct(null);
                        InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                        page_.setCurrentBlock(b);
                        page_.setCurrentAnaBlock(b);
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(c.getMappings());
                        method_.buildExpressionLanguageReadOnly(_context,mem_.getAllElementFields().getVal(method_));
                    }
                    if (b instanceof FieldBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        FieldBlock method_ = (FieldBlock) b;
                        if (!method_.isStaticField()) {
                            continue;
                        }
                        page_.setCurrentBlock(b);
                        page_.setCurrentAnaBlock(b);
                        page_.setCurrentFct(null);
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(c.getMappings());
                        method_.buildExpressionLanguageReadOnly(_context,mem_.getAllExplicitFields().getVal(method_));
                    }
                    if (b instanceof StaticBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        StaticBlock method_ = (StaticBlock) b;
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(method_.getMappings());
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllInits().getVal(method_));
                    }
                }

            }
            _context.setAssignedStaticFields(true);
            for (RootBlock c: page_.getFoundTypes()) {
                page_.setImporting(c);
                page_.setImportingAcces(new TypeAccessor(c.getFullName()));
                page_.setImportingTypes(c);
                Members mem_ = page_.getMapMembers().getVal(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                _context.getCoverage().putCalls(_context,fullName_);
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        page_.getAllDeclaredFields().addAllElts(method_.getFieldName());
                        if (method_.isStaticField()) {
                            page_.getAssignedDeclaredFields().addAllElts(method_.getFieldName());
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        page_.getAssignedDeclaredFields().addAllElts(((FieldBlock)b).getAssignedDeclaredFields());
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
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        page_.setCurrentFct(null);
                        FieldBlock method_ = (FieldBlock) b;
                        page_.setCurrentBlock(b);
                        page_.setCurrentAnaBlock(b);
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(c.getMappings());
                        method_.buildExpressionLanguageReadOnly(_context,mem_.getAllExplicitFields().getVal(method_));
                    }
                    if (b instanceof InstanceBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        InstanceBlock method_ = (InstanceBlock) b;
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(method_.getMappings());
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllInits().getVal(method_));
                    }
                }
                processInterfaceCtor(_context, c, fullName_, bl_);
                for (Block b: bl_) {
                    if (b instanceof ConstructorBlock) {
                        page_.getInitFieldsCtors().clear();
                        page_.getInitFieldsCtors().addAllElts(page_.getInitFields());
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        ConstructorBlock method_ = (ConstructorBlock) b;
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(method_.getMappings());
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllCtors().getVal(method_));
                    }
                }
            }
            _context.setAssignedFields(true);
            for (RootBlock c: page_.getFoundTypes()) {
                page_.setImporting(c);
                page_.setImportingAcces(new TypeAccessor(c.getFullName()));
                page_.setImportingTypes(c);
                Members mem_ = page_.getMapMembers().getVal(c);
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (!(b instanceof OverridableBlock)) {
                        continue;
                    }
                    OverridableBlock method_ = (OverridableBlock) b;
                    if (isStdOrExplicit(method_)) {
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(method_.getMappings());
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllMethods().getVal(method_));
                    } else {
                        page_.setGlobalClass(c.getGenericString());
                        page_.setGlobalType(c);
                        page_.setGlobalDirType(c);
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                        processValueParam(_context, page_, c, method_);
                        page_.getMappingLocal().clear();
                        page_.getMappingLocal().putAllMap(method_.getMappings());
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllMethods().getVal(method_));
                    }
                }
            }
            page_.setGlobalClass("");
            page_.setGlobalType(null);
            page_.setGlobalDirType(null);
            putCallOperator(_context);
            for (OperatorBlock o: _context.getAnalyzing().getFoundOperators()) {
                page_.setImporting(o);
                page_.setImportingAcces(new OperatorAccessor());
                page_.setImportingTypes(o);
                _context.getCoverage().putCalls(_context,"",o);
                StringList params_ = o.getParametersNames();
                StringList types_ = o.getImportedParametersTypes();
                prepareParams(page_, o.getParametersNamesOffset(),o.getParamErrors(),params_, types_, o.isVarargs());
                page_.getMappingLocal().clear();
                ExecOperatorBlock value_ = _context.getAnalyzing().getMapOperators().getValue(o.getNameNumber());
                o.buildFctInstructionsReadOnly(_context,value_);
            }
        }
        _context.setAnnotAnalysis(true);
        for (RootBlock c: page_.getFoundTypes()) {
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
            Members mem_ = page_.getMapMembers().getVal(c);
            page_.setGlobalClass(c.getGenericString());
            page_.setGlobalType(c);
            page_.setCurrentFct(null);
            CustList<Block> annotated_ = new CustList<Block>();
            if (!(c instanceof InnerElementBlock)) {
                annotated_.add(c);
            }
            annotated_.addAllElts(getDirectChildren(c));
            _context.getCoverage().putBlockOperations(_context,page_.getMapTypes().getVal(c),c);
            page_.getMappingLocal().clear();
            page_.getMappingLocal().putAllMap(c.getMappings());
            for (Block b:annotated_) {
                page_.setCurrentBlock(b);
                page_.setCurrentAnaBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    _context.setAnnotAnalysisField(true);
                    ((AnnotationMethodBlock)b).buildExpressionLanguage(_context,mem_.getAllAnnotMethods().getVal(((AnnotationMethodBlock)b)));
                    _context.getCoverage().putBlockOperations(_context, mem_.getAllAnnotMethods().getVal((AnnotationMethodBlock) b),b);
                    _context.setAnnotAnalysisField(false);
                }
                if (b instanceof RootBlock) {
                    _context.setAnnotAnalysisField(false);
                    _context.getCoverage().putBlockOperationsField(_context,b);
                    ((RootBlock)b).buildAnnotations(_context,mem_.getAllAnnotables().getVal((AnnotableBlock) b));
                }
                if (b instanceof NamedFunctionBlock) {
                    _context.setAnnotAnalysisField(false);
                    _context.getCoverage().putBlockOperationsField(_context,b);
                    ((NamedFunctionBlock)b).buildAnnotations(_context,mem_.getAllNamed().getVal((NamedFunctionBlock) b));
                    ((NamedFunctionBlock)b).buildAnnotationsParameters(_context,mem_.getAllNamed().getVal((NamedFunctionBlock) b));
                }
                if (b instanceof InfoBlock) {
                    _context.setAnnotAnalysisField(false);
                    _context.getCoverage().putBlockOperationsField(_context,b);
                    _context.getCoverage().putBlockOperations(_context, (ExecBlock) mem_.getAllFields().getVal((InfoBlock) b),b);
                    ((InfoBlock)b).buildAnnotations(_context,mem_.getAllAnnotables().getVal((AnnotableBlock) b));
                }
            }
        }
        page_.setGlobalClass("");
        page_.setGlobalType(null);
        page_.setGlobalDirType(null);
        page_.setCurrentFct(null);
        for (OperatorBlock o: _context.getAnalyzing().getFoundOperators()) {
            page_.setImporting(o);
            page_.setImportingAcces(new OperatorAccessor());
            page_.setImportingTypes(o);
            page_.setCurrentBlock(o);
            page_.setCurrentAnaBlock(o);
            _context.setAnnotAnalysisField(false);
             _context.getCoverage().putBlockOperationsField(_context,o);
            page_.getMappingLocal().clear();
            ExecOperatorBlock value_ = _context.getAnalyzing().getMapOperators().getValue(o.getNameNumber());
            o.buildAnnotations(_context,value_);
            o.buildAnnotationsParameters(_context,value_);
        }
        _context.setAnnotAnalysis(false);
        //init annotations here
        for (RootBlock c: page_.getFoundTypes()) {
            c.validateConstructors(_context);
        }
    }

    private static void validateFinals(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        AssignedVariablesBlock assVars_ = new AssignedVariablesBlock();
        for (RootBlock c: page_.getFoundTypes()) {
            Members mem_ = page_.getMapMembers().getVal(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
            page_.getInitFields().clear();
            page_.getAssignedDeclaredFields().clear();
            page_.getAllDeclaredFields().clear();
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
                int v_ = 0;
                for (String f: f_.getFieldName()) {
                    AssignmentBefore as_ = new AssignmentBefore();
                    StringList err_ = getErFields(f_, v_);
                    checkConstField(_context, err_,c, fullName_, f);
                    as_.setUnassignedBefore(true);
                    ass_.put(f, as_);
                    v_++;
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
                if (b instanceof InnerTypeOrElement) {
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    page_.setCurrentFct(null);
                    InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                    page_.setCurrentBlock(b);
                    page_.setCurrentAnaBlock(b);
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(c.getMappings());
                    ExecInnerTypeOrElement val_ = mem_.getAllElementFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(_context, val_);
                    AssInfoBlock aInfo_ = new AssElementBlock(val_);
                    aInfo_.setAssignmentBeforeAsLeaf(_context,assVars_,pr_);
                    aInfo_.buildExpressionLanguage(_context,assVars_);
                    aInfo_.setAssignmentAfterAsLeaf(_context,assVars_,pr_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                    pr_ = (AssBlock) aInfo_;
                }
                if (b instanceof FieldBlock) {
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    FieldBlock method_ = (FieldBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    page_.setCurrentBlock(b);
                    page_.setCurrentAnaBlock(b);
                    page_.setCurrentFct(null);
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(c.getMappings());
                    ExecFieldBlock exp_ = mem_.getAllExplicitFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(_context, exp_);
                    AssInfoBlock aInfo_;
                    aInfo_ = new AssFieldBlock(exp_);
                    aInfo_.setAssignmentBeforeAsLeaf(_context,assVars_,pr_);
                    aInfo_.buildExpressionLanguage(_context,assVars_);
                    aInfo_.setAssignmentAfterAsLeaf(_context,assVars_,pr_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                    pr_ = (AssBlock) aInfo_;
                }
                if (b instanceof StaticBlock) {
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    StaticBlock method_ = (StaticBlock) b;
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(method_.getMappings());
                    AssMemberCallingsBlock res_ = AssBlockUtil.buildFctInstructions(method_,mem_.getAllInits().getVal(method_),_context,pr_,assVars_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal(res_).getFieldsRoot());
                    page_.clearAllLocalVars(assVars_);
                    pr_ = res_;
                }
            }
            for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
                String key_ = a.getKey();
                ClassField id_ = new ClassField(fullName_, key_);
                if (!ContextUtil.isFinalField(_context,id_)) {
                    continue;
                }
                if (!StringList.contains(page_.getInitFields(),key_)) {
                    //error
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(c.getFile().getFileName());
                    un_.setIndexFile(c.getOffset().getOffsetTrim());
                    un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                            key_,fullName_);
                    _context.addError(un_);
                }
            }

        }
        _context.setAssignedStaticFields(true);
        for (RootBlock c: page_.getFoundTypes()) {
            Members mem_ = page_.getMapMembers().getVal(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
            page_.getInitFields().clear();
            page_.getAssignedDeclaredFields().clear();
            page_.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            _context.getCoverage().putCalls(_context,fullName_);
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
                if (b instanceof FieldBlock) {
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    FieldBlock method_ = (FieldBlock) b;
                    page_.setCurrentBlock(b);
                    page_.setCurrentAnaBlock(b);
                    page_.setCurrentFct(null);
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(c.getMappings());
                    ExecFieldBlock exp_ = mem_.getAllExplicitFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(_context, exp_);
                    AssFieldBlock aInfo_ = new AssFieldBlock(exp_);
                    aInfo_.setAssignmentBeforeAsLeaf(_context,assVars_,pr_);
                    aInfo_.buildExpressionLanguage(_context,assVars_);
                    aInfo_.setAssignmentAfterAsLeaf(_context,assVars_,pr_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal(aInfo_).getFieldsRoot());
                    pr_ = aInfo_;
                }
                if (b instanceof InstanceBlock) {
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    InstanceBlock method_ = (InstanceBlock) b;
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(method_.getMappings());
                    AssMemberCallingsBlock res_ = AssBlockUtil.buildFctInstructions(method_,mem_.getAllInits().getVal(method_), _context,pr_, assVars_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal(res_).getFieldsRoot());
                    page_.clearAllLocalVars(assVars_);
                    pr_ = res_;
                }
            }
            b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            if (!hasCtor_) {
                for (EntryCust<String, SimpleAssignment> a : assAfter_.entryList()) {
                    String fieldName_ = a.getKey();
                    ClassField key_ = new ClassField(fullName_, fieldName_);
                    if (!ContextUtil.isFinalField(_context,key_)) {
                        continue;
                    }
                    if (StringList.contains(page_.getInitFields(), fieldName_)) {
                        continue;
                    }
                    //error
                    for (Block b : bl_) {
                        if (b instanceof InfoBlock) {
                            if (StringList.contains(((InfoBlock) b).getFieldName(), fieldName_)) {
                                FoundErrorInterpret un_ = new FoundErrorInterpret();
                                un_.setFileName(c.getFile().getFileName());
                                un_.setIndexFile(((InfoBlock) b).getFieldNameOffset());
                                un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                                        fieldName_,fullName_);
                                _context.addError(un_);
                            }
                        }
                    }
                }
            }
            b_.putAllMap(AssignmentsUtil.assignSimpleBefore(assAfter_));
            processInterfaceCtor(_context, c, fullName_, bl_);
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    page_.getInitFieldsCtors().clear();
                    page_.getInitFieldsCtors().addAllElts(page_.getInitFields());
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(method_.getMappings());
                    AssMemberCallingsBlock met_ = AssBlockUtil.buildFctInstructions(method_,mem_.getAllCtors().getVal(method_), _context,null, assVars_);
                    IdMap<AssBlock, AssignedVariables> id_ = assVars_.getFinalVariables();
                    AssignedVariables assTar_ = id_.getVal(met_);
                    for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                        String fieldName_ = f.getKey();
                        ClassField key_ = new ClassField(fullName_, fieldName_);
                        if (!ContextUtil.isFinalField(_context,key_)) {
                            continue;
                        }
                        if (StringList.contains(page_.getInitFieldsCtors(),fieldName_)) {
                            continue;
                        }
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(c.getFile().getFileName());
                        un_.setIndexFile(method_.getNameOffset());
                        un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                                fieldName_,fullName_);
                        _context.addError(un_);
                    }
                    page_.clearAllLocalVars(assVars_);
                }
            }
        }
        _context.setAssignedFields(true);
        assVars_.getFinalVariablesGlobal().getFields().clear();
        assVars_.getFinalVariablesGlobal().getFieldsRoot().clear();
        assVars_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
        assVars_.getFinalVariablesGlobal().getFieldsBefore().clear();
        StringMap<AssignmentBefore> b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
        b_.clear();

        for (RootBlock c: page_.getFoundTypes()) {
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
            Members mem_ = page_.getMapMembers().getVal(c);
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
                if (!(b instanceof OverridableBlock)) {
                    continue;
                }
                OverridableBlock method_ = (OverridableBlock) b;
                if (isStdOrExplicit(method_)) {
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(page_,method_.getParametersNamesOffset(), method_.getParamErrors(),params_, types_, method_.isVarargs());
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(method_.getMappings());
                    AssBlockUtil.buildFctInstructions(method_,mem_.getAllMethods().getVal(method_),_context,null,assVars_);
                    page_.clearAllLocalVars(assVars_);
                } else {
                    page_.setGlobalClass(c.getGenericString());
                    page_.setGlobalType(c);
                    page_.setGlobalDirType(c);
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                    processValueParam(_context, page_, c, method_);
                    page_.getMappingLocal().clear();
                    page_.getMappingLocal().putAllMap(method_.getMappings());
                    AssBlockUtil.buildFctInstructions(method_,mem_.getAllMethods().getVal(method_),_context,null,assVars_);
                    page_.clearAllLocalVars(assVars_);
                }
            }
        }
        page_.setGlobalClass("");
        page_.setGlobalType(null);
        page_.setGlobalDirType(null);
        putCallOperator(_context);
        for (OperatorBlock o: _context.getAnalyzing().getFoundOperators()) {
            page_.setImporting(o);
            page_.setImportingAcces(new OperatorAccessor());
            page_.setImportingTypes(o);
            _context.getCoverage().putCalls(_context,"",o);
            StringList params_ = o.getParametersNames();
            StringList types_ = o.getImportedParametersTypes();
            ExecOperatorBlock value_ = _context.getAnalyzing().getMapOperators().getValue(o.getNameNumber());
            prepareParams(page_,o.getParametersNamesOffset(),o.getParamErrors(), params_, types_, o.isVarargs());
            page_.getMappingLocal().clear();
            AssBlockUtil.buildFctInstructions(o,value_,_context,null,assVars_);
            page_.clearAllLocalVars(assVars_);
        }
    }

    private static void putCallOperator(ContextEl _context) {
        if (_context.getAnalyzing().getFoundOperators().isEmpty()) {
            return;
        }
        _context.getCoverage().putCalls(_context,"");
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

    private static void processValueParam(ContextEl _context, AnalyzedPageEl _page, RootBlock _cl, OverridableBlock _method) {
        if (_method.getKind() == MethodKind.SET_INDEX) {
            String p_ = _context.getKeyWords().getKeyWordValue();
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
                _page.getInfosVars().put(p_, lv_);
            }
        }
    }

    private static void processInterfaceCtor(ContextEl _context, RootBlock _cl, String _name, CustList<Block> _blocks) {
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
            StringList.removeAllElements(allCopy_, _context.getStandards().getPredefinedInterfacesInitOrder());
            String superClass_ = un_.getImportedDirectGenericSuperClass();
            String superClassId_ = StringExpUtil.getIdFromAllTypes(superClass_);
            RootBlock superType_ = _context.getAnalyzing().getAnaClassBody(superClassId_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            for (String i: allCopy_) {
                RootBlock int_ = _context.getAnalyzing().getAnaClassBody(i);
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
        _context.getNeedInterfaces().clear();
        _context.getNeedInterfaces().addAllElts(filteredCtor_);
        if (!hasCtor_ && !filteredCtor_.isEmpty()) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFileName(_cl.getFile().getFileName());
            undef_.setIndexFile(0);
            //original id len
            undef_.buildError(_context.getAnalysisMessages().getMustCallIntCtor(),
                    _cl.getFullName());
            _context.addError(undef_);
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

    private static void checkConstField(ContextEl _context, StringList _err, RootBlock _cl, String _clName, String _field) {
        if (_context.getClasses().getStaticFieldMap(_clName).getVal(_field) == null) {
            if (!_cl.isStaticType()) {
                //ERROR
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cl.getFile().getFileName());
                un_.setIndexFile(_cl.getOffset().getOffsetTrim());
                //field name len
                un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                        _field,_clName);
                _context.addError(un_);
                _err.add(un_.getBuiltError());
            }
        }
    }

    public static void initStaticFields(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
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
            _context.getClasses().getStaticFields().put(fullName_, cl_);
        }
        IdMap<ClassField,ClassFieldBlock> cstFields_ = new IdMap<ClassField,ClassFieldBlock>();
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(c.getFullName()));
            page_.setImportingTypes(c);
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
                page_.setGlobalClass(c.getGenericString());
                page_.setGlobalType(c);
                page_.setGlobalDirType(c);
                page_.setCurrentBlock(f_);
                page_.setCurrentAnaBlock(f_);
                page_.getMappingLocal().clear();
                page_.getMappingLocal().putAllMap(c.getMappings());
                page_.setCurrentFct(null);
                CustList<OperationNode> list_ = f_.buildExpressionLanguageQuickly(_context);
                String cl_ = c.getFullName();
                for (String f: f_.getFieldName()) {
                    ClassField k_ = new ClassField(cl_, f);
                    cstFields_.addEntry(k_,new ClassFieldBlock(list_,f_));
                }
            }
        }
        page_.getAnonymousTypes().clear();
        while (true) {
            boolean calculatedValue_ = false;
            for (EntryCust<ClassField,ClassFieldBlock> e: cstFields_.entryList()) {
                ClassField k_ = e.getKey();
                Struct value_ = _context.getClasses().getStaticField(k_);
                if (value_ != null) {
                    continue;
                }
                ClassFieldBlock cf_ = e.getValue();
                FieldBlock f_ = cf_.getFieldName();
                CustList<OperationNode> ops_ = cf_.getClassName();
                ElUtil.tryCalculate(f_,ops_, _context, k_.getFieldName());
                value_ = _context.getClasses().getStaticField(k_);
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
