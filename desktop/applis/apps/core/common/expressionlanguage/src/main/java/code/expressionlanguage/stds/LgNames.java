package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.StringMap;

public abstract class LgNames implements BuildableLgNames {

    private final LgNamesContent content = new LgNamesContent();

    private final AbstractGenerator generator;
    private AbstractExecConstantsCalculator calculator;

    protected LgNames(AbstractGenerator _generator) {
        this.generator = _generator;
        defCaluclator();
    }

    private void defCaluclator() {
        setCalculator(new DefaultExecConstantsCalculator(content.getNbAlias()));
    }

    public CommonExecutionInfos commonExecutionInfos(AbstractInterceptorStdCaller _caller, Options _opt, Forwards _options, Initializer _initializer) {
        return new CommonExecutionInfos(_caller,new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene(),_opt.getDbConverter()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),_initializer);
    }
    /**Called after setters*/
    public void buildBase() {
        content.getCoreNames().build(this);
        content.getNbAlias().build(this);
        content.getCharSeq().build(this);
        content.getReflect().build(this);
        content.getMathRef().build(this);
        content.getStackElt().build(this);
        content.getPrimTypes().buildPrimitiveTypes(this);
    }

    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return ExecCatOperation.getStringOfObjectBase(_cont, _arg);
    }

    public final Struct getSimpleResult(ClassField _classField) {
        return calculator.getInnerSimpleResult(_classField);
    }

    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new InnerCustStruct(_className.getFormatted(),fs_, _named, _functional);
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

    public AliasMathType getMathRef() {
        return content.getMathRef();
    }

    public AliasCharSequenceType getCharSeq() {
        return content.getCharSeq();
    }

    public AliasCore getCoreNames() {
        return content.getCoreNames();
    }

    public AliasNumberType getNbAlias() {
        return content.getNbAlias();
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

    @Override
    public AbstractConstantsCalculator newConstantsCalculator() {
        return new DefaultConstantsCalculator(getNbAlias());
    }

    protected void setCalculator(AbstractExecConstantsCalculator _calculator) {
        this.calculator = _calculator;
    }
}
