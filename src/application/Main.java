package application;

import model.entities.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
         * EXCEÇÕES
         *
         * - uma exceção é qualquer condição de erro ou comportamento inesperado
         * encontrado por um programa em execução
         *
         * Em java, uma exceção é um objeto herdado da classe:
         * - java.lang.Exception - o compilador obriga a tratar ou propagar
         * - java.lang.RuntimeException - o compilador nõa obriga a tratar ou propagar
         *
         * Quando lançada, uma exceção é propagada na pilha de chamadas de métodos em execução, até
         * que seja capturada (tratada) ou o programa seja encerrado
         *
         * Existe uma hierarquia de exceções do Java
         * - Error: problemas que o programador não vai tratar, quando quebram o programa e é encerrado
         * exemplo: OutOfMemoryError / VirtualMachineError
         *
         * Exception: problemas que são possíveis de tratar
         * - IOException: erros relacionados ao input e output
         * - RuntimeException:
         *      IndexOutOfBoundsException: ex quando tenta acessar uma posição do array que não existe
         *      NullPointerException: ex quando tenta acessar uma variável que vale nulo
         *
         * Existem várias outras exceções, acima são somente exemplos
         *
         * O modelo de tratamento de exceções permite que os erroes sejam tratados de forma consistente e flexível
         *
         * Vantagens:
         *  - delega a lógica do erro para a classe responsável por conhecer as regras que podem ocasionar o erro
         *  - trata de forma organizada (inclusive hierárquica) exceções de tipos diferentes
         *  - a exceção pode carregar dados quaisquer
         *
         * ESTRUTURA TRY-CATCH
         * - bloco try
         *      contém o código que representa a execução normal do trecho de código que PODE acarretar em uma exceção
         *
         * - bloco catch
         *      contém o código a ser eecutado caso uma exceção ocorra
         *      deve ser especificado o tipo de exceção a ser tratada (upcasting é permitido)
         *
         * Sintaxe
         * try {
         * }
         * catch (ExceptionType e){
         *      ...codigo
         * }
         * catch (ExceptionType e){
         *      ...codigo
         * }
         *
         * - bloco finally
         *      é um bloco que contém código a ser executado independentemente de ter ocorrido ou não uma exceção
         *      exemplo clássico: fechar um arquivo, conexão com o db
         *
         * DIVISÃO DE PACOTES "model" - composto pelas entidades e as regras de negócio
         * model
         *      entities
         *      enums
         *      exception
         *      services
         */

        // Problema exemplo

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Room number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Check-in date (dd/MM/yyyy): ");
        LocalDate checkIn = LocalDate.parse(sc.next(), pattern);
        System.out.print("Check-out date (dd/MM/yyyy): ");
        LocalDate checkOut = LocalDate.parse(sc.next(), pattern);

        if(!checkOut.isAfter(checkIn)){
            System.out.println("Error in reservation: Check-out date must be after check-in date");
        } else {
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println(reservation);
            System.out.println();

            System.out.println("Enter data to update the reservation: ");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = LocalDate.parse(sc.next(), pattern);
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = LocalDate.parse(sc.next(), pattern);

            LocalDate now = LocalDate.now();

            if (checkIn.isBefore(now) || checkOut.isBefore(now)){
                System.out.println("Error in reservation: dates must be future");
            } else if(!checkOut.isAfter(checkIn)) {
                System.out.println("Error in reservation: Check-out date must be after check-in date");
            } else {
                reservation.updateDates(checkIn, checkOut);
                System.out.println(reservation);
            }

        }

        sc.close();
    }

//    public static void method1(){
//        System.out.println("### METHOD1 START ###");
//
//        method2();
//
//        System.out.println("### METHOD1 END ###");
//    }
//
//    public static void method2(){
//        System.out.println("### METHOD2 START ###");
//        Scanner sc = new Scanner(System.in);
//
//        try {
//            // tenta executar o código correto
//            String[] vect = sc.nextLine().split(" ");
//            int position = sc.nextInt();
//            System.out.println(vect[position]);
//
//            // se der esse erro específico, apelida de E e executa o bloco
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Invalid position");
//            // imprime na tela o rastreamento do stack de erros
//            // por padrão, quando a exceção não é tratada, termina o programa
//            e.printStackTrace();
//            sc.next();
//
//            // se der esse erro específico, apelida de E e executa o bloco
//        } catch (InputMismatchException e) {
//            System.out.println("Input error");
//        }
//
//        sc.close();
//        System.out.println("### METHOD2 END ###");
//
//    }
//
//    public static void testeFinally(){
//        File file = new File ("C:\\temp\\in.txt");
//        Scanner sc = null;
//
//        try {
//            sc = new Scanner(file);
//            while (sc.hasNextLine()){
//                System.out.println(sc.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Error opening file: " + e.getMessage());
//        } finally {
//            // Finally nesse caso fecha o arquivo independente do resultado
//            if(sc != null){
//                sc.close();
//            }
//            System.out.println("Bloco Finally executado");
//        }
//    }
}