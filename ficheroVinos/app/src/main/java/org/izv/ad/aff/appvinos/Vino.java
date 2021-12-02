package org.izv.ad.aff.appvinos;

import java.util.Objects;

public class Vino {

    //atributos
    private long id;
    private String nombre;
    private String bodega;
    private String color;
    private String origen;
    private double grados;
    private int fecha;

    //constructor que recibe los datos del vino
    public Vino(long id, String nombre, String bodega, String color, String origen, double grados, int fecha) {
        this.id = id;
        this.nombre = nombre;
        this.bodega = bodega;
        this.color = color;
        this.origen = origen;
        this.grados = grados;
        this.fecha = fecha;
    }

    //iniciamos los datos del vino
    public Vino() {
        this(0, null, null, null, null, 0.0, 0);
    }

    //GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }

    public String getBodega() {
        return bodega;
    }

    public String getColor() {
        return color;
    }

    public String getOrigen() {
        return origen;
    }

    public double getGrados() {
        return grados;
    }

    public int getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setGrados(double grados) {
        this.grados = grados;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //comparar objetos vino
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vino vino = (Vino) o;

        if (fecha != vino.fecha) return false;
        if (nombre != null ? !nombre.equals(vino.nombre) : vino.nombre != null) return false;
        return bodega != null ? bodega.equals(vino.bodega) : vino.bodega == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //para crear objetos vino
    public static Vino leeVino(String s) {
        //cada posicion dle array es un atributo del vino
        String[] atributos = s.split(";");

        Vino v = null;
        if (atributos.length >= 6) {
            //creamos objeto vino y le pasamos la informacion recogida en el array
            v = new Vino();
            try {
                v.setId(Long.parseLong(atributos[0].trim()));
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
            v.setNombre(atributos[1].trim());
            v.setBodega(atributos[2].trim());
            v.setColor(atributos[3].trim());
            v.setOrigen(atributos[4].trim());
            try {
                v.setGrados(Double.parseDouble(atributos[5].trim()));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            try {
                v.setFecha(Integer.parseInt(atributos[6].trim()));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return v;
    }

    //método que recibe un vino y lo guarda en el CSV
    public static String escribeVino(Vino v){

        return v.getId() + "; " +
                v.getNombre() + "; " +
                v.getBodega() + "; " +
                v.getColor() + "; " +
                v.getOrigen() + "; " +
                v.getGrados() + "; " +
                v.getFecha();

    }

}