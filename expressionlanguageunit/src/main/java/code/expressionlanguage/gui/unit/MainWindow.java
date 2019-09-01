package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.LgNamesUtils;
import code.expressionlanguage.RunnableContextEl;
import code.expressionlanguage.RunningTest;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.TextField;
import code.gui.events.QuittingEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private TableGui resultsTable;
    private TextArea results;
    private ProgressBar progressBar;

    private DefaultTableModel model;
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
        Object[] cols_ = new Object[4];
        cols_[0] ="Number";
        cols_[1] ="Method";
        cols_[2] ="Params";
        cols_[3] ="Success";
        model = new DefaultTableModel(cols_,0);
        resultsTable = new TableGui(model);
        results = new TextArea(1024,1024);
        ScrollPane scrTable_ = new ScrollPane(resultsTable);
        scrTable_.setPreferredSize(new Dimension(256,96));
        ScrollPane scrRes_ = new ScrollPane(results);
        scrRes_.setPreferredSize(new Dimension(256,96));
        SplitPane splitPane_ = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,scrTable_,scrRes_);
        splitPane_.setOneTouchExpandable(true);
        progressing.add(splitPane_);
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

    public void setResults(RunnableContextEl _ctx, Argument _res) {
        LgNamesUtils stds_ = (LgNamesUtils) _ctx.getStandards();
        if (!_res.isNull()) {
            Struct results_ = _res.getStruct();
            String tableCl_ = stds_.getAliasTable();
            String listTable_ = stds_.getAliasListTa();
            Struct list_ = ((FieldableStruct)results_).getStruct(new ClassField(tableCl_,listTable_));
            String listCl_ = stds_.getAliasList();
            String arrList_ = stds_.getAliasArrayLi();
            Struct array_ = ((FieldableStruct)list_).getStruct(new ClassField(listCl_,arrList_));
            String pairCl_ = stds_.getAliasCustPair();
            String pairFirst_ = stds_.getAliasFirst();
            String pairSecond_ = stds_.getAliasSecond();
            String aliasResult_ = stds_.getAliasResult();
            String aliasSuccess_ = stds_.getAliasResultSuccess();
            String aliasFailMessage_ = stds_.getAliasResultFailMessage();
            String aliasParams_ = stds_.getAliasResultParams();
            int i =0;
            model.setRowCount(((ArrayStruct)array_).getInstance().length);
            for (Struct t: ((ArrayStruct)array_).getInstance()) {
                Struct method_ = ((FieldableStruct)t).getStruct(new ClassField(pairCl_,pairFirst_));
                Struct result_ = ((FieldableStruct)t).getStruct(new ClassField(pairCl_,pairSecond_));
                i++;
                resultsTable.setValueAt(i,i-1,0);
                results.append(Integer.toString(i)+"\n");
                String methodInfo_ = ((MethodMetaInfo) method_).getClassName() + "." + ((MethodMetaInfo) method_).getRealId().getSignature(_ctx) + "\n";
                resultsTable.setValueAt(methodInfo_,i-1,1);
                results.append(methodInfo_);
                Struct params_ = ((FieldableStruct) result_).getStruct(new ClassField(aliasResult_, aliasParams_));
                resultsTable.setValueAt(((StringStruct)params_).getInstance(),i-1,2);
                Struct success_ = ((FieldableStruct) result_).getStruct(new ClassField(aliasResult_, aliasSuccess_));
                Struct failMessage_ = ((FieldableStruct) result_).getStruct(new ClassField(aliasResult_, aliasFailMessage_));
                if (((BooleanStruct)success_).getInstance()) {
                    results.append("success\n");
                    resultsTable.setValueAt("x",i-1,3);
                } else {
                    results.append("fail\n");
                    resultsTable.setValueAt("",i-1,3);
                }
                results.append(((StringStruct)failMessage_).getInstance()+"\n");
                results.append(((StringStruct)params_).getInstance()+"\n");
                results.append("\n");

            }
        }
    }
}
