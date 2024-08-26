package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.utilimpl.MessCdmLogs;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThreadFactory;
import code.util.core.DefaultUniformingString;

public final class FileInfos {
    public static final String CDM = "cdm";
    public static final String COMMENTS = "comments";
    public static final String COMMENTS_V1 = "html";
    public static final String COMMENTS_V2 = "html";
    public static final String COMM_BEGIN = "1";
    public static final String COMM_END = "2";
    public static final String MESSAGES = "messages";
    public static final String KEYWORDS = "keywords";
    public static final String TYPES = "types";
    public static final String TYPES_GUI = "types_gui";

    private final AbstractLogger logger;
    private final AbstractFileSystem fileSystem;
    private final AbstractReporter reporter;
    private final AbstractGenerator generator;
    private final AbstractZipFact zipFact;
    private final AbstractThreadFactory threadFactory;

    public FileInfos(AbstractLogger _logger, AbstractFileSystem _fileSystem, AbstractReporter _reporter, AbstractGenerator _generator, AbstractZipFact _zipFact, AbstractThreadFactory _threadFactory) {
        this.logger = _logger;
        this.fileSystem = _fileSystem;
        this.reporter = _reporter;
        this.generator = _generator;
        zipFact = _zipFact;
        threadFactory = _threadFactory;
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(CDM, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(CDM);
    }
    public static TranslationsAppli enTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(MESSAGES, MessCdmLogs.en());
        _lgs.getMapping().addEntry(KEYWORDS, KeyWords.en());
        _lgs.getMapping().addEntry(TYPES, CustAliases.en());
        _lgs.getMapping().addEntry(TYPES_GUI, GuiAliases.en());
        MessagesExecutingOptions.updateEn(_lgs);
        return _lgs;
    }
    public static TranslationsAppli frTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(MESSAGES, MessCdmLogs.fr());
        _lgs.getMapping().addEntry(KEYWORDS, KeyWords.fr());
        _lgs.getMapping().addEntry(TYPES, CustAliases.fr());
        _lgs.getMapping().addEntry(TYPES_GUI, GuiAliases.fr());
        MessagesExecutingOptions.updateFr(_lgs);
        return _lgs;
    }

    public static TranslationsAppli initComments(TranslationsLg _lgs) {
        TranslationsAppli a_ = initAppliTr(_lgs);
        appendComments(a_);
        return a_;
    }

    public static void appendComments(TranslationsAppli _a) {
        _a.getMapping().addEntry(COMMENTS, comFile());
    }

    private static TranslationsFile comFile() {
        TranslationsFile file_ = new TranslationsFile();
        file_.getMapping().addEntry(COMM_BEGIN,COMMENTS_V1);
        file_.getMapping().addEntry(COMM_END,COMMENTS_V2);
        return file_;
    }
    public static FileInfos buildMemoryFromFile(AbstractLightProgramInfos _light, AbstractGenerator _generator, AbstractNameValidating _nameValidating, AbstractIssuer _issuer, MemInputFiles _mem, AbstractZipFact _zipFact, AbstractThreadFactory _threadFactory) {
        DefaultUniformingString uniformingString_ = new DefaultUniformingString();
        return new FileInfos(new MemoryLogger(_issuer, _threadFactory),new MemoryFileSystem(_nameValidating, _threadFactory),new MemoryReporter(_light,_mem.getConf(), _mem.getSrc(), _mem.getFiles(), _nameValidating, uniformingString_),_generator, _zipFact, _threadFactory);
    }
    public void tryLogIssue(String _info) {
        AbstractIssuer issuer_ = logger.getIssuer();
        if (issuer_ != null) {
            issuer_.log(_info);
        }
    }

    public AbstractLogger getLogger() {
        return logger;
    }

    public AbstractFileSystem getFileSystem() {
        return fileSystem;
    }

    public AbstractReporter getReporter() {
        return reporter;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public AbstractZipFact getZipFact() {
        return zipFact;
    }

}
