package aiki.beans;

import aiki.beans.simulation.*;
import aiki.facade.*;
import code.bean.*;
import code.formathtml.render.*;
import code.util.*;

public abstract class IntBeanBuilderHelper extends IntBeanBuilderHelperCommon {
    private StringMap<BeanRenderWithAppName> renders = new StringMap<BeanRenderWithAppName>();

    private IntBeanGeneInput genInput;
    private FacadeGame facade;
    private final IdList<IntBeanAction> anchors = new IdList<IntBeanAction>();
    private final StringMapObject forms = new StringMapObject();
    protected IntBeanBuilderHelper() {
    }

    public void clearAnchors() {
        anchors.clear();
    }

    public void formatMessageAnc(String _with,IntBeanAction _e,String _file, String _key, String... _values) {
        String txt_ = formatMessageRend(_with,_file, _key, _values);
        formatMessageDir(txt_,_e);
    }
    public PageFormSimu reset(PageFormSimu _page) {
        CustList<MetaSearchableContent> n_ = new CustList<MetaSearchableContent>();
        for (MetaSearchableContent o: getContent().getMetaSearchableContents()) {
            if (o.getFormGroup() != _page.getFormGroup()) {
                n_.add(o);
            }
        }
        setFormGroup(_page.getFormGroup());
        getContent().getMetaSearchableContents().clear();
        getContent().getMetaSearchableContents().addAllElts(n_);
        return _page;
    }
    public PageFormSimu end(PageFormSimu _page) {
        CustList<MetaSearchableContent> n_ = new CustList<MetaSearchableContent>();
        for (MetaSearchableContent o: getContent().getMetaSearchableContents()) {
            if (o.getFormGroup() < _page.getFormGroup()) {
                n_.add(o);
            }
        }
        for (MetaSearchableContent o: getContent().getMetaSearchableContents()) {
            if (o.getFormGroup() == _page.getFormGroup()) {
                n_.add(o);
            }
        }
        for (MetaSearchableContent o: getContent().getMetaSearchableContents()) {
            if (o.getFormGroup() > _page.getFormGroup()) {
                n_.add(o);
            }
        }
        getContent().getMetaSearchableContents().clear();
        getContent().getMetaSearchableContents().addAllElts(n_);
        return _page;
    }
    public PageFormSimu initPageForm(SimulationBean _s) {
        super.initPage();
        getContent().setFormGroup(getContent().getFormGroup()+1);
        getContent().setRowGroup(0);
        getContent().setPartGroup(0);
        PageFormSimu p_ = new PageFormSimu(_s);
        p_.setFormGroup(getFormGroup());
        return p_;
    }
    public void feedParentForm() {
        feedParents();
        setFormGroup(getFormGroup()+1);
        setRowGroup(0);
        setPartGroup(0);
    }
    @Override
    public String getLanguage() {
        return getFacade().getLanguage();
    }

    public abstract void formatMessageDir(String _txt, IntBeanAction _e);
    public abstract void formatMessageDirCts(String _txt, IntBeanAction _e);
    public abstract void addImgCtsAnc(int[][] _img, String _tip, IntBeanAction _e);
    public abstract void formatMessageDirCtsHeader(String _txt);

    public void build(IntBeanAction _action) {
        build(_action.actionBean());
        if (_action instanceof IntBeanActionPart) {
            ((IntBeanActionPart)_action).special();
        }
    }
    public void build(String _dest) {
        BeanRenderWithAppName target_ = getRenders().getVal(_dest);
        if (target_ == null) {
            return;
        }
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
