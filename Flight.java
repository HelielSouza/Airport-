/**
 * @author Heliel Souza Lima
 * @since 2023.
 */
/**
 * Representa um Voo.
 */
public class Flight implements Cloneable{

    //numero do voo dentro de um voo
    private int flightNumber;
    //code destino de um voo
    private String destinationCode;
    //nome da city destino de um voo
    private String destinationName;
    
    //CONSTRUTORES
    /**
     * Construtor que cria um novo objeto de Voo com um número, code do airport de destino e o nome do airport
     *
     * @param flightNumber    - Numero do voo
     * @param destinationCode - O código do airport de destino
     * @param destinationName - Nome do airport destino
     */

    public Flight(int flightNumber, String destinationCode, String destinationName){
        this.flightNumber    = flightNumber;
        this.destinationCode = destinationCode;
        this.destinationName = destinationName;
    }

    /**
     * Construtor que cria um novo objeto de Voo com um número, code do airport de destino e o nome do airport
     *
     * @param flightNumber - Numero do voo
     */

    public Flight(int flightNumber){
        this.flightNumber = flightNumber;
    }


    //GETTERS
    /**
     * Recupera o número do voo.
     *
     * @return O número do voo.
     */
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     * Recupera o código do airport de destino.
     *
     * @return O código do airport de destino.
     */
    public String getDestinationCode() {
        return destinationCode;
    }

    /**
     * Recupera o nome do airport de destino.
     *
     * @return O nome do airport de destino.
     */
    public String getDestinationName(){
        return destinationName;
    }

    //SETTERS
    /** 
     * Define o número do voo.
     *
     * @param flightNumber - O número do voo a ser definido.
     */
    public void setFlightNumber(int flightNumber){
        this.flightNumber = flightNumber;
    }

    /** 
     * Define o código do airport de destino.
     *
     * @param destinationCode - O código do airport de destino a ser definido.
     */
    public void setDestinationCode(String destinationCode){
        this.destinationCode = destinationCode;
    }

    /** 
     * Define o nome da city do airport de destino.
     *
     * @param destinationName - O nome da city do airport de destino a ser definido.
     */
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    //MÉTODOS OBRIGATÓRIOS
    /**
     * Retorna uma representação em String do objeto Airport.
     * @return Uma String que representa o objeto Airport.
     */
    @Override
    public String toString(){
        String ret = "(N°:" + this.flightNumber + ", Aeroporto Destino:" + this.destinationCode+")";

        return ret;
    }

    /**
     * Calcula o código hash do objeto Voo.
     * @return O código hash do objeto Voo.
     */
    @Override
    public int hashCode() {
        final int PRIMENUMBER = 13;

        int ret=5;

        ret = PRIMENUMBER * ret +  Integer.valueOf(this.flightNumber).hashCode();
        ret = PRIMENUMBER * ret +  Integer.valueOf(this.destinationCode).hashCode();

        if (ret<0) ret = -ret;
        return ret;
    }

     /**
     * Verifica se o objeto Voo é igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return True se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals (Object obj){
        if (this==obj)
            return true;
        if (obj==null)
            return false;
        if (!(obj instanceof Flight))
            return false;

        Flight fli = (Flight)obj;

        if (this.flightNumber == fli.flightNumber){
            return true;
        }
        if (this.destinationCode == fli.destinationCode){
            return true;
        }

        return false;
    }

    /**
     * Cria um construtor de cópia da classe.
     * @return - Uma cópia superficial do objeto Voo.
     * @throws Exception Caso o modelo seja nulo
     */
    public Flight (Flight model) throws Exception
    {
        if (model==null)
            throw new Exception ("Modelo ausente");

        this.flightNumber = model.flightNumber;
        this.destinationCode = model.destinationCode; 
    }

    /**
     * Cria e retorna uma cópia superficial do objeto Voo.
     * @return Uma cópia superficial do objeto Voo.
     */
    public Object clone ()
    {
        Flight ret=null;

        try
        {
            ret = new Flight (this);
        }
        catch (Exception e)
        {} // this NUNCA é null 
        return ret;
    }
}