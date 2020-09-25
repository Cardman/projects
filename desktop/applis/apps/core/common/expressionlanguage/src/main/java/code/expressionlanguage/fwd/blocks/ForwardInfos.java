package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.Argument;
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
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class ForwardInfos {
    private ForwardInfos() {
    }
    public static void generalForward(AnalyzedPageEl _page) {
        StringMap<ExecFileBlock> files_ = new StringMap<ExecFileBlock>();
        for (EntryCust<String, FileBlock> f: _page.getFilesBodies().entryList()) {
            String file_ = f.getKey();
            FileBlock content_ = f.getValue();
            ExecFileBlock exFile_ = new ExecFileBlock(content_);
            _page.getCoverage().putFile(content_);
            _page.getErrors().putFile(content_, _page);
            files_.addEntry(file_,exFile_);
        }
        for (EntryCust<String,FileBlock> e: _page.getFilesBodies().entryList()) {
            FileBlock fileBlock_ = e.getValue();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecFile(fileBlock_,exFile_,_page);
        }
        for (AnonymousInstancingOperation e: _page.getAnonymousList()) {
            AnonymousTypeBlock block_ = e.getBlock();
            RootBlock parentType_ = block_.getParentType();
            if (parentType_ == null) {
                continue;
            }
            FileBlock fileBlock_ = block_.getFile();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecType(exFile_, block_,_page);
        }
        for (AnonymousLambdaOperation f: _page.getAllAnonymousLambda()) {
            AnonymousFunctionBlock e = f.getBlock();
            FileBlock fileBlock_ = e.getFile();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecType(exFile_, e,_page);
        }
        innerFetchExecEnd(_page);
        StringMap<PolymorphMethod> toStringMethodsToCallBodies_ = _page.getToStringMethodsToCallBodies();
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
            ClassMethodIdReturn resDyn_ = e.getValue();
            ExecRootBlock ex_ = fetchType(resDyn_.getRootNumber(), _page);
            ExecNamedFunctionBlock fct_ = fetchFunction(resDyn_.getRootNumber(),resDyn_.getMemberNumber(), _page);
            String fullName_ = e.getKey().getFullName();
            toStringMethodsToCallBodies_.addEntry(fullName_,new PolymorphMethod(ex_,fct_));
            _page.getToStringOwners().add(fullName_);
            _page.getCoverage().putToStringOwner(fullName_);
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
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
                ClassMethodIdOverride override_ = new ClassMethodIdOverride(fetchFunction(root_.getNumberAll(), o.getNameNumber(), _page));
                for (EntryCust<String,GeneStringOverridable> g: map_.entryList()) {
                    override_.put(g.getKey(),g.getValue(), _page);
                }
                redirections_.add(override_);
            }
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
            RootBlock root_ = e.getKey();
            if (!root_.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(root_,root_.getGenericString()));
                allSuperClass_.addAllElts(root_.getAllGenericSuperTypesInfo());
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    RootBlock superBl_ = s.getRootBlock();
                    for (OverridableBlock m: ClassesUtil.getMethodExecBlocks(superBl_)) {
                        if (m.isAbstractMethod()) {
                            ExecRootBlock ex_ = _page.getMapMembers().getValue(superBl_.getNumberAll()).getRootBlock();
                            ExecOverrideInfo val_ = ex_.getRedirections().getVal(fetchFunction(superBl_.getNumberAll(),m.getNameNumber(), _page), root_.getFullName());
                            if (val_ == null) {
                                ExecOverridableBlock value_ = _page.getMapMembers().getValue(superBl_.getNumberAll()).getAllMethods().getValue(m.getNameOverrideNumber());
                                e.getValue().getRootBlock().getFunctionalBodies().add(new ExecFunctionalInfo(s.getFormatted(),value_));
                            }
                        }
                    }
                }
            }
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
            e.getValue().getRootBlock().getAllSuperTypes().addAllElts(e.getKey().getAllSuperTypes());
            e.getValue().getRootBlock().getStaticInitImportedInterfaces().addAllElts(e.getKey().getStaticInitImportedInterfaces());
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
           e.getValue().getRootBlock().buildMapParamType(e.getKey());
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
            RootBlock i = e.getKey();
            CustList<AnaFormattedRootBlock> genericClasses_ = i.getAllGenericClassesInfo();
            if (i instanceof UniqueRootedBlock && genericClasses_.size() > 1) {
                e.getValue().getRootBlock().setUniqueType(fetchType(genericClasses_.get(1).getRootBlock().getNumberAll(), _page));
            }
            ConstructorBlock emptyCtor_ = i.getEmptyCtor();
            if (emptyCtor_ != null) {
                e.getValue().getRootBlock().setEmptyCtor(fetchFunction(i.getNumberAll(),emptyCtor_.getNameNumber(), _page));
            }
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
            CustList<AnaFormattedRootBlock> allGenericSuperTypes_ = e.getKey().getAllGenericSuperTypesInfo();
            CustList<ExecFormattedRootBlock> l_ = new CustList<ExecFormattedRootBlock>();
            for (AnaFormattedRootBlock s: allGenericSuperTypes_) {
                l_.add(new ExecFormattedRootBlock(_page.getMapMembers().getValue(s.getRootBlock().getNumberAll()).getRootBlock(),s.getFormatted()));
            }
            e.getValue().getRootBlock().getAllGenericSuperTypes().addAllElts(l_);
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
            ExecRootBlock type_ = e.getValue().getRootBlock();
            IdMap<RootBlock, Members> mapMembers_ = _page.getMapMembers();
            type_.validateIds(e.getKey(), mapMembers_);
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _page.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            value_.buildImportedTypes(o);
        }
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            Members mem_ = e.getValue();
            String fullName_ = c.getFullName();
            _page.getCoverage().putCalls(fullName_);
            mem_.getAllAnnotables().addEntry(c,mem_.getRootBlock());
            for (Block b: ClassesUtil.getDirectChildren(c)) {
                if (b instanceof RootBlock) {
                    ExecRootBlock val_ = _page.getMapMembers().getValue(((RootBlock) b).getNumberAll()).getRootBlock();
                    _page.getMapMembers().getValue(c.getNumberAll()).getRootBlock().getChildrenTypes().add(val_);
                    mem_.getAllAnnotables().addEntry((RootBlock) b,_page.getMapMembers().getValue(((RootBlock) b).getNumberAll()).getRootBlock());
                }
            }
            for (EntryCust<AnnotationMethodBlock, ExecAnnotationMethodBlock> f: mem_.getAllAnnotMethods().entryList()) {
                mem_.getAllAnnotables().addEntry(f.getKey(),f.getValue());
                mem_.getRootBlock().getAnnotationsFields().add(f.getValue());
            }
            for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> f: mem_.getAllElementFields().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                _page.setCurrentFct(null);
                InnerTypeOrElement method_ = f.getKey();
                _page.setCurrentBlock((Block) f.getKey());
                _page.setCurrentAnaBlock((AnalyzedBlock) f.getKey());
                ExecInnerTypeOrElement val_ = f.getValue();
                mem_.getRootBlock().getEnumElements().add(val_);
                mem_.getAllAnnotables().addEntry(method_,val_);
                method_.fwdExpressionLanguageReadOnly(val_, _page);
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
                mem_.getAllAnnotables().addEntry(method_,exp_);
                method_.fwdExpressionLanguageReadOnly(exp_, _page);
                if (!method_.isStaticField()) {
                    mem_.getRootBlock().getInstanceFields().add(exp_);
                }
            }
            for (EntryCust<InitBlock, ExecInitBlock> f: mem_.getAllInits().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                InitBlock method_ = f.getKey();
                _page.getAllFct().addEntry(method_,f.getValue());
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> f: mem_.getAllCtors().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                ConstructorBlock method_ = f.getKey();
                _page.getCoverage().putCalls(fullName_,method_);
                _page.getAllFct().addEntry(method_,f.getValue());
                mem_.getAllAnnotables().addEntry(method_,f.getValue());
                method_.fwdInstancingStep(f.getValue(), _page);
            }
            for (EntryCust<OverridableBlock, ExecOverridableBlock> f: mem_.getAllMethods().entryList()) {
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(c);
                OverridableBlock method_ =  f.getKey();
                _page.getCoverage().putCalls(fullName_,method_);
                mem_.getAllAnnotables().addEntry(method_,f.getValue());
                _page.getAllFct().addEntry(method_,f.getValue());
            }
        }
        _page.getCoverage().putCalls("");
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _page.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            _page.getCoverage().putCalls("",o);
            _page.getAllFct().addEntry(o,value_);
        }
        _page.setAnnotAnalysis(true);
        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
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
            _page.getCoverage().putBlockOperations(_page.getMapMembers().getVal(c).getRootBlock(),c);
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(c.getMappings());
            for (Block b:annotated_) {
                _page.setCurrentBlock(b);
                _page.setCurrentAnaBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    _page.setAnnotAnalysisField(true);
                    _page.setGlobalDirType(c);
                    ((AnnotationMethodBlock)b).fwd(mem_.getAllAnnotMethods().getVal(((AnnotationMethodBlock)b)), _page);
                    _page.getCoverage().putBlockOperations(mem_.getAllAnnotMethods().getVal((AnnotationMethodBlock) b),b);
                    _page.setAnnotAnalysisField(false);
                }
                if (b instanceof RootBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putBlockOperationsField(_page, b);
                    ExecAnnotableBlock val_ = mem_.getAllAnnotables().getVal((AnnotableBlock) b);
                    ((RootBlock)b).fwdAnnotations(val_, _page);
                }
                if (b instanceof NamedFunctionBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putBlockOperationsField(_page, b);
                    ExecNamedFunctionBlock val_ = mem_.getAllNamed().getVal((NamedFunctionBlock) b);
                    ((NamedFunctionBlock)b).fwdAnnotations(val_, _page);
                    ((NamedFunctionBlock)b).fwdAnnotationsParameters(val_, _page);
                }
                if (b instanceof InfoBlock) {
                    _page.setAnnotAnalysisField(false);
                    _page.setGlobalDirType(c);
                    _page.getCoverage().putBlockOperationsField(_page, b);
                    _page.getCoverage().putBlockOperations((ExecBlock) mem_.getAllFields().getVal((InfoBlock) b),b);
                    ExecAnnotableBlock val_ = mem_.getAllAnnotables().getVal((AnnotableBlock) b);
                    ((InfoBlock)b).fwdAnnotations(val_, _page);
                }
            }
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _page.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            _page.setImporting(o);
            _page.setImportingAcces(new OperatorAccessor());
            _page.setImportingTypes(o);
            _page.setCurrentBlock(o);
            _page.setCurrentAnaBlock(o);
            _page.setAnnotAnalysisField(false);
            _page.getCoverage().putBlockOperationsField(_page, o);
            ExecOperatorBlock value_ = e.getValue();
            o.fwdAnnotations(value_, _page);
            o.fwdAnnotationsParameters(value_, _page);
        }
        _page.setAnnotAnalysis(false);
        for (AnonymousLambdaOperation e: _page.getAllAnonymousLambda()) {
            AnonymousFunctionBlock method_ = e.getBlock();
            RootBlock c_ = method_.getParentType();
            _page.getCoverage().putCalls(c_.getFullName(),method_);
            ExecNamedFunctionBlock function_ = buildExecAnonymousLambdaOperation(e,_page);
            _page.getAllFct().addEntry(method_, function_);
            int numberFile_ = method_.getFile().getNumberFile();
            ExecFileBlock value_ = files_.getValue(numberFile_);
            function_.setFile(value_);
        }
        for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> e: _page.getAllFct().entryList()) {
            buildExec(_page,e.getKey(),e.getValue());
        }
        for (EntryCust<AnonymousLambdaOperation, ExecAnonymousLambdaOperation> e: _page.getMapAnonymousLambda().entryList()) {
            AnonymousLambdaOperation key_ = e.getKey();
            AnonymousFunctionBlock method_ = key_.getBlock();
            ExecMemberCallingsBlock r_ = _page.getAllFct().getVal(method_);
            e.getValue().setExecAnonymousLambdaOperation((ExecAnonymousFunctionBlock) r_, new ExecLambdaAnoContent(key_.getLambdaAnoContent(), _page));
        }
        for (EntryCust<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> e: _page.getMapAnonymous().entryList()) {
            AnonymousInstancingOperation key_ = e.getKey();
            AnonymousTypeBlock block_ = key_.getBlock();
            RootBlock parentType_ = block_.getParentType();
            if (parentType_ == null) {
                continue;
            }
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
            value_.setExecAnonymousInstancingOperation(new ExecInstancingCommonContent(key_.getInstancingCommonContent()), _page.getMapMembers().getValue(key_.getBlock().getNumberAll()).getRootBlock(), fetchFunctionOp(key_.getRootNumber(), key_.getMemberNumber(), _page));
        }

        for (EntryCust<RootBlock, Members> e: _page.getMapMembers().entryList()) {
            RootBlock root_ = e.getKey();
            Members valueMember_ = e.getValue();
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
                        value_.getAnonymous().add(_page.getMapAnonTypes().getValue(a.getNumberAnonType()));
                    }
                    for (AnonymousFunctionBlock a: ((InfoBlock)b).getAnonymousFct()) {
                        value_.getAnonymousLambda().add(_page.getMapAnonLambda().getValue(a.getNumberLambda()));
                    }
                }
            }
            ExecRootBlock value_ = e.getValue().getRootBlock();
            for (AnonymousFunctionBlock a: root_.getAnonymousRootFct()) {
                value_.getAnonymousRootLambda().add(_page.getMapAnonLambda().getValue(a.getNumberLambda()));
            }
            for (AnonymousTypeBlock a: root_.getAnonymousRoot()) {
                value_.getAnonymousRoot().add(_page.getMapAnonTypes().getValue(a.getNumberAnonType()));
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
            value_.getAnonymous().add(page_.getMapAnonTypes().getValue(a.getNumberAnonType()));
        }
        for (RootBlock a: b1_.getReserved()) {
            value_.getReserved().add(page_.getMapMembers().getValue(a.getNumberAll()).getRootBlock());
        }
    }

    public static void processAppend(ExecFileBlock _exFile, Block _outer, RootBlock _root, AnalyzedPageEl _page) {
        _page.getCoverage().putType(_root);
        String fullName_ = _root.getFullName();
        RootBlock parentType_ = _root.getParentType();
        int index_ = -1;
        if (parentType_ != null) {
            index_ = parentType_.getNumberAll();
        }
        ExecRootBlock execParentType_ = null;
        if (_page.getMapMembers().getKeys().isValidIndex(index_)) {
            execParentType_ = _page.getMapMembers().getValue(index_).getRootBlock();
        }
        _page.getMapMembers().getValue(_root.getNumberAll());
        if (_root instanceof AnonymousTypeBlock) {
            ExecAnonymousTypeBlock e_ = new ExecAnonymousTypeBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _page.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _page.getMapAnonTypes().put((AnonymousTypeBlock)_root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
        }
        if (_root instanceof ClassBlock) {
            ExecClassBlock e_ = new ExecClassBlock((ClassBlock) _root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ =_page.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof EnumBlock) {
            ExecEnumBlock e_ = new ExecEnumBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _page.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InterfaceBlock) {
            ExecInterfaceBlock e_ = new ExecInterfaceBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _page.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof AnnotationBlock) {
            ExecAnnotationBlock e_ = new ExecAnnotationBlock(_root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _page.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InnerElementBlock) {
            ExecInnerElementBlock e_ = new ExecInnerElementBlock((InnerElementBlock) _root);
            e_.setParentType(execParentType_);
            e_.setFile(_exFile);
            Members v_ = _page.getMapMembers().getValue(_root.getNumberAll());
            v_.setRootBlock(e_);
            _page.getMapInnerEltTypes().put((InnerElementBlock) _root, e_);
            _page.getClasses().getClassesBodies().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
    }

    private static void appendType(ExecFileBlock _exFile, Block _outer, RootBlock _root, ExecRootBlock e_) {
        if (_outer == _root) {
            _exFile.appendChild(e_);
        }
    }

    private static void innerFetchExecEnd(AnalyzedPageEl _page) {
        for (EntryCust<RootBlock, Members> r: _page.getMapMembers().entryList()) {
            ExecRootBlock current_ = r.getValue().getRootBlock();
            RootBlock k_ = r.getKey();
            Members mem_ = r.getValue();
            for (Block b: ClassesUtil.getDirectChildren(k_)) {
                if (b instanceof InnerElementBlock) {
                    ExecInnerElementBlock val_ = _page.getMapInnerEltTypes().getValue(((InnerElementBlock) b).getNumberInner());
                    current_.appendChild(val_);
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllElementFields().addEntry((InnerElementBlock) b,val_);
                }
                if (b instanceof ElementBlock) {
                    ExecElementBlock val_ = new ExecElementBlock((ElementBlock) b);
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllElementFields().addEntry((ElementBlock) b,val_);
                }
                if (b instanceof FieldBlock) {
                    ExecFieldBlock val_ = new ExecFieldBlock((FieldBlock) b);
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllExplicitFields().addEntry((FieldBlock) b,val_);
                }
                if (b instanceof ConstructorBlock) {
                    ExecConstructorBlock val_ = new ExecConstructorBlock((ConstructorBlock)b);
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllCtors().addEntry((ConstructorBlock) b,val_);
                    mem_.getAllNamed().addEntry((ConstructorBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof OverridableBlock) {
                    ExecOverridableBlock val_ = new ExecOverridableBlock((OverridableBlock)b);
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllMethods().addEntry((OverridableBlock) b,val_);
                    mem_.getAllNamed().addEntry((OverridableBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof AnnotationMethodBlock) {
                    ExecAnnotationMethodBlock val_ = new ExecAnnotationMethodBlock((AnnotationMethodBlock)b);
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllAnnotMethods().addEntry((AnnotationMethodBlock) b,val_);
                    mem_.getAllNamed().addEntry((AnnotationMethodBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof InstanceBlock) {
                    ExecInstanceBlock val_ = new ExecInstanceBlock(b.getOffset());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    val_.setNumber(mem_.getAllInits().size());
                    mem_.getAllInits().put((InitBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
                if (b instanceof StaticBlock) {
                    ExecStaticBlock val_ = new ExecStaticBlock(b.getOffset());
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    val_.setNumber(mem_.getAllInits().size());
                    mem_.getAllInits().put((InitBlock) b,val_);
                    mem_.getAllFct().addEntry((MemberCallingsBlock)b,val_);
                }
            }
        }
    }

    public static void processExecFile(FileBlock _anaFile, ExecFileBlock _exeFile, AnalyzedPageEl _page) {
        for (Block b: ClassesUtil.getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processExecType(_exeFile,r_,_page);
            }
            if (b instanceof OperatorBlock) {
                OperatorBlock r_ = (OperatorBlock) b;
                ExecOperatorBlock e_ = new ExecOperatorBlock(r_);
                _exeFile.appendChild(e_);
                e_.setFile(_exeFile);
                _page.getClasses().getOperators().add(e_);
                _page.getMapOperators().put(r_,e_);
                _page.getCoverage().putOperator(r_);
            }
        }
    }

    private static void processExecType(ExecFileBlock _exeFile, Block _r, AnalyzedPageEl _page) {
        Block c_ = _r;
        if (c_.getFirstChild() != null) {
            while (true) {
                if (c_ instanceof RootBlock) {
                    RootBlock cur_ = (RootBlock) c_;
                    processAppend(_exeFile,_r,cur_,_page);
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
            if (_r instanceof RootBlock) {
                processAppend(_exeFile,_r,(RootBlock) _r,_page);
            }
        }
    }

    public static ExecAnonymousFunctionBlock buildExecAnonymousLambdaOperation(AnonymousLambdaOperation _s, AnalyzedPageEl _page) {
        ExecRootBlock declaring = _page.getMapMembers().getValue(_s.getRootNumber()).getRootBlock();
        AnonymousFunctionBlock block_ = _s.getBlock();
        block_.setNumberLambda(_page.getMapAnonLambda().size());
        ExecAnonymousFunctionBlock fct_ = new ExecAnonymousFunctionBlock(block_);
        fct_.setParentType(declaring);
        _page.getMapAnonLambda().addEntry(block_,fct_);
        fct_.buildImportedTypes(block_);
        return fct_;
    }

    public static void buildExec(AnalyzedPageEl _page,MemberCallingsBlock _from,ExecMemberCallingsBlock _dest) {
        ExecBracedBlock blockToWrite_ = _dest;
        ExecFileBlock fileDest_ = _dest.getFile();
        Block firstChild_ = _from.getFirstChild();
        ExecDeclareVariable decl_ = null;
        _page.setCurrentBlock(_from);
        _page.getCoverage().putBlockOperations(_dest,_from);
        Block en_ = _from;
        if (firstChild_ == null) {
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_);
            _page.setCurrentAnaBlock(en_);
            _page.getCoverage().putBlockOperations(en_);
            Block n_ = en_.getFirstChild();
            boolean visit_ = true;
            if (en_ instanceof BreakBlock) {
                ExecBreakBlock exec_ = new ExecBreakBlock(en_.getOffset(), ((BreakBlock) en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
            } else if (en_ instanceof CaseCondition) {
                ExecBracedBlock exec_;
                BracedBlock par_ = en_.getParent();
                if (!(par_ instanceof SwitchBlock)) {
                    exec_ = new ExecNullCaseCondition(en_.getOffset(), ((CaseCondition)en_).getValueOffset());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else {
                    _page.getCoverage().putBlockOperationsSwitchs(par_,en_);
                    if (!((CaseCondition) en_).getInstanceTest().isEmpty()) {
                        if (((CaseCondition) en_).getImportedType().isEmpty()) {
                            exec_ = new ExecNullInstanceCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset());
                            exec_.setFile(fileDest_);
                            blockToWrite_.appendChild(exec_);
                            _page.getCoverage().putBlockOperations(exec_,en_);
                        } else {
                            exec_ = new ExecInstanceCaseCondition(en_.getOffset(), ((CaseCondition)en_).getVariableName(), ((CaseCondition) en_).getImportedType(), ((CaseCondition) en_).getValueOffset());
                            exec_.setFile(fileDest_);
                            blockToWrite_.appendChild(exec_);
                            _page.getCoverage().putBlockOperations(exec_,en_);
                        }
                    } else {
                        getExecutableNodes(_page, ((CaseCondition)en_).getRoot());
                        if (((CaseCondition) en_).isBuiltEnum()) {
                            if (((CaseCondition) en_).isNullCaseEnum()) {
                                exec_ = new ExecNullCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset());
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                _page.getCoverage().putBlockOperations(exec_,en_);
                            } else {
                                exec_ = new ExecEnumCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValue(), ((CaseCondition) en_).getValueOffset());
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                _page.getCoverage().putBlockOperations(exec_, en_);
                            }
                        } else {
                            Argument argument_ = ((CaseCondition) en_).getArgument();
                            if (argument_ == null) {
                                exec_ = new ExecUnclassedBracedBlock(en_.getOffset());
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                _page.getCoverage().putBlockOperations(exec_, en_);
                            } else {
                                if (!argument_.isNull()) {
                                    exec_ = new ExecStdCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset(), argument_);
                                } else {
                                    exec_ = new ExecNullCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset());
                                }
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                _page.getCoverage().putBlockOperations(exec_, en_);
                            }
                        }
                    }
                }
                blockToWrite_ = exec_;
            } else if (en_ instanceof CatchEval) {
                _page.getCoverage().putCatches(en_);
                ExecCatchEval exec_ = new ExecCatchEval(en_.getOffset(),((CatchEval)en_).getVariableName(), ((CatchEval)en_).getImportedClassName());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof IfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot());
                ExecCondition exec_ = new ExecIfCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), ((IfCondition) en_).getLabel(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperationsConditions(en_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseIfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot());
                ExecCondition exec_ = new ExecElseIfCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperationsConditions(en_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof WhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot());
                ExecCondition exec_ = new ExecWhileCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), ((WhileCondition) en_).getLabel(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperationsConditions(en_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof DoWhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(_page, ((Condition)en_).getRoot());
                ExecCondition exec_ = new ExecDoWhileCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperationsConditions(en_);
                _page.getCoverage().putBlockOperations(exec_,en_);
            } else if (en_ instanceof DoBlock) {
                ExecDoBlock exec_ = new ExecDoBlock(en_.getOffset(),((DoBlock)en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ContinueBlock) {
                ExecContinueBlock exec_ = new ExecContinueBlock(en_.getOffset(), ((ContinueBlock) en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
            } else if (en_ instanceof DeclareVariable) {
                ExecDeclareVariable exec_ = new ExecDeclareVariable(en_.getOffset(), ((DeclareVariable) en_).getImportedClassName(),((DeclareVariable)en_).getVariableNames());
                decl_ = exec_;
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
            } else if (en_ instanceof DefaultCondition) {
                BracedBlock b_ = en_.getParent();
                ExecBracedBlock exec_;
                if (!(b_ instanceof SwitchBlock)) {
                    exec_ = new ExecDefaultCondition(en_.getOffset());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else {
                    _page.getCoverage().putBlockOperationsSwitchs(b_,en_);
                    String instanceTest_ = ((DefaultCondition)en_).getInstanceTest();
                    if (instanceTest_.isEmpty()) {
                        exec_ = new ExecDefaultCondition(en_.getOffset());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        _page.getCoverage().putBlockOperations(exec_,en_);
                    } else {
                        exec_ = new ExecInstanceDefaultCondition(en_.getOffset(), ((DefaultCondition)en_).getVariableName(), instanceTest_, ((DefaultCondition)en_).getVariableOffset());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        _page.getCoverage().putBlockOperations(exec_, en_);
                    }
                }
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseCondition) {
                ExecElseCondition exec_ = new ExecElseCondition(en_.getOffset());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Line) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((Line)en_).getRoot());
                if (decl_ != null) {
                    decl_.setImportedClassName(((Line) en_).getImportedClass());
                }
                decl_ = null;
                ExecLine exec_ = new ExecLine(en_.getOffset(), ((Line) en_).getExpressionOffset(),op_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
            } else if (en_ instanceof EmptyInstruction) {
                ExecEmptyInstruction exec_ = new ExecEmptyInstruction(en_.getOffset());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
            } else if (en_ instanceof FinallyEval) {
                ExecFinallyEval exec_ = new ExecFinallyEval(en_.getOffset());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachLoop) {
                _page.getCoverage().putBlockOperationsLoops(en_);
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((ForEachLoop)en_).getRoot());
                ExecForEachLoop exec_ = new ExecForEachLoop(en_.getOffset(), ((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                        ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachTable) {
                _page.getCoverage().putBlockOperationsLoops(en_);
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((ForEachTable)en_).getRoot());
                ExecForEachTable exec_ = new ExecForEachTable(en_.getOffset(), ((ForEachTable) en_).getLabel(), ((ForEachTable)en_).getImportedClassNameFirst(),
                        ((ForEachTable)en_).getImportedClassNameSecond(),
                        ((ForEachTable)en_).getImportedClassIndexName(), ((ForEachTable)en_).getVariableNameFirst(),
                        ((ForEachTable)en_).getVariableNameSecond(), ((ForEachTable)en_).getExpressionOffset(),op_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForIterativeLoop) {
                _page.getCoverage().putBlockOperationsLoops(en_);
                CustList<ExecOperationNode> init_ = getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootInit());
                CustList<ExecOperationNode> exp_ = getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootExp());
                CustList<ExecOperationNode> step_ = getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootStep());
                ExecForIterativeLoop exec_ = new ExecForIterativeLoop(en_.getOffset(), ((ForIterativeLoop) en_).getLabel(), ((ForIterativeLoop)en_).getImportedClassName(),
                        ((ForIterativeLoop)en_).getImportedClassIndexName(), ((ForIterativeLoop)en_).getVariableName(), ((ForIterativeLoop)en_).getVariableNameOffset(), ((ForIterativeLoop)en_).getInitOffset(),
                        ((ForIterativeLoop)en_).getExpressionOffset(), ((ForIterativeLoop)en_).getStepOffset(), ((ForIterativeLoop)en_).isEq(),init_,exp_,step_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForMutableIterativeLoop) {
                CustList<ExecOperationNode> init_;
                OperationNode rInit_ = ((ForMutableIterativeLoop) en_).getRootInit();
                if (rInit_ == null) {
                    init_ = new CustList<ExecOperationNode>();
                } else {
                    init_ = getExecutableNodes(_page, rInit_);
                }
                CustList<ExecOperationNode> exp_;
                OperationNode rExp_ = ((ForMutableIterativeLoop) en_).getRootExp();
                if (rExp_ == null) {
                    exp_ = new CustList<ExecOperationNode>();
                } else {
                    exp_ = getExecutableNodes(_page, rExp_);
                }
                _page.getCoverage().putBlockOperationsConditions(en_);
                OperationNode rStep_ = ((ForMutableIterativeLoop) en_).getRootStep();
                CustList<ExecOperationNode> step_;
                if (rStep_ == null) {
                    step_ = new CustList<ExecOperationNode>();
                } else {
                    step_ = getExecutableNodes(_page, rStep_);
                }
                ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(en_.getOffset(), ((ForMutableIterativeLoop) en_).getLabel(), ((ForMutableIterativeLoop) en_).getImportedClassName(), ((ForMutableIterativeLoop) en_).getImportedClassIndexName(),
                        ((ForMutableIterativeLoop) en_).getVariableNames(), ((ForMutableIterativeLoop) en_).getInitOffset(), ((ForMutableIterativeLoop) en_).getExpressionOffset(), ((ForMutableIterativeLoop) en_).getStepOffset(),
                        init_,exp_,step_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof NullCatchEval) {
                _page.getCoverage().putCatches(en_);
                ExecNullCatchEval exec_ = new ExecNullCatchEval(en_.getOffset());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ReturnMethod) {
                OperationNode r_ = ((ReturnMethod) en_).getRoot();
                if (r_ == null) {
                    ExecReturnMethod exec_ = new ExecReturnMethod(en_.getOffset(), true, ((ReturnMethod) en_).getExpressionOffset(),null, ((ReturnMethod) en_).getReturnType());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else {
                    CustList<ExecOperationNode> op_ = getExecutableNodes(_page, r_);
                    ExecReturnMethod exec_ = new ExecReturnMethod(en_.getOffset(), false, ((ReturnMethod) en_).getExpressionOffset(),op_, ((ReturnMethod) en_).getReturnType());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
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
                _page.getCoverage().putBlockOperationsSwitchs(en_,def_);
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((SwitchBlock)en_).getRoot());
                ExecBracedBlock exec_;
                if (!((SwitchBlock) en_).getInstanceTest().isEmpty()) {
                    exec_ = new ExecInstanceSwitchBlock(en_.getOffset(), ((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_);
                } else if (((SwitchBlock) en_).isEnumTest()) {
                    exec_ = new ExecEnumSwitchBlock(en_.getOffset(), ((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_);
                } else {
                    exec_ = new ExecStdSwitchBlock(en_.getOffset(), ((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_);
                }
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                if (!emp_) {
                    blockToWrite_ = exec_;
                }
            } else if (en_ instanceof TryEval) {
                ExecTryEval exec_ = new ExecTryEval(en_.getOffset(), ((TryEval) en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Throwing) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(_page, ((Throwing) en_).getRoot());
                ExecThrowing exec_ = new ExecThrowing(en_.getOffset(), ((Throwing)en_).getExpressionOffset(),op_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
            } else if (en_ instanceof UnclassedBracedBlock) {
                ExecUnclassedBracedBlock exec_ = new ExecUnclassedBracedBlock(en_.getOffset());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _page.getCoverage().putBlockOperations(exec_,en_);
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

    public static CustList<ExecOperationNode> getExecutableNodes(AnalyzedPageEl _page, OperationNode root_) {
        Block bl_ = _page.getCurrentBlock();
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        OperationNode current_ = root_;
        ExecOperationNode exp_ = createExecOperationNode(current_, _page);
        setImplicits(exp_, _page, current_);
        _page.getCoverage().putBlockOperation(_page, bl_, current_,exp_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof ExecMethodOperation && op_ != null) {
                ExecOperationNode loc_ = createExecOperationNode(op_, _page);
                setImplicits(loc_, _page, op_);
                _page.getCoverage().putBlockOperation(_page, bl_, op_,loc_);
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
                    ExecOperationNode loc_ = createExecOperationNode(op_, _page);
                    setImplicits(loc_, _page, op_);
                    _page.getCoverage().putBlockOperation(_page, bl_, op_,loc_);
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

    public static void setImplicits(ExecOperationNode _ex, AnalyzedPageEl _page, OperationNode _ana){
        AnaClassArgumentMatching ana_ = _ana.getResultClass();
        ImplicitMethods implicits_ = _ex.getImplicits();
        ImplicitMethods implicitsTest_ = _ex.getImplicitsTest();
        setImplicits(ana_, implicits_, implicitsTest_, _page);
    }

    public static void setImplicits(AnaClassArgumentMatching _ana, ImplicitMethods _implicitsOp, ImplicitMethods _implicitsTestOp, AnalyzedPageEl _page) {
        CustList<ClassMethodId> implicits_ = _ana.getImplicits();
        String owner_ = "";
        ExecNamedFunctionBlock conv_ = null;
        if (!implicits_.isEmpty()) {
            owner_ = implicits_.first().getClassName();
            conv_ = fetchFunction(_ana.getRootNumber(),_ana.getMemberNumber(), _page);
        }
        if (conv_ != null) {
            ExecRootBlock classBody_ = fetchType(_ana.getRootNumber(), _page);
            _implicitsOp.getConverter().add(conv_);
            _implicitsOp.setOwnerClass(owner_);
            _implicitsOp.setRootBlock(classBody_);
        }
        CustList<ClassMethodId> implicitsTest_ = _ana.getImplicitsTest();
        String ownerTest_ = "";
        ExecNamedFunctionBlock convTest_ = null;
        if (!implicitsTest_.isEmpty()) {
            ownerTest_ = implicitsTest_.first().getClassName();
            convTest_ = fetchFunction(_ana.getRootNumberTest(),_ana.getMemberNumberTest(), _page);
        }
        if (convTest_ != null) {
            ExecRootBlock classBody_ = fetchType(_ana.getRootNumberTest(), _page);
            _implicitsTestOp.getConverter().add(convTest_);
            _implicitsTestOp.setOwnerClass(ownerTest_);
            _implicitsTestOp.setRootBlock(classBody_);
        }
    }

    public static ImplicitMethods fetchImplicits(ClassMethodId _clMet, int _root, int _member, AnalyzedPageEl _page) {
        ExecNamedFunctionBlock conv_ = null;
        String converterClass = "";
        if (_clMet != null) {
            converterClass = _clMet.getClassName();
            conv_ = fetchFunction(_root,_member, _page);
        }
        if (conv_ != null) {
            ImplicitMethods converter = new ImplicitMethods();
            ExecRootBlock classBody_ = fetchType(_root, _page);
            converter.getConverter().add(conv_);
            converter.setOwnerClass(converterClass);
            converter.setRootBlock(classBody_);
            return converter;
        }
        return null;
    }

    public static ExecRootBlock fetchType(int _nbRoot, AnalyzedPageEl _page) {
        if (_page.getMapMembers().getKeys().isValidIndex(_nbRoot)) {
            return _page.getMapMembers().getValue(_nbRoot).getRootBlock();
        }
        return null;
    }

    public static ExecInfoBlock fetchField(AnalyzedPageEl _page, int _rootNumber, int _memberNumber) {
        if (_page.getMapMembers().getKeys().isValidIndex(_rootNumber)) {
            if (_page.getMapMembers().getValue(_rootNumber).getAllFields().getKeys().isValidIndex(_memberNumber)) {
                return _page.getMapMembers().getValue(_rootNumber).getAllFields().getValue(_memberNumber);
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

    public static ExecNamedFunctionBlock fetchFunctionOp(int _rootNumber, int _memberNumber, AnalyzedPageEl _page) {
        return fetchFunction(_rootNumber,_memberNumber,_memberNumber, _page);
    }

    public static ExecNamedFunctionBlock fetchFunction(int _rootNumber, int _memberNumber, int _operatorNumber, AnalyzedPageEl _page) {
        if (_page.getMapMembers().getKeys().isValidIndex(_rootNumber)) {
            if (_page.getMapMembers().getValue(_rootNumber).getAllNamed().getKeys().isValidIndex(_memberNumber)) {
                return _page.getMapMembers().getValue(_rootNumber).getAllNamed().getValue(_memberNumber);
            }
            return null;
        }
        if (_page.getMapOperators().getKeys().isValidIndex(_operatorNumber)) {
            return _page.getMapOperators().getValue(_operatorNumber);
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchFunction(AbstractCallFctOperation _l, AnalyzedPageEl _page) {
        return fetchFunction(_l.getRootNumber(),_l.getMemberNumber(), _page);
    }

    public static ExecNamedFunctionBlock fetchFunction(int _nbRoot, int _nbMember, AnalyzedPageEl _page) {
        if (_page.getMapMembers().getKeys().isValidIndex(_nbRoot)) {
            if (_page.getMapMembers().getValue(_nbRoot).getAllNamed().getKeys().isValidIndex(_nbMember)) {
                return _page.getMapMembers().getValue(_nbRoot).getAllNamed().getValue(_nbMember);
            }
        }
        return null;
    }

    public static ExecOperationNode createExecOperationNode(OperationNode _anaNode, AnalyzedPageEl _page) {
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
            return new ExecAnnotationInstanceOperation(fetchType(n_.getRootNumber(), _page), new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInstancingAnnotContent(n_.getInstancingAnnotContent()));
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
            ClassMethodId classMethodId_ = a_.getClassMethodId();
            if (classMethodId_ != null) {
                if (a_.getStandardMethod() != null) {
                    return new ExecStdFctOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStdFctContent(a_.getCallFctContent(), a_.isStaticMethod()));
                }
                ExecRootBlock ex_ = fetchType(a_.getRootNumber(), _page);
                if (ex_ instanceof ExecAnnotationBlock) {
                    return new ExecAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(a_.getCallFctContent()));
                }
                if (a_.isTrueFalse()) {
                    return new ExecExplicitOperation(
                            fetchFunction(a_, _page),
                            fetchType(a_.getRootNumber(), _page), new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent()));
                }
                if (a_.isStaticMethod()) {
                    ExecNamedFunctionBlock fct_ = fetchFunction(a_, _page);
                    return new ExecStaticFctOperation(fct_,ex_, new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctContent(a_.getCallFctContent()));
                }
            }
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor n_ = (InterfaceFctConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _page), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _page), n_.getClassName());
        }
        if (_anaNode instanceof InterfaceInvokingConstructor) {
            InterfaceInvokingConstructor n_ = (InterfaceInvokingConstructor) _anaNode;
            return new ExecInterfaceInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _page), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _page));
        }
        if (_anaNode instanceof CurrentInvokingConstructor) {
            CurrentInvokingConstructor n_ = (CurrentInvokingConstructor) _anaNode;
            return new ExecCurrentInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _page), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _page));
        }
        if (_anaNode instanceof SuperInvokingConstructor) {
            SuperInvokingConstructor n_ = (SuperInvokingConstructor) _anaNode;
            return new ExecSuperInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), fetchType(n_.getRootNumber(), _page), fetchFunctionOp(n_.getRootNumber(), n_.getMemberNumber(), _page));
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
            ExecNamedFunctionBlock ctor_ = fetchFunctionOp(s_.getRootNumber(), s_.getMemberNumber(), _page);
            ExecRootBlock rootBlock_ = fetchType(s_.getRootNumber(), _page);
            if (rootBlock_ != null) {
                return new ExecStandardInstancingOperation(rootBlock_,ctor_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), new ExecInstancingStdContent(s_.getInstancingStdContent()));
            }
            return new ExecDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()));
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            ExecAnonymousInstancingOperation exec_ = new ExecAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()));
            _page.getMapAnonymous().addEntry(s_,exec_);
            return exec_;
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            ExecRootBlock ex_ = fetchType(a_.getRootNumber(), _page);
            ExecNamedFunctionBlock get_ = fetchFunction(a_.getRootNumber(), a_.getMemberNumber(), _page);
            ExecNamedFunctionBlock set_ = fetchFunction(a_.getRootNumber(), a_.getMemberNumberSet(), _page);
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
            return new ExecEnumValueOfOperation(new ExecOperationContent(d_.getContent()), new ExecValuesContent(d_.getValuesContent(), _page));
        }
        if (_anaNode instanceof ValuesOperation) {
            ValuesOperation d_ = (ValuesOperation) _anaNode;
            return new ExecValuesOperation(new ExecOperationContent(d_.getContent()), new ExecValuesContent(d_.getValuesContent(), _page));
        }
        if (_anaNode instanceof AbstractTernaryOperation) {
            AbstractTernaryOperation t_ = (AbstractTernaryOperation) _anaNode;
            return new ExecTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal());
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            ExecRootBlock ex_ = fetchType(c_.getRootNumber(), _page);
            if (ex_ != null) {
                ExecNamedFunctionBlock fct_ = fetchFunction(c_, _page);
                return new ExecChoiceFctOperation(fct_,ex_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true));
            }
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecRootBlock ex_ = fetchType(s_.getRootNumber(), _page);
            if (ex_ != null) {
                ExecNamedFunctionBlock fct_ = fetchFunction(s_, _page);
                return new ExecSuperFctOperation(fct_,ex_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true));
            }
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecNamedFunctionBlock fct_ = fetchFunction(f_, _page);
            ExecRootBlock ex_ = fetchType(f_.getRootNumber(), _page);
            if (ex_ != null) {
                return new ExecFctOperation(fct_,ex_, new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), new ExecInstFctContent(f_.getCallFctContent(), f_.getAnc(), f_.isStaticChoiceMethod()));
            }
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
            ExecAnonymousLambdaOperation exec_ = new ExecAnonymousLambdaOperation(new ExecOperationContent(s_.getContent()), new ExecLambdaCommonContent(s_.getLambdaCommonContent()));
            _page.getMapAnonymousLambda().addEntry(s_,exec_);
            return exec_;
        }
        if (_anaNode instanceof LambdaOperation) {
            LambdaOperation f_ = (LambdaOperation) _anaNode;
            if (f_.getStandardMethod() != null) {
                return new ExecStdMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), f_.getMethod(), f_.getStandardMethod());
            }
            if (f_.getMethod() == null && f_.getRealId() == null) {
                return new ExecFieldLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaFieldContent(f_.getFieldId(), f_.getLambdaFieldContent(), f_.getLambdaMemberNumberContent(), _page));
            }
            if (f_.getMethod() == null) {
                return new ExecConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaConstructorContent(f_.getRealId(),f_.getLambdaMemberNumberContent(), _page));
            }
            return new ExecMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaMethodContent(f_.getMethod(), f_.getLambdaMethodContent(), f_.getLambdaMemberNumberContent(), _page));
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
            if (s_.getFieldId() == null) {
                return new ExecDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
            }
            return new ExecSettableFieldOperation(fetchType(s_.getRootNumber(), _page), new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()), new ExecSettableOperationContent(s_.getSettableFieldContent()));
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
            return new ExecExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getCallFctContent()), m_.getOffsetOper(), fetchFunctionOp(m_.getRootNumber(), m_.getMemberNumber(), _page), fetchType(m_.getRootNumber(), _page));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            return new ExecSemiAffectationOperation(new ExecOperationContent(m_.getContent()), new ExecStaticPostEltContent(m_.getClassMethodId(), m_.isPost()), new ExecOperatorContent(m_.getOperatorContent()), fetchFunctionOp(m_.getRootNumber(), m_.getMemberNumber(), _page), fetchType(m_.getRootNumber(), _page), fetchImplicits(m_.getConverterFrom(), m_.getRootNumberFrom(), m_.getMemberNumberFrom(), _page), fetchImplicits(m_.getConverterTo(), m_.getRootNumberTo(), m_.getMemberNumberTo(), _page));
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (!n_.isOkNum()) {
                return new ExecDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
            }
            if (n_.getClassMethodId() != null) {
                return new ExecCustNumericOperation(fetchFunctionOp(n_.getRootNumber(),n_.getMemberNumber(), _page),fetchType(n_.getRootNumber(), _page), new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), getKind(n_.getClassMethodId()), getType(n_.getClassMethodId()));
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
                    fetchFunction(m_.getRootNumber(),m_.getMemberNumber(), _page),
                    fetchType(m_.getRootNumber(), _page), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return new ExecImplicitOperation(
                    fetchFunction(m_.getRootNumber(),m_.getMemberNumber(), _page),
                    fetchType(m_.getRootNumber(), _page), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
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
        if (_anaNode instanceof QuickOperation) {
            QuickOperation q_ = (QuickOperation) _anaNode;
            if (!q_.isOkNum()) {
                return new ExecDeclaringOperation(new ExecOperationContent(q_.getContent()));
            }
        }
        if (_anaNode instanceof AndOperation) {
            AndOperation c_ = (AndOperation) _anaNode;
            return new ExecAndOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getClassMethodId()), fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _page), fetchType(c_.getRootNumber(), _page), fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _page));
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            return new ExecOrOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getClassMethodId()), fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _page), fetchType(c_.getRootNumber(), _page), fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _page));
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
            return new ExecCompoundAffectationOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), new ExecStaticEltContent(c_.getClassMethodId()), fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _page), fetchType(c_.getRootNumber(), _page), fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _page));
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new ExecAffectationOperation(new ExecOperationContent(a_.getContent()), a_.getOpOffset());
        }
        return new ExecDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
    }
}
