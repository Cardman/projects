package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilfiles.DefaultResourcesReader;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.*;
import code.gui.Menu;
import code.gui.MenuBar;
import code.gui.MenuItem;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
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
    protected MainWindow(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
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
        basicDispose();
    }

    @Override
    public void quit() {
        if (th != null) {
            while (th.isAlive()) {
                ThreadUtil.sleep(0);
            }
        }
        basicDispose();
    }

    @Override
    public String getApplicationName() {
        return LaunchingAppUnitTests.getMainWindowClass();
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
        FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, "", getFrames().getHomePath(),"jre");
        String fichier_=FileOpenDialog.getStaticSelectedPath(getFileOpenDialog());
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

    public void showProgress(ContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count, LgNamesWithNewAliases _evolved) {
        String infoTest_ = _evolved.getCustAliases().getAliasInfoTest();
        String infoTestDone_ = _evolved.getCustAliases().getAliasInfoTestDone();
        String infoTestCount_ = _evolved.getCustAliases().getAliasInfoTestCount();
        String curMethodName_ = _evolved.getCustAliases().getAliasInfoTestCurrentMethod();
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
    public void finish(Struct _infos, LgNamesWithNewAliases _evolved) {
        String infoTest_ = _evolved.getCustAliases().getAliasInfoTest();
        String infoTestCount_ = _evolved.getCustAliases().getAliasInfoTestCount();
        Struct count_ = ((FieldableStruct) _infos).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        doneTestsCount.setText(((NumberStruct)count_).longStruct()+"/"+((NumberStruct)count_).longStruct());
        progressBar.setValue(progressBar.getMaximum());
    }

    public void setResults(ContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        if (!_res.isNull()) {
            Struct results_ = _res.getStruct();
            String tableCl_ = _evolved.getCustAliases().getAliasTable();
            String listTable_ = _evolved.getCustAliases().getAliasListTa();
            Struct list_ = ((FieldableStruct)results_).getEntryStruct(new ClassField(tableCl_,listTable_)).getStruct();
            String listCl_ = _evolved.getCustAliases().getAliasList();
            String arrList_ = _evolved.getCustAliases().getAliasArrayLi();
            Struct array_ = ((FieldableStruct)list_).getEntryStruct(new ClassField(listCl_,arrList_)).getStruct();
            String pairCl_ = _evolved.getCustAliases().getAliasCustPair();
            String pairFirst_ = _evolved.getCustAliases().getAliasFirst();
            String pairSecond_ = _evolved.getCustAliases().getAliasSecond();
            String aliasResult_ = _evolved.getCustAliases().getAliasResult();
            String aliasSuccess_ = _evolved.getCustAliases().getAliasResultSuccess();
            String aliasFailMessage_ = _evolved.getCustAliases().getAliasResultFailMessage();
            String aliasParams_ = _evolved.getCustAliases().getAliasResultParams();
            int testLen_ = ((ArrayStruct) array_).getLength();
            resultsTable.setRowCount(testLen_);
            for (int i =0; i < testLen_; i++) {
                Struct t = ((ArrayStruct) array_).get(i);
                Struct method_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairFirst_)).getStruct();
                Struct result_ = ((FieldableStruct)t).getEntryStruct(new ClassField(pairCl_,pairSecond_)).getStruct();
                resultsTable.setValueAt(Long.toString(i),i-1,0);
                results.append(Long.toString(i)+"\n");
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
