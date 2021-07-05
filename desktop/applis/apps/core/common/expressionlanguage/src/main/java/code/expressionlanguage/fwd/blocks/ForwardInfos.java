package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.OverridesTypeUtil;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.Members;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.opers.*;
import code.util.*;
import code.util.core.StringUtil;

public final class ForwardInfos {
    private ForwardInfos() {
    }
    public static void generalForward(AnalyzedPageEl _page, Forwards _forwards, ContextEl _context) {
        Coverage coverage_ = _context.getCoverage();
        coverage_.setKeyWords(_page.getKeyWords());
        coverage_.putToStringOwner(_page.getAliasObject());
        coverage_.putRandCodeOwner(_page.getAliasObject());
        _forwards.setAliasBoolean(_page.getAliasBoolean());
        _forwards.setAliasPrimBoolean(_page.getAliasPrimBoolean());
        StringMap<ExecFileBlock> files_ = new StringMap<ExecFileBlock>();
        for (EntryCust<String, FileBlock> f: _page.getFilesBodies().entryList()) {
            String file_ = f.getKey();
            FileBlock content_ = f.getValue();
            ExecFileBlock exFile_ = new ExecFileBlock(content_.getMetricsCore(), content_.getFileName());
            coverage_.putFile(content_);
            files_.addEntry(file_,exFile_);
        }
        feedExecTypes(_page, _forwards, coverage_, files_);
        innerFetchExecEnd(_forwards);
        Classes classes_ = _context.getClasses();
        for (RootBlock e: _page.getSorted().values()) {
            ExecRootBlock e_ = _forwards.getMember(e).getRootBlock();
            String fullName_ = e.getFullName();
            classes_.getClassesBodies().addEntry(fullName_, e_);
        }
        for (OperatorBlock o: _page.getAllOperators()){
            ExecFileBlock exFile_ = files_.getValue(o.getFile().getNumberFile());
            ExecOperatorBlock e_ = new ExecOperatorBlock(o.isRetRef(), o.getName(), o.isVarargs(), o.getAccess(), o.getParametersNames(), o.getImportedParametersTypes(), o.getParametersRef());
            e_.setImportedReturnType(o.getImportedReturnType());
            e_.setFile(exFile_);
            _forwards.addOperator(o,e_);
            coverage_.putOperator(o);
        }
        for (OperatorBlock o: _page.getSortedOperators()) {
            classes_.getSortedOperators().add(_forwards.getOperator(o));
        }
        StringMap<ExecTypeFunction> toStringMethodsToCallBodies_ = _context.getClasses().getToStringMethodsToCallBodies();
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
            ClassMethodIdReturn resDyn_ = e.getValue();
            String fullName_ = e.getKey().getFullName();
            toStringMethodsToCallBodies_.addEntry(fullName_,FetchMemberUtil.fetchOvTypeFunction(resDyn_.getMemberId(), _forwards));
            coverage_.putToStringOwner(fullName_);
        }
        StringMap<ExecTypeFunction> randCodeMethodsToCallBodies_ = _context.getClasses().getRandCodeMethodsToCallBodies();
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getRandCodes().entryList()) {
            ClassMethodIdReturn resDyn_ = e.getValue();
            String fullName_ = e.getKey().getFullName();
            randCodeMethodsToCallBodies_.addEntry(fullName_,FetchMemberUtil.fetchOvTypeFunction(resDyn_.getMemberId(), _forwards));
            coverage_.putRandCodeOwner(fullName_);
        }
        feedRedirs(_page, _forwards);
        feed(_forwards);
        feedParents(_forwards);
        feedInherits(_forwards);
        feedFunctional(_forwards, _context);
        feedFct(_page, _forwards, coverage_, files_);
        feedMemberLists(_forwards);
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            Members mem_ = e.getValue();
            for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> f: mem_.getElementFields()) {
                InnerTypeOrElement method_ = f.getKey();
                CustList<ExecOperationNode> exNodes_ = processField(method_, (ExecBlock) f.getValue(), coverage_, _forwards, method_.getRoot());
                f.getValue().getElementContent().setOpValue(exNodes_);
            }
            for (EntryCust<FieldBlock, ExecFieldBlock> f: mem_.getExplicitFields()) {
                FieldBlock method_ = f.getKey();
                CustList<ExecOperationNode> exNodes_ = processField(method_, f.getValue(), coverage_, _forwards, method_.getRoot());
                f.getValue().setOpValue(exNodes_);
            }
        }
        for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> e: _forwards.getFctBodies()) {
            buildExec(e.getKey(),e.getValue(), coverage_, _forwards);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            coverage_.putBlockOperationsType(mem_.getRootBlock(),c);
            for (EntryCust<NamedCalledFunctionBlock, ExecAnnotationMethodBlock> a: mem_.getAnnotMethods()) {
                NamedCalledFunctionBlock b = a.getKey();
                ExecAnnotationMethodBlock d = a.getValue();
                coverage_.putBlockOperationsField(d, b);
                coverage_.putBlockOperationsAnnotMethodField(b);
                fwd(b,d, coverage_, _forwards);
            }
        }
        fwdAnnotated(_forwards, coverage_);
        feedFct(_forwards);
    }

    private static void feedExecTypes(AnalyzedPageEl _page, Forwards _forwards, Coverage _coverage, StringMap<ExecFileBlock> _files) {
        for (RootBlock r: _page.getAllFoundTypes()) {
            Members v_ = new Members();
            FileBlock fileBlock_ = r.getFile();
            ExecFileBlock exFile_ = _files.getValue(fileBlock_.getNumberFile());
            if (r instanceof AnonymousTypeBlock) {
                ExecAnonymousTypeBlock e_ = new ExecAnonymousTypeBlock(new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
                _forwards.addAnonType((AnonymousTypeBlock)r, e_);
            }
            if (r instanceof ClassBlock) {
                ExecClassBlock e_ = new ExecClassBlock(new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), new ExecClassContent(((ClassBlock) r).getClassContent()));
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof EnumBlock) {
                ExecEnumBlock e_ = new ExecEnumBlock(new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof InterfaceBlock) {
                ExecInterfaceBlock e_ = new ExecInterfaceBlock(new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), r.withoutInstance());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof AnnotationBlock) {
                ExecAnnotationBlock e_ = new ExecAnnotationBlock(new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof InnerElementBlock) {
                ExecInnerElementBlock e_ = new ExecInnerElementBlock(new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), new ExecElementContent(((InnerElementBlock) r).getElementContent()), ((InnerElementBlock) r).getTrOffset());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
                _forwards.addInnerEltType((InnerElementBlock) r, e_);
            }
            if (r instanceof RecordBlock) {
                ExecRecordBlock e_ = new ExecRecordBlock(((RecordBlock)r).isMutable(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            _coverage.putType(r);
            _forwards.addMember(r, v_);
        }
    }

    private static void feedParents(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            RootBlock parentType_ = root_.getParentType();
            int index_ = -1;
            if (parentType_ != null) {
                index_ = parentType_.getNumberAll();
            }
            ExecRootBlock execParentType_ = FetchMemberUtil.fetchType(index_, _forwards);
            ExecRootBlock e_ = e.getValue().getRootBlock();
            e_.setParentType(execParentType_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            updateExec(e.getValue().getRootBlock(), e.getKey());
        }
    }

    private static void feedInherits(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock i = e.getKey();
            CustList<AnaFormattedRootBlock> genericClasses_ = i.getAllGenericClassesInfo();
            ExecFormattedRootBlock formattedType_ = null;
            Members mem_ = e.getValue();
            if (i instanceof UniqueRootedBlock && genericClasses_.size() > 1) {
                AnaFormattedRootBlock anaFormattedRootBlock_ = genericClasses_.get(1);
                mem_.getRootBlock().setUniqueType(FetchMemberUtil.fetchType(anaFormattedRootBlock_.getRootBlock().getNumberAll(), _forwards));
                formattedType_ = FetchMemberUtil.fwdFormatType(anaFormattedRootBlock_, _forwards);
            }
            ConstructorBlock emptyCtor_ = i.getEmptyCtor();
            ExecNamedFunctionBlock fct_ = null;
            if (emptyCtor_ != null) {
                fct_ = FetchMemberUtil.fetchCtorFunction(mem_, emptyCtor_.getCtorNumber());
            }
            mem_.getRootBlock().emptyCtorPair(fct_);
            mem_.getRootBlock().setFormattedSuperClass(formattedType_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            CustList<AnaFormattedRootBlock> allGenericSuperTypes_ = e.getKey().getAllGenericSuperTypesInfo();
            CustList<ExecFormattedRootBlock> l_ = new CustList<ExecFormattedRootBlock>();
            for (AnaFormattedRootBlock s: allGenericSuperTypes_) {
                l_.add(FetchMemberUtil.fwdFormatType(s, _forwards));
            }
            e.getValue().getRootBlock().getAllGenericSuperTypes().addAllElts(l_);
        }
    }

    private static void feedRedirs(AnalyzedPageEl _page, Forwards _forwards) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            Members mem_ = e.getValue();
            ClassMethodIdOverrides redirections_ = mem_.getRootBlock().getRedirections();
            for (NamedCalledFunctionBlock o: root_.getOverridableBlocks()) {
                if (o.hiddenInstance() || o.isFinalMethod()) {
                    continue;
                }
                MethodId id_ = o.getId();
                StringMap<GeneStringOverridable> map_ = OverridesTypeUtil.getConcreteMethodsToCall(root_, id_, _page);
                map_.putAllMap(o.getOverrides());
                ClassMethodIdOverride override_ = new ClassMethodIdOverride(mem_.getOvNamed(o));
                for (EntryCust<String,GeneStringOverridable> g: map_.entryList()) {
                    GeneStringOverridable value_ = g.getValue();
                    Members memTarget_ = _forwards.getMember(value_.getType());
                    override_.put(g.getKey(), new ExecFormattedRootBlock(memTarget_.getRootBlock(),value_.getGeneString()), new ExecTypeFunction(memTarget_.getRootBlock(),memTarget_.getOvNamed(value_.getBlock())));
                }
                redirections_.add(override_);
            }
        }
    }

    private static void feedFunctional(Forwards _forwards, ContextEl _context) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            if (root_.mustImplement()) {
                continue;
            }
            CustList<AnaFormattedRootBlock> allSuperClass_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(root_));
            allSuperClass_.addAllElts(root_.getAllGenericSuperTypesInfo());
            boolean instEltCount_ = instEltCount(_forwards, _context, e, root_, allSuperClass_);
            e.getValue().getRootBlock().setWithInstanceElements(instEltCount_);
        }
    }

    private static boolean instEltCount(Forwards _forwards, ContextEl _context, EntryCust<RootBlock, Members> _e, RootBlock _root, CustList<AnaFormattedRootBlock> _allSuperClass) {
        boolean instEltCount_ = false;
        for (AnaFormattedRootBlock s: _allSuperClass) {
            RootBlock superBl_ = s.getRootBlock();
            feedFunctBodies(_forwards, _context, _e, _root, s, superBl_);
            for (AbsBk b: ClassesUtil.getDirectChildren(superBl_)) {
                if ((b instanceof FieldBlock)) {
                    if (((FieldBlock)b).isStaticField()) {
                        continue;
                    }
                    instEltCount_ = true;
                }
                if (b instanceof InstanceBlock || b instanceof ConstructorBlock) {
                    instEltCount_ = true;
                }
            }
        }
        return instEltCount_;
    }

    private static void feedFunctBodies(Forwards _forwards, ContextEl _context, EntryCust<RootBlock, Members> _e, RootBlock _root, AnaFormattedRootBlock _s, RootBlock _superBl) {
        for (NamedCalledFunctionBlock b: _superBl.getOverridableBlocks()) {
            if (b.isAbstractMethod()) {
                Members mem_ = _forwards.getMember(_superBl);
                ExecRootBlock ex_ = mem_.getRootBlock();
                ExecOverrideInfo val_ = ex_.getRedirections().getVal(mem_.getOvNamed(b), _root.getFullName());
                if (val_ == null) {
                    ExecOverridableBlock value_ = mem_.getOvNamed(b);
                    String ret_ = b.getImportedReturnType();
                    _e.getValue().getRootBlock().getFunctionalBodies().add(new ExecFunctionalInfo(FetchMemberUtil.formatType(_s,value_.getId()),FetchMemberUtil.formatType(_s,ret_), value_, _context));
                }
            }
        }
    }

    private static void feedFct(AnalyzedPageEl _page, Forwards _forwards, Coverage _coverage, StringMap<ExecFileBlock> _files) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            for (EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> f: mem_.getOvNamed()) {
                NamedCalledFunctionBlock method_ =  f.getKey();
                _coverage.putCallsNamedCalledFunctionBlock(c);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> f: mem_.getCtors()) {
                ConstructorBlock method_ =  f.getKey();
                _coverage.putCallsConstructorBlock(c);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<InstanceBlock, ExecInstanceBlock> f: mem_.getInstInitBodies()) {
                InstanceBlock method_ =  f.getKey();
                _coverage.putCallsInstanceBlock(c);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<StaticBlock, ExecStaticBlock> f: mem_.getStatInitBodies()) {
                StaticBlock method_ =  f.getKey();
                _coverage.putCallsStaticBlock(c);
                _forwards.addFctBody(method_,f.getValue());
            }
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getOperators()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            _forwards.addFctBody(o,value_);
        }
        for (AnonymousLambdaOperation e: _page.getAllAnonymousLambda()) {
            NamedCalledFunctionBlock method_ = e.getBlock();
            _coverage.putCallsAnon();
            ExecNamedFunctionBlock function_ = buildExecAnonymousLambdaOperation(e, _forwards);
            _forwards.addFctBody(method_, function_);
            int numberFile_ = method_.getFile().getNumberFile();
            ExecFileBlock value_ = _files.getValue(numberFile_);
            function_.setFile(value_);
        }
        for (SwitchOperation e: _page.getAllSwitchMethods()) {
            SwitchMethodBlock method_ = e.getSwitchMethod();
            _coverage.putCallsSwitchMethod();
            ExecAbstractSwitchMethod function_ = buildExecSwitchOperation(e, _forwards);
            _forwards.addFctBody(method_, function_);
            int numberFile_ = method_.getFile().getNumberFile();
            ExecFileBlock value_ = _files.getValue(numberFile_);
            function_.setFile(value_);
        }
    }

    private static void feedMemberLists(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            feedMemberList(_forwards, e);
        }
    }

    private static void feedMemberList(Forwards _forwards, EntryCust<RootBlock, Members> _e) {
        RootBlock c = _e.getKey();
        Members mem_ = _e.getValue();
        for (RootBlock b:c.getChildrenRootBlocks()){
            ExecRootBlock val_ = _forwards.getMember(b).getRootBlock();
            mem_.getRootBlock().getChildrenTypes().add(val_);
        }
        for (EntryCust<ConstructorBlock, ExecConstructorBlock> b:mem_.getCtors()) {
            mem_.getRootBlock().getChildrenOthers().add(b.getValue());
        }
        for (EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> b:mem_.getOvNamed()) {
            mem_.getRootBlock().getChildrenOthers().add(b.getValue());
        }
        for (EntryCust<NamedCalledFunctionBlock, ExecAnnotationMethodBlock> f: mem_.getAnnotMethods()) {
            mem_.getRootBlock().getAnnotationsFields().add(f.getValue());
        }
        for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> f: mem_.getElementFields()) {
            ExecInnerTypeOrElement val_ = f.getValue();
            mem_.getRootBlock().getEnumElements().add(val_);
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> f: mem_.getExplicitFields()) {
            ExecFieldBlock val_ = f.getValue();
            mem_.getRootBlock().getAllExpFields().add(val_);
        }
        for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> f: mem_.getFcts()) {
            ExecMemberCallingsBlock val_ = f.getValue();
            mem_.getRootBlock().getAllFct().add(val_);
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> f: mem_.getExplicitFields()) {
            FieldBlock method_ = f.getKey();
            ExecFieldBlock exp_ = f.getValue();
            if (!method_.isStaticField()) {
                mem_.getRootBlock().getInstanceFields().add(exp_);
            }
        }
    }

    private static void fwdAnnotated(Forwards _forwards, Coverage _coverage) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            fwdAnnotatedRoot(_forwards, _coverage, e);
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getOperators()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            fwdAnnotations(o, value_, _coverage, _forwards);
            fwdAnnotationsParameters(o, value_, _coverage, _forwards);
        }
        for (EntryCust<NamedCalledFunctionBlock, ExecAnonymousFunctionBlock> a: _forwards.getAnonLambdas()) {
            NamedCalledFunctionBlock key_ = a.getKey();
            ExecAnonymousFunctionBlock value_ = a.getValue();
            fwdAnnotations(key_, value_, _coverage, _forwards);
            fwdAnnotationsParameters(key_, value_, _coverage, _forwards);
        }
        for (EntryCust<SwitchMethodBlock, ExecAbstractSwitchMethod> a: _forwards.getSwitchMethods()) {
            SwitchMethodBlock key_ = a.getKey();
            ExecAbstractSwitchMethod value_ = a.getValue();
            fwdAnnotationsSw(key_, value_, _coverage, _forwards);
            fwdAnnotationsParametersSw(key_, value_, _coverage, _forwards);
        }
    }

    private static void fwdAnnotatedRoot(Forwards _forwards, Coverage _coverage, EntryCust<RootBlock, Members> _e) {
        RootBlock c = _e.getKey();
        Members mem_ = _e.getValue();
        for (EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> a: mem_.getNamed()) {
            NamedFunctionBlock b = a.getKey();
            ExecNamedFunctionBlock d = a.getValue();
            fwdAnnotations(b, d, _coverage, _forwards);
            fwdAnnotationsParameters(b, d, _coverage, _forwards);
        }
        for (EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> a: mem_.getOvNamed()) {
            NamedFunctionBlock b = a.getKey();
            ExecNamedFunctionBlock d = a.getValue();
            fwdAnnotations(b, d, _coverage, _forwards);
            fwdAnnotationsParameters(b, d, _coverage, _forwards);
        }
        for (EntryCust<ConstructorBlock, ExecConstructorBlock> a: mem_.getCtors()) {
            NamedFunctionBlock b = a.getKey();
            ExecNamedFunctionBlock d = a.getValue();
            fwdAnnotations(b, d, _coverage, _forwards);
            fwdAnnotationsParameters(b, d, _coverage, _forwards);
        }
        for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> a: mem_.getElementFields()) {
            InnerTypeOrElement b = a.getKey();
            ExecInnerTypeOrElement d = a.getValue();
            fwdAnnotations(b, d, _coverage, _forwards);
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> a: mem_.getExplicitFields()) {
            FieldBlock b = a.getKey();
            ExecFieldBlock d = a.getValue();
            fwdAnnotations(b, d, _coverage, _forwards);
        }
        if (!(mem_.getRootBlock() instanceof ExecInnerElementBlock)) {
            ExecRootBlock d = mem_.getRootBlock();
            fwdAnnotations(c, d, _coverage, _forwards);
        }
    }

    private static void feedFct(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            feedFctRoot(_forwards, e);
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getOperators()) {
            OperatorBlock key_ = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            feedFct(key_, value_, _forwards);
        }
        for (EntryCust<NamedCalledFunctionBlock, ExecAnonymousFunctionBlock> a: _forwards.getAnonLambdas()) {
            NamedCalledFunctionBlock key_ = a.getKey();
            ExecAnonymousFunctionBlock value_ = a.getValue();
            feedFct(key_, value_, _forwards);
        }
        for (EntryCust<SwitchMethodBlock, ExecAbstractSwitchMethod> a: _forwards.getSwitchMethods()) {
            SwitchMethodBlock key_ = a.getKey();
            ExecAbstractSwitchMethod value_ = a.getValue();
            feedFct(key_, value_, _forwards);
        }
    }

    private static void feedFctRoot(Forwards _forwards, EntryCust<RootBlock, Members> _e) {
        RootBlock root_ = _e.getKey();
        Members valueMember_ = _e.getValue();
        for (InfoBlock b: root_.getFieldsBlocks()) {
            ExecInfoBlock value_ = valueMember_.getField(b);
            ExecFieldContainer container_ = value_.getElementContent().getContainer();
            for (AnonymousTypeBlock a: b.getAnonymous()) {
                container_.getAnonymous().add(_forwards.getAnonType(a));
            }
            for (SwitchMethodBlock a: b.getSwitchMethods()) {
                container_.getSwitchMethods().add(_forwards.getSwitchMethod(a));
            }
            for (NamedCalledFunctionBlock a: b.getAnonymousFct()) {
                container_.getAnonymousLambda().add(_forwards.getAnonLambda(a));
            }
        }
        for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> f:valueMember_.getFcts()) {
            feedFct(f.getKey(), f.getValue(), _forwards);
        }
        ExecRootBlock value_ = _e.getValue().getRootBlock();
        for (NamedCalledFunctionBlock a: root_.getAnonymousRootFct()) {
            value_.getAnonymousRootLambda().add(_forwards.getAnonLambda(a));
        }
        for (SwitchMethodBlock a: root_.getSwitchMethodRoots()) {
            value_.getSwitchMethodsRoot().add(_forwards.getSwitchMethod(a));
        }
        for (AnonymousTypeBlock a: root_.getAnonymousRoot()) {
            value_.getAnonymousRoot().add(_forwards.getAnonType(a));
        }
    }

    private static void feed(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            e.getValue().getRootBlock().getAllSuperTypes().addAllElts(e.getKey().getAllSuperTypes());
            for (RootBlock r: e.getKey().getStaticInitImportedInterfaces()) {
                e.getValue().getRootBlock().getStaticInitImportedInterfaces().add(FetchMemberUtil.fetchType(r, _forwards));
            }
        }
    }

    private static void feedFct(MemberCallingsBlock _b1, ExecMemberCallingsBlock _value, Forwards _forwards) {
        for (SwitchMethodBlock a: _b1.getSwitchMethods()) {
            _value.getSwitchMethods().add(_forwards.getSwitchMethod(a));
        }
        for (NamedCalledFunctionBlock a: _b1.getAnonymousFct()) {
            _value.getAnonymousLambda().add(_forwards.getAnonLambda(a));
        }
        for (AnonymousTypeBlock a: _b1.getAnonymous()) {
            _value.getAnonymous().add(_forwards.getAnonType(a));
        }
        for (RootBlock a: _b1.getReserved()) {
            _value.getReserved().add(_forwards.getMember(a).getRootBlock());
        }
    }

    private static void innerFetchExecEnd(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> r: _forwards.getMembers()) {
            innerFetchExecEnd(_forwards, r);
        }
    }

    private static void innerFetchExecEnd(Forwards _forwards, EntryCust<RootBlock, Members> _r) {
        ExecRootBlock current_ = _r.getValue().getRootBlock();
        RootBlock k_ = _r.getKey();
        Members mem_ = _r.getValue();
        buildFieldInfos(_forwards, current_, k_, mem_);
        buildFctInfos(current_, k_, mem_);
        IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> ovNamed_ = ovNamed(mem_);
        IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> named_ = named(mem_);
        IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> ctors_;
        ctors_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
        for(EntryCust<ConstructorBlock, ExecConstructorBlock> e: mem_.getCtors()) {
            ctors_.addEntry(e.getKey(),e.getValue());
        }
        IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> instInit_;
        instInit_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
        for(EntryCust<InstanceBlock, ExecInstanceBlock> e: mem_.getInstInitBodies()) {
            instInit_.addEntry(e.getKey(),e.getValue());
        }
        IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> statInit_;
        statInit_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
        for(EntryCust<StaticBlock, ExecStaticBlock> e: mem_.getStatInitBodies()) {
            statInit_.addEntry(e.getKey(),e.getValue());
        }
        IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> mergedFct_;
        mergedFct_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
        mergedFct_ = merge(mergedFct_,ovNamed_);
        mergedFct_ = merge(mergedFct_,named_);
        mergedFct_ = merge(mergedFct_,ctors_);
        mergedFct_ = merge(mergedFct_,instInit_);
        mergedFct_ = merge(mergedFct_,statInit_);
        for(EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> e: mergedFct_.entryList()) {
            mem_.addFct(e.getKey(),e.getValue());
        }
        IdMap<AbsBk,ExecBlock> instField_ = new IdMap<AbsBk,ExecBlock>();
        IdMap<AbsBk,ExecBlock> statField_ = new IdMap<AbsBk,ExecBlock>();
        for(EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> e: mem_.getElementFields()) {
            statField_.addEntry((AbsBk) e.getKey(),(ExecBlock)e.getValue());
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> e:mem_.getExplicitFields()) {
            if (e.getValue().isStaticField()) {
                statField_.addEntry(e.getKey(),e.getValue());
            } else {
                instField_.addEntry(e.getKey(),e.getValue());
            }
        }
        for(EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> e: mem_.getNamed()) {
            OperationNode root_ = ((NamedCalledFunctionBlock)e.getKey()).getRoot();
            if (root_ != null) {
                instField_.addEntry(e.getKey(),e.getValue());
            }
        }
        IdMap<AbsBk,ExecBlock> instInitExec_ = new IdMap<AbsBk,ExecBlock>();
        for(EntryCust<InstanceBlock, ExecInstanceBlock> e: mem_.getInstInitBodies()) {
            instInitExec_.addEntry(e.getKey(),e.getValue());
        }
        IdMap<AbsBk,ExecBlock> statInitExec_ = new IdMap<AbsBk,ExecBlock>();
        for(EntryCust<StaticBlock, ExecStaticBlock> e: mem_.getStatInitBodies()) {
            statInitExec_.addEntry(e.getKey(),e.getValue());
        }
        IdMap<AbsBk,ExecBlock> mergedExecInst_;
        mergedExecInst_ = new IdMap<AbsBk,ExecBlock>();
        mergedExecInst_ = mergeExec(mergedExecInst_,instField_);
        mergedExecInst_ = mergeExec(mergedExecInst_,instInitExec_);
        IdMap<AbsBk,ExecBlock> mergedExecStat_;
        mergedExecStat_ = new IdMap<AbsBk,ExecBlock>();
        mergedExecStat_ = mergeExec(mergedExecStat_,statField_);
        mergedExecStat_ = mergeExec(mergedExecStat_,statInitExec_);
        current_.getAllInstanceMembers().addAllElts(mergedExecInst_.values());
        current_.getAllStaticMembers().addAllElts(mergedExecStat_.values());
        addFields(mem_);
    }

    private static IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> named(Members _mem) {
        IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> named_;
        named_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
        for(EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> e: _mem.getNamed()) {
            named_.addEntry(e.getKey(),e.getValue());
        }
        return named_;
    }

    private static IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> ovNamed(Members _mem) {
        IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> ovNamed_;
        ovNamed_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
        for(EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> e: _mem.getOvNamed()) {
            ovNamed_.addEntry(e.getKey(),e.getValue());
        }
        return ovNamed_;
    }

    private static void buildFctInfos(ExecRootBlock _current, RootBlock _k, Members _mem) {
        for (NamedCalledFunctionBlock b: _k.getOverridableBlocks()) {
            MethodKind kind_ = b.getKind();
            ExecOverridableBlock val_ = new ExecOverridableBlock(b.getAccess(), b.getModifier(), toExecMethodKind(kind_), new ExecExecNamedFunctionContent(b.getName(), b.getImportedParametersTypes(), b.getParametersRef(), b.getParametersNames(), b.isRetRef(), b.isVarargs()));
            val_.setFile(_current.getFile());
            _mem.addOvNamed(b,val_);
            val_.setImportedReturnType(b.getImportedReturnType());
            String returnTypeGet_ = b.getReturnTypeGet();
            if (!returnTypeGet_.isEmpty()) {
                val_.setImportedReturnType(returnTypeGet_);
            }
        }
        for (NamedCalledFunctionBlock b: _k.getAnnotationsMethodsBlocks()) {
            ExecAnnotationMethodBlock val_ = new ExecAnnotationMethodBlock(b.getName(), b.isVarargs(), b.getAccess(), b.getParametersNames(), b.getDefaultValueOffset());
            val_.setFile(_current.getFile());
            _mem.addAnnotMethod(b,val_);
            _mem.addNamed(b,val_);
            val_.setImportedReturnType(b.getImportedReturnType());
            val_.getImportedParametersTypes().addAllElts(b.getImportedParametersTypes());
        }
        for (ConstructorBlock b: _k.getConstructorBlocks()) {
            ExecConstructorBlock val_ = new ExecConstructorBlock(b.getName(), b.isVarargs(), b.getAccess(), b.getParametersNames(), b.getImportedParametersTypes(), b.getParametersRef());
            val_.setFile(_current.getFile());
            _mem.addCtor(b,val_);
            fwdInstancingStep(b, val_);
            val_.setImportedReturnType(b.getImportedReturnType());
        }
        for (InstanceBlock b: _k.getInstanceBlocks()) {
            ExecInstanceBlock val_ = new ExecInstanceBlock(b.getOffset());
            val_.setFile(_current.getFile());
            val_.setNumber(b.getNumber());
            _mem.addInstInitBody(b,val_);
            _current.getAllInstanceInits().add(val_);
        }
        for (StaticBlock b: _k.getStaticBlocks()) {
            ExecStaticBlock val_ = new ExecStaticBlock(b.getOffset());
            val_.setFile(_current.getFile());
            val_.setNumber(b.getNumber());
            _mem.addStatInitBody(b,val_);
            _current.getAllStaticInits().add(val_);
        }
    }

    private static void buildFieldInfos(Forwards _forwards, ExecRootBlock _current, RootBlock _k, Members _mem) {
        for (InfoBlock i: _k.getFieldsBlocks()) {
            if (i instanceof InnerElementBlock) {
                ExecInnerElementBlock val_ = _forwards.getInnerEltType((InnerElementBlock) i);
                _mem.addElementField((InnerElementBlock) i,val_);
                buildImportedTypes(val_, i);
            }
            if (i instanceof ElementBlock) {
                ExecElementBlock val_ = new ExecElementBlock(new ExecElementContent(((ElementBlock) i).getElementContent()), ((ElementBlock) i).getTrOffset());
                val_.setFile(_current.getFile());
                _mem.addElementField((ElementBlock) i,val_);
                buildImportedTypes(val_, i);
            }
            if (i instanceof FieldBlock) {
                ExecFieldBlock val_ = new ExecFieldBlock(((FieldBlock) i).getFieldContent());
                val_.setFile(_current.getFile());
                _mem.addExplicitField((FieldBlock) i,val_);
                buildImportedTypes(val_, i);
            }
        }
    }

    private static IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> merge(IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> _firstList,
                              IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> _secondList){
        IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> out_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
        int i_ = 0;
        int j_ = 0;
        int firstLen_ = _firstList.size();
        int secondLen_ = _secondList.size();
        while (i_ < firstLen_ && j_ < secondLen_){
            MemberCallingsBlock first_ = _firstList.getKey(i_);
            MemberCallingsBlock second_ = _secondList.getKey(j_);
            if (first_.getOffset() < second_.getOffset()) {
                addFctEntry(_firstList, out_, i_);
                i_++;
            } else {
                addFctEntry(_secondList, out_, j_);
                j_++;
            }
        }
        if (i_ < _firstList.size()){
            addFctEntries(_firstList, out_, i_, firstLen_);
        } else {
            addFctEntries(_secondList, out_, j_, secondLen_);
        }
        return out_;
    }

    private static void addFctEntries(IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> _firstList, IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> _out, int _i, int _firstLen) {
        for (int k = _i; k < _firstLen; k++) {
            addFctEntry(_firstList, _out, k);
        }
    }

    private static void addFctEntry(IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> _firstList, IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> _out, int _k) {
        _out.addEntry(_firstList.getKey(_k), _firstList.getValue(_k));
    }

    private static IdMap<AbsBk,ExecBlock> mergeExec(IdMap<AbsBk,ExecBlock> _firstList,
                                                    IdMap<AbsBk,ExecBlock> _secondList){
        IdMap<AbsBk,ExecBlock> out_ = new IdMap<AbsBk,ExecBlock>();
        int i_ = 0;
        int j_ = 0;
        int firstLen_ = _firstList.size();
        int secondLen_ = _secondList.size();
        while (i_ < firstLen_ && j_ < secondLen_){
            AbsBk first_ = _firstList.getKey(i_);
            AbsBk second_ = _secondList.getKey(j_);
            if (first_.getOffset() < second_.getOffset()) {
                addEntry(_firstList, out_, i_);
                i_++;
            } else {
                addEntry(_secondList, out_, j_);
                j_++;
            }
        }
        if (i_ < _firstList.size()){
            addEntries(_firstList, out_, i_, firstLen_);
        } else {
            addEntries(_secondList, out_, j_, secondLen_);
        }
        return out_;
    }

    private static void addEntries(IdMap<AbsBk, ExecBlock> _firstList, IdMap<AbsBk, ExecBlock> _out, int _i, int _firstLen) {
        for (int k = _i; k < _firstLen; k++) {
            addEntry(_firstList, _out, k);
        }
    }

    private static void addEntry(IdMap<AbsBk, ExecBlock> _firstList, IdMap<AbsBk, ExecBlock> _out, int _k) {
        _out.addEntry(_firstList.getKey(_k), _firstList.getValue(_k));
    }

    private static void addFields(Members _mem) {
        for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> e: _mem.getElementFields()) {
            _mem.addField(e.getKey(),e.getValue());
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> e: _mem.getExplicitFields()) {
            _mem.addField(e.getKey(),e.getValue());
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
        if (_k == MethodKind.RAND_CODE) {
            return ExecMethodKind.RAND_CODE;
        }
        if (_k == MethodKind.OPERATOR) {
            return ExecMethodKind.OPERATOR;
        }
        return ExecMethodKind.STD_METHOD;
    }

    private static ExecAnonymousFunctionBlock buildExecAnonymousLambdaOperation(AnonymousLambdaOperation _s, Forwards _forwards) {
//        ExecRootBlock declaring_ = _forwards.getMapMembers().getValue(_s.getRootNumber()).getRootBlock();
        NamedCalledFunctionBlock block_ = _s.getBlock();
        block_.setNumberLambda(_forwards.countAnonLambda());
        ExecAnonymousFunctionBlock fct_ = new ExecAnonymousFunctionBlock(block_.getAccess(), block_.getModifier(), new ExecAnonFctContent(block_.getAnaAnonFctContent()), new ExecExecNamedFunctionContent(block_.getName(), block_.getImportedParametersTypes(), block_.getParametersRef(), block_.getParametersNames(), block_.isRetRef(), block_.isVarargs()));
        _forwards.addAnonLambda(block_,fct_);
        fct_.setImportedReturnType(block_.getImportedReturnType());
        return fct_;
    }

    private static ExecAbstractSwitchMethod buildExecSwitchOperation(SwitchOperation _s, Forwards _forwards) {
        SwitchMethodBlock block_ = _s.getSwitchMethod();
        block_.setConditionNb(_forwards.countSwitchMethod());
        String parType_ = block_.getResult().getSingleNameOrEmpty();
        boolean retRef_ = block_.isRetRef();
        String name_ = block_.getName();
        MethodAccessKind kind_ = block_.getStaticContext();
        String retType_ = block_.getRetType();
        ExecAnonFctContent anonFctContent_ = new ExecAnonFctContent(block_.getAnaAnonFctContent());
        ExecAbstractSwitchMethod fct_;
        if (block_.isInstance()) {
            fct_ = new ExecSwitchInstanceMethod(retRef_, name_, kind_, parType_, retType_, anonFctContent_);
        } else {
            fct_ = new ExecSwitchValueMethod(retRef_, name_, kind_, parType_, retType_, anonFctContent_);
        }
        _forwards.addSwitchMethod(block_,fct_);
        return fct_;
    }

    private static void buildExec(MemberCallingsBlock _from, ExecMemberCallingsBlock _dest, Coverage _coverage, Forwards _forwards) {
        ExecFileBlock fileDest_ = _dest.getFile();
        AbsBk firstChild_ = _from.getFirstChild();
        _coverage.putBlockOperationsCaller(_dest,_from);
        if (firstChild_ == null) {
            return;
        }
        buildExecBody(_from, _coverage, _forwards, _dest, fileDest_);
    }

    private static void buildExecBody(MemberCallingsBlock _from, Coverage _coverage, Forwards _forwards, ExecBracedBlock _blockToWrite, ExecFileBlock _fileDest) {
        ExecBracedBlock blockToWrite_ = _blockToWrite;
        AbsBk en_ = _from;
        while (en_ != null) {
            _coverage.putBlockOperationsPre(_from,en_);
            AbsBk n_ = en_.getFirstChild();
            ExecBlock toWrite_ = block(en_, _fileDest, _coverage, _forwards);
            blockToWrite_ = complete(blockToWrite_, n_, toWrite_);
            if ((toWrite_ != null || en_ == _from) && n_ != null) {
                en_ = n_;
                continue;
            }
            while (en_ != null) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                BracedBlock par_ = en_.getParent();
                blockToWrite_ = blockToWrite_.getParent();
                if (blockToWrite_ == null) {
                    en_ = null;
                } else {
                    en_ = par_;
                }
            }
        }
    }

    private static ExecBracedBlock complete(ExecBracedBlock _blockToWrite, AbsBk _n, ExecBlock _toWrite) {
        if (_toWrite != null) {
            _blockToWrite.appendChild(_toWrite);
        }
        if (_toWrite instanceof ExecBracedBlock && _n != null) {
            return (ExecBracedBlock) _toWrite;
        }
        return _blockToWrite;
    }

    private static ExecBlock block(AbsBk _en, ExecFileBlock _fileDest, Coverage _coverage, Forwards _forwards) {
        if (_en instanceof BreakBlock) {
            ExecBreakBlock exec_ = new ExecBreakBlock(((BreakBlock) _en).getLabel());
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof CaseCondition) {
            ExecBracedBlock exec_ = buildCaseCondition((CaseCondition) _en);
            SwitchBlock par_ = ((CaseCondition) _en).getSwitchParent();
            if (par_ != null) {
                _coverage.putBlockOperationsSwitchsPart(par_, (CaseCondition) _en, exec_);
            }
            SwitchMethodBlock met_ = ((CaseCondition) _en).getSwitchMethod();
            if (met_ != null) {
                _coverage.putBlockOperationsSwitchsMethodPart((CaseCondition) _en, exec_);
            }
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof CatchEval) {
            ExecCatchEval exec_ = new ExecCatchEval(((CatchEval) _en).getVariableName(), ((CatchEval) _en).getImportedClassName());
            _coverage.putCatches((CatchEval) _en, exec_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof IfCondition) {
            CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock) _en).getRoot(), _coverage, _forwards, _en);
            ExecCondition exec_ = new ExecIfCondition(((ConditionBlock) _en).getConditionOffset(), ((IfCondition) _en).getLabel(), opCondition_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperationsConditions((ConditionBlock) _en, exec_);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof ElseIfCondition) {
            CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock) _en).getRoot(), _coverage, _forwards, _en);
            ExecCondition exec_ = new ExecElseIfCondition(((ConditionBlock) _en).getConditionOffset(), opCondition_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperationsConditions((ConditionBlock) _en, exec_);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof WhileCondition) {
            CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock) _en).getRoot(), _coverage, _forwards, _en);
            ExecCondition exec_ = new ExecWhileCondition(((ConditionBlock) _en).getConditionOffset(), ((WhileCondition) _en).getLabel(), opCondition_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperationsConditions((ConditionBlock) _en, exec_);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        return block4(_en, _fileDest, _coverage, _forwards);
    }

    private static ExecBlock block4(AbsBk _en, ExecFileBlock _fileDest, Coverage _coverage, Forwards _forwards) {
        if (_en instanceof DoWhileCondition) {
            CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock) _en).getRoot(), _coverage, _forwards, _en);
            ExecCondition exec_ = new ExecDoWhileCondition(((ConditionBlock) _en).getConditionOffset(), opCondition_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperationsConditions((ConditionBlock) _en, exec_);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof DoBlock) {
            ExecDoBlock exec_ = new ExecDoBlock(((DoBlock) _en).getLabel());
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof ContinueBlock) {
            ExecContinueBlock exec_ = new ExecContinueBlock(((ContinueBlock) _en).getLabel());
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof DeclareVariable) {
            ExecDeclareVariable exec_ = new ExecDeclareVariable(((DeclareVariable) _en).getImportedClassName(),((DeclareVariable) _en).getVariableNames());
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof DefaultCondition) {
            ExecBracedBlock exec_ = buildDefaultCondition((DefaultCondition) _en);
            SwitchBlock b_ = ((DefaultCondition) _en).getSwitchParent();
            if (b_ != null) {
                _coverage.putBlockOperationsSwitchsPart(b_, (DefaultCondition) _en, exec_);
            }
            SwitchMethodBlock met_ = ((DefaultCondition) _en).getSwitchMethod();
            if (met_ != null) {
                _coverage.putBlockOperationsSwitchsMethodPart((DefaultCondition) _en, exec_);
            }
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        return block3(_en, _fileDest, _coverage, _forwards);
    }

    private static ExecBlock block3(AbsBk _en, ExecFileBlock _fileDest, Coverage _coverage, Forwards _forwards) {
        if (_en instanceof ElseCondition) {
            ExecElseCondition exec_ = new ExecElseCondition();
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof Line) {
            CustList<ExecOperationNode> op_ = getExecutableNodes(((Line) _en).getRoot(), _coverage, _forwards, _en);
            ExecLine exec_ = new ExecLine(((Line) _en).isCallSuper(),((Line) _en).isCallInts(), new ExecOperationNodeListOff(op_, ((Line) _en).getExpressionOffset()));
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof EmptyInstruction) {
            ExecEmptyInstruction exec_ = new ExecEmptyInstruction();
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof FinallyEval) {
            ExecFinallyEval exec_ = new ExecFinallyEval();
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof ForEachLoop) {
            CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachLoop) _en).getRoot(), _coverage, _forwards, _en);
            ExecAbstractForEachLoop exec_;
            if (((ForEachLoop) _en).getRoot().getResultClass().isArray()) {
                if (((ForEachLoop) _en).isRefVariable()) {
                    exec_ = new ExecForEachRefArray(((ForEachLoop) _en).getLabel(), ((ForEachLoop) _en).getImportedClassName(),
                            ((ForEachLoop) _en).getImportedClassIndexName(), ((ForEachLoop) _en).getVariableName(), ((ForEachLoop) _en).getVariableNameOffset(), ((ForEachLoop) _en).getExpressionOffset(),op_);
                } else {
                    exec_ = new ExecForEachArray(((ForEachLoop) _en).getLabel(), ((ForEachLoop) _en).getImportedClassName(),
                            ((ForEachLoop) _en).getImportedClassIndexName(), ((ForEachLoop) _en).getVariableName(), ((ForEachLoop) _en).getVariableNameOffset(), ((ForEachLoop) _en).getExpressionOffset(),op_);
                }
            } else {
                exec_ = new ExecForEachIterable(((ForEachLoop) _en).getLabel(), ((ForEachLoop) _en).getImportedClassName(),
                        ((ForEachLoop) _en).getImportedClassIndexName(), ((ForEachLoop) _en).getVariableName(), ((ForEachLoop) _en).getVariableNameOffset(), ((ForEachLoop) _en).getExpressionOffset(),op_);
            }
            _coverage.putBlockOperationsLoops((AbstractForLoop) _en, exec_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        return block2(_en, _fileDest, _coverage, _forwards);
    }

    private static ExecBlock block2(AbsBk _en, ExecFileBlock _fileDest, Coverage _coverage, Forwards _forwards) {
        if (_en instanceof ForEachTable) {
            CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachTable) _en).getRoot(), _coverage, _forwards, _en);
            ExecForEachTable exec_ = new ExecForEachTable(((ForEachTable) _en).getLabel(), ((ForEachTable) _en).getImportedClassNameFirst(),
                    ((ForEachTable) _en).getImportedClassNameSecond(),
                    ((ForEachTable) _en).getImportedClassIndexName(), ((ForEachTable) _en).getVariableNameFirst(),
                    ((ForEachTable) _en).getVariableNameSecond(), new ExecOperationNodeListOff(op_, ((ForEachTable) _en).getExpressionOffset()));
            _coverage.putBlockOperationsLoops((AbstractForLoop) _en, exec_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof ForIterativeLoop) {
            CustList<ExecOperationNode> init_ = getExecutableNodes(((ForIterativeLoop) _en).getRootInit(), _coverage, _forwards, _en);
            CustList<ExecOperationNode> exp_ = getExecutableNodes(((ForIterativeLoop) _en).getRootExp(), _coverage, _forwards, _en);
            CustList<ExecOperationNode> step_ = getExecutableNodes(((ForIterativeLoop) _en).getRootStep(), _coverage, _forwards, _en);
            ExecForIterativeLoop exec_ = buildIter((ForIterativeLoop) _en, init_, exp_, step_);
            _coverage.putBlockOperationsLoops((AbstractForLoop) _en, exec_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof ForMutableIterativeLoop) {
            CustList<ExecOperationNode> init_;
            OperationNode rInit_ = ((ForMutableIterativeLoop) _en).getRootInit();
            init_ = getExecutableNodes(rInit_, _coverage, _forwards, _en);
            CustList<ExecOperationNode> exp_;
            OperationNode rExp_ = ((ForMutableIterativeLoop) _en).getRootExp();
            exp_ = getExecutableNodes(rExp_, _coverage, _forwards, _en);
            OperationNode rStep_ = ((ForMutableIterativeLoop) _en).getRootStep();
            CustList<ExecOperationNode> step_;
            step_ = getExecutableNodes(rStep_, _coverage, _forwards, _en);
            ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(((ForMutableIterativeLoop) _en).getLabel(), ((ForMutableIterativeLoop) _en).getImportedClassName(), ((ForMutableIterativeLoop) _en).getImportedClassIndexName(),
                    ((ForMutableIterativeLoop) _en).getVariableNames(),
                    new ExecOperationNodeListOff(init_, ((ForMutableIterativeLoop) _en).getInitOffset()), new ExecOperationNodeListOff(exp_, ((ForMutableIterativeLoop) _en).getExpressionOffset()), new ExecOperationNodeListOff(step_, ((ForMutableIterativeLoop) _en).getStepOffset()));
            _coverage.putBlockOperationsConditionsForMutable((ForMutableIterativeLoop) _en, exec_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof NullCatchEval) {
            ExecNullCatchEval exec_ = new ExecNullCatchEval();
            _coverage.putCatches((NullCatchEval) _en, exec_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof ReturnMethod) {
            OperationNode r_ = ((ReturnMethod) _en).getRoot();
            ExecBlock exec_ = buildRet(_coverage, _forwards, _en, r_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof SwitchBlock) {
            CustList<ExecOperationNode> op_ = getExecutableNodes(((SwitchBlock) _en).getRoot(), _coverage, _forwards, _en);
            ExecBracedBlock exec_ = buildSwitch((SwitchBlock) _en, op_);
            _coverage.putBlockOperationsSwitchs((SwitchBlock) _en, exec_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof TryEval) {
            ExecTryEval exec_ = new ExecTryEval(((TryEval) _en).getLabel());
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof Throwing) {
            CustList<ExecOperationNode> op_ = getExecutableNodes(((Throwing) _en).getRoot(), _coverage, _forwards, _en);
            ExecThrowing exec_ = new ExecThrowing(((Throwing) _en).getExpressionOffset(),op_);
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        if (_en instanceof UnclassedBracedBlock) {
            ExecUnclassedBracedBlock exec_ = new ExecUnclassedBracedBlock();
            exec_.setFile(_fileDest);
            _coverage.putBlockOperations(exec_, _en);
            return exec_;
        }
        return null;
    }

    private static ExecBracedBlock buildSwitch(SwitchBlock _en, CustList<ExecOperationNode> _op) {
        ExecBracedBlock exec_;
        if (_en.isInstance()) {
            exec_ = new ExecInstanceSwitchBlock(_en.getInstanceTest(), _en.getLabel(), _en.getValueOffset(), _op);
        } else {
            exec_ = new ExecStdSwitchBlock(_en.getInstanceTest(), _en.getLabel(), _en.getValueOffset(), _op);
        }
        return exec_;
    }

    private static ExecBracedBlock buildCaseCondition(CaseCondition _en) {
        ExecBracedBlock exec_;
//        if (((CaseCondition) _en).isBuiltEnum()) {
//            if (((CaseCondition) _en).isNullCaseEnum()) {
//                exec_ = new ExecStdCaseCondition(Argument.createVoid());
//            } else {
//                exec_ = new ExecEnumCaseCondition(((CaseCondition) _en).getValue());
//            }
//        } else
        if (!_en.getImportedType().isEmpty()) {
            exec_ = new ExecAbstractInstanceCaseCondition(_en.getVariableName(), _en.getImportedType(), true);
//        } else if (((CaseCondition) _en).isInstance()) {
//            exec_ = new ExecStdCaseCondition(Argument.createVoid());
//        } else if (((CaseCondition) _en).getQualif() != null) {
//            ClassField qualif_ = ((CaseCondition) _en).getQualif();
//            exec_ = new ExecQualifEnumCaseCondition(qualif_.getClassName(),qualif_.getFieldName());
        } else {
//            getExecutableNodes(((CaseCondition) _en).getRoot(), _coverage, _forwards, _en);
//            Argument argument_ = Argument.getNullableValue(((CaseCondition) _en).getArgument());
            exec_ = new ExecSwitchValuesCondition(_en.getStdValues(), _en.getEnumValues());
//            exec_ = new ExecStdCaseCondition(argument_);
        }
        return exec_;
    }

    private static ExecBracedBlock buildDefaultCondition(DefaultCondition _en) {
        ExecBracedBlock exec_;
        String instanceTest_ = _en.getInstanceTest();
        if (instanceTest_.isEmpty()) {
            exec_ = new ExecDefaultCondition();
        } else {
            exec_ = new ExecAbstractInstanceCaseCondition(_en.getVariableName(), instanceTest_, false);
        }
        return exec_;
    }

    private static ExecForIterativeLoop buildIter(ForIterativeLoop _en, CustList<ExecOperationNode> _init, CustList<ExecOperationNode> _exp, CustList<ExecOperationNode> _step) {
        ExecForIterativeLoop exec_;
        if (_en.isEq()) {
            exec_ = new ExecForIterativeLoopEq(_en.getLabel(), _en.getImportedClassName(),
                    _en.getImportedClassIndexName(), _en.getVariableName(),
                    new ExecOperationNodeListOff(_init, _en.getInitOffset()), new ExecOperationNodeListOff(_exp, _en.getExpressionOffset()), new ExecOperationNodeListOff(_step, _en.getStepOffset()));
        } else {
            exec_ = new ExecForIterativeLoopStrict(_en.getLabel(), _en.getImportedClassName(),
                    _en.getImportedClassIndexName(), _en.getVariableName(),
                    new ExecOperationNodeListOff(_init, _en.getInitOffset()), new ExecOperationNodeListOff(_exp, _en.getExpressionOffset()), new ExecOperationNodeListOff(_step, _en.getStepOffset()));
        }
        return exec_;
    }

    private static ExecBlock buildRet(Coverage _coverage, Forwards _forwards, AbsBk _en, OperationNode _r) {
        ExecBlock exec_;
        if (_r == null) {
            exec_ = new ExecVoidReturnMethod();
        } else {
            CustList<ExecOperationNode> op_ = getExecutableNodes(_r, _coverage, _forwards, _en);
            if (_r instanceof WrappOperation){
                exec_ = new ExecRefReturnMethod(((ReturnMethod) _en).getExpressionOffset(),op_);
            } else {
                exec_ = new ExecReturnMethod(((ReturnMethod) _en).getExpressionOffset(),op_, ((ReturnMethod) _en).getReturnType());
            }
        }
        return exec_;
    }

    private static CustList<ExecOperationNode> getExecutableNodes(OperationNode _root, Coverage _coverage, Forwards _forwards, AbsBk _currentBlock) {
        if (_root == null) {
            return new CustList<ExecOperationNode>();
        }
        return getExecutableNodes(-1,-1,_root,_coverage,_forwards, _currentBlock);
    }
    private static CustList<ExecOperationNode> getExecutableNodes(int _indexAnnotGroup, int _indexAnnot, OperationNode _root, Coverage _coverage, Forwards _forwards, AbsBk _currentBlock) {
        OperationNode current_ = _root;
        ExecOperationNode exp_ = createExecOperationNode(current_, _forwards);
        setImplicits(exp_, current_, _forwards);
        ExecOperationNode expRoot_ = exp_;
        _coverage.putBlockOperation(_indexAnnotGroup, _indexAnnot, _forwards, _currentBlock, current_,exp_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (hasToCreateExec(exp_, op_)) {
                ExecOperationNode loc_ = createExecOperationNode(op_, _forwards);
                setImplicits(loc_, op_, _forwards);
                _coverage.putBlockOperation(_indexAnnotGroup, _indexAnnot, _forwards, _currentBlock, op_,loc_);
                ((ExecMethodOperation)exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (current_ != null) {
                tryInitSettablePart(exp_);
                op_ = current_.getNextSibling();
                ExecMethodOperation par_ = exp_.getParent();
                if (hasToCreateExec(par_, op_)) {
                    ExecOperationNode loc_ = createExecOperationNode(op_, _forwards);
                    setImplicits(loc_, op_, _forwards);
                    _coverage.putBlockOperation(_indexAnnotGroup, _indexAnnot, _forwards, _currentBlock, op_,loc_);
                    par_.appendChild(loc_);
                    tryInitIntermediate(exp_, op_, loc_);
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (par_ == null) {
                    current_ = null;
                } else if (op_ == _root) {
                    tryInitSettablePart(par_);
                    current_ = null;
                } else {
                    current_ = op_;
                    exp_ = par_;
                }
            }
        }
        return getReducedNodes(expRoot_);
    }

    private static void tryInitIntermediate(ExecOperationNode _exp, OperationNode _op, ExecOperationNode _loc) {
        if (_op.getParent() instanceof AbstractDotOperation && _loc instanceof ExecPossibleIntermediateDotted) {
            _exp.setSiblingSet((ExecPossibleIntermediateDotted) _loc);
        }
    }

    private static void tryInitSettablePart(ExecOperationNode _exp) {
        if (_exp instanceof ExecAbstractAffectOperation) {
            ((ExecAbstractAffectOperation) _exp).setup();
        }
    }

    private static boolean hasToCreateExec(ExecOperationNode _exp, OperationNode _op) {
        return _exp instanceof ExecMethodOperation && _op != null;
    }

    private static void setImplicits(ExecOperationNode _ex, OperationNode _ana, Forwards _forwards){
        AnaClassArgumentMatching ana_ = _ana.getResultClass();
        ImplicitMethods implicits_ = _ex.getImplicits();
        ImplicitMethods implicitsTest_ = _ex.getImplicitsTest();
        FetchMemberUtil.setImplicits(ana_, implicits_, implicitsTest_, _forwards);
    }

    private static ExecOperationNode createExecOperationNode(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof AnnotationInstanceArobaseOperation) {
            AnnotationInstanceArobaseOperation n_ = (AnnotationInstanceArobaseOperation) _anaNode;
            return new ExecAnnotationInstanceArobaseOperation(FetchMemberUtil.fetchType(n_.getRootNumber(), _forwards), new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInstancingAnnotContent(n_.getInstancingAnnotContent(),_forwards));
        }
        if (_anaNode instanceof AnnotationInstanceArrOperation) {
            AnnotationInstanceArrOperation n_ = (AnnotationInstanceArrOperation) _anaNode;
            return new ExecArrayElementOperation(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(n_.getClassName()));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            if (f_.isClonedMethod()) {
                return new ExecCloneOperation(new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), f_.getCallFctContent().getMethodName());
            }
        }
        return procOperands6(_anaNode, _forwards);
    }

    private static ExecOperationNode procOperands6(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof InvokingOperation && _anaNode instanceof AbstractCallFctOperation) {
            InvokingOperation i_ = (InvokingOperation) _anaNode;
            AbstractCallFctOperation a_ = (AbstractCallFctOperation) _anaNode;
            if (a_.getStandardMethod() != null) {
                return new ExecStdFctOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStdFctContent(a_.getCallFctContent(), a_.isStaticMethod()), new ExecArrContent(a_.getArrContent()));
            }
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(a_.getCallFctContent().getMemberId(), _forwards);
            if (ex_ instanceof ExecAnnotationBlock) {
                return new ExecAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(a_.getCallFctContent()));
            }
            ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(a_.getCallFctContent().getMemberId(), _forwards);
            if (a_.isTrueFalse()) {
                return new ExecExplicitOperation(
                        pair_,
                        new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent(), _forwards));
            }
            if (a_.isStaticMethod()) {
                if (pair_ == null) {
                    return new ExecEnumMethOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctCommonContent(a_.getCallFctContent()), new ExecArrContent(a_.getArrContent()), ex_);
                }
                return new ExecStaticFctOperation(pair_, new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctContent(a_.getCallFctContent(), _forwards), new ExecArrContent(a_.getArrContent()));
            }
        }
        return procOperands5(_anaNode, _forwards);
    }

    private static ExecOperationNode procOperands5(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor n_ = (InterfaceFctConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(), _forwards), n_.getClassName(), FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof InterfaceInvokingConstructor) {
            InterfaceInvokingConstructor n_ = (InterfaceInvokingConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(), _forwards), "",FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof CurrentInvokingConstructor) {
            CurrentInvokingConstructor n_ = (CurrentInvokingConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(), _forwards), "",FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof SuperInvokingConstructor) {
            SuperInvokingConstructor n_ = (SuperInvokingConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(), _forwards), "",FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _anaNode;
            return new ExecCallDynMethodOperation(new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), c_.getFctName(), new ExecArrContent(c_.getArrContent()));
        }
        if (_anaNode instanceof ArgumentListInstancing) {
            ArgumentListInstancing c_ = (ArgumentListInstancing) _anaNode;
            return new ExecArgumentListInstancing(new ExecOperationContent(c_.getContent()));
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
        return procOperands4(_anaNode, _forwards);
    }

    private static ExecOperationNode procOperands4(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchPossibleTypeCtor(s_.getMemberId(), _forwards);
            if (typeCtor_ != null) {
                return new ExecStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), s_.isNewBefore(), new ExecInstancingCustContent(s_.getInstancingCommonContent(),typeCtor_, _forwards), new ExecInstancingStdContent(s_.getInstancingStdContent()));
            }
            return new ExecDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingDirContent(s_.getInstancingCommonContent()));
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards);
            return new ExecAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), s_.isNewBefore(), new ExecInstancingCustContent(s_.getInstancingCommonContent(),typeCtor_, _forwards));
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            ExecTypeFunction get_ = FetchMemberUtil.fetchOvTypeFunction(a_.getMemberIdGet(), _forwards);
            ExecTypeFunction set_ = FetchMemberUtil.fetchOvTypeFunction(a_.getMemberIdSet(), _forwards);
            boolean cust_ = get_ != null;
            if (set_ == null) {
                cust_ = false;
            }
            if (cust_) {
                return new ExecCustArrOperation(get_,set_, new ExecOperationContent(a_.getContent()), a_.isIntermediateDottedOperation(), new ExecArrContent(a_.getArrContent()), new ExecInstFctContent(a_.getCallFctContent(), a_.getAnc(), a_.isStaticChoiceMethod(), _forwards));
            }
            return new ExecArrOperation(new ExecOperationContent(a_.getContent()), a_.isIntermediateDottedOperation(), new ExecArrContent(a_.getArrContent()));
        }
        if (_anaNode instanceof SwitchOperation) {
            SwitchOperation s_ = (SwitchOperation) _anaNode;
            SwitchMethodBlock switchMethod_ = s_.getSwitchMethod();
            ExecAbstractSwitchMethod r_ = _forwards.getSwitchMethod(switchMethod_);
             return new ExecSwitchOperation(new ExecOperationContent(s_.getContent()), r_, new ExecArrContent(s_.getArrContent()));
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation d_ = (IdOperation) _anaNode;
            if (d_.isStandard()) {
                return new ExecIdOperation(new ExecOperationContent(d_.getContent()));
            }
            return new ExecMultIdOperation(new ExecOperationContent(d_.getContent()));
        }
        return procOperands3(_anaNode, _forwards);
    }

    private static ExecOperationNode procOperands3(OperationNode _anaNode, Forwards _forwards) {
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
            return new ExecRefTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal(),new ExecArrContent(false));
        }
        if (_anaNode instanceof AbstractRefTernaryOperation) {
            AbstractRefTernaryOperation t_ = (AbstractRefTernaryOperation) _anaNode;
            return new ExecRefTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal(),new ExecArrContent(t_.getArrContent()));
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(c_.getCallFctContent().getMemberId(), _forwards);
            return new ExecChoiceFctOperation(p_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true, _forwards), new ExecArrContent(c_.getArrContent()));
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(s_.getCallFctContent().getMemberId(), _forwards);
            return new ExecChoiceFctOperation(p_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true, _forwards), new ExecArrContent(s_.getArrContent()));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(f_.getCallFctContent().getMemberId(), _forwards);
            return new ExecFctOperation(p_, new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), new ExecInstFctContent(f_.getCallFctContent(), f_.getAnc(), f_.isStaticChoiceMethod(), _forwards), new ExecArrContent(f_.getArrContent()));
        }
        if (_anaNode instanceof NamedArgumentOperation) {
            NamedArgumentOperation f_ = (NamedArgumentOperation) _anaNode;
            return new ExecNamedArgumentOperation(new ExecOperationContent(f_.getContent()), new ExecNamedContent(f_.getNamedContent()));
        }
        if (_anaNode instanceof WrappOperation) {
            WrappOperation f_ = (WrappOperation) _anaNode;
            return new ExecWrappOperation(new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof FirstOptOperation) {
            FirstOptOperation f_ = (FirstOptOperation) _anaNode;
            return new ExecFirstOptOperation(new ExecOperationContent(f_.getContent()), f_.getOffset());
        }
        return procOperands2(_anaNode, _forwards);
    }

    private static ExecOperationNode procOperands2(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof ConstantOperation|| _anaNode instanceof StaticAccessOperation|| _anaNode instanceof StaticCallAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new ExecConstLeafOperation(false, new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof DefaultValueOperation) {
            DefaultValueOperation f_ = (DefaultValueOperation) _anaNode;
            return new ExecDefaultValueOperation(new ExecOperationContent(f_.getContent()), f_.getClassName());
        }
        if (_anaNode instanceof DefaultOperation) {
            DefaultOperation f_ = (DefaultOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new ExecDefaultOperation(new ExecOperationContent(f_.getContent()), f_.getOffset(), names_);
        }
        if (InvokingOperation.getDeltaCount(_anaNode) != 0) {
            return new ExecConstLeafOperation(true, new ExecOperationContent(_anaNode.getContent()));
        }
        if (_anaNode instanceof AnonymousLambdaOperation) {
            AnonymousLambdaOperation s_ = (AnonymousLambdaOperation) _anaNode;
            ExecTypeFunction pair_ = buildAnonFctPair(_forwards, s_);
            return new ExecAnonymousLambdaOperation(new ExecOperationContent(s_.getContent()), new ExecLambdaCommonContent(s_.getLambdaCommonContent(), _forwards), new ExecLambdaMethodContent(s_.getMethod(), pair_));
        }
        if (_anaNode instanceof LambdaOperation) {
            return buildLambda((LambdaOperation) _anaNode, _forwards);
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
            StringList names_ = _anaNode.getResultClass().getNames();
            return new ExecParentInstanceOperation(new ExecOperationContent(f_.getContent()), new ExecParentInstanceContent(f_.getParentInstanceContent()), names_);
        }
        if (_anaNode instanceof ForwardOperation) {
            ForwardOperation f_ = (ForwardOperation) _anaNode;
            return new ExecForwardOperation(new ExecOperationContent(f_.getContent()), f_.isIntermediate());
        }
        return procOperands(_anaNode, _forwards);
    }

    private static ExecOperationNode procOperands(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation s_ = (SettableAbstractFieldOperation) _anaNode;
            return new ExecSettableFieldOperation(FetchMemberUtil.fetchType(s_.getFieldType(), _forwards), new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()), new ExecSettableOperationContent(s_.getSettableFieldContent()));
        }
        if (_anaNode instanceof ArrayFieldOperation) {
            ArrayFieldOperation s_ = (ArrayFieldOperation) _anaNode;
            return new ExecArrayFieldOperation(new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()));
        }
        if (_anaNode instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation m_ = (MutableLoopVariableOperation) _anaNode;
            return new ExecStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation m_ = (VariableOperation) _anaNode;
            return new ExecStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof RefVariableOperation) {
            RefVariableOperation m_ = (RefVariableOperation) _anaNode;
            return new ExecStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()),m_.isDeclare());
        }
        if (_anaNode instanceof RefParamOperation) {
            RefParamOperation m_ = (RefParamOperation) _anaNode;
            return new ExecStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof FinalVariableOperation) {
            return finalVariable((FinalVariableOperation) _anaNode);
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new ExecDotOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof SafeDotOperation) {
            SafeDotOperation m_ = (SafeDotOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new ExecSafeDotOperation(new ExecOperationContent(m_.getContent()), names_);
        }
        if (_anaNode instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation m_ = (ExplicitOperatorOperation) _anaNode;
            return new ExecExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getCallFctContent(), _forwards), m_.getOffsetOper(), FetchMemberUtil.fetchFunctionOpPair(m_.getCallFctContent().getMemberId(), _forwards), new ExecArrContent(m_.getArrContent()));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            return semi((SemiAffectationOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            ClassMethodIdMemberIdTypeFct fct_ = n_.getFct();
            AnaTypeFct pair_ = fct_.getFunction();
            if (pair_ != null) {
                return new ExecCustNumericOperation(FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards), new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), new ExecStaticEltContent(fct_, _forwards));
            }
        }
        return procGeneOperators(_anaNode, _forwards);
    }

    private static ExecLeafOperation finalVariable(FinalVariableOperation _anaNode) {
        if (_anaNode.getType() == ConstType.LOOP_INDEX) {
            return new ExecFinalVariableOperation(new ExecOperationContent(_anaNode.getContent()), new ExecVariableContent(_anaNode.getVariableContent()));
        }
        return new ExecStdRefVariableOperation(new ExecOperationContent(_anaNode.getContent()), new ExecVariableContent(_anaNode.getVariableContent()));
    }

    private static ExecSemiAffectationOperation semi(SemiAffectationOperation _anaNode, Forwards _forwards) {
        StringList names_ = _anaNode.getResultClass().getNames();
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(_anaNode.getFct(), _forwards);
        if (pair_.getFct() == null) {
            return new ExecSemiAffectationNatOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), FetchMemberUtil.fetchImplicits(_anaNode.getConvFrom(), _forwards), FetchMemberUtil.fetchImplicits(_anaNode.getConvTo(), _forwards), _anaNode.isPost(), names_);
        }
        return new ExecSemiAffectationCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecStaticPostEltContent(_anaNode.getFct(), _anaNode.isPost(), _forwards), new ExecOperatorContent(_anaNode.getOperatorContent()), pair_, names_);
    }

    private static ExecOperationNode procGeneOperators(OperationNode _anaNode, Forwards _forwards) {
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
        if (_anaNode instanceof RandCodeOperation) {
            RandCodeOperation m_ = (RandCodeOperation) _anaNode;
            return new ExecRandCodeOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset());
        }
        if (_anaNode instanceof CastOperation) {
            CastOperation m_ = (CastOperation) _anaNode;
            return new ExecCastOperation(new ExecOperationContent(m_.getContent()), new ExecTypeCheckContent(m_.getTypeCheckContent()));
        }
        if (_anaNode instanceof ExplicitOperation) {
            ExplicitOperation m_ = (ExplicitOperation) _anaNode;
            return cast(_forwards, m_.getExplicitContent(), m_.getContent());
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return cast(_forwards, m_.getExplicitContent(), m_.getContent());
        }
        return procOperators(_anaNode, _forwards);
    }

    private static ExecOperationNode cast(Forwards _forwards, AnaExplicitContent _explicitContent, AnaOperationContent _content) {
        ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(_explicitContent.getMemberId(), _forwards);
        if (ExecExplicitOperation.direct(pair_, _explicitContent.getClassName())) {
            return new ExecImplicitOperation(
                    new ExecOperationContent(_content), new ExecExplicitCommonContent(_explicitContent));
        }
        return new ExecExplicitOperation(
                pair_,
                new ExecOperationContent(_content), new ExecExplicitContent(_explicitContent, _forwards));
    }

    private static ExecOperationNode procOperators(OperationNode _anaNode, Forwards _forwards) {
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
            return compare((CmpOperation) _anaNode);
        }
        if (_anaNode instanceof RangeOperation) {
            RangeOperation c_ = (RangeOperation) _anaNode;
            return new ExecRangeOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.isImplicitMiddle());
        }
        if (_anaNode instanceof InstanceOfOperation) {
            InstanceOfOperation c_ = (InstanceOfOperation) _anaNode;
            return new ExecInstanceOfOperation(new ExecOperationContent(c_.getContent()), new ExecTypeCheckContent(c_.getTypeCheckContent()));
        }
        if (_anaNode instanceof EqOperation) {
            EqOperation c_ = (EqOperation) _anaNode;
            return new ExecEqOperation(new ExecOperationContent(c_.getContent()), c_.getOper());
        }
        return procOper(_anaNode, _forwards);
    }

    private static ExecMethodOperation compare(CmpOperation _anaNode) {
        if (!_anaNode.isStringCompare()) {
            return new ExecNbCmpOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()));
        }
        return new ExecStrCmpOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()));
    }

    private static ExecOperationNode procOper(OperationNode _anaNode, Forwards _forwards) {
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
            return quickOp((QuickOperation) _anaNode, _forwards, false);
        }
        if (_anaNode instanceof OrOperation) {
            return quickOp((QuickOperation) _anaNode, _forwards, true);
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation c_ = (NullSafeOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new ExecNullSafeOperation(new ExecOperationContent(c_.getContent()),c_.getOpOffset(), names_);
        }
        if (_anaNode instanceof AssocationOperation) {
            AssocationOperation c_ = (AssocationOperation) _anaNode;
            return new ExecAssocationOperation(new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            return compound((CompoundAffectationOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new ExecAffectationOperation(new ExecOperationContent(a_.getContent()), a_.getOpOffset(), names_);
        }
        return new ExecDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
    }

    private static ExecOperationNode quickOp(QuickOperation _anaNode, Forwards _forwards, boolean _value) {
        ClassMethodIdMemberIdTypeFct fct_ = _anaNode.getFct();
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
        if (pair_.getFct() != null) {
            return new ExecQuickCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecStaticEltContent(fct_, _forwards), pair_, FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), _value);
        }
        return new ExecQuickNatOperation(new ExecOperationContent(_anaNode.getContent()), _anaNode.getOpOffset(), FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), _value);
    }

    private static ExecCompoundAffectationOperation compound(CompoundAffectationOperation _anaNode, Forwards _forwards) {
        StringList names_ = _anaNode.getResultClass().getNames();
        ClassMethodIdMemberIdTypeFct fct_ = _anaNode.getFct();
        if (_anaNode.isConcat()) {
            return new ExecCompoundAffectationStringOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), names_);
        }
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
        if (pair_.getFct() != null) {
            return new ExecCompoundAffectationCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), new ExecStaticEltContent(fct_, _forwards), pair_, FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), names_);
        }
        String oper_ = _anaNode.getOperatorContent().getOper();
        String op_ = oper_.substring(0, oper_.length() - 1);
        if (StringUtil.quickEq(op_, "??") || StringUtil.quickEq(op_, "???")) {
            return new ExecCompoundAffectationNatSafeOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), names_);
        }
        return new ExecCompoundAffectationNatOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), names_);
    }

    private static ExecAbstractLambdaOperation buildLambda(LambdaOperation _anaNode, Forwards _forwards) {
        ExecLambdaCommonContent lamCont_ = new ExecLambdaCommonContent(_anaNode.getLambdaCommonContent(), _forwards);
        if (_anaNode.getStandardMethod() != null) {
            return new ExecStdMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, _anaNode.getMethod(), _anaNode.getStandardMethod());
        }
        if (_anaNode.getStandardType() != null) {
            return new ExecStdConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, _anaNode.getRealId(), _anaNode.getStandardType());
        }
        int recordType_ = _anaNode.getRecordType();
        ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(recordType_, _forwards);
        if (rootBlock_ != null) {
            return new ExecRecordConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, rootBlock_, _anaNode.getInfos());
        }
        if (_anaNode.getMethod() == null && _anaNode.getRealId() == null) {
            return new ExecFieldLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, new ExecLambdaFieldContent(_anaNode.getFieldId(), _anaNode.getLambdaFieldContent(), _anaNode.getLambdaMemberNumberContentId(), _forwards));
        }
        if (_anaNode.getMethod() == null) {
            ExecLambdaConstructorContent lambdaConstructorContent_ = new ExecLambdaConstructorContent(_anaNode.getRealId(), _anaNode.getLambdaMemberNumberContentId(), _forwards);
            ExecTypeFunction pair_ = lambdaConstructorContent_.getPair();
            if (pair_ != null) {
                return new ExecTypeConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaConstructorContent_);
            }
            return new ExecConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_);
        }
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(_anaNode.getLambdaMemberNumberContentId(), _forwards);
        ExecRootBlock declaring_ = pair_.getType();
        ExecNamedFunctionBlock named_ = pair_.getFct();
        ExecLambdaMethodContent lambdaMethodContent_ = new ExecLambdaMethodContent(_anaNode.getMethod().getConstraints(), _anaNode.getLambdaMethodContent(), pair_);
        if (declaring_ != null || named_ != null) {
            return new ExecCustMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaMethodContent_);
        }
        if (lambdaMethodContent_.isDirectCast() || lambdaMethodContent_.isClonedMethod()) {
            return new ExecSimpleMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaMethodContent_);
        }
        return new ExecMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaMethodContent_);
    }

    private static ExecTypeFunction buildAnonFctPair(Forwards _forwards, AnonymousLambdaOperation _s) {
        NamedCalledFunctionBlock method_ = _s.getBlock();
        ExecAnonymousFunctionBlock r_ = _forwards.getAnonLambda(method_);
        return new ExecTypeFunction(FetchMemberUtil.fetchType(_s.getLambdaCommonContent().getFoundFormatted().getRootBlock(), _forwards), r_);
    }

    private static void updateExec(ExecRootBlock _ex, RootBlock _root){
        ExecRootBlockContent rootBlockContent_ = _ex.getRootBlockContent();
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

    private static void fwd(NamedCalledFunctionBlock _ana, ExecAnnotationMethodBlock _exec, Coverage _coverage, Forwards _forwards) {
        OperationNode root_ = _ana.getRoot();
        if (root_ == null) {
            _exec.setOpValue(new CustList<ExecOperationNode>());
            return;
        }
        CustList<ExecOperationNode> ops_ = getExecutableNodes(-1,-1,root_, _coverage, _forwards, _ana);
        _exec.setOpValue(ops_);
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
    private static void fwdAnnotationsParameters(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ann, Coverage _coverage, Forwards _forwards) {
        fwdAnnotationsParameters(_ana, _ann, _coverage, _forwards, _ana.getRootsList(), _ana.getAnnotationsIndexesParams());
    }

    private static void fwdAnnotationsParametersSw(SwitchMethodBlock _ana, ExecAnnotableParamBlock _ann, Coverage _coverage, Forwards _forwards) {
        fwdAnnotationsParameters(_ana, _ann, _coverage, _forwards, _ana.getRootsList(), _ana.getAnnotationsIndexesParams());
    }

    private static void fwdAnnotationsParameters(MemberCallingsBlock _ana, ExecAnnotableParamBlock _ann, Coverage _coverage, Forwards _forwards, CustList<CustList<OperationNode>> _roots, CustList<Ints> _annotationsIndexesParams) {
        CustList<CustList<ExecAnnotContent>> ops_ = new CustList<CustList<ExecAnnotContent>>();
        int i_ = 0;
        for (CustList<OperationNode> l: _roots) {
            CustList<ExecAnnotContent> annotation_;
            annotation_ = new CustList<ExecAnnotContent>();
            _coverage.putBlockOperationsAnnotMethodParam(_ana);
            int j_ = 0;
            for (OperationNode r: l) {
                _coverage.putBlockOperationsAnnotMethod(_ana,i_);
                annotation_.add(new ExecAnnotContent(getExecutableNodes(i_,j_,r, _coverage, _forwards, _ana),r.getResultClass().getNames(), _annotationsIndexesParams.get(i_).get(j_)));
                j_++;
            }
            ops_.add(annotation_);
            i_++;
        }
        _ann.getAnnotationsOpsParams().addAllElts(ops_);
    }

    private static void fwdAnnotations(InnerTypeOrElement _ana, ExecInnerTypeOrElement _ann, Coverage _coverage, Forwards _forwards) {
        fwdAnnotations(_ana, (AbsBk)_ana, _coverage, _forwards, _ana.getRoots(), _ana.getAnnotationsIndexes(), _ann);
    }

    private static void fwdAnnotations(FieldBlock _ana, ExecFieldBlock _ann, Coverage _coverage, Forwards _forwards) {
        fwdAnnot(_ana, _ana, _ann, _coverage, _forwards, _ana.getRoots(), _ana.getAnnotationsIndexes());
    }

    private static void fwdAnnot(InfoBlock _info,AbsBk _ana, ExecFieldBlock _ann, Coverage _coverage, Forwards _forwards, CustList<OperationNode> _roots, Ints _annotationsIndexes) {
        fwdAnnotations(_info, _ana, _coverage, _forwards, _roots, _annotationsIndexes, _ann);
    }

    private static void fwdAnnotations(InfoBlock _info, AbsBk _ana, Coverage _coverage, Forwards _forwards, CustList<OperationNode> _roots, Ints _annotationsIndexes, ExecAnnotableBlock _ann) {
        CustList<ExecAnnotContent> ops_ = new CustList<ExecAnnotContent>();
        int i_ = 0;
        for (OperationNode r : _roots) {
            _coverage.putBlockOperationsAnnotField(_info);
            ops_.add(new ExecAnnotContent(getExecutableNodes(-1, i_, r, _coverage, _forwards, _ana), r.getResultClass().getNames(), _annotationsIndexes.get(i_)));
            i_++;
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static CustList<ExecOperationNode> processField(InfoBlock _ana, ExecBlock _exec, Coverage _coverage, Forwards _forwards, OperationNode _root) {
        _coverage.putBlockOperationsField((AbsBk)_ana);
        _coverage.putBlockOperationsField(_exec, (AbsBk)_ana);
        return getExecutableNodes(-1,-1,_root, _coverage, _forwards, (AbsBk)_ana);
    }
    private static void fwdAnnotations(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ex, Coverage _coverage, Forwards _forwards) {
        fwdAnnotations(_ana, _ex, _coverage, _forwards, _ana.getRoots(), _ana.getAnnotationsIndexes());
    }
    private static void fwdAnnotationsSw(SwitchMethodBlock _ana, ExecAbstractSwitchMethod _ex, Coverage _coverage, Forwards _forwards) {
        fwdAnnotations(_ana, _ex, _coverage, _forwards, _ana.getRoots(), _ana.getAnnotationsIndexes());
    }

    private static void fwdAnnotations(MemberCallingsBlock _ana, ExecAnnotableBlock _ex, Coverage _coverage, Forwards _forwards, CustList<OperationNode> _roots, Ints _annotationsIndexes) {
        CustList<ExecAnnotContent> ops_ = new CustList<ExecAnnotContent>();
        int i_ = 0;
        for (OperationNode r : _roots) {
            _coverage.putBlockOperationsAnnotMethod(_ana);
            ops_.add(new ExecAnnotContent(getExecutableNodes(-1, i_, r, _coverage, _forwards, _ana), r.getResultClass().getNames(), _annotationsIndexes.get(i_)));
            i_++;
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(RootBlock _ana, ExecRootBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<ExecAnnotContent> ops_ = new CustList<ExecAnnotContent>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotType(_ana);
            ops_.add(new ExecAnnotContent(getExecutableNodes(-1,i_,r, _coverage, _forwards, _ana),r.getResultClass().getNames(), _ana.getAnnotationsIndexes().get(i_)));
            i_++;
        }
        _ann.getAnnotationsOps().clear();
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static CustList<ExecOperationNode> getReducedNodes(ExecOperationNode _root) {
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        ExecOperationNode current_ = _root;
        while (current_ != null) {
            ExecOperationNode op_ = current_.getFirstChild();
            if (op_ != null && current_.getArgument() == null) {
                current_ = op_;
                continue;
            }
            while (current_ != null) {
                current_.setOrder(out_.size());
                out_.add(current_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                } else if (op_ == _root) {
                    op_.setOrder(out_.size());
                    out_.add(op_);
                    current_ = null;
                } else {
                    current_ = op_;
                }
            }
        }
        return out_;
    }
}
