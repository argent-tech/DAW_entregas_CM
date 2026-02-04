package entrega_04.src;

import java.time.LocalDate;

public class Reserva {
    private LocalDate fechaReserva;
    private String nombreReserva;
    private String nombreCliente;
    private TipoReserva tipoReserva;

    public Reserva(LocalDate fechaReserva, String nombreReserva, String nombreCliente, TipoReserva tipoReserva) {
        this.fechaReserva = fechaReserva;
        this.nombreReserva = nombreReserva;
        this.nombreCliente = nombreCliente;
        this.tipoReserva = tipoReserva;
    }

    public String getNombreReserva() {
        return nombreReserva;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public TipoReserva getTipoReserva() {
        return tipoReserva;
    }

    public void setNombreReserva(String nombreReserva) {
        this.nombreReserva = nombreReserva;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setTipoReserva(TipoReserva tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "nombreReserva='" + nombreReserva + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", fechaReserva=" + fechaReserva +
                ", tipoReserva=" + tipoReserva +
                '}';
    }
}
