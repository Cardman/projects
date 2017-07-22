package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.exceptions.CyclicCallingException;
import code.expressionlanguage.methods.exceptions.UndefinedSuperConstructorException;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.graphs.Graph;

public final class ClassBlock extends BracedBlock implements RootedBlock {

    private final String name;

    private final String packageName;

    private final String superClass;

    private final StringList allSuperClasses = new StringList();

    private final EqList<MethodId> normalMethods = new EqList<MethodId>();

    private final ObjectNotNullMap<ClassMethodId, Boolean> availableMethods = new ObjectNotNullMap<ClassMethodId, Boolean>();

    private final AccessEnum access;

    public ClassBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        String superClass_ = _el.getAttribute(ATTRIBUTE_SUPER_CLASS);
        if (superClass_.trim().isEmpty()) {
            superClass_ = Object.class.getName();
        }
        superClass = superClass_;
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
    }

    public ObjectNotNullMap<ClassMethodId, Boolean> getAvailableMethods() {
        return availableMethods;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public EqList<MethodId> getNormalMethods() {
        return normalMethods;
    }

    @Override
    public String getSuperClass() {
        return superClass;
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }

    @Override
    public void validateConstructors(ContextEl _cont) {
        boolean opt_ = optionalCallConstr(_cont);
        ClassMetaInfo curMeta_ = _cont.getClasses().getClassMetaInfo(getFullName());
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> c_;
        c_ = curMeta_.getConstructors();
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            b_.setupInstancingStep(_cont);
        }
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            if (b_.implicitConstr() && !opt_) {
                throw new UndefinedSuperConstructorException(_cont.joinPages());
            }
        }
//        List.<List<?>>equalsSet(null,null);
        EqList<ConstructorId> l_ = new EqList<ConstructorId>();
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            if (b_.getConstIdSameClass() != null) {
                l_.add(e.getKey());
            }
        }
//        //validate calling of constructors of the current class
//        for (ConstructorId f: l_) {
//            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), f);
//            ConstructorId id_ = b_.getConstIdSameClass();
//            if (!_cont.getClasses().getClassMetaInfo(getFullName()).getConstructors().contains(id_)) {
//                throw new UndefinedConstructorException(id_.getSignature()+RETURN_LINE+_cont.joinPages());
//            }
//        }
//        //validate calling of super constructors
//        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
//            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
//            if (b_.getConstIdSameClass() != null) {
//                continue;
//            }
//            ConstructorId super_ = b_.getConstId();
//            if (super_ == null) {
//                continue;
//            }
//            String superClass_ = _cont.getClasses().getClassMetaInfo(getFullName()).getSuperClass();
//            if (superClass_.eq(Object.class.getName())) {
////                if (super_.getClassNames().isEmpty()) {
////                    continue;
////                }
//                throw new UndefinedConstructorException(super_.getSignature()+RETURN_LINE+_cont.joinPages());
//            }
//            ClassMetaInfo meta_ = _cont.getClasses().getClassMetaInfo(superClass_);
//            if (!meta_.getConstructors().contains(super_)) {
//                throw new UndefinedConstructorException(super_.getSignature()+RETURN_LINE+_cont.joinPages());
//            }
//        }
        Graph<ConstructorEdge> graph_;
        graph_ = new Graph<ConstructorEdge>();
        for (ConstructorId f: l_) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), f);
            ConstructorId co_ = b_.getConstIdSameClass();
            ConstructorEdge f_ = new ConstructorEdge(f);
            ConstructorEdge t_ = new ConstructorEdge(co_);
            graph_.addSegment(f_, t_);
        }
        EqList<ConstructorEdge> cycle_ = graph_.elementsCycle();
        if (!cycle_.isEmpty()) {
            //TODO souligner
            throw new CyclicCallingException(_cont.joinPages());
        }
//        List<ConstructorId> called_ = new List<ConstructorId>();
//        for (ConstructorId f: l_) {
//            if (called_.containsObj(f)) {
//                continue;
//            }
//            called_.add(f);
//            ConstructorId f_ = f;
//            while (true) {
//                ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), f_);
//                ConstructorId o_ = b_.getConstIdSameClass();
//                if (o_ == null) {
//                    break;
//                }
//                if (called_.containsObj(o_)) {
//                    throw new CyclicCallingException(_cont.joinPages());
//                }
//                called_.add(o_);
//                f_ = o_;
//            }
//        }
    }

    private boolean optionalCallConstr(ContextEl _cont) {
        ClassMetaInfo clMeta_ = _cont.getClasses().getClassMetaInfo(superClass);
        if (clMeta_ == null) {
            return true;
        }
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> m_;
        m_ = clMeta_.getConstructors();
        if (m_.isEmpty()) {
            return true;
        }
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: m_.entryList()) {
            if (e.getKey().getClassNames().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_SUPER_CLASS, superClass);
        return tr_;
    }

    @Override
    public String getFullName() {
        return packageName+DOT+name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_CLASS;
    }

    @Override
    public StringList getDirectSuperClasses() {
        StringList classes_ = new StringList();
        classes_.add(superClass);
        return classes_;
    }

    @Override
    public RootedBlock belong() {
        return this;
    }
}
