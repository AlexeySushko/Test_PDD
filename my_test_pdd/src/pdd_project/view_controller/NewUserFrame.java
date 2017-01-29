package pdd_project.view_controller;

import pdd_project.Constants;
import pdd_project.model.Methods;
import pdd_project.model.Utilities;
import pdd_project.model.entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Alexey Sushko
 * Создание оклна для добавления нового пользователя в программу
 */
public class NewUserFrame extends JFrame implements ActionListener {

    public static JFrame newUserFrame;

    private Box mainNewUserBox, horizontalLayoutNewUserLogin, horizontalLayoutNewUserPassword,
                horizontalLayoutNewUserPassword2, horizontalNewUserLayoutButtons;

    private JLabel loginNewUserLabel, passwordNewUserLabel, passwordNewUserLabel2;

    private static JTextField textNewUserFieldText;

    private static JPasswordField passwordNewUserField;

    private JPasswordField passwordNewUserField2;

    private JButton saveNewUserButton, exitNewUserButton;

    public NewUserFrame() {

        newUserFrame = new JFrame(Constants.NAME_WINDOW_NEW_USER);
        newUserFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Панель ЛОГИН
        horizontalLayoutNewUserLogin = Box.createHorizontalBox();
        loginNewUserLabel = new JLabel(Constants.STRING_LOGIN);
        textNewUserFieldText = new JTextField(Constants.SIZE_LONG_TEXT_WINDOW);

        horizontalLayoutNewUserLogin.add(loginNewUserLabel);
        horizontalLayoutNewUserLogin.add(Box.createHorizontalStrut(Constants.SIZE_TEXT_WINDOW));
        horizontalLayoutNewUserLogin.add(textNewUserFieldText);
        //Панель ПАРОЛЬ
        horizontalLayoutNewUserPassword = Box.createHorizontalBox();
        passwordNewUserLabel = new JLabel(Constants.STRING_PASSWORD);
        passwordNewUserField = new JPasswordField(Constants.SIZE_LONG_TEXT_WINDOW);

        horizontalLayoutNewUserPassword.add(passwordNewUserLabel);
        horizontalLayoutNewUserPassword.add(Box.createHorizontalStrut(Constants.SIZE_TEXT_WINDOW));
        horizontalLayoutNewUserPassword.add(passwordNewUserField);
        //Панель Повторите пароль
        horizontalLayoutNewUserPassword2 = Box.createHorizontalBox();
        passwordNewUserLabel2 = new JLabel(Constants.STRING_REPEAD_PASSWORD);
        passwordNewUserField2 = new JPasswordField(Constants.SIZE_LONG_TEXT_WINDOW);

        horizontalLayoutNewUserPassword2.add(passwordNewUserLabel2);
        horizontalLayoutNewUserPassword2.add(Box.createHorizontalStrut(Constants.SIZE_TEXT_WINDOW));
        horizontalLayoutNewUserPassword2.add(passwordNewUserField2);
        //Панель с кнопками СОХРАНИТЬ и ОТМЕНА
        horizontalNewUserLayoutButtons = Box.createHorizontalBox();

        saveNewUserButton = new JButton(Constants.NAME_BUTTON_SAVE);
        saveNewUserButton.addActionListener(this);//Добавляем слушателя

        exitNewUserButton = new JButton(Constants.NAME_BUTTON_CANCEL);
        exitNewUserButton.addActionListener(this);//Добавляем слушателя

        horizontalNewUserLayoutButtons.add(Box.createHorizontalGlue());
        horizontalNewUserLayoutButtons.add(saveNewUserButton);
        horizontalNewUserLayoutButtons.add(Box.createHorizontalStrut(12));
        horizontalNewUserLayoutButtons.add(exitNewUserButton);
        //Уточняем размеры
        passwordNewUserLabel.setPreferredSize(passwordNewUserLabel2.getPreferredSize());
        loginNewUserLabel.setPreferredSize(passwordNewUserLabel.getPreferredSize());
        //Собираем все до кучи в одну панель
        mainNewUserBox = Box.createVerticalBox();
        mainNewUserBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        mainNewUserBox.add(horizontalLayoutNewUserLogin);
        mainNewUserBox.add(Box.createVerticalStrut(12));
        mainNewUserBox.add(horizontalLayoutNewUserPassword);
        mainNewUserBox.add(Box.createVerticalStrut(12));
        mainNewUserBox.add(horizontalLayoutNewUserPassword2);
        mainNewUserBox.add(Box.createVerticalStrut(12));
        mainNewUserBox.add(horizontalNewUserLayoutButtons);

        newUserFrame.setContentPane(mainNewUserBox);
        newUserFrame.pack();
        newUserFrame.setLocationRelativeTo(null);
        newUserFrame.setVisible(true);
        newUserFrame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals(Constants.NAME_BUTTON_SAVE)){
            getTextNewUserFieldText();
            getPasswordNewUserField();
            getPasswordNewUserField2();
            String x = "hs5CmZu/67Y=";//Это пустое значение при кодировке пароля
            if (getPasswordNewUserField().equals(getPasswordNewUserField2()) &
                    (((!getTextNewUserFieldText().equals("") & !getTextNewUserFieldText().equals("Admin")) &
                            !getPasswordNewUserField().equals(x) &
                            !getPasswordNewUserField2().equals(x)))) {
                try {
                    if(Methods.searchLoginInFile() == true){
                        JOptionPane.showMessageDialog(null, "Польлзователь с таким именем уже зарегистрирован!");
                    }
                    if(Methods.searchLoginInFile()==false){
                        newUserFrame.setVisible(false);
                        Methods.addNewUser();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (!getPasswordNewUserField().equals(getPasswordNewUserField2()) |
                    getTextNewUserFieldText().equals("") | getPasswordNewUserField().equals(x) |getPasswordNewUserField2().equals(x)){

                JOptionPane.showMessageDialog(null, Constants.TEXT_PASSWORD_NO_PASSWORD);
            }
            setTextNewUserFieldText();
            setPasswordNewUserField();
            setPasswordNewUserField2();
        }
        if(actionCommand.equals(Constants.NAME_BUTTON_CANCEL)){
            LoginFrame.loginFrame.setVisible(true);
            newUserFrame.setVisible(false);
        }

    }
    public static String getTextNewUserFieldText() {
        return textNewUserFieldText.getText();
    }

    public static String getPasswordNewUserField() {
        return Utilities.encryptText(new String(passwordNewUserField.getPassword()));
    }

    public String getPasswordNewUserField2() {
        return Utilities.encryptText(new String(passwordNewUserField2.getPassword()));
    }

    public void setTextNewUserFieldText() {
        this.textNewUserFieldText.setText(null);
    }

    public void setPasswordNewUserField2() {
        this.passwordNewUserField2.setText(null);
    }

    public void setPasswordNewUserField() {
        this.passwordNewUserField.setText(null);
    }
}