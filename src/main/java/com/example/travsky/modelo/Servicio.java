package com.example.travsky.modelo;

import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * @author Matias
 */

@Entity
@Table(name = "servicios")
public class Servicio {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "s_codigo")
    @JsonView({ServiceView.ServicioSimple.class, ServiceView.ServicioDetallado.class})
    private int s_codigo;
    
    @Column(name = "s_nombre")
    @JsonView({ServiceView.ServicioSimple.class, ServiceView.ServicioDetallado.class})
    private String s_nombre;
    
    @Column(name = "descripcion")
    @JsonView({ServiceView.ServicioSimple.class, ServiceView.ServicioDetallado.class})
    private String descripcion;
    
    @Column(name = "imagen")
    @JsonView({ServiceView.ServicioSimple.class, ServiceView.ServicioDetallado.class})
    private String imagen;
    
    @Column(name = "destino")
    @JsonView({ServiceView.ServicioDetallado.class})
    private String destino;
    
    @Column(name = "fecha")
    @JsonView({ServiceView.ServicioDetallado.class})
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "tipo")
    @JsonView({ServiceView.ServicioSimple.class, ServiceView.ServicioDetallado.class})
    private String tipo;
    
    @Column(name = "costo")
    @JsonView({ServiceView.ServicioSimple.class})
    private float costo;

    @OneToMany(mappedBy = "servicio")
    @JsonIgnore
    private List<ServiciosPaquetes> servPaq;
    
    public Servicio() {
    }

    public Servicio(int s_codigo, String s_nombre, String descripcion, String imagen, String destino, Date fecha, String tipo, float costo) {
        this.s_codigo = s_codigo;
        this.s_nombre = s_nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.destino = destino;
        this.fecha = fecha;
        this.costo = costo;
        this.tipo = tipo;
    }

    public int getS_codigo() {
        return s_codigo;
    }

    public void setS_codigo(int s_codigo) {
        this.s_codigo = s_codigo;
    }

    public String getS_nombre() {
        return s_nombre;
    }

    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public List<ServiciosPaquetes> getServPaq() {
        return servPaq;
    }

    public void setServPaq(List<ServiciosPaquetes> servPaq) {
        this.servPaq = servPaq;
    } 
    
}