package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.NoExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ExecClassesUtil {

    public static final String WILD_CARD_PAIR = "<?,?>";
    public static final String WILD_CARD = "<?>";

    private ExecClassesUtil() {
    }

    public static void buildIterable(Classes _classes, ContextEl _context) {
        //local names
        LgNames stds_ = _context.getStandards();
        String next_ = stds_.getContent().getPredefTypes().getAliasNext();
        String hasNext_ = stds_.getContent().getPredefTypes().getAliasHasNext();
        String nextPair_ = stds_.getContent().getPredefTypes().getAliasNextPair();
        String hasNextPair_ = stds_.getContent().getPredefTypes().getAliasHasNextPair();
        StringList l_ = new StringList();
        String locName_ = ValidatorStandard.tr(l_);
        _classes.setIteratorVarCust(locName_);
        String iterator_ = stds_.getContent().getPredefTypes().getAliasIterator();
        _classes.setExpsIteratorCust(newCall(_classes.getIteratorVarCust(),StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIterable(), WILD_CARD),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIterable(),new MethodId(MethodAccessKind.INSTANCE,iterator_, new StringList())),
                StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIteratorType(), WILD_CARD), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setHasNextVarCust(locName_);
        _classes.setExpsHasNextCust(newCall(_classes.getHasNextVarCust(), StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIteratorType(), WILD_CARD),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,hasNext_, new StringList())),
                stds_.getContent().getPrimTypes().getAliasPrimBoolean(), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setNextVarCust(locName_);
        _classes.setExpsNextCust(newCall(_classes.getNextVarCust(), StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIteratorType(), WILD_CARD),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,next_, new StringList())),
                stds_.getContent().getCoreNames().getAliasObject(), _classes));

        _classes.setIteratorTableVarCust(locName_);
        String iteratorTable_ = stds_.getContent().getPredefTypes().getAliasIteratorTable();
        _classes.setExpsIteratorTableCust(newCall(_classes.getIteratorTableVarCust(), StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIterableTable(), WILD_CARD_PAIR),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIterableTable(),new MethodId(MethodAccessKind.INSTANCE,iteratorTable_, new StringList())),
                StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIteratorTableType(), WILD_CARD_PAIR), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setHasNextPairVarCust(locName_);
        _classes.setExpsHasNextPairCust(newCall(_classes.getHasNextPairVarCust(), StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIteratorTableType(), WILD_CARD_PAIR),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,hasNextPair_, new StringList())),
                stds_.getContent().getPrimTypes().getAliasPrimBoolean(), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setNextPairVarCust(locName_);
        _classes.setExpsNextPairCust(newCall(_classes.getNextPairVarCust(), StringUtil.concat(stds_.getContent().getPredefTypes().getAliasIteratorTableType(), WILD_CARD_PAIR),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,nextPair_, new StringList())),
                StringUtil.concat(stds_.getContent().getPredefTypes().getAliasPairType(), WILD_CARD_PAIR), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setFirstVarCust(locName_);
        String first_ = stds_.getContent().getPredefTypes().getAliasGetFirst();
        _classes.setExpsFirstCust(newCall(_classes.getFirstVarCust(), StringUtil.concat(stds_.getContent().getPredefTypes().getAliasPairType(), WILD_CARD_PAIR),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,first_, new StringList())),
                stds_.getContent().getCoreNames().getAliasObject(), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setSecondVarCust(locName_);
        String second_ = stds_.getContent().getPredefTypes().getAliasGetSecond();
        _classes.setExpsSecondCust(newCall(_classes.getSecondVarCust(), StringUtil.concat(stds_.getContent().getPredefTypes().getAliasPairType(), WILD_CARD_PAIR),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,second_, new StringList())),
                stds_.getContent().getCoreNames().getAliasObject(), _classes));
        String id_ = StringExpUtil.getIdFromAllTypes(stds_.getContent().getPredefTypes().getAliasSeedDoubleGenerator());
        ExecRootBlock classBody_ = _classes.getClassBody(id_);
        String nameToCall_ = stds_.getContent().getPredefTypes().getAliasSeedGet();
        MethodId idMet_ = new MethodId(MethodAccessKind.INSTANCE, nameToCall_, new StringList());
        ExecNamedFunctionBlock fct_ = getMethodBodiesById(classBody_, idMet_).first();
        _classes.setSeedDoubleGeneratorPair(new ExecTypeFunction(classBody_,fct_));
        id_ = StringExpUtil.getIdFromAllTypes(stds_.getContent().getPredefTypes().getAliasSeedGenerator());
        classBody_ = _classes.getClassBody(id_);
        idMet_ = new MethodId(MethodAccessKind.INSTANCE, nameToCall_, new StringList(stds_.getContent().getPrimTypes().getAliasPrimLong()));
        fct_ = getMethodBodiesById(classBody_, idMet_).first();
        _classes.setSeedGeneratorPair(new ExecTypeFunction(classBody_,fct_));
    }

    private static CustList<ExecOperationNode> newCall(String _varPrevious, String _previous,
                                                       ClassMethodId _id,
                                                       String _res, Classes _classes) {
        CustList<ExecOperationNode> ops_ = new CustList<ExecOperationNode>();
        ExecDotOperation dot_ = new ExecDotOperation(0,new ExecClassArgumentMatching(_res),2);
        ExecInternVariableOperation r_ = new ExecInternVariableOperation(0,new ExecClassArgumentMatching(_previous),0,_varPrevious);
        ops_.add(r_);
        dot_.appendChild(r_);
        ExecFormattedRootBlock formattedType_ = ExecFormattedRootBlock.build(_id.getClassName(),_classes);
        ExecRootBlock classBody_ = formattedType_.getRootBlock();
        ExecNamedFunctionBlock fct_ = getMethodBodiesById(classBody_, _id.getConstraints()).first();
        ExecTypeFunction execTypeFunction_ = new ExecTypeFunction(formattedType_, fct_);
        ExecFctOperation f_ = new ExecFctOperation(new ExecClassArgumentMatching(_res), 1,1, new ExecArrContent(false), execTypeFunction_, formattedType_);
        dot_.appendChild(f_);
        r_.setSiblingSet(f_);
        ops_.add(f_);
        ops_.add(dot_);
        return ops_;
    }

    public static StackCall tryInitStaticlyTypes(ContextEl _context, Options _options) {
        return tryInitStaticlyTypes(_context,_options,null);
    }
    public static StackCall tryInitStaticlyTypes(ContextEl _context, Options _options, StackCall _st) {
        if (_st == null) {
            StackCall s_ = endOrder(_context, init(_context));
            return tryList(s_,_context, _options);
        }
        if (_st.getInitializingTypeInfos().getInitEnums() == InitPhase.NOTHING) {
            return _st;
        }
        _context.getInit().loopCalling(_context, _st);
        if (_st.isFailInit()) {
            Classes cl_ = _context.getClasses();
            cl_.getCommon().setStaticFields(_context.getClasses().getDebugMapping().getShared());
            _st.getInitializingTypeInfos().resetInitEnums(_st);
        }
        StackCall s_;
        if (_st.getInitializingTypeInfos().getInitEnums() == InitPhase.READ_ONLY_OTHERS) {
            s_ = endOrder(_context, _st);
        } else {
            if (!_st.isStoppedBreakPoint()) {
                StringUtil.removeObj(_context.getClasses().getDebugMapping().getTypesInit(), _context.getClasses().getDebugMapping().getInitClass());
            }
            s_ = _st;
        }
        return tryList(s_,_context, _options);
    }

    private static StackCall check(ContextEl _context, StackCall _st) {
        if (_st.isStoppedBreakPoint()) {
            return _st;
        }
        return afterInit(_context);
    }

    private static StackCall afterInit(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        for (EntryCust<String, StringMap<Struct>> e: cl_.getCommon().getStaticFields().entryList()) {
            for (EntryCust<String, Struct> f: e.getValue().entryList()) {
                f.setValue(NullStruct.def(f.getValue()));
            }
        }
        _context.setExiting(new NoExiting());
        StackCall st_ = StackCall.newInstance(InitPhase.NOTHING,_context);
        st_.getInitializingTypeInfos().setInitEnums(InitPhase.NOTHING);
        return st_;
    }

    private static StackCall init(ContextEl _context) {
        forwardClassesMetaInfos(_context);
        DefaultLockingClass dl_ = _context.getLocks();
        dl_.init(_context);
        _context.setExiting(new DefaultExiting(_context));
        StackCall st_ = StackCall.newInstance(InitPhase.READ_ONLY_OTHERS,_context);
        st_.getInitializingTypeInfos().setInitEnums(InitPhase.READ_ONLY_OTHERS);
        return st_;
    }

    private static StackCall endOrder(ContextEl _context, StackCall _st) {
        if (_st.isStoppedBreakPoint()) {
            return _st;
        }
        CustList<String> f_ = notInit(_context);
        StringUtil.removeObj(f_, _context.getClasses().getDebugMapping().getInitClass());
        Classes cl_ = _context.getClasses();
        while (true) {
            StringList new_ = new StringList();
            for (String c: f_) {
                _st.getInitializingTypeInfos().resetInitEnums(_st);
                StringMap<StringMap<Struct>> bk_ = buildFieldValues(cl_.getCommon().getStaticFields());
                _context.getClasses().getDebugMapping().setShared(bk_);
                _context.getClasses().getDebugMapping().setInitClass(c);
                ProcessMethod.initializeClassPre(c,cl_.getClassBody(c), _context, _st);
                if (_st.isFailInit()) {
                    cl_.getCommon().setStaticFields(bk_);
                } else {
                    if (_st.isStoppedBreakPoint()) {
                        return _st;
                    }
                    new_.add(c);
                }
            }
            StringUtil.removeAllElements(f_, new_);
            if (new_.isEmpty()) {
                break;
            }
        }
        _st.getInitializingTypeInfos().resetInitEnums(_st);
        return _st;
    }

    private static StackCall tryList(StackCall _orig, ContextEl _context, Options _options) {
        if (_orig.isStoppedBreakPoint()) {
            return endList(_context, _options, _orig);
        }
        if (_orig.getInitializingTypeInfos().getInitEnums() == InitPhase.LIST) {
            StackCall st_ = StackCall.newInstance(InitPhase.LIST,_context);
            st_.getInitializingTypeInfos().setInitEnums(InitPhase.LIST);
            return endList(_context, _options, st_);
        }
        StackCall st_ = StackCall.newInstance(InitPhase.LIST,_context);
        st_.getInitializingTypeInfos().setInitEnums(InitPhase.LIST);
        DefaultLockingClass dl_ = _context.getLocks();
        dl_.initAlwaysSuccess();
        _context.getClasses().getDebugMapping().getTypesInit().clear();
        _context.getClasses().getDebugMapping().getTypesInit().addAllElts(_options.getTypesInit());
        return endList(_context, _options, st_);
    }

    private static StackCall endList(ContextEl _context, Options _options, StackCall _st) {
        if (_st.isStoppedBreakPoint()) {
            return check(_context, _st);
        }
        CustList<String> ts_ = _context.getClasses().getDebugMapping().getTypesInit();
        CustList<String> init_ = new CustList<String>();
        for (String t: _options.getTypesInit()) {
            String res_ = StringExpUtil.removeDottedSpaces(t);
            ExecRootBlock classBody_ = _context.getClasses().getClassBody(res_);
            if (classBody_ == null) {
                continue;
            }
            _st.getInitializingTypeInfos().resetInitEnums(_st);
            _context.getClasses().getDebugMapping().setInitClass(res_);
            ProcessMethod.initializeClass(res_,classBody_, _context, _st);
            if (_st.isStoppedBreakPoint()) {
                StringUtil.removeAllElements(ts_,init_);
                return _st;
            }
            init_.add(t);
        }
        StringUtil.removeAllElements(ts_,init_);
        return check(_context, _st);
    }

    private static CustList<String> notInit(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        DefaultLockingClass dl_ = _context.getLocks();
        CustList<String> notInitial_ = new CustList<String>();
        for (EntryCust<String, ExecRootBlock> a: cl_.getClassesBodies().entryList()) {
            if (dl_.getState(a.getValue()) == InitClassState.NOT_YET) {
                notInitial_.add(a.getKey());
            }
        }
        return notInitial_;
    }

    private static void forwardClassesMetaInfos(ContextEl _context) {
        for (ClassMetaInfo c: _context.getClasses().getClassMetaInfos()) {
            String name_ = c.getFormatted().getFormatted();
            ClassMetaInfo.forward(ClassMetaInfo.getClassMetaInfo(_context,name_),c);
        }
    }

    private static StringMap<StringMap<Struct>> buildFieldValues(StringMap<StringMap<Struct>> _infos) {
        StringMap<StringMap<Struct>> bkSt_ = new StringMap<StringMap<Struct>>();
        for (EntryCust<String, StringMap<Struct>> e: _infos.entryList()) {
            StringMap<Struct> b_ = new StringMap<Struct>();
            for (EntryCust<String, Struct> f: e.getValue().entryList()) {
                b_.addEntry(f.getKey(), f.getValue());
            }
            bkSt_.addEntry(e.getKey(), b_);
        }
        return bkSt_;
    }

    public static CustList<ExecOverridableBlock> getMethodBodiesById(ExecRootBlock _genericClassName, MethodId _id) {
        CustList<ExecOverridableBlock> methods_ = new CustList<ExecOverridableBlock>();
        for (ExecBlock m: _genericClassName.getChildrenOthers()) {
            if (m instanceof ExecOverridableBlock && ((ExecOverridableBlock) m).getId().eq(_id)) {
                methods_.add((ExecOverridableBlock) m);
            }
        }
        return methods_;
    }

}
