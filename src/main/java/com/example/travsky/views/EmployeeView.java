package com.example.travsky.views;

/**
 * Clase que define diferentes vistas del objeto Employee para organizar la información acorde el controlador lo pida.
 */
public class EmployeeView {
    
    /**
     * Clase estática que representa la vista simple de la entidad Employee.
     * Contiene solo la información básica del empleado y muestra únicamente DNI, nombre y apellido para otorgar seguridad.
     */
    public static class SimpleEmployee {}
    
    /**
     * Clase estática que representa la vista detallada de la entidad Employee.
     * No se usa en el programa pero en caso de necesitar se podrá usar, objetivo: lograr mostrar todos los datos del empleado garantizando seguridad.
     */
    public static class DetailedEmployee {}
}
