/**
 * @author Heliel Souza Lima
 * @since 2023.
 */
/**
 * Representa uma lista ligada desordenada genérica.
 */
import java.lang.reflect.*;
public class SimpleLinkedList <X>
{
    /**
     * Representa um nó de uma lista.
     */
    private class Node
    {
        //informação do nó
        private X  info;
        //próximo nó
        private Node next;

        //CONSTRUTORES
        /**
         * Construtor que cria um novo NÓ com sua informação e seu próximo elemento
         *
         * @param i - informação do nó do tipo genérico
         * @param x - seu próximo elemento
         */
        public Node (X i, Node n)
        {
            this.info = i;
            this.next = n;
        }

         /**
         * Construtor que cria um novo NÓ com sua informação
         *
         * @param i - informação do nó do tipo genérico
         */
        public Node (X i)
        {
            this.info = i;
            this.next = null;
        }

         //GETTERS
        /**
         * Recupera a informação do nó
         *
         * @return A informação do nó
         */
        public X getInfo ()
        {
            return this.info;
        }

        /**
         * Recupera o próximo elemento do nó
         *
         * @return O próximo elemento do nó
         */
        public Node getNext ()
        {
            return this.next;
        }

        //SETTERS
        /** 
         * Define a informação do nó
         *
         * @param i - A informação do nó
         */
        public void setInfo (X i)
        {
            this.info = i;
        }

        /** 
         * Define o próximo elemento do nó
         *
         * @param n - o próximo elemento do nó
         */
        public void setNext (Node n)
        {
            this.next = n;
        }  
    } //fim da classe Node

    //primeiro e ultimo elemento de uma lista
    private Node first, last;

    //CONSTRUTORES
    /**
     * Construtor que cria uma nova lista, definindo o primeiro e ultimo elemento como nulos
     * 
     */
    public SimpleLinkedList ()
    {
        this.first=this.last=null;
    }

    // MÉTODOS
    /** 
     * criar uma cópia (clone) do objeto passado como parâmetro, usando a reflexão
     *
     * @param x - A lista de voos do aeroporto
     * @return    A cópia
     */
    private X myXClone (X x)
    {
        X ret=null;

        try
        {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro)
        {}
        catch(IllegalAccessException erro)
        {}
        catch(InvocationTargetException erro)
        {}

        return ret;
    }


    /** 
     * Adiciona um novo nó no início da lista ligada
     *
     * @param i          - Nó a ser inserido
     * @throws Exception   Se o parametro for nulo
     */
    public void insertAtBeginning (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X insert=null;
        if (i instanceof Cloneable)
            insert = (X)myXClone(i);
        else
            insert = i;
            
        this.first = new Node (insert,this.first);

        if (this.last==null)
            this.last=this.first;
    }

    /** 
     * Adiciona um novo nó no final da lista ligada
     *
     * @param i          - Nó a ser inserido
     * @throws Exception   Se o parametro for nulo
     */
    public void insertAtFinal (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X insert=null;
        if (i instanceof Cloneable)
            insert = (X)myXClone(i);
        else
            insert = i;
            
        if (this.last==null) // && this.first==null
        {
            this.last   = new Node (insert);
            this.first = this.last;
        }
        else
        {
            this.last.setNext (new Node (insert));
            this.last = this.last.getNext();
        }
    }
    
    /** 
     * Obtém o primeiro nó da lista ligada
     *
     * @return           - Retorna a informação do primeiro nó da lista
     * @throws Exception   Se não tiver nenhum nó adicionado na lista
     */
    public X getFromBeginning () throws Exception
    {
        if (this.first==null/*&&this.fim==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.first.getInfo();
        if (ret instanceof Cloneable)
            ret = myXClone (ret);
            
        return ret;
    }

    /** 
     * Obtém o ultimo nó da lista ligada
     *
     * @return           - Retorna a informação do ultimo nó da lista
     * @throws Exception   Se não tiver nenhum nó adicionado na lista
     */
    public X getFromFinal () throws Exception
    {
        if (this.first==null)
            throw new Exception ("Nada a obter");

        X ret = this.last.getInfo();
        if (ret instanceof Cloneable)
            ret = myXClone (ret);
            
        return ret;
    }
    
    /** 
     * Obtém um nó de acordo com seu valor de informação
     *
     * @param i          - Informação do nó a ser procurado
     * @throws Exception   Se o parametro for nulo
     */
    public X getIndicatedItem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X item =null;
        Node current = this.first;

        while(current != null){
            if(current.getInfo().equals(i)){
                item = current.getInfo();
                return item;
            }else{
                current = current.getNext();
            }
        }

        return item;
    }

    /** 
     * Remove um nó do início da lista
     * 
     * @throws Exception  Se a lista estiver vazia
     */
    public void removeFromBeginning () throws Exception
    {
        if (this.first==null /*&& this.last==null*/)
            throw new Exception ("Nada a remover");

        if (this.first==this.last) //so 1 elemento
        {
            this.first=this.last=null;
            return;
        }
        this.first = this.first.getNext();
    }
    
    /** 
     * Remove um nó do final da lista
     * 
     * @throws Exception  Se a lista estiver vazia
     */
    public void removeFromFinal () throws Exception
    {
        if (this.first==null/*&&this.last==null*/)
            throw new Exception ("Nada a remover");

        if (this.first==this.last) //so 1 elemento
        {
            this.first=this.last=null;
            return;
        }

        Node current;
        for (current=this.first;
             current.getNext()!=this.last;
             current=current.getNext())
             /*comando vazio*/;

        current.setNext(null);
        this .last=current;
    }
    
    /** 
     * Remove um nó da lista de acordo com seu valor de informação
     * 
     * @param i          - Informação do nó a ser removido 
     * @throws Exception Se a lista estiver vazia
     */
    public void removeIndicatedItem (X i) throws Exception
	{
        if (i==null)
            throw new Exception ("Informacao ausente");

        boolean removed=false;

        for(;;) // FOR EVER (repete até break)
        {
            if (this.first==null)
                break;

            if (!i.equals(this.first.getInfo()))
                break;
                
            if (this.last==this.first)
                this.last=null;

            this.first=this.first.getNext();

            removed=true;
        }

        if (this.first!=null)
        {
            Node current=this.first;

            forever:for(;;) // repete ate break
            {
                if (current.getNext()==null)
                    break;

                while (i.equals(current.getNext().getInfo()))
                {
                    if (this.last==current.getNext())
                        this.last=current;

                    current.setNext(current.getNext().getNext());

                    removed=true;

                    if (current==this.last)
                        break forever;
                }

                current=current.getNext();
            }
        }

        if (!removed)
            throw new Exception ("Informacao inexistente");
	}

    /** 
    * Obtém a quantidade de elementos na lista
    *
    * @return A quantidade em numero de quantos nós há na lista
    */
    public int getQuantity ()
    {
        Node current = this.first;
        int ret = 0;

        while (current!=null)
        {
            ret ++;                
            current = current.getNext();
        }
        
        return ret;
    }

    /** 
    * Verifica se há ou não determinado nó
    *
    * @param i         - Elemento a ser procurado 
    * @return            Verdadeiro ou falso se determinado nó existe na lista
    * @throws Exception  Se o parâmetro estiver nulo
    */
    public boolean has (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");
		
        Node current=this.first;

        while (current!=null)
        {
            if (i.equals(current.getInfo()))
                return true;
                
            current = current.getNext();
        }
        
        return false;
	}
	
    /** 
    * Obtem nó na posição i de um for
    *
    * @param i         - Elemento a ser procurado 
    * @return            Elemento na posição i
    * @throws Exception  Se a posição for inválida
    */
    public X getIElement(int i) throws Exception{

        if(i<0){
            throw new Exception("Posicao invalida");
        }

        Node current = this.first;

        int position = 0;

        for(;;){      //for infinito
            if(current == null){
                throw new Exception("Posicao invalida");
            }
            if(position == i){
                if(current.getInfo() instanceof Cloneable){
                    return myXClone(current.getInfo());
                }else{
                    return current.getInfo();
                }
            }
            position++;
            current = current.getNext();
        }       
    }


    /** 
    * Verifica se a lista está vazia
    *
    * @return Verdadeiro se o primeiro elemento for nulo
    */
    public boolean isEmpty ()
    {
        return this.first==null/*&&this.last==null*/;
    }

    // MÉTODOS OBRIGATÓRIOS
    /**
     * Retorna uma representação em String do objeto da lista ligada.
     * @return Uma String que representa a lista ligada.
     */
    public String toString ()
    {
        String ret="[";

        Node current=this.first;

        while (current!=null)
        {
            ret=ret+current.getInfo();

            if (current!=this.last)
                ret=ret+",";

            current=current.getNext();
        }

        return ret+"]";
    }
    
    /**
     * Calcula o código hash do objeto lista ligada.
     * @return O código hash do objeto lista ligada.
     */
    public int hashCode ()
    {
        final int PRIMENUMBER = 13; // qualquer número primo serve
        
        int ret=666; // qualquer inteiro positivo serve

        for (Node current=this.first;
             current!=null;
             current=current.getNext())
             ret = PRIMENUMBER*ret + current.getInfo().hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }

     /**
     * Verifica se o objeto da lista é igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return True se os objetos são iguais, false caso contrário.
     */
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        SimpleLinkedList<X> list =
       (SimpleLinkedList<X>)obj;

        Node currentThis =this .first;
        Node currentList=list.first;

        while (currentThis!=null && currentList!=null)
        {
            if (!currentThis.getInfo().equals(currentList.getInfo()))
                return false;

                currentThis  = currentThis .getNext();
                currentList = currentList.getNext();
        }

        if (currentThis!=null  /* && atualLista==null */)
            return false;

        if (currentList!=null /* && atualThis ==null */)
            return false;

        // atualThis==null && atualLista==null
        return true;
    }
   
    /**
     * Cria um construtor de cópia da classe.
     * @return         - Uma cópia superficial do objeto lista ligada.
     * @throws Exception Caso o modelo seja nulo
     */
    public SimpleLinkedList (SimpleLinkedList<X> model) throws Exception
    {
        if (model==null)
            throw new Exception ("Modelo ausente");

        if (model.first==null)
            return; // sai do construtor, pq this.first ja é null

        this.first = new Node (model.first.getInfo());

        Node currentFromthis   = this  .first;
        Node currentFromModel = model.first.getNext();

        while (currentFromModel!=null)
        {
            currentFromthis.setNext (new Node (currentFromModel.getInfo()));
            currentFromthis  = currentFromthis.getNext ();
            currentFromModel = currentFromModel.getNext ();
        }

        this.last = currentFromthis;
    }

    /**
     * Cria e retorna uma cópia superficial do objeto lista ligada.
     * @return Uma cópia superficial do objeto lista ligada.
     */
    public Object clone ()
    {
        SimpleLinkedList<X> ret=null;

        try
        {
            ret = new SimpleLinkedList (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
