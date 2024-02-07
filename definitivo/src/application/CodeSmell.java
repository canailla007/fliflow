package application;

public class CodeSmell {
    @Override
    public String toString() {
        return "CodeSmell{" +
                "id=" + id +
                ", resultado='" + resultado + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    private int id;
    private String resultado;

    public CodeSmell(int id, String Resultado){
        this.id=id;
        this.resultado=Resultado;
    }
}
