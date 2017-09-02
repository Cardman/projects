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

    private final String name;

    private final String packageName;

    private final StringList superInterfaces;

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final ObjectMap<FctConstraints, String> defaultMethods = new ObjectMap<FctConstraints, String>();

    private final AccessEnum access;

    public InterfaceBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        superInterfaces = new StringList();
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            superInterfaces.add(_el.getAttribute(ATTRIBUTE_CLASS+i_));
            i_++;
        }
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
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
        StringList all_ = getAllSuperClasses();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mCl_ = (MethodBlock) b;
                if (mCl_.isStaticMethod()) {
                    continue;
                }
                for (String s: all_) {
                    if (StringList.quickEq(s, Object.class.getName())) {
                        continue;
                    }
                    FctConstraints mDer_ = ((MethodBlock) b).getConstraints();
                    MethodBlock m_ = _context.getClasses().getMethodBody(s, mDer_);
                    if (m_ == null) {
                        continue;
                    }
                    if (m_.isStaticMethod()) {
                        continue;
                    }
                    if (_context.getClasses().canAccessMethod(getFullName(), s, mDer_)) {
                        ((MethodBlock) b).getOverridenClasses().add(s);
                        break;
                    }
                }
            }
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mDer_ = (MethodBlock) b;
                mDer_.getAllOverridenClasses().addAllElts(mDer_.getOverridenClasses());
                for (String s: mDer_.getOverridenClasses()) {
                    MethodBlock mBase_ = _context.getClasses().getMethodBody(s, mDer_.getConstraints());
                    mDer_.getAllOverridenClasses().addAllElts(mBase_.getAllOverridenClasses());
                }
            }
        }
    }

    @Override
    public StringList getDirectSuperTypes() {
        return new StringList(superInterfaces);
    }

    @Override
    public AccessEnum getAccess() {
        return access;
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
        return TAG_INTERFACE;
    }

    @Override
    public StringList getDirectSuperClasses() {
        StringList classes_ = new StringList(superInterfaces);
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
}
