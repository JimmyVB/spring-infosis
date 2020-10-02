package com.hotel.springboot.app.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.springboot.app.models.entity.Habitacion;
import com.hotel.springboot.app.models.entity.Reserva;
import com.hotel.springboot.app.models.service.HotelService;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private HotelService hotelService;

	@GetMapping(value = "/habitaciones/listar")
	public List<Habitacion> listar() {
		return hotelService.findAll();
	}

	@GetMapping(value = "/habitaciones/listar/descripcion/{descripcion}")
	public Habitacion listarPorDescripcion(@PathVariable String descripcion) {
		return hotelService.findOne(descripcion);
	}

	@GetMapping(value = "/habitaciones/listar/estado/{id}")
	public List<Habitacion> listarPorEstado(@PathVariable Long id) {
		return hotelService.findHabitacionByIdEstado(id);
	}

	@GetMapping(value = "/habitaciones/listar/tipo/{id}")
	public List<Habitacion> listarPorTipo(@PathVariable Long id) {
		return hotelService.findHabitacionByIdTipo(id);
	}
	
	@PostMapping("/crearhabitacion")
	@ResponseStatus(HttpStatus.CREATED)
	public Habitacion crearHabitacion(@RequestBody Habitacion habitacion) {
		return hotelService.saveHabitacion(habitacion);
	}
	
	@PostMapping("/reservar")
	@ResponseStatus(HttpStatus.CREATED)
	public Reserva reservar(@RequestBody Reserva reserva) {
		return hotelService.saveReserva(reserva);
	}
	
	
	@PostMapping(value ="/transicionar")
	@ResponseStatus(HttpStatus.CREATED)
	public String transicionar(@RequestBody Habitacion habitacion) {
		return hotelService.transicionarEstado(habitacion);
	}

}