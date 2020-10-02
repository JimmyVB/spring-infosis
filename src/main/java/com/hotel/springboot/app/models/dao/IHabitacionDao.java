package com.hotel.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hotel.springboot.app.models.entity.Habitacion;

public interface IHabitacionDao extends CrudRepository<Habitacion, Long>{

	@Query("select th from Habitacion th where th.tipoHabitacion.id=?1")
	public List<Habitacion> findByTipoHabitacionId(Long id);
	
	@Query("select th from Habitacion th where th.estadoHabitacion.id=?1")
	public List<Habitacion> findByIdEstado(Long id);

	@Query("select h from Habitacion h where h.descripcion like %?1%")
	public Habitacion findByDescripcion(String descripcion);
}
