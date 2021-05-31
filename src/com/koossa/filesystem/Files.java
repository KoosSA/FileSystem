package com.koossa.filesystem;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

public class Files {

	private static File root;
	private static Map<String, File> folders = new HashMap<String, File>();

	public static void init(String folderName, RootFileLocation location) {
		if (location == RootFileLocation.LOCAL) {
			root = new File(folderName);
		} else if (location == RootFileLocation.DEFAULT_DIRECTORY) {
			root = new File(FileSystemView.getFileSystemView().getDefaultDirectory(), folderName);
		}
		validateFolder(root);
	}

	public static File getCommonFolder(CommonFolders folder) {
		return getFolder(folder.toString());
	}

	public static File getFolder(String folderName) {
		return folders.getOrDefault(folderName, createFolder(folderName));
	}

	private static File createFolder(String folderName) {
		File folder = new File(root, folderName);
		validateFolder(folder);
		folders.put(folderName, folder);
		return folder;
	}
	
	public static String getCommonFolderPath(CommonFolders folder) {
		return getCommonFolder(folder).getAbsolutePath();
	}
	
	public static String getFolderPath(String folderName) {
		return getFolder(folderName).getAbsolutePath();
	}

	public static void validateFolder(File folder) {
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

}
