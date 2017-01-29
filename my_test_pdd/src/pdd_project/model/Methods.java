package pdd_project.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pdd_project.Constants;
import pdd_project.model.entity.Question;
import pdd_project.model.entity.Theory;
import pdd_project.model.entity.User;
import pdd_project.view_controller.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static pdd_project.view_controller.ProgramExamFrame.*;


/**
 * Created by Alexey Sushko
 * Класс где находятся все методы
 */


public class Methods {

    public static User onlineUser;  //Сюда записывается ПОЛЬЗОВАТЕЛЬ который авторизировался
    public static JSONObject allInfoOutExam = new JSONObject(); //Сюда вытянем первоначальное значение из файла с которого
    //добавим в значения ниже теорию и вопросы
    public static List<Theory> runListTheory = new ArrayList<>();   //List где будет только теория взятая из allInfoOutExamм
    public static List<Question> runListQuestion = new ArrayList<>();   //Аналогично теории тут будет только Вопросы из allInfoOutExamм
    public static JSONObject allInfoForWriteInFile = new JSONObject();  //Обьект записи в файл
    public static String rezultExam = "";   //Информация которая появится в окне по окончанию экзамена, где будут показазны результаты
    public static  int rezultAnswer;    //количество правильных ответов за экзамен
    public static String resultInFile = ""; //строка которая запишется в файл с именем пользователя для отчета
    public  static int percentInFile;   // проценты знаний по тесту
    public static ArrayList<Integer> clickArray = new ArrayList<>();    // Сюда будут генерироваться номера(десяти) вопросов на экзамен


    /**
     * Этот метод будет использоваться только для точго что бы создать первоначальную структуру файла если
     * нет пользователей и появилась ошибка
     */
     private static void  firstUser(){

        JSONObject jsonExc = new JSONObject();
        List <Object> firstUser = new ArrayList<>();
        JSONObject valUser = new JSONObject();
         //добавляем по ключу значения в valUser
        valUser.put(Constants.TEXT_ID, 777);//777- общее для всех
        valUser.put(Constants.TEXT_NAME, NewUserFrame.getTextNewUserFieldText());
        valUser.put(Constants.TEXT_PASSWORD, NewUserFrame.getPasswordNewUserField());// по ключу пароль вставляем с помощью
         //уже переопределенного метода getPasswordNewUserField() шифрованое значение пароля с окна нового пользователя
        valUser.put(Constants.TEXT_ONLINE, false);
        valUser.put(Constants.TEXT_PERCENT, -1);
        firstUser.add(0, valUser);

        jsonExc.put(Constants.SEARCH_TEXT_USER, firstUser);

        try {
            FileWriter file = new FileWriter(Constants.NAME_FILE_DATA);
            file.write(jsonExc.toJSONString());
            file.flush();
            file.close();

            onlineUser = new User(777, NewUserFrame.getTextNewUserFieldText(), NewUserFrame.getPasswordNewUserField(), true, -1);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Метод который проверит есть ли уже пользователь с таким же логином в файле
     * Если файла вообще нет то создаст файл с этим пользователем
     * @throws IOException
     */
    public static boolean searchLoginInFile() throws IOException {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(Constants.NAME_FILE_DATA));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray valUser = (JSONArray) jsonObject.get(Constants.SEARCH_TEXT_USER);
            Iterator<Object> iterator = valUser.iterator();
            while(iterator.hasNext()){
                JSONObject jsonObject1 = (JSONObject) iterator.next();
                if(jsonObject1.get(Constants.TEXT_NAME).equals(NewUserFrame.getTextNewUserFieldText())){
                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            firstUser();
            NewUserFrame.newUserFrame.setVisible(false);
            createLists();
            new ProgramChoiseFrame();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }


    /**
     * Метод добавляющий нового пользователя в файл, для этого мы читаем файл полностью и в массив пользователей
     * добавляем нового пользовтеля
     * @throws IOException
     */
    public static void addNewUser() throws IOException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(Constants.NAME_FILE_DATA));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray valUser = (JSONArray) jsonObject.get(Constants.SEARCH_TEXT_USER);

            JSONObject user = new JSONObject();
            user.put(Constants.TEXT_ID, 777);
            user.put(Constants.TEXT_NAME, NewUserFrame.getTextNewUserFieldText());
            user.put(Constants.TEXT_PASSWORD, NewUserFrame.getPasswordNewUserField());
            user.put(Constants.TEXT_ONLINE, false);
            user.put(Constants.TEXT_PERCENT, -1);

            onlineUser = new User(777, NewUserFrame.getTextNewUserFieldText(),NewUserFrame.getPasswordNewUserField(),true, -1);
            valUser.add(user);
            jsonObject.put(Constants.SEARCH_TEXT_USER, valUser);

            Methods.createLists();

            FileWriter file = new FileWriter(Constants.NAME_FILE_DATA);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        new ProgramChoiseFrame();
    }


    /**
     * Метод который читает файл с пользователями и сравнивает значения логина и пароля
     */
    public static void jsonReadData() {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(Constants.NAME_FILE_DATA));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray valUser = (JSONArray) jsonObject.get(Constants.SEARCH_TEXT_USER);

            Iterator<Object> iterator = valUser.iterator();
            while(iterator.hasNext()){
               JSONObject jsonObject1 = (JSONObject) iterator.next();

                if(jsonObject1.get(Constants.TEXT_NAME).equals(LoginFrame.getLoginTextField()) && jsonObject1.get(Constants.TEXT_PASSWORD).equals(LoginFrame.getPasswordFieldPassword())){
                    onlineUser = new User(345, String.valueOf(jsonObject1.get("name")), LoginFrame.getPasswordFieldPassword(), true, -1);
                    JOptionPane.showMessageDialog(null, Constants.TEXT_AUTORIZATION_GOOD);
                    LoginFrame.loginFrame.setVisible(false);
                    new ProgramChoiseFrame();
                    Methods.createLists();
                }
            }

            if (onlineUser==null){
                JOptionPane.showMessageDialog(null, Constants.TEXT_AUTORIZATION_ERROR);
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, Constants.TEXT_NULL_USERS);
            LoginFrame.loginFrame.setVisible(false);
            new NewUserFrame();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    /**
     * Метод читает файл и присваивет его содержимое в allInfoOutExam
     * далее вытягивает из allInfoOutExam все вопросы и теорию в коллекции runListTheory, runListQuestion
     */
    public static void createLists() {
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(Constants.NAME_FILE_EXAM));
            allInfoOutExam = (JSONObject) obj;

            JSONArray valExam = (JSONArray) allInfoOutExam.get(Constants.SEARCH_TEXT_EXAM);
            Iterator<Object> iterator = valExam.iterator();
            for(int i =1; i<=valExam.size(); i++){
                JSONObject jsonObject1 = (JSONObject) iterator.next();

                if(jsonObject1.get(Constants.TEXT_THEORY).equals("Теория "+ i)){

                    runListTheory.add(new Theory(String.valueOf(jsonObject1.get(Constants.TEXT_TEXT_THEORY)),
                            String.valueOf(jsonObject1.get(Constants.TEXT_THEORY))));

                    JSONObject valueVariant = (JSONObject) jsonObject1.get(Constants.TEXT_VAR_ANSWER);
                    List<String> allVariant = new ArrayList<>();
                    String var1 = String.valueOf(valueVariant.get("1"));
                    String var2 = String.valueOf(valueVariant.get("2"));
                    String var3 = String.valueOf(valueVariant.get("3"));
                    allVariant.add(0, var1);
                    allVariant.add(1, var2);
                    allVariant.add(2, var3);

                    runListQuestion.add(new Question( allVariant,
                            String.valueOf(jsonObject1.get(Constants.TEXT_GOOD_ANSWER)),
                            String.valueOf(jsonObject1.get(Constants.TEXT_TEXT_QUESTION)),
                            String.valueOf(jsonObject1.get(Constants.TEXT_QUESTION))));
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, Constants.TEXT_NOT_HAVE_BILLET);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *Метод получающий значения Теории.
     * В методе меняем значения откуда будут браться для окон, в кнопке где этот метод вызывается с этих значений сэтятся новые
     */
    public static void addValuesTheory() {
        Theory theory = runListTheory.get(ProgramStudyFrame.CLIK);
        ProgramStudyFrame.strLabel = theory.getTitle();
        ProgramStudyFrame.strTextTheory = theory.getTextTheory();
    }

    /**
     * Метод создания 10ти рандомных чисел что бы брать вопросы для экзамена, рандомные числа берутся в промежутке от
     * нуля до значения размера коллекции которая хранит все вопросы и теорию
     */
    public static void random(){
        for(int i = 0; i<10; i++){
            Random random = new Random();
            int iNumber = random.nextInt(runListQuestion.size());
            if(!clickArray.contains(iNumber)){
                clickArray.add(i, iNumber);
            }
            else{
                i--;
            }
        }
    }


    /**
     * Данным методом получаем значения Вопросов, которые присваиваются в переменным откуда будут сетится вызовом
     * метода updateValues в поле окна экзамена
     */
    public static void addValuesExam() {
        Question question = runListQuestion.get(clickArray.get(ProgramStudyFrame.CLIK));
        stringNumberQuestion = "Вопрос "+ (ProgramStudyFrame.CLIK+1);
        stringTextQuesst = question.getTitle();
        ProgramExamFrame.goodAnswer = question.getRightAnswers();
        List<String> varAnswer = question.getAnswers();
        varA = varAnswer.get(0);
        ProgramExamFrame.varB = varAnswer.get(1);
        ProgramExamFrame.varC = varAnswer.get(2);
    }


    /**
     * Метод который сетит новые значения в поля окна экзамена
     */
    public static void updateValues(){
        textnumberQuestion.setText(String.valueOf(stringNumberQuestion));
        textQuestions.setText(String.valueOf(stringTextQuesst));
        buttonJRadio_A.setText(String.valueOf(varA));
        buttonJRadio_B.setText(String.valueOf(varB));
        buttonJRadio_C.setText(String.valueOf(varC));
        textOneIzTen.setText(textnumberQuestion.getText() + Constants.TEXT_MAX_THEORY_IZ__);
    }


    /**
     * Метод сравнения выбора и правильного ответа. Сразу тут же записывает в строку правильные ответы которые
     * в конце в Диалоговом окне выкинет, а вторую строку в файл запишет с помошью отдельного метода
     */

    public static void sravnenie(){
        try{
            if(!ProgramExamFrame.vybor.equals("")){
                if(ProgramExamFrame.vybor.equals(ProgramExamFrame.goodAnswer)){
                    rezultExam += stringNumberQuestion + " -->> " + "Правильно" +"\n";
                    rezultAnswer += 1;

                    resultInFile += stringNumberQuestion + "\t"+ stringTextQuesst +"\n";
                    resultInFile += "Варианты ответов " +"\n"+
                            varA + "\n"+
                            ProgramExamFrame.varB + "\n"+
                            ProgramExamFrame.varC+ "\n";
                    resultInFile += "Вы выбрали " + ProgramExamFrame.vybor + "\n";
                    resultInFile += "ПРАВИЛЬНЫЙ ОТВЕТ\n\n";
                }
                if(!ProgramExamFrame.vybor.equals(ProgramExamFrame.goodAnswer)){
                    rezultExam += stringNumberQuestion + " -->> " + "Не правильно"+"\n";

                    resultInFile += stringNumberQuestion + "\t"+ stringTextQuesst +"\n";
                    resultInFile += "Варианты ответов " +"\n"+
                            varA + "\n"+
                            ProgramExamFrame.varB + "\n"+
                            ProgramExamFrame.varC + "\n";
                    resultInFile += "Вы выбрали " + ProgramExamFrame.vybor + "\n";
                    resultInFile += "НЕ ПРАВИЛЬНЫЙ ОТВЕТ\n";
                    resultInFile += "Правильным ответом явлется--> " + ProgramExamFrame.goodAnswer+"\n\n";
                }
            }
            if(ProgramExamFrame.vybor.equals("")){
                ProgramStudyFrame.CLIK-=1;
                JOptionPane.showMessageDialog(null, "Вы не сделали выбор\n\nСделайте выбор!!!");
            }
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Вы не сделали выбор\n\nСделайте выбор!!!");
            ProgramStudyFrame.CLIK=-1;
        }
    }


    /**
     * Метод просто выводящий результат на экран, что б в кнопке меньше текста было
     */
    public static void viewRezultExam(){
        percentInFile = rezultAnswer * 10;
        JOptionPane.showMessageDialog(null, "Пользователь " + onlineUser.getName() + "\n" +
                "Экзаменирование закончено \n\n"+"*****РЕЗУЛЬТАТЫ ЭКЗАМЕНА*****\n\n" +"Правильных ответов "+
                rezultAnswer+ " из 10\n\n" + rezultExam + "\n");

    }


    /**
     * Метод заходящий в файл и перезаписывающий значение percent y Юсера
     */
    public static void writeResult(){
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(Constants.NAME_FILE_DATA));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray valUser = (JSONArray) jsonObject.get(Constants.SEARCH_TEXT_USER);
            Iterator<Object> iterator = valUser.iterator();

            while (iterator.hasNext()){

                JSONObject jsonObject1 = (JSONObject) iterator.next();
                if(jsonObject1.get(Constants.TEXT_NAME).equals(onlineUser.getName())){
                    jsonObject1.remove(Constants.TEXT_PERCENT);
                    jsonObject1.put(Constants.TEXT_PERCENT, percentInFile);
                }
            }try{
                FileWriter file = new FileWriter(Constants.NAME_FILE_DATA);
                file.write(String.valueOf(obj));
                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /**
     * Метод для записи результата экзамена с полным текстом в файл с именем пользователя
     */
    public static void writeRezultUser(){

        try {
            FileWriter userFile = new FileWriter("Result_" + onlineUser.getName() +".txt");
            userFile.write(resultInFile);
            userFile.flush();
            userFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *     Метод для добавления билетов в программу если зайли как администратор
     */
    public static void addExam() {
        int number;//это будет номер вопроса и теории при добавлении, возьмет размер массива с вопросами+1
        if(AdminFrame.poleQuestion.getText().equals("") | AdminFrame.poleTheory.getText().equals("") |
                AdminFrame.var1.getText().equals("") | AdminFrame.var2.getText().equals("") | AdminFrame.var3.getText().equals("") |
                AdminFrame.group.getSelection()==(null)){
            JOptionPane.showMessageDialog(null, Constants.TEXT_ERROR_ADD);
        }
        if((!AdminFrame.poleQuestion.getText().equals("") & !AdminFrame.poleTheory.getText().equals("") &
                !AdminFrame.var1.getText().equals("") & !AdminFrame.var2.getText().equals("") & !AdminFrame.var3.getText().equals("") &
                AdminFrame.group.getSelection()!=(null))){

           JSONArray valExam = (JSONArray) allInfoOutExam.get(Constants.SEARCH_TEXT_EXAM);

            if(valExam == null){
                valExam = new JSONArray();
            }
            if(valExam != null) {
                number = valExam.size()+1;

                //Нижи добавляли <html> что бы потом в окне текст не уходил за границы а переносился автоматически
                JSONObject dannye = new JSONObject();
                dannye.put(Constants.TEXT_THEORY, AdminFrame.textTheoryNumber + number);
                dannye.put(Constants.TEXT_TEXT_THEORY, "<html>" + AdminFrame.poleTheory.getText());
                dannye.put(Constants.TEXT_QUESTION, AdminFrame.textQuestionNumber + number);
                dannye.put(Constants.TEXT_TEXT_QUESTION, "<html>" + AdminFrame.poleQuestion.getText());

                JSONObject arrayAnswer = new JSONObject();
                arrayAnswer.put(1, "<html>" + AdminFrame.var1.getText());
                arrayAnswer.put(2, "<html>" + AdminFrame.var2.getText());
                arrayAnswer.put(3, "<html>" + AdminFrame.var3.getText());
                dannye.put(Constants.TEXT_VAR_ANSWER, arrayAnswer);
                dannye.put(Constants.TEXT_GOOD_ANSWER, "<html>" + AdminFrame.vybor);

                valExam.add(dannye);

                allInfoForWriteInFile.put(Constants.SEARCH_TEXT_EXAM, valExam);

                try {
                    FileWriter newQuestion = new FileWriter(Constants.NAME_FILE_EXAM);
                    newQuestion.write(String.valueOf(allInfoForWriteInFile));
                    newQuestion.flush();
                    newQuestion.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, Constants.TEXT_BILET_ADD_GOOD);
                //обнуляем поля для корректгой записи информауии что бы не дублировалась и не "конкатерировались" значения
                AdminFrame.poleTheory.setText("");
                AdminFrame.var1.setText("");
                AdminFrame.var2.setText("");
                AdminFrame.var3.setText("");
                AdminFrame.poleQuestion.setText("");
                AdminFrame.group.clearSelection();
                Methods.createLists();

            }
        }

    }
}
