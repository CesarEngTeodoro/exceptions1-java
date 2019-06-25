package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	// sdf eu coloco como static para que não ser instanciado um novo SimpleDateFormat para cada Objeto Reservation;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}
	
	// Não deixar que as datas serão alteradas arbitrariamente, pois vou criar um método para isso updateDates(checkIn e checkOut)

	// public void setCheckIn(Date checkIn) {
	//	this.checkIn = checkIn;
	//}

	public Date getCheckOut() {
		return checkOut;
	}

	//public void setCheckOut(Date checkOut) {
	//	this.checkOut = checkOut;
	//}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); // Diferença entre as duas datas em milisegundos
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // Converte uma data em milisegundos para dias
	}
	
	public String updateDates(Date checkIn, Date checkOut) {		
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) {
			 return "Reservation dates for update must be future dates";
		}
		if(!checkOut.after(checkIn)) {   
			return "Check-out date must be after check-in date";
		}		
		this.checkIn = checkIn;   // Fazer o checkIn do Objeto recebendo checkIn que veio como argumento do método
		this.checkOut = checkOut;
		return null;
	}
	
	@Override
	public String toString() {
		return  "Room "
			+ roomNumber	
			+ ", check-in: "
			+ sdf.format(checkIn)			
		    + ", check-out: "
		    + sdf.format(checkOut)
		    + ", "
		    + duration()
		    + " nights";
	}
	
	
	
	

}
