package code.expressionlanguage;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.OverridableBlock;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.options.Options;
import code.gui.Clock;
import code.gui.CustComponent;
import code.stream.StreamTextFile;
import code.stream.StreamZipFile;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

import java.io.File;


public final class GuiProcess implements Runnable {
    private GuiContextEl context;
    private ExecutingOptions executingOptions;
    private String clName;
    private String mName;
    public static void launch(String[] _args) {
        if (_args.length == 0) {
            return;
        }
        StringList mainArgs_ = new StringList(_args);
        String content_ = StreamTextFile.contentsOfFile(_args[0]);
        if (content_ == null) {
            return;
        }
        StringList lines_ = StringList.splitStrings(content_, "\n", "\r\n");
        StringList linesFiles_ = new StringList();
        for (String s: lines_) {
            if (s.trim().isEmpty()) {
                continue;
            }
            linesFiles_.add(s.trim());
        }
        if (linesFiles_.size() < 3) {
            return;
        }
        String archive_ = linesFiles_.first();
        StringMap<String> zipFiles_ = RunningTest.getFiles(archive_);
        String lg_ = linesFiles_.get(1);
        String clName_ = "";
        String mName_ = "";
        String line_ = ContextEl.removeDottedSpaces(linesFiles_.get(2));
        int last_ = line_.lastIndexOf('.');
        if (last_ > -1) {
            clName_ = line_.substring(0,last_);
            mName_ = line_.substring(last_+1);
        }
        ExecutingOptions exec_ = new ExecutingOptions();
        RunningTest.setupOptionals(3,exec_,linesFiles_);
        String folder_ = exec_.getLogFolder();
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        LgNamesGui stds_ = new LgNamesGui();
        GuiContextEl cont_ = GuiContextFactory.buildDefKw(lg_, mainArgs_,opt_, exec_, stds_, zipFiles_, exec_.getTabWidth());
        if (cont_ == null) {
            return;
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new CoveringCodeTask(cont_,exec_)));
        if (!cont_.getClasses().isEmptyErrors() || !cont_.getClasses().isEmptyStdError()) {
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+cont_.getClasses().displayErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+cont_.getClasses().displayWarnings());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+cont_.getClasses().displayStdErrors());
            return;
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
        CustComponent.invokeLater(pr_);
    }

    @Override
    public void run() {
        RunnableStruct.setupThread(context);
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
            context.getCustInit().removeThreadFromList();
        }
    }
}
