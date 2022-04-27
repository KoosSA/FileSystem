package com.koossa.filesystem;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

/**
 * Small library that manages common gaming folders. <br>
 * Call init() once when application starts to access other functions.
 * @author KoosSA
 *
 */
public class Files {

	private static File root;
	private static Map<String, File> folders = new HashMap<String, File>();

	/**
	 * Initializes the folder system.
	 * @param folderName
	 * @param location
	 */
	public static void init(String folderName, RootFileLocation location) {
		if (location == RootFileLocation.LOCAL) {
			root = new File(folderName);
		} else if (location == RootFileLocation.DEFAULT_DIRECTORY) {
			root = new File(FileSystemView.getFileSystemView().getDefaultDirectory(), folderName);
		}
		validateFolder(root);
	}

	/**
	 * Get a folder ({@link File}) from the {@link CommonFolders} class.
	 * @param folder
	 * @return {@link File}
	 */
	public static File getCommonFolder(CommonFolders folder) {
		return getFolder(folder.toString());
	}

	/**
	 * Get a folder ({@link File}) not specified in the {@link CommonFolders} class.
	 * @param folderName
	 * @return {@link File}
	 */
	public static File getFolder(String folderName) {
		return folders.getOrDefault(folderName, createFolder(folderName));
	}


	private static File createFolder(String folderName) {
		File folder = new File(root, folderName);
		validateFolder(folder);
		folders.put(folderName, folder);
		return folder;
	}

	/**
	 * Get the path to a folder from the {@link CommonFolders} class.
	 * @param folder
	 * @return {@link String}
	 */
	public static String getCommonFolderPath(CommonFolders folder) {
		return getCommonFolder(folder).getAbsolutePath();
	}

	/**
	 * Get the path to a folder not specified in the {@link CommonFolders} class.
	 * @param folderName
	 * @return {@link String}
	 */
	public static String getFolderPath(String folderName) {
		return getFolder(folderName).getAbsolutePath();
	}

	/**
	 * Validates a folder. <br>
	 * If the folder does not exists on the system, creates the folder.
	 * @param folder
	 */
	public static void validateFolder(File folder) {
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	/**
	 * Gets the root folder for the filesystem.
	 * @return rootFolder
	 */
	public static File getRootFolder() {
		return root;
	}

}
