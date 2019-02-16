/**
 * Created by Chalkias Konstantinos
 * Date: 20 Αυγ 2006
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TimeLockPuzzle extends JFrame {

    BorderLayout initialLayout = new BorderLayout();
    GridLayout textGrid = new GridLayout(2,1);

    JPanel contentPane = new JPanel();
    JPanel textPanel = new JPanel();
    JPanel clearPanel = new JPanel();
    JPanel cryptoPanel = new JPanel();
    JPanel optionPanel = new JPanel();

    JMenuBar jMenuBar1 = new JMenuBar();

    JMenu jMenuFile = new JMenu();
    JMenuItem exit = new JMenuItem();
    JMenuItem openClear = new JMenuItem();
    JMenuItem saveClear = new JMenuItem();
    JMenuItem openEncrypted = new JMenuItem();
    JMenuItem saveEncrypted = new JMenuItem();

    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpAbout = new JMenuItem();
    JLabel statusBar = new JLabel();


    JMenu jMenuOption = new JMenu();
    JMenuItem encrypt = new JMenuItem();
    JMenuItem decrypt = new JMenuItem();
    JMenuItem createKeys = new JMenuItem();
    JMenuItem restoreKeys = new JMenuItem();
    JMenuItem restorePrivateKey = new JMenuItem();

    TextArea clearArea = new TextArea();
    TextArea cryptoArea = new TextArea();
    JLabel clearLabel = new JLabel("Clear text");
    JLabel cryptoLabel = new JLabel("Encrypted text");

    JLabel optionLabel = new JLabel("Options");

    public TimeLockPuzzle() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            FrameInit();
            } catch (Exception exception) {
              exception.printStackTrace();
            }

    }


    public static void main(String args[]) {
        TimeLockPuzzle tLP = new TimeLockPuzzle();
        tLP.setVisible(true);

     }
        private void FrameInit() throws Exception {

            setSize(new Dimension(640, 480));
            setTitle("TimeLockPuzzle");
            this.add(contentPane);

            contentPane.setLayout(initialLayout);

            textPanel.setLayout(textGrid);
            clearPanel.setLayout(new BorderLayout());
            cryptoPanel.setLayout(new BorderLayout());
            optionPanel.setLayout(new BorderLayout());

            clearArea.setSize(new Dimension(200,100));
            cryptoArea.setSize(new Dimension(200,100));
            statusBar.setText(" ");
            jMenuFile.setText("File");

            openClear.setText("Open clear-text file");
            saveClear.setText("Save clear-text file");
            openEncrypted.setText("Open encrypted file");
            saveEncrypted.setText("Save encrypted file");


            exit.setText("Exit");
            exit.addActionListener(new TimeLockPuzzle_jMenuFileExit_ActionAdapter(this));





            jMenuHelp.setText("Help");
            jMenuHelpAbout.setText("About");
            jMenuHelpAbout.addActionListener(new
                                             TimeLockPuzzle_jMenuHelpAbout_ActionAdapter(this));
            jMenuOption.setText("Options");
            encrypt.setText("Encrypt");
            encrypt.addActionListener(new TimeLockPuzzle_encrypt_actionAdapter(this));
            decrypt.setText("Decrypt");
            decrypt.addActionListener(new TimeLockPuzzle_decrypt_actionAdapter(this));
            createKeys.setText("Create new KeyPair");
            createKeys.setToolTipText("");
            createKeys.addActionListener(new TimeLockPuzzle_createPair_actionAdapter(this));
            restoreKeys.setText("Restore KeyPair");
            restoreKeys.addActionListener(new TimeLockPuzzle_restorePair_actionAdapter(this));
            restorePrivateKey.setText("Restore Private Key");
            restorePrivateKey.addActionListener(new TimeLockPuzzle_restorePriv_actionAdapter(this));
            jMenuBar1.add(jMenuFile);
            jMenuBar1.add(jMenuOption);
            jMenuFile.add(openClear);
            jMenuFile.add(saveClear);
            jMenuFile.add(openEncrypted);
            jMenuFile.add(saveEncrypted);
            jMenuFile.add(exit);

            jMenuBar1.add(jMenuHelp);
            jMenuHelp.add(jMenuHelpAbout);
            setJMenuBar(jMenuBar1);
            //contentPane.add(statusBar, BorderLayout.SOUTH);
            jMenuOption.add(createKeys);
            jMenuOption.add(restoreKeys);
            jMenuOption.add(restorePrivateKey);
            jMenuOption.add(encrypt);
            jMenuOption.add(decrypt);

            contentPane.add(textPanel, BorderLayout.CENTER);
            contentPane.add(optionPanel, BorderLayout.EAST);

            textPanel.add(clearPanel);
            textPanel.add(cryptoPanel);

            clearPanel.add(clearLabel, BorderLayout.NORTH);
            clearPanel.add(clearArea, BorderLayout.CENTER);
            cryptoPanel.add(cryptoLabel, BorderLayout.NORTH);
            cryptoPanel.add(cryptoArea, BorderLayout.CENTER);

            optionPanel.add(optionLabel, BorderLayout.NORTH);
        }

        /**
         * File | Exit action performed.
         *
         * @param actionEvent ActionEvent
         */
        void jMenuFileExit_actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }

        /**
         * Help | About action performed.
         *
         * @param actionEvent ActionEvent
         */
        void jMenuHelpAbout_actionPerformed(ActionEvent actionEvent) {

        }

        public void encrypt_actionPerformed(ActionEvent actionEvent) {

        }

        public void decrypt_actionPerformed(ActionEvent actionEvent) {

        }

        public void createPair_actionPerformed(ActionEvent actionEvent) {

        }

        public void restorePair_actionPerformed(ActionEvent actionEvent) {

        }
        public void restorePriv_actionPerformed(ActionEvent actionEvent) {

        }
    }


    class TimeLockPuzzle_encrypt_actionAdapter implements ActionListener {
        private TimeLockPuzzle adaptee;
        TimeLockPuzzle_encrypt_actionAdapter(TimeLockPuzzle adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            adaptee.encrypt_actionPerformed(actionEvent);
        }
    }


    class TimeLockPuzzle_decrypt_actionAdapter implements ActionListener {
        private TimeLockPuzzle adaptee;
        TimeLockPuzzle_decrypt_actionAdapter(TimeLockPuzzle adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            adaptee.decrypt_actionPerformed(actionEvent);
        }
    }


    class TimeLockPuzzle_createPair_actionAdapter implements ActionListener {
        private TimeLockPuzzle adaptee;
        TimeLockPuzzle_createPair_actionAdapter(TimeLockPuzzle adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            adaptee.createPair_actionPerformed(actionEvent);
        }
    }


    class TimeLockPuzzle_restorePair_actionAdapter implements ActionListener {
        private TimeLockPuzzle adaptee;
        TimeLockPuzzle_restorePair_actionAdapter(TimeLockPuzzle adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            adaptee.restorePair_actionPerformed(actionEvent);
        }
    }
    class TimeLockPuzzle_restorePriv_actionAdapter implements ActionListener {
        private TimeLockPuzzle adaptee;
        TimeLockPuzzle_restorePriv_actionAdapter(TimeLockPuzzle adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            adaptee.restorePriv_actionPerformed(actionEvent);
        }
    }


    class TimeLockPuzzle_jMenuFileExit_ActionAdapter implements ActionListener {
        TimeLockPuzzle adaptee;

        TimeLockPuzzle_jMenuFileExit_ActionAdapter(TimeLockPuzzle adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            adaptee.jMenuFileExit_actionPerformed(actionEvent);
        }
    }


    class TimeLockPuzzle_jMenuHelpAbout_ActionAdapter implements ActionListener {
        TimeLockPuzzle adaptee;

        TimeLockPuzzle_jMenuHelpAbout_ActionAdapter(TimeLockPuzzle adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            adaptee.jMenuHelpAbout_actionPerformed(actionEvent);
        }
    }

