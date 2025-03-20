package aiki.beans;

import aiki.facade.*;
import code.bean.*;
import code.util.*;

public abstract class IntBeanBuilderHelper extends IntBeanBuilderHelperCommon {
    private StringMap<BeanRenderWithAppName> renders = new StringMap<BeanRenderWithAppName>();

    private IntBeanGeneInput genInput;
    private FacadeGame facade;
    private final IdList<IntBeanAction> anchors = new IdList<IntBeanAction>();
    private final StringMapObject forms = new StringMapObject();
    protected IntBeanBuilderHelper() {
    }

    @Override
    public void breakNext() {
        nextPart();
    }

    @Override
    public void nextPart() {
        breakLine();
        super.nextPart();
    }

    public void clearAnchors() {
        anchors.clear();
    }

    public void formatMessageAnc(String _with,IntBeanAction _e,String _file, String _key, String... _values) {
        String txt_ = formatMessageRend(_with,_file, _key, _values);
        formatMessageDir(txt_,_e);
    }

    @Override
    public String getLanguage() {
        return getFacade().getLanguage();
    }

    public abstract void formatMessageDir(String _txt, IntBeanAction _e);
    public abstract void formatMessageDirCts(String _txt, IntBeanAction _e);
    public abstract void breakLine();
    public abstract void addImgCtsAnc(int[][] _img, String _tip, IntBeanAction _e);

    public void build(IntBeanAction _action) {
        build(_action.actionBean());
    }
    public void build(String _dest) {
        BeanRenderWithAppName target_ = getRenders().getVal(_dest);
        clearAnchors();
        getOrderedLists().clear();
        initPage();
        target_.setBuilder(this);
        target_.build(target_.getFacade());
        decr();
    }

    public IntBeanChgSubmit button(String _txt) {
        return getGenInput().newSubmit(_txt);
    }
    public IntBeanGeneInput getGenInput() {
        return genInput;
    }

    protected void setGenInput(IntBeanGeneInput _g) {
        this.genInput = _g;
    }

    public StringMapObject getForms() {
        return forms;
    }

    public StringMap<BeanRenderWithAppName> getRenders() {
        return renders;
    }

    public void setRenders(StringMap<BeanRenderWithAppName> _r) {
        this.renders = _r;
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public void setFacade(FacadeGame _f) {
        this.facade = _f;
    }


    public IdList<IntBeanAction> getAnchors() {
        return anchors;
    }
}
