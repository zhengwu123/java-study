import javax.swing.*;

/**
 * Created by zhengwu on 11/11/16.
 */
public class FoilMakerRunner {
    public static void main(String[] args) {
        FoilMakerModel foilMakerModel = new FoilMakerModel();
     FoilMakerClient view = new FoilMakerClient();
        FoilMakerController foilMakerController = new FoilMakerController(view,foilMakerModel);
        FoilMakerClient view2 = foilMakerController.getView();
        Thread thread = new Thread() {
            public void run() {

                view2.setTitle("FoilMaker!");
                view2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                view2.setSize(350,600);
                view2.setVisible(true);
            }
        };
        thread.start();
    }
}
