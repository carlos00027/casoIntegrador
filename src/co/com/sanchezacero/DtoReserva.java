package co.com.sanchezacero;

import java.util.Date;

public class DtoReserva {
    private int noDocumento;
    private String nombreCliente;
    private String noContacto;
    private String correo;
    private Date fechaReserva;
    private String estado;
    private int cantidadPersonas;
    private String motivo;
    private String tipoDecoracion;

    public DtoReserva(){}

    public String getTipoDecoracion() {
        return tipoDecoracion;
    }

    public void setTipoDecoracion(String tipoDecoracion) {
        this.tipoDecoracion = tipoDecoracion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNoContacto() {
        return noContacto;
    }

    public void setNoContacto(String noContacto) {
        this.noContacto = noContacto;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getNoDocumento() {
        return noDocumento;
    }

    public void setNoDocumento(int noDocumento) {
        this.noDocumento = noDocumento;
    }    
    
}
