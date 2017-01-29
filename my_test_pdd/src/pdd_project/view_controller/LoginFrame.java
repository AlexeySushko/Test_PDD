package pdd_project.view_controller;

import pdd_project.Constants;
import pdd_project.model.Methods;
import pdd_project.model.Utilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

/**
 * Created by Alexey Sushko
 * Класс создания окна авторизации
 */
public class LoginFrame extends JFrame implements ActionListener{


    public static JFrame loginFrame;
    private Box mainBox, horizontalLayoutLogin, horizontalLayoutPassword,horizontalLayoutButtons;
    private JLabel loginLabel, passwordLabel;
    private JButton okButton, cancelButton, newUserButton;
    private static JTextField textFieldText;
    private static JPasswordField passwordField;

    public LoginFrame() {

        loginFrame = new JFrame(Constants.NAME_WINDOW_AUTORIZATION);

        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Настраиваем первую горизонтальную панель Логин
        horizontalLayoutLogin = Box.createHorizontalBox();

        loginLabel = new JLabel(Constants.STRING_LOGIN);
        textFieldText = new JTextField(20);

        horizontalLayoutLogin.add(loginLabel);
        horizontalLayoutLogin.add(Box.createHorizontalStrut(20));
        horizontalLayoutLogin.add(textFieldText);
        //------------------------------------------------------------------------------
        //Настраиваем вторую панель Пароль
        horizontalLayoutPassword = Box.createHorizontalBox();
        passwordLabel = new JLabel(Constants.STRING_PASSWORD);
        passwordField = new JPasswordField(20);

        horizontalLayoutPassword.add(passwordLabel);
        horizontalLayoutPassword.add(Box.createHorizontalStrut(20));
        horizontalLayoutPassword.add(passwordField);
        //-------------------------------------------------------------------------------
        //Настраиваем третью горизонталную панель NewUser; OK; Отмена
        horizontalLayoutButtons = Box.createHorizontalBox();

        newUserButton = new JButton(Constants.NAME_BUTTON_NEW_USER);
        newUserButton.addActionListener(this);

        okButton = new JButton(Constants.NAME_BUTTON_OK);
        okButton.addActionListener(this);

        cancelButton = new JButton(Constants.NAME_BUTTON_CANCEL);
        cancelButton.addActionListener(this);

        horizontalLayoutButtons.add(Box.createHorizontalStrut(12));
        horizontalLayoutButtons.add(newUserButton);
        horizontalLayoutButtons.add(Box.createHorizontalGlue());
        horizontalLayoutButtons.add(okButton);
        horizontalLayoutButtons.add(Box.createHorizontalStrut(12));
        horizontalLayoutButtons.add(cancelButton);

        //Уточняем размеры компонентов
        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

        //Размещаем три гориз панели на одной вертик панели
        mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        mainBox.add(horizontalLayoutLogin);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(horizontalLayoutPassword);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(horizontalLayoutButtons);

        loginFrame.setContentPane(mainBox);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(actionCommand.equals(Constants.NAME_BUTTON_OK)){
            //если введенные значени равны Admin Admin
            if(getLoginTextField().equals("Admin")&&getPasswordFieldPassword().equals("tl85v5sIgD4=")){//Пароль зашифрован- "Admin"
                loginFrame.setVisible(false);
                Methods.createLists();
                new AdminFrame();
            }
            else {
                Methods.jsonReadData();
                setTextFieldText();
                setPasswordField();
            }
        }

        if(actionCommand.equals(Constants.NAME_BUTTON_CANCEL)){
            System.exit(0);
        }
        if(actionCommand.equals(Constants.NAME_BUTTON_NEW_USER)){
            setTextFieldText();
            setPasswordField();
            loginFrame.setVisible(false);
            new NewUserFrame();
        }
    }


    public static String getLoginTextField(){
        return new String(textFieldText.getText());
    }

    public static  String getPasswordFieldPassword() {
        return Utilities.encryptText(new String(passwordField.getPassword()));
    }

    public void setPasswordField() {
        this.passwordField.setText(null);
    }

    public void setTextFieldText() {
        this.textFieldText.setText(null);
    }
}

