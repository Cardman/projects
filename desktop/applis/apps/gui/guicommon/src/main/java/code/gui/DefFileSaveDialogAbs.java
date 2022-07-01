package code.gui;

public final class DefFileSaveDialogAbs implements FileSaveDialogAbs{
    @Override
    public String input(AbsGroupFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialogByFrame((GroupFrame)_w, _language, _currentFolderRoot, _extension,_folder, ((GroupFrame)_w).getFrames().getHomePath());
        return FileSaveDialog.getStaticSelectedPath(((GroupFrame)_w).getFileSaveDialog());
    }

    @Override
    public String input(AbsGroupFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialog((GroupFrame)_c,_w,_language,_currentFolderRoot,_extension,_folder,((GroupFrame)_c).getFrames().getHomePath());
        return FileSaveDialog.getStaticSelectedPath(((GroupFrame)_c).getFileSaveDialog());
    }
}
