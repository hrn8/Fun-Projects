package Menu; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javafx.beans.binding.Bindings.length;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MarkerSetter extends JPanel{

    public MarkerSetter(){
        super();
    }

    private JLabel[][] createLabels(int width, int length){
        JLabel[][] labels=new JLabel[width][length];
        for (int i=0;i<width;i++){
            for (int k = 0; k < length; k++){
                labels[i][k]=new JLabel("message" + i);
            }
        }
        return labels;
    }

    private void showGUI(int length, int width){
        JLabel[][] labels= createLabels(length, width);
        for (int i=0;i<length;i++){
            for (int k = 0; k < width; k++){
                labels[i][k].setSize(5, 5);
                labels[i][k].setLocation(5*i, 5*k);
                this.add(labels[i][k]);
            }
        }
    }
}

