package code.expressionlanguage.fwd;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.fwd.blocks.FwdRootBlockMembers;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LoggableLgNames;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class Forwards {
    private String aliasPrimBoolean="";
    private String aliasBoolean="";
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> mapInnerEltTypes = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final CustList<FwdRootBlockMembers> mapMembers = new CustList<FwdRootBlockMembers>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> mapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private final IdMap<NamedCalledFunctionBlock,ExecAnonymousFunctionBlock> mapAnonLambda = new IdMap<NamedCalledFunctionBlock,ExecAnonymousFunctionBlock>();
    private final IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod> mapSwitchMethods = new IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod>();
    private final IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock> mapAnonTypes = new IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFctBodies = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();

    private final AbstractFileBuilder fileBuilder;
    private final AbstractConstantsCalculator constantsCalculator;
    private final Coverage coverage;
    private final Classes classes;
    private final BuildableLgNames generator;
    private final LoggableLgNames loggable;
    private final Options options;

    public Forwards(BuildableLgNames _generator, LoggableLgNames _loggable, AbstractFileBuilder _fileBuilder, Options _options) {
        generator = _generator;
        loggable = _loggable;
        options = _options;
        constantsCalculator = _generator.newConstantsCalculator();
        coverage = new Coverage(_options.isCovering());
        coverage.setImplicit(_options.isDisplayImplicit());
        coverage.setDisplayEncode(_options.isEncodeHeader());
        classes = new Classes(_options.getChecker());
        fileBuilder = _fileBuilder;
    }

    public ContextEl generate() {
        return loggable.newContext(options, this);
    }

    public Options getOptions() {
        return options;
    }

    public LoggableLgNames getLoggable() {
        return loggable;
    }

    public BuildableLgNames getGenerator() {
        return generator;
    }

    public AbstractConstantsCalculator getConstantsCalculator() {
        return constantsCalculator;
    }

    public AbstractFileBuilder getFileBuilder() {
        return fileBuilder;
    }

    public Classes getClasses() {
        return classes;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public StringMap<String> getResources() {
        return classes.getCommon().getResources();
    }

    public StringMap<StringMap<Struct>> getStaticFields() {
        return classes.getCommon().getStaticFields();
    }

    public void addMember(RootBlock _type, Members _mem) {
        mapMembers.add(new FwdRootBlockMembers(_type, _mem));
    }
    public boolean isMember(int _root) {
        return mapMembers.isValidIndex(_root);
    }
    public Members getMember(RootBlock _root) {
        return getMember(_root.getNumberAll());
    }
    public Members getMember(int _root) {
        return mapMembers.get(_root).getMembers();
    }

    public CustList<FwdRootBlockMembers> getMembers() {
        return mapMembers;
    }
    public void addOperator(OperatorBlock _type, ExecOperatorBlock _mem) {
        mapOperators.addEntry(_type, _mem);
    }
    public boolean isOperator(int _root) {
        return mapOperators.isValidIndex(_root);
    }
    public ExecOperatorBlock getOperator(OperatorBlock _root) {
        return getOperator(_root.getOperatorNumber());
    }
    public ExecOperatorBlock getOperator(int _root) {
        return mapOperators.getValue(_root);
    }

    public Iterable<EntryCust<OperatorBlock, ExecOperatorBlock>> getOperators() {
        return mapOperators.entryList();
    }

    public void addAnonType(AnonymousTypeBlock _type, ExecAnonymousTypeBlock _mem) {
        mapAnonTypes.addEntry(_type, _mem);
    }
    public ExecAnonymousTypeBlock getAnonType(AnonymousTypeBlock _root) {
        return mapAnonTypes.getValue(_root.getNumberAnonType());
    }

    public void addFctBody(MemberCallingsBlock _type, ExecMemberCallingsBlock _mem) {
        allFctBodies.addEntry(_type, _mem);
    }

    public Iterable<EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock>> getFctBodies() {
        return allFctBodies.entryList();
    }
    public void addAnonLambda(NamedCalledFunctionBlock _type, ExecAnonymousFunctionBlock _mem) {
        mapAnonLambda.addEntry(_type, _mem);
    }
    public ExecAnonymousFunctionBlock getAnonLambda(NamedCalledFunctionBlock _root) {
        return mapAnonLambda.getValue(_root.getNumberLambda());
    }
    public int countAnonLambda(){
        return mapAnonLambda.size();
    }
    public Iterable<EntryCust<NamedCalledFunctionBlock, ExecAnonymousFunctionBlock>> getAnonLambdas() {
        return mapAnonLambda.entryList();
    }
    public void addSwitchMethod(SwitchMethodBlock _type, ExecAbstractSwitchMethod _mem) {
        mapSwitchMethods.addEntry(_type, _mem);
    }
    public ExecAbstractSwitchMethod getSwitchMethod(SwitchMethodBlock _root) {
        return mapSwitchMethods.getValue(_root.getConditionNb());
    }
    public int countSwitchMethod(){
        return mapSwitchMethods.size();
    }
    public Iterable<EntryCust<SwitchMethodBlock, ExecAbstractSwitchMethod>> getSwitchMethods() {
        return mapSwitchMethods.entryList();
    }

    public void addInnerEltType(InnerElementBlock _type, ExecInnerElementBlock _mem) {
        mapInnerEltTypes.addEntry(_type, _mem);
    }
    public ExecInnerElementBlock getInnerEltType(InnerElementBlock _root) {
        return mapInnerEltTypes.getValue(_root.getNumberInner());
    }

    public String getAliasBoolean() {
        return aliasBoolean;
    }

    public void setAliasBoolean(String _aliasBoolean) {
        this.aliasBoolean = _aliasBoolean;
    }

    public String getAliasPrimBoolean() {
        return aliasPrimBoolean;
    }

    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        this.aliasPrimBoolean = _aliasPrimBoolean;
    }
}
