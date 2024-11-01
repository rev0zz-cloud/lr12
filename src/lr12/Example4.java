/*4. Напишите программу, которая создает 10 потоков
и каждый поток выводит на экран свой номер.*/
package lr12;

public class Example4 {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        Thread t4 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        Thread t5 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        Thread t6 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        Thread t7 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        Thread t8 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        Thread t9 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        Thread t10 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        t9.join();
        t10.join();

    }
}
