package N1Excepciones.repositorio.listas;

import N1Excepciones.modelo.Producto;
import N1Excepciones.repositorio.AbstractListRepository;
import N1Excepciones.repositorio.Direccion;
import N1Excepciones.repositorio.exceptions.AccessReadDataException;

import java.util.ArrayList;
import java.util.List;

public class ProductListRepository extends AbstractListRepository<Producto> {


    @Override
    public List<Producto> listar(String campo, Direccion direccion) {
        List<Producto> listaOrdenada = new ArrayList<>(dataSource);
        listaOrdenada.sort((o1, o2) -> {
            int resultado = 0;
            if (direccion == Direccion.ASC) {
                resultado = ordenar(campo,o1, o2);
            } else if (direccion == Direccion.DESC) {
                resultado = ordenar(campo,o2, o1);
            }
            return resultado;
        });
        return listaOrdenada;
    }

    private int ordenar(String campo, Producto a, Producto b) {
        int result = 0;
        switch (campo) {
            case "id" -> result = a.getId().compareTo(b.getId());
            case "descripcion" -> result = a.getDescripcion().compareTo(b.getDescripcion());
            case "precio" -> result = a.getPrecio().compareTo(b.getPrecio());
        }
        return result;
    }

    @Override
    public void modificar(Producto producto)throws AccessReadDataException {
        Producto p= porId(producto.getId());
        p.setDescripcion(producto.getDescripcion());
        p.setPrecio(producto.getPrecio());
    }
}
