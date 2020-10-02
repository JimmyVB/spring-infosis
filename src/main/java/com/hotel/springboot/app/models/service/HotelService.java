package com.hotel.springboot.app.models.service;

import java.util.List;

import com.hotel.springboot.app.models.entity.Habitacion;
import com.hotel.springboot.app.models.entity.Reserva;

public interface HotelService {

	public Habitacion findById(Long id);
	public List<Habitacion> findAll();
	public Habitacion findOne(String descripcion);
	public List<Habitacion> findHabitacionByIdTipo(Long id);
	public List<Habitacion> findHabitacionByIdEstado(Long id);
	public Reserva saveReserva(Reserva reserva);
	public Habitacion saveHabitacion(Habitacion habitacion);
	public String transicionarEstado(Habitacion habitacion);
}
