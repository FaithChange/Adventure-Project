package entities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import java.util.List;

public class Map {
	
	private int width;
	private int height;
	private char[][] grid;
	private static Map map;
	
	private Map(){
		
		try {
			Path pathToMap = Paths.get("src/main/resources/map.txt");
			List<String> mapRows = Files.readAllLines(pathToMap, StandardCharsets.UTF_8);
			height = mapRows.size();
			width = mapRows
		             .stream()
		             .max(Comparator.comparingInt(String::length))
		             .get().length();
			grid = new char[width][height];
			
			for(int i = 0; i < mapRows.size(); i++) {
				char[] row = mapRows.get(i).toCharArray();
				for(int j = 0; j < row.length; j++) {
					grid[j][i] = row[j];
				}
			}
	
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static Map getInstance() {
		
		if(map != null) return map;
		
		map = new Map();
		return map;
	}
	public char[][] getGrid() {
		return grid;
	}
	
	

}
