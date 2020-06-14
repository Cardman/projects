package code.gui.document;

import javax.swing.Timer;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErroneousStruct;
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
    private String lgCode = "";

    private String fileName;
    private StringMap<String> fileNames;

    private boolean directFirst;
    private boolean usedFirstUrl;

    private BeanLgNames stds;

    private boolean form;

    private Timer timer;

    private String classDbName;

    private String methodName;

    private ThreadActions(){}
    ThreadActions(RenderedPage _page) {
        page = _page;
        page.start();
        directFirst = true;
    }

    static ThreadActions inst(RenderedPage _page, BeanLgNames _lgNames, String _anchor, String _fileName, boolean _form, boolean _usedFirstUrl) {
        ThreadActions t_ = new ThreadActions();
        t_.page = _page;
        t_.page.start();
        t_.stds = _lgNames;
        t_.anchor = _anchor;
        t_.form = _form;
        t_.fileName = _fileName;
        t_.usedFirstUrl = _usedFirstUrl;
        return t_;
    }

    static ThreadActions inst(RenderedPage _page, BeanLgNames _lgNames, String _lgCode, String _anchor, String _fileName, StringMap<String> _fileNames) {
        ThreadActions t_ = new ThreadActions();
        t_.page = _page;
        t_.page.start();
        t_.stds = _lgNames;
        t_.lgCode = _lgCode;
        t_.anchor = _anchor;
        t_.form = false;
        t_.fileName = _fileName;
        t_.fileNames = _fileNames;
        t_.usedFirstUrl = true;
        return t_;
    }
    void setClassDbName(String _classDbName) {
        classDbName = _classDbName;
    }

    void setMethodName(String _methodName) {
        methodName = _methodName;
    }

    @Override
    public void run() {
        if (directFirst) {
            page.getNavigation().initializeRendSession();
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
            page.getNavigation().loadConfiguration(content_,lgCode, stds);
            if (!page.getNavigation().isError()) {
                HtmlPage htmlPage_ = page.getNavigation().getHtmlPage();
                htmlPage_.setUrl(-1);
                if (fileNames == null) {
                    page.updateFiles();
                } else {
                    page.getNavigation().setFiles(fileNames);
                    page.getNavigation().setupRendClassesInit();
                    Configuration conf_ = page.getNavigation().getSession();
                    if (conf_.isEmptyErrors()) {
                        LgNames stds_ = conf_.getStandards();
                        String arrStr_ = PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasString());
                        MethodId id_ = new MethodId(MethodAccessKind.STATIC, methodName, new StringList(arrStr_,arrStr_));
                        ContextEl ctx_ = conf_.getContext();
                        CustList<NamedFunctionBlock> methods_ = Classes.getMethodBodiesById(ctx_, classDbName, id_);
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
        CallingState exc_ = context_.getCallingState();
        if (exc_ instanceof Struct) {
            if (page.getArea() != null) {
                Struct exception_ = (Struct) exc_;
                if (exception_ instanceof ErroneousStruct) {
                    ArrayStruct fullStack_ = ((ErroneousStruct) exception_).getFullStack();
                    page.getArea().append(((ErroneousStruct) exception_).getStringRep(conf_.getContext(),fullStack_.getInstance()));
                } else {
                    context_.setException(null);
                    String str_ = conf_.getAdvStandards().processString(new Argument(exception_), conf_);
                    page.getArea().append(str_);
                }
            }
            context_.setException(null);
            finish();
            return;
        }
        if (!page.isProcessing()) {
            return;
        }
        Document doc_ = page.getNavigation().getDocument();
        if (doc_ == null) {
            if (page.getArea() != null) {
                page.getArea().append(conf_.getClasses().displayErrors());
                page.getArea().append("\n");
                page.getArea().append(conf_.getErrorsDet().display());
            }
            finish();
            return;
        }
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_,page.getNavigation().getSession().getRendKeyWords());
        CustComponent.invokeLater(new WindowPage(metadoc_, page.getScroll(), page));
    }

    private void finish() {
        if (timer != null) {
            timer.stop();
        }
        page.finish();
    }
}
