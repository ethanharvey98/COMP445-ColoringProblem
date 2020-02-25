package ColoringProblem;

public class ColoringProblem {
	
	public static void main(String[] args) {
		ConnectionMap map = new ConnectionMap(5, 2, "data.txt");
		System.out.println(map);
		ConnectionMap result = BacktrackSearch.backtrack(map, 0);
		if (result != null) {
			System.out.println(result);
		}
		else {
			System.out.println("No solution");
		}
	}
}
