package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.Menu;
import code.gui.MenuBar;
import code.gui.MenuItem;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.events.QuittingEvent;
import code.threads.ThreadUtil;
import code.util.StringMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public final class MainWindow extends GroupFrame {
    private Menu menu;
    private MenuItem open;

    private Panel contentPane;
    private Panel form;
    private PlainLabel content;
    private TextArea conf;
    private PlainButton launch;
    private Panel progressing;
    private PlainLabel doneTests;
    private PlainLabel doneTestsCount;

    private PlainLabel method;
    private PlainLabel currentMethod;
    private TableGui resultsTable;
    private TextArea results;
    private ProgressBar progressBar;

    private Thread th;
    private StringMap<String> messages;
    protected MainWindow(String _lg) {
        super(_lg);
        setAccessFile("unit.mainwindow");
        messages = getMessages(this,"resources_unit/gui/messages");
        setTitle(messages.getVal("title"));
        setJMenuBar(new MenuBar());
        menu = new Menu(messages.getVal("file"));
        open = new MenuItem(messages.getVal("open"));
        open.addActionListener(new FileOpenEvent(this));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menu.addMenuItem(open);
        getJMenuBar().add(menu);
        contentPane = Panel.newPageBox();
        form = Panel.newGrid(0,2);
        content = new PlainLabel(messages.getVal("configuration"));
        form.add(content);
        conf = new TextArea(64,64);
        ScrollPane scr_ = new ScrollPane(conf);
        scr_.setPreferredSize(new Dimension(256,96));
        form.add(scr_);
        launch = new PlainButton(messages.getVal("launch"));
        launch.addActionListener(new ListenerLaunchTests(this));
        form.add(launch);
        form.add(new TextLabel(""));
        contentPane.add(form);
        progressing = Panel.newPageBox();
        doneTests = new PlainLabel(messages.getVal("tests"));
        progressing.add(doneTests);
        doneTestsCount = new PlainLabel("");
        progressing.add(doneTestsCount);
        method = new PlainLabel(messages.getVal("method"));
        progressing.add(method);
        currentMethod = new PlainLabel("");
        progressing.add(currentMethod);
        progressBar = new ProgressBar();
        progressing.add(progressBar);
        String[] cols_ = new String[4];
        cols_[0] =messages.getVal("number");
        cols_[1] =messages.getVal("method");
        cols_[2] =messages.getVal("params");
        cols_[3] =messages.getVal("success");
        resultsTable = new TableGui(cols_);
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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    @Override
    public void dispose() {
        LaunchingAppUnitTests.decrement();
        super.dispose();
    }

    @Override
    public void quit() {
        if (th != null) {
            while (th.isAlive()) {
                ThreadUtil.sleep(0);
            }
        }
        dispose();
    }

    @Override
    public String getApplicationName() {
        return "ug";
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
        RunningTest r_ = RunningTest.newFromContent(txt_, new ProgressingTestsImpl(this),
                new FileInfos(new DefaultResourcesReader(),new DefaultLogger(),
                        new DefaultFileSystem(), new DefaultReporter(), getGenerator()));
        Thread th_ = new Thread(r_);
        th = th_;
        th_.start();
    }
    public void selectFile() {
        FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, "", ConstFiles.getHomePath());
        String fichier_=FileOpenDialog.getStaticSelectedPath();
        if (fichier_ == null) {
            fichier_ = "";
        }
        if (fichier_.isEmpty()) {
            return;
        }
        launchFileConf(fichier_);
    }

    public void launchFileConf(String _fichier) {
        RunningTest r_ = RunningTest.newFromFile(_fichier, new ProgressingTestsImpl(this),
                new FileInfos(new DefaultResourcesReader(),new DefaultLogger(),
                        new DefaultFileSystem(), new DefaultReporter(), getGenerator()));
        Thread th_ = new Thread(r_);
        th = th_;
        th_.start();
    }

    public void showProgress(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count) {
        String infoTest_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTest();
        String infoTestDone_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTestDone();
        String infoTestCount_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTestCount();
        String curMethodName_ = ((LgNamesUtils) _ctx.getStandards()).getAliasInfoTestCurrentMethod();
        Struct done_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestDone_)).getStruct();
        Struct count_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        Struct method_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, curMethodName_)).getStruct();
        if (!count_.sameReference(_count)) {
            progressBar.setMinimum(0);
            progressBar.setMaximum(((NumberStruct)count_).intStruct());
        }
        if (!_doneTests.sameReference(done_)) {
            doneTestsCount.setText(((NumberStruct)done_).longStruct()+"/"+((NumberStruct)count_).longStruct());
            progressBar.setValue(((NumberStruct)done_).intStruct());
        }
        if (!_method.sameReference(method_) && method_ instanceof MethodMetaInfo) {
            currentMethod.setText(((MethodMetaInfo)method_).getSignature(_ctx));
        }
    }
    public void finish(RunnableContextEl _ctx, Struct _infos) {
        String infoTest_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTest();
        String infoTestCount_ = ((LgNamesUtils)_ctx.getStandards()).getAliasInfoTestCount();
        Struct count_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        doneTestsCount.setText(((NumberStruct)count_).longStruct()+"/"+((NumberStruct)count_).longStruct());
        progressBar.setValue(progressBar.getMaximum());
    }

    public void setResults(RunnableContextEl _ctx, Argument _res) {
        LgNamesUtils stds_ = (LgNamesUtils) _ctx.getStandards();
        if (!_res.isNull()) {
            Struct results_ = _res.getStruct();
            String tableCl_ = stds_.getAliasTable();
            String listTable_ = stds_.getAliasListTa();
            Struct list_ = ((FieldableStruct)results_).getEntryStruct(new ClassField(tableCl_,listTable_)).getStruct();
            String listCl_ = stds_.getAliasList();
            String arrList_ = stds_.getAliasArrayLi();
            Struct array_ = ((FieldableStruct)list_).getEntryStruct(new ClassField(listCl_,arrList_)).getStruct();
            String pairCl_ = stds_.getAliasCustPair();
            String pairFirst_ = stds_.getAliasFirst();
            String pairSecond_ = stds_.getAliasSecond();
            String aliasResult_ = stds_.getAliasResult();
            String aliasSuccess_ = stds_.getAliasResultSuccess();
            String aliasFailMessage_ = stds_.getAliasResultFailMessage();
            String aliasParams_ = stds_.getAliasResultParams();
            int i =0;
            resultsTable.setRowCount(((ArrayStruct)array_).getInstance().length);
            for (Struct t: ((ArrayStruct)array_).getInstance()) {
                Struct method_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairFirst_)).getStruct();
                Struct result_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairSecond_)).getStruct();
                i++;
                resultsTable.setValueAt(Integer.toString(i),i-1,0);
                results.append(Integer.toString(i)+"\n");
                String methodInfo_ = ((MethodMetaInfo) method_).getClassName() + "." + ((MethodMetaInfo) method_).getSignature(_ctx) + "\n";
                resultsTable.setValueAt(methodInfo_,i-1,1);
                results.append(methodInfo_);
                Struct params_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasParams_)).getStruct();
                resultsTable.setValueAt(((StringStruct)params_).getInstance(),i-1,2);
                Struct success_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasSuccess_)).getStruct();
                Struct failMessage_ = ((FieldableStruct) result_).getEntryStruct(new ClassField(aliasResult_, aliasFailMessage_)).getStruct();
                if (BooleanStruct.isTrue(success_)) {
                    results.append(messages.getVal("success")+"\n");
                    resultsTable.setValueAt("x",i-1,3);
                } else {
                    results.append(messages.getVal("fail")+"\n");
                    resultsTable.setValueAt("",i-1,3);
                }
                results.append(((StringStruct)failMessage_).getInstance()+"\n");
                results.append(((StringStruct)params_).getInstance()+"\n");
                results.append("\n");

            }
        }
    }
}
