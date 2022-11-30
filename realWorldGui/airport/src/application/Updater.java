package application;

public class Updater {
	volatile String paris;
	volatile String london;
	volatile String newyork;

	public void run() {

		try {

			Reader read = new Reader();
			Thread thread = new Thread(read);

			thread.start();
			thread.join();

			paris = Integer.toString(read.getParis());
			london = Integer.toString(read.getLondon());
			newyork = Integer.toString(read.getNewyork());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String parisvalue() {
		return paris;
	}

	public String londonvalue() {
		return london;
	}

	public String newyorkvalue() {
		return newyork;
	}
}