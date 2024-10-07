package code.sml;

public final class TestedSmlImgAnimAttr extends AbsImgAnimAttr {
    public TestedSmlImgAnimAttr(String _name) {
        super(_name);
    }

    @Override
    public Attr copy() {
        TestedSmlImgAnimAttr at_ = new TestedSmlImgAnimAttr(getName());
        at_.setAnim(getAnim());
        return at_;
    }

    @Override
    public String escape() {
        return "";
    }
}
