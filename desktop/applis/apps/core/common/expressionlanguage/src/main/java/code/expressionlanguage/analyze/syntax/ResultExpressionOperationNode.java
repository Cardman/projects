package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.expressionlanguage.analyze.types.LocationsPartTypeUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.Ints;

public final class ResultExpressionOperationNode {
    private ResultExpression resultExpression;
    private AbsBk block;
    private OperationNode found;
    public static String vexerChamps(AnalyzedPageEl _page, String _fileName, int _caret) {
        ResultExpressionOperationNode res_ = container(_page, _fileName, _caret);
        if (res_.getFound() instanceof SettableAbstractFieldOperation) {
            return ((SettableAbstractFieldOperation)res_.getFound()).getFieldIdReadOnly().getFieldName();
        }
        return "";
    }
    public static CustList<SrcFileLocation> locations(AnalyzedPageEl _page, String _fileName, int _caret) {
        ResultExpressionOperationNode res_ = container(_page, _fileName, _caret);
        CustList<SrcFileLocation> machines_ = coeur(_fileName, _caret, res_);
        if (!machines_.isEmpty()) {
            return machines_;
        }
        AbsBk bl_ = res_.getBlock();
        if (bl_ instanceof InnerTypeOrElement) {
            return fetch(_caret,((InnerTypeOrElement)bl_).getElementContent().getPartOffsets());
        }
        if (bl_ instanceof BreakableBlock&&inRange(((BreakableBlock)bl_).getRealLabelInfo().getOffset(),_caret,((BreakableBlock)bl_).getRealLabelInfo().getOffset()+((BreakableBlock)bl_).getRealLabelInfo().getInfo().length())) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationLabel(((BreakableBlock)bl_).getRealLabelInfo().getInfo(),_fileName,((BreakableBlock)bl_).getRealLabelInfo().getOffset()));
            return ls_;
        }
        OffsetStringInfo o_ = tryDecl(bl_, _caret);
        if (o_ != null) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationVariable(-1,o_.getInfo(),_fileName,o_.getOffset()));
            return ls_;
        }
        if (bl_ instanceof DeclareVariable) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((DeclareVariable)bl_).getPartOffsets(),_caret);
        }
        if (bl_ instanceof ForMutableIterativeLoop) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForMutableIterativeLoop)bl_).getPartOffsets(),_caret);
        }
        if (bl_ instanceof ForEachLoop) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachLoop)bl_).getPartOffsets(),_caret);
        }
        if (bl_ instanceof ForEachTable) {
            CustList<SrcFileLocation> ls_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachTable) bl_).getPartOffsetsFirst(), _caret);
            ls_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachTable) bl_).getPartOffsetsSecond(), _caret));
            return ls_;
        }
        if (bl_ instanceof FieldBlock) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((FieldBlock)bl_).getTypePartOffsets(),_caret);
        }
        if (bl_ instanceof WithFilterContent) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((WithFilterContent)bl_).getFilterContent().getPartOffsets(),_caret);
        }
        if (bl_ instanceof BreakBlock&&((BreakBlock)bl_).getLabelOffsetRef()>-1) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationLabel(((BreakBlock)bl_).getLabel(),_fileName,((BreakBlock)bl_).getLabelOffsetRef()));
            return ls_;
        }
        if (bl_ instanceof ContinueBlock&&((ContinueBlock)bl_).getLabelOffsetRef()>-1) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationLabel(((ContinueBlock)bl_).getLabel(),_fileName,((ContinueBlock)bl_).getLabelOffsetRef()));
            return ls_;
        }
        return def(_caret, bl_);
    }

    private static CustList<SrcFileLocation> def(int _caret, AbsBk _bl) {
        CustList<SrcFileLocation> t_ = otherTypes(_bl, _caret);
        if (!t_.isEmpty()) {
            return t_;
        }
        return new CustList<SrcFileLocation>();
    }

    private static CustList<SrcFileLocation> otherTypes(AbsBk _bl, int _caret) {
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        if (_bl instanceof RootBlock) {
            if (inRange(((RootBlock)_bl).getIdRowCol(),_caret,((RootBlock)_bl).getIdRowCol()+((RootBlock)_bl).getNameLength())) {
                ls_.add(new SrcFileLocationType((RootBlock) _bl));
            }
            if (!(_bl instanceof AnonymousTypeBlock)) {
                ls_.addAllElts(fetch(_caret,((RootBlock)_bl).getPartsStaticInitInterfacesOffset()));
                ls_.addAllElts(fetch(_caret,((RootBlock)_bl).getPartsInstInitInterfacesOffset()));
                for (TypeVar t: ((RootBlock)_bl).getParamTypes()) {
                    if (inRange(t.getOffset(),_caret,t.getOffset()+t.getLength())) {
                        ls_.add(new SrcFileLocationTypeVar(t.getName(),t.getOffset(),_bl.getFile()));
                    }
                    ls_.addAllElts(fetchAna(_caret,t.getResults()));
                }
            }
            ls_.addAllElts(fetchAna(_caret,((RootBlock)_bl).getResults()));
        }
        return ls_;
    }
    private static OffsetStringInfo tryDecl(AbsBk _bl, int _caret) {
        if (_bl instanceof ForEachLoop&&inRange(((ForEachLoop)_bl).getVariableNameOffset(),_caret,((ForEachLoop)_bl).getVariableNameOffset()+((ForEachLoop)_bl).getVariableName().length())) {
            return new OffsetStringInfo(((ForEachLoop)_bl).getVariableNameOffset(), ((ForEachLoop)_bl).getVariableName());
        }
        if (_bl instanceof ForEachTable&&inRange(((ForEachTable)_bl).getVariableNameOffsetFirst(),_caret,((ForEachTable)_bl).getVariableNameOffsetFirst()+((ForEachTable)_bl).getVariableNameFirst().length())) {
            return new OffsetStringInfo(((ForEachTable)_bl).getVariableNameOffsetFirst(), ((ForEachTable)_bl).getVariableNameFirst());
        }
        if (_bl instanceof ForEachTable&&inRange(((ForEachTable)_bl).getVariableNameOffsetSecond(),_caret,((ForEachTable)_bl).getVariableNameOffsetSecond()+((ForEachTable)_bl).getVariableNameSecond().length())) {
            return new OffsetStringInfo(((ForEachTable)_bl).getVariableNameOffsetSecond(), ((ForEachTable)_bl).getVariableNameSecond());
        }
        if (_bl instanceof WithFilterContent&&inRange(((WithFilterContent)_bl).getFilterContent().getVariableOffset(),_caret,((WithFilterContent)_bl).getFilterContent().getVariableOffset()+((WithFilterContent)_bl).getFilterContent().getVariableName().length())){
            return new OffsetStringInfo(((WithFilterContent)_bl).getFilterContent().getVariableOffset(), ((WithFilterContent)_bl).getFilterContent().getVariableName());
        }
        if (_bl instanceof DefaultCondition&&inRange(((DefaultCondition)_bl).getVariableOffset(),_caret,((DefaultCondition)_bl).getVariableOffset()+((DefaultCondition)_bl).getVariableName().length())) {
            return new OffsetStringInfo(((DefaultCondition)_bl).getVariableOffset(),((DefaultCondition)_bl).getVariableName());
        }
        return null;
    }

    private static CustList<SrcFileLocation> coeur(String _fileName, int _caret, ResultExpressionOperationNode _res) {
        OperationNode foundOp_ = _res.getFound();
        if (foundOp_ instanceof StaticAccessOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticAccessOperation)foundOp_).getPartOffsets(), _caret);
        }
        if (foundOp_ instanceof StaticCallAccessOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticCallAccessOperation)foundOp_).getPartOffsets().getResult(), _caret);
        }
        if (foundOp_ instanceof FunctFilterOperation) {
            return id(_caret, foundOp_);
        }
        return trefle(_fileName, _caret, _res);
    }

    private static CustList<SrcFileLocation> trefle(String _fileName, int _caret, ResultExpressionOperationNode _res) {
        CustList<SrcFileLocation> ls_ = carreau(_fileName, _caret, _res);
        OperationNode f_ = _res.getFound();
        if (f_ != null) {
            fctPub(f_.getResultClass().getFunction(),ls_);
            MethodOperation p_ = f_.getParent();
            if (p_ instanceof AbstractDotOperation) {
                fctPub(p_.getResultClass().getFunction(),ls_);
            }
        }
        return ls_;
    }

    private static CustList<SrcFileLocation> carreau(String _fileName, int _caret, ResultExpressionOperationNode _res) {
        OperationNode foundOp_ = _res.getFound();
        if (foundOp_ instanceof AbsFctOperation) {
            return fct(_caret, _res);
        }
        if (foundOp_ instanceof AbstractInstancingOperation) {
            return young(_caret, _res, (AbstractInstancingOperation) foundOp_);
        }
        if (foundOp_ instanceof AnnotationInstanceArobaseOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((AnnotationInstanceArobaseOperation)foundOp_).getPartOffsets(),_caret);
        }
        if (foundOp_ instanceof SettableAbstractFieldOperation) {
            return feelIt(_caret, foundOp_);
        }
        if (foundOp_ instanceof AbstractUnaryOperation) {
            return unary(_caret, foundOp_);
        }
        if (foundOp_ instanceof MiddleSymbolOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            fctPub(((SymbolOperation) foundOp_).getFct().getFunction(), ls_);
            return ls_;
        }
        if (foundOp_ instanceof AbstractComTernaryOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            fctPub(((AbstractComTernaryOperation) foundOp_).getImplFct(), ls_);
            fctPub(((AbstractComTernaryOperation) foundOp_).getTestFct(), ls_);
            return ls_;
        }
        if (foundOp_ instanceof CompoundAffectationOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            fctPub(((CompoundAffectationOperation) foundOp_).getFct().getFunction(), ls_);
            fctPub(((CompoundAffectationOperation) foundOp_).getConv().getFunction(), ls_);
            fctPub(((CompoundAffectationOperation) foundOp_).getFunctionTest(), ls_);
            return ls_;
        }
        if (foundOp_ instanceof QuickOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            fctPub(((QuickOperation) foundOp_).getFct().getFunction(), ls_);
            fctPub(((QuickOperation) foundOp_).getConv().getFunction(), ls_);
            fctPub(((QuickOperation) foundOp_).getFunctionTest(), ls_);
            return ls_;
        }
        if (foundOp_ instanceof ExplicitOperatorOperation) {
            return exp(_caret, (ExplicitOperatorOperation) foundOp_);
        }
        if (foundOp_ instanceof ArrOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            fctPub(((ArrOperation) foundOp_).getFunctionGet(), ls_);
            fctPub(((ArrOperation) foundOp_).getFunctionSet(), ls_);
            return ls_;
        }
        if (foundOp_ instanceof AbstractInvokingConstructor) {
            return curr(_caret, (AbstractInvokingConstructor) foundOp_);
        }
        if (foundOp_ instanceof DimensionArrayInstancing) {
            return types(((DimensionArrayInstancing)foundOp_).getPartOffsets(),_caret);
        }
        if (foundOp_ instanceof ElementArrayInstancing) {
            return types(((ElementArrayInstancing)foundOp_).getPartOffsets(),_caret);
        }
        return pique(_fileName, _caret, _res);
    }

    private static CustList<SrcFileLocation> curr(int _caret, AbstractInvokingConstructor _foundOp) {
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        ls_.addAllElts(fetch(_caret, _foundOp.getPartOffsets()));
        if (!ls_.isEmpty()) {
            return ls_;
        }
        fctPub(_foundOp.getConstructor(), ls_);
        return ls_;
    }

    private static CustList<SrcFileLocation> exp(int _caret, ExplicitOperatorOperation _foundOp) {
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        ls_.addAllElts(fetch(_caret, _foundOp.getTypesImpl()));
        ls_.addAllElts(fetch(_caret, _foundOp.getTypesTest()));
        ls_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(_foundOp.getPartOffsets(), _caret));
        if (!ls_.isEmpty()) {
            return ls_;
        }
        fctPub(_foundOp.getCallFctContent().getFunction(), ls_);
        fctPub(_foundOp.getConv().getFunction(), ls_);
        fctPub(_foundOp.getFunctionTest(), ls_);
        return ls_;
    }

    private static CustList<SrcFileLocation> unary(int _caret, OperationNode _foundOp) {
        if (_foundOp instanceof AssocationOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            AnaTypeFct f_ = ((AssocationOperation) _foundOp).getFunction();
            fctPub(f_, ls_);
            return ls_;
        }
        if (_foundOp instanceof EnumValueOfOperation) {
            return fetch(_caret, ((EnumValueOfOperation)_foundOp).getPartOffsets());
        }
        if (_foundOp instanceof InstanceOfOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((InstanceOfOperation) _foundOp).getPartOffsets(), _caret);
        }
        if (_foundOp instanceof CastFctOperation) {
            return cast(_caret, (CastFctOperation) _foundOp);
        }
        if (_foundOp instanceof CastOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((CastOperation) _foundOp).getPartOffsets(), _caret);
        }
        if (_foundOp instanceof SymbolOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            fctPub(((SymbolOperation) _foundOp).getFct().getFunction(), ls_);
            return ls_;
        }
        if (_foundOp instanceof SemiAffectationOperation) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            fctPub(((SemiAffectationOperation) _foundOp).getFct().getFunction(), ls_);
            fctPub(((SemiAffectationOperation) _foundOp).getConvTo().getFunction(), ls_);
            return ls_;
        }
        if (_foundOp instanceof NamedArgumentOperation) {
            return name(_caret,(NamedArgumentOperation)_foundOp);
        }
        if (_foundOp instanceof SwitchOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((SwitchOperation) _foundOp).getPartOffsets(), _caret);
        }
        return new CustList<SrcFileLocation>();
    }

    private static CustList<SrcFileLocation> name(int _caret, NamedArgumentOperation _foundOp) {
        if (_foundOp.isRecordBlock()) {
            CustList<SrcFileLocation> ls_ = fetch(_caret, _foundOp.getPartOffsets());
            if (!ls_.isEmpty()) {
                return ls_;
            }
            int i_ = _foundOp.getRef();
            RootBlock r_ = _foundOp.getField();
            ClassField cf_ = _foundOp.getIdField();
            String fileName_ = fileName(r_);
            if (!cf_.getClassName().isEmpty()) {
                ls_.add(new SrcFileLocationField(cf_,fileName_,i_));
            }
            return ls_;
        }
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        feedFiltersNamedList(_foundOp,ls_);
        return ls_;
    }

    private static void feedFiltersNamedList(NamedArgumentOperation _namedArg, CustList<SrcFileLocation> _list) {
        CustList<NamedFunctionBlock> customMethods_ = _namedArg.getCustomMethod();
        Ints offs_ = new Ints();
        CustList<String> fs_ = new CustList<String>();
        for (NamedFunctionBlock n: customMethods_) {
            n.offsetByNameOut(_namedArg.getName(),offs_,fs_);
        }
        int s_ = offs_.size();
        for (int i = 0; i < s_; i++) {
            _list.add(new SrcFileLocationVariable(-1,_namedArg.getName(),fs_.get(i),offs_.get(i)));
        }
    }
    private static CustList<SrcFileLocation> cast(int _caret, CastFctOperation _foundOp) {
        CustList<SrcFileLocation> locs_ = fetch(_caret, _foundOp.getPartOffsets());
        if (!locs_.isEmpty()) {
            return locs_;
        }
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        AnaTypeFct f_ = _foundOp.getFunction();
        fctPub(f_, ls_);
        return ls_;
    }

    private static CustList<SrcFileLocation> pique(String _fileName, int _caret, ResultExpressionOperationNode _res) {
        OperationNode foundOp_ = _res.getFound();
        if (foundOp_ instanceof DefaultValueOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((DefaultValueOperation)foundOp_).getPartOffsets(),_caret);
        }
        if (okFinalVar(foundOp_)) {
            AnaVariableContent v_ = ((FinalVariableOperation) foundOp_).getVariableContent();
            CustList<SrcFileLocation> l_ = new CustList<SrcFileLocation>();
            l_.add(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),_fileName,((FinalVariableOperation)foundOp_).getRef()));
            return l_;
        }
        if (foundOp_ instanceof ForwardOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForwardOperation)foundOp_).getPartOffsets(),_caret);
        }
        if (foundOp_ instanceof StaticInfoOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((StaticInfoOperation)foundOp_).getPartOffsets(),_caret);
        }
        if (foundOp_ instanceof ValuesOperation) {
            return fetch(_caret, ((ValuesOperation)foundOp_).getPartOffsets());
        }
        if (okVar(foundOp_)) {
            AnaVariableContent v_ = ((VariableOperation) foundOp_).getVariableContent();
            CustList<SrcFileLocation> l_ = new CustList<SrcFileLocation>();
            l_.add(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),_fileName,((VariableOperation)foundOp_).getRef()));
            return l_;
        }
        if (foundOp_ instanceof VariableOperationUse) {
            AnaVariableContent v_ = ((VariableOperationUse) foundOp_).getVariableContent();
            CustList<SrcFileLocation> l_ = new CustList<SrcFileLocation>();
            l_.add(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),_fileName,((VariableOperationUse)foundOp_).getRef()));
            return l_;
        }
        if (foundOp_ instanceof LambdaOperation) {
            return lambda(_res, (LambdaOperation) foundOp_, _caret);
        }
        return new CustList<SrcFileLocation>();
    }

    private static CustList<SrcFileLocation> lambda(ResultExpressionOperationNode _res,LambdaOperation _lda, int _caret) {
        CustList<AnaNamedFieldContent> namedFields_ = _lda.getNamedFields();
        int len_ = namedFields_.size();
        int beginLambda_ = _res.begin(_lda)+_lda.getOffset();
        for (int i = 0; i < len_; i++) {
            int ref_ = _lda.getRefs().get(i);
            if (ref_ < 0) {
                continue;
            }
            AnaNamedFieldContent naFi_ = namedFields_.get(i);
            String name_ = naFi_.getName();
            int off_ = _lda.getOffsets().get(i);
            if (inRange(off_+beginLambda_,_caret,off_+beginLambda_+name_.length())) {
                RootBlock r_ = naFi_.getDeclaring();
                CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
                ls_.add(new SrcFileLocationField(new ClassField(naFi_.getIdClass(),name_),fileName(r_),ref_));
                return ls_;
            }
        }
        CustList<SrcFileLocation> types_ = new CustList<SrcFileLocation>();
        types_.addAllElts(fetch(_caret, _lda.getPartOffsetsBegin()));
        types_.addAllElts(fetch(_caret, _lda.getPartOffsetsPre()));
        types_.addAllElts(fetch(_caret, _lda.getPartOffsets()));
        types_.addAllElts(fetch(_caret, _lda.getPartsInstInitInterfaces()));
        for (int i = 0; i < len_; i++) {
            types_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(_lda.getPartOffsetsRec().get(i).build(),_caret));
        }
        if (!types_.isEmpty()) {
            return types_;
        }
        AnaTypeFct function_ = _lda.getFunction();
        RootBlock fieldType_ = _lda.getFieldType();
        CustList<SrcFileLocation> def_ = new CustList<SrcFileLocation>();
        ctStd(_lda.getStandardConstructor(), def_);
        callStd(_lda.getStandardMethod(), def_);
        fctPub(function_, def_);
        if (!def_.isEmpty()) {
            return def_;
        }
        ClassField fieldId_ = _lda.getFieldId();
        if (fieldId_ != null) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationField(fieldId_,fileName(fieldType_), _lda.getValueOffset()));
            return ls_;
        }
        return new CustList<SrcFileLocation>();
    }

    private static CustList<SrcFileLocation> fct(int _caret, ResultExpressionOperationNode _res) {
        OperationNode foundOp_ = _res.getFound();
        CustList<SrcFileLocation> locs_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((AbsFctOperation) foundOp_).getPartOffsets(), _caret);
        if (!locs_.isEmpty()) {
            return locs_;
        }
        AnaCallFctContent c_ = ((AbsFctOperation) foundOp_).getCallFctContent();
        return methodsLocations(c_);
    }

    private static boolean okFinalVar(OperationNode _op) {
        return _op instanceof FinalVariableOperation && ((FinalVariableOperation) _op).isOk();
    }

    private static boolean okVar(OperationNode _op) {
        return _op instanceof VariableOperation && ((VariableOperation) _op).isOk();
    }

    private static CustList<SrcFileLocation> feelIt(int _caret, OperationNode _foundOp) {
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        if (_foundOp instanceof SettableFieldOperation) {
            ls_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((SettableFieldOperation) _foundOp).getPartOffsets(), _caret));
        }
        if (!ls_.isEmpty()) {
            return ls_;
        }
        int i_ = ((SettableAbstractFieldOperation) _foundOp).getValueOffset();
        RootBlock r_ = ((SettableAbstractFieldOperation) _foundOp).getFieldType();
        ClassField cf_ = ((SettableAbstractFieldOperation) _foundOp).getFieldIdReadOnly();
        String fileName_ = fileName(r_);
        if (!cf_.getClassName().isEmpty()) {
            ls_.add(new SrcFileLocationField(cf_,fileName_,i_));
        }
        return ls_;
    }

    private static String fileName(AbsBk _r) {
        String fileName_;
        if (_r != null) {
            fileName_ = _r.getFile().getFileName();
        } else {
            fileName_ = "";
        }
        return fileName_;
    }

    private static CustList<SrcFileLocation> young(int _caret, ResultExpressionOperationNode _res, AbstractInstancingOperation _op) {
        int offsetNew_ = _op.getOffsetFct();
        int beginInst_ = offsetNew_ + _res.begin(_op);
        int lengthInst_ = StringExpUtil.getDollarWordSeparators(_op.getMethodName()).get(1).length();
        if (_op instanceof StandardInstancingOperation&&((StandardInstancingOperation) _op).getInnerElt() != null||inRange(beginInst_, _caret,beginInst_+lengthInst_)) {
            return now(_op);
        }
        ResolvedInstance r_ = _op.getResolvedInstance();
        CustList<SrcFileLocation> t_ = types(r_,_caret);
        if (!t_.isEmpty()) {
            return t_;
        }
        if (_op instanceof StandardInstancingOperation){
            return fetch(_caret, ((StandardInstancingOperation) _op).getPartsInstInitInterfaces());
        }
        return now(_op);
    }
    private static CustList<SrcFileLocation> types(ResolvedInstance _r, int _caret) {
        CustList<SrcFileLocation> s_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(_r.getResult(), _caret);
        if (!s_.isEmpty()) {
            return s_;
        }
        CustList<SrcFileLocation> t_ = fetchAna(_caret, _r.getParts());
        if (!t_.isEmpty()) {
            return t_;
        }
        return new CustList<SrcFileLocation>();
    }

    private static CustList<SrcFileLocation> now(AbstractInstancingOperation _maintenant) {
        StandardConstructor std_ = _maintenant.getInstancingCommonContent().getConstructor();
        AnaTypeFct constructor_ = _maintenant.getConstructor();
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        ctStd(std_, ls_);
        fctPub(constructor_, ls_);
        AnaFormattedRootBlock format_ = _maintenant.getFormattedType();
        if (format_ != null) {
            RootBlock r_ = format_.getRootBlock();
            ls_.add(new SrcFileLocationType(r_));
        }
        return ls_;
    }

    private static void ctStd(StandardConstructor _std, CustList<SrcFileLocation> _ls) {
        if (_std != null) {
            _ls.add(new SrcFileLocationStdMethod(_std));
        }
    }

    private static void fctPub(AnaTypeFct _ct, CustList<SrcFileLocation> _ls) {
        NamedFunctionBlock f_ = fct(_ct);
        if (f_ != null) {
            _ls.add(new SrcFileLocationMethod(f_));
        }
    }

    private static CustList<SrcFileLocation> id(int _caret, OperationNode _foundOp) {
        if (_foundOp instanceof IdFctOperation) {
            CustList<SrcFileLocation> s_ = fetch(_caret, ((IdFctOperation) _foundOp).getPartOffsetsSet());
            if (!s_.isEmpty()) {
                return s_;
            }
        }
        return fetch(_caret, ((FunctFilterOperation) _foundOp).getPartOffsets());
    }

    private static CustList<SrcFileLocation> fetch(int _caret, CustList<AnaResultPartTypeDtoInt> _list) {
        CustList<SrcFileLocation> s_ = new CustList<SrcFileLocation>();
        for (AnaResultPartTypeDtoInt a: _list) {
            s_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(a, _caret));
        }
        return s_;
    }

    private static CustList<SrcFileLocation> fetchAna(int _caret, CustList<AnaResultPartType> _list) {
        CustList<SrcFileLocation> s_ = new CustList<SrcFileLocation>();
        for (AnaResultPartType a: _list) {
            s_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(a, _caret));
        }
        return s_;
    }

    private static CustList<SrcFileLocation> methodsLocations(AnaCallFctContent _c) {
        NamedFunctionBlock cust_ = fct(_c);
        if (cust_ != null) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationMethod(cust_));
            return ls_;
        }
        StandardMethod std_ = _c.getStandardMethod();
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        callStd(std_, ls_);
        return ls_;
    }

    private static void callStd(StandardMethod _std, CustList<SrcFileLocation> _ls) {
        if (_std != null) {
            _ls.add(new SrcFileLocationStdMethod(_std));
        }
    }

    private static NamedFunctionBlock fct(AnaCallFctContent _c) {
        AnaTypeFct f_ = _c.getFunction();
        return fct(f_);
    }

    private static NamedFunctionBlock fct(AnaTypeFct _f) {
        if (_f == null) {
            return null;
        }
        return _f.getFunction();
    }

    public static ResultExpressionOperationNode container(AnalyzedPageEl _page, String _fileName, int _caret) {
        AbsBk sub_ = _page.getPreviousFilesBodies().getVal(_fileName);
        ResultExpressionOperationNode out_ = new ResultExpressionOperationNode();
        while (sub_ != null) {
            sub_ = subContainer(_caret, sub_);
            ResultExpression res_ = result(sub_, _caret);
            out_.setResultExpression(res_);
            BracedBlock next_;
            if (res_ == null) {
                next_ = null;
                out_.setBlock(sub_);
                out_.setFound(null);
            } else {
                OperationNode found_ = out_.subContainer(_caret);
                next_ = nextBlock(found_, res_.getSumOffset(),_caret);
                if (next_ == null) {
                    out_.setBlock(sub_);
                    out_.setFound(found_);
                }
            }
            sub_ = next_;
        }
        return out_;
    }

    public static AbsBk subContainer(int _caret, AbsBk _bl) {
        AbsBk current_ = _bl;
        AbsBk out_ = _bl;
        while (current_ != null) {
            AbsBk ch_ = current_.getFirstChild();
            if (match(current_, _caret)) {
                out_ = current_;
                current_ = ch_;
                continue;
            }
            while (current_ != null) {
                AbsBk n_ = current_.getNextSibling();
                current_ = n_;
                if (match(n_, _caret)) {
                    out_ = n_;
                    break;
                }
            }
        }
        return out_;
    }

    private static boolean match(AbsBk _b, int _caret) {
        return _b!=null&&_b.getOffset() <= _caret && _caret < _b.getEndAll();
    }

    public static ResultExpression result(AbsBk _block, int _caret) {
        if (_block instanceof InfoBlock) {
            ResultParsedAnnots a_ = ((InfoBlock) _block).getAnnotations();
            int index_ = indexOfAnnot(a_, _caret);
            if (index_ > -1) {
                return a_.getAnnotations().get(index_).getRes();
            }
        }
        if (_block instanceof InnerTypeOrElement) {
            return lookElt((InnerTypeOrElement) _block, _caret);
        }
        if (_block instanceof RootBlock) {
            return resRoot((RootBlock) _block, _caret);
        }
        if (_block instanceof NamedFunctionBlock) {
            return lookFct(_block, _caret);
        }
        if (_block instanceof SwitchMethodBlock) {
            return resSw((SwitchMethodBlock) _block, _caret);
        }
        if (_block instanceof FieldBlock) {
            ResultExpression res_ = ((FieldBlock) _block).getRes();
            if (inExp(res_,_caret)){
                return res_;
            }
            return null;
        }
        return instrLook(_block, _caret);
    }

    private static ResultExpression lookElt(InnerTypeOrElement _block, int _caret) {
        AnaElementContent e_ = _block.getElementContent();
        int beginName_ = e_.getFieldNameOffest();
        int endName_ = beginName_ + e_.getFieldName().length();
        if (inRange(beginName_,_caret,endName_)) {
            return _block.getRes();
        }
        int begin_ = e_.getTempClassOffset();
        int end_ = begin_+e_.getTempClass().length();
        if (inRange(begin_,_caret,end_)) {
            return null;
        }
        return _block.getRes();
    }

    private static ResultExpression lookFct(AbsBk _block, int _caret) {
        ResultParsedAnnots a_ = ((NamedFunctionBlock) _block).getAnnotations();
        int index_ = indexOfAnnot(a_, _caret);
        if (index_ > -1) {
            return a_.getAnnotations().get(index_).getRes();
        }
        CustList<ResultParsedAnnots> params_ = ((NamedFunctionBlock) _block).getAnnotationsParams();
        int ind_ = indexOfAnnots(params_, _caret);
        if (ind_ > -1) {
            int loc_ = indexOfAnnot(params_.get(ind_), _caret);
            return params_.get(ind_).getAnnotations().get(loc_).getRes();
        }
        if (_block instanceof NamedCalledFunctionBlock) {
            ResultParsedAnnots annotationsSupp_ = ((NamedCalledFunctionBlock) _block).getAnnotationsSupp();
            int indexSupp_ = indexOfAnnot(annotationsSupp_, _caret);
            if (indexSupp_ > -1) {
                return annotationsSupp_.getAnnotations().get(indexSupp_).getRes();
            }
        }
        return null;
    }

    private static ResultExpression instrLook(AbsBk _block, int _caret) {
        if (_block instanceof ConditionBlock) {
            ResultExpression res_ = ((ConditionBlock) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof WithFilterContent) {
            return caseLook((WithFilterContent) _block, _caret);
        }
        if (_block instanceof SwitchBlock) {
            ResultExpression res_ = ((SwitchBlock) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ForEachLoop) {
            ResultExpression res_ = ((ForEachLoop) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ForEachTable) {
            ResultExpression res_ = ((ForEachTable) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        return defLook(_block, _caret);
    }

    private static ResultExpression caseLook(WithFilterContent _block, int _caret) {
        ResultExpression resValue_ = _block.getFilterContent().getResValue();
        if (inExp(resValue_, _caret)){
            return resValue_;
        }
        ResultExpression resCondition_ = _block.getFilterContent().getResCondition();
        if (inExp(resCondition_, _caret)){
            return resCondition_;
        }
        return null;
    }

    private static ResultExpression defLook(AbsBk _block, int _caret) {
        if (_block instanceof Line) {
            ResultExpression res_ = ((Line) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ReturnMethod) {
            ResultExpression res_ = ((ReturnMethod) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof Throwing) {
            ResultExpression res_ = ((Throwing) _block).getRes();
            if (inExp(res_, _caret)){
                return res_;
            }
            return null;
        }
        if (_block instanceof ForIterativeLoop) {
            return lookForIter((ForIterativeLoop) _block, _caret);
        }
        if (_block instanceof ForMutableIterativeLoop) {
            return lookForMut((ForMutableIterativeLoop) _block, _caret);
        }
        return null;
    }

    private static ResultExpression lookForIter(ForIterativeLoop _block, int _caret) {
        ResultExpression resValue_ = _block.getResInit();
        if (inExp(resValue_, _caret)){
            return resValue_;
        }
        ResultExpression resCondition_ = _block.getResExp();
        if (inExp(resCondition_, _caret)){
            return resCondition_;
        }
        ResultExpression resStep_ = _block.getResStep();
        if (inExp(resStep_, _caret)){
            return resStep_;
        }
        return null;
    }

    private static ResultExpression lookForMut(ForMutableIterativeLoop _block, int _caret) {
        ResultExpression resValue_ = _block.getResInit();
        if (inExp(resValue_, _caret)){
            return resValue_;
        }
        ResultExpression resCondition_ = _block.getResExp();
        if (inExp(resCondition_, _caret)){
            return resCondition_;
        }
        ResultExpression resStep_ = _block.getResStep();
        if (inExp(resStep_, _caret)){
            return resStep_;
        }
        return null;
    }

    public static ResultExpression resRoot(RootBlock _block, int _caret) {
        ResultParsedAnnots a_ = _block.getAnnotations();
        int index_ = indexOfAnnot(a_, _caret);
        if (index_ > -1) {
            return a_.getAnnotations().get(index_).getRes();
        }
        return null;
    }

    public static ResultExpression resSw(SwitchMethodBlock _block, int _caret) {
        ResultParsedAnnots a_ = _block.getAnnotations();
        int index_ = indexOfAnnot(a_, _caret);
        if (index_ > -1) {
            return a_.getAnnotations().get(index_).getRes();
        }
        CustList<ResultParsedAnnots> params_ = _block.getAnnotationsParams();
        int ind_ = indexOfAnnots(params_, _caret);
        if (ind_ > -1) {
            int loc_ = indexOfAnnot(params_.get(ind_), _caret);
            return params_.get(ind_).getAnnotations().get(loc_).getRes();
        }
        return null;
    }

    private static boolean inExp(ResultExpression _res, int _caret) {
        int begin_ = _res.getSumOffset();
        int end_ = begin_ + _res.getAnalyzedString().length();
        return begin_<=_caret&&_caret<end_;
    }

    private static int indexOfAnnots(CustList<ResultParsedAnnots> _a, int _caret) {
        int len_ = _a.size();
        for (int i = 0; i < len_; i++) {
            ResultParsedAnnots annot_ = _a.get(i);
            int loc_ = indexOfAnnot(annot_, _caret);
            if (loc_ > -1) {
                return i;
            }
        }
        return -1;
    }

    private static int indexOfAnnot(ResultParsedAnnots _a, int _caret) {
        CustList<ResultParsedAnnot> annots_ = _a.getAnnotations();
        int len_ = annots_.size();
        for (int i = 0; i < len_; i++) {
            ResultParsedAnnot annot_ = annots_.get(i);
            if (annot_.getIndex()<=_caret&&_caret<annot_.getIndex()+annot_.getAnnotation().trim().length()){
                return i;
            }
        }
        return -1;
    }

    public OperationNode subContainer(int _caret) {
        OperationNode current_ = root(resultExpression);
        OperationNode out_ = root(resultExpression);
        while (current_ != null) {
            OperationNode ch_ = current_.getFirstChild();
            if (match(current_, _caret)) {
                out_ = current_;
                current_ = ch_;
                continue;
            }
            while (current_ != null) {
                OperationNode n_ = current_.getNextSibling();
                current_ = n_;
                if (match(n_, _caret)) {
                    out_ = n_;
                    break;
                }
            }
        }
        return out_;
    }

    public static BracedBlock nextBlock(OperationNode _result, int _sum, int _caret) {
        if (_result instanceof AnonymousLambdaOperation) {
            NamedCalledFunctionBlock anon_ = ((AnonymousLambdaOperation) _result).getBlock();
            int arrow_ = anon_.getNameOffset();
            if (inRange(arrow_, _caret, arrow_ + 2)) {
                return null;
            }
            return anon_;
        }
        if (_result instanceof SwitchOperation) {
            ResultExpression res_ = resSw(((SwitchOperation) _result).getSwitchMethod(), _caret);
            if (res_ != null) {
                return ((SwitchOperation) _result).getSwitchMethod();
            }
            int begin_ = _sum+_result.getIndexInEl()+((SwitchOperation) _result).getOffsetFct();
            int end_ = begin_+((SwitchOperation) _result).getMethodName().length();
            if (inRange(begin_, _caret, end_)) {
                return null;
            }
            return ((SwitchOperation) _result).getSwitchMethod();
        }
        if (_result instanceof AnonymousInstancingOperation) {
            ResultExpression res_ = resRoot(((AnonymousInstancingOperation) _result).getBlock(), _caret);
            if (res_ != null) {
                return ((AnonymousInstancingOperation) _result).getBlock();
            }
            int begin_ = _sum+_result.getIndexInEl()+((AnonymousInstancingOperation) _result).getOffsetFct();
            int end_ = begin_+((AnonymousInstancingOperation) _result).getMethodName().length();
            if (inRange(begin_, _caret, end_)) {
                return null;
            }
            return ((AnonymousInstancingOperation) _result).getBlock();
        }
        return null;
    }

    private static OperationNode root(ResultExpression _r) {
        OperationNode r_ = _r.getRoot();
        if (r_ instanceof AffectationOperation && ((AffectationOperation)r_).isSynthetic()) {
            return r_.getFirstChild().getNextSibling();
        }
        return r_;
    }

    private boolean match(OperationNode _b, int _caret) {
        if (_b == null) {
            return false;
        }
        int begin_ = begin(_b);
        int end_ = end(_b);
        return inRange(begin_, _caret, end_);
    }

    public int begin() {
        return begin(getFound());
    }

    public int begin(OperationNode _b) {
        return _b.getIndexInEl() + resultExpression.getSumOffset();
    }

    public int end() {
        return end(getFound());
    }

    public int end(OperationNode _b) {
        MethodOperation par_ = _b.getParent();
        if (par_ != null) {
            int indexChild_ = _b.getIndexChild();
            return begin(_b) + par_.getChildren().getValue(indexChild_).length();
        }
        return begin(_b) + resultExpression.getAnalyzedString().length();
    }

    public static boolean inRange(int _begin, int _caret, int _end) {
        return _begin <= _caret && _caret < _end;
    }

    public AbsBk getBlock() {
        return block;
    }

    public void setBlock(AbsBk _b) {
        this.block = _b;
    }

    public OperationNode getFound() {
        return found;
    }

    public void setFound(OperationNode _f) {
        this.found = _f;
    }

    public void setResultExpression(ResultExpression _r) {
        this.resultExpression = _r;
    }
}
