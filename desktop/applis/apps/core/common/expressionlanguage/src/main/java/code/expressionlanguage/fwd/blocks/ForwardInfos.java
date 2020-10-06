package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.OperatorAccessor;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.OverridesTypeUtil;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.fwd.Members;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.opers.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class ForwardInfos {
    private ForwardInfos() {
    }
    public static void generalForward(AnalyzedPageEl _page, Forwards _forwards, ContextEl _context) {
        Coverage coverage_ = _context.getCoverage();
        coverage_.setKeyWords(_page.getKeyWords());
        coverage_.putToStringOwner(_page.getAliasObject());
        StringMap<ExecFileBlock> files_ = new StringMap<ExecFileBlock>();
        for (EntryCust<String, FileBlock> f: _page.getFilesBodies().entryList()) {
            String file_ = f.getKey();
            FileBlock content_ = f.getValue();
            ExecFileBlock exFile_ = new ExecFileBlock(content_.getOffset().getOffsetTrim(), content_.getMetricsCore(), content_.getFileName());
            coverage_.putFile(content_);
            files_.addEntry(file_,exFile_);
        }
        for (RootBlock r: _page.getAllFoundTypes()) {
            Members v_ = new Members();
            _forwards.getMapMembers().put(r, v_);
        }
        Classes classes_ = _context.getClasses();
        for (EntryCust<String,FileBlock> e: _page.getFilesBodies().entryList()) {
            FileBlock fileBlock_ = e.getValue();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecFile(fileBlock_,exFile_, coverage_, classes_, _forwards);
        }
        for (AnonymousInstancingOperation e: _page.getAnonymousList()) {
            AnonymousTypeBlock block_ = e.getBlock();
            FileBlock fileBlock_ = block_.getFile();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecType(exFile_, block_, coverage_, classes_, _forwards);
        }
        for (AnonymousLambdaOperation f: _page.getAllAnonymousLambda()) {
            AnonymousFunctionBlock e = f.getBlock();
            FileBlock fileBlock_ = e.getFile();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecType(exFile_, e, coverage_, classes_, _forwards);
        }
        innerFetchExecEnd(_forwards);
        StringMap<PolymorphMethod> toStringMethodsToCallBodies_ = _context.getClasses().getToStringMethodsToCallBodies();
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
            ClassMethodIdReturn resDyn_ = e.getValue();
            ExecRootBlock ex_ = fetchType(resDyn_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock fct_ = fetchFunction(resDyn_.getRootNumber(),resDyn_.getMemberNumber(), _forwards);
            String fullName_ = e.getKey().getFullName();
            toStringMethodsToCallBodies_.addEntry(fullName_,new PolymorphMethod(ex_,fct_));
            coverage_.putToStringOwner(fullName_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock root_ = e.getKey();
            ClassMethodIdOverrides redirections_ = e.getValue().getRootBlock().getRedirections();
            for (OverridableBlock o: root_.getOverridableBlocks()) {
                if (o.hiddenInstance()) {
                    continue;
                }
                if (o.isFinalMethod()) {
                    continue;
                }
                MethodId id_ = o.getId();
                StringMap<GeneStringOverridable> map_ = OverridesTypeUtil.getConcreteMethodsToCall(root_, id_, _page);
                map_.putAllMap(o.getOverrides());
                ClassMethodIdOverride override_ = new ClassMethodIdOverride(fetchFunction(root_.getNumberAll(), o.getNameNumber(), _forwards));
                for (EntryCust<String,GeneStringOverridable> g: map_.entryList()) {
                    GeneStringOverridable value_ = g.getValue();
                    int numberAll_ = value_.getType().getNumberAll();
                    override_.put(g.getKey(), value_.getGeneString(), fetchType(numberAll_, _forwards), fetchFunction(numberAll_, value_.getBlock().getNameNumber(), _forwards));
                }
                redirections_.add(override_);
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock root_ = e.getKey();
            if (!root_.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(root_,root_.getGenericString()));
                allSuperClass_.addAllElts(root_.getAllGenericSuperTypesInfo());
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    RootBlock superBl_ = s.getRootBlock();
                    for (OverridableBlock m: ClassesUtil.getMethodExecBlocks(superBl_)) {
                        if (m.isAbstractMethod()) {
                            ExecRootBlock ex_ = _forwards.getMapMembers().getValue(superBl_.getNumberAll()).getRootBlock();
                            ExecOverrideInfo val_ = ex_.getRedirections().getVal(fetchFunction(superBl_.getNumberAll(),m.getNameNumber(), _forwards), root_.getFullName());
                            if (val_ == null) {
                                ExecOverridableBlock value_ = _forwards.getMapMembers().getValue(superBl_.getNumberAll()).getAllMethods().getValue(m.getNameOverrideNumber());
                                e.getValue().getRootBlock().getFunctionalBodies().add(new ExecFunctionalInfo(s.getFormatted(),value_));
                            }
                        }
                    }
                }
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            e.getValue().getRootBlock().getAllSuperTypes().addAllElts(e.getKey().getAllSuperTypes());
            e.getValue().getRootBlock().getStaticInitImportedInterfaces().addAllElts(e.getKey().getStaticInitImportedInterfaces());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            updateExec(e.getValue().getRootBlock(), e.getKey());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock i = e.getKey();
            CustList<AnaFormattedRootBlock> genericClasses_ = i.getAllGenericClassesInfo();
            if (i instanceof UniqueRootedBlock && genericClasses_.size() > 1) {
                e.getValue().getRootBlock().setUniqueType(fetchType(genericClasses_.get(1).getRootBlock().getNumberAll(), _forwards));
            }
            ConstructorBlock emptyCtor_ = i.getEmptyCtor();
            if (emptyCtor_ != null) {
                e.getValue().getRootBlock().setEmptyCtor(fetchFunction(i.getNumberAll(),emptyCtor_.getNameNumber(), _forwards));
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            CustList<AnaFormattedRootBlock> allGenericSuperTypes_ = e.getKey().getAllGenericSuperTypesInfo();
            CustList<ExecFormattedRootBlock> l_ = new CustList<ExecFormattedRootBlock>();
            for (AnaFormattedRootBlock s: allGenericSuperTypes_) {
                l_.add(new ExecFormattedRootBlock(_forwards.getMapMembers().getValue(s.getRootBlock().getNumberAll()).getRootBlock(),s.getFormatted()));
            }
            e.getValue().getRootBlock().getAllGenericSuperTypes().addAllElts(l_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            IdMap<RootBlock, Members> mapMembers_ = _forwards.getMapMembers();
            validateIds(e.getKey(), mapMembers_);
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            value_.buildImportedTypes(o.getImportedReturnType(), o.getImportedParametersTypes());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            Members mem_ = e.getValue();
            String fullName_ = c.getFullName();
            coverage_.putCalls(fullName_);
            for (EntryCust<InitBlock, ExecInitBlock> f: mem_.getAllInits().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                InitBlock method_ = f.getKey();
                _forwards.getAllFct().addEntry(method_,f.getValue());
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> f: mem_.getAllCtors().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                ConstructorBlock method_ = f.getKey();
                coverage_.putCalls(fullName_,method_);
                _forwards.getAllFct().addEntry(method_,f.getValue());
                mem_.getAllAnnotables().addEntry(method_,f.getValue());
                fwdInstancingStep(method_, f.getValue());
            }
            for (EntryCust<OverridableBlock, ExecOverridableBlock> f: mem_.getAllMethods().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                OverridableBlock method_ =  f.getKey();
                coverage_.putCalls(fullName_,method_);
                mem_.getAllAnnotables().addEntry(method_,f.getValue());
                _forwards.getAllFct().addEntry(method_,f.getValue());
            }
        }
        coverage_.putCalls("");
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            coverage_.putCalls("",o);
            _forwards.getAllFct().addEntry(o,value_);
        }
        for (AnonymousLambdaOperation e: _page.getAllAnonymousLambda()) {
            AnonymousFunctionBlock method_ = e.getBlock();
            RootBlock c_ = method_.getParentType();
            coverage_.putCalls(c_.getFullName(),method_);
            ExecNamedFunctionBlock function_ = buildExecAnonymousLambdaOperation(e, _forwards);
            _forwards.getAllFct().addEntry(method_, function_);
            int numberFile_ = method_.getFile().getNumberFile();
            ExecFileBlock value_ = files_.getValue(numberFile_);
            function_.setFile(value_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            mem_.getAllAnnotables().addEntry(c,mem_.getRootBlock());
            mem_.getAllAnnotablesRoots().addEntry(c,mem_.getRootBlock());
            for (Block b: ClassesUtil.getDirectChildren(c)) {
                if (b instanceof RootBlock) {
                    ExecRootBlock val_ = _forwards.getMapMembers().getValue(((RootBlock) b).getNumberAll()).getRootBlock();
                    _forwards.getMapMembers().getValue(c.getNumberAll()).getRootBlock().getChildrenTypes().add(val_);
                    mem_.getAllAnnotables().addEntry((RootBlock) b, _forwards.getMapMembers().getValue(((RootBlock) b).getNumberAll()).getRootBlock());
                    mem_.getAllAnnotablesRoots().addEntry((RootBlock) b, _forwards.getMapMembers().getValue(((RootBlock) b).getNumberAll()).getRootBlock());
                }
            }
            for (EntryCust<AnnotationMethodBlock, ExecAnnotationMethodBlock> f: mem_.getAllAnnotMethods().entryList()) {
                mem_.getAllAnnotables().addEntry(f.getKey(),f.getValue());
                mem_.getRootBlock().getAnnotationsFields().add(f.getValue());
            }
            for (EntryCust<InnerElementBlock, ExecInnerElementBlock> f: mem_.getAllInnerElementFields().entryList()) {
                InnerElementBlock method_ = f.getKey();
                ExecInnerElementBlock val_ = f.getValue();
                mem_.getRootBlock().getEnumElements().add(val_);
                mem_.getAllAnnotables().addEntry(method_,val_);
                mem_.getAllAnnotablesRoots().addEntry(method_,val_);
            }
            for (EntryCust<ElementBlock, ExecElementBlock> f: mem_.getAllSimpleElementFields().entryList()) {
                ElementBlock method_ = f.getKey();
                ExecElementBlock val_ = f.getValue();
                mem_.getRootBlock().getEnumElements().add(val_);
                mem_.getAllAnnotables().addEntry(method_,val_);
            }
            for (EntryCust<FieldBlock, ExecFieldBlock> f: mem_.getAllExplicitFields().entryList()) {
                FieldBlock method_ = f.getKey();
                ExecFieldBlock exp_ = f.getValue();
                mem_.getAllAnnotables().addEntry(method_,exp_);
                if (!method_.isStaticField()) {
                    mem_.getRootBlock().getInstanceFields().add(exp_);
                }
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            Members mem_ = e.getValue();
            for (EntryCust<InnerElementBlock, ExecInnerElementBlock> f: mem_.getAllInnerElementFields().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                _page.setCurrentFct(null);
                InnerElementBlock method_ = f.getKey();
                _page.setCurrentBlock(f.getKey());
                _page.setCurrentAnaBlock(f.getKey());
                ExecInnerElementBlock val_ = f.getValue();
                fwdExpressionLanguageReadOnly(method_, val_, _page, coverage_, _forwards);
            }
            for (EntryCust<ElementBlock, ExecElementBlock> f: mem_.getAllSimpleElementFields().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                _page.setCurrentFct(null);
                ElementBlock method_ = f.getKey();
                _page.setCurrentBlock(f.getKey());
                _page.setCurrentAnaBlock(f.getKey());
                ExecElementBlock val_ = f.getValue();
                fwdExpressionLanguageReadOnly(method_, val_, _page, coverage_, _forwards);
            }
            for (EntryCust<FieldBlock, ExecFieldBlock> f: mem_.getAllExplicitFields().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                FieldBlock method_ = f.getKey();
                _page.setCurrentBlock(f.getKey());
                _page.setCurrentAnaBlock(f.getKey());
                _page.setCurrentFct(null);
                ExecFieldBlock exp_ = f.getValue();
                fwdExpressionLanguageReadOnly(method_, exp_, _page, coverage_, _forwards);
            }
        }
        _page.setAnnotAnalysis(true);
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            Members mem_ = e.getValue();
            _page.setGlobalClass(c.getGenericString());
            _page.setGlobalType(c);
            _page.setCurrentFct(null);
            CustList<Block> annotated_ = new CustList<Block>();
            if (!(c instanceof InnerElementBlock)) {
                annotated_.add(c);
            }
            annotated_.addAllElts(ClassesUtil.getDirectChildren(c));
            coverage_.putBlockOperations(_forwards.getMapMembers().getVal(c).getRootBlock(),c);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getMappings());
            for (Block b:annotated_) {
                _page.setCurrentBlock(b);
                _page.setCurrentAnaBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    _page.setAnnotAnalysisField(true);
                    _page.setGlobalDirType(c);
                    fwd((AnnotationMethodBlock)b,mem_.getAllAnnotMethods().getVal(((AnnotationMethodBlock)b)), _page, coverage_, _forwards);
                    coverage_.putBlockOperations(mem_.getAllAnnotMethods().getVal((AnnotationMethodBlock) b),b);
                    _page.setAnnotAnalysisField(false);
                }
                if (b instanceof RootBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    coverage_.putBlockOperationsField(_page, b);
                    ExecRootBlock val_ = mem_.getAllAnnotablesRoots().getVal((RootBlock) b);
                    fwdAnnotations(((RootBlock)b), val_, _page, coverage_, _forwards);
                }
                if (b instanceof NamedFunctionBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    coverage_.putBlockOperationsField(_page, b);
                    ExecNamedFunctionBlock val_ = mem_.getAllNamed().getVal((NamedFunctionBlock) b);
                    fwdAnnotations(((NamedFunctionBlock)b), val_, _page, coverage_, _forwards);
                    fwdAnnotationsParameters(((NamedFunctionBlock)b), val_, _page, coverage_, _forwards);
                }
                if (b instanceof InnerElementBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    coverage_.putBlockOperationsField(_page, b);
                    coverage_.putBlockOperations((ExecBlock) mem_.getAllFields().getVal((InfoBlock) b),b);
                    ExecInnerElementBlock val_ = mem_.getAllInnerElementFields().getVal((InnerElementBlock) b);
                    fwdAnnotations(((InnerElementBlock)b), val_, _page, coverage_, _forwards);
                }
                if (b instanceof ElementBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    coverage_.putBlockOperationsField(_page, b);
                    coverage_.putBlockOperations((ExecBlock) mem_.getAllFields().getVal((InfoBlock) b),b);
                    ExecElementBlock val_ = mem_.getAllSimpleElementFields().getVal((ElementBlock) b);
                    fwdAnnotations(((ElementBlock)b), val_, _page, coverage_, _forwards);
                }
                if (b instanceof FieldBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    coverage_.putBlockOperationsField(_page, b);
                    coverage_.putBlockOperations((ExecBlock) mem_.getAllFields().getVal((InfoBlock) b),b);
                    ExecFieldBlock val_ = mem_.getAllExplicitFields().getVal((FieldBlock) b);
                    fwdAnnotations(((FieldBlock)b), val_, _page, coverage_, _forwards);
                }
            }
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            _page.setCurrentBlock(o);
            _page.setCurrentAnaBlock(o);
            _page.setAnnotAnalysisField(false);
            coverage_.putBlockOperationsField(_page, o);
            ExecOperatorBlock value_ = e.getValue();
            fwdAnnotations(o, value_, _page, coverage_, _forwards);
            fwdAnnotationsParameters(o, value_, _page, coverage_, _forwards);
        }
        _page.setAnnotAnalysis(false);
        for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> e: _forwards.getAllFct().entryList()) {
            buildExec(_page,e.getKey(),e.getValue(), coverage_, _forwards);
        }

        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock root_ = e.getKey();
            Members valueMember_ = e.getValue();
            IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> allFct_ = valueMember_.getAllFct();
            IdMap<InfoBlock, ExecInfoBlock> allFields_ = valueMember_.getAllFields();
            for (Block b: ClassesUtil.getDirectChildren(root_)) {
                if (b instanceof MemberCallingsBlock) {
                    MemberCallingsBlock b1_ = (MemberCallingsBlock) b;
                    ExecMemberCallingsBlock value_ = allFct_.getValue(b1_.getNumberFct());
                    feedFct(b1_, value_, _forwards);
                }
                if (b instanceof InfoBlock) {
                    ExecInfoBlock value_ = allFields_.getValue(((InfoBlock)b).getFieldNumber());
                    for (AnonymousTypeBlock a: ((InfoBlock)b).getAnonymous()) {
                        value_.getAnonymous().add(_forwards.getMapAnonTypes().getValue(a.getNumberAnonType()));
                    }
                    for (AnonymousFunctionBlock a: ((InfoBlock)b).getAnonymousFct()) {
                        value_.getAnonymousLambda().add(_forwards.getMapAnonLambda().getValue(a.getNumberLambda()));
                    }
                }
            }
            ExecRootBlock value_ = e.getValue().getRootBlock();
            for (AnonymousFunctionBlock a: root_.getAnonymousRootFct()) {
                value_.getAnonymousRootLambda().add(_forwards.getMapAnonLambda().getValue(a.getNumberLambda()));
            }
            for (AnonymousTypeBlock a: root_.getAnonymousRoot()) {
                value_.getAnonymousRoot().add(_forwards.getMapAnonTypes().getValue(a.getNumberAnonType()));
            }
        }
        for (EntryCust<AnonymousFunctionBlock, ExecAnonymousFunctionBlock> a: _forwards.getMapAnonLambda().entryList()) {
            AnonymousFunctionBlock key_ = a.getKey();
            ExecAnonymousFunctionBlock value_ = a.getValue();
            feedFct(key_, value_, _forwards);
        }
    }

    private static void feedFct(MemberCallingsBlock b1_, ExecMemberCallingsBlock value_, Forwards _forwards) {
        for (AnonymousFunctionBlock a: b1_.getAnonymousFct()) {
            value_.getAnonymousLambda().add(_forwards.getMapAnonLambda().getValue(a.getNumberLambda()));
        }
        for (AnonymousTypeBlock a: b1_.getAnonymous()) {
            value_.getAnonymous().add(_forwards.getMapAnonTypes().getValue(a.getNumberAnonType()));
        }
        for (RootBlock a: b1_.getReserved()) {
            value_.getReserved().add(_forwards.getMapMembers().getValue(a.getNumberAll()).getRootBlock());
        }
    }

    private static void processAppend(ExecFileBlock _exFile, Block _outer, RootBlock _root, Coverage _coverage, Classes _classes, Forwards _forwards) {
        _coverage.putType(_root);
        String fullName_ = _root.getFullName();
        RootBlock parentType_ = _root.getParentType();
        int index_ = -1;
        if (parentType_ != null) {
            index_ = parentType_.getNumberAll();
        }
        ExecRootBlock execParentType_ = null;
        if (_forwards.getMapMembers().getKeys().isValidIndex(index_)) {
            execParentType_ = _forwards.getMapMembers().getValue(index_).getRootBlock();
        }
        if (_root instanceof AnonymousTypeBlock) {
            ExecAnonymousTypeBlock e_ = new ExecAnonymousTypeBlock(_root.getOffset().getOffsetTrim(), new ExecRootBlockContent(_root.getRootBlockContent()), _root.getAccess());
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _forwards.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _forwards.getMapAnonTypes().put((AnonymousTypeBlock)_root, e_);
            _classes.getClassesBodies().put(fullName_, e_);
        }
        if (_root instanceof ClassBlock) {
            ExecClassBlock e_ = new ExecClassBlock(_root.getOffset().getOffsetTrim(), new ExecRootBlockContent(_root.getRootBlockContent()), _root.getAccess(), new ExecClassContent(((ClassBlock) _root).getClassContent()));
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _forwards.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _classes.getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof EnumBlock) {
            ExecEnumBlock e_ = new ExecEnumBlock(_root.getOffset().getOffsetTrim(), new ExecRootBlockContent(_root.getRootBlockContent()), _root.getAccess());
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _forwards.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _classes.getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InterfaceBlock) {
            ExecInterfaceBlock e_ = new ExecInterfaceBlock(_root.getOffset().getOffsetTrim(), new ExecRootBlockContent(_root.getRootBlockContent()), _root.getAccess(), _root.isStaticType());
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _forwards.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _classes.getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof AnnotationBlock) {
            ExecAnnotationBlock e_ = new ExecAnnotationBlock(_root.getOffset().getOffsetTrim(), new ExecRootBlockContent(_root.getRootBlockContent()), _root.getAccess());
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _forwards.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _classes.getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InnerElementBlock) {
            ExecInnerElementBlock e_ = new ExecInnerElementBlock(_root.getOffset().getOffsetTrim(), new ExecRootBlockContent(_root.getRootBlockContent()), _root.getAccess(), new ExecElementContent(((InnerElementBlock) _root).getElementContent()));
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _forwards.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _forwards.getMapInnerEltTypes().put((InnerElementBlock) _root, e_);
            _classes.getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
    }

    private static void appendType(ExecFileBlock _exFile, Block _outer, RootBlock _root, ExecRootBlock e_) {
        if (_outer == _root) {
            _exFile.appendChild(e_);
        }
    }

    private static void innerFetchExecEnd(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> r: _forwards.getMapMembers().entryList()) {
            ExecRootBlock current_ = r.getValue().getRootBlock();
            RootBlock k_ = r.getKey();
            Members mem_ = r.getValue();
            for (Block b: ClassesUtil.getDirectChildren(k_)) {
                if (b instanceof InnerElementBlock) {
                    ExecInnerElementBlock val_ = _forwards.getMapInnerEltTypes().getValue(((InnerElementBlock) b).getNumberInner());
                    current_.appendChild(val_);
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllInnerElementFields().addEntry((InnerElementBlock) b,val_);
                }
                if (b instanceof ElementBlock) {
                    ExecElementBlock val_ = new ExecElementBlock(b.getOffset().getOffsetTrim(), new ExecElementContent(((ElementBlock) b).getElementContent()));
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllSimpleElementFields().addEntry((ElementBlock) b,val_);
                }
                if (b instanceof FieldBlock) {
                    ExecFieldBlock val_ = new ExecFieldBlock(b.getOffset().getOffsetTrim(), ((FieldBlock) b).getFieldContent());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllExplicitFields().addEntry((FieldBlock) b,val_);
                }
                if (b instanceof ConstructorBlock) {
                    ExecConstructorBlock val_ = new ExecConstructorBlock(((ConstructorBlock)b).getName(), ((ConstructorBlock)b).isVarargs(), ((ConstructorBlock)b).getAccess(), ((ConstructorBlock)b).getParametersNames(), b.getOffset().getOffsetTrim());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllCtors().addEntry((ConstructorBlock) b,val_);
                    mem_.getAllNamed().addEntry((ConstructorBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof OverridableBlock) {
                    MethodKind kind_ = ((OverridableBlock) b).getKind();
                    ExecOverridableBlock val_ = new ExecOverridableBlock(((OverridableBlock)b).getName(), ((OverridableBlock)b).isVarargs(), ((OverridableBlock)b).getAccess(), ((OverridableBlock)b).getParametersNames(), ((OverridableBlock)b).getModifier(), toExecMethodKind(kind_), b.getOffset().getOffsetTrim());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllMethods().addEntry((OverridableBlock) b,val_);
                    mem_.getAllNamed().addEntry((OverridableBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof AnnotationMethodBlock) {
                    ExecAnnotationMethodBlock val_ = new ExecAnnotationMethodBlock(((AnnotationMethodBlock)b).getName(), ((AnnotationMethodBlock)b).isVarargs(), ((AnnotationMethodBlock)b).getAccess(), ((AnnotationMethodBlock)b).getParametersNames(), ((AnnotationMethodBlock)b).getDefaultValueOffset(), b.getOffset().getOffsetTrim());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllAnnotMethods().addEntry((AnnotationMethodBlock) b,val_);
                    mem_.getAllNamed().addEntry((AnnotationMethodBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof InstanceBlock) {
                    ExecInstanceBlock val_ = new ExecInstanceBlock(b.getOffset().getOffsetTrim());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    val_.setNumber(mem_.getAllInits().size());
                    mem_.getAllInits().put((InitBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof StaticBlock) {
                    ExecStaticBlock val_ = new ExecStaticBlock(b.getOffset().getOffsetTrim());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    val_.setNumber(mem_.getAllInits().size());
                    mem_.getAllInits().put((InitBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
            }
        }
    }

    private static ExecMethodKind toExecMethodKind(MethodKind _k) {
        if (_k == MethodKind.EXPLICIT_CAST) {
            return ExecMethodKind.EXPLICIT_CAST;
        }
        if (_k == MethodKind.IMPLICIT_CAST) {
            return ExecMethodKind.IMPLICIT_CAST;
        }
        if (_k == MethodKind.TRUE_OPERATOR) {
            return ExecMethodKind.TRUE_OPERATOR;
        }
        if (_k == MethodKind.FALSE_OPERATOR) {
            return ExecMethodKind.FALSE_OPERATOR;
        }
        if (_k == MethodKind.GET_INDEX) {
            return ExecMethodKind.GET_INDEX;
        }
        if (_k == MethodKind.SET_INDEX) {
            return ExecMethodKind.SET_INDEX;
        }
        if (_k == MethodKind.TO_STRING) {
            return ExecMethodKind.TO_STRING;
        }
        if (_k == MethodKind.OPERATOR) {
            return ExecMethodKind.OPERATOR;
        }
        return ExecMethodKind.STD_METHOD;
    }
    private static void processExecFile(FileBlock _anaFile, ExecFileBlock _exeFile, Coverage _coverage, Classes _classes, Forwards _forwards) {
        for (Block b: ClassesUtil.getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processExecType(_exeFile,r_, _coverage, _classes, _forwards);
            }
            if (b instanceof OperatorBlock) {
                OperatorBlock r_ = (OperatorBlock) b;
                ExecOperatorBlock e_ = new ExecOperatorBlock(r_.getName(), r_.isVarargs(), r_.getAccess(), r_.getParametersNames(), r_.getOffset().getOffsetTrim());
                _exeFile.appendChild(e_);
                e_.setFile(_exeFile);
                _classes.getOperators().add(e_);
                _forwards.getMapOperators().put(r_,e_);
                _coverage.putOperator(r_);
            }
        }
    }

    private static void processExecType(ExecFileBlock _exeFile, Block _r, Coverage _coverage, Classes _classes, Forwards _forwards) {
        Block c_ = _r;
        if (c_.getFirstChild() != null) {
            while (true) {
                tryProcessAppend(_exeFile, _r, _coverage, _classes, _forwards, c_);
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
                    if (p_ == _r) {
                        end_ = true;
                        break;
                    }
                    c_ = p_;
                }
                if (end_) {
                    break;
                }
            }
        } else {
            tryProcessAppend(_exeFile, _r, _coverage, _classes, _forwards, _r);
        }
    }

    private static void tryProcessAppend(ExecFileBlock _exeFile, Block _r, Coverage _coverage, Classes _classes, Forwards _forwards, Block c_) {
        if (c_ instanceof RootBlock) {
            processAppend(_exeFile,_r,(RootBlock) c_, _coverage, _classes, _forwards);
        }
    }

    private static ExecAnonymousFunctionBlock buildExecAnonymousLambdaOperation(AnonymousLambdaOperation _s, Forwards _forwards) {
        ExecRootBlock declaring = _forwards.getMapMembers().getValue(_s.getRootNumber()).getRootBlock();
        AnonymousFunctionBlock block_ = _s.getBlock();
        block_.setNumberLambda(_forwards.getMapAnonLambda().size());
        ExecAnonymousFunctionBlock fct_ = new ExecAnonymousFunctionBlock(block_.getName(), block_.isVarargs(), block_.getAccess(), block_.getParametersNames(), block_.getModifier(), block_.getOffset().getOffsetTrim(), new ExecAnonFctContent(block_.getAnaAnonFctContent()));
        fct_.setParentType(declaring);
        _forwards.getMapAnonLambda().addEntry(block_,fct_);
        fct_.buildImportedTypes(block_.getImportedReturnType(), block_.getImportedParametersTypes());
        return fct_;
    }

    private static void buildExec(AnalyzedPageEl _page, MemberCallingsBlock _from, ExecMemberCallingsBlock _dest, Coverage _coverage, Forwards _forwards) {
        ExecBracedBlock blockToWrite_ = _dest;
        ExecFileBlock fileDest_ = _dest.getFile();
        Block firstChild_ = _from.getFirstChild();
        ExecDeclareVariable decl_ = null;
        _page.setCurrentBlock(_from);
        _coverage.putBlockOperations(_dest,_from);
        Block en_ = _from;
        if (firstChild_ == null) {
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_);
            _page.setCurrentAnaBlock(en_);
            _coverage.putBlockOperations(en_);
            Block n_ = en_.getFirstChild();
            boolean visit_ = true;
            if (en_ instanceof BreakBlock) {
                ExecBreakBlock exec_ = new ExecBreakBlock(((BreakBlock) en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof CaseCondition) {
                ExecBracedBlock exec_;
                BracedBlock par_ = en_.getParent();
                _coverage.putBlockOperationsSwitchs(par_,en_);
                if (!((CaseCondition) en_).getInstanceTest().isEmpty()) {
                    if (((CaseCondition) en_).getImportedType().isEmpty()) {
                        exec_ = new ExecNullInstanceCaseCondition(((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        _coverage.putBlockOperations(exec_,en_);
                    } else {
                        exec_ = new ExecInstanceCaseCondition(((CaseCondition)en_).getVariableName(), ((CaseCondition) en_).getImportedType(), ((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        _coverage.putBlockOperations(exec_,en_);
                    }
                } else {
                    getExecutableNodes(_page, ((CaseCondition)en_).getRoot(), _coverage, _forwards);
                    if (((CaseCondition) en_).isBuiltEnum()) {
                        if (((CaseCondition) en_).isNullCaseEnum()) {
                            exec_ = new ExecNullCaseCondition(((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                            exec_.setFile(fileDest_);
                            blockToWrite_.appendChild(exec_);
                            _coverage.putBlockOperations(exec_,en_);
                        } else {
                            exec_ = new ExecEnumCaseCondition(((CaseCondition) en_).getValue(), ((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                            exec_.setFile(fileDest_);
                            blockToWrite_.appendChild(exec_);
                            _coverage.putBlockOperations(exec_, en_);
                        }
                    } else {
                        Argument argument_ = Argument.getNullableValue(((CaseCondition) en_).getArgument());
                        if (!argument_.isNull()) {
                            exec_ = new ExecStdCaseCondition(((CaseCondition) en_).getValueOffset(), argument_, en_.getOffset().getOffsetTrim());
                        } else {
                            exec_ = new ExecNullCaseCondition(((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                        }
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        _coverage.putBlockOperations(exec_, en_);
                    }
                }
                blockToWrite_ = exec_;
            } else if (en_ instanceof CatchEval) {
                _coverage.putCatches(en_);
                ExecCatchEval exec_ = new ExecCatchEval(((CatchEval)en_).getVariableName(), ((CatchEval)en_).getImportedClassName(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof IfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot(), _coverage, _forwards);
                ExecCondition exec_ = new ExecIfCondition(((Condition) en_).getConditionOffset(), ((IfCondition) en_).getLabel(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(en_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseIfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot(), _coverage, _forwards);
                ExecCondition exec_ = new ExecElseIfCondition(((Condition) en_).getConditionOffset(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(en_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof WhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot(), _coverage, _forwards);
                ExecCondition exec_ = new ExecWhileCondition(((Condition) en_).getConditionOffset(), ((WhileCondition) en_).getLabel(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(en_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof DoWhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot(), _coverage, _forwards);
                ExecCondition exec_ = new ExecDoWhileCondition(((Condition) en_).getConditionOffset(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(en_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof DoBlock) {
                ExecDoBlock exec_ = new ExecDoBlock(((DoBlock)en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ContinueBlock) {
                ExecContinueBlock exec_ = new ExecContinueBlock(((ContinueBlock) en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof DeclareVariable) {
                ExecDeclareVariable exec_ = new ExecDeclareVariable(((DeclareVariable) en_).getImportedClassName(),((DeclareVariable)en_).getVariableNames(), en_.getOffset().getOffsetTrim());
                decl_ = exec_;
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof DefaultCondition) {
                BracedBlock b_ = en_.getParent();
                ExecBracedBlock exec_;
                _coverage.putBlockOperationsSwitchs(b_,en_);
                String instanceTest_ = ((DefaultCondition)en_).getInstanceTest();
                if (instanceTest_.isEmpty()) {
                    exec_ = new ExecDefaultCondition(en_.getOffset().getOffsetTrim());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(exec_,en_);
                } else {
                    exec_ = new ExecInstanceDefaultCondition(((DefaultCondition)en_).getVariableName(), instanceTest_, ((DefaultCondition)en_).getVariableOffset(), en_.getOffset().getOffsetTrim());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(exec_, en_);
                }
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseCondition) {
                ExecElseCondition exec_ = new ExecElseCondition(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Line) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((Line)en_).getRoot(), _coverage, _forwards);
                if (decl_ != null) {
                    decl_.setImportedClassName(((Line) en_).getImportedClass());
                }
                decl_ = null;
                ExecLine exec_ = new ExecLine(((Line) en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof EmptyInstruction) {
                ExecEmptyInstruction exec_ = new ExecEmptyInstruction(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof FinallyEval) {
                ExecFinallyEval exec_ = new ExecFinallyEval(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachLoop) {
                _coverage.putBlockOperationsLoops(en_);
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((ForEachLoop)en_).getRoot(), _coverage, _forwards);
                ExecForEachLoop exec_ = new ExecForEachLoop(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                        ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachTable) {
                _coverage.putBlockOperationsLoops(en_);
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((ForEachTable)en_).getRoot(), _coverage, _forwards);
                ExecForEachTable exec_ = new ExecForEachTable(((ForEachTable) en_).getLabel(), ((ForEachTable)en_).getImportedClassNameFirst(),
                        ((ForEachTable)en_).getImportedClassNameSecond(),
                        ((ForEachTable)en_).getImportedClassIndexName(), ((ForEachTable)en_).getVariableNameFirst(),
                        ((ForEachTable)en_).getVariableNameSecond(), ((ForEachTable)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForIterativeLoop) {
                _coverage.putBlockOperationsLoops(en_);
                CustList<ExecOperationNode> init_ = getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootInit(), _coverage, _forwards);
                CustList<ExecOperationNode> exp_ = getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootExp(), _coverage, _forwards);
                CustList<ExecOperationNode> step_ = getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootStep(), _coverage, _forwards);
                ExecForIterativeLoop exec_ = new ExecForIterativeLoop(((ForIterativeLoop) en_).getLabel(), ((ForIterativeLoop)en_).getImportedClassName(),
                        ((ForIterativeLoop)en_).getImportedClassIndexName(), ((ForIterativeLoop)en_).getVariableName(), ((ForIterativeLoop)en_).getVariableNameOffset(), ((ForIterativeLoop)en_).getInitOffset(),
                        ((ForIterativeLoop)en_).getExpressionOffset(), ((ForIterativeLoop)en_).getStepOffset(), ((ForIterativeLoop)en_).isEq(),init_,exp_,step_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForMutableIterativeLoop) {
                CustList<ExecOperationNode> init_;
                OperationNode rInit_ = ((ForMutableIterativeLoop) en_).getRootInit();
                if (rInit_ == null) {
                    init_ = new CustList<ExecOperationNode>();
                } else {
                    init_ = getExecutableNodes(_page, rInit_, _coverage, _forwards);
                }
                CustList<ExecOperationNode> exp_;
                OperationNode rExp_ = ((ForMutableIterativeLoop) en_).getRootExp();
                if (rExp_ == null) {
                    exp_ = new CustList<ExecOperationNode>();
                } else {
                    exp_ = getExecutableNodes(_page, rExp_, _coverage, _forwards);
                }
                _coverage.putBlockOperationsConditions(en_);
                OperationNode rStep_ = ((ForMutableIterativeLoop) en_).getRootStep();
                CustList<ExecOperationNode> step_;
                if (rStep_ == null) {
                    step_ = new CustList<ExecOperationNode>();
                } else {
                    step_ = getExecutableNodes(_page, rStep_, _coverage, _forwards);
                }
                ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(((ForMutableIterativeLoop) en_).getLabel(), ((ForMutableIterativeLoop) en_).getImportedClassName(), ((ForMutableIterativeLoop) en_).getImportedClassIndexName(),
                        ((ForMutableIterativeLoop) en_).getVariableNames(), ((ForMutableIterativeLoop) en_).getInitOffset(), ((ForMutableIterativeLoop) en_).getExpressionOffset(), ((ForMutableIterativeLoop) en_).getStepOffset(),
                        init_,exp_,step_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof NullCatchEval) {
                _coverage.putCatches(en_);
                ExecNullCatchEval exec_ = new ExecNullCatchEval(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ReturnMethod) {
                OperationNode r_ = ((ReturnMethod) en_).getRoot();
                if (r_ == null) {
                    ExecReturnMethod exec_ = new ExecReturnMethod(true, ((ReturnMethod) en_).getExpressionOffset(),null, ((ReturnMethod) en_).getReturnType(), en_.getOffset().getOffsetTrim());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(exec_,en_);
                } else {
                    CustList<ExecOperationNode> op_ = getExecutableNodes(_page, r_, _coverage, _forwards);
                    ExecReturnMethod exec_ = new ExecReturnMethod(false, ((ReturnMethod) en_).getExpressionOffset(),op_, ((ReturnMethod) en_).getReturnType(), en_.getOffset().getOffsetTrim());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(exec_,en_);
                }
            } else if (en_ instanceof SwitchBlock) {
                Block first_ = en_.getFirstChild();
                boolean emp_ = first_ == null;
                boolean def_ = false;
                while (first_ != null) {
                    if (first_ instanceof DefaultCondition) {
                        def_ = true;
                    }
                    first_ = first_.getNextSibling();
                }
                _coverage.putBlockOperationsSwitchs(en_,def_);
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((SwitchBlock)en_).getRoot(), _coverage, _forwards);
                ExecBracedBlock exec_;
                if (!((SwitchBlock) en_).getInstanceTest().isEmpty()) {
                    exec_ = new ExecInstanceSwitchBlock(((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_, en_.getOffset().getOffsetTrim());
                } else if (((SwitchBlock) en_).isEnumTest()) {
                    exec_ = new ExecEnumSwitchBlock(((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_, en_.getOffset().getOffsetTrim());
                } else {
                    exec_ = new ExecStdSwitchBlock(((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_, en_.getOffset().getOffsetTrim());
                }
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                if (!emp_) {
                    blockToWrite_ = exec_;
                }
            } else if (en_ instanceof TryEval) {
                ExecTryEval exec_ = new ExecTryEval(((TryEval) en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Throwing) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((Throwing) en_).getRoot(), _coverage, _forwards);
                ExecThrowing exec_ = new ExecThrowing(((Throwing)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof UnclassedBracedBlock) {
                ExecUnclassedBracedBlock exec_ = new ExecUnclassedBracedBlock(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ != _from) {
                visit_ = false;
            }
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                BracedBlock par_;
                par_ = en_.getParent();
                _page.setCurrentBlock(par_);
                _page.setCurrentAnaBlock(par_);
                if (par_ == _from) {
                    return;
                }
                blockToWrite_ = blockToWrite_.getParent();
                en_ = par_;
            }
        }
    }

    private static CustList<ExecOperationNode> getExecutableNodes(AnalyzedPageEl _page, OperationNode root_, Coverage _coverage, Forwards _forwards) {
        Block bl_ = _page.getCurrentBlock();
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        OperationNode current_ = root_;
        ExecOperationNode exp_ = createExecOperationNode(current_, _forwards);
        setImplicits(exp_, current_, _forwards);
        _coverage.putBlockOperation(_page, bl_, current_,exp_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof ExecMethodOperation && op_ != null) {
                ExecOperationNode loc_ = createExecOperationNode(op_, _forwards);
                setImplicits(loc_, op_, _forwards);
                _coverage.putBlockOperation(_page, bl_, op_,loc_);
                ((ExecMethodOperation)exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (true) {
                if (exp_ instanceof ExecAffectationOperation) {
                    ((ExecAffectationOperation)exp_).setup();
                }
                if (exp_ instanceof ExecSemiAffectationOperation) {
                    ((ExecSemiAffectationOperation)exp_).setup();
                }
                if (exp_ instanceof ExecCompoundAffectationOperation) {
                    ((ExecCompoundAffectationOperation)exp_).setup();
                }
                out_.add(exp_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    ExecOperationNode loc_ = createExecOperationNode(op_, _forwards);
                    setImplicits(loc_, op_, _forwards);
                    _coverage.putBlockOperation(_page, bl_, op_,loc_);
                    ExecMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof AbstractDotOperation && loc_ instanceof ExecPossibleIntermediateDotted) {
                        exp_.setSiblingSet((ExecPossibleIntermediateDotted) loc_);
                    }
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                ExecMethodOperation par_ = exp_.getParent();
                if (op_ == root_) {
                    if (par_ instanceof ExecAffectationOperation) {
                        ((ExecAffectationOperation)par_).setup();
                    }
                    if (par_ instanceof ExecSemiAffectationOperation) {
                        ((ExecSemiAffectationOperation)par_).setup();
                    }
                    if (par_ instanceof ExecCompoundAffectationOperation) {
                        ((ExecCompoundAffectationOperation)par_).setup();
                    }
                    out_.add(par_);
                    current_ = null;
                    break;
                }
                current_ = op_;
                exp_ = par_;
            }
        }
        return out_;
    }

    private static void setImplicits(ExecOperationNode _ex, OperationNode _ana, Forwards _forwards){
        AnaClassArgumentMatching ana_ = _ana.getResultClass();
        ImplicitMethods implicits_ = _ex.getImplicits();
        ImplicitMethods implicitsTest_ = _ex.getImplicitsTest();
        setImplicits(ana_, implicits_, implicitsTest_, _forwards);
    }

    public static void setImplicits(AnaClassArgumentMatching _ana, ImplicitMethods _implicitsOp, ImplicitMethods _implicitsTestOp, Forwards _forwards) {
        CustList<ClassMethodId> implicits_ = _ana.getImplicits();
        String owner_ = "";
        ExecNamedFunctionBlock conv_ = null;
        if (!implicits_.isEmpty()) {
            owner_ = implicits_.first().getClassName();
            conv_ = fetchFunction(_ana.getRootNumber(),_ana.getMemberNumber(), _forwards);
        }
        if (conv_ != null) {
            ExecRootBlock classBody_ = fetchType(_ana.getRootNumber(), _forwards);
            _implicitsOp.getConverter().add(conv_);
            _implicitsOp.setOwnerClass(owner_);
            _implicitsOp.setRootBlock(classBody_);
        }
        CustList<ClassMethodId> implicitsTest_ = _ana.getImplicitsTest();
        String ownerTest_ = "";
        ExecNamedFunctionBlock convTest_ = null;
        if (!implicitsTest_.isEmpty()) {
            ownerTest_ = implicitsTest_.first().getClassName();
            convTest_ = fetchFunction(_ana.getRootNumberTest(),_ana.getMemberNumberTest(), _forwards);
        }
        if (convTest_ != null) {
            ExecRootBlock classBody_ = fetchType(_ana.getRootNumberTest(), _forwards);
            _implicitsTestOp.getConverter().add(convTest_);
            _implicitsTestOp.setOwnerClass(ownerTest_);
            _implicitsTestOp.setRootBlock(classBody_);
        }
    }

    public static ImplicitMethods fetchImplicits(ClassMethodId _clMet, int _root, int _member, Forwards _forwards) {
        ExecNamedFunctionBlock conv_ = null;
        String converterClass = "";
        if (_clMet != null) {
            converterClass = _clMet.getClassName();
            conv_ = fetchFunction(_root,_member, _forwards);
        }
        if (conv_ != null) {
            ImplicitMethods converter = new ImplicitMethods();
            ExecRootBlock classBody_ = fetchType(_root, _forwards);
            converter.getConverter().add(conv_);
            converter.setOwnerClass(converterClass);
            converter.setRootBlock(classBody_);
            return converter;
        }
        return null;
    }

    public static ExecRootBlock fetchType(int _nbRoot, Forwards _forwards) {
        if (_forwards.getMapMembers().getKeys().isValidIndex(_nbRoot)) {
            return _forwards.getMapMembers().getValue(_nbRoot).getRootBlock();
        }
        return null;
    }

    public static ExecInfoBlock fetchField(int _rootNumber, int _memberNumber, Forwards _forwards) {
        if (_forwards.getMapMembers().getKeys().isValidIndex(_rootNumber)) {
            if (_forwards.getMapMembers().getValue(_rootNumber).getAllFields().getKeys().isValidIndex(_memberNumber)) {
                return _forwards.getMapMembers().getValue(_rootNumber).getAllFields().getValue(_memberNumber);
            }
        }
        return null;
    }

    public static MethodAccessKind getKind(ClassMethodId _cl) {
        if (_cl == null) {
            return MethodAccessKind.STATIC;
        }
        return _cl.getConstraints().getKind();
    }

    public static String getType(ClassMethodId _cl) {
        if (_cl == null) {
            return "";
        }
        return _cl.getClassName();
    }

    public static ExecNamedFunctionBlock fetchFunctionOp(int _rootNumber, int _memberNumber, Forwards _forwards) {
        return fetchFunction(_rootNumber,_memberNumber,_memberNumber, _forwards);
    }

    public static ExecNamedFunctionBlock fetchFunction(int _rootNumber, int _memberNumber, int _operatorNumber, Forwards _forwards) {
        if (_forwards.getMapMembers().getKeys().isValidIndex(_rootNumber)) {
            if (_forwards.getMapMembers().getValue(_rootNumber).getAllNamed().getKeys().isValidIndex(_memberNumber)) {
                return _forwards.getMapMembers().getValue(_rootNumber).getAllNamed().getValue(_memberNumber);
            }
            return null;
        }
        if (_forwards.getMapOperators().getKeys().isValidIndex(_operatorNumber)) {
            return _forwards.getMapOperators().getValue(_operatorNumber);
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchFunction(AbstractCallFctOperation _l, Forwards _forwards) {
        return fetchFunction(_l.getRootNumber(),_l.getMemberNumber(), _forwards);
    }

    public static ExecNamedFunctionBlock fetchFunction(int _nbRoot, int _nbMember, Forwards _forwards) {
        if (_forwards.getMapMembers().getKeys().isValidIndex(_nbRoot)) {
            if (_forwards.getMapMembers().getValue(_nbRoot).getAllNamed().getKeys().isValidIndex(_nbMember)) {
                return _forwards.getMapMembers().getValue(_nbRoot).getAllNamed().getValue(_nbMember);
            }
        }
        return null;
    }

    private static ExecOperationNode createExecOperationNode(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof StaticInitOperation) {
            StaticInitOperation c_ = (StaticInitOperation) _anaNode;
            return new ExecStaticInitOperation(new ExecOperationContent(c_.getContent()), c_.isPossibleInitClass());
        }
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new ExecConstLeafOperation(false, new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof AnnotationInstanceOperation) {
            AnnotationInstanceOperation n_ = (AnnotationInstanceOperation) _anaNode;
            return new ExecAnnotationInstanceOperation(fetchType(n_.getRootNumber(), _forwards), new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInstancingAnnotContent(n_.getInstancingAnnotContent()));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            if (f_.isClonedMethod()) {
                return new ExecCloneOperation(new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), f_.getCallFctContent().getMethodName());
            }
        }
        if (_anaNode instanceof InvokingOperation && _anaNode instanceof AbstractCallFctOperation) {
            InvokingOperation i_ = (InvokingOperation) _anaNode;
            AbstractCallFctOperation a_ = (AbstractCallFctOperation) _anaNode;
            if (a_.getStandardMethod() != null) {
                return new ExecStdFctOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStdFctContent(a_.getCallFctContent(), a_.isStaticMethod()));
            }
            ExecRootBlock ex_ = fetchType(a_.getRootNumber(), _forwards);
            if (ex_ instanceof ExecAnnotationBlock) {
                return new ExecAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(a_.getCallFctContent()));
            }
            if (a_.isTrueFalse()) {
                return new ExecExplicitOperation(
                        fetchFunction(a_, _forwards),
                        fetchType(a_.getRootNumber(), _forwards), new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent()));
            }
            if (a_.isStaticMethod()) {
                ExecNamedFunctionBlock fct_ = fetchFunction(a_, _forwards);
                return new ExecStaticFctOperation(fct_,ex_, new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctContent(a_.getCallFctContent()));
            }
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor n_ = (InterfaceFctConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _forwards), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _forwards), n_.getClassName());
        }
        if (_anaNode instanceof InterfaceInvokingConstructor) {
            InterfaceInvokingConstructor n_ = (InterfaceInvokingConstructor) _anaNode;
            return new ExecInterfaceInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _forwards), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _forwards));
        }
        if (_anaNode instanceof CurrentInvokingConstructor) {
            CurrentInvokingConstructor n_ = (CurrentInvokingConstructor) _anaNode;
            return new ExecCurrentInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _forwards), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _forwards));
        }
        if (_anaNode instanceof SuperInvokingConstructor) {
            SuperInvokingConstructor n_ = (SuperInvokingConstructor) _anaNode;
            return new ExecSuperInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _forwards), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _forwards));
        }
        if (_anaNode instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _anaNode;
            return new ExecCallDynMethodOperation(new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), c_.getFctName());
        }
        if (_anaNode instanceof InferArrayInstancing) {
            InferArrayInstancing i_ = (InferArrayInstancing) _anaNode;
            return new ExecArrayElementOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(i_.getArrayInstancingContent()));
        }
        if (_anaNode instanceof ElementArrayInstancing) {
            ElementArrayInstancing e_ = (ElementArrayInstancing) _anaNode;
            return new ExecArrayElementOperation(new ExecOperationContent(e_.getContent()), e_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(e_.getArrayInstancingContent()));
        }
        if (_anaNode instanceof DimensionArrayInstancing) {
            DimensionArrayInstancing d_ = (DimensionArrayInstancing) _anaNode;
            return new ExecDimensionArrayInstancing(new ExecOperationContent(d_.getContent()), d_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(d_.getArrayInstancingContent()), d_.getCountArrayDims());
        }
        if (_anaNode instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _anaNode;
            ExecNamedFunctionBlock ctor_ = fetchFunctionOp(s_.getRootNumber(), s_.getMemberNumber(), _forwards);
            ExecRootBlock rootBlock_ = fetchType(s_.getRootNumber(), _forwards);
            if (rootBlock_ != null) {
                return new ExecStandardInstancingOperation(rootBlock_,ctor_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), new ExecInstancingStdContent(s_.getInstancingStdContent()));
            }
            return new ExecDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()));
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            return new ExecAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), _forwards.getMapMembers().getValue(s_.getBlock().getNumberAll()).getRootBlock(), fetchFunctionOp(s_.getRootNumber(), s_.getMemberNumber(), _forwards));
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            ExecRootBlock ex_ = fetchType(a_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock get_ = fetchFunction(a_.getRootNumber(), a_.getMemberNumber(), _forwards);
            ExecNamedFunctionBlock set_ = fetchFunction(a_.getRootNumber(), a_.getMemberNumberSet(), _forwards);
            if (a_.getCallFctContent().getClassMethodId() != null) {
                return new ExecCustArrOperation(get_,set_,ex_, new ExecOperationContent(a_.getContent()), a_.isIntermediateDottedOperation(), new ExecArrContent(a_.getArrContent()), new ExecInstFctContent(a_.getCallFctContent(), a_.getAnc(), a_.isStaticChoiceMethod()));
            }
            return new ExecArrOperation(new ExecOperationContent(a_.getContent()), a_.isIntermediateDottedOperation(), new ExecArrContent(a_.getArrContent()));
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation d_ = (IdOperation) _anaNode;
            if (d_.isStandard()) {
                return new ExecIdOperation(new ExecOperationContent(d_.getContent()));
            }
            return new ExecMultIdOperation(new ExecOperationContent(d_.getContent()));
        }
        if (_anaNode instanceof EnumValueOfOperation) {
            EnumValueOfOperation d_ = (EnumValueOfOperation) _anaNode;
            return new ExecEnumValueOfOperation(new ExecOperationContent(d_.getContent()), new ExecValuesContent(d_.getValuesContent(), _forwards));
        }
        if (_anaNode instanceof ValuesOperation) {
            ValuesOperation d_ = (ValuesOperation) _anaNode;
            return new ExecValuesOperation(new ExecOperationContent(d_.getContent()), new ExecValuesContent(d_.getValuesContent(), _forwards));
        }
        if (_anaNode instanceof AbstractTernaryOperation) {
            AbstractTernaryOperation t_ = (AbstractTernaryOperation) _anaNode;
            return new ExecTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal());
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            ExecRootBlock ex_ = fetchType(c_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock fct_ = fetchFunction(c_, _forwards);
            return new ExecChoiceFctOperation(fct_,ex_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true));
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecRootBlock ex_ = fetchType(s_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock fct_ = fetchFunction(s_, _forwards);
            return new ExecSuperFctOperation(fct_,ex_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecNamedFunctionBlock fct_ = fetchFunction(f_, _forwards);
            ExecRootBlock ex_ = fetchType(f_.getRootNumber(), _forwards);
            return new ExecFctOperation(fct_,ex_, new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), new ExecInstFctContent(f_.getCallFctContent(), f_.getAnc(), f_.isStaticChoiceMethod()));
        }
        if (_anaNode instanceof NamedArgumentOperation) {
            NamedArgumentOperation f_ = (NamedArgumentOperation) _anaNode;
            return new ExecNamedArgumentOperation(new ExecOperationContent(f_.getContent()), new ExecNamedContent(f_.getNamedContent()));
        }
        if (_anaNode instanceof FirstOptOperation) {
            FirstOptOperation f_ = (FirstOptOperation) _anaNode;
            return new ExecFirstOptOperation(new ExecOperationContent(f_.getContent()), f_.getOffset());
        }
        if (_anaNode instanceof StaticAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new ExecConstLeafOperation(false, new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof StaticCallAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new ExecConstLeafOperation(false, new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof VarargOperation) {
            VarargOperation f_ = (VarargOperation) _anaNode;
            return new ExecConstLeafOperation(true, new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof DefaultValueOperation) {
            DefaultValueOperation f_ = (DefaultValueOperation) _anaNode;
            return new ExecDefaultValueOperation(new ExecOperationContent(f_.getContent()), f_.getClassName());
        }
        if (_anaNode instanceof DefaultOperation) {
            DefaultOperation f_ = (DefaultOperation) _anaNode;
            return new ExecDefaultOperation(new ExecOperationContent(f_.getContent()), f_.getOffset());
        }
        if (_anaNode instanceof IdFctOperation) {
            IdFctOperation f_ = (IdFctOperation) _anaNode;
            return new ExecConstLeafOperation(true, new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof AnonymousLambdaOperation) {
            AnonymousLambdaOperation s_ = (AnonymousLambdaOperation) _anaNode;

            AnonymousFunctionBlock method_ = s_.getBlock();
            ExecAnonymousFunctionBlock r_ = _forwards.getMapAnonLambda().getValue(method_.getNumberLambda());
            return new ExecAnonymousLambdaOperation(new ExecOperationContent(s_.getContent()), new ExecLambdaCommonContent(s_.getLambdaCommonContent()), r_, new ExecLambdaAnoContent(s_.getLambdaAnoContent(), _forwards));
        }
        if (_anaNode instanceof LambdaOperation) {
            LambdaOperation f_ = (LambdaOperation) _anaNode;
            if (f_.getStandardMethod() != null) {
                return new ExecStdMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), f_.getMethod(), f_.getStandardMethod());
            }
            if (f_.getMethod() == null && f_.getRealId() == null) {
                return new ExecFieldLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaFieldContent(f_.getFieldId(), f_.getLambdaFieldContent(), f_.getLambdaMemberNumberContent(), _forwards));
            }
            if (f_.getMethod() == null) {
                return new ExecConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaConstructorContent(f_.getRealId(),f_.getLambdaMemberNumberContent(), _forwards));
            }
            return new ExecMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaMethodContent(f_.getMethod(), f_.getLambdaMethodContent(), f_.getLambdaMemberNumberContent(), _forwards));
        }
        if (_anaNode instanceof StaticInfoOperation) {
            StaticInfoOperation f_ = (StaticInfoOperation) _anaNode;
            return new ExecStaticInfoOperation(new ExecOperationContent(f_.getContent()), f_.getClassName());
        }
        if (_anaNode instanceof ThisOperation) {
            ThisOperation f_ = (ThisOperation) _anaNode;
            return new ExecThisOperation(new ExecOperationContent(f_.getContent()), new ExecThisContent(f_.getThisContent()));
        }
        if (_anaNode instanceof ParentInstanceOperation) {
            ParentInstanceOperation f_ = (ParentInstanceOperation) _anaNode;
            return new ExecParentInstanceOperation(new ExecOperationContent(f_.getContent()), new ExecParentInstanceContent(f_.getParentInstanceContent()));
        }
        if (_anaNode instanceof ForwardOperation) {
            ForwardOperation f_ = (ForwardOperation) _anaNode;
            return new ExecForwardOperation(new ExecOperationContent(f_.getContent()), f_.isIntermediate());
        }
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation s_ = (SettableAbstractFieldOperation) _anaNode;
            return new ExecSettableFieldOperation(fetchType(s_.getRootNumber(), _forwards), new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()), new ExecSettableOperationContent(s_.getSettableFieldContent()));
        }
        if (_anaNode instanceof ArrayFieldOperation) {
            ArrayFieldOperation s_ = (ArrayFieldOperation) _anaNode;
            return new ExecArrayFieldOperation(new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()));
        }
        if (_anaNode instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation m_ = (MutableLoopVariableOperation) _anaNode;
            return new ExecStdVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation m_ = (VariableOperation) _anaNode;
            return new ExecStdVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof FinalVariableOperation) {
            FinalVariableOperation m_ = (FinalVariableOperation) _anaNode;
            if (m_.getType() == ConstType.LOOP_INDEX) {
                return new ExecFinalVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
            }
            return new ExecStdVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new ExecDotOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof SafeDotOperation) {
            SafeDotOperation m_ = (SafeDotOperation) _anaNode;
            return new ExecSafeDotOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation m_ = (ExplicitOperatorOperation) _anaNode;
            return new ExecExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getCallFctContent()), m_.getOffsetOper(), fetchFunctionOp(m_.getRootNumber(), m_.getMemberNumber(), _forwards), fetchType(m_.getRootNumber(), _forwards));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            return new ExecSemiAffectationOperation(new ExecOperationContent(m_.getContent()), new ExecStaticPostEltContent(m_.getClassMethodId(), m_.isPost()), new ExecOperatorContent(m_.getOperatorContent()), fetchFunctionOp(m_.getRootNumber(), m_.getMemberNumber(), _forwards), fetchType(m_.getRootNumber(), _forwards), fetchImplicits(m_.getConverterFrom(), m_.getRootNumberFrom(), m_.getMemberNumberFrom(), _forwards), fetchImplicits(m_.getConverterTo(), m_.getRootNumberTo(), m_.getMemberNumberTo(), _forwards));
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (n_.getClassMethodId() != null) {
                return new ExecCustNumericOperation(fetchFunctionOp(n_.getRootNumber(),n_.getMemberNumber(), _forwards),fetchType(n_.getRootNumber(), _forwards), new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), getKind(n_.getClassMethodId()), getType(n_.getClassMethodId()));
            }
        }
        if (_anaNode instanceof UnaryBooleanOperation) {
            UnaryBooleanOperation m_ = (UnaryBooleanOperation) _anaNode;
            return new ExecUnaryBooleanOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof UnaryBinOperation) {
            UnaryBinOperation m_ = (UnaryBinOperation) _anaNode;
            return new ExecUnaryBinOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof UnaryOperation) {
            UnaryOperation m_ = (UnaryOperation) _anaNode;
            return new ExecUnaryOperation(new ExecOperationContent(m_.getContent()), m_.getOper());
        }
        if (_anaNode instanceof CastOperation) {
            CastOperation m_ = (CastOperation) _anaNode;
            return new ExecCastOperation(new ExecOperationContent(m_.getContent()), new ExecTypeCheckContent(m_.getTypeCheckContent()));
        }
        if (_anaNode instanceof ExplicitOperation) {
            ExplicitOperation m_ = (ExplicitOperation) _anaNode;
            return new ExecExplicitOperation(
                    fetchFunction(m_.getRootNumber(),m_.getMemberNumber(), _forwards),
                    fetchType(m_.getRootNumber(), _forwards), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return new ExecImplicitOperation(
                    fetchFunction(m_.getRootNumber(),m_.getMemberNumber(), _forwards),
                    fetchType(m_.getRootNumber(), _forwards), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
        }
        if (_anaNode instanceof MultOperation) {
            MultOperation m_ = (MultOperation) _anaNode;
            return new ExecMultOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof AddOperation) {
            AddOperation m_ = (AddOperation) _anaNode;
            if (m_.isCatString()) {
                return new ExecCatOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset());
            }
            return new ExecAddOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof ShiftLeftOperation) {
            ShiftLeftOperation m_ = (ShiftLeftOperation) _anaNode;
            return new ExecShiftLeftOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof ShiftRightOperation) {
            ShiftRightOperation m_ = (ShiftRightOperation) _anaNode;
            return new ExecShiftRightOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof BitShiftLeftOperation) {
            BitShiftLeftOperation m_ = (BitShiftLeftOperation) _anaNode;
            return new ExecBitShiftLeftOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof BitShiftRightOperation) {
            BitShiftRightOperation m_ = (BitShiftRightOperation) _anaNode;
            return new ExecBitShiftRightOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof RotateLeftOperation) {
            RotateLeftOperation m_ = (RotateLeftOperation) _anaNode;
            return new ExecRotateLeftOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof RotateRightOperation) {
            RotateRightOperation m_ = (RotateRightOperation) _anaNode;
            return new ExecRotateRightOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof CmpOperation) {
            CmpOperation c_ = (CmpOperation) _anaNode;
            if (!c_.isStringCompare()) {
                return new ExecNbCmpOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()));
            }
            return new ExecStrCmpOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()));
        }
        if (_anaNode instanceof InstanceOfOperation) {
            InstanceOfOperation c_ = (InstanceOfOperation) _anaNode;
            return new ExecInstanceOfOperation(new ExecOperationContent(c_.getContent()), new ExecTypeCheckContent(c_.getTypeCheckContent()));
        }
        if (_anaNode instanceof EqOperation) {
            EqOperation c_ = (EqOperation) _anaNode;
            return new ExecEqOperation(new ExecOperationContent(c_.getContent()), c_.getOper());
        }
        if (_anaNode instanceof BitAndOperation) {
            BitAndOperation c_ = (BitAndOperation) _anaNode;
            return new ExecBitAndOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.getOp());
        }
        if (_anaNode instanceof BitOrOperation) {
            BitOrOperation c_ = (BitOrOperation) _anaNode;
            return new ExecBitOrOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.getOp());
        }
        if (_anaNode instanceof BitXorOperation) {
            BitXorOperation c_ = (BitXorOperation) _anaNode;
            return new ExecBitXorOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.getOp());
        }
        if (_anaNode instanceof AndOperation) {
            AndOperation c_ = (AndOperation) _anaNode;
            return new ExecAndOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getClassMethodId()), fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _forwards), fetchType(c_.getRootNumber(), _forwards), fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _forwards));
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            return new ExecOrOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getClassMethodId()), fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _forwards), fetchType(c_.getRootNumber(), _forwards), fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _forwards));
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation c_ = (NullSafeOperation) _anaNode;
            return new ExecNullSafeOperation(new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof AssocationOperation) {
            AssocationOperation c_ = (AssocationOperation) _anaNode;
            return new ExecAssocationOperation(new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _anaNode;
            return new ExecCompoundAffectationOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), new ExecStaticEltContent(c_.getClassMethodId()), fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _forwards), fetchType(c_.getRootNumber(), _forwards), fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _forwards));
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new ExecAffectationOperation(new ExecOperationContent(a_.getContent()), a_.getOpOffset());
        }
        return new ExecDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
    }

    private static void updateExec(ExecRootBlock _ex, RootBlock _root){
        CustList<ExecRootBlock> pars_ = new CustList<ExecRootBlock>();
        ExecRootBlock c_ = _ex;
        boolean add_ = true;
        while (c_ != null) {
            if (add_) {
                pars_.add(c_);
            }
            if (c_.withoutInstance()) {
                add_ = false;
            }
            c_ = c_.getParentType();
        }
        ExecRootBlockContent rootBlockContent_ = _ex.getRootBlockContent();
        rootBlockContent_.setSelfAndParentTypes(pars_.getReverse());
        updateExec(rootBlockContent_,_root);
    }

    private static void updateExec(ExecRootBlockContent _ex, RootBlock _root){
        _ex.setImportedDirectSuperClass(_root.getImportedDirectGenericSuperClass());
        _ex.setImportedDirectSuperInterfaces(_root.getImportedDirectSuperInterfaces());
        _ex.update(_root.getRootBlockContent());
        _ex.setBoundsAll(_root.getBoundAll());
        _ex.setFullName(_root.getFullName());
        _ex.setGenericString(_root.getGenericString());
        _ex.setWildCardString(_root.getWildCardString());
        _ex.setTypeVarCounts(_root.getTypeVarCounts());
    }

    private static void fwd(AnnotationMethodBlock _ana, ExecAnnotationMethodBlock _exec, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        OperationNode root_ = _ana.getRoot();
        if (root_ == null) {
            _exec.setOpValue(new CustList<ExecOperationNode>());
            return;
        }
        _coverage.putBlockOperationsField(_page, _ana);
        CustList<ExecOperationNode> ops_ = getExecutableNodes(_page, root_, _coverage, _forwards);
        _exec.setOpValue(ops_);
    }

    private static void validateIds(RootBlock _key, IdMap<RootBlock, Members> _mapMembers) {
        Members mem_ = _mapMembers.getVal(_key);
        for (EntryCust<OverridableBlock,ExecOverridableBlock> e: mem_.getAllMethods().entryList()) {
            e.getValue().buildImportedTypes(e.getKey().getImportedReturnType(), e.getKey().getImportedParametersTypes());
            String returnTypeGet_ = e.getKey().getReturnTypeGet();
            if (!returnTypeGet_.isEmpty()) {
                e.getValue().setImportedReturnType(returnTypeGet_);
            }
        }
        for (EntryCust<ConstructorBlock,ExecConstructorBlock> e: mem_.getAllCtors().entryList()) {
            e.getValue().buildImportedTypes(e.getKey().getImportedReturnType(), e.getKey().getImportedParametersTypes());
        }
        for (EntryCust<AnnotationMethodBlock,ExecAnnotationMethodBlock> e: mem_.getAllAnnotMethods().entryList()) {
            AnnotationMethodBlock _key1 = e.getKey();
            e.getValue().setImportedReturnType(_key1.getImportedReturnType());
            e.getValue().getImportedParametersTypes().addAllElts(_key1.getImportedParametersTypes());
        }
        for (EntryCust<InnerElementBlock, ExecInnerElementBlock> e: mem_.getAllInnerElementFields().entryList()) {
            buildImportedTypes(e.getValue(),e.getKey());
        }
        for (EntryCust<ElementBlock, ExecElementBlock> e: mem_.getAllSimpleElementFields().entryList()) {
            buildImportedTypes(e.getValue(),e.getKey());
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> e: mem_.getAllExplicitFields().entryList()) {
            buildImportedTypes(e.getValue(),e.getKey());
        }
    }

    private static void buildImportedTypes(ExecFieldBlock _field, InfoBlock _key) {
        _field.setImportedClassName(_key.getImportedClassName());
        _field.getFieldName().addAllElts(_key.getFieldName());
    }

    private static void buildImportedTypes(ExecInnerElementBlock _ex, InfoBlock _key) {
        _ex.setImportedClassName(_key.getImportedClassName());
        _ex.setRealImportedClassName(_key.getRealImportedClassName());
    }

    private static void buildImportedTypes(ExecElementBlock _ex, InfoBlock _key) {
        _ex.setImportedClassName(_key.getImportedClassName());
    }

    private static void fwdInstancingStep(ConstructorBlock _ana, ExecConstructorBlock _exec) {
        _exec.setImplicitCallSuper(_ana.implicitConstr());
    }

    private static void fwdExpressionLanguageReadOnly(InnerElementBlock _ana, ExecInnerElementBlock _exec, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        _exec.setTrOffset(_ana.getTrOffset());
        _coverage.putBlockOperations(_exec, _ana);
        _coverage.putBlockOperations(_ana);
        _exec.setOpValue(getExecutableNodes(_page, _ana.getRoot(), _coverage, _forwards));
    }

    private static void fwdExpressionLanguageReadOnly(ElementBlock _ana, ExecInnerTypeOrElement _exec, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        _exec.setTrOffset(_ana.getTrOffset());
        _coverage.putBlockOperations((ExecBlock) _exec, _ana);
        _coverage.putBlockOperations(_ana);
        _exec.setOpValue(getExecutableNodes(_page, _ana.getRoot(), _coverage, _forwards));
    }

    private static void fwdAnnotationsParameters(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ann, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<CustList<ExecOperationNode>>> ops_ = new CustList<CustList<CustList<ExecOperationNode>>>();
        for (CustList<OperationNode> l: _ana.getRootsList()) {
            CustList<CustList<ExecOperationNode>> annotation_;
            annotation_ = new CustList<CustList<ExecOperationNode>>();
            for (OperationNode r: l) {
                annotation_.add(getExecutableNodes(_page, r, _coverage, _forwards));
            }
            ops_.add(annotation_);
        }
        _ann.getAnnotationsOpsParams().addAllElts(ops_);
    }

    private static void fwdAnnotations(ElementBlock _ana, ExecElementBlock _ann, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(_page, r, _coverage, _forwards));
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(FieldBlock _ana, ExecFieldBlock _ann, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(_page, r, _coverage, _forwards));
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdExpressionLanguageReadOnly(FieldBlock _ana, ExecFieldBlock _exec, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        processPutCoverage(_ana, _exec, _coverage);
        _exec.setOpValue(getExecutableNodes(_page, _ana.getRoot(), _coverage, _forwards));
    }

    private static void processPutCoverage(FieldBlock _ana, ExecFieldBlock _exec, Coverage _coverage) {
        _coverage.putBlockOperations(_exec, _ana);
        _coverage.putBlockOperations(_ana);
    }

    private static void fwdAnnotations(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ex, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(_page, r, _coverage, _forwards));
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(RootBlock _ana, ExecRootBlock _ann, AnalyzedPageEl _page, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(_page, r, _coverage, _forwards));
        }
        _ann.getAnnotationsOps().clear();
        _ann.getAnnotationsOps().addAllElts(ops_);
    }
}
