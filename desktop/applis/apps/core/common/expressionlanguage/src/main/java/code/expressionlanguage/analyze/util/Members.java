package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.blocks.*;
import code.util.IdMap;

public final class Members {
    private ExecRootBlock rootBlock;
    private IdMap<OverridableBlock,ExecOverridableBlock> allMethods = new IdMap<OverridableBlock,ExecOverridableBlock>();
    private IdMap<AnnotationMethodBlock,ExecAnnotationMethodBlock> allAnnotMethods = new IdMap<AnnotationMethodBlock,ExecAnnotationMethodBlock>();
    private IdMap<ConstructorBlock,ExecConstructorBlock> allCtors = new IdMap<ConstructorBlock,ExecConstructorBlock>();
    private IdMap<InitBlock,ExecInitBlock> allInits = new IdMap<InitBlock,ExecInitBlock>();
    private IdMap<InfoBlock,ExecInfoBlock> allFields = new IdMap<InfoBlock,ExecInfoBlock>();
    private IdMap<FieldBlock,ExecFieldBlock> allExplicitFields = new IdMap<FieldBlock,ExecFieldBlock>();
    private IdMap<InnerElementBlock,ExecInnerElementBlock> allInnerElementFields = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private IdMap<ElementBlock,ExecElementBlock> allSimpleElementFields = new IdMap<ElementBlock,ExecElementBlock>();
    private IdMap<AnnotableBlock,ExecAnnotableBlock> allAnnotables = new IdMap<AnnotableBlock,ExecAnnotableBlock>();
    private IdMap<RootBlock,ExecRootBlock> allAnnotablesRoots = new IdMap<RootBlock,ExecRootBlock>();
    private IdMap<NamedFunctionBlock,ExecNamedFunctionBlock> allNamed = new IdMap<NamedFunctionBlock,ExecNamedFunctionBlock>();
    private IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFct = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();

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

    public IdMap<AnnotableBlock, ExecAnnotableBlock> getAllAnnotables() {
        return allAnnotables;
    }

    public IdMap<RootBlock, ExecRootBlock> getAllAnnotablesRoots() {
        return allAnnotablesRoots;
    }

    public IdMap<NamedFunctionBlock, ExecNamedFunctionBlock> getAllNamed() {
        return allNamed;
    }

    public IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> getAllFct() {
        return allFct;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public void setRootBlock(ExecRootBlock rootBlock) {
        this.rootBlock = rootBlock;
    }
}
