public class RingBuffer {

    private int capacity;
    private double[] array;
    private int first;
    private int last;
    private double r;
    private int size = 0;


    public RingBuffer(int capacity) {
        this.capacity = capacity;
        array = new double[capacity];
        first = 0;
        last = 0;


    }

    public int capacity() {
        return capacity;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        boolean v = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                v = false;
            } else {
                v = true;
            }
        }
        return v;
    }

    public boolean isFull() {
        boolean t = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                t = true;
            } else {
                t = false;
            }
        }
        return t;
    }

    public void enqueue(double x) {

        array[last] = x;
        if (first == last && !isEmpty()) {
            if (last == capacity - 1) {
                last = 0;

            } else {
                first++;
                last++;
            }
        } else {
            //throw new RuntimeException("Queue is full cant add");
            if (last == capacity - 1) {
                last = 0;
            } else {
                last++;
            }
        }
        size++;
    }

    public double dequeue() {

        r = array[first];
        array[first] = 0;
        if (first == capacity - 1) {
            first = 0;
        } else {
            first++;
        }
        size--;
        return r;
    }


    public double peek() {
        
        return array[first];

    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        System.out.println(buffer.isEmpty());
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);

        }
        System.out.println(buffer.isFull());
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);

        }
        StdOut.println(buffer.peek());
    }
}
