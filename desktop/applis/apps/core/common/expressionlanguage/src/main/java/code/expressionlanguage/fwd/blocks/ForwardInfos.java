package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.Argument;
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
import code.expressionlanguage.common.ClassField;
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
        for (RootBlock r: _page.getAllFoundTypes()) {
            Members v_ = new Members();
            FileBlock fileBlock_ = r.getFile();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
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
                    override_.put(g.getKey(), new ExecFormattedRootBlock(memTarget_.getRootBlock(),value_.getGeneString()), new ExecTypeFunction(memTarget_.getRootBlock(),memTarget_.getOvNamed(value_.getBlock())));
                }
                redirections_.add(override_);
            }
        }
        feed(_forwards);
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
            ExecFormattedRootBlock formattedType_ = null;
            Members mem_ = e.getValue();
            if (i instanceof UniqueRootedBlock && genericClasses_.size() > 1) {
                AnaFormattedRootBlock anaFormattedRootBlock_ = genericClasses_.get(1);
                mem_.getRootBlock().setUniqueType(FetchMemberUtil.fetchType(anaFormattedRootBlock_.getRootBlock().getNumberAll(), _forwards));
                formattedType_ = FetchMemberUtil.fwdFormatType(anaFormattedRootBlock_,_forwards);
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
                l_.add(FetchMemberUtil.fwdFormatType(s,_forwards));
            }
            e.getValue().getRootBlock().getAllGenericSuperTypes().addAllElts(l_);
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock root_ = e.getKey();
            if (!root_.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(root_));
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
                                ExecOverridableBlock value_ = mem_.getOvNamed(b);
                                String ret_ = b.getImportedReturnType();
                                e.getValue().getRootBlock().getFunctionalBodies().add(new ExecFunctionalInfo(FetchMemberUtil.formatType(s,value_.getId()),FetchMemberUtil.formatType(s,ret_), value_,_context));
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
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            for (EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> f: mem_.getOvNamed()) {
                NamedCalledFunctionBlock method_ =  f.getKey();
                coverage_.putCallsNamedCalledFunctionBlock(c);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> f: mem_.getCtors()) {
                ConstructorBlock method_ =  f.getKey();
                coverage_.putCallsConstructorBlock(c);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<InstanceBlock, ExecInstanceBlock> f: mem_.getInstInitBodies()) {
                InstanceBlock method_ =  f.getKey();
                coverage_.putCallsInstanceBlock(c);
                _forwards.addFctBody(method_,f.getValue());
            }
            for (EntryCust<StaticBlock, ExecStaticBlock> f: mem_.getStatInitBodies()) {
                StaticBlock method_ =  f.getKey();
                coverage_.putCallsStaticBlock(c);
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
        for (EntryCust<RootBlock, Members> e: _forwards.getMembers()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            for (EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> a: mem_.getNamed()) {
                NamedFunctionBlock b = a.getKey();
                ExecNamedFunctionBlock d = a.getValue();
                fwdAnnotations(b, d, coverage_, _forwards);
                fwdAnnotationsParameters(b, d, coverage_, _forwards);
            }
            for (EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> a: mem_.getOvNamed()) {
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
            ExecRootBlock value_ = e.getValue().getRootBlock();
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
            ExecRootBlock current_ = r.getValue().getRootBlock();
            RootBlock k_ = r.getKey();
            Members mem_ = r.getValue();
            buildFieldInfos(_forwards, current_, k_, mem_);
            buildFctInfos(current_, k_, mem_);
            IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> ovNamed_;
            ovNamed_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
            for(EntryCust<NamedCalledFunctionBlock,ExecOverridableBlock> e: mem_.getOvNamed()) {
                ovNamed_.addEntry(e.getKey(),e.getValue());
            }
            IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> named_;
            named_ = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
            for(EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> e: mem_.getNamed()) {
                named_.addEntry(e.getKey(),e.getValue());
            }
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
            int firstOff_ = first_.getOffset();
            int secondOff_ = second_.getOffset();
            if (firstOff_ < secondOff_) {
                out_.addEntry(first_,_firstList.getValue(i_));
                i_++;
            } else {
                out_.addEntry(second_,_secondList.getValue(j_));
                j_++;
            }
        }
        if (i_ < _firstList.size()){
            for (int k = i_; k < firstLen_; k++){
                out_.addEntry(_firstList.getKey(k),_firstList.getValue(k));
            }
        } else {
            for (int k = j_; k < secondLen_; k++){
                out_.addEntry(_secondList.getKey(k),_secondList.getValue(k));
            }
        }
        return out_;
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
            int firstOff_ = first_.getOffset();
            int secondOff_ = second_.getOffset();
            if (firstOff_ < secondOff_) {
                out_.addEntry(first_, _firstList.getValue(i_));
                i_++;
            } else {
                out_.addEntry(second_, _secondList.getValue(j_));
                j_++;
            }
        }
        if (i_ < _firstList.size()){
            for (int k = i_; k < firstLen_; k++){
                out_.addEntry(_firstList.getKey(k),_firstList.getValue(k));
            }
        } else {
            for (int k = j_; k < secondLen_; k++){
                out_.addEntry(_secondList.getKey(k),_secondList.getValue(k));
            }
        }
        return out_;
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
        ExecBracedBlock blockToWrite_ = _dest;
        ExecFileBlock fileDest_ = _dest.getFile();
        AbsBk firstChild_ = _from.getFirstChild();
        _coverage.putBlockOperationsCaller(_dest,_from);
        AbsBk en_ = _from;
        if (firstChild_ == null) {
            return;
        }
        while (true) {
            _coverage.putBlockOperationsPre(_from,en_);
            AbsBk n_ = en_.getFirstChild();
            boolean visit_ = true;
            if (en_ instanceof BreakBlock) {
                ExecBreakBlock exec_ = new ExecBreakBlock(((BreakBlock) en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof CaseCondition) {
                ExecBracedBlock exec_ = buildCaseCondition(_coverage, _forwards, en_);
                SwitchBlock par_ = ((CaseCondition) en_).getSwitchParent();
                if (par_ != null) {
                    _coverage.putBlockOperationsSwitchsPart(par_, (CaseCondition) en_, exec_);
                }
                SwitchMethodBlock met_ = ((CaseCondition)en_).getSwitchMethod();
                if (met_ != null) {
                    _coverage.putBlockOperationsSwitchsMethodPart((CaseCondition) en_, exec_);
                }
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof CatchEval) {
                ExecCatchEval exec_ = new ExecCatchEval(((CatchEval)en_).getVariableName(), ((CatchEval)en_).getImportedClassName());
                _coverage.putCatches((CatchEval)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof IfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecIfCondition(((ConditionBlock) en_).getConditionOffset(), ((IfCondition) en_).getLabel(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions((ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseIfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecElseIfCondition(((ConditionBlock) en_).getConditionOffset(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions((ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof WhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecWhileCondition(((ConditionBlock) en_).getConditionOffset(), ((WhileCondition) en_).getLabel(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions((ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof DoWhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((ConditionBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecDoWhileCondition(((ConditionBlock) en_).getConditionOffset(), opCondition_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions((ConditionBlock)en_, exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof DoBlock) {
                ExecDoBlock exec_ = new ExecDoBlock(((DoBlock)en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ContinueBlock) {
                ExecContinueBlock exec_ = new ExecContinueBlock(((ContinueBlock) en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof DeclareVariable) {
                ExecDeclareVariable exec_ = new ExecDeclareVariable(((DeclareVariable) en_).getImportedClassName(),((DeclareVariable)en_).getVariableNames());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof DefaultCondition) {
                ExecBracedBlock exec_ = buildDefaultCondition((DefaultCondition) en_);
                SwitchBlock b_ = ((DefaultCondition)en_).getSwitchParent();
                if (b_ != null) {
                    _coverage.putBlockOperationsSwitchsPart(b_, (DefaultCondition) en_, exec_);
                }
                SwitchMethodBlock met_ = ((DefaultCondition)en_).getSwitchMethod();
                if (met_ != null) {
                    _coverage.putBlockOperationsSwitchsMethodPart((DefaultCondition) en_, exec_);
                }
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseCondition) {
                ExecElseCondition exec_ = new ExecElseCondition();
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Line) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((Line)en_).getRoot(), _coverage, _forwards, en_);
                ExecLine exec_ = new ExecLine(((Line) en_).isCallSuper(),((Line) en_).isCallInts(), new ExecOperationNodeListOff(op_, ((Line) en_).getExpressionOffset()));
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof EmptyInstruction) {
                ExecEmptyInstruction exec_ = new ExecEmptyInstruction();
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof FinallyEval) {
                ExecFinallyEval exec_ = new ExecFinallyEval();
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachLoop) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachLoop)en_).getRoot(), _coverage, _forwards, en_);
                ExecAbstractForEachLoop exec_;
                if (((ForEachLoop)en_).getRoot().getResultClass().isArray()) {
                    if (((ForEachLoop)en_).isRefVariable()) {
                        exec_ = new ExecForEachRefArray(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                                ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_);
                    } else {
                        exec_ = new ExecForEachArray(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                                ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_);
                    }
                } else {
                    exec_ = new ExecForEachIterable(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                            ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_);
                }
                _coverage.putBlockOperationsLoops((AbstractForLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachTable) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachTable)en_).getRoot(), _coverage, _forwards, en_);
                ExecForEachTable exec_ = new ExecForEachTable(((ForEachTable) en_).getLabel(), ((ForEachTable)en_).getImportedClassNameFirst(),
                        ((ForEachTable)en_).getImportedClassNameSecond(),
                        ((ForEachTable)en_).getImportedClassIndexName(), ((ForEachTable)en_).getVariableNameFirst(),
                        ((ForEachTable)en_).getVariableNameSecond(), new ExecOperationNodeListOff(op_, ((ForEachTable)en_).getExpressionOffset()));
                _coverage.putBlockOperationsLoops((AbstractForLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForIterativeLoop) {
                CustList<ExecOperationNode> init_ = getExecutableNodes(((ForIterativeLoop)en_).getRootInit(), _coverage, _forwards, en_);
                CustList<ExecOperationNode> exp_ = getExecutableNodes(((ForIterativeLoop)en_).getRootExp(), _coverage, _forwards, en_);
                CustList<ExecOperationNode> step_ = getExecutableNodes(((ForIterativeLoop)en_).getRootStep(), _coverage, _forwards, en_);
                ExecForIterativeLoop exec_ = buildIter((ForIterativeLoop) en_, init_, exp_, step_);
                _coverage.putBlockOperationsLoops((AbstractForLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForMutableIterativeLoop) {
                CustList<ExecOperationNode> init_;
                OperationNode rInit_ = ((ForMutableIterativeLoop) en_).getRootInit();
                init_ = getExecutableNodes(rInit_, _coverage, _forwards, en_);
                CustList<ExecOperationNode> exp_;
                OperationNode rExp_ = ((ForMutableIterativeLoop) en_).getRootExp();
                exp_ = getExecutableNodes(rExp_, _coverage, _forwards, en_);
                OperationNode rStep_ = ((ForMutableIterativeLoop) en_).getRootStep();
                CustList<ExecOperationNode> step_;
                step_ = getExecutableNodes(rStep_, _coverage, _forwards, en_);
                ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(((ForMutableIterativeLoop) en_).getLabel(), ((ForMutableIterativeLoop) en_).getImportedClassName(), ((ForMutableIterativeLoop) en_).getImportedClassIndexName(),
                        ((ForMutableIterativeLoop) en_).getVariableNames(),
                        new ExecOperationNodeListOff(init_, ((ForMutableIterativeLoop) en_).getInitOffset()), new ExecOperationNodeListOff(exp_, ((ForMutableIterativeLoop) en_).getExpressionOffset()), new ExecOperationNodeListOff(step_, ((ForMutableIterativeLoop) en_).getStepOffset()));
                _coverage.putBlockOperationsConditionsForMutable((ForMutableIterativeLoop)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof NullCatchEval) {
                ExecNullCatchEval exec_ = new ExecNullCatchEval();
                _coverage.putCatches((NullCatchEval)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ReturnMethod) {
                OperationNode r_ = ((ReturnMethod) en_).getRoot();
                ExecBlock exec_ = buildRet(_coverage, _forwards, en_, r_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof SwitchBlock) {
                AbsBk first_ = en_.getFirstChild();
                boolean emp_ = first_ == null;
                CustList<ExecOperationNode> op_ = getExecutableNodes(((SwitchBlock)en_).getRoot(), _coverage, _forwards, en_);
                ExecBracedBlock exec_ = buildSwitch((SwitchBlock) en_, op_);
                _coverage.putBlockOperationsSwitchs((SwitchBlock)en_, exec_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                if (!emp_) {
                    blockToWrite_ = exec_;
                }
            } else if (en_ instanceof TryEval) {
                ExecTryEval exec_ = new ExecTryEval(((TryEval) en_).getLabel());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof Throwing) {
                CustList<ExecOperationNode> op_ = getExecutableNodes(((Throwing) en_).getRoot(), _coverage, _forwards, en_);
                ExecThrowing exec_ = new ExecThrowing(((Throwing)en_).getExpressionOffset(),op_);
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
            } else if (en_ instanceof UnclassedBracedBlock) {
                ExecUnclassedBracedBlock exec_ = new ExecUnclassedBracedBlock();
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
                BracedBlock par_ = en_.getParent();
                if (par_ == _from) {
                    return;
                }
                blockToWrite_ = blockToWrite_.getParent();
                en_ = par_;
            }
        }
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

    private static ExecBracedBlock buildCaseCondition(Coverage _coverage, Forwards _forwards, AbsBk _en) {
        ExecBracedBlock exec_;
//        if (((CaseCondition) _en).isBuiltEnum()) {
//            if (((CaseCondition) _en).isNullCaseEnum()) {
//                exec_ = new ExecStdCaseCondition(Argument.createVoid());
//            } else {
//                exec_ = new ExecEnumCaseCondition(((CaseCondition) _en).getValue());
//            }
//        } else
        if (!((CaseCondition) _en).getImportedType().isEmpty()) {
            exec_ = new ExecAbstractInstanceCaseCondition(((CaseCondition) _en).getVariableName(), ((CaseCondition) _en).getImportedType(), true);
//        } else if (((CaseCondition) _en).isInstance()) {
//            exec_ = new ExecStdCaseCondition(Argument.createVoid());
//        } else if (((CaseCondition) _en).getQualif() != null) {
//            ClassField qualif_ = ((CaseCondition) _en).getQualif();
//            exec_ = new ExecQualifEnumCaseCondition(qualif_.getClassName(),qualif_.getFieldName());
        } else {
            getExecutableNodes(((CaseCondition) _en).getRoot(), _coverage, _forwards, _en);
//            Argument argument_ = Argument.getNullableValue(((CaseCondition) _en).getArgument());
            exec_ = new ExecSwitchValuesCondition(((CaseCondition) _en).getStdValues(),((CaseCondition) _en).getEnumValues());
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
        StringList names_ = _anaNode.getResultClass().getNames();
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new ExecConstLeafOperation(false, new ExecOperationContent(c_.getContent()));
        }
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
                        new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent(),_forwards));
            }
            if (a_.isStaticMethod()) {
                if (pair_ == null) {
                    return new ExecEnumMethOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctCommonContent(a_.getCallFctContent()), new ExecArrContent(a_.getArrContent()),ex_);
                }
                return new ExecStaticFctOperation(pair_, new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctContent(a_.getCallFctContent(),_forwards), new ExecArrContent(a_.getArrContent()));
            }
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor n_ = (InterfaceFctConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(),_forwards), n_.getClassName(), FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof InterfaceInvokingConstructor) {
            InterfaceInvokingConstructor n_ = (InterfaceInvokingConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(),_forwards), "",FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof CurrentInvokingConstructor) {
            CurrentInvokingConstructor n_ = (CurrentInvokingConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(),_forwards), "",FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof SuperInvokingConstructor) {
            SuperInvokingConstructor n_ = (SuperInvokingConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent(),_forwards), "",FetchMemberUtil.fetchTypeCtor(n_.getMemberId(), _forwards));
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
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchPossibleTypeCtor(s_.getMemberId(), _forwards);
            if (typeCtor_ != null) {
                return new ExecStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), s_.isNewBefore(), new ExecInstancingCustContent(s_.getInstancingCommonContent(),typeCtor_,_forwards), new ExecInstancingStdContent(s_.getInstancingStdContent()));
            }
            return new ExecDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingDirContent(s_.getInstancingCommonContent()));
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards);
            return new ExecAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), s_.isNewBefore(), new ExecInstancingCustContent(s_.getInstancingCommonContent(),typeCtor_,_forwards));
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
                return new ExecCustArrOperation(get_,set_, new ExecOperationContent(a_.getContent()), a_.isIntermediateDottedOperation(), new ExecArrContent(a_.getArrContent()), new ExecInstFctContent(a_.getCallFctContent(), a_.getAnc(), a_.isStaticChoiceMethod(),_forwards));
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
            return new ExecChoiceFctOperation(p_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true,_forwards), new ExecArrContent(c_.getArrContent()));
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(s_.getCallFctContent().getMemberId(), _forwards);
            return new ExecChoiceFctOperation(p_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true,_forwards), new ExecArrContent(s_.getArrContent()));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(f_.getCallFctContent().getMemberId(), _forwards);
            return new ExecFctOperation(p_, new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), new ExecInstFctContent(f_.getCallFctContent(), f_.getAnc(), f_.isStaticChoiceMethod(),_forwards), new ExecArrContent(f_.getArrContent()));
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
//        if (_anaNode instanceof VarargOperation) {
//            VarargOperation f_ = (VarargOperation) _anaNode;
//            return new ExecConstLeafOperation(true, new ExecOperationContent(f_.getContent()));
//        }
        if (_anaNode instanceof DefaultValueOperation) {
            DefaultValueOperation f_ = (DefaultValueOperation) _anaNode;
            return new ExecDefaultValueOperation(new ExecOperationContent(f_.getContent()), f_.getClassName());
        }
        if (_anaNode instanceof DefaultOperation) {
            DefaultOperation f_ = (DefaultOperation) _anaNode;
            return new ExecDefaultOperation(new ExecOperationContent(f_.getContent()), f_.getOffset(),names_);
        }
        if (InvokingOperation.getDeltaCount(_anaNode) != 0) {
            return new ExecConstLeafOperation(true, new ExecOperationContent(_anaNode.getContent()));
        }
        if (_anaNode instanceof AnonymousLambdaOperation) {
            AnonymousLambdaOperation s_ = (AnonymousLambdaOperation) _anaNode;

            ExecTypeFunction pair_ = buildAnonFctPair(_forwards, s_);
//            ExecTypeFunction pair_ = new ExecTypeFunction(_forwards.getMapMembers().getValue(s_.getRootNumber()).getRootBlock(), r_);
            return new ExecAnonymousLambdaOperation(new ExecOperationContent(s_.getContent()), new ExecLambdaCommonContent(s_.getLambdaCommonContent(),_forwards), new ExecLambdaMethodContent(s_.getMethod(), pair_));
        }
        if (_anaNode instanceof LambdaOperation) {
            LambdaOperation f_ = (LambdaOperation) _anaNode;
            ExecLambdaCommonContent lamCont_ = new ExecLambdaCommonContent(f_.getLambdaCommonContent(),_forwards);
            if (f_.getStandardMethod() != null) {
                return new ExecStdMethodLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, f_.getMethod(), f_.getStandardMethod());
            }
            if (f_.getStandardType() != null) {
                return new ExecStdConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, f_.getRealId(), f_.getStandardType());
            }
            int recordType_ = f_.getRecordType();
            ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(recordType_, _forwards);
            if (rootBlock_ != null) {
                return new ExecRecordConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, rootBlock_, f_.getInfos());
            }
            if (f_.getMethod() == null && f_.getRealId() == null) {
                return new ExecFieldLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, new ExecLambdaFieldContent(f_.getFieldId(), f_.getLambdaFieldContent(), f_.getLambdaMemberNumberContentId(), _forwards));
            }
            if (f_.getMethod() == null) {
                ExecLambdaConstructorContent lambdaConstructorContent_ = new ExecLambdaConstructorContent(f_.getRealId(), f_.getLambdaMemberNumberContentId(), _forwards);
                ExecTypeFunction pair_ = lambdaConstructorContent_.getPair();
                if (pair_ != null) {
                    return new ExecTypeConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, lambdaConstructorContent_);
                }
                return new ExecConstructorLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_);
            }
            ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(f_.getLambdaMemberNumberContentId(), _forwards);
            ExecRootBlock declaring_ = pair_.getType();
            ExecNamedFunctionBlock named_ = pair_.getFct();
            ExecLambdaMethodContent lambdaMethodContent_ = new ExecLambdaMethodContent(f_.getMethod().getConstraints(), f_.getLambdaMethodContent(), pair_);
            if (declaring_ != null || named_ != null) {
                return new ExecCustMethodLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, lambdaMethodContent_);
            }
            if (lambdaMethodContent_.isDirectCast() || lambdaMethodContent_.isClonedMethod()) {
                return new ExecSimpleMethodLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, lambdaMethodContent_);
            }
            return new ExecMethodLambdaOperation(new ExecOperationContent(f_.getContent()), lamCont_, lambdaMethodContent_);
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
            return new ExecParentInstanceOperation(new ExecOperationContent(f_.getContent()), new ExecParentInstanceContent(f_.getParentInstanceContent()),names_);
        }
        if (_anaNode instanceof ForwardOperation) {
            ForwardOperation f_ = (ForwardOperation) _anaNode;
            return new ExecForwardOperation(new ExecOperationContent(f_.getContent()), f_.isIntermediate());
        }
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
            FinalVariableOperation m_ = (FinalVariableOperation) _anaNode;
            if (m_.getType() == ConstType.LOOP_INDEX) {
                return new ExecFinalVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
            }
            return new ExecStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new ExecDotOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof SafeDotOperation) {
            SafeDotOperation m_ = (SafeDotOperation) _anaNode;
            return new ExecSafeDotOperation(new ExecOperationContent(m_.getContent()),names_);
        }
        if (_anaNode instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation m_ = (ExplicitOperatorOperation) _anaNode;
            return new ExecExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getCallFctContent(),_forwards), m_.getOffsetOper(), FetchMemberUtil.fetchFunctionOpPair(m_.getCallFctContent().getMemberId(), _forwards), new ExecArrContent(m_.getArrContent()));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(m_.getFct(), _forwards);
            if (pair_.getFct() == null) {
                return new ExecSemiAffectationNatOperation(new ExecOperationContent(m_.getContent()), new ExecOperatorContent(m_.getOperatorContent()), FetchMemberUtil.fetchImplicits(m_.getConvFrom(), _forwards), FetchMemberUtil.fetchImplicits(m_.getConvTo(), _forwards), m_.isPost(),names_);
            }
            return new ExecSemiAffectationCustOperation(new ExecOperationContent(m_.getContent()), new ExecStaticPostEltContent(m_.getFct(), m_.isPost(),_forwards), new ExecOperatorContent(m_.getOperatorContent()), pair_,names_);
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            ClassMethodIdMemberIdTypeFct fct_ = n_.getFct();
            AnaTypeFct pair_ = fct_.getFunction();
            if (pair_ != null) {
                return new ExecCustNumericOperation(FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards), new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), new ExecStaticEltContent(fct_,_forwards));
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
            ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(m_.getMemberId(), _forwards);
            if (ExecExplicitOperation.direct(pair_,m_.getExplicitContent().getClassName())) {
                return new ExecImplicitOperation(
                        new ExecOperationContent(m_.getContent()),new ExecExplicitCommonContent(m_.getExplicitContent()));
            }
            return new ExecExplicitOperation(
                    pair_,
                    new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent(),_forwards));
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(m_.getMemberId(), _forwards);
            if (ExecExplicitOperation.direct(pair_,m_.getExplicitContent().getClassName())) {
                return new ExecImplicitOperation(
                        new ExecOperationContent(m_.getContent()), new ExecExplicitCommonContent(m_.getExplicitContent()));
            }
            return new ExecExplicitOperation(
                    pair_,
                    new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent(),_forwards));
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
            ClassMethodIdMemberIdTypeFct fct_ = c_.getFct();
            ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
            if (pair_.getFct() != null) {
                return new ExecQuickCustOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(fct_,_forwards), pair_, FetchMemberUtil.fetchImplicits(c_.getConv(), _forwards), false);
            }
            return new ExecQuickNatOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), FetchMemberUtil.fetchImplicits(c_.getConv(), _forwards), false);
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            ClassMethodIdMemberIdTypeFct fct_ = c_.getFct();
            ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
            if (pair_.getFct() != null) {
                return new ExecQuickCustOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(fct_,_forwards), pair_, FetchMemberUtil.fetchImplicits(c_.getConv(), _forwards), true);
            }
            return new ExecQuickNatOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), FetchMemberUtil.fetchImplicits(c_.getConv(), _forwards), true);
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation c_ = (NullSafeOperation) _anaNode;
            return new ExecNullSafeOperation(new ExecOperationContent(c_.getContent()),c_.getOpOffset(),names_);
        }
        if (_anaNode instanceof AssocationOperation) {
            AssocationOperation c_ = (AssocationOperation) _anaNode;
            return new ExecAssocationOperation(new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _anaNode;
            ClassMethodIdMemberIdTypeFct fct_ = c_.getFct();
            if (c_.isConcat()) {
                return new ExecCompoundAffectationStringOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()),names_);
            }
            ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
            if (pair_.getFct() != null) {
                return new ExecCompoundAffectationCustOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), new ExecStaticEltContent(fct_,_forwards), pair_, FetchMemberUtil.fetchImplicits(c_.getConv(), _forwards),names_);
            }
            String oper_ = c_.getOperatorContent().getOper();
            String op_ = oper_.substring(0, oper_.length() - 1);
            if (StringUtil.quickEq(op_, "??") || StringUtil.quickEq(op_, "???")) {
                return new ExecCompoundAffectationNatSafeOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), FetchMemberUtil.fetchImplicits(c_.getConv(), _forwards),names_);
            }
            return new ExecCompoundAffectationNatOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), FetchMemberUtil.fetchImplicits(c_.getConv(), _forwards),names_);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new ExecAffectationOperation(new ExecOperationContent(a_.getContent()), a_.getOpOffset(),names_);
        }
        return new ExecDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
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
        CustList<CustList<ExecAnnotContent>> ops_ = new CustList<CustList<ExecAnnotContent>>();
        int i_ = 0;
        for (CustList<OperationNode> l: _ana.getRootsList()) {
            CustList<ExecAnnotContent> annotation_;
            annotation_ = new CustList<ExecAnnotContent>();
            _coverage.putBlockOperationsAnnotMethodParam(_ana);
            int j_ = 0;
            for (OperationNode r: l) {
                _coverage.putBlockOperationsAnnotMethod(_ana,i_);
                annotation_.add(new ExecAnnotContent(getExecutableNodes(i_,j_,r, _coverage, _forwards, _ana),r.getResultClass().getNames(), _ana.getAnnotationsIndexesParams().get(i_).get(j_)));
                j_++;
            }
            ops_.add(annotation_);
            i_++;
        }
        _ann.getAnnotationsOpsParams().addAllElts(ops_);
    }
    private static void fwdAnnotationsParametersSw(SwitchMethodBlock _ana, ExecAbstractSwitchMethod _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecAnnotContent>> ops_ = new CustList<CustList<ExecAnnotContent>>();
        int i_ = 0;
        for (CustList<OperationNode> l: _ana.getRootsList()) {
            CustList<ExecAnnotContent> annotation_;
            annotation_ = new CustList<ExecAnnotContent>();
            _coverage.putBlockOperationsAnnotMethodParam(_ana);
            int j_ = 0;
            for (OperationNode r: l) {
                _coverage.putBlockOperationsAnnotMethod(_ana,i_);
                annotation_.add(new ExecAnnotContent(getExecutableNodes(i_,j_,r, _coverage, _forwards, _ana),r.getResultClass().getNames(), _ana.getAnnotationsIndexesParams().get(i_).get(j_)));
                j_++;
            }
            ops_.add(annotation_);
            i_++;
        }
        _ann.getAnnotationsOpsParams().addAllElts(ops_);
    }

    private static void fwdAnnotations(InnerTypeOrElement _ana, ExecInnerTypeOrElement _ann, Coverage _coverage, Forwards _forwards) {
        CustList<ExecAnnotContent> ops_ = new CustList<ExecAnnotContent>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotField(_ana);
            ops_.add(new ExecAnnotContent(getExecutableNodes(-1,i_,r, _coverage, _forwards, (AbsBk)_ana),r.getResultClass().getNames(), _ana.getAnnotationsIndexes().get(i_)));
            i_++;
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(FieldBlock _ana, ExecFieldBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<ExecAnnotContent> ops_ = new CustList<ExecAnnotContent>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotField(_ana);
            ops_.add(new ExecAnnotContent(getExecutableNodes(-1,i_,r, _coverage, _forwards, _ana),r.getResultClass().getNames(), _ana.getAnnotationsIndexes().get(i_)));
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
        CustList<ExecAnnotContent> ops_ = new CustList<ExecAnnotContent>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotMethod(_ana);
            ops_.add(new ExecAnnotContent(getExecutableNodes(-1,i_,r, _coverage, _forwards, _ana),r.getResultClass().getNames(), _ana.getAnnotationsIndexes().get(i_)));
            i_++;
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }
    private static void fwdAnnotationsSw(SwitchMethodBlock _ana, ExecAbstractSwitchMethod _ex, Coverage _coverage, Forwards _forwards) {
        CustList<ExecAnnotContent> ops_ = new CustList<ExecAnnotContent>();
        int i_ = 0;
        for (OperationNode r: _ana.getRoots()) {
            _coverage.putBlockOperationsAnnotMethod(_ana);
            ops_.add(new ExecAnnotContent(getExecutableNodes(-1,i_,r, _coverage, _forwards, _ana),r.getResultClass().getNames(), _ana.getAnnotationsIndexes().get(i_)));
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
