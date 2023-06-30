/**
 * @author Heliel Souza Lima
 * @since 2023.
 */
/**
 * Representa a classe Principal.
 */
public class Main{
    /**
     * Representa o método principal
     */
    public static void main(String[] args) throws Exception {

        //register de aeroportos descritos na atividade
        Airports.registerAirport("Brasilia", "BSB");
        Airports.registerAirport("Belo Horizonte", "CNF");
        Airports.registerAirport("Rio de Janeiro", "GIG");
        Airports.registerAirport("São Paulo", "GRU");
        Airports.registerAirport("Salvador", "SSA");

         //register de voos desses aeroportos
        Flights.registerFlight("BSB", "SSA", "107");
        Flights.registerFlight("CNF", "SSA", "214");
        Flights.registerFlight("CNF", "GIG", "555");
        Flights.registerFlight("CNF", "GRU", "101");
        Flights.registerFlight("GIG", "CNF", "554");
        Flights.registerFlight("GIG", "GRU", "90");
        Flights.registerFlight("GRU", "BSB", "50");
        Flights.registerFlight("GRU", "GIG", "89");
        Flights.registerFlight("GRU", "CNF", "102");
        Flights.registerFlight("SSA", "CNF", "215");

        //chamada para exibir menu
       Menu.showMenu(); 
    }
}
