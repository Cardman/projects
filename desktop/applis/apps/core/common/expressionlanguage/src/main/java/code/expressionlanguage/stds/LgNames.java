package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;

public abstract class LgNames implements BuildableLgNames {

    public static final String ITERATOR = "Iterator";
    public static final String HAS_NEXT = "HasNext";
    public static final String NEXT = "Next";
    public static final String GET_FIRST = "GetFirst";
    public static final String NEXT_PAIR = "NextPair";
    public static final String ITERATOR_TABLE = "IteratorTable";
    public static final String HAS_NEXT_PAIR = "HasNextPair";
    public static final String GET_SECOND = "GetSecond";
    public static final String SEED_GET = "SeedGet";
    public static final String ENUM_NAME = "EnumName";
    public static final String ENUM_ORDINAL = "EnumOrdinal";

    private final LgNamesContent content = new LgNamesContent();

    private final AbstractGenerator generator;
    private AbstractExecConstantsCalculator calculator;

    protected LgNames(AbstractGenerator _generator) {
        this.generator = _generator;
        setCalculator(new DefaultExecConstantsCalculator(content.getNbAlias()));
    }

    /**Called after setters*/
    public void build() {
        content.getCoreNames().build(this);
        content.getNbAlias().build(this);
        content.getCharSeq().build(this);
        content.getReflect().build(this);
        content.getMathRef().build(this);
        content.getStackElt().build(this);
        content.getPrimTypes().buildPrimitiveTypes(this);
        buildOther();
    }

    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return ApplyCoreMethodUtil.getStringOfObjectBase(_cont, _arg);
    }

    public abstract void buildOther();
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, StackCall _stackCall, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.invoke(_stackCall, _cont,_method,_struct, _exit, _args);
    }

    protected ResultErrorStd invoke(StackCall _stack, ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Argument... _args) {
        return ApplyCoreMethodUtil.invokeBase(_cont, _method, _struct, _exit, _args, _stack);
    }

    /**@param  _instance l'instance*/
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        return ApplyCoreMethodUtil.getOtherResultBase(_cont, _method, _args, _stack);
    }

    public Argument defaultInstance(ContextEl _cont, String _id, StackCall _stackCall) {
        return new Argument(new SimpleObjectStruct());
    }

    protected ResultErrorStd instance(StackCall _stack, ContextEl _cont, ConstructorId _method, Argument... _args) {
        return ApplyCoreMethodUtil.instanceBase(_cont, _method, _args, _stack);
    }

    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(new SimpleObjectStruct());
        return res_;
    }

    public final Struct getSimpleResult(ClassField _classField) {
        return calculator.getInnerSimpleResult(_classField);
    }

    public AbstractFunctionalInstance newFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return new FunctionalInstance(_className.getFormatted(),_functional, _named);
    }
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className,_rootBock);
        return new FullFunctionalInstance(_className.getFormatted(),_functional,fs_, _named);
    }
    public StringMap<StandardType> getStandards() {
        return content.getStandards();
    }
    public StringMap<PrimitiveType> getPrimitiveTypes() {
        return getPrimTypes().getPrimitiveTypes();
    }

    public PrimitiveTypes getPrimTypes() {
        return content.getPrimTypes();
    }

    public AliasReflection getReflect() {
        return content.getReflect();
    }

    public AliasMath getMathRef() {
        return content.getMathRef();
    }

    public AliasCharSequence getCharSeq() {
        return content.getCharSeq();
    }

    public AliasCore getCoreNames() {
        return content.getCoreNames();
    }

    public AliasNumber getNbAlias() {
        return content.getNbAlias();
    }

    public AliasStackTraceElement getStackElt() {
        return content.getStackElt();
    }

    public DisplayedStrings getDisplayedStrings() {
        return content.getDisplayedStrings();
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AliasPredefinedTypes getPredefTypes() {
        return content.getPredefTypes();
    }

    public LgNamesContent getContent() {
        return content;
    }

    protected void setCalculator(AbstractExecConstantsCalculator _calculator) {
        this.calculator = _calculator;
    }
}
