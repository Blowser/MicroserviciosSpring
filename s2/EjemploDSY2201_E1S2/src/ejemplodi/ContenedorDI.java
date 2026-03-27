package ejemplodi;

public class ContenedorDI {

	public static void main(String[] args) {

		ClaseB claseB = new ClaseB();
		ClaseA claseA = new ClaseA(claseB);

		claseA.ejecutarTarea();
	}
}
