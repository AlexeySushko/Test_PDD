package pdd_project.view_controller;

import pdd_project.Constants;
import pdd_project.model.Methods;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Created by Alexey Sushko
 * Создание окна для экзаменирования
*/
public class ProgramExamFrame extends JFrame implements ActionListener {

    private JFrame programmExamFrame;

    private Box verticalMainBox, horizontalExam, horizontalNumberQuestionANDTextQuestions, horizontalNumberQuestions,
            horizontalQuestions, horizontalSelectionANDVariantAnswer, horizontalWriteANDWindowWriteAnswer,
            horizontalSelection, horizontalWriteAnswer, verticalBOX_JCheckSelection, horizontalBOX_JTextWriteAnswer,
            horizontalA, horizontalB, horizontalC, horizontalOneizTen, horizontalButtonBox;

    private JLabel textExam, textSelection, textWriteAnswer, textA, textB, textC;

    public static JLabel textnumberQuestion, textQuestions, textOneIzTen;
    public static String stringTextQuesst, stringNumberQuestion;
    public static String varA, varB, varC;
    public static String vybor, goodAnswer;

    public static JRadioButton buttonJRadio_A, buttonJRadio_B, buttonJRadio_C;

    private ButtonGroup group;

    private JTextField pannelForEnterAnswer;

    private JButton nextButton, finishedButton, cancelButton;

    public ProgramExamFrame(){

        ProgramStudyFrame.CLIK = 0;
        programmExamFrame = new JFrame();
        //--------------------------------------------------------------------
        //Первая строка. BOX с полосой текста Экзаменирование
        horizontalExam = Box.createHorizontalBox();
        textExam = new JLabel(Constants.PART_EXAM);
        textExam.setHorizontalAlignment(SwingConstants.CENTER);
        horizontalExam.add(textExam);
        //---------------------------------------------------------------------
        //Вторая строка. BOX с двумя панелями
        //Создаем левую часть ТекстВопрос № ...;
        horizontalNumberQuestions = Box.createHorizontalBox();
        textnumberQuestion = new JLabel(stringNumberQuestion);
        horizontalNumberQuestions.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalNumberQuestions.add(textnumberQuestion);
        horizontalNumberQuestions.add(Box.createHorizontalGlue());
        //Создаем правую часть
        horizontalQuestions = Box.createHorizontalBox();
        textQuestions = new JLabel(stringTextQuesst);
        horizontalQuestions.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalQuestions.add(textQuestions);
        horizontalQuestions.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        //Соединяем две панельки во вторую строку ВопросТекстВопроса
        horizontalNumberQuestionANDTextQuestions = Box.createHorizontalBox();
        horizontalNumberQuestionANDTextQuestions.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalNumberQuestionANDTextQuestions.add(horizontalNumberQuestions);
        horizontalNumberQuestionANDTextQuestions.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalNumberQuestionANDTextQuestions.add(horizontalQuestions);
        horizontalNumberQuestionANDTextQuestions.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        //------------------------------------------------------------------------
        //Третяя строка. BOX с Текстом ВЫБОРА или ВВОДА и ВАРИАНТАМИ ПРАВИЛЬНЫЗ ОТВЕТОВ или ПОЛЕ ДЛЯ ВВОДА
        //Левая панелька с текстом ВЫБЕРИТЕ ПРАВИЛЬНЫЙ ОТВЕТ
        horizontalSelection = Box.createHorizontalBox();
        textSelection = new JLabel(Constants.TEXT_CHOISE_VARIANT);
        horizontalSelection.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalSelection.add(textSelection);
        horizontalSelection.add(Box.createHorizontalGlue());
        //Левая пенелька с текстом ВВЕДИТЕ ПРАВИЛЬНЫЙ ОТВЕТ
        horizontalWriteAnswer = Box.createHorizontalBox();
        textWriteAnswer = new JLabel();
        horizontalWriteAnswer.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalWriteAnswer.add(textWriteAnswer);
        horizontalWriteAnswer.add(Box.createHorizontalGlue());
        //Правая часть в ВЫБОРОМ или ПОЛЕМ ДЛЯ ВВОДА ОТВЕТА
        //ПАНЕЛЬ С ВЫБОРОМ
        // A
        horizontalA = Box.createHorizontalBox();
        textA = new JLabel("A:");
        buttonJRadio_A = new JRadioButton(varA);
        buttonJRadio_A.addActionListener(this);
        buttonJRadio_A.setActionCommand(Constants.STRING_A);

        horizontalA.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalA.add(textA);
        horizontalA.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalA.add(buttonJRadio_A);
        horizontalA.add(Box.createHorizontalGlue());
        // B
        horizontalB = Box.createHorizontalBox();
        textB = new JLabel("B:");
        buttonJRadio_B = new JRadioButton(varB);
        buttonJRadio_B.addActionListener(this);
        buttonJRadio_B.setActionCommand(Constants.STRING_B);

        horizontalB.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalB.add(textB);
        horizontalB.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalB.add(buttonJRadio_B);
        horizontalB.add(Box.createHorizontalGlue());
        // C
        horizontalC = Box.createHorizontalBox();
        textC = new JLabel("C:");
        buttonJRadio_C = new JRadioButton(varC);
        buttonJRadio_C.addActionListener(this);
        buttonJRadio_C.setActionCommand(Constants.STRING_C);

        horizontalC.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalC.add(textC);
        horizontalC.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalC.add(buttonJRadio_C);
        horizontalC.add(Box.createHorizontalGlue());

        group = new ButtonGroup();
        group.add(buttonJRadio_A);
        group.add(buttonJRadio_B);
        group.add(buttonJRadio_C);

        //Создаем панельку с ТОЧКАМИ ВЫБОРА которые создали выше
        verticalBOX_JCheckSelection = Box.createVerticalBox();
        verticalBOX_JCheckSelection.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalBOX_JCheckSelection.add(horizontalA);
        verticalBOX_JCheckSelection.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalBOX_JCheckSelection.add(horizontalB);
        verticalBOX_JCheckSelection.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalBOX_JCheckSelection.add(horizontalC);
        verticalBOX_JCheckSelection.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        //Создаем панель с ПОЛЕМ ДЛЯ ВВОДА ПРАВИЛЬНОГО ОТВЕТА
        horizontalBOX_JTextWriteAnswer = Box.createHorizontalBox();
        pannelForEnterAnswer = new JTextField(Constants.SIZE_LONG_TEXT_WINDOW);
        horizontalBOX_JTextWriteAnswer.add(Box.createHorizontalGlue());
        horizontalBOX_JTextWriteAnswer.add(pannelForEnterAnswer);
        horizontalBOX_JTextWriteAnswer.add(Box.createHorizontalGlue());
        //Создаем третью строку двух вариантов
        // Вариант с текстом ВЫБОРА и Окном с КРУЖКАМИ ВЫБОРА
        horizontalSelectionANDVariantAnswer = Box.createHorizontalBox();
        horizontalSelectionANDVariantAnswer.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalSelectionANDVariantAnswer.add(horizontalSelection);
        horizontalSelectionANDVariantAnswer.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalSelectionANDVariantAnswer.add(verticalBOX_JCheckSelection);
        horizontalSelectionANDVariantAnswer.add(Box.createHorizontalGlue());
        //Вариант с текстом ВВЕДИТЕ ОТВЕТ и ПОЛЕМ ДЛЯ ОТВЕТА
        horizontalWriteANDWindowWriteAnswer = Box.createHorizontalBox();
        horizontalWriteANDWindowWriteAnswer.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalWriteANDWindowWriteAnswer.add(horizontalWriteAnswer);
        horizontalWriteANDWindowWriteAnswer.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalWriteANDWindowWriteAnswer.add(horizontalBOX_JTextWriteAnswer);
        horizontalWriteANDWindowWriteAnswer.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalWriteANDWindowWriteAnswer.setVisible(false);
        //--------------------------------------------------------------
        //Создаем четвертую панельку с текстом ПРОЙДЕНО 1 ИЗ 10
        horizontalOneizTen = Box.createHorizontalBox();
        textOneIzTen = new JLabel("Вопрос " + (ProgramStudyFrame.CLIK+1) + " из 10");
        horizontalOneizTen.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalOneizTen.add(textOneIzTen);
        horizontalOneizTen.add(Box.createHorizontalGlue());
        //---------------------------------------------------------------
        //Создадим Пятую панельку с КНОПКАМИ
        horizontalButtonBox = Box.createHorizontalBox();
        nextButton = new JButton(Constants.NAME_BUTTON_NEXT);
        nextButton.addActionListener(this);

        finishedButton = new JButton(Constants.NAME_BUTTON_FINISHED);
        finishedButton.addActionListener(this);

        cancelButton = new JButton(Constants.NAME_BUTTON_CANCEL);
        cancelButton.addActionListener(this);

        horizontalButtonBox.add(Box.createHorizontalGlue());
        horizontalButtonBox.add(nextButton);
        horizontalButtonBox.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_BUTTON));
        horizontalButtonBox.add(finishedButton);
        horizontalButtonBox.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_BUTTON));
        horizontalButtonBox.add(cancelButton);
        horizontalButtonBox.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_BUTTON));
        //-----------------------------------------------------------------------
        //Собираем все на главной панеле
        verticalMainBox = Box.createVerticalBox();
        verticalMainBox.setBorder(new EmptyBorder(Constants.SIZE_BORDER,
                Constants.SIZE_BORDER,
                Constants.SIZE_BORDER,
                Constants.SIZE_BORDER));
        verticalMainBox.add(horizontalExam);
        verticalMainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalMainBox.add(horizontalNumberQuestionANDTextQuestions);
        verticalMainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalMainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalMainBox.add(horizontalSelectionANDVariantAnswer);
        verticalMainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalMainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalMainBox.add(horizontalWriteANDWindowWriteAnswer);
        verticalMainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalMainBox.add(horizontalOneizTen);
        verticalMainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        verticalMainBox.add(horizontalButtonBox);
        //--------------------------------------------------------
        programmExamFrame.setContentPane(verticalMainBox);
        programmExamFrame.pack();
        programmExamFrame.setSize(Constants.SIZE_PROGRAM_WINDOW_WIDTH, Constants.SIZE_PROGRAM_WINDOW_HEIGHT);
        programmExamFrame.setLocationRelativeTo(null);
        programmExamFrame.setVisible(true);
        programmExamFrame.setResizable(false);
        programmExamFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals(Constants.NAME_BUTTON_NEXT)){
            Methods.sravnenie();
            group.clearSelection();
            vybor = "";
            ProgramStudyFrame.CLIK+=1;

            if (ProgramStudyFrame.CLIK == 10){

                nextButton.setEnabled(false);
                Methods.viewRezultExam();
                Methods.writeResult();
                Methods.writeRezultUser();
                System.exit(0);
            }
            Methods.addValuesExam();
            Methods.updateValues();
        }
        if(actionCommand.equals(Constants.NAME_BUTTON_FINISHED)){
            Methods.viewRezultExam();
            Methods.writeResult();
            Methods.writeRezultUser();
            System.exit(0);

        }

        if(actionCommand.equals(Constants.NAME_BUTTON_CANCEL)){
            System.exit(0);
        }


        if(actionCommand.equals(Constants.STRING_A)){
            vybor = buttonJRadio_A.getText();
        }

        if(actionCommand.equals(Constants.STRING_B)){
            vybor = buttonJRadio_B.getText();
        }

        if(actionCommand.equals(Constants.STRING_C)){
            vybor= buttonJRadio_C.getText();
        }
    }

}