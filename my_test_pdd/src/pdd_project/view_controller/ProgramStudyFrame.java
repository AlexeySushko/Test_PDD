package pdd_project.view_controller;

import pdd_project.Constants;
import pdd_project.model.Methods;
import pdd_project.model.entity.Theory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Created by Alexey Sushko
 * Создание окна для прохождения обучения
*/
public class ProgramStudyFrame extends JFrame implements ActionListener {

    private JFrame programmStudyFrame;

    private Box mainBox, boxTheoryAndText, horizontalLayoutRazdel, horizontalLayoutTheory, horizontalLayoutText,
                horizontalLayoutParsent, horizontalButtonBox;

    public static JLabel razdelLabel, pynkt1iz10Label, theoryLabel, textTheoryLabel;

    public static String strLabel, strTextTheory;
    public static int CLIK = 0;


    private JButton backButton, nextButton, cancelButton, examButton;


    public ProgramStudyFrame() {


        programmStudyFrame = new JFrame(Constants.NAME_WINDOW_PROGRAMM);
        programmStudyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //-------------------------------------------------
        //Настраиваем первую панель Раздел ообучения
        horizontalLayoutRazdel = Box.createHorizontalBox();
        razdelLabel = new JLabel(Constants.PART_STUDY);//Название раздела
        razdelLabel.setHorizontalAlignment(SwingConstants.CENTER);// Установка текста по центру
        horizontalLayoutRazdel.add(razdelLabel);

        //-------------------------------------------------
        //Создаем вторую горизонт панель из двух горизонтальных
        //Первая горизонтаьная
        horizontalLayoutTheory = Box.createHorizontalBox();
        theoryLabel = new JLabel(strLabel);
        theoryLabel.setSize(30, 70);
        theoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
        theoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);//???
        horizontalLayoutTheory.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalLayoutTheory.add(theoryLabel);
        horizontalLayoutTheory.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        //Вторая горизонтальная
        horizontalLayoutText = Box.createHorizontalBox();
        textTheoryLabel = new JLabel(strTextTheory);
        JScrollPane jScrollPane = new JScrollPane(textTheoryLabel);
        horizontalLayoutText.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalLayoutText.add(jScrollPane);

        boxTheoryAndText = Box.createHorizontalBox();
        boxTheoryAndText.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        boxTheoryAndText.add(horizontalLayoutTheory);
        boxTheoryAndText.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_BUTTON));
        boxTheoryAndText.add(horizontalLayoutText);
        boxTheoryAndText.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        //-------------------------------------------------
        //Создаем третюю с прочитанными пунктами
        horizontalLayoutParsent = Box.createHorizontalBox();
        pynkt1iz10Label = new JLabel("Прочитано " + (CLIK + 1)+ " из "+ Methods.runListTheory.size());
        pynkt1iz10Label.setHorizontalAlignment(SwingConstants.LEFT);
        horizontalLayoutParsent.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_PANNEL));
        horizontalLayoutParsent.add(pynkt1iz10Label);
        horizontalLayoutParsent.add(Box.createHorizontalGlue());
        //Панель с кнопками
        horizontalButtonBox = Box.createHorizontalBox();

        backButton = new JButton(Constants.NAME_BUTTON_BACK);
        backButton.addActionListener(this);
        backButton.setVisible(true);
        backButton.setEnabled(false);

        nextButton = new JButton(Constants.NAME_BUTTON_NEXT);
        nextButton.addActionListener(this);

        cancelButton = new JButton(Constants.NAME_BUTTON_CANCEL);
        cancelButton.addActionListener(this);

        examButton = new JButton(Constants.NAME_BUTTON_EXAM_STUDY);
        examButton.addActionListener(this);

        horizontalButtonBox.add(Box.createHorizontalGlue());
        horizontalButtonBox.add(backButton);
        horizontalButtonBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_BUTTON));
        horizontalButtonBox.add(nextButton);
        horizontalButtonBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_BUTTON));
        horizontalButtonBox.add(cancelButton);
        horizontalButtonBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_BUTTON));
        horizontalButtonBox.add(examButton);
        horizontalButtonBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_BUTTON));

        //Соединяем все в кучу и образуется заполненный фрейм
        mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        mainBox.add(horizontalLayoutRazdel);
        mainBox.add(Box.createVerticalStrut(21));//Что бы трижды не дублировать
        mainBox.add(boxTheoryAndText);
        mainBox.add(Box.createVerticalGlue());
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        mainBox.add(horizontalLayoutParsent);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        mainBox.add(horizontalButtonBox);
        mainBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));

        programmStudyFrame.setContentPane(mainBox);
        programmStudyFrame.pack();
        programmStudyFrame.setSize(Constants.SIZE_PROGRAM_WINDOW_WIDTH, Constants.SIZE_PROGRAM_WINDOW_HEIGHT);
        programmStudyFrame.setLocationRelativeTo(null);
        programmStudyFrame.setVisible(true);
        programmStudyFrame.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals(Constants.NAME_BUTTON_BACK)){
            CLIK-=1;
            Methods.addValuesTheory();
            theoryLabel.setText(String.valueOf(strLabel));
            textTheoryLabel.setText(String.valueOf(strTextTheory));
            pynkt1iz10Label.setText("Прочитано " + (CLIK + 1)+ " из "+Methods.runListTheory.size());

            if (CLIK == 0){
                backButton.setEnabled(false);
            }
        }

        if(actionCommand.equals(Constants.NAME_BUTTON_NEXT)){
            CLIK += 1;
            if(CLIK < Methods.runListTheory.size()) {
                Methods.addValuesTheory();
                theoryLabel.setText(String.valueOf(strLabel));
                textTheoryLabel.setText(String.valueOf(strTextTheory));
                pynkt1iz10Label.setText(Constants.TEXT_READED + (CLIK + 1) + " из " + Methods.runListTheory.size());
                backButton.setEnabled(true);
            }
            if (CLIK == Methods.runListTheory.size()){
                programmStudyFrame.setVisible(false);
                JOptionPane.showMessageDialog(null, Constants.TEXT_STUDY_FINISHED);
                CLIK = 0;
                Methods.random();
                Methods.addValuesExam();
                new ProgramExamFrame();
            }
        }

        if(actionCommand.equals(Constants.NAME_BUTTON_CANCEL)){
            System.exit(0);
        }

        if(actionCommand.equals(Constants.NAME_BUTTON_EXAM_STUDY)){
            programmStudyFrame.setVisible(false);
            CLIK = 0;
            Methods.random();
            Methods.addValuesExam();
            new ProgramExamFrame();
        }
    }
}