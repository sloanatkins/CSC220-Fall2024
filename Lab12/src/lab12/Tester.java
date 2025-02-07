package lab12;

public class Tester {

	public static void main(String[] args) {
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
		
		System.out.println(p1);
		p1.writeOutput();
	}
}
