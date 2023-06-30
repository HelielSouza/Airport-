/**
 * @author Heliel Souza Lima
 * @since 2023.
 */
/**
 * Representa vários aeroportos.
 */

public class Airports{
   
    static SimpleLinkedList<Airport> airportsList = new SimpleLinkedList<>();
    static String cityName = null;
    static String airportCode = null;

    //MÉTODOS
    /**
     * Adiciona um aeroporto à lista de aeroportos.
     *
     * @param cityName    - O nome da cidade do aeoorto.
     * @param airportCode - O código do aeroporto
     * @throws Exception    se o tamanho do código do aeroporto for diferente de 3
     * @throws Exception    se já existir algum aeroporto com o código digitado
     */

    public static void registerAirport(String cityName, String airportCode) throws Exception{
        if(airportCode.length()!=3){
            throw new Exception
            ("\n----------------------------------\n"
              +"| O tamanho do código deve ser 3 |\n"+
               "----------------------------------");
        }
        if(hasAirport(airportCode)){
            throw new Exception
            ("\n------------------------------------------------------------\n"+
               "| Aeroporto já existente com esse código! Use outro código |\n"+
               "------------------------------------------------------------");
        }
        else{
            SimpleLinkedList<Flight> flightList = new SimpleLinkedList<>();
            Airport airport = new Airport(cityName, airportCode, flightList);
            airportsList.insertAtFinal(airport);
        }
    }

    /**
     * Lista todos os aeroporto já cadastrados
     *
     */

    public static void listAllAirports() throws Exception{ 
        if (Airports.airportsList.getQuantity() != 0){
            System.out.println("\nAeroporto(s) já cadastrado(s):\n");
            for(int i = 0; i < Airports.airportsList.getQuantity(); i++){
                System.out.print("Codigo: " + Airports.airportsList.getIElement(i).getAirportCode() 
                + " .............. " + "Nome: " + Airports.airportsList.getIElement(i).getAirportCity() + "\n");
            }
            System.out.println();
        }
    }

    /**
     * Recupera determinado aeroporto cadastrado.
     *
     * @param airportCode - O código do aeroporto
     * @throws Exception    se o tamanho do código do aeroporto for diferente de 3
     * @throws Exception    se nenhum aeroporto for encontrado com código determinado
     * @return              O aeroporto caso for encontrado
     */

    public static Airport getOneAirport(String airportCode) throws Exception {
        if(airportCode.length()!=3){
            throw new Exception
            ("\n----------------------------------\n"
              +"| O tamanho do código deve ser 3 |\n"+
               "----------------------------------");
        }
        Airport airportFound = null;
        for (int i = 0; i < airportsList.getQuantity(); i++) {
            Airport airport = airportsList.getIElement(i);
            if (airport.getAirportCode().equals(airportCode)) {
                airportFound = airport;
                break;
            }
        }
        if (airportFound == null) {
            throw new Exception
            ("\n---------------------------------------------------\n"+
               "| Não foi encontrado um aeroporto com este código |\n"+
               "---------------------------------------------------");
        }
        else{
            return airportFound;
        }
    }


    /**
     * Verificar se há determinado aeroporto.
     * 
     * @param airportCode - O código do aeroporto
     * @throws Exception    Se o tamanho do código do aeroporto for diferente de 3
     * @return              Se existe ou não o determinado aeroporto procurado
     */

    public static boolean hasAirport(String airportCode) throws Exception{
        if(airportCode.length()!=3){
            throw new Exception
            ("\n----------------------------------\n"
              +"| O tamanho do código deve ser 3 |\n"+
               "----------------------------------");
        }
        Airport a1 = new Airport(airportCode);
        for(int i = 0; i < airportsList.getQuantity(); i++){
            Airport airport = Airports.airportsList.getIElement(i); 
            if(airport.getAirportCode().equals(a1.getAirportCode())){
                return true;
            }
        }
        return false;
    }

    /**
     * Confere se a lista está vazia
     * 
     * @throws Exception Caso a lista de aeroportos estiver vazia
     */

    public static void isAirportListEmpty() throws Exception{
        if(airportsList.getQuantity()<=1){
                throw new Exception
                ("--------------------------------------------------------------------------------------------\n"+
               "\n| Quantidade de aeroportos cadastrados insuficientes! Por favor, cadastre pelo menos dois! |\n"+
                 "--------------------------------------------------------------------------------------------");
        }
    }
}