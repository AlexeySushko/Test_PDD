package pdd_project.view_controller;

import pdd_project.Constants;
import pdd_project.model.Methods;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexey Sushko
 * для создания окна при выборе обучения или прохождении экзамена
 */
public class ProgramChoiseFrame extends JFrame implements ActionListener {

    //переменные
    private JFrame programChoiseFrame;

    private JButton studyButton, examButton, cancelButton;

    private Box horizontalLayoutStudExam, horizontalLayoutCancel, mainChoiseBox;

    public ProgramChoiseFrame() {

        programChoiseFrame = new JFrame(Constants.NAME_WINDOW_PROGRAMM);
        programChoiseFrame.setSize(Constants.SIZE_PROGRAM_WINDOW_WIDTH, Constants.SIZE_PROGRAM_WINDOW_HEIGHT);
        programChoiseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //--------------------------------------------
        //Создание выбора обучение экзамен

        horizontalLayoutStudExam = Box.createHorizontalBox();
        setLayout(new GridLayout(2, 1, 5, 5));

        studyButton = new JButton(Constants.NAME_BUTTON_STUDY, new ImageIcon(Constants.NAME_IMAGE_STUDY));
        studyButton.setVerticalTextPosition(SwingConstants.TOP);
        studyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        studyButton.addActionListener(this);

        examButton = new JButton(Constants.NAME_BUTTON_EXAM, new ImageIcon(Constants.NAME_IMAGE_EXAM));
        examButton.setVerticalTextPosition(SwingConstants.TOP);
        examButton.setHorizontalTextPosition(SwingConstants.CENTER);
        examButton.addActionListener(this);

        horizontalLayoutStudExam.add(Box.createHorizontalGlue());
        horizontalLayoutStudExam.add(studyButton);
        horizontalLayoutStudExam.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_BUTTON));
        horizontalLayoutStudExam.add(examButton);
        horizontalLayoutStudExam.add(Box.createHorizontalGlue());
        //----------------------------------------------------
        //Панелька с кнопкой отмена
        horizontalLayoutCancel = Box.createHorizontalBox();
        cancelButton = new JButton(Constants.NAME_BUTTON_CANCEL);
        cancelButton.addActionListener(this);

        horizontalLayoutCancel.add(Box.createHorizontalGlue());
        horizontalLayoutCancel.add(cancelButton);
        horizontalLayoutCancel.add(Box.createHorizontalStrut(Constants.SIZE_MEJDY_BUTTON));
        //------------------------------------------------------
        //В кучу все
        mainChoiseBox = Box.createVerticalBox();
        mainChoiseBox.setBorder(new EmptyBorder(Constants.SIZE_BORDER, Constants.SIZE_BORDER,
                Constants.SIZE_BORDER, Constants.SIZE_BORDER));
        mainChoiseBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        mainChoiseBox.add(horizontalLayoutStudExam);
        mainChoiseBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));
        mainChoiseBox.add(horizontalLayoutCancel);
        mainChoiseBox.add(Box.createVerticalStrut(Constants.SIZE_MEJDY_PANNEL));

        programChoiseFrame.setContentPane(mainChoiseBox);
        programChoiseFrame.pack();
        programChoiseFrame.setLocationRelativeTo(null);
        programChoiseFrame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals(Constants.NAME_BUTTON_STUDY)){
            Methods.addValuesTheory();
            new ProgramStudyFrame();
            programChoiseFrame.setVisible(false);
        }

        if(actionCommand.equals(Constants.NAME_BUTTON_EXAM)){
            programChoiseFrame.setVisible(false);
            Methods.random();
            Methods.addValuesExam();
            new ProgramExamFrame();
        }

        if(actionCommand.equals(Constants.NAME_BUTTON_CANCEL)){
            System.exit(0);
        }

    }
}
