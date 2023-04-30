package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class CallersRef {
    private final CustList<FileBlockIndex> variablesParamsUse = new CustList<FileBlockIndex>();
    private final CustList<BlockCallerFct> allBlocks = new CustList<BlockCallerFct>();

//    private final CustList<SrcFileLocation> directRefNamed = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directRefNamedStd = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directRefNamedStdCtor = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directRefImplCtor = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directCallNamed = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directCallNamedStd = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directCallNamedRef = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directCallNamedRefAll = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directCallImplicits = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNew = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNewStd = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNewTypes = new CustList<SrcFileLocation>();
//    private final CustList<SrcFileLocation> directNewInherits = new CustList<SrcFileLocation>();
    public static CallersRef loop(AnalyzedPageEl _page, CustList<SrcFileLocation> _piano) {
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        CallersRef c_ = new CallersRef();
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
        for (BlockCallerFct b: c_.allBlocks) {
            MemberCallingsBlock caller_ = b.getCaller();
            for (SrcFileLocation s: tryDecl(b.getBlock())){
                addIfMatch(s,new SrcFileLocationMethod(caller_.getParent(),caller_),s.getFile(),s.getIndex(),c_.variablesParamsUse,_piano);
            }
            c_.def(b.getBlock(),_piano);
        }
        CustList<ResultExpressionBlockOperation> ops_ = new CustList<ResultExpressionBlockOperation>();
        for (ResultExpressionBlock r: ls_) {
            ops_.addAllElts(loopOperation(r));
        }
        for (ResultExpressionBlockOperation o: ops_) {
            c_.callingsCustDirect(o,_piano);
        }
        return c_;
    }

    private void type(CustList<ResultExpressionBlock> _ls, RootBlock _r) {
        for (InfoBlock i: _r.getFieldsBlocks()) {
            _ls.addAllElts(rootBlock(null,(AbsBk) i));
        }
        for (NamedCalledFunctionBlock b: _r.getOverridableBlocks()) {
            _ls.addAllElts(loopFct(b));
        }
        for (NamedCalledFunctionBlock b: _r.getAnnotationsMethodsBlocks()) {
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
        CustList<ResultExpressionBlock> ls_ = new CustList<ResultExpressionBlock>();
        AbsBk en_ = _mem;
        while (en_ != null) {
            AbsBk n_ = en_.getFirstChild();
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
    private static CustList<ResultExpressionBlockOperation> loopOperation(ResultExpressionBlock _mem) {
        CustList<ResultExpressionBlockOperation> ls_ = new CustList<ResultExpressionBlockOperation>();
        OperationNode r_ = _mem.getRes().getRoot();
        OperationNode en_ = r_;
        while (en_ != null) {
            OperationNode n_ = en_.getFirstChild();
            ls_.add(new ResultExpressionBlockOperation(en_,_mem));
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
                MethodOperation p_ = en_.getParent();
                if (p_ instanceof DeclaringOperation||p_ == r_||p_ == null) {
                    en_ = null;
                } else {
                    en_ = p_;
                }
            }
        }
        return ls_;
    }
    private CustList<ResultExpressionBlock> rootBlock(MemberCallingsBlock _caller, AbsBk _en) {
        allBlocks.add(new BlockCallerFct(_en,_caller));
        CustList<ResultExpressionBlock> annotFields_ = new CustList<ResultExpressionBlock>();
//        if (_en instanceof InfoBlock) {
//            ResultParsedAnnots a_ = ((InfoBlock) _en).getAnnotations();
//            addAnnots(_en,annotFields_, a_);
//        }
        if (_en instanceof InfoBlock) {
            for (String c: ((InfoBlock)_en).getElements().getFieldName()) {
                int o_ = AnaTypeUtil.getIndex(((InfoBlock) _en), c);
                RootBlock d_ = ((InfoBlock) _en).getDeclaringType();
                annotFields_.add(new ResultExpressionBlock(new SrcFileLocationField(new ClassField(d_.getFullName(),c), d_,o_),_en,((InfoBlock)_en).getRes()));
            }
            return annotFields_;
        }
//        if (_en instanceof RootBlock) {
//            addAnnots(_en,annotFields_, ((RootBlock)_en).getAnnotations());
//            return annotFields_;
//        }
//        if (_en instanceof NamedFunctionBlock) {
//            addAnnotsList(annotFields_,(NamedFunctionBlock)_en);
//            return annotFields_;
//        }
//        if (_en instanceof SwitchMethodBlock) {
//            resSw(annotFields_,(SwitchMethodBlock)_en);
//            return annotFields_;
//        }
        return instrLook(_caller,_en);
    }

//    public static void resSw(CustList<ResultExpressionBlock> _list, SwitchMethodBlock _block) {
//        ResultParsedAnnots a_ = _block.getAnnotations();
//        addAnnots(_block,_list,a_);
//        CustList<ResultParsedAnnots> params_ = _block.getAnnotationsParams();
//        for (ResultParsedAnnots a: params_) {
//            addAnnots(_block,_list,a);
//        }
//    }
//    private static void addAnnotsList(CustList<ResultExpressionBlock> _list, NamedFunctionBlock _block) {
//        if (_block instanceof NamedCalledFunctionBlock) {
//            _list.add(new ResultExpressionBlock(_block,((NamedCalledFunctionBlock)_block).getRes()));
//        }
//        ResultParsedAnnots a_ = _block.getAnnotations();
//        addAnnots(_block,_list,a_);
//        CustList<ResultParsedAnnots> params_ = _block.getAnnotationsParams();
//        for (ResultParsedAnnots a: params_) {
//            addAnnots(_block,_list,a);
//        }
//        if (_block instanceof NamedCalledFunctionBlock) {
//            ResultParsedAnnots annotationsSupp_ = ((NamedCalledFunctionBlock) _block).getAnnotationsSupp();
//            addAnnots(_block,_list,annotationsSupp_);
//        }
//    }
//    private static void addAnnots(AbsBk _en,CustList<ResultExpressionBlock> _anns, ResultParsedAnnots _a) {
//        for (ResultParsedAnnot a: _a.getAnnotations()) {
//            _anns.add(new ResultExpressionBlock(_en,a.getRes()));
//        }
//    }

    private static CustList<ResultExpressionBlock> instrLook(MemberCallingsBlock _caller,AbsBk _block) {
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
    public void callingsCustDirect(ResultExpressionBlockOperation _c, CustList<SrcFileLocation> _piano) {
        OperationNode o_ = _c.getBlock();
        FileBlock f_ = _c.getRes().getBlock().getFile();
        if (o_ instanceof FinalVariableOperation && ((FinalVariableOperation) o_).isOk()) {
            AnaVariableContent v_ = ((FinalVariableOperation) o_).getVariableContent();
            addIfMatch(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),f_,((FinalVariableOperation)o_).getRef()),_c.getRes().getCaller(), f_,begin(_c),variablesParamsUse,_piano);
        }
        if (o_ instanceof VariableOperation && ((VariableOperation) o_).isOk()) {
            AnaVariableContent v_ = ((VariableOperation) o_).getVariableContent();
            addIfMatch(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),f_,((VariableOperation)o_).getRef()),_c.getRes().getCaller(), f_,begin(_c),variablesParamsUse,_piano);
        }
        if (o_ instanceof VariableOperationUse) {
            AnaVariableContent v_ = ((VariableOperationUse) o_).getVariableContent();
            addIfMatch(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),f_,((VariableOperationUse)o_).getRef()),_c.getRes().getCaller(), f_,begin(_c),variablesParamsUse,_piano);
        }
        if (o_ instanceof NamedArgumentOperation) {
            for (SrcFileLocation s: name((NamedArgumentOperation)o_)){
                addIfMatch(s,_c.getRes().getCaller(), f_,begin(_c),variablesParamsUse,_piano);
            }
        }
//        if (_c instanceof AbstractInvokingConstructor) {
//            fctPub(((AbstractInvokingConstructor)_c).getConstructor(), directNewInherits, _piano);
//        }
//        if (_c instanceof AbstractInstancingOperation) {
//            AnaTypeFct cto_ = ((AbstractInstancingOperation) _c).getConstructor();
//            if (cto_ != null) {
//                fctPub(cto_, directNew, _piano);
//            } else {
//                AnaFormattedRootBlock format_ = ((AbstractInstancingOperation) _c).getFormattedType();
//                if (format_ != null) {
//                    RootBlock r_ = format_.getRootBlock();
//                    addIfMatch(new SrcFileLocationType(r_),directNewTypes,_piano);
//                }
//            }
//            ctStd(((AbstractInstancingOperation) _c).getInstancingCommonContent().getConstructor(), ((AbstractInstancingOperation) _c).getInstancingCommonContent().getStd(), directNewStd, _piano);
//        }
//        if (_c instanceof AbsFctOperation) {
//            fctPub(((AbsFctOperation)_c).getCallFctContent().getFunction(), directCallNamed, _piano);
//            callStd(((AbsFctOperation)_c).getCallFctContent().getStandardMethod(),((AbsFctOperation)_c).getCallFctContent().getStandardType(), directCallNamedStd, _piano);
//        }
//        if (_c instanceof CallDynMethodOperation) {
//            callStd(((CallDynMethodOperation)_c).getStdMethod(),((CallDynMethodOperation)_c).getStdType(), directCallNamedStd, _piano);
//            String r_ = ((CallDynMethodOperation) _c).getRefer();
//            if (!r_.isEmpty()) {
//                SrcFileLocationCall ref_ = new SrcFileLocationCall(r_);
//                addIfMatch(ref_,directCallNamedRef,_piano);
//                directCallNamedRefAll.add(ref_);
//            }
//        }
//        if (_c instanceof CastFctOperation) {
//            fctPub(((CastFctOperation)_c).getFunction(), directCallImplicits, _piano);
//        }
//        if (_c instanceof LambdaOperation) {
//            lambda((LambdaOperation) _c, _piano);
//        }
    }

    private static int begin(ResultExpressionBlockOperation _b) {
        return _b.getBlock().getIndexInEl() + _b.getRes().getRes().getSumOffset();
    }
    private static CustList<SrcFileLocation> name(NamedArgumentOperation _foundOp) {
//        if (_foundOp.isRecordBlock()) {
//            CustList<SrcFileLocation> ls_ = fetch(_caret, _foundOp.getPartOffsets());
//            if (!ls_.isEmpty()) {
//                return ls_;
//            }
//            int i_ = _foundOp.getRef();
//            RootBlock r_ = _foundOp.getField();
//            ClassField cf_ = _foundOp.getIdField();
//            if (!cf_.getClassName().isEmpty()) {
//                ls_.add(new SrcFileLocationField(cf_,r_, i_));
//            }
//            return ls_;
//        }
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        ResultExpressionOperationNode.feedFiltersNamedList(_foundOp,ls_);
        return ls_;
    }

    private void def(AbsBk _bl, CustList<SrcFileLocation> _piano) {
//        CustList<SrcFileLocation> t_ = otherTypes(_bl, _caret);
//        if (!t_.isEmpty()) {
//            return t_;
//        }
        if (_bl instanceof NamedFunctionBlock) {
            defName((NamedFunctionBlock) _bl, _piano);
        }
//        if (_bl instanceof InternOverrideBlock) {
//            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
//            for (PartOffsetsClassMethodIdList l:((InternOverrideBlock)_bl).getAllPartsTypes()) {
//                for (PartOffsetsClassMethodId p:l.getOverrides()) {
//                    if (inRange(p.getBegin(), _caret,p.getBegin()+p.getLength())) {
//                        fctPub(p.getFct(),ls_);
//                    }
//                }
//            }
//            return ls_;
//        }
    }

    private void defName(NamedFunctionBlock _bl, CustList<SrcFileLocation> _piano) {
        Ints offs_ = _bl.getParametersNamesOffset();
        StringList names_ = _bl.getParametersNames();
        int s_ = names_.size();
        for (int i = 0; i < s_; i++) {
            addIfMatch(new SrcFileLocationVariable(-1,names_.get(i), _bl.getFile(),offs_.get(i)),new SrcFileLocationMethod(_bl.getParent(),_bl),_bl.getFile(),offs_.get(i),variablesParamsUse,_piano);
        }
//        if (_bl instanceof NamedCalledFunctionBlock) {
//            for (PartOffsetsClassMethodId p:((NamedCalledFunctionBlock) _bl).getAllInternTypesParts()) {
//                if (inRange(p.getBegin(), _caret,p.getBegin()+p.getLength())) {
//                    fctPub(p.getFct(),p_);
//                }
//            }
//        }
//        if (inRange(_bl.getNameOffset(),_caret,_bl.getNameOffset()+_bl.getRealLength())) {
//            p_.add(new SrcFileLocationMethod(_bl.getParent(),_bl));
//        }
    }

    private static CustList<SrcFileLocation> tryDecl(AbsBk _bl) {
        CustList<SrcFileLocation> vars_ = new CustList<SrcFileLocation>();
        if (_bl instanceof ForEachLoop) {
            vars_.add(new SrcFileLocationVariable(-1,((ForEachLoop)_bl).getVariableName(),_bl.getFile(),((ForEachLoop)_bl).getVariableNameOffset()));
            return vars_;
        }
        if (_bl instanceof ForIterativeLoop) {
            vars_.add(new SrcFileLocationVariable(-1,((ForIterativeLoop)_bl).getVariableName(),_bl.getFile(),((ForIterativeLoop)_bl).getVariableNameOffset()));
            return vars_;
        }
        if (_bl instanceof ForEachTable) {
            vars_.add(new SrcFileLocationVariable(-1,((ForEachTable)_bl).getVariableNameFirst(),_bl.getFile(),((ForEachTable)_bl).getVariableNameOffsetFirst()));
            vars_.add(new SrcFileLocationVariable(-1,((ForEachTable)_bl).getVariableNameSecond(),_bl.getFile(),((ForEachTable)_bl).getVariableNameOffsetSecond()));
            return vars_;
        }
        if (_bl instanceof WithFilterContent){
            vars_.add(new SrcFileLocationVariable(-1,((WithFilterContent)_bl).getFilterContent().getVariableName(),_bl.getFile(),((WithFilterContent)_bl).getFilterContent().getVariableOffset()));
            return vars_;
        }
        if (_bl instanceof DefaultCondition) {
            vars_.add(new SrcFileLocationVariable(-1,((DefaultCondition)_bl).getVariableName(),_bl.getFile(),((DefaultCondition)_bl).getVariableOffset()));
            return vars_;
        }
        return new CustList<SrcFileLocation>();
    }

//    private static boolean okVar(OperationNode _op) {
//        return _op instanceof VariableOperation && ((VariableOperation) _op).isOk();
//    }
//    private void lambda(LambdaOperation _lda,  CustList<SrcFileLocation> _piano) {
////        CustList<AnaNamedFieldContent> namedFields_ = _lda.getNamedFields();
////        int len_ = namedFields_.size();
////        int beginLambda_ = _res.begin(_lda)+_lda.getOffset();
////        for (int i = 0; i < len_; i++) {
////            int ref_ = _lda.getRefs().get(i);
////            if (ref_ < 0) {
////                continue;
////            }
////            AnaNamedFieldContent naFi_ = namedFields_.get(i);
////            String name_ = naFi_.getName();
////            int off_ = _lda.getOffsets().get(i);
////            if (inRange(off_+beginLambda_,_caret,off_+beginLambda_+name_.length())) {
////                RootBlock r_ = naFi_.getDeclaring();
////                CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
////                ls_.add(new SrcFileLocationField(new ClassField(naFi_.getIdClass(),name_),r_, ref_));
////                return ls_;
////            }
////        }
////        CustList<SrcFileLocation> types_ = new CustList<SrcFileLocation>();
////        types_.addAllElts(fetch(_caret, _lda.getPartOffsetsBegin()));
////        types_.addAllElts(fetch(_caret, _lda.getPartOffsetsPre()));
////        types_.addAllElts(fetch(_caret, _lda.getPartOffsets()));
////        types_.addAllElts(fetch(_caret, _lda.getPartsInstInitInterfaces()));
////        for (int i = 0; i < len_; i++) {
////            types_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(_lda.getPartOffsetsRec().get(i).build(),_caret));
////        }
//        AnaTypeFct function_ = _lda.getFunction();
//        ctStd(_lda.getStandardConstructor(), _lda.getStandardType(), directRefNamedStdCtor, _piano);
//        callStd(_lda.getStandardMethod(), _lda.getStandardType(), directRefNamedStd, _piano);
//        NamedFunctionBlock n_ = fct(function_);
//        if (n_ != null) {
//            fctPub(function_, directRefNamed, _piano);
//        } else if (function_ != null){
//            RootBlock r_ = function_.getType();
//            addIfMatch(new SrcFileLocationType(r_),directRefImplCtor,_piano);
//        }
////        ClassField fieldId_ = _lda.getFieldId();
////        if (fieldId_ != null) {
////            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
////            ls_.add(new SrcFileLocationField(fieldId_,fieldType_, _lda.getValueOffset()));
////            return ls_;
////        }
//    }

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
    private static void addIfMatch(SrcFileLocation _c, SrcFileLocation _a,FileBlock _currFile, int _index, CustList<FileBlockIndex> _ls, CustList<SrcFileLocation> _piano) {
        for (SrcFileLocation r: _piano) {
//            if (((r instanceof SrcFileLocationStdMethod && _c instanceof SrcFileLocationStdMethod && ((SrcFileLocationStdMethod) r).getStd() == ((SrcFileLocationStdMethod) _c).getStd() || r instanceof SrcFileLocationStdType && _c instanceof SrcFileLocationStdType && StringUtil.quickEq(((SrcFileLocationStdType) r).getType(), ((SrcFileLocationStdType) _c).getType())) || r instanceof SrcFileLocationCall && _c instanceof SrcFileLocationCall && StringUtil.quickEq(((SrcFileLocationCall) r).getTypeRef(), ((SrcFileLocationCall) _c).getTypeRef())) || r instanceof SrcFileLocationField && _c instanceof SrcFileLocationField && ((SrcFileLocationField) r).getCf().eq(((SrcFileLocationField) _c).getCf()) || r.getFile() == _c.getFile() && r.getIndex() == _c.getIndex()) {
//                _ls.add(_c);
//            }
            if (r.getFile() == _c.getFile() && r.getIndex() == _c.getIndex()) {
                _ls.add(new FileBlockIndex(_currFile,_index,_c,_a));
            }
        }
    }

    public CustList<FileBlockIndex> getVariablesParamsUse() {
        return variablesParamsUse;
    }
//    private static NamedFunctionBlock fct(AnaTypeFct _f) {
//        if (_f == null) {
//            return null;
//        }
//        return _f.getFunction();
//    }
}
