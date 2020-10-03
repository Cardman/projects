package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilfiles.DefaultResourcesReader;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.Clock;
import code.gui.CustComponent;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.threads.ThreadUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

import java.io.File;


public final class GuiProcess implements Runnable {
    private GuiContextEl context;
    private ExecutingOptions executingOptions;
    private String clName;
    private String mName;
    private MainWindow window;

    public static GuiProcess build(String _conf, MainWindow _window, String _content) {
        StringList mainArgs_ = new StringList(_conf);
        StringList lines_ = StringList.splitStrings(_content, "\n", "\r\n");
        StringList linesFiles_ = new StringList();
        for (String s: lines_) {
            if (s.trim().isEmpty()) {
                continue;
            }
            linesFiles_.add(s.trim());
        }
        if (linesFiles_.size() < 3) {
            return null;
        }
        String archive_ = linesFiles_.first();
        StringMap<String> zipFiles_ = StreamFolderFile.getFiles(archive_);
        String lg_ = linesFiles_.get(1);
        String clName_ = "";
        String mName_ = "";
        int from_ = 2;
        if (lg_.startsWith("main=")) {
            String line_ = StringExpUtil.removeDottedSpaces(lg_);
            if (line_.startsWith("main=")) {
                String subLine_ = line_.substring("main=".length());
                int last_ = subLine_.lastIndexOf('.');
                if (last_ > -1) {
                    clName_ = subLine_.substring(0,last_);
                    mName_ = subLine_.substring(last_+1);
                }
            }
        } else {
            from_++;
            String line_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
            if (line_.startsWith("main=")) {
                String subLine_ = line_.substring("main=".length());
                int last_ = subLine_.lastIndexOf('.');
                if (last_ > -1) {
                    clName_ = subLine_.substring(0,last_);
                    mName_ = subLine_.substring(last_+1);
                }
            }
        }


        ExecutingOptions exec_ = new ExecutingOptions();
        Options opt_ = new Options();
        RunningTest.setupOptionals(from_, opt_, exec_,linesFiles_);
        String folder_ = exec_.getLogFolder();
        if (exec_.isHasArg()) {
            mainArgs_ = exec_.getArgs();
            mainArgs_.add(0, _conf);
        }
        opt_.setReadOnly(true);
        LgNamesGui stds_ = new LgNamesGui(new FileInfos(new DefaultResourcesReader(),new DefaultLogger(), new DefaultFileSystem(), new DefaultReporter(), _window.getGenerator()));
        ResultsGuiContext res_ = GuiContextFactory.buildDefKw(lg_, mainArgs_,_window,opt_, exec_, stds_, zipFiles_, exec_.getTabWidth());
        GuiContextEl cont_ = res_.getRunnable();
        ReportedMessages reportedMessages_ = res_.getReportedMessages();
        CustContextFactory.reportErrors(cont_, opt_, exec_, reportedMessages_, stds_.getInfos());
        if (!reportedMessages_.isAllEmptyErrors()) {
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+ reportedMessages_.displayErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+ reportedMessages_.displayWarnings());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+ reportedMessages_.displayStdErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+ reportedMessages_.displayMessageErrors());
            return null;
        }
        if (!reportedMessages_.isEmptyWarnings()) {
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+ reportedMessages_.displayWarnings());
        }
        GuiProcess pr_ = new GuiProcess();
        pr_.executingOptions = exec_;
        pr_.context = cont_;
        pr_.clName = clName_;
        pr_.mName = mName_;
        pr_.window = _window;
        return pr_;
    }
    @Override
    public void run() {
        RunnableStruct.setupThread(context);
        String folder_ = executingOptions.getLogFolder();
        new File(folder_).mkdirs();
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, mName, new StringList());
        ExecRootBlock classBody_ = context.getClasses().getClassBody(clName);
        CustList<ExecNamedFunctionBlock> methods_ = ExecBlock.getMethodBodiesById(classBody_, id_);
        if (!methods_.isEmpty()) {
            ProcessMethod.initializeClass(clName, classBody_,context);
            if (context.callsOrException()) {
                context.getCustInit().prExc(context);
                return;
            }
            CustList<Argument> args_ = new CustList<Argument>();
            Argument arg_ = new Argument();
            ExecNamedFunctionBlock fct_ = methods_.first();
            RunnableStruct.invoke(arg_, clName, classBody_,fct_, args_, context);
        } else {
            context.getCustInit().removeThreadFromList(context);
        }
        if (!isVisible()) {
            context.getGuiInit().launchHooks(context);
            window.setNullCurrent();
            Thread th_ = CustComponent.newThread(new CoveringCodeTask(context, executingOptions));
            th_.start();
            ThreadUtil.join(th_);
        }
    }

    public boolean isVisible() {
        return ((LgNamesGui) context.getStandards()).getGuiExecutingBlocks().getFrame().isVisible();
    }
}
