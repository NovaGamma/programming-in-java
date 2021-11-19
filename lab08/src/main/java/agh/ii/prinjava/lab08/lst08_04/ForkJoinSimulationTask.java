package agh.ii.prinjava.lab08.lst08_04;

import java.util.concurrent.RecursiveAction;

class ForkJoinSimulationTask extends RecursiveAction {
    private final int simSize;

    public ForkJoinSimulationTask(int simSize) {
        this.simSize = simSize;
    }

    @Override
    protected void compute() {
        if (simSize < 8) {
            try {
                System.out.println(Thread.currentThread().getName() +
                        ": simulation in progress for " + simSize + " seconds");
                Thread.sleep(simSize * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            ForkJoinSimulationTask task1 = new ForkJoinSimulationTask(simSize / 2);
            ForkJoinSimulationTask task2 = new ForkJoinSimulationTask(simSize / 2);
            invokeAll(task1, task2);
        }
    }
}
