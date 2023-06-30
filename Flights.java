/**
 * @author Heliel Souza Lima
 * @since 2023.
 */
/**
 * Representa vários voos.
 */

public class Flights {

    //uma lista de voos
    private SimpleLinkedList<Flight> flightsList;


    // CONSTRUTOR
    /**
     * Construtor que cria um novo objeto de Voos inicializando a lista de voos
     */
    public Flights() {
        this.flightsList = new SimpleLinkedList<>();
    }

    //Getter
    /**
     * Recupera a lista de voos
     *
     * @return A lista de voos
     */
    public SimpleLinkedList<Flight> getFlightsList() {
        return flightsList;
    }

    //Setter
    /** 
     * Define a lista de voos 
     *
     * @param voos - refere-se a lista de voos
     */
    public void setListaVoos(SimpleLinkedList<Flight> voos) {
        this.flightsList = voos;
    }



    //MÉTODOS
    /** 
     * Adiciona um voo a lista de voos de um aeroporto
     *
     * @param originAirCode        - Código do aeroporto de partida
     * @param destinationAirCode   - Código do aeroporto de destino
     * @param flightNumberString   - Numero do voo a ser adicionado
     * @throws Exception             Caso o código do aeroporto esteja vazio
     * @throws Exception             Caso o tamanho do código do aeroporto seja diferente de 3
     * @throws Exception             Caso não seja encontrado o airport com determinado código
     * @throws Exception             Caso o código do aeroporto de partida e de desinto sejam iguais
     * @throws Exception             Caso o número do voo esteja vazio
     * @throws NumberFormatException Caso o número do voo não seja em número inteiros
     * @throws Exception             Caso o número do voo seja menor que 0
     * @throws Exception             Caso o número do voo já esteja cadastrado anteriormente
     * @throws Exception             Caso nenhum exista nenhum airport cadastrado
     */

    public static void registerFlight(String originAirCode, String destinationAirCode, String flightNumberString) throws Exception{
      
        if(originAirCode == null || originAirCode.equals("")){
            throw new Exception("\n------------------------------------------------------\n"+
                                  "| O código está vazio! Digite o número do aeroporto! |\n"+
                                  "------------------------------------------------------");
        }
        if(originAirCode.length()!=3){
            throw new Exception
            ("\n----------------------------------\n"
              +"| O tamanho do código deve ser 3 |\n"+
               "----------------------------------");
        }
        
        Airport originAirport = null; 
        int i=0;
        
        while (i<Airports.airportsList.getQuantity()){
            Airport airport = Airports.airportsList.getIElement(i);
            if (airport.getAirportCode().equals(originAirCode)) {
                originAirport = airport; //se existir, esse tal elemento é atribuido ao variavel do tipo airport
                break; //se existir da um break no while caso o elemento percorrido for igual ao digitado
            }   
            i++;
        }
        if(originAirport == null){ // se for nulo, é porque nao existe
            throw new Exception("\n----------------------------------------------------\n"+
                                  "| Não foi encontrado um aeroporto com este código! |\n"+
                                  "----------------------------------------------------");
        }
        
        if(destinationAirCode == null || destinationAirCode.equals("")){
            throw new Exception
            ("\n------------------------------------------------------\n"+
               "| O código está vazio! Digite o número do aeroporto! |\n"+
               "------------------------------------------------------");
        }

        if(destinationAirCode.length()!=3){
            throw new Exception
            ("\n----------------------------------\n"
              +"| O tamanho do código deve ser 3 |\n"+
               "----------------------------------");
        }


        Airport destinationAirport = null; 
        int j=0;

        while (j <Airports.airportsList.getQuantity()){
            Airport airport = Airports.airportsList.getIElement(j);
            if (airport.getAirportCode().equals(destinationAirCode)) {
                destinationAirport = airport;
                break;
            }
            j++; 
        }

        if(destinationAirport == null)
        throw new Exception
        ("\n----------------------------------------------------\n"+
           "| Não foi encontrado um aeroporto com este código! |\n"+
           "----------------------------------------------------");

        if(originAirCode.equals(destinationAirCode))
        throw new Exception
        ("\n--------------------------------------------------------------------------------------\n"+
           "| Os códigos de aeroporto de partida e destino estão iguais! Precisam ser diferentes |\n"+
           "--------------------------------------------------------------------------------------");

        if(flightNumberString == null || flightNumberString.equals("")){
            throw new Exception
            ("\n----------------------------------------------------------------\n"+
               "| O número do voo está vazio! Você precisa digitar um valor... |\n"+
               "----------------------------------------------------------------");
        }

        int flightNumber;
        try {
            flightNumber = Integer.parseInt(flightNumberString);
        } catch (Exception e) {
            throw new NumberFormatException
            ("\n---------------------------------------------------------------------------------\n"+
               "| Valor INVÁLIDO: O numero do voo deve ser com números inteiros. Ex: 1, 2, 3... |\n"+
               "---------------------------------------------------------------------------------");
        }
        if (flightNumber < 0) {
            throw new Exception
            ("\n---------------------------------------------------------\n"+
               "| Número inválido! O número de voo deve ser maior que 0 |\n"+
               "---------------------------------------------------------");
        }

        //cria uma variavel que recebe o airport com o code digitado pelo usuario
        Airport airport1 = Airports.getOneAirport(originAirCode);
        Flight voo = new Flight(flightNumber); //cria um novo objeto de voo com esse numero  


        //procura esse voo na lista de voos, se existir, lança excecao
        if(voo.equals(originAirport.flightList.getIndicatedItem(voo))){
            throw new Exception
            ("\n---------------------------------------------------------------------\n"+
               "| Já existe um voo cadastrado com esse número! Digite outro número! |\n"+
               "---------------------------------------------------------------------");
        }
        Flight v1 = new Flight(flightNumber, destinationAirport.getAirportCode(), destinationAirport.getAirportCity());
        Airports.airportsList.getIndicatedItem(airport1).getFlightsList().insertAtFinal(v1);
    }           

    /** 
     * Remove um voo da lista de voos de um aeroporto
     *
     * @param airportCode          - Código do aeroporto que contém determinado voo
     * @param flightNumberString   - Numero do voo a ser removido
     * @throws Exception             Caso nenhum exista nenhum aeroporto cadastrado
     * @throws Exception             Caso o código do aeroporto esteja vazio
     * @throws Exception             Caso o tamanho do código do aeroporto seja diferente de 3
     * @throws Exception             Caso não seja encontrado o aeroporto com determinado código
     * @throws Exception             Caso exista nenhum voo cadastrado no código determinado
     * @throws Exception             Caso o número do voo esteja vazio
     * @throws NumberFormatException Caso o número do voo não seja em número inteiros
     * @throws Exception             Caso o número do voo seja menor que 0
     * @throws Exception             Caso o número do voo não esteja cadastrado
     
     */

    public static void removeFlight(String airportCode, String flightNumberString) throws Exception {
        if(Airports.airportsList.getQuantity()==0){
            throw new Exception("No momento não há aeroporto cadastrado para realizar o register de um Vôo!");
        }
        if(airportCode == null || airportCode.equals("")){
            throw new Exception("\n------------------------------------------------------\n"+
                                  "| O código está vazio! Digite o número do aeroporto! |\n"+
                                  "------------------------------------------------------");
        }
        if(airportCode.length()!=3){
            throw new Exception
            ("\n----------------------------------\n"
              +"| O tamanho do código deve ser 3 |\n"+
               "----------------------------------");
        }

        SimpleLinkedList<Airport> copylist1 = (SimpleLinkedList<Airport>) Airports.airportsList.clone();
        SimpleLinkedList<Airport> copylist2 = (SimpleLinkedList<Airport>) Airports.airportsList.clone();

        Airport airport1 = null;
        int i=0;
        while (i<Airports.airportsList.getQuantity()){
            if (copylist1.getFromBeginning().getAirportCode().equals(airportCode)) {
                airport1 = copylist1.getFromBeginning();
                break;
            }
            copylist1.removeFromBeginning();
            i++;
            if(i==Airports.airportsList.getQuantity()){
                throw new Exception("Nao foi encontrado um aeroporto com este código");
            }
        }
        
        if(airport1.flightsQuantity()==0) {
            throw new Exception("Ainda não há nenhum Vôo cadastrado neste aeroporto!");
        }

        if(flightNumberString == null || flightNumberString.equals("")){
            throw new Exception
            ("\n----------------------------------------------------------------\n"+
               "| O número do voo está vazio! Você precisa digitar um valor... |\n"+
               "----------------------------------------------------------------");
        }
        int flightNumber; 
        try {
            flightNumber = Integer.parseInt(flightNumberString);
        } catch (Exception e) {
            throw new NumberFormatException
            ("\n---------------------------------------------------------------------------------\n"+
               "| Valor INVÁLIDO: O numero do voo deve ser com números inteiros. Ex: 1, 2, 3... |\n"+
               "---------------------------------------------------------------------------------");
        }

        //caso seja menor do que 0, invalido
        if(flightNumber<0){
            throw new Exception
            ("\n-----------------------------------------------------\n"+
               "| número INVÁLIDO: O numero do deve ser maior que 0 |\n"+
               "-----------------------------------------------------");
        }

        Flight flight = new Flight(flightNumber);
        Flight f1 = null;
        Airport airport2 = null;

        for(int a=0; a< Airports.airportsList.getQuantity(); a++){
            if(f1==null){
                f1 = copylist2.getFromBeginning().getFlightsList().getIndicatedItem(flight);
                airport2 = copylist2.getFromBeginning();
            }
            if(f1!=null){
                break;
            }
            copylist2.removeFromBeginning();
        }

        if(f1 == null){
            throw new Exception
            ("\n-------------------------------------------------------------------------------\n"+
               "| Número de voo INEXISTENTE: Você só pode excluir voos que já foram incluídos |\n"+
               "-------------------------------------------------------------------------------");
        }

        Airports.airportsList.removeIndicatedItem(airport2);
        airport2.removeAFlight(airport2.getFlightsList(),flightNumber);
        Airports.airportsList.insertAtFinal(airport2);
    }

    /** 
     * Lista os voos de um determinado aeroporto pelo seu código
     * 
     * @param airportCode - Código do aeroporto a ser listado todos seus voos
     * @throws Exception    Caso o código do aeroporto esteja vazio
     * @throws Exception    Caso o tamanho do código do aeroporto seja diferente de 3
     * @throws Exception    Caso nenhum aeroporto com determinado código
    */

    public static void showFlighstByCode(String airportCode) throws Exception{

        if(airportCode == null || airportCode.equals("")){
            throw new Exception
            ("\n----------------------------------------------------------------\n"+
               "| O número do voo está vazio! Você precisa digitar um valor... |\n"+
               "----------------------------------------------------------------");
        }
        if(airportCode.length()!=3){
            throw new Exception
            ("\n----------------------------------\n"
              +"| O tamanho do código deve ser 3 |\n"+
               "----------------------------------");
        }
        if(!Airports.hasAirport(airportCode)){
            throw new Exception
            ("\n----------------------------------------------------------------\n"+
               "| código INVÁLIDO: Não existe nenhum aeroporto com esse código |\n"+
               "----------------------------------------------------------------");
        }
        
        Airport airportSearch = Airports.getOneAirport(airportCode);

        if (airportSearch.flightsQuantity() <= 0) {
            System.out.println("\n>>> Ainda não há voos cadastrados nesse aeroporto <<<");
        } else {
            System.out.println("\nVoos do aeroporto do código " + 
            airportSearch.getAirportCode() + ": ------------------------------------------\n");
        
            for (int d = 0; d < airportSearch.flightsQuantity(); d++) {
                Flight f = airportSearch.getFlightsList().getIElement(d);
                String destinationCode = f.getDestinationCode();
                Airport destinationAirport = Airports.getOneAirport(destinationCode);
                String destinationCity = destinationAirport.getAirportCity();
        
                if (d == airportSearch.flightsQuantity() - 1) {
                    System.out.println("Numero do voo: " + f.getFlightNumber()
                            + " | Código aeroporto Destino: " + destinationCode
                            + " | Cidade do aeroporto Destino: " + destinationCity);
                    break;
                }
                System.out.println("Numero do voo: " + f.getFlightNumber()
                        + " | Código aeroporto Destino: " + destinationCode
                        + " | Cidade do aeroporto Destino: " + destinationCity);
            }
        }
    }
}