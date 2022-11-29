import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    //the threads are placed into a FIFO queue when blocked, so any starvation problems are solved.
    public  static Semaphore readLock = new Semaphore(1 , true);
    public volatile static int readCount = 0; //every thread will read/write the value in main memory only. (atomic)

    @Override
    public void run() {
        try {
            //Acquire Section
            readLock.acquire();
            synchronized(this) { //only thread it can proceed, otherwise it will wait.
                readCount++;
            }
            //tack the lock from Writer and prevent from enter with Reader, so any deadlock problems are solved.
            if (readCount == 1) {
                Writer.writeLock.acquire();
            }
            readLock.release();

            //Reading section
            System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
            Thread.sleep(1500);
            System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

            //Releasing section
            readLock.acquire();
            synchronized(this) {
                readCount--;
            }
            //leave the lock to Writer and allow to enter after Reader finish, so any deadlock problems are solved.
            if(readCount == 0) {
                Writer.writeLock.release();
            }
            readLock.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
