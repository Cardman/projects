package code.renders;

import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.gui.document.RenderedPage;

public final class RenderIssuer implements AbstractIssuer {
    private final RenderedPage session;

    public RenderIssuer(RenderedPage _s) {
        this.session = _s;
    }

    @Override
    public void log(String _info) {
        if (session.getArea() != null) {
            session.getArea().append(_info);
        }
    }
}
