package es.upm.miw.pd.gestorVehiculos;

import java.util.List;

public abstract class Vehiculo {

    protected static final Integer MIN_DIAS_ALQUILER = 1;

    private String descripcion;

    private String id;

    public Vehiculo(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehiculo other = (Vehiculo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getId() {
        return id;
    }

    protected abstract Double getPrecio(Integer diasAlquiler);

    protected Double getPrecioRangos(Integer diasAlquiler, Double precioBase) {
        if (diasAlquiler < MIN_DIAS_ALQUILER) {
            throw new MalformedElement("El minimo de dias para alquilar es de 1");
        }
        HandlerRangoPagos handlerRangoPagos = new HandlerRangoPagos(precioBase,
                this.getRangoPagos());
        return handlerRangoPagos.getPrecioFinal(diasAlquiler);
    }

    protected abstract List<RangoPago> getRangoPagos();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " >> ID: " + this.getId() + " DESCRIPCION: "
                + this.getDescripcion();
    }

    public Boolean esVehiculoCorrecto() {
        return this.getId() != null && !this.getId().isEmpty() && this.getDescripcion() != null
                && !this.getDescripcion().isEmpty();
    }

}
