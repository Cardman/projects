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
        FindBeanEvent find_ = new FindBeanEvent(field, _rend, _api);
        search.addActionListener(find_);
        AbsScrollPane scrollSession_ = _api.getCompoFactory().newAbsScrollPane();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
        scrollSession_.setViewportView(_rend.build(_api,_facade,find_, new StringMapObject()));
        scrollSession_.validate();
        _rend.setScrollPane(scrollSession_);
        container.add(scrollSession_);
        container.add(field);
        container.add(search);
        for (AbsBeanRender r: renders.values()) {
            r.setRenders(renders);
            r.setScrollPane(scrollSession_);
            r.setEvent(find_);
            r.setFacade(_facade);
            r.setFactory(_api);
            r.setFrame(_fr);
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
