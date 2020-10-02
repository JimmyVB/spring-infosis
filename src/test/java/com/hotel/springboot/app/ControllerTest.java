package com.hotel.springboot.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.hotel.springboot.app.models.dao.IEstadoHabitacion;
import com.hotel.springboot.app.models.dao.IHabitacionDao;
import com.hotel.springboot.app.models.dao.IReservaDao;
import com.hotel.springboot.app.models.entity.EstadoHabitacion;
import com.hotel.springboot.app.models.entity.Habitacion;
import com.hotel.springboot.app.models.entity.Reserva;
import com.hotel.springboot.app.models.service.HotelServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	@InjectMocks
	private HotelServiceImpl hotelServiceImpl;

	@Mock
	private IHabitacionDao habitacionDao ;

	@Mock
	private IReservaDao reservaDao;

	@Mock
	private IEstadoHabitacion estadoHabitacionDao;

	@BeforeEach
	public void beforeEachTest() {
		MockitoAnnotations.initMocks(this);
	}
	 
	@Test
	public void testFindHabitacionById() {
		Habitacion habitacion = new Habitacion();
		Mockito.when(habitacionDao.findById(Mockito.anyLong())).thenReturn(Optional.of(habitacion));
		assertEquals(Habitacion.class, hotelServiceImpl.findById((long) 1).getClass());
	}
	
	@Test
	public void testFindAllHabitacion() {
		List<Habitacion> listaActual = new ArrayList<>();
		listaActual.add(new Habitacion());

		Mockito.when(habitacionDao.findAll()).thenReturn(listaActual);
		assertEquals(listaActual, hotelServiceImpl.findAll());
	}
	
	@Test
	public void testFindDescripcionHabitacion() {
		Habitacion habitacion = new Habitacion();
		Mockito.when(habitacionDao.findByDescripcion(Mockito.anyString())).thenReturn(habitacion);
		assertEquals(Habitacion.class, hotelServiceImpl.findOne("test").getClass());
	}
	
	@Test
	public void testFindHabitacionByIdTipo() {
		List<Habitacion> listaActual = new ArrayList<>();
		listaActual.add(new Habitacion());
		
		Mockito.when(habitacionDao.findByTipoHabitacionId(Mockito.anyLong())).thenReturn(listaActual);
		assertEquals(listaActual, hotelServiceImpl.findHabitacionByIdTipo((long) 1));
	}

	@Test
	public void testFindHabitacionByIdEstado() {
		List<Habitacion> listaActual = new ArrayList<>();
		listaActual.add(new Habitacion());
		
		Mockito.when(habitacionDao.findByIdEstado(Mockito.anyLong())).thenReturn(listaActual);
		assertEquals(listaActual, hotelServiceImpl.findHabitacionByIdEstado((long) 1));
	}

	@Test
	public void testSaveReserva() {
		Reserva reserva = new Reserva();
		Mockito.when(reservaDao.save(new Reserva())).thenReturn(reserva);
		assertNotNull(hotelServiceImpl.saveReserva(reserva));
	}
	
	@Test
	public void testReservaNull() {
		Reserva reserva = new Reserva();
		reserva.setClienteDni("123");
		reserva.setDiasEstadia(4);
		Habitacion habitacion = new Habitacion();
		habitacion.setId((long)1);
		
		reserva.setHabitacion(habitacion);
		
		Mockito.when(reservaDao.save(new Reserva())).thenReturn(null);
		assertNull(hotelServiceImpl.saveReserva(reserva));
	}
	
	@Test
	public void testTrancicionarEstadoLibreAOcupadoYMantenimiento() {
		
		Habitacion habitacion = new Habitacion();
		
		EstadoHabitacion estadoHabitacion = new EstadoHabitacion();
		habitacion.setId((long) 1);
		estadoHabitacion.setId((long)3);
		estadoHabitacion.setDescripcion("libre");
		habitacion.setEstadoHabitacion(estadoHabitacion);
		
		Mockito.when(habitacionDao.findById(habitacion.getId())).thenReturn(Optional.of(habitacion));
		Mockito.when(estadoHabitacionDao.findById(estadoHabitacion.getId())).thenReturn(Optional.of(estadoHabitacion));

		assertEquals("Se hizo la trancicion de la habitacion correctamente.", hotelServiceImpl.transicionarEstado(habitacion));
		
	}
	
	@Test
	public void testTrancicionarEstadoLibreALimpiezaError() {
		
		Habitacion habitacion = new Habitacion();
		EstadoHabitacion estadoHabitacion = new EstadoHabitacion();
		habitacion.setId((long) 1);
		estadoHabitacion.setId((long)1);
		estadoHabitacion.setDescripcion("libre");
		habitacion.setEstadoHabitacion(estadoHabitacion);
		
		Habitacion nuevaHabitacion = new Habitacion();
		EstadoHabitacion nuevoEstadoHabitacion = new EstadoHabitacion();
		nuevaHabitacion.setId((long) 1);
		nuevoEstadoHabitacion.setId((long)4);
		nuevoEstadoHabitacion.setDescripcion("limpieza");
		nuevaHabitacion.setEstadoHabitacion(nuevoEstadoHabitacion);
		
		Mockito.when(habitacionDao.findById(habitacion.getId())).thenReturn(Optional.of(habitacion));
		Mockito.when(estadoHabitacionDao.findById(estadoHabitacion.getId())).thenReturn(Optional.of(estadoHabitacion));

		assertEquals("La habitacion no se puede cambiar a Limpieza.", hotelServiceImpl.transicionarEstado(nuevaHabitacion));
		
	}
}
