package ColoringProblem;
import java.io.*;
import java.util.ArrayList;

public class BacktrackSearch {
	
	/**
	 * DO NOT MODIFY THIS METHOD. It should copy the ConnectionMap argument and, on the copy, attempt to assign different values to the variable at position "index."
	 *   The method should then call itself recursively until it either produces a full solution or determines that no solution is possible for the map it was originally given.
	 *   Example: For a three-node problem, you might initially call backtrack(map, 0), which would attempt various values
	 *   for the 0th node; for each possible value, it would make several calls to backtrack(map, 1). Each of those would
	 *   make calls to backtrack(map, 2), finally returning either success or failure.
	 * @param map The original connection map
	 * @param index The variable to be assigned a value.
	 * @return A solved ConnectionMap, if a solution is possible from this starting point, or null otherwise.
	 */
	public static ConnectionMap backtrack(final ConnectionMap map, int index) {
		// Returns null if first variable has no possible values
		if (map.domains.get(0).size()==0) {
			return null;
		}
		// If the possible values for the index are null step back
		if (map.domains.get(index).size()==0) {
			//System.out.println("BACKTRACK!");
			ArrayList<Integer> newRow = new ArrayList<Integer>();
			for (int k = 0; k < map.numColors; k++) {
				newRow.add(k);
			}
			map.domains.set(index, newRow);
			map.domains.get(index-1).remove(0);
			BacktrackSearch.backtrack(map, index-1);
		}
		// Loops through the possible values for the index
		for (int j=index; j>=0; j--) {
			if(map.connections[index][j]==1 && map.domains.get(index).size()>0) {
				ArrayList<Integer> neighbor = map.domains.get(j);
				if (neighbor.size() != 0) {
					map.domains.get(index).remove(neighbor.get(0));
				}
				else {
					return null;
				}
			}
			// If the possible values for the index are null step back
			if (map.domains.get(index).size()==0) {
				//System.out.println("BACKTRACK!");
				ArrayList<Integer> newRow = new ArrayList<Integer>();
				for (int k = 0; k < map.numColors; k++) {
					newRow.add(k);
				}
				map.domains.set(index, newRow);
				map.domains.get(index-1).remove(0);
				BacktrackSearch.backtrack(map, index-1);
			}
		}
		// If the index is not the last index step forward
		if (index < map.domains.size()-1) {
			BacktrackSearch.backtrack(map, index+1);
		}
		// Returns null if first variable has no possible values
		if (map.domains.get(0).size()==0) {
			return null;
		}
		// Removes end of colors
		for(int i = 0; i < map.domains.size(); i++) {
			for(int j = 1; j < map.domains.get(i).size(); j++) {
				map.domains.get(i).remove(j);
			}
		}
		// Returns map
		return map;	
	}
}
