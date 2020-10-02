package com.hotel.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.hotel.springboot.app.models.entity.Reserva;

public interface IReservaDao extends CrudRepository<Reserva, Long>{

}
