package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.common.AccessEnum;

public final class DefaultAccess {
    private final DefaultAccessType accClass = new DefaultAccessType();
    private final DefaultAccessType accInterface = new DefaultAccessType();
    private final DefaultAccessType accAnnotation = new DefaultAccessType();
    private final DefaultAccessType accEnum = new DefaultAccessType();
    private final DefaultAccessType accInnerEnum = new DefaultAccessType();
    private final DefaultAccessType accAnonymous = new DefaultAccessType();
    private AccessEnum accOuter = AccessEnum.PUBLIC;

    public DefaultAccessType getAccessInner(BracedBlock _root) {
        BracedBlock root_ = _root;
        if (_root instanceof MemberCallingsBlock) {
            root_ = _root.getParent();
        }
        if (root_ instanceof ClassBlock) {
            return getAccClass();
        }
        if (root_ instanceof InterfaceBlock) {
            return getAccInterface();
        }
        if (root_ instanceof EnumBlock) {
            return getAccEnum();
        }
        if (root_ instanceof InnerElementBlock) {
            return getAccInnerEnum();
        }
        if (root_ instanceof AnnotationBlock) {
            return getAccAnnotation();
        }
        return getAccAnonymous();
    }
    public DefaultAccessType getAccClass() {
        return accClass;
    }

    public DefaultAccessType getAccInterface() {
        return accInterface;
    }

    public DefaultAccessType getAccAnnotation() {
        return accAnnotation;
    }

    public DefaultAccessType getAccEnum() {
        return accEnum;
    }

    public DefaultAccessType getAccInnerEnum() {
        return accInnerEnum;
    }

    public DefaultAccessType getAccAnonymous() {
        return accAnonymous;
    }

    public AccessEnum getAccOuter() {
        return accOuter;
    }

    public void setAccOuter(AccessEnum _accOuter) {
        accOuter = _accOuter;
    }
}
