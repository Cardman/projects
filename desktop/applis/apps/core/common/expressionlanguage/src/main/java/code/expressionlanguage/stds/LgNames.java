package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;

public abstract class LgNames {

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

    private LgNamesContent content = new LgNamesContent();

    private final AbstractGenerator generator;
    private AbstractExecConstantsCalculator calculator;

    protected LgNames(AbstractGenerator generator) {
        this.generator = generator;
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
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.invoke(_cont,_method,_struct, _exit, _args);
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Argument... _args) {
        return ApplyCoreMethodUtil.invokeBase(_cont, _method, _struct, _exit, _args);
    }

    /**@param  _instance l'instance*/
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        return ApplyCoreMethodUtil.getOtherResultBase(_cont, _method, _args);
    }

    public Argument defaultInstance(ContextEl _cont, String _id) {
        return new Argument(new SimpleObjectStruct());
    }

    protected ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        return ApplyCoreMethodUtil.instanceBase(_cont, _method, _args);
    }

    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(new SimpleObjectStruct());
        return res_;
    }

    public final Struct getSimpleResult(ClassField _classField) {
        return calculator.getInnerSimpleResult(_classField);
    }

    public AbstractFunctionalInstance newFunctionalInstance(String _className, ExecRootBlock _rootBock, LambdaStruct _functional, ContextEl _contextEl) {
        return new FunctionalInstance(_className,_functional);
    }
    public AbstractFunctionalInstance newFullFunctionalInstance(String _className, ExecRootBlock _rootBock, LambdaStruct _functional, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className,_rootBock);
        return new FullFunctionalInstance(_className,_functional,fs_);
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

    protected void setCalculator(AbstractExecConstantsCalculator calculator) {
        this.calculator = calculator;
    }
}
