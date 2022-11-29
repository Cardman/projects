package code.bean.nat.exec;

import code.bean.nat.NatHtmlPage;

public final class NatRendStackCallAdv extends NatRendStackCall {
    private final NatHtmlPage htmlPage = new NatHtmlPage();
    private final NatFormParts natFormParts;
    public NatRendStackCallAdv() {
        natFormParts = new NatFormParts();
    }

    public NatHtmlPage getHtmlPage() {
        return htmlPage;
    }

    public NatFormParts getFormParts() {
        return natFormParts;
    }

}
