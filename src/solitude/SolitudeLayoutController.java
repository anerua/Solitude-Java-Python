/*
 * @author: Martins Anerua.
 * Martins Anerua © 2018-2019.
 * All rights reserved.
 */
package solitude;

import com.sun.prism.paint.Color;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * 31-12-2018
 * @author MARTINS
 */
public class SolitudeLayoutController implements Initializable {
    
    @FXML private ImageView A,B,C,D,E,F,G,H,I;
    @FXML private GridPane grid;
    
    Image img_ex, img_oh;
    int i = 0;
    PythonInterpreter interp = null;
    Logic logic = new Logic();
      
    @FXML 
    private void handleAction(MouseEvent event){
        img_ex = new Image(SolitudeLayoutController.class.getResource("/solitude/icns/ex.png").toString());
        img_oh = new Image(SolitudeLayoutController.class.getResource("/solitude/icns/oh.png").toString());
        
        String move = ((ImageView) event.getSource()).getId();

        logic.exec("l.pMove('" + move + "')", interp);
        ((ImageView) event.getSource()).setImage(img_ex);
        ((ImageView) event.getSource()).setDisable(true);
        
        
        if (logic.eval("len(l.avail_moves)", interp).toString().equals("0")){
            // draw game
            System.out.println("Draw game");
        }
        else {
            PyObject play = logic.eval(tactic(i), interp);
            getView(play.toString()).setImage(img_oh);
            getView(play.toString()).setDisable(true);

            PyObject won = logic.eval("l.won", interp);
            if (won.toString().equals("True")){
                // won game
                System.out.println("You lost!");
            }
            else {
                ++i;
            }
        }
    }
    
    public ImageView getView(String play){
        if(A.getId().equals(play)){
            return A;
        }
        else if(B.getId().equals(play)){
            return B;
        }
        else if(C.getId().equals(play)){
            return C;
        }
        else if(D.getId().equals(play)){
            return D;
        }
        else if(E.getId().equals(play)){
            return E;
        }
        else if(F.getId().equals(play)){
            return F;
        }
        else if(G.getId().equals(play)){
            return G;
        }
        else if(H.getId().equals(play)){
            return H;
        }
        else if(I.getId().equals(play)){
            return I;
        }
        else{
            return null;
        }
    }
    
    public String tactic(int state){
        switch(state){
            case 0:
                return "l.firstMove()";
            case 1:
                return "l.secondMove()";
            case 2:
                return "l.thirdMove()";
            case 3:
                return "l.fourthMove()";
            case 4:
                return "l.fifthMove()";
            default:
                return "l.firstMove()";
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Properties p = new Properties();
            p.setProperty("python.path", "C:\\Users\\MARTINS\\Documents\\jython2.7.0");
            p.setProperty("python.home", "C:\\Users\\MARTINS\\Documents\\jython2.7.0");
            p.setProperty("python.prefix", "C:\\Users\\MARTINS\\Documents\\jython2.7.0");
            PythonInterpreter.initialize(System.getProperties(), p, new String[] {});
            interp = new PythonInterpreter();            
        }
        catch(Exception ex){
//            log.error("Exception while creating python interpreter: " + ex.toString());
        }
        logic.execfile(logic.file, interp);
        logic.exec("l = Logic()", interp);   
        
    }       
}