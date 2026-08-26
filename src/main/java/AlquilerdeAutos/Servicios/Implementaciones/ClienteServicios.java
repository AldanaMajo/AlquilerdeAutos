package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Cliente;
import AlquilerdeAutos.Repositorios.ClienteRepository;
import AlquilerdeAutos.Servicios.Interfaces.IclienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicios implements IclienteServicios {

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
    public Cliente buscarPorDocumento(String documentoIdentidad) {
        return clienteRepository.findByDocumentoIdentidad(documentoIdentidad)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con documento: " + documentoIdentidad));
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        if (clienteRepository.existsByDocumentoIdentidad(cliente.getDocumentoIdentidad())) {
            throw new RuntimeException("Ya existe un cliente con ese documento de identidad");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Integer id, Cliente cliente) {
        Cliente existente = buscarPorId(id);
        existente.setNombre(cliente.getNombre());
        existente.setApellido(cliente.getApellido());
        existente.setTelefono(cliente.getTelefono());
        existente.setEmail(cliente.getEmail());
        existente.setDireccion(cliente.getDireccion());
        existente.setNumeroLicencia(cliente.getNumeroLicencia());
        existente.setCategoriaLicencia(cliente.getCategoriaLicencia());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        clienteRepository.deleteById(id);
    }
}