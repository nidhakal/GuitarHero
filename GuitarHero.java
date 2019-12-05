public class GuitarHero {
    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] piano = new GuitarString[37];
        int index;


        for (int i = 0; i < 37; i++) {
            double note = (440 * (Math.pow(2, ((i - 24.0) / 12.0))));

            piano[i] = new GuitarString(note);
            ;
        }

        while (true) {


            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);

                //double sample = piano[keyboard.indexOf(key)].sample();
                //StdAudio.play(sample);
                if (index == -1) {
                    continue;
                }
                piano[index].pluck();
            }
            double sample = 0;

            for (int i = 0; i < 37; i++) {
                sample = sample + piano[i].sample();

            }
            StdAudio.play(sample);
            for (int i = 0; i < 37; i++) {
                piano[i].tic();

            }

        }
    }
}
