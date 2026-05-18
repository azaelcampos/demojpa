package com.demojpa;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demojpa.models.Categoria;
import com.demojpa.repository.ICategoriaRepository;

@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner {
	
	@Autowired
	private ICategoriaRepository repoCategoria;

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
		buscarTodos();
		
	}
        private void testConexion() {
		
		     if (repoCategoria != null)
		        System.out.println("Conexion exitosa: " + repoCategoria);
		     else
			    System.out.println("error en conexion");
	    }
        
		private void guardar() {
			Categoria categoria = new Categoria();
			categoria.setNombre("Trips en la playa");
			categoria.setDescripcion("Todo tipo de paseos en la playa");
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
		
	

}
