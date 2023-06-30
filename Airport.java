/**
 * @author Heliel Souza Lima
 * @since 2023.
 */
/**
 * Representa um aeroporto.
 */

public class Airport implements Cloneable{

    //código de um aeroporto
    private String code;   
     //nome da cidade de um aeroporto                    
    private String city;                                            
     //lista de voos de um aeroporto
    SimpleLinkedList<Flight> flightList = new SimpleLinkedList<>();

    //CONSTRUTORES
    /**
     * Construtor que cria um novo objeto aeroporto com nome da cidade, código do aeroporto e uma lista de voos.
     *
     * @param cityName    - O nome do aeroporto.
     * @param airportCode - O código do aeroporto.
     * @param flights     - Representa uma lista de voos 
     */

     public Airport(String cityName, String airportCode, SimpleLinkedList<Flight> flights){
        this.city = cityName;
        this.code = airportCode;
        this.flightList = flights;
    }

    /**
     * Construtor que cria um novo objeto de aeroporto com apenas o código
     *
     * @param code O código do aeroporto.
     */

    public Airport(String code){
        this.code = code;
    }

    //GETTERS
    /**
     * Recupera o nome do aeroporto.
     *
     * @return O nome do aeroporto.
     */
    public String getAirportCity() {
        return city;
    }

    /**
     * Recupera o código do aeroporto.
     *
     * @return O código do aeroporto.
     */
    public String getAirportCode() {
        return code;
    }

    /**
     * Recupera a lista de voos do aeroporto.
     *
     * @return A lista de voos do do aeroporto.
     */
    public SimpleLinkedList<Flight> getFlightsList() {
        return this.flightList;
    }

    //SETTERS 
    /** 
     * Define o nome da cidade do aeroporto.
     *
     * @param city - O nome da cidade do aeroporto a ser definido.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /** 
     * Define o código do aeroporto
     *
     * @param cod - O código do aeroporto
     */
    public void setCode(String code) throws Exception {
        this.code = code;
    }

    /** 
     * Define a lista de voos do aeroporto
     *
     * @param flights - A lista de voos do aeroporto
     */
    public void setFlights(SimpleLinkedList<Flight> flights) {
        this.flightList = flights;
    }

    // MÉTODOS
    /** 
     * Remove um voo desse aeroporto específico
     *
     * @param flightList   - A lista de voos do aeroporto
     * @param flightNumber - O nuúmero do voo a ser removido desse aeroporto
     * @throws Exception     Caso a lista de voos não esteja definida
     */
    
    public void removeAFlight(SimpleLinkedList<Flight>flightList, int flightNumber)throws Exception{

        if(flightList == null){
            throw new Exception("A lista está vazia");
        }

        SimpleLinkedList<Flight> copyList = (SimpleLinkedList<Flight>) flightList.clone();
        SimpleLinkedList<Flight> list = new SimpleLinkedList<>();

        for(int i=0; i<flightList.getQuantity();i++){
            if(flightNumber==copyList.getFromBeginning().getFlightNumber()){
                copyList.removeFromBeginning();
            }else {
                list.insertAtBeginning(copyList.getFromBeginning());
                copyList.removeFromBeginning();
            }
        }
        this.flightList = list;
    }

     /** 
     * Recupera a quantide em números de voos existentes na lista de voos
     *
     *@return A quantidade de voos 
     */
    public int flightsQuantity(){
        return this.flightList.getQuantity();
    }

    // MÉTODOS OBRIGATÓRIOS
    /**
     * Retorna uma representação em String do objeto aeroporto.
     * @return Uma String que representa o objeto aeroporto.
     */
    @Override
    public String toString() {
        return city + " " + code;
    }

    /**
     * Calcula o código hash do objeto aeroporto.
     * @return O código hash do objeto aeroporto.
     */
    @Override
    public int hashCode() {
        int hash = 2;
        hash = 3 * hash + city.hashCode();
        hash = 5 * hash + Integer.valueOf(this.code).hashCode();
        hash = 7 * hash + flightList.hashCode();

        if (hash < 0) hash = -hash;
        return hash;
    }

     /**
     * Verifica se o objeto aeroporto é igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return True se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;

        if (this.getClass() != obj.getClass()) return false;

        Airport a = (Airport) obj;

        return city.equals(a.city) && code== a.code && flightList.equals(a.flightList);
    }

    /**
     * Cria um construtor de cópia da classe.
     * @return         - Uma cópia superficial do objeto aeroporto.
     * @throws Exception Caso o modelo seja nulo
     */
    public Airport (Airport model) throws Exception
    {
        if (model==null)
            throw new Exception ("Modelo ausente");

        this.city = model.city;
        this.code = model.code;
        this.flightList  = (SimpleLinkedList<Flight>) model.flightList.clone();
    }
    
    /**
     * Cria e retorna uma cópia superficial do objeto aeroporto.
     * @return Uma cópia superficial do objeto aeroporto.
     */
    public Object clone ()
    {
       Airport ret=null;

        try
        {
            ret = new Airport (this);
        }
        catch (Exception erro)
        {} // sei que this NUNCA é null e o contrutor de copia da erro quando seu parametro é null

        return ret;
    }
}