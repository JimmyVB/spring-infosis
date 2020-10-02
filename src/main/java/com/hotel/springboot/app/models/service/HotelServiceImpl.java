package com.hotel.springboot.app.models.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.springboot.app.models.dao.IEstadoHabitacion;
import com.hotel.springboot.app.models.dao.IHabitacionDao;
import com.hotel.springboot.app.models.dao.IReservaDao;
import com.hotel.springboot.app.models.entity.EstadoHabitacion;
import com.hotel.springboot.app.models.entity.Habitacion;
import com.hotel.springboot.app.models.entity.Reserva;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private IHabitacionDao habitacionDao;

	@Autowired
	private IReservaDao reservaDao;
	
	@Autowired
	private IEstadoHabitacion estadoHabitacionDao;
	
	@Override
	@Transactional(readOnly = true)
	public Habitacion findById(Long id) {
		return habitacionDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> findAll() {
		return (List<Habitacion>) habitacionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Habitacion findOne(String descripcion) {
		return habitacionDao.findByDescripcion(descripcion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> findHabitacionByIdTipo(Long id) {
		return habitacionDao.findByTipoHabitacionId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> findHabitacionByIdEstado(Long id) {
		return habitacionDao.findByIdEstado(id);
	}

	@Override
	@Transactional
	public Habitacion saveHabitacion(Habitacion habitacion) {
		return habitacionDao.save(habitacion);
	}
	
	@Override
	@Transactional
	public Reserva saveReserva(Reserva reserva) {
		if(!Objects.isNull(reservaDao.save(reserva))) {
			Habitacion habitacionActual = findById(reserva.getHabitacion().getId());
			EstadoHabitacion estadoHabitacion = new EstadoHabitacion();
			estadoHabitacion.setId((long) 2);
			habitacionActual.setEstadoHabitacion(estadoHabitacion);
			habitacionDao.save(habitacionActual);
		}else {
			reserva = null;
		}
		return reserva;
	}

	@Override
	@Transactional
	public String transicionarEstado(Habitacion habitacion){
			
		Long idEstado = habitacion.getEstadoHabitacion().getId();
		String status = null;
		Habitacion nuevaHabitacion = habitacionDao.findById(habitacion.getId()).orElse(null);;
		EstadoHabitacion estadoHabitacion = estadoHabitacionDao.findById(idEstado).orElse(null);;
		EstadoHabitacion nuevoEstadoHabitacion = new EstadoHabitacion();
		
		if(nuevaHabitacion.getEstadoHabitacion().getDescripcion().equalsIgnoreCase("libre")){
			if(idEstado == 2 || idEstado == 3) {
				nuevoEstadoHabitacion.setId(idEstado);
				nuevaHabitacion.setEstadoHabitacion(estadoHabitacion);
				habitacionDao.save(nuevaHabitacion);
				status = "Se hizo la trancicion de la habitacion correctamente.";
			}else if(idEstado == 1){
				status = "Esta enviando el mismo estado.";
			}else {
				status = "La habitacion no se puede cambiar a Limpieza.";
			}
		}else if(nuevaHabitacion.getEstadoHabitacion().getDescripcion().equalsIgnoreCase("ocupada")){
			if(idEstado == 3 || idEstado == 4) {
				nuevoEstadoHabitacion.setId(idEstado);
				nuevaHabitacion.setEstadoHabitacion(estadoHabitacion);
				habitacionDao.save(nuevaHabitacion);
				status = "Se hizo la trancicion de la habitacion correctamente.";
			}else if(idEstado == 2){
				status = "Esta enviando el mismo estado.";
			}else {
				status = "La habitacion no se puede cambiar a Libre.";
			}
		}else if(nuevaHabitacion.getEstadoHabitacion().getDescripcion().equalsIgnoreCase("en mantenimiento")){
			if(idEstado == 1 || idEstado == 4) {
				nuevoEstadoHabitacion.setId(idEstado);
				nuevaHabitacion.setEstadoHabitacion(estadoHabitacion);
				habitacionDao.save(nuevaHabitacion);
				status = "Se hizo la trancicion de la habitacion correctamente.";
				
			}else if(idEstado == 3){
				status = "Esta enviando el mismo estado.";
			}else {
				status = "La habitacion no se puede cambiar a Ocupada.";
			}
		}else if(nuevaHabitacion.getEstadoHabitacion().getDescripcion().equalsIgnoreCase("limpieza")){
			if(idEstado == 1 || idEstado == 3) {
				nuevoEstadoHabitacion.setId(idEstado);
				nuevaHabitacion.setEstadoHabitacion(estadoHabitacion);
				habitacionDao.save(nuevaHabitacion);
				status = "Se hizo la trancicion de la habitacion correctamente.";
			}else if(idEstado == 4){
				status = "Esta enviando el mismo estado.";
			}else {
				status = "La habitacion no se puede cambiar a Limpieza.";
			}
		}
		
		return status;
	}
}
