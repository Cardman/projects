package code.gui.document;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.ReportedMessages;
import code.formathtml.HtmlPage;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.util.BeanLgNames;
import code.resources.ResourceFiles;

public final class ThreadActions extends AbstractThreadActions {

    private String fileName;

    private boolean directFirst;

    private BeanLgNames stds;


    private ThreadActions(){}
    private boolean ok;
    ThreadActions(RenderedPage _page, boolean _ok) {
        setPage(_page);
        getPage().start();
        directFirst = true;
        ok = _ok;
    }

    static ThreadActions inst(RenderedPage _page, BeanNatLgNames _lgNames, String _fileName) {
        ThreadActions t_ = new ThreadActions();
        t_.setPage(_page);
        t_.getPage().start();
        t_.stds = _lgNames;
        t_.fileName = _fileName;
        return t_;
    }

    @Override
    public void run() {
        if (directFirst) {
            if (!ok) {
                return;
            }
            getPage().getNavigation().initializeRendSession();
            afterAction();
            return;
        }
        String content_;
        content_ = ResourceFiles.ressourceFichier(fileName);
        if (content_ == null) {
            afterAction();
            return;
        }
        RendAnalysisMessages rend_ = new RendAnalysisMessages();
        AbstractFileBuilder fileBuilder_;
        fileBuilder_ = new DefaultFileBuilder(stds.getContent());
        AnalyzedPageEl page_ = getPage().getNavigation().loadConfiguration(content_, "", stds, rend_, fileBuilder_);
        if (!getPage().getNavigation().isError()) {
            HtmlPage htmlPage_ = getPage().getNavigation().getHtmlPage();
            htmlPage_.setUrl(-1);
            getPage().setFiles();
            ReportedMessages reportedMessages_ = getPage().getNavigation().setupRendClassesInit(page_, stds, rend_);
            if (!reportedMessages_.isAllEmptyErrors()) {
                if (getPage().getArea() != null) {
                    getPage().getArea().append(reportedMessages_.displayErrors());
                }
                finish();
                return;
            }
            getPage().getNavigation().initializeRendSession();
        }
        afterAction();
    }
}
