package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.OverridesTypeUtil;
import code.expressionlanguage.analyze.opers.*;
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
import code.expressionlanguage.fwd.PutCoveragePhase;
import code.expressionlanguage.fwd.opers.*;
import code.util.*;

public final class ForwardInfos {
    private ForwardInfos() {
    }
    public static void generalForward(AnalyzedPageEl _page, Forwards _forwards, ContextEl _context) {
        Coverage coverage_ = _context.getCoverage();
        coverage_.setKeyWords(_page.getKeyWords());
        coverage_.putToStringOwner(_page.getAliasObject());
        _forwards.setAliasBoolean(_page.getAliasBoolean());
        _forwards.setAliasPrimBoolean(_page.getAliasPrimBoolean());
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
            FileBlock fileBlock_ = r.getFile();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            if (r instanceof AnonymousTypeBlock) {
                ExecAnonymousTypeBlock e_ = new ExecAnonymousTypeBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
                _forwards.addAnonType((AnonymousTypeBlock)r, e_);
            }
            if (r instanceof ClassBlock) {
                ExecClassBlock e_ = new ExecClassBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), new ExecClassContent(((ClassBlock) r).getClassContent()));
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof EnumBlock) {
                ExecEnumBlock e_ = new ExecEnumBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof InterfaceBlock) {
                ExecInterfaceBlock e_ = new ExecInterfaceBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), r.withoutInstance());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof AnnotationBlock) {
                ExecAnnotationBlock e_ = new ExecAnnotationBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof InnerElementBlock) {
                ExecInnerElementBlock e_ = new ExecInnerElementBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), new ExecElementContent(((InnerElementBlock) r).getElementContent()), ((InnerElementBlock) r).getTrOffset());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
                _forwards.addInnerEltType((InnerElementBlock) r, e_);
            }
            if (r instanceof RecordBlock) {
                ExecRecordBlock e_ = new ExecRecordBlock(((RecordBlock)r).isMutable(),r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            coverage_.putType(r);
            _forwards.addMember(r, v_);
        }
        innerFetchExecEnd(_forwards);
        Classes classes_ = _context.getClasses();
        for (RootBlock e: _page.getSorted().values()) {
            ExecRootBlock e_ = _forwards.getMember(e).getRootBlock();
            String fullName_ = e.getFullName();
            classes_.getClassesBodies().addEntry(fullName_, e_);
        }
        for (OperatorBlock o: _page.getAllOperators()){
            ExecFileBlock exFile_ = files_.getValue(o.getFile().getNumberFile());
            ExecOperatorBlock e_ = new ExecOperatorBlock(o.isRetRef(), o.getName(), o.isVarargs(), o.getAccess(), o.getParametersNames(), o.getOffset().getOffsetTrim(), o.getImportedParametersTypes(), o.getParametersRef());
            e_.setFile(exFile_);
            _forwards.addOperator(o,e_);
            coverage_.putOperator(o);
        }
        for (OperatorBlock o: _page.getSortedOperators()) {
            classes_.getSortedOperators().add(_forwards.getOperator(o));
        }
        for (EntryCust<String,FileBlock> e: _page.getFilesBodies().entryList()) {
            FileBlock fileBlock_ = e.getValue();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecFile(fileBlock_,exFile_, _forwards);
        }
        StringMap<ExecTypeFunction> toStringMethodsToCallBodies_ = _context.getClasses().getToStringMethodsToCallBodies();
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
            ClassMethodIdReturn resDyn_ = e.getValue();
            String fullName_ = e.getKey().getFullName();
            toStringMethodsToCallBodies_.addEntry(fullName_,FetchMemberUtil.fetchOvTypeFunction(resDyn_.getMemberId(), _forwards));
            coverage_.putToStringOwner(fullName_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            Members mem_ = e.getValue();
            ClassMethodIdOverrides redirections_ = mem_.getRootBlock().getRedirections();
            for (NamedCalledFunctionBlock o: root_.getOverridableBlocks()) {
                if (o.hiddenInstance()) {
                    continue;
                }
                if (o.isFinalMethod()) {
                    continue;
                }
                MethodId id_ = o.getId();
                StringMap<GeneStringOverridable> map_ = OverridesTypeUtil.getConcreteMethodsToCall(root_, id_, _page);
                map_.putAllMap(o.getOverrides());
                ClassMethodIdOverride override_ = new ClassMethodIdOverride(mem_.getOvNamed(o));
                for (EntryCust<String,GeneStringOverridable> g: map_.entryList()) {
                    GeneStringOverridable value_ = g.getValue();
                    Members memTarget_ = _forwards.getMember(value_.getType());
                    override_.put(g.getKey(), value_.getGeneString(), new ExecTypeFunction(memTarget_.getRootBlock(),memTarget_.getOvNamed(value_.getBlock())));
                }
                redirections_.add(override_);
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            if (!root_.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(root_,root_.getGenericString()));
                allSuperClass_.addAllElts(root_.getAllGenericSuperTypesInfo());
                boolean instEltCount_ = false;
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    RootBlock superBl_ = s.getRootBlock();
                    for (NamedCalledFunctionBlock b: superBl_.getOverridableBlocks()) {
                        if (b.isAbstractMethod()) {
                            Members mem_ = _forwards.getMember(superBl_);
                            ExecRootBlock ex_ = mem_.getRootBlock();
                            ExecOverrideInfo val_ = ex_.getRedirections().getVal(mem_.getOvNamed(b), root_.getFullName());
                            if (val_ == null) {
                                ExecOverridableBlock value_ = mem_.getMethod(b);
                                e.getValue().getRootBlock().getFunctionalBodies().add(new ExecFunctionalInfo(s.getFormatted(),value_));
                            }
                        }
                    }
                    for (AbsBk b: ClassesUtil.getDirectChildren(superBl_)) {
                        if ((b instanceof FieldBlock)) {
                            if (((FieldBlock)b).isStaticField()) {
                                continue;
                            }
                            instEltCount_ = true;
                        }
                        if (b instanceof InstanceBlock) {
                            instEltCount_ = true;
                        }
                        if (b instanceof ConstructorBlock) {
                            instEltCount_ = true;
                        }
                    }
                }
                e.getValue().getRootBlock().setWithInstanceElements(instEltCount_);
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            e.getValue().getRootBlock().getAllSuperTypes().addAllElts(e.getKey().getAllSuperTypes());
            e.getValue().getRootBlock().getStaticInitImportedInterfaces().addAllElts(e.getKey().getStaticInitImportedInterfaces());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            RootBlock parentType_ = root_.getParentType();
            int index_ = -1;
            if (parentType_ != null) {
                index_ = parentType_.getNumberAll();
            }
            ExecRootBlock execParentType_ = FetchMemberUtil.fetchType(index_,_forwards);
            ExecRootBlock e_ = e.getValue().getRootBlock();
            e_.setParentType(execParentType_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            updateExec(e.getValue().getRootBlock(), e.getKey());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock i = e.getKey();
            CustList<AnaFormattedRootBlock> genericClasses_ = i.getAllGenericClassesInfo();
            Members mem_ = e.getValue();
            if (i instanceof UniqueRootedBlock && genericClasses_.size() > 1) {
                mem_.getRootBlock().setUniqueType(FetchMemberUtil.fetchType(genericClasses_.get(1).getRootBlock().getNumberAll(), _forwards));
            }
            ConstructorBlock emptyCtor_ = i.getEmptyCtor();
            ExecNamedFunctionBlock fct_ = null;
            if (emptyCtor_ != null) {
                fct_ = FetchMemberUtil.fetchCtorFunction(mem_, emptyCtor_.getCtorNumber());
            }
            mem_.getRootBlock().emptyCtorPair(fct_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            CustList<AnaFormattedRootBlock> allGenericSuperTypes_ = e.getKey().getAllGenericSuperTypesInfo();
            CustList<ExecFormattedRootBlock> l_ = new CustList<ExecFormattedRootBlock>();
            for (AnaFormattedRootBlock s: allGenericSuperTypes_) {
                l_.add(new ExecFormattedRootBlock(_forwards.getMember(s.getRootBlock()).getRootBlock(),s.getFormatted()));
            }
            e.getValue().getRootBlock().getAllGenericSuperTypes().addAllElts(l_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            validateIds(e.getValue());
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getOperators()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            value_.setImportedReturnType(o.getImportedReturnType());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> f: mem_.getFctBodies()) {
                MemberCallingsBlock method_ =  f.getKey();
                coverage_.putCalls(c,method_);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> f: mem_.getCtors()) {
                MemberCallingsBlock method_ =  f.getKey();
                coverage_.putCalls(c,method_);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<InstanceBlock, ExecInstanceBlock> f: mem_.getInstInitBodies()) {
                InstanceBlock method_ =  f.getKey();
                coverage_.putCalls(c,method_);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<StaticBlock, ExecStaticBlock> f: mem_.getStatInitBodies()) {
                StaticBlock method_ =  f.getKey();
                coverage_.putCalls(c,method_);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> f: mem_.getCtors()) {
                ConstructorBlock method_ = f.getKey();
                fwdInstancingStep(method_, f.getValue());
            }
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getOperators()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            _forwards.addFctBody(o,value_);
        }
        for (AnonymousLambdaOperation e: _page.getAllAnonymousLambda()) {
            NamedCalledFunctionBlock method_ = e.getBlock();
            coverage_.putCallsAnon();
            ExecNamedFunctionBlock function_ = buildExecAnonymousLambdaOperation(e, _forwards);
            _forwards.addFctBody(method_, function_);
            int numberFile_ = method_.getFile().getNumberFile();
            ExecFileBlock value_ = files_.getValue(numberFile_);
            function_.setFile(value_);
        }
        for (SwitchOperation e: _page.getAllSwitchMethods()) {
            SwitchMethodBlock method_ = e.getSwitchMethod();
            coverage_.putCallsSwitchMethod();
            ExecAbstractSwitchMethod function_ = buildExecSwitchOperation(e, _forwards);
            _forwards.addFctBody(method_, function_);
            int numberFile_ = method_.getFile().getNumberFile();
            ExecFileBlock value_ = files_.getValue(numberFile_);
            function_.setFile(value_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            for (AbsBk b: ClassesUtil.getDirectChildren(c)) {
                if (b instanceof RootBlock) {
                    ExecRootBlock val_ = _forwards.getMember((RootBlock) b).getRootBlock();
                    mem_.getRootBlock().getChildrenTypes().add(val_);
                } else {
                    if (b instanceof InfoBlock) {
                        ExecInfoBlock elt_ = mem_.getField((InfoBlock) b);
                        mem_.getRootBlock().getChildrenOthers().add((ExecBlock) elt_);
                    }
                    if (b instanceof MemberCallingsBlock) {
                        ExecMemberCallingsBlock elt_ = mem_.getFct((MemberCallingsBlock) b);
                        mem_.getRootBlock().getChildrenOthers().add(elt_);
                    }
                }
            }
            for (EntryCust<NamedCalledFunctionBlock, ExecAnnotationMethodBlock> f: mem_.getAnnotMethods()) {
                mem_.getRootBlock().getAnnotationsFields().add(f.getValue());
            }
            for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> f: mem_.getElementFields()) {
                ExecInnerTypeOrElement val_ = f.getValue();
                mem_.getRootBlock().getEnumElements().add(val_);
            }
            for (EntryCust<InfoBlock, ExecInfoBlock> f: mem_.getFields()) {
                ExecInfoBlock val_ = f.getValue();
                mem_.getRootBlock().getAllFields().add(val_);
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
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            Members mem_ = e.getValue();
            for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> f: mem_.getElementFields()) {
                InnerTypeOrElement method_ = f.getKey();
                CustList<ExecOperationNode> exNodes_ = processField(method_, (ExecBlock) f.getValue(), coverage_, _forwards, method_.getRoot());
                f.getValue().setOpValue(exNodes_);
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
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            for (EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> a: mem_.getNamed()) {
                NamedFunctionBlock b = a.getKey();
                ExecNamedFunctionBlock d = a.getValue();
                fwdAnnotations(b, d, coverage_, _forwards);
                fwdAnnotationsParameters(b, d, coverage_, _forwards);
            }
            for (EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> a: mem_.getOvNamed()) {
                NamedFunctionBlock b = a.getKey();
                ExecNamedFunctionBlock d = a.getValue();
                fwdAnnotations(b, d, coverage_, _forwards);
                fwdAnnotationsParameters(b, d, coverage_, _forwards);
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> a: mem_.getCtors()) {
                NamedFunctionBlock b = a.getKey();
                ExecNamedFunctionBlock d = a.getValue();
                fwdAnnotations(b, d, coverage_, _forwards);
                fwdAnnotationsParameters(b, d, coverage_, _forwards);
            }
            for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> a: mem_.getElementFields()) {
                InnerTypeOrElement b = a.getKey();
                ExecInnerTypeOrElement d = a.getValue();
                fwdAnnotations(b, d, coverage_, _forwards);
            }
            for (EntryCust<FieldBlock, ExecFieldBlock> a: mem_.getExplicitFields()) {
                FieldBlock b = a.getKey();
                ExecFieldBlock d = a.getValue();
                fwdAnnotations(b, d, coverage_, _forwards);
            }
            if (!(mem_.getRootBlock() instanceof ExecInnerElementBlock)) {
                ExecRootBlock d = mem_.getRootBlock();
                fwdAnnotations(c, d, coverage_, _forwards);
            }
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getOperators()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            fwdAnnotations(o, value_, coverage_, _forwards);
            fwdAnnotationsParameters(o, value_, coverage_, _forwards);
        }
        for (EntryCust<NamedCalledFunctionBlock, ExecAnonymousFunctionBlock> a: _forwards.getAnonLambdas()) {
            NamedCalledFunctionBlock key_ = a.getKey();
            ExecAnonymousFunctionBlock value_ = a.getValue();
            fwdAnnotations(key_, value_, coverage_, _forwards);
            fwdAnnotationsParameters(key_, value_, coverage_, _forwards);
        }
        for (EntryCust<SwitchMethodBlock, ExecAbstractSwitchMethod> a: _forwards.getSwitchMethods()) {
            SwitchMethodBlock key_ = a.getKey();
            ExecAbstractSwitchMethod value_ = a.getValue();
            fwdAnnotationsSw(key_, value_, coverage_, _forwards);
            fwdAnnotationsParametersSw(key_, value_, coverage_, _forwards);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            Members valueMember_ = e.getValue();
            for (AbsBk b: ClassesUtil.getDirectChildren(root_)) {
                if (b instanceof MemberCallingsBlock) {
                    MemberCallingsBlock b1_ = (MemberCallingsBlock) b;
                    ExecMemberCallingsBlock value_ = valueMember_.getFct(b1_);
                    feedFct(b1_, value_, _forwards);
                }
                if (b instanceof InfoBlock) {
                    ExecInfoBlock value_ = valueMember_.getField((InfoBlock)b);
                    for (AnonymousTypeBlock a: ((InfoBlock)b).getAnonymous()) {
                        value_.getAnonymous().add(_forwards.getAnonType(a));
                    }
                    for (SwitchMethodBlock a: ((InfoBlock)b).getSwitchMethods()) {
                        value_.getSwitchMethods().add(_forwards.getSwitchMethod(a));
                    }
                    for (NamedCalledFunctionBlock a: ((InfoBlock)b).getAnonymousFct()) {
                        value_.getAnonymousLambda().add(_forwards.getAnonLambda(a));
                    }
                }
            }
            ExecRootBlock value_ = e.getValue().getRootBlock();
            for (NamedCalledFunctionBlock a: root_.getAnonymousRootFct()) {
                value_.getAnonymousRootLambda().add(_forwards.getAnonLambda(a));
            }
            for (SwitchMethodBlock a: root_.getSwitchMethods()) {
                value_.getSwitchMethodsRoot().add(_forwards.getSwitchMethod(a));
            }
            for (AnonymousTypeBlock a: root_.getAnonymousRoot()) {
                value_.getAnonymousRoot().add(_forwards.getAnonType(a));
            }
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

    private static void processAppend(ExecFileBlock _exFile, RootBlock _root, Forwards _forwards) {
        ExecRootBlock e_ = _forwards.getMember(_root).getRootBlock();
        _exFile.appendChild(e_);
    }

    private static void innerFetchExecEnd(Forwards _forwards) {
        for (EntryCust<RootBlock, Members> r: _forwards.getMembers()) {
            ExecRootBlock current_ = r.getValue().getRootBlock();
            RootBlock k_ = r.getKey();
            Members mem_ = r.getValue();
            for (AbsBk b: ClassesUtil.getDirectChildren(k_)) {
                if (AbsBk.isOverBlock(b)) {
                    NamedCalledFunctionBlock ov_ = (NamedCalledFunctionBlock) b;
                    MethodKind kind_ = ov_.getKind();
                    ExecOverridableBlock val_ = new ExecOverridableBlock(ov_.isRetRef(), ov_.getName(), ov_.isVarargs(), ov_.getAccess(), ov_.getParametersNames(), ov_.getModifier(), toExecMethodKind(kind_), b.getOffset().getOffsetTrim(), ov_.getImportedParametersTypes(), ov_.getParametersRef());
                    val_.setFile(current_.getFile());
                    mem_.addMethod(ov_,val_);
                    mem_.addOvNamed(ov_,val_);
                    mem_.addFct(ov_,val_);
                    mem_.addFctBody(ov_,val_);
                }
                if (AbsBk.isAnnotBlock(b)) {
                    NamedCalledFunctionBlock annot_ = (NamedCalledFunctionBlock) b;
                    ExecAnnotationMethodBlock val_ = new ExecAnnotationMethodBlock((annot_).getName(), (annot_).isVarargs(), (annot_).getAccess(), (annot_).getParametersNames(), (annot_).getDefaultValueOffset(), b.getOffset().getOffsetTrim());
                    val_.setFile(current_.getFile());
                    mem_.addAnnotMethod(annot_,val_);
                    mem_.addNamed(annot_,val_);
                    mem_.addFct(annot_,val_);
                    procAnnotMember(current_, annot_, val_);
                }
                if (b instanceof InnerElementBlock) {
                    ExecInnerElementBlock val_ = _forwards.getInnerEltType((InnerElementBlock) b);
                    mem_.addInnerElementField((InnerElementBlock) b,val_);
                    mem_.addElementField((InnerElementBlock) b,val_);
                    current_.getAllStaticMembers().add(val_);
                }
                if (b instanceof ElementBlock) {
                    ExecElementBlock val_ = new ExecElementBlock(b.getOffset().getOffsetTrim(), new ExecElementContent(((ElementBlock) b).getElementContent()), ((ElementBlock) b).getTrOffset());
                    val_.setFile(current_.getFile());
                    mem_.addSimpleElementField((ElementBlock) b,val_);
                    mem_.addElementField((ElementBlock) b,val_);
                    current_.getAllStaticMembers().add(val_);
                }
                if (b instanceof FieldBlock) {
                    ExecFieldBlock val_ = new ExecFieldBlock(b.getOffset().getOffsetTrim(), ((FieldBlock) b).getFieldContent());
                    val_.setFile(current_.getFile());
                    mem_.addExplicitField((FieldBlock) b,val_);
                    chooseForField(current_, val_);
                }
                if (b instanceof ConstructorBlock) {
                    ExecConstructorBlock val_ = new ExecConstructorBlock(((ConstructorBlock)b).getName(), ((ConstructorBlock)b).isVarargs(), ((ConstructorBlock)b).getAccess(), ((ConstructorBlock)b).getParametersNames(), b.getOffset().getOffsetTrim(), ((ConstructorBlock)b).getImportedParametersTypes(), ((ConstructorBlock)b).getParametersRef());
                    val_.setFile(current_.getFile());
                    mem_.addCtor((ConstructorBlock) b,val_);
                    mem_.addFct((MemberCallingsBlock)b,val_);
                }
                if (b instanceof InstanceBlock) {
                    ExecInstanceBlock val_ = new ExecInstanceBlock(b.getOffset().getOffsetTrim());
                    val_.setFile(current_.getFile());
                    val_.setNumber(((InitBlock) b).getNumber());
                    mem_.addFct((MemberCallingsBlock)b,val_);
                    mem_.addInstInitBody((InstanceBlock)b,val_);
                    current_.getAllInstanceMembers().add(val_);
                    current_.getAllInstanceInits().add(val_);
                }
                if (b instanceof StaticBlock) {
                    ExecStaticBlock val_ = new ExecStaticBlock(b.getOffset().getOffsetTrim());
                    val_.setFile(current_.getFile());
                    val_.setNumber(((InitBlock) b).getNumber());
                    mem_.addFct((MemberCallingsBlock)b,val_);
                    mem_.addStatInitBody((StaticBlock)b,val_);
                    current_.getAllStaticMembers().add(val_);
                    current_.getAllStaticInits().add(val_);
                }
            }
            addFields(mem_);
        }
    }

    private static void addFields(Members _mem) {
        for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> e: _mem.getElementFields()) {
            _mem.addField(e.getKey(),e.getValue());
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> e: _mem.getExplicitFields()) {
            _mem.addField(e.getKey(),e.getValue());
        }
    }

    private static void procAnnotMember(ExecRootBlock _current, NamedCalledFunctionBlock _annot, ExecAnnotationMethodBlock _val) {
        OperationNode root_ = _annot.getRoot();
        if (root_ != null) {
            _current.getAllInstanceMembers().add(_val);
        }
    }

    private static void chooseForField(ExecRootBlock _current, ExecFieldBlock _val) {
        if (_val.isStaticField()) {
            _current.getAllStaticMembers().add(_val);
        } else {
            _current.getAllInstanceMembers().add(_val);
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
    private static void processExecFile(FileBlock _anaFile, ExecFileBlock _exeFile, Forwards _forwards) {
        for (AbsBk b: ClassesUtil.getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processAppend(_exeFile, r_, _forwards);
            }
            if (b instanceof OperatorBlock) {
                OperatorBlock r_ = (OperatorBlock) b;
                ExecOperatorBlock e_ = _forwards.getOperator(r_);
                _exeFile.appendChild(e_);
            }
        }
    }

    private static ExecAnonymousFunctionBlock buildExecAnonymousLambdaOperation(AnonymousLambdaOperation _s, Forwards _forwards) {
        ExecRootBlock declaring_ = FetchMemberUtil.fetchType(_s.getRootNumber(),_forwards);
//        ExecRootBlock declaring_ = _forwards.getMapMembers().getValue(_s.getRootNumber()).getRootBlock();
        NamedCalledFunctionBlock block_ = _s.getBlock();
        block_.setNumberLambda(_forwards.countAnonLambda());
        ExecAnonymousFunctionBlock fct_ = new ExecAnonymousFunctionBlock(block_.isRetRef(),block_.getName(), block_.isVarargs(), block_.getAccess(), block_.getParametersNames(), block_.getModifier(), block_.getOffset().getOffsetTrim(), new ExecAnonFctContent(block_.getAnaAnonFctContent()), block_.getImportedParametersTypes(), block_.getParametersRef());
        fct_.setParentType(declaring_);
        fct_.setOperator(FetchMemberUtil.fetchOperator(_s.getOperatorNumber(),_forwards));
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
        if (!block_.getInstanceTest().isEmpty()) {
            fct_ = new ExecSwitchInstanceMethod(retRef_, name_, kind_, parType_,block_.getOffset().getOffsetTrim(),retType_, anonFctContent_);
        } else if (block_.isEnumTest()) {
            fct_ = new ExecSwitchEnumMethod(retRef_, name_, kind_, parType_,block_.getOffset().getOffsetTrim(),retType_, anonFctContent_);
        } else {
            fct_ = new ExecSwitchValueMethod(retRef_, name_, kind_, parType_, block_.getOffset().getOffsetTrim(),retType_, anonFctContent_);
        }
        ExecRootBlock declaring_ = FetchMemberUtil.fetchType(_s.getRootNumber(),_forwards);
        fct_.setParentType(declaring_);
        fct_.setOperator(FetchMemberUtil.fetchOperator(_s.getOperatorNumber(),_forwards));
        _forwards.addSwitchMethod(block_,fct_);
        return fct_;
    }

    private static void buildExec(MemberCallingsBlock _from, ExecMemberCallingsBlock _dest, Coverage _coverage, Forwards _forwards) {
        ExecBracedBlock blockToWrite_ = _dest;
        ExecFileBlock fileDest_ = _dest.getFile();
        AbsBk firstChild_ = _from.getFirstChild();
        ExecDeclareVariable decl_ = null;
        _coverage.putBlockOperations(_from,_dest,_from);
        AbsBk en_ = _from;
        if (firstChild_ == null) {
            return;
        }
        while (true) {
            _coverage.putBlockOperations(_from,en_);
            AbsBk n_ = en_.getFirstChild();
            boolean visit_ = true;
            if (en_ instanceof BreakBlock) {
                ExecBreakBlock exec_ = new ExecBreakBlock(((BreakBlock) en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
            } else if (en_ instanceof CaseCondition) {
                ExecBracedBlock exec_;
                if (!((CaseCondition) en_).getInstanceTest().isEmpty()) {
                    if (((CaseCondition) en_).getImportedType().isEmpty()) {
                        exec_ = new ExecNullInstanceCaseCondition(((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                    } else {
                        exec_ = new ExecAbstractInstanceTypeCaseCondition(((CaseCondition)en_).getVariableName(), ((CaseCondition) en_).getImportedType(), ((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim(), true);
                    }
                } else {
                    getExecutableNodes(((CaseCondition)en_).getRoot(), _coverage, _forwards, en_);
                    if (((CaseCondition) en_).isBuiltEnum()) {
                        if (((CaseCondition) en_).isNullCaseEnum()) {
                            exec_ = new ExecNullCaseCondition(((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                        } else {
                            exec_ = new ExecEnumCaseCondition(((CaseCondition) en_).getValue(), ((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                        }
                    } else {
                        Argument argument_ = Argument.getNullableValue(((CaseCondition) en_).getArgument());
                        if (!argument_.isNull()) {
                            exec_ = new ExecStdCaseCondition(((CaseCondition) en_).getValueOffset(), argument_, en_.getOffset().getOffsetTrim());
                        } else {
                            exec_ = new ExecNullCaseCondition(((CaseCondition) en_).getValueOffset(), en_.getOffset().getOffsetTrim());
                        }
                    }
                }
                SwitchBlock par_ = ((CaseCondition) en_).getSwitchParent();
                if (par_ != null) {
                    _coverage.putBlockOperationsSwitchsPart(_from, par_, (CaseCondition) en_, exec_);
                }
                SwitchMethodBlock met_ = ((CaseCondition)en_).getSwitchMethod();
                if (met_ != null) {
                    _coverage.putBlockOperationsSwitchsMethodPart(met_, (CaseCondition) en_, exec_);
                }
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof CatchEval) {
                ExecCatchEval exec_ = new ExecCatchEval(((CatchEval)en_).getVariableName(), ((CatchEval)en_).getImportedClassName(), en_.getOffset().getOffsetTrim());
                _coverage.putCatches(_from,(CatchEval)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof IfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecIfCondition(((ConditionBlock) en_).getConditionOffset(), ((IfCondition) en_).getLabel(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(_from,(ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseIfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecElseIfCondition(((ConditionBlock) en_).getConditionOffset(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(_from,(ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof WhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecWhileCondition(((ConditionBlock) en_).getConditionOffset(), ((WhileCondition) en_).getLabel(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(_from,(ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof DoWhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecDoWhileCondition(((ConditionBlock) en_).getConditionOffset(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(_from,(ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
            } else if (en_ instanceof DoBlock) {
                ExecDoBlock exec_ = new ExecDoBlock(((DoBlock)en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ContinueBlock) {
                ExecContinueBlock exec_ = new ExecContinueBlock(((ContinueBlock) en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
            } else if (en_ instanceof DeclareVariable) {
                if (((DeclareVariable) en_).isRefVariable()) {
                    ExecRefDeclareVariable exec_ = new ExecRefDeclareVariable(en_.getOffset().getOffsetTrim(), ((DeclareVariable)en_).getVariableNames());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(_from,exec_,en_);
                } else {
                    ExecDeclareVariable exec_ = new ExecDeclareVariable(((DeclareVariable) en_).getImportedClassName(),((DeclareVariable)en_).getVariableNames(), en_.getOffset().getOffsetTrim());
                    decl_ = exec_;
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(_from,exec_,en_);
                }
            } else if (en_ instanceof DefaultCondition) {
                ExecBracedBlock exec_;
                String instanceTest_ = ((DefaultCondition)en_).getInstanceTest();
                if (instanceTest_.isEmpty()) {
                    exec_ = new ExecDefaultCondition(en_.getOffset().getOffsetTrim());
                } else {
                    exec_ = new ExecAbstractInstanceTypeCaseCondition(((DefaultCondition)en_).getVariableName(), instanceTest_, ((DefaultCondition)en_).getVariableOffset(), en_.getOffset().getOffsetTrim(), false);
                }
                SwitchBlock b_ = ((DefaultCondition)en_).getSwitchParent();
                if (b_ != null) {
                    _coverage.putBlockOperationsSwitchsPart(_from, b_, (DefaultCondition) en_, exec_);
                }
                SwitchMethodBlock met_ = ((DefaultCondition)en_).getSwitchMethod();
                if (met_ != null) {
                    _coverage.putBlockOperationsSwitchsMethodPart(met_, (DefaultCondition) en_, exec_);
                }
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseCondition) {
                ExecElseCondition exec_ = new ExecElseCondition(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Line) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((Line)en_).getRoot(), _coverage, _forwards, en_);
                if (decl_ != null) {
                    decl_.setImportedClassName(((Line) en_).getImportedClass());
                }
                decl_ = null;
                ExecLine exec_ = new ExecLine(((Line) en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
            } else if (en_ instanceof EmptyInstruction) {
                ExecEmptyInstruction exec_ = new ExecEmptyInstruction(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
            } else if (en_ instanceof FinallyEval) {
                ExecFinallyEval exec_ = new ExecFinallyEval(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachLoop) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachLoop)en_).getRoot(), _coverage, _forwards, en_);
                ExecAbstractForEachLoop exec_;
                if (((ForEachLoop)en_).getRoot().getResultClass().isArray()) {
                    if (((ForEachLoop)en_).isRefVariable()) {
                        exec_ = new ExecForEachRefArray(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                                ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                    } else {
                        exec_ = new ExecForEachArray(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                                ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                    }
                } else {
                    exec_ = new ExecForEachIterable(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                            ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                }
                _coverage.putBlockOperationsLoops(_from,(AbstractForLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachTable) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachTable)en_).getRoot(), _coverage, _forwards, en_);
                ExecForEachTable exec_ = new ExecForEachTable(((ForEachTable) en_).getLabel(), ((ForEachTable)en_).getImportedClassNameFirst(),
                        ((ForEachTable)en_).getImportedClassNameSecond(),
                        ((ForEachTable)en_).getImportedClassIndexName(), ((ForEachTable)en_).getVariableNameFirst(),
                        ((ForEachTable)en_).getVariableNameSecond(), ((ForEachTable)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                _coverage.putBlockOperationsLoops(_from,(AbstractForLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForIterativeLoop) {
                CustList<ExecOperationNode> init_ = getExecutableNodes(((ForIterativeLoop)en_).getRootInit(), _coverage, _forwards, en_);
                CustList<ExecOperationNode> exp_ = getExecutableNodes(((ForIterativeLoop)en_).getRootExp(), _coverage, _forwards, en_);
                CustList<ExecOperationNode> step_ = getExecutableNodes(((ForIterativeLoop)en_).getRootStep(), _coverage, _forwards, en_);
                ExecForIterativeLoop exec_ = new ExecForIterativeLoop(((ForIterativeLoop) en_).getLabel(), ((ForIterativeLoop)en_).getImportedClassName(),
                        ((ForIterativeLoop)en_).getImportedClassIndexName(), ((ForIterativeLoop)en_).getVariableName(), ((ForIterativeLoop)en_).getVariableNameOffset(), ((ForIterativeLoop)en_).getInitOffset(),
                        ((ForIterativeLoop)en_).getExpressionOffset(), ((ForIterativeLoop)en_).getStepOffset(), ((ForIterativeLoop)en_).isEq(),init_,exp_,step_, en_.getOffset().getOffsetTrim());
                _coverage.putBlockOperationsLoops(_from,(AbstractForLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForMutableIterativeLoop) {
                CustList<ExecOperationNode> init_;
                OperationNode rInit_ = ((ForMutableIterativeLoop) en_).getRootInit();
                if (rInit_ == null) {
                    init_ = new CustList<ExecOperationNode>();
                } else {
                    init_ = getExecutableNodes(rInit_, _coverage, _forwards, en_);
                }
                CustList<ExecOperationNode> exp_;
                OperationNode rExp_ = ((ForMutableIterativeLoop) en_).getRootExp();
                if (rExp_ == null) {
                    exp_ = new CustList<ExecOperationNode>();
                } else {
                    exp_ = getExecutableNodes(rExp_, _coverage, _forwards, en_);
                }
                OperationNode rStep_ = ((ForMutableIterativeLoop) en_).getRootStep();
                CustList<ExecOperationNode> step_;
                if (rStep_ == null) {
                    step_ = new CustList<ExecOperationNode>();
                } else {
                    step_ = getExecutableNodes(rStep_, _coverage, _forwards, en_);
                }
                ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(((ForMutableIterativeLoop) en_).getLabel(), ((ForMutableIterativeLoop) en_).getImportedClassName(), ((ForMutableIterativeLoop) en_).getImportedClassIndexName(),
                        ((ForMutableIterativeLoop) en_).getVariableNames(), ((ForMutableIterativeLoop) en_).getInitOffset(), ((ForMutableIterativeLoop) en_).getExpressionOffset(), ((ForMutableIterativeLoop) en_).getStepOffset(),
                        init_,exp_,step_, en_.getOffset().getOffsetTrim(),((ForMutableIterativeLoop) en_).isRefVariable());
                _coverage.putBlockOperationsConditionsForMutable(_from,(ForMutableIterativeLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof NullCatchEval) {
                ExecNullCatchEval exec_ = new ExecNullCatchEval(en_.getOffset().getOffsetTrim());
                _coverage.putCatches(_from,(NullCatchEval)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ReturnMethod) {
                OperationNode r_ = ((ReturnMethod) en_).getRoot();
                if (r_ == null) {
                    ExecReturnMethod exec_ = new ExecReturnMethod(((ReturnMethod) en_).getExpressionOffset(),null, ((ReturnMethod) en_).getReturnType(), en_.getOffset().getOffsetTrim());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(_from,exec_,en_);
                } else {
                    CustList<ExecOperationNode> op_ = getExecutableNodes(r_, _coverage, _forwards, en_);
                    ExecReturnMethod exec_ = new ExecReturnMethod(((ReturnMethod) en_).getExpressionOffset(),op_, ((ReturnMethod) en_).getReturnType(), en_.getOffset().getOffsetTrim());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _coverage.putBlockOperations(_from,exec_,en_);
                }
            } else if (en_ instanceof SwitchBlock) {
                AbsBk first_ = en_.getFirstChild();
                boolean emp_ = first_ == null;
                CustList<ExecOperationNode> op_ = getExecutableNodes(((SwitchBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecBracedBlock exec_;
                if (!((SwitchBlock) en_).getInstanceTest().isEmpty()) {
                    exec_ = new ExecInstanceSwitchBlock(((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_, en_.getOffset().getOffsetTrim());
                } else if (((SwitchBlock) en_).isEnumTest()) {
                    exec_ = new ExecEnumSwitchBlock(((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_, en_.getOffset().getOffsetTrim());
                } else {
                    exec_ = new ExecStdSwitchBlock(((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_, en_.getOffset().getOffsetTrim());
                }
                _coverage.putBlockOperationsSwitchs(_from,(SwitchBlock)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                if (!emp_) {
                    blockToWrite_ = exec_;
                }
            } else if (en_ instanceof TryEval) {
                ExecTryEval exec_ = new ExecTryEval(((TryEval) en_).getLabel(), en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Throwing) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((Throwing) en_).getRoot(), _coverage, _forwards, en_);
                ExecThrowing exec_ = new ExecThrowing(((Throwing)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
            } else if (en_ instanceof UnclassedBracedBlock) {
                ExecUnclassedBracedBlock exec_ = new ExecUnclassedBracedBlock(en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(_from,exec_,en_);
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
                BracedBlock par_ = en_.getParent();
                if (par_ == _from) {
                    return;
                }
                blockToWrite_ = blockToWrite_.getParent();
                en_ = par_;
            }
        }
    }

    private static CustList<ExecOperationNode> getExecutableNodes(OperationNode _root, Coverage _coverage, Forwards _forwards, AbsBk _currentBlock) {
        return getExecutableNodes(-1,-1,_root,_coverage,_forwards, PutCoveragePhase.NORMAL, _currentBlock);
    }
    private static CustList<ExecOperationNode> getExecutableNodes(int _indexAnnotGroup, int _indexAnnot, OperationNode _root, Coverage _coverage, Forwards _forwards, PutCoveragePhase _phase, AbsBk _currentBlock) {
        OperationNode current_ = _root;
        ExecOperationNode exp_ = createExecOperationNode(current_, _forwards);
        setImplicits(exp_, current_, _forwards);
        ExecOperationNode expRoot_ = exp_;
        _coverage.putBlockOperation(_indexAnnotGroup, _indexAnnot, _phase, _forwards, _currentBlock, current_,exp_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (hasToCreateExec(exp_, op_)) {
                ExecOperationNode loc_ = createExecOperationNode(op_, _forwards);
                setImplicits(loc_, op_, _forwards);
                _coverage.putBlockOperation(_indexAnnotGroup, _indexAnnot, _phase, _forwards, _currentBlock, op_,loc_);
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
                    _coverage.putBlockOperation(_indexAnnotGroup, _indexAnnot, _phase, _forwards, _currentBlock, op_,loc_);
                    par_.appendChild(loc_);
                    tryInitIntermediate(exp_, op_, loc_);
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                if (par_ == null) {
                    current_ = null;
                    continue;
                }
                op_ = current_.getParent();
                if (op_ == _root) {
                    tryInitSettablePart(par_);
                    current_ = null;
                    continue;
                }
                current_ = op_;
                exp_ = par_;
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
        if (_exp instanceof ExecAffectationOperation) {
            ((ExecAffectationOperation) _exp).setup();
        }
        if (_exp instanceof ExecSemiAffectationOperation) {
            ((ExecSemiAffectationOperation) _exp).setup();
        }
        if (_exp instanceof ExecCompoundAffectationOperation) {
            ((ExecCompoundAffectationOperation) _exp).setup();
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
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new ExecConstLeafOperation(false, new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof AnnotationInstanceOperation) {
            AnnotationInstanceOperation n_ = (AnnotationInstanceOperation) _anaNode;
            return new ExecAnnotationInstanceOperation(FetchMemberUtil.fetchType(n_.getRootNumber(), _forwards), new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInstancingAnnotContent(n_.getInstancingAnnotContent()));
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
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(a_.getMemberId(), _forwards);
            if (ex_ instanceof ExecAnnotationBlock) {
                return new ExecAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(a_.getCallFctContent()));
            }
            ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(a_.getMemberId(), _forwards);
            pair_ = FetchMemberUtil.defPair(ex_, pair_);
            if (a_.isTrueFalse()) {
                return new ExecExplicitOperation(
                        pair_,
                        new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent()));
            }
            if (a_.isStaticMethod()) {
                return new ExecStaticFctOperation(pair_, new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctContent(a_.getFunction(),a_.getCallFctContent()), new ExecArrContent(a_.getArrContent()));
            }
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor n_ = (InterfaceFctConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), n_.getClassName(), FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof InterfaceInvokingConstructor) {
            InterfaceInvokingConstructor n_ = (InterfaceInvokingConstructor) _anaNode;
            return new ExecInterfaceInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof CurrentInvokingConstructor) {
            CurrentInvokingConstructor n_ = (CurrentInvokingConstructor) _anaNode;
            return new ExecCurrentInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof SuperInvokingConstructor) {
            SuperInvokingConstructor n_ = (SuperInvokingConstructor) _anaNode;
            return new ExecSuperInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
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
        if (_anaNode instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards);
            if (typeCtor_ != null) {
                return new ExecStandardInstancingOperation(typeCtor_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), s_.isNewBefore(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), new ExecInstancingStdContent(s_.getInstancingStdContent()));
            }
            return new ExecDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()));
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            return new ExecAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), s_.isNewBefore(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            ExecTypeFunction get_ = FetchMemberUtil.fetchOvTypeFunction(a_.getMemberIdGet(), _forwards);
            ExecTypeFunction set_ = FetchMemberUtil.fetchOvTypeFunction(a_.getMemberIdSet(), _forwards);
            boolean cust_ = true;
            if (get_ == null) {
                cust_ = false;
            }
            if (set_ == null) {
                cust_ = false;
            }
            if (cust_) {
                return new ExecCustArrOperation(get_,set_, new ExecOperationContent(a_.getContent()), a_.isIntermediateDottedOperation(), new ExecArrContent(a_.getArrContent()), new ExecInstFctContent(a_.getCallFctContent(), a_.getAnc(), a_.isStaticChoiceMethod()));
            }
            return new ExecArrOperation(new ExecOperationContent(a_.getContent()), a_.isIntermediateDottedOperation(), new ExecArrContent(a_.getArrContent()));
        }
        if (_anaNode instanceof SwitchOperation) {
            SwitchOperation s_ = (SwitchOperation) _anaNode;
            SwitchMethodBlock switchMethod_ = s_.getSwitchMethod();
            ExecAbstractSwitchMethod r_ = _forwards.getSwitchMethod(switchMethod_);
            ExecRootBlock type_ = FetchMemberUtil.fetchType(s_.getRootNumber(), _forwards);
            return new ExecSwitchOperation(new ExecOperationContent(s_.getContent()),type_,r_, new ExecArrContent(s_.getArrContent()));
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
        if (_anaNode instanceof AbstractRefTernaryOperation) {
            AbstractRefTernaryOperation t_ = (AbstractRefTernaryOperation) _anaNode;
            return new ExecRefTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal(),new ExecArrContent(t_.getArrContent()));
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(c_.getMemberId(), _forwards);
            return new ExecChoiceFctOperation(p_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true), new ExecArrContent(c_.getArrContent()));
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(s_.getMemberId(), _forwards);
            return new ExecSuperFctOperation(p_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true), new ExecArrContent(s_.getArrContent()));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(f_.getMemberId(), _forwards);
            return new ExecFctOperation(p_, new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), new ExecInstFctContent(f_.getCallFctContent(), f_.getAnc(), f_.isStaticChoiceMethod()), new ExecArrContent(f_.getArrContent()));
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

            NamedCalledFunctionBlock method_ = s_.getBlock();
            ExecAnonymousFunctionBlock r_ = _forwards.getAnonLambda(method_);
            ExecTypeFunction pair_ = new ExecTypeFunction(FetchMemberUtil.fetchType(s_.getRootNumber(),_forwards), r_);
//            ExecTypeFunction pair_ = new ExecTypeFunction(_forwards.getMapMembers().getValue(s_.getRootNumber()).getRootBlock(), r_);
            return new ExecAnonymousLambdaOperation(new ExecOperationContent(s_.getContent()), new ExecLambdaCommonContent(s_.getLambdaCommonContent()), new ExecLambdaAnoContent(s_.getLambdaAnoContent()), pair_);
        }
        if (_anaNode instanceof LambdaOperation) {
            LambdaOperation f_ = (LambdaOperation) _anaNode;
            if (f_.getStandardMethod() != null) {
                return new ExecStdMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), f_.getMethod(), f_.getStandardMethod());
            }
            if (f_.getStandardType() != null) {
                return new ExecStdConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), f_.getRealId(), f_.getStandardType());
            }
            if (f_.isRecordType()) {
                ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(f_.getLambdaMemberNumberContentId().getRootNumber(), _forwards);
                return new ExecRecordConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), rootBlock_, f_.getInfos());
            }
            if (f_.getMethod() == null && f_.getRealId() == null) {
                return new ExecFieldLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaFieldContent(f_.getFieldId(), f_.getLambdaFieldContent(), f_.getLambdaMemberNumberContentId(), _forwards));
            }
            if (f_.getMethod() == null) {
                ExecLambdaConstructorContent lambdaConstructorContent_ = new ExecLambdaConstructorContent(f_.getRealId(), f_.getLambdaMemberNumberContentId(), _forwards);
                ExecTypeFunction pair_ = lambdaConstructorContent_.getPair();
                if (pair_ != null) {
                    return new ExecTypeConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), lambdaConstructorContent_, pair_);
                }
                return new ExecConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()));
            }
            ExecLambdaMethodContent lambdaMethodContent_ = new ExecLambdaMethodContent(f_.getMethod(), f_.getLambdaMethodContent(), f_.getLambdaMemberNumberContentId(), _forwards);
            ExecTypeFunction pair_ = lambdaMethodContent_.getPair();
            if (pair_ != null) {
                return new ExecCustMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), lambdaMethodContent_, pair_);
            }
            ExecRootBlock declaring_ = lambdaMethodContent_.getDeclaring();
            if (declaring_ != null) {
                return new ExecEnumMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), lambdaMethodContent_, declaring_);
            }
            if (lambdaMethodContent_.getFunction() != null) {
                return new ExecOperatorMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), lambdaMethodContent_);
            }
            if (lambdaMethodContent_.isDirectCast()) {
                return new ExecCastMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), lambdaMethodContent_);
            }
            if (lambdaMethodContent_.isClonedMethod()) {
                return new ExecCloneMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), lambdaMethodContent_);
            }
            return new ExecMethodLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), lambdaMethodContent_);
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
            return new ExecSettableFieldOperation(FetchMemberUtil.fetchType(s_.getMemberId(), _forwards), new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()), new ExecSettableOperationContent(s_.getSettableFieldContent()));
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
        if (_anaNode instanceof RefVariableOperation) {
            RefVariableOperation m_ = (RefVariableOperation) _anaNode;
            return new ExecStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()),m_.isDeclare());
        }
        if (_anaNode instanceof RefParamOperation) {
            RefParamOperation m_ = (RefParamOperation) _anaNode;
            return new ExecRefParamOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
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
            ExecRootBlock type_ = FetchMemberUtil.fetchType(m_.getMemberId(), _forwards);
            return new ExecExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getFunction(),m_.getCallFctContent()), m_.getOffsetOper(), FetchMemberUtil.fetchFunctionOp(type_,m_.getMemberId(), _forwards), type_, new ExecArrContent(m_.getArrContent()));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            ExecRootBlock type_ = FetchMemberUtil.fetchType(m_.getMemberId(), _forwards);
            return new ExecSemiAffectationOperation(new ExecOperationContent(m_.getContent()), new ExecStaticPostEltContent(m_.getFunction(),m_.getClassName(), m_.isPost()), new ExecOperatorContent(m_.getOperatorContent()), FetchMemberUtil.fetchFunctionOp(type_,m_.getMemberId(), _forwards), type_, FetchMemberUtil.fetchImplicits(m_.getConverterFrom(), m_.getMemberIdFrom(), _forwards), FetchMemberUtil.fetchImplicits(m_.getConverterTo(), m_.getMemberIdTo(), _forwards));
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (n_.getFunction() != null) {
                ExecRootBlock type_ = FetchMemberUtil.fetchType(n_.getMemberId(), _forwards);
                return new ExecCustNumericOperation(FetchMemberUtil.fetchFunctionOp(type_,n_.getMemberId(), _forwards), type_, new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), new ExecStaticEltContent(n_.getFunction(), n_.getClassName()));
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
                    FetchMemberUtil.fetchOvTypeFunction(m_.getMemberId(),_forwards),
                    new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return new ExecImplicitOperation(
                    new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()), FetchMemberUtil.fetchOvTypeFunction(m_.getMemberId(), _forwards));
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
            ExecRootBlock type_ = FetchMemberUtil.fetchType(c_.getMemberId(), _forwards);
            return new ExecAndOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getFunction(),c_.getClassName()), FetchMemberUtil.fetchFunctionOp(type_,c_.getMemberId(), _forwards), type_, FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getMemberConverter(), _forwards),c_.getOpOffset());
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            ExecRootBlock type_ = FetchMemberUtil.fetchType(c_.getMemberId(), _forwards);
            return new ExecOrOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getFunction(),c_.getClassName()), FetchMemberUtil.fetchFunctionOp(type_,c_.getMemberId(), _forwards), type_, FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getMemberConverter(), _forwards),c_.getOpOffset());
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation c_ = (NullSafeOperation) _anaNode;
            return new ExecNullSafeOperation(new ExecOperationContent(c_.getContent()),c_.getOpOffset());
        }
        if (_anaNode instanceof AssocationOperation) {
            AssocationOperation c_ = (AssocationOperation) _anaNode;
            return new ExecAssocationOperation(new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _anaNode;
            ExecRootBlock type_ = FetchMemberUtil.fetchType(c_.getMemberId(), _forwards);
            return new ExecCompoundAffectationOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), new ExecStaticEltContent(c_.getFunction(),c_.getClassName()), FetchMemberUtil.fetchFunctionOp(type_,c_.getMemberId(), _forwards), type_, FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getMemberIdConv(), _forwards));
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new ExecAffectationOperation(new ExecOperationContent(a_.getContent()), a_.getOpOffset());
        }
        return new ExecDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
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
        CustList<ExecOperationNode> ops_ = getExecutableNodes(0,0,root_, _coverage, _forwards, PutCoveragePhase.NORMAL, _ana);
        _exec.setOpValue(ops_);
    }

    private static void validateIds(Members _mem) {
        for (EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> e: _mem.getMethods()) {
            e.getValue().setImportedReturnType(e.getKey().getImportedReturnType());
            String returnTypeGet_ = e.getKey().getReturnTypeGet();
            if (!returnTypeGet_.isEmpty()) {
                e.getValue().setImportedReturnType(returnTypeGet_);
            }
        }
        for (EntryCust<ConstructorBlock,ExecConstructorBlock> e: _mem.getCtors()) {
            e.getValue().setImportedReturnType(e.getKey().getImportedReturnType());
        }
        for (EntryCust<NamedCalledFunctionBlock, ExecAnnotationMethodBlock> e: _mem.getAnnotMethods()) {
            NamedCalledFunctionBlock key1_ = e.getKey();
            e.getValue().setImportedReturnType(key1_.getImportedReturnType());
            e.getValue().getImportedParametersTypes().addAllElts(key1_.getImportedParametersTypes());
        }
        for (EntryCust<InnerElementBlock, ExecInnerElementBlock> e: _mem.getInnerElementFields()) {
            buildImportedTypes(e.getValue(),e.getKey());
        }
        for (EntryCust<ElementBlock, ExecElementBlock> e: _mem.getSimpleElementFields()) {
            buildImportedTypes(e.getValue(),e.getKey());
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> e: _mem.getExplicitFields()) {
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
    private static void fwdAnnotationsParameters(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<CustList<ExecOperationNode>>> ops_ = new CustList<CustList<CustList<ExecOperationNode>>>();
        int i_ = 0;
        for (CustList<OperationNode> l: _ana.getRootsList()) {
            CustList<CustList<ExecOperationNode>> annotation_;
            annotation_ = new CustList<CustList<ExecOperationNode>>();
            _coverage.putBlockOperationsAnnotMethodParam(_ana);
            int j_ = 0;
            for (OperationNode r: l) {
                _coverage.putBlockOperationsAnnotMethod(_ana,i_);
                annotation_.add(getExecutableNodes(i_,j_,r, _coverage, _forwards, PutCoveragePhase.ANNOTATION, _ana));
                j_++;
            }
            ops_.add(annotation_);
            i_++;
        }
        _ann.getAnnotationsOpsParams().addAllElts(ops_);
    }
    private static void fwdAnnotationsParametersSw(SwitchMethodBlock _ana, ExecAbstractSwitchMethod _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<CustList<ExecOperationNode>>> ops_ = new CustList<CustList<CustList<ExecOperationNode>>>();
        int i_ = 0;
        for (CustList<OperationNode> l: _ana.getRootsList()) {
            CustList<CustList<ExecOperationNode>> annotation_;
            annotation_ = new CustList<CustList<ExecOperationNode>>();
            _coverage.putBlockOperationsAnnotMethodParam(_ana);
            int j_ = 0;
            for (OperationNode r: l) {
                _coverage.putBlockOperationsAnnotMethod(_ana,i_);
                annotation_.add(getExecutableNodes(i_,j_,r, _coverage, _forwards, PutCoveragePhase.ANNOTATION, _ana));
                j_++;
            }
            ops_.add(annotation_);
            i_++;
        }
        _ann.getAnnotationsOpsParams().addAllElts(ops_);
    }

    private static void fwdAnnotations(InnerTypeOrElement _ana, ExecInnerTypeOrElement _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotField(_ana);
            ops_.add(getExecutableNodes(-1,i_,r, _coverage, _forwards, PutCoveragePhase.ANNOTATION, (AbsBk)_ana));
            i_++;
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(FieldBlock _ana, ExecFieldBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotField(_ana);
            ops_.add(getExecutableNodes(-1,i_,r, _coverage, _forwards, PutCoveragePhase.ANNOTATION, _ana));
            i_++;
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static CustList<ExecOperationNode> processField(InfoBlock _ana, ExecBlock _exec, Coverage _coverage, Forwards _forwards, OperationNode _root) {
        _coverage.putBlockOperationsField((AbsBk)_ana);
        _coverage.putBlockOperationsField(_exec, (AbsBk)_ana);
        return getExecutableNodes(0,-1,_root, _coverage, _forwards, PutCoveragePhase.NORMAL, (AbsBk)_ana);
    }
    private static void fwdAnnotations(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ex, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotMethod(_ana);
            ops_.add(getExecutableNodes(-1,i_,r, _coverage, _forwards, PutCoveragePhase.ANNOTATION, _ana));
            i_++;
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }
    private static void fwdAnnotationsSw(SwitchMethodBlock _ana, ExecAbstractSwitchMethod _ex, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotMethod(_ana);
            ops_.add(getExecutableNodes(-1,i_,r, _coverage, _forwards, PutCoveragePhase.ANNOTATION, _ana));
            i_++;
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(RootBlock _ana, ExecRootBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotType(_ana);
            ops_.add(getExecutableNodes(-1,i_,r, _coverage, _forwards, PutCoveragePhase.ANNOTATION, _ana));
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
            while (true) {
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
                    break;
                }
                if (op_ == _root) {
                    op_.setOrder(out_.size());
                    out_.add(op_);
                    current_ = null;
                    break;
                }
                current_ = op_;
            }
        }
        return out_;
    }
}
