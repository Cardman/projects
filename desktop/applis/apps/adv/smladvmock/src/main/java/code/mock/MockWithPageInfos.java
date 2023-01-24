package code.mock;

import code.sml.HtmlPage;
import code.sml.HtmlPageInt;
import code.sml.WithPageInfos;

public final class MockWithPageInfos implements WithPageInfos {
    private final HtmlPage htmlPage = new HtmlPage();

    @Override
    public HtmlPageInt getPage() {
        return htmlPage;
    }
}
