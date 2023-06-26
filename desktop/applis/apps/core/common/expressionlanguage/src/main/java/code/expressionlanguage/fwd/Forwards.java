package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.dbg.DebugMapping;
import code.expressionlanguage.fwd.blocks.FwdRootBlockMembers;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
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
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> allMapInnerEltTypes = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final CustList<FwdRootBlockMembers> mapMembers = new CustList<FwdRootBlockMembers>();
    private final CustList<FwdRootBlockMembers> allMapMembers = new CustList<FwdRootBlockMembers>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> mapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> allMapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private final IdMap<NamedCalledFunctionBlock,ExecAnonymousFunctionBlock> mapAnonLambda = new IdMap<NamedCalledFunctionBlock,ExecAnonymousFunctionBlock>();
    private final IdMap<NamedCalledFunctionBlock,ExecAnonymousFunctionBlock> allMapAnonLambda = new IdMap<NamedCalledFunctionBlock,ExecAnonymousFunctionBlock>();
    private final IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod> mapSwitchMethods = new IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod>();
    private final IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod> allMapSwitchMethods = new IdMap<SwitchMethodBlock,ExecAbstractSwitchMethod>();
    private final IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock> mapAnonTypes = new IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock>();
    private final IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock> allMapAnonTypes = new IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFctBodies = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();

    private final AbstractFileBuilder fileBuilder;
    private final AbstractConstantsCalculator constantsCalculator;
    private final Coverage coverage;
    private final Classes classes;
    private final BuildableLgNames generator;
    private final LoggableLgNames loggable;
    private final Options options;
    private int countTypes;
    private final AbstractInterceptorStdCaller interceptor;

    public Forwards(BuildableLgNames _generator, LoggableLgNames _loggable, AbstractFileBuilder _fileBuilder, Options _options) {
        generator = _generator;
        loggable = _loggable;
        options = _options;
        constantsCalculator = _generator.newConstantsCalculator();
        interceptor = _generator.interceptor();
        coverage = new Coverage(_options.isCovering());
        coverage.getOptionsReport().setDisplayImplicit(_options.getOptionsReport().isDisplayImplicit());
        coverage.getOptionsReport().setDisplayImplicitLabel(_options.getOptionsReport().isDisplayImplicitLabel());
        coverage.getOptionsReport().setEncodeHeader(_options.getOptionsReport().isEncodeHeader());
        classes = new Classes(_options.getChecker(),new DebugMapping(_options.isDebugging(),interceptor));
        fileBuilder = _fileBuilder;
    }

    public Forwards(Forwards _from, AnalyzedPageEl _page) {
        generator = _from.getGenerator();
        interceptor = _from.getInterceptor();
        loggable = _from.getLoggable();
        options = new Options();
        constantsCalculator = _from.getGenerator().newConstantsCalculator();
        coverage = new Coverage(false);
        classes = new Classes(_page.getChecker(),new DebugMapping(false,interceptor));
        fileBuilder = _page.getFileBuilder();
        getAllMapOperators().addAllEntries(_from.getAllMapOperators());
        getAllMapInnerEltTypes().addAllEntries(_from.getAllMapInnerEltTypes());
        getAllMapAnonTypes().addAllEntries(_from.getAllMapAnonTypes());
        getAllMapMembers().addAllElts(_from.getAllMapMembers());
        getAllMapAnonLambda().addAllEntries(_from.getAllMapAnonLambda());
        getAllMapSwitchMethods().addAllEntries(_from.getAllMapSwitchMethods());
        setCountTypes(_from.getCountTypes());
    }
    public DebugMapping dbg() {
        return getClasses().getDebugMapping();
    }

    public Options getOptions() {
        return options;
    }

    public LoggableLgNames getLoggable() {
        return loggable;
    }

    public AbstractInterceptorStdCaller getInterceptor() {
        return interceptor;
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
        FwdRootBlockMembers f_ = new FwdRootBlockMembers(_type, _mem);
        mapMembers.add(f_);
        allMapMembers.add(f_);
    }
    public boolean isMember(int _root) {
        return allMapMembers.isValidIndex(_root);
    }
    public Members getMember(RootBlock _root) {
        return getMember(_root.getNumberAll());
    }
    public Members getMember(int _root) {
        return allMapMembers.get(_root).getMembers();
    }

    public CustList<FwdRootBlockMembers> getAllMapMembers() {
        return allMapMembers;
    }

    public CustList<FwdRootBlockMembers> getMembers() {
        return mapMembers;
    }

    public IdMap<OperatorBlock, ExecOperatorBlock> getAllMapOperators() {
        return allMapOperators;
    }

    public void addOperator(OperatorBlock _type, ExecOperatorBlock _mem) {
        mapOperators.addEntry(_type, _mem);
        allMapOperators.addEntry(_type, _mem);
    }
    public boolean isOperator(int _root) {
        return allMapOperators.isValidIndex(_root);
    }
    public ExecOperatorBlock getOperator(OperatorBlock _root) {
        return getOperator(_root.getOperatorNumber());
    }
    public ExecOperatorBlock getOperator(int _root) {
        return allMapOperators.getValue(_root);
    }

    public Iterable<EntryCust<OperatorBlock, ExecOperatorBlock>> getOperators() {
        return mapOperators.entryList();
    }

    public void addAnonType(AnonymousTypeBlock _type, ExecAnonymousTypeBlock _mem) {
        mapAnonTypes.addEntry(_type, _mem);
        allMapAnonTypes.addEntry(_type, _mem);
    }

    public IdMap<AnonymousTypeBlock, ExecAnonymousTypeBlock> getAllMapAnonTypes() {
        return allMapAnonTypes;
    }

    public ExecAnonymousTypeBlock getAnonType(AnonymousTypeBlock _root) {
        return allMapAnonTypes.getValue(_root.getNumberAnonType());
    }

    public void addFctBody(MemberCallingsBlock _type, ExecMemberCallingsBlock _mem) {
        allFctBodies.addEntry(_type, _mem);
    }

    public Iterable<EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock>> getFctBodies() {
        return allFctBodies.entryList();
    }

    public IdMap<NamedCalledFunctionBlock, ExecAnonymousFunctionBlock> getAllMapAnonLambda() {
        return allMapAnonLambda;
    }

    public void addAnonLambda(NamedCalledFunctionBlock _type, ExecAnonymousFunctionBlock _mem) {
        mapAnonLambda.addEntry(_type, _mem);
        allMapAnonLambda.addEntry(_type, _mem);
    }
    public ExecAnonymousFunctionBlock getAnonLambda(NamedCalledFunctionBlock _root) {
        return allMapAnonLambda.getValue(_root.getNumberLambda());
    }
    public int countAnonLambda(){
        return allMapAnonLambda.size();
    }
    public Iterable<EntryCust<NamedCalledFunctionBlock, ExecAnonymousFunctionBlock>> getAnonLambdas() {
        return mapAnonLambda.entryList();
    }

    public IdMap<SwitchMethodBlock, ExecAbstractSwitchMethod> getAllMapSwitchMethods() {
        return allMapSwitchMethods;
    }

    public void addSwitchMethod(SwitchMethodBlock _type, ExecAbstractSwitchMethod _mem) {
        mapSwitchMethods.addEntry(_type, _mem);
        allMapSwitchMethods.addEntry(_type, _mem);
    }
    public ExecAbstractSwitchMethod getSwitchMethod(SwitchMethodBlock _root) {
        return allMapSwitchMethods.getValue(_root.getConditionNb());
    }
    public int countSwitchMethod(){
        return allMapSwitchMethods.size();
    }
    public Iterable<EntryCust<SwitchMethodBlock, ExecAbstractSwitchMethod>> getSwitchMethods() {
        return mapSwitchMethods.entryList();
    }

    public IdMap<InnerElementBlock, ExecInnerElementBlock> getAllMapInnerEltTypes() {
        return allMapInnerEltTypes;
    }

    public void addInnerEltType(InnerElementBlock _type, ExecInnerElementBlock _mem) {
        mapInnerEltTypes.addEntry(_type, _mem);
        allMapInnerEltTypes.addEntry(_type, _mem);
    }
    public ExecInnerElementBlock getInnerEltType(InnerElementBlock _root) {
        return allMapInnerEltTypes.getValue(_root.getNumberInner());
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

    public int getCountTypes() {
        return countTypes;
    }

    public void setCountTypes(int _c) {
        this.countTypes = _c;
    }

}
