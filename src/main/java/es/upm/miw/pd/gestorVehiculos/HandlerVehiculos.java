package es.upm.miw.pd.gestorVehiculos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HandlerVehiculos implements Iterable<Vehiculo> {

    private final List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

    public void altaVehiculo(Vehiculo vehiculo) {
        if (vehiculos.contains(vehiculo)) {
            throw new ElementAlreadyExistsException("Ya existe un vehiculo con ese id");
        } else if (vehiculo == null || !vehiculo.esVehiculoCorrecto()) {
            throw new MalformedElement("Debe introducir los datos del vehiculo");
        }
        vehiculos.add(vehiculo);
    }

    public String listarVehiculos() {
        String vehiculosString = "";
        for (Vehiculo vehiculo : vehiculos) {
            vehiculosString = vehiculosString + vehiculo.toString() + "\n";
        }
        return vehiculosString;
    }

    public Double verPrecio(String idVehiculo, Integer diasAlquiler) {
        Double precioVehiculo = null;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId().equals(idVehiculo)) {
                precioVehiculo = vehiculo.getPrecio(diasAlquiler);
            }
        }
        if (precioVehiculo == null) {
            throw new NoSuchElementException("Vehiculo no encontrado");
        }
        return precioVehiculo;
    }

    @Override
    public Iterator<Vehiculo> iterator() {
        return this.vehiculos.iterator();
    }

    public Integer numeroVehiculos() {
        return this.vehiculos.size();
    }

}
