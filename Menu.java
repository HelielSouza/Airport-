/**
 * @author Heliel Souza Lima
 * @since 2023.
 /** 
 * Representa a classe menu que será exibido ao usuário, vai requisitar digitação de dados para realizar
 * operações de outras classes.
 */
import java.util.Scanner;
public class Menu {

     /**
     * Método estático usado para exibir menu para o usuário, requisição de digitação de dados e etc.
     */
     public static void showMenu() throws Exception{
          Scanner sc = new Scanner(System.in);   
          
          int option = 9;
         
          while(option != 0){
                    System.out.println("\nBem vindo à VARIG -----------------------------------------------\n");
                    System.out.println("1. Cadastrar um novo aeroporto");
                    System.out.println("2. Cadastrar voos entre aeroportos");
                    System.out.println("3. Remover voo pelo número");
                    System.out.println("4. Listar voos de um aeroporto");
                    System.out.println("0. Sair\n");
                    System.out.println("Qual a opção que você deseja escolher?\n");
                    option = sc.nextInt();
          

               switch(option){
                    case 1: 
                         boolean register = false;
                         boolean codeVerification = false;
                         try{
                              System.out.println("\n--------- CADASTRAR AEROPORTO ---------\n");
                              try {
                                   Airports.listAllAirports();
                              } catch (Exception e) {
                                   System.out.println(e.getMessage());
                              }
                              while(!register || !codeVerification){

                                   System.out.println("\nDigite o nome da cidade a ser cadastrado o aeroporto ou 'v' para sair:");
                                   String airportNameRegister = sc.next().toUpperCase();
                                   if(airportNameRegister.equals("V")){
                                        System.out.println("\n<< Você retornou! <<");
                                        register = true;
                                        codeVerification = true;
                                   }
                                   else{
                                        while(!codeVerification){
                                             System.out.println("\nDigite o código do aeroporto a ser cadastrado ou 'v' para voltar:");
                                             String airportCodeRegister = sc.next().toUpperCase();
                                             if(airportCodeRegister.equals("v")){
                                                  System.out.println("\n<< Você retornou! <<");
                                                  codeVerification = true;
                                             }
                                             else{
                                                  try {
                                                       Airports.registerAirport(airportNameRegister, airportCodeRegister);
                                                       System.out.println("\n>>>>> Aeroporto cadastrado com SUCESSO <<<<<");
                                                       codeVerification = true;
                                                       register = true;
                                                  } 
                                                  catch (Exception e) {
                                                     System.out.println(e.getMessage());
                                                  }
                                             }
                                        }
                                   }
                              }
                         } 
                         catch (Exception e) {
                              System.out.println(e.getMessage());
                         }
                    break;

                    case 2:
                         try {
                              boolean registerFlight = false;
                              boolean codeDestinationVerify = false;
                              boolean flightVerify = false;
                              System.out.println("\n--------- CADASTRAR VOO ---------\n");

                              Airports.isAirportListEmpty();
                              while(!registerFlight || !codeDestinationVerify || !flightVerify){
                                   Airports.listAllAirports();
                                   System.out.println("\nDigite o código do aeroporto de PARTIDA para adicionar um voo ou 'v' para voltar:");
                                   String originAirRegister = sc.next().toUpperCase();
                                   if(originAirRegister.equals("V")){
                                        System.out.println("\n<< Você retornou! <<");
                                        registerFlight = true; //fecha first loop
                                        codeDestinationVerify = true;
                                        flightVerify = true;         
                                   }
                                   else{
                                        codeDestinationVerify = false;
                                        flightVerify = false;
                                        try {
                                             while(!codeDestinationVerify || !flightVerify){
                                                  Flights.showFlighstByCode(originAirRegister); /* código do airport no qual deseja listar seus voos */
                                                  System.out.println("\nDigite o código do aeroporto de DESTINO ou 'v' para voltar:");
                                                  String destinationAirRegister = sc.next().toUpperCase();
                                                  if(destinationAirRegister.equals("v")){
                                                       System.out.println("\n<< Você retornou! <<");
                                                       codeDestinationVerify = true; //fecha segundo loop, volta pro first
                                                       flightVerify = true;
                                                  }
                                                  else{
                                                       while(!flightVerify){
                                                            System.out.println("\nDigite o número do Voo para finalizar o cadastro de voo ou 'v' para voltar:");
                                                            String flightNumber = sc.next();
                                                            if(flightNumber.equals("v")){
                                                                 System.out.println("\n<< Você retornou! <<");
                                                                 flightVerify = true; //fecha terceiro loop, volta pro segundo
                                                            }
                                                            else{
                                                                 try {
                                                                      Flights.registerFlight(originAirRegister, destinationAirRegister, flightNumber);
                                                                      System.out.println("\n>>>>> Voo incluído com SUCESSO <<<<<");
                                                                      registerFlight = true;
                                                                      codeDestinationVerify = true; //fecha todos os loops, pois deu certo
                                                                      flightVerify = true;
                                                                 } 
                                                                 catch (Exception e) {
                                                                      System.out.println(e.getMessage());
                                                                 }
                                                            }
                                                       }
                                                  }
                                             }
                                        } catch (Exception e) {
                                             System.out.println(e.getMessage());
                                        } 
                                   }
                              }  
                         } 
                         catch (Exception e) {
                              System.out.println(e.getMessage());
                         }
                    break;
                    
                    case 3: 
                         try {
                              System.out.println("\n--------- REMOVER VOO ---------\n");
                              Airports.isAirportListEmpty();
                         } 
                         catch (Exception e) {
                              System.out.println(e.getMessage());
                         }
                         boolean checkDelete = false;
                         boolean checkNumberflight  = false;

                         while(!checkDelete || !checkNumberflight){
                              try {
                                   Airports.listAllAirports();
                              } catch (Exception e) {
                                   System.out.println(e.getMessage());
                              }
                              System.out.println("\nDigite o código do aeroporto no qual você deseja excluir um voo ou 'v' para voltar:");
                              String airportCode = sc.next().toUpperCase();
                              if(airportCode.equals("v")){
                                   System.out.println("\n<< Você retornou! <<");
                                   checkDelete = true;
                                   checkNumberflight = true;
                              }
                              else{
                                   checkNumberflight = false;
                                   while(!checkNumberflight){
                                        try {
                                             Flights.showFlighstByCode(airportCode);
                                             System.out.println("\nDigite o número do voo no qual voce deseja excluir ou 'v' para voltar:");
                                             String flightNumberDelete = sc.next();
                                             if(flightNumberDelete.equals("v")){
                                                  System.out.println("\n<< Você retornou! <<");
                                                  checkNumberflight = true;
                                             }
                                             else{
                                                  try {
                                                       Flights.removeFlight(airportCode, flightNumberDelete);
                                                       System.out.println("\n>>>>> Voo removido com SUCESSO <<<<<");
                                                       checkNumberflight  = true;
                                                       checkDelete = true;
                                                  } catch (Exception e) {
                                                       System.out.println(e.getMessage());
                                                  }
                                             }
                                        } 
                                        catch (Exception e) {
                                             System.out.println(e.getMessage());
                                        }
                                   }
                              }
                         }
                         
                    break;

                    case 4:   
                         try {
                              System.out.println("\n--------- LISTAR VOOS ---------\n");
                              Airports.isAirportListEmpty();
                              Airports.listAllAirports();
                              boolean checkListAirport = false;
                              while(!checkListAirport){
                                   System.out.println("Digite o código aeroporto no qual você deseja listar seus voos ou 'v' para voltar");
                                   String airportCodeList = sc.next().toUpperCase();
                                   if(airportCodeList.equals("V")){
                                        System.out.println("\n<< Você retornou! <<");
                                        checkListAirport = true;
                                   }
                                   else{
                                        Flights.showFlighstByCode(airportCodeList); 
                                        checkListAirport = true;     
                                   }
                              }  
                         }
                         catch (Exception e) {
                              System.out.println(e.getMessage());
                         } 
                    break;

                    case 0:
                         System.out.println("Obrigado por acessar meu app!");
                    break;
                    
                    default:
                         System.out.println("Opção invalida");
                    break; 
               }
          }
     sc.close();
     }
}
