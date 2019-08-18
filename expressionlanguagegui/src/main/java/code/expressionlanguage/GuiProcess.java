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
        StringMap<byte[]> zip_ =  StreamZipFile.zippedBinaryFiles(archive_);
        if (zip_ == null) {
            return;
        }
        StringMap<String> zipFiles_ = new StringMap<String>();
        String lg_ = linesFiles_.get(1);
        for (EntryCust<String,byte[]> e: zip_.entryList()) {
            String key_ = e.getKey();
            if (key_.endsWith("/")) {
                continue;
            }
            String dec_ = StringList.decode(e.getValue());
            if (dec_ == null) {
                continue;
            }
            zipFiles_.addEntry(key_,dec_);
        }
        String clName_ = "";
        String mName_ = "";
        String line_ = ContextEl.removeDottedSpaces(linesFiles_.get(2));
        int last_ = line_.lastIndexOf('.');
        if (last_ > -1) {
            clName_ = line_.substring(0,last_);
            mName_ = line_.substring(last_+1);
        }
        ExecutingOptions exec_ = new ExecutingOptions();
        if (linesFiles_.size() > 3) {
            String output_ = linesFiles_.get(3);
            int lastSep_ = output_.lastIndexOf('>');
            if (lastSep_ > -1) {
                exec_.setLogFolder(output_.substring(0,lastSep_));
                exec_.setMainThread(output_.substring(lastSep_+1));
            }
        }
        String folder_ = exec_.getLogFolder();
        Options opt_ = new Options();
        LgNamesGui stds_ = new LgNamesGui();
        GuiContextEl cont_ = GuiContextFactory.buildDefKw(lg_, mainArgs_,opt_, exec_, stds_, zipFiles_, 4);
        if (cont_ == null) {
            return;
        }
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
        Thread thread_ = Thread.currentThread();
        context.getCustInit().initAlive(thread_);
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(LgNamesUtils.getDateTimeText("_", "_", "_"));
        dtPart_.append("__");
        dtPart_.append(context.getCustInit().increment());
        dtPart_.append(".txt");
        context.getCustInit().putNewCustTreadIdDate(thread_, dtPart_.toString());
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
            ProcessMethod.calculateArgument(arg_, clName, id_, args_, context, null);
            if (context.hasException()) {
                context.getCustInit().prExc(context);
            } else {
                context.getCustInit().removeThreadFromList();
            }
        } else {
            context.getCustInit().removeThreadFromList();
        }
    }
}
