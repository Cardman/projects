package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodId;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodIdList;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.expressionlanguage.analyze.types.LocationsPartTypeUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ResultExpressionOperationNode {
    private ResultExpression resultExpression;
    private int beginOff = -1;
    private AbsBk block;
    private OperationNode found;
    private ResultExpressionOperationNode(){
    }
    public static SynthFieldInfo vexerChamps(AnalyzedPageEl _page, String _fileName, int _caret) {
        ResultExpressionOperationNode res_ = container(_caret, _page.getPreviousFilesBodies().getVal(_fileName));
        if (res_.getFound() instanceof SettableAbstractFieldOperation) {
            RootBlock r_ = ((SettableAbstractFieldOperation) res_.getFound()).getFieldType();
            if (r_ != null) {
                return new SynthFieldInfo(((SettableAbstractFieldOperation)res_.getFound()).getFieldIdReadOnly(), r_);
            }
        }
        return new SynthFieldInfo(new ClassField("",""),null);
    }

    public static AnalyzedPageEl prepareFields(WatchPointBlockPair _trField, AnalyzedPageEl _original, int _setting) {
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentPkg(a_.getDefaultPkg());
        if (_trField.getWp().isTrueField()) {
            trField(_trField, _setting, a_);
        } else {
            for (NamedCalledFunctionBlock i: _trField.getRoot().getAnnotationsMethodsBlocks()) {
                if (StringUtil.quickEq(i.getName(), _trField.getWp().fieldName())) {
                    field(a_, _trField.getRoot(), false);
                    possibleSet(_setting, a_, i.getImportedReturnType());
                }
            }
        }
        return a_;
    }

    private static void trField(WatchPointBlockPair _trField, int _setting, AnalyzedPageEl _a) {
        CustList<InfoBlock> ls_ = _trField.getRoot().getFieldsBlocks();
        for (InfoBlock i: ls_) {
            if (StringUtil.contains(i.getElements().getFieldName(), _trField.getWp().fieldName())) {
                field(_a, _trField.getRoot(), i.isStaticField());
                possibleSet(_setting, _a, i.getImportedClassName());
            }
        }
    }

    private static void possibleSet(int _setting, AnalyzedPageEl _a, String _cl) {
        if (_setting == WatchPoint.BPC_WRITE || _setting == WatchPoint.BPC_COMPOUND_WRITE || _setting == WatchPoint.BPC_COMPOUND_WRITE_ERR) {
            String p_ = _a.getKeyWords().getKeyWordValue();
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(_cl);
            lv_.setConstType(ConstType.PARAM);
            lv_.setFinalVariable(true);
            lv_.setKeyWord(true);
            _a.getCache().getLocalVariables().add(new AnaNamedLocalVariable(p_, lv_));
        }
    }

    public static AnalyzedPageEl prepareExc(String _id, int _exact, AnalyzedPageEl _original) {
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentPkg(a_.getDefaultPkg());
        RootBlock r_ = _original.getAnaClassBody(StringExpUtil.getIdFromAllTypes(_id));
        if (r_ != null) {
            field(a_,r_,false);
            if (_exact == ExcPointBlockKey.SAME) {
                a_.setOriginalGlobalType(new AnaFormattedRootBlock(r_,_id));
            }
        } else {
            a_.setAccessStaticContext(MethodAccessKind.INSTANCE);
            a_.setOriginalGlobalType(new AnaFormattedRootBlock((RootBlock)null,_id));
        }
        return a_;
    }

    public static AnalyzedPageEl preparePar(String _id, int _exact, RootBlock _rootBlock, AnalyzedPageEl _original) {
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentPkg(a_.getDefaultPkg());
        if (_rootBlock != null) {
            field(a_,_rootBlock,false);
            if (_exact == ExcPointBlockKey.SAME) {
                a_.setOriginalGlobalType(new AnaFormattedRootBlock(_rootBlock,_id));
            }
        } else {
            a_.setOriginalGlobalType(new AnaFormattedRootBlock((RootBlock)null,_original.getAliasObject()));
        }
        AnaLocalVariable lvIndex_ = new AnaLocalVariable();
        lvIndex_.setClassName(a_.getAliasPrimInteger());
        lvIndex_.setConstType(ConstType.PARAM);
        lvIndex_.setFinalVariable(true);
        a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(a_.getKeyWords().getKeyWordValue(), lvIndex_));
        return a_;
    }

    public static AnalyzedPageEl prepareArr(String _id, AnalyzedPageEl _original, int _flag) {
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentPkg(a_.getDefaultPkg());
        a_.setAccessStaticContext(MethodAccessKind.INSTANCE);
        if (_flag != ArrPoint.BPC_INIT) {
            a_.setOriginalGlobalType(new AnaFormattedRootBlock((RootBlock)null,_id));
        }
        if (_flag == ArrPoint.BPC_INIT || _flag == ArrPoint.BPC_INT_GET || _flag == ArrPoint.BPC_INT_SET || _flag == ArrPoint.BPC_INT_COMPOUND_GET || _flag == ArrPoint.BPC_INT_COMPOUND_SET || _flag == ArrPoint.BPC_INT_COMPOUND_SET_ERR || _flag == ArrPoint.BPC_INT_GET_SET) {
            AnaLocalVariable lvIndex_ = new AnaLocalVariable();
            lvIndex_.setClassName(StringExpUtil.getPrettyArrayType(a_.getAliasPrimInteger()));
            lvIndex_.setConstType(ConstType.PARAM);
            lvIndex_.setFinalVariable(true);
            a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(a_.getKeyWords().getKeyWordValue(), lvIndex_));
        } else if (_flag == ArrPoint.BPC_RANGE_GET || _flag == ArrPoint.BPC_RANGE_SET || _flag == ArrPoint.BPC_RANGE_COMPOUND_GET || _flag == ArrPoint.BPC_RANGE_COMPOUND_SET) {
            AnaLocalVariable lvIndex_ = new AnaLocalVariable();
            lvIndex_.setClassName(a_.getAliasRange());
            lvIndex_.setConstType(ConstType.PARAM);
            lvIndex_.setFinalVariable(true);
            a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(a_.getKeyWords().getKeyWordValue(), lvIndex_));
        }
        if (_flag == ArrPoint.BPC_INT_SET || _flag == ArrPoint.BPC_INT_COMPOUND_SET || _flag == ArrPoint.BPC_INT_COMPOUND_SET_ERR) {
            String p_ = a_.getKeyWords().getKeyWordValue();
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(_id)));
            lv_.setConstType(ConstType.PARAM);
            lv_.setFinalVariable(true);
            a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(p_, lv_));
        } else if (_flag == ArrPoint.BPC_RANGE_SET || _flag == ArrPoint.BPC_RANGE_COMPOUND_SET) {
            String p_ = a_.getKeyWords().getKeyWordValue();
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(_id);
            lv_.setConstType(ConstType.PARAM);
            lv_.setFinalVariable(true);
            a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(p_, lv_));
        } else if (_flag == ArrPoint.BPC_INT_GET_SET) {
            String p_ = a_.getKeyWords().getKeyWordValue();
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(_original.getAliasObject());
            lv_.setConstType(ConstType.PARAM);
            lv_.setFinalVariable(true);
            a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(p_, lv_));
        }
        return a_;
    }
    public static AnalyzedPageEl prepare(String _fileName, int _caret, AnalyzedPageEl _original, int _flag) {
        FileBlock file_ = _original.getPreviousFilesBodies().getVal(_fileName);
        ResultExpressionOperationNode c_ = container(_caret, file_);
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentBlock(c_.block);
        a_.setCurrentPkg(a_.getDefaultPkg());
        setAnnot(c_, a_);
        if (a_.isAnnotAnalysis()) {
            return annotationCase(c_, a_);
        }
        accessRoot(_flag, a_, c_.block);
        return notAnnot(a_, c_.block);
    }

    private static void accessRoot(int _flag, AnalyzedPageEl _a, AbsBk _bl) {
        if (_bl instanceof RootBlock) {
            if (_flag == BreakPoint.BPC_INSTANCE) {
                _a.setAccessStaticContext(MethodAccessKind.INSTANCE);
            } else {
                _a.setAccessStaticContext(MethodAccessKind.STATIC);
            }
        }
    }

    public static AnalyzedPageEl prepare(StdMethodPointBlockPair _instance, AnalyzedPageEl _original) {
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentPkg(a_.getDefaultPkg());
        StandardType std_ = _instance.getSm().getType();
        StandardNamedFunction id_ = _instance.getSm().getId();
        if (id_ instanceof StandardMethod && ((StandardMethod)id_).getModifier() != MethodModifier.STATIC) {
            a_.setAccessStaticContext(MethodAccessKind.INSTANCE);
        } else {
            a_.setAccessStaticContext(MethodAccessKind.STATIC);
        }
        a_.setOriginalGlobalType(new AnaFormattedRootBlock((RootBlock)null,std_.getFullName()));
        ClassesUtil.prepare(id_, a_);
        return a_;
    }

    public static AnalyzedPageEl prepare(OperNatPointBlockPair _instance, AnalyzedPageEl _original) {
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentPkg(a_.getDefaultPkg());
        a_.setAccessStaticContext(MethodAccessKind.STATIC);
        a_.setOriginalGlobalType(new AnaFormattedRootBlock((RootBlock)null,_original.getAliasObject()));
        String p_ = a_.getKeyWords().getKeyWordValue();
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(_instance.getOn().getFirst());
        lv_.setConstType(ConstType.PARAM);
        lv_.setFinalVariable(true);
        a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(p_, lv_));
        if (!_instance.getOn().getSecond().isEmpty()) {
            AnaLocalVariable slv_ = new AnaLocalVariable();
            slv_.setClassName(_instance.getOn().getSecond());
            slv_.setConstType(ConstType.PARAM);
            slv_.setFinalVariable(true);
            a_.getCache().getLocalVariables().add(new AnaNamedLocalVariable(p_, slv_));
        }
        return a_;
    }
    public static AnalyzedPageEl prepare(MethodPointBlockPair _instance, AnalyzedPageEl _original) {
        AnalyzedPageEl a_ = AnalyzedPageEl.copy(_original);
        a_.setDynamic(true);
        a_.setCurrentBlock(_instance.getMp().getId());
        a_.setCurrentPkg(a_.getDefaultPkg());
        return notAnnot(a_, _instance.getMp().getId());
    }

    private static AnalyzedPageEl notAnnot(AnalyzedPageEl _a, AbsBk _block) {
        MemberCallingsBlock m_ = AbsBk.getOuterFuntionInType(_block);
        if (AbsBk.isAnonBlock(m_)) {
            _a.setupFctChars((NamedCalledFunctionBlock) m_);
            _a.getCache().getLocalVariables().addAllElts(((NamedCalledFunctionBlock) m_).getCache().getLocalVariables());
            _a.getCache().getLoopVariables().addAllElts(((NamedCalledFunctionBlock) m_).getCache().getLoopVariables());
        } else if (m_ instanceof SwitchMethodBlock) {
            _a.setupFctChars((SwitchMethodBlock) m_);
            _a.getCache().getLocalVariables().addAllElts(((SwitchMethodBlock) m_).getCache().getLocalVariables());
            _a.getCache().getLoopVariables().addAllElts(((SwitchMethodBlock) m_).getCache().getLoopVariables());
        } else if (m_ instanceof OperatorBlock) {
            _a.setImporting((OperatorBlock)m_);
            _a.setImportingTypes((OperatorBlock)m_);
            _a.setCurrentPkg(_a.getDefaultPkg());
        } else {
            RootBlock par_ = parent(m_);
            if (par_ != null) {
                _a.setImporting(par_);
                _a.setCurrentPkg(par_.getPackageName());
            }
        }
        if (m_ != null) {
            _a.setCurrentFct(m_);
            _a.setAccessStaticContext(m_.getStaticContext());
        }
        feedVars(_a, _block);
        if (m_ != null) {
            ClassesUtil.prepare(m_, _a);
        }
        typeOrField(_a, _block);
        return _a;
    }

    private static void typeOrField(AnalyzedPageEl _a, AbsBk _bl) {
        if (_bl instanceof InfoBlock) {
            field(_a, ((InfoBlock) _bl).getDeclaringType(), ((InfoBlock) _bl).isStaticField());
        } else if (_bl instanceof RootBlock) {
            _a.setImporting((AccessedBlock) _bl);
            _a.setCurrentPkg(((RootBlock) _bl).getPackageName());
            _a.setCurrentFct(null);
            _a.getMappingLocal().addAllEntries(((RootBlock) _bl).getRefMappings());
        }
    }

    private static void field(AnalyzedPageEl _a, RootBlock _decl, boolean _stat) {
        _a.setImporting(_decl);
        if (_stat) {
            _a.setAccessStaticContext(MethodAccessKind.STATIC);
        } else {
            _a.setAccessStaticContext(MethodAccessKind.INSTANCE);
        }
        _a.setCurrentPkg(_decl.getPackageName());
        _a.setCurrentFct(null);
        _a.getMappingLocal().addAllEntries(_decl.getRefMappings());
    }

    private static void setAnnot(ResultExpressionOperationNode _c, AnalyzedPageEl _a) {
        if (_c.resultExpression != null) {
            _a.setAnnotAnalysis(AbsBk.isAnnotBlock(_c.block)|| _c.resultExpression.getAnalyzedString().trim().startsWith("@"));
        }
    }

    private static AnalyzedPageEl annotationCase(ResultExpressionOperationNode _c, AnalyzedPageEl _a) {
        AbsBk m_ = _c.block;
        if (AbsBk.isAnonBlock(m_)) {
            _a.setupFctChars((NamedCalledFunctionBlock) m_);
            _a.getMappingLocal().addAllEntries(((NamedCalledFunctionBlock) m_).getRefMappings());
        } else if (m_ instanceof SwitchMethodBlock) {
            _a.setupFctChars((SwitchMethodBlock) m_);
            _a.getMappingLocal().addAllEntries(((SwitchMethodBlock) m_).getRefMappings());
        } else if (m_ instanceof OperatorBlock) {
            _a.setImporting((OperatorBlock)m_);
            _a.setImportingTypes((OperatorBlock)m_);
            _a.setCurrentPkg(_a.getDefaultPkg());
        } else {
            RootBlock par_;
            if (m_ instanceof NamedFunctionBlock) {
                par_ = parent(m_);
            } else {
                par_ = null;
            }
            if (par_ != null) {
                _a.setImporting(par_);
                _a.setCurrentPkg(par_.getPackageName());
                _a.getMappingLocal().addAllEntries(par_.getRefMappings());
            } else {
                accessRoot(BreakPoint.BPC_STATIC, _a, m_);
                typeOrField(_a, m_);
            }
        }
        _a.setCurrentFct(null);
        _a.setAccessStaticContext(MethodAccessKind.STATIC);
        return _a;
    }

    private static void feedVars(AnalyzedPageEl _a, AbsBk _found) {
        AbsBk curr_ = _found;
        while (curr_ != null) {
            if (curr_ instanceof MemberCallingsBlock) {
                break;
            }
            if (curr_ instanceof RootBlock) {
                curr_ = null;
            } else {
                localVarsLine(_a,curr_);
                loopVars(_a, curr_, _found);
                curr_ = curr_.getParent();
            }
        }
    }

    private static void loopVars(AnalyzedPageEl _a, AbsBk _curr, AbsBk _found) {
        if (_found != _curr) {
            if (_curr instanceof ForIterativeLoop) {
                AnaLocalVariable vari_ = new AnaLocalVariable();
                vari_.setConstType(ConstType.FIX_VAR);
                vari_.setFinalVariable(true);
                vari_.setClassName(((ForIterativeLoop) _curr).getImportedClassName());
                _a.getInfosVars().addEntry(((ForIterativeLoop) _curr).getVariableName(),vari_);
                AnaLoopVariable loop_ = new AnaLoopVariable();
                loop_.setIndexClassName(((ForIterativeLoop) _curr).getImportedClassIndexName());
                _a.getLoopsVars().addEntry(((ForIterativeLoop) _curr).getVariableName(), loop_);
            }
            if (_curr instanceof ForEachLoop) {
                AnaLocalVariable vari_ = new AnaLocalVariable();
                if (((ForEachLoop) _curr).isRefVariable()) {
                    vari_.setConstType(ConstType.REF_LOC_VAR);
                } else {
                    vari_.setConstType(ConstType.FIX_VAR);
                    vari_.setFinalVariable(true);
                }
                vari_.setClassName(((ForEachLoop) _curr).getImportedClassName());
                _a.getInfosVars().addEntry(((ForEachLoop) _curr).getVariableName(),vari_);
                AnaLoopVariable loop_ = new AnaLoopVariable();
                loop_.setIndexClassName(((ForEachLoop) _curr).getImportedClassIndexName());
                _a.getLoopsVars().addEntry(((ForEachLoop) _curr).getVariableName(), loop_);
            }
            if (_curr instanceof ForEachTable) {
                AnaLocalVariable variFirst_ = new AnaLocalVariable();
                variFirst_.setConstType(ConstType.FIX_VAR);
                variFirst_.setFinalVariable(true);
                variFirst_.setClassName(((ForEachTable)_curr).getImportedClassNameFirst());
                _a.getInfosVars().addEntry(((ForEachTable)_curr).getVariableNameFirst(),variFirst_);
                AnaLoopVariable loopFirst_ = new AnaLoopVariable();
                loopFirst_.setIndexClassName(((ForEachTable)_curr).getImportedClassIndexName());
                _a.getLoopsVars().addEntry(((ForEachTable)_curr).getVariableNameFirst(), loopFirst_);
                AnaLocalVariable variSecond_ = new AnaLocalVariable();
                variSecond_.setConstType(ConstType.FIX_VAR);
                variSecond_.setFinalVariable(true);
                variSecond_.setClassName(((ForEachTable)_curr).getImportedClassNameSecond());
                _a.getInfosVars().addEntry(((ForEachTable)_curr).getVariableNameSecond(),variSecond_);
                AnaLoopVariable loopSecond_ = new AnaLoopVariable();
                loopSecond_.setIndexClassName(((ForEachTable)_curr).getImportedClassIndexName());
                _a.getLoopsVars().addEntry(((ForEachTable)_curr).getVariableNameSecond(), loopSecond_);
            }
        }
        chgVars(_a, _curr);
        caseCatch(_a, _curr);
    }

    private static void chgVars(AnalyzedPageEl _a, AbsBk _curr) {
        if (_curr instanceof ForMutableIterativeLoop) {
            String imp_ = ((ForMutableIterativeLoop) _curr).getImportedClassName();
            String ind_ = ((ForMutableIterativeLoop) _curr).getImportedClassIndexName();
            for (String v: ((ForMutableIterativeLoop) _curr).getVariableNames()) {
                AnaLocalVariable vari_ = new AnaLocalVariable();
                if (((ForMutableIterativeLoop) _curr).isRefVariable()) {
                    vari_.setConstType(ConstType.REF_LOC_VAR);
                } else {
                    vari_.setConstType(ConstType.LOC_VAR);
                }
                vari_.setFinalVariable(((ForMutableIterativeLoop) _curr).isFinalVariable());
                vari_.setClassName(imp_);
                _a.getInfosVars().addEntry(v,vari_);
                AnaLoopVariable loop_ = new AnaLoopVariable();
                loop_.setIndexClassName(ind_);
                _a.getLoopsVars().addEntry(v, loop_);
            }
        }
    }

    private static void caseCatch(AnalyzedPageEl _a, AbsBk _curr) {
        if (_curr instanceof WithFilterContent) {
            WithFilterContent w_ = (WithFilterContent) _curr;
            if (!w_.getFilterContent().getVariableName().isEmpty()) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(w_.getFilterContent().getImportedType());
                lv_.setConstType(ConstType.FIX_VAR);
                lv_.setFinalVariable(true);
                _a.getInfosVars().addEntry(w_.getFilterContent().getVariableName(), lv_);
            }
        }
        if (_curr instanceof DefaultCondition) {
            DefaultCondition w_ = (DefaultCondition) _curr;
            if (!w_.getVariableName().isEmpty()) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(w_.getInstanceTest());
                lv_.setConstType(ConstType.FIX_VAR);
                lv_.setFinalVariable(true);
                _a.getInfosVars().addEntry(w_.getVariableName(), lv_);
            }
        }
    }

    private static void localVarsLine(AnalyzedPageEl _a,AbsBk _curr) {
        AbsBk prev_ = _curr;
        while (prev_ != null) {
            if (prev_ instanceof DeclareVariable) {
                String imp_ = ((DeclareVariable) prev_).getImportedClassName();
                for (String v: ((DeclareVariable) prev_).getVariableNames()) {
                    AnaLocalVariable vari_ = new AnaLocalVariable();
                    if (((DeclareVariable) prev_).isRefVariable()) {
                        vari_.setConstType(ConstType.REF_LOC_VAR);
                    } else {
                        vari_.setConstType(ConstType.LOC_VAR);
                    }
                    vari_.setFinalVariable(((DeclareVariable) prev_).isFinalVariable());
                    vari_.setClassName(imp_);
                    _a.getInfosVars().addEntry(v,vari_);
                }
            }
            prev_ = prev_.getPreviousSibling();
        }
    }

    public static String beginPartFct(int _caret, FileBlock _file, DisplayedStrings _page) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        MemberCallingsBlock m_ = AbsBk.getOuterFuntionInType(c_.block);
        if (m_ == null) {
            return "";
        }
        return clName(_page, m_);
    }

    public static String clName(DisplayedStrings _page, MemberCallingsBlock _m) {
        RootBlock p_ = parent(_m);
        String cl_;
        if (p_ != null) {
            cl_ = p_.getFullName();
        } else {
            AccessedBlock acc_ = MemberCallingsBlock.accessed(_m);
            if (acc_ instanceof RootBlock) {
                cl_ = ((RootBlock)acc_).getFullName();
            } else if (acc_ instanceof OperatorBlock){
                cl_ = ((OperatorBlock)acc_).getSignature(_page);
            } else {
                cl_ = "";
            }
        }
        if (cl_.isEmpty()) {
            return _m.getSignature(_page);
        }
        return cl_+"."+_m.getSignature(_page);
    }

    public static RootBlock parent(AbsBk _m) {
        BracedBlock b_;
        if (_m == null) {
            b_ = null;
        } else {
            b_ = _m.getParent();
        }
        if (b_ instanceof RootBlock) {
            return (RootBlock) b_;
        }
        return null;
    }
    public static String beginPartFctKey(int _caret, FileBlock _file) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        MemberCallingsBlock m_ = AbsBk.getOuterFuntionInType(c_.block);
        if (m_ == null) {
            return "";
        }
        return MemberCallingsBlock.clName(m_);
    }
    public static int beginPartExp(int _caret, FileBlock _file) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        return c_.beginOff;
    }
    public static int beginPart(int _caret, FileBlock _file) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        if (withoutExp(c_.block)) {
            return c_.block.getOffset();
        }
        if (!ReturnMethod.isImplicitReturn(c_.block) && c_.block instanceof ReturnMethod && ((ReturnMethod)c_.block).isEmpty()) {
            return ((ReturnMethod)c_.block).getExpressionOffset();
        }
        if (c_.resultExpression != null) {
            if (!c_.resultExpression.getAnalyzedString().trim().startsWith("@") && c_.block instanceof InnerTypeOrElement) {
                return ((InnerTypeOrElement)c_.block).getFieldNameOffset();
            }
            return c_.resultExpression.getSumOffset();
        }
        if (c_.block instanceof InnerTypeOrElement) {
            return ((InnerTypeOrElement)c_.block).getFieldNameOffset();
        }
        if (c_.block instanceof RootBlock) {
            return ((RootBlock)c_.block).getIdRowCol();
        }
        return c_.outExp(_caret);
    }

    public static boolean withoutExp(AbsBk _bl) {
        return _bl instanceof TryEval || _bl instanceof LabelAbruptBlock || _bl instanceof FinallyEval || _bl instanceof ElseCondition || _bl instanceof DoBlock || _bl instanceof DefaultCondition || _bl instanceof UnclassedBracedBlock;
    }

    public static boolean enabledTypeBp(int _caret, FileBlock _file) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        return c_.resultExpression == null && c_.block instanceof RootBlock;
    }

    public static MemberCallingsBlock keyMethodBp(int _caret, FileBlock _file) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        if (c_.resultExpression == null && c_.block instanceof MemberCallingsBlock) {
            return (MemberCallingsBlock) c_.block;
        }
        return null;
    }
    private int outExp(int _caret) {
        if (block instanceof ForIterativeLoop) {
            return forIterativeLoop(_caret);
        }
        if (block instanceof ForEachLoop) {
            return forEachIterable(_caret);
        }
        if (block instanceof ForEachTable) {
            return forEachTable(_caret);
        }
        if (block instanceof WithFilterContent) {
            return withFilterContent(_caret);
        }
        if (block instanceof MemberCallingsBlock) {
            return block.getOffset();
        }
        if (block instanceof Line) {
            return ((Line)block).getRes().end();
        }
        if (block instanceof ReturnMethod) {
            return ((ReturnMethod)block).getRes().end();
        }
        if (block instanceof Throwing) {
            return ((Throwing)block).getRes().end();
        }
        if (block instanceof ForMutableIterativeLoop) {
            return forMutableIterativeLoop(_caret);
        }
        if (block instanceof ConditionBlock) {
            return ((ConditionBlock)block).getRes().end();
        }
        if (block instanceof SwitchBlock) {
            return ((SwitchBlock)block).getRes().end();
        }
        return -1;
    }

    private int withFilterContent(int _caret) {
        int e_ = ((WithFilterContent) block).getFilterContent().getResCondition().end();
        if (!((WithFilterContent) block).getFilterContent().getResCondition().getAnalyzedString().isEmpty()&& _caret >= e_) {
            return e_;
        }
        if (!((WithFilterContent)block).getFilterContent().getDeclaringType().isEmpty()){
            return ((WithFilterContent) block).getFilterContent().getValueOffset();
        }
        return block.getOffset();
    }

    private int forMutableIterativeLoop(int _caret) {
        int vf_ = ((ForMutableIterativeLoop) block).getFirst()+1;
        int vs_ = ((ForMutableIterativeLoop) block).getSecond()+1;
        int vl_ = ((ForMutableIterativeLoop) block).getLast()+1;
        if (inRange(((ForMutableIterativeLoop) block).getResInit().end(), _caret,vf_)) {
            return ((ForMutableIterativeLoop) block).getResInit().end();
        }
        if (inRange(((ForMutableIterativeLoop) block).getResExp().end(), _caret,vs_)) {
            return ((ForMutableIterativeLoop) block).getResExp().end();
        }
        if (inRange(((ForMutableIterativeLoop) block).getResStep().end(), _caret,vl_)) {
            return ((ForMutableIterativeLoop) block).getResStep().end();
        }
        return -1;
    }

    private int forIterativeLoop(int _caret) {
        int vf_ = ((ForIterativeLoop) block).getFirst()+1;
        int vs_ = ((ForIterativeLoop) block).getSecond()+1;
        int vl_ = ((ForIterativeLoop) block).getLast()+1;
        if (inRange(((ForIterativeLoop) block).getResInit().end(), _caret,vf_)) {
            return ((ForIterativeLoop) block).getResInit().end();
        }
        if (inRange(((ForIterativeLoop) block).getResExp().end(), _caret,vs_)) {
            return ((ForIterativeLoop) block).getResExp().end();
        }
        if (inRange(((ForIterativeLoop) block).getResStep().end(), _caret,vl_)) {
            return ((ForIterativeLoop) block).getResStep().end();
        }
        return ((ForIterativeLoop) block).getVariableNameOffset();
    }

    private int forEachTable(int _caret) {
        int s_ = ((ForEachTable) block).getSepOffset();
        int sn_ = ((ForEachTable) block).getSepNext();
        int vf_ = ((ForEachTable) block).getVariableNameOffsetFirst();
        int nf_ = ((ForEachTable) block).getVariableNameFirst().length();
        int vs_ = ((ForEachTable) block).getVariableNameOffsetSecond();
        int ns_ = ((ForEachTable) block).getVariableNameSecond().length();
        if (inRange(sn_, _caret,sn_+1)) {
            return sn_;
        }
        if (inRange(s_, _caret,s_+1)) {
            return s_;
        }
        if (inRange(vf_, _caret,vf_+nf_)) {
            return vf_;
        }
        if (inRange(vs_, _caret,vs_+ns_)) {
            return vs_;
        }
        int e_ = ((ForEachTable) block).getRes().end();
        if (_caret >= e_) {
            return e_;
        }
        return block.getOffset();
    }

    private int forEachIterable(int _caret) {
        int s_ = ((ForEachLoop) block).getSepOffset();
        int v_ = ((ForEachLoop) block).getVariableNameOffset();
        int n_ = ((ForEachLoop) block).getVariableName().length();
        if (inRange(s_, _caret,s_+1)) {
            return s_;
        }
        if (inRange(v_, _caret,v_+n_)) {
            return v_;
        }
        int e_ = ((ForEachLoop) block).getRes().end();
        if (_caret >= e_) {
            return e_;
        }
        return block.getOffset();
    }

    public static CustList<RowSrcLocation> locationsDisplay(AnalyzedPageEl _page, String _fileName, int _caret) {
        return export(_page,locations(_page, _fileName, _caret));
    }
    public static CustList<RowSrcLocation> export(AnalyzedPageEl _page, CustList<SrcFileLocation> _l) {
        CustList<RowSrcLocation> d_ = new CustList<RowSrcLocation>();
        for (SrcFileLocation s: _l) {
            d_.add(s.build(_page.getDisplayedStrings()));
        }
        return d_;
    }
    public static CustList<SrcFileLocation> locations(AnalyzedPageEl _page, String _fileName, int _caret) {
        FileBlock file_ = _page.getPreviousFilesBodies().getVal(_fileName);
        ResultExpressionOperationNode res_ = container(_caret, file_);
        CustList<SrcFileLocation> machines_ = coeur(file_, _caret, res_);
        if (!machines_.isEmpty()) {
            return machines_;
        }
        AbsBk bl_ = res_.getBlock();
        if (bl_ instanceof InnerTypeOrElement) {
            return fetch(_caret,((InnerTypeOrElement)bl_).getElementContent().getPartOffsets());
        }
        if (bl_ instanceof BreakableBlock&& inLabelRange(_caret, bl_)) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationLabel(((BreakableBlock)bl_).getRealLabelInfo().getInfo(),file_, ((BreakableBlock)bl_).getRealLabelInfo().getOffset()));
            return ls_;
        }
        OffsetStringInfo o_ = tryDecl(bl_, _caret);
        if (o_ != null) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationVariable(-1,o_.getInfo(),file_,o_.getOffset()));
            return ls_;
        }
        CustList<SrcFileLocation> declTypes_ = declared(bl_, _caret);
        if (!declTypes_.isEmpty()) {
            return declTypes_;
        }
        if (bl_ instanceof BreakBlock&&((BreakBlock)bl_).getLabelOffsetRef()>-1) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationLabel(((BreakBlock)bl_).getLabel(),file_, ((BreakBlock)bl_).getLabelOffsetRef()));
            return ls_;
        }
        if (bl_ instanceof ContinueBlock&&((ContinueBlock)bl_).getLabelOffsetRef()>-1) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(new SrcFileLocationLabel(((ContinueBlock)bl_).getLabel(),file_, ((ContinueBlock)bl_).getLabelOffsetRef()));
            return ls_;
        }
        return def(_caret, bl_);
    }
    private static CustList<SrcFileLocation> declared(AbsBk _bl,int _caret) {
        if (_bl instanceof DeclareVariable) {
            CustList<SrcFileLocation> ls_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((DeclareVariable) _bl).getPartOffsets(), _caret);
            possibleInfer(_caret,((DeclareVariable) _bl).getClassName().length(),((DeclareVariable) _bl).getClassNameOffset(),_bl,((DeclareVariable) _bl).getImportedClassName(),ls_);
            return ls_;
        }
        if (_bl instanceof ForMutableIterativeLoop) {
            CustList<SrcFileLocation> ls_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForMutableIterativeLoop) _bl).getPartOffsets(), _caret);
            possibleInfer(_caret,((ForMutableIterativeLoop) _bl).getClassName().length(),((ForMutableIterativeLoop) _bl).getClassNameOffset(),_bl,((ForMutableIterativeLoop) _bl).getImportedClassName(),ls_);
            return ls_;
        }
        if (_bl instanceof ForEachLoop) {
            CustList<SrcFileLocation> ls_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachLoop) _bl).getPartOffsets(), _caret);
            possibleInfer(_caret,((ForEachLoop) _bl).getClassName().length(),((ForEachLoop) _bl).getClassNameOffset(),_bl,((ForEachLoop) _bl).getImportedClassName(),ls_);
            return ls_;
        }
        if (_bl instanceof ForEachTable) {
            CustList<SrcFileLocation> ls_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachTable) _bl).getPartOffsetsFirst(), _caret);
            possibleInfer(_caret,((ForEachTable) _bl).getClassNameFirst().length(),((ForEachTable) _bl).getClassNameOffsetFirst(),_bl,((ForEachTable) _bl).getImportedClassNameFirst(),ls_);
            CustList<SrcFileLocation> sec_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((ForEachTable) _bl).getPartOffsetsSecond(), _caret);
            possibleInfer(_caret,((ForEachTable) _bl).getClassNameSecond().length(),((ForEachTable) _bl).getClassNameOffsetSecond(),_bl,((ForEachTable) _bl).getImportedClassNameSecond(),sec_);
            ls_.addAllElts(sec_);
            return ls_;
        }
        if (_bl instanceof FieldBlock) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((FieldBlock) _bl).getTypePartOffsets(),_caret);
        }
        if (_bl instanceof WithFilterContent) {
            CustList<SrcFileLocation> ls_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((WithFilterContent) _bl).getFilterContent().getPartOffsets(), _caret);
            possibleInfer(_caret,((WithFilterContent) _bl).getFilterContent().getDeclaringType().length(),((WithFilterContent) _bl).getFilterContent().getValueOffset(),_bl,((WithFilterContent) _bl).getFilterContent().getImportedType(),ls_);
            return ls_;
        }
        return new CustList<SrcFileLocation>();
    }
    private static void possibleInfer(int _caret, int _length,int _offset, AbsBk _bl, String _inferred,CustList<SrcFileLocation> _ls) {
        if (_ls.isEmpty()&&inRange(_offset,_caret,_offset+_length)) {
            _ls.add(new SrcFileLocationInferredType(_offset, _inferred, _bl.getFile()));
        }
    }

    private static boolean inLabelRange(int _caret, AbsBk _bl) {
        return inRange(_bl.getBegin(), _caret, _bl.getBegin() + _bl.getLengthHeader()) || inRange(((BreakableBlock) _bl).getRealLabelInfo().getOffset(), _caret, ((BreakableBlock) _bl).getRealLabelInfo().getOffset() + ((BreakableBlock) _bl).getRealLabelInfo().getInfo().length());
    }

    private static CustList<SrcFileLocation> def(int _caret, AbsBk _bl) {
        CustList<SrcFileLocation> t_ = otherTypes(_bl, _caret);
        if (!t_.isEmpty()) {
            return t_;
        }
        if (_bl instanceof NamedFunctionBlock) {
            return defName(_caret, (NamedFunctionBlock) _bl);
        }
        if (_bl instanceof InternOverrideBlock) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            for (PartOffsetsClassMethodIdList l:((InternOverrideBlock)_bl).getAllPartsTypes()) {
                for (PartOffsetsClassMethodId p:l.getOverrides()) {
                    if (inRange(p.getBegin(), _caret,p.getBegin()+p.getLength())) {
                        fctPub(p.getFct(),ls_);
                    }
                }
            }
            return ls_;
        }
        return new CustList<SrcFileLocation>();
    }

    private static CustList<SrcFileLocation> defName(int _caret, NamedFunctionBlock _bl) {
        CustList<SrcFileLocation> p_ = new CustList<SrcFileLocation>();
        Ints offs_ = _bl.getParametersNamesOffset();
        StringList names_ = _bl.getParametersNames();
        int s_ = names_.size();
        for (int i = 0; i < s_; i++) {
            if (inRange(offs_.get(i), _caret,offs_.get(i)+names_.get(i).length())) {
                p_.add(new SrcFileLocationVariable(-1,names_.get(i), _bl.getFile(),offs_.get(i)));
            }
        }
        if (_bl instanceof NamedCalledFunctionBlock) {
            for (PartOffsetsClassMethodId p:((NamedCalledFunctionBlock) _bl).getAllInternTypesParts()) {
                if (inRange(p.getBegin(), _caret,p.getBegin()+p.getLength())) {
                    fctPub(p.getFct(),p_);
                }
            }
        }
        if (inRange(_bl.getNameOffset(),_caret,_bl.getNameOffset()+_bl.getRealLength())) {
            p_.add(new SrcFileLocationMethod(_bl.getParent(),_bl));
        }
        return p_;
    }

    private static CustList<SrcFileLocation> otherTypes(AbsBk _bl, int _caret) {
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        typesDecl(_bl, _caret, ls_);
        if (_bl instanceof NamedFunctionBlock) {
            ls_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((NamedFunctionBlock)_bl).getPartOffsetsReturn(), _caret));
            ls_.addAllElts(fetchAna(_caret,((NamedFunctionBlock)_bl).getPartOffsetsParams()));
            if (_bl instanceof NamedCalledFunctionBlock) {
                ls_.addAllElts(LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((NamedCalledFunctionBlock)_bl).getPartOffsetsReturnSetter(), _caret));
                for (PartOffsetsClassMethodId p:((NamedCalledFunctionBlock)_bl).getAllInternTypesParts()) {
                    ls_.addAllElts(fetch(_caret,p.getTypes()));
                    ls_.addAllElts(fetch(_caret,p.getSuperTypes()));
                }
            }
        }
        if (_bl instanceof InternOverrideBlock) {
            for (PartOffsetsClassMethodIdList l:((InternOverrideBlock)_bl).getAllPartsTypes()) {
                ls_.addAllElts(fetch(_caret,l.getTypes()));
                for (PartOffsetsClassMethodId p:l.getOverrides()) {
                    ls_.addAllElts(fetch(_caret,p.getTypes()));
                    ls_.addAllElts(fetch(_caret,p.getSuperTypes()));
                }
            }
        }
        return ls_;
    }

    private static void typesDecl(AbsBk _bl, int _caret, CustList<SrcFileLocation> _ls) {
        if (_bl instanceof RootBlock) {
            if (inRange(((RootBlock) _bl).getIdRowCol(), _caret,((RootBlock) _bl).getIdRowCol()+((RootBlock) _bl).getNameLength())) {
                _ls.add(new SrcFileLocationType(((RootBlock) _bl).getIdRowCol(),(RootBlock) _bl));
            }
            _ls.addAllElts(fetch(_caret,((RootBlock) _bl).getPartsStaticInitInterfacesOffset()));
            _ls.addAllElts(fetch(_caret,((RootBlock) _bl).getPartsInstInitInterfacesOffset()));
            if (!(_bl instanceof AnonymousTypeBlock)) {
                for (TypeVar t: ((RootBlock) _bl).getParamTypes()) {
                    if (inRange(t.getOffset(), _caret,t.getOffset()+t.getLength())) {
                        _ls.add(new SrcFileLocationTypeVar(t.getOffset(),t.getName(),t.getOffset(), _bl.getFile()));
                    }
                    _ls.addAllElts(fetchAna(_caret,t.getResults()));
                }
            }
            _ls.addAllElts(fetchAna(_caret,((RootBlock) _bl).getResults()));
        }
    }

    private static OffsetStringInfo tryDecl(AbsBk _bl, int _caret) {
        if (_bl instanceof ForIterativeLoop&&inRange(((ForIterativeLoop)_bl).getVariableNameOffset(),_caret,((ForIterativeLoop)_bl).getVariableNameOffset()+((ForIterativeLoop)_bl).getVariableName().length())) {
            return new OffsetStringInfo(((ForIterativeLoop)_bl).getVariableNameOffset(), ((ForIterativeLoop)_bl).getVariableName());
        }
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

    private static CustList<SrcFileLocation> coeur(FileBlock _f, int _caret, ResultExpressionOperationNode _res) {
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
        return trefle(_f, _caret, _res);
    }

    private static CustList<SrcFileLocation> trefle(FileBlock _f, int _caret, ResultExpressionOperationNode _res) {
        CustList<SrcFileLocation> ls_ = carreau(_f, _caret, _res);
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

    private static CustList<SrcFileLocation> carreau(FileBlock _f, int _caret, ResultExpressionOperationNode _res) {
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
            return curr(begin(_res.resultExpression, foundOp_),_caret, (AbstractInvokingConstructor) foundOp_);
        }
        if (foundOp_ instanceof DimensionArrayInstancing) {
            return types(((DimensionArrayInstancing)foundOp_).getPartOffsets(),_caret);
        }
        if (foundOp_ instanceof ElementArrayInstancing) {
            return types(((ElementArrayInstancing)foundOp_).getPartOffsets(),_caret);
        }
        if (foundOp_ instanceof CallDynMethodOperation) {
            return refs((CallDynMethodOperation)foundOp_);
        }
        return pique(_f, _caret, _res);
    }

    private static CustList<SrcFileLocation> refs(CallDynMethodOperation _foundOp) {
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        callStd(_foundOp.getStdMethod(), _foundOp.getStdType(), ls_);
        if (!_foundOp.getRefer().isEmpty()) {
            ls_.add(new SrcFileLocationCall(_foundOp.getRefer()));
        }
        return ls_;
    }

    private static CustList<SrcFileLocation> curr(int _found,int _caret, AbstractInvokingConstructor _foundOp) {
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        ls_.addAllElts(fetch(_caret, _foundOp.getPartOffsets()));
        if (!ls_.isEmpty()) {
            return ls_;
        }
        AnaTypeFct constructor_ = _foundOp.getConstructor();
        fctPub(constructor_, ls_);
        timbre(_found,root(constructor_),ls_);
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
            CustList<SrcFileLocation> all_ = LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((SwitchOperation) _foundOp).getPartOffsets(), _caret);
            if (!all_.isEmpty()) {
                return all_;
            }
            all_.add(new SrcFileLocationMethod(null,((SwitchOperation)_foundOp).getSwitchMethod()));
            return all_;
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
            SrcFileLocationField.addField(cf_,r_, i_, null,ls_);
            return ls_;
        }
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        feedFiltersNamedList(_foundOp,ls_);
        return ls_;
    }

    static void feedFiltersNamedList(NamedArgumentOperation _namedArg, CustList<SrcFileLocation> _list) {
        CustList<NamedFunctionBlock> customMethods_ = _namedArg.getCustomMethod();
        Ints offs_ = new Ints();
        CustList<FileBlock> fs_ = new CustList<FileBlock>();
        if (!customMethods_.isEmpty()) {
            NamedFunctionBlock c_ = customMethods_.first();
            offs_.add(_namedArg.getRef());
            fs_.add(c_.getFile());
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

    private static CustList<SrcFileLocation> pique(FileBlock _f, int _caret, ResultExpressionOperationNode _res) {
        OperationNode foundOp_ = _res.getFound();
        if (foundOp_ instanceof DefaultValueOperation) {
            return LocationsPartTypeUtil.processAnalyzeConstraintsRepParts(((DefaultValueOperation)foundOp_).getPartOffsets(),_caret);
        }
        if (okFinalVar(foundOp_)) {
            AnaVariableContent v_ = ((FinalVariableOperation) foundOp_).getVariableContent();
            CustList<SrcFileLocation> l_ = new CustList<SrcFileLocation>();
            l_.add(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),_f,((FinalVariableOperation)foundOp_).getRef()));
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
            l_.add(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),_f,((VariableOperation)foundOp_).getRef()));
            return l_;
        }
        if (foundOp_ instanceof VariableOperationUse) {
            AnaVariableContent v_ = ((VariableOperationUse) foundOp_).getVariableContent();
            CustList<SrcFileLocation> l_ = new CustList<SrcFileLocation>();
            l_.add(new SrcFileLocationVariable(v_.getDeep(),v_.getVariableName(),_f,((VariableOperationUse)foundOp_).getRef()));
            return l_;
        }
        if (foundOp_ instanceof LambdaOperation) {
            return lambda(_res, (LambdaOperation) foundOp_, _caret);
        }
        if (foundOp_ instanceof AnonymousLambdaOperation) {
            return def(_caret,((AnonymousLambdaOperation)foundOp_).getBlock());
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
                SrcFileLocationField.addField(new ClassField(naFi_.getIdClass(),name_),r_, ref_,null,ls_);
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
        CustList<SrcFileLocation> def_ = new CustList<SrcFileLocation>();
        ctStd(_lda.getStandardConstructor(), _lda.getStandardType(), def_);
        callStd(_lda.getStandardMethod(), _lda.getStandardType(), def_);
        fctPub(function_, def_);
        timbre(_lda.getMemberOffset(),root(_lda),def_);
        if (!def_.isEmpty()) {
            return def_;
        }
        ClassField fieldId_ = _lda.getFieldId();
        if (fieldId_ != null) {
            RootBlock fieldType_ = _lda.getLambdaCommonContent().getFoundFormatted().getRootBlock();
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            SrcFileLocationField.addField(fieldId_,fieldType_, _lda.getValueOffset(), _lda.getCstFieldInfo(),ls_);
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
        int i_ = ((SettableAbstractFieldOperation) _foundOp).getSettableFieldContent().getValueOffset();
        RootBlock r_ = ((SettableAbstractFieldOperation) _foundOp).getFieldType();
        ClassField cf_ = ((SettableAbstractFieldOperation) _foundOp).getFieldIdReadOnly();
        CstFieldInfo c_ = ((SettableAbstractFieldOperation) _foundOp).getSettableFieldContent().getCstFieldInfo();
        SrcFileLocationField.addField(cf_,r_, i_,c_,ls_);
        return ls_;
    }

    private static CustList<SrcFileLocation> young(int _caret, ResultExpressionOperationNode _res, AbstractInstancingOperation _op) {
        int offsetNew_ = _op.getOffsetFct();
        int beginInst_ = offsetNew_ + _res.begin(_op);
        int lengthInst_ = StringExpUtil.getDollarWordSeparators(_op.getMethodName()).get(1).length();
        if (_op instanceof StandardInstancingOperation&&((StandardInstancingOperation) _op).getInnerElt() != null||inRange(beginInst_, _caret,beginInst_+lengthInst_)) {
            return now(beginInst_,_op);
        }
        ResolvedInstance r_ = _op.getResolvedInstance();
        CustList<SrcFileLocation> t_ = types(r_,_caret);
        if (!t_.isEmpty()) {
            return t_;
        }
        if (_op instanceof StandardInstancingOperation){
            return fetch(_caret, ((StandardInstancingOperation) _op).getPartsInstInitInterfaces());
        }
        return now(beginInst_,_op);
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

    private static CustList<SrcFileLocation> now(int _found,AbstractInstancingOperation _maintenant) {
        StandardConstructor std_ = _maintenant.getInstancingCommonContent().getConstructor();
        AnaTypeFct constructor_ = _maintenant.getConstructor();
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        ctStd(std_, _maintenant.getInstancingCommonContent().getStd(), ls_);
        fctPub(constructor_, ls_);
        timbre(_found,root(constructor_),ls_);
        return ls_;
    }

    private static void timbre(int _found,RootBlock _root, CustList<SrcFileLocation> _dest) {
        if (_root != null) {
            _dest.add(new SrcFileLocationType(_found,_root));
        }
    }
    private static void ctStd(StandardConstructor _std, StandardType _type, CustList<SrcFileLocation> _ls) {
        if (_std != null) {
            _ls.add(new SrcFileLocationStdMethod(_type, _std));
        }
    }

    private static void fctPub(AnaTypeFct _ct, CustList<SrcFileLocation> _ls) {
        NamedFunctionBlock f_ = LambdaOperation.fct(_ct);
        if (f_ != null) {
            _ls.add(new SrcFileLocationMethod(_ct.getType(),f_));
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
            ls_.add(new SrcFileLocationMethod(_c.getFunction().getType(),cust_));
            return ls_;
        }
        StandardMethod std_ = _c.getStandardMethod();
        CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
        callStd(std_,_c.getStandardType(), ls_);
        return ls_;
    }

    private static void callStd(StandardMethod _std, StandardType _type, CustList<SrcFileLocation> _ls) {
        if (_std != null) {
            _ls.add(new SrcFileLocationStdMethod(_type, _std));
        }
    }

    private static NamedFunctionBlock fct(AnaCallFctContent _c) {
        AnaTypeFct f_ = _c.getFunction();
        return LambdaOperation.fct(f_);
    }

    static RootBlock root(LambdaOperation _f) {
        if (_f.getMethod() != null) {
            return null;
        }
        return root(_f.getFunction());
    }

    static RootBlock root(AnaTypeFct _f) {
        if (_f == null) {
            return null;
        }
        return _f.getType();
    }
    static Ints containerSeg(int _caret, FileBlock _file) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        return Ints.newList(c_.begin(),c_.end());
    }
    static AbsBk containerBlock(int _caret, FileBlock _file) {
        ResultExpressionOperationNode c_ = container(_caret, _file);
        return c_.getBlock();
    }
    private static ResultExpressionOperationNode container(int _caret, FileBlock _file) {
        AbsBk sub_ = _file;
        ResultExpressionOperationNode out_ = new ResultExpressionOperationNode();
        while (sub_ != null) {
            sub_ = subContainer(_caret, sub_);
            ResultExpression res_ = result(sub_, _caret);
            out_.setResultExpression(res_);
            ResultExpression u_ = null;
            BracedBlock next_;
            if (res_ == null) {
                next_ = null;
                out_.setBlock(sub_);
                out_.setFound(null);
            } else {
                OperationNode found_ = out_.subContainer(_caret);
                next_ = nextBlock(found_, res_.getSumOffset(),_caret);
                u_ = res_;
                if (next_ == null) {
                    out_.setBlock(sub_);
                    out_.setFound(found_);
                }
            }
            OperationNode f_ = out_.getFound();
            if (u_ != null && f_ != null) {
                out_.beginOff = begin(u_, f_);
            }
            sub_ = next_;
        }
        return out_;
    }

    private static AbsBk subContainer(int _caret, AbsBk _bl) {
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

    private static ResultExpression result(AbsBk _block, int _caret) {
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
        if (_block instanceof NamedCalledFunctionBlock && inExp(((NamedCalledFunctionBlock)_block).getRes(),_caret)) {
            return ((NamedCalledFunctionBlock)_block).getRes();
        }
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

    private static ResultExpression resRoot(RootBlock _block, int _caret) {
        ResultParsedAnnots a_ = _block.getAnnotations();
        int index_ = indexOfAnnot(a_, _caret);
        if (index_ > -1) {
            return a_.getAnnotations().get(index_).getRes();
        }
        return null;
    }

    private static ResultExpression resSw(SwitchMethodBlock _block, int _caret) {
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
        int end_ = _res.end();
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

    private OperationNode subContainer(int _caret) {
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

    private static BracedBlock nextBlock(OperationNode _result, int _sum, int _caret) {
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

    private int begin() {
        return begin(getFound());
    }

    private int begin(OperationNode _b) {
        return begin(resultExpression,_b);
    }

    public static int begin(ResultExpression _r,OperationNode _b) {
        return _b.getIndexInEl() + _r.getSumOffset();
    }

    private int end() {
        return end(getFound());
    }

    private int end(OperationNode _b) {
        return end(resultExpression, _b);
    }
    public static int end(ResultExpression _r,OperationNode _b) {
        MethodOperation par_ = _b.getParent();
        if (par_ != null) {
            int indexChild_ = _b.getIndexChild();
            return begin(_r,_b) + par_.getChildren().getValue(indexChild_).length();
        }
        return begin(_r,_b) + _r.getAnalyzedString().length();
    }

    public static boolean inRange(int _begin, int _caret, int _end) {
        return _begin <= _caret && _caret < _end;
    }

    private AbsBk getBlock() {
        return block;
    }

    private void setBlock(AbsBk _b) {
        this.block = _b;
    }

    private OperationNode getFound() {
        return found;
    }

    private void setFound(OperationNode _f) {
        this.found = _f;
    }

    private void setResultExpression(ResultExpression _r) {
        this.resultExpression = _r;
    }
}
