package code.gui.document;

import aiki.beans.*;
import aiki.facade.*;
import aiki.gui.components.*;
import aiki.sml.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;

public final class WrapBeanRender {
    private final AbsPanel container;
    private AbsButton search;
    private AbsTextField field;
    private BeanRenderWithAppName current;
    private final StringMap<BeanRenderWithAppName> renders = new StringMap<BeanRenderWithAppName>();

    public WrapBeanRender(AbsPanel _c) {
        this.container = _c;
    }
    public void display(BeanRenderWithAppName _rend, AbstractProgramInfos _api, FacadeGame _facade, AbsCommonFrame _fr) {
        current = _rend;
        getContainer().removeAll();
        StringMap<String> messages_ = PkDetailContent.file(_api.currentLg());
        search = _api.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
        field = _api.getCompoFactory().newTextField(20);
        FindBeanEvent find_ = new FindBeanEvent(field, _api);
        search.addActionListener(find_);
        AbsScrollPane scrollSession_ = _api.getCompoFactory().newAbsScrollPane();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
        BeanBuilderHelper bu_ = new BeanBuilderHelper(_api, find_);
        bu_.setFacade(_facade);
        bu_.setTranslations(_api.getTranslations());
        bu_.setRenders(renders);
        bu_.initPage();
        bu_.setBackgroundBody();
        _rend.setFacade(_facade);
        _rend.setLanguage(_facade.getLanguage());
        _rend.setBuilder(bu_);
        _rend.build(_facade);
        bu_.setScrollPane(scrollSession_);
        bu_.setFrame(_fr);
        find_.setFinding(bu_);
        scrollSession_.setViewportView(bu_.getStack().last());
        bu_.getStack().removeQuicklyLast();
        bu_.decr();
        scrollSession_.validate();
        container.add(scrollSession_);
        container.add(field);
        container.add(search);
        for (BeanRenderWithAppName r: renders.values()) {
            r.setLanguage(_facade.getLanguage());
            r.setFacade(_facade);
            r.setBuilder(bu_);
        }
    }

    public AbsPanel getContainer() {
        return container;
    }

    public AbsTextField getField() {
        return field;
    }

    public AbsButton getSearch() {
        return search;
    }

    public BeanRenderWithAppName getCurrent() {
        return current;
    }

    public StringMap<BeanRenderWithAppName> getRenders() {
        return renders;
    }
}
