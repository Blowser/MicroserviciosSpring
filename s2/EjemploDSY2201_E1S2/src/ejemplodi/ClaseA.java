package ejemplodi;

public class ClaseA {

	private ClaseB claseB;

	public ClaseA(ClaseB claseB) {

		this.claseB = claseB;
	}

	public void ejecutarTarea() {

		claseB.realizarLogica();
	}
}
