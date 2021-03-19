package com.company;
import java.util.Scanner;

public class Dz2 {

    public static void main(String[] args) {

        Song result = new Song();
        result.display();

    }
}
abstract  class Device{

    private String device ;
    public static  int NumDevice; //номер устройсва
    public static  int n2; // вспомогательная переменная
    public static Scanner sc = new Scanner(System.in);

    public Device() {

    }

    public void setDevice(){

        System.out.println("Выберете с чего прослушать музыку: \n 1 - CD диск \n 2 - USB \n 3 - облако ");
        NumDevice = sc.nextInt();

        if (NumDevice < 1 || NumDevice > 3){ //если пользователь вышел за границы выбора
            System.out.print( "\u001B[31m" + "Нет такого варианта.  \n" + "\u001B[0m");
            setDevice();  //повторный вызов вариантов
        }
        else{
            if (NumDevice==1){
                this.device = "CD диск";
            }
            else if (NumDevice==2){
                this.device = "USB";
            }
            else  {
                this.device = "облако";
            }
        }

    }


     void display(){

        System.out.printf("Носитель: %s \n", this.device);
    }
}

abstract class Player extends Device{

    private  String player;

    public Player() {
        super();
    }

    public void setPlayer(){
         n2 = 0;
         System.out.println("Выберете проигрыватель:  \n 1 - компьютер "); //с компьютера можно прослушать любое устройство
         if (NumDevice == 1){
             System.out.print(" 2 - CD плеер "); // только сд-диски
         }
         if (NumDevice == 2){
             System.out.println(" 2 - колонки ");  // только USB
         }
         if (NumDevice == 3){
             System.out.println(" 2 - телефон ");  // только с облка
         }
         n2 = sc.nextInt();
         if (n2 < 1 || n2 > 2){
             System.out.print( "\u001B[31m" + "Нет такого варианта.  \n" + "\u001B[0m");
             setPlayer();
         }
         else{
             if (n2==1){
                 this.player = "компьютер";
             }
             else {
                 if (NumDevice == 1) {
                     this.player = "CD плеер";
                 }
                 if (NumDevice == 2) {
                     this.player = "колонки";
                 }
                 if (NumDevice == 3) {
                     this.player = "телефон";
                 }
             }
         }
     }

    @Override
    public void display(){

        System.out.println("Проигрыватель: " + this.player);
        super.display();
    }
}
abstract class  Musician extends Player {

    private  String musician ;
    String[][][] groups = { // список песен в виде [устройство на котором песня][группа][название песни(0 элимент - группа)]
            {
                    {"1_GroupCD", "1_Song_1_GroupCD", "2_Song_1_GroupCD", "3_Song_1_GroupCD", "4_Song_1_GroupCD"},
                    {"2_GroupCD", "1_Song_2_GroupCD", "2_Song_2_GroupCD", "3_Song_2_GroupCD"},
                    {"3_GroupCD", "1_Song_3_GroupCD", "2_Song_3_GroupCD", "3_Song_3_GroupCD"},
            },
            {
                    {"1_GroupUSB", "1_Song_1_GroupUSB", "2_Song_1_GroupUSB", "3_Song_1_GroupUSB", "4_Song_1_GroupUSB"},
                    {"2_GroupUSB", "1_Song_2_GroupUSB", "2_Song_2_GroupUSB", "3_Song_2_GroupUSB"},
                    {"3_GroupUSB", "1_Song_3_GroupUSB", "2_Song_3_GroupUSB", "3_Song_3_GroupUSB", "4_Song_3_GroupUSB"},
            },
            {
                    {"1_GroupCL", "1_Song_1_GroupCL", "2_Song_1_GroupCL", "3_Song_1_GroupCL", "4_Song_1_GroupCL"},
                    {"2_GroupCL", "1_Song_2_GroupCL", "2_Song_2_GroupCL", "3_Song_2_GroupCL"},
            }

    };



    public Musician() {
        super();
    }

    public void setMusician(){
        n2 = 0;
        System.out.println("Выберете номер исполнителя: ");
        for(int i = 0; i < groups[NumDevice-1].length; i++){ // [всегда выбранное устройство][перечисляем группы][всегда 0 для выбора группы ]
            System.out.println(i+1 + " " + groups[NumDevice-1][i][0]);
        }
        n2 = sc.nextInt();

        if (n2 < 1 || n2 > groups[NumDevice-1].length){ // проверка существования группы
            System.out.print( "\u001B[31m" + "Нет такого варианта.  \n" + "\u001B[0m");
            setMusician();
        }
        this.musician = groups[NumDevice-1][n2-1][0];
    }

    @Override
    public void display(){
        super.display();
        System.out.printf("Исполнитель: %s \n",  this.musician);

    }
}
class  Song extends Musician {

    private  String song ;
    int n3;
    public void setSong(){
         n3 = 0;
        System.out.println("Выберете номер песни: ");
        for(int i = 1; i < groups[NumDevice-1][n2-1].length; i++){ // идём от 1 потому что 0 это группы
            System.out.println(i + " " + groups[NumDevice-1][n2-1][i]);
        }
        n3 = sc.nextInt();
        if (n3 < 1 || n3 > groups[NumDevice-1][n2-1].length-1){
            System.out.print( "\u001B[31m" + "Нет такого варианта.  \n" + "\u001B[0m");
            setSong();
        }

        this.song = groups[NumDevice-1][n2-1][n3];
    }

    public Song(){
        setDevice();
        setPlayer();
        setMusician();
        setSong();

    }

    @Override
    public void display(){
        super.display();
        System.out.printf("Песня: %s \n",  this.song);

    }
}
