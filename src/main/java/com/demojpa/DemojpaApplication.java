package com.demojpa;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.demojpa.models.Categoria;
import com.demojpa.models.Perfil;
import com.demojpa.models.Trip;
import com.demojpa.models.Usuario;
import com.demojpa.repository.ICategoriaRepository;
import com.demojpa.repository.IPerfilesRepository;
import com.demojpa.repository.ITripRepository;
import com.demojpa.repository.IUsuariosRepository;

@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner {
	
	@Autowired
	private ITripRepository repoTrip;
	
	@Autowired
	private ICategoriaRepository repoCategoria;
	
	@Autowired
	private IPerfilesRepository repoPerfil;
	
	@Autowired
	private IUsuariosRepository repoUsuario;

	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		//llamar a otro metodos
		//testConexion();
		
		//guardar();
		//buscarPorId();
		//modificar();
		//eliminarPorId();
		//cantidadCategorias();
		//eliminarTodo();
		//encontrarPorIds();
		//buscarTodos();
		//existeId();
		//guardarTodas();
		
		//Metodos con JPARepository
		//buscarTodosJpa();
		//borrarEnBatch();
		//buscarTodosOrdenados();
		//buscarTodoEnPaginacion();
		
		//Clase Relaciones
		//buscarTrips();
		//guardarTrip();
		//crearPerfiles();
		//crearUsuarioConDosPerfiles();
		//getUsuario();
		//buscarTripPorEstatus();
		//buscarTripPorDestacadoEstatusOrdenadosDescId();
		//buscarTripEntreCosto();
		//buscarTripEstosEstatus();
		
		
	}
	
        private void testConexion() {
		
		     if (repoCategoria != null)
		        System.out.println("Conexion exitosa: " + repoCategoria);
		     else
			    System.out.println("error en conexion");
	    }
        
		private void guardar() {
			Categoria categoria = new Categoria();
			categoria.setNombre("Trips en la laguna");
			categoria.setDescripcion("Todo tipo de paseos en la laguna");
			repoCategoria.save(categoria);
		}
		
		private void buscarPorId() {
			Optional<Categoria> optional = repoCategoria.findById(1);
			if (optional.isPresent())
				System.out.println(optional.get().getNombre());
			else
				System.out.println("Categoria no encontrada");
		}
		
		private void modificar() {
			Optional<Categoria> optional = repoCategoria.findById(1);
			if (optional.isPresent()) {
				Categoria catTemp = new Categoria();
				catTemp = optional.get();
				catTemp.setNombre("Caminatas en el volcan");
				catTemp.setDescripcion("Exigentes caminatas para...");
				repoCategoria.save(catTemp);
				System.out.println(optional.get());
			}else
				System.out.println("Categoria no encontrada");
		}
		
		private void eliminarPorId() {
			repoCategoria.deleteById(1);
		}
		
		private void cantidadCategorias() {
			long cantidad = repoCategoria.count();
			System.out.println("Cantidad: " + cantidad);
		}
		
		private void eliminarTodo() {
			repoCategoria.deleteAll();
		}
		
		private void encontrarPorIds() {
			List<Integer> ids = new LinkedList<Integer>();
			ids.add(1);
			ids.add(3);
			ids.add(6);
			Iterable<Categoria> categoria = repoCategoria.findAllById(ids);
			for (Categoria cat : categoria)
				System.out.println(cat.getNombre() + " " + cat.getDescripcion());
		}
		
		private void buscarTodos() {
			Iterable<Categoria> categoria = repoCategoria.findAll();
			for (Categoria cat : categoria)
			    System.out.println(cat.getNombre() + " " + cat.getDescripcion());
			     
		}
		
		private void existeId() {
			boolean existe = repoCategoria.existsById(3);
			System.out.println("La categoria existe: " + existe);
		}
		
		private List<Categoria> getCategoria(){
			
			List<Categoria> lista = new LinkedList<Categoria>();
			
			Categoria cat1 = new Categoria();
			cat1.setNombre("Trips en la playa");
			cat1.setDescripcion("Paseos en la playa...");
			
			Categoria cat2 = new Categoria();
			cat1.setNombre("Trips en la ciudad");
			cat1.setDescripcion("Paseos en la ciudad...");
			
			lista.add(cat1);
			lista.add(cat2);
			
			return lista;
			
		}
		
		private void guardarTodas() {
			List<Categoria> lista = getCategoria();
			repoCategoria.saveAll(lista);
		}
		
		
		private void buscarTodosJpa() {
			List<Categoria> lista = repoCategoria.findAll();
			for (Categoria cat : lista) {
				System.out.println(cat.getId() + " " + cat.getNombre());
			}
		}
		
		private void borrarEnBatch() {
			repoCategoria.deleteAllInBatch();
		}
		
		private void buscarTodosOrdenados() {
			List<Categoria> lista = repoCategoria.findAll(Sort.by("nombre"));
			for (Categoria cat : lista)
				System.out.println(cat.getId() + " " + cat.getNombre());
		}
		
		private void buscarTodoEnPaginacion() {
			Page<Categoria> page = repoCategoria.findAll(PageRequest.of(0, 5));
			System.out.println("Total Categorias: " + page.getTotalElements());
			System.out.println("Total Paginas: " + page.getTotalPages());
			for (Categoria cat : page)
				System.out.println(cat.getId() + " " + cat.getNombre());
		}
		
		private void buscarTrips() {
			List<Trip> lista = repoTrip.findAll();
			for (Trip trip : lista)
				System.out.println(trip.getId() + " " + trip.getNombre()
				+ trip.getCategoria().getNombre());
		}
		
		private void guardarTrip() {
			Trip trip = new Trip();
			trip.setNombre("Caminata en la playa");
			trip.setDescripcion("Bonitas caminatas en la playa San Marcelino");
			trip.setFecha(new Date());
			trip.setCosto(15.0);
			trip.setEstatus("Aprobada");
			trip.setDestacado(0);
			trip.setImagen("trip1.png");
			trip.setDescripcion("Esta es una descripcion larga!!!");
			trip.setDetalles("Detalles del trip");
			Categoria categoria = new Categoria();
			categoria.setId(1);
			trip.setCategoria(categoria);
			
			repoTrip.save(trip);
		}
		
		private List<Perfil> getListaPerfiles(){
			
			List<Perfil> lista = new LinkedList<Perfil>();
			Perfil perfil1 = new Perfil();
			perfil1.setNombre("SuperAdministrador");
			
			Perfil perfil2 = new Perfil();
			perfil2.setNombre("Admin");
			
			Perfil perfil3 = new Perfil();
			perfil3.setNombre("Visitante");
	
			
			lista.add(perfil1);
			lista.add(perfil2);
			lista.add(perfil3);
			
			return lista;
		}
		
		private void crearPerfiles() {
			
			repoPerfil.saveAll(getListaPerfiles());
		}
		
		private void crearUsuarioConDosPerfiles() {
			Usuario usuario = new Usuario();
			usuario.setNombre("Cesar Sanchez");
			usuario.setEmail("correo@correo.com");
			usuario.setUsarname("csanchez");
			usuario.setPassword("123");
			usuario.setEstatus("Activo");
			
			Perfil perfil1 = new Perfil();
			perfil1.setId(1);
			
			Perfil perfil2 = new Perfil();
			perfil2.setId(2);
			
			usuario.agregarPerfil(perfil1);
			usuario.agregarPerfil(perfil2);
			
			repoUsuario.save(usuario);
		
		}
		
		private void getUsuario() {
			Optional<Usuario> usuario = repoUsuario.findById(1);
			if (usuario.isPresent()) {
				Usuario usu = usuario.get();
				System.out.println("Usuario: " + usu.getNombre());
				System.out.println("Perfiles del Usuario:");
				for (Perfil p : usu.getPerfiles()) {
					System.out.println(p.getNombre());
				}
			}else {
				System.out.println("Usuario sin perfiles");
			}
		}
		
		private void buscarTripPorEstatus() {
			List<Trip> lista = repoTrip.findByEstatus("Aprobada");
			for (Trip t : lista)
				System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getEstatus());
		}
		
		private void buscarTripPorDestacadoEstatusOrdenadosDescId() {
			List<Trip> lista = repoTrip.findByDestacadoAndEstatusOrderByIdDesc(0, "Aprobada");
			for (Trip t : lista)
				System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getEstatus() 
				                    + " Destacado: " + t.getDestacado());
		}
		
		private void buscarTripEntreCosto() {
			List<Trip> lista = repoTrip.findByCostoBetween(10, 20);
			for (Trip t : lista)
				System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getCosto());
		}
		
		private void buscarTripEstosEstatus() {
			String[] estatus = new String[] {"Aprobada", "Rechazada"};
			List<Trip> lista = repoTrip.findByEstatusIn(estatus);
			for (Trip t : lista)
				System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getEstatus());
		}
		
		
		
}
