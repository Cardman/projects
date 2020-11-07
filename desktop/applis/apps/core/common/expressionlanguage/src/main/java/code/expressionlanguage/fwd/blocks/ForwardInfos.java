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
                _forwards.getMapAnonTypes().addEntry((AnonymousTypeBlock)r, e_);
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
                ExecInterfaceBlock e_ = new ExecInterfaceBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), r.isStaticType());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof AnnotationBlock) {
                ExecAnnotationBlock e_ = new ExecAnnotationBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess());
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
            }
            if (r instanceof InnerElementBlock) {
                ExecInnerElementBlock e_ = new ExecInnerElementBlock(r.getOffset().getOffsetTrim(), new ExecRootBlockContent(r.getRootBlockContent()), r.getAccess(), new ExecElementContent(((InnerElementBlock) r).getElementContent()));
                e_.setFile(exFile_);
                v_.setRootBlock(e_);
                _forwards.getMapInnerEltTypes().addEntry((InnerElementBlock) r, e_);
            }
            coverage_.putType(r);
            _forwards.getMapMembers().addEntry(r, v_);
        }
        innerFetchExecEnd(_forwards);
        Classes classes_ = _context.getClasses();
        for (RootBlock e: _page.getSorted().values()) {
            ExecRootBlock e_ = _forwards.getMapMembers().getValue(e.getNumberAll()).getRootBlock();
            String fullName_ = e.getFullName();
            classes_.getClassesBodies().addEntry(fullName_, e_);
        }
        for (EntryCust<String,FileBlock> e: _page.getFilesBodies().entryList()) {
            FileBlock fileBlock_ = e.getValue();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            for (Block b: ClassesUtil.getDirectChildren(fileBlock_)) {
                if (b instanceof OperatorBlock) {
                    OperatorBlock r_ = (OperatorBlock) b;
                    ExecOperatorBlock e_ = new ExecOperatorBlock(r_.getName(), r_.isVarargs(), r_.getAccess(), r_.getParametersNames(), r_.getOffset().getOffsetTrim());
                    e_.setFile(exFile_);
                    _forwards.getMapOperators().addEntry(r_,e_);
                    coverage_.putOperator(r_);
                }
            }
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getMapOperators().entryList()) {
            classes_.getOperators().add(e.getValue());
        }
        for (EntryCust<String,FileBlock> e: _page.getFilesBodies().entryList()) {
            FileBlock fileBlock_ = e.getValue();
            ExecFileBlock exFile_ = files_.getValue(fileBlock_.getNumberFile());
            processExecFile(fileBlock_,exFile_, _forwards);
        }
        StringMap<PolymorphMethod> toStringMethodsToCallBodies_ = _context.getClasses().getToStringMethodsToCallBodies();
        for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
            ClassMethodIdReturn resDyn_ = e.getValue();
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(resDyn_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock fct_ = FetchMemberUtil.fetchFunction(resDyn_.getRootNumber(),resDyn_.getMemberNumber(), _forwards);
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
                ClassMethodIdOverride override_ = new ClassMethodIdOverride(FetchMemberUtil.fetchFunction(root_.getNumberAll(), o.getNameNumber(), _forwards));
                for (EntryCust<String,GeneStringOverridable> g: map_.entryList()) {
                    GeneStringOverridable value_ = g.getValue();
                    int numberAll_ = value_.getType().getNumberAll();
                    override_.put(g.getKey(), value_.getGeneString(), FetchMemberUtil.fetchType(numberAll_, _forwards), FetchMemberUtil.fetchFunction(numberAll_, value_.getBlock().getNameNumber(), _forwards));
                }
                redirections_.add(override_);
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock root_ = e.getKey();
            if (!root_.mustImplement()) {
                CustList<AnaFormattedRootBlock> allSuperClass_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(root_,root_.getGenericString()));
                allSuperClass_.addAllElts(root_.getAllGenericSuperTypesInfo());
                boolean instEltCount_ = false;
                for (AnaFormattedRootBlock s: allSuperClass_) {
                    RootBlock superBl_ = s.getRootBlock();
                    for (Block b: ClassesUtil.getDirectChildren(superBl_)) {
                        if (b instanceof OverridableBlock) {
                            OverridableBlock m =(OverridableBlock)b;
                            if (m.isAbstractMethod()) {
                                ExecRootBlock ex_ = _forwards.getMapMembers().getValue(superBl_.getNumberAll()).getRootBlock();
                                ExecOverrideInfo val_ = ex_.getRedirections().getVal(FetchMemberUtil.fetchFunction(superBl_.getNumberAll(),m.getNameNumber(), _forwards), root_.getFullName());
                                if (val_ == null) {
                                    ExecOverridableBlock value_ = _forwards.getMapMembers().getValue(superBl_.getNumberAll()).getAllMethods().getValue(m.getNameOverrideNumber());
                                    e.getValue().getRootBlock().getFunctionalBodies().add(new ExecFunctionalInfo(s.getFormatted(),value_));
                                }
                            }
                        }
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
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            e.getValue().getRootBlock().getAllSuperTypes().addAllElts(e.getKey().getAllSuperTypes());
            e.getValue().getRootBlock().getStaticInitImportedInterfaces().addAllElts(e.getKey().getStaticInitImportedInterfaces());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
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
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            updateExec(e.getValue().getRootBlock(), e.getKey());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock i = e.getKey();
            CustList<AnaFormattedRootBlock> genericClasses_ = i.getAllGenericClassesInfo();
            if (i instanceof UniqueRootedBlock && genericClasses_.size() > 1) {
                e.getValue().getRootBlock().setUniqueType(FetchMemberUtil.fetchType(genericClasses_.get(1).getRootBlock().getNumberAll(), _forwards));
            }
            ConstructorBlock emptyCtor_ = i.getEmptyCtor();
            if (emptyCtor_ != null) {
                e.getValue().getRootBlock().setEmptyCtor(FetchMemberUtil.fetchFunction(i.getNumberAll(),emptyCtor_.getNameNumber(), _forwards));
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
            validateIds(e.getValue());
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            ExecOperatorBlock value_ = e.getValue();
            value_.buildImportedTypes(o.getImportedReturnType(), o.getImportedParametersTypes());
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            String fullName_ = c.getFullName();
            coverage_.putCalls(fullName_);
            for (EntryCust<InitBlock, ExecInitBlock> f: mem_.getAllInits().entryList()) {
                InitBlock method_ = f.getKey();
                _forwards.getAllFct().addEntry(method_,f.getValue());
            }
            for (EntryCust<ConstructorBlock, ExecConstructorBlock> f: mem_.getAllCtors().entryList()) {
                ConstructorBlock method_ = f.getKey();
                coverage_.putCalls(fullName_,method_);
                _forwards.getAllFct().addEntry(method_,f.getValue());
                fwdInstancingStep(method_, f.getValue());
            }
            for (EntryCust<OverridableBlock, ExecOverridableBlock> f: mem_.getAllMethods().entryList()) {
                OverridableBlock method_ =  f.getKey();
                coverage_.putCalls(fullName_,method_);
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
            for (Block b: ClassesUtil.getDirectChildren(c)) {
                if (b instanceof RootBlock) {
                    ExecRootBlock val_ = _forwards.getMapMembers().getValue(((RootBlock) b).getNumberAll()).getRootBlock();
                    mem_.getRootBlock().getChildrenTypes().add(val_);
                } else {
                    if (b instanceof InfoBlock) {
                        ExecInfoBlock elt_ = mem_.getAllFields().getValue(((InfoBlock) b).getFieldNumber());
                        mem_.getRootBlock().getChildrenOthers().add((ExecBlock) elt_);
                    }
                    if (b instanceof MemberCallingsBlock) {
                        ExecMemberCallingsBlock elt_ = mem_.getAllFct().getValue(((MemberCallingsBlock) b).getNumberFct());
                        mem_.getRootBlock().getChildrenOthers().add(elt_);
                    }
                }
            }
            for (EntryCust<AnnotationMethodBlock, ExecAnnotationMethodBlock> f: mem_.getAllAnnotMethods().entryList()) {
                mem_.getRootBlock().getAnnotationsFields().add(f.getValue());
            }
            for (EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement> f: mem_.getAllElementFields().entryList()) {
                ExecInnerTypeOrElement val_ = f.getValue();
                mem_.getRootBlock().getEnumElements().add(val_);
            }
            for (EntryCust<InfoBlock, ExecInfoBlock> f: mem_.getAllFields().entryList()) {
                ExecInfoBlock val_ = f.getValue();
                mem_.getRootBlock().getAllFields().add(val_);
            }
            for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> f: mem_.getAllFct().entryList()) {
                ExecMemberCallingsBlock val_ = f.getValue();
                mem_.getRootBlock().getAllFct().add(val_);
            }
            for (EntryCust<FieldBlock, ExecFieldBlock> f: mem_.getAllExplicitFields().entryList()) {
                FieldBlock method_ = f.getKey();
                ExecFieldBlock exp_ = f.getValue();
                if (!method_.isStaticField()) {
                    mem_.getRootBlock().getInstanceFields().add(exp_);
                }
            }
        }
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            Members mem_ = e.getValue();
            for (EntryCust<InnerElementBlock, ExecInnerElementBlock> f: mem_.getAllInnerElementFields().entryList()) {
                InnerElementBlock method_ = f.getKey();
                ExecInnerElementBlock val_ = f.getValue();
                fwdExpressionLanguageReadOnly(method_, val_, coverage_, _forwards);
            }
            for (EntryCust<ElementBlock, ExecElementBlock> f: mem_.getAllSimpleElementFields().entryList()) {
                ElementBlock method_ = f.getKey();
                ExecElementBlock val_ = f.getValue();
                fwdExpressionLanguageReadOnly(method_, val_, coverage_, _forwards);
            }
            for (EntryCust<FieldBlock, ExecFieldBlock> f: mem_.getAllExplicitFields().entryList()) {
                FieldBlock method_ = f.getKey();
                ExecFieldBlock exp_ = f.getValue();
                fwdExpressionLanguageReadOnly(method_, exp_, coverage_, _forwards);
            }
        }
        _forwards.setAnnotAnalysis(true);
        for (EntryCust<RootBlock, Members> e: _forwards.getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            Members mem_ = e.getValue();
            CustList<Block> annotated_ = new CustList<Block>();
            if (!(c instanceof InnerElementBlock)) {
                annotated_.add(c);
            }
            annotated_.addAllElts(ClassesUtil.getDirectChildren(c));
            coverage_.putBlockOperations(mem_.getRootBlock(),c);
            for (EntryCust<AnnotationMethodBlock, ExecAnnotationMethodBlock> a: mem_.getAllAnnotMethods().entryList()) {
                _forwards.setAnnotAnalysisField(true);
                AnnotationMethodBlock b = a.getKey();
                ExecAnnotationMethodBlock d = a.getValue();
                fwd(b,d, coverage_, _forwards);
                coverage_.putBlockOperations(d,b);
            }
            for (EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock> a: mem_.getAllNamed().entryList()) {
                _forwards.setAnnotAnalysisField(false);
                NamedFunctionBlock b = a.getKey();
                ExecNamedFunctionBlock d = a.getValue();
                coverage_.putBlockOperationsField(_forwards, b);
                fwdAnnotations(b, d, coverage_, _forwards);
                fwdAnnotationsParameters(b, d, coverage_, _forwards);
            }
            for (EntryCust<FieldBlock, ExecFieldBlock> a: mem_.getAllExplicitFields().entryList()) {
                _forwards.setAnnotAnalysisField(false);
                FieldBlock b = a.getKey();
                ExecFieldBlock d = a.getValue();
                coverage_.putBlockOperationsField(_forwards, b);
                coverage_.putBlockOperations(d,b);
                fwdAnnotations(b, d, coverage_, _forwards);
            }
            for (EntryCust<ElementBlock, ExecElementBlock> a: mem_.getAllSimpleElementFields().entryList()) {
                _forwards.setAnnotAnalysisField(false);
                ElementBlock b = a.getKey();
                ExecElementBlock d = a.getValue();
                coverage_.putBlockOperationsField(_forwards, b);
                coverage_.putBlockOperations(d,b);
                fwdAnnotations(b, d, coverage_, _forwards);
            }
            for (EntryCust<InnerElementBlock, ExecInnerElementBlock> a: mem_.getAllInnerElementFields().entryList()) {
                _forwards.setAnnotAnalysisField(false);
                InnerElementBlock b = a.getKey();
                ExecInnerElementBlock d = a.getValue();
                coverage_.putBlockOperationsField(_forwards, b);
                coverage_.putBlockOperations(d,b);
                fwdAnnotations(b, d, coverage_, _forwards);
            }
            if (!(mem_.getRootBlock() instanceof ExecInnerElementBlock)) {
                _forwards.setAnnotAnalysisField(false);
                ExecRootBlock d = mem_.getRootBlock();
                coverage_.putBlockOperationsField(_forwards, c);
                fwdAnnotations(c, d, coverage_, _forwards);
            }
        }
        for (EntryCust<OperatorBlock, ExecOperatorBlock> e: _forwards.getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            _forwards.setAnnotAnalysisField(false);
            coverage_.putBlockOperationsField(_forwards, o);
            ExecOperatorBlock value_ = e.getValue();
            fwdAnnotations(o, value_, coverage_, _forwards);
            fwdAnnotationsParameters(o, value_, coverage_, _forwards);
        }
        _forwards.setAnnotAnalysis(false);
        for (EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock> e: _forwards.getAllFct().entryList()) {
            buildExec(e.getKey(),e.getValue(), coverage_, _forwards);
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

    private static void feedFct(MemberCallingsBlock _b1, ExecMemberCallingsBlock _value, Forwards _forwards) {
        for (AnonymousFunctionBlock a: _b1.getAnonymousFct()) {
            _value.getAnonymousLambda().add(_forwards.getMapAnonLambda().getValue(a.getNumberLambda()));
        }
        for (AnonymousTypeBlock a: _b1.getAnonymous()) {
            _value.getAnonymous().add(_forwards.getMapAnonTypes().getValue(a.getNumberAnonType()));
        }
        for (RootBlock a: _b1.getReserved()) {
            _value.getReserved().add(_forwards.getMapMembers().getValue(a.getNumberAll()).getRootBlock());
        }
    }

    private static void processAppend(ExecFileBlock _exFile, RootBlock _root, Forwards _forwards) {
        ExecRootBlock e_ = _forwards.getMapMembers().getValue(_root.getNumberAll()).getRootBlock();
        _exFile.appendChild(e_);
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
                    mem_.getAllElementFields().addEntry((InnerElementBlock) b,val_);
                }
                if (b instanceof ElementBlock) {
                    ExecElementBlock val_ = new ExecElementBlock(b.getOffset().getOffsetTrim(), new ExecElementContent(((ElementBlock) b).getElementContent()));
                    current_.appendChild(val_);
                    val_.setFile(current_.getFile());
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllSimpleElementFields().addEntry((ElementBlock) b,val_);
                    mem_.getAllElementFields().addEntry((ElementBlock) b,val_);
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
    private static void processExecFile(FileBlock _anaFile, ExecFileBlock _exeFile, Forwards _forwards) {
        for (Block b: ClassesUtil.getDirectChildren(_anaFile)) {
            if (b instanceof RootBlock) {
                RootBlock r_ = (RootBlock) b;
                processAppend(_exeFile, r_, _forwards);
            }
            if (b instanceof OperatorBlock) {
                OperatorBlock r_ = (OperatorBlock) b;
                ExecOperatorBlock e_ = _forwards.getMapOperators().getValue(r_.getNameNumber());
                _exeFile.appendChild(e_);
            }
        }
    }

    private static ExecAnonymousFunctionBlock buildExecAnonymousLambdaOperation(AnonymousLambdaOperation _s, Forwards _forwards) {
        ExecRootBlock declaring_ = _forwards.getMapMembers().getValue(_s.getRootNumber()).getRootBlock();
        AnonymousFunctionBlock block_ = _s.getBlock();
        block_.setNumberLambda(_forwards.getMapAnonLambda().size());
        ExecAnonymousFunctionBlock fct_ = new ExecAnonymousFunctionBlock(block_.getName(), block_.isVarargs(), block_.getAccess(), block_.getParametersNames(), block_.getModifier(), block_.getOffset().getOffsetTrim(), new ExecAnonFctContent(block_.getAnaAnonFctContent()));
        fct_.setParentType(declaring_);
        _forwards.getMapAnonLambda().addEntry(block_,fct_);
        fct_.buildImportedTypes(block_.getImportedReturnType(), block_.getImportedParametersTypes());
        return fct_;
    }

    private static void buildExec(MemberCallingsBlock _from, ExecMemberCallingsBlock _dest, Coverage _coverage, Forwards _forwards) {
        ExecBracedBlock blockToWrite_ = _dest;
        ExecFileBlock fileDest_ = _dest.getFile();
        Block firstChild_ = _from.getFirstChild();
        ExecDeclareVariable decl_ = null;
        _coverage.putBlockOperations(_dest,_from);
        Block en_ = _from;
        if (firstChild_ == null) {
            return;
        }
        while (true) {
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
                    getExecutableNodes(((CaseCondition)en_).getRoot(), _coverage, _forwards, en_);
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
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((Condition)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecIfCondition(((Condition) en_).getConditionOffset(), ((IfCondition) en_).getLabel(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(en_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ElseIfCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((Condition)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecElseIfCondition(((Condition) en_).getConditionOffset(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(en_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof WhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((Condition)en_).getRoot(), _coverage, _forwards, en_);
                ExecCondition exec_ = new ExecWhileCondition(((Condition) en_).getConditionOffset(), ((WhileCondition) en_).getLabel(), opCondition_, en_.getOffset().getOffsetTrim());
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperationsConditions(en_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof DoWhileCondition) {
                CustList<ExecOperationNode> opCondition_ = getExecutableNodes(((Condition)en_).getRoot(), _coverage, _forwards, en_);
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
                CustList<ExecOperationNode> op_ = getExecutableNodes(((Line)en_).getRoot(), _coverage, _forwards, en_);
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
                CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachLoop)en_).getRoot(), _coverage, _forwards, en_);
                ExecAbstractForEachLoop exec_;
                if (((ForEachLoop)en_).getRoot().getResultClass().isArray()) {
                    exec_ = new ExecForEachArray(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                            ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                } else {
                    exec_ = new ExecForEachIterable(((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                            ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_, en_.getOffset().getOffsetTrim());
                }
                exec_.setFile(fileDest_);
                blockToWrite_.appendChild(exec_);
                _coverage.putBlockOperations(exec_,en_);
                blockToWrite_ = exec_;
            } else if (en_ instanceof ForEachTable) {
                _coverage.putBlockOperationsLoops(en_);
                CustList<ExecOperationNode> op_ = getExecutableNodes(((ForEachTable)en_).getRoot(), _coverage, _forwards, en_);
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
                CustList<ExecOperationNode> init_ = getExecutableNodes(((ForIterativeLoop)en_).getRootInit(), _coverage, _forwards, en_);
                CustList<ExecOperationNode> exp_ = getExecutableNodes(((ForIterativeLoop)en_).getRootExp(), _coverage, _forwards, en_);
                CustList<ExecOperationNode> step_ = getExecutableNodes(((ForIterativeLoop)en_).getRootStep(), _coverage, _forwards, en_);
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
                    init_ = getExecutableNodes(rInit_, _coverage, _forwards, en_);
                }
                CustList<ExecOperationNode> exp_;
                OperationNode rExp_ = ((ForMutableIterativeLoop) en_).getRootExp();
                if (rExp_ == null) {
                    exp_ = new CustList<ExecOperationNode>();
                } else {
                    exp_ = getExecutableNodes(rExp_, _coverage, _forwards, en_);
                }
                _coverage.putBlockOperationsConditions(en_);
                OperationNode rStep_ = ((ForMutableIterativeLoop) en_).getRootStep();
                CustList<ExecOperationNode> step_;
                if (rStep_ == null) {
                    step_ = new CustList<ExecOperationNode>();
                } else {
                    step_ = getExecutableNodes(rStep_, _coverage, _forwards, en_);
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
                    CustList<ExecOperationNode> op_ = getExecutableNodes(r_, _coverage, _forwards, en_);
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
                CustList<ExecOperationNode> op_ = getExecutableNodes(((SwitchBlock)en_).getRoot(), _coverage, _forwards, en_);
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
                CustList<ExecOperationNode> op_ = getExecutableNodes(((Throwing) en_).getRoot(), _coverage, _forwards, en_);
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
                BracedBlock par_ = en_.getParent();
                if (par_ == _from) {
                    return;
                }
                blockToWrite_ = blockToWrite_.getParent();
                en_ = par_;
            }
        }
    }

    private static CustList<ExecOperationNode> getExecutableNodes(OperationNode _root, Coverage _coverage, Forwards _forwards, Block _currentBlock) {
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        OperationNode current_ = _root;
        ExecOperationNode exp_ = createExecOperationNode(current_, _forwards);
        setImplicits(exp_, current_, _forwards);
        _coverage.putBlockOperation(_forwards, _currentBlock, current_,exp_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (hasToCreateExec(exp_, op_)) {
                ExecOperationNode loc_ = createExecOperationNode(op_, _forwards);
                setImplicits(loc_, op_, _forwards);
                _coverage.putBlockOperation(_forwards, _currentBlock, op_,loc_);
                ((ExecMethodOperation)exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (current_ != null) {
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
                ExecMethodOperation par_ = exp_.getParent();
                if (hasToCreateExec(par_, op_)) {
                    ExecOperationNode loc_ = createExecOperationNode(op_, _forwards);
                    setImplicits(loc_, op_, _forwards);
                    _coverage.putBlockOperation(_forwards, _currentBlock, op_,loc_);
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof AbstractDotOperation && loc_ instanceof ExecPossibleIntermediateDotted) {
                        exp_.setSiblingSet((ExecPossibleIntermediateDotted) loc_);
                    }
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
                    continue;
                }
                current_ = op_;
                exp_ = par_;
            }
        }
        return out_;
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
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(a_.getRootNumber(), _forwards);
            if (ex_ instanceof ExecAnnotationBlock) {
                return new ExecAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(a_.getCallFctContent()));
            }
            if (a_.isTrueFalse()) {
                return new ExecExplicitOperation(
                        FetchMemberUtil.fetchFunction(a_, _forwards),
                        FetchMemberUtil.fetchType(a_.getRootNumber(), _forwards), new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent()));
            }
            if (a_.isStaticMethod()) {
                ExecNamedFunctionBlock fct_ = FetchMemberUtil.fetchFunction(a_, _forwards);
                return new ExecStaticFctOperation(fct_,ex_, new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctContent(a_.getCallFctContent()));
            }
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor n_ = (InterfaceFctConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), FetchMemberUtil.fetchType(n_.getRootNumber(), _forwards), FetchMemberUtil.fetchFunction(n_.getRootNumber(), n_.getMemberNumber(), _forwards), n_.getClassName());
        }
        if (_anaNode instanceof InterfaceInvokingConstructor) {
            InterfaceInvokingConstructor n_ = (InterfaceInvokingConstructor) _anaNode;
            return new ExecInterfaceInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), FetchMemberUtil.fetchType(n_.getRootNumber(), _forwards), FetchMemberUtil.fetchFunction(n_.getRootNumber(), n_.getMemberNumber(), _forwards));
        }
        if (_anaNode instanceof CurrentInvokingConstructor) {
            CurrentInvokingConstructor n_ = (CurrentInvokingConstructor) _anaNode;
            return new ExecCurrentInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), FetchMemberUtil.fetchType(n_.getRootNumber(), _forwards), FetchMemberUtil.fetchFunction(n_.getRootNumber(), n_.getMemberNumber(), _forwards));
        }
        if (_anaNode instanceof SuperInvokingConstructor) {
            SuperInvokingConstructor n_ = (SuperInvokingConstructor) _anaNode;
            return new ExecSuperInvokingConstructor(new ExecOperationContent(n_.getContent()), n_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(n_.getInvokingConstructorContent()), FetchMemberUtil.fetchType(n_.getRootNumber(), _forwards), FetchMemberUtil.fetchFunction(n_.getRootNumber(), n_.getMemberNumber(), _forwards));
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
            ExecNamedFunctionBlock ctor_ = FetchMemberUtil.fetchFunction(s_.getRootNumber(), s_.getMemberNumber(), _forwards);
            ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(s_.getRootNumber(), _forwards);
            if (rootBlock_ != null) {
                return new ExecStandardInstancingOperation(rootBlock_,ctor_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), new ExecInstancingStdContent(s_.getInstancingStdContent()));
            }
            return new ExecDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()));
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            return new ExecAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), _forwards.getMapMembers().getValue(s_.getBlock().getNumberAll()).getRootBlock(), FetchMemberUtil.fetchFunction(s_.getRootNumber(), s_.getMemberNumber(), _forwards));
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(a_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock get_ = FetchMemberUtil.fetchFunction(a_.getRootNumber(), a_.getMemberNumber(), _forwards);
            ExecNamedFunctionBlock set_ = FetchMemberUtil.fetchFunction(a_.getRootNumber(), a_.getMemberNumberSet(), _forwards);
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
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(c_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock fct_ = FetchMemberUtil.fetchFunction(c_, _forwards);
            return new ExecChoiceFctOperation(fct_,ex_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true));
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(s_.getRootNumber(), _forwards);
            ExecNamedFunctionBlock fct_ = FetchMemberUtil.fetchFunction(s_, _forwards);
            return new ExecSuperFctOperation(fct_,ex_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecNamedFunctionBlock fct_ = FetchMemberUtil.fetchFunction(f_, _forwards);
            ExecRootBlock ex_ = FetchMemberUtil.fetchType(f_.getRootNumber(), _forwards);
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
            return new ExecSettableFieldOperation(FetchMemberUtil.fetchType(s_.getRootNumber(), _forwards), new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()), new ExecSettableOperationContent(s_.getSettableFieldContent()));
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
            return new ExecExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getCallFctContent()), m_.getOffsetOper(), FetchMemberUtil.fetchFunctionOp(m_.getRootNumber(), m_.getMemberNumber(), _forwards), FetchMemberUtil.fetchType(m_.getRootNumber(), _forwards));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            return new ExecSemiAffectationOperation(new ExecOperationContent(m_.getContent()), new ExecStaticPostEltContent(m_.getClassMethodId(), m_.isPost()), new ExecOperatorContent(m_.getOperatorContent()), FetchMemberUtil.fetchFunctionOp(m_.getRootNumber(), m_.getMemberNumber(), _forwards), FetchMemberUtil.fetchType(m_.getRootNumber(), _forwards), FetchMemberUtil.fetchImplicits(m_.getConverterFrom(), m_.getRootNumberFrom(), m_.getMemberNumberFrom(), _forwards), FetchMemberUtil.fetchImplicits(m_.getConverterTo(), m_.getRootNumberTo(), m_.getMemberNumberTo(), _forwards));
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (n_.getClassMethodId() != null) {
                return new ExecCustNumericOperation(FetchMemberUtil.fetchFunctionOp(n_.getRootNumber(),n_.getMemberNumber(), _forwards), FetchMemberUtil.fetchType(n_.getRootNumber(), _forwards), new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), FetchMemberUtil.getKind(n_.getClassMethodId()), FetchMemberUtil.getType(n_.getClassMethodId()));
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
                    FetchMemberUtil.fetchFunction(m_.getRootNumber(),m_.getMemberNumber(), _forwards),
                    FetchMemberUtil.fetchType(m_.getRootNumber(), _forwards), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return new ExecImplicitOperation(
                    FetchMemberUtil.fetchFunction(m_.getRootNumber(),m_.getMemberNumber(), _forwards),
                    FetchMemberUtil.fetchType(m_.getRootNumber(), _forwards), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
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
            return new ExecAndOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getClassMethodId()), FetchMemberUtil.fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _forwards), FetchMemberUtil.fetchType(c_.getRootNumber(), _forwards), FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _forwards),c_.getOpOffset());
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            return new ExecOrOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getClassMethodId()), FetchMemberUtil.fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _forwards), FetchMemberUtil.fetchType(c_.getRootNumber(), _forwards), FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _forwards),c_.getOpOffset());
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
            return new ExecCompoundAffectationOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), new ExecStaticEltContent(c_.getClassMethodId()), FetchMemberUtil.fetchFunctionOp(c_.getRootNumber(), c_.getMemberNumber(), _forwards), FetchMemberUtil.fetchType(c_.getRootNumber(), _forwards), FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getRootNumberConv(), c_.getMemberNumberConv(), _forwards));
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

    private static void fwd(AnnotationMethodBlock _ana, ExecAnnotationMethodBlock _exec, Coverage _coverage, Forwards _forwards) {
        OperationNode root_ = _ana.getRoot();
        if (root_ == null) {
            _exec.setOpValue(new CustList<ExecOperationNode>());
            return;
        }
        _coverage.putBlockOperationsField(_forwards, _ana);
        CustList<ExecOperationNode> ops_ = getExecutableNodes(root_, _coverage, _forwards, _ana);
        _exec.setOpValue(ops_);
    }

    private static void validateIds(Members _mem) {
        for (EntryCust<OverridableBlock,ExecOverridableBlock> e: _mem.getAllMethods().entryList()) {
            e.getValue().buildImportedTypes(e.getKey().getImportedReturnType(), e.getKey().getImportedParametersTypes());
            String returnTypeGet_ = e.getKey().getReturnTypeGet();
            if (!returnTypeGet_.isEmpty()) {
                e.getValue().setImportedReturnType(returnTypeGet_);
            }
        }
        for (EntryCust<ConstructorBlock,ExecConstructorBlock> e: _mem.getAllCtors().entryList()) {
            e.getValue().buildImportedTypes(e.getKey().getImportedReturnType(), e.getKey().getImportedParametersTypes());
        }
        for (EntryCust<AnnotationMethodBlock,ExecAnnotationMethodBlock> e: _mem.getAllAnnotMethods().entryList()) {
            AnnotationMethodBlock key1_ = e.getKey();
            e.getValue().setImportedReturnType(key1_.getImportedReturnType());
            e.getValue().getImportedParametersTypes().addAllElts(key1_.getImportedParametersTypes());
        }
        for (EntryCust<InnerElementBlock, ExecInnerElementBlock> e: _mem.getAllInnerElementFields().entryList()) {
            buildImportedTypes(e.getValue(),e.getKey());
        }
        for (EntryCust<ElementBlock, ExecElementBlock> e: _mem.getAllSimpleElementFields().entryList()) {
            buildImportedTypes(e.getValue(),e.getKey());
        }
        for (EntryCust<FieldBlock, ExecFieldBlock> e: _mem.getAllExplicitFields().entryList()) {
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

    private static void fwdExpressionLanguageReadOnly(InnerElementBlock _ana, ExecInnerElementBlock _exec, Coverage _coverage, Forwards _forwards) {
        _exec.setTrOffset(_ana.getTrOffset());
        _coverage.putBlockOperations(_exec, _ana);
        _coverage.putBlockOperations(_ana);
        _exec.setOpValue(getExecutableNodes(_ana.getRoot(), _coverage, _forwards, _ana));
    }

    private static void fwdExpressionLanguageReadOnly(ElementBlock _ana, ExecInnerTypeOrElement _exec, Coverage _coverage, Forwards _forwards) {
        _exec.setTrOffset(_ana.getTrOffset());
        _coverage.putBlockOperations((ExecBlock) _exec, _ana);
        _coverage.putBlockOperations(_ana);
        _exec.setOpValue(getExecutableNodes(_ana.getRoot(), _coverage, _forwards, _ana));
    }

    private static void fwdAnnotationsParameters(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<CustList<ExecOperationNode>>> ops_ = new CustList<CustList<CustList<ExecOperationNode>>>();
        for (CustList<OperationNode> l: _ana.getRootsList()) {
            CustList<CustList<ExecOperationNode>> annotation_;
            annotation_ = new CustList<CustList<ExecOperationNode>>();
            for (OperationNode r: l) {
                annotation_.add(getExecutableNodes(r, _coverage, _forwards, _ana));
            }
            ops_.add(annotation_);
        }
        _ann.getAnnotationsOpsParams().addAllElts(ops_);
    }

    private static void fwdAnnotations(ElementBlock _ana, ExecElementBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(r, _coverage, _forwards, _ana));
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(FieldBlock _ana, ExecFieldBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(r, _coverage, _forwards, _ana));
        }
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdExpressionLanguageReadOnly(FieldBlock _ana, ExecFieldBlock _exec, Coverage _coverage, Forwards _forwards) {
        processPutCoverage(_ana, _exec, _coverage);
        _exec.setOpValue(getExecutableNodes(_ana.getRoot(), _coverage, _forwards, _ana));
    }

    private static void processPutCoverage(FieldBlock _ana, ExecFieldBlock _exec, Coverage _coverage) {
        _coverage.putBlockOperations(_exec, _ana);
        _coverage.putBlockOperations(_ana);
    }

    private static void fwdAnnotations(NamedFunctionBlock _ana, ExecNamedFunctionBlock _ex, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(r, _coverage, _forwards, _ana));
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    private static void fwdAnnotations(RootBlock _ana, ExecRootBlock _ann, Coverage _coverage, Forwards _forwards) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        for (OperationNode r: _ana.getRoots()) {
            ops_.add(getExecutableNodes(r, _coverage, _forwards, _ana));
        }
        _ann.getAnnotationsOps().clear();
        _ann.getAnnotationsOps().addAllElts(ops_);
    }

}
