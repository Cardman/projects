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
    private AbsBeanRender current;
    private final StringMap<AbsBeanRender> renders = new StringMap<AbsBeanRender>();

    public WrapBeanRender(AbsPanel _c) {
        this.container = _c;
    }
    public void display(AbsBeanRender _rend, AbstractProgramInfos _api, FacadeGame _facade, AbsCommonFrame _fr) {
        current = _rend;
        _rend.setRenders(renders);
        getContainer().removeAll();
        StringMap<String> messages_ = PkDetailContent.file(_api.currentLg());
        search = _api.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
        field = _api.getCompoFactory().newTextField(20);
        FindBeanEvent find_ = new FindBeanEvent(field, _api);
        search.addActionListener(find_);
        AbsScrollPane scrollSession_ = _api.getCompoFactory().newAbsScrollPane();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
        BeanBuilderHelper bu_ = new BeanBuilderHelper(_api, find_);
        bu_.setRenders(renders);
        _rend.build(_facade, new StringMapObject(), bu_);
        find_.setFinding(bu_);
        scrollSession_.setViewportView(_rend.getBuilder().getStack().last());
        _rend.getBuilder().getStack().removeQuicklyLast();
        scrollSession_.validate();
        bu_.setScrollPane(scrollSession_);
        bu_.setFrame(_fr);
        container.add(scrollSession_);
        container.add(field);
        container.add(search);
        for (AbsBeanRender r: renders.values()) {
            r.setRenders(renders);
            r.setFacade(_facade);
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

    public AbsBeanRender getCurrent() {
        return current;
    }

    public StringMap<AbsBeanRender> getRenders() {
        return renders;
    }
}
