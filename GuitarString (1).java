import java.util.Random;

public class GuitarString {
    // YOUR INSTANCE VARIABLES HERE
    private static double SAMPLING_RATE = 44100;
    private int t = 0;


    RingBuffer buff;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        Double a = Math.ceil(SAMPLING_RATE/frequency);
        buff = new RingBuffer(a.intValue());
        //System.out.println(a.intValue());
        for (int i = 0; i < a; i++){
            buff.enqueue(0);
        }
        //System.out.println(buff.size());

    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        buff = new RingBuffer(init.length);
        for (int i = 0; i < init.length; i++){
            buff.enqueue(init[i]);
        }
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return buff.size();
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        Random random = new Random();
        for (int i = 0; i < buff.size(); i++){
            double rand = random.nextDouble()-0.5;
            buff.dequeue();
            buff.enqueue(rand);
        }
    }

    // advances the Karplus-String simulation one time step
    public void tic() {
            buff.enqueue(((buff.dequeue()+buff.peek())*.5)*0.996);
        t++;
    }

    // returns the current sample
    public double sample() {
    return buff.peek();
    }

    public int time() {                          // return number of times tic was called so far
    return t;
    }


    // unit tests this class
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }


}