public class Main {
    public static void main(String[] args) {
        //Shared message object between Reader and Writer threads.
        Reader read = new Reader();
        Writer write = new Writer();

        Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(read);
        t2.setName("thread2");
        Thread t3 = new Thread(write);
        t3.setName("thread3");
        Thread t4 = new Thread(read);
        t4.setName("thread4");
        Thread t5 = new Thread(write);
        t5.setName("thread5");
        t3.start();
        t2.start();
        t1.start();
        t4.start();
        t5.start();
    }
}