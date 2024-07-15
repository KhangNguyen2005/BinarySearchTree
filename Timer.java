package assign08;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.TreeSet;

public class Timer extends TimerTemplate {
    Random random = new Random();
    BinarySearchTree<Integer> sortedBST = new BinarySearchTree<>();
    BinarySearchTree<Integer> randomBST = new BinarySearchTree<>();
    TreeSet<Integer> treeSet = new TreeSet<>();


    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public Timer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);

    }

    @Override
    protected void setup(int n) {
        randomBST.clear();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int num : numbers) {
            randomBST.add(num);
        }

//        treeSet.clear();
//        ArrayList<Integer> numbers = new ArrayList<>();
//        for (int i = 0; i < n; i++){
//            numbers.add(i);
//        }
//        Collections.shuffle(numbers);
//
//        for(int num: numbers)
//            treeSet.add(num);

//        sortedBST.clear();
//        for (int i = 0; i < n; i++){
//            sortedBST.add(i);
//        }
    }

    @Override
    protected void timingIteration(int n) {
        for (int i = 0; i < n; i++) {
//            sortedBST.contains(random.nextInt(0, n));
            randomBST.contains(i);
//            treeSet.contains(i);
        }

    }

    @Override
    protected void compensationIteration(int n) {

    }

    public static void main(String[] args) {
        int[] problemSizes = new int[20];
        for (int i = 0; i < problemSizes.length; i++) {
            problemSizes[i] = 10000 + i * 10000;
        }

        int timesToLoop = 1000;
        var timer = new Timer(problemSizes, timesToLoop);
        timer.setup(10);
        var results = timer.run();

        try {
            FileWriter csvWriter = new FileWriter("Result.csv");
            csvWriter.write("n, time\n");

            for (Result result : results) {
                String line = result.n() + ", " + result.avgNanoSecs() + "\n";
                csvWriter.write(line);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
