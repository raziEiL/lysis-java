package lysis.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class SmxFilter extends FileFilter {

	// Accept all directories and all smx files.
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return (ext != null && ext.equals("smx"));
	}

	// The description of this filter
	public String getDescription() {
		return "Just smx";
	}
}
