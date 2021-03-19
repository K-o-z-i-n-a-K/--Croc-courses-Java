package com.company;
import java.util.Scanner;

public class Dz2 {
    public static  int NumDevice; //номер устройсва
    public static  int n2; // вспомогательная переменная
    public static Scanner sc = new Scanner(System.in);
    public static String[][][] groups = { // список песен в виде [устройство на котором песня][группа][название песни(0 элимент - группа)]
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

    public static String StartD() {
        System.out.println("Выберете с чего прослушать музыку: \n 1 - CD диск \n 2 - USB \n 3 - облако ");
        NumDevice = sc.nextInt();

        if (NumDevice < 1 || NumDevice > 3){ //если пользователь вышел за границы выбора
            System.out.print( "\u001B[31m" + "Нет такого варианта.  \n" + "\u001B[0m");
            StartD();  //повторный вызов вариантов
        }
        else{
            if (NumDevice==1){
                return "CD диск";
            }
            else if (NumDevice==2){
                return "USB";
            }
            else  {
                return "облако";
            }

        }
        return "";
    }

    public static String StartP() {
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
            StartP();
        }
        else{
            if (n2==1){
                return "компьютер";
            }
            else {
                if (NumDevice == 1){
                    return "CD плеер";
                }
                if (NumDevice == 2){
                    return "колонки";
                }
                if (NumDevice == 3){
                    return "телефон";
                }

            }

        }
        return "";

    }

    public static String StartM() {
        n2 = 0;
        System.out.println("Выберете номер исполнителя: ");
        for(int i = 0; i < groups[NumDevice-1].length; i++){ // [всегда выбранное устройство][перечисляем группы][всегда 0 для выбора группы ]
            System.out.println(i+1 + " " + groups[NumDevice-1][i][0]);
        }
        n2 = sc.nextInt();

        if (n2 < 1 || n2 > groups[NumDevice-1].length){ // проверка существования группы
            System.out.print( "\u001B[31m" + "Нет такого варианта.  \n" + "\u001B[0m");
            StartM();
        }
        return groups[NumDevice-1][n2-1][0];

    }

    public static String StartS() {
        System.out.print("Выберете номер песни: ");
        for(int i = 1; i < groups[NumDevice-1][n2-1].length; i++){ // идём от 1 потому что 0 это группы
            System.out.println(i + " " + groups[NumDevice-1][n2-1][i]);
        }
        int n3 = sc.nextInt();
        if (n3 < 1 || n3 > groups[NumDevice-1][n2-1].length-1){
            System.out.print( "\u001B[31m" + "Нет такого варианта.  \n" + "\u001B[0m");
            StartS();
        }

        return groups[NumDevice-1][n2-1][n3];

    }
    public static void main(String[] args) {

        Song result = new Song(StartD(), StartP(), StartM(), StartS());
        result.display();

    }
}
abstract  class Device{

    private final String device ;

    public Device(String device){

        this.device=device;
    }

     void display(){

        System.out.printf("Носитель: %s \n", device);
    }
}

abstract class Player extends Device{

    private final String player;

    public Player (String device, String player){
        super(device);
        this.player=player;
    }

    @Override
    public void display(){

        System.out.printf("Проигрыватель: %s \n",  player);
        super.display();
    }
}
abstract class  Musician extends Player {

    private final String musician ;

    public Musician(String device, String player, String musician) {
        super(device, player);
        this.musician=musician;
    }

    @Override
    public void display(){
        super.display();
        System.out.printf("Исполнитель: %s \n",  musician);

    }
}
class  Song extends Musician {

    private final String song ;

    public Song(String device, String player, String musician, String song) {
        super(device, player, musician);
        this.song=song;
    }

    @Override
    public void display(){
        super.display();
        System.out.printf("Песня: %s \n",  song);

    }
}
