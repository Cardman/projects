package code.expressionlanguage.utilcompo;

public final class FileInfos {
    private final AbstractResourcesReader reader;
    private final AbstractLogger logger;
    private final AbstractFileSystem fileSystem;

    public FileInfos(AbstractResourcesReader reader, AbstractLogger logger, AbstractFileSystem fileSystem) {
        this.reader = reader;
        this.logger = logger;
        this.fileSystem = fileSystem;
    }

    public AbstractResourcesReader getReader() {
        return reader;
    }

    public AbstractLogger getLogger() {
        return logger;
    }

    public AbstractFileSystem getFileSystem() {
        return fileSystem;
    }
}
