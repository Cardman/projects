package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.Clock;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.core.OutputType;
import code.stream.core.ReadFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;


public final class GuiProcess implements GuiRunnable {
    private GuiContextEl context;
    private ExecutingOptions executingOptions;
    private String clName;
    private String mName;
    private AbstractProgramInfos programInfos;

    public static GuiProcess build(String _conf, String _content, GuiInterpreterElements _currentElements) {
        AbstractProgramInfos programInfos_ = _currentElements.getProgramInfos();
        StringList mainArgs_ = new StringList(_conf);
        StringList lines_ = StringUtil.splitStrings(_content, "\n", "\r\n");
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
        UniformingString app_ = new DefaultUniformingString();
        ReadFiles result_ = StreamFolderFile.getFiles(archive_, app_, programInfos_.getFileCoreStream(), programInfos_.getStreams());
        if (result_.getType() == OutputType.NOTHING) {
            return null;
        }
        String lg_ = linesFiles_.get(1);
        String clName_ = "";
        String mName_ = "";
        if (!StringUtil.contains(Constants.getAvailableLanguages(),lg_)){
            lg_ = "";
        }
        String line_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
        if (line_.startsWith("main=")) {
            String subLine_ = line_.substring("main=".length());
            int last_ = subLine_.lastIndexOf('.');
            if (last_ > -1) {
                clName_ = subLine_.substring(0,last_);
                mName_ = subLine_.substring(last_+1);
            }
        }


        ExecutingOptions exec_ = new ExecutingOptions(programInfos_.getThreadFactory().newAtomicBoolean());
        Options opt_ = new Options();
        RunningTest.setupOptionals(3, opt_, exec_,linesFiles_);
        if (exec_.isHasArg()) {
            mainArgs_ = exec_.getArgs();
            mainArgs_.add(0, _conf);
        }
        AbstractNameValidating validator_ = programInfos_.getValidator();
        FileInfos fileInfos_ = new FileInfos(new DefaultLogger(validator_, null, programInfos_.getFileCoreStream(), programInfos_.getStreams()), new DefaultFileSystem(app_, validator_, programInfos_.getFileCoreStream(), programInfos_.getStreams()), new DefaultReporter(validator_, app_, false, new TechInfos(programInfos_.getThreadFactory(), programInfos_.getStreams()), programInfos_.getFileCoreStream()), programInfos_.getGenerator(), programInfos_.getStreams().getZipFact(), programInfos_.getThreadFactory());

        StringMap<String> list_ = RunningTest.tryGetSrc(archive_, exec_, fileInfos_, result_);
        if (list_ == null) {
            return null;
        }
        opt_.setReadOnly(true);
        LgNamesGui stds_ = new LgNamesGui(fileInfos_,programInfos_.getInterceptor());
        ResultsGuiContext res_ = GuiContextFactory.buildDefKw(lg_, mainArgs_, opt_, exec_, stds_, list_, _currentElements);
        GuiContextEl cont_ = res_.getRunnable();
        ReportedMessages reportedMessages_ = res_.getReportedMessages();
        CustContextFactory.reportErrors(opt_, exec_, reportedMessages_, stds_.getInfos());
        String time_ = Clock.getDateTimeText("_", "_", "_", programInfos_.getThreadFactory());
        if (cont_ == null) {
            MemoryReporter.buildError(reportedMessages_,exec_,fileInfos_,time_);
            AbstractLogger logger_ = fileInfos_.getLogger();
            byte[] bytes_ = fileInfos_.getReporter().exportErrs(exec_, logger_);
            if (bytes_ != null) {
                StreamFolderFile.makeParent(exec_.getOutputFolder()+"/"+exec_.getOutputZip(), programInfos_.getFileCoreStream());
                StreamBinaryFile.writeFile(exec_.getOutputFolder()+"/"+exec_.getOutputZip(),bytes_, programInfos_.getStreams());
            }
            return null;
        }
        MemoryReporter.buildWarning(reportedMessages_,exec_,fileInfos_,time_);
        GuiProcess pr_ = new GuiProcess();
        pr_.executingOptions = exec_;
        pr_.context = cont_;
        pr_.clName = clName_;
        pr_.mName = mName_;
        pr_.programInfos = programInfos_;
        return pr_;
    }
    @Override
    public void run() {
        RunnableStruct.setupThread(context);
        String folder_ = executingOptions.getLogFolder();
        StreamFolderFile.makeParent(folder_, programInfos.getFileCoreStream());
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, mName, new StringList());
        ExecRootBlock classBody_ = context.getClasses().getClassBody(clName);
        if (classBody_ == null) {
            context.getCustInit().removeThreadFromList(context);
            lastThread();
            return;
        }
        CustList<ExecOverridableBlock> methods_ = ExecClassesUtil.getMethodBodiesById(classBody_, id_);
        if (!methods_.isEmpty()) {
            StackCall st_ = StackCall.newInstance(InitPhase.NOTHING,context);
            ProcessMethod.initializeClass(clName, classBody_,context, st_);
            if (context.callsOrException(st_)) {
                context.getCustInit().prExc(context, st_);
                return;
            }
            CustList<Argument> args_ = new CustList<Argument>();
            Argument arg_ = new Argument();
            ExecNamedFunctionBlock fct_ = methods_.first();
            RunnableStruct.invoke(arg_, new ExecFormattedRootBlock(classBody_, clName), context, new ExecTypeFunction(classBody_, fct_), st_, new ArgumentListCall());
        } else {
            context.getCustInit().removeThreadFromList(context);
        }
        lastThread();
    }

    private void lastThread() {
        if (!isVisible()) {
            LgNamesGui lg_ = (LgNamesGui)context.getStandards();
            context.disposeAll(lg_.getGuiExecutingBlocks(),StackCall.newInstance(InitPhase.NOTHING,context));
        }
    }

    @Override
    public boolean isVisible() {
        return ((LgNamesGui) context.getStandards()).getGuiExecutingBlocks().getFrame().isVisible();
    }
}
