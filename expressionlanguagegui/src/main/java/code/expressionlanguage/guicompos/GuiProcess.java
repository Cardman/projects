package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.OverridableBlock;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.expressionlanguage.utilcompo.RunningTest;
import code.gui.Clock;
import code.gui.CustComponent;
import code.stream.StreamTextFile;
import code.stream.ThreadUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

import java.io.File;


public final class GuiProcess implements Runnable {
    private GuiContextEl context;
    private ExecutingOptions executingOptions;
    private String clName;
    private String mName;

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
        StringMap<String> zipFiles_ = RunningTest.getFiles(archive_);
        String lg_ = linesFiles_.get(1);
        String clName_ = "";
        String mName_ = "";
        int from_ = 2;
        if (lg_.startsWith("main=")) {
            String line_ = ContextEl.removeDottedSpaces(lg_);
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
            String line_ = ContextEl.removeDottedSpaces(linesFiles_.get(2));
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
        RunningTest.setupOptionals(from_,exec_,linesFiles_);
        String folder_ = exec_.getLogFolder();
        if (exec_.isHasArg()) {
            mainArgs_ = exec_.getArgs();
            mainArgs_.add(0, _conf);
        }
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        opt_.setFailIfNotAllInit(true);
        LgNamesGui stds_ = new LgNamesGui();
        GuiContextEl cont_ = GuiContextFactory.buildDefKw(lg_, mainArgs_,_window,opt_, exec_, stds_, zipFiles_, exec_.getTabWidth());
        if (cont_ == null) {
            return null;
        }
        if (!cont_.getClasses().isEmptyErrors() || !cont_.getClasses().isEmptyStdError()) {
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+cont_.getClasses().displayErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+cont_.getClasses().displayWarnings());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+cont_.getClasses().displayStdErrors());
            return null;
        }
        if (!cont_.getClasses().isEmptyWarnings()) {
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+cont_.getClasses().displayWarnings());
        }
        GuiProcess pr_ = new GuiProcess();
        pr_.executingOptions = exec_;
        pr_.context = cont_;
        pr_.clName = clName_;
        pr_.mName = mName_;
        return pr_;
    }
    @Override
    public void run() {
        long nb_ = RunnableStruct.setupThread(context);
        context.setNumber(nb_);
        String folder_ = executingOptions.getLogFolder();
        new File(folder_).mkdirs();
        MethodId id_ = new MethodId(MethodModifier.STATIC, mName, new StringList());
        CustList<OverridableBlock> methods_ = Classes.getMethodBodiesById(context, clName, id_);
        if (!methods_.isEmpty()) {
            ProcessMethod.initializeClass(clName,context);
            if (context.hasException()) {
                context.getCustInit().prExc(context);
                return;
            }
            CustList<Argument> args_ = new CustList<Argument>();
            Argument arg_ = new Argument();
            RunnableStruct.invoke(arg_, clName, id_, args_, context, null);
        } else {
            context.getCustInit().removeThreadFromList(context);
        }
        if (!context.getFrame().isVisible()) {
            context.getGuiInit().launchHooks(context);
            Thread th_ = CustComponent.newThread(new CoveringCodeTask(context, executingOptions));
            th_.start();
            ThreadUtil.join(th_);
        }
    }
}
