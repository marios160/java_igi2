package aayush.atharva.server.controller.x;

public class UpdatePlayers extends Thread {

    GUI_Main g;

    public UpdatePlayers(GUI_Main g) {
        this.g = g;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                this.g.updateTable();
                Thread.sleep(1000L);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
