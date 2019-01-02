package code.expressionlanguage.options;

public final class ExecutingOptions {

    private String logFolder = "logs";
    private String mainThread = "main_thread.txt";
    private int tabWidth = 4;
	public String getLogFolder() {
		return logFolder;
	}
	public void setLogFolder(String _logFolder) {
		logFolder = _logFolder;
	}
	public String getMainThread() {
		return mainThread;
	}
	public void setMainThread(String _mainThread) {
		mainThread = _mainThread;
	}
	public int getTabWidth() {
		return tabWidth;
	}
	public void setTabWidth(int _tabWidth) {
		tabWidth = _tabWidth;
	}

}
