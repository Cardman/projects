package code.gui.document;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.ReportedMessages;
import code.formathtml.HtmlPage;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.resources.ResourceFiles;

public final class ThreadActions extends AbstractThreadActions {

    private String fileName;

    private boolean directFirst;

    private BeanLgNames stds;


    ThreadActions(RenderedPage _page) {
        super(_page);
    }

    void startPage() {
        getPage().start();
        directFirst = true;
    }

    static ThreadActions inst(RenderedPage _page, BeanNatLgNames _lgNames, String _fileName) {
        ThreadActions t_ = new ThreadActions(_page);
        t_.getPage().start();
        t_.stds = _lgNames;
        t_.fileName = _fileName;
        return t_;
    }

    @Override
    public void run() {
        RenderedPage page_ = getPage();
        if (directFirst) {
            ContextEl ctx_ = page_.getContext();
            if (ctx_ == null) {
                return;
            }
            page_.getNavigation().initializeRendSession(ctx_, page_.getStandards());
            afterAction(ctx_);
            return;
        }
        String content_;
        content_ = ResourceFiles.ressourceFichier(fileName);
        if (content_ == null) {
            ContextEl ctx_ = page_.getContext();
            afterAction(ctx_);
            return;
        }
        RendAnalysisMessages rend_ = new RendAnalysisMessages();
        AbstractFileBuilder fileBuilder_;
        fileBuilder_ = DefaultFileBuilder.newInstance(stds.getContent());
        DualAnalyzedContext du_ = page_.getNavigation().loadConfiguration(content_, "", stds, rend_, fileBuilder_);
        ContextEl ctx_ = du_.getContext();
        page_.setContext(ctx_);
        if (ctx_ != null) {
            HtmlPage htmlPage_ = page_.getNavigation().getHtmlPage();
            htmlPage_.setUrl(-1);
            page_.setFiles();
            ReportedMessages reportedMessages_ = page_.getNavigation().setupRendClassesInit(stds, rend_, du_);
            if (!reportedMessages_.isAllEmptyErrors()) {
                if (page_.getArea() != null) {
                    page_.getArea().append(reportedMessages_.displayErrors());
                }
                finish();
                return;
            }
            page_.getNavigation().initializeRendSession(ctx_, du_.getStds());
        }
        afterAction(ctx_);
    }
}
