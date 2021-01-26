package com.learnjava.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjava.model.Persona;

@RestController
public class PersonController {
	
	Map<Integer, Persona> personMap = new HashMap<Integer, Persona>();
	@PostConstruct
	public void initIt() throws Exception {
		Persona persona1=new Persona("Juan Alonso", 35);
		Persona persona2=new Persona("Ana Sanchez", 15);
		Persona persona3=new Persona("Diego Cerretero", 35);
		Persona persona4=new Persona("Sonia Briviesca", 15);
		personMap.put(1, persona1);
		personMap.put(2, persona2);
		personMap.put(3, persona3);
		personMap.put(4, persona4);
	}
	@RequestMapping("/")
	String home() {
		return "API REST Personas";
		
	}
	//otros lo vereis de esta forma:
		// @GetMapping("/get")
		// @PostMapping("/post")
		// @PutMapping("/put/{personId}")
		// @DeleteMapping("/delete/{personId}")
		
		
		@GetMapping("/listar")
		public Map<Integer, Persona> getAllPersons(){
			//Devuelve los datos de la lista, una aplicación  real los devolvería de una base de datos, XML,JSON,...
						
			return personMap;
		} 
		//un poco de teoria
		//¿Cuál es la diferencia entre @RequestParam y @PathVariable?
		
		//con @RequestParam
		//http://localhost:8080/books?isbn=1234
		//@GetMapping("/books/")
	    //public Book getBookDetails(@RequestParam("isbn") String isbn) {
		
		
		//public String welcome(@RequestParam(name="name", required=false, defaultValue="null") String name, @RequestParam(required=false, defaultValue="0") int age)
		
		//public String procesar(@RequestParam("mes") int mes) {
		//De este modo Spring hace por nosotros el trabajo de extraer el parámetro y convertir el tipo de String a int.
		
		
		//en cambio @PathVariable
		//http://localhost:8080/books/1234
		//@GetMapping("/books/{isbn}")
		//public Book getBook(@PathVariable("isbn") String isbn) {
		
		
		@GetMapping("/listar/{personId}")
		public Persona getPersonWithId(@PathVariable Integer personId){
			return personMap.get(personId);
			 
		} 
		
		
		@PostMapping("/insertar/{personId}")
		public void addPerson(@PathVariable Integer personId,@RequestBody Persona persona){
			
			// Añado al HashMap una persona
			personMap.put(personId, persona);
			
			
		}
		
		//un poco de teoria
		// put es un método para modificar recursos donde el cliente envía datos que actualizan TODO el recurso.
		//A diferencia de PUT, PATCH aplica una actualización parcial al recurso.
		//Supongamos que tenemos un recurso que contiene el nombre y el apellido de una persona.

		//Si queremos cambiar el nombre, enviamos una solicitud de actualización.

		//{ "first": "Michael", "last": "Angelo" } 
		//Aquí, aunque solo estamos cambiando el nombre, con la solicitud PUT tenemos que enviar ambos parámetros primero y último .
		//En otras palabras, es obligatorio enviar todos los valores nuevamente, la carga útil completa.

		//Sin embargo, cuando enviamos una solicitud PATCH, solo enviamos los datos que queremos actualizar. En otras palabras, solo enviamos el nombre para actualizar, no es necesario enviar el apellido.
		
		@PutMapping("/modificar/{personId}")
		public void updatePerson(@PathVariable Integer personId, @RequestBody Persona persona){
			
				personMap.remove(personId);
				personMap.put(personId, persona);
			
		}
		
		
		@DeleteMapping("/borrar/{personId}")
		public void deletePerson(@PathVariable Integer personId){
			
			personMap.remove(personId);
			
		}

}
