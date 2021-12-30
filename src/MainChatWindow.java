import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MainChatWindowSub extends JFrame implements ActionListener, KeyListener {

    private BorderLayout bl = new BorderLayout(5,5);
    private TextArea textArea = new TextArea();
    private Label lb1 = new Label("Talking : ", Label.RIGHT);
    private TextField textField = new TextField();
    private Button bt1 = new Button("Send");
    private Button bt2 = new Button("LogOut");

    public MainChatWindowSub(String str){
        super(str);
        this.init();
        this.start();
        this.setSize(500, 400);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension di = tk.getScreenSize();
        Dimension di1 = this.getSize();
        this.setLocation((int)(di.getWidth() / 2 - di1.getWidth() / 2), (int)(di.getHeight() / 2 - di1.getHeight() / 2));
        //this.pack();
        this.setVisible(true);

        bt1.addActionListener(this);//이벤트메소드호출
        bt2.addActionListener(this);//이벤트메소드호출

        textField.addKeyListener(this);

    }

    private void start() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init(){
        this.setLayout(bl);

        Panel p = new Panel(new BorderLayout(2,2));
        Panel p1 = new Panel(new BorderLayout());
        p.add("North", p1);
        p.add("Center", textArea);
        Panel p2 = new Panel(new BorderLayout());
        p2.add("West", lb1);
        p2.add("Center", textField);
        p2.add("East", bt1);
        p.add("South", p2);
        this.add("Center", p);
        Panel p3 = new Panel(new BorderLayout(2,2));
        Panel p4 = new Panel(new BorderLayout());
        p3.add("North", p4 );
        Panel p6 = new Panel(new BorderLayout());
        Panel p7 = new Panel(new GridLayout(3,1 ));
        p6.add("South", p7);
        p3.add("Center", p6);

        Panel p5 = new Panel(new GridLayout(1,1));
        p5.add(bt2);
        p3.add("North", p5);
        this.add("East", p3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();

        if(e.getSource() == bt1) {
            textArea.append(text + "\n");
            textField.selectAll();
            textField.setText("");
        }
        if(e.getSource() == bt2) {
            bt2.addActionListener(e1 -> System.exit(0));
        }
    }

    //============ KeyListener ============

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        String text = textField.getText();
        if(key == KeyEvent.VK_ENTER){
            textArea.append("SERVER : " + text + "\n");
            textField.selectAll();
            textField.setText("");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
//============ KeyListener ============


public class MainChatWindow {

    public static void main(String[] args) {
        MainChatWindowSub exe = new MainChatWindowSub("");
    }
}