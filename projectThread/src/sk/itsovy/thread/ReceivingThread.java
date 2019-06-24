package sk.itsovy.thread;

public class ReceivingThread {
        private String packet;
        private boolean traffic = true;
    public synchronized String receive() {
        while (traffic) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        traffic = true;

        notifyAll();
        return packet;
    }
}
