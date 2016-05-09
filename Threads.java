public class Threads implements Runnable {

    int  currentPos = 2;

    Threads(int currentPos) {
        this.currentPos = currentPos;
    }

    // find non prime numbers
    public void run() {
        for (int i = 2; i*currentPos < main.maxSearch; i++) {
            main.isPrime[i*currentPos] = false;
        }
    }
}
