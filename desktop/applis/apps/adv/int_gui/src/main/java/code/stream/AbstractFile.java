package code.stream;

public interface AbstractFile {
    String getName();
    String getAbsolutePath();
    String[] list();

    boolean isDirectory();

    boolean exists();

    long length();

    long lastModified();

    boolean renameTo(AbstractFile _newFile);

    boolean delete();

    String getParent();

    boolean mkdirs();
}
