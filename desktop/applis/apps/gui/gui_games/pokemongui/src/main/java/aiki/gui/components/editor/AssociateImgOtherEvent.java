package aiki.gui.components.editor;

import code.gui.events.*;

public class AssociateImgOtherEvent implements AbsActionListener {
    private final CrudGeneFormEntImgUniq window;
    private final ImgFieldRetriever key;
    public AssociateImgOtherEvent(CrudGeneFormEntImgUniq _w, ImgFieldRetriever _k) {
        window = _w;
        key = _k;
    }

    @Override
    public void action() {
        window.apply(key);
    }
}
