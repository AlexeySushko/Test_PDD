package pdd_project.view_controller;

import pdd_project.Constants;
import pdd_project.model.Methods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexey Sushko
 * Класс для создания окна администратора(при входе как администратор для добавления вопросов в программу)
 */
public class AdminFrame extends JFrame implements ActionListener{

    private JFrame mainFrame;
    private Box mainBox, horizontalBoxVarAnswer, verticalBoxVybor, verticalBoxVariants, buttonBox,
            horBox_A, horBox_B, horBox_C, finalHorBox, poleQuestionBox, poleTheoryBox, questionNumberBox,
            writeTextQuestionBox, writeTextTheoryBox, theoryNumberBox;
    private JLabel writeTextQuestion, writeVariantQuestion, writeTextTheory, textA, textB, textC;
    private JButton buttonAdd, buttonExit;

    public static ButtonGroup group;
    public static JTextField var1, var2, var3;
    public static JLabel questionNumber, theoryNumber;
    public static JTextArea poleQuestion, poleTheory;
    public static JRadioButton buttonAdmin_JRadio_A, buttonAdmin_JRadio_B, buttonAdmin_JRadio_C;
    public static String vybor = "", textQuestionNumber = "Вопрос ", textTheoryNumber = "Теория ";//Сюда буду добавлять json.size+1

    public AdminFrame(){

        mainFrame = new JFrame(Constants.NAME_WINDOW_ADD_TEXT_IN_FILE);

        questionNumberBox = Box.createHorizontalBox();
        questionNumber = new JLabel(textQuestionNumber);
        questionNumberBox.add(Box.createHorizontalGlue());
        questionNumberBox.add(questionNumber);
        questionNumberBox.add(Box.createHorizontalGlue());

        writeTextQuestion = new JLabel(Constants.STRING_WRITE_TEXT);
        writeTextQuestionBox = Box.createHorizontalBox();
        writeTextQuestionBox.add(writeTextQuestion);
        writeTextQuestionBox.add(Box.createHorizontalGlue());

        poleQuestion = new JTextArea(5,600);
        poleQuestion.setLineWrap(true);//Перенос строки
        poleQuestion.setWrapStyleWord(true);//Перенос слов
        poleQuestionBox = Box.createHorizontalBox();
        poleQuestionBox.add(new JScrollPane(poleQuestion));

        writeVariantQuestion = new JLabel(Constants.STRING_WRITE_VAR_ANSWERS);
        writeVariantQuestion.setHorizontalAlignment(SwingConstants.LEFT);

        textA = new JLabel("A: ");
        buttonAdmin_JRadio_A = new JRadioButton();
        buttonAdmin_JRadio_A.addActionListener(this);
        buttonAdmin_JRadio_A.setActionCommand(Constants.STRING_A);

        textB = new JLabel("B :");
        buttonAdmin_JRadio_B = new JRadioButton();
        buttonAdmin_JRadio_B.addActionListener(this);
        buttonAdmin_JRadio_B.setActionCommand(Constants.STRING_B);

        textC = new JLabel("C: ");
        buttonAdmin_JRadio_C = new JRadioButton();
        buttonAdmin_JRadio_C.addActionListener(this);
        buttonAdmin_JRadio_C.setActionCommand(Constants.STRING_C);

        group = new ButtonGroup();
        group.add(buttonAdmin_JRadio_A);
        group.add(buttonAdmin_JRadio_B);
        group.add(buttonAdmin_JRadio_C);

        horBox_A = Box.createHorizontalBox();
        horBox_A.add(textA);
        horBox_A.add(buttonAdmin_JRadio_A);
        horBox_B = Box.createHorizontalBox();
        horBox_B.add(textB);
        horBox_B.add(buttonAdmin_JRadio_B);
        horBox_C = Box.createHorizontalBox();
        horBox_C.add(textC);
        horBox_C.add(buttonAdmin_JRadio_C);

        verticalBoxVybor = Box.createVerticalBox();
        verticalBoxVybor.add(horBox_A);
        verticalBoxVybor.add(Box.createHorizontalStrut(7));
        verticalBoxVybor.add(horBox_B);
        verticalBoxVybor.add(Box.createHorizontalStrut(7));
        verticalBoxVybor.add(horBox_C);

        var1 = new JTextField();
        var2 = new JTextField();
        var3 = new JTextField();

        verticalBoxVariants = Box.createVerticalBox();
        verticalBoxVariants.add(var1);
        verticalBoxVariants.add(Box.createVerticalStrut(7));
        verticalBoxVariants.add(var2);
        verticalBoxVariants.add(Box.createVerticalStrut(7));
        verticalBoxVariants.add(var3);

        horizontalBoxVarAnswer = Box.createHorizontalBox();
        horizontalBoxVarAnswer.add(verticalBoxVybor);
        horizontalBoxVarAnswer.add(verticalBoxVariants);

        theoryNumber = new JLabel(textTheoryNumber);
        theoryNumberBox = Box.createHorizontalBox();
        theoryNumberBox.add(Box.createHorizontalGlue());
        theoryNumberBox.add(theoryNumber);
        theoryNumberBox.add(Box.createHorizontalGlue());

        writeTextTheory = new JLabel(Constants.STRING_WRITE_THEORY);
        writeTextTheoryBox = Box.createHorizontalBox();
        writeTextTheoryBox.add(writeTextTheory);
        writeTextTheoryBox.add(Box.createHorizontalGlue());

        poleTheory = new JTextArea(5, 600);
        poleTheory.setLineWrap(true);
        poleTheory.setWrapStyleWord(true);
        poleTheoryBox = Box.createHorizontalBox();
        poleTheoryBox.add(new JScrollPane(poleTheory));

        buttonBox = Box.createHorizontalBox();
        buttonAdd = new JButton(Constants.NAME_BUTTON_ADD_BILET);
        buttonAdd.addActionListener(this);
        buttonExit = new JButton(Constants.NAME_BUTTON_CANCEL);
        buttonExit.addActionListener(this);
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(buttonAdd);
        buttonBox.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_BUTTON));
        buttonBox.add(buttonExit);
        buttonBox.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));

        mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));
        mainBox.add(questionNumberBox);
        mainBox.add(writeTextQuestionBox);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));
        mainBox.add(poleQuestionBox);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));
        mainBox.add(writeVariantQuestion);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));
        mainBox.add(horizontalBoxVarAnswer);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));
        mainBox.add(theoryNumberBox);
        mainBox.add(writeTextTheoryBox);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));
        mainBox.add(poleTheoryBox);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_BUTTON));
        mainBox.add(buttonBox);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_BORDER));

        finalHorBox = Box.createHorizontalBox();
        finalHorBox.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        finalHorBox.add(mainBox);
        finalHorBox.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));

        mainFrame.add(finalHorBox);
        mainFrame.pack();
        mainFrame.setSize(700, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals(Constants.NAME_BUTTON_CANCEL)){
            System.exit(0);
        }

        if(actionCommand.equals(Constants.STRING_A)){
            vybor = var1.getText();
            if(vybor.equals("")){
                JOptionPane.showMessageDialog(null, Constants.TEXT_STUDY_NOT_WRITE_TEXT);
                group.clearSelection();
            }
        }

        if(actionCommand.equals(Constants.STRING_B)){
            vybor = var2.getText();
            if(vybor.equals("")){
                JOptionPane.showMessageDialog(null, Constants.TEXT_STUDY_NOT_WRITE_TEXT);
                group.clearSelection();
            }
        }

        if(actionCommand.equals(Constants.STRING_C)){
            vybor = var3.getText();
            if(vybor.equals("")){
                JOptionPane.showMessageDialog(null, Constants.TEXT_STUDY_NOT_WRITE_TEXT);
                group.clearSelection();
            }
        }

        if(actionCommand.equals(Constants.NAME_BUTTON_ADD_BILET)){
            Methods.addExam();

        }
    }
}
