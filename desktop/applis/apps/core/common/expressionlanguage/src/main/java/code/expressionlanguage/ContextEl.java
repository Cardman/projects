package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.opers.ExecDotOperation;
import code.expressionlanguage.exec.opers.ExecFctOperation;
import code.expressionlanguage.exec.opers.ExecInternVariableOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.*;

public abstract class ContextEl {

    private final CommonExecutionInfos executionInfos;

    private CallingState callingState;

    private final CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private InitializingTypeInfos initializingTypeInfos;
    private AbstractFullStack fullStack;
    private Struct seed;
    private AbstractExiting exiting;

    protected ContextEl(CommonExecutionInfos _executionInfos, InitPhase _readOnlyOthers) {
        executionInfos = _executionInfos;
        initializingTypeInfos = new InitializingTypeInfos();
        initializingTypeInfos.setInitEnums(_readOnlyOthers);
        seed = NullStruct.NULL_VALUE;
    }

    private static void buildIterable(Classes _classes, ContextEl _context) {
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
        _classes.setExpsIteratorCust(newCall(_classes.getIteratorVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasIterable(),"<?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIterable(),new MethodId(MethodAccessKind.INSTANCE,iterator_, new StringList())),
                StringList.concat(stds_.getContent().getPredefTypes().getAliasIteratorType(),"<?>"), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setHasNextVarCust(locName_);
        _classes.setExpsHasNextCust(newCall(_classes.getHasNextVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasIteratorType(),"<?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,hasNext_, new StringList())),
                stds_.getContent().getPrimTypes().getAliasPrimBoolean(), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setNextVarCust(locName_);
        _classes.setExpsNextCust(newCall(_classes.getNextVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasIteratorType(),"<?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,next_, new StringList())),
                stds_.getContent().getCoreNames().getAliasObject(), _classes));

        _classes.setIteratorTableVarCust(locName_);
        String iteratorTable_ = stds_.getContent().getPredefTypes().getAliasIteratorTable();
        _classes.setExpsIteratorTableCust(newCall(_classes.getIteratorTableVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasIterableTable(),"<?,?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIterableTable(),new MethodId(MethodAccessKind.INSTANCE,iteratorTable_, new StringList())),
                StringList.concat(stds_.getContent().getPredefTypes().getAliasIteratorTableType(),"<?,?>"), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setHasNextPairVarCust(locName_);
        _classes.setExpsHasNextPairCust(newCall(_classes.getHasNextPairVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,hasNextPair_, new StringList())),
                stds_.getContent().getPrimTypes().getAliasPrimBoolean(), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setNextPairVarCust(locName_);
        _classes.setExpsNextPairCust(newCall(_classes.getNextPairVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,nextPair_, new StringList())),
                StringList.concat(stds_.getContent().getPredefTypes().getAliasPairType(),"<?,?>"), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setFirstVarCust(locName_);
        String first_ = stds_.getContent().getPredefTypes().getAliasGetFirst();
        _classes.setExpsFirstCust(newCall(_classes.getFirstVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasPairType(),"<?,?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,first_, new StringList())),
                stds_.getContent().getCoreNames().getAliasObject(), _classes));
        locName_ = ValidatorStandard.tr(l_);
        _classes.setSecondVarCust(locName_);
        String second_ = stds_.getContent().getPredefTypes().getAliasGetSecond();
        _classes.setExpsSecondCust(newCall(_classes.getSecondVarCust(),StringList.concat(stds_.getContent().getPredefTypes().getAliasPairType(),"<?,?>"),
                new ClassMethodId(stds_.getContent().getPredefTypes().getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,second_, new StringList())),
                stds_.getContent().getCoreNames().getAliasObject(), _classes));
        String id_ = StringExpUtil.getIdFromAllTypes(stds_.getContent().getPredefTypes().getAliasSeedDoubleGenerator());
        ExecRootBlock classBody_ = _classes.getClassBody(id_);
        _classes.setSeedDoubleGenerator(classBody_);
        String nameToCall_ = stds_.getContent().getPredefTypes().getAliasSeedGet();
        MethodId idMet_ = new MethodId(MethodAccessKind.INSTANCE, nameToCall_, new StringList());
        ExecNamedFunctionBlock fct_ = ExecBlock.getMethodBodiesById(classBody_, idMet_).first();
        _classes.setSeedDoublePick(fct_);
        id_ = StringExpUtil.getIdFromAllTypes(stds_.getContent().getPredefTypes().getAliasSeedGenerator());
        classBody_ = _classes.getClassBody(id_);
        _classes.setSeedGenerator(classBody_);
        idMet_ = new MethodId(MethodAccessKind.INSTANCE, nameToCall_, new StringList(stds_.getContent().getPrimTypes().getAliasPrimLong()));
        fct_ = ExecBlock.getMethodBodiesById(classBody_, idMet_).first();
        _classes.setSeedPick(fct_);
    }

    private static CustList<ExecOperationNode> newCall(String _varPrevious, String _previous,
                                                       ClassMethodId _id,
                                                       String _res, Classes _classes) {
        CustList<ExecOperationNode> ops_ = new CustList<ExecOperationNode>();
        ExecDotOperation dot_ = new ExecDotOperation(0,new ExecClassArgumentMatching(_res),2);
        ExecInternVariableOperation r_ = new ExecInternVariableOperation(0,new ExecClassArgumentMatching(_previous),0,_varPrevious);
        ops_.add(r_);
        dot_.appendChild(r_);
        String id_ = StringExpUtil.getIdFromAllTypes(_id.getClassName());
        ExecRootBlock classBody_ = _classes.getClassBody(id_);
        ExecNamedFunctionBlock fct_ = ExecBlock.getMethodBodiesById(classBody_, _id.getConstraints()).first();
        ExecFctOperation f_ = new ExecFctOperation(new ExecClassArgumentMatching(_res),_id,1,1,fct_,classBody_);
        dot_.appendChild(f_);
        r_.setSiblingSet(f_);
        ops_.add(f_);
        ops_.add(dot_);
        return ops_;
    }

    public GeneType getClassBody(String _type) {
        ExecRootBlock c_ = getExecutionInfos().getClasses().getClassBody(_type);
        if (c_ != null) {
            return c_;
        }
        return getExecutionInfos().getStandards().getStandards().getVal(_type);
    }
    public Initializer getInit() {
        return getExecutionInfos().getInitializer();
    }
    public int getStackOverFlow() {
        return getExecutionInfos().getStackOverFlow();
    }

    public int getTabWidth() {
        return getExecutionInfos().getTabWidth();
    }


    public Classes getClasses() {
        return getExecutionInfos().getClasses();
    }

    public Coverage getCoverage() {
        return getExecutionInfos().getCoverage();
    }

    public LgNames getStandards() {
        return getExecutionInfos().getStandards();
    }

    public DefaultLockingClass getLocks() {
        return getExecutionInfos().getLocks();
    }
    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public void clearPages() {
        importing.clear();
    }
    public boolean hasPages() {
        return !importing.isEmpty();
    }

    public AbstractPageEl getCall(int _index) {
        return importing.get(_index);
    }
    public int nbPages() {
        return importing.size();
    }

    public void removeLastPage() {
        importing.removeLast();
    }

    public void addInternPage(AbstractPageEl _page) {
        importing.add(_page);
    }

    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        ForwardInfos.generalForward(_ana,_forwards, this);
        for (ClassMetaInfo c: _ana.getClassMetaInfos()) {
            getClasses().getClassMetaInfos().add(c);
        }
        _ana.getClassMetaInfos().clear();
        getClasses().setKeyWordValue(_ana.getKeyWords().getKeyWordValue());
        buildIterable(getClasses(), this);
    }

    public AbstractPageEl getLastPage() {
        return importing.last();
    }

    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }

    public boolean callsOrException() {
        return callsOrExceptionBase(this);
    }

    private static boolean callsOrExceptionBase(ContextEl _context) {
        if (_context.callingState != null) {
            return true;
        }
        return _context.isFailInit();
    }

    public boolean calls() {
        return !hasException();
    }

    public boolean hasException() {
        return callingState instanceof Struct;
    }
    public boolean isFailInit() {
        return initializingTypeInfos.isFailInit();
    }

    public void setException(Struct _exception) {
        callingState = _exception;
    }

    public CallingState getCallingState() {
        return callingState;
    }

    public void setCallingState(CallingState _callingState) {
        callingState = _callingState;
    }

    public AbstractFullStack getFullStack() {
        return fullStack;
    }

    public final void setFullStack(AbstractFullStack fullStack) {
        this.fullStack = fullStack;
    }


    public InitializingTypeInfos getInitializingTypeInfos() {
        return initializingTypeInfos;
    }

    public Struct getSeed() {
        return seed;
    }

    public void setSeed(Struct seed) {
        this.seed = seed;
    }

    public AbstractExiting getExiting() {
        return exiting;
    }

    public void setExiting(AbstractExiting _exiting) {
        exiting = _exiting;
    }
}
