package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.blocks.*;
import code.util.IdMap;

public final class Members {
    private ExecRootBlock rootBlock;
    private final IdMap<OverridableBlock,ExecOverridableBlock> allMethods = new IdMap<OverridableBlock,ExecOverridableBlock>();
    private final IdMap<AnnotationMethodBlock,ExecAnnotationMethodBlock> allAnnotMethods = new IdMap<AnnotationMethodBlock,ExecAnnotationMethodBlock>();
    private final IdMap<ConstructorBlock,ExecConstructorBlock> allCtors = new IdMap<ConstructorBlock,ExecConstructorBlock>();
    private final IdMap<InitBlock,ExecInitBlock> allInits = new IdMap<InitBlock,ExecInitBlock>();
    private final IdMap<InfoBlock,ExecInfoBlock> allFields = new IdMap<InfoBlock,ExecInfoBlock>();
    private final IdMap<FieldBlock,ExecFieldBlock> allExplicitFields = new IdMap<FieldBlock,ExecFieldBlock>();
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> allInnerElementFields = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final IdMap<InnerTypeOrElement,ExecInnerTypeOrElement> allElementFields = new IdMap<InnerTypeOrElement,ExecInnerTypeOrElement>();
    private final IdMap<ElementBlock,ExecElementBlock> allSimpleElementFields = new IdMap<ElementBlock,ExecElementBlock>();
    private final IdMap<NamedFunctionBlock,ExecNamedFunctionBlock> allNamed = new IdMap<NamedFunctionBlock,ExecNamedFunctionBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFct = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
    private final IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFctBodies = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();

    public IdMap<AnnotationMethodBlock, ExecAnnotationMethodBlock> getAllAnnotMethods() {
        return allAnnotMethods;
    }

    public IdMap<ConstructorBlock, ExecConstructorBlock> getAllCtors() {
        return allCtors;
    }

    public IdMap<InfoBlock, ExecInfoBlock> getAllFields() {
        return allFields;
    }

    public IdMap<InitBlock, ExecInitBlock> getAllInits() {
        return allInits;
    }

    public IdMap<OverridableBlock, ExecOverridableBlock> getAllMethods() {
        return allMethods;
    }

    public IdMap<FieldBlock, ExecFieldBlock> getAllExplicitFields() {
        return allExplicitFields;
    }

    public IdMap<ElementBlock, ExecElementBlock> getAllSimpleElementFields() {
        return allSimpleElementFields;
    }

    public IdMap<InnerElementBlock, ExecInnerElementBlock> getAllInnerElementFields() {
        return allInnerElementFields;
    }

    public IdMap<InnerTypeOrElement, ExecInnerTypeOrElement> getAllElementFields() {
        return allElementFields;
    }

    public IdMap<NamedFunctionBlock, ExecNamedFunctionBlock> getAllNamed() {
        return allNamed;
    }

    public IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> getAllFct() {
        return allFct;
    }

    public IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> getAllFctBodies() {
        return allFctBodies;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public void setRootBlock(ExecRootBlock _rootBlock) {
        this.rootBlock = _rootBlock;
    }
}
