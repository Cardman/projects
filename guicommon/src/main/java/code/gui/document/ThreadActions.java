package code.gui.document;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import code.formathtml.HtmlPage;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.BeanLgNames;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.util.StringList;

public final class ThreadActions extends Thread {

    private static final String RETURN_LINE = "\n";

    private RenderedPage page;

    private String anchor;

    private String fileName;

    private boolean usedFirstUrl;

    private BeanLgNames stds;

    private boolean refresh;

    private boolean form;

    private Timer timer;

    public ThreadActions(RenderedPage _page, BeanLgNames _lgNames, String _anchor, String _fileName, boolean _form, boolean _refresh, boolean _usedFirstUrl) {
        page = _page;
        page.start();
        stds = _lgNames;
        anchor = _anchor;
        form = _form;
        refresh = _refresh;
        fileName = _fileName;
        usedFirstUrl = _usedFirstUrl;
        initTimer();
    }

    private void initTimer() {
        page.getNavigation().getSession().setInterrupt(false);
//        if (session.getLabel() != null) {
//            timer = new Timer(DELTA, new Chronometer(session.getLabel(), session, 0));
//            timer.start();
//        }
    }
    @Override
    public void run() {
        if (refresh) {
            page.getNavigation().rendRefresh();
            afterAction();
            return;
        }
        if (usedFirstUrl) {
            String content_ = ResourceFiles.ressourceFichier(fileName);
            page.getNavigation().loadConfiguration(content_, stds, new InterruptImpl());
            if (!page.getNavigation().isError()) {
                HtmlPage htmlPage_ = page.getNavigation().getHtmlPage();
                htmlPage_.setUrl(-1);
                RenderedPage.updateFiles(page.getNavigation());
                page.getNavigation().setupRendClasses();
                page.getNavigation().initializeRendSession();
            }
            afterAction();
            return;
        }
        if (form) {
            page.getNavigation().processRendFormRequest();
            afterAction();
            return;
        }
        page.getNavigation().processRendAnchorRequest(anchor);
        afterAction();
    }
    private void afterAction() {
        if (page.getNavigation().getSession().getContext().getException() != null) {
            if (page.getArea() != null) {
                page.getArea().append(StringList.concat(page.getNavigation().getSession().joinPages(), RETURN_LINE));
            }
            page.getNavigation().getSession().getContext().setException(null);
            finish();
            return;
        }
        if (page.getNavigation().getSession().isInterrupt()) {
            if (page.isProcessing()) {
                finish();
                return;
            }
        }
        if (!page.isProcessing()) {
            return;
        }
        Document doc_ = page.getNavigation().getDocument();
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_);
        SwingUtilities.invokeLater(new WindowPage(metadoc_, page.getScroll(), page));
    }

    void finish() {
        if (timer != null) {
            timer.stop();
        }
        page.finish(false);
    }
}
