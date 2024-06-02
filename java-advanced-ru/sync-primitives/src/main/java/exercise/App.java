package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        SafetyList safetyList = new SafetyList();

        ListThread thread1 = new ListThread(safetyList);
        ListThread thread2 = new ListThread(safetyList);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("SIZE OF SAFETYLIST:\t" + safetyList.getSize());
        // END
    }
}
