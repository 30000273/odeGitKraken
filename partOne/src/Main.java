//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Thread[] threadArr = new Thread[1000];
    public static void main(String[] args) {
        for(int i = 0; i < threadArr.length; i++){
            threadArr[i] = new Thread(new countToOneMillion());
            threadArr[i].run();
        }
        for(int i = 0; i < threadArr.length; i++){
            System.out.println(threadArr[i].toString());
        }

    }
}

class countToOneMillion implements Runnable {
    @Override
    public void run() {
        int sum = 0;
        for(int i = 1; i < 1000000; i++) {
            sum = sum + i;
        }
    }
}

