package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Cliente;
import AlquilerdeAutos.Repositorios.ClienteRepository;
import AlquilerdeAutos.Servicios.Interfaces.IClienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicios implements IClienteServicios {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServicios(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Cliente buscarPorDui(String dui) {
        return clienteRepository.findByDui(dui)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con DUI: " + dui));
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Integer id, Cliente cliente) {
        Cliente existente = buscarPorId(id);
        existente.setDui(cliente.getDui());
        existente.setLicencia(cliente.getLicencia());
        existente.setTelefono(cliente.getTelefono());
        existente.setDireccion(cliente.getDireccion());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        clienteRepository.deleteById(id);
    }
}