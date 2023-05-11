package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodId;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodIdList;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class CallersRef {
    public static final String TRIM_FILTER = "";
    private final CustList<AbsBkSrcFileLocation> blocksLocations = new CustList<AbsBkSrcFileLocation>();
    private final CustList<ResultExpressionBlockLabel> breakContinue = new CustList<ResultExpressionBlockLabel>();
    private final CustList<FileBlockIndex> labels = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> variablesParamsUse = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> fieldsUse = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> fieldsUseInit = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> fieldsRefUse = new CustList<FileBlockIndex>();
//    private final CustList<BlockCallerFct> allBlocks = new CustList<BlockCallerFct>();

    //    private final CustList<SrcFileLocation> directRefNamed = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directRefNamedStd = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directRefNamedStdCtor = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directRefImplCtor = new CustList<SrcFileLocation>();
    private final CustList<FileBlockIndex> callNamedUse = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callNamedFieldUse = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callNamedUseImpl = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callNamedUsePoly = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callNamedOverridden = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callNamedOverriding = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callAnonRefUse = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callNamedRefUse = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> callNamedRefUsePoly = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceNewTypes = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceNewTypesElt = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceNewTypesEltFwd = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceNewTypesEltArray = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceArobase = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceNewTypesFwd = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceNewTypesRef = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> staticAccess = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> castOperation = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> instanceOperation = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> interfacesInit = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> interfacesInitRef = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> typesFinders = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> typesFindersRef = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> typesInfos = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> typesInfosDef = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> paramType = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> returnType = new CustList<FileBlockIndex>();
    private final CustList<MemberAnnotFilterCall> annotCandidatesMembers = new CustList<MemberAnnotFilterCall>();
    private final CustList<MemberAnnotFilterCall> annotCandidatesParameters = new CustList<MemberAnnotFilterCall>();
    private final CustList<MemberAnnotFilterCall> annotCandidatesSuppl = new CustList<MemberAnnotFilterCall>();
    private final CustList<MemberAnnotFilterCall> annotCandidatesDefValue = new CustList<MemberAnnotFilterCall>();
    private final CustList<FileBlockIndex> annotCandidatesCallsStdMembers = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsStdParameters = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsStdSuppl = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsStdDefValue = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitMembers = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitParameters = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitSuppl = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitDefValue = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitArobaseMembers = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitArobaseParameters = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitArobaseSuppl = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> annotCandidatesCallsInitArobaseDefValue = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> dynCallStd = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> dynCallPotential = new CustList<FileBlockIndex>();
    private final CustList<String> dynCallRefer = new CustList<String>();
    private final CustList<LambdaDynFilterCall> lambda = new CustList<LambdaDynFilterCall>();
    private final CustList<AnnotationInitFilterCall> annotationsMembers = new CustList<AnnotationInitFilterCall>();
    private final CustList<AnnotationInitFilterCall> annotationsParameters = new CustList<AnnotationInitFilterCall>();
    private final CustList<AnnotationInitFilterCall> annotationsSuppl = new CustList<AnnotationInitFilterCall>();
    private final CustList<AnnotationInitFilterCall> annotationsDefValue = new CustList<AnnotationInitFilterCall>();
    private final CustList<MemberCallingsBlock> fcts = new CustList<MemberCallingsBlock>();
    private final CustList<FileBlockIndex> internElts = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> internEltsFct = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> fieldDeclaring = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> variableDeclaring = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> interfacesStatic = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> interfacesInstance = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> constraints = new CustList<FileBlockIndex>();
    private final CustList<FileBlockIndex> inherits = new CustList<FileBlockIndex>();
    //    private final CustList<SrcFileLocation> directCallNamedRefAll = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directCallImplicits = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNew = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNewStd = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNewTypes = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNewInherits = new CustList<SrcFileLocation>();
    public static CallersRef loop(AnalyzedPageEl _page, CustList<SrcFileLocation> _piano) {
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        CallersRef c_ = new CallersRef();
        c_.callNamedOverridden.addAllElts(feedOverridden(_page, _piano));
        c_.callNamedOverriding.addAllElts(feedOverriding(_page, _piano));
        for (RootBlock r : _page.getAllFoundTypes()){
            c_.type(ls_, r);
        }
        for (OperatorBlock o: _page.getAllOperators()){
            ls_.addAllElts(c_.loopFct(o));
        }
        for (AnonymousLambdaOperation e: _page.getAllAnonymousLambda()) {
            ls_.addAllElts(c_.loopFct(e.getBlock()));
        }
        for (SwitchOperation e: _page.getAllSwitchMethods()) {
            ls_.addAllElts(c_.loopFct(e.getSwitchMethod()));
        }
        intern(_page, _piano, c_);
        for (MemberCallingsBlock r: c_.fcts) {
            c_.callingsCustDirect(r,_piano);
        }
        for (AbsBkSrcFileLocation a: c_.blocksLocations) {
            c_.typesFound(a,_piano);
        }
//        for (BlockCallerFct b: c_.allBlocks) {
////            MemberCallingsBlock caller_ = b.getCaller();
////            for (SrcFileLocation s: tryDecl(b.getBlock())){
////                addIfMatch(s,new SrcFileLocationMethod(caller_.getParent(),caller_),s.getFile(),s.getIndex(),c_.variablesParamsUse,_piano);
////            }
//            c_.def(b.getBlock(),_piano);
//        }
        CustList<ResultExpressionBlockOperation> ops_ = new CustList<ResultExpressionBlockOperation>();
        for (ResultExpressionBlock r: ls_) {
            ops_.addAllElts(c_.loopOperation(r));
        }
        for (ResultExpressionBlockLabel r: c_.breakContinue) {
            AbsBk bl_ = r.getBlock();
            if (bl_ instanceof BreakBlock) {
                addIfMatch(new SrcFileLocationLabel(((BreakBlock)bl_).getLabel(),bl_.getFile(), ((BreakBlock)bl_).getLabelOffsetRef()),r.getCaller(),bl_.getFile(),bl_.getOffset()+bl_.getLengthHeader(),c_.labels,_piano);
            }
            if (bl_ instanceof ContinueBlock) {
                addIfMatch(new SrcFileLocationLabel(((ContinueBlock)bl_).getLabel(),bl_.getFile(), ((ContinueBlock)bl_).getLabelOffsetRef()),r.getCaller(),bl_.getFile(),bl_.getOffset()+bl_.getLengthHeader(),c_.labels,_piano);
            }
        }
        for (ResultExpressionBlockOperation o: ops_) {
            c_.lookForAnnotationsCandidates(o);
        }
        for (ResultExpressionBlockOperation o: ops_) {
            c_.lookForDynCall(_page,o);
        }
        for (ResultExpressionBlockOperation o: ops_) {
            c_.typesFound(o,_piano);
            c_.callingsCustDirect(o,_piano);
            c_.symbols(o,_piano);
            c_.fctPub(o,o.getBlock().getResultClass().getFunction(),0, _piano, c_.callNamedUseImpl);
        }
        feed(_page, c_.annotCandidatesCallsStdMembers,c_.annotCandidatesMembers,c_.annotationsMembers,c_.annotCandidatesCallsInitMembers,c_.annotCandidatesCallsInitArobaseMembers);
        feed(_page, c_.annotCandidatesCallsStdParameters,c_.annotCandidatesParameters,c_.annotationsParameters,c_.annotCandidatesCallsInitParameters,c_.annotCandidatesCallsInitArobaseParameters);
        feed(_page, c_.annotCandidatesCallsStdSuppl,c_.annotCandidatesSuppl,c_.annotationsSuppl,c_.annotCandidatesCallsInitSuppl,c_.annotCandidatesCallsInitArobaseSuppl);
        feed(_page, c_.annotCandidatesCallsStdDefValue,c_.annotCandidatesDefValue,c_.annotationsDefValue,c_.annotCandidatesCallsInitDefValue,c_.annotCandidatesCallsInitArobaseDefValue);
        callDyn(_page, c_.dynCallStd, c_.dynCallRefer, c_.lambda, c_.dynCallPotential);
        return c_;
    }

    private static void intern(AnalyzedPageEl _page, CustList<SrcFileLocation> _piano, CallersRef _c) {
        for(RootBlock r: _page.getAllFoundTypes()) {
            for (AbsBk b: ClassesUtil.getDirectChildren(r)) {
                if (b instanceof InternOverrideBlock) {
                    internElt(_piano, _c, r, (InternOverrideBlock) b);
                }
            }
        }
    }

    private static void internElt(CustList<SrcFileLocation> _piano, CallersRef _c, RootBlock _r, InternOverrideBlock _b) {
        for (PartOffsetsClassMethodIdList l: _b.getAllPartsTypes()) {
            for (PartOffsetsClassMethodId p:l.getOverrides()) {
                _c.fctPub(_b,new SrcFileLocationType(_r.getIdRowCol(), _r),p.getFct(),p.getBegin(), _piano, _c.internElts);
            }
        }
        for (PartOffsetsClassMethodIdList l: _b.getAllPartsTypes()) {
            addAllIfMatch(fetch(l.getTypes()),new SrcFileLocationType(_r.getIdRowCol(), _r), _b.getFile(), _c.internElts, _piano);
            for (PartOffsetsClassMethodId p:l.getOverrides()) {
                addAllIfMatch(fetch(p.getTypes()),new SrcFileLocationType(_r.getIdRowCol(), _r), _b.getFile(), _c.internElts, _piano);
                addAllIfMatch(fetch(p.getSuperTypes()),new SrcFileLocationType(_r.getIdRowCol(), _r), _b.getFile(), _c.internElts, _piano);
            }
        }
    }

    private static void callDyn(AnalyzedPageEl _page, CustList<FileBlockIndex> _d, CustList<String> _c, CustList<LambdaDynFilterCall> _r, CustList<FileBlockIndex> _pot) {
        for (FileBlockIndex f: _d) {
            for (LambdaDynFilterCall e: _r) {
                if (matchesCall(_page,e,_c)) {
                    _pot.add(new FileBlockIndex(f.getFile(), f.getIndex(), e.getCalleeRef(), f.getCaller()));
                }
            }
        }
    }
    private static boolean matchesCall(AnalyzedPageEl _page, LambdaDynFilterCall _e, CustList<String> _candidates) {
        for (String c: _candidates) {
            Mapping m_ = new Mapping();
            m_.setArg(_e.getLambda());
            m_.setParam(c);
            if (AnaInherits.isCorrectOrNumbers(m_,_page)){
                return true;
            }
        }
        return false;
    }

    private static void feed(AnalyzedPageEl _page, CustList<FileBlockIndex> _f, CustList<MemberAnnotFilterCall> _candidates, CustList<AnnotationInitFilterCall> _a, CustList<FileBlockIndex> _c, CustList<FileBlockIndex> _potArobase) {
        for (FileBlockIndex f: _f) {
            for (AnnotationInitFilterCall e: _a) {
                CustList<String> m_ = matches(_page, e, _candidates);
                if (StringUtil.contains(m_, TRIM_FILTER) || StringUtil.contains(m_, e.getInit())) {
                    _c.add(new FileBlockIndex(f.getFile(), f.getIndex(), e.getCalleeRef(), f.getCaller()));
                    _potArobase.add(new FileBlockIndex(f.getFile(), f.getIndex(), e.getCallerRef(), f.getCaller()));
                }
            }
        }
    }
    private static CustList<String> matches(AnalyzedPageEl _page, AnnotationInitFilterCall _e, CustList<MemberAnnotFilterCall> _candidates) {
        CustList<String> a_ = new CustList<String>();
        filterAndMap(_candidates, a_, _page.getAliasAnnotated());
        if (_e.getBlock() instanceof InfoBlock) {
            filterAndMap(_candidates, a_, _page.getAliasField());
        }
        if (_e.getBlock() instanceof RootBlock) {
            filterAndMap(_candidates, a_, _page.getAliasClassType());
        }
        if (_e.getBlock() instanceof ConstructorBlock) {
            filterAndMap(_candidates, a_, _page.getAliasConstructor());
        } else if (_e.getBlock() instanceof MemberCallingsBlock) {
            filterAndMap(_candidates, a_, _page.getAliasMethod());
        }
        return a_;
    }

    private static void filterAndMap(CustList<MemberAnnotFilterCall> _candidates, CustList<String> _ls, String _alias) {
        for (MemberAnnotFilterCall f: _candidates) {
            if (StringUtil.quickEq(f.getMember(), _alias)) {
                _ls.add(f.getAnnotation());
            }
        }
    }

    private static CustList<FileBlockIndex> feedOverridden(AnalyzedPageEl _page, CustList<SrcFileLocation> _piano) {
        CustList<FileBlockIndex> out_ = new CustList<FileBlockIndex>();
        for (EntryCust<NamedCalledFunctionBlock, StringMap<GeneStringOverridable>> e: _page.getOverriding().entryList()) {
            NamedCalledFunctionBlock redef_ = e.getKey();
            SrcFileLocationMethod caller_ = new SrcFileLocationMethod(redef_.getParent(), redef_);
            for (GeneStringOverridable g: e.getValue().values()) {
                addIfMatch(new SrcFileLocationMethod(g.getType(),g.getBlock()),caller_,caller_.getFile(),caller_.getIndex(), out_, _piano);
            }
        }
        return out_;
    }

    private static CustList<FileBlockIndex> feedOverriding(AnalyzedPageEl _page, CustList<SrcFileLocation> _piano) {
        CustList<FileBlockIndex> out_ = new CustList<FileBlockIndex>();
        for (EntryCust<NamedCalledFunctionBlock, StringMap<GeneStringOverridable>> e: _page.getOverriding().entryList()) {
            NamedCalledFunctionBlock redef_ = e.getKey();
            SrcFileLocationMethod caller_ = new SrcFileLocationMethod(redef_.getParent(), redef_);
            for (SrcFileLocation s: _piano) {
                if (s.match(caller_)) {
                    for (GeneStringOverridable g: e.getValue().values()) {
                        out_.add(new FileBlockIndex(caller_.getFile(),caller_.getIndex(),caller_,new SrcFileLocationMethod(g.getType(),g.getBlock())));
                    }
                }
            }
        }
        return out_;
    }

    private void type(CustList<ResultExpressionBlock> _ls, RootBlock _r) {
        if (!(_r instanceof InfoBlock)) {
            blocksLocations.add(new AbsBkSrcFileLocation(new SrcFileLocationType(_r.getIdRowCol(),_r),_r));
            _ls.addAllElts(rootBlock(null,_r));
        }
        for (InfoBlock i: _r.getFieldsBlocks()) {
            for (String f: i.getElements().getFieldName()) {
                int o_ = AnaTypeUtil.getIndex(i, f);
                blocksLocations.add(new AbsBkSrcFileLocation(new SrcFileLocationFieldCust(new ClassField(_r.getFullName(),f),_r,o_),(AbsBk) i));
            }
            _ls.addAllElts(rootBlock(null,(AbsBk) i));
        }
        for (NamedCalledFunctionBlock b: _r.getOverridableBlocks()) {
            _ls.addAllElts(loopFct(b));
        }
        for (NamedCalledFunctionBlock b: _r.getAnnotationsMethodsBlocks()) {
            blocksLocations.add(new AbsBkSrcFileLocation(new SrcFileLocationMethod(_r,b),b));
            _ls.addAllElts(rootBlock(null,b));
        }
        for (ConstructorBlock b: _r.getConstructorBlocks()) {
            _ls.addAllElts(loopFct(b));
        }
        for (InstanceBlock b: _r.getInstanceBlocks()) {
            _ls.addAllElts(loopFct(b));
        }
        for (StaticBlock b: _r.getStaticBlocks()) {
            _ls.addAllElts(loopFct(b));
        }
    }

    private CustList<ResultExpressionBlock> loopFct(MemberCallingsBlock _mem) {
        fcts.add(_mem);
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        if (_mem.getFirstChild() == null) {
            blocksLocations.add(new AbsBkSrcFileLocation(new SrcFileLocationMethod(_mem.getParent(),_mem),_mem));
            ls_.addAllElts(rootBlock(_mem,_mem));
            return ls_;
        }
        AbsBk en_ = _mem;
        while (en_ != null) {
            AbsBk n_ = en_.getFirstChild();
            blocksLocations.add(new AbsBkSrcFileLocation(new SrcFileLocationMethod(_mem.getParent(),_mem),en_));
            ls_.addAllElts(rootBlock(_mem,en_));
            if (n_ != null) {
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
                if (par_ == _mem) {
                    en_ = null;
                } else {
                    en_ = par_;
                }
            }
        }
        return ls_;
    }
    private CustList<ResultExpressionBlockOperation> loopOperation(ResultExpressionBlock _mem) {
        CustList<ResultExpressionBlockOperation> ls_ = new CustList<ResultExpressionBlockOperation>();
        OperationNode en_ = _mem.getRes().getRoot();
        while (en_ != null) {
            OperationNode n_ = en_.getFirstChild();
            choux(_mem, ls_, en_);
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            while (en_ != null) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                en_ = en_.getParent();
            }
        }
        return ls_;
    }

    private void choux(ResultExpressionBlock _mem, CustList<ResultExpressionBlockOperation> _ls, OperationNode _en) {
        if (_mem.getBegin() == -1 || ResultExpressionOperationNode.begin(_mem.getRes(), _en) >= _mem.getBegin() && ResultExpressionOperationNode.end(_mem.getRes(), _en) <= _mem.getEnd()) {
            _ls.add(new ResultExpressionBlockOperation(_en, _mem));
        }
    }

    private CustList<ResultExpressionBlock> rootBlock(MemberCallingsBlock _caller, AbsBk _en) {
        CustList<ResultExpressionBlock> annotFields_ = new CustList<ResultExpressionBlock>();
        if (_en instanceof InfoBlock) {
            ResultParsedAnnots a_ = ((InfoBlock) _en).getAnnotations();
            addAnnots(_en,annotFields_, a_, AnnotationKind.MEMBER);
        }
        if (_en instanceof InfoBlock) {
            RootBlock d_ = ((InfoBlock) _en).getDeclaringType();
            annotFields_.addAllElts(declared(((InfoBlock)_en).getRes().getRoot(),_en,d_,((InfoBlock)_en).getRes()));
            return annotFields_;
        }
        if (_en instanceof RootBlock) {
            ResultParsedAnnots a_ = ((RootBlock) _en).getAnnotations();
            addAnnots(_en,annotFields_, a_, AnnotationKind.MEMBER);
            return annotFields_;
        }
        if (_en instanceof NamedFunctionBlock) {
            addAnnotsList(annotFields_,(NamedFunctionBlock)_en);
            return annotFields_;
        }
        if (_en instanceof SwitchMethodBlock) {
            resSw(annotFields_,(SwitchMethodBlock)_en);
            return annotFields_;
        }
        return instrLook(_caller,_en);
    }
    private static CustList<ResultExpressionBlock> declared(OperationNode _root, AbsBk _en, RootBlock _type, ResultExpression _res) {
        CustList<ResultExpressionBlock> fields_ = new CustList<ResultExpressionBlock>();
        if (_en instanceof InnerTypeOrElement) {
            String f_ = ((InnerTypeOrElement) _en).getUniqueFieldName();
            int o_ = AnaTypeUtil.getIndex(((InfoBlock) _en), f_);
            fields_.add(new ResultExpressionBlock(SrcFileLocationField.fieldInit(new ClassField(_type.getFullName(),f_), _type,o_,null), _en, _res));
        } else if (_root instanceof DeclaringOperation) {
            for (OperationNode o: ((DeclaringOperation)_root).getChildrenNodes()) {
                tryAddFields(_en, _type, _res, fields_, o);
            }
        } else {
            tryAddFields(_en, _type, _res, fields_, _root);
        }
        return fields_;
    }

    private static void tryAddFields(AbsBk _en, RootBlock _type, ResultExpression _res, CustList<ResultExpressionBlock> _fields, OperationNode _o) {
        OperationNode d_ = declaring(_o);
        if (d_ instanceof DeclaredFieldOperation) {
            ClassField cf_ = ((DeclaredFieldOperation) d_).getFieldIdReadOnly();
            int o_ = ((DeclaredFieldOperation) d_).getValueOffset();
            int begin_ = ResultExpressionOperationNode.begin(_res, _o);
            int end_ = ResultExpressionOperationNode.end(_res, _o);
            _fields.add(new ResultExpressionBlock(SrcFileLocationField.fieldInit(cf_, _type,o_,null), _en, _res,begin_,end_));
        } else {
            int begin_ = ResultExpressionOperationNode.begin(_res, _o);
            int end_ = ResultExpressionOperationNode.end(_res, _o);
            _fields.add(new ResultExpressionBlock(SrcFileLocationField.fieldInit(new ClassField("",""), _type,0,null), _en, _res,begin_,end_));
        }
    }

    private static OperationNode declaring(OperationNode _e) {
        if (_e instanceof AffectationOperation) {
            return _e.getFirstChild();
        }
        return _e;
    }

    public static void resSw(CustList<ResultExpressionBlock> _list, SwitchMethodBlock _block) {
        ResultParsedAnnots a_ = _block.getAnnotations();
        addAnnots(_block,_list,a_, AnnotationKind.MEMBER);
        CustList<ResultParsedAnnots> params_ = _block.getAnnotationsParams();
        for (ResultParsedAnnots a: params_) {
            addAnnots(_block,_list,a, AnnotationKind.PARAMETER);
        }
    }
    private static void addAnnotsList(CustList<ResultExpressionBlock> _list, NamedFunctionBlock _block) {
        if (AbsBk.isAnnotBlock(_block)) {
            _list.add(new ResultExpressionBlock(new SrcFileLocationMethod(_block.getFile(),_block),_block,((NamedCalledFunctionBlock)_block).getRes(),AnnotationKind.DEF_VALUE));
        }
        ResultParsedAnnots a_ = _block.getAnnotations();
        addAnnots(_block,_list,a_, AnnotationKind.MEMBER);
        CustList<ResultParsedAnnots> params_ = _block.getAnnotationsParams();
        for (ResultParsedAnnots a: params_) {
            addAnnots(_block,_list,a, AnnotationKind.PARAMETER);
        }
        if (_block instanceof NamedCalledFunctionBlock) {
            ResultParsedAnnots annotationsSupp_ = ((NamedCalledFunctionBlock) _block).getAnnotationsSupp();
            addAnnots(_block,_list,annotationsSupp_, AnnotationKind.SUPPL);
        }
    }
    private static void addAnnots(AbsBk _en,CustList<ResultExpressionBlock> _anns, ResultParsedAnnots _a, AnnotationKind _k) {
        for (ResultParsedAnnot a: _a.getAnnotations()) {
            _anns.add(new ResultExpressionBlock(new SrcFileLocationAnnotationMember(_en.getFile(),a),_en,a.getRes(),_k));
        }
    }

    private CustList<ResultExpressionBlock> instrLook(MemberCallingsBlock _caller,AbsBk _block) {
        if (_block instanceof BreakBlock || _block instanceof ContinueBlock) {
            breakContinue.add(new ResultExpressionBlockLabel(new SrcFileLocationMethod(_caller.getParent(),_caller),_block));
        }
        if (_block instanceof ConditionBlock) {
            ResultExpression res_ = ((ConditionBlock) _block).getRes();
            return single(_caller,_block,res_);
        }
        if (_block instanceof WithFilterContent) {
            return caseLook(_caller,(WithFilterContent) _block);
        }
        if (_block instanceof SwitchBlock) {
            ResultExpression res_ = ((SwitchBlock) _block).getRes();
            return single(_caller,_block,res_);
        }
        if (_block instanceof ForEachLoop) {
            ResultExpression res_ = ((ForEachLoop) _block).getRes();
            return single(_caller,_block,res_);
        }
        if (_block instanceof ForEachTable) {
            ResultExpression res_ = ((ForEachTable) _block).getRes();
            return single(_caller,_block,res_);
        }
        return defLook(_caller,_block);
    }

    private static CustList<ResultExpressionBlock> caseLook(MemberCallingsBlock _caller,WithFilterContent _block) {
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),(AbsBk) _block,_block.getFilterContent().getResValue()));
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),(AbsBk) _block,_block.getFilterContent().getResCondition()));
        return ls_;
    }

    private static CustList<ResultExpressionBlock> defLook(MemberCallingsBlock _caller,AbsBk _block) {
        if (_block instanceof Line) {
            ResultExpression res_ = ((Line) _block).getRes();
            return single(_caller,_block,res_);
        }
        if (_block instanceof ReturnMethod) {
            ResultExpression res_ = ((ReturnMethod) _block).getRes();
            return single(_caller,_block,res_);
        }
        if (_block instanceof Throwing) {
            ResultExpression res_ = ((Throwing) _block).getRes();
            return single(_caller,_block,res_);
        }
        if (_block instanceof ForIterativeLoop) {
            return lookForIter(_caller,(ForIterativeLoop) _block);
        }
        if (_block instanceof ForMutableIterativeLoop) {
            return lookForMut(_caller,(ForMutableIterativeLoop) _block);
        }
        return new CustList<ResultExpressionBlock>();
    }

    private static CustList<ResultExpressionBlock> single(MemberCallingsBlock _caller,AbsBk _en,ResultExpression _res) {
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),_en,_res));
        return ls_;
    }
    private static CustList<ResultExpressionBlock> lookForIter(MemberCallingsBlock _caller,ForIterativeLoop _block) {
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),_block,_block.getResInit()));
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),_block,_block.getResExp()));
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),_block,_block.getResStep()));
        return ls_;
    }

    private static CustList<ResultExpressionBlock> lookForMut(MemberCallingsBlock _caller,ForMutableIterativeLoop _block) {
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),_block,_block.getResInit()));
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),_block,_block.getResExp()));
        ls_.add(new ResultExpressionBlock(new SrcFileLocationMethod(_caller.getParent(),_caller),_block,_block.getResStep()));
        return ls_;
    }
    public void lookForAnnotationsCandidates(ResultExpressionBlockOperation _c) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        SrcFileLocation caller_ = _c.getRes().getCaller();
        if (o_ instanceof AbsFctOperation) {
            int delta_ = ((AbsFctOperation) o_).getDelta();
            int index_ = begin(_c) + delta_;
            StandardMethod s_ = ((AbsFctOperation) o_).getCallFctContent().getStandardMethod();
            if (s_ != null) {
                if (s_.getCaller() instanceof FctAnnotatedGetAnnotations0) {
                    MemberAnnotFilterCall m_ = new MemberAnnotFilterCall();
                    m_.setMember(((AbsFctOperation) o_).getPreviousResultClass().getSingleNameOrEmpty());
                    annotCandidatesMembers.add(m_);
                    annotCandidatesCallsStdMembers.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((AbsFctOperation)o_).getCallFctContent().getStandardType(),s_),caller_));
                } else if (s_.getCaller() instanceof FctAnnotatedGetAnnotationsParam0) {
                    MemberAnnotFilterCall m_ = new MemberAnnotFilterCall();
                    m_.setMember(((AbsFctOperation) o_).getPreviousResultClass().getSingleNameOrEmpty());
                    annotCandidatesParameters.add(m_);
                    annotCandidatesCallsStdParameters.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((AbsFctOperation)o_).getCallFctContent().getStandardType(),s_),caller_));
                } else if (s_.getCaller() instanceof FctAnnotatedGetAnnotationsSupp0) {
                    MemberAnnotFilterCall m_ = new MemberAnnotFilterCall();
                    m_.setMember(((AbsFctOperation) o_).getPreviousResultClass().getSingleNameOrEmpty());
                    annotCandidatesSuppl.add(m_);
                    annotCandidatesCallsStdSuppl.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((AbsFctOperation)o_).getCallFctContent().getStandardType(),s_),caller_));
                } else if (s_.getCaller() instanceof FctMethodGetDefaultValue) {
                    MemberAnnotFilterCall m_ = new MemberAnnotFilterCall();
                    m_.setMember(((AbsFctOperation) o_).getPreviousResultClass().getSingleNameOrEmpty());
                    annotCandidatesDefValue.add(m_);
                    annotCandidatesCallsStdDefValue.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((AbsFctOperation)o_).getCallFctContent().getStandardType(),s_),caller_));
                } else if (s_.getCaller() instanceof FctAnnotatedGetAnnotations1) {
                    tissuVocal((AbsFctOperation) o_,annotCandidatesMembers);
                    annotCandidatesCallsStdMembers.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((AbsFctOperation)o_).getCallFctContent().getStandardType(),s_),caller_));
                } else if (s_.getCaller() instanceof FctAnnotatedGetAnnotationsParam1) {
                    tissuVocal((AbsFctOperation) o_,annotCandidatesParameters);
                    annotCandidatesCallsStdParameters.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((AbsFctOperation)o_).getCallFctContent().getStandardType(),s_),caller_));
                } else if (s_.getCaller() instanceof FctAnnotatedGetAnnotationsSupp1) {
                    tissuVocal((AbsFctOperation) o_,annotCandidatesSuppl);
                    annotCandidatesCallsStdSuppl.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((AbsFctOperation)o_).getCallFctContent().getStandardType(),s_),caller_));
                }
            }
        }
    }
    public void lookForDynCall(AnalyzedPageEl _page,ResultExpressionBlockOperation _c) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        SrcFileLocation caller_ = _c.getRes().getCaller();
        if (o_ instanceof CallDynMethodOperation) {
            int delta_ = ((CallDynMethodOperation) o_).getOffsetFct();
            int index_ = begin(_c) + delta_;
            StandardMethod s_ = ((CallDynMethodOperation) o_).getStdMethod();
            if (s_ != null && !((CallDynMethodOperation) o_).getRefer().isEmpty()) {
                dynCallStd.add(new FileBlockIndex(f_,index_,new SrcFileLocationStdMethod(((CallDynMethodOperation) o_).getStdType(),s_),caller_));
                dynCallRefer.add(transform(_page,((CallDynMethodOperation) o_).getRefer()));
            }
        }
    }
    private static String transform(AnalyzedPageEl _page,String _in) {
        StringList parts_ = StringExpUtil.getAllTypes(_in);
        int s_ = parts_.size();
        String id_ = parts_.get(0);
        CustList<String> all_ = new CustList<String>();
        for (int i = 1; i < s_; i++) {
            transform(_page, parts_, all_, i);
        }
        return AnaInherits.getRealClassName(id_,all_);
    }

    private static void transform(AnalyzedPageEl _page, StringList _input, CustList<String> _output, int _i) {
        String p_ = _input.get(_i);
        if (!p_.contains("#")) {
            _output.add(p_);
            return;
        }
        if (_input.isValidIndex(_i +1)||p_.startsWith("~")) {
            _output.add("?");
            return;
        }
        DimComp q_ = StringExpUtil.getQuickComponentBaseType(p_);
        int d_ = q_.getDim();
        if (q_.getComponent().startsWith("#")) {
            _output.add(StringExpUtil.getPrettyArrayType(_page.getAliasObject(),d_));
            return;
        }
        AnaGeneType r_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(p_));
        if (r_ instanceof RootBlock) {
            _output.add(StringExpUtil.getPrettyArrayType(((RootBlock)r_).getWildCardString(),d_));
            return;
        }
        int parts_ = StringExpUtil.getAllTypes(p_).size()-1;
        CustList<String> wc_ = new CustList<String>();
        for (int i = 0; i < parts_; i++) {
            wc_.add("?");
        }
        _output.add(StringExpUtil.getPrettyArrayType(AnaInherits.getRealClassName(r_.getFullName(),wc_),d_));
    }

    private static void tissuVocal(AbsFctOperation _o, CustList<MemberAnnotFilterCall> _types) {
        Argument ar_ = _o.getFirstChild().getArgument();
        MemberAnnotFilterCall m_ = new MemberAnnotFilterCall();
        m_.setMember(_o.getPreviousResultClass().getSingleNameOrEmpty());
        if (ar_ != null && ar_.getStruct() instanceof ClassMetaInfo) {
            m_.setAnnotation(((ClassMetaInfo)ar_.getStruct()).getFormatted().getFormatted());
        }
        _types.add(m_);
    }
    public void typesFound(AbsBkSrcFileLocation _c, CustList<SrcFileLocation> _piano) {
        AbsBk bl_ = _c.getBlock();
        if (bl_ instanceof InnerTypeOrElement) {
            addAllIfMatch(fetch(((InnerTypeOrElement)bl_).getElementContent().getPartOffsets()),_c.getCaller(),bl_.getFile(),fieldDeclaring,_piano);
        }
        if (bl_ instanceof FieldBlock) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((FieldBlock)bl_).getTypePartOffsets(),new AllTypeSegmentFilter()),_c.getCaller(),bl_.getFile(),fieldDeclaring,_piano);
        }
        if (bl_ instanceof DeclareVariable) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((DeclareVariable)bl_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getCaller(),bl_.getFile(),variableDeclaring,_piano);
        }
        if (bl_ instanceof ForMutableIterativeLoop) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForMutableIterativeLoop)bl_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getCaller(),bl_.getFile(),variableDeclaring,_piano);
        }
        if (bl_ instanceof ForEachLoop) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachLoop)bl_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getCaller(),bl_.getFile(),variableDeclaring,_piano);
        }
        if (bl_ instanceof WithFilterContent) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((WithFilterContent)bl_).getFilterContent().getPartOffsets(), new AllTypeSegmentFilter()),_c.getCaller(),bl_.getFile(),variableDeclaring,_piano);
        }
        if (bl_ instanceof ForEachTable) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachTable)bl_).getPartOffsetsFirst(),new AllTypeSegmentFilter()),_c.getCaller(),bl_.getFile(),variableDeclaring,_piano);
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachTable)bl_).getPartOffsetsSecond(),new AllTypeSegmentFilter()),_c.getCaller(),bl_.getFile(),variableDeclaring,_piano);
        }
        if (bl_ instanceof RootBlock) {
            addAllIfMatch(fetch(((RootBlock) bl_).getPartsStaticInitInterfacesOffset()), _c.getCaller(), bl_.getFile(), interfacesStatic,_piano);
            addAllIfMatch(fetch(((RootBlock) bl_).getPartsInstInitInterfacesOffset()), _c.getCaller(), bl_.getFile(), interfacesInstance,_piano);
            if (!(bl_ instanceof AnonymousTypeBlock)) {
                for (TypeVar t : ((RootBlock) bl_).getParamTypes()) {
                    addAllIfMatch(fetchAna(t.getResults()), _c.getCaller(), bl_.getFile(), constraints, _piano);
                }
            }
            addAllIfMatch(fetchAna(((RootBlock) bl_).getResults()), _c.getCaller(), bl_.getFile(), inherits,_piano);
        }
    }
    public void typesFound(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (o_ instanceof EnumValueOfOperation) {
            addAllIfMatch(fetch(((EnumValueOfOperation)o_).getPartOffsets()),_c.getRes().getCaller(), f_, staticAccess, _piano);
        }
        if (o_ instanceof ValuesOperation) {
            addAllIfMatch(fetch(((ValuesOperation)o_).getPartOffsets()),_c.getRes().getCaller(), f_, staticAccess, _piano);
        }
        if (o_ instanceof CastOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((CastOperation)o_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getRes().getCaller(), f_, castOperation, _piano);
        }
        if (o_ instanceof InstanceOfOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((InstanceOfOperation)o_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getRes().getCaller(), f_, instanceOperation, _piano);
        }
        if (o_ instanceof StaticAccessOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticAccessOperation)o_).getPartOffsets(), new AllTypeSegmentFilter()),_c.getRes().getCaller(), f_, staticAccess,_piano);
        }
        if (o_ instanceof StaticCallAccessOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticCallAccessOperation)o_).getPartOffsets().getResult(), new AllTypeSegmentFilter()),_c.getRes().getCaller(), f_, staticAccess,_piano);
        }
        if (o_ instanceof AbstractInstancingOperation) {
            inst(_c, _piano);
        }
        if (o_ instanceof AbstractInvokingConstructor) {
            addAllIfMatch(fetch(((AbstractInvokingConstructor) o_).getPartOffsets()),_c.getRes().getCaller(), f_,instanceNewTypesEltFwd,_piano);
        }
        if (o_ instanceof DimensionArrayInstancing) {
            types(((DimensionArrayInstancing) o_).getPartOffsets(),_c.getRes().getCaller(), f_,instanceNewTypesEltArray,_piano);
        }
        if (o_ instanceof ElementArrayInstancing) {
            types(((ElementArrayInstancing) o_).getPartOffsets(),_c.getRes().getCaller(), f_,instanceNewTypesEltArray,_piano);
        }
        if (o_ instanceof AnnotationInstanceArobaseOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((AnnotationInstanceArobaseOperation)o_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getRes().getCaller(), f_,instanceArobase,_piano);
            if (o_.getParent() == null) {
                CustList<FileBlockIndex> added_ = new CustList<FileBlockIndex>();
                addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((AnnotationInstanceArobaseOperation)o_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getRes().getCaller(), f_,added_,_piano);
                feedArobase(_c.getRes(),(AnnotationInstanceArobaseOperation)o_,_c.getRes().getBlock(),added_);
            }
        }
        finderFilters(_c,_piano);
        typesFoundAnon(_c,_piano);
        if (o_ instanceof NamedArgumentOperation) {
            nameType(_c,(NamedArgumentOperation) o_,_piano);
        }
        infos(_c, _piano);
    }

    private void inst(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        types(((AbstractInstancingOperation) o_).getResolvedInstance(), _c.getRes().getCaller(), f_,instanceNewTypesElt, _piano);
        if (o_ instanceof StandardInstancingOperation) {
            addAllIfMatch(fetch(((StandardInstancingOperation) o_).getPartsInstInitInterfaces()), _c.getRes().getCaller(), f_,interfacesInit, _piano);
        }
    }

    private void infos(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (o_ instanceof DefaultValueOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((DefaultValueOperation) o_).getPartOffsets(),new AllTypeSegmentFilter()), _c.getRes().getCaller(), f_, typesInfosDef, _piano);
        }
        if (o_ instanceof StaticInfoOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticInfoOperation) o_).getPartOffsets(),new AllTypeSegmentFilter()), _c.getRes().getCaller(), f_, typesInfos, _piano);
        }
    }

    public void typesFoundAnon(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (o_ instanceof SwitchOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((SwitchOperation)o_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getRes().getCaller(), f_,returnType,_piano);
        }
    }
    private void feedArobase(ResultExpressionBlock _mem,AnnotationInstanceArobaseOperation _lda, AbsBk _block, CustList<FileBlockIndex> _refs) {
        for (FileBlockIndex r: _refs) {
            AnnotationInitFilterCall l_ = new AnnotationInitFilterCall();
            l_.setCalleeRef(r.getCallee());
            l_.setCallerRef(r.getCaller());
            l_.setInit(_lda.getResultClass().getSingleNameOrEmpty());
            l_.setBlock(_block);
            if (_mem.getAnnotationKind() == AnnotationKind.MEMBER) {
                annotationsMembers.add(l_);
            } else if (_mem.getAnnotationKind() == AnnotationKind.PARAMETER) {
                annotationsParameters.add(l_);
            } else if (_mem.getAnnotationKind() == AnnotationKind.SUPPL) {
                annotationsSuppl.add(l_);
            } else {
                annotationsDefValue.add(l_);
            }
        }
    }

    private void finderFilters(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (o_ instanceof ExplicitOperatorOperation) {
            exp(_c,(ExplicitOperatorOperation) o_,_piano);
        }
        if (o_ instanceof CastFctOperation) {
            addAllIfMatch(fetch(((CastFctOperation)o_).getPartOffsets()),_c.getRes().getCaller(), f_, typesFinders, _piano);
        }
        if (o_ instanceof AbsFctOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((AbsFctOperation) o_).getPartOffsets(), new AllTypeSegmentFilter()),_c.getRes().getCaller(),f_,typesFinders,_piano);
        }
        if (o_ instanceof SettableFieldOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((SettableFieldOperation) o_).getPartOffsets(), new AllTypeSegmentFilter()),_c.getRes().getCaller(),f_,typesFinders,_piano);
        }
        if (o_ instanceof LambdaOperation) {
            CustList<AnaNamedFieldContent> namedFields_ = ((LambdaOperation)o_).getNamedFields();
            int len_ = namedFields_.size();
            addAllIfMatch(fetch(((LambdaOperation)o_).getPartOffsetsBegin()),_c.getRes().getCaller(),f_,typesFindersRef,_piano);
            addAllIfMatch(fetch(((LambdaOperation)o_).getPartOffsetsPre()),_c.getRes().getCaller(),f_,typesFindersRef,_piano);
            addAllIfMatch(fetch(((LambdaOperation)o_).getPartOffsets()),_c.getRes().getCaller(),f_,typesFindersRef,_piano);
            addAllIfMatch(fetch(((LambdaOperation)o_).getPartsInstInitInterfaces()),_c.getRes().getCaller(),f_,interfacesInitRef,_piano);
            for (int i = 0; i < len_; i++) {
                addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((LambdaOperation)o_).getPartOffsetsRec().get(i).build(),new AllTypeSegmentFilter()),_c.getRes().getCaller(),f_,typesFindersRef,_piano);
            }
        }
        if (o_ instanceof FunctFilterOperation) {
            if (o_ instanceof IdFctOperation) {
                addAllIfMatch(fetch(((IdFctOperation) o_).getPartOffsetsSet()),_c.getRes().getCaller(),f_,typesFinders,_piano);
            }
            addAllIfMatch(fetch(((FunctFilterOperation) o_).getPartOffsets()),_c.getRes().getCaller(),f_,typesFinders,_piano);
        }
        if (o_ instanceof ForwardOperation) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForwardOperation)o_).getPartOffsets(),new AllTypeSegmentFilter()),_c.getRes().getCaller(),f_,typesFinders,_piano);
        }
    }

    private void exp(ResultExpressionBlockOperation _c, ExplicitOperatorOperation _foundOp, CustList<SrcFileLocation> _piano) {
        FileBlock f_ = _c.getRes().getBlock().getFile();
        addAllIfMatch(fetch(_foundOp.getTypesImpl()),_c.getRes().getCaller(),f_,typesFinders,_piano);
        addAllIfMatch(fetch(_foundOp.getTypesTest()),_c.getRes().getCaller(),f_,typesFinders,_piano);
        addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(_foundOp.getPartOffsets(), new AllTypeSegmentFilter()),_c.getRes().getCaller(),f_,typesFinders,_piano);
    }

    public void callingsCustDirect(MemberCallingsBlock _c, CustList<SrcFileLocation> _piano) {
        if (_c instanceof NamedFunctionBlock) {
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((NamedFunctionBlock)_c).getPartOffsetsReturn(), new AllTypeSegmentFilter()),new SrcFileLocationMethod(_c.getParent(),_c),_c.getFile(),returnType,_piano);
            addAllIfMatch(fetchAna(((NamedFunctionBlock)_c).getPartOffsetsParams()),new SrcFileLocationMethod(_c.getParent(),_c),_c.getFile(),paramType,_piano);
        }
        if (_c instanceof NamedCalledFunctionBlock) {
            for (PartOffsetsClassMethodId p:((NamedCalledFunctionBlock) _c).getAllInternTypesParts()) {
                fctPub(_c,new SrcFileLocationMethod(_c.getParent(),_c),p.getFct(),p.getBegin(),_piano,internEltsFct);
            }
            addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((NamedCalledFunctionBlock)_c).getPartOffsetsReturnSetter(), new AllTypeSegmentFilter()),new SrcFileLocationMethod(_c.getParent(),_c),_c.getFile(),internEltsFct,_piano);
            for (PartOffsetsClassMethodId p:((NamedCalledFunctionBlock)_c).getAllInternTypesParts()) {
                addAllIfMatch(fetch(p.getTypes()),new SrcFileLocationMethod(_c.getParent(),_c),_c.getFile(),internEltsFct,_piano);
                addAllIfMatch(fetch(p.getSuperTypes()),new SrcFileLocationMethod(_c.getParent(),_c),_c.getFile(),internEltsFct,_piano);
            }
        }
    }
    public void callingsCustDirect(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (o_ instanceof FinalVariableOperation && ((FinalVariableOperation) o_).isOk()) {
            AnaVariableContent v_ = ((FinalVariableOperation) o_).getVariableContent();
            addIfMatch(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),f_,((FinalVariableOperation)o_).getRef()),_c.getRes().getCaller(), f_,begin(_c),variablesParamsUse,_piano);
        }
        if (o_ instanceof VariableOperationUse) {
            AnaVariableContent v_ = ((VariableOperationUse) o_).getVariableContent();
            addIfMatch(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),f_,((VariableOperationUse)o_).getRef()),_c.getRes().getCaller(), f_,begin(_c),variablesParamsUse,_piano);
        }
        if (o_ instanceof NamedArgumentOperation) {
            name(_c,(NamedArgumentOperation)o_,_piano);
        }
        if (o_ instanceof SettableFieldOperation) {
            feelIt(_c, (SettableFieldOperation) o_,_piano);
        }
        if (o_ instanceof AbsFctOperation) {
            int delta_ = ((AbsFctOperation) o_).getDelta();
            SrcFileLocationMethod callee_ = fctPub(_c, ((AbsFctOperation) o_).getCallFctContent().getFunction(), delta_, _piano, callNamedUse);
            poly(_c,callee_,!((AbsFctOperation) o_).isStaticChoiceMethod(),delta_,callNamedUsePoly);
            callStd(_c,((AbsFctOperation)o_).getCallFctContent().getStandardMethod(),((AbsFctOperation)o_).getCallFctContent().getStandardType(), delta_, _piano, callNamedUse);
        }
        if (o_ instanceof AbstractInvokingConstructor) {
            fctPub(_c, ((AbstractInvokingConstructor) o_).getConstructor(), 0, _piano, callNamedUse);
            instanceNewTypes(_c, 0, _piano, ResultExpressionOperationNode.root(((AbstractInvokingConstructor) o_).getConstructor()), instanceNewTypesFwd);
        }
        if (o_ instanceof AbstractInstancingOperation) {
            fctPub(_c, ((AbstractInstancingOperation) o_).getConstructor(), 0, _piano, callNamedUse);
            callStd(_c, ((AbstractInstancingOperation) o_).getInstancingCommonContent().getConstructor(),((AbstractInstancingOperation) o_).getInstancingCommonContent().getStd(), 0, _piano, callNamedUse);
            instanceNewTypes(_c, 0, _piano, ResultExpressionOperationNode.root(((AbstractInstancingOperation) o_).getConstructor()), instanceNewTypes);
        }
        if (o_ instanceof ArrOperation) {
            SrcFileLocationMethod callee_ = fctPub(_c, ((ArrOperation) o_).getFunctionGet(), 0, _piano, callNamedUse);
            poly(_c,callee_,!((ArrOperation) o_).isStaticChoiceMethod(),0,callNamedUsePoly);
            SrcFileLocationMethod calleeSet_ = fctPub(_c, ((ArrOperation) o_).getFunctionSet(), 0, _piano, callNamedUse);
            poly(_c,calleeSet_,!((ArrOperation) o_).isStaticChoiceMethod(),0,callNamedUsePoly);
        }
//        if (_c instanceof CallDynMethodOperation) {
//            callStd(((CallDynMethodOperation)_c).getStdMethod(),((CallDynMethodOperation)_c).getStdType(), directCallNamedStd, _piano);
//            String r_ = ((CallDynMethodOperation) _c).getRefer();
//            if (!r_.isEmpty()) {
//                SrcFileLocationCall ref_ = new SrcFileLocationCall(r_);
//                addIfMatch(ref_,directCallNamedRef,_piano);
//                directCallNamedRefAll.add(ref_);
//            }
//        }
        if (o_ instanceof LambdaOperation) {
            lambda(_c, (LambdaOperation) o_, _piano);
        }
        if (o_ instanceof AnonymousLambdaOperation) {
            CustList<FileBlockIndex> added_ = new CustList<FileBlockIndex>();
            addIfMatch(new SrcFileLocationMethod(null,((AnonymousLambdaOperation)o_).getBlock()),_c.getRes().getCaller(), f_,begin(_c),added_,_piano);
            callAnonRefUse.addAllElts(added_);
            feedLambda(o_,added_);
        }
        if (o_ instanceof CastFctOperation) {
            int off_ = ((CastFctOperation) o_).getOperators().firstKey();
            fctPub(_c, ((CastFctOperation) o_).getFunction(), off_, _piano, callNamedUse);
        }
        if (o_ instanceof ExplicitOperatorOperation) {
            fctPub(_c, ((ExplicitOperatorOperation) o_).getCallFctContent().getFunction(), 0, _piano, callNamedUse);
            fctPub(_c, ((ExplicitOperatorOperation) o_).getConv().getFunction(), ((ExplicitOperatorOperation)o_).getAffOffset(), _piano, callNamedUseImpl);
            fctPub(_c, ((ExplicitOperatorOperation) o_).getFunctionTest(), ((ExplicitOperatorOperation)o_).getAffOffset(), _piano, callNamedUseImpl);
        }
        if (o_ instanceof AssocationOperation) {
            annots(_c,_piano,(AssocationOperation) o_);
        }
        if (o_ instanceof SwitchOperation) {
            addIfMatch(new SrcFileLocationMethod(null,((SwitchOperation)o_).getSwitchMethod()),_c.getRes().getCaller(), f_,begin(_c)+((SwitchOperation)o_).getOffsetFct(),callNamedUse,_piano);
        }
    }
    private void annots(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano, AssocationOperation _a) {
        AnaTypeFct ct_ = _a.getFunction();
        NamedFunctionBlock f_ = LambdaOperation.fct(ct_);
        if (f_ == null) {
            return;
        }
        SrcFileLocationMethod callee_ = new SrcFileLocationMethod(ct_.getType(), f_);
        int off_ = _a.getOffsetFct();
        annot(_c, callee_, off_,callNamedFieldUse,_piano);
    }

    private static void instanceNewTypes(ResultExpressionBlockOperation _c, int _offset, CustList<SrcFileLocation> _piano, RootBlock _format, CustList<FileBlockIndex> _inst) {
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (_format != null) {
            addIfMatch(new SrcFileLocationType(begin(_c) + _offset,_format), _c.getRes().getCaller(), f_, begin(_c) + _offset, _inst, _piano);
        }
    }

    public void symbols(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        if (o_ instanceof SymbolOperation) {
            fctPub(_c, ((SymbolOperation) o_).getFct().getFunction(), ((SymbolOperation)o_).getOperatorContent().getOpOffset(), _piano, callNamedUse);
        }
        if (o_ instanceof AbstractComTernaryOperation) {
            fctPub(_c, ((AbstractComTernaryOperation) o_).getImplFct(), 0, _piano, callNamedUseImpl);
            fctPub(_c, ((AbstractComTernaryOperation) o_).getTestFct(), 0, _piano, callNamedUseImpl);
        }
        if (o_ instanceof SemiAffectationOperation) {
            int off_ = ((SemiAffectationOperation) o_).getOpOffset();
            fctPub(_c, ((SemiAffectationOperation) o_).getFct().getFunction(), off_, _piano, callNamedUse);
            fctPub(_c, ((SemiAffectationOperation) o_).getConvTo().getFunction(), off_, _piano, callNamedUseImpl);
        }
        if (o_ instanceof CompoundAffectationOperation) {
            int off_ = ((CompoundAffectationOperation) o_).getOpOffset();
            fctPub(_c, ((CompoundAffectationOperation) o_).getFct().getFunction(), off_, _piano, callNamedUse);
            fctPub(_c, ((CompoundAffectationOperation) o_).getConv().getFunction(), off_, _piano, callNamedUseImpl);
            fctPub(_c, ((CompoundAffectationOperation) o_).getFunctionTest(), off_, _piano, callNamedUseImpl);
        }
        if (o_ instanceof QuickOperation) {
            int off_ = ((QuickOperation) o_).getOpOff();
            fctPub(_c, ((QuickOperation) o_).getFct().getFunction(), off_, _piano, callNamedUse);
            fctPub(_c, ((QuickOperation) o_).getConv().getFunction(), off_, _piano, callNamedUseImpl);
            fctPub(_c, ((QuickOperation) o_).getFunctionTest(), off_, _piano, callNamedUseImpl);
        }
    }

    private SrcFileLocationMethod fctPub(ResultExpressionBlockOperation _c, AnaTypeFct _ct, int _offset, CustList<SrcFileLocation> _piano, CustList<FileBlockIndex> _out) {
        return fctPub(_c.getRes(),_ct,begin(_c) + _offset,_piano,_out);
    }

    private SrcFileLocationMethod fctPub(ResultExpressionBlock _c, AnaTypeFct _ct, int _offset, CustList<SrcFileLocation> _piano, CustList<FileBlockIndex> _out) {
        return fctPub(_c.getBlock(),_c.getCaller(),_ct,_offset,_piano,_out);
    }

    private SrcFileLocationMethod fctPub(AbsBk _bl, SrcFileLocation _caller, AnaTypeFct _ct, int _offset, CustList<SrcFileLocation> _piano, CustList<FileBlockIndex> _out) {
        FileBlock file_ = _bl.getFile();
        NamedFunctionBlock f_ = LambdaOperation.fct(_ct);
        if (f_ != null) {
            SrcFileLocationMethod callee_ = new SrcFileLocationMethod(_ct.getType(), f_);
            addIfMatch(callee_, _caller,file_, _offset, _out,_piano);
            return callee_;
        }
        return null;
    }

    private void poly(ResultExpressionBlockOperation _c, SrcFileLocationMethod _callee, boolean _poly, int _offset, CustList<FileBlockIndex> _outPoly) {
        FileBlock file_ = _c.getRes().getBlock().getFile();
        if (_callee != null) {
            int index_ = begin(_c) + _offset;
            SrcFileLocation caller_ = _c.getRes().getCaller();
            if (_poly) {
                for (FileBlockIndex f: callNamedOverridden) {
                    if (f.getCaller().match(_callee)) {
                        _outPoly.add(new FileBlockIndex(file_, index_,f.getCallee(), caller_));
                    }
                }
            }
        }
    }

    private void annot(ResultExpressionBlockOperation _c, SrcFileLocationMethod _callee, int _offset, CustList<FileBlockIndex> _outPoly, CustList<SrcFileLocation> _piano) {
        FileBlock file_ = _c.getRes().getBlock().getFile();
        int index_ = begin(_c) + _offset;
        SrcFileLocation caller_ = _c.getRes().getCaller();
        addIfMatch(_callee,caller_,file_, index_,_outPoly,_piano);
    }
    private static void callStd(ResultExpressionBlockOperation _c, StandardNamedFunction _std, StandardType _type, int _offset, CustList<SrcFileLocation> _piano, CustList<FileBlockIndex> _out) {
        FileBlock file_ = _c.getRes().getBlock().getFile();
        if (_std != null) {
            addIfMatch(new SrcFileLocationStdMethod(_type, _std),_c.getRes().getCaller(),file_,begin(_c)+_offset, _out,_piano);
        }
    }

    private static int begin(ResultExpressionBlockOperation _b) {
        return ResultExpressionOperationNode.begin(_b.getRes().getRes(), _b.getBlock());
    }
    private void name(ResultExpressionBlockOperation _c, NamedArgumentOperation _foundOp, CustList<SrcFileLocation> _piano) {
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (_foundOp.isRecordBlock()) {
            int i_ = _foundOp.getRef();
            RootBlock r_ = _foundOp.getField();
            ClassField cf_ = _foundOp.getIdField();
            notEmptyField(_c, _piano, _foundOp.getOffsetTr(), fieldsUseInit, SrcFileLocationField.field(cf_, r_, i_, null));
            return;
        }
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        ResultExpressionOperationNode.feedFiltersNamedList(_foundOp,ls_);
        for (SrcFileLocation s: ls_) {
            addIfMatch(s,_c.getRes().getCaller(), f_,begin(_c),variablesParamsUse,_piano);
        }
    }
    private void nameType(ResultExpressionBlockOperation _c, NamedArgumentOperation _foundOp, CustList<SrcFileLocation> _piano) {
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (_foundOp.isRecordBlock()) {
            addAllIfMatch(fetch(_foundOp.getPartOffsets()),_c.getRes().getCaller(), f_,typesFinders,_piano);
        }
    }

//    private void def(AbsBk _bl, CustList<SrcFileLocation> _piano) {
////        CustList<SrcFileLocation> t_ = otherTypes(_bl, _caret);
////        if (!t_.isEmpty()) {
////            return t_;
////        }
//        if (_bl instanceof NamedFunctionBlock) {
//            defName((NamedFunctionBlock) _bl, _piano);
//        }
////        if (_bl instanceof InternOverrideBlock) {
////            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
////            for (PartOffsetsClassMethodIdList l:((InternOverrideBlock)_bl).getAllPartsTypes()) {
////                for (PartOffsetsClassMethodId p:l.getOverrides()) {
////                    if (inRange(p.getBegin(), _caret,p.getBegin()+p.getLength())) {
////                        fctPub(p.getFct(),ls_);
////                    }
////                }
////            }
////            return ls_;
////        }
//    }

//    private void defName(NamedFunctionBlock _bl, CustList<SrcFileLocation> _piano) {
//        Ints offs_ = _bl.getParametersNamesOffset();
//        StringList names_ = _bl.getParametersNames();
//        int s_ = names_.size();
//        for (int i = 0; i < s_; i++) {
//            addIfMatch(new SrcFileLocationVariable(-1,names_.get(i), _bl.getFile(),offs_.get(i)),new SrcFileLocationMethod(_bl.getParent(),_bl),_bl.getFile(),offs_.get(i),variablesParamsUse,_piano);
//        }
////        if (_bl instanceof NamedCalledFunctionBlock) {
////            for (PartOffsetsClassMethodId p:((NamedCalledFunctionBlock) _bl).getAllInternTypesParts()) {
////                if (inRange(p.getBegin(), _caret,p.getBegin()+p.getLength())) {
////                    fctPub(p.getFct(),p_);
////                }
////            }
////        }
////        if (inRange(_bl.getNameOffset(),_caret,_bl.getNameOffset()+_bl.getRealLength())) {
////            p_.add(new SrcFileLocationMethod(_bl.getParent(),_bl));
////        }
//    }

//    private static CustList<SrcFileLocation> tryDecl(AbsBk _bl) {
//        CustList<SrcFileLocation> vars_ = new CustList<SrcFileLocation>();
//        if (_bl instanceof ForEachLoop) {
//            vars_.add(new SrcFileLocationVariable(-1,((ForEachLoop)_bl).getVariableName(),_bl.getFile(),((ForEachLoop)_bl).getVariableNameOffset()));
//            return vars_;
//        }
//        if (_bl instanceof ForIterativeLoop) {
//            vars_.add(new SrcFileLocationVariable(-1,((ForIterativeLoop)_bl).getVariableName(),_bl.getFile(),((ForIterativeLoop)_bl).getVariableNameOffset()));
//            return vars_;
//        }
//        if (_bl instanceof ForEachTable) {
//            vars_.add(new SrcFileLocationVariable(-1,((ForEachTable)_bl).getVariableNameFirst(),_bl.getFile(),((ForEachTable)_bl).getVariableNameOffsetFirst()));
//            vars_.add(new SrcFileLocationVariable(-1,((ForEachTable)_bl).getVariableNameSecond(),_bl.getFile(),((ForEachTable)_bl).getVariableNameOffsetSecond()));
//            return vars_;
//        }
//        if (_bl instanceof WithFilterContent){
//            vars_.add(new SrcFileLocationVariable(-1,((WithFilterContent)_bl).getFilterContent().getVariableName(),_bl.getFile(),((WithFilterContent)_bl).getFilterContent().getVariableOffset()));
//            return vars_;
//        }
//        if (_bl instanceof DefaultCondition) {
//            vars_.add(new SrcFileLocationVariable(-1,((DefaultCondition)_bl).getVariableName(),_bl.getFile(),((DefaultCondition)_bl).getVariableOffset()));
//            return vars_;
//        }
//        return new CustList<SrcFileLocation>();
//    }

//    private static boolean okVar(OperationNode _op) {
//        return _op instanceof VariableOperation && ((VariableOperation) _op).isOk();
//    }
    private void lambda(ResultExpressionBlockOperation _c, LambdaOperation _lda, CustList<SrcFileLocation> _piano) {
        FileBlock f_ = _c.getRes().getBlock().getFile();
        RootBlock fieldType_ = _lda.getFieldType();
        CustList<FileBlockIndex> added_ = new CustList<FileBlockIndex>();
        instanceNewTypes(_c, _lda.getMemberOffset(), _piano, ResultExpressionOperationNode.root(_lda),added_);
        instanceNewTypesRef.addAllElts(added_);
        feedLambda(_lda,added_);
        added_.clear();
        SrcFileLocationMethod callee_ = fctPub(_c, _lda.getFunction(), _lda.getMemberOffset(), _piano, added_);
        callStd(_c,_lda.getStandardMethod(),_lda.getStandardType(), _lda.getMemberOffset(), _piano, added_);
        callStd(_c,_lda.getStandardConstructor(),_lda.getStandardType(), _lda.getMemberOffset(), _piano, added_);
        callNamedRefUse.addAllElts(added_);
        feedLambda(_lda,added_);
        added_.clear();
        poly(_c,callee_,_lda.getLambdaMethodContent().isPolymorph(),_lda.getMemberOffset(),added_);
        callNamedRefUsePoly.addAllElts(added_);
        feedLambda(_lda,added_);
        added_.clear();
        CustList<AnaNamedFieldContent> namedFields_ = _lda.getNamedFields();
        int len_ = namedFields_.size();
        int off_ = _lda.getOffset();
        for (int i = 0; i < len_; i++) {
            int ref_ = _lda.getRefs().get(i);
            if (ref_ < 0) {
                continue;
            }
            AnaNamedFieldContent naFi_ = namedFields_.get(i);
            String name_ = naFi_.getName();
            RootBlock r_ = naFi_.getDeclaring();
            int offset_ = _lda.getOffsets().get(i);
            addIfMatch(SrcFileLocationField.field(new ClassField(naFi_.getIdClass(),name_),r_, ref_,null),_c.getRes().getCaller(), f_,begin(_c)+offset_+off_,added_,_piano);
            fieldsRefUse.addAllElts(added_);
        }
        ClassField fieldId_ = _lda.getFieldId();
        if (fieldId_ != null) {
            addIfMatch(SrcFileLocationField.field(fieldId_,fieldType_, _lda.getValueOffset(),_lda.getCstFieldInfo()),_c.getRes().getCaller(), f_,_lda.getMemberOffset()+begin(_c),added_,_piano);
            fieldsRefUse.addAllElts(added_);
        }
        feedLambda(_lda,added_);
    }
    private void feedLambda(OperationNode _lda, CustList<FileBlockIndex> _refs) {
        for (FileBlockIndex r: _refs) {
            LambdaDynFilterCall l_ = new LambdaDynFilterCall();
            l_.setCalleeRef(r.getCallee());
            l_.setLambda(_lda.getResultClass().getSingleNameOrEmpty());
            lambda.add(l_);
        }
    }

    private void feelIt(ResultExpressionBlockOperation _c, SettableFieldOperation _foundOp, CustList<SrcFileLocation> _piano) {
//        if (_foundOp instanceof SettableFieldOperation) {
//            ls_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((SettableFieldOperation) _foundOp).getPartOffsets(), _caret));
//        }
//        if (!ls_.isEmpty()) {
//            return ls_;
//        }
        int i_ = _foundOp.getValueOffset();
        RootBlock r_ = _foundOp.getFieldType();
        ClassField cf_ = _foundOp.getFieldIdReadOnly();
        int delta_ = _foundOp.getOffset();
        delta_ += _foundOp.getDelta();
        notEmptyField(_c, _piano, delta_, fieldsUse, SrcFileLocationField.field(cf_, r_, i_, _foundOp.getSettableFieldContent().getCstFieldInfo()));
    }

    private void notEmptyField(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano, int _offset, CustList<FileBlockIndex> _usages, SrcFileLocationField _fi) {
        FileBlock f_ = _c.getRes().getBlock().getFile();
        addIfMatch(_fi, _c.getRes().getCaller(), f_,begin(_c)+_offset, _usages, _piano);
    }

    //    private static void ctStd(StandardConstructor _std, StandardType _type, CustList<SrcFileLocation> _ls, CustList<SrcFileLocation> _piano) {
//        if (_std != null) {
//            addIfMatch(new SrcFileLocationStdMethod(_type, _std), _ls, _piano);
//        }
//    }
//    private static void callStd(StandardMethod _std, StandardType _type, CustList<SrcFileLocation> _ls, CustList<SrcFileLocation> _piano) {
//        if (_std != null) {
//            addIfMatch(new SrcFileLocationStdMethod(_type, _std),_ls,_piano);
//        }
//    }
//    private static void fctPub(AnaTypeFct _ct, CustList<SrcFileLocation> _ls, CustList<SrcFileLocation> _piano) {
//        NamedFunctionBlock f_ = fct(_ct);
//        if (f_ != null) {
//            addIfMatch(new SrcFileLocationMethod(_ct.getType(),f_),_ls,_piano);
//        }
//    }
    private static void types(ResolvedInstance _r, SrcFileLocation _a, FileBlock _currFile, CustList<FileBlockIndex> _ls, CustList<SrcFileLocation> _piano) {
        addAllIfMatch(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(_r.getResult(), new AllTypeSegmentFilter()),_a,_currFile,_ls,_piano);
        addAllIfMatch(fetchAna(_r.getParts()),_a,_currFile,_ls,_piano);
    }
    private static CustList<AbsSrcFileLocationType> fetch(CustList<AnaResultPartTypeDtoInt> _list) {
        CustList<AbsSrcFileLocationType> s_ = new CustList<AbsSrcFileLocationType>();
        for (AnaResultPartTypeDtoInt a: _list) {
            s_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(a, new AllTypeSegmentFilter()));
        }
        return s_;
    }

    private static CustList<AbsSrcFileLocationType> fetchAna(CustList<AnaResultPartType> _list) {
        CustList<AbsSrcFileLocationType> s_ = new CustList<AbsSrcFileLocationType>();
        for (AnaResultPartType a: _list) {
            s_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(a, new AllTypeSegmentFilter()));
        }
        return s_;
    }
    private static void addAllIfMatch(CustList<AbsSrcFileLocationType> _c, SrcFileLocation _a, FileBlock _currFile, CustList<FileBlockIndex> _ls, CustList<SrcFileLocation> _piano) {
        for (AbsSrcFileLocationType f: _c) {
            addIfMatch(f,_a,_currFile,f.getOffset(),_ls,_piano);
        }
    }

    private static void addIfMatch(SrcFileLocation _c, SrcFileLocation _a,FileBlock _currFile, int _index, CustList<FileBlockIndex> _ls, CustList<SrcFileLocation> _piano) {
        if (_c == null) {
            return;
        }
        for (SrcFileLocation r: _piano) {
//            if (((r instanceof SrcFileLocationStdMethod && _c instanceof SrcFileLocationStdMethod && ((SrcFileLocationStdMethod) r).getStd() == ((SrcFileLocationStdMethod) _c).getStd() || r instanceof SrcFileLocationStdType && _c instanceof SrcFileLocationStdType && StringUtil.quickEq(((SrcFileLocationStdType) r).getType(), ((SrcFileLocationStdType) _c).getType())) || r instanceof SrcFileLocationCall && _c instanceof SrcFileLocationCall && StringUtil.quickEq(((SrcFileLocationCall) r).getTypeRef(), ((SrcFileLocationCall) _c).getTypeRef())) || r instanceof SrcFileLocationField && _c instanceof SrcFileLocationField && ((SrcFileLocationField) r).getCf().eq(((SrcFileLocationField) _c).getCf()) || r.getFile() == _c.getFile() && r.getIndex() == _c.getIndex()) {
//                _ls.add(_c);
//            }
            if (_c.match(r)) {
                _ls.add(new FileBlockIndex(_currFile,_index,_c,_a));
            }
        }
    }
    public CustList<FileBlockIndex> getLabels() {
        return labels;
    }

    public CustList<FileBlockIndex> getVariablesParamsUse() {
        return variablesParamsUse;
    }

    public CustList<FileBlockIndex> getFieldsRefUse() {
        return fieldsRefUse;
    }

    public CustList<FileBlockIndex> getFieldsUse() {
        return fieldsUse;
    }

    public CustList<FileBlockIndex> getFieldsUseInit() {
        return fieldsUseInit;
    }

    public CustList<FileBlockIndex> getCallNamedUse() {
        return callNamedUse;
    }

    public CustList<FileBlockIndex> getCallNamedRefUse() {
        return callNamedRefUse;
    }

    public CustList<FileBlockIndex> getCallNamedOverridden() {
        return callNamedOverridden;
    }

    public CustList<FileBlockIndex> getCallNamedOverriding() {
        return callNamedOverriding;
    }

    public CustList<FileBlockIndex> getCallNamedUsePoly() {
        return callNamedUsePoly;
    }

    public CustList<FileBlockIndex> getCallNamedRefUsePoly() {
        return callNamedRefUsePoly;
    }

    public CustList<FileBlockIndex> getInstanceNewTypes() {
        return instanceNewTypes;
    }

    public CustList<FileBlockIndex> getInstanceNewTypesRef() {
        return instanceNewTypesRef;
    }

    public CustList<FileBlockIndex> getInstanceNewTypesFwd() {
        return instanceNewTypesFwd;
    }

    public CustList<FileBlockIndex> getCallNamedFieldUse() {
        return callNamedFieldUse;
    }

    public CustList<FileBlockIndex> getCallNamedUseImpl() {
        return callNamedUseImpl;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitMembers() {
        return annotCandidatesCallsInitMembers;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitParameters() {
        return annotCandidatesCallsInitParameters;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitSuppl() {
        return annotCandidatesCallsInitSuppl;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitDefValue() {
        return annotCandidatesCallsInitDefValue;
    }

    public CustList<FileBlockIndex> getInstanceArobase() {
        return instanceArobase;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitArobaseMembers() {
        return annotCandidatesCallsInitArobaseMembers;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitArobaseParameters() {
        return annotCandidatesCallsInitArobaseParameters;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitArobaseSuppl() {
        return annotCandidatesCallsInitArobaseSuppl;
    }

    public CustList<FileBlockIndex> getAnnotCandidatesCallsInitArobaseDefValue() {
        return annotCandidatesCallsInitArobaseDefValue;
    }

    public CustList<FileBlockIndex> getDynCallPotential() {
        return dynCallPotential;
    }

    public CustList<FileBlockIndex> getStaticAccess() {
        return staticAccess;
    }

    public CustList<FileBlockIndex> getInstanceNewTypesElt() {
        return instanceNewTypesElt;
    }

    public CustList<FileBlockIndex> getCallAnonRefUse() {
        return callAnonRefUse;
    }

    public CustList<FileBlockIndex> getInstanceNewTypesEltFwd() {
        return instanceNewTypesEltFwd;
    }

    public CustList<FileBlockIndex> getInstanceNewTypesEltArray() {
        return instanceNewTypesEltArray;
    }

    public CustList<FileBlockIndex> getInterfacesInit() {
        return interfacesInit;
    }

    public CustList<FileBlockIndex> getInterfacesInitRef() {
        return interfacesInitRef;
    }

    public CustList<FileBlockIndex> getTypesFinders() {
        return typesFinders;
    }

    public CustList<FileBlockIndex> getTypesFindersRef() {
        return typesFindersRef;
    }

    public CustList<FileBlockIndex> getReturnType() {
        return returnType;
    }

    public CustList<FileBlockIndex> getCastOperation() {
        return castOperation;
    }

    public CustList<FileBlockIndex> getInstanceOperation() {
        return instanceOperation;
    }

    public CustList<FileBlockIndex> getTypesInfos() {
        return typesInfos;
    }

    public CustList<FileBlockIndex> getTypesInfosDef() {
        return typesInfosDef;
    }

    public CustList<FileBlockIndex> getInternElts() {
        return internElts;
    }

    public CustList<FileBlockIndex> getInternEltsFct() {
        return internEltsFct;
    }

    public CustList<FileBlockIndex> getParamType() {
        return paramType;
    }

    public CustList<FileBlockIndex> getFieldDeclaring() {
        return fieldDeclaring;
    }

    public CustList<FileBlockIndex> getVariableDeclaring() {
        return variableDeclaring;
    }

    public CustList<FileBlockIndex> getInterfacesInstance() {
        return interfacesInstance;
    }

    public CustList<FileBlockIndex> getInterfacesStatic() {
        return interfacesStatic;
    }

    public CustList<FileBlockIndex> getInherits() {
        return inherits;
    }

    public CustList<FileBlockIndex> getConstraints() {
        return constraints;
    }
//    private static NamedFunctionBlock fct(AnaTypeFct _f) {
//        if (_f == null) {
//            return null;
//        }
//        return _f.getFunction();
//    }
}
