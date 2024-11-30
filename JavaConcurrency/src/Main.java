import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Main {
    public static void work() {
        try {
            System.out.println(Thread.currentThread());
            Thread.sleep(5000);
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        /*If ten thousand tasks are created then it will go for out of memory exception*/
        int MAX = 10000;

//        for (int i = 0; i < MAX; i++) {
//            new Thread(() -> work()).start();
//
//        }
//        System.out.println("Completed");
//        /*In order to over come we can use VirtualThread that is been provided by JDK 19 */

        for (int i = 0; i < MAX; i++) {
            Thread.startVirtualThread(() -> work()).getName();

        }
        System.out.println("Completed");



    }


}
