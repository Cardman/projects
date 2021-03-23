package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.blocks.*;
import code.util.EntryCust;
import code.util.IdMap;

public final class Members {
    private ExecRootBlock rootBlock;
    private final IdMap<NamedCalledFunctionBlock,ExecOverridableBlock> allMethods = new IdMap<NamedCalledFunctionBlock,ExecOverridableBlock>();
    private final IdMap<NamedCalledFunctionBlock,ExecAnnotationMethodBlock> allAnnotMethods = new IdMap<NamedCalledFunctionBlock,ExecAnnotationMethodBlock>();
    private final IdMap<ConstructorBlock,ExecConstructorBlock> allCtors = new IdMap<ConstructorBlock,ExecConstructorBlock>();
    private final IdMap<InfoBlock,ExecInfoBlock> allFields = new IdMap<InfoBlock,ExecInfoBlock>();
    private final IdMap<FieldBlock,ExecFieldBlock> allExplicitFields = new IdMap<FieldBlock,ExecFieldBlock>();
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> allInnerElementFields = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final IdMap<InnerTypeOrElement,ExecInnerTypeOrElement> allElementFields = new IdMap<InnerTypeOrElement,ExecInnerTypeOrElement>();
    private final IdMap<ElementBlock,ExecElementBlock> allSimpleElementFields = new IdMap<ElementBlock,ExecElementBlock>();
    private final IdMap<NamedFunctionBlock,ExecNamedFunctionBlock> allNamed = new IdMap<NamedFunctionBlock,ExecNamedFunctionBlock>();
    private final IdMap<NamedFunctionBlock,ExecNamedFunctionBlock> allOvNamed = new IdMap<NamedFunctionBlock,ExecNamedFunctionBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFct = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFctBodies = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();

    public void addMethod(NamedCalledFunctionBlock _key, ExecOverridableBlock _value) {
        allMethods.addEntry(_key, _value);
    }
    public ExecOverridableBlock getMethod(NamedCalledFunctionBlock _key) {
        return allMethods.getValue(_key.getNameOverrideNumber());
    }
    public Iterable<EntryCust<NamedCalledFunctionBlock, ExecOverridableBlock>> getMethods() {
        return allMethods.entryList();
    }
    public void addAnnotMethod(NamedCalledFunctionBlock _key, ExecAnnotationMethodBlock _value) {
        allAnnotMethods.addEntry(_key, _value);
    }
    public Iterable<EntryCust<NamedCalledFunctionBlock, ExecAnnotationMethodBlock>> getAnnotMethods() {
        return allAnnotMethods.entryList();
    }
    public void addCtor(ConstructorBlock _key, ExecConstructorBlock _value) {
        allCtors.addEntry(_key, _value);
    }
    public boolean isCtor(int _key) {
        return allCtors.isValidIndex(_key);
    }
    public ExecConstructorBlock getCtor(int _key) {
        return allCtors.getValue(_key);
    }
    public Iterable<EntryCust<ConstructorBlock, ExecConstructorBlock>> getCtors() {
        return allCtors.entryList();
    }

    public void addField(InfoBlock _key, ExecInfoBlock _value) {
        allFields.addEntry(_key, _value);
    }
    public boolean isField(int _key) {
        return allFields.isValidIndex(_key);
    }
    public ExecInfoBlock getField(InfoBlock _key) {
        return getField(_key.getFieldNumber());
    }
    public ExecInfoBlock getField(int _key) {
        return allFields.getValue(_key);
    }
    public Iterable<EntryCust<InfoBlock, ExecInfoBlock>> getFields() {
        return allFields.entryList();
    }
    public void addExplicitField(FieldBlock _key, ExecFieldBlock _value) {
        allExplicitFields.addEntry(_key, _value);
    }
    public Iterable<EntryCust<FieldBlock, ExecFieldBlock>> getExplicitFields() {
        return allExplicitFields.entryList();
    }
    public void addSimpleElementField(ElementBlock _key, ExecElementBlock _value) {
        allSimpleElementFields.addEntry(_key, _value);
    }
    public Iterable<EntryCust<ElementBlock, ExecElementBlock>> getSimpleElementFields() {
        return allSimpleElementFields.entryList();
    }
    public void addInnerElementField(InnerElementBlock _key, ExecInnerElementBlock _value) {
        allInnerElementFields.addEntry(_key, _value);
    }
    public Iterable<EntryCust<InnerElementBlock, ExecInnerElementBlock>> getInnerElementFields() {
        return allInnerElementFields.entryList();
    }
    public void addElementField(InnerTypeOrElement _key, ExecInnerTypeOrElement _value) {
        allElementFields.addEntry(_key, _value);
    }
    public Iterable<EntryCust<InnerTypeOrElement, ExecInnerTypeOrElement>> getElementFields() {
        return allElementFields.entryList();
    }
    public void addNamed(NamedFunctionBlock _key, ExecNamedFunctionBlock _value) {
        allNamed.addEntry(_key, _value);
    }

    public ExecNamedFunctionBlock getNamed(int _key) {
        return allNamed.getValue(_key);
    }
    public Iterable<EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock>> getNamed() {
        return allNamed.entryList();
    }
    public void addOvNamed(NamedFunctionBlock _key, ExecNamedFunctionBlock _value) {
        allOvNamed.addEntry(_key, _value);
    }
    public boolean isOvNamed(int _key) {
        return allOvNamed.isValidIndex(_key);
    }
    public ExecNamedFunctionBlock getOvNamed(NamedCalledFunctionBlock _key) {
        return getOvNamed(_key.getNameOverrideNumber());
    }
    public ExecNamedFunctionBlock getOvNamed(int _key) {
        return allOvNamed.getValue(_key);
    }
    public Iterable<EntryCust<NamedFunctionBlock, ExecNamedFunctionBlock>> getOvNamed() {
        return allOvNamed.entryList();
    }
    public void addFct(MemberCallingsBlock _key, ExecMemberCallingsBlock _value) {
        allFct.addEntry(_key, _value);
    }
    public ExecMemberCallingsBlock getFct(MemberCallingsBlock _key) {
        return allFct.getValue(_key.getNumberFct());
    }
    public Iterable<EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock>> getFcts() {
        return allFct.entryList();
    }
    public void addFctBody(MemberCallingsBlock _key, ExecMemberCallingsBlock _value) {
        allFctBodies.addEntry(_key, _value);
    }

    public Iterable<EntryCust<MemberCallingsBlock, ExecMemberCallingsBlock>> getFctBodies() {
        return allFctBodies.entryList();
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public void setRootBlock(ExecRootBlock _rootBlock) {
        this.rootBlock = _rootBlock;
    }
}
