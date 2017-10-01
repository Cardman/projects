package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.xml.RowCol;

public final class InterfaceBlock extends RootBlock {

    private final StringList superInterfaces;

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final ObjectMap<FctConstraints, String> defaultMethods = new ObjectMap<FctConstraints, String>();

    public InterfaceBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        superInterfaces = new StringList();
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            superInterfaces.add(_el.getAttribute(ATTRIBUTE_CLASS+i_));
            i_++;
        }
    }

    @Override
    protected void validateClassNames(ContextEl _context) {
        CustList<Block> bl_ = Classes.getDirectChildren(this);
        for (Block b: bl_) {
            if (b instanceof MethodBlock) {
                MethodBlock m_ = (MethodBlock) b;
                if (m_.getAccess() != AccessEnum.PUBLIC) {
                    //TODO protected and package method cases
                    BadAccessMethod err_;
                    err_ = new BadAccessMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(m_.getAttributes().getVal(ATTRIBUTE_ACCESS));
                    err_.setId(m_.getId());
                    _context.getClasses().getErrorsDet().add(err_);
                }
                continue;
            }
            if (b instanceof InfoBlock) {
                continue;
            }
            if (b instanceof AloneBlock) {
                continue;
            }
            RowCol where_ = ((Block)b).getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
            String tagName_ = ((Block)b).getTagName();
            UnexpectedTagName unexp_ = new UnexpectedTagName();
            unexp_.setFileName(getFullName());
            unexp_.setFoundTag(tagName_);
            unexp_.setRc(where_);
            _context.getClasses().getErrorsDet().add(unexp_);
        }
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        StringList all_ = getAllGenericSuperTypes(classesRef_);
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mCl_ = (MethodBlock) b;
                if (mCl_.isStaticMethod()) {
                    continue;
                }
                addClass(getAllOverridingMethods(), mCl_.getId(), getFullDefinition());
                for (String s: all_) {
                    if (StringList.quickEq(s, Object.class.getName())) {
                        continue;
                    }
                    CustList<MethodBlock> methods_ ;
                    methods_ = classesRef_.getMethodBodiesByFormattedId(s, mCl_.getId());
                    if (methods_.isEmpty()) {
                        continue;
                    }
                    MethodBlock m_ = methods_.first();
                    if (m_.isStaticMethod()) {
                        continue;
                    }
                    if (classesRef_.canAccess(getFullName(), m_)) {
                        ((MethodBlock) b).getOverridenClasses().add(s);
                        break;
                    }
                }
            }
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mDer_ = (MethodBlock) b;
                if (mDer_.isStaticMethod()) {
                    continue;
                }
                mDer_.getAllOverridenClasses().addAllElts(mDer_.getOverridenClasses());
                for (String s: mDer_.getOverridenClasses()) {
                    MethodBlock mBase_ = classesRef_.getMethodBodiesByFormattedId(s, mDer_.getId()).first();
                    if (mBase_.isStaticMethod()) {
                        continue;
                    }
                    mDer_.getAllOverridenClasses().addAllElts(mBase_.getAllOverridenClasses());
                }
                for (String s: getAllGenericSuperTypes(classesRef_)) {
                    CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesByFormattedId(s, mDer_.getId());
                    if (mBases_.isEmpty()) {
                        continue;
                    }
                    MethodBlock mBase_ = mBases_.first();
                    if (mBase_.isStaticMethod()) {
                        continue;
                    }
                    if (!classesRef_.canAccess(getFullName(), mBase_)) {
                        continue;
                    }
                    addClass(getAllOverridingMethods(), mDer_.getId(), s);
                }
            }
        }
    }

    @Override
    public StringList getDirectGenericSuperTypes() {
        return new StringList(superInterfaces);
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }
    
    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        int i_ = 0;
        for (String t: superInterfaces) {
            tr_.put(ATTRIBUTE_CLASS+i_, t);
            i_++;
        }
        return tr_;
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
        return TAG_INTERFACE;
    }

    @Override
    public StringList getDirectGenericSuperClasses() {
        StringList classes_ = new StringList(superInterfaces);
        if (superInterfaces.isEmpty()) {
            classes_.add(Object.class.getName());
        }
        return classes_;
    }

    @Override
    public StringList getDirectSuperClasses() {
        StringList classes_ = new StringList();
        for (String s: superInterfaces) {
            int index_ = s.indexOf(LT);
            if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
                classes_.add(s.substring(CustList.FIRST_INDEX, index_));
            } else {
                classes_.add(s);
            }
        }
        if (superInterfaces.isEmpty()) {
            classes_.add(Object.class.getName());
        }
        return classes_;
    }

    @Override
    public RootBlock belong() {
        return this;
    }

    @Override
    public boolean isFinalType() {
        return false;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public boolean mustImplement() {
        return false;
    }

    @Override
    public ObjectMap<FctConstraints, String> getDefaultMethods() {
        return defaultMethods;
    }

    @Override
    public StringList getAllInterfaces() {
        return getAllSuperClasses();
    }

    @Override
    public StringList getAllGenericSuperClasses(Classes _classes) {
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = StringList.getAllTypes(s).first();
            if (_classes.getClassBody(base_) instanceof InterfaceBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        return allGenericSuperClasses_;
    }

    @Override
    public StringList getAllGenericInterfaces(Classes _classes) {
        return getAllGenericSuperClasses(_classes);
    }
}
