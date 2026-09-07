package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Categoria;
import AlquilerdeAutos.Modelos.Vehiculo;
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaServicios;
import AlquilerdeAutos.Servicios.Interfaces.ImarcaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IvehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/Vehiculo")
public class VehiculoWebController {

    private final IvehiculoServicios vehiculoService;
    private final IcategoriaServicios categoriaService;
    private final ImarcaServicios marcaService;

    @Autowired
    public VehiculoWebController(IvehiculoServicios vehiculoService,
                                 IcategoriaServicios categoriaService,
                                 ImarcaServicios marcaService) {
        this.vehiculoService = vehiculoService;
        this.categoriaService = categoriaService;
        this.marcaService = marcaService;
    }

    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("vehiculos", vehiculoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("marcas", marcaService.listar());
        model.addAttribute("nuevoVehiculo", new Vehiculo());
        return "Vehiculo/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculo,
                          @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {

        if (vehiculo.getPrecio_por_dia() == null && vehiculo.getCategoria() != null && vehiculo.getCategoria().getId() != null) {
            Categoria cat = categoriaService.buscarPorId(vehiculo.getCategoria().getId());
            vehiculo.setPrecio_por_dia(cat.getTarifaBaseDiaria());
        }

        if (imagenFile != null && !imagenFile.isEmpty()) {
            String nombreArchivo = guardarImagen(imagenFile);
            vehiculo.setImagen(nombreArchivo);
        }

        vehiculoService.guardar(vehiculo);
        return "redirect:/Vehiculo/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Vehiculo vehiculo,
                         @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {

        if (vehiculo.getCategoria() != null && vehiculo.getCategoria().getId() != null) {
            Categoria cat = categoriaService.buscarPorId(vehiculo.getCategoria().getId());
            vehiculo.setCategoria(cat);
            if (vehiculo.getPrecio_por_dia() == null) {
                vehiculo.setPrecio_por_dia(cat.getTarifaBaseDiaria());
            }
        }

        if (vehiculo.getMarca() != null && vehiculo.getMarca().getId() != null) {
            vehiculo.setMarca(marcaService.buscarPorId(vehiculo.getMarca().getId()));
        }

        if (imagenFile != null && !imagenFile.isEmpty()) {
            String nombreArchivo = guardarImagen(imagenFile);
            vehiculo.setImagen(nombreArchivo);
        }

        vehiculoService.actualizar(vehiculo.getId(), vehiculo);
        return "redirect:/Vehiculo/Index";
    }

    private String guardarImagen(MultipartFile file) {
        try {
            String carpeta = "uploads/vehiculos/";
            Files.createDirectories(Paths.get(carpeta));

            String extension = "";
            String original = file.getOriginalFilename();
            if (original != null && original.contains(".")) {
                extension = original.substring(original.lastIndexOf("."));
            }

            String nombreUnico = UUID.randomUUID().toString() + extension;
            Path destino = Paths.get(carpeta + nombreUnico);
            Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            return nombreUnico;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
        }
    }
}