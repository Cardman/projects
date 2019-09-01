package code.expressionlanguage.gui.unit;

import code.expressionlanguage.LgNamesUtils;
import code.expressionlanguage.RunnableContextEl;
import code.expressionlanguage.RunningTest;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.TextField;
import code.gui.events.QuittingEvent;

import java.awt.*;

public final class MainWindow extends GroupFrame {
    private Panel contentPane;
    private Panel form;
    private TextLabel file;
    private TextField fileName;
    private TextLabel content;
    private TextArea conf;
    private PlainButton launch;
    private Panel progressing;
    private TextLabel doneTests;
    private TextLabel doneTestsCount;

    private TextLabel method;
    private TextLabel currentMethod;
    private ProgressBar progressBar;

    private Thread th;
    protected MainWindow(String _lg) {
        super(_lg);
        contentPane = Panel.newPageBox();
        form = Panel.newGrid(0,2);
        file = new TextLabel("file");
        form.add(file);
        fileName = new TextField(32);
        form.add(fileName);
        content = new TextLabel("configuration");
        form.add(content);
        conf = new TextArea(64,64);
        ScrollPane scr_ = new ScrollPane(conf);
        scr_.setPreferredSize(new Dimension(256,64));
        form.add(scr_);
        launch = new PlainButton("OK");
        launch.addActionListener(new ListenerLaunchTests(this));
        form.add(launch);
        form.add(new TextLabel(""));
        contentPane.add(form);
        progressing = Panel.newPageBox();
        doneTests = new TextLabel("tests:");
        progressing.add(doneTests);
        doneTestsCount = new TextLabel("");
        progressing.add(doneTestsCount);
        method = new TextLabel("method:");
        progressing.add(method);
        currentMethod = new TextLabel("");
        progressing.add(currentMethod);
        progressBar = new ProgressBar();
        progressing.add(progressBar);
        contentPane.add(progressing);
        setContentPane(contentPane);
        pack();
        setVisible(true);
        addWindowListener(new QuittingEvent(this));
    }

    @Override
    public void quit() {
        if (th != null) {
            while (th.isAlive()) {
                continue;
            }
        }
        dispose();
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {

    }

    public void process() {
        String txt_ = conf.getText().trim();
        if (!txt_.isEmpty()) {
            RunningTest r_ = RunningTest.newFromContent(txt_, new ProgressingTestsImpl(this));
            Thread th_ = new Thread(r_);
            th = th_;
            th_.start();
        } else {
            String fileName_ = fileName.getText();
            RunningTest r_ = RunningTest.newFromFile(fileName_, new ProgressingTestsImpl(this));
            Thread th_ = new Thread(r_);
            th = th_;
            th_.start();
        }
    }
    public void showProgress(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count) {
        String infoTest_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTest();
        String infoTestDone_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTestDone();
        String infoTestCount_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTestCount();
        String curMethodName_ = ((LgNamesUtils) _ctx.getStandards()).getAliasInfoTestCurrentMethod();
        Struct done_ = ((FieldableStruct) _infos).getStruct(new ClassField(infoTest_, infoTestDone_));
        Struct count_ = ((FieldableStruct) _infos).getStruct(new ClassField(infoTest_, infoTestCount_));
        Struct method_ = ((FieldableStruct) _infos).getStruct(new ClassField(infoTest_, curMethodName_));
        if (!count_.sameReference(_count)) {
            progressBar.setMinimum(0);
            progressBar.setMaximum(((NumberStruct)count_).intStruct());
        }
        if (!_doneTests.sameReference(done_)) {
            doneTestsCount.setText(((NumberStruct)done_).longStruct()+"/"+((NumberStruct)count_).longStruct());
            progressBar.setValue(((NumberStruct)done_).intStruct());
        }
        if (!_method.sameReference(method_) && method_ instanceof MethodMetaInfo) {
            currentMethod.setText(((MethodMetaInfo)method_).getRealId().getSignature(_ctx));
        }
    }
    public void finish(RunnableContextEl _ctx, Struct _infos) {
        String infoTest_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTest();
        String infoTestCount_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTestCount();
        Struct count_ = ((FieldableStruct) _infos).getStruct(new ClassField(infoTest_, infoTestCount_));
        doneTestsCount.setText(((NumberStruct)count_).longStruct()+"/"+((NumberStruct)count_).longStruct());
        progressBar.setValue(progressBar.getMaximum());
    }
}
