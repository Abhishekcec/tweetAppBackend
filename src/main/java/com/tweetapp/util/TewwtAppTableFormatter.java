package com.tweetapp.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

public class TewwtAppTableFormatter {
	private static final String[] BLINE = { "-", "\u2501" };
	private static final String[] VERTICAL_TSEP = { "|", "\u2502" };
	private static final String[] VERTICAL_BSEP = { "|", "\u2503" };

	private String[] descriptions;
	private ArrayList<String[]> table;
	private int[] tableSizes;
	private int rows;
	private int findex;
	private String filter;
	private boolean ucode;
	private Comparator<String[]> comparator;
	private int spacing;
	private EnumAlignment aligns[];

	public TewwtAppTableFormatter(String... descriptions) {
		this(descriptions.length, descriptions);
	}
	
	public TewwtAppTableFormatter(int columns, String... descriptions) {
		if (descriptions.length != columns) {
			throw new IllegalArgumentException();
		}
		this.filter = null;
		this.rows = columns;
		this.descriptions = descriptions;
		this.table = new ArrayList<>();
		this.tableSizes = new int[columns];
		this.updateSizes(descriptions);
		this.ucode = false;
		this.spacing = 1;
		this.aligns = new EnumAlignment[columns];
		this.comparator = null;
		for (int i = 0; i < aligns.length; i++) {
			aligns[i] = EnumAlignment.LEFT;
		}
	}

	private void updateSizes(String[] elements) {
		for (int i = 0; i < tableSizes.length; i++) {
			if (elements[i] != null) {
				int j = tableSizes[i];
				j = Math.max(j, elements[i].length());
				tableSizes[i] = j;
			}
		}
	}



	/**
	 * Adds a row to the table with the specified elements.
	 */

	public TewwtAppTableFormatter addRow(String... elements) {
		if (elements.length != rows) {
			throw new IllegalArgumentException();
		}
		table.add(elements);
		updateSizes(elements);
		return this;
	}



	public void print() {
		StringBuilder line = null;
		
		
		endingLine();
		
		// print header
		line = null;
		for (int i = 0; i < rows; i++) {
			if (line != null) {
				line.append(gc(VERTICAL_TSEP));
			} else {
				line = new StringBuilder();
			}
			String part = descriptions[i];
			while (part.length() < tableSizes[i] + spacing) {
				part += " ";
			}
			for (int j = 0; j < spacing; j++) {
				line.append(" ");
			}
			line.append(part);
		}
		System.out.println(line.toString());

		endingLine();

		line = null;
		ArrayList<String[]> localTable = table;

		if (filter != null) {
			Pattern p = Pattern.compile(filter);
			localTable.removeIf(arr -> {
				String s = arr[findex];
				return !p.matcher(s).matches();
			});
		}

		if (localTable.isEmpty()) {
			String[] sa = new String[rows];
			localTable.add(sa);
		}

		localTable.forEach(arr -> {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == null) {
					arr[i] = "";
				}
			}
		});

		if (comparator != null) {
			localTable.sort(comparator);
		}

		for (String[] strings : localTable) {
			for (int i = 0; i < rows; i++) {
				if (line != null) {
					line.append(gc(VERTICAL_BSEP));
				} else {
					line = new StringBuilder();
					
				}
				String part = "";
				for (int j = 0; j < spacing; j++) {
					part += " ";
				}
				if (strings[i] != null) {
					switch (aligns[i]) {
					case LEFT:
						part += strings[i];
						break;
					case RIGHT:
						for (int j = 0; j < tableSizes[i] - strings[i].length(); j++) {
							part += " ";
						}
						part += strings[i];
						break;
					case CENTER:
						for (int j = 0; j < (tableSizes[i] - strings[i].length()) / 2; j++) {
							part += " ";
						}
						part += strings[i];
						break;
					}
				}
				while (part.length() < tableSizes[i] + spacing) {
					part += " ";
				}
				for (int j = 0; j < spacing; j++) {
					part += " ";
				}
				line.append(part);
			}
			
			System.out.println(line.toString());

			line = null;
		}
		endingLine();
		
	}

	private void endingLine() {
		StringBuilder line;
		line = null;
		for (int i = 0; i < rows; i++) {
			if (line != null) {
				line.append(gc(BLINE));
			} else {
				line = new StringBuilder();
			}
			for (int j = 0; j < tableSizes[i] + 2 * spacing; j++) {
				line.append(gc(BLINE));
			}
		}
		System.out.println(line.toString());
	}

	private String gc(String[] src) {
		return src[ucode ? 1 : 0];
	}

	public static enum EnumAlignment {
		LEFT, CENTER, RIGHT
	}
}
