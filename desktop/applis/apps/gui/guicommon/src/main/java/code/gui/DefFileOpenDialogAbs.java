package code.gui;

public final class DefFileOpenDialogAbs implements FileOpenDialogAbs{
    @Override
    public String input(GroupFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileOpenDialog.setFileOpenDialog(_w, _language, _currentFolderRoot, _extension, _folder);
        return FileOpenDialog.getStaticSelectedPath(_w.getFileOpenDialog());
    }
}
