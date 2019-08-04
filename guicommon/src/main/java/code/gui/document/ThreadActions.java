package code.gui.document;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.OverridableBlock;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.HtmlPage;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.BeanLgNames;
import code.gui.CustComponent;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ThreadActions implements Runnable {

    private static final String RETURN_LINE = "\n";

    private RenderedPage page;

    private String anchor;

    private String fileName;
    private StringMap<String> fileNames;

    private boolean usedFirstUrl;

    private BeanLgNames stds;

    private boolean refresh;

    private boolean form;

    private Timer timer;

    private String classDbName;

    private String methodName;

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

    public ThreadActions(RenderedPage _page, BeanLgNames _lgNames, String _anchor, String _fileName, StringMap<String> _fileNames, boolean _form, boolean _refresh, boolean _usedFirstUrl) {
        page = _page;
        page.start();
        stds = _lgNames;
        anchor = _anchor;
        form = _form;
        refresh = _refresh;
        fileName = _fileName;
        fileNames = _fileNames;
        usedFirstUrl = _usedFirstUrl;
        initTimer();
    }

    public void setClassDbName(String _classDbName) {
        classDbName = _classDbName;
    }

    public void setMethodName(String _methodName) {
        methodName = _methodName;
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
            String content_;
            if (fileNames != null) {
                content_ = fileNames.getVal(fileName);
            } else {
                content_ = ResourceFiles.ressourceFichier(fileName);
            }
            if (content_ == null) {
                afterAction();
                return;
            }
            page.getNavigation().loadConfiguration(content_, stds, new InterruptImpl());
            if (!page.getNavigation().isError()) {
                HtmlPage htmlPage_ = page.getNavigation().getHtmlPage();
                htmlPage_.setUrl(-1);
                if (fileNames == null) {
                    RenderedPage.updateFiles(page.getNavigation());
                    page.getNavigation().setupRendClasses();
                } else {
                    page.getNavigation().setFiles(fileNames);
                    page.getNavigation().setupRendClasses();
                    Configuration conf_ = page.getNavigation().getSession();
                    Classes cls_ = conf_.getClasses();
                    if (cls_.isEmptyErrors()) {
                        LgNames stds_ = conf_.getStandards();
                        String arrStr_ = PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasString());
                        MethodId id_ = new MethodId(MethodModifier.STATIC, methodName, new StringList(arrStr_,arrStr_));
                        ContextEl ctx_ = conf_.getContext();
                        CustList<OverridableBlock> methods_ = Classes.getMethodBodiesById(ctx_, classDbName, id_);
                        if (!methods_.isEmpty()) {
                            ProcessMethod.initializeClass(classDbName,ctx_);
                            if (ctx_.hasException()) {
                                afterAction();
                                return;
                            }
                            Argument arg_ = new Argument();
                            CustList<Argument> args_ = new CustList<Argument>();
                            int len_ = fileNames.size();
                            Struct[] names_ = new Struct[len_];
                            for (int i = 0; i < len_; i++) {
                                names_[i]= new StringStruct(fileNames.getKey(i));
                            }
                            Struct[] contents_ = new Struct[len_];
                            for (int i = 0; i < len_; i++) {
                                contents_[i]= new StringStruct(fileNames.getValue(i));
                            }
                            ArrayStruct arrNames_ = new ArrayStruct(names_,arrStr_);
                            ArrayStruct arrContents_ = new ArrayStruct(contents_,arrStr_);
                            args_.add(new Argument(arrNames_));
                            args_.add(new Argument(arrContents_));
                            Argument out_ = ProcessMethod.calculateArgument(arg_, classDbName, id_, args_, ctx_, null);
                            if (ctx_.hasException()) {
                                afterAction();
                                return;
                            }
                            page.getNavigation().setDataBaseStruct(out_.getStruct());
                        }
                    }
                }
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
        Configuration conf_ = page.getNavigation().getSession();
        ContextEl context_ = conf_.getContext();
        if (context_.getException() != null) {
            if (page.getArea() != null) {
                Struct exception_ = context_.getException();
                String str_ = conf_.getAdvStandards().processString(new Argument(exception_), conf_);
                page.getArea().append(str_);
            }
            context_.setException(null);
            finish();
            return;
        }
        if (conf_.isInterrupt()) {
            if (page.isProcessing()) {
                finish();
                return;
            }
        }
        if (!page.isProcessing()) {
            return;
        }
        Document doc_ = page.getNavigation().getDocument();
        if (doc_ == null) {
            if (page.getArea() != null) {
                page.getArea().append(conf_.getClasses().displayErrors());
            }
            finish();
            return;
        }
        System.out.println(doc_.export());
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_);
        CustComponent.invokeLater(new WindowPage(metadoc_, page.getScroll(), page));
    }

    void finish() {
        if (timer != null) {
            timer.stop();
        }
        page.finish(false);
    }
}
